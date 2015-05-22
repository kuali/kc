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

@./kc/bootstrap/V521_020__KC_SEQ_CONTACT_USAGE_ID.sql
@./kc/bootstrap/V521_021__KC_SEQ_S2S_USER_ATTACHED_FORM_ID.sql
@./kc/bootstrap/V521_022__KC_SEQ_S2S_USER_ATTD_FORM_ATT_ID.sql
@./kc/bootstrap/V521_023__KC_SEQ_S2S_USER_ATTD_FORM_FILE_ID.sql
@./kc/bootstrap/V521_024__KC_SEQ_S2S_USR_ATD_FRM_ATT_FLE_ID.sql
@./kc/bootstrap/V521_025__KC_SEQ_SUBAWARD_ATTACHMENT_ID.sql
@./kc/bootstrap/V521_026__KC_SEQ_SUBAWARD_REPORT_ID.sql
@./kc/bootstrap/V521_027__KC_SEQ_VAL_PROTO_SUB_REV_TYP_ID.sql
@./kc/bootstrap/V521_028__KC_SEQ_VAL_PROTO_SUB_TYP_QUAL_ID.sql
@./kc/bootstrap/V521_029__KC_TBL_AWARD_PAYMENT_SCHEDULE.sql
@./kc/bootstrap/V521_030__KC_TBL_CONTACT_USAGE.sql
@./kc/bootstrap/V521_031__KC_TBL_S2S_USER_ATTACHED_FORM_ATT.sql
@./kc/bootstrap/V521_032__KC_TBL_S2S_USER_ATTACHED_FORM.sql
@./kc/bootstrap/V521_033__KC_TBL_SUBAWARD_AMOUNT_INFO.sql
@./kc/bootstrap/V521_034__KC_TBL_SUBAWARD_ATTACHMENTS.sql
@./kc/bootstrap/V521_035__KC_TBL_SUBAWARD_ATTACHMENT_TYPE.sql
@./kc/bootstrap/V521_036__KC_TBL_SUBAWARD_FORMS.sql
@./kc/bootstrap/V521_037__KC_TBL_SUBAWARD_REPORTS.sql
@./kc/bootstrap/V521_038__KC_TBL_SUBAWARD_REPORT_TYPE.sql
@./kc/bootstrap/V521_039__KC_TBL_SUBAWARD.sql
@./kc/bootstrap/V521_040__KC_TBL_SUBAWARD_TEMPLATE_INFO.sql
@./kc/bootstrap/V521_041__KC_TBL_SUBAWARD_TEMPLATE_TYPE.sql
@./kc/bootstrap/V521_042__KC_TBL_SUBCONTRACT_COPYRIGHT_TYPE.sql
@./kc/bootstrap/V521_043__KC_TBL_SUBCONTRACT_COST_TYPE.sql
@./kc/bootstrap/V521_044__KC_DML_01_KRACOEUS-6792_B000.sql
@./kc/bootstrap/V521_045__KC_DML_01_KRACOEUS-6805_B000.sql
@./kc/bootstrap/V521_046__KC_DML_01_KRACOEUS-7035_B000.sql
@./kc/bootstrap/V521_047__KC_DML_01_KRACOEUS-7036_B000.sql
@./kc/bootstrap/V521_048__KC_DML_01_KRACOEUS-7037_B000.sql
@./kc/bootstrap/V521_049__KC_DML_01_KRACOEUS-7038_B000.sql
@./kc/bootstrap/V521_050__KC_DML_01_KRACOEUS-7039_B000.sql
@./kc/bootstrap/V521_051__KC_DML_01_KRACOEUS-7040_B000.sql
@./kc/bootstrap/V521_052__KC_DML_01_KRACOEUS-7042_B000.sql
@./kc/bootstrap/V521_053__KC_DML_01_KRACOEUS-7072_B000.sql
@./kc/bootstrap/V521_054__KC_DML_01_KRACOEUS-7158_B000.sql
@./kc/bootstrap/V521_055__KC_DML_01_KRACOEUS-7172_B000.sql
@./kc/bootstrap/V521_056__KC_DML_01_KRACOEUS-7206_B000.sql
@./kc/bootstrap/V521_057__KC_DML_01_KRACOEUS-7223_B000.sql
@./kc/bootstrap/V521_058__KC_DML_01_KRACOEUS-7228_B000.sql
@./kc/bootstrap/V521_059__KC_DML_01_KRACOEUS-7229_B000.sql
@./kc/bootstrap/V521_060__KC_DML_01_KRACOEUS-7234_B000.sql
@./kc/bootstrap/V521_061__KC_DML_01_KRACOEUS-7236_B000.sql
@./kc/bootstrap/V521_062__KC_DML_02_KRACOEUS-7042_B000.sql
@./kc/bootstrap/V521_063__KC_DML_02_KRACOEUS-7206_B000.sql
@./kc/bootstrap/V521_064__KC_FK_CONTACT_USAGE.sql
@./kc/bootstrap/V521_065__KC_FK_SUBAWARD_FORMS.sql
commit;
