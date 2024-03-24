






------


DNS: O SERVIÇO DE DIRETÓRIO DA 

Assim como seres humanos podem ser identifcados de muitas maneiras, o mesmo acontece com hospedeiros da Internet. Um identifcador é seu nome de hospedeiro (hostname). Nomes de hospedeiro — como
cnn.com, www.yahoo.com, gaia.cs.umass.edu e cis.poly.edu — são fáceis de lembrar e, portanto,
apreciados pelos seres humanos. como
nomes de hospedeiros podem consistir em caracteres alfanuméricos de comprimento variável, seriam difíceis de
ser processados por roteadores. Por essas razões, hospedeiros também são identifcados pelo que denominamos
endereços IP.



Serviços fornecidos pelo DNS

Acabamos de ver que há duas maneiras de identifcar um hospedeiro — por um nome de hospedeiro e por
um endereço IP. As pessoas preferem o identifcador nome de hospedeiro por ser mais fácil de lembrar, ao passo
que roteadores preferem endereços IP de comprimento fxo e estruturados hierarquicamente. Para conciliar essas
preferências, é necessário um serviço de diretório que traduza nomes de hospedeiro para endereços IP. Esta é a
tarefa principal do DNS (domain name system — sistema de nomes de domínio) da Internet

. O DNS é (1) um
banco de dados distribuído executado em uma hierarquia de servidores de DNS, e (2) um protocolo de camada
de aplicação que permite que hospedeiros consultem o banco de dados distribuído. Os servidores DNS são muitas
vezes máquinas UNIX que executam o sofware BIND (Berkeley Internet Name Domain) [BIND, 2012]. O protocolo DNS utiliza UDP e usa a porta 53.


Para que a máquina do usuário possa enviar uma mensagem de requisição HTTP ao servidor Web www.someschool.edu, ela precisa primeiro obter o endereço IP.
Isso é feito da seguinte maneira:
1. A própria máquina do usuário executa o lado cliente da aplicação DNS.
2. O navegador extrai o nome de hospedeiro, www.someschool.edu, do URL e passa o nome para o lado
cliente da aplicação DNS.
3. O cliente DNS envia uma consulta contendo o nome do hospedeiro para um servidor DNS.
4. O cliente DNS por fm recebe uma resposta, que inclui o endereço IP correspondente ao nome de hospedeiro.
5. Tão logo o navegador receba o endereço do DNS, pode abrir uma conexão TCP com o processo servidor
HTTP localizado na porta 80 naquele endereço IP.



O DNS provê alguns outros serviços importantes além da tradução de nomes de hospedeiro para endereços IP:


Apelidos (aliasing) de hospedeiro
Apelidos de servidor de correio.
Distribuição de carga





Visão geral do modo de funcionamento do DNS

as três classes de servidores DNS:
•	 Servidores DNS raiz. Na Internet há 13 servidores DNS raiz (denominados de A a M) e a maior parte
deles está localizada na América do Norte. Um mapa de servidores DNS raiz de outubro de 2006 é mostrado na Figura 2.20; uma lista dos servidores DNS raiz existentes hoje está disponível em Root-servers
[2012]. Embora tenhamos nos referido a cada um dos 13 servidores DNS raiz como se fossem um servidor único, na realidade, cada um é um conglomerado de servidores replicados, para fns de segurança e
confabilidade. No total, havia 247 servidores raiz no segundo semestre de 2011.

•	 Servidores DNS de Domínio de Alto Nível (TLD). Esses servidores são responsáveis por domínios de
alto nível como com, org, net, edu e gov, e por todos os domínios de alto nível de países, tais como uk, fr ca e jp. A empresa Verisign Global Registry Services mantém os servidores TLD para o domínio de alto
nível com e a Educause mantém os servidores TLD para o domínio de alto nível edu. Veja em IANA TLD
[2012] uma lista de todos os domínios de alto nível.

