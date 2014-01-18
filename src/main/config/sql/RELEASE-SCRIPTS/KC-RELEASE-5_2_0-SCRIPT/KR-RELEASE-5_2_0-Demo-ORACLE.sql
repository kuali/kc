set define off
set sqlblanklines on
spool KR-RELEASE-5_2_0-Demo-ORACLE-Install.log
@../../current/5.2.0/dml/KR_DML_02_KRACOEUS-6458_BTSD.sql
@../../current/5.2.0/dml/KR_DML_02_KRACOEUS-6566_0TSD.sql
@../../current/5.2.0/dml/KR_DML_02_KRACOEUS-6775_0TSD.sql
commit;
exit
