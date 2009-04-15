-- 
-- SQL script to patch a release 1.0 DB to release 1.0.1 DB
-- Created 6-AUG-2008 by Tyler Wilson
--

set feedback off;
set term off;
set heading off;

spool install_kcra_release-1_1_1-Patch.log;

set term on;
select '*************************** START INSTALLING KRA DATABASE OBJECTS *************************************************' from dual;
set term off;

set term on;
select '*************************** START CREATING KRA TABLES *************************************************' from dual;
set term off;

@DDL/KCRA_TABLES-1_1_1-Patch

@TYPES/KCRA_TYPES-1_1_1-Patch

set term on;
select '*************************** START CREATING SEQUENCES *************************************************' from dual;
set term off;

@SEQUENCES/KCRA_SEQUENCES-1_1_1-Patch

set term on;
select '*************************** START LOADING BOOTSTRAP DATA *************************************************' from dual;
set term off;
set define ~;

@DML/KCRA_DML-1_1_1-Patch

COMMIT;

set term on;
select '*************************** START CREATING CONSTRAINTS *************************************************' from dual;
set term off;

@CONSTRAINTS/KCRA_PRIMARY_KEYS-1_1_1-Patch
@CONSTRAINTS/KCRA_FOREIGN_KEYS-1_1_1-Patch

set term on;
select '*************************** START CREATING INDEXES *************************************************' from dual;
set term off;

REM @INDEXES/KCRA_INDEXES-1_1_1-Patch

set term on;
select '*************************** START LOADING S2S OBJECTS *************************************************' from dual;
set term off;
@S2S\PROCFUNPACKS\All_ProcFunPacks.sql
@S2S\VIEWS\All_Views.sql
exec DBMS_UTILITY.COMPILE_SCHEMA(USER);

set term on;
select '*************************** END KRA DATABASE UPDATE *************************************************' from dual;
set term off;

exit
