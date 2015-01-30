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

set define off
set sqlblanklines on
spool KC-RELEASE-5_1_0-Upgrade-ORACLE-Install.log
@../../current/5.1.0/sequences/KC_SEQ_BUDGET_SUBAWARD_PER_DET.sql
@../../current/5.1.0/sequences/KC_SEQ_PERSON_SIGNATURE.sql
@../../current/5.1.0/sequences/KC_SEQ_REPORT_ID.sql
@../../current/5.1.0/tables/KC_TBL_AWARD_SUBCONTRACT_GOALS_EXP.sql
@../../current/5.1.0/tables/KC_TBL_AWARD_TEMPLATE_COMMENT.sql
@../../current/5.1.0/tables/KC_TBL_AWARD_UNIT_CONTACTS.sql
@../../current/5.1.0/tables/KC_TBL_BUDGET_DETAILS.sql
@../../current/5.1.0/tables/KC_TBL_BUDGET_PERIODS.sql
@../../current/5.1.0/tables/KC_TBL_BUDGET_SUB_AWARDS.sql
@../../current/5.1.0/tables/KC_TBL_BUDGET_SUB_AWARD_PERIOD_DETAIL.sql
@../../current/5.1.0/tables/KC_TBL_COEUS_SUB_MODULE.sql
@../../current/5.1.0/tables/KC_TBL_COI_DISCLOSURE.sql
@../../current/5.1.0/tables/KC_TBL_COI_DISCLOSURE_NOTEPAD.sql
@../../current/5.1.0/tables/KC_TBL_COI_NOTIFICATION.sql
@../../current/5.1.0/tables/KC_TBL_COMM_MEMBER_EXPERTISE.sql
@../../current/5.1.0/tables/KC_TBL_COMM_RESEARCH_AREAS.sql
@../../current/5.1.0/tables/KC_TBL_CUST_REPORT_DETAILS.sql
@../../current/5.1.0/tables/KC_TBL_CUST_REPORT_TYPE.sql
@../../current/5.1.0/tables/KC_TBL_CUST_RPT_DEFAULT_PARMS.sql
@../../current/5.1.0/tables/KC_TBL_CUST_RPT_TYPE_DOCUMENT.sql
@../../current/5.1.0/tables/KC_TBL_EPS_PROPOSAL.sql
@../../current/5.1.0/tables/KC_TBL_EPS_PROPOSAL_BUDGET_EXT.sql
@../../current/5.1.0/tables/KC_TBL_IACUC_PROTOCOL_NOTEPAD.sql
@../../current/5.1.0/tables/KC_TBL_IACUC_PROTOCOL_NOTIFICATION.sql
@../../current/5.1.0/tables/KC_TBL_IACUC_PROTOCOL_OLR_STATUS.sql
@../../current/5.1.0/tables/KC_TBL_IACUC_PROTO_OLR_DT_REC_TYPE.sql
@../../current/5.1.0/tables/KC_TBL_NEGOTIATION_NOTIFICATION.sql
@../../current/5.1.0/tables/KC_TBL_PENDING_TRANSACTIONS.sql
@../../current/5.1.0/tables/KC_TBL_PERSON_EXT_T.sql
@../../current/5.1.0/tables/KC_TBL_PERSON_SIGNATURE.sql
@../../current/5.1.0/tables/KC_TBL_PROPOSAL.sql
@../../current/5.1.0/tables/KC_TBL_PROPOSAL_LOG.sql
@../../current/5.1.0/tables/KC_TBL_PROTOCOL_NOTEPAD.sql
@../../current/5.1.0/tables/KC_TBL_PROTOCOL_NOTIFICATION.sql
@../../current/5.1.0/tables/KC_TBL_QUESTIONNAIRE_USAGE.sql
@../../current/5.1.0/tables/KC_TBL_S2S_OPPORTUNITY.sql
@../../current/5.1.0/tables/KC_TBL_S2S_PROVIDERS.sql
@../../current/5.1.0/tables/KC_TBL_SPONSOR.sql
@../../current/5.1.0/tables/KC_TBL_SUBCONTRACTING_BUD.sql
@../../current/5.1.0/tables/KC_TBL_SUBCONTRACT_EXP_CAT.sql
@../../current/5.1.0/tables/KC_TBL_SUBCONTRACT_EXP_CAT_DET.sql
@../../current/5.1.0/tables/KC_TBL_SUB_EXP_CAT_BY_FY.sql
@../../current/5.1.0/tables/KC_TBL_WATERMARK.sql
@../../current/5.1.0/dml/KC_DML_01_KCCOI-324_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KCIAC-375_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KCIAC-446_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KCIAC-449_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-5361_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-5674_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-5693_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-5762_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-5812_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-6004_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-6005_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-6013_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-6045_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-6056_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-6076_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-6105_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-6110_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-6112_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-6209_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-6212_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-6225_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-6228_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-6229_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-6237_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-6241_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-6243_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-6295_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-6331_B000.sql
@../../current/5.1.0/dml/KC_DML_01_KRACOEUS-6340_B000.sql
@../../current/5.1.0/constraints/KC_FK1_BUDGET_SUBAWARD_PER_DET.sql
@../../current/5.1.0/constraints/KC_FK1_BUDGET_SUB_AWARDS.sql
@../../current/5.1.0/constraints/KC_FK2_BUDGET_DETAILS.sql
@../../current/5.1.0/constraints/KC_FK_COMM_MEMBER_EXPERTISE.sql
@../../current/5.1.0/constraints/KC_FK_COMM_RESEARCH_AREAS.sql
@../../current/5.1.0/constraints/KC_FK_S2S_PROVIDERS.sql
@../../current/5.1.0/constraints/KC_IX_KRACOEUS-6157.sql
@../../current/5.1.0/constraints/KC_UQ_PERSON_SIGNATURE.sql
commit;
exit
