set define off
set sqlblanklines on
spool KR-RELEASE-4_0-Demo-ORACLE-Install.log
@../../current/4.0/dml/KR_DML_40001_KCCOI-142_0TSD.sql
@../../current/4.0/dml/KR_DML_40002_KCINFR-483_0TSD.sql
@../../current/4.0/dml/KR_DML_40002_KCIRB-1664_0TSD.sql
@../../current/4.0/dml/KR_DML_40002_KRACOEUS-5100_0TSD.sql
@../../current/4.0/dml/KR_DML_40002_KRACOEUS-5257_0TSD.sql
commit;
exit
