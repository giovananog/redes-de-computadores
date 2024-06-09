# A Web e o HTTP

## Descrição Geral do HTTP

O **HTTP** — Protocolo de Transferência de Hipertexto (*HyperText Transfer Protocol*) —, o protocolo da camada de aplicação da Web, está no coração da Web e é definido no [RFC 1945] e no [RFC 2616]. O **HTTP** é executado em dois programas: um cliente e outro servidor. Os dois, executados em sistemas finais diferentes, conversam entre si por meio da troca de mensagens **HTTP**. O **HTTP** define a estrutura dessas mensagens e o modo como o cliente e o servidor as trocam.

Uma página Web (também denominada documento) é constituída de objetos. Um objeto é apenas um arquivo — tal como um arquivo HTML, uma imagem JPEG, um applet Java, ou um clipe de vídeo — que se pode acessar com um único URL. 

O **HTTP** define como os clientes requisitam páginas aos servidores e como eles as transferem aos clientes.

O **HTTP** usa o TCP como seu protocolo de transporte subjacente (em vez de rodar em cima do UDP). O cliente **HTTP** primeiro inicia uma conexão TCP com o servidor. Uma vez estabelecida, os processos do navegador e do servidor acessam o TCP por meio de suas interfaces de socket.

Como o servidor **HTTP** não mantém informação alguma sobre clientes, o **HTTP** é denominado um protocolo sem estado. Além disso, a Web usa a arquitetura de aplicação cliente-servidor. Um servidor Web está sempre em funcionamento, tem um endereço IP fixo e atende requisições de potencialmente milhões de navegadores diferentes.

# Conexões Persistentes e Não Persistentes

Em muitas aplicações da Internet, o cliente e o servidor se comunicam por um período prolongado de tempo, em que o cliente faz uma série de requisições e o servidor responde a cada uma. Dependendo da aplicação e de como ela está sendo usada, a série de requisições pode ser feita de forma consecutiva, periodicamente em intervalos regulares ou de modo esporádico. Quando a interação cliente-servidor acontece por meio de conexão TCP, o programador da aplicação precisa tomar uma importante decisão — cada par de requisição/resposta deve ser enviado por uma conexão TCP distinta ou todas as requisições e suas respostas devem ser enviadas por uma mesma conexão TCP? 

## O HTTP com Conexões Não Persistentes

...

## O HTTP com Conexões Persistentes

...

# Formato da Mensagem HTTP

### Mensagem de Requisição HTTP

GET /somedir/page.html HTTP/1.1
Host: www.someschool.edu
Connection: close
User-agent: Mozilla/5.0
Accept-language: fr

A primeira linha é denominada linha de requisição; as subsequentes são denominadas linhas de cabeçalho. A linha de requisição tem três campos: o do método, o do URL e o da versão do HTTP. O campo do método pode assumir vários valores diferentes, entre eles GET, POST, HEAD, PUT e DELETE. A grande maioria das mensagens de requisição HTTP emprega o método GET, o qual é usado quando o navegador requisita um objeto e este é identificado no campo do URL.

A linha de cabeçalho Host: www.some-school.edu especifica o hospedeiro no qual o objeto reside. Talvez você ache que ela é desnecessária, pois já existe uma conexão TCP para o hospedeiro. Mas, como veremos na Seção 2.2.5, a informação fornecida pela linha de cabeçalho do hospedeiro é exigida por caches proxy da Web. Ao incluir a linha de cabeçalho Connection: close, o navegador está dizendo ao servidor que não quer usar conexões persistentes; quer que o servidor feche a conexão após o envio do objeto requisitado. A linha de cabeçalho User-agent: especifica o agente de usuário, isto é, o tipo de navegador que está fazendo a requisição ao servidor. Neste caso, o agente de usuário é o Mozilla/5.0, um navegador Firefox. Essa linha de cabeçalho é útil porque, na verdade, o servidor pode enviar versões diferentes do mesmo objeto a tipos diferentes de agentes de usuário. Por fim, o cabeçalho Accept-language: mostra que o usuário prefere receber uma versão em francês do objeto se este existir no servidor; se não existir, o servidor deve enviar a versão default. O cabeçalho Accept-language: é apenas um dos muitos de negociação de conteúdo disponíveis no HTTP.

Apresentamos a seguir uma mensagem de resposta HTTP típica. Essa mensagem poderia ser a resposta ao exemplo de mensagem de requisição que acabamos de discutir.

