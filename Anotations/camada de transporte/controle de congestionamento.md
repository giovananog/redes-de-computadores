# PRINCÍPIOS DE CONTROLE DE CONGESTIONAMENTO

Esse estudo mais geral do controle de congestionamento é apropriado, já que, como acontece com a transferência confiável de dados, o congestionamento é um dos "dez mais" da lista de problemas fundamentalmente importantes no trabalho em rede.

## Mecanismos de controle de congestionamento

No nível mais amplo, podemos distinguir mecanismos de controle de congestionamento conforme a camada de rede ofereça ou não assistência explícita à camada de transporte com a finalidade de controle de congestionamento:

- **Controle de congestionamento fim a fim**: Nesse método, a camada de rede não fornece nenhum suporte explícito à camada de transporte com a finalidade de controle de congestionamento. Até mesmo a presença de congestionamento na rede deve ser intuída pelos sistemas finais com base apenas na observação do comportamento da rede (por exemplo, perda de pacotes e atraso).

- **Controle de congestionamento assistido pela rede**: Com esse método, os componentes da camada de rede (isto é, roteadores) fornecem retroalimentação específica de informações ao remetente a respeito do estado de congestionamento na rede. Essa retroalimentação pode ser tão simples como um único bit indicando o congestionamento em um enlace.
