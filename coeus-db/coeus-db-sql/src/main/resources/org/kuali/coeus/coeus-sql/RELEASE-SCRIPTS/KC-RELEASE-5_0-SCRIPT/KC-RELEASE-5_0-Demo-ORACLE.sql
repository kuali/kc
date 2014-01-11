set define off
set sqlblanklines on
spool KC-RELEASE-5_0-Demo-ORACLE-Install.log
@../../current/5.0/dml/KC_DML_01_KCCOI-164_0TSD.sql
@../../current/5.0/dml/KC_DML_01_KCCOI-166_0TSD.sql
@../../current/5.0/dml/KC_DML_01_KCIAC-17_0TSD.sql
@../../current/5.0/dml/KC_DML_01_KRACOEUS-5539_0TSD.sql
@../../current/5.0/dml/KC_DML_02_KCCOI-162_0TSD.sql
@../../current/5.0/dml/KC_DML_02_KCCOI-163_0TSD.sql
@../../current/5.0/dml/KC_DML_03_KCIAC-16_0TSD.sql
commit;
exit
