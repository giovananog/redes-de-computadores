# PRINCÍPIOS DA TRANSFERÊNCIA CONFIÁVEL DE DADOS

O problema de realizar transferência confiável de dados ocorre não só na camada de transporte, mas também na de enlace e na de aplicação.

Com um canal confiável, nenhum dos dados transferidos é corrompido (trocado de 0 para 1 ou vice-versa) nem perdido, e todos são entregues na ordem em que foram enviados. Este é exatamente o modelo de serviço oferecido pelo TCP às aplicações de Internet que recorrem a ele.

É responsabilidade de um protocolo de transferência confiável de dados implementar essa abstração de serviço. A tarefa é dificultada pelo fato de que a camada abaixo do protocolo de transferência confiável de dados talvez seja não confiável. Por exemplo, o TCP é um protocolo confiável de transferência de dados que é executado sobre uma camada de rede fim a fim não confiável (IP). De modo mais geral, a camada abaixo das duas extremidades que se comunicam de modo confiável pode consistir em um único enlace físico (como no caso de um protocolo de transferência de dados na camada de enlace) ou em uma rede global interligada (como em um protocolo de camada de transporte).

## Construindo um protocolo de transferência confiável de dados
