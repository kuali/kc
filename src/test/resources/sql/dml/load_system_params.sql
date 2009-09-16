insert into krns_nmspc_t(NMSPC_CD,NM,ACTV_IND) values('KRA-PD','Proposal Development','Y');
insert into krns_nmspc_t(NMSPC_CD,NM,ACTV_IND) values('KRA-B','Budget','Y');
insert into krns_nmspc_t(NMSPC_CD,NM,ACTV_IND) values('KC-IP','Institutional Proposal','Y');
INSERT INTO krns_nmspc_t(NMSPC_CD,NM, ACTV_IND, OBJ_ID, VER_NBR) values('KC-AWARD', 'Award','Y',sys_guid(),'1');
Insert into krns_nmspc_t( NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) Values  ('KC-PROTOCOL', sys_guid(), 1, 'KC IRB Protocol', 'Y');

insert into KRNS_PARM_DTL_TYP_T(NMSPC_CD,PARM_DTL_TYP_CD,NM,ACTV_IND) values ('KRA-PD','D','Document','Y');
insert into KRNS_PARM_DTL_TYP_T(NMSPC_CD,PARM_DTL_TYP_CD,NM,ACTV_IND) values ('KRA-PD','L','Lookup','Y');
insert into KRNS_PARM_DTL_TYP_T(NMSPC_CD,PARM_DTL_TYP_CD,NM,ACTV_IND) values ('KRA-PD','A','All','Y');

--insert into KRNS_PARM_TYP_T (PARM_TYP_CD, NM, ACTV_IND) values ('HELP','Help','Y');
--insert into KRNS_PARM_TYP_T ("PARM_TYP_CD","VER_NBR","NM","ACTV_IND") values ('CONFG', 0,'Config',1);
--insert into KRNS_PARM_TYP_T ("PARM_TYP_CD","VER_NBR","NM","ACTV_IND") values ('AUTH', 0,'Authorization',1);
--insert into krns_nmspc_t ("NMSPC_CD","VER_NBR","NM","ACTV_IND") values ('KR-NS', 0,'Kuali Rice',1);

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','L','multipleValueLookupResultsPerPage','CONFG','200','Limit results returned for lookup - multiple results','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposaldevelopment.creditsplit.enabled','CONFG','Y','Determines whether the Credit Split is turned on for proposal','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposaldevelopment.personrole.readonly.roles','CONFG','KP','Proposal Person Role Id list for roles that are read-only','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposaldevelopment.personrole.nonnih.kp','CONFG','Key Person','Description of key person for Non-NIH Proposals','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposaldevelopment.personrole.nonnih.pi','CONFG','Proposal Investigator Contact','Description of principal investigator contact for Non-NIH Proposals','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposaldevelopment.personrole.nonnih.coi','CONFG','Proposal Investigator Multiple','Description of principal investigator multiple for Non-NIH Proposals','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposaldevelopment.proposaltype.new','CONFG','1','ProposalTypeCode of NEW','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposaldevelopment.proposaltype.renewal','CONFG','3','ProposalTypeCode of RENEWAL','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposaldevelopment.proposaltype.revision','CONFG','5','ProposalTypeCode of REVISION','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposaldevelopment.proposaltype.continuation','CONFG','2','ProposalTypeCode of CONTINUATION','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposaldevelopment.displayKeywordPanel','CONFG','TRUE','Display Proposal Keyword panel','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposaldevelopment.personrole.kp','CONFG','Key Person','Description of key person for NIH Proposals','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposaldevelopment.personrole.coi','CONFG','Co-Investigator','Description of co-investigator for NIH Proposals','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposaldevelopment.personrole.pi','CONFG','Principal Investigator','Description of principal investigator for NIH Proposals','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposalNarrativeTypeGroup','CONFG','P','Define Narrative Type Group for Proposal Attachments','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','instituteNarrativeTypeGroup','CONFG','O','Define Narrative Type Group for Institute Attachments','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','deliveryInfoDisplayIndicator','CONFG','Y','Flag to display delivery infor panel','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-B','D','budgetPersonDefaultJobCode','CONFG','0','The Job Code a new Budget Person should default to','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-B','D','budgetPersonDefaultAppointmentType','CONFG','1','The Appointment Type a new Budget Person should default to','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-B','D','budgetPersonDefaultCalculationBase','CONFG','0','The Calculation Base a new Budget Person should default to','A');

