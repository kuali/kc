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

spool 501_oracle_kc_upgrade.sql.log
@./kc/bootstrap/V501_035__KC_SEQ_BUD_FRMLTD_COST_DETAIL_ID.sql
@./kc/bootstrap/V501_036__KC_SEQ_IACUC_BATCH_CORRESPONDENCE_DETAIL.sql
@./kc/bootstrap/V501_037__KC_SEQ_IACUC_PROC_CAT_CUSTOM_DATA.sql
@./kc/bootstrap/V501_038__KC_SEQ_IACUC_PROC_PERSON_RESPONSIBLE.sql
@./kc/bootstrap/V501_039__KC_SEQ_IACUC_PROTOCOL_STUDY_GROUPS.sql
@./kc/bootstrap/V501_040__KC_SEQ_IACUC_PROTOCOL_STUDY_GROUP_DTL.sql
@./kc/bootstrap/V501_041__KC_SEQ_IACUC_PROTOCOL_STUDY_GROUP_HDR.sql
@./kc/bootstrap/V501_042__KC_SEQ_IACUC_PROTO_AMEND_RENEWAL.sql
@./kc/bootstrap/V501_043__KC_SEQ_IACUC_PROTO_AMEND_RENEW_MOD.sql
@./kc/bootstrap/V501_044__KC_SEQ_IACUC_PROTO_CORRESP_TEMPL.sql
@./kc/bootstrap/V501_045__KC_SEQ_IACUC_PROTO_STUDY_CUSTOM_DATA.sql
@./kc/bootstrap/V501_046__KC_SEQ_IACUC_PROTO_STUDY_GROUP_LOCS.sql
@./kc/bootstrap/V501_047__KC_SEQ_IACUC_PROTO_SUBMIS_DOC_ID.sql
@./kc/bootstrap/V501_048__KC_SEQ_PERSON_TRAINING_ID.sql
@./kc/bootstrap/V501_049__KC_SEQ_UNIT_FRMULATED_COST_ID.sql
@./kc/bootstrap/V501_050__KC_TBL_BUDGET_DETAILS.sql
@./kc/bootstrap/V501_051__KC_TBL_BUD_FORMULATED_COST_DETAIL.sql
@./kc/bootstrap/V501_052__KC_TBL_COI_DISCLOSURE.sql
@./kc/bootstrap/V501_053__KC_TBL_COI_DISCL_PROJECTS.sql
@./kc/bootstrap/V501_054__KC_TBL_COI_USER_ROLES.sql
@./kc/bootstrap/V501_055__KC_TBL_COMM_BATCH_CORRESP.sql
@./kc/bootstrap/V501_056__KC_TBL_COMM_BATCH_CORRESP_DETAIL.sql
@./kc/bootstrap/V501_057__KC_TBL_COMM_SCHEDULE_MINUTES.sql
@./kc/bootstrap/V501_058__KC_TBL_FORMULATED_TYPE.sql
@./kc/bootstrap/V501_059__KC_TBL_IACUC_BATCH_CORRESPONDENCE.sql
@./kc/bootstrap/V501_060__KC_TBL_IACUC_BATCH_CORRESP_DETAIL.sql
@./kc/bootstrap/V501_061__KC_TBL_IACUC_LOCATION_NAME.sql
@./kc/bootstrap/V501_062__KC_TBL_IACUC_LOCATION_TYPE.sql
@./kc/bootstrap/V501_063__KC_TBL_IACUC_PAIN_CATEGORY.sql
@./kc/bootstrap/V501_064__KC_TBL_IACUC_PERSON_TRAINING.sql
@./kc/bootstrap/V501_065__KC_TBL_IACUC_PRINCIPLES.sql
@./kc/bootstrap/V501_066__KC_TBL_IACUC_PROC_CAT_CUSTOM_DATA.sql
@./kc/bootstrap/V501_067__KC_TBL_IACUC_PROC_PERSON_RESPONSIBLE.sql
@./kc/bootstrap/V501_068__KC_TBL_IACUC_PROTOCOL_ACTIONS.sql
@./kc/bootstrap/V501_069__KC_TBL_IACUC_PROTOCOL_ACTION_TYPE.sql
@./kc/bootstrap/V501_070__KC_TBL_IACUC_PROTOCOL_ALT_SEARCH.sql
@./kc/bootstrap/V501_071__KC_TBL_IACUC_PROTOCOL_CONTINGENCY.sql
@./kc/bootstrap/V501_072__KC_TBL_IACUC_PROTOCOL_DOCUMENT.sql
@./kc/bootstrap/V501_073__KC_TBL_IACUC_PROTOCOL_STUDY_GROUPS.sql
@./kc/bootstrap/V501_074__KC_TBL_IACUC_PROTOCOL_STUDY_GROUP_DTL.sql
@./kc/bootstrap/V501_075__KC_TBL_IACUC_PROTOCOL_STUDY_GROUP_HDR.sql
@./kc/bootstrap/V501_076__KC_TBL_IACUC_PROTOCOL_SUBMISSION_DOC.sql
@./kc/bootstrap/V501_077__KC_TBL_IACUC_PROTOCOL_UNITS.sql
@./kc/bootstrap/V501_078__KC_TBL_IACUC_PROTO_AMEND_RENEWAL.sql
@./kc/bootstrap/V501_079__KC_TBL_IACUC_PROTO_AMEND_RENEW_MOD.sql
@./kc/bootstrap/V501_080__KC_TBL_IACUC_PROTO_CORRESPONDENCE.sql
@./kc/bootstrap/V501_081__KC_TBL_IACUC_PROTO_STUDY_CUSTOM_DATA.sql
@./kc/bootstrap/V501_082__KC_TBL_IACUC_PROTO_STUDY_GROUP_LOCS.sql
@./kc/bootstrap/V501_083__KC_TBL_IACUC_RESEARCH_AREAS.sql
@./kc/bootstrap/V501_084__KC_TBL_PROPOSAL_NOTEPAD.sql
@./kc/bootstrap/V501_085__KC_TBL_PROTOCOL_VOTE_ABSTAINEES.sql
@./kc/bootstrap/V501_086__KC_TBL_PROTOCOL_VOTE_RECUSED.sql
@./kc/bootstrap/V501_087__KC_TBL_QUESTIONNAIRE_ANSWER_HEADER.sql
@./kc/bootstrap/V501_088__KC_TBL_QUESTIONNAIRE_QUESTIONS.sql
@./kc/bootstrap/V501_089__KC_TBL_UNIT_FORMULATED_COST.sql
@./kc/bootstrap/V501_090__KC_TBL_VALID_IACUC_PROTO_ACTN_ACTN.sql
@./kc/bootstrap/V501_091__KC_DML_01_KCCOI-253_B000.sql
@./kc/bootstrap/V501_092__KC_DML_01_KCCOI-332_B000.sql
@./kc/bootstrap/V501_093__KC_DML_01_KCIAC-113_B000.sql
@./kc/bootstrap/V501_094__KC_DML_01_KCIAC-126_B000.sql
@./kc/bootstrap/V501_095__KC_DML_01_KCIAC-159_B000.sql
@./kc/bootstrap/V501_096__KC_DML_01_KCIAC-164_B000.sql
@./kc/bootstrap/V501_097__KC_DML_01_KCIAC-170_B000.sql
@./kc/bootstrap/V501_098__KC_DML_01_KCIAC-193_B000.sql
@./kc/bootstrap/V501_099__KC_DML_01_KCIAC-197_B000.sql
@./kc/bootstrap/V501_100__KC_DML_01_KCIAC-214_B000.sql
@./kc/bootstrap/V501_101__KC_DML_01_KCIAC-250_B000.sql
@./kc/bootstrap/V501_102__KC_DML_01_KCIAC-277_B000.sql
@./kc/bootstrap/V501_103__KC_DML_01_KCIAC-278_B000.sql
@./kc/bootstrap/V501_104__KC_DML_01_KCIAC-285_B000.sql
@./kc/bootstrap/V501_105__KC_DML_01_KCIAC-286_B000.sql
@./kc/bootstrap/V501_106__KC_DML_01_KCIAC-287_B000.sql
@./kc/bootstrap/V501_107__KC_DML_01_KCIAC-288_B000.sql
@./kc/bootstrap/V501_108__KC_DML_01_KCIAC-289_B000.sql
@./kc/bootstrap/V501_109__KC_DML_01_KCIAC-290_B000.sql
@./kc/bootstrap/V501_110__KC_DML_01_KCIAC-291_B000.sql
@./kc/bootstrap/V501_111__KC_DML_01_KCIAC-297_B000.sql
@./kc/bootstrap/V501_112__KC_DML_01_KCIAC-307_B000.sql
@./kc/bootstrap/V501_113__KC_DML_01_KCIAC-310_B000.sql
@./kc/bootstrap/V501_114__KC_DML_01_KCIAC-311_B000.sql
@./kc/bootstrap/V501_115__KC_DML_01_KCIAC-312_B000.sql
@./kc/bootstrap/V501_116__KC_DML_01_KCIAC-313_B000.sql
@./kc/bootstrap/V501_117__KC_DML_01_KCIAC-314_B000.sql
@./kc/bootstrap/V501_118__KC_DML_01_KCIAC-315_B000.sql
@./kc/bootstrap/V501_119__KC_DML_01_KCIAC-316_B000.sql
@./kc/bootstrap/V501_120__KC_DML_01_KCIAC-317_B000.sql
@./kc/bootstrap/V501_121__KC_DML_01_KCIAC-319_B000.sql
@./kc/bootstrap/V501_122__KC_DML_01_KCIAC-321_B000.sql
@./kc/bootstrap/V501_123__KC_DML_01_KCIAC-324_B000.sql
@./kc/bootstrap/V501_124__KC_DML_01_KCIAC-378_B000.sql
@./kc/bootstrap/V501_125__KC_DML_01_KCIAC-379_B000.sql
@./kc/bootstrap/V501_126__KC_DML_01_KCIAC-400_B000.sql
@./kc/bootstrap/V501_127__KC_DML_01_KCIAC-408_B000.sql
@./kc/bootstrap/V501_128__KC_DML_01_KCIAC-419_B000.sql
@./kc/bootstrap/V501_129__KC_DML_01_KCIAC-425_B000.sql
@./kc/bootstrap/V501_130__KC_DML_01_KCIAC-55_B000.sql
@./kc/bootstrap/V501_131__KC_DML_01_KCIAC-68_B000.sql
@./kc/bootstrap/V501_132__KC_DML_01_KCIAC-69_B000.sql
@./kc/bootstrap/V501_133__KC_DML_01_KCIAC-80_B000.sql
@./kc/bootstrap/V501_134__KC_DML_01_KCIAC_338_B000.sql
@./kc/bootstrap/V501_135__KC_DML_01_KCIRB-1697_B000.sql
@./kc/bootstrap/V501_136__KC_DML_01_KCIRB-1779_B000.sql
@./kc/bootstrap/V501_137__KC_DML_01_KCIRB-1844_B000.sql
@./kc/bootstrap/V501_138__KC_DML_01_KRACOEUS-5232_B000.sql
@./kc/bootstrap/V501_139__KC_DML_01_KRACOEUS-5446_B000.sql
@./kc/bootstrap/V501_140__KC_DML_01_KRACOEUS-5561_B000.sql
@./kc/bootstrap/V501_141__KC_DML_01_KRACOEUS-5562_B000.sql
@./kc/bootstrap/V501_142__KC_DML_01_KRACOEUS-5583_B000.sql
@./kc/bootstrap/V501_143__KC_DML_01_KRACOEUS-5593_B000.sql
@./kc/bootstrap/V501_144__KC_DML_01_KRACOEUS-5610_B000.sql
@./kc/bootstrap/V501_145__KC_DML_02_KCIAC-340_B000.sql
@./kc/bootstrap/V501_146__KC_DML_03_KRACOEUS-5493_B000.sql
@./kc/bootstrap/V501_147__KC_DML_04_KRACOEUS-5493_B000.sql
@./kc/bootstrap/V501_148__KC_DML_04_KRACOEUS-5571_B000.sql
@./kc/bootstrap/V501_149__KC_DML_04_KRACOEUS-5572_B000.sql
@./kc/bootstrap/V501_150__KC_DML_04_KRACOEUS-5573_B000.sql
@./kc/bootstrap/V501_151__KC_DML_05_KRACOEUS-5636_B000.sql
@./kc/bootstrap/V501_152__KC_FK_BUD_FRMLTD_COST_DETAIL.sql
@./kc/bootstrap/V501_153__KC_FK_COMM_BATCH_CORRESP.sql
@./kc/bootstrap/V501_154__KC_FK_COMM_BATCH_CORRESP_DETAIL.sql
@./kc/bootstrap/V501_155__KC_FK_COMM_SCHEDULE_MINUTES.sql
@./kc/bootstrap/V501_156__KC_FK_IACUC_BATCH_CORRESPONDENCE.sql
@./kc/bootstrap/V501_157__KC_FK_IACUC_BATCH_CORRESP_DETAIL.sql
@./kc/bootstrap/V501_158__KC_FK_IACUC_PERSON_TRAINING.sql
@./kc/bootstrap/V501_159__KC_FK_IACUC_PROC_PERSON_RESPONSIBLE.sql
@./kc/bootstrap/V501_160__KC_FK_IACUC_PROTOCOL_RESEARCH_AREAS.sql
@./kc/bootstrap/V501_161__KC_FK_IACUC_PROTOCOL_STUDY_GROUPS.sql
@./kc/bootstrap/V501_162__KC_FK_IACUC_PROTOCOL_STUDY_GROUP_DTL.sql
@./kc/bootstrap/V501_163__KC_FK_IACUC_PROTOCOL_STUDY_GROUP_HDR.sql
@./kc/bootstrap/V501_164__KC_FK_IACUC_PROTO_AMEND_RENEWAL.sql
@./kc/bootstrap/V501_165__KC_FK_IACUC_PROTO_AMEND_RENEW_MOD.sql
@./kc/bootstrap/V501_166__KC_FK_IACUC_PROTO_STUDY_GROUP_LOCS.sql
@./kc/bootstrap/V501_167__KC_FK_PROTOCOL_VOTE_ABSTAINEES.sql
@./kc/bootstrap/V501_168__KC_FK_PROTOCOL_VOTE_RECUSED.sql
@./kc/bootstrap/V501_169__KC_FK_UNIT_FORMULATED_COST.sql
@./kc/bootstrap/V501_170__KC_FK_VALID_IACUC_PROTO_ACTN_ACTN.sql
@./kc/bootstrap/V501_171__KC_UQ_IACUC_PERSON_TRAINING.sql
@./kc/bootstrap/V501_172__KC_UQ_IACUC_PROC_PERSON_RESPONSIBLE.sql
@./kc/bootstrap/V501_173__KC_UQ_IACUC_PROTO_AMEND_RENEW_MOD.sql
@./kc/bootstrap/V501_174__KC_UQ_IACUC_PROTO_STUDY_GROUP_LOCS.sql
commit;
