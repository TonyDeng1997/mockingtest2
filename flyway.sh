#!/usr/bin/bash

flyway.url=jdbc:mysql://localhost
flyway.user=root
flyway.password=123456


flyway -locations=filesystem:D:\fe_project\mockingtest2\src\main\resources\db\migration migrate
