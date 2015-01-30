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
spool KC-RELEASE-5_2_1-Upgrade-ORACLE-Install.log
@../../current/5.2.1/sequences/KC_SEQ_CONTACT_USAGE_ID.sql
@../../current/5.2.1/sequences/KC_SEQ_S2S_USER_ATTACHED_FORM_ID.sql
@../../current/5.2.1/sequences/KC_SEQ_S2S_USER_ATTD_FORM_ATT_ID.sql
@../../current/5.2.1/sequences/KC_SEQ_S2S_USER_ATTD_FORM_FILE_ID.sql
@../../current/5.2.1/sequences/KC_SEQ_S2S_USR_ATD_FRM_ATT_FLE_ID.sql
@../../current/5.2.1/sequences/KC_SEQ_SUBAWARD_ATTACHMENT_ID.sql
@../../current/5.2.1/sequences/KC_SEQ_SUBAWARD_REPORT_ID.sql
@../../current/5.2.1/sequences/KC_SEQ_VAL_PROTO_SUB_REV_TYP_ID.sql
@../../current/5.2.1/sequences/KC_SEQ_VAL_PROTO_SUB_TYP_QUAL_ID.sql
@../../current/5.2.1/tables/KC_TBL_AWARD_PAYMENT_SCHEDULE.sql
@../../current/5.2.1/tables/KC_TBL_CONTACT_USAGE.sql
@../../current/5.2.1/tables/KC_TBL_S2S_USER_ATTACHED_FORM_ATT.sql
@../../current/5.2.1/tables/KC_TBL_S2S_USER_ATTACHED_FORM.sql
@../../current/5.2.1/tables/KC_TBL_SUBAWARD_AMOUNT_INFO.sql
@../../current/5.2.1/tables/KC_TBL_SUBAWARD_ATTACHMENTS.sql
@../../current/5.2.1/tables/KC_TBL_SUBAWARD_ATTACHMENT_TYPE.sql
@../../current/5.2.1/tables/KC_TBL_SUBAWARD_FORMS.sql
@../../current/5.2.1/tables/KC_TBL_SUBAWARD_REPORTS.sql
@../../current/5.2.1/tables/KC_TBL_SUBAWARD_REPORT_TYPE.sql
@../../current/5.2.1/tables/KC_TBL_SUBAWARD.sql
@../../current/5.2.1/tables/KC_TBL_SUBAWARD_TEMPLATE_INFO.sql
@../../current/5.2.1/tables/KC_TBL_SUBAWARD_TEMPLATE_TYPE.sql
@../../current/5.2.1/tables/KC_TBL_SUBCONTRACT_COPYRIGHT_TYPE.sql
@../../current/5.2.1/tables/KC_TBL_SUBCONTRACT_COST_TYPE.sql
@../../current/5.2.1/dml/KC_DML_01_KRACOEUS-6792_B000.sql
@../../current/5.2.1/dml/KC_DML_01_KRACOEUS-6805_B000.sql
@../../current/5.2.1/dml/KC_DML_01_KRACOEUS-7035_B000.sql
@../../current/5.2.1/dml/KC_DML_01_KRACOEUS-7036_B000.sql
@../../current/5.2.1/dml/KC_DML_01_KRACOEUS-7037_B000.sql
@../../current/5.2.1/dml/KC_DML_01_KRACOEUS-7038_B000.sql
@../../current/5.2.1/dml/KC_DML_01_KRACOEUS-7039_B000.sql
@../../current/5.2.1/dml/KC_DML_01_KRACOEUS-7040_B000.sql
@../../current/5.2.1/dml/KC_DML_01_KRACOEUS-7042_B000.sql
@../../current/5.2.1/dml/KC_DML_01_KRACOEUS-7072_B000.sql
@../../current/5.2.1/dml/KC_DML_01_KRACOEUS-7158_B000.sql
@../../current/5.2.1/dml/KC_DML_01_KRACOEUS-7172_B000.sql
@../../current/5.2.1/dml/KC_DML_01_KRACOEUS-7206_B000.sql
@../../current/5.2.1/dml/KC_DML_01_KRACOEUS-7223_B000.sql
@../../current/5.2.1/dml/KC_DML_01_KRACOEUS-7228_B000.sql
@../../current/5.2.1/dml/KC_DML_01_KRACOEUS-7229_B000.sql
@../../current/5.2.1/dml/KC_DML_01_KRACOEUS-7234_B000.sql
@../../current/5.2.1/dml/KC_DML_01_KRACOEUS-7236_B000.sql
@../../current/5.2.1/dml/KC_DML_02_KRACOEUS-7042_B000.sql
@../../current/5.2.1/dml/KC_DML_02_KRACOEUS-7206_B000.sql
@../../current/5.2.1/constraints/KC_FK_CONTACT_USAGE.sql
@../../current/5.2.1/constraints/KC_FK_S2S_USER_ATTACHED_FORM_ATT.sql 
@../../current/5.2.1/constraints/KC_FK_S2S_USER_ATTACHED_FORM_FILE.sql 
@../../current/5.2.1/constraints/KC_FK_S2S_USER_ATTACHED_FORM.sql 
@../../current/5.2.1/constraints/KC_FK_SUBAWARD_FORMS.sql
commit;
exit
