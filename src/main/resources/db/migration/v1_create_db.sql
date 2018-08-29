create database mockingtest;

CREATE TABLE question_data (
id int auto_increment,
title varchar(255) not null,
description text,
PRIMARY KEY(id)
);

CREATE INDEX idx_question_title ON question_data(title);


CREATE TABLE question_test (
id int auto_increment,
question_id int,
test_body text,
PRIMARY KEY(id),
FOREIGN KEY (question_id ) REFERENCES question_data (id)
);

CREATE INDEX idx_question_id
ON question_test(question_id);
