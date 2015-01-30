--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
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

\. ./mysql/sequences/KC_SEQ_COI_DISCLOSURE.sql
\. ./mysql/sequences/KC_SEQ_COI_DISCLOSURE_PERSON.sql
\. ./mysql/sequences/KC_SEQ_COI_DISCLOSURE_PERSON_UNITS.sql
\. ./mysql/sequences/KC_SEQ_COI_DISCL_NUMBER_S.sql
\. ./mysql/sequences/KC_SEQ_COI_DISCL_PROJECTS_ID.sql
\. ./mysql/sequences/KC_SEQ_COI_DISC_DETAILS_ID.sql
\. ./mysql/sequences/KC_SEQ_ENTITY_NUMBER_S.sql
\. ./mysql/sequences/KC_SEQ_FINANCIAL_ENTITY_REPORTER.sql
\. ./mysql/sequences/KC_SEQ_FIN_ENTITY_CONTACT_INFO_ID.sql
\. ./mysql/sequences/KC_SEQ_FIN_ENTITY_REPORTER_UNITS.sql
\. ./mysql/sequences/KC_SEQ_FIN_INT_ENTITY_YNQ.sql
\. ./mysql/sequences/KC_SEQ_INV_COI_DISC_DETAILS.sql
\. ./mysql/sequences/KC_SEQ_NEGOTIATION_ACTIVITY_S.sql
\. ./mysql/sequences/KC_SEQ_NEGOTIATION_ACTIVITY_TYPE_S.sql
\. ./mysql/sequences/KC_SEQ_NEGOTIATION_AGREEMENT_TYPE_S.sql
\. ./mysql/sequences/KC_SEQ_NEGOTIATION_ASSOCIATION_TYPE_S.sql
\. ./mysql/sequences/KC_SEQ_NEGOTIATION_ATTACHMENT_S.sql
\. ./mysql/sequences/KC_SEQ_NEGOTIATION_CUSTOM_DATA_ID.sql
\. ./mysql/sequences/KC_SEQ_NEGOTIATION_LOCATION_S.sql
\. ./mysql/sequences/KC_SEQ_NEGOTIATION_S.sql
\. ./mysql/sequences/KC_SEQ_NEGOTIATION_STATUS_S.sql
\. ./mysql/sequences/KC_SEQ_NEGOTIATION_UNASSOC_DETAIL_S.sql
\. ./mysql/sequences/KC_SEQ_NOTIFICATION_MDL_ROLE_QLFR.sql
\. ./mysql/sequences/KC_SEQ_NOTIFICATION_MODULE_ROLE.sql
\. ./mysql/sequences/KC_SEQ_ORG_CORRESP.sql
\. ./mysql/sequences/KC_SEQ_PERSON_FIN_INT_DISCLOSURE.sql
\. ./mysql/sequences/KC_SEQ_PERSON_TRAINING.sql
\. ./mysql/sequences/KC_SEQ_PER_FIN_INT_DISCL_DET.sql
\. ./mysql/sequences/KC_SEQ_SUBAWARD.sql
\. ./mysql/sequences/KC_SEQ_SUBAWARD_AMOUNT_INFO.sql
\. ./mysql/sequences/KC_SEQ_SUBAWARD_AMT_RELEASED.sql
\. ./mysql/sequences/KC_SEQ_SUBAWARD_CLOSEOUT.sql
\. ./mysql/sequences/KC_SEQ_SUBAWARD_CONTACT.sql
\. ./mysql/sequences/KC_SEQ_SUBAWARD_CUSTOM_DATA.sql
\. ./mysql/sequences/KC_SEQ_SUBAWARD_FUNDING_SOURCE.sql
\. ./mysql/sequences/KC_SEQ_UNIT_CORRESP.sql
\. ./mysql/sequences/KC_SEQ_WATERMARK.sql
\. ./mysql/tables/KC_TBL_AWARD.sql
\. ./mysql/tables/KC_TBL_AWARD_PAYMENT_SCHEDULE.sql
\. ./mysql/tables/KC_TBL_AWARD_REPORT_TRACKING.sql
\. ./mysql/tables/KC_TBL_BUDGET_PERSONNEL_DETAILS.sql
\. ./mysql/tables/KC_TBL_CLOSEOUT_TYPE.sql
\. ./mysql/tables/KC_TBL_COI_COMMITTEE_ROLE_TYPE.sql
\. ./mysql/tables/KC_TBL_COI_DISCLOSURE.sql
\. ./mysql/tables/KC_TBL_COI_DISCLOSURE_DOCUMENT.sql
\. ./mysql/tables/KC_TBL_COI_DISCLOSURE_EVENT_TYPE.sql
\. ./mysql/tables/KC_TBL_COI_DISCLOSURE_PERSON.sql
\. ./mysql/tables/KC_TBL_COI_DISCLOSURE_PERSON_UNITS.sql
\. ./mysql/tables/KC_TBL_COI_DISCLOSURE_STATUS.sql
\. ./mysql/tables/KC_TBL_COI_DISCL_PROJECTS.sql
\. ./mysql/tables/KC_TBL_COI_DISC_DETAILS.sql
\. ./mysql/tables/KC_TBL_COI_ENTITY_STATUS_CODE.sql
\. ./mysql/tables/KC_TBL_COI_REVIEWER.sql
\. ./mysql/tables/KC_TBL_COI_REVIEW_TYPE.sql
\. ./mysql/tables/KC_TBL_COI_STATUS.sql
\. ./mysql/tables/KC_TBL_COMMITTEE.sql
\. ./mysql/tables/KC_TBL_DISTRIBUTION.sql
\. ./mysql/tables/KC_TBL_FINANCIAL_ENTITY_CONTACT_INFO.sql
\. ./mysql/tables/KC_TBL_FINANCIAL_ENTITY_REPORTER.sql
\. ./mysql/tables/KC_TBL_FIN_ENTITIES_DATA_GROUPS.sql
\. ./mysql/tables/KC_TBL_FIN_ENTITIES_DATA_MATRIX.sql
\. ./mysql/tables/KC_TBL_FIN_ENTITY_REPORTER_UNITS.sql
\. ./mysql/tables/KC_TBL_FIN_INT_ENTITY_REL_TYPE.sql
\. ./mysql/tables/KC_TBL_FIN_INT_ENTITY_STATUS.sql
\. ./mysql/tables/KC_TBL_FIN_INT_ENTITY_YNQ.sql
\. ./mysql/tables/KC_TBL_FREQUENCY.sql
\. ./mysql/tables/KC_TBL_FREQUENCY_BASE.sql
\. ./mysql/tables/KC_TBL_INV_COI_DISC_DETAILS.sql
\. ./mysql/tables/KC_TBL_NEGOTIATION.sql
\. ./mysql/tables/KC_TBL_NEGOTIATION_ACTIVITY.sql
\. ./mysql/tables/KC_TBL_NEGOTIATION_ACTIVITY_TYPE.sql
\. ./mysql/tables/KC_TBL_NEGOTIATION_AGREEMENT_TYPE.sql
\. ./mysql/tables/KC_TBL_NEGOTIATION_ASSOCIATION_TYPE.sql
\. ./mysql/tables/KC_TBL_NEGOTIATION_ATTACHMENT.sql
\. ./mysql/tables/KC_TBL_NEGOTIATION_CUSTOM_DATA.sql
\. ./mysql/tables/KC_TBL_NEGOTIATION_DOCUMENT.sql
\. ./mysql/tables/KC_TBL_NEGOTIATION_LOCATION.sql
\. ./mysql/tables/KC_TBL_NEGOTIATION_STATUS.sql
\. ./mysql/tables/KC_TBL_NEGOTIATION_UNASSOC_DETAIL.sql
\. ./mysql/tables/KC_TBL_NOTIFICATION_MDL_ROLE_QLFR.sql
\. ./mysql/tables/KC_TBL_NOTIFICATION_MODULE_ROLE.sql
\. ./mysql/tables/KC_TBL_NOTIFICATION_TYPE.sql
\. ./mysql/tables/KC_TBL_NOTIFICATION_TYPE_RECIPIENT.sql
\. ./mysql/tables/KC_TBL_ORGANIZATION_CORRESPONDENT.sql
\. ./mysql/tables/KC_TBL_PERSON_EXT_T.sql
\. ./mysql/tables/KC_TBL_PERSON_FIN_INT_DISCLOSURE.sql
\. ./mysql/tables/KC_TBL_PER_FIN_INT_DISCL_DET.sql
\. ./mysql/tables/KC_TBL_PROPOSAL_ADMIN_DETAILS.sql
\. ./mysql/tables/KC_TBL_PROTOCOL_REVIEW_TYPE.sql
\. ./mysql/tables/KC_TBL_QUESTION.sql
\. ./mysql/tables/KC_TBL_REPORT.sql
\. ./mysql/tables/KC_TBL_REPORT_CLASS.sql
\. ./mysql/tables/KC_TBL_REPORT_STATUS.sql
\. ./mysql/tables/KC_TBL_SBAWD_00_SUBAWARD_DOCUMENT.sql
\. ./mysql/tables/KC_TBL_SBAWD_01_SUBAWARD_STATUS.sql
\. ./mysql/tables/KC_TBL_SBAWD_02_SUBAWARD_APPROVAL_TYPE.sql
\. ./mysql/tables/KC_TBL_SBAWD_03_SUBAWARD.sql
\. ./mysql/tables/KC_TBL_SBAWD_04_SUBAWARD_AMOUNT_INFO.sql
\. ./mysql/tables/KC_TBL_SBAWD_05_SUBAWARD_AMT_RELEASED.sql
\. ./mysql/tables/KC_TBL_SBAWD_06_SUBAWARD_CLOSEOUT.sql
\. ./mysql/tables/KC_TBL_SBAWD_07_SUBAWARD_CONTACT.sql
\. ./mysql/tables/KC_TBL_SBAWD_08_SUBAWARD_CUSTOM_DATA.sql
\. ./mysql/tables/KC_TBL_SBAWD_09_SUBAWARD_FUNDING_SOURCE.sql
\. ./mysql/tables/KC_TBL_SUBAWARD_FORMS.sql
\. ./mysql/tables/KC_TBL_UNIT_CORRESPONDENT.sql
\. ./mysql/dml/KC_DML_01_KCCOI-15_B000.sql
\. ./mysql/dml/KC_DML_01_KCCOI-21_B000.sql
\. ./mysql/dml/KC_DML_01_KCCOI-22_B000.sql
\. ./mysql/dml/KC_DML_01_KCCOI-24_B000.sql
\. ./mysql/dml/KC_DML_01_KCCOI-4_B000.sql
\. ./mysql/dml/KC_DML_01_KCCOI-52_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1327_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1354_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1470_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1497_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1517_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1529_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1530_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1562_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1596_B000.sql
\. ./mysql/dml/KC_DML_01_KRACOEUS-4930_B000.sql
\. ./mysql/dml/KC_DML_01_KRACOEUS-4937_B000.sql
\. ./mysql/dml/KC_DML_01_KRACOEUS-4956_B000.sql
\. ./mysql/dml/KC_DML_01_KRACOEUS-4962_B000.sql
\. ./mysql/dml/KC_DML_01_KRACOEUS-4969_B000.sql
\. ./mysql/dml/KC_DML_01_KRACOEUS-4979_B000.sql
\. ./mysql/dml/KC_DML_01_KRACOEUS-4998_B000.sql
\. ./mysql/dml/KC_DML_01_KRACOEUS-5071_B000.sql
\. ./mysql/dml/KC_DML_01_KRACOEUS-5083_B000.sql
\. ./mysql/dml/KC_DML_02_KCIRB-1567_B000.sql
\. ./mysql/dml/KC_DML_02_KCIRB-1568_B000.sql
\. ./mysql/constraints/KC_FK2_AWARD_PAYMENT_SCHEDULE.sql
\. ./mysql/constraints/KC_FK_AWARD_REPORT_TRACKING.sql
\. ./mysql/constraints/KC_FK_COI_DISCLOSURE_PERSON_UNITS.sql
\. ./mysql/constraints/KC_FK_COI_DISCL_PROJECTS.sql
\. ./mysql/constraints/KC_FK_COI_DISC_DETAILS.sql
\. ./mysql/constraints/KC_FK_COMMITTEE.sql
\. ./mysql/constraints/KC_FK_FIN_ENTITIES_DATA_MATRIX.sql
\. ./mysql/constraints/KC_FK_FIN_ENTITY_REPORTER_UNITS.sql
\. ./mysql/constraints/KC_FK_FIN_INT_ENTITY_YNQ.sql
\. ./mysql/constraints/KC_FK_INV_COI_DISC_DETAILS.sql
\. ./mysql/constraints/KC_FK_NEGOTIATION.sql
\. ./mysql/constraints/KC_FK_NEGOTIATION_ACTIVITY.sql
\. ./mysql/constraints/KC_FK_NEGOTIATION_ATTACHMENT.sql
\. ./mysql/constraints/KC_FK_NEGOTIATION_CUSTOM_DATA.sql
\. ./mysql/constraints/KC_FK_NEGOTIATION_UNASSOC_DETAIL.sql
\. ./mysql/constraints/KC_FK_NOTIFICATION_MDL_ROLE_QLFR.sql
\. ./mysql/constraints/KC_FK_PERSON_FIN_INT_DISCLOSURE.sql
\. ./mysql/constraints/KC_FK_PER_FIN_INT_DISCL_DET.sql
\. ./mysql/constraints/KC_NEGOTIATION_ACTIVITY_TYPE_UK1.sql
\. ./mysql/constraints/KC_NEGOTIATION_LOCATION_UK1.sql
\. ./mysql/constraints/KC_PK_COI_DISCLOSURE_STATUS.sql
\. ./mysql/constraints/KC_TYPE_SUBAWARD_AMOUNT_INFO.sql
\. ./mysql/constraints/KC_TYPE_SUBAWARD_AMT_RELEASED.sql
\. ./mysql/constraints/KC_TYPE_SUBAWARD_CLOSEOUT.sql
\. ./mysql/constraints/KC_TYPE_SUBAWARD_CONTACT.sql
\. ./mysql/constraints/KC_TYPE_SUBAWARD_CUSTOM_DATA.sql
\. ./mysql/constraints/KC_TYPE_SUBAWARD_FUNDING_SOURCE.sql
\. ./mysql/constraints/KC_UQ_COI_DISCL_PROJECTS.sql
\. ./mysql/constraints/KC_UQ_COI_DISC_DETAILS.sql
\. ./mysql/constraints/KC_UQ_FINANCIAL_ENTITY_REPORTER.sql
\. ./mysql/constraints/KC_UQ_FIN_INT_ENTITY_YNQ.sql
\. ./mysql/constraints/KC_UQ_INV_COI_DISC_DETAILS.sql
\. ./mysql/constraints/KC_UQ_NEGOTIATION_AGREEMENT_TYPE.sql
\. ./mysql/constraints/KC_UQ_NEGOTIATION_ASSC_TYPE.sql
\. ./mysql/constraints/KC_UQ_NEGOTIATION_STATUS.sql
\. ./mysql/constraints/KC_UQ_PERSON_FIN_INT_DISCLOSURE.sql
\. ./mysql/constraints/KC_UQ_PER_FIN_INT_DISCL_DET.sql
commit;
exit
