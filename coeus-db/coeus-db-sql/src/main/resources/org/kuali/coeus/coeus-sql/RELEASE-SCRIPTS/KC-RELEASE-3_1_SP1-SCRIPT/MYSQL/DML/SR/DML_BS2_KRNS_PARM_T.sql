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
-- Staging data
INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID) 
VALUES ('KC','KC-AWARD','D','AWARD_ACTIVE_STATUS_CODES',1,'CONFG','1','Comma delimited list of award status codes considered active.','A',UUID())
/
INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID) 
VALUES ('KC','KC-AWARD','D','AWARD_COST_SHARING',1,'CONFG','009906','Numeric code from the Sponsor table that defines an award as being for Cost Sharing for sync descendants.','A',UUID())
/
INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID) 
VALUES ('KC','KC-AWARD','D','AWARD_FABRICATED_EQUIPMENT',1,'CONFG','2','Numeric code from Account Type table that defines an award as being for Fabricated Equipment for sync descendants.','A',UUID())
/
-- INSERT INTO KRCR_PARM_T (APPL_ID,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
--  VALUES ('KC','A','KC-B',UUID(),'Broad F','D','BROAD_F_AND_A','CONFG','421502',1);

-- INSERT INTO KRCR_PARM_T (APPL_ID,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
--  VALUES ('KC','A','KC-B',UUID(),'Subcontract F greater than 25K','D','SUBCONTRACTOR_F_AND_A_GT_25K','CONFG','420630',1);

-- INSERT INTO KRCR_PARM_T (APPL_ID,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
--  VALUES ('KC','A','KC-B',UUID(),'Subcontract F less than 25K','D','SUBCONTRACTOR_F_AND_A_LT_25K','CONFG','420610',1);

UPDATE KRCR_PARM_T T SET T.CMPNT_CD = 'Document' where T.CMPNT_CD = 'D'
/
UPDATE KRCR_PARM_T T SET T.CMPNT_CD = 'All' where T.CMPNT_CD = 'A'
/
UPDATE KRCR_PARM_T T SET T.CMPNT_CD = 'Lookup' where T.CMPNT_CD = 'L'
/
UPDATE KRCR_PARM_T T SET T.APPL_ID = 'KC' WHERE NMSPC_CD LIKE 'KC%'
/
update KRCR_PARM_T
set VAL = 'Modify Protocol:KC-PROTOCOL;Maintain Questionnaire Usage:KC-PD;Maintain Questionnaire Usage:KC-PROTOCOL;Edit Institutional Proposal:KC-IP'
where nmspc_cd = 'KC-QUESTIONNAIRE' and parm_nm = 'associateModuleQuestionnairePermission' and CMPNT_CD = 'P'
/
commit
/

DELIMITER ;
