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

@./kc/bootstrap/V510_055__KC_SEQ_BUDGET_SUBAWARD_PER_DET.sql
@./kc/bootstrap/V510_056__KC_SEQ_PERSON_SIGNATURE.sql
@./kc/bootstrap/V510_057__KC_SEQ_REPORT_ID.sql
@./kc/bootstrap/V510_058__KC_TBL_AWARD_SUBCONTRACT_GOALS_EXP.sql
@./kc/bootstrap/V510_059__KC_TBL_AWARD_TEMPLATE_COMMENT.sql
@./kc/bootstrap/V510_060__KC_TBL_AWARD_UNIT_CONTACTS.sql
@./kc/bootstrap/V510_061__KC_TBL_BUDGET_DETAILS.sql
@./kc/bootstrap/V510_062__KC_TBL_BUDGET_PERIODS.sql
@./kc/bootstrap/V510_063__KC_TBL_BUDGET_SUB_AWARDS.sql
@./kc/bootstrap/V510_064__KC_TBL_BUDGET_SUB_AWARD_PERIOD_DETAIL.sql
@./kc/bootstrap/V510_065__KC_TBL_COEUS_SUB_MODULE.sql
@./kc/bootstrap/V510_066__KC_TBL_COI_DISCLOSURE.sql
@./kc/bootstrap/V510_067__KC_TBL_COI_DISCLOSURE_NOTEPAD.sql
@./kc/bootstrap/V510_068__KC_TBL_COI_NOTIFICATION.sql
@./kc/bootstrap/V510_069__KC_TBL_COMM_MEMBER_EXPERTISE.sql
@./kc/bootstrap/V510_070__KC_TBL_COMM_RESEARCH_AREAS.sql
@./kc/bootstrap/V510_071__KC_TBL_CUST_REPORT_DETAILS.sql
@./kc/bootstrap/V510_072__KC_TBL_CUST_REPORT_TYPE.sql
@./kc/bootstrap/V510_073__KC_TBL_CUST_RPT_DEFAULT_PARMS.sql
@./kc/bootstrap/V510_074__KC_TBL_CUST_RPT_TYPE_DOCUMENT.sql
@./kc/bootstrap/V510_075__KC_TBL_EPS_PROPOSAL.sql
@./kc/bootstrap/V510_076__KC_TBL_EPS_PROPOSAL_BUDGET_EXT.sql
@./kc/bootstrap/V510_077__KC_TBL_IACUC_PROTOCOL_NOTEPAD.sql
@./kc/bootstrap/V510_078__KC_TBL_IACUC_PROTOCOL_NOTIFICATION.sql
@./kc/bootstrap/V510_079__KC_TBL_IACUC_PROTOCOL_OLR_STATUS.sql
@./kc/bootstrap/V510_080__KC_TBL_IACUC_PROTO_OLR_DT_REC_TYPE.sql
@./kc/bootstrap/V510_081__KC_TBL_NEGOTIATION_NOTIFICATION.sql
@./kc/bootstrap/V510_082__KC_TBL_PENDING_TRANSACTIONS.sql
@./kc/bootstrap/V510_083__KC_TBL_PERSON_EXT_T.sql
@./kc/bootstrap/V510_084__KC_TBL_PERSON_SIGNATURE.sql
@./kc/bootstrap/V510_085__KC_TBL_PROPOSAL.sql
@./kc/bootstrap/V510_086__KC_TBL_PROPOSAL_LOG.sql
@./kc/bootstrap/V510_087__KC_TBL_PROTOCOL_NOTEPAD.sql
@./kc/bootstrap/V510_088__KC_TBL_PROTOCOL_NOTIFICATION.sql
@./kc/bootstrap/V510_089__KC_TBL_QUESTIONNAIRE_USAGE.sql
@./kc/bootstrap/V510_090__KC_TBL_S2S_OPPORTUNITY.sql
@./kc/bootstrap/V510_091__KC_TBL_S2S_PROVIDERS.sql
@./kc/bootstrap/V510_092__KC_TBL_SPONSOR.sql
@./kc/bootstrap/V510_093__KC_TBL_SUBCONTRACTING_BUD.sql
@./kc/bootstrap/V510_094__KC_TBL_SUBCONTRACT_EXP_CAT.sql
@./kc/bootstrap/V510_095__KC_TBL_SUBCONTRACT_EXP_CAT_DET.sql
@./kc/bootstrap/V510_096__KC_TBL_SUB_EXP_CAT_BY_FY.sql
@./kc/bootstrap/V510_097__KC_TBL_WATERMARK.sql
@./kc/bootstrap/V510_098__KC_DML_01_KCCOI-324_B000.sql
@./kc/bootstrap/V510_099__KC_DML_01_KCIAC-375_B000.sql
@./kc/bootstrap/V510_100__KC_DML_01_KCIAC-446_B000.sql
@./kc/bootstrap/V510_101__KC_DML_01_KCIAC-449_B000.sql
@./kc/bootstrap/V510_102__KC_DML_01_KRACOEUS-5361_B000.sql
@./kc/bootstrap/V510_103__KC_DML_01_KRACOEUS-5674_B000.sql
@./kc/bootstrap/V510_104__KC_DML_01_KRACOEUS-5693_B000.sql
@./kc/bootstrap/V510_105__KC_DML_01_KRACOEUS-5762_B000.sql
@./kc/bootstrap/V510_106__KC_DML_01_KRACOEUS-5812_B000.sql
@./kc/bootstrap/V510_107__KC_DML_01_KRACOEUS-6004_B000.sql
@./kc/bootstrap/V510_108__KC_DML_01_KRACOEUS-6005_B000.sql
@./kc/bootstrap/V510_109__KC_DML_01_KRACOEUS-6013_B000.sql
@./kc/bootstrap/V510_110__KC_DML_01_KRACOEUS-6045_B000.sql
@./kc/bootstrap/V510_111__KC_DML_01_KRACOEUS-6056_B000.sql
@./kc/bootstrap/V510_112__KC_DML_01_KRACOEUS-6076_B000.sql
@./kc/bootstrap/V510_113__KC_DML_01_KRACOEUS-6105_B000.sql
@./kc/bootstrap/V510_114__KC_DML_01_KRACOEUS-6110_B000.sql
@./kc/bootstrap/V510_115__KC_DML_01_KRACOEUS-6112_B000.sql
@./kc/bootstrap/V510_116__KC_DML_01_KRACOEUS-6209_B000.sql
@./kc/bootstrap/V510_117__KC_DML_01_KRACOEUS-6212_B000.sql
@./kc/bootstrap/V510_118__KC_DML_01_KRACOEUS-6225_B000.sql
@./kc/bootstrap/V510_119__KC_DML_01_KRACOEUS-6228_B000.sql
@./kc/bootstrap/V510_120__KC_DML_01_KRACOEUS-6229_B000.sql
@./kc/bootstrap/V510_121__KC_DML_01_KRACOEUS-6237_B000.sql
@./kc/bootstrap/V510_122__KC_DML_01_KRACOEUS-6241_B000.sql
@./kc/bootstrap/V510_123__KC_DML_01_KRACOEUS-6243_B000.sql
@./kc/bootstrap/V510_124__KC_DML_01_KRACOEUS-6295_B000.sql
@./kc/bootstrap/V510_125__KC_DML_01_KRACOEUS-6331_B000.sql
@./kc/bootstrap/V510_126__KC_DML_01_KRACOEUS-6340_B000.sql
@./kc/bootstrap/V510_127__KC_FK1_BUDGET_SUBAWARD_PER_DET.sql
@./kc/bootstrap/V510_128__KC_FK1_BUDGET_SUB_AWARDS.sql
@./kc/bootstrap/V510_129__KC_FK2_BUDGET_DETAILS.sql
@./kc/bootstrap/V510_130__KC_FK_COMM_MEMBER_EXPERTISE.sql
@./kc/bootstrap/V510_131__KC_FK_COMM_RESEARCH_AREAS.sql
@./kc/bootstrap/V510_132__KC_FK_S2S_PROVIDERS.sql
@./kc/bootstrap/V510_133__KC_IX_KRACOEUS-6157.sql
@./kc/bootstrap/V510_134__KC_UQ_PERSON_SIGNATURE.sql
commit;
