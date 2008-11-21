INSERT INTO SH_PARM_T (SH_PARM_NMSPC_CD,SH_PARM_DTL_TYP_CD,SH_PARM_NM,SH_PARM_TYP_CD,SH_PARM_TXT,SH_PARM_DESC,SH_PARM_CONS_CD,ACTIVE_IND) 
VALUES ('KRA-PD','D','proposalDevelopmentBudgetVersionsHelp','HELP','default.htm?turl=WordDocuments%2Fbudgetversionspage2.htm','Budget Page Help','A','Y');

INSERT INTO SH_PARM_T (SH_PARM_NMSPC_CD,SH_PARM_DTL_TYP_CD,SH_PARM_NM,SH_PARM_TYP_CD,SH_PARM_TXT,SH_PARM_DESC,SH_PARM_CONS_CD,ACTIVE_IND) 
VALUES ('KRA-PD','D','proposalUserHelpUrl','HELP','default.htm?turl=WordDocuments%2Fuserstab.htm','Proposal Development Page Help','A','Y');

UPDATE SH_PARM_T SET SH_PARM_TXT = 'default.htm?turl=WordDocuments%2Fbudgetversionspage1.htm' 
WHERE SH_PARM_NM = 'budgetVersionsHelp' AND SH_PARM_NMSPC_CD = 'KRA-B';

UPDATE SH_PARM_T SET SH_PARM_NM = 'budgetParametersHelp' , SH_PARM_TXT = 'default.htm?turl=WordDocuments%2Fparameterspage.htm' 
WHERE SH_PARM_NM = 'budgetSummaryHelp' AND SH_PARM_NMSPC_CD = 'KRA-B';

UPDATE SH_PARM_T SET SH_PARM_NM = 'budgetSummaryHelp' , SH_PARM_TXT = 'default.htm?turl=WordDocuments%2Fsummarypage.htm' 
WHERE SH_PARM_NM = 'budgetTotalsHelp' AND SH_PARM_NMSPC_CD = 'KRA-B';

UPDATE SH_PARM_T SET SH_PARM_TXT = 'default.htm?turl=WordDocuments%2Fpersonnelpage.htm' 
WHERE SH_PARM_NM = 'budgetPersonnelHelp' AND SH_PARM_NMSPC_CD = 'KRA-B';

UPDATE SH_PARM_T SET SH_PARM_NM = 'budgetNonPersonnelHelp' , SH_PARM_TXT = 'default.htm?turl=WordDocuments%2Fnonpersonnelpage.htm' 
WHERE SH_PARM_NM = 'budgetExpensesHelp' AND SH_PARM_NMSPC_CD = 'KRA-B';

UPDATE SH_PARM_T SET SH_PARM_TXT = 'default.htm?turl=WordDocuments%2Fdistributionincomepage.htm' 
WHERE SH_PARM_NM = 'budgetDistributionAndIncomeHelp' AND SH_PARM_NMSPC_CD = 'KRA-B';

UPDATE SH_PARM_T SET SH_PARM_DESC = 'Budget Parameters Help' 
WHERE SH_PARM_NMSPC_CD = 'KRA-B' AND SH_PARM_DTL_TYP_CD = 'D' AND SH_PARM_NM = 'proposalBudgetStatusHelpUrl';

UPDATE SH_PARM_T SET SH_PARM_TXT = 'default.htm?turl=WordDocuments%2Fprojectpersonnel.htm' 
WHERE SH_PARM_NMSPC_CD = 'KRA-B' AND SH_PARM_DTL_TYP_CD = 'D' AND SH_PARM_NM = 'budgetPersonHelpUrl';

UPDATE SH_PARM_T SET SH_PARM_NM = 'budgetPersonnelDetailsHelpUrl', SH_PARM_TXT = 'default.htm?turl=WordDocuments%2Fpersonneldetailtab.htm'  
WHERE SH_PARM_NMSPC_CD = 'KRA-B' AND SH_PARM_DTL_TYP_CD = 'D' AND SH_PARM_NM = 'budgetbudgetPersonnelDetailsHelpUrl';

UPDATE SH_PARM_T SET SH_PARM_TXT = 'default.htm?turl=WordDocuments%2Fdirectcosttab.htm' 
WHERE SH_PARM_NMSPC_CD = 'KRA-B' AND SH_PARM_DTL_TYP_CD = 'D' AND SH_PARM_NM = 'budgetModularHelpUrl';

UPDATE SH_PARM_T SET SH_PARM_TXT = 'default.htm?turl=WordDocuments%2Fprojectpersonneltab.htm' 
WHERE SH_PARM_NM = 'budgetPersonHelpUrl' AND SH_PARM_NMSPC_CD = 'KRA-B' AND SH_PARM_DTL_TYP_CD = 'D';

UPDATE SH_PARM_T SET SH_PARM_TXT = 'default.htm?turl=WordDocuments%2Fnonpersonnelpage.htm' 
WHERE SH_PARM_NM = 'budgetLineItemHelpUrl' AND SH_PARM_NMSPC_CD = 'KRA-B' AND SH_PARM_DTL_TYP_CD = 'D';

UPDATE SH_PARM_T SET SH_PARM_TXT = 'default.htm?turl=WordDocuments%2Forganizationlocationtab.htm' 
WHERE SH_PARM_NM = 'proposalDevelopmentOrganizationHelpUrl' AND SH_PARM_NMSPC_CD = 'KRA-PD' AND SH_PARM_DTL_TYP_CD = 'D';

UPDATE SH_PARM_T SET SH_PARM_TXT = 'default.htm?turl=WordDocuments%2Ftabbedpagesectionexamples.htm' 
WHERE SH_PARM_NM = 'proposalDevelopmentCustomAttributeHelpUrl' AND SH_PARM_NMSPC_CD = 'KRA-PD' AND SH_PARM_DTL_TYP_CD = 'D'; 

UPDATE SH_PARM_T SET SH_PARM_TXT = 'default.htm?turl=WordDocuments%2Fabstractsandattachmentspage.htm' 
WHERE SH_PARM_NM = 'proposalDevelopmentNarrativeHelpUrl' AND SH_PARM_NMSPC_CD = 'KRA-PD' AND SH_PARM_DTL_TYP_CD = 'D'; 

UPDATE SH_PARM_T SET SH_PARM_TXT = 'default.htm?turl=WordDocuments%2Fpermissionspage.htm' 
WHERE SH_PARM_NM = 'proposalDevelopmentPermissionsHelp' AND SH_PARM_NMSPC_CD = 'KRA-PD' AND SH_PARM_DTL_TYP_CD = 'D'; 

UPDATE SH_PARM_T SET SH_PARM_TXT = 'default.htm?turl=WordDocuments%2Fcopytonewdocumenttab.htm' 
WHERE SH_PARM_NM = 'proposalDevelopmentCopyCriteriaHelpUrl' AND SH_PARM_NMSPC_CD = 'KRA-PD' AND SH_PARM_DTL_TYP_CD = 'D'; 



