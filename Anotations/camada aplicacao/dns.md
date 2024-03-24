# DNS: O SERVIÇO DE DIRETÓRIO DA INTERNET

Assim como seres humanos podem ser identificados de muitas maneiras, o mesmo acontece com hospedeiros da Internet. Um identificador é seu nome de hospedeiro (hostname). Nomes de hospedeiro — como cnn.com, www.yahoo.com, gaia.cs.umass.edu e cis.poly.edu — são fáceis de lembrar e, portanto, apreciados pelos seres humanos. Como nomes de hospedeiros podem consistir em caracteres alfanuméricos de comprimento variável, seriam difíceis de ser processados por roteadores. Por essas razões, hospedeiros também são identificados pelo que denominamos endereços IP.

## Serviços fornecidos pelo DNS

Acabamos de ver que há duas maneiras de identificar um hospedeiro — por um nome de hospedeiro e por um endereço IP. As pessoas preferem o identificador nome de hospedeiro por ser mais fácil de lembrar, ao passo que roteadores preferem endereços IP de comprimento fixo e estruturados hierarquicamente. Para conciliar essas preferências, é necessário um serviço de diretório que traduza nomes de hospedeiro para endereços IP. Esta é a tarefa principal do DNS (Domain Name System) da Internet.

O DNS é (1) um banco de dados distribuído executado em uma hierarquia de servidores de DNS, e (2) um protocolo de camada de aplicação que permite que hospedeiros consultem o banco de dados distribuído. Os servidores DNS são muitas vezes máquinas UNIX que executam o software BIND (Berkeley Internet Name Domain). O protocolo DNS utiliza UDP e usa a porta 53.

Para que a máquina do usuário possa enviar uma mensagem de requisição HTTP ao servidor Web www.someschool.edu, ela precisa primeiro obter o endereço IP. Isso é feito da seguinte maneira:
1. A própria máquina do usuário executa o lado cliente da aplicação DNS.
2. O navegador extrai o nome de hospedeiro, www.someschool.edu, do URL e passa o nome para o lado cliente da aplicação DNS.
3. O cliente DNS envia uma consulta contendo o nome do hospedeiro para um servidor DNS.
4. O cliente DNS por fim recebe uma resposta, que inclui o endereço IP correspondente ao nome de hospedeiro.
5. Tão logo o navegador receba o endereço do DNS, pode abrir uma conexão TCP com o processo servidor HTTP localizado na porta 80 naquele endereço IP.

O DNS provê alguns outros serviços importantes além da tradução de nomes de hospedeiro para endereços IP:
- Apelidos (aliasing) de hospedeiro
- Apelidos de servidor de correio.
- Distribuição de carga

## Visão geral do modo de funcionamento do DNS

As três classes de servidores DNS:
- Servidores DNS raiz.
- Servidores DNS de Domínio de Alto Nível (TLD).
- Servidores DNS autoritativos.

Há mais um tipo importante de DNS, denominado servidor DNS local, que não pertence, estritamente, à hierarquia de servidores, mas, mesmo assim, é central para a arquitetura DNS. Cada ISP tem um servidor DNS local (também denominado servidor DNS default).

Em teoria, qualquer consulta DNS pode ser iterativa ou recursiva. Na prática, a consulta do hospedeiro requisitante ao servidor DNS local é recursiva e todas as outras são iterativas.

## Cache DNS

Até aqui, nossa discussão ignorou o cache DNS, uma característica muito importante do sistema DNS. A ideia por trás do cache DNS é muito simples. Em uma cadeia de consultas, quando um servidor DNS recebe uma resposta DNS (contendo, por exemplo, o mapeamento de um nome de hospedeiro para um endereço IP), pode fazer cache das informações da resposta em sua memória local.

Se um par nome de hospedeiro/endereço IP estiver no cache de um servidor DNS e outra consulta chegar ao mesmo servidor para o mesmo nome de hospedeiro, o servidor DNS poderá fornecer o endereço IP desejado, mesmo que não tenha autoridade para esse nome.

Como hospedeiros e mapeamentos entre hospedeiros e endereços IP não são, de modo algum, permanentes, após um período de tempo (quase sempre dois dias), os servidores DNS descartam as informações armazenadas em seus caches.

## Registros e mensagens DNS

Os servidores DNS que juntos executam o banco de dados distribuído do DNS armazenam registros de recursos (RR) que fornecem mapeamentos de nomes de hospedeiros para endereços IP.

Um registro de recurso é uma tupla de quatro elementos que contém os seguintes campos:
- Name
- Value
- Type
- TTL

TTL é o tempo de vida útil do registro de recurso; determina quando um recurso deve ser removido de um cache.

Os significados de Name e Value dependem de Type:
- Se Type=A, então Name é um nome de hospedeiro e Value é o endereço IP para o nome de hospedeiro.
- Se Type=NS, então Name é um domínio e Value é o nome de um servidor DNS autoritativo.
- Se Type=CNAME, então Value é um nome canônico de hospedeiro para o apelido de hospedeiro contido em Name.
- Se Type=MX, então Value é o nome canônico de um servidor de correio cujo apelido de hospedeiro está contido em Name.

