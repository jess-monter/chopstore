CREATE USER 'choppr'@'%' IDENTIFIED BY 'Ch0ppswd2021';
CREATE DATABASE chopstore;
GRANT ALL ON chopstore.* TO 'choppr'@'%';