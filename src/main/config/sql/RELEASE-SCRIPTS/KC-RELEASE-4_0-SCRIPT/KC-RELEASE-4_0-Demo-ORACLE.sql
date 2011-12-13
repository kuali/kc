set define off
set sqlblanklines on
spool KC-RELEASE-4_0-Demo-ORACLE-Install.log
@../../current/4.0/dml/KC_DML_01_KRACOEUS-4937_0TSD.sql
@../../current/4.0/dml/KC_DML_01_KRACOEUS-5144_00SD.sql
@../../current/4.0/dml/KC_DML_01_WATERMARK_000D.sql
commit;
exit
