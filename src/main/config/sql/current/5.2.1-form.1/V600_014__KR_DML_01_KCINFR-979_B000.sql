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

INSERT INTO KRCR_NMSPC_T (NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, APPL_ID)
VALUES ('KC-S2S', sys_guid(), 1, 'Kuali Coeus System to System', 'Y', 'KC');

UPDATE KRCR_PARM_T SET NMSPC_CD = 'KC-S2S', CMPNT_CD = 'All', PARM_TYP_CD = 'CONFG', EVAL_OPRTR_CD = 'A' WHERE NMSPC_CD = 'KC-PD' AND CMPNT_CD = 'Document' AND PARM_NM = 'PI_CITIZENSHIP_FROM_CUSTOM_DATA' AND APPL_ID = 'KC';

UPDATE KRCR_PARM_T SET NMSPC_CD = 'KC-S2S', CMPNT_CD = 'All', PARM_TYP_CD = 'CONFG', EVAL_OPRTR_CD = 'A' WHERE NMSPC_CD = 'KC-GEN' AND CMPNT_CD = 'A' AND PARM_NM = 'ALLOW_PROPOSAL_PERSON_TO_OVERRIDE_KC_PERSON_EXTENDED_ATTRIBUTES' AND APPL_ID = 'KC';

UPDATE KRCR_PARM_T SET NMSPC_CD = 'KC-S2S', CMPNT_CD = 'All', PARM_TYP_CD = 'CONFG', EVAL_OPRTR_CD = 'A' WHERE NMSPC_CD = 'KC-GEN' AND CMPNT_CD = 'A' AND PARM_NM = 'NON_US_CITIZEN_WITH_TEMPORARY_VISA_TYPE_CODE' AND APPL_ID = 'KC';

UPDATE KRCR_PARM_T SET NMSPC_CD = 'KC-S2S', CMPNT_CD = 'All', PARM_TYP_CD = 'CONFG', EVAL_OPRTR_CD = 'A' WHERE NMSPC_CD = 'KC-GEN' AND CMPNT_CD = 'A' AND PARM_NM = 'PERMANENT_RESIDENT_OF_US_TYPE_CODE' AND APPL_ID = 'KC';

UPDATE KRCR_PARM_T SET NMSPC_CD = 'KC-S2S', CMPNT_CD = 'All', PARM_TYP_CD = 'CONFG', EVAL_OPRTR_CD = 'A' WHERE NMSPC_CD = 'KC-GEN' AND CMPNT_CD = 'A' AND PARM_NM = 'US_CITIZEN_OR_NONCITIZEN_NATIONAL_TYPE_CODE' AND APPL_ID = 'KC';

UPDATE KRCR_PARM_T SET NMSPC_CD = 'KC-S2S', CMPNT_CD = 'All', PARM_TYP_CD = 'CONFG', EVAL_OPRTR_CD = 'A' WHERE NMSPC_CD = 'KC-GEN' AND CMPNT_CD = 'A' AND PARM_NM = 'PERMANENT_RESIDENT_OF_US_PENDING' AND APPL_ID = 'KC';