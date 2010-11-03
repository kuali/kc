--
-- Copyright 2009 The Kuali Foundation
-- 
-- Licensed under the Educational Community License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- 
-- http://www.opensource.org/licenses/ecl2.php
-- 
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
-- 

-- #############################################################################
-- # demo-server-dataset-cleanup.sql                                           #
-- #                                                                           #
-- # This file will clean up and remove data from the database to prepare the  # 
-- # "demo" server dataset which essentially clears out document and other     #
-- # volatile data from the database but leaves our demo set of data.          #
-- #############################################################################

-- ##############
-- # KEW Tables #
-- ##############

-- Document Tables - we want to clean out all of this

delete from krew_actn_itm_t
/
delete from krew_out_box_itm_t
/
delete from krew_actn_tkn_t
/
delete from krew_actn_rqst_t
/
delete from krew_doc_nte_t
/
delete from krew_att_t
/
delete from krew_doc_hdr_cntnt_t
/
delete from krew_doc_hdr_ext_dt_t
/
delete from krew_doc_hdr_ext_flt_t
/
delete from krew_doc_hdr_ext_long_t
/
delete from krew_doc_hdr_ext_t
/
delete from krew_init_rte_node_instn_t
/
delete from krew_rte_node_instn_lnk_t
/
delete from krew_rte_node_instn_st_t
/
delete from krew_rte_node_instn_t
/
delete from krew_rte_brch_st_t
/
delete from krew_rte_brch_t
/
delete from krew_doc_hdr_t
/
delete from krew_doc_typ_app_doc_stat_t
/
delete from krew_app_doc_stat_tran_t
/
delete from krew_doc_lnk_t
/

-- Document Type Tables - delete all except the current version

delete from krew_doc_typ_attr_t where doc_typ_id in (select doc_typ_id from krew_doc_typ_t where CUR_IND=0)
/
delete from krew_doc_typ_plcy_reln_t where doc_typ_id in (select doc_typ_id from krew_doc_typ_t where CUR_IND=0)
/
delete from krew_doc_typ_proc_t where doc_typ_id in (select doc_typ_id from krew_doc_typ_t where CUR_IND=0)
/
delete from krew_rte_brch_proto_t where rte_brch_proto_id in 
(select rn.BRCH_PROTO_ID from krew_rte_node_t rn, krew_doc_typ_t dt where rn.doc_typ_id=dt.doc_typ_id and dt.CUR_IND=0 and rn.BRCH_PROTO_ID is not null)
/
delete from krew_rte_node_cfg_parm_t where rte_node_id in 
(select rn.rte_node_id from krew_rte_node_t rn, krew_doc_typ_t dt where rn.doc_typ_id=dt.doc_typ_id and dt.CUR_IND=0)
/
delete from krew_rte_node_lnk_t where FROM_RTE_NODE_ID in 
(select rn.RTE_NODE_ID from krew_rte_node_t rn, krew_doc_typ_t dt where rn.doc_typ_id=dt.doc_typ_id and dt.CUR_IND=0)
/
delete from krew_rte_node_t where doc_typ_id in (select doc_typ_id from krew_doc_typ_t where CUR_IND=0)
/
delete from krew_doc_typ_t where cur_ind=0
/
update krew_doc_typ_t set PREV_DOC_TYP_VER_NBR=NULL, DOC_TYP_VER_NBR=0, VER_NBR=0
/

-- Rule Tables - delete all except the current version
-- note that the statements below depend on the fact that there are no foreign key constraints on these
-- tables, when we add them we will either need to rewrite this script or turn off constraints prior to
-- executing this

