set feedback off
--set term off
set heading off
set escape \
set define off

spool install_kra_release-1.0.log

--set term on;
Prompt ************* START INSTALLING KRA DATABASE OBJECTS *************
--set term off;

--set term on;
Prompt ************* START CREATING KRA TABLES *************
--set term off;

@DDL/KRA_ALL_TABLES

--set term on;
Prompt ************* START CREATING KRA TYPES *************
--set term off;

@TYPES/KRA_ALL_TYPES

--set term on;
Prompt ************* START CREATING SEQUENCES *************
--set term off;

@SEQUENCES/KRA_ALL_SEQUENCES

--set term on;
Prompt ************* START LOADING BOOTSTRAP DATA *************
--set term off;

@DML/KRA_DML-1
@DML/KRA_DML-2
@DML/KRA_DML-3

COMMIT;

--set term on;
Prompt ************* START CREATING CONSTRAINTS *************
--set term off;

@CONSTRAINTS/KRA_ALL_PRIMARY_KEYS
@CONSTRAINTS/KRA_ALL_FOREIGN_KEYS

--set term on;
Prompt ************* START CREATING INDEXES *************
--set term off;

REM @INDEXES/KRA_ALL_INDEXES

--set term on;
Prompt ************* START LOADING S2S OBJECTS *************
--set term off;


@S2S/recompile

@S2S/procfunpacks/FN_GET_PROP_EXEMPT_NUMBERS.sql

