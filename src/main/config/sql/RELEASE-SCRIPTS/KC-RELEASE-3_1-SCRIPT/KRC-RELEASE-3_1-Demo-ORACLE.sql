set define off
set sqlblanklines on
spool KRC-RELEASE-3_1-Demo-ORACLE-Install.log
@../../current/3.1/dml/KRC_DML_01_KRNS_DOC_HDR_T_0TSD.sql
@../../current/3.1/dml/KRC_DML_02_KRNS_MAINT_DOC_T_0TSD.sql
commit;
exit
