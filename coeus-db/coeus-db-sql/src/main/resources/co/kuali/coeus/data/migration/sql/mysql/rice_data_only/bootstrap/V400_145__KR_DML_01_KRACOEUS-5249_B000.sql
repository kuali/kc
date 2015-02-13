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
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetBudgetCategoryTypePersonnel',UUID(),1,'CONFG','P','Code corresponding to Proposal Type budget category type personnel','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetFilterCategoryTypePersonnel',UUID(),1,'CONFG','P','Code corresponding to Proposal Type filter category type personnel','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetRateClassTypeSalariesMs',UUID(),1,'CONFG','L','Code corresponding to Proposal Type Rate Class Type Salaries Ms','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetRateClassTypeLabAllocationSalaries',UUID(),1,'CONFG','Y','Code corresponding to Proposal Type Rate Class Type Lab Allocation Salaries','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetRateClassTypeEmployeeBenefits',UUID(),1,'CONFG','E','Code corresponding to Proposal Type Rate Class Type Employee Benefits','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetRateClassTypeVacation',UUID(),1,'CONFG','V','Code corresponding to Proposal Type Rate Class Type Vacation','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetRateTypeAdministrativeSalaries',UUID(),1,'CONFG','2','Code corresponding to Proposal Type Rate Type Administrative Salaries','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetRateTypeSupportStaffSalaries',UUID(),1,'CONFG','3','Code corresponding to Proposal Type Rate Type Support Staff Salaries','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetRateClassCodeEmployeeBenefits',UUID(),1,'CONFG','5','Code corresponding to Proposal Type Rate Class Code Employee Benefits','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetRateClassCodeVacation',UUID(),1,'CONFG','8','Code corresponding to Proposal Type Rate Class Code Vacation','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetPeriodTypeAcademicMonths',UUID(),1,'CONFG','2','Code corresponding to Proposal Type Period Type Academic Months','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetPeriodTypeCalendarMonths',UUID(),1,'CONFG','3','Code corresponding to Proposal Type Period Type Calendar Months','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetPeriodTypeSummerMonths',UUID(),1,'CONFG','4','Code corresponding to Proposal Type Period Type Summer Months','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetPeriodTypeCycleMonths',UUID(),1,'CONFG','CY','Code corresponding to Proposal Type Period Type Cycle Months','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetTargetCategoryCodeEquipmentCost',UUID(),1,'CONFG','42','Code corresponding to Proposal Type Target Category Code Equipment Cost','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetCategory01Graduates',UUID(),1,'CONFG','01-Graduates','Code corresponding to Proposal Type Category 01 Graduates','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetCategory01Postdocs',UUID(),1,'CONFG','01-PostDocs','Code corresponding to Proposal Type Category 01 Postdocs','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetCategory01Undergrads',UUID(),1,'CONFG','01-Undergrads','Code corresponding to Proposal Type Category 01 Undergrads','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetCategory01Secretarial',UUID(),1,'CONFG','01-Secretarial','Code corresponding to Proposal Type Category 01 Secretarial','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetCategory01Other',UUID(),1,'CONFG','01-Other','Code corresponding to Proposal Type Category 01 Other','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetCategory01OtherProfs',UUID(),1,'CONFG','01-Other Profs','Code corresponding to Proposal Type Category 01 Other Profs','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetMaterialsAndSuppliesCategory',UUID(),1,'CONFG','Materials and Supplies','Code corresponding to Proposal Type Materials And Supplies Category','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetConsultantCostsCategory',UUID(),1,'CONFG','Consultant Costs','Code corresponding to Proposal Type Consultant Costs Category','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetPublicationCostsCategory',UUID(),1,'CONFG','Publication Costs','Code corresponding to Proposal Type Publication Costs Category','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetComputerServicesCategory',UUID(),1,'CONFG','Computer Services','Code corresponding to Proposal Type Computer Services Category','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetAlterationsCategory',UUID(),1,'CONFG','Alterations','Code corresponding to Proposal Type Alterations Category','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetSubcontractCategory',UUID(),1,'CONFG','Subcontract','Code corresponding to Proposal Type Subcontract Category','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetEquipmentRentalCategory',UUID(),1,'CONFG','Equipment Rental','Code corresponding to Proposal Type Equipment Rental Category','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetDomesticTravelCategory',UUID(),1,'CONFG','Domestic Travel','Code corresponding to Proposal Type Domestic Travel Category','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetForeignTravelCategory',UUID(),1,'CONFG','Foreign Travel','Code corresponding to Proposal Type Foreign Travel Category','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetParticipantStipendsCategory',UUID(),1,'CONFG','Participant Stipends','Code corresponding to Proposal Type Participant Stipends Category','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetParticipantTravelCategory',UUID(),1,'CONFG','Participant Travel','Code corresponding to Proposal Type Participant Travel Category','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetParticipantTutionCategory',UUID(),1,'CONFG','Participant Tuition','Code corresponding to Proposal Type Participant Tution Category','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetParticipantSubsistenceCategory',UUID(),1,'CONFG','Participant Subsistence','Code corresponding to Proposal Type Participant Subsistence Category','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetParticipantOtherCategory',UUID(),1,'CONFG','Participant Other','Code corresponding to Proposal Type Participant Other Category','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetOtherDirectCostsCategory',UUID(),1,'CONFG','Other Direct Costs','Code corresponding to Proposal Type OtherDirect Costs Category','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetAppointmentTypeSumEmployee',UUID(),1,'CONFG','2','Code corresponding to Proposal Type Appointment Type Sum Employee','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','s2sBudgetAppointmentTypeTmpEmployee',UUID(),1,'CONFG','1','Code corresponding to Proposal Type Appointment Type Tmp Employee','A','KC')
/
commit
/
DELIMITER ;
