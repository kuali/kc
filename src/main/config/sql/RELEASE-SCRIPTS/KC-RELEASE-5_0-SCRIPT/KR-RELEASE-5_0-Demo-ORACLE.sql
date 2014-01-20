set define off
set sqlblanklines on
spool KR-RELEASE-5_0-Demo-ORACLE-Install.log
@../../current/5.0/dml/KR_DML_00_KCINFR-353_0TSD.sql
@../../current/5.0/dml/KR_DML_01_KCCOI-280_0TSD.sql
@../../current/5.0/dml/KR_DML_01_KCIAC-181_0TSD.sql
commit;
exit
