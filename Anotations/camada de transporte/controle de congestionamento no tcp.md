# CONTROLE DE CONGESTIONAMENTO NO TCP

O TCP provê um serviço de transferência confiável entre dois processos que rodam em hospedeiros diferentes. Outro componente de extrema importância do TCP é seu mecanismo de controle de congestionamento.

O TCP deve usar controle de congestionamento fim a fim em vez de controle assistido pela rede, já que a camada IP não fornece aos sistemas finais realimentação explícita relativa ao congestionamento da rede.

A abordagem adotada pelo TCP é obrigar cada remetente a limitar a taxa à qual enviam tráfego para sua conexão como uma função do congestionamento de rede percebido. Se um remetente TCP perceber que há pouco congestionamento no caminho entre ele e o destinatário, aumentará sua taxa de envio; se perceber que há congestionamento, reduzirá sua taxa de envio.

## Taxa de envio como uma função do congestionamento fim a fim percebido

Para começar, vamos examinar como um remetente TCP limita a taxa de envio à qual envia tráfego para sua conexão. Na Seção 3.5, vimos que cada lado de uma conexão TCP consiste em um buffer de recepção, um buffer de envio e diversas variáveis (LastByteRead, rwnd e assim por diante). O mecanismo de controle de congestionamento que opera no remetente monitora uma variável adicional, a janela de congestionamento. Esta, denominada cwnd, impõe uma limitação à taxa à qual um remetente TCP pode enviar tráfego para dentro da rede. Especificamente, a quantidade de dados não reconhecidos em um hospedeiro não pode exceder o mínimo de cwnd e rwnd, ou seja:

LastByteSent – LastByteAcked ≤ min{cwnd, rwnd}


Como os remetentes TCP determinam suas taxas de envio de um modo que não congestionem mas, ao mesmo tempo, façam uso de toda a largura de banda? Os remetentes TCP são claramente coordenados, ou existe uma abordagem distribuída na qual eles podem ajustar suas taxas de envio baseando-se apenas nas informações locais? O TCP responde a essas perguntas utilizando os seguintes princípios:

- Um segmento perdido implica congestionamento, portanto, a taxa do remetente TCP deve diminuir quando um segmento é perdido.
- Um segmento reconhecido indica que a rede está enviando os segmentos do remetente ao destinatário e, por isso, a taxa do remetente pode aumentar quando um ACK chegar para um segmento não reconhecido antes.
- Busca por largura de banda. Dados os ACKs que indicam um percurso de origem a destino sem congestionamento, e eventos de perda que indicam um percurso congestionado, a estratégia do TCP de ajustar sua taxa de transmissão é aumentar a taxa em resposta aos ACKs que chegam até que ocorra um evento de perda, momento em que a taxa de transmissão diminui.


## Partida Lenta

Quando uma conexão TCP começa, o valor de cwnd costuma ser inicializado em 1 MSS [RFC 3390], resultando em uma taxa inicial de envio de aproximadamente MSS/RTT. Como exemplo, se MSS = 500 bytes e RTT = 200 ms, então a taxa de envio inicial resultante é cerca de 20 kbits/s apenas. Como a largura de banda disponível para a conexão pode ser muito maior do que MSS/RTT, o remetente TCP gostaria de aumentar a quantidade de largura de banda rapidamente. Dessa forma, no estado de partida lenta, o valor de cwnd começa em 1 MSS e aumenta 1 MSS toda vez que um segmento transmitido é reconhecido.

