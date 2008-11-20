insert into sh_parm_nmspc_t(SH_PARM_NMSPC_CD,SH_PARM_NMSPC_NM,ACTIVE_IND) values('KRA-PD','Proposal Development','Y');
insert into sh_parm_nmspc_t(SH_PARM_NMSPC_CD,SH_PARM_NMSPC_NM,ACTIVE_IND) values('KRA-B','Budget','Y');

insert into sh_parm_dtl_typ_t(SH_PARM_NMSPC_CD,SH_PARM_DTL_TYP_CD,SH_PARM_DTL_TYP_NM,ACTIVE_IND) values ('KRA-PD','D','Document','Y');
insert into sh_parm_dtl_typ_t(SH_PARM_NMSPC_CD,SH_PARM_DTL_TYP_CD,SH_PARM_DTL_TYP_NM,ACTIVE_IND) values ('KRA-PD','L','Lookup','Y');
insert into sh_parm_dtl_typ_t(SH_PARM_NMSPC_CD,SH_PARM_DTL_TYP_CD,SH_PARM_DTL_TYP_NM,ACTIVE_IND) values ('KRA-PD','A','All','Y');

insert into sh_parm_typ_t (sh_parm_typ_cd, sh_parm_typ_nm, active_ind) values ('HELP','Help','Y');
insert into SH_PARM_TYP_T ("SH_PARM_TYP_CD","VER_NBR","SH_PARM_TYP_NM","ACTIVE_IND") values ('CONFG', 0,'Config',1);
insert into SH_PARM_TYP_T ("SH_PARM_TYP_CD","VER_NBR","SH_PARM_TYP_NM","ACTIVE_IND") values ('AUTH', 0,'Authorization',1);
insert into SH_PARM_NMSPC_T ("SH_PARM_NMSPC_CD","VER_NBR","SH_PARM_NMSPC_NM","ACTIVE_IND") values ('KR-NS', 0,'Kuali Rice',1);

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','L','multipleValueLookupResultsPerPage','CONFG','200','Limit results returned for lookup - multiple results','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','proposaldevelopment.creditsplit.enabled','CONFG','Y','Determines whether the Credit Split is turned on for proposal','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','proposaldevelopment.personrole.readonly.roles','CONFG','KP','Proposal Person Role Id list for roles that are read-only','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','proposaldevelopment.personrole.nonnih.kp','CONFG','Key Person','Description of key person for Non-NIH Proposals','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','proposaldevelopment.personrole.nonnih.pi','CONFG','Proposal Investigator Contact','Description of principal investigator contact for Non-NIH Proposals','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','proposaldevelopment.personrole.nonnih.coi','CONFG','Proposal Investigator Multiple','Description of principal investigator multiple for Non-NIH Proposals','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','proposaldevelopment.proposaltype.new','CONFG','1','ProposalTypeCode of NEW','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','proposaldevelopment.proposaltype.renewal','CONFG','3','ProposalTypeCode of RENEWAL','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','proposaldevelopment.proposaltype.revision','CONFG','5','ProposalTypeCode of REVISION','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','proposaldevelopment.proposaltype.continuation','CONFG','2','ProposalTypeCode of CONTINUATION','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','proposaldevelopment.displayKeywordPanel','CONFG','TRUE','Display Proposal Keyword panel','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','proposaldevelopment.personrole.kp','CONFG','Key Person','Description of key person for NIH Proposals','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','proposaldevelopment.personrole.coi','CONFG','Co-Investigator','Description of co-investigator for NIH Proposals','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','proposaldevelopment.personrole.pi','CONFG','Principal Investigator','Description of principal investigator for NIH Proposals','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','proposalNarrativeTypeGroup','CONFG','P','Define Narrative Type Group for Proposal Attachments','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','instituteNarrativeTypeGroup','CONFG','O','Define Narrative Type Group for Institute Attachments','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','deliveryInfoDisplayIndicator','CONFG','Y','Flag to display delivery infor panel','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-B','D','budgetPersonDefaultJobCode','CONFG','0','The Job Code a new Budget Person should default to','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-B','D','budgetPersonDefaultAppointmentType','CONFG','1','The Appointment Type a new Budget Person should default to','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-B','D','budgetPersonDefaultCalculationBase','CONFG','0','The Calculation Base a new Budget Person should default to','A','Y');

