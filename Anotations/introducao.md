# Redes: Fundamentos e Funcionamento

## O que é a Internet

A internet pode ser descrita pelos componentes de software e hardware básicos que a formam ou em termos de uma infraestrutura de redes que fornece serviços para aplicações distribuídas.

### Componentes de Rede (Descrição)

A internet é uma rede de computadores que interconecta bilhões de dispositivos de computação ao redor do mundo. Todos os equipamentos conectados à internet são denominados hospedeiros (hosts) ou sistemas finais. Sistemas finais são conectados entre si por enlaces (links) de comunicação e comutadores (switches) de pacotes (enlaces de comunicação são constituídos de diferentes tipos de meios físicos, e a taxa de transmissão de um enlace é medida em bits por segundo).

Quando um sistema final possui dados para enviar a outro sistema final, o sistema emissor segmenta esses dados e adiciona bytes de cabeçalho a cada segmento. Os blocos de informação resultantes (pacotes) são enviados através da rede ao sistema final de destino, onde são remontados na forma dos dados originais. Os dois comutadores de pacotes mais presentes na internet hoje são roteadores e switches. Os switches geralmente são utilizados em redes locais, enquanto os roteadores são utilizados principalmente na parte mais interna da rede. A sequência de enlaces de comunicação e nós de comutação de pacotes que um pacote percorre desde o sistema final remetente até o sistema final receptor é conhecida como rota ou caminho através da rede.

Sistemas finais acessam a internet por meio de ISPs (Internet Service Providers). Os sistemas finais, os nós de comutação e outras peças da internet executam protocolos que controlam o envio e o recebimento de informações, sendo os principais o TCP (Transmission Control Protocol) e o IP (Internet Protocol). Padrões da internet são desenvolvidos pela IETF (Internet Engineering Task Force), e os documentos padronizados da IETF são denominados RFCs (Request for Comments).

---

## Uma Descrição dos Serviços

Aplicações distribuídas envolvem diversos sistemas finais que trocam informações manualmente. As aplicações da internet são executadas em sistemas finais e não em nós de comutação no núcleo da rede. Os sistemas finais ligados à internet oferecem uma interface de socket que especifica como o programa que é executado no sistema final solicita à infraestrutura da internet que envie dados a um programa de destino específico, executado em outro sistema final.

---

## Protocolos

Todas as atividades da internet que envolvem duas ou mais entidades remotas comunicantes são governadas por um protocolo. Um protocolo define o formato e a ordem das mensagens trocadas entre duas ou mais entidades comunicantes, bem como as ações realizadas na transmissão e/ou no recebimento de uma mensagem ou outro evento.

---

## O Núcleo da Rede

### Comutação de Pacotes

Para enviar uma mensagem de um sistema final de origem para um destino, o originador fragmenta a mensagem longa em porções de dados menores, denominados pacotes. Entre origem e destino, cada pacote percorre enlaces de comunicação e nós de comutação de pacotes (roteadores e switches).

Pacotes são transmitidos por cada enlace de comunicação a uma taxa igual à de transmissão total. Assim, se um sistema final de origem ou um nó de comutação de pacotes estiver enviando um pacote de L bits por um enlace com taxa de transmissão de R bits/s, então o tempo para transmitir o pacote é L/R segundos.

### Transmissão Armazena-e-Reenvia

A transmissão armazena-e-reenvia (store-and-forward) significa que o nó de comutação de pacotes deve receber o pacote inteiro antes de poder começar a transmitir o primeiro bit para o enlace de saída. Para isso, partes do pacote são mantidas em buffer até que todos estejam presentes, e a transmissão dos bits recebidos possa ser feita.

### Atrasos de Fila e Perda de Pacote

Os buffers de saída desempenham um papel fundamental na comutação de pacotes. Se um pacote que está chegando precisa ser transmitido por um enlace, mas o encontra ocupado com a transmissão de outro pacote, deve aguardar no buffer de saída. Além dos atrasos de armazenagem e reenvio, os pacotes sofrem atrasos de fila no buffer de saída (esses atrasos são variáveis e dependem do grau de congestionamento da rede). Como o espaço do buffer é finito, um pacote que está chegando pode encontrar o buffer lotado de outros que estão esperando pela transmissão. Nesse caso, ocorrerá uma perda de pacote (um pacote que está chegando ou um dos que está na fila é descartado).

### Tabelas de Repasse e Protocolos de Roteamento

Quando um pacote chega a um roteador na rede, este examina uma parte do endereço de destino e o conduz a um roteador adjacente. Mais especificamente, cada roteador possui uma tabela de repasse que mapeia os endereços de destino (ou parte deles) para enlaces de saída desse roteador. Quando um pacote chega a um roteador, este examina o endereço e pesquisa sua tabela de repasse, utilizando esse endereço de destino para encontrar o enlace de saída apropriado. O roteador, então, direciona o pacote a esse enlace de saída.

### Comutação de Circuitos e Comutação de Pacotes

