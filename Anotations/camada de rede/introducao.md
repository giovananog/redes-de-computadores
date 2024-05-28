# Introdução

O papel primordial dos roteadores é repassar datagramas de enlaces de entrada para enlaces de saída.

## Repasse e Roteamento

O papel da camada de rede é aparentemente simples: transportar pacotes de um hospedeiro remetente a um hospedeiro destinatário. Para fazê-lo, duas importantes funções da camada de rede podem ser identificadas:

- **Repasse**: Quando um pacote chega ao enlace de entrada de um roteador, este deve conduzi-lo até o enlace de saída apropriado.
- **Roteamento**: A camada de rede deve determinar a rota ou o caminho tomado pelos pacotes ao fluírem de um remetente a um destinatário. Os algoritmos que calculam esses caminhos são denominados algoritmos de roteamento. Um algoritmo de roteamento determinaria, por exemplo, o caminho pelo qual os pacotes fluiriam de H1 para H2.

Repasse refere-se à ação local realizada por um roteador para transferir um pacote da interface de um enlace de entrada para a interface de enlace de saída apropriada. Roteamento refere-se ao processo de âmbito geral da rede que determina os caminhos fim a fim que os pacotes percorrem desde a origem até o destino.

Cada roteador tem uma tabela de repasse. Um roteador repassa um pacote examinando o valor de um campo no cabeçalho do pacote que está chegando e então utiliza esse valor para indexar sua tabela de repasse. O resultado da tabela de repasse indica para qual das interfaces de enlace do roteador o pacote deve ser repassado. Dependendo do protocolo de camada de rede, o valor no cabeçalho do pacote pode ser o endereço de destino do pacote ou uma indicação da conexão à qual ele pertence.

O algoritmo de roteamento determina os valores que são inseridos nas tabelas de repasse dos roteadores. Esse algoritmo pode ser centralizado (por exemplo, com um algoritmo que roda em um local central e descarrega informações de roteamento a cada um dos roteadores) ou descentralizado (isto é, com um pedaço do algoritmo de roteamento distribuído funcionando em cada roteador).

Em qualquer dos casos, um roteador recebe mensagens de protocolo de roteamento que são utilizadas para configurar sua tabela de repasse. As finalidades distintas e diferentes das funções de repasse e roteamento podem ser mais bem esclarecidas considerando o caso hipotético (e não realista, mas tecnicamente viável) de uma rede na qual todas as tabelas de repasse são configuradas diretamente por operadores de rede humanos, fisicamente presentes nos roteadores. Nesse caso, não seria preciso nenhum protocolo de roteamento! É claro que os operadores humanos precisariam interagir uns com os outros para garantir que as tabelas fossem configuradas de tal modo que os pacotes chegassem a seus destinos pretendidos.

Reservaremos o termo comutador de pacotes para designar um dispositivo geral de comutação de pacotes que transfere um pacote de interface de enlace de entrada para interface de enlace de saída conforme o valor que está em um campo no cabeçalho do pacote. Alguns comutadores de pacotes, denominados comutadores de camada de enlace, baseiam a decisão de repasse no valor que está no campo da camada de enlace. Outros, denominados roteadores, baseiam sua decisão de repasse no valor que está no campo de camada de rede. Os roteadores, portanto, são dispositivos da camada de rede (camada 3), mas também devem utilizar protocolos da camada 2, pois os dispositivos da camada 3 exigem os serviços da camada 2 para implementar sua funcionalidade (camada 3).

## Estabelecimento de Conexão

Acabamos de dizer que a camada de rede tem duas funções importantes, repasse e roteamento. Mas logo veremos que em algumas redes de computadores há uma terceira função importante, a saber, o estabelecimento de conexão. Lembre-se de que, quando estudamos o TCP, verificamos que é necessária uma apresentação de três vias antes de os dados realmente poderem fluir do remetente ao destinatário. Isso permite que o remetente e o destinatário estabeleçam a informação de estado necessária (por exemplo, número de sequência e tamanho inicial da janela de controle de fluxo). De modo semelhante, algumas arquiteturas de camada de rede — por exemplo, ATM, frame-relay e MPLS — exigem que roteadores ao longo do caminho escolhido desde a origem até o destino troquem mensagens entre si com a finalidade de estabelecer estado antes que pacotes de dados de camada de rede dentro de uma dada conexão origem-destino possam começar a fluir. Na camada de rede, esse processo é denominado estabelecimento de conexão.

## Modelos de Serviço de Rede

O modelo de serviço de rede define as características do transporte de dados fim a fim entre uma borda da rede e a outra, isto é, entre sistemas finais remetente e destinatário.

A camada de rede da Internet fornece um único modelo de serviço, conhecido como serviço de melhor esforço. Com o serviço de melhor esforço, não há garantia de que a temporização entre pacotes seja preservada, não há garantia de que os pacotes sejam recebidos na ordem em que foram enviados e não há garantia da entrega final dos pacotes transmitidos. Dada essa definição, uma rede que não entregasse nenhum pacote ao destinatário satisfaria a definição de serviço de entrega de melhor esforço. Contudo, como discutiremos adiante, há razões sólidas para esse modelo minimalista de serviço de camada de rede.

Outras arquiteturas de rede definiram e puseram em prática modelos de serviço que vão além do serviço de melhor esforço da Internet. Por exemplo, a arquitetura de rede ATM habilita vários modelos de serviço, o que significa que, dentro da mesma rede, podem ser oferecidas conexões diferentes com classes de serviço diferentes.
