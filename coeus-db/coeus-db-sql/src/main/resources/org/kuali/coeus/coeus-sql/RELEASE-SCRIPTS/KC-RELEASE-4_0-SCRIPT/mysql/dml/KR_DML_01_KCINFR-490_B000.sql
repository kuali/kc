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
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-PD','Document','proposalDevelopmentperformancesitelocationsHelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/performancesitelocations.htm','PD Performance site locations Document Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-PD','Document','proposalDevelopmentpersonnelattachmentsHelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/personnelattachments.htm','PD Personnel Attachment Document Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-PD','Document','proposalDevelopmentinternalattachmentsHelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/internalattachments.htm','PD Internal Attachment Document Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-PD','Document','proposalDevelopmentnotesHelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/notes.htm','PD Notes Document Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-PD','Document','proposalHierarchyHelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/proposalhierarchy.htm','Proposal Hierarchy Document Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-PD','Document','proposalDevelopmentDataValidation1HelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/datavalidation1.htm','PD Data Validation Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-PD','Document','proposalDevelopmentPrintFormsHelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/print.htm','PD Print Forms Help','A','KC')
/
UPDATE KRCR_PARM_T
SET VAL = 'default.htm?turl=Documents/copytonewdocument.htm',
     PARM_DESC_TXT = 'Proposal Copy Criteria Help'
WHERE PARM_NM = 'proposalDevelopmentCopyCriteriaHelpUrl'
 AND CMPNT_CD = 'Document' 
 AND NMSPC_CD = 'KC-PD'
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-PD','Document','proposalDevelopmentMedusaHelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/medusa.htm','PD medusa Document Help','A','KC')
/
DELIMITER ;