-- Nervous system params we need
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KR-NS','Lookup','MULTIPLE_VALUE_RESULTS_PER_PAGE','CONFG','200','Limit results returned for lookup - multiple results','A','Y');
INSERT INTO sh_parm_t ("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","SH_PARM_TXT","SH_PARM_CONS_CD","SH_PARM_DESC","SH_PARM_TYP_CD","WRKGRP_NM") VALUES ('KR-NS','All','ENABLE_FIELD_LEVEL_HELP_IND','N','A','Indicates whether field level help links are enabled on lookup pages and documents.','CONFG','KUALI_FMSOPS');
INSERT INTO sh_parm_t ("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","SH_PARM_TXT","SH_PARM_CONS_CD","SH_PARM_DESC","SH_PARM_TYP_CD","WRKGRP_NM") VALUES ('KR-NS','Document','SUPERVISOR_GROUP','KUALI_ROLE_SUPERVISOR','A','Workgroup which can perform almost any function within Kuali.','AUTH','KUALI_FMSOPS');
INSERT INTO sh_parm_t ("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","SH_PARM_TXT","SH_PARM_CONS_CD","SH_PARM_DESC","SH_PARM_TYP_CD","WRKGRP_NM") VALUES ('KR-NS','All','CHECK_ENCRYPTION_SERVICE_OVERRIDE_IND','Y','A','Flag for enabling/disabling the demonstration encryption check.','CONFG','KUALI_FMSOPS');
INSERT INTO sh_parm_t ("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","SH_PARM_TXT","SH_PARM_CONS_CD","SH_PARM_DESC","SH_PARM_TYP_CD","WRKGRP_NM") VALUES ('KR-NS','Document','DEFAULT_CAN_PERFORM_ROUTE_REPORT_IND','N','A','If Y, the Route Report button will be displayed on the document actions bar if the document is using the default DocumentAuthorizerBase.getDocumentActionFlags to set the canPerformRouteReport property of the returned DocumentActionFlags instance.','CONFG','KUALI_FMSOPS');
INSERT INTO sh_parm_t ("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","SH_PARM_TXT","SH_PARM_CONS_CD","SH_PARM_DESC","SH_PARM_TYP_CD","WRKGRP_NM") VALUES ('KR-NS','Lookup','RESULTS_DEFAULT_MAX_COLUMN_LENGTH','70','A','If a maxLength attribute has not been set on a lookup result field in the data dictionary, then the result column''s max length will be the value of this parameter. Set this parameter to 0 for an unlimited default length or a positive value (i.e. greater than 0) for a finite max length.','CONFG','KUALI_FMSOPS');
INSERT INTO sh_parm_t ("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","SH_PARM_TXT","SH_PARM_CONS_CD","SH_PARM_DESC","SH_PARM_TYP_CD","WRKGRP_NM") VALUES ('KR-NS','Document','MAX_FILE_SIZE_ATTACHMENT','5M','A','Maximum attachment upload size for the application. Used by KualiDocumentFormBase. Must be an integer, optionally followed by ''K'', ''M'', or ''G''.','CONFG','KUALI_FMSOPS');
INSERT INTO SH_PARM_T(SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_NM, OBJ_ID, VER_NBR, SH_PARM_TYP_CD, SH_PARM_TXT, SH_PARM_DESC, SH_PARM_CONS_CD, WRKGRP_NM) VALUES('KR-NS', 'All', 'ENABLE_DIRECT_INQUIRIES_IND', sys_guid(), 1, 'CONFG', 'Y', 'Flag for enabling/disabling direct inquiries on screens that are drawn by the nervous system (i.e. lookups and maintenance documents)', 'A', 'KUALI_FMSOPS');
INSERT INTO sh_parm_t ("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","SH_PARM_TXT","SH_PARM_CONS_CD","SH_PARM_DESC","SH_PARM_TYP_CD","WRKGRP_NM") VALUES ('KR-NS','Lookup','RESULTS_LIMIT','200','A','Limit of results returned in a lookup query','CONFG','KUALI_FMSOPS');
INSERT INTO SH_PARM_T(SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_NM, OBJ_ID, VER_NBR, SH_PARM_TYP_CD, SH_PARM_TXT, SH_PARM_DESC, SH_PARM_CONS_CD, WRKGRP_NM) VALUES('KR-NS', 'Document', 'SESSION_TIMEOUT_WARNING_MESSAGE_TIME', sys_guid(), 1, 'CONFG', '5', 'The number of minutes before a session expires that user should be warned when a document uses pessimistic locking.', 'A', 'KUALI_FMSOPS'); 
INSERT INTO SH_PARM_T(SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_NM, OBJ_ID, VER_NBR, SH_PARM_TYP_CD, SH_PARM_TXT, SH_PARM_DESC, SH_PARM_CONS_CD, WRKGRP_NM) VALUES('KR-NS', 'Document', 'PESSIMISTIC_LOCK_ADMIN_GROUP', sys_guid(), 1, 'AUTH', 'WorkflowAdmin', 'Workgroup which can perform admin deletion and lookup functions for Pessimistic Locks.', 'A', 'KUALI_FMSOPS'); 
-- End nervous system params

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-B','D','budgetStatusCompleteCode','CONFG','1','Code corresponding to the budget status of Complete','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-B','D','budgetStatusIncompleteCode','CONFG','2','Code corresponding to the budget status of Incomplete','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-B','D','defaultOverheadRateClassCode','CONFG','1','The overhead rate class a new Budget should default to','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-B','D','defaultOverheadRateTypeCode','CONFG','1','The overhead rate type a new Budget should default to','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-B','D','defaultUnderrecoveryRateClassCode','CONFG','1','The underrecovery rate class a new Budget should default to','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-B','D','defaultModularFlag','CONFG','N','Default value of modular flag for a new Budget.','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','s2s.revisiontype.other','CONFG','E','RevisionType of Other','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','s2s.submissiontype.changedCorrected','CONFG','3','SubmissionType of Changed/Corrected','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd, sh_parm_dtl_typ_cd, sh_parm_nm, sh_parm_typ_cd, sh_parm_txt, sh_parm_desc, sh_parm_cons_cd, active_ind) values ('KRA-B','D','budgetCurrentFiscalYear','CONFG','07/01/2000',' The starting fiscal year for a budget','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd, sh_parm_dtl_typ_cd, sh_parm_nm, sh_parm_typ_cd, sh_parm_txt, sh_parm_desc, sh_parm_cons_cd, active_ind) values ('KRA-B','D','budgetCostSharingApplicabilityFlag','CONFG','Y',' Flag indicating if Cost Sharing is applicable for the budget','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd, sh_parm_dtl_typ_cd, sh_parm_nm, sh_parm_typ_cd, sh_parm_txt, sh_parm_desc, sh_parm_cons_cd, active_ind) values ('KRA-B','D','budgetUnrecoveredFandAApplicabilityFlag','CONFG','Y',' Flag indicating if Unrecovered &FA is applicable for the budget','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd, sh_parm_dtl_typ_cd, sh_parm_nm, sh_parm_typ_cd, sh_parm_txt, sh_parm_desc, sh_parm_cons_cd, active_ind) values ('KRA-B','D','subcontractorFandAGreaterThan25k','CONFG','420630','Cost element code for subcontractor F&A over 25k','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd, sh_parm_dtl_typ_cd, sh_parm_nm, sh_parm_typ_cd, sh_parm_txt, sh_parm_desc, sh_parm_cons_cd, active_ind) values ('KRA-B','D','subcontractorFandALessThan25k','CONFG','420610','Cost element code for subcontractor F&A under 25k','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetVersionsHelp','HELP','default.htm?turl=WordDocuments%2Fbudgetversionspage.htm','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetSummaryHelp','HELP','default.htm?turl=WordDocuments%2Fsummarypage.htm','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetPersonnelHelp','HELP','default.htm?turl=WordDocuments%2Fprojectpersonnelpage.htm','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetRatesHelp','HELP','default.htm?turl=WordDocuments%2Fratespage.htm','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetExpensesHelp','HELP','expensespage.htm','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetTotalsHelp','HELP','default.htm?turl=WordDocuments%2Ftotalspage.htm','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetDistributionAndIncomeHelp','HELP','distributionincomepage.htm','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetModularBudgetHelp','HELP','default.htm?turl=WordDocuments%2Fmodularbudgetpage.htm','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetActionsHelp','HELP','default.htm?turl=WordDocuments%2Fbudgetactionspage.htm','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetStatusHelpUrl','HELP','','Budget Status Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetPeriodHelpUrl','HELP','default.htm?turl=WordDocuments%2Fbudgetperiodstotalstab.htm','Budget Period Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetRateClassHelpUrl','HELP','default.htm?turl=WordDocuments%2Frateclasstab.htm','Rate Class Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetPersonHelpUrl','HELP','default.htm?turl=WordDocuments%2Fprojectpersonnelpage.htm','Budget Person Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetUnrecoveredFandAHelpUrl','HELP','default.htm?turl=WordDocuments%2Funrecoveredfatab.htm','Budget Unrecovered F and A Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetProjectIncomeHelpUrl','HELP','default.htm?turl=WordDocuments%2Fprojectincometab.htm','Budget Project Income Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetCategoryHelpUrl','HELP','default.htm?turl=WordDocuments%2Fcategorytab.htm','Budget Category Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetCostShareHelpUrl','HELP','default.htm?turl=WordDocuments%2Fbudgetcostsharetab.htm','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetLineItemHelpUrl','HELP','default.htm?turl=WordDocuments%2Fbudgetlineitem.htm','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetbudgetPersonnelDetailsHelpUrl','HELP','default.htm?turl=WordDocuments%2Fpersonneldetailstab.htm','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetModularHelpUrl','HELP','default.htm?turl=WordDocuments%2Fbudgetmodulartab.htm','Budget Page Help','A','Y');


insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentKeyPersonnelHelp','HELP','default.htm?turl=WordDocuments%2Fkeypersonnelpage.htm','Proposal Development Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentSpecialReviewHelp','HELP','default.htm?turl=WordDocuments%2Fspecialreviewpage.htm','Proposal Development Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentCustomDataHelp','HELP','default.htm?turl=WordDocuments%2Fcustomdatapage.htm','Proposal Development Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentQuestionsHelp','HELP','default.htm?turl=WordDocuments%2Fquestionspage.htm','Proposal Development Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentPermissionsHelp','HELP','default.htm?turl=WordDocuments%2Fpermissionspage.htm','Proposal Development Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentAbstractsAttachmentsHelp','HELP','default.htm?turl=WordDocuments%2Fabstractsandattachmentspage.htm','Proposal Development Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentProposalHelp','HELP','default.htm?turl=WordDocuments%2Fproposalpage.htm','Proposal Development Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentGrantsGovHelp','HELP','default.htm?turl=WordDocuments%2Fgrantsgovpage.htm','Proposal Development Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentActionsHelp','HELP','default.htm?turl=WordDocuments%2Fproposalactionspage.htm','Proposal Development Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentSponsorHelpUrl','HELP','default.htm?turl=WordDocuments%2Fsponsorprograminformationtab.htm','Sponsor Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentOrganizationHelpUrl','HELP','default.htm?turl=WordDocuments%2F?organizationlocationtab.htm','Organization Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentLocationHelpUrl','HELP','default.htm?turl=WordDocuments%2Forganizationlocationtab.htm','Location Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentKeywordsHelpUrl','HELP','default.htm?turl=WordDocuments%2Fkeywordstab.htm','Keywords Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentCreditSplitHelpUrl','HELP','default.htm?turl=WordDocuments%2Fcombinedcreditsplittab.htm','Credit Split Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentYnqHelpUrl','HELP','','Yes/No Questions Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentPersonHelpUrl','HELP','','Person Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentOpportunityHelpUrl','HELP','default.htm?turl=WordDocuments%2Fopportunitysection.htm','Grants.gov Opportunity Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind)
values ('KRA-PD','D','proposalDevelopmentNarrativeHelpUrl','HELP','','Narrative Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentAbstractHelpUrl','HELP','default.htm?turl=WordDocuments%2Fabstractstab.htm','Abstract Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentCustomAttributeHelpUrl','HELP','default.htm?turl=WordDocuments%2Fcustomattributetab.htm','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentMailByHelpUrl','HELP','default.htm?turl=WordDocuments%2Fmailbytab.htm','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentProposalTypeHelpUrl','HELP','default.htm?turl=WordDocuments%2Frequiredfieldstab.htm','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentSpecialReviewHelpUrl','HELP','default.htm?turl=WordDocuments%2Fspecialreviewtab.htm','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentCopyCriteriaHelpUrl','HELP','default.htm?turl=WordDocuments%2Fcopytab.htm','Proposal Copy Criteria Help','A','Y');

