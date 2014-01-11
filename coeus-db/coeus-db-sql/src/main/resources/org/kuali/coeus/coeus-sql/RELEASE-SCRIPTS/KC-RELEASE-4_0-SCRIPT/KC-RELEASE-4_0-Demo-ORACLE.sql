set define off
set sqlblanklines on
spool KC-RELEASE-4_0-Demo-ORACLE-Install.log
@../../current/4.0/dml/KC_DML_40001_CUSTOM_ATTRIBUTE_DOCUMENT_0TSD.sql
@../../current/4.0/dml/KC_DML_40001_KCINFR-477_00SD.sql
@../../current/4.0/dml/KC_DML_40001_SPONSOR_FORM_TEMPLATES_00SD.sql
@../../current/4.0/dml/KC_DML_40002_SPONSOR_FORM_TEMPLATES_00SD.sql
@../../current/4.0/dml/KC_DML_40003_SPONSOR_FORM_TEMPLATES_00SD.sql
@../../current/4.0/dml/KC_DML_40004_SPONSOR_FORM_TEMPLATES_00SD.sql
commit;
exit
