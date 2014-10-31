set define off
set sqlblanklines on
spool KC-RELEASE-6_0_0-Upgrade-ORACLE-Install.log
@../../current/6.0.0/sequences/KC_SEQ_DOCUMENT_ACCESS_ID.sql
@../../current/6.0.0/sequences/KC_SEQ_EPS_PROP_PERSON_ROLE.sql
@../../current/6.0.0/sequences/KC_SEQ_S2S_ERROR_ID.sql
@../../current/6.0.0/tables/KC_TBL_AWARD_BUDGET_EXT.sql
@../../current/6.0.0/tables/KC_TBL_AWARD_PERSONS.sql
@../../current/6.0.0/tables/KC_TBL_BUDGET.sql
@../../current/6.0.0/tables/KC_TBL_DOCUMENT_ACCESS.sql
@../../current/6.0.0/tables/KC_TBL_DOCUMENT_NEXTVALUE.sql
@../../current/6.0.0/tables/KC_TBL_EPS_PROPOSAL.sql
@../../current/6.0.0/tables/KC_TBL_EPS_PROPOSAL_BUDGET_EXT.sql
@../../current/6.0.0/tables/KC_TBL_EPS_PROP_CONG_DISTRICT.sql
@../../current/6.0.0/tables/KC_TBL_EPS_PROP_PERSON.sql
@../../current/6.0.0/tables/KC_TBL_EPS_PROP_PERSON_EXT.sql
@../../current/6.0.0/tables/KC_TBL_EPS_PROP_PERSON_ROLE.sql
@../../current/6.0.0/tables/KC_TBL_PROPOSAL_PERSONS.sql
@../../current/6.0.0/tables/KC_TBL_PROPOSAL_RESPONSE.sql
@../../current/6.0.0/tables/KC_TBL_S2S_ERROR.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-6956_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-7089_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-7109_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-7120_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-7722_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-7889_B000.sql
@../../current/6.0.0/dml/KC_DML_02_KCINFR-979_B000.sql
commit;
exit
