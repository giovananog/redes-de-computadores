**Arquiteturas de Aplicação de Rede**

A arquitetura de uma aplicação difere significativamente da arquitetura da rede. Do ponto de vista do desenvolvedor, a arquitetura de rede é estática e fornece um conjunto específico de serviços. Por outro lado, a arquitetura da aplicação é projetada pelo programador, determinando a organização nos diversos sistemas finais.

Ao escolher a arquitetura da aplicação, é comum utilizar uma das arquiteturas mais populares em aplicações modernas de rede: Cliente-Servidor ou P2P.

**Cliente-Servidor**

Na arquitetura Cliente-Servidor, um hospedeiro sempre em funcionamento, chamado servidor, atende a solicitações de vários outros hospedeiros, denominados clientes.

Nesta arquitetura, os clientes não se comunicam diretamente entre si. Por exemplo, em uma aplicação web, dois navegadores não se comunicam diretamente. Além disso, o servidor possui um endereço IP fixo bem conhecido.

Exemplos de aplicações Cliente-Servidor incluem Web, FTP, Telnet e email. Geralmente, um único servidor pode não ser capaz de atender a todas as solicitações, levando à necessidade de datacenters.

**P2P**

Na arquitetura P2P, há uma dependência mínima de servidores dedicados em datacenters. A aplicação utiliza a comunicação direta entre pares de hospedeiros conectados alternadamente, chamados pares.

Uma característica marcante é a autoescalabilidade, além de uma boa relação custo-benefício, pois geralmente não requerem infraestrutura e largura de banda significativas de servidores. No entanto, devido à sua estrutura altamente descentralizada, as aplicações P2P enfrentam desafios de segurança, desempenho e confiabilidade.

---

**Comunicação entre Processos**

Um processo é definido como um programa em execução dentro de um sistema final. Processos em dois sistemas finais diferentes se comunicam trocando mensagens pela rede de computadores. Um processo originador cria e envia mensagens; um processo destinatário as recebe e responde.

**Processo Cliente e Processo Servidor**

Na comunicação entre um par de processos, aquele que inicia é o cliente, e o que espera ser contatado é o servidor. Na web, um navegador é um processo cliente, enquanto um servidor web é um processo servidor. No compartilhamento de arquivos P2P, a entidade que envia é rotulada como cliente, e a que recebe, como servidor.

**Interface entre Processo e Rede de Computadores**

Um processo se comunica com a rede por meio de uma interface de software chamada socket (semelhante a uma porta da casa para uma casa). O socket é a interface entre a camada de aplicação e a de transporte em um hospedeiro, também conhecido como Interface de Programação de Aplicação (API).

**Endereçamento de Processos**

Para um processo em um hospedeiro enviar pacotes a um processo em outro hospedeiro, o receptor precisa de um endereço. No caso da internet, o hospedeiro é identificado por seu endereço IP, e o processo receptor é identificado pelo número de porta de destino.

---

**Serviços de Transporte Disponíveis para Aplicações**

