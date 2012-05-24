set define off
set sqlblanklines on
spool KR-RELEASE-3_2-Demo-ORACLE-Install.log
@oracle/dml/KR_DML_01_KRACOEUS-5091_0TSD.sql
@oracle/dml/KR_DML_01_KRIM_ROLE_MBR_T_0TSD.sql
commit;
exit
