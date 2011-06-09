set define off
set sqlblanklines on
spool KRC-RELEASE-3_1_SP3-Upgrade-ORACLE-Install.log
@ORACLE/DML/KRC_DML_BS1_KRNS_DOC_HDR_T.sql
@ORACLE/DML/KRC_DML_BS2_KRNS_MAINT_DOC_T.sql
commit;
exit
