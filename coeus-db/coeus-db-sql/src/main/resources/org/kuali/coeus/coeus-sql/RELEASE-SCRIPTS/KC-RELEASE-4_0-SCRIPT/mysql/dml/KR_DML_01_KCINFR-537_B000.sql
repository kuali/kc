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
VALUES ('KC-B','Document','budgetOverviewHelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/budgetoverview.htm','Budget Overview Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-B','Document','budgetSummaryHelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/summary3.htm','Budget Summary Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-B','Document','budgetNonpersonnelHelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/nonpersonnel.htm','Budget Non-personnel Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-B','Document','budgetNonPersonnelOverviewHelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/budgetoverview2.htm','Budget Non-personnel Overview Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-B','Document','budgetNonPersonnelEquipmentHelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/equipment.htm','Budget Non-personnel Equipment Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-B','Document','budgetNonPersonnelTravelHelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/travel.htm','Budget Non-personnel Travel Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-B','Document','budgetNonPersonnelParticipantSupportHelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/participantsupport.htm','Budget Non-personnel Participant Support Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-B','Document','budgetNonPersonnelOtherDirectHelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/otherdirect.htm','Budget Non-personnel Other Direct Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-B','Document','budgetDistributionAndIncomeSummaryHelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/incomesummary.htm','Budget Income Summary Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-B','Document','budgetDistributionAndIncomeDetailsHelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/incomedetails.htm','Budget Income Details Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-B','Document','budgetDirectCostHelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/directcost.htm','Budget Direct Cost  Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-B','Document','budgetActionPrintFormsHelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/printforms.htm','Budget Print Forms Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-B','Document','budgetActionJustificationHelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/budgetjustification.htm','Budget Justification Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-B','Document','budgetActionSubAwardHelpUrl',UUID(),1,'HELP','default.htm?turl=Documents/subawardbudget.htm','Budget SubAward Help','A','KC')
/

DELIMITER ;