HTTP/1.1 200 OK
Connection: close
Date: Tue, 09 Aug 2011 15:44:04 GMT
Server: Apache/2.2.3 (CentOS)
Last-Modifed: Tue, 09 Aug 2011 15:11:03 GMT
Content-Length: 6821
Content-Type: text/html
(dados dados dados dados dados ...)

Ela tem três seções: uma linha inicial ou linha
de estado, seis linhas de cabeçalho e, em seguida, o corpo da entidade, que é a parte principal da mensagem —
contém o objeto solicitado (representado por dados dados dados dados dados ...). A linha de estado tem
três campos: o de versão do protocolo, um código de estado e uma mensagem de estado correspondente. Neste
exemplo, ela mostra que o servidor está usando o HTTP/1.1 e que está tudo OK (isto é, o servidor encontrou e
está enviando o objeto solicitado).

o. O servidor usa Connection: close para informar ao cliente
que fechará a conexão TCP após enviar a mensagem. A linha de cabeçalho Date: indica a hora e a data em que
a resposta HTTP foi criada e enviada pelo servidor

A linha de cabeçalho Server: mostra que a mensagem foi
gerada por um servidor Web Apache, semelhante à linha de cabeçalho User-agent: na mensagem de requisição HTTP. A linha de cabeçalho Last-Modifed: indica a hora e a data em que o objeto foi criado ou sofreu
a última modifcação

---

## Interação usuário-servidor: cookies

Um servidor HTTP não tem estado, o que simplifica o projeto do servidor e vem permitindo que engenheiros desenvolvam servidores Web de alto desempenho que podem manipular milhares de conexões TCP simultâneas.  
No entanto, é sempre bom que um site identifique usuários, seja porque o servidor deseja restringir acesso, seja porque quer apresentar conteúdo em função da identidade do usuário. Para essas finalidades, o HTTP usa cookies. Cookies, definidos no [RFC 6265], permitem que sites monitorem seus usuários.

---

## Caches Web

Um cache Web — também denominado servidor proxy — é uma entidade da rede que atende requisições HTTP em nome de um servidor Web de origem. O cache Web tem seu próprio disco de armazenagem e mantém, dentro dele, cópias de objetos recentemente requisitados.

Como exemplo, suponha que um navegador esteja requisitando o objeto http://www.someschool.edu/campus.gif. Eis o que acontece:

1. O navegador estabelece uma conexão TCP com o cache Web e envia a ele uma requisição HTTP para o objeto.
2. O cache Web verifica se tem uma cópia do objeto armazenada localmente. Se tiver, envia o objeto ao navegador do cliente, dentro de uma mensagem de resposta HTTP.
3. Se não tiver o objeto, o cache Web abre uma conexão TCP com o servidor de origem, isto é, com www.someschool.edu. Então, envia uma requisição HTTP do objeto para a conexão TCP. Após recebê-la, o servidor de origem envia o objeto ao cache Web, dentro de uma resposta HTTP.
4. Quando recebe o objeto, o cache Web guarda uma cópia em seu armazenamento local e envia outra, dentro de uma mensagem de resposta HTTP, ao navegador do cliente (pela conexão TCP existente entre o navegador do cliente e o cache Web).

Um cache é, ao mesmo tempo, um servidor e um cliente. Quando recebe requisições de um navegador e lhe envia respostas, é um servidor. Quando envia requisições para um servidor de origem e recebe respostas dele, é um cliente.

O cache na Web tem tido ampla utilização na Internet por duas razões. Primeiro, pode reduzir substancialmente o tempo de resposta para a requisição de um cliente, em particular se o gargalo da largura de banda entre o cliente e o servidor de origem for muito menor do que aquele entre o cliente e o cache. Se houver uma conexão de alta velocidade entre o cliente e o cache, como em geral é o caso, e se este tiver o objeto requisitado, então ele poderá entregar com rapidez o objeto ao cliente. Segundo, caches Web podem reduzir de modo substancial o tráfego no enlace de acesso de uma instituição qualquer à Internet. Com a redução do tráfego, a instituição (por exemplo, uma empresa ou uma universidade) não precisa ampliar sua largura de banda tão rapidamente, o que diminui os custos.

---

## GET Condicional

Embora possa reduzir os tempos de resposta do ponto de vista do usuário, fazer cache introduz um novo problema — a cópia de um objeto existente no cache pode estar desatualizada. Em outras palavras, o objeto abrigado no servidor pode ter sido modificado desde a data em que a cópia entrou no cache no cliente. Felizmente, o HTTP tem um mecanismo que permite que um cache verifique se seus objetos estão atualizados. Esse mecanismo é denominado GET condicional.