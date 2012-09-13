set define off
set sqlblanklines on
spool KR-RELEASE-5_0_1-Demo-ORACLE-Install.log
@../../current/5.0.1/dml/KR_DML_01_KCIAC-209_0TSD.sql
@../../current/5.0.1/dml/KR_DML_02_KCCOI-281_0TSD.sql
@../../current/5.0.1/dml/KR_DML_02_KCIAC-236_0TSD.sql
commit;
exit
