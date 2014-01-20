set define off
set sqlblanklines on
spool KC-RELEASE-3_1_1-Demo-ORACLE-Install.log
@../../current/4.0/dml/KC_DML_31101_RATE_CLASS_00SD.sql
@../../current/4.0/dml/KC_DML_31101_SPONSOR_FORM_TEMPLATES_00SD.sql
@../../current/4.0/dml/KC_DML_31102_INSTITUTE_RATES_00SD.sql
@../../current/4.0/dml/KC_DML_31103_VALID_CE_RATE_TYPES_00SD.sql
commit;
exit
