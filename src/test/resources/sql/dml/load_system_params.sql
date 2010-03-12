--located in src/main/resources/DefaultTestData.sql

--insert into krns_parm_typ_t (PARM_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND)
--values ('AUTH', 'E7243CAFD1554F2D949D7799AD8323CA', 1, 'Authorization', 'Y');
--
--insert into krns_parm_typ_t (PARM_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND)
--values ('CONFG', '724A325D41B14DFF97229904A99E801B', 1, 'Config', 'Y');
--
--insert into krns_parm_typ_t (PARM_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND)
--values ('HELP', '07AAF5BE2A664E1AB3FE6414ACC66F9A', 1, 'Help', 'Y');
--
--insert into krns_parm_typ_t (PARM_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND)
--values ('VALID', '5E5C1594CB26412FB1DFC20D1EB5B15D', 1, 'Document Validation', 'Y');

--insert into krns_nmspc_t (NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, APPL_NMSPC_CD)
--values ('KR-NS', '4752DA20C146474193CDAF30FF87452F', 1, 'Kuali Nervous System', 'Y', '');
--insert into krns_nmspc_t (NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, APPL_NMSPC_CD)
--values ('KR-WKFLW', '8B7C2C39C7F64C419D62D4CFCD6063AD', 0, 'Workflow', 'Y', '');
--insert into krns_nmspc_t (NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, APPL_NMSPC_CD)
--values ('KR-IDM', '3FC771D389664B1E8FD6F46373341FE0', 1, 'Identity Management', 'Y', '');

insert into krns_nmspc_t(NMSPC_CD,NM,ACTV_IND) values('KC-GEN','General Kuali Coeus','Y');
insert into krns_nmspc_t(NMSPC_CD,NM,ACTV_IND) values('KRA-PD','Proposal Development','Y');
insert into krns_nmspc_t(NMSPC_CD,NM,ACTV_IND) values('KRA-B','Budget','Y');
insert into krns_nmspc_t(NMSPC_CD,NM,ACTV_IND) values('KC-IP','Institutional Proposal','Y');
INSERT INTO krns_nmspc_t(NMSPC_CD,NM, ACTV_IND, OBJ_ID, VER_NBR) values('KC-AWARD', 'Award','Y',sys_guid(),'1');
Insert into krns_nmspc_t( NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) Values  ('KC-PROTOCOL', sys_guid(), 1, 'KC IRB Protocol', 'Y');
INSERT INTO KRNS_NMSPC_T (NMSPC_CD, NM, ACTV_IND) VALUES('KC-T', 'Time And Money', 'Y') ;

insert into KRNS_PARM_DTL_TYP_T(NMSPC_CD,PARM_DTL_TYP_CD,NM,ACTV_IND) values ('KRA-PD','D','Document','Y');
insert into KRNS_PARM_DTL_TYP_T(NMSPC_CD,PARM_DTL_TYP_CD,NM,ACTV_IND) values ('KRA-PD','L','Lookup','Y');
insert into KRNS_PARM_DTL_TYP_T(NMSPC_CD,PARM_DTL_TYP_CD,NM,ACTV_IND) values ('KRA-PD','A','All','Y');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','L','multipleValueLookupResultsPerPage','CONFG','200','Limit results returned for lookup - multiple results','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposaldevelopment.creditsplit.enabled','CONFG','Y','Determines whether the Credit Split is turned on for proposal','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KC-GEN','A','personrole.readonly.roles','CONFG','KP','Proposal Person Role Id list for roles that are read-only','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KC-GEN','A','personrole.nih.kp','CONFG','Key Person','Description of key person for Non-NIH Proposals','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KC-GEN','A','personrole.nih.pi','CONFG','PI/Contact','Description of principal investigator contact for Non-NIH Proposals','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KC-GEN','A','personrole.nih.coi','CONFG','PI/Multiple','Description of principal investigator multiple for Non-NIH Proposals','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposaldevelopment.proposaltype.new','CONFG','1','ProposalTypeCode of NEW','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposaldevelopment.proposaltype.renewal','CONFG','3','ProposalTypeCode of RENEWAL','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposaldevelopment.proposaltype.revision','CONFG','5','ProposalTypeCode of REVISION','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposaldevelopment.proposaltype.continuation','CONFG','4','ProposalTypeCode of CONTINUATION','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposaldevelopment.proposaltype.resubmission','CONFG','2','ProposalTypeCode of RESUBMISSION','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposaldevelopment.displayKeywordPanel','CONFG','TRUE','Display Proposal Keyword panel','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KC-GEN','A','personrole.kp','CONFG','Key Person','Description of key person for NIH Proposals','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KC-GEN','A','personrole.coi','CONFG','Co-Investigator','Description of co-investigator for NIH Proposals','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KC-GEN','A','personrole.pi','CONFG','Principal Investigator','Description of principal investigator for NIH Proposals','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','proposalNarrativeTypeGroup','CONFG','P','Define Narrative Type Group for Proposal Attachments','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','instituteNarrativeTypeGroup','CONFG','O','Define Narrative Type Group for Institute Attachments','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-PD','D','deliveryInfoDisplayIndicator','CONFG','Y','Flag to display delivery infor panel','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-B','D','budgetPersonDefaultJobCode','CONFG','0','The Job Code a new Budget Person should default to','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-B','D','budgetPersonDefaultAppointmentType','CONFG','1','The Appointment Type a new Budget Person should default to','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-B','D','budgetPersonDefaultCalculationBase','CONFG','0','The Calculation Base a new Budget Person should default to','A');

