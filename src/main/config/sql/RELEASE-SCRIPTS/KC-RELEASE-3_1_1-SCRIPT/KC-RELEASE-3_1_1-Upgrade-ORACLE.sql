set define off
set sqlblanklines on
spool KC-RELEASE-3_1_1-Upgrade-ORACLE-Install.log
@oracle/sequences/KC_SEQ_SEQ_CUSTOM_ATTRIBUTE.sql
@oracle/sequences/KC_SEQ_SEQ_FIN_OBJECT_CODE_MAPPING_ID.sql
@oracle/tables/KC_TBL_AWARD.sql
@oracle/tables/KC_TBL_AWARD_IDC_RATE.sql
@oracle/tables/KC_TBL_BUDGET.sql
@oracle/tables/KC_TBL_BUDGET_DETAILS.sql
@oracle/tables/KC_TBL_COST_ELEMENT.sql
@oracle/tables/KC_TBL_EPS_PROP_PERSON_EXT.sql
@oracle/tables/KC_TBL_FIN_IDC_TYPE_CODE.sql
@oracle/tables/KC_TBL_FIN_OBJECT_CODE_MAPPING.sql
@oracle/tables/KC_TBL_PERSON_EXT_T.sql
@oracle/tables/KC_TBL_RATE_CLASS.sql
@oracle/tables/KC_TBL_REVIEWER_ATTACHMENTS.sql
@oracle/tables/KC_TBL_STATE_CODE.sql
@oracle/tables/KC_TBL_USER_TABLES_TEMP.sql
@oracle/dml/KC_DML_01_KCINFR-347_B000.sql
@oracle/dml/KC_DML_01_KCINFR-348_B000.sql
@oracle/dml/KC_DML_01_KCINFR-350_B000.sql
@oracle/dml/KC_DML_01_KCIRB-1448_B000.sql
@oracle/dml/KC_DML_01_KCIRB-1449_B000.sql
@oracle/dml/KC_DML_01_KRACOEUS-4357_B000.sql
@oracle/constraints/KC_FK_FIN_IDC_TYPE_CODE.sql
@oracle/constraints/KC_FK_FIN_OBJECT_CODE_MAPPING.sql
@oracle/constraints/KC_UQ_FIN_OBJECT_CODE_MAPPING.sql
commit;
exit