Insert into sh_parm_t (SH_PARM_NMSPC_CD,SH_PARM_DTL_TYP_CD,SH_PARM_NM,OBJ_ID,VER_NBR,SH_PARM_TYP_CD,SH_PARM_TXT,SH_PARM_DESC,SH_PARM_CONS_CD,WRKGRP_NM,ACTIVE_IND)
 values ('KRA-PD','D','sponsorGroupHierarchyName','823CF24F070046C8A65E86EA8EAD9C6B',1,'HELP','Sponsor Groups','Sponsor Group Hierarchy Name','A','WorkflowAdmin','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','consortiumFnaCostElements','CONFG','420630;420610','Cost elements considered to be consortium F and A','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','fnaRateClassTypeCode','CONFG','O','Rate class type code for F and A','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentDocumentHelp','HELP','default.htm?turl=WordDocuments%2Fproposaldevelopmentdocument.htm','Proposal Development Document Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','A','initialUnitLoadDepth','CONFG','3','Initial UnitHierarchy Load Depth','A','Y');

insert into SH_PARM_T (SH_PARM_NMSPC_CD,SH_PARM_DTL_TYP_CD,SH_PARM_NM,SH_PARM_TYP_CD,SH_PARM_TXT,SH_PARM_DESC,SH_PARM_CONS_CD,ACTIVE_IND) 
values ('KRA-B', 'D','budgetPersonDefaultPeriodType','CONFG', '3','Default Period Type','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','pessimisticLocking.cronExpression','CONFG','0 0 1 * * ?','The Cron Expression for Quartz to activate a clearing of old locks','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','pessimisticLocking.expriationAge','CONFG','1440','The expiration timeout in minutes; expired locks are deleted','A','Y');

