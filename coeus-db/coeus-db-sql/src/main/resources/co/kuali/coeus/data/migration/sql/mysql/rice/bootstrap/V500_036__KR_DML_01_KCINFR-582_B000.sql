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
VALUES ('KC-M','Document','mailTypeMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/mailtype.htm','Maintenance Mail Type Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','objectCodeMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/objectcode.htm','Maintenance Object Code Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','ipReviewActivityTypeMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/ipreviewactivitytype.htm','Maintenance IP Review Activity Type Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','ipReviewRequirementTypeMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/ipreviewrequirementtype.htm','Maintenance IP Review Requirement Type Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','ipReviewResultTypeMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/ipreviewresulttype.htm','Maintenance IP Review Result Type Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','jobCodeMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/jobcode1.htm','Maintenance Job Code Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','locationTypeMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/proposallocationtype.htm','Maintenance Location Type Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','personDocumentTypeMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/persondocumenttype1.htm','Maintenance Location Type Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','proposalDevEditableFieldMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/proposaldeveditablecolumns.htm','Maintenance Proposal Dev Editable Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','proposalDevelopmentStatusMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/proposaldevelopmentstatus.htm','Maintenance Proposal Dev Status Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','proposalRateClassMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/rateclass1.htm','Maintenance Rate Class Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','proposalRateTypeMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/ratetype1.htm','Maintenance Rate Type Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','trainingStipendRateMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/trainingstipendrate1.htm','Maintenance Training Stipend Rate Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','proposalValidCalTypeMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/validcalculationtype1.htm','Maintenance Valid Calculation Type Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','proposalValidCeRateTypeMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/validcostelementratetype1.htm','Maintenance Valid Cost Element Type Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','institutionalProposalStatusMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/institutionalproposalstatus.htm','Maintenance Institutional Proposal Status Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','proposalLogStatusMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/proposallogstatus.htm','Maintenance Proposal Log Status Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','proposalLogTypeMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/proposallogtype.htm','Maintenance Proposal Log Type Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','toBeNamedPersonMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/tobenamedperson.htm','Maintenance To Be Named Person Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','accountTypeMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/accounttype.htm','Maintenance Account Type Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','attachmentTypeMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/attachmenttype.htm','Maintenance Attachment Type Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','awardStatusMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/awardstatus.htm','Maintenance Award Status Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','awardTypeMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/awardtype.htm','Maintenance Award Type Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','basisOfPaymentMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/basisofpayment.htm','Maintenance Basis of Payment Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','validAwardBasisOfPaymentMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/validawardbasisofpayment.htm','Maintenance Basis of Payment Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','validClassReportFrequencyMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/validclassreportfrequency.htm','Maintenance Valid Class Report Frequency Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','validFrequencyBaseMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/validfrequencybase.htm','Maintenance Valid Frequency Base Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','validBasisAndMethodOfPaymentMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/validbasisandmethodofpayment.htm','Maintenance Valid Basis and Method of Payment Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','contactTypeMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/contacttype.htm','Maintenance Contact Type Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','frequencyMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/frequency.htm','Maintenance Frequency Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','frequencyBaseMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/frequencybase.htm','Maintenance Frequency Base Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','methodOfPaymentMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/methodofpayment.htm','Maintenance Method of Payment Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','reportMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/report.htm','Maintenance Report Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','reportClassMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/reportclass.htm','Maintenance Report Class Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','reportStatusMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/reportstatus.htm','Maintenance Report Status Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','sponsorTemplateMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/sponsortemplate1.htm','Maintenance Sponsor Template Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','sponsorTermsMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/sponsorterms.htm','Maintenance Sponsor Terms Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','awardTransactionTypeMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/awardtransactiontype.htm','Maintenance Award Transaction Type Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','negotiationActivityMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/negotiationactivitytype.htm','Maintenance Negotiation Activity Type Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','negotiationAgreementMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/negotiationagreementtype.htm','Maintenance Negotiation Agreement Type Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','negotiationAssociationMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/negotiationassociationtype.htm','Maintenance Negotiation Association Type Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','negotiationLocationMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/negotiationlocation.htm','Maintenance Negotiation Location Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','negotiationStatusMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/negotiationstatus.htm','Maintenance Negotiation Status Type Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','closeOutTypeMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/closeouttype1.htm','Maintenance Closeout Type Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','subAwardApprovalTypeMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/subawardapprovaltype1.htm','Maintenance Subaward Approval Type Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','subAwardFormsMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/subawardforms1.htm','Maintenance Subaward Forms Help','A','KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) 
VALUES ('KC-M','Document','subAwardStatusMaintenanceHelp',UUID(),1,'HELP','default.htm?turl=Documents/subawardstatus2.htm','Maintenance Subaward Status Help','A','KC')
/
UPDATE KRCR_PARM_T
SET VAL = 'default.htm?turl=Documents/narrativetypes.htm'
WHERE NMSPC_CD = 'KC-M'
  AND CMPNT_CD = 'Document'
  AND PARM_NM = 'narrativeTypeMaintenanceHelp'
  AND VAL = 'default.htm?turl=Documents/narrativetype.htm'
/



DELIMITER ;
