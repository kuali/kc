set feedback off;
set term off;
set heading off;

spool install_kra_release-1_0_1-Patch.log;

set term on;
select '*************************** START INSTALLING KRA DATABASE OBJECTS *************************************************' from dual;
set term off;

set term on;
select '*************************** START CREATING KRA TABLES *************************************************' from dual;
set term off;

@DDL/KRA_ALL_TABLES

@TYPES/KRA_ALL_TYPES

set term on;
select '*************************** START CREATING SEQUENCES *************************************************' from dual;
set term off;

@SEQUENCES/KRA_ALL_SEQUENCES

set term on;
select '*************************** START LOADING BOOTSTRAP DATA *************************************************' from dual;
set term off;
set define ~;


COMMIT;

set term on;
select '*************************** START CREATING CONSTRAINTS *************************************************' from dual;
set term off;

@CONSTRAINTS/KRA_ALL_PRIMARY_KEYS
@CONSTRAINTS/KRA_ALL_FOREIGN_KEYS

set term on;
select '*************************** START CREATING INDEXES *************************************************' from dual;
set term off;

REM @INDEXES/KRA_ALL_INDEXES

set term on;
select '*************************** START LOADING S2S OBJECTS *************************************************' from dual;
set term off;

exec recompile;
exec recompile;
exec recompile;

set term on;
select '*************************** END KRA DATABASE UPDATE *************************************************' from dual;
set term off;

exit