INSERT
INTO sh_parm_t(sh_parm_nmspc_cd,    sh_parm_dtl_typ_cd,    sh_parm_nm,     sh_parm_typ_cd,    sh_parm_txt,    sh_parm_desc,    sh_parm_cons_cd,    wrkgrp_nm,    active_ind)
VALUES('KRA-PD',    'A',    'numberPerSponsorHierarchyGroup',     'CONFG',    '300',    'Number of nodes per sponsor group',    'A',    'WorkflowAdmin',    'Y');

INSERT INTO SH_PARM_T(SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_NM, OBJ_ID, VER_NBR, SH_PARM_TYP_CD, SH_PARM_TXT, SH_PARM_DESC, SH_PARM_CONS_CD, WRKGRP_NM) 
VALUES('KR-NS', 'Document', 'SESSION_TIMEOUT_WARNING_MESSAGE_TIME', sys_guid(), 1, 'CONFG', '5', 'The number of minutes before a session expires that user should be warned when a document uses pessimistic locking.', 'A', 'KUALI_FMSOPS');

INSERT INTO SH_PARM_T(SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_NM, OBJ_ID, VER_NBR, SH_PARM_TYP_CD, SH_PARM_TXT, SH_PARM_DESC, SH_PARM_CONS_CD, WRKGRP_NM) 
VALUES('KR-NS', 'Document', 'PESSIMISTIC_LOCK_ADMIN_GROUP', sys_guid(), 1, 'AUTH', 'KUALI_ROLE_SUPERVISOR', 'Workgroup which can perform admin deletion and lookup functions for Pessimistic Locks.', 'A', 'KUALI_FMSOPS');

INSERT
INTO sh_parm_t(sh_parm_nmspc_cd,    sh_parm_dtl_typ_cd,    sh_parm_nm,    obj_id,    ver_nbr,    sh_parm_typ_cd,    sh_parm_txt,    sh_parm_desc,    sh_parm_cons_cd,    wrkgrp_nm,    active_ind)
VALUES('KR-NS',    'All',    'MAX_FILE_SIZE_DEFAULT_UPLOAD',    '4CBAA7A57E0581C1E0404F8189D82E3D',    1,    'CONFG',    '5M',    'Maximum file upload size for the application. Used by PojoFormBase. Must be an integer, optionally followed by "K", "M", or "G". Only used if no other upload limits are in effect.',    'A',    'WorkflowAdmin',    'Y');

INSERT INTO sh_parm_nmspc_t
(SH_PARM_NMSPC_CD,SH_PARM_NMSPC_NM,ACTIVE_IND, OBJ_ID, VER_NBR)
values('KC-AWARD', 'Award','Y',sys_guid(),'1');

INSERT into SH_PARM_T 
( SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_NM,  SH_PARM_TYP_CD, SH_PARM_TXT, SH_PARM_DESC, SH_PARM_CONS_CD, WRKGRP_NM, ACTIVE_IND, OBJ_ID, VER_NBR)
Values 
('KC-AWARD','D','mit.idc.validation.enabled','CONFG','1','MitIdcValidationEnabled is configurable at impl time','A','WorkflowAdmin','Y',sys_guid(),'1');

