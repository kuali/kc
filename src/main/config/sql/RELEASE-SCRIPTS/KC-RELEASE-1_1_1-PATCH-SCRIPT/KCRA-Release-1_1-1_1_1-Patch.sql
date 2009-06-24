-- 
-- SQL script to patch a release 1.1 DB to release 1.1.1 DB
-- Created 6-AUG-2008 by Tyler Wilson
--

set feedback off
--set term off
set heading off
set escape \
set define off

spool install_kcra_release-1_1_1-Patch.log

--set term on;
Prompt ************* START INSTALLING KRA DATABASE OBJECTS *************
--set term off;

--set term on;
Prompt ************* START CREATING KRA TABLES *************
--set term off;

@DDL/KCRA_TABLES-1_1_1-Patch

@TYPES/KCRA_TYPES-1_1_1-Patch

--set term on;
Prompt ************* START CREATING SEQUENCES *************
--set term off;

@SEQUENCES/KCRA_SEQUENCES-1_1_1-Patch

--set term on;
Prompt ************* START LOADING BOOTSTRAP DATA *************
--set term off;
set define ~;

@DML/KCRA_DML-1_1_1-Patch

COMMIT;

--set term on;
Prompt ************* START CREATING CONSTRAINTS *************
--set term off;
Prompt *** Primary Keys ***

@CONSTRAINTS/KCRA_PRIMARY_KEYS-1_1_1-Patch

Prompt *** Foreign Keys ***

@CONSTRAINTS/KCRA_FOREIGN_KEYS-1_1_1-Patch

--set term on;
Prompt ************* START CREATING INDEXES *************
--set term off;

REM @INDEXES/KCRA_INDEXES-1_1_1-Patch

--set term on;
Prompt ************* START LOADING S2S OBJECTS *************
--set term off;

@PROCFUNPACKS/KCRA_PROCFUNPACK-1_1_1-Patch.sql
@S2S/TYPES/All_Types.sql
@S2S/PROCFUNPACKS/All_ProcFunPacks.sql
@S2S/VIEWS/All_Views.sql

Prompt Recompiling Invalid Objects

begin DBMS_UTILITY.COMPILE_SCHEMA(USER); END;
/

select 'Invalid objects still remaining: ' || count(*) from user_objects where status = 'INVALID';

--set term on;
Prompt ************* END KRA DATABASE UPDATE *************
--set term off;

exit
