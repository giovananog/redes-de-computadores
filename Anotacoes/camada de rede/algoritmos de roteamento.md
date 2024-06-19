___

# Algoritmos de Roteamento

A tarefa do roteamento é determinar bons caminhos (ou rotas) entre remetentes e destinatários através da rede de roteadores.

## Conceito de Grafo

Um grafo é usado para formular problemas de roteamento. Um grafo \( G = (N, E) \) é um conjunto \( N \) de nós e uma coleção \( E \) de arestas, no qual cada aresta é um par de nós do conjunto \( N \). No contexto do roteamento da camada de rede:
- **Nós**: Representam roteadores, onde são tomadas decisões de repasse de pacotes.
- **Arestas**: Representam enlaces físicos entre esses roteadores.

## Tipos de Algoritmos de Roteamento

### Algoritmos de Roteamento Global

Um algoritmo de roteamento global calcula o caminho de menor custo entre uma origem e um destino usando conhecimento completo e global sobre a rede. 

- **Características**:
  - Considera a conectividade entre todos os nós e todos os custos dos enlaces.
  - Exige obtenção prévia dessas informações.
  - Pode ser centralizado ou replicado em vários locais.
  - Frequentemente denominado algoritmo de estado de enlace (link-state — LS), já que deve estar a par dos custos de cada enlace na rede.

### Algoritmos de Roteamento Descentralizado

Em um algoritmo de roteamento descentralizado, o cálculo do caminho de menor custo é realizado de modo iterativo e distribuído. 

- **Características**:
  - Nenhum nó tem informação completa sobre os custos de todos os enlaces da rede.
  - Cada nó começa sabendo apenas os custos dos enlaces diretamente ligados a ele.
  - Por meio de um processo iterativo de cálculo e troca de informações com nós vizinhos, um nó gradualmente calcula o caminho de menor custo até um destino ou conjunto de destinos.

## Classificação dos Algoritmos de Roteamento

### Algoritmos Estáticos e Dinâmicos

#### Algoritmos Estáticos

As rotas mudam muito devagar ao longo do tempo, muitas vezes como resultado de intervenção humana (por exemplo, uma pessoa editando manualmente a tabela de repasse do roteador).

#### Algoritmos Dinâmicos

Mudam os caminhos de roteamento à medida que mudam as cargas de tráfego ou a topologia da rede.

- **Características**:
  - Podem ser rodados periodicamente ou como reação direta a mudanças de topologia ou de custo dos enlaces.
  - Mais sensíveis a mudanças na rede.
  - Mais suscetíveis a problemas como loops de roteamento e oscilação em rotas.

### Algoritmos Sensíveis e Insensíveis à Carga

#### Algoritmos Sensíveis à Carga

Custos de enlace variam dinamicamente para refletir o nível corrente de congestionamento no enlace subjacente.

- **Exemplo Histórico**: Algoritmos de roteamento da ARPAnet [McQuillan, 1980].

#### Algoritmos Insensíveis à Carga

O custo de um enlace não reflete explicitamente seu nível de congestionamento atual.

- **Exemplos**: Algoritmos de roteamento utilizados na Internet hoje, como RIP, OSPF e BGP.
