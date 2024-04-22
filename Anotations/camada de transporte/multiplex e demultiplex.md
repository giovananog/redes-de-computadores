# MULTIPLEXAÇÃO E DEMULTIPLEXAÇÃO

O serviço de multiplexação/demultiplexação é necessário para todas as redes de computadores.

No hospedeiro de destino, a camada de transporte recebe segmentos da camada de rede logo abaixo dela e tem a responsabilidade de entregar os dados desses segmentos ao processo de aplicação apropriado que roda no hospedeiro.

Um processo (como parte de uma aplicação de rede) pode ter um ou mais sockets, portas pelas quais dados passam da rede para o processo e do processo para a rede. Assim, a camada de transporte do hospedeiro destinatário na verdade não entrega dados diretamente a um processo, mas a um socket intermediário. Como, a qualquer dado instante, pode haver mais de um socket no hospedeiro destinatário, cada um tem um identificador exclusivo. O formato do identificador depende de o socket ser UDP ou TCP.

Agora, vamos considerar como um hospedeiro destinatário direciona, ao socket apropriado, um segmento de camada de transporte que chega. Cada segmento de camada de transporte tem um conjunto de campos para tal finalidade. Na extremidade receptora, a camada de transporte examina esses campos para identificar a porta receptora e direcionar o segmento a esse socket. A tarefa de entregar os dados contidos em um segmento da camada de transporte ao socket correto é denominada demultiplexação.

O trabalho de reunir, no hospedeiro de origem, partes de dados provenientes de diferentes sockets, encapsular cada parte de dados com informações de cabeçalho (que mais tarde serão usadas na demultiplexação) para criar segmentos, e passar esses segmentos para a camada de rede é denominada multiplexação.

É importante notar que um socket UDP é totalmente identificado por uma tupla com dois elementos, consistindo em um endereço IP de destino e um número de porta de destino. Por conseguinte, se dois segmentos UDP tiverem endereços IP de origem e/ou números de porta de origem diferentes, porém o mesmo endereço IP de destino e o mesmo número de porta de destino, eles serão direcionados ao mesmo processo de destino por meio do mesmo socket de destino.
