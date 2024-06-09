# Arquiteturas de Aplicação de Rede

A arquitetura de uma aplicação difere significativamente da arquitetura da rede. Do ponto de vista do desenvolvedor, a arquitetura de rede é estática e fornece um conjunto específico de serviços. Por outro lado, a arquitetura da aplicação é projetada pelo programador, determinando a organização nos diversos sistemas finais.

Ao escolher a arquitetura da aplicação, é comum utilizar uma das arquiteturas mais populares em aplicações modernas de rede: Cliente-Servidor ou P2P.

## Cliente-Servidor

Na arquitetura Cliente-Servidor, um hospedeiro sempre em funcionamento, chamado servidor, atende a solicitações de vários outros hospedeiros, denominados clientes.

Nesta arquitetura, os clientes não se comunicam diretamente entre si. Por exemplo, em uma aplicação web, dois navegadores não se comunicam diretamente. Além disso, o servidor possui um endereço IP fixo bem conhecido.

Exemplos de aplicações Cliente-Servidor incluem Web, FTP, Telnet e email. Geralmente, um único servidor pode não ser capaz de atender a todas as solicitações, levando à necessidade de datacenters.

## P2P

Na arquitetura P2P, há uma dependência mínima de servidores dedicados em datacenters. A aplicação utiliza a comunicação direta entre pares de hospedeiros conectados alternadamente, chamados pares.

Uma característica marcante é a autoescalabilidade, além de uma boa relação custo-benefício, pois geralmente não requerem infraestrutura e largura de banda significativas de servidores. No entanto, devido à sua estrutura altamente descentralizada, as aplicações P2P enfrentam desafios de segurança, desempenho e confiabilidade.

# Comunicação entre Processos

Um processo é definido como um programa em execução dentro de um sistema final. Processos em dois sistemas finais diferentes se comunicam trocando mensagens pela rede de computadores. Um processo originador cria e envia mensagens; um processo destinatário as recebe e responde.

## Processo Cliente e Processo Servidor

Na comunicação entre um par de processos, aquele que inicia é o cliente, e o que espera ser contatado é o servidor. Na web, um navegador é um processo cliente, enquanto um servidor web é um processo servidor. No compartilhamento de arquivos P2P, a entidade que envia é rotulada como cliente, e a que recebe, como servidor.

## Interface entre Processo e Rede de Computadores

Um processo se comunica com a rede por meio de uma interface de software chamada socket (semelhante a uma porta da casa para uma casa). O socket é a interface entre a camada de aplicação e a de transporte em um hospedeiro, também conhecido como Interface de Programação de Aplicação (API).

## Endereçamento de Processos

Para um processo em um hospedeiro enviar pacotes a um processo em outro hospedeiro, o receptor precisa de um endereço. No caso da internet, o hospedeiro é identificado por seu endereço IP, e o processo receptor é identificado pelo número de porta de destino.

# Serviços de Transporte Disponíveis para Aplicações

Um socket é a interface entre o processo da aplicação e o protocolo de camada de transporte. A aplicação do lado remetente envia mensagens por meio do socket. Do outro lado, o protocolo de camada de transporte tem a responsabilidade de levar as mensagens pela rede até o socket do processo destinatário.

Muitas redes, inclusive a Internet, oferecem mais de um protocolo de camada de transporte. Ao desenvolver uma aplicação, você deve escolher um dos protocolos de camada de transporte disponíveis. 

## Transferência Confiável de Dados

Os pacotes podem se perder dentro de uma rede de computadores. Um pacote pode, por exemplo, esgotar um buffer em um roteador, ou ser descartado por um hospedeiro ou um roteador após alguns de seus bits terem sido corrompidos. 

Um importante serviço que o protocolo da camada de transporte pode oferecer para uma aplicação é a transferência confiável de dados processo a processo. Quando um protocolo de transporte oferece esse serviço, o processo remetente pode apenas passar seus dados para um socket e saber com absoluta confiança que eles chegarão sem erro ao processo destinatário.

## Vazão

O conceito de vazão disponível, no contexto de sessão da comunicação entre dois processos ao longo de um caminho de rede, é a taxa pela qual o processo remetente pode enviar bits ao processo destinatário. Como outras sessões compartilharão a largura de banda no caminho da rede e estão indo e voltando, a vazão disponível pode oscilar com o tempo.

Aplicações que possuam necessidade de vazão são conhecidas como aplicações sensíveis à largura de banda. Muitas aplicações de multimídia existentes são sensíveis à largura de banda, embora algumas possam usar técnicas adaptativas para codificar a uma taxa que corresponda à vazão disponível na ocasião.

Embora aplicações sensíveis à largura de banda possuam necessidades específicas de vazão, aplicações elásticas podem usar qualquer quantidade mínima ou máxima que por acaso esteja disponível. Correio eletrônico, transferência de arquivos e transferências Web são todas aplicações elásticas. Claro, quanto mais vazão, melhor.

## Temporização

Um protocolo da camada de transporte pode também oferecer garantias de temporização. Como nas garantias de vazão, as de temporização podem surgir em diversos aspectos e modos.

Longos atrasos na telefonia por Internet, por exemplo, tendem a resultar em pausas artificiais na conversação; em um jogo multiusuário ou ambiente virtual interativo, um longo atraso entre realizar uma ação e ver a reação do ambiente (por exemplo, a reação de outro jogador na outra extremidade de uma conexão fim a fim) faz a aplicação parecer menos realista.

Para aplicações que não são em tempo real, é sempre preferível um atraso menor a um maior, mas não há nenhuma limitação estrita aos atrasos fim a fim.

## Segurança

Por fim, um protocolo de transporte pode oferecer um ou mais serviços de segurança a uma aplicação. Por exemplo, no hospedeiro remetente, um protocolo de transporte é capaz de codificar todos os dados transmitidos pelo processo remetente e, no hospedeiro destinatário, o protocolo da camada de transporte pode codificar os dados antes de enviá-los ao destinatário. Tal serviço pode oferecer sigilo entre os dois, mesmo que os dados sejam, de algum modo, observados entre os processos remetente e destinatário. A Internet hoje pode oferecer serviços satisfatórios a aplicações sensíveis ao tempo, mas não garantias de temporização ou de largura de banda.

# Protocolos de Camada de Aplicação

Um protocolo de camada de aplicação define como processos de uma aplicação, que funcionam em sistemas finais diferentes, passam mensagens entre si. Em particular, um protocolo de camada de aplicação define:

- Os tipos de mensagens trocadas, por exemplo, de requisição e de resposta.
- A sintaxe dos vários tipos de mensagens, tais como os campos da mensagem e como os campos são delineados.
- A semântica dos campos, isto é, o significado da informação nos campos.
- Regras para determinar quando e como um processo envia mensagens e responde a mensagens.

Alguns protocolos de camada de aplicação estão especificados em RFCs e, portanto, são de domínio público. Por exemplo, o protocolo de camada de aplicação da Web, HTTP (HyperText Transfer Protocol [RFC 2616]), está à disposição como um RFC.
