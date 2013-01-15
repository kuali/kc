set define off
set sqlblanklines on
spool KC-RELEASE-5_1_0-Demo-ORACLE-Install.log
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-5892_00SD.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-6052_0TSD.sql
commit;
exit
