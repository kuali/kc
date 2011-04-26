set define off
set sqlblanklines on
spool KRC-RELEASE-3_1-Demo-ORACLE-Install.log
@ORACLE/KRC_DEMO1_KRNS_DOC_HDR_T.sql
@ORACLE/KRC_DEMO2_KRNS_MAINT_DOC_T.sql
commit;
exit
