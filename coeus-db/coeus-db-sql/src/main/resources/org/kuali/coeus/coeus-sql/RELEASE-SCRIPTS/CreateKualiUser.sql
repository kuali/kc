CREATE USER <USERNAME> IDENTIFIED BY "<password>" DEFAULT TABLESPACE <TABLESPACENAME> TEMPORARY TABLESPACE <TEMP TABLESPACE>;
alter user <USERNAME> quota unlimited on <tablespacename>;
grant create session to <USERNAME>;
grant create synonym to <USERNAME>;
grant create procedure to <USERNAME>;
grant create trigger to <USERNAME>;
grant create table to <USERNAME>;
grant create type to <USERNAME>;
grant create view to <USERNAME>;
grant create sequence to <USERNAME>;
quit
