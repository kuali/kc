set sqlblanklines on
set define off
SPOOL DML_KC_RICE.log
PROMPT DML_BS_KRIM.sql
@DML/DML_BS_KRIM.sql
PROMPT DML_BS_KRNS.sql
@DML/DML_BS_KRNS.sql
SPOOL OFF
