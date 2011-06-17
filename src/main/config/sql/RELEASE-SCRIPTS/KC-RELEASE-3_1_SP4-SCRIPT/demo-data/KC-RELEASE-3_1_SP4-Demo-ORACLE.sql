set define off
set sqlblanklines on
spool KC-RELEASE-3_1_SP4-Demo-ORACLE-Install.log
@ORACLE/KC_DEMO1_SPONSOR_HIERARCHY.sql
commit;
exit
