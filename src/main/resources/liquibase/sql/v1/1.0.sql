--liquibase formatted sql

--changeset phuong:1 dbms:h2
CREATE TABLE USER (
  LOGIN varchar(64) NOT NULL PRIMARY KEY ,
  PASSWORD varchar(128) NOT NULL,
  USERNAME varchar(200) DEFAULT NULL
);

--changeset phuong:2 dbms:h2
CREATE TABLE PROJECT (
  ID BIGINT(20) NOT NULL PRIMARY KEY ,
  NAME varchar(100) NOT NULL,
  DESCRIPTION varchar(200) DEFAULT NULL,
  OWNER varchar(64) NOT NULL
);

CREATE TABLE Function (
  ID BIGINT(20) NOT NULL PRIMARY KEY ,
  NAME varchar(100) NOT NULL,
  BROWSER varchar(200) DEFAULT NULL,
  TOTALSCRIPT INT NOT NULL,
  PROJECT BIGINT(20) NOT NULL,
  DATA LONGTEXT NOT NULL
);