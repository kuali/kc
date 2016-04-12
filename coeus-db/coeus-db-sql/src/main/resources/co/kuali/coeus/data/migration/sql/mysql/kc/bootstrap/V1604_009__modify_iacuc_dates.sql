--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
--
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
--
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
--
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--
-- generated using 
-- select distinct concat('alter table ', tables.table_name, ' modify column ', column_name, ' datetime;') from information_schema.columns join information_schema.tables on columns.table_name = tables.table_name where tables.table_schema = 'kcdev' and tables.table_name like 'iacuc%' and data_type = 'date' and table_type = 'BASE TABLE';

alter table iacuc_affiliation_type modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_alt_search_db modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_batch_corresp_detail modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_batch_correspondence modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_exception_category modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_exempt_number modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_location_name modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_location_type modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_org_correspondent modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_pain_category modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_person_training modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_principles modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_proc_cat_custom_data modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_proc_person_responsible modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_procedure_category modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_procedures modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_proto_amend_renew_mod modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_proto_amend_renewal modify column DATE_CREATED datetime;
alter table iacuc_proto_amend_renewal modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_proto_attach_type_group modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_proto_attachment_group modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_proto_attachment_status modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_proto_corresp_templ modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_proto_correspondence modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_proto_correspondence modify column CREATE_TIMESTAMP datetime;
alter table iacuc_proto_correspondence modify column FINAL_FLAG_TIMESTAMP datetime;
alter table iacuc_proto_olr_dt_rec_type modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_proto_review_type modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_proto_study_custom_data modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_proto_study_group_locs modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol modify column APPLICATION_DATE datetime;
alter table iacuc_protocol modify column APPROVAL_DATE datetime;
alter table iacuc_protocol modify column EXPIRATION_DATE datetime;
alter table iacuc_protocol modify column CREATE_TIMESTAMP datetime;
alter table iacuc_protocol modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol modify column LAST_APPROVAL_DATE datetime;
alter table iacuc_protocol_action_type modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_actions modify column ACTION_DATE datetime;
alter table iacuc_protocol_actions modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_actions modify column ACTUAL_ACTION_DATE datetime;
alter table iacuc_protocol_alt_search modify column SEARCH_DATE datetime;
alter table iacuc_protocol_alt_search modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_alt_search_dbs modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_attach_protocol modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_attach_protocol modify column CREATE_TIMESTAMP datetime;
alter table iacuc_protocol_attach_prsnnl modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_attachment_type modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_contingency modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_corresp_type modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_custom_data modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_document modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_exceptions modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_funding_source modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_location modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_modules modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_notepad modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_notepad modify column CREATE_TIMESTAMP datetime;
alter table iacuc_protocol_notification modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_notification modify column CREATE_TIMESTAMP datetime;
alter table iacuc_protocol_olr_document modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_olr_status modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_onln_rvws modify column DATE_REQUESTED datetime;
alter table iacuc_protocol_onln_rvws modify column DATE_DUE datetime;
alter table iacuc_protocol_onln_rvws modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_onln_rvws modify column DETERMINE_REVIEW_DATE_DUE datetime;
alter table iacuc_protocol_org_type modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_person_roles modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_persons modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_persons modify column DATE_OF_BIRTH datetime;
alter table iacuc_protocol_persons modify column VISA_RENEWAL_DATE datetime;
alter table iacuc_protocol_project_type modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_prsn_role_mppng modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_reference_type modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_references modify column APPLICATION_DATE datetime;
alter table iacuc_protocol_references modify column APPROVAL_DATE datetime;
alter table iacuc_protocol_references modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_references modify column EXPIRATION_DATE datetime;
alter table iacuc_protocol_research_areas modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_reviewer_type modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_reviewers modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_special_review modify column APPLICATION_DATE datetime;
alter table iacuc_protocol_special_review modify column APPROVAL_DATE datetime;
alter table iacuc_protocol_special_review modify column EXPIRATION_DATE datetime;
alter table iacuc_protocol_special_review modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_species modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_status modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_study_group_hdr modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_study_groups modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_submission modify column SUBMISSION_DATE datetime;
alter table iacuc_protocol_submission modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_submission_doc modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_type modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_protocol_units modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_research_areas modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_reviewer_attachments modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_reviewer_attachments modify column CREATE_TIMESTAMP datetime;
alter table iacuc_species modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_species_count_type modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_submission_status modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_submission_type modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_submission_type_qualif modify column UPDATE_TIMESTAMP datetime;
alter table iacuc_valid_proto_sub_typ_ql modify column UPDATE_TIMESTAMP datetime;
