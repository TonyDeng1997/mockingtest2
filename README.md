# Building a Website with Node.js and Express.js
Need to integrate with webpack to organize the resource.

## Installing Tools
1. Make sure you have these installed
	- [eclipse]
	- [tomcat]
    - [maven]
    - [java]
    - [NodeJS/NPM]
        
## Checkout source code
Clone this repository into your local machine using the terminal (mac) or Gitbash (PC) `> git clone CLONEURL`

## Create a database
Create a database called `notes_app` for now,

* In application.properties, change your database password.
* Create a database by `create database notes_app;`
<pre>
xiaofeng@tvlx:~/projects/mockingtest2$ mysql -uroot -p
Enter password: 
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 14
Server version: 5.7.22-0ubuntu0.16.04.1 (Ubuntu)

Copyright (c) 2000, 2018, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> create database notes_app;
Query OK, 1 row affected (0.00 sec)
</pre>

## Create a database user account for development
Throughtout the whole project, developer is going to connect to their own local db but in the configuration file
we have to change the user password and user name all the time, so for the convinience, we decided to set a dummy user account
called `mockingtest`, password: `!LetUsUseThis2018`. The following script will help you create such db user in your 
local db, and grant permissiosn.

`CREATE USER 'mockingtest'@'localhost' IDENTIFIED BY '!LetUsUseThis2018';`

`GRANT ALL PRIVILEGES ON * . * TO 'mockingtest'@'localhost';`

This database account should be used in pom.xml for connecting to database by flyway.
It is better not to use your default root account for development and this is close to real deployment.

ref: https://www.digitalocean.com/community/tutorials/how-to-create-a-new-user-and-grant-permissions-in-mysql

## Start the server and run the code
Enter the project folder by `cd FOLDERNAME`, and make sure you see the pom.xml, then:

Run `./mvnw spring-boot:run` to install jars and start tomcat server for you (mvnw.bat for Windows users)

The above command installs all the jar and library dependencies project will need for you, and they are defined in pom.xml.

Note, the embeded tomcat server is just used for fast development, it is not the real deployment. In the reality, we will have a 
dedicated server and need to deploy the war file.

## Generate war file
Run `mvn package`, and/or run maven build to generate a war file in the target/ directory.
Drop this war file to tomcat's webapps/ directory.

## Flyway integration
For flyway integration, please check the pom.xml in the root of the project.
Also checkout the Flyway.md and flyway.sh for the usage.

Note, flyway is to database schema version control, simply say it is used to incrementally alter database, tables schemas and data.

ref: `https://flywaydb.org/getstarted/firststeps/maven`

## Possible Maven error solution

* if mention about Java, check JAVA_HOME path, and version, java10 is not supported. [how to change java version](https://stackoverflow.com/questions/21964709/how-to-set-or-change-the-default-java-jdk-version-on-os-x)
* if mention about port being used, open process manager to kill java process and try again. 
* if everything fails, try mvn clean and build again.

## Trouble shooting root password of MySQL

```
If you don't remember the password you set for root and need to reset it, follow these steps:

Stop the mysqld server, this varies per install
Run the server in safe mode with privilege bypass
sudo mysqld_safe --skip-grant-tables;

In a new window connect to the database, set a new password and flush the permissions & quit:
mysql -u root

For MySQL older than MySQL 5.7 use:

UPDATE mysql.user SET Password=PASSWORD('your-password') WHERE User='root';

For MySQL 5.7+ use:

USE mysql;

UPDATE mysql.user SET authentication_string=PASSWORD("your-password") WHERE User='root';

Refresh and quit:

FLUSH PRIVILEGES;

\q
```

## Code commit and contribution

### Create your branch off develop branch

1/ git checkout develop <br>

2/ git pull or git fetch origin -p (sync origin/develop and remove your local branch that was deleted on remote) <br>

3/ Create your branch with -b <br>
git checkout -b xiaofeng/add-img-slider <br>


 git add README.md

 git add app/routes/index.js

 git status


Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

        modified:   README.md
        modified:   app/routes/index.js

Untracked files:
  (use "git add <file>..." to include in what will be committed)

        node_modules/

Please do not commit node_modules.


## Send pull request to develop branch

* Always make your PR to develop branch
* Do not commit files in node_modules
* Do not commit hidden files .* files
 
## Merge from develop to master branch
Master branch is always the most trustable branch.

## Troubleshooting

* If you have any issues on not able to install mvn package, please 
  remove the node_modules dir in your project root.
* cd ~/.m2, and remove everything under it.
* If you see test file issue, then please do:
** mvn clean
** mvn test
** mvn spring-boot:run