Mas em que momento esse crescimento exponencial termina? A partida lenta apresenta diversas respostas para essa pergunta. Primeiro, se houver um evento de perda (ou seja, um congestionamento) indicado por um esgotamento de temporização, o remetente TCP estabelece o valor de cwnd em 1 e inicia o processo de partida lenta novamente. Ele também estabelece o valor de uma segunda variável de estado, ssthresh (abreviação de “slow start threshold” [limiar de partida lenta]), em cwnd/2 — metade do valor da janela de congestionamento quando este foi detectado. O segundo modo pelo qual a partida lenta pode terminar é ligado diretamente ao valor de ssthresh. Visto que ssthresh é metade do valor de cwnd quando o congestionamento foi detectado pela última vez, pode ser uma atitude precipitada continuar duplicando cwnd ao atingir ou ultrapassar o valor de ssthresh. Assim, quando o valor de cwnd se igualar ao de ssthresh, a partida lenta termina e o TCP é alterado para o modo de prevenção de congestionamento. 

O último modo pelo qual a partida lenta pode terminar é se três ACKs duplicados forem detectados, caso no qual o TCP apresenta uma retransmissão rápida (veja Seção 3.5.4) e entra no estado de recuperação rápida.

## Prevenção de Congestionamento

Ao entrar no estado de prevenção de congestionamento, o valor de cwnd é cerca de metade de seu valor quando o congestionamento foi encontrado pela última vez. Desta forma, em vez de duplicar o valor de cwnd a cada RTT, o TCP adota um método mais conservador e aumenta o valor de cwnd por meio de um único MSS a cada RTT.

Como no caso da partida lenta: o valor de cwnd é ajustado para 1 MSS, e o valor de ssthresh é atualizado para metade do valor de cwnd quando ocorreu o evento de perda. Lembre-se, entretanto, de que um evento de perda também pode ser acionado por um evento ACK duplicado triplo. Neste caso, a rede continua a enviar segmentos do remetente ao destinatário (como indicado pelo recebimento de ACKs duplicados). Portanto, o comportamento do TCP para esse tipo de evento de perda de esgotamento de temporização deve ser menos drástico do que com uma perda de esgotamento de temporização: O TCP reduz o valor de cwnd para metade (adicionando em 3 MSS a mais para contabilizar os ACKs duplicados triplos recebidos) e registra o valor de ssthresh como metade do de cwnd quando os ACKs duplicados triplos foram recebidos. Então, entra-se no estado de recuperação rápida.

## Recuperação Rápida

Na recuperação rápida, o valor de cwnd é aumentado em 1 MSS para cada ACK duplicado recebido no segmento perdido que fez o TCP entrar no modo de recuperação rápida. Mais cedo ou mais tarde, quando um ACK chega ao segmento perdido, o TCP entra no modo de prevenção de congestionamento após reduzir cwnd. Se houver um evento de esgotamento de temporização, a recuperação rápida é alterada para o modo de partida lenta após desempenhar as mesmas ações que a partida lenta e a prevenção de congestionamento: o valor de cwnd é ajustado para 1 MSS, e o valor de ssthresh, para metade do valor de cwnd no momento em que o evento de perda ocorreu.

A recuperação rápida é recomendada, mas não exigida, para o protocolo TCP [RFC 5681]. É interessante o fato de que uma antiga versão do TCP, conhecida como TCP Tahoe, reduzia incondicionalmente sua janela de congestionamento para 1 MSS e entrava na fase de partida lenta após um evento de perda de esgotamento do temporizador ou de ACK duplicado triplo. A versão atual do TCP, a TCP Reno, incluiu a recuperação rápida.

## Controle de Congestionamento no TCP: Retrospectiva

Desconsiderando o período inicial de partida lenta, quando uma conexão se inicia, e supondo que as perdas são indicadas por ACKs duplicados triplos e não por esgotamentos de temporização, o controle de congestionamento no TCP consiste em um aumento linear (aditivo) em cwnd de 1 MMS por RTT e, então, uma redução à metade (diminuição multiplicativa) de cwnd em um evento ACK duplicado triplo. Por esta razão, o controle de congestionamento no TCP é quase sempre denominado aumento aditivo, diminuição multiplicativa (AIMD — Additive-Increase, Multiplicative-Decrease).