-- Nervous system params we need
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,TXT,CONS_CD,PARM_DESC_TXT,PARM_TYP_CD) VALUES ('KR-NS','Document','SUPERVISOR_GROUP','KUALI_ROLE_SUPERVISOR','A','Workgroup which can perform almost any function within Kuali.','AUTH'); 
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
 values ('KC-GEN','A','sponsorGroupHierarchyName','823CF24F070046C8A65E86EA8EAD9C6B',1,'HELP','Sponsor Groups','Sponsor Group Hierarchy Name','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD)
values ('KRA-PD','D','proposaldevelopment.autogenerate.institutionalproposal','CONFG','Y','Should an Institutional Proposal be automatically generated','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','consortiumFnaCostElements','CONFG','420630;420610','Cost elements considered to be consortium F and A','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-B','D','fnaRateClassTypeCode','CONFG','O','Rate class type code for F and A','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','proposalDevelopmentDocumentHelp','HELP','default.htm?turl=WordDocuments%2Fproposaldevelopmentdocument.htm','Proposal Development Document Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values ('KRA-PD','D','initialUnitLoadDepth','CONFG','4','Initial UnitHierarchy Load Depth','A');

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
--VALUES('KR-NS', 'Document', 'PESSIMISTIC_LOCK_ADMIN_GROUP', sys_guid(), 1, 'AUTH', 'KUALI_ROLE_SUPERVISOR', 'Workgroup which can perform admin deletion and lookup functions for Pessimistic Locks.', 'A', 'KUALI_FMSOPS');

-- Award Parms
INSERT into KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM,  PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID, VER_NBR) 
Values ('KC-AWARD','D','mit.idc.validation.enabled','CONFG','1','MitIdcValidationEnabled is configurable at impl time','A',sys_guid(),'1');

INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
VALUES ('KC-AWARD','D','federalCapitalizationMinimum',sys_guid(),1,'CONFG','100.00','Federal Capitalization Minimum','A');
  
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
VALUES ('KC-AWARD','D','institutionCapitalizationMinimum',sys_guid(),1,'CONFG','50.00','Institution Capitalization Minimum','A');

-- Protocol Parms


Insert into KRNS_PARM_DTL_TYP_T ( NMSPC_CD, PARM_DTL_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND)
Values ('KC-PROTOCOL','D','1EA4E50A05844D36964A0FCBB2992881',1,'Document', 'Y');

Insert into KRNS_PARM_T ( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
Values ('KC-PROTOCOL','D','irb.protocol.referenceID1','F1C228F9D4D8408A8E0BBC801C9525ab',1,'CONFG','Reference ID1','Referece id is configurable at impl time','A');

Insert into KRNS_PARM_T ( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
Values ('KC-PROTOCOL','D','irb.protocol.referenceID2','F1C228F9D4D8408A8E0BBC801C9525az',1,'CONFG','Reference ID2','Referece id is configurable at impl time','A');

Insert into KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
 Values ('KC-PROTOCOL', 'D', 'protocolPersonTrainingSectionRequired', '2347F6B21CAB41DAB20A395611C6ED23', 1, 'CONFG', 'True', 'Implementing institution can decide on whether to display training section', 'A');

Insert INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
VALUES ('KC-AWARD','D','reportClassForPaymentsAndInvoices',sys_guid(),1,'CONFG','6','Report Class For Payments And Invoices','A');
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
VALUES ('KC-AWARD','D','contactTypeOther',sys_guid(),1,'CONFG','8','Contact Type Code For Contact Type Other','A');
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
VALUES ('KC-AWARD','D','scheduleGenerationPeriodInYearsWhenFrequencyBaseCodeIsFinalExpirationDate',sys_guid(),1,'CONFG','1','Schedule Generation Period In Years When Frequency Base Code Is Final Expiration Date','A');
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD) 
VALUES ('KC-AWARD','D','closeoutReportTypeUserDefined',sys_guid(),1,'CONFG','UD','User Defined Close out Report Type','A');
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD) 
VALUES ('KC-AWARD','D','closeoutReportTypeFinancialReport',sys_guid(),1,'CONFG','1','This system parameter maps the CloseoutReportType Financial Report(closeoutReoprtTypeCode=1) with ReportClass Fiscal(reportClassCode=1). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.','A');
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD) 
VALUES ('KC-AWARD','D','closeoutReportTypeTechnical',sys_guid(),1,'CONFG','4','This system parameter maps the CloseoutReportType Technical(closeoutReoprtTypeCode=4) with ReportClass Technical Management(reportClassCode=4). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.','A');
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD) 
VALUES ('KC-AWARD','D','closeoutReportTypePatent',sys_guid(),1,'CONFG','3','This system parameter maps the CloseoutReportType Patent(closeoutReoprtTypeCode=3) with ReportClass Intellectual Property(reportClassCode=3). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.','A');
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD) 
VALUES ('KC-AWARD','D','closeoutReportTypeProperty',sys_guid(),1,'CONFG','2','This system parameter maps the CloseoutReportType Property(closeoutReoprtTypeCode=2) with ReportClass Property(reportClassCode=2). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.','A');
Insert into KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
Values ('KC-PROTOCOL','D','irb.protocol.billable', 'F1C228F9D4D8408A8E0BBC801C9525ak', 1,'CONFG','Y','Billable is configurable at impl time','A');

