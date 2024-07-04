# Introdução

- O protocolo IP de segurança (IPsec) provê segurança na camada de Rede.
- Muitas instituições usam o IPsec para criar redes virtuais privadas (VPNs) que funcionam em cima da Internet pública.
- Encripta as cargas úteis de todos os datagramas (todos os dados enviados de uma entidade a outra).
  - Conhecido por prover “cobertura total”.

# IPsec e VPNs

- Uma instituição que se estende por diversas regiões geográficas muitas vezes deseja ter sua própria rede IP.
  - Alternativa 1: Empregar uma rede física independente (completamente separada da Internet), chamada de rede privada $$$$$.
  - Alternativa 2: Criar VPNs em cima da Internet, fazendo com que os dados que trafegarem pela Internet sejam antes encriptados.

# Os Protocolos AH e ESP

- Dois protocolos principais dentro do IPsec são:
  - **Cabeçalho de autenticação (AH)**
    - Provê autenticação de origem e integridade, mas não o sigilo.
  - **Carga de Segurança de Encapsulamento (ESP)**
    - Provê autenticação de origem, integridade e sigilo.

# Associações de Segurança

- Antes que um remetente possa enviar datagramas IPsec a um destinatário, é necessário que seja criada uma conexão lógica da camada de rede.
  - **Associação de segurança (SA)**
    - Uma SA é uma conexão lógica simples (unidirecional).
      - Caso ambos os lados da conexão desejem enviar dados, então duas SAs deverão ser criadas.
