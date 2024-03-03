## Questão 1

Qual o tempo gasto para se transmitir um arquivo de 640 Kbits do host A para o host B através de uma rede de comutação de circuitos?
- Velocidade do link: 1,536 Mbps
- Multiplexação TDM: 24 slots/segundo
- Gastam-se 500 milissegundos para que o caminho seja reservado

### Resposta:

- Tamanho do arquivo: 6400 bits
- Velocidade do link: 1536 Mbps
- Multiplexação: 24 slots/s
- Tempo de reserva: 0.5 s

Reserva-se um slot para a passagem desse arquivo, logo a velocidade desse slot será 1536/24 = 64.
Se o slot reservado transporta 64 Mbps, então um arquivo de 640 Mbps gastará 640/64 = 10s + tempo de reserva = 10.5s.

---

## Questão 2

Suponha que usuários compartilhem um enlace de 1 Mbps, e que o enlace utilize da comutação de circuitos. Sabendo-se que, em média, 10% do tempo os usuários geram dados a uma taxa de 100 Kbps, e que nos 90% restantes do tempo eles tomam café, quantos usuários o enlace conseguirá atender? Dica: Considere que os slots do enlace sempre ficam reservados para os usuários! Não sabemos quando um usuário irá gerar dados!

### Resposta:

- Taxa de geração de dados: 0,1 Mbps
- Enlace: 1 Mbps

\[ \frac{1}{0,1} = 10 \] usuários