@S2S/views/OSP_ABSTRACT_TYPE.sql
@S2S/views/OSP_ACTIVITY_TYPE.sql
@S2S/views/OSP_BUDGET.sql
@S2S/views/OSP_BUDGET_CATEGORY.sql
@S2S/views/OSP_BUDGET_CATEGORY_MAPPING.sql
@S2S/views/OSP_BUDGET_CATEGORY_MAPS.sql
@S2S/views/OSP_BUDGET_DETAILS.sql
@S2S/views/OSP_BUDGET_DETAILS_CAL_AMOUNTS.sql
@S2S/views/OSP_BUDGET_DETAILS_CAL_AMTS.sql
@S2S/views/OSP_BUDGET_JUSTIFICATION.sql
@S2S/views/OSP_BUDGET_MODULAR.sql
@S2S/views/OSP_BUDGET_MODULAR_IDC.sql
@S2S/views/OSP_BUDGET_PERIODS.sql
@S2S/views/OSP_BUDGET_PERSONNEL_CAL_AMTS.sql
@S2S/views/OSP_BUDGET_PERSONNEL_DETAILS.sql
@S2S/views/OSP_BUDGET_PERSONS.sql
@S2S/views/OSP_BUDGET_PER_CAL_AMT_FOR_EDI.sql
@S2S/views/OSP_BUDGET_PER_DETAILS_FOR_EDI.sql
@S2S/views/OSP_BUDGET_PROJECT_INCOME.sql
@S2S/views/OSP_BUDGET_RATE_AND_BASE.sql
@S2S/views/OSP_BUDGET_RATE_BASE_FOR_EDI.sql
@S2S/views/OSP_BUDGET_SUB_AWARDS.sql
@S2S/views/OSP_BUDGET_SUB_AWARD_ATT.sql
@S2S/views/OSP_COST_ELEMENT.sql
@S2S/views/OSP_COUNTRY_CODE.sql
@S2S/views/OSP_DEGREE_TYPE.sql
@S2S/views/OSP_EPS_PROPOSAL.sql
@S2S/views/OSP_EPS_PROPOSAL_STATUS.sql
@S2S/views/OSP_EPS_PROP_ABSTRACT.sql
@S2S/views/OSP_EPS_PROP_COST_SHARING.sql
@S2S/views/OSP_EPS_PROP_IDC_RATE.sql
@S2S/views/OSP_EPS_PROP_INVESTIGATORS.sql
@S2S/views/OSP_EPS_PROP_KEY_PERSONS.sql
@S2S/views/OSP_EPS_PROP_LA_RATES.sql
@S2S/views/OSP_EPS_PROP_LOCATION.sql
@S2S/views/OSP_EPS_PROP_PERSON.sql
@S2S/views/OSP_EPS_PROP_PERSON_BIO.sql
@S2S/views/OSP_EPS_PROP_PERSON_BIO_PDF.sql
@S2S/views/OSP_EPS_PROP_PERSON_DEGREE.sql
@S2S/views/OSP_EPS_PROP_PERS_YNQ.sql
@S2S/views/OSP_EPS_PROP_PER_CREDIT_SPLIT.sql
@S2S/views/OSP_EPS_PROP_PER_DOC_TYPE.sql
@S2S/views/OSP_EPS_PROP_RATES.sql
@S2S/views/OSP_EPS_PROP_SCIENCE_CODE.sql
@S2S/views/OSP_EPS_PROP_SPECIAL_REVIEW.sql
@S2S/views/OSP_EPS_PROP_UNITS.sql
@S2S/views/OSP_EPS_PROP_UNIT_CREDIT_SPLIT.sql
@S2S/views/OSP_EPS_PROP_USER_ROLES.sql
@S2S/views/OSP_EPS_PROP_YNQ.sql
@S2S/views/OSP_INSTITUTE_LA_RATES.sql
@S2S/views/OSP_INSTITUTE_RATES.sql
@S2S/views/OSP_INV_CREDIT_TYPE.sql
@S2S/views/OSP_JOB_CODE.sql
@S2S/views/OSP_NARRATIVE.sql
@S2S/views/OSP_NARRATIVE_PDF.sql
@S2S/views/OSP_NARRATIVE_TYPE.sql
@S2S/views/OSP_NARRATIVE_USER_RIGHTS.sql
@S2S/views/OSP_NOTICE_OF_OPPORTUNITY.sql
@S2S/views/OSP_NSF_CODEs.sql
@S2S/views/OSP_ORGANIZATION.sql
@S2S/views/OSP_ORGANIZATION_AUDIT.sql
@S2S/views/OSP_ORGANIZATION_IDC.sql
@S2S/views/OSP_ORGANIZATION_TYPE.sql
@S2S/views/OSP_ORGANIZATION_TYPE_LIST.sql
@S2S/views/OSP_ORGANIZATION_YNQ.sql
@S2S/views/OSP_PERSON.sql
@S2S/views/OSP_PERSON_BIO.sql
@S2S/views/OSP_PERSON_BIO_PDF.sql
@S2S/views/OSP_PERSON_BIO_SOURCE.sql
@S2S/views/OSP_PERSON_EDITABLE_FIELDS.sql
@S2S/views/OSP_PROPOSAL_INV_CERTIFICATION.sql
@S2S/views/OSP_PROPOSAL_TYPE.sql
@S2S/views/OSP_RATE_CLASS.sql
@S2S/views/OSP_RATE_TYPE.sql
@S2S/views/OSP_RIGHTS.sql
@S2S/views/OSP_ROLE.sql
@S2S/views/OSP_ROLE_RIGHTS.sql
@S2S/views/OSP_ROLODEX.sql
@S2S/views/OSP_S2S_APPLICATION.sql
@S2S/views/OSP_S2S_APP_ATTACHMENTS.sql
@S2S/views/OSP_S2S_APP_SUBMISSION.sql
@S2S/views/OSP_S2S_OPPORTUNITY.sql
@S2S/views/OSP_S2S_OPP_FORMS.sql
@S2S/views/OSP_S2S_SUBMISSION_TYPE.sql
@S2S/views/OSP_SCHOOL_CODE.sql
@S2S/views/OSP_SCIENCE_CODE.sql
@S2S/views/OSP_SPECIAL_REVIEW.sql
@S2S/views/OSP_SPONSOR.sql
@S2S/views/OSP_SPONSOR_FORMS.sql
@S2S/views/OSP_SPONSOR_FORM_TEMPLATES.sql
@S2S/views/OSP_SPONSOR_TYPE.sql
@S2S/views/OSP_SP_REV_APPROVAL_TYPE.sql
@S2S/views/OSP_STATE_CODE.sql
@S2S/views/OSP_UNIT.sql
@S2S/views/OSP_UNIT_ADMINISTRATORS.sql
@S2S/views/OSP_UNIT_ADMINISTRATOR_TYPE.sql
@S2S/views/OSP_UNIT_HIERARCHY.sql
@S2S/views/OSP_USER.sql
@S2S/views/OSP_USER_ROLES.sql
@S2S/views/OSP_VALID_CALC_TYPES.sql
@S2S/views/OSP_VALID_CE_JOB_CODES.sql
@S2S/views/OSP_VALID_CE_RATE_TYPES.sql
@S2S/views/OSP_VALID_SP_REV_APPROVAL.sql
@S2S/views/OSP_YNQ.sql
@S2S/views/OSP_YNQ_EXPLANATION.sql
@S2S/views/OSP_YNQ_EXPLANATION_TYPE.sql
@S2S/views/OSP_AWARD.sql
@S2S/views/OSP_AWARD_AMOUNT_INFO.sql


