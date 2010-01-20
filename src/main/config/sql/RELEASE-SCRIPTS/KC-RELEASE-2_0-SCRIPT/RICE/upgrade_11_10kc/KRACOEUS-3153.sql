CREATE INDEX krim_role_mbr_attr_data_ti1 ON krim_role_mbr_attr_data_t (role_mbr_id)
/
CREATE INDEX krim_role_mbr_ti1 ON krim_role_mbr_t (mbr_id)
/
CREATE INDEX krim_entity_addr_ti1 ON krim_entity_addr_t (entity_id)
/
CREATE INDEX krim_entity_email_ti1 ON krim_entity_email_t (entity_id)
/
CREATE INDEX krew_rte_node_cfg_parm_ti1 ON krew_rte_node_cfg_parm_t (rte_node_id)
/
CREATE INDEX krim_entity_phone_ti1 ON krim_entity_phone_t (entity_id)
/
CREATE INDEX krim_grp_mbr_ti1 ON krim_grp_mbr_t (mbr_id)
/

CREATE INDEX krim_entity_afltn_ti1 ON krim_entity_afltn_t (entity_id)
/
CREATE INDEX krim_entity_emp_info_ti1 ON krim_entity_emp_info_t (entity_id)
/
CREATE INDEX krim_entity_emp_info_ti2 ON krim_entity_emp_info_t (entity_afltn_id)
/
CREATE INDEX krim_entity_ent_typ_ti1 ON krim_entity_ent_typ_t (entity_id)
/
CREATE INDEX krim_entity_ext_id_ti1 ON krim_entity_ext_id_t (entity_id)
/
CREATE INDEX krim_entity_nm_ti1 ON krim_entity_nm_t (entity_id)
/
CREATE INDEX krim_perm_attr_data_ti1 ON krim_perm_attr_data_t (perm_id)
/
CREATE INDEX krim_role_perm_ti1 ON krim_role_perm_t (perm_id)
/
CREATE INDEX krim_role_rsp_ti1 ON krim_role_rsp_t (rsp_id)
/
CREATE INDEX krim_typ_attribute_ti1 ON krim_typ_attr_t (kim_typ_id) 
/
commit;
/
--KULRICE-3635
ALTER TABLE KRIM_PND_ROLE_MBR_MT
DROP COLUMN MBR_NM
/ 