Nas redes de comutação de circuitos, os recursos necessários ao longo de um caminho (buffers, taxa de transmissão de enlaces) para oferecer comunicação entre os sistemas finais são reservados pelo período da sessão de comunicação entre os sistemas finais. Em redes de comutação de pacotes, tais recursos não são reservados; as mensagens de uma sessão usam os recursos por demanda e, como consequência, poderão ter de esperar para conseguir acesso a um enlace de comunicação.

Os defensores da comutação de pacotes sempre argumentam que a comutação de circuitos é desperdício, porque os circuitos dedicados ficam ociosos durante períodos de silêncio (exemplo: quando um dos participantes de uma conversa telefônica para de falar).

---

## Tipos de Atrasos

### Atraso de Processamento

O tempo exigido para examinar o cabeçalho do pacote e determinar para onde direcioná-lo é parte do atraso de processamento, que pode também incluir outros fatores, como o tempo necessário para verificar os erros em bits existentes no pacote que ocorreram durante a transmissão dos bits desde o nó de origem ao roteador.

### Atraso de Fila

Ocorre com o pacote enquanto espera para ser transmitido no enlace. O tamanho desse atraso dependerá da quantidade de outros pacotes que chegarem antes e que já estiverem na fila esperando pela transmissão no enlace.

### Atraso de Transmissão

Se os pacotes são transmitidos seguindo a estratégia de 'o primeiro a chegar será o primeiro a ser processado', como é comum em redes de comutação de pacotes, o nosso pacote somente poderá ser transmitido depois de todos os que chegaram antes terem sido enviados.

### Atraso de Propagação

Assim que é lançado no enlace, um bit precisa se propagar até o roteador B. O tempo necessário para propagar o bit desde o início do enlace até o roteador B é o atraso de propagação. O atraso de propagação é a distância entre dois roteadores dividida pela velocidade de propagação.


---

## Arquiteturas de Camadas

### Camada de Aplicação

A pilha de protocolos da internet é formada por cinco camadas: física, de enlace, de rede, de transporte e de aplicação.

#### Camada de Aplicação

Residem aplicações de rede e seus protocolos. A camada de aplicação da internet inclui muitos protocolos, tais como HTTP, SMTP e o FTP. Um protocolo de camada de aplicação é distribuído por diversos sistemas finais, e a aplicação em um sistema final utiliza o protocolo para trocar pacotes de informação (mensagens) com a aplicação em outro sistema final.

#### Camada de Transporte

Carrega mensagens da camada de aplicação entre os lados do cliente e servidor de uma aplicação. Há dois protocolos de transporte na internet: TCP e UDP, e qualquer um pode levar mensagens da camada de aplicação.

O TCP fornece serviços orientados à conexão para suas aplicações. Alguns desses serviços são a entrega garantida de mensagens da camada de aplicação ao destino e controle de fluxo. O TCP também fragmenta mensagens longas em segmentos mais curtos e fornece mecanismos de controle de congestionamento, de modo que uma origem reduza sua velocidade de transmissão quando a rede está congestionada.

O protocolo UDP fornece serviço não orientado à conexão para suas aplicações. É um serviço econômico que não oferece confiabilidade, nem controle de fluxo ou de congestionamento.

#### Camada de Rede

É responsável pela movimentação, de um hospedeiro para outro, de pacotes da camada de rede, conhecidos como datagramas. O protocolo de camada de transporte da internet (TCP ou UDP) em um hospedeiro de origem passa um segmento da camada de transporte e um endereço de destino à camada de rede.
A camada de rede, então, fornece o serviço de entrega do segmento à camada de transporte no hospedeiro de destino.

Essa camada inclui o protocolo IP, que define os campos no datagrama e o modo como os sistemas finais e os roteadores agem nesses campos. Existe apenas um único protocolo IP, e todos os componentes da internet que têm uma camada de rede devem executá-lo.

#### Camada de Enlace

Para levar um pacote de um nó (hospedeiro ou roteador) ao nó seguinte na rota, a camada de rede depende dos serviços da camada de enlace. Em cada nó, a camada de rede passa o datagrama para a camada de enlace, que o encaminha, ao longo da rota, ao nó seguinte, no qual o datagrama é passado da camada de enlace para a de rede.

Os serviços prestados pela camada de enlace dependem do protocolo específico empregado no enlace.

Exemplos de protocolos de camadas de enlace são Ethernet, Wi-Fi e o protocolo DOCSIS da rede de acesso por cabeamento.
Pacotes de camada de enlace = quadros.

#### Camada Física

Enquanto a camada de enlace movimenta quadros inteiros de um elemento da rede até um elemento adjacente, a função da camada física é movimentar os bits individuais que estão dentro do quadro de um nó para o seguinte.

Os protocolos nessa camada dependem também do enlace e além disso do próprio meio de transmissão do enlace.

O Ethernet, por exemplo, tem muitos protocolos de camada física, um para fios de cobre trançado, outro para cabo coaxial, mais um para fibra e assim por diante. Em cada caso, o bit atravessa o enlace de um modo diferente.

