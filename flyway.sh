#!/usr/bin/bash

flyway.url=jdbc:mysql://localhost
flyway.user=mockingtest
flyway.password=!LetUsUseThis2018
flyway -locations=filesystem:D:\fe_project\mockingtest2\src\main\resources\db\migration migrate