Insert into KRNS_PARM_T ( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
Values ('KC-PROTOCOL','D','irb.protocol.award.linking.enabled', sys_guid(), 1,'CONFG','Y','Linking from Award to Protocol Funding source is configurable at impl time','A');

Insert into KRNS_PARM_T ( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
Values ('KC-PROTOCOL','D','irb.protocol.development.proposal.linking.enabled', sys_guid(), 1,'CONFG','Y','Linking from Award to Protocol Funding source is configurable at impl time','A');

Insert into KRNS_PARM_T ( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
Values ('KC-PROTOCOL','D','irb.protocol.institute.proposal.linking.enabled', sys_guid(), 1,'CONFG','N','Linking from Award to Protocol Funding source is configurable at impl time','A');

-- krew
--INSERT INTO KRNS_NMSPC_T(NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, APPL_NMSPC_CD)
--VALUES('KR-WKFLW', '5E1D690C419B3E2EE0404F8189D82677', 0, 'Workflow', 'Y', NULL);
INSERT INTO KRNS_PARM_DTL_TYP_T(NMSPC_CD, PARM_DTL_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) 
 VALUES ('KR-WKFLW', 'DocumentSearch', '18695E69ED0D4FBE8B084FCA8066D21C', 1, 'Document Search ', 'Y');

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID,     VER_NBR,  PARM_TYP_CD,  TXT, PARM_DESC_TXT, CONS_CD)
VALUES ('KC-AWARD', 'D', 'award.creditsplit.enabled',    sys_guid(),    1,     'CONFG',         'Y',    'Determines whether the Credit Split is turned on for Award',    'A');
  
Insert into KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)
 Values ('KRA-PD', 'D', 'PROPOSAL_CONTACT_TYPE', sys_guid(), 1, 'CONFG', '2', 'Value for Proposal Contact Type', 'A');
   
Insert into KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)
 Values('KRA-PD', 'D', 'MULTI_CAMPUS_ENABLED', sys_guid(), 1, 'CONFG', '0', 'Flag for enabling/disabling Multicampus', 'A');

Insert into KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)
 Values ('KRA-PD', 'D', 'DHHS_AGREEMENT', sys_guid(), 1, 'CONFG', '0', 'Value for DHHS Agreement', 'A');

Insert into KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)
 Values ('KRA-PD', 'D', 'SCHEDULER_SERVICE_ENABLED', sys_guid(), 1, 'CONFG', '0', 'Flag for enabling/disabling scheduler service', 'A');

Insert into KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)
 Values ('KC-PROTOCOL', 'D', 'IRB_COMM_SELECTION_DURING_SUBMISSION', sys_guid(), 1, 'CONFG', 'O', 'Implementing institution can decide to allow committee/schedule/reviewers to be selected upon an IRB submission.', 'A');


insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KC-IP','D','proposalcommenttype.generalcomment','CONFG','16','Code for General Proposal Comment Type','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KC-IP','D','proposalcommenttype.reviewercomment','CONFG','17','Code for IP Reviewer Proposal Comment Type','A');

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM,         OBJ_ID,     VER_NBR,  PARM_TYP_CD,  TXT, PARM_DESC_TXT, CONS_CD)
  VALUES ('KC-IP', 'D', 'institutionalproposal.creditsplit.enabled', sys_guid(),    1,     'CONFG',      'N', 'Determines whether the Credit Split is turned on for IP', 'A');

insert into KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)
 Values ('KRA-PD', 'D', 'GENERIC_SPONSOR_CODE', sys_guid(), 1, 'CONFG', '009800', 'Generic sponsor code used for printing sponsor form', 'A');

INSERT INTO KRNS_NMSPC_T(NMSPC_CD, NM, ACTV_IND, APPL_NMSPC_CD, OBJ_ID) VALUES ('KC-WKFLW', 'KC Workflow Infrastructure', 'Y', NULL, SYS_GUID());

