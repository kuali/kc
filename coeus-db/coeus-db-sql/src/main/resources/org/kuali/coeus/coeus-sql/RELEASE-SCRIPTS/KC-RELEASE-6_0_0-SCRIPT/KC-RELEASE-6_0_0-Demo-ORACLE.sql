set define off
set sqlblanklines on
spool KC-RELEASE-6_0_0-Demo-ORACLE-Install.log
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-7120_BTSD.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-7356_0TSD.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-7659_00SD.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-8741_000D.sql
commit;
exit
