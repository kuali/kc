set define off
set sqlblanklines on
spool KC-RELEASE-5_0_1-Demo-ORACLE-Install.log
@../../current/5.0.1/dml/KC_DML_01_KCCOI-165_0TSD.sql
@../../current/5.0.1/dml/KC_DML_01_KCIAC-209_0TSD.sql
@../../current/5.0.1/dml/KC_DML_01_KCIAC-230_0TSD.sql
@../../current/5.0.1/dml/KC_DML_01_KRACOEUS-5240_00SD.sql
commit;
exit
