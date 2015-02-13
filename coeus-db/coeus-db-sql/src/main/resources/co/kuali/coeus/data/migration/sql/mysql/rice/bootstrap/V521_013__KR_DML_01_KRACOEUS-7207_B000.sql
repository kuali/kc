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

UPDATE KRCR_PARM_T SET VAL = 'default.htm?turl=Documents/summary5.htm'
WHERE APPL_ID = 'KC' AND CMPNT_CD = 'Document' AND NMSPC_CD = 'KC-T' AND PARM_NM = 'tmSummaryHelpUrl' AND PARM_TYP_CD = 'HELP'
/

UPDATE KRCR_PARM_T SET VAL = 'default.htm?turl=Documents/datesamounts.htm'
WHERE APPL_ID = 'KC' AND CMPNT_CD = 'Document' AND NMSPC_CD = 'KC-T' AND PARM_NM = 'tmDatesAmountsHelpUrl' AND PARM_TYP_CD = 'HELP'
/

UPDATE KRCR_PARM_T SET VAL = 'default.htm?turl=Documents/awarddetailsrecorded.htm'
WHERE APPL_ID = 'KC' AND CMPNT_CD = 'Document' AND NMSPC_CD = 'KC-T' AND PARM_NM = 'tmAwardDetailsRecordedHelpUrl' AND PARM_TYP_CD = 'HELP'
/

UPDATE KRCR_PARM_T SET VAL = 'default.htm?turl=Documents/investigators.htm'
WHERE APPL_ID = 'KC' AND CMPNT_CD = 'Document' AND NMSPC_CD = 'KC-T' AND PARM_NM = 'tmInvestigatorsHelpUrl' AND PARM_TYP_CD = 'HELP'
/

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-T', 'Document', 'tmHistoryHelpUrl', UUID(), 1, 'HELP', 'default.htm?turl=Documents/history2.htm', 'T&M History Help', 'A', 'KC')
/

DELIMITER ;
