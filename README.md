# iad-course-project
'Internet Application Development' course project
 
* Application Server - Payara 5.1 <br>
* Backend - Java EE: EJB, JMS for some stupid logic (just because it was in task) <br>
* DataBase operations - Hibernate <br>
* Frontend - Angular Framework <br>
<br>

# Настройка аппликейшн сервера
Я использовал сервер приложений payara5, так как некоторые настройки невоможно нормально установить на glassfish'e (по-крайней мере, через админ-панель)

<b>JDBC</b>
1. Создать бд таблицы, DDL которых описан в файле <a href="https://github.com/allacee/iad-course-project/blob/master/IAD-DDL.sql"> IAD-DDL.sql </a>, а также тригеры к этим таблицам, описанные в файле <a href="https://github.com/allacee/iad-course-project/blob/master/IAD-TRIGGERS.sql"> IAD-TRIGGERS.sql </a>
2. В настройка админ-панели сервера приложений создать новый JDBC Connection Pool (установить properties password, databaseName, serverName, user, portNumber, driver_class)
3. Добавить в директорию с доменом в папку lib .jar файл постгреса (в моем случае postgresql-42.2.5.jar) <i>Возможно, есть и другой способ заставить coonection pool работать, но я не нашел (хотя особо и не искал)</i>
4. Создать новый JDBC Resource, в котором указать раннее созданный connectionPool

<b>JMS</b>

1. Создать новый JMS Destination Resource и выбрать тип javax.jms.Topic
2. Создать новый JMS Connection Factory установить ему jndi имя, и выбрать тип javax.jms.TopicConnectionFactory
<i>Вообще то говоря, тут было логичнее использовать queue</i>

<b>Security Realms</b>

В настройках server-config, во вкладке Security -> Realms создать новый Реалм.
1.  <b> JAAS Context: jdbcRealm </b>
2. JNDI: jdbc/имя_jdbc_ресурса
3. Charset: UTF-8
4. Digest Algorithm можно оставить пустым, так как значение SHA-256 стоит по умолчанию или 'none' для отключения шифрования
5. User Table: ЛИЧНОСТЬ
User Column Name: НИКНЕЙМ
Password Column: ХЕШ_ПАРОЛЬ
Group Table: ГРУППА_ЛИЧН
Group Table User Name Column: НИКНЕЙМ
Group Name Column: ГРУППА
<i>Перечисленные выше значения соответвуют таблицам, DDL которых содержится в <a href="https://github.com/allacee/iad-course-project/blob/master/IAD-DDL.sql"> IAD-DDL.sql </a></i>

Это гайд по настройке написан для самого себя на случай, если мне когда-то опять придется делать что-то подобное, поэтому он очень не полный.
<b>Решения всех возникших во время настройки ошибок можно найти в интернете </b>
