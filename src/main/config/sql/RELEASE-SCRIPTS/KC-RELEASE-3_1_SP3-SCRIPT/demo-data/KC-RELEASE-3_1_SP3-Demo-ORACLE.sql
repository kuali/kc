set define off
set sqlblanklines on
spool KC-RELEASE-3_1_SP3-Demo-ORACLE-Install.log
@ORACLE/KC_DEMO1_SPONSOR_FORM_TEMPLATES.sql
commit;
exit