--KULRICE-3636
ALTER INDEX KRNS_MAINT_DOC_ATTACHMENT_TC0 RENAME TO KRNS_MAINT_DOC_ATT_TC0
/
ALTER INDEX KRNS_MAINTENANCE_DOCUMENT_TC0 RENAME TO KRNS_MAINT_DOC_TC0
/
ALTER INDEX krns_lookup_results_mtc0 RENAME TO KRNS_LOOKUP_RSLT_TC0
/
ALTER INDEX krns_lookup_selections_mtc0 RENAME TO KRNS_LOOKUP_SEL_TC0
/
ALTER INDEX kren_messages_uk1 RENAME TO KREN_MSG_TC0
/
ALTER INDEX kren_msg_delivs_uk1 RENAME TO KREN_MSG_DELIV_TC0
/
ALTER INDEX kren_recip_prefs_uk1 RENAME TO KREN_RECIP_PREFS_TC0
/
ALTER INDEX KR_KIM_ADDR_TYPE_TC0 RENAME TO KRIM_ADDR_TYP_TC0
/
ALTER INDEX KR_KIM_ADDR_TYPE_TC1 RENAME TO KRIM_ADDR_TYP_TC1
/
ALTER INDEX KR_KIM_AFLTN_TYPE_TC0 RENAME TO KRIM_AFLTN_TYP_TC0
/
ALTER INDEX KR_KIM_AFLTN_TYPE_TC1 RENAME TO KRIM_AFLTN_TYP_TC1
/
ALTER INDEX KR_KIM_ATTRIBUTE_TC0 RENAME TO KRIM_ATTR_DEFN_TC0
/
ALTER INDEX KR_KIM_CTZNSHP_STAT_TC0 RENAME TO KRIM_CTZNSHP_STAT_TC0
/
ALTER INDEX KR_KIM_CTZNSHP_STAT_TC1 RENAME TO KRIM_CTZNSHP_STAT_TC1
/
ALTER INDEX KR_KIM_DELE_MBR_ATTR_DATA_TC0 RENAME TO KRIM_DLGN_MBR_ATTR_DATA_TC0
/
ALTER INDEX KR_KIM_DELE_TC0 RENAME TO KRIM_DLGN_TC0
/
ALTER INDEX KR_KIM_EMAIL_TYPE_TC0 RENAME TO KRIM_EMAIL_TYP_TC0
/
ALTER INDEX KR_KIM_EMAIL_TYPE_TC1 RENAME TO KRIM_EMAIL_TYP_TC1
/
ALTER INDEX KR_KIM_EMP_STAT_TC0 RENAME TO KRIM_EMP_STAT_TC0
/
ALTER INDEX KR_KIM_EMP_STAT_TC1 RENAME TO KRIM_EMP_STAT_TC1
/
ALTER INDEX KR_KIM_EMP_TYPE_TC0 RENAME TO KRIM_EMP_TYP_TC0
/
ALTER INDEX KR_KIM_EMP_TYPE_TC1 RENAME TO KRIM_EMP_TYP_TC1
/
ALTER INDEX KR_KIM_ENT_NAME_TYPE_TC0 RENAME TO KRIM_ENT_NM_TYP_TC0
/
ALTER INDEX KR_KIM_ENT_NAME_TYPE_TC1 RENAME TO KRIM_ENT_NM_TYP_TC1
/
ALTER INDEX KR_KIM_ENT_TYPE_TC0 RENAME TO KRIM_ENT_TYP_TC0
/
ALTER INDEX KR_KIM_ENT_TYPE_TC1 RENAME TO KRIM_ENT_TYP_TC1
/
ALTER INDEX KR_KIM_ENTITY_ADDR_TC0 RENAME TO KRIM_ENTITY_ADDR_TC0
/
ALTER INDEX KR_KIM_ENTITY_AFLTN_TC0 RENAME TO KRIM_ENTITY_AFLTN_TC0
/
ALTER INDEX KR_KIM_ENTITY_BIO_TC0 RENAME TO KRIM_ENTITY_BIO_TC0
/
ALTER INDEX KR_KIM_ENTITY_CTZNSHP_TC0 RENAME TO KRIM_ENTITY_CTZNSHP_TC0
/
ALTER INDEX KR_KIM_ENTITY_EMAIL_TC0 RENAME TO KRIM_ENTITY_EMAIL_TC0
/
ALTER INDEX KR_KIM_ENTITY_EMP_INFO_TC0 RENAME TO KRIM_ENTITY_EMP_INFO_TC0
/
ALTER INDEX KR_KIM_ENTITY_EXT_ID_TC0 RENAME TO KRIM_ENTITY_EXT_ID_TC0
/
ALTER INDEX KR_KIM_ENTITY_NAME_TC0 RENAME TO KRIM_ENTITY_NM_TC0
/
ALTER INDEX KR_KIM_ENTITY_PHONE_TC0 RENAME TO KRIM_ENTITY_PHONE_TC0
/
ALTER INDEX KR_KIM_ENTITY_PRIV_PREF_TC0 RENAME TO KRIM_ENTITY_PRIV_PREF_TC0
/
ALTER INDEX KR_KIM_ENTITY_TC0 RENAME TO KRIM_ENTITY_TC0
/
ALTER INDEX KR_KIM_EXT_ID_TYPE_TC0 RENAME TO KRIM_EXT_ID_TYP_TC0
/
ALTER INDEX KR_KIM_EXT_ID_TYPE_TC1 RENAME TO KRIM_EXT_ID_TYP_TC1
/
ALTER INDEX KR_KIM_GROUP_ATTR_DATA_TC0 RENAME TO KRIM_GRP_ATTR_DATA_TC0
/
ALTER INDEX KR_KIM_GROUP_TC0 RENAME TO KRIM_GRP_TC0
/
ALTER INDEX KR_KIM_GROUP_TC1 RENAME TO KRIM_GRP_TC1
/
ALTER INDEX KR_KIM_PERM_ATTR_DATA_TC0 RENAME TO KRIM_PERM_ATTR_DATA_TC0
/
ALTER INDEX KR_KIM_PERM_TC0 RENAME TO KRIM_PERM_TC0
/
ALTER INDEX KR_KIM_PERM_TMPL_TC0 RENAME TO KRIM_PERM_TMPL_TC0
/
ALTER INDEX KR_KIM_PHONE_TYPE_TC0 RENAME TO KRIM_PHONE_TYP_TC0
/
ALTER INDEX KR_KIM_PHONE_TYPE_TC1 RENAME TO KRIM_PHONE_TYP_TC1
/
ALTER INDEX KR_KIM_PRINCIPAL_TC0 RENAME TO KRIM_PRNCPL_TC0
/
ALTER INDEX KR_KIM_PRINCIPAL_TC1 RENAME TO KRIM_PRNCPL_TC1
/
ALTER INDEX KR_KIM_RESP_ATTR_DATA_TC0 RENAME TO KRIM_RSP_ATTR_DATA_TC0
/
ALTER INDEX KR_KIM_RESP_TC0 RENAME TO KRIM_RSP_TC0
/
ALTER INDEX KR_KIM_RESP_TMPL_TC0 RENAME TO KRIM_RSP_TMPL_TC0
/
ALTER INDEX KR_KIM_ROLE_MBR_ATTR_DATA_TC0 RENAME TO KRIM_ROLE_MBR_ATTR_DATA_TC0
/
ALTER INDEX KR_KIM_ROLE_PERM_TC0 RENAME TO KRIM_ROLE_PERM_TC0
/
ALTER INDEX KR_KIM_ROLE_RESP_ACTN_TC0 RENAME TO KRIM_ROLE_RSP_ACTN_TC0
/
ALTER INDEX KR_KIM_ROLE_RESP_TC0 RENAME TO KRIM_ROLE_RSP_TC0
/
ALTER INDEX KR_KIM_ROLE_TC0 RENAME TO KRIM_ROLE_TC0
/
ALTER INDEX KR_KIM_ROLE_TC1 RENAME TO KRIM_ROLE_TC1
/
ALTER INDEX KR_KIM_TYPE_ATTRIBUTE_TC0 RENAME TO KRIM_TYP_ATTR_TC0
/
ALTER INDEX KR_KIM_TYPE_TC0 RENAME TO KRIM_TYP_TC0
/
ALTER INDEX kren_ntfctn_msg_delivs_uk RENAME TO KREN_NTFCTN_MSG_DELIV_TC0
/
ALTER INDEX kren_notification_chnl_uk1 RENAME TO KREN_CHNL_TC0
/
ALTER INDEX kren_ntfctn_i1 RENAME TO KREN_CNTNT_TYP_TC0
/
ALTER INDEX kren_ntfctn_i2 RENAME TO KREN_PRIO_TC0
/
ALTER INDEX kren_ntfctn_i3 RENAME TO KREN_PRODCR_TC0
/
ALTER INDEX kren_notifn_recip_l_uk1 RENAME TO KREN_RECIP_LIST_TC0
/
ALTER INDEX kren_notification_recip_uk1 RENAME TO KREN_RECIP_TC0
/
ALTER INDEX kren_notification_revwr_uk1 RENAME TO KREN_RVWER_TC0
/
ALTER INDEX kren_notifn_senders_uk1 RENAME TO KREN_SNDR_TC0
/
ALTER INDEX kren_user_chnl_subscrpn_uk1 RENAME TO KREN_CHNL_SUBSCRP_TC0
/ 
commit;
/
--KULRICE-3627
UPDATE KREN_CNTNT_TYP_T SET XSD=REPLACE((SELECT XSD FROM KREN_CNTNT_TYP_T WHERE NM='Event'), 'type="dateTime"', 'type="c:NonEmptyShortStringType"') WHERE NM='Event'
/
commit;
/
ALTER TABLE KRNS_LOOKUP_RSLT_T MODIFY PRNCPL_ID VARCHAR2(40)
/
ALTER TABLE KRNS_LOOKUP_SEL_T MODIFY PRNCPL_ID VARCHAR2(40)
/
ALTER TABLE KRNS_NTE_T MODIFY AUTH_PRNCPL_ID VARCHAR2(40)
/
ALTER TABLE KRNS_PESSIMISTIC_LOCK_T MODIFY PRNCPL_ID VARCHAR2(40)
/
ALTER TABLE KREW_ACTN_ITM_T MODIFY PRNCPL_ID VARCHAR2(40)
/
ALTER TABLE KREW_ACTN_ITM_T MODIFY DLGN_PRNCPL_ID VARCHAR2(40)
/
ALTER TABLE KREW_ACTN_RQST_T MODIFY PRNCPL_ID VARCHAR2(40)
/
ALTER TABLE KREW_ACTN_TKN_T MODIFY PRNCPL_ID VARCHAR2(40)
/
ALTER TABLE KREW_ACTN_TKN_T MODIFY DLGTR_PRNCPL_ID VARCHAR2(40)
/
ALTER TABLE KREW_DOC_HDR_T MODIFY INITR_PRNCPL_ID VARCHAR2(40)
/
ALTER TABLE KREW_DOC_HDR_T MODIFY RTE_PRNCPL_ID VARCHAR2(40)
/
ALTER TABLE KREW_DOC_NTE_T MODIFY AUTH_PRNCPL_ID VARCHAR2(40)
/
ALTER TABLE KREW_EDL_DMP_T MODIFY DOC_HDR_INITR_PRNCPL_ID VARCHAR2(40)
/
ALTER TABLE KREW_OUT_BOX_ITM_T MODIFY PRNCPL_ID VARCHAR2(40)
/
ALTER TABLE KREW_OUT_BOX_ITM_T MODIFY DLGN_PRNCPL_ID VARCHAR2(40)
/
ALTER TABLE KREW_RMV_RPLC_DOC_T MODIFY PRNCPL_ID VARCHAR2(40)
/
ALTER TABLE KREW_RMV_RPLC_DOC_T MODIFY RPLC_PRNCPL_ID VARCHAR2(40)
/
ALTER TABLE KREW_USR_OPTN_T MODIFY PRNCPL_ID VARCHAR2(40)
/
commit;
/
UPDATE KRNS_PARM_T SET TXT='MM/dd/yy;MM/dd/yyyy;MM/dd/yyyy HH:mm:ss;MM/dd/yy;MM-dd-yy;MMMM dd;yyyy;MMddyy' WHERE NMSPC_CD='KR-NS' AND PARM_DTL_TYP_CD='All' AND PARM_NM='STRING_TO_DATE_FORMATS' AND APPL_NMSPC_CD='KUALI'
/
UPDATE KRNS_PARM_T SET TXT='MM/dd/yyyy hh:mm a;MM/dd/yyyy;MM/dd/yyyy HH:mm:ss;MM/dd/yy;MM-dd-yy;MMMM dd;yyyy;MMddyy' WHERE NMSPC_CD='KR-NS' AND PARM_DTL_TYP_CD='All' AND PARM_NM='STRING_TO_TIMESTAMP_FORMATS' AND APPL_NMSPC_CD='KUALI'
/
commit;
/
update krew_doc_typ_t set help_def_url = 'default.htm?turl=WordDocuments%2Fperson.htm' where doc_typ_nm = 'IdentityManagementPersonDocument' and actv_ind = 1 and cur_ind = 1
/
update krew_doc_typ_t set help_def_url = 'default.htm?turl=WordDocuments%2Fgroup.htm' where doc_typ_nm = 'IdentityManagementGroupDocument' and actv_ind = 1 and cur_ind = 1
/
update krew_doc_typ_t set help_def_url = 'default.htm?turl=WordDocuments%2Frole.htm' where doc_typ_nm = 'IdentityManagementRoleDocument' and actv_ind = 1 and cur_ind = 1
/
update krew_doc_typ_t set help_def_url = 'default.htm?turl=WordDocuments%2Fpermission.htm' where doc_typ_nm = 'IdentityManagementGenericPermissionMaintenanceDocument' and actv_ind = 1 and cur_ind = 1
/
update krew_doc_typ_t set help_def_url = 'default.htm?turl=WordDocuments%2Fresponsibility.htm' where doc_typ_nm = 'IdentityManagementReviewResponsibilityMaintenanceDocument' and actv_ind = 1 and cur_ind = 1
/
update krew_doc_typ_t set help_def_url = 'default.htm?turl=WordDocuments%2Fcampus.htm' where doc_typ_nm = 'CampusMaintenanceDocument' and actv_ind = 1 and cur_ind = 1
/
update krew_doc_typ_t set help_def_url = 'default.htm?turl=WordDocuments%2Fcountry.htm' where doc_typ_nm = 'CountryMaintenanceDocument' and actv_ind = 1 and cur_ind = 1
/
update krew_doc_typ_t set help_def_url = 'default.htm?turl=WordDocuments%2Fcounty.htm' where doc_typ_nm = 'CountyMaintenanceDocument' and actv_ind = 1 and cur_ind = 1
/
update krew_doc_typ_t set help_def_url = 'default.htm?turl=WordDocuments%2Fpostalcode.htm' where doc_typ_nm = 'PostalCodeMaintenanceDocument' and actv_ind = 1 and cur_ind = 1
/
update krew_doc_typ_t set help_def_url = 'default.htm?turl=WordDocuments%2Fstate.htm' where doc_typ_nm = 'StateMaintenanceDocument' and actv_ind = 1 and cur_ind = 1
/
update krew_doc_typ_t set help_def_url = 'default.htm?turl=WordDocuments%2Faddresstype.htm' where doc_typ_nm = 'PMAT' and actv_ind = 1 and cur_ind = 1
/
update krew_doc_typ_t set help_def_url = 'default.htm?turl=WordDocuments%2Fcampustype.htm' where doc_typ_nm = 'CampusTypeMaintenanceDocument' and actv_ind = 1 and cur_ind = 1
/
update krew_doc_typ_t set help_def_url = 'default.htm?turl=WordDocuments%2Fphonetype.htm' where doc_typ_nm = 'PMPT' and actv_ind = 1 and cur_ind = 1
/
update krew_doc_typ_t set help_def_url = 'default.htm?turl=WordDocuments%2Fdocumenttype.htm' where doc_typ_nm = 'DocumentTypeDocument' and actv_ind = 1 and cur_ind = 1
/
update krew_doc_typ_t set help_def_url = 'default.htm?turl=WordDocuments%2Fparameter.htm' where doc_typ_nm = 'ParameterMaintenanceDocument' and actv_ind = 1 and cur_ind = 1
/
update krew_doc_typ_t set help_def_url = 'default.htm?turl=WordDocuments%2Fparametercomponent.htm' where doc_typ_nm = 'ParameterDetailTypeMaintenanceDocument' and actv_ind = 1 and cur_ind = 1
/
update krew_doc_typ_t set help_def_url = 'default.htm?turl=WordDocuments%2Fparametertype.htm' where doc_typ_nm = 'ParameterTypeMaintenanceDocument' and actv_ind = 1 and cur_ind = 1
/
update krew_doc_typ_t set help_def_url = 'default.htm?turl=WordDocuments%2Fnamespace.htm' where doc_typ_nm = 'NamespaceMaintenanceDocument' and actv_ind = 1 and cur_ind = 1
/ 
commit;
/