set define off
set sqlblanklines on
spool KC-RELEASE-3_1_1-Demo-ORACLE-Install.log
@../../current/3.1.1/dml/KC_DML_01_RATE_CLASS_00SD.sql
@../../current/3.1.1/dml/KC_DML_01_SPONSOR_FORM_TEMPLATES_00SD.sql
@../../current/3.1.1/dml/KC_DML_02_INSTITUTE_RATES_00SD.sql
@../../current/3.1.1/dml/KC_DML_03_VALID_CE_RATE_TYPES_00SD.sql
commit;
exit
