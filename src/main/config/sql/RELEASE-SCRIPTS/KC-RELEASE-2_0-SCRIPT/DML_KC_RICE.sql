set sqlblanklines on
set define off
SPOOL DML_KC_RICE.log
@DML/DML_BS_KRIM.sql
@DML/DML_BS_KRNS_NMSPC_T.sql
@DML/DML_BS_KRNS_PARM_DTL_TYP_T.sql
@DML/DML_BS_KRNS_PARM_T.sql
SPOOL OFF
