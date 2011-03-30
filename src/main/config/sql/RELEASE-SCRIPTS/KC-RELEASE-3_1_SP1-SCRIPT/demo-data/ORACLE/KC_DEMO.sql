set define off 
set sqlblanklines on
spool kc_demo-3_1_SP1.log

@KC_SEQUENCE_RESET.sql

COMMIT;
EXIT;