-- Nervous system params we need
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KR-NS','Lookup','MULTIPLE_VALUE_RESULTS_PER_PAGE','CONFG','200','Limit results returned for lookup - multiple results','A');
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,TXT,CONS_CD,PARM_DESC_TXT,PARM_TYP_CD) VALUES ('KR-NS','All','ENABLE_FIELD_LEVEL_HELP_IND','N','A','Indicates whether field level help links are enabled on lookup pages and documents.','CONFG');
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,TXT,CONS_CD,PARM_DESC_TXT,PARM_TYP_CD) VALUES ('KR-NS','Document','SUPERVISOR_GROUP','KUALI_ROLE_SUPERVISOR','A','Workgroup which can perform almost any function within Kuali.','AUTH');
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,TXT,CONS_CD,PARM_DESC_TXT,PARM_TYP_CD) VALUES ('KR-NS','All','CHECK_ENCRYPTION_SERVICE_OVERRIDE_IND','Y','A','Flag for enabling/disabling the demonstration encryption check.','CONFG');
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,TXT,CONS_CD,PARM_DESC_TXT,PARM_TYP_CD) VALUES ('KR-NS','Document','DEFAULT_CAN_PERFORM_ROUTE_REPORT_IND','N','A','If Y, the Route Report button will be displayed on the document actions bar if the document is using the default DocumentAuthorizerBase.getDocumentActionFlags to set the canPerformRouteReport property of the returned DocumentActionFlags instance.','CONFG');
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,TXT,CONS_CD,PARM_DESC_TXT,PARM_TYP_CD) VALUES ('KR-NS','Lookup','RESULTS_DEFAULT_MAX_COLUMN_LENGTH','70','A','If a maxLength attribute has not been set on a lookup result field in the data dictionary, then the result column''s max length will be the value of this parameter. Set this parameter to 0 for an unlimited default length or a positive value (i.e. greater than 0) for a finite max length.','CONFG');
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,TXT,CONS_CD,PARM_DESC_TXT,PARM_TYP_CD) VALUES ('KR-NS','Document','MAX_FILE_SIZE_ATTACHMENT','5M','A','Maximum attachment upload size for the application. Used by KualiDocumentFormBase. Must be an integer, optionally followed by ''K'', ''M'', or ''G''.','CONFG');
INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD) VALUES('KR-NS', 'All', 'ENABLE_DIRECT_INQUIRIES_IND', sys_guid(), 1, 'CONFG', 'Y', 'Flag for enabling/disabling direct inquiries on screens that are drawn by the nervous system (i.e. lookups and maintenance documents)', 'A');
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,TXT,CONS_CD,PARM_DESC_TXT,PARM_TYP_CD) VALUES ('KR-NS','Lookup','RESULTS_LIMIT','200','A','Limit of results returned in a lookup query','CONFG');
INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD) VALUES('KR-NS', 'Document', 'SESSION_TIMEOUT_WARNING_MESSAGE_TIME', sys_guid(), 1, 'CONFG', '5', 'The number of minutes before a session expires that user should be warned when a document uses pessimistic locking.', 'A'); 
INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD) VALUES('KR-NS', 'Document', 'PESSIMISTIC_LOCK_ADMIN_GROUP', sys_guid(), 1, 'AUTH', 'WorkflowAdmin', 'Workgroup which can perform admin deletion and lookup functions for Pessimistic Locks.', 'A'); 
-- End nervous system params

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-B','D','budgetStatusCompleteCode','CONFG','1','Code corresponding to the budget status of Complete','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-B','D','budgetStatusIncompleteCode','CONFG','2','Code corresponding to the budget status of Incomplete','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-B','D','defaultOverheadRateClassCode','CONFG','1','The overhead rate class a new Budget should default to','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-B','D','defaultOverheadRateTypeCode','CONFG','1','The overhead rate type a new Budget should default to','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-B','D','defaultUnderrecoveryRateClassCode','CONFG','1','The underrecovery rate class a new Budget should default to','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-B','D','defaultModularFlag','CONFG','N','Default value of modular flag for a new Budget.','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','s2s.revisiontype.other','CONFG','E','RevisionType of Other','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','s2s.submissiontype.changedCorrected','CONFG','3','SubmissionType of Changed/Corrected','A');

