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
UPDATE KRCR_PARM_T
SET VAL = 'default.htm?turl=Documents/awardhierarchy.htm',
     PARM_DESC_TXT = 'T&M Award Hierarchy Help'
WHERE PARM_NM = 'awardHierarchyNodeHelpUrl'
 AND CMPNT_CD = 'Document' 
 AND NMSPC_CD = 'KC-T'
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-T',UUID(),'T&M Award Fund Distribution Help','awardFundDistributionHelpUrl','HELP','default.htm?turl=Documents/directfafundsdistribution.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-T',UUID(),'T&M Summary Help','tmSummaryHelpUrl','HELP','default.htm?turl=Documents/summary6.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-T',UUID(),'T&M Dates & Amounts Help','tmDatesAmountsHelpUrl','HELP','default.htm?turl=Documents/datesamounts1.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-T',UUID(),'T&M Award Details Recorded Help','tmAwardDetailsRecordedHelpUrl','HELP','default.htm?turl=Documents/awarddetailsrecorded1.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-T',UUID(),'T&M Investigator Help','tmInvestigatorsHelpUrl','HELP','default.htm?turl=Documents/investigators1.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-T',UUID(),'T&M Action Summary Help','tmActionSummaryHelpUrl','HELP','default.htm?turl=Documents/actionsummary.htm',1)
/
DELIMITER ;
