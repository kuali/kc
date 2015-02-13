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
SET VAL = 'default.htm?turl=Documents/keywords2.htm',
     PARM_DESC_TXT = 'Award Keywords Help'
WHERE PARM_NM = 'awardKeywordHelpUrl'
 AND CMPNT_CD = 'Document' 
 AND NMSPC_CD = 'KC-AWARD'
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-AWARD',UUID(),'Award Unit Contact Help','awardUnitContactsHelpUrl','HELP','default.htm?turl=Documents/unitcontacts1.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-AWARD',UUID(),'Award Sponsor Contact Help','awardSponsorContactsHelpUrl','HELP','default.htm?turl=Documents/sponsorcontacts.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-AWARD',UUID(),'Award Central Admin Help','awardCentralAdminContactsHelpUrl','HELP','default.htm?turl=Documents/centraladministrationcontacts1.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-AWARD',UUID(),'Award Budget Limits Help','awardBudgetLimitsHelpUrl','HELP','default.htm?turl=Documents/budgetlimits.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-AWARD',UUID(),'Award Budget Overview Help','awardBudgetOverviewHelpUrl','HELP','default.htm?turl=Documents/budgetoverview3.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-AWARD',UUID(),'Award Payments and Invoices Help','awardPaymentsandInvoicesHelpUrl','HELP','default.htm?turl=Documents/paymentsinvoices.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-AWARD',UUID(),'Award Reports Help','awardReportsHelpUrl','HELP','default.htm?turl=Documents/reports.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-AWARD',UUID(),'Award Special Approval Help','awardSpecialApprovalHelpUrl','HELP','default.htm?turl=Documents/specialapproval.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-AWARD',UUID(),'Award Closeout Help','awardCloseOutHelpUrl','HELP','default.htm?turl=Documents/closeout.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-AWARD',UUID(),'Award Special Review Help','awardSpecialReviewHelpUrl','HELP','default.htm?turl=Documents/specialreview4.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-AWARD',UUID(),'Award Actions Help','awardActionsHelpUrl','HELP','default.htm?turl=Documents/awardactions.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-AWARD',UUID(),'Award Data Validation Help','awardDataValidationHelpUrl','HELP','default.htm?turl=Documents/datavalidation4.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-AWARD',UUID(),'Award Medusa Help','awardMedusaHelpUrl','HELP','default.htm?turl=Documents/medusa3.htm',1)
/

DELIMITER ;
