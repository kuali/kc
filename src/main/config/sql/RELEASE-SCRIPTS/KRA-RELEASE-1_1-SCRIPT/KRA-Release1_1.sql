set feedback off;
--set term off;
set heading off;
set escape \
set define off

spool install_kra_release-1-1.log;

--set term on;
Prompt ************* START INSTALLING KRA 1.1 DATABASE UPGRADE CHANGES *************
--set term off;

@DDL/KRA_1-1_DDL

@DML/KRA_1-1_DML

COMMIT;

--set term on;
Prompt ************* START UPGRADING S2S OBJECTS *************
--set term off;


@S2S/recompile

@S2S/views/OSP_EPS_PROP_KEY_PERSONS.sql

@S2S/procfunpacks/s2sED524V11Pkg.sql

Prompt Recompiling Invalid Objects
begin DBMS_UTILITY.COMPILE_SCHEMA(USER); END;
/

Select 'Invalid objects still remaining: ' || count(*) from user_objects where status = 'INVALID';

--set term on;
Prompt ************* END KRA 1.1 DATABASE UPDATE *************
--set term off;

exit