delete from krew_rule_t where CUR_IND=0
/
delete from krew_rule_t where TMPL_RULE_IND=0 and doc_typ_nm not in (select doc_typ_nm from krew_doc_typ_t)
/
update krew_rule_t set RULE_VER_NBR=0, PREV_RULE_VER_NBR=NULL, VER_NBR=0
/
delete from krew_rule_ext_t where rule_id not in (select rule_id from krew_rule_t)
/
delete from krew_rule_ext_val_t where RULE_EXT_ID not in (select rule_ext_id from krew_rule_ext_t)
/
delete from krew_rule_rsp_t where rule_id not in (select rule_id from krew_rule_t)
/
delete from krew_rule_tmpl_attr_t where ACTV_IND=0
/
delete from krew_dlgn_rsp_t where DLGN_RULE_BASE_VAL_ID not in (select rule_id from krew_rule_t)
/
delete from krew_rule_expr_t where RULE_EXPR_ID not in (select RULE_EXPR_ID from krew_rule_t where RULE_EXPR_ID is not null)
/

-- EDL Tables - delete associations that are inactive or for doc types that aren't current or no longer exist
-- clear out all "dump" data

delete from krew_edl_def_t where ACTV_IND=0
/
delete from krew_style_t where ACTV_IND=0
/
delete from krew_edl_assctn_t where ACTV_IND=0
/
delete from krew_edl_assctn_t where DOC_TYP_NM not in (select doc_typ_nm from krew_doc_typ_t)
/
delete from krew_edl_assctn_t where STYLE_NM not in (select NM from krew_style_t)
/
delete from krew_edl_assctn_t where EDL_DEF_NM not in (select NM from krew_edl_def_t)
/
delete from krew_edl_fld_dmp_t
/
delete from krew_edl_dmp_t
/


-- User Option Table - delete RELOAD_ACTION_LIST options

delete from krew_usr_optn_t where PRSN_OPTN_ID like 'RELOAD_ACTION_LIST%'
/

-- Remove/Replace Document Tables - clear all data

delete from krew_rmv_rplc_doc_t
/
delete from krew_rmv_rplc_grp_t
/
delete from krew_rmv_rplc_rule_t
/

-- ##############
-- # KSB Tables #
-- ##############

-- Note that for most of the KSB-related tables, the client-dataset-cleanup script will need to be run

-- Service Registry - table should be emptied, will be re-populated on startup 

delete from krsb_flt_svc_def_t
/
delete from krsb_svc_def_t
/


-- ##############
-- # KNS Tables #
-- ##############

delete from krns_pessimistic_lock_t
/

-- ##############
-- # KEN Tables #
-- ##############

-- delete message and notification data

delete from kren_msg_t
/
delete from kren_msg_deliv_t
/
delete from kren_ntfctn_msg_deliv_t
/
delete from kren_ntfctn_t
/
delete from kren_recip_t
/
delete from kren_sndr_t
/

-- currently, all KIM tables in the master datasource are empty, so nothing needs to be done here

-- ##############
-- # KIM Tables #
-- ##############

-- delete data from cache table and from "document" and related "pending" data tables

delete from krim_entity_cache_t
/
delete from krim_grp_document_t
/
delete from krim_person_document_t
/
delete from krim_role_document_t
/
delete from krim_pnd_dlgn_mbr_attr_data_t
/
delete from krim_pnd_dlgn_mbr_t
/
delete from krim_pnd_dlgn_t
/
delete from krim_pnd_addr_mt
/
delete from krim_pnd_afltn_mt
/
delete from krim_pnd_ctznshp_mt
/
delete from krim_pnd_email_mt
/
delete from krim_pnd_emp_info_mt
/
delete from krim_pnd_grp_prncpl_mt
/
delete from krim_pnd_grp_attr_data_t
/
delete from krim_pnd_grp_mbr_t
/
delete from krim_pnd_nm_mt
/
delete from krim_pnd_phone_mt
/
delete from krim_pnd_priv_pref_mt
/
delete from krim_pnd_role_mbr_attr_data_mt
/
delete from krim_pnd_role_mbr_mt
/
delete from krim_pnd_role_mt
/
delete from krim_pnd_role_rsp_actn_mt
/
delete from krim_pnd_role_perm_t
/
delete from krim_pnd_role_rsp_t
/
