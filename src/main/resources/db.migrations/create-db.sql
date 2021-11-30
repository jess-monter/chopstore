CREATE USER 'choppr'@'%' IDENTIFIED BY 'Ch0ppswd2021';
CREATE DATABASE choppstore;
GRANT ALL ON choppstore.* TO 'choppr'@'%';