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

-- ############################################################################
-- # This file will clean up and remove data from the database to prepare the #
-- # "bootstrap" dataset which essentially clears out any test or "fake" data #
-- # that an implementer would not need in their database.					  #
-- #																		  #
-- # This includes fake principals and entities in KIM as well as document    #
-- # types that exist for testing and/or demonstration purposes.			  #
-- ############################################################################
--
-- # IMPORTANT! ###############################################################
-- # The demo-server-dataset-cleanup.sql should be run against the database   # 
-- # prior to this script!													  #
-- ############################################################################

-- Disable constraints for the duration of this script
DECLARE 
   CURSOR constraint_cursor IS 
      SELECT table_name, constraint_name 
         FROM user_constraints 
         WHERE constraint_type = 'R'
           AND status = 'ENABLED';
BEGIN 
   FOR r IN constraint_cursor LOOP
      execute immediate 'ALTER TABLE '||r.table_name||' DISABLE CONSTRAINT '||r.constraint_name; 
   END LOOP; 
END;
/

-- ##############
-- # KEW Tables #
-- ##############

-- Document Types

delete from krew_doc_typ_t where doc_typ_nm='TravelAccountMaintenanceDocument'
/
delete from krew_doc_typ_t where doc_typ_nm='FiscalOfficerMaintenanceDocument'
/
delete from krew_doc_typ_t where doc_typ_nm='eDoc.Example1.ParentDoctype'
/
delete from krew_doc_typ_t where doc_typ_nm='SampleThinClientDocument'
/
delete from krew_doc_typ_t where doc_typ_nm='eDoc.Example1Doctype'
/
delete from krew_doc_typ_t where doc_typ_nm='TravelRequest'
/
delete from krew_doc_typ_t where doc_typ_nm like 'Recipe%'
/
delete from krew_doc_typ_attr_t where DOC_TYP_ID not in (select doc_typ_id from KREW_DOC_TYP_T)
/
delete from krew_doc_typ_plcy_reln_t where DOC_TYP_ID not in (select doc_typ_id from KREW_DOC_TYP_T)
/
delete from krew_doc_typ_proc_t where DOC_TYP_ID not in (select doc_typ_id from KREW_DOC_TYP_T)
/
delete from krew_rte_node_t where DOC_TYP_ID not in (select doc_typ_id from KREW_DOC_TYP_T)
/
delete from krew_rte_node_lnk_t where from_rte_node_id not in (select rte_node_id from krew_rte_node_t)
/
delete from krew_rte_node_cfg_parm_t where rte_node_id not in (select rte_node_id from krew_rte_node_t)
/
delete from krew_rte_brch_proto_t where RTE_BRCH_PROTO_ID not in (select rte_brch_proto_id from krew_rte_node_t)
/

-- Rule Attributes

delete from krew_rule_attr_t where nm='DestinationAttribute'
/
delete from krew_rule_attr_t where nm='EmployeeAttribute'
/
delete from krew_rule_attr_t where nm='AccountAttribute'
/
delete from krew_rule_attr_t where nm='TravelAccountDocumentAccountNumberAttribute'
/
delete from krew_rule_attr_t where nm='EDL.Campus.Example'
/
delete from krew_rule_attr_t where nm='FiscalOfficer'
/
delete from krew_rule_attr_t where nm='LoadTestActionListAttribute'
/
delete from krew_rule_attr_t where nm='XMLSearchableAttribute_CaseInsensitive'
/
delete from krew_rule_attr_t where nm='XMLSearchableAttributeStdLong'
/
delete from krew_rule_attr_t where nm='XMLSearchableAttributeStdFloat'
/
delete from krew_rule_attr_t where nm='XMLSearchableAttributeStdCurrency'
/
delete from krew_rule_attr_t where nm='XMLSearchableAttributeStdDateTime'
/

-- Rule Templates

delete from krew_rule_tmpl_t where nm='TravelRequest-DestinationRouting'
/
delete from krew_rule_tmpl_t where nm='TravelRequest-TravelerRouting'
/
delete from krew_rule_tmpl_t where nm='TravelRequest-SupervisorRouting'
/
delete from krew_rule_tmpl_t where nm='TravelRequest-AccountRouting'
/
delete from krew_rule_tmpl_t where nm='eDoc.Example1.Node1'
/
delete from krew_rule_tmpl_t where nm='Ack1Template'
/
delete from krew_rule_tmpl_t where nm='Ack2Template'
/
delete from krew_rule_tmpl_t where nm='WorkflowDocument2Template'
/
delete from krew_rule_tmpl_t where nm='WorkflowDocument3Template'
/

-- Rules

