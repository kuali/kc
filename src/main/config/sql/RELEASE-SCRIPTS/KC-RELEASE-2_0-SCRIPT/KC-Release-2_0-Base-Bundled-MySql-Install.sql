select 'Dropping schema...';
drop schema if exists kcptd;
select 'Creating schema...';
create schema kcptd;
use kcptd;
\. RICE/BUNDLEDSERVER_MYSQL/sql/schema.sql
select "Running datasql/ABSTRACT_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/ABSTRACT_TYPE.sql
select "Running datasql/ACCOUNT_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/ACCOUNT_TYPE.sql
select "Running datasql/ACTIVITY_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/ACTIVITY_TYPE.sql
select "Running datasql/AFFILIATION_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/AFFILIATION_TYPE.sql
select "Running datasql/APPOINTMENT_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/APPOINTMENT_TYPE.sql
select "Running datasql/ARG_VALUE_LOOKUP.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/ARG_VALUE_LOOKUP.sql
select "Running datasql/AWARD_ATTACHMENT_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/AWARD_ATTACHMENT_TYPE.sql
select "Running datasql/AWARD_BASIS_OF_PAYMENT.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/AWARD_BASIS_OF_PAYMENT.sql
select "Running datasql/AWARD_BUDGET_STATUS.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/AWARD_BUDGET_STATUS.sql
select "Running datasql/AWARD_BUDGET_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/AWARD_BUDGET_TYPE.sql
select "Running datasql/AWARD_METHOD_OF_PAYMENT.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/AWARD_METHOD_OF_PAYMENT.sql
select "Running datasql/AWARD_STATUS.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/AWARD_STATUS.sql
--select "Running datasql/AWARD_TEMPLATE_COMMENTS.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/AWARD_TEMPLATE_COMMENTS.sql
--select "Running datasql/AWARD_TEMPLATE_CONTACT.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/AWARD_TEMPLATE_CONTACT.sql
--select "Running datasql/AWARD_TEMPLATE_REPORT_TERMS.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/AWARD_TEMPLATE_REPORT_TERMS.sql
--select "Running datasql/AWARD_TEMPLATE.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/AWARD_TEMPLATE.sql
--select "Running datasql/AWARD_TEMPLATE_TERMS.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/AWARD_TEMPLATE_TERMS.sql
--select "Running datasql/AWARD_TEMPL_REP_TERMS_RECNT.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/AWARD_TEMPL_REP_TERMS_RECNT.sql
select "Running datasql/AWARD_TRANSACTION_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/AWARD_TRANSACTION_TYPE.sql
select "Running datasql/AWARD_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/AWARD_TYPE.sql
select "Running datasql/BATCH_CORRESPONDENCE_DETAIL.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/BATCH_CORRESPONDENCE_DETAIL.sql
select "Running datasql/BATCH_CORRESPONDENCE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/BATCH_CORRESPONDENCE.sql
select "Running datasql/BUDGET_CATEGORY_MAPPING.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/BUDGET_CATEGORY_MAPPING.sql
select "Running datasql/BUDGET_CATEGORY_MAPS.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/BUDGET_CATEGORY_MAPS.sql
select "Running datasql/BUDGET_CATEGORY.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/BUDGET_CATEGORY.sql
select "Running datasql/BUDGET_CATEGORY_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/BUDGET_CATEGORY_TYPE.sql
select "Running datasql/BUDGET_PERIOD_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/BUDGET_PERIOD_TYPE.sql
select "Running datasql/BUDGET_STATUS.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/BUDGET_STATUS.sql
select "Running datasql/CARRIER_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/CARRIER_TYPE.sql
select "Running datasql/CLOSEOUT_REPORT_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/CLOSEOUT_REPORT_TYPE.sql
select "Running datasql/COEUS_MODULE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/COEUS_MODULE.sql
select "Running datasql/COMMENT_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/COMMENT_TYPE.sql
select "Running datasql/COMMITTEE_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/COMMITTEE_TYPE.sql
select "Running datasql/COMM_MEMBERSHIP_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/COMM_MEMBERSHIP_TYPE.sql
select "Running datasql/COMM_SCHEDULE_FREQUENCY.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/COMM_SCHEDULE_FREQUENCY.sql
select "Running datasql/CONTACT_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/CONTACT_TYPE.sql
select "Running datasql/CORRESPONDENT_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/CORRESPONDENT_TYPE.sql
--select "Running datasql/COST_ELEMENT.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/COST_ELEMENT.sql
select "Running datasql/COST_SHARE_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/COST_SHARE_TYPE.sql
select "Running datasql/COUNTRY_CODE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/COUNTRY_CODE.sql
select "Running datasql/CUSTOM_ATTRIBUTE_DATA_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/CUSTOM_ATTRIBUTE_DATA_TYPE.sql
select "Running datasql/CUSTOM_ATTRIBUTE_DOCUMENT.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/CUSTOM_ATTRIBUTE_DOCUMENT.sql
--select "Running datasql/CUSTOM_ATTRIBUTE.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/CUSTOM_ATTRIBUTE.sql
select "Running datasql/DEADLINE_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/DEADLINE_TYPE.sql
select "Running datasql/DEGREE_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/DEGREE_TYPE.sql
select "Running datasql/DISTRIBUTION.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/DISTRIBUTION.sql
select "Running datasql/EPS_PROP_COLUMNS_TO_ALTER.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/EPS_PROP_COLUMNS_TO_ALTER.sql
select "Running datasql/EPS_PROPOSAL_STATUS.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/EPS_PROPOSAL_STATUS.sql
select "Running datasql/EPS_PROP_PER_DOC_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/EPS_PROP_PER_DOC_TYPE.sql
select "Running datasql/EPS_PROP_PERSON_ROLE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/EPS_PROP_PERSON_ROLE.sql
select "Running datasql/EPS_PROP_POST_SUB_STATUS.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/EPS_PROP_POST_SUB_STATUS.sql
select "Running datasql/EXEMPTION_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/EXEMPTION_TYPE.sql
select "Running datasql/EXEMPT_STUDIES_CHECKLIST.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/EXEMPT_STUDIES_CHECKLIST.sql
select "Running datasql/EXPEDITED_REVIEW_CHECKLIST.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/EXPEDITED_REVIEW_CHECKLIST.sql
select "Running datasql/FP_DOC_TYPE_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/FP_DOC_TYPE_T.sql
select "Running datasql/FREQUENCY_BASE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/FREQUENCY_BASE.sql
select "Running datasql/FREQUENCY.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/FREQUENCY.sql
select "Running datasql/FUNDING_SOURCE_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/FUNDING_SOURCE_TYPE.sql
select "Running datasql/GROUP_TYPES.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/GROUP_TYPES.sql
select "Running datasql/IDC_RATE_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/IDC_RATE_TYPE.sql
--select "Running datasql/INSTITUTE_LA_RATES.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/INSTITUTE_LA_RATES.sql
--select "Running datasql/INSTITUTE_RATES.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/INSTITUTE_RATES.sql
--select "Running datasql/INV_CREDIT_TYPE.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/INV_CREDIT_TYPE.sql
select "Running datasql/IP_REVIEW_ACTIVITY_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/IP_REVIEW_ACTIVITY_TYPE.sql
select "Running datasql/IP_REVIEW_REQ_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/IP_REVIEW_REQ_TYPE.sql
select "Running datasql/IP_REVIEW_RESULT_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/IP_REVIEW_RESULT_TYPE.sql
--select "Running datasql/JOB_CODE.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/JOB_CODE.sql
select "Running datasql/KC_COUNTRY_CODES_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KC_COUNTRY_CODES_T.sql
select "Running datasql/KRA_USER.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRA_USER.sql
select "Running datasql/KR_COUNTRY_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KR_COUNTRY_T.sql
select "Running datasql/KREN_CHNL_PRODCR_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREN_CHNL_PRODCR_T.sql
select "Running datasql/KREN_CHNL_SUBSCRP_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREN_CHNL_SUBSCRP_T.sql
select "Running datasql/KREN_CHNL_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREN_CHNL_T.sql
select "Running datasql/KREN_CNTNT_TYP_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREN_CNTNT_TYP_T.sql
select "Running datasql/KREN_PRIO_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREN_PRIO_T.sql
select "Running datasql/KREN_PRODCR_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREN_PRODCR_T.sql
select "Running datasql/KREN_RECIP_DELIV_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREN_RECIP_DELIV_T.sql
select "Running datasql/KREN_RECIP_LIST_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREN_RECIP_LIST_T.sql
select "Running datasql/KREN_RVWER_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREN_RVWER_T.sql
select "Running datasql/KREW_ACTN_ITM_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_ACTN_ITM_T.sql
select "Running datasql/KREW_ACTN_RQST_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_ACTN_RQST_T.sql
select "Running datasql/KREW_ACTN_TKN_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_ACTN_TKN_T.sql
select "Running datasql/KREW_ATT_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_ATT_T.sql
select "Running datasql/KREW_DOC_HDR_CNTNT_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_DOC_HDR_CNTNT_T.sql
select "Running datasql/KREW_DOC_HDR_EXT_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_DOC_HDR_EXT_T.sql
select "Running datasql/KREW_DOC_HDR_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_DOC_HDR_T.sql
select "Running datasql/KREW_DOC_NTE_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_DOC_NTE_T.sql
select "Running datasql/KREW_DOC_TYP_ATTR_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_DOC_TYP_ATTR_T.sql
select "Running datasql/KREW_DOC_TYP_PLCY_RELN_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_DOC_TYP_PLCY_RELN_T.sql
select "Running datasql/KREW_DOC_TYP_PROC_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_DOC_TYP_PROC_T.sql
select "Running datasql/KREW_DOC_TYP_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_DOC_TYP_T.sql
select "Running datasql/KREW_EDL_ASSCTN_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_EDL_ASSCTN_T.sql
select "Running datasql/KREW_EDL_DEF_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_EDL_DEF_T.sql
select "Running datasql/KREW_INIT_RTE_NODE_INSTN_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_INIT_RTE_NODE_INSTN_T.sql
select "Running datasql/KREW_OUT_BOX_ITM_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_OUT_BOX_ITM_T.sql
select "Running datasql/KREW_RTE_BRCH_PROTO_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_RTE_BRCH_PROTO_T.sql
select "Running datasql/KREW_RTE_BRCH_ST_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_RTE_BRCH_ST_T.sql
select "Running datasql/KREW_RTE_BRCH_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_RTE_BRCH_T.sql
select "Running datasql/KREW_RTE_NODE_CFG_PARM_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_RTE_NODE_CFG_PARM_T.sql
select "Running datasql/KREW_RTE_NODE_INSTN_LNK_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_RTE_NODE_INSTN_LNK_T.sql
select "Running datasql/KREW_RTE_NODE_INSTN_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_RTE_NODE_INSTN_T.sql
select "Running datasql/KREW_RTE_NODE_LNK_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_RTE_NODE_LNK_T.sql
select "Running datasql/KREW_RTE_NODE_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_RTE_NODE_T.sql
select "Running datasql/KREW_RULE_ATTR_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_RULE_ATTR_T.sql
select "Running datasql/KREW_RULE_EXT_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_RULE_EXT_T.sql
select "Running datasql/KREW_RULE_EXT_VAL_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_RULE_EXT_VAL_T.sql
select "Running datasql/KREW_RULE_RSP_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_RULE_RSP_T.sql
select "Running datasql/KREW_RULE_TMPL_ATTR_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_RULE_TMPL_ATTR_T.sql
select "Running datasql/KREW_RULE_TMPL_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_RULE_TMPL_T.sql
select "Running datasql/KREW_RULE_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_RULE_T.sql
select "Running datasql/KREW_STYLE_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_STYLE_T.sql
select "Running datasql/KREW_USR_OPTN_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KREW_USR_OPTN_T.sql
select "Running datasql/KRIM_ADDR_TYP_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_ADDR_TYP_T.sql
select "Running datasql/KRIM_AFLTN_TYP_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_AFLTN_TYP_T.sql
select "Running datasql/KRIM_ATTR_DEFN_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_ATTR_DEFN_T.sql
select "Running datasql/KRIM_EMAIL_TYP_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_EMAIL_TYP_T.sql
select "Running datasql/KRIM_EMP_STAT_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_EMP_STAT_T.sql
select "Running datasql/KRIM_EMP_TYP_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_EMP_TYP_T.sql
select "Running datasql/KRIM_ENTITY_ADDR_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_ENTITY_ADDR_T.sql
select "Running datasql/KRIM_ENTITY_CACHE_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_ENTITY_CACHE_T.sql
select "Running datasql/KRIM_ENTITY_EMAIL_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_ENTITY_EMAIL_T.sql
select "Running datasql/KRIM_ENTITY_EMP_INFO_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_ENTITY_EMP_INFO_T.sql
select "Running datasql/KRIM_ENTITY_ENT_TYP_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_ENTITY_ENT_TYP_T.sql
select "Running datasql/KRIM_ENTITY_NM_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_ENTITY_NM_T.sql
select "Running datasql/KRIM_ENTITY_PHONE_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_ENTITY_PHONE_T.sql
select "Running datasql/KRIM_ENTITY_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_ENTITY_T.sql
select "Running datasql/KRIM_ENT_NM_TYP_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_ENT_NM_TYP_T.sql
select "Running datasql/KRIM_ENT_TYP_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_ENT_TYP_T.sql
select "Running datasql/KRIM_EXT_ID_TYP_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_EXT_ID_TYP_T.sql
select "Running datasql/KRIM_GRP_MBR_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_GRP_MBR_T.sql
select "Running datasql/KRIM_GRP_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_GRP_T.sql
select "Running datasql/KRIM_PERM_ATTR_DATA_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_PERM_ATTR_DATA_T.sql
select "Running datasql/KRIM_PERM_TMPL_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_PERM_TMPL_T.sql
select "Running datasql/KRIM_PERM_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_PERM_T.sql
select "Running datasql/KRIM_PHONE_TYP_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_PHONE_TYP_T.sql
select "Running datasql/KRIM_PRNCPL_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_PRNCPL_T.sql
select "Running datasql/KRIM_ROLE_MBR_ATTR_DATA_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_ROLE_MBR_ATTR_DATA_T.sql
select "Running datasql/KRIM_ROLE_MBR_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_ROLE_MBR_T.sql
select "Running datasql/KRIM_ROLE_PERM_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_ROLE_PERM_T.sql
select "Running datasql/KRIM_ROLE_RSP_ACTN_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_ROLE_RSP_ACTN_T.sql
select "Running datasql/KRIM_ROLE_RSP_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_ROLE_RSP_T.sql
select "Running datasql/KRIM_ROLE_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_ROLE_T.sql
select "Running datasql/KRIM_RSP_ATTR_DATA_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_RSP_ATTR_DATA_T.sql
select "Running datasql/KRIM_RSP_TMPL_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_RSP_TMPL_T.sql
select "Running datasql/KRIM_RSP_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_RSP_T.sql
select "Running datasql/KRIM_TYP_ATTR_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_TYP_ATTR_T.sql
select "Running datasql/KRIM_TYP_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRIM_TYP_T.sql
select "Running datasql/KRNS_CAMPUS_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRNS_CAMPUS_T.sql
select "Running datasql/KRNS_CMP_TYP_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRNS_CMP_TYP_T.sql
select "Running datasql/KRNS_DOC_HDR_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRNS_DOC_HDR_T.sql
select "Running datasql/KRNS_MAINT_DOC_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRNS_MAINT_DOC_T.sql
select "Running datasql/KRNS_NMSPC_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRNS_NMSPC_T.sql
select "Running datasql/KRNS_NTE_TYP_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRNS_NTE_TYP_T.sql
select "Running datasql/KRNS_PARM_DTL_TYP_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRNS_PARM_DTL_TYP_T.sql
select "Running datasql/KRNS_PARM_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRNS_PARM_T.sql
select "Running datasql/KRNS_PARM_TYP_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRNS_PARM_TYP_T.sql
select "Running datasql/KRSB_MSG_PYLD_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRSB_MSG_PYLD_T.sql
select "Running datasql/KRSB_MSG_QUE_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRSB_MSG_QUE_T.sql
select "Running datasql/KRSB_QRTZ_FIRED_TRIGGERS.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRSB_QRTZ_FIRED_TRIGGERS.sql
select "Running datasql/KRSB_QRTZ_LOCKS.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRSB_QRTZ_LOCKS.sql
select "Running datasql/KRSB_QRTZ_SCHEDULER_STATE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRSB_QRTZ_SCHEDULER_STATE.sql
select "Running datasql/KRSB_QRTZ_TRIGGERS.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KRSB_QRTZ_TRIGGERS.sql
select "Running datasql/KR_STATE_T.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/KR_STATE_T.sql
select "Running datasql/LOCATION_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/LOCATION_TYPE.sql
select "Running datasql/MAIL_BY.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/MAIL_BY.sql
select "Running datasql/MAIL_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/MAIL_TYPE.sql
select "Running datasql/MEMBERSHIP_ROLE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/MEMBERSHIP_ROLE.sql
select "Running datasql/MINUTE_ENTRY_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/MINUTE_ENTRY_TYPE.sql
select "Running datasql/NARRATIVE_STATUS.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/NARRATIVE_STATUS.sql
select "Running datasql/NARRATIVE_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/NARRATIVE_TYPE.sql
select "Running datasql/NOTICE_OF_OPPORTUNITY.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/NOTICE_OF_OPPORTUNITY.sql
select "Running datasql/NSF_CODES.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/NSF_CODES.sql
--select "Running datasql/ORGANIZATION.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/ORGANIZATION.sql
select "Running datasql/ORGANIZATION_TYPE_LIST.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/ORGANIZATION_TYPE_LIST.sql
--select "Running datasql/ORGANIZATION_TYPE.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/ORGANIZATION_TYPE.sql
--select "Running datasql/ORGANIZATION_YNQ.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/ORGANIZATION_YNQ.sql
--select "Running datasql/PERSON_APPOINTMENT.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/PERSON_APPOINTMENT.sql
select "Running datasql/PERSON_EDITABLE_FIELDS.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PERSON_EDITABLE_FIELDS.sql
--select "Running datasql/PERSON_EXT_T.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/PERSON_EXT_T.sql
select "Running datasql/PERSON_TRAINING.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PERSON_TRAINING.sql
select "Running datasql/PROPOSAL_LOG_STATUS.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROPOSAL_LOG_STATUS.sql
select "Running datasql/PROPOSAL_LOG_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROPOSAL_LOG_TYPE.sql
select "Running datasql/PROPOSAL_RESPONSE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROPOSAL_RESPONSE.sql
select "Running datasql/PROPOSAL_STATE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROPOSAL_STATE.sql
select "Running datasql/PROPOSAL_STATUS.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROPOSAL_STATUS.sql
select "Running datasql/PROPOSAL_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROPOSAL_TYPE.sql
select "Running datasql/PROTOCOL_ACTION_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROTOCOL_ACTION_TYPE.sql
select "Running datasql/PROTOCOL_ATTACHMENT_GROUP.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROTOCOL_ATTACHMENT_GROUP.sql
select "Running datasql/PROTOCOL_ATTACHMENT_STATUS.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROTOCOL_ATTACHMENT_STATUS.sql
select "Running datasql/PROTOCOL_ATTACHMENT_TYPE_GROUP.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROTOCOL_ATTACHMENT_TYPE_GROUP.sql
select "Running datasql/PROTOCOL_ATTACHMENT_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROTOCOL_ATTACHMENT_TYPE.sql
select "Running datasql/PROTOCOL_CONTINGENCY.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROTOCOL_CONTINGENCY.sql
select "Running datasql/PROTOCOL_MODULES.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROTOCOL_MODULES.sql
select "Running datasql/PROTOCOL_ORG_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROTOCOL_ORG_TYPE.sql
select "Running datasql/PROTOCOL_PERSON_ROLE_MAPPING.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROTOCOL_PERSON_ROLE_MAPPING.sql
select "Running datasql/PROTOCOL_PERSON_ROLES.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROTOCOL_PERSON_ROLES.sql
select "Running datasql/PROTOCOL_REFERENCE_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROTOCOL_REFERENCE_TYPE.sql
select "Running datasql/PROTOCOL_REVIEWER_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROTOCOL_REVIEWER_TYPE.sql
select "Running datasql/PROTOCOL_REVIEW_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROTOCOL_REVIEW_TYPE.sql
select "Running datasql/PROTOCOL_STATUS.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROTOCOL_STATUS.sql
select "Running datasql/PROTOCOL_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROTOCOL_TYPE.sql
select "Running datasql/PROTO_CORRESP_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/PROTO_CORRESP_TYPE.sql
select "Running datasql/QUESTION_EXPLANATION.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/QUESTION_EXPLANATION.sql
select "Running datasql/QUESTION.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/QUESTION.sql
select "Running datasql/QUESTION_TYPES.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/QUESTION_TYPES.sql
--select "Running datasql/RATE_CLASS.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/RATE_CLASS.sql
select "Running datasql/RATE_CLASS_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/RATE_CLASS_TYPE.sql
--select "Running datasql/RATE_TYPE.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/RATE_TYPE.sql
select "Running datasql/REPORT_CLASS.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/REPORT_CLASS.sql
select "Running datasql/REPORT.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/REPORT.sql
select "Running datasql/RESEARCH_AREAS.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/RESEARCH_AREAS.sql
select "Running datasql/RISK_LEVEL.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/RISK_LEVEL.sql
--select "Running datasql/ROLODEX.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/ROLODEX.sql
select "Running datasql/S2S_REVISION_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/S2S_REVISION_TYPE.sql
select "Running datasql/S2S_SUBMISSION_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/S2S_SUBMISSION_TYPE.sql
select "Running datasql/SCHEDULE_ACT_ITEM_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/SCHEDULE_ACT_ITEM_TYPE.sql
select "Running datasql/SCHEDULE_STATUS.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/SCHEDULE_STATUS.sql
select "Running datasql/SCHOOL_CODE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/SCHOOL_CODE.sql
--select "Running datasql/SCIENCE_KEYWORD.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/SCIENCE_KEYWORD.sql
select "Running datasql/SPECIAL_REVIEW.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/SPECIAL_REVIEW.sql
--select "Running datasql/SPONSOR_FORMS.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/SPONSOR_FORMS.sql
--select "Running datasql/SPONSOR_FORM_TEMPLATES.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/SPONSOR_FORM_TEMPLATES.sql
--select "Running datasql/SPONSOR_HIERARCHY.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/SPONSOR_HIERARCHY.sql
--select "Running datasql/SPONSOR.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/SPONSOR.sql
select "Running datasql/SPONSOR_TERM.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/SPONSOR_TERM.sql
select "Running datasql/SPONSOR_TERM_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/SPONSOR_TERM_TYPE.sql
select "Running datasql/SPONSOR_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/SPONSOR_TYPE.sql
select "Running datasql/SP_REV_APPROVAL_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/SP_REV_APPROVAL_TYPE.sql
select "Running datasql/STATE_CODE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/STATE_CODE.sql
select "Running datasql/SUBMISSION_STATUS.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/SUBMISSION_STATUS.sql
select "Running datasql/SUBMISSION_TYPE_QUALIFIER.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/SUBMISSION_TYPE_QUALIFIER.sql
select "Running datasql/SUBMISSION_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/SUBMISSION_TYPE.sql
select "Running datasql/TBN.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/TBN.sql
select "Running datasql/TRAINING.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/TRAINING.sql
--select "Running datasql/UNIT_ADMINISTRATOR.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/UNIT_ADMINISTRATOR.sql
select "Running datasql/UNIT_ADMINISTRATOR_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/UNIT_ADMINISTRATOR_TYPE.sql
--select "Running datasql/UNIT.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/UNIT.sql
select "Running datasql/USER_TABLES_TEMP.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/USER_TABLES_TEMP.sql
select "Running datasql/VALID_AWARD_BASIS_PAYMENT.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/VALID_AWARD_BASIS_PAYMENT.sql
select "Running datasql/VALID_BASIS_METHOD_PMT.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/VALID_BASIS_METHOD_PMT.sql
select "Running datasql/VALID_CALC_TYPES.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/VALID_CALC_TYPES.sql
--select "Running datasql/VALID_CE_JOB_CODES.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/VALID_CE_JOB_CODES.sql
--select "Running datasql/VALID_CE_RATE_TYPES.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/VALID_CE_RATE_TYPES.sql
select "Running datasql/VALID_CLASS_REPORT_FREQ.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/VALID_CLASS_REPORT_FREQ.sql
select "Running datasql/VALID_FREQUENCY_BASE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/VALID_FREQUENCY_BASE.sql
--select "Running datasql/VALID_RATES.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/VALID_RATES.sql
--select "Running datasql/VALID_SP_REV_APPROVAL.sql..."; 
--\. RICE/BUNDLEDSERVER_MYSQL/datasql/VALID_SP_REV_APPROVAL.sql
select "Running datasql/VULNERABLE_SUBJECT_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/VULNERABLE_SUBJECT_TYPE.sql
select "Running datasql/YNQ_EXPLANATION.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/YNQ_EXPLANATION.sql
select "Running datasql/YNQ_EXPLANATION_TYPE.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/YNQ_EXPLANATION_TYPE.sql
select "Running datasql/YNQ.sql..."; 
\. RICE/BUNDLEDSERVER_MYSQL/datasql/YNQ.sql
select 'Applying constraints...';
\. RICE/BUNDLEDSERVER_MYSQL/sql/schema-constraints.sql
delimiter /
DROP FUNCTION IF EXISTS to_date ;
/
CREATE FUNCTION to_date (inDate CHAR(50), inFmt CHAR(50))
RETURNS CHAR(50) DETERMINISTIC
RETURN inDate ;
/
delimiter ;
quit
