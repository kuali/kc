set feedback off;
--set term off;
set heading off;
set escape \
set define off

spool install_kra_release-1-1.log;

--set term on;
select '************* START INSTALLING KRA 1.1 DATABASE UPGRADE CHANGES *************' from dual;
--set term off;

@DDL/KRA_1-1_DDL

@DML/KRA_1-1_DML

COMMIT;

--set term on;
select '************* START UPGRADING S2S OBJECTS *************' from dual;
--set term off;


@S2S/recompile

@S2S/views/OSP_EPS_PROP_KEY_PERSONS.sql

@S2S/procfunpacks/s2sED524V11Pkg.sql

begin DBMS_UTILITY.COMPILE_SCHEMA(USER); END;
/

--set term on;
select '************* END KRA 1.1 DATABASE UPDATE *************' from dual;
--set term off;

exit
