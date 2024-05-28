# Redes de Circuitos Virtuais e de Datagramas

A camada de transporte da Internet oferece a cada aplicação uma alternativa entre dois serviços: UDP, um não orientado para conexão; ou TCP, orientado para conexão. De modo semelhante, uma camada de rede também pode oferecer qualquer dos dois. Serviços de camada de rede orientados para conexão e não orientados para conexão são, em muitos aspectos, semelhantes a esses mesmos serviços providos pela camada de transporte. Por exemplo, um serviço de camada de rede orientado para conexão começa com uma apresentação entre os hospedeiros de origem e de destino; e um serviço de camada de rede não orientado para conexão não tem nenhuma apresentação preliminar.

Embora os serviços de camada de rede orientados para conexão e não orientados para conexão tenham algumas semelhanças com os mesmos serviços oferecidos pela camada de transporte, há diferenças cruciais:
- Na camada de rede, são serviços de hospedeiro a hospedeiro providos pela camada de rede à camada de transporte. Na camada de transporte, são serviços de processo a processo fornecidos pela camada de transporte à camada de aplicação.
- Em todas as arquiteturas importantes de redes de computadores existentes até agora (Internet, ATM, frame relay e assim por diante), a camada de rede oferece um serviço entre hospedeiros não orientado para conexão, ou um serviço entre hospedeiros orientado para conexão, mas não ambos. Redes de computadores que oferecem apenas um serviço orientado para conexão na camada de rede são denominadas redes de circuitos virtuais (redes CV); redes de computadores que oferecem apenas um serviço não orientado para conexão na camada de rede são denominadas redes de datagramas.
- As execuções de serviço orientado para conexão na camada de transporte e de serviço de conexão na camada de rede são fundamentalmente diferentes. Redes de circuitos virtuais e redes de datagramas são duas classes fundamentais de redes de computadores. Elas utilizam informações muito diferentes para tomar suas decisões de repasse.

## Redes de Circuitos Virtuais

Embora a Internet seja uma rede de datagramas, muitas arquiteturas de rede alternativas — entre elas as das redes ATM e frame relay — são redes de circuitos virtuais e, portanto, usam conexões na camada de rede. Essas conexões de camada de rede são denominadas circuitos virtuais (CVs).

Um circuito virtual (CV) consiste em:
1. Um caminho (isto é, uma série de enlaces e roteadores) entre hospedeiros de origem e de destino.
2. Números de CVs, um número para cada enlace ao longo do caminho.
3. Registros na tabela de repasse em cada roteador ao longo do caminho.

Um pacote que pertence a um circuito virtual portará um número de CV em seu cabeçalho. Como um circuito virtual pode ter um número de CV diferente em cada enlace, cada roteador interveniente deve substituir esse número de cada pacote em trânsito por um novo número. Esse número novo do CV é obtido da tabela de repasse.

Há três fases que podem ser identificadas em um circuito virtual:
- **Estabelecimento de CV**: Durante a fase de estabelecimento, a camada de transporte remetente contata a camada de rede, especifica o endereço do receptor e espera até a rede estabelecer o CV. A camada de rede determina o caminho entre remetente e destinatário, ou seja, a série de enlaces e roteadores pelos quais todos os pacotes do CV trafegarão. A camada de rede também determina o número de CV para cada enlace ao longo do caminho e, por fim, adiciona um registro na tabela de repasse em cada roteador no caminho. Durante o estabelecimento do CV, a camada de rede pode também reservar recursos (por exemplo, largura de banda) no caminho.
- **Transferência de dados**: Tão logo estabelecido o CV, pacotes podem começar a fluir ao longo dele.
- **Encerramento do CV**: O encerramento começa quando o remetente (ou o destinatário) informa à camada de rede seu desejo de desativar o CV. A camada de rede então informará o sistema final do outro lado da rede do término de conexão e atualizará as tabelas de repasse em cada um dos roteadores de pacotes no caminho para indicar que o CV não existe mais.

Estabelecer conexão na camada de transporte envolve apenas os dois sistemas finais. Durante o estabelecimento da conexão na camada de transporte, os dois sistemas finais determinam os parâmetros (por exemplo, número de sequência inicial e tamanho da janela de controle de fluxo) de sua conexão de camada de transporte. Embora os dois sistemas finais fiquem cientes da conexão de camada de transporte, os roteadores dentro da rede ficam completamente alheios a ela. Por outro lado, com uma camada de rede de CV, os roteadores do caminho entre os dois sistemas finais estão envolvidos no estabelecimento de CV e cada roteador fica totalmente ciente de todos os CVs que passam por ele.

As mensagens que os sistemas finais enviam à rede para iniciar ou encerrar um CV e aquelas passadas entre os roteadores para estabelecer o CV (isto é, modificar estado de conexão em tabelas de roteadores) são conhecidas como mensagens de sinalização e os protocolos usados para trocá-las costumam ser denominados protocolos de sinalização.

## Redes de Datagramas

Em uma rede de datagramas, toda vez que um sistema final quer enviar um pacote, ele marca o pacote com o endereço do sistema final de destino e então o envia para dentro da rede. Isso é feito sem o estabelecimento de nenhum CV. Roteadores em uma rede de datagramas não mantêm nenhuma informação de estado sobre CVs (porque não há nenhum!).

Ao ser transmitido da origem ao destino, um pacote passa por uma série de roteadores. Cada um desses roteadores usa o endereço de destino do pacote para repassá-lo. Especificamente, cada roteador tem uma tabela de repasse que mapeia endereços de destino para interfaces de enlaces; quando um pacote chega ao roteador, este usa o endereço de destino do pacote para procurar a interface de enlace de saída apropriada na tabela de repasse. Então, o roteador transmite o pacote para aquela interface de enlace de saída.
