set define off
set sqlblanklines on
spool KR-FINISH-ORACLE-Install.log
@../../../current/99.9.9/dml/KR_DML_99_SUPERUSER_B000.sql
commit;
exit
