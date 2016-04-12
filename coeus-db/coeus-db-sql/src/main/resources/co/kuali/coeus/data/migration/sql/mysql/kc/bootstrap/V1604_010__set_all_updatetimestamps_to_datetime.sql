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
-- select distinct concat('alter table ', tables.table_name, ' modify column ', column_name, ' datetime;') from information_schema.columns join information_schema.tables on columns.table_name = tables.table_name where tables.table_schema = 'kcdev' and column_name = 'UPDATE_TIMESTAMP' and data_type = 'date' and table_type = 'BASE TABLE';

alter table attachments_type modify column UPDATE_TIMESTAMP datetime;
alter table award_budget_limit modify column UPDATE_TIMESTAMP datetime;
alter table award_budget_limit_orphan modify column UPDATE_TIMESTAMP datetime;
alter table award_report_notification_sent modify column UPDATE_TIMESTAMP datetime;
alter table award_subcontract_goals_exp modify column UPDATE_TIMESTAMP datetime;
alter table award_sync_change modify column UPDATE_TIMESTAMP datetime;
alter table award_sync_log modify column UPDATE_TIMESTAMP datetime;
alter table award_sync_status modify column UPDATE_TIMESTAMP datetime;
alter table awd_bgt_per_sum_calc_amt modify column UPDATE_TIMESTAMP datetime;
alter table bud_formulated_cost_detail modify column UPDATE_TIMESTAMP datetime;
alter table budget_changed_data modify column UPDATE_TIMESTAMP datetime;
alter table budget_columns_to_alter modify column UPDATE_TIMESTAMP datetime;
alter table budget_person_salary_details modify column UPDATE_TIMESTAMP datetime;
alter table budget_sub_award_period_detail modify column UPDATE_TIMESTAMP datetime;
alter table citizenship_type_t modify column UPDATE_TIMESTAMP datetime;
alter table closeout_type modify column UPDATE_TIMESTAMP datetime;
alter table coi_attachment_type modify column UPDATE_TIMESTAMP datetime;
alter table coi_committee_role_type modify column UPDATE_TIMESTAMP datetime;
alter table coi_disc_details modify column UPDATE_TIMESTAMP datetime;
alter table coi_discl_projects modify column UPDATE_TIMESTAMP datetime;
alter table coi_disclosure modify column UPDATE_TIMESTAMP datetime;
alter table coi_disclosure_attachment modify column UPDATE_TIMESTAMP datetime;
alter table coi_disclosure_document modify column UPDATE_TIMESTAMP datetime;
alter table coi_disclosure_event_type modify column UPDATE_TIMESTAMP datetime;
alter table coi_disclosure_history modify column UPDATE_TIMESTAMP datetime;
alter table coi_disclosure_notepad modify column UPDATE_TIMESTAMP datetime;
alter table coi_disclosure_person modify column UPDATE_TIMESTAMP datetime;
alter table coi_disclosure_person_units modify column UPDATE_TIMESTAMP datetime;
alter table coi_disclosure_status modify column UPDATE_TIMESTAMP datetime;
alter table coi_disposition_status modify column UPDATE_TIMESTAMP datetime;
alter table coi_entity_status_code modify column UPDATE_TIMESTAMP datetime;
alter table coi_note_type modify column UPDATE_TIMESTAMP datetime;
alter table coi_notification modify column UPDATE_TIMESTAMP datetime;
alter table coi_recomended_action_type modify column UPDATE_TIMESTAMP datetime;
alter table coi_review_status modify column UPDATE_TIMESTAMP datetime;
alter table coi_review_type modify column UPDATE_TIMESTAMP datetime;
alter table coi_reviewer modify column UPDATE_TIMESTAMP datetime;
alter table coi_status modify column UPDATE_TIMESTAMP datetime;
alter table coi_user_roles modify column UPDATE_TIMESTAMP datetime;
alter table comm_schedule_attachments modify column UPDATE_TIMESTAMP datetime;
alter table cust_report_details modify column UPDATE_TIMESTAMP datetime;
alter table cust_report_type modify column UPDATE_TIMESTAMP datetime;
alter table cust_rpt_default_parms modify column UPDATE_TIMESTAMP datetime;
alter table cust_rpt_type_document modify column UPDATE_TIMESTAMP datetime;
alter table dash_board_menu_items modify column UPDATE_TIMESTAMP datetime;
alter table document_access modify column UPDATE_TIMESTAMP datetime;
alter table fin_entities_data_groups modify column UPDATE_TIMESTAMP datetime;
alter table fin_entities_data_matrix modify column UPDATE_TIMESTAMP datetime;
alter table fin_entity_reporter_units modify column UPDATE_TIMESTAMP datetime;
alter table fin_int_entity_rel_type modify column UPDATE_TIMESTAMP datetime;
alter table fin_int_entity_status modify column UPDATE_TIMESTAMP datetime;
alter table fin_int_entity_ynq modify column UPDATE_TIMESTAMP datetime;
alter table financial_entity_attachment modify column UPDATE_TIMESTAMP datetime;
alter table financial_entity_contact_info modify column UPDATE_TIMESTAMP datetime;
alter table financial_entity_reporter modify column UPDATE_TIMESTAMP datetime;
alter table formulated_type modify column UPDATE_TIMESTAMP datetime;
alter table inv_coi_disc_details modify column UPDATE_TIMESTAMP datetime;
alter table kc_krms_term_fun_param modify column UPDATE_TIMESTAMP datetime;
alter table kc_krms_term_function modify column UPDATE_TIMESTAMP datetime;
alter table negotiation modify column UPDATE_TIMESTAMP datetime;
alter table negotiation_activity modify column UPDATE_TIMESTAMP datetime;
alter table negotiation_activity_type modify column UPDATE_TIMESTAMP datetime;
alter table negotiation_agreement_type modify column UPDATE_TIMESTAMP datetime;
alter table negotiation_association_type modify column UPDATE_TIMESTAMP datetime;
alter table negotiation_attachment modify column UPDATE_TIMESTAMP datetime;
alter table negotiation_custom_data modify column UPDATE_TIMESTAMP datetime;
alter table negotiation_document modify column UPDATE_TIMESTAMP datetime;
alter table negotiation_location modify column UPDATE_TIMESTAMP datetime;
alter table negotiation_notification modify column UPDATE_TIMESTAMP datetime;
alter table negotiation_status modify column UPDATE_TIMESTAMP datetime;
alter table negotiation_unassoc_detail modify column UPDATE_TIMESTAMP datetime;
alter table notification modify column UPDATE_TIMESTAMP datetime;
alter table notification_mdl_role_qlfr modify column UPDATE_TIMESTAMP datetime;
alter table notification_module_role modify column UPDATE_TIMESTAMP datetime;
alter table notification_type modify column UPDATE_TIMESTAMP datetime;
alter table notification_type_recipient modify column UPDATE_TIMESTAMP datetime;
alter table organization_audit_acc_type modify column UPDATE_TIMESTAMP datetime;
alter table organization_correspondent modify column UPDATE_TIMESTAMP datetime;
alter table per_fin_int_discl_det modify column UPDATE_TIMESTAMP datetime;
alter table person_biosketch modify column UPDATE_TIMESTAMP datetime;
alter table person_custom_data modify column UPDATE_TIMESTAMP datetime;
alter table person_editable_fields modify column UPDATE_TIMESTAMP datetime;
alter table person_fin_int_disclosure modify column UPDATE_TIMESTAMP datetime;
alter table person_mass_change modify column UPDATE_TIMESTAMP datetime;
alter table person_mass_change_document modify column UPDATE_TIMESTAMP datetime;
alter table person_signature modify column UPDATE_TIMESTAMP datetime;
alter table person_signature_module modify column UPDATE_TIMESTAMP datetime;
alter table pmc_award modify column UPDATE_TIMESTAMP datetime;
alter table pmc_eps_proposal modify column UPDATE_TIMESTAMP datetime;
alter table pmc_iacuc_protocol modify column UPDATE_TIMESTAMP datetime;
alter table pmc_negotiation modify column UPDATE_TIMESTAMP datetime;
alter table pmc_proposal modify column UPDATE_TIMESTAMP datetime;
alter table pmc_proposal_log modify column UPDATE_TIMESTAMP datetime;
alter table pmc_protocol modify column UPDATE_TIMESTAMP datetime;
alter table pmc_subaward modify column UPDATE_TIMESTAMP datetime;
alter table pmc_unit_administrator modify column UPDATE_TIMESTAMP datetime;
alter table protocol_notification modify column UPDATE_TIMESTAMP datetime;
alter table question_multi_choice modify column UPDATE_TIMESTAMP datetime;
alter table questionnaire_condition_type modify column UPDATE_TIMESTAMP datetime;
alter table rate_class_base_exclusion modify column UPDATE_TIMESTAMP datetime;
alter table rate_class_base_inclusion modify column UPDATE_TIMESTAMP datetime;
alter table report_status modify column UPDATE_TIMESTAMP datetime;
alter table reviewer_attachments modify column UPDATE_TIMESTAMP datetime;
alter table s2s_error modify column UPDATE_TIMESTAMP datetime;
alter table s2s_form_to_questionnaire modify column UPDATE_TIMESTAMP datetime;
alter table s2s_providers modify column UPDATE_TIMESTAMP datetime;
alter table s2s_user_attached_form modify column UPDATE_TIMESTAMP datetime;
alter table s2s_user_attached_form_att modify column UPDATE_TIMESTAMP datetime;
alter table s2s_user_attached_form_file modify column UPDATE_TIMESTAMP datetime;
alter table s2s_user_attd_form_att_file modify column UPDATE_TIMESTAMP datetime;
alter table special_review_usage modify column UPDATE_TIMESTAMP datetime;
alter table sub_exp_cat_by_fy modify column UPDATE_TIMESTAMP datetime;
alter table subaward modify column UPDATE_TIMESTAMP datetime;
alter table subaward_amount_info modify column UPDATE_TIMESTAMP datetime;
alter table subaward_amt_released modify column UPDATE_TIMESTAMP datetime;
alter table subaward_approval_type modify column UPDATE_TIMESTAMP datetime;
alter table subaward_attachment_type modify column UPDATE_TIMESTAMP datetime;
alter table subaward_attachments modify column UPDATE_TIMESTAMP datetime;
alter table subaward_closeout modify column UPDATE_TIMESTAMP datetime;
alter table subaward_contact modify column UPDATE_TIMESTAMP datetime;
alter table subaward_custom_data modify column UPDATE_TIMESTAMP datetime;
alter table subaward_document modify column UPDATE_TIMESTAMP datetime;
alter table subaward_forms modify column UPDATE_TIMESTAMP datetime;
alter table subaward_funding_source modify column UPDATE_TIMESTAMP datetime;
alter table subaward_report_type modify column UPDATE_TIMESTAMP datetime;
alter table subaward_reports modify column UPDATE_TIMESTAMP datetime;
alter table subaward_status modify column UPDATE_TIMESTAMP datetime;
alter table subaward_template_info modify column UPDATE_TIMESTAMP datetime;
alter table subaward_template_type modify column UPDATE_TIMESTAMP datetime;
alter table subcontract_copyright_type modify column UPDATE_TIMESTAMP datetime;
alter table subcontract_cost_type modify column UPDATE_TIMESTAMP datetime;
alter table subcontract_exp_cat modify column UPDATE_TIMESTAMP datetime;
alter table subcontract_exp_cat_det modify column UPDATE_TIMESTAMP datetime;
alter table subcontracting_bud modify column UPDATE_TIMESTAMP datetime;
alter table training_stipend_rates modify column UPDATE_TIMESTAMP datetime;
alter table unit_correspondent modify column UPDATE_TIMESTAMP datetime;
alter table unit_formulated_cost modify column UPDATE_TIMESTAMP datetime;
alter table valid_iacuc_proto_act_coresp modify column UPDATE_TIMESTAMP datetime;
alter table valid_iacuc_proto_actn_actn modify column UPDATE_TIMESTAMP datetime;
alter table valid_iacuc_proto_sub_rev_type modify column UPDATE_TIMESTAMP datetime;
alter table valid_proto_action_action modify column UPDATE_TIMESTAMP datetime;
alter table watermark modify column UPDATE_TIMESTAMP datetime;