insert into KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD) values ('KRA-B','D','budgetCurrentFiscalYear','CONFG','07/01/2000',' The starting fiscal year for a budget','A');
insert into KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD) values ('KRA-B','D','budgetCostSharingApplicabilityFlag','CONFG','Y',' Flag indicating if Cost Sharing is applicable for the budget','A');
insert into KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD) values ('KRA-B','D','budgetUnrecoveredFandAApplicabilityFlag','CONFG','Y',' Flag indicating if Unrecovered &FA is applicable for the budget','A');

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, ver_nbr, PARM_TYP_CD, TXT, PARM_DESC_TXT, cons_cd) VALUES('KRA-B', 'D', 'budgetCostSharingEnforcementFlag', 1, 'CONFG', 'Y', 'Flag indicating if Cost Sharing allocation should be enforced', 'A');
INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, ver_nbr, PARM_TYP_CD, TXT, PARM_DESC_TXT, cons_cd) VALUES('KRA-B', 'D', 'budgetUnrecoveredFandAEnforcementFlag',1, 'CONFG', 'Y', 'Flag indicating if Unrecovered F and A allocation should be enforced', 'A');

insert into KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, cons_cd) values ('KRA-B','D','subcontractorFandAGreaterThan25k','CONFG','420630','Cost element code for subcontractor F&A over 25k','A');
insert into KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, cons_cd) values ('KRA-B','D','subcontractorFandALessThan25k','CONFG','420610','Cost element code for subcontractor F&A under 25k','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','budgetVersionsHelp','HELP','default.htm?turl=WordDocuments%2Fbudgetversionspage.htm','Budget Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','budgetSummaryHelp','HELP','default.htm?turl=WordDocuments%2Fsummarypage.htm','Budget Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','budgetPersonnelHelp','HELP','default.htm?turl=WordDocuments%2Fprojectpersonnelpage.htm','Budget Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','budgetRatesHelp','HELP','default.htm?turl=WordDocuments%2Fratespage.htm','Budget Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','budgetExpensesHelp','HELP','expensespage.htm','Budget Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','budgetTotalsHelp','HELP','default.htm?turl=WordDocuments%2Ftotalspage.htm','Budget Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','budgetDistributionAndIncomeHelp','HELP','distributionincomepage.htm','Budget Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','budgetModularBudgetHelp','HELP','default.htm?turl=WordDocuments%2Fmodularbudgetpage.htm','Budget Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','budgetActionsHelp','HELP','default.htm?turl=WordDocuments%2Fbudgetactionspage.htm','Budget Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','budgetStatusHelpUrl','HELP','','Budget Status Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','budgetPeriodHelpUrl','HELP','default.htm?turl=WordDocuments%2Fbudgetperiodstotalstab.htm','Budget Period Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','budgetRateClassHelpUrl','HELP','default.htm?turl=WordDocuments%2Frateclasstab.htm','Rate Class Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','budgetPersonHelpUrl','HELP','default.htm?turl=WordDocuments%2Fprojectpersonnelpage.htm','Budget Person Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','budgetUnrecoveredFandAHelpUrl','HELP','default.htm?turl=WordDocuments%2Funrecoveredfatab.htm','Budget Unrecovered F and A Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','budgetProjectIncomeHelpUrl','HELP','default.htm?turl=WordDocuments%2Fprojectincometab.htm','Budget Project Income Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','budgetCategoryHelpUrl','HELP','default.htm?turl=WordDocuments%2Fcategorytab.htm','Budget Category Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','budgetCostShareHelpUrl','HELP','default.htm?turl=WordDocuments%2Fbudgetcostsharetab.htm','Budget Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','budgetLineItemHelpUrl','HELP','default.htm?turl=WordDocuments%2Fbudgetlineitem.htm','Budget Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','budgetbudgetPersonnelDetailsHelpUrl','HELP','default.htm?turl=WordDocuments%2Fpersonneldetailstab.htm','Budget Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','budgetModularHelpUrl','HELP','default.htm?turl=WordDocuments%2Fbudgetmodulartab.htm','Budget Page Help','A');


insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentKeyPersonnelHelp','HELP','default.htm?turl=WordDocuments%2Fkeypersonnelpage.htm','Proposal Development Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentSpecialReviewHelp','HELP','default.htm?turl=WordDocuments%2Fspecialreviewpage.htm','Proposal Development Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentCustomDataHelp','HELP','default.htm?turl=WordDocuments%2Fcustomdatapage.htm','Proposal Development Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentQuestionsHelp','HELP','default.htm?turl=WordDocuments%2Fquestionspage.htm','Proposal Development Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentPermissionsHelp','HELP','default.htm?turl=WordDocuments%2Fpermissionspage.htm','Proposal Development Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentAbstractsAttachmentsHelp','HELP','default.htm?turl=WordDocuments%2Fabstractsandattachmentspage.htm','Proposal Development Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentProposalHelp','HELP','default.htm?turl=WordDocuments%2Fproposalpage.htm','Proposal Development Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentGrantsGovHelp','HELP','default.htm?turl=WordDocuments%2Fgrantsgovpage.htm','Proposal Development Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentActionsHelp','HELP','default.htm?turl=WordDocuments%2Fproposalactionspage.htm','Proposal Development Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentSponsorHelpUrl','HELP','default.htm?turl=WordDocuments%2Fsponsorprograminformationtab.htm','Sponsor Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentOrganizationHelpUrl','HELP','default.htm?turl=WordDocuments%2F?organizationlocationtab.htm','Organization Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentLocationHelpUrl','HELP','default.htm?turl=WordDocuments%2Forganizationlocationtab.htm','Location Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentKeywordsHelpUrl','HELP','default.htm?turl=WordDocuments%2Fkeywordstab.htm','Keywords Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentCreditSplitHelpUrl','HELP','default.htm?turl=WordDocuments%2Fcombinedcreditsplittab.htm','Credit Split Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentYnqHelpUrl','HELP','','Yes/No Questions Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentPersonHelpUrl','HELP','','Person Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentOpportunityHelpUrl','HELP','default.htm?turl=WordDocuments%2Fopportunitysection.htm','Grants.gov Opportunity Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD)
values ('KRA-PD','D','proposalDevelopmentNarrativeHelpUrl','HELP','','Narrative Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentAbstractHelpUrl','HELP','default.htm?turl=WordDocuments%2Fabstractstab.htm','Abstract Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentCustomAttributeHelpUrl','HELP','default.htm?turl=WordDocuments%2Fcustomattributetab.htm','Budget Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentMailByHelpUrl','HELP','default.htm?turl=WordDocuments%2Fmailbytab.htm','Budget Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentProposalTypeHelpUrl','HELP','default.htm?turl=WordDocuments%2Frequiredfieldstab.htm','Budget Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentSpecialReviewHelpUrl','HELP','default.htm?turl=WordDocuments%2Fspecialreviewtab.htm','Budget Page Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentCopyCriteriaHelpUrl','HELP','default.htm?turl=WordDocuments%2Fcopytab.htm','Proposal Copy Criteria Help','A');

Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD)
 values ('KRA-PD','D','sponsorGroupHierarchyName','823CF24F070046C8A65E86EA8EAD9C6B',1,'HELP','Sponsor Groups','Sponsor Group Hierarchy Name','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','consortiumFnaCostElements','CONFG','420630;420610','Cost elements considered to be consortium F and A','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','fnaRateClassTypeCode','CONFG','O','Rate class type code for F and A','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentDocumentHelp','HELP','default.htm?turl=WordDocuments%2Fproposaldevelopmentdocument.htm','Proposal Development Document Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','A','initialUnitLoadDepth','CONFG','4','Initial UnitHierarchy Load Depth','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B', 'D','budgetPersonDefaultPeriodType','CONFG', '3','Default Period Type','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','pessimisticLocking.cronExpression','CONFG','0 0 1 * * ?','The Cron Expression for Quartz to activate a clearing of old locks','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','pessimisticLocking.expriationAge','CONFG','1440','The expiration timeout in minutes; expired locks are deleted','A');

