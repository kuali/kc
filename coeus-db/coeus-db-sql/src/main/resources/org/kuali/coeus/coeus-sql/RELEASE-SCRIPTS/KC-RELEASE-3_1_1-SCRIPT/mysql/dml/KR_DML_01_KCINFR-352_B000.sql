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

DELIMITER /

UPDATE KREW_DOC_HDR_T SET DOC_TYP_ID = (SELECT DOC_TYP_ID FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'QuestionnaireMaintenanceDocument' AND CUR_IND = 1), INITR_PRNCPL_ID = 'admin', RTE_PRNCPL_ID = 'admin' WHERE TTL = 'NSF s2s form supporting questions'
/
UPDATE KREW_DOC_HDR_T SET DOC_TYP_ID = (SELECT DOC_TYP_ID FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'QuestionnaireMaintenanceDocument' AND CUR_IND = 1), INITR_PRNCPL_ID = 'admin', RTE_PRNCPL_ID = 'admin' WHERE TTL = 'PHS Fellowship Supplemental Form V1-0 & 1-1'
/
UPDATE KREW_DOC_HDR_T SET DOC_TYP_ID = (SELECT DOC_TYP_ID FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'QuestionnaireMaintenanceDocument' AND CUR_IND = 1), INITR_PRNCPL_ID = 'admin', RTE_PRNCPL_ID = 'admin' WHERE TTL = 'PHS398 Training Budget Form version 1-0'
/
UPDATE KREW_DOC_HDR_T SET DOC_TYP_ID = (SELECT DOC_TYP_ID FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'QuestionnaireMaintenanceDocument' AND CUR_IND = 1), INITR_PRNCPL_ID = 'admin', RTE_PRNCPL_ID = 'admin' WHERE TTL = 'PHS Fellowship Supplemental Form V1-2'
/
UPDATE KREW_DOC_HDR_T SET DOC_TYP_ID = (SELECT DOC_TYP_ID FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'QuestionnaireMaintenanceDocument' AND CUR_IND = 1), INITR_PRNCPL_ID = 'admin', RTE_PRNCPL_ID = 'admin' WHERE TTL = 'Proposal Person Certification'
/

UPDATE KREW_ACTN_TKN_T SET PRNCPL_ID = 'admin' WHERE DOC_HDR_ID = (SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'NSF s2s form supporting questions')
/
UPDATE KREW_ACTN_TKN_T SET PRNCPL_ID = 'admin' WHERE DOC_HDR_ID = (SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'PHS Fellowship Supplemental Form V1-0 & 1-1')
/
UPDATE KREW_ACTN_TKN_T SET PRNCPL_ID = 'admin' WHERE DOC_HDR_ID = (SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'PHS398 Training Budget Form version 1-0')
/
UPDATE KREW_ACTN_TKN_T SET PRNCPL_ID = 'admin' WHERE DOC_HDR_ID = (SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'PHS Fellowship Supplemental Form V1-2')
/
UPDATE KREW_ACTN_TKN_T SET PRNCPL_ID = 'admin' WHERE DOC_HDR_ID = (SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'Proposal Person Certification')
/

DELIMITER ;
