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
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-SUBAWARD',UUID(),'Subaward Home Help','subAwardHomeHelpUrl','HELP','default.htm?turl=Documents/subaward2.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-SUBAWARD',UUID(),'Subaward Actions Help','subAwardActionsHelpUrl','HELP','default.htm?turl=Documents/subawardactions.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-SUBAWARD',UUID(),'Subaward Financial Help','subAwardFinancialHelpUrl','HELP','default.htm?turl=Documents/financial1.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-SUBAWARD',UUID(),'Subaward Custom Data Help','subAwardCustomDataHelpUrl','HELP','default.htm?turl=Documents/customdata11.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-SUBAWARD',UUID(),'Subaward Funding Source Help','subAwardFundingSourceHelpUrl','HELP','default.htm?turl=Documents/fundingsource1.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-SUBAWARD',UUID(),'Subaward Contact Source Help','subAwardContactHelpUrl','HELP','default.htm?turl=Documents/contacts2.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-SUBAWARD',UUID(),'Subaward Closeout Help','subAwardCloseOutHelpUrl','HELP','default.htm?turl=Documents/closeouts.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-SUBAWARD',UUID(),'Subaward History of Changes Help','subAwardHistoryOfChangesHelpUrl','HELP','default.htm?turl=Documents/historyofchanges.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-SUBAWARD',UUID(),'Subaward Invoice Help','subAwardInvoicesHelpUrl','HELP','default.htm?turl=Documents/invoices.htm',1)
/
DELIMITER ;