INSERT INTO krns_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
VALUES ('KC-IP','D','InstitutionalProposalHelpUrl',sys_guid(),1,'HELP','default.htm','Institutional Proposal Help','A');
INSERT INTO krns_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
VALUES ('KC-IP','D','InstitutionalProposalConstactsHelpUrl',sys_guid(),1,'HELP','default.htm','Institutional Proposal Contacts Help','A');
INSERT INTO krns_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
VALUES ('KC-IP','D','InstitutionalProposalCustomDataHelpUrl',sys_guid(),1,'HELP','default.htm','Institutional Proposal Custom Data Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
VALUES ('KC-IP','D','InstitutionalProposalSpecialReviewHelpUrl',sys_guid(),1,'HELP','default.htm','Institutional Proposal Special Review Help','A');
INSERT INTO krns_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
VALUES ('KC-IP','D','InstitutionalProposalIPReviewHelpUrl',sys_guid(),1,'HELP','default.htm','Institutional Proposal Intellectual Property Review Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
VALUES ('KC-IP','D','InstitutionalProposalDistributionHelpUrl',sys_guid(),1,'HELP','default.htm','Institutional Proposal Distribution Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
VALUES ('KC-IP','D','InstitutionalProposalActionsHelpUrl',sys_guid(),1,'HELP','default.htm','Institutional Proposal Actions Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
VALUES ('KC-IP','D','InstitutionalProposalIPReviewActivityHelpUrl',sys_guid(),1,'HELP','default.htm','Institutional Proposal Intellectual Property Reivew Activity Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
VALUES ('KC-IP','D','InstitutionalProposalUnitAdministratorHelpUrl',sys_guid(),1,'HELP','default.htm','Institutional Proposal Unit Administrator Help','A');	
INSERT INTO krns_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES ('KC-AWARD','D','awardHelpUrl',sys_guid(),1,'HELP','default.htm','Award Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES ('KC-AWARD','D','awardHomeHelp',sys_guid(),1,'HELP','default.htm','Award Home Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','awardContactsHelp',sys_guid(),1,'HELP','default.htm','Award Contacts Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','awardCommitmentsHelp',sys_guid(),1,'HELP','default.htm','Award Commitments Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','awardTimeAndMoneyHelp',sys_guid(),1,'HELP','default.htm','Award Time and Money Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','awardPaymentsReportsAndTermsHelp',sys_guid(),1,'HELP','default.htm','Award Payments Reports and Terms Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','awardSpecialReviewHelp',sys_guid(),1,'HELP','default.htm','Award Special Review Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','awardCustomDataHelp',sys_guid(),1,'HELP','default.htm','Award Custom Data Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','awardQuestionsHelp',sys_guid(),1,'HELP','default.htm','Award Questions Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES ('KC-AWARD','D','awardPermissionsHelp',sys_guid(),1,'HELP','default.htm','Award Permissions Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','awardNoteAndAttachmentsHelp',sys_guid(),1,'HELP','default.htm','Award Note and Attachments Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','awardActionsHelp',sys_guid(),1,'HELP','default.htm','Award Actions Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','awardApprovedEquipmentHelpUrl',sys_guid(),1,'HELP','default.htm','Award Approved Equipment Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','awardApprovedForeignTravelHelpUrl',sys_guid(),1,'HELP','default.htm','Award Approved Foreign Travel Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','awardApprovedSubawardHelpUrl',sys_guid(),1,'HELP','default.htm','Award Approved Subaward Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','awardCommentHelpUrl',sys_guid(),1,'HELP','default.htm','Award Comment Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','awardContactHelpUrl',sys_guid(),1,'HELP','default.htm','Award Contact Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','awardCostShareHelpUrl',sys_guid(),1,'HELP','default.htm','Award Cost Share Help','A');
INSERT INTO krns_PARM_T 	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES ('KC-AWARD','D','awardCustomDataHelpUrl',sys_guid(),1,'HELP','default.htm','Award Custom Data Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','awardPersonCreditSplitHelpUrl',sys_guid(),1,'HELP','default.htm','Award Person Credit Split Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','CommentTypeHelpUrl',sys_guid(),1,'HELP','default.htm','Award Comment Type Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','CostShareTypeHelpUrl',sys_guid(),1,'HELP','default.htm','Award Cost Share Type Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','awardFandaRateHelpUrl',sys_guid(),1,'HELP','default.htm','Award F and A Rate Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','awardReportTermHelpUrl',sys_guid(),1,'HELP','default.htm','Award Report Term Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','awardCloseoutHelpUrl',sys_guid(),1,'HELP','default.htm','Award Report Term Help','A');	
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-AWARD','D','awardAttachmentsHelpUrl',sys_guid(),1,'HELP','default.htm','Award Attachments Help','A');		
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-T','D','timeAndMoneyHelp',sys_guid(),1,'HELP','default.htm','Time And Money Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-T','D','TransactionHelp',sys_guid(),1,'HELP','default.htm','Transaction Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-T','D','PendingTransactionHelp',sys_guid(),1,'HELP','default.htm','Pending Transaction Help','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-T','D','awardHierarchyNodeHelpUrl',sys_guid(),1,'HELP','default.htm','Award Hierarchy Help','A');	
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-T','D','awardHierarchyHelpUrl',sys_guid(),1,'HELP','default.htm','Award Hierarchy Help','A');	
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 	('KC-GEN','D','permissionsHelpUrl',sys_guid(),1,'HELP','default.htm','Institutional Proposal Intellectual Property Reivew Activity Help','A');

insert into KRNS_PARM_T	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values 	('KRA-PD','A','SCHOOL_NAME','CONFG','Kuali Coeus','School Name','A');
insert into KRNS_PARM_T	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values 	('KRA-PD','A','SCHOOL_ACRONYM','CONFG','KC','School acronym','A');

insert into KRNS_PARM_T	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values 	('KC-AWARD','A','ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST','CONFG','2143','obligated direct indirect cost','A');

insert into KRNS_PARM_T	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values 	('KRA-PD','A','FELLOWSHIP_OSP_ADMIN','CONFG','qucikStart','Fellowship admin name','A');	

insert into krns_parm_t    (nmspc_cd,    parm_dtl_typ_cd,    parm_nm,    obj_id,    ver_nbr,    parm_typ_cd,    txt,    parm_desc_txt,    cons_cd)
VALUES    ('KC-GEN', 'A', 'sponsorLevelHierarchy', '5183FC8FA0B11606E0404F8189D8140F', 1, 'HELP', 'NIH', 'Sponsor Level Hierarchy', 'A');
    
insert into KRNS_PARM_T	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values 	('KRA-PD','D','s2sschedulercronExpressionstarttime','CONFG','01-JAN-2010 00:00 AM','Starttime for s2s scheduler cron job to start','A');
insert into KRNS_PARM_T	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
values 	('KRA-PD','D','PI_CITIZENSHIP_FROM_CUSTOM_DATA','CONFG','01-JAN-2010 00:00 AM','It defines where the citizenship info should fetch from','A');
INSERT INTO krns_PARM_T	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
VALUES ('KRA-PD','D','FEDERAL_ID_COMES_FROM_CURRENT_AWARD',sys_guid(),1,'CONFG','N','Determines whether the Grants.Gov Federal ID must be populated from the current award.','A');

Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-IDM','EntityNameImpl','PREFIXES','61645D045B0105D7E0404F8189D849B1',1,'CONFG','Ms;Mrs;Mr;Dr',null,'A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-IDM','EntityNameImpl','SUFFIXES','61645D045B0205D7E0404F8189D849B1',1,'CONFG','Jr;Sr;Mr;Md',null,'A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','Batch','ACTIVE_FILE_TYPES','5A689075D35E7AEBE0404F8189D80321',1,'CONFG','collectorInputFileType;procurementCardInputFileType;enterpriseFeederFileSetType;assetBarcodeInventoryInputFileType;customerLoadInputFileType','Batch file types that are active options for the file upload screen.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','All','CHECK_ENCRYPTION_SERVICE_OVERRIDE_IND','53680C68F59AAD9BE0404F8189D80A6C',1,'CONFG','Y','Flag for enabling/disabling (Y/N) the demonstration encryption check.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','ScheduleStep','CUTOFF_TIME','5A689075D35C7AEBE0404F8189D80321',1,'CONFG','02:00:00:AM','Controls when the daily batch schedule should terminate. The scheduler service implementation compares the start time of the schedule job from quartz with this time on day after the schedule job started running.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','ScheduleStep','CUTOFF_TIME_NEXT_DAY_IND','5A689075D35D7AEBE0404F8189D80321',1,'CONFG','Y','Controls whether when the system is comparing the schedule start day  with the scheduleStep_CUTOFF_TIME parameter, it considers the specified time to apply to the day after the schedule starts.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','Document','DEFAULT_CAN_PERFORM_ROUTE_REPORT_IND','53680C68F59EAD9BE0404F8189D80A6C',1,'CONFG','N','If Y, the Route Report button will be displayed on the document actions bar if the document is using the default DocumentAuthorizerBase.getDocumentActionFlags to set the canPerformRouteReport property of the returned DocumentActionFlags instance.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','All','ENABLE_DIRECT_INQUIRIES_IND','53680C68F59BAD9BE0404F8189D80A6C',1,'CONFG','Y','Flag for enabling/disabling direct inquiries on screens that are drawn by the nervous system (i.e. lookups and maintenance documents)','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','All','ENABLE_FIELD_LEVEL_HELP_IND','53680C68F59CAD9BE0404F8189D80A6C',1,'CONFG','N','Indicates whether field level help links are enabled on lookup pages and documents.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','PurgePendingAttachmentsStep','MAX_AGE','5A689075D35A7AEBE0404F8189D80321',1,'CONFG','86400','Pending attachments are attachments that do not yet have a permanent link with the associated Business Object (BO). These pending attachments are stored in the attachments.pending.directory (defined in the configuration service). If the BO is never persisted, then this attachment will become orphaned (i.e. not associated with any BO), but will remain in this directory. The PurgePendingAttachmentsStep batch step deletes these pending attachment files that are older than the value of this parameter. The unit of this value is seconds. Do not set this value too short, as this will cause problems attaching files to BOs.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','Document','MAX_FILE_SIZE_ATTACHMENT','53680C68F5A0AD9BE0404F8189D80A6C',1,'CONFG','5M','Maximum attachment upload size for the application. Used by KualiDocumentFormBase. Must be an integer, optionally followed by "K", "M", or "G".','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','All','MAX_FILE_SIZE_DEFAULT_UPLOAD','53680C68F59DAD9BE0404F8189D80A6C',1,'CONFG','5M','Maximum file upload size for the application. Used by PojoFormBase. Must be an integer, optionally followed by "K", "M", or "G". Only used if no other upload limits are in effect.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','Lookup','MULTIPLE_VALUE_RESULTS_EXPIRATION_SECONDS','53680C68F5A3AD9BE0404F8189D80A6C',1,'CONFG','86400','Lookup results may continue to be persisted in the DB long after they are needed. This parameter represents the maximum amount of time, in seconds, that the results will be allowed to persist in the DB before they are deleted from the DB.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','Lookup','MULTIPLE_VALUE_RESULTS_PER_PAGE','53680C68F5A6AD9BE0404F8189D80A6C',1,'CONFG','100','Maximum number of rows that will be displayed on a look-up results screen.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','PurgeSessionDocumentsStep','NUMBER_OF_DAYS_SINCE_LAST_UPDATE','5A689075D3597AEBE0404F8189D80321',1,'CONFG','1','Determines the age of the session document records that the the step will operate on, e.g. if this param is set to 4, the rows with a last update timestamp older that 4 days prior to when the job is running will be deleted.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','Lookup','RESULTS_DEFAULT_MAX_COLUMN_LENGTH','53680C68F5A7AD9BE0404F8189D80A6C',1,'CONFG','70','If a maxLength attribute has not been set on a lookup result field in the data dictionary, then the result column''s max length will be the value of this parameter. Set this parameter to 0 for an unlimited default length or a positive value (i.e. greater than 0) for a finite max length.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','Lookup','RESULTS_LIMIT','53680C68F5A8AD9BE0404F8189D80A6C',1,'CONFG','200','Maximum number of results returned in a look-up query.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','Document','SEND_NOTE_WORKFLOW_NOTIFICATION_ACTIONS','53680C68F5A1AD9BE0404F8189D80A6C',1,'CONFG','K','Some documents provide the functionality to send notes to another user using a workflow FYI or acknowledge functionality. This parameter specifies the default action that will be used when sending notes. This parameter should be one of the following 2 values: "K" for acknowledge or "F" for fyi. Depending on the notes and workflow service implementation, other values may be possible.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','Document','SESSION_TIMEOUT_WARNING_MESSAGE_TIME','53680C68F5A4AD9BE0404F8189D80A6C',1,'CONFG','5','The number of minutes before a session expires that user should be warned when a document uses pessimistic locking.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','ScheduleStep','STATUS_CHECK_INTERVAL','5A689075D35B7AEBE0404F8189D80321',1,'CONFG','30000','Time in milliseconds that the scheduleStep should wait between iterations.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','ActionList','ACTION_LIST_DOCUMENT_POPUP_IND','290E45BA032F4F4FB423CE5F78AC52E1',1,'CONFG','Y','Flag to specify if clicking on a Document ID from the Action List will load the Document in a new window.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','ActionList','ACTION_LIST_ROUTE_LOG_POPUP_IND','967B0311A5E94F7191B2C544FA7DE095',1,'CONFG','Y','Flag to specify if clicking on a Route Log from the Action List will load the Route Log in a new window.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','Rule','CACHING_IND','E05A692D62E54B87901D872DC37208A1',1,'CONFG','Y','Indicator to determine if rule caching is enabled.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','Rule','CUSTOM_DOCUMENT_TYPES','BDE964269F2743338C00A4326B676195',1,'CONFG',null,'Defines custom Document Type processes to use for certain types of routing rules.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','EDocLite','DEBUG_TRANSFORM_IND','68B2EA08E13A4FF3B9EDBD5415818C93',1,'CONFG','N','Defines whether the debug transform is enabled for eDcoLite.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','Rule','DELEGATE_LIMIT','21EA54B9A9E846709E76C176DE0AF47C',1,'CONFG','20','Specifies that maximum number of delegation rules that will be displayed on a Rule inquiry before the screen shows a count of delegate rules and provides a link for the user to show them.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','DocSearchCriteriaDTO','DOCUMENT_SEARCH_POPUP_IND','E78100F6F14C4932B54F7719FA5C27E9',1,'CONFG','Y','Flag to specify if clicking on a Document ID from Document Search will load the Document in a new window.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','ActionList','EMAIL_NOTIFICATION_TEST_ADDRESS','340789CDF30F4252A1A2A42AD39B90B2',1,'CONFG',null,'Default email address used for testing.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','DocSearchCriteriaDTO','DOCUMENT_SEARCH_ROUTE_LOG_POPUP_IND','632680DDE9A7478CBD379FAF90C7AE72',1,'CONFG','Y','Flag to specify if clicking on a Route Log from Document Search will load the Route Log in a new window.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','DocSearchCriteriaDTO','FETCH_MORE_ITERATION_LIMIT','D43459D143FC46C6BF83C71AC2383B76',1,'CONFG',null,'Limit of fetch more iterations for document searches.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','Mailer','FROM_ADDRESS','700AB6A6E23740D0B3E00E02A8FB6347',1,'CONFG','quickstart@localhost','Default from email address for notifications.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','Rule','GENERATE_ACTION_REQUESTS_IND','96868C896B4B4A8BA87AD20E42948431',1,'CONFG','Y','Flag to determine whether or not a change to a routing rule should be applied retroactively to existing documents.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','Feature','IS_LAST_APPROVER_ACTIVATE_FIRST_IND','BEBDBCFA74A5458EADE2CF075FFF206E',1,'CONFG',null,'A flag to specify whether the WorkflowInfo.isLastApproverAtNode(...) API method attempts to active requests first, prior to execution.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','All','KIM_PRIORITY_ON_DOC_TYP_PERMS_IND','5C731F2968A3689AE0404F8189D86653',1,'CONFG','N','Flag for enabling/disabling document type permission checks to use KIM Permissions as priority over Document Type policies.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','All','MAXIMUM_NODES_BEFORE_RUNAWAY','4656B6E7E9844E2C9E2255014AFC86B5',1,'CONFG',null,'The maximum number of nodes the workflow engine will process before it determines the process is a runaway process.  This is prevent infinite "loops" in the workflow engine.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','Notification','NOTIFY_EXCLUDED_USERS_IND','08280F2575904F3586CF48BB97907506',1,'CONFG',null,'Defines whether or not to send a notification to users excluded from a workgroup.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','ActionList','PAGE_SIZE_THROTTLE','2CE075BC0C59435CA6DEFF724492DE3F',1,'CONFG',null,'Throttles the number of results returned on all users Action Lists, regardless of their user preferences.  This is intended to be used in a situation where excessively large Action Lists are causing performance issues.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','QuickLinks','RESTRICT_DOCUMENT_TYPES','5292CFD9A0EA48BEB22A2EB3B3BD3CDA',1,'CONFG',null,'Comma seperated list of Document Types to exclude from the Rule Quicklinks.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','DocSearchCriteriaDTO','RESULT_CAP','E324D85082184EB6967537B3EE1F655B',1,'CONFG',null,'Maximum number of documents to return from a search.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','Rule','RULE_CACHE_REQUEUE_DELAY','8AE796DB88484468830A8879630CCF5D',1,'CONFG','5000','Amount of time after a rule change is made before the rule cache update message is sent.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','ActionList','SEND_EMAIL_NOTIFICATION_IND','A87659E198214A8B90BE5BEF41630411',1,'CONFG','N','Flag to determine whether or not to send email notification.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','All','SHOW_ATTACHMENTS_IND','8A37388A2D7A46EF9E6BF3FA8D08A03A',1,'CONFG','Y','Flag to specify whether or not a file upload box is displayed for KEW notes which allows for uploading of an attachment with the note.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','Backdoor','SHOW_BACK_DOOR_LOGIN_IND','9BD6785416434C4D9E5F05AF077DB9B7',1,'CONFG','Y','Flag to show the backdoor login.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','EDocLite','USE_XSLTC_IND','FCAEE745A7E64AF5982937C47EBC2698',1,'CONFG','N','Defines whether XSLTC is used for eDocLite.','A','KUALI');
--located in src/main/resources/DefaultTestData.sql
--Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','All','STRING_TO_DATE_FORMATS','728B1F1A84174FE9E0404F8189D865C3',1,'CONFG','MM/dd/yy;MM-dd-yy;MMMM dd, yyyy;MMddyy','A semi-colon delimted list of strings representing date formats that the DateTimeService will use to parse dates when DateTimeServiceImpl.convertToSqlDate(String) or DateTimeServiceImpl.convertToDate(String) is called. Note that patterns will be applied in the order listed (and the first applicable one will be used). For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.','A','KUALI');
--Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','All','DATE_TO_STRING_FORMAT_FOR_FILE_NAME','728B1F1A84184FE9E0404F8189D865C3',1,'CONFG','yyyyMMdd','A single date format string that the DateTimeService will use to format dates to be used in a file name when DateTimeServiceImpl.toDateStringForFilename(Date) is called. For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.','A','KUALI');
--Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','All','TIMESTAMP_TO_STRING_FORMAT_FOR_FILE_NAME','728B1F1A84194FE9E0404F8189D865C3',1,'CONFG','yyyyMMdd-HH-mm-ss-S','A single date format string that the DateTimeService will use to format a date and time string to be used in a file name when DateTimeServiceImpl.toDateTimeStringForFilename(Date) is called.. For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.','A','KUALI');
--Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','All','DATE_TO_STRING_FORMAT_FOR_USER_INTERFACE','728B1F1A841A4FE9E0404F8189D865C3',1,'CONFG','MM/dd/yyyy','A single date format string that the DateTimeService will use to format a date to be displayed on a web page. For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.','A','KUALI');
--Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','All','TIMESTAMP_TO_STRING_FORMAT_FOR_USER_INTERFACE','728B1F1A841B4FE9E0404F8189D865C3',1,'CONFG','MM/dd/yyyy hh:mm a','A single date format string that the DateTimeService will use to format a date and time to be displayed on a web page. For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.','A','KUALI');
--Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','All','STRING_TO_TIMESTAMP_FORMATS','728B1F1A841C4FE9E0404F8189D865C3',1,'CONFG','MM/dd/yyyy hh:mm a','A semi-colon delimted list of strings representing date formats that the DateTimeService will use to parse date and times when DateTimeServiceImpl.convertToDateTime(String) or DateTimeServiceImpl.convertToSqlTimestamp(String) is called. Note that patterns will be applied in the order listed (and the first applicable one will be used). For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','All','SENSITIVE_DATA_PATTERNS','5a5fbe94-846f-102c-8db0-c405cae621f3',1,'CONFG','[0-9]{9};[0-9]{3}-[0-9]{2}-[0-9]{4}','A semi-colon delimted list of regular expressions that identify potentially sensitive data in strings.  These patterns will be matched against notes, document explanations, and routing annotations.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','All','DEFAULT_COUNTRY','64B87B4C5E3B8F4CE0404F8189D8291A',1,'CONFG','US','Used as the default country code when relating records that do not have a country code to records that do have a country code, e.g. validating a zip code where the country is not collected.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-IDM','Document','MAX_MEMBERS_PER_PAGE','2238b58e-8fb9-102c-9461-def224dad9b3',1,'CONFG','20','The maximum number of role or group members to display at once on their documents. If the number is above this value, the document will switch into a paging mode with only this many rows displayed at a time.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','Document','ALLOW_ENROUTE_BLANKET_APPROVE_WITHOUT_APPROVAL_REQUEST_IND','74382C90B076049FE0404F8189D84C5D',1,'CONFG','N','Controls whether the nervous system will show the blanket approve button to a user who is authorized for blanket approval but is neither the initiator of the particular document nor the recipient of an active, pending, approve action request.','A','KUALI');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-B','D','awardBudgetEbRateClassCode','CONFG','5','The EB rate class code to be used for award budget if the rates are overridden on commitements tab','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-B','D','awardBudgetEbRateTypeCode','CONFG','6','The EB rate type code to be used for award budget if the rates are overridden on commitements tab','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-B','D','defaultFnARateClassCode','CONFG','1','The OH rate class code to be used for award budget if the fna rates are overridden on commitements tab','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-B','D','AWARD_BUDGET_TYPE_NEW_PARAMETER','CONFG','1','Default award budget status code','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KRA-B','D','AWARD_BUDGET_STATUS_IN_PROGRESS_CODE','CONFG','1','Default award budget type code','A');

insert into krns_parm_t (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
values ('kc', 'KR-WKFLW', 'ActionList', 'ACTION_LIST_DOCUMENT_POPUP_IND', SYS_GUID(), 1, 'CONFG', 'N', 'Flag to specify if clicking on a Document ID from the Action List will load the Document in a new window.', 'A');

insert into krns_parm_t (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
values ('kc', 'KR-WKFLW', 'ActionList', 'ACTION_LIST_ROUTE_LOG_POPUP_IND', SYS_GUID(), 1, 'CONFG', 'N', 'Flag to specify if clicking on a Route Log from the Action List will load the Route Log in a new window.', 'A');

insert into krns_parm_t (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
values ('kc', 'KR-WKFLW', 'DocSearchCriteriaDTO', 'DOCUMENT_SEARCH_POPUP_IND', SYS_GUID(), 1, 'CONFG', 'N', 'Flag to specify if clicking on a Document ID from Document Search will load the Document in a new window.', 'A');

insert into krns_parm_t (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
values ('kc', 'KR-WKFLW', 'DocSearchCriteriaDTO', 'DOCUMENT_SEARCH_ROUTE_LOG_POPUP_IND', SYS_GUID(), 1, 'CONFG', 'N', 'Flag to specify if clicking on a Route Log from Document Search will load the Route Log in a new window.', 'A');