delete from krew_rule_t where rule_tmpl_id is not null and rule_tmpl_id not in (select rule_tmpl_id from krew_rule_tmpl_t)
/
delete from krew_rule_t where doc_typ_nm is not null and doc_typ_nm not in (select doc_typ_nm from krew_doc_typ_t)
/

-- EDL

delete from krew_edl_assctn_t
/
delete from krew_edl_def_t
/
delete from krew_style_t where nm='eDoc.Example1.Style'
/

-- User Options

delete from krew_usr_optn_t
/

-- ##############
-- # KSB Tables #
-- ##############

-- no KSB data needs to be dealt with for the bootstrap 
-- dataset (it's all handled by the client and demo cleanup files)

-- ##############
-- # KNS Tables #
-- ##############

delete from krns_campus_t
/

-- ##############
-- # KEN Tables #
-- ##############

-- leave only the 'KEW' Channel which is used for action list notification

delete from KREN_CHNL_T where NM = 'Kuali Rice Channel'
/
delete from KREN_CHNL_T where NM = 'Library Events Channel'
/
delete from KREN_CHNL_T where NM = 'Overdue Library Books'
/
delete from KREN_CHNL_T where NM = 'Concerts Coming to Campus'
/
delete from KREN_CHNL_T where NM = 'University Alerts'
/

-- delete all channel subscriptions

delete from kren_chnl_subscrp_t
/

-- delete all producers
delete from kren_prodcr_t
/
delete from kren_chnl_prodcr_t
/

-- delete recipient data

delete from kren_recip_deliv_t
/
delete from kren_recip_list_t
/
delete from kren_recip_prefs_t
/

-- delete reviewer configuration

delete from kren_rvwer_t
/

-- ##############
-- # KIM Tables #
-- ##############

-- currently, the only built-in external identifier type is the TAX id

delete from krim_ext_id_typ_t where ext_id_typ_cd != 'TAX'
/

-- delete all groups except WorkflowAdmin and NotificationAdmin (they're referenced from Document Types)

delete from krim_grp_t where grp_nm not in ('WorkflowAdmin', 'NotificationAdmin')
/
delete from krim_grp_attr_data_t where grp_id not in (select grp_id from krim_grp_t)
/
delete from krim_grp_mbr_t where grp_id not in (select grp_id from krim_grp_t)
/

-- delete all entity and principal data except for principalID/entityID = 1 which is the 'kr' system user
-- and principalID=admin/entityID = 1100 which is the 'admin' user

delete from krim_entity_addr_t where entity_id not in ('1', '1100')
/
delete from krim_entity_afltn_t where entity_id not in ('1', '1100')
/
delete from krim_entity_bio_t where entity_id not in ('1', '1100')
/
delete from krim_entity_ctznshp_t where entity_id not in ('1', '1100')
/
delete from krim_entity_email_t where entity_id not in ('1', '1100')
/
delete from krim_entity_emp_info_t where entity_id not in ('1', '1100')
/
delete from krim_entity_ent_typ_t where entity_id not in ('1', '1100')
/
delete from krim_entity_ext_id_t where entity_id not in ('1', '1100')
/
delete from krim_entity_nm_t where entity_id not in ('1', '1100')
/
delete from krim_entity_phone_t where entity_id not in ('1', '1100')
/
delete from krim_entity_priv_pref_t where entity_id not in ('1', '1100')
/
delete from KRIM_ENTITY_ETHNIC_T where entity_id not in ('1', '1100')
/
delete from KRIM_ENTITY_RESIDENCY_T where entity_id not in ('1', '1100')
/
delete from KRIM_ENTITY_VISA_T where entity_id not in ('1', '1100')
/
delete from krim_entity_t where entity_id not in ('1', '1100')
/

delete from krim_prncpl_t where prncpl_id not in ('1', 'admin')
/

-- #####################
-- # Sample App Tables #
-- #####################

-- drop all sample application tables

drop table trav_doc_2_accounts
/
drop table trv_acct
/
drop table trv_acct_ext
/
drop table trv_acct_fo
/
drop table trv_acct_type
/
drop table trv_doc_2
/
drop table trv_doc_acct
/
drop sequence trv_fo_id_s
/

drop table kr_kim_test_bo
/
drop table TST_SEARCH_ATTR_INDX_TST_DOC_T
/
delete from ACCT_DD_ATTR_DOC
/

-- Re-enable constraints
DECLARE 
   CURSOR constraint_cursor IS 
      SELECT table_name, constraint_name 
         FROM user_constraints 
         WHERE constraint_type = 'R'
           AND status <> 'ENABLED';
BEGIN 
   FOR r IN constraint_cursor LOOP
      execute immediate 'ALTER TABLE '||r.table_name||' ENABLE CONSTRAINT '||r.constraint_name; 
   END LOOP; 
END;
/