@S2S/procfunpacks/dw_get_all_rights.sql
@S2S/procfunpacks/dw_get_budget_la_exclusions.sql
@S2S/procfunpacks/dw_get_budget_oh_exclusions.sql
@S2S/procfunpacks/dw_get_budget_summary_non_per.sql
@S2S/procfunpacks/dw_get_country_code.sql
@S2S/procfunpacks/DW_GET_CUR_SYSDATE.sql
@S2S/procfunpacks/dw_get_default_prop_user_roles.sql
@S2S/procfunpacks/dw_get_eps_prop_cost_sharing.sql
@S2S/procfunpacks/dw_get_eps_prop_idc_rate.sql
@S2S/procfunpacks/dw_get_narr_users_all_mod.sql
@S2S/procfunpacks/DW_GET_ORGANIZATION_AUDIT.sql
@S2S/procfunpacks/dw_get_organization_detail.sql
@S2S/procfunpacks/DW_GET_ORGANIZATION_IDC.sql
@S2S/procfunpacks/DW_GET_ORGANIZATION_LIST.sql
@S2S/procfunpacks/dw_get_organization_type.sql
@S2S/procfunpacks/DW_GET_ORGANIZATION_TYPE_LIST.sql
@S2S/procfunpacks/dw_get_organization_ynq.sql
@S2S/procfunpacks/dw_get_proposal_key_persons.sql
@S2S/procfunpacks/dw_get_proposal_ynq.sql
@S2S/procfunpacks/dw_get_prop_person.sql
@S2S/procfunpacks/dw_get_prop_person_for_one.sql
@S2S/procfunpacks/dw_get_prop_pers_ynq_for_pp.sql
@S2S/procfunpacks/dw_get_role_rights_for_role.sql
@S2S/procfunpacks/dw_get_science_codes_for_prop.sql
@S2S/procfunpacks/dw_get_sponsor_type.sql
@S2S/procfunpacks/dw_get_state_codes.sql
@S2S/procfunpacks/dw_get_user.sql
@S2S/procfunpacks/dw_get_users_for_prop_role.sql
@S2S/procfunpacks/dw_get_users_for_role.sql
@S2S/procfunpacks/dw_get_user_roles_for_unit.sql
@S2S/procfunpacks/dw_get_ynq_explanation.sql
@S2S/procfunpacks/dw_get_ynq_explanation_all.sql
@S2S/procfunpacks/dw_get_ynq_for_qtype.sql
@S2S/procfunpacks/dw_get_ynq_list.sql
@S2S/procfunpacks/dw_update_role_rights.sql
@S2S/procfunpacks/dw_update_user.sql
@S2S/procfunpacks/fnGetCountOtherPersonnel.sql
@S2S/procfunpacks/fnGetFringe.sql
@S2S/procfunpacks/fnGetInventions.sql
@S2S/procfunpacks/FN_CHECK_PROP_READY_FOR_S2S.sql
@S2S/procfunpacks/FN_CHECK_S2S_ATTR_MATCH.sql
@S2S/procfunpacks/FN_CHECK_S2S_CANDIDATE.sql
@S2S/procfunpacks/FN_DELETE_OPP_FORMS.sql
@S2S/procfunpacks/FN_DEL_S2S_SUB_APPLICATION.sql
@S2S/procfunpacks/FN_GET_CATEGORY_DESC.sql
@S2S/procfunpacks/fn_get_eps_prop_pi_name.sql
@S2S/procfunpacks/FN_GET_FUNDING_MONTHS.sql
@S2S/procfunpacks/FN_GET_MAX_S2S_SUB_NUM.sql
@S2S/procfunpacks/FN_GET_MOD_DC_FOR_PER.sql
@S2S/procfunpacks/FN_GET_MOD_IDC_FOR_PER.sql
@S2S/procfunpacks/fn_get_nsf_checklist_answer.sql
@S2S/procfunpacks/FN_GET_NSF_DEGREE.sql
@S2S/procfunpacks/FN_GET_NUMBER_OF_MONTHS.sql
@S2S/procfunpacks/fn_get_organization_name.sql
@S2S/procfunpacks/fn_get_owned_by_unit.sql
@S2S/procfunpacks/FN_GET_PROP_DUNS_NUMBER.sql
@S2S/procfunpacks/fn_get_unit_name.sql
@S2S/procfunpacks/fn_Get_Version.sql
@S2S/procfunpacks/fn_in_sponsor_group.sql
@S2S/procfunpacks/FN_IS_FIRST_S2S_SUBMISSION.sql
@S2S/procfunpacks/fn_is_inv.sql
@S2S/procfunpacks/FN_PARSE_STRING.sql
@S2S/procfunpacks/fn_proposal_overrides_exists.sql
@S2S/procfunpacks/FN_UPD_PROPOSAL_S2S_DETAILS.sql
@S2S/procfunpacks/FN_UPD_SPONSOR_PROP_NUMBER.sql
@S2S/procfunpacks/fn_user_has_any_osp_right.sql
@S2S/procfunpacks/fn_user_has_osp_right.sql
@S2S/procfunpacks/fn_user_has_proposal_role.sql
@S2S/procfunpacks/fn_user_has_right.sql
@S2S/procfunpacks/fn_user_has_right_in_any_unit.sql
@S2S/procfunpacks/fn_user_has_role.sql
@S2S/procfunpacks/GetAbstracts.sql
@S2S/procfunpacks/GetBudgetJustification.sql
@S2S/procfunpacks/GetBudPersonSal.sql
@S2S/procfunpacks/getCostsForPrinting.sql
@S2S/procfunpacks/GetFundingMonths.sql
@S2S/procfunpacks/GetH4LobbyQuestion.sql
@S2S/procfunpacks/GetModularAmount.sql
@S2S/procfunpacks/getModularQuestion.sql
@S2S/procfunpacks/getMrLA.sql
@S2S/procfunpacks/GetNSFDuration.sql
@S2S/procfunpacks/GetNsfSeniorPersonel.sql
@S2S/procfunpacks/GetNsfSrPersonnelEffort.sql
@S2S/procfunpacks/GetNSFTotalSalAndFringe.sql
@S2S/procfunpacks/getOtherPersonnelSal.sql
@S2S/procfunpacks/getPersonOrg.sql
@S2S/procfunpacks/GetProgIncomeForPrint.sql
@S2S/procfunpacks/GetProposalPersons.sql
@S2S/procfunpacks/GetPropPersonSal.sql
@S2S/procfunpacks/GET_ALL_ROLES_FOR_USER.sql
@S2S/procfunpacks/get_authorized_Signer.sql
@S2S/procfunpacks/get_budget_cum_eb_rate_base.sql
@S2S/procfunpacks/GET_BUDGET_CUM_IDC_FOR_REPORT.sql
@S2S/procfunpacks/GET_BUDGET_CUM_LA_EXCLUSIONS.sql
@S2S/procfunpacks/GET_BUDGET_CUM_LA_RATE_BASE.sql
@S2S/procfunpacks/GET_BUDGET_CUM_OH_EXCLUSIONS.sql
@S2S/procfunpacks/GET_BUDGET_CUM_OH_RATE_BASE.sql
@S2S/procfunpacks/GET_BUDGET_CUM_OTHER_RATE_BASE.sql
@S2S/procfunpacks/get_budget_cum_summary_non_per.sql
@S2S/procfunpacks/GET_BUDGET_CUM_VAC_RATE_BASE.sql
@S2S/procfunpacks/GET_BUDGET_EB_RATE_BASE.sql
@S2S/procfunpacks/GET_BUDGET_IDC_FOR_REPORT.sql
@S2S/procfunpacks/GET_BUDGET_LA_EXCLUSIONS.sql
@S2S/procfunpacks/GET_BUDGET_LA_RATE_BASE.sql
@S2S/procfunpacks/GET_BUDGET_OH_EXCLUSIONS.sql
@S2S/procfunpacks/GET_BUDGET_OH_RATE_BASE.sql
@S2S/procfunpacks/GET_BUDGET_OTHER_RATE_BASE.sql
@S2S/procfunpacks/get_budget_sub_award_att_all.sql
@S2S/procfunpacks/GET_BUDGET_SUMMARY_NON_PER.sql
@S2S/procfunpacks/GET_BUDGET_VAC_RATE_BASE.sql
@S2S/procfunpacks/get_bud_periods_for_prnt.sql
@S2S/procfunpacks/GET_CLOB_PAGE_DATA.sql
@S2S/procfunpacks/get_consortium_costs.sql
@S2S/procfunpacks/get_consortium_fanda_costs.sql
@S2S/procfunpacks/get_cum_salary_summary.sql
@S2S/procfunpacks/GET_EPS_PROPOSAL_DETAIL.sql
@S2S/procfunpacks/GET_EPS_PROPOSAL_TITLE.sql
@S2S/procfunpacks/Get_Funding_Months.sql
@S2S/procfunpacks/get_home_unit.sql
@S2S/procfunpacks/get_indrl_bgt_summary_non_per.sql
@S2S/procfunpacks/GET_INDSRL_SALARY_SUMMARY.sql
@S2S/procfunpacks/get_major_subdivision.sql
@S2S/procfunpacks/get_max_prop_degree_for_one.sql
@S2S/procfunpacks/get_name_from_rolodex.sql
@S2S/procfunpacks/GET_NARRATIVE_TYPE.sql
@S2S/procfunpacks/GET_NARR_USERS_UNIQ_MOD.sql
@S2S/procfunpacks/get_orga_name_addressid.sql
@S2S/procfunpacks/get_org_contact.sql
@S2S/procfunpacks/GET_PARAMETER_VALUE.sql
@S2S/procfunpacks/get_person_info.sql
@S2S/procfunpacks/GET_PER_BIO_FOR_PERSON.sql
@S2S/procfunpacks/GET_PROPOSAL_INVESTIGATORS.sql
@S2S/procfunpacks/GET_PROPOSAL_ROLES_FOR_UNIT.sql
@S2S/procfunpacks/GET_PROP_BIO_FOR_PERSON.sql
@S2S/procfunpacks/get_prop_investigator_units.sql
@S2S/procfunpacks/GET_PROP_LOCATION_LIST.sql
@S2S/procfunpacks/GET_PROP_NARR_PDF_FOR_PROP.sql
@S2S/procfunpacks/GET_PRO_SP_REV.sql
@S2S/procfunpacks/get_p_budget_for_pk.sql
@S2S/procfunpacks/GET_ROLE_RIGHTS_FOR_ROLE.sql
@S2S/procfunpacks/GET_ROLODEX.sql
@S2S/procfunpacks/GET_S2S_APPLICATION.sql
@S2S/procfunpacks/GET_S2S_OPPORTUNITY.sql
@S2S/procfunpacks/GET_S2S_OPP_FORMS.sql
@S2S/procfunpacks/GET_S2S_SUBMISSION_TYPE_CODES.sql
@S2S/procfunpacks/GET_S2S_SUB_ATTACHEMNTS.sql
@S2S/procfunpacks/GET_S2S_SUB_ATTACHMENT_DATA.sql
@S2S/procfunpacks/GET_S2S_SUB_DETAILS.sql
@S2S/procfunpacks/GET_S2S_SUB_DETAILS_FOR_POLL.sql
@S2S/procfunpacks/GET_SALARY_SUMMARY.sql
@S2S/procfunpacks/GET_SALARY_SUMMARY_FOR_EDI.sql
@S2S/procfunpacks/GET_SPONSOR.sql
@S2S/procfunpacks/get_sponsor_name_new.sql
@S2S/procfunpacks/get_tot_bud_info_for_prnt.sql
@S2S/procfunpacks/get_unit_name.sql
@S2S/procfunpacks/GET_USERS_FOR_UNIT.sql
@S2S/procfunpacks/pkg_oh_rate_and_base.sql
@S2S/procfunpacks/PR_RESULT_TABLE.sql
@S2S/procfunpacks/RESULT_SETS.sql
@S2S/procfunpacks/s2sAttachmentsV11Pkg.sql
@S2S/procfunpacks/s2sED524V11Pkg.sql
@S2S/procfunpacks/s2sEDSF424SupplementV11Pkg.sql
@S2S/procfunpacks/s2sFaithBased_SurveyOnEEOPkg.sql
@S2S/procfunpacks/s2sGG_LobbyingFormV11Pkg.sql
@S2S/procfunpacks/s2sKeyPersonExp_V11Pkg.sql
@S2S/procfunpacks/s2sKeyPerson_V11Pkg.sql
@S2S/procfunpacks/s2sNasaOtherProjectInforPkg.sql
@S2S/procfunpacks/s2sNasaPIandAORSmtDtShtPkg.sql
@S2S/procfunpacks/s2sNASASenKeyPerSmtDtShtPkg.sql
@S2S/procfunpacks/s2spackage.sql
@S2S/procfunpacks/s2sPHS398ChecklistPkg.sql
@S2S/procfunpacks/s2sPHS398CoverPageSupplemtPkg.sql
@S2S/procfunpacks/s2sPHS398ModBud.sql
@S2S/procfunpacks/s2sRRFedNonFedBudgetPkg.sql
@S2S/procfunpacks/s2sRRPerfSites_V11Pkg.sql
@S2S/procfunpacks/s2sRRSF424_V11Pkg.sql
@S2S/procfunpacks/s2sSF424V2Pkg.sql
@S2S/procfunpacks/s2sSFLLLPkg.sql
@S2S/procfunpacks/select_rolodex.sql
@S2S/procfunpacks/UPD_S2S_APP_SUBMISSION.sql
@S2S/procfunpacks/UPD_S2S_OPPORTUNITY.sql
@S2S/procfunpacks/UPD_S2S_OPP_FORMS.sql
@S2S/procfunpacks/UPD_S2S_SUB_ATTACHMENT.sql
@S2S/procfunpacks/DW_GET_UNIT_DETAIL_NEW.sql
@S2S/procfunpacks/GET_UNIT_HIERARCHY_NODE.sql
@S2S/procfunpacks/GetNIHAward.sql
@S2S/procfunpacks/GetNSFPrevAward.sql
@S2S/procfunpacks/getTotalProjEndDt.sql
@S2S/procfunpacks/getTotalProjStartDt.sql

Prompt Recompiling invalid objects

begin DBMS_UTILITY.COMPILE_SCHEMA(USER); END;
/

select 'Invalid objects still remaining: ' || count(*) from user_objects where status = 'INVALID';

--set term on;
Prompt ************* END KC DATABASE UPDATE *************
--set term off;

exit
