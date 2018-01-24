--- Minhas explicações ---

* Existe um arquivo insert_exemplos.txt que tem as entradas de dados de exemplo no banco de dados.


--- Explicações do professor ----

### Para que a aplicação funcione corretamente
* Adicione o Driver do banco de dados que você está utilizando na pasta do Tomcat/Glassfish (<TOMCAT_HOME>\lib ou <GLASSFISH_HOME>\glassfish\domains\domain1\lib)
* Adicionar o pool de conexões no Glassfish, de modo que os dados de conexão sejam gerenciados pelo Glassfish e não pela aplicação web (ver pasta \docs).


### News:

* Parâmetro "javax.faces.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE"
	- web.xml
