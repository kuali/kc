set define off
set sqlblanklines on
spool KC-demo-Demo-ORACLE-Install.log
@ORACLE/KC_DEMO1_UNIT.sql
@ORACLE/KC_DEMO1_YNQ.sql
commit;
exit