INSERT
INTO KRNS_PARM_T(NMSPC_CD,    PARM_DTL_TYP_CD,    PARM_NM,     PARM_TYP_CD,    TXT,    PARM_DESC_TXT,    CONS_CD    )
VALUES('KRA-PD',    'A',    'numberPerSponsorHierarchyGroup',     'CONFG',    '300',    'Number of nodes per sponsor group',    'A');

insert into KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
values ('KRA-B', 'D', 'JOBCODE_VALIDATION_ENABLED', 'CONFG', 'Y', 'Whether Job code based validation is enabled', 'A');

INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD)  
VALUES ('KRA-B','D','budgetPersonDetailsDefaultPeriodType','CONFG','3','The Period Type of a newly budgeted Person should default to','A');

insert into krns_parm_dtl_typ_t(NMSPC_CD,PARM_DTL_TYP_CD,NM) values ('KRA-B','A','All');
insert into krns_parm_dtl_typ_t(NMSPC_CD,PARM_DTL_TYP_CD,NM) values ('KRA-B','D','Document');

insert into KRNS_PARM_T (NMSPC_CD,parm_dtl_typ_cd,parm_nm,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD)
values ('KRA-B','A','instituteRateClassTypes','CONFG','E;I;O;V;X','Manages a list of Institute rate class types.','A');

insert into KRNS_PARM_T (NMSPC_CD,parm_dtl_typ_cd,parm_nm,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD)
values ('KRA-B','A','instituteLaRateClassTypes','CONFG','Y;L','Manages a list of Institute La rate class types.','A');

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD) 
VALUES ('KRA-B', 'D', 'budgetCategoryType.personnel', 'CONFG', 'P', 'Personnel Budget Category Type', 'A');

--INSERT INTO KRNS_PARM_T(NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM) 
--VALUES('KR-NS', 'Document', 'SESSION_TIMEOUT_WARNING_MESSAGE_TIME', sys_guid(), 1, 'CONFG', '5', 'The number of minutes before a session expires that user should be warned when a document uses pessimistic locking.', 'A', 'KUALI_FMSOPS');

--INSERT INTO KRNS_PARM_T(NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM) 
--VALUES('KR-NS', 'Document', 'PESSIMISTIC_LOCK_ADMIN_GROUP', sys_guid(), 1, 'AUTH', 'KUALI_ROLE_SUPERVISOR', 'Workgroup which can perform admin deletion and lookup functions for Pessimistic Locks.', 'A', 'KUALI_FMSOPS');

INSERT
INTO KRNS_PARM_T(NMSPC_CD,    PARM_DTL_TYP_CD,    PARM_NM,    obj_id,    ver_nbr,    PARM_TYP_CD,    TXT,    PARM_DESC_TXT,    CONS_CD)
VALUES('KR-NS',    'All',    'MAX_FILE_SIZE_DEFAULT_UPLOAD',    '4CBAA7A57E0581C1E0404F8189D82E3D',    1,    'CONFG',    '5M',    'Maximum file upload size for the application. Used by PojoFormBase. Must be an integer, optionally followed by "K", "M", or "G". Only used if no other upload limits are in effect.',    'A');



-- Award Parms
INSERT into KRNS_PARM_T 
	(NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM,  PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID, VER_NBR)
	Values 
	('KC-AWARD','D','mit.idc.validation.enabled','CONFG','1','MitIdcValidationEnabled is configurable at impl time','A',sys_guid(),'1');

