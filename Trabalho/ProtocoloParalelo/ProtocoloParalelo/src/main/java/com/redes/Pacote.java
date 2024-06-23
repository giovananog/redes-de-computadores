package com.redes;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Timer;
import java.util.TimerTask;

public class Pacote {
    private int numeroSequencia;
    private int[] dados;
    private boolean confirmado;
    private Timer timer;
    private TimerTask timerTask;

    public Pacote(int numeroSequencia, int[] dados) {
        this.numeroSequencia = numeroSequencia;
        this.dados = dados;
        this.timer = new Timer();
    }

    public int getNumeroSequencia() {
        return numeroSequencia;
    }

    public void setNumeroSequencia(int numeroSequencia) {
        this.numeroSequencia = numeroSequencia;
    }

    public int[] getDados() {
        return dados;
    }

    public void setDados(int[] dados) {
        this.dados = dados;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
        if (confirmado && timerTask != null) {
            timerTask.cancel();
        }
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public byte[] toByteArray() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(dados.length * 4);
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(dados);

        return byteBuffer.array();
    }

    public void startTimer(long delay, TimerTask task) {
        if (timerTask != null) {
            timerTask.cancel();
        }
        this.timerTask = task;
        timer.schedule(task, delay);
    }
}
