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