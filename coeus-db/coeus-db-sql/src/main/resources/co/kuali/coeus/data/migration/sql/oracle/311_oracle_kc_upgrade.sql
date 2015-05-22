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

@./kc/bootstrap/V311_038__KC_SEQ_SEQ_CUSTOM_ATTRIBUTE.sql
@./kc/bootstrap/V311_039__KC_SEQ_SEQ_FIN_OBJECT_CODE_MAPPING_ID.sql
@./kc/bootstrap/V311_040__KC_TBL_AWARD.sql
@./kc/bootstrap/V311_041__KC_TBL_AWARD_IDC_RATE.sql
@./kc/bootstrap/V311_042__KC_TBL_BUDGET.sql
@./kc/bootstrap/V311_043__KC_TBL_BUDGET_DETAILS.sql
@./kc/bootstrap/V311_044__KC_TBL_COST_ELEMENT.sql
@./kc/bootstrap/V311_045__KC_TBL_EPS_PROP_PERSON_EXT.sql
@./kc/bootstrap/V311_046__KC_TBL_FIN_IDC_TYPE_CODE.sql
@./kc/bootstrap/V311_047__KC_TBL_FIN_OBJECT_CODE_MAPPING.sql
@./kc/bootstrap/V311_048__KC_TBL_PERSON_EXT_T.sql
@./kc/bootstrap/V311_049__KC_TBL_RATE_CLASS.sql
@./kc/bootstrap/V311_050__KC_TBL_REVIEWER_ATTACHMENTS.sql
@./kc/bootstrap/V311_051__KC_TBL_STATE_CODE.sql
@./kc/bootstrap/V311_052__KC_TBL_USER_TABLES_TEMP.sql
@./kc/bootstrap/V311_053__KC_DML_01_KCINFR-347_B000.sql
@./kc/bootstrap/V311_054__KC_DML_01_KCINFR-348_B000.sql
@./kc/bootstrap/V311_055__KC_DML_01_KCINFR-350_B000.sql
@./kc/bootstrap/V311_056__KC_DML_01_KCIRB-1448_B000.sql
@./kc/bootstrap/V311_057__KC_DML_01_KCIRB-1449_B000.sql
@./kc/bootstrap/V311_058__KC_DML_01_KRACOEUS-4357_B000.sql
@./kc/bootstrap/V311_059__KC_FK_FIN_IDC_TYPE_CODE.sql
@./kc/bootstrap/V311_060__KC_FK_FIN_OBJECT_CODE_MAPPING.sql
@./kc/bootstrap/V311_061__KC_UQ_FIN_OBJECT_CODE_MAPPING.sql
commit;