INSERT INTO KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
	VALUES 
	('KC-AWARD','D','federalCapitalizationMinimum',sys_guid(),1,'CONFG','100.00','Federal Capitalization Minimum','A');
  
INSERT INTO KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
	VALUES 
	('KC-AWARD','D','institutionCapitalizationMinimum',sys_guid(),1,'CONFG','50.00','Institution Capitalization Minimum','A');

-- Protocol Parms


Insert into KRNS_PARM_DTL_TYP_T 
( NMSPC_CD, PARM_DTL_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND)
Values 
('KC-PROTOCOL','D','1EA4E50A05844D36964A0FCBB2992881',1,'Document', 'Y');

Insert into KRNS_PARM_T 
( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
Values 
('KC-PROTOCOL','D','irb.protocol.referenceID1','F1C228F9D4D8408A8E0BBC801C9525ab',1,'CONFG','Reference ID1','Referece id is configurable at impl time','A');

Insert into KRNS_PARM_T 
( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
Values
('KC-PROTOCOL','D','irb.protocol.referenceID2','F1C228F9D4D8408A8E0BBC801C9525az',1,'CONFG','Reference ID2','Referece id is configurable at impl time','A');

Insert into KRNS_PARM_T
   (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
 Values
   ('KC-PROTOCOL', 'D', 'protocolPersonTrainingSectionRequired', '2347F6B21CAB41DAB20A395611C6ED23', 1, 'CONFG', 'True', 'Implementing institution can decide on whether to display training section', 'A');


Insert INTO KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
	VALUES 
	('KC-AWARD','D','reportClassForPaymentsAndInvoices',sys_guid(),1,'CONFG','6','Report Class For Payments And Invoices','A');
INSERT INTO KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
	VALUES 
	('KC-AWARD','D','contactTypeOther',sys_guid(),1,'CONFG','8','Contact Type Code For Contact Type Other','A');
INSERT INTO KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
	VALUES 
	('KC-AWARD','D','scheduleGenerationPeriodInYearsWhenFrequencyBaseCodeIsFinalExpirationDate',sys_guid(),1,'CONFG','1','Schedule Generation Period In Years When Frequency Base Code Is Final Expiration Date','A');
INSERT INTO KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD) 
	VALUES 
	('KC-AWARD','D','closeoutReportTypeUserDefined',sys_guid(),1,'CONFG','UD','User Defined Close out Report Type','A');
INSERT INTO KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD) 
	VALUES 
	('KC-AWARD','D','closeoutReportTypeFinancialReport',sys_guid(),1,'CONFG','1','This system parameter maps the CloseoutReportType Financial Report(closeoutReoprtTypeCode=1) with ReportClass Fiscal(reportClassCode=1). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.','A');
INSERT INTO KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD) 
	VALUES 
	('KC-AWARD','D','closeoutReportTypeTechnical',sys_guid(),1,'CONFG','4','This system parameter maps the CloseoutReportType Technical(closeoutReoprtTypeCode=4) with ReportClass Technical Management(reportClassCode=4). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.','A');
INSERT INTO KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD) 
	VALUES 
	('KC-AWARD','D','closeoutReportTypePatent',sys_guid(),1,'CONFG','3','This system parameter maps the CloseoutReportType Patent(closeoutReoprtTypeCode=3) with ReportClass Intellectual Property(reportClassCode=3). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.','A');
INSERT INTO KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD) 
	VALUES 
	('KC-AWARD','D','closeoutReportTypeProperty',sys_guid(),1,'CONFG','2','This system parameter maps the CloseoutReportType Property(closeoutReoprtTypeCode=2) with ReportClass Property(reportClassCode=2). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.','A');

