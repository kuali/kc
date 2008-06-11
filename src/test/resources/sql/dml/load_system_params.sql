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

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','s2s.revisiontype.other','CONFG','5','RevisionType of Other','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','s2s.submissiontype.changedCorrected','CONFG','3','SubmissionType of Changed/Corrected','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd, sh_parm_dtl_typ_cd, sh_parm_nm, sh_parm_typ_cd, sh_parm_txt, sh_parm_desc, sh_parm_cons_cd, active_ind) values ('KRA-B','D','budgetCurrentFiscalYear','CONFG','07/01/2000',' The starting fiscal year for a budget','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd, sh_parm_dtl_typ_cd, sh_parm_nm, sh_parm_typ_cd, sh_parm_txt, sh_parm_desc, sh_parm_cons_cd, active_ind) values ('KRA-B','D','budgetCostSharingApplicabilityFlag','CONFG','Y',' Flag indicating if Cost Sharing is applicable for the budget','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd, sh_parm_dtl_typ_cd, sh_parm_nm, sh_parm_typ_cd, sh_parm_txt, sh_parm_desc, sh_parm_cons_cd, active_ind) values ('KRA-B','D','budgetUnrecoveredFandAApplicabilityFlag','CONFG','Y',' Flag indicating if Unrecovered &FA is applicable for the budget','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd, sh_parm_dtl_typ_cd, sh_parm_nm, sh_parm_typ_cd, sh_parm_txt, sh_parm_desc, sh_parm_cons_cd, active_ind) values ('KRA-B','D','subcontractorFandAGreaterThan25k','CONFG','420630','Cost element code for subcontractor F&A over 25k','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd, sh_parm_dtl_typ_cd, sh_parm_nm, sh_parm_typ_cd, sh_parm_txt, sh_parm_desc, sh_parm_cons_cd, active_ind) values ('KRA-B','D','subcontractorFandALessThan25k','CONFG','420610','Cost element code for subcontractor F&A under 25k','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetVersionsHelp','HELP','blah://blah','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetSummaryHelp','HELP','blah://blah','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetPersonnelHelp','HELP','blah://blah','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetRatesHelp','HELP','blah://blah','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetExpensesHelp','HELP','blah://blah','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetTotalsHelp','HELP','blah://blah','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetDistributionAndIncomeHelp','HELP','blah://blah','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetModularBudgetHelp','HELP','blah://blah','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetActionsHelp','HELP','blah://blah','Budget Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetStatusHelpUrl','HELP','blah://blah','Budget Status Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetPeriodHelpUrl','HELP','blah://blah','Budget Period Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetRateClassHelpUrl','HELP','blah://blah','Rate Class Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetPersonHelpUrl','HELP','blah://blah','Budget Person Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetUnrecoveredFandAHelpUrl','HELP','blah://blah','Budget Unrecovered F and A Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetProjectIncomeHelpUrl','HELP','blah://blah','Budget Project Income Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetCategoryHelpUrl','HELP','blah://blah','Budget Category Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetLineItemHelpUrl','HELP','blah://blah','Budget Details Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetModularHelpUrl','HELP','blah://blah','Budget Modular Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetPersonnelDetailsHelpUrl','HELP','blah://blah','Budget Personnel Details Help','A','Y');


insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentKeyPersonnelHelp','HELP','blah://blah','Proposal Development Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentSpecialReviewHelp','HELP','blah://blah','Proposal Development Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentCustomDataHelp','HELP','blah://blah','Proposal Development Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentQuestionsHelp','HELP','blah://blah','Proposal Development Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentPermissionsHelp','HELP','blah://blah','Proposal Development Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentAbstractsAttachmentsHelp','HELP','blah://blah','Proposal Development Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentProposalHelp','HELP','blah://blah','Proposal Development Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentGrantsGovHelp','HELP','blah://blah','Proposal Development Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentActionsHelp','HELP','blah://blah','Proposal Development Page Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentSponsorHelpUrl','HELP','blah://blah','Sponsor Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentOrganizationHelpUrl','HELP','blah://blah','Organization Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentLocationHelpUrl','HELP','blah://blah','Location Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentKeywordsHelpUrl','HELP','blah://blah','Keywords Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentCreditSplitHelpUrl','HELP','blah://blah','Credit Split Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentYnqHelpUrl','HELP','blah://blah','Yes/No Questions Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentPersonHelpUrl','HELP','blah://blah','Person Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentOpportunityHelpUrl','HELP','blah://blah','Grants.gov Opportunity Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind)
values ('KRA-PD','D','proposalDevelopmentNarrativeHelpUrl','HELP','blah://blah','Narrative Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentAbstractHelpUrl','HELP','blah://blah','Abstract Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentProposalTypeHelpUrl','HELP','blah://blah','Proposal Type Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentCustomAttributeHelpUrl','HELP','blah://blah','Custom Attribute Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentCopyCriteriaHelpUrl','HELP','blah://blah','Proposal Copy Criteria Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentMailByHelpUrl','HELP','blah://blah','Delivery Info Help','A','Y');

Insert into sh_parm_t (SH_PARM_NMSPC_CD,SH_PARM_DTL_TYP_CD,SH_PARM_NM,OBJ_ID,VER_NBR,SH_PARM_TYP_CD,SH_PARM_TXT,SH_PARM_DESC,SH_PARM_CONS_CD,WRKGRP_NM,ACTIVE_IND)
 values ('KRA-PD','D','sponsorGroupHierarchyName','823CF24F070046C8A65E86EA8EAD9C6B',1,'HELP','Sponsor Groups','Sponsor Group Hierarchy Name','A','WorkflowAdmin','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','consortiumFnaCostElements','CONFG','420630;420610','Cost elements considered to be consortium F and A','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','fnaRateClassTypeCode','CONFG','O','Rate class type code for F and A','A','Y');
