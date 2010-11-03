set sqlblanklines on
set define off
spool KR-Release-2_0-3_0-Upgrade-Oracle-Install.log

Prompt Running DML_BS1_KREN_CHNL_T.sql...
@ORACLE\DML\DML_BS1_KREN_CHNL_T.sql

Prompt Running DML_BS1_KRIM_ATTR_DEFN_T.sql...
@ORACLE\DML\DML_BS1_KRIM_ATTR_DEFN_T.sql

Prompt Running DML_BS1_KRIM_ENTITY.sql...
@ORACLE\DML\DML_BS1_KRIM_ENTITY.sql

Prompt Running DML_BS1_KRIM_TYP_T.sql...
@ORACLE\DML\DML_BS1_KRIM_TYP_T.sql

Prompt Running DML_BS2_KREN_CHNL_PRODCR_T.sql...
@ORACLE\DML\DML_BS2_KREN_CHNL_PRODCR_T.sql

Prompt Running DML_BS2_KRIM_PERM_TMPL_T.sql...
@ORACLE\DML\DML_BS2_KRIM_PERM_TMPL_T.sql

Prompt Running DML_BS2_KRIM_ROLE_T.sql...
@ORACLE\DML\DML_BS2_KRIM_ROLE_T.sql

Prompt Running DML_BS2_KRIM_RSP_T.sql...
@ORACLE\DML\DML_BS2_KRIM_RSP_T.sql

Prompt Running DML_BS2_KRIM_TYP_ATTR_T.sql...
@ORACLE\DML\DML_BS2_KRIM_TYP_ATTR_T.sql

Prompt Running DML_BS2_KRNS_PARM_T.sql...
@ORACLE\DML\DML_BS2_KRNS_PARM_T.sql

Prompt Running DML_BS3_KRIM_PERM_T.sql...
@ORACLE\DML\DML_BS3_KRIM_PERM_T.sql

Prompt Running DML_BS4_KRIM_PERM_ATTR_DATA_T.sql...
@ORACLE\DML\DML_BS4_KRIM_PERM_ATTR_DATA_T.sql

Prompt Running DML_BS4_KRIM_ROLE_MBR_T.sql...
@ORACLE\DML\DML_BS4_KRIM_ROLE_MBR_T.sql

Prompt Running DML_BS4_KRIM_ROLE_PERM_T.sql...
@ORACLE\DML\DML_BS4_KRIM_ROLE_PERM_T.sql

Prompt Running DML_BS4_KRIM_ROLE_RSP_ACTN_T.sql...
@ORACLE\DML\DML_BS4_KRIM_ROLE_RSP_ACTN_T.sql

Prompt Running DML_BS4_KRIM_ROLE_RSP_T.sql...
@ORACLE\DML\DML_BS4_KRIM_ROLE_RSP_T.sql

Prompt Running DML_BS4_KRIM_RSP_ATTR_DATA_T.sql...
@ORACLE\DML\DML_BS4_KRIM_RSP_ATTR_DATA_T.sql

commit;
spool off
exit
