# Database Migration using Flyway

## Maven flyway plugin

This plugin has been added to pom.xml but unfortunately, there are currently some known bug
so the solution is non-trivial also it is very vulnerable due to 
different version of mysql-connector drivers.

## Current solution for flyway migration

* Install the flyway in local development computer, and in the flyway*/conf/flyway.conf,
configure all the url, user, password...etc. 

To install it, please try not to use `brew install flyway`, get the tar ball, unzip it,

and set the following,

	flyway.url=jdbc:mysql://localhost
	flyway.user=root
    flyway.password=123456

* Use the following commands,

In mysql command line or mysql workbench, use the following,

`drop database words;`

Then do the following in bash,


	flyway -locations=filesystem:/Users/xiaofeng/cloud/mockingtest/app/src/main/resources/db/migration/words/ clean

	flyway -locations=filesystem:/Users/xiaofeng/cloud/mockingtest/app/src/main/resources/db/migration/words/ migrate


After the above second command, it will create the words database with all the tables and data.

* From maven we should run the following,

`$ mvn flyway:migrate`


Output is like this, once the sql scripts are run properly, you can validate the change by login to the db and check them. 

<pre>
D:\fe_project\mockingtest2>mvn flyway:migrate
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building auth 1.3.5.RELEASE
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- flyway-maven-plugin:5.1.4:migrate (default-cli) @ auth ---
[INFO] Flyway Community Edition 5.1.4 by Boxfuse
[INFO] Database: jdbc:mysql://localhost:3306/mockingtest (MySQL 5.7)
[INFO] Successfully validated 1 migration (execution time 00:00.012s)
[INFO] Current version of schema `mockingtest`: << Empty Schema >>
[INFO] Migrating schema `mockingtest` to version 1 - Create  Question  DataBase
[INFO] Successfully applied 1 migration to schema `mockingtest` (execution time 00:00.150s)
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.815 s
[INFO] Finished at: 2018-09-05T16:55:20-04:00
[INFO] Final Memory: 17M/245M
[INFO] ------------------------------------------------------------------------


D:\fe_project\mockingtest2>mysql -umockingtest -p
Enter password: ***********
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 49
Server version: 5.7.22-log MySQL Community Server (GPL)

Copyright (c) 2000, 2018, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| challenge          |
| mockingtest        |
| mysql              |
| performance_schema |
| sakila             |
| sys                |
| test               |
| world              |
+--------------------+
9 rows in set (0.00 sec)

mysql> use mockingtest;
Database changed
mysql> show tables;
+-----------------------+
| Tables_in_mockingtest |
+-----------------------+
| flyway_schema_history |
| question_data         |
| question_test         |
+-----------------------+
3 rows in set (0.00 sec)

mysql>
</pre>
[How to name a flyway file](https://flywaydb.org/documentation/migrations#naming)