set define off 
set sqlblanklines on 
spool KR-Release-3_0-3_0_1-Upgrade-Oracle-Install.log
@ORACLE/DML/DML_BS1_KRIM_PERM_ATTR_DATA_T.sql
@ORACLE/DML/DML_BS1_KRIM_ROLE_MBR_T.sql
@ORACLE/DML/DML_BS1_KRNS_PARM_T.sql
commit;
exit;