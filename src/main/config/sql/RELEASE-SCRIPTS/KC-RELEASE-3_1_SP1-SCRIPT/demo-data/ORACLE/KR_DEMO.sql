set define off 
set sqlblanklines on
spool kr_demo-3_1_SP1.log

@KR_DEMO3_KRIM_ROLE_MBR_T.sql
@KR_DEMO4_KRIM_ROLE_MBR_ATTR_DATA_T.sql
@KR_DEMO5_KREN_PRODCR_T.sql
@KR_SEQUENCE_RESET.sql

COMMIT;
EXIT;