•	 Servidores DNS autoritativos. Toda organização que tiver hospedeiros que possam ser acessados publicamente na Internet (como servidores Web e servidores de correio) deve fornecer registros DNS também
acessíveis publicamente que mapeiem os nomes desses hospedeiros para endereços IP. Um servidor DNS
autoritativo de uma organização abriga esses registros. Uma organização pode preferir executar seu próprio
servidor DNS autoritativo para abrigar esses registros ou, como alternativa, pagar para armazená-los em
um servidor DNS autoritativo de algum provedor de serviço. A maioria das universidades e empresas de
grande porte executa e mantém seus próprios servidores DNS primário e secundário (backup) autoritativos

Há mais um tipo importante de DNS, denominado servidor DNS local, que não pertence, estritamente, à hierarquia de servidores, mas, mesmo assim, é central para a arquitetura DNS. Cada ISP — como o de
uma universidade, de um departamento acadêmico, de uma empresa ou de uma residência — tem um servidor
DNS local (também denominado servidor DNS default). Quando um hospedeiro se conecta com um ISP, este
fornece os endereços IP de um ou mais de seus servidores 

Em teoria, qualquer consulta DNS pode ser iterativa ou recursiva. Na prática, a consulta do hospedeiro requisitante ao servidor DNS local é recursiva e todas as outras são iterativas


Cache DNS

Até aqui, nossa discussão ignorou o cache DNS, uma característica muito importante do sistema DNS
 A ideia por trás do cache DNS é muito simples. Em uma
cadeia de consultas, quando um servidor DNS recebe uma resposta DNS (contendo, por exemplo, o mapeamento de um nome de hospedeiro para um endereço IP), pode fazer cache das informações da resposta em sua
memória local. 

Se um par nome
de hospedeiro/endereço IP estiver no cache de um servidor DNS e outra consulta chegar ao mesmo servidor para
o mesmo nome de hospedeiro, o servidor DNS poderá fornecer o endereço IP desejado, mesmo que não tenha
autoridade para esse nome.

 Como hospedeiros e mapeamentos entre hospedeiros e endereços IP não são, de
modo algum, permanentes, após um período de tempo (quase sempre dois dias), os servidores DNS descartam
as informações armazenadas em seus caches.




Registros e mensagens DNS

Os servidores DNS que juntos executam o banco de dados distribuído do DNS armazenam registros de recursos (RR) que fornecem mapeamentos de nomes de hospedeiros para endereços IP.


Um registro de recurso é uma tupla de quatro elementos que contém os seguintes campos:
(Name, Value, Type, TTL)

TTL é o tempo de vida útil do registro de recurso; determina quando um recurso deve ser removido de
um cache. Nos exemplos de registros dados a seguir, ignoramos o campo TTL. Os signifcados de Name e Value
dependem de Type:

•	 Se Type=A, então Name é um nome de hospedeiro e Value é o endereço IP para o nome de hospedeiro.
Assim, um registro Type A fornece o mapeamento-padrão entre nomes de hospedeiros e endereços IP.
Como exemplo, (relay1.bar.foo.com, 145.37.93.126, A) é um registro com Type igual a A.
•	 Se Type=NS, então Name é um domínio (como foo.com) e Value é o nome de um servidor DNS
autoritativo que sabe como obter os endereços IP para hospedeiros do domínio. Esse registro é usado
para encaminhar consultas DNS ao longo da cadeia de consultas. Como exemplo, (foo.com, dns.
foo.com, NS) é um registro com Type igual a NS.
•	 Se Type=CNAME, então Value é um nome canônico de hospedeiro para o apelido de hospedeiro contido
em Name. Esse registro pode fornecer aos hospedeiros consultantes o nome canônico correspondente a um
apelido de hospedeiro. Como exemplo, (foo.com, relay1.bar.foo.com, CNAME) é um registro
CNAME.
•	 Se Type=MX, então Value é o nome canônico de um servidor de correio cujo apelido de hospedeiro
está contido em Name. Como exemplo, (foo.com, mail.bar.foo.com, MX) é um registro MX.
Registros MX permitem que os nomes de hospedeiros de servidores de correio tenham apelidos simples. Note que, usando o registro MX, uma empresa pode ter o mesmo apelido para seu servidor de
arquivo e para um de seus outros servidores (tal como seu servidor Web). Para obter o nome canônico do servidor de correio, um cliente DNS consultaria um registro MX; para obter o nome canônico
do outro servidor, o cliente DNS consultaria o registro CNAME.