Insert into KRNS_PARM_T 
( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
Values
('KC-PROTOCOL','D','irb.protocol.billable', 'F1C228F9D4D8408A8E0BBC801C9525ak', 1,'CONFG','Y','Billable is configurable at impl time','A');

Insert into KRNS_PARM_T 
( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
Values
('KC-PROTOCOL','D','irb.protocol.award.linking.enabled', sys_guid(), 1,'CONFG','Y','Linking from Award to Protocol Funding source is configurable at impl time','A');

Insert into KRNS_PARM_T 
( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
Values
('KC-PROTOCOL','D','irb.protocol.development.proposal.linking.enabled', sys_guid(), 1,'CONFG','Y','Linking from Award to Protocol Funding source is configurable at impl time','A');

Insert into KRNS_PARM_T 
( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
Values
('KC-PROTOCOL','D','irb.protocol.institute.proposal.linking.enabled', sys_guid(), 1,'CONFG','N','Linking from Award to Protocol Funding source is configurable at impl time','A');

-- krew
--INSERT INTO KRNS_NMSPC_T(NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, APPL_NMSPC_CD)
--VALUES('KR-WKFLW', '5E1D690C419B3E2EE0404F8189D82677', 0, 'Workflow', 'Y', NULL);
INSERT INTO KRNS_PARM_DTL_TYP_T(NMSPC_CD, PARM_DTL_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND)
  VALUES('KR-WKFLW', 'DocumentSearch', '18695E69ED0D4FBE8B084FCA8066D21C', 1, 'Document Search ', 'Y')
;

INSERT INTO KRNS_PARM_T(NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
  VALUES('KR-WKFLW', 'DocumentSearch', 'DOCUMENT_SEARCH_POPUP_IND', 'E78100F6F14C4932B54F7719FA5C27E9', 1, 'CONFG', 'Y', 'Flag to specify if clicking on a Document ID from Document Search will load the Document in a new window.', 'A');
INSERT INTO KRNS_PARM_T(NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
  VALUES('KR-WKFLW', 'DocumentSearch', 'DOCUMENT_SEARCH_ROUTE_LOG_POPUP_IND', '632680DDE9A7478CBD379FAF90C7AE72', 1, 'CONFG', 'N', 'Flag to specify if clicking on a Route Log from Document Search will load the Route Log in a new window.', 'A');

INSERT INTO KRNS_PARM_T
(NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM,                     OBJ_ID,     VER_NBR,  PARM_TYP_CD,  TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD',    'D',             'award.creditsplit.enabled',    sys_guid(),    1,     'CONFG',         'Y',    'Determines whether the Credit Split is turned on for Award',    'A');
  
Insert into KRNS_PARM_T
   (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)
 Values
   ('KRA-PD', 'D', 'PROPOSAL_CONTACT_TYPE', sys_guid(), 1, 'CONFG', '2', 'Value for Proposal Contact Type', 'A');
   
Insert into KRNS_PARM_T
   (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)
 Values
   ('KRA-PD', 'D', 'MULTI_CAMPUS_ENABLED', sys_guid(), 1, 'CONFG', '0', 'Flag for enabling/disabling Multicampus', 'A');

Insert into KRNS_PARM_T
   (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)
 Values
   ('KRA-PD', 'D', 'DHHS_AGREEMENT', sys_guid(), 1, 'CONFG', '0', 'Value for DHHS Agreement', 'A');

Insert into KRNS_PARM_T
   (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)
 Values
   ('KRA-PD', 'D', 'SCHEDULER_SERVICE_ENABLED', sys_guid(), 1, 'CONFG', '0', 'Flag for enabling/disabling scheduler service', 'A');

Insert into KRNS_PARM_T
   (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)
 Values
   ('KC-PROTOCOL', 'D', 'IRB_COMM_SELECTION_DURING_SUBMISSION', sys_guid(), 1, 'CONFG', 'O', 'Implementing institution can decide to allow committee/schedule/reviewers to be selected upon an IRB submission.', 'A');


insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KC-IP','D','proposalcommenttype.generalcomment','CONFG','2','Code for General Proposal Comment Type','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KC-IP','D','proposalcommenttype.reviewercomment','CONFG','16','Code for IP Reviewer Proposal Comment Type','A');

insert into KRNS_PARM_T
   (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)
 Values
   ('KRA-PD', 'D', 'GENERIC_SPONSOR_CODE', sys_guid(), 1, 'CONFG', '009800', 'Generic sponsor code used for printing sponsor form', 'A');

