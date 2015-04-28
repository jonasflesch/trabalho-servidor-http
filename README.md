# trabalho-servidor-http

### Como rodar
Este é um projeto Maven, e deve ser importado para dentro da IDE como tal.

Foi desenvolvido utilizando IntelliJ IDEA 14, mas pode ser importado através do eclipse através de Import > Maven -> Maven Project

Foram utilizado para testes sistemas Unix (Linux e OS X), não homologado para Windows (podem ocorrer problemas com separadores de diretório invertidos).

A classe main está dentro de HttpServer e recebe como argumento o diretório do filesystem com os arquivos.

O servidor roda sempre na porta 8080.

Para sua comodidade um jar buildado está incluído na entrega, para rodar basta executar (alterando o path de conteúdo):
java -jar trabalho-servidor-http.jar /Users/jonasflesch/Documents/trabalho-servidor-http/content/
