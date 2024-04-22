# TRANSPORTE NÃO ORIENTADO PARA CONEXÃO: UDP

O UDP pega as mensagens do processo da aplicação, anexa os campos de número da porta de origem e de destino para o serviço de multiplexação/demultiplexação, adiciona dois outros pequenos campos e passa o segmento resultante à camada de rede, que encapsula o segmento dentro de um datagrama IP e, em seguida, faz a melhor tentativa para entregar o segmento ao hospedeiro receptor. Se o segmento chegar ao hospedeiro receptor, o UDP usará o número de porta de destino para entregar os dados do segmento ao processo de aplicação correto.

Com o UDP, não há apresentação entre as entidades remetente e destinatária da camada de transporte antes de enviar um segmento. Por essa razão, dizemos que o UDP é não orientado para conexão.

## Estrutura do segmento UDP

O segmento UDP contém uma mensagem de consulta ou uma mensagem de resposta. Para uma aplicação de recepção de áudio, amostras de áudio preenchem o campo de dados. O cabeçalho UDP tem apenas quatro campos, cada um consistindo em 2 bytes.

O campo de comprimento especifica o número de bytes no segmento UDP (cabeçalho mais dados). Um valor de comprimento explícito é necessário porque o tamanho do campo de dados pode ser diferente de um segmento UDP para o outro. A soma de verificação é usada pelo hospedeiro receptor para verificar se foram introduzidos erros no segmento.

## Soma de verificação UDP

A soma de verificação UDP é usada para determinar se bits dentro do segmento UDP foram alterados (por exemplo, por ruído nos enlaces ou enquanto armazenados em um roteador) durante sua movimentação da origem até o destino.

Exemplo de cálculo de soma de verificação:

```
0110011001100000
0101010101010101
1000111100001100
```

A soma das duas primeiras é:

```
0110011001100000
0101010101010101
1011101110110101
```

Adicionando a terceira palavra à soma anterior, temos:

```
1011101110110101
1000111100001100
0100101011000010
```

Note que a última adição teve um "vai um" no bit mais significativo que foi somado ao bit menos significativo. O complemento de 1 é obtido pela conversão de todos os 0 em 1 e de todos os 1 em 0. Desse modo, o complemento de 1 da soma `0100101011000010` é `1011010100111101`, que passa a ser a soma de verificação. No destinatário, todas as quatro palavras de 16 bits são somadas, inclusive a soma de verificação. Se nenhum erro for introduzido no pacote, a soma no destinatário será, claro, `1111111111111111`. Se um dos bits for um zero, saberemos então que um erro foi introduzido no pacote.
