set define off
set sqlblanklines on
spool KR-RELEASE-6_0_1-Upgrade-ORACLE-Install.log
@./rice/bootstrap/V601_004__Resolve_Repackaging_Db_Issues.sql
@./rice/bootstrap/V601_010__KRACOEUS-8837.sql
@./rice/bootstrap/V601_011__KRACOEUS-8454.sql
@./rice/bootstrap/V601_012__Fix_Duplicate_KEW_Docs.sql
@./rice/bootstrap/V601_015__KRACOEUS-8855.sql
@./rice/bootstrap/V601_018__KRACOEUS-8890.sql
@./rice/bootstrap/V601_020__KRACOEUS-8898.sql
@./rice/bootstrap/V601_021__sponsor_kfs_int.sql
@./rice/bootstrap/V601_023__KRACOEUS-8767.sql
commit;
exit
