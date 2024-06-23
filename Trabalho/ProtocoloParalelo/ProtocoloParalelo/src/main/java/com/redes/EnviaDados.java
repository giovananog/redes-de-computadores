package com.redes;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnviaDados extends Thread {

    private final int portaLocalEnvio = 2000;
    private final int portaDestino = 2001;
    private final int portaLocalRecebimento = 2003;
    private Semaphore sem;
    private final String funcao;
    private static List<Pacote> pacotes;
    private static int totalPacotes;
    private int pacotesConfirmados;

    public EnviaDados(Semaphore sem, String funcao) {
        super(funcao);
        this.sem = sem;
        this.funcao = funcao;
        pacotes = new ArrayList<>();
        totalPacotes = 0;
        pacotesConfirmados = 0;
    }

    public String getFuncao() {
        return funcao;
    }

    private void enviaPct(Pacote pacote, boolean reenvio) {
        ByteBuffer byteBuffer = ByteBuffer.allocate((pacote.getDados().length + 1) * 4);
        byteBuffer.putInt(pacote.getNumeroSequencia());
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(pacote.getDados());

        byte[] buffer = byteBuffer.array();

        try {

            if (!reenvio) {
//                System.out.println("Semaforo: " + sem.availablePermits());
                sem.acquire();
//                System.out.println("Semaforo: " + sem.availablePermits());
            }

            InetAddress address = InetAddress.getByName("localhost");
            try (DatagramSocket datagramSocket = new DatagramSocket(portaLocalEnvio)) {
                DatagramPacket packet = new DatagramPacket(
                        buffer, buffer.length, address, portaDestino);

                datagramSocket.send(packet);
                System.out.println("[->] Dado enviado: " + pacote.getNumeroSequencia());

                pacote.startTimer(500, new TimerTask() {
                    @Override
                    public void run() {
                        if (!pacote.isConfirmado()) {
                            System.out.println("[!!] Timeout do pacote: " + pacote.getNumeroSequencia());
                            System.out.println("[->] Dado reenviado: " + pacote.getNumeroSequencia());
                            enviaPct(pacote, true);
                        }
                    }
                });
            }

        } catch (SocketException ex) {
            Logger.getLogger(EnviaDados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(EnviaDados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void divideArquivo(String nomeArquivo) {
        try (FileInputStream fileInput = new FileInputStream(nomeArquivo)) {
            int[] dados = new int[349];
            int cont = 0;

            int numeroSequencia = 0;

            int lido;
            while ((lido = fileInput.read()) != -1) {
                dados[cont] = lido;
                cont++;

                if (cont == 349) {
                    Pacote pacote = new Pacote(numeroSequencia, dados.clone());
                    pacotes.add(pacote);
                    totalPacotes++;
                    numeroSequencia++;
                    dados = new int[349];
                    cont = 0;
                }
            }

            if (cont > 0) {
                for (int i = cont; i < 349; i++) {
                    dados[i] = -1;
                }
                Pacote pacoteFinal = new Pacote(numeroSequencia, dados.clone());
                pacotes.add(pacoteFinal);
                totalPacotes++;
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        switch (this.getFuncao()) {
            case "envia":
                divideArquivo("entrada.txt");

                // Check if all packets are already confirmed before sending any
                boolean allConfirmed = true;
                for (Pacote pacote : pacotes) {
                    if (!pacote.isConfirmado()) {
                        allConfirmed = false;
                        break;
                    }
                }

                if (allConfirmed) {
                    System.out.println("Todos os pacotes já foram confirmados. Não há necessidade de enviar novamente.");
                    return;
                }

                for (Pacote pacote : pacotes) {
                    if (!pacote.isConfirmado()) {
                        enviaPct(pacote, false);
                    }
                }
                break;
            case "ack":
                try {
                    DatagramSocket serverSocket = new DatagramSocket(portaLocalRecebimento);
                    byte[] receiveData = new byte[4];
                    while (true) {
                        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        serverSocket.receive(receivePacket);

                        int numeroSequencia = ByteBuffer.wrap(receivePacket.getData()).getInt();

                        for (Pacote pacote : pacotes) {
                            if (pacote.getNumeroSequencia() == numeroSequencia) {
                                System.out.println("[ok] confirmando pacote: " + pacote.getNumeroSequencia());
                                pacote.setConfirmado(true);
                                pacotesConfirmados++;
                                break;
                            }
                        }

                        sem.release();

                        // Check if all packets are confirmed
                        if (pacotesConfirmados == totalPacotes) {
                            System.out.println("[fim] Todos os pacotes foram confirmados. Parando o envio.");
                            serverSocket.close(); // Close socket and stop receiving further acknowledgments
                            return;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Exceção: " + e.getMessage());
                }
                break;
            default:
                break;
        }

    }
}
