# INTRODUÇÃO E SERVIÇOS DE CAMADA DE TRANSPORTE

Um protocolo da camada de transporte fornece comunicação lógica entre processos de aplicação que rodam em hospedeiros diferentes. Comunicação lógica nesse contexto significa que, do ponto de vista de uma aplicação, tudo se passa como se os hospedeiros que rodam os processos estivessem conectados diretamente; na verdade, eles poderão estar em lados opostos do planeta, conectados por diversos roteadores e uma ampla variedade de tipos de enlace. Processos de aplicação usam a comunicação lógica fornecida pela camada de transporte para enviar mensagens entre si, livres da preocupação dos detalhes da infraestrutura física utilizada para transportá-las.

Protocolos da camada de transporte são implementados nos sistemas finais, mas não em roteadores de rede. No lado remetente, a camada de transporte converte as mensagens que recebe de um processo de aplicação remetente em pacotes de camada de transporte, denominados segmentos de camada de transporte na terminologia da Internet.

## Relação entre as camadas de transporte e de rede

A camada de transporte se situa logo acima da de rede na pilha de protocolos. Enquanto um protocolo de camada de transporte fornece comunicação lógica entre processos que rodam em hospedeiros diferentes, um protocolo de camada de rede fornece comunicação lógica entre hospedeiros.

## Visão geral da camada de transporte na Internet

A Internet — e, de maneira mais geral, a rede TCP/IP — disponibiliza dois protocolos de transporte distintos para a camada de aplicação. Um deles é o UDP (User Datagram Protocol — Protocolo de Datagrama de Usuário), que oferece à aplicação solicitante um serviço não confiável, não orientado para conexão. O segundo é o TCP (Transmission Control Protocol — Protocolo de Controle de Transmissão), que oferece à aplicação solicitante um serviço confiável, orientado para conexão.

A responsabilidade fundamental do UDP e do TCP é ampliar o serviço de entrega IP entre dois sistemas finais para um serviço de entrega entre dois processos que rodam nos sistemas finais. A ampliação da entrega hospedeiro a hospedeiro para entrega processo a processo é denominada multiplexação/demultiplexação de camada de transporte.
