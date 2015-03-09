set define off
set sqlblanklines on
spool KC-RELEASE-6_0_1-Upgrade-ORACLE-Install.log
@./kc/bootstrap/V601_001__KC_TBL_EPS_PROP_PERSON.sql
@./kc/bootstrap/V601_002__KC_DML_KRACOEUS-6639.sql
@./kc/bootstrap/V601_003__Resolve_Repackaging_Db_Issues.sql
@./kc/bootstrap/V601_007__KRACOEUS-8814.sql
@./kc/bootstrap/V601_013__KRACOEUS-8866.sql
@./kc/bootstrap/V601_019__KRACOEUS-8896.sql
@./kc/bootstrap/V601_024__KRACOEUS-8868.sql
@./kc/bootstrap/V601_025__KRACOEUS-8839.sql
@./kc/bootstrap/V601_026__KRACOEUS-8839.sql

commit;
exit