--located in src/test/resources/DefaultTestData.sql

--insert into KRNS_PARM_TYP_T (PARM_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) --values ('AUTH', 'E7243CAFD1554F2D949D7799AD8323CA', 1, 'Authorization', 'Y');
--
--insert into KRNS_PARM_TYP_T (PARM_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) --values ('CONFG', '724A325D41B14DFF97229904A99E801B', 1, 'Config', 'Y');
--
--insert into KRNS_PARM_TYP_T (PARM_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) --values ('HELP', '07AAF5BE2A664E1AB3FE6414ACC66F9A', 1, 'Help', 'Y');
--
--insert into KRNS_PARM_TYP_T (PARM_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) --values ('VALID', '5E5C1594CB26412FB1DFC20D1EB5B15D', 1, 'Document Validation', 'Y');

--insert into KRNS_NMSPC_T (NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, APPL_NMSPC_CD) --values ('KR-NS', '4752DA20C146474193CDAF30FF87452F', 1, 'Kuali Nervous System', 'Y', '');
--insert into KRNS_NMSPC_T (NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, APPL_NMSPC_CD) --values ('KR-WKFLW', '8B7C2C39C7F64C419D62D4CFCD6063AD', 0, 'Workflow', 'Y', '');
--insert into KRNS_NMSPC_T (NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, APPL_NMSPC_CD) --values ('KR-IDM', '3FC771D389664B1E8FD6F46373341FE0', 1, 'Identity Management', 'Y', '');

insert into KRNS_NMSPC_T(NMSPC_CD,NM,ACTV_IND,OBJ_ID) values('KC-GEN','General Kuali Coeus','Y','40966c09-3bf3-49d9-9acc-0c1fa1c00532');
insert into KRNS_NMSPC_T(NMSPC_CD,NM,ACTV_IND,OBJ_ID) values('KC-PD','Proposal Development','Y','318cd908-8ae8-49c5-9ecd-3b7133f8fa45');
insert into KRNS_NMSPC_T(NMSPC_CD,NM,ACTV_IND,OBJ_ID) values('KC-B','Budget','Y','0d6fca75-2691-4bca-b858-948c1303b135');
insert into KRNS_NMSPC_T(NMSPC_CD,NM,ACTV_IND,OBJ_ID) values('KC-IP','Institutional Proposal','Y','60768ea6-fce0-495b-9058-d0a863fe28dd');
INSERT INTO KRNS_NMSPC_T(NMSPC_CD,NM, ACTV_IND, OBJ_ID, VER_NBR) values('KC-AWARD', 'Award','Y','70d3e090-5a5f-4e92-ac0c-f8465dac7990','1');
Insert into KRNS_NMSPC_T( NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) Values ('KC-PROTOCOL', '312cbee6-8f02-4c3f-87be-a54581eb5c3c', 1, 'KC IRB Protocol', 'Y');
INSERT INTO KRNS_NMSPC_T (NMSPC_CD, NM, ACTV_IND,OBJ_ID) VALUES('KC-T', 'Time And Money', 'Y','0c999a64-c22a-4142-8524-14f4f728a899') ;

insert into KRNS_PARM_DTL_TYP_T(NMSPC_CD,PARM_DTL_TYP_CD,NM,ACTV_IND,OBJ_ID) values ('KC-PD','D','Document','Y','6a18dd88-0cf2-464f-91e6-626acb6c49cd');
insert into KRNS_PARM_DTL_TYP_T(NMSPC_CD,PARM_DTL_TYP_CD,NM,ACTV_IND,OBJ_ID) values ('KC-PD','L','Lookup','Y','220c08a3-96f7-48da-8002-a92f795d850c');
insert into KRNS_PARM_DTL_TYP_T(NMSPC_CD,PARM_DTL_TYP_CD,NM,ACTV_IND,OBJ_ID) values ('KC-PD','A','All','Y','bdc36091-487d-4137-a49f-ac1029a9d30b');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-PD','L','multipleValueLookupResultsPerPage','CONFG','200','Limit results returned for lookup - multiple results','A','5053c5cd-a38e-43ba-b226-924757d56cd1');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-IP','D', 'validFundingProposalStatusCodes','CONFG', '1,2,6', 'comma delimited list of codes for proposal status on the institutional proposal tab', 'A', '7367da3c033e42b182cb54fcaee7263d');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-PD','D','proposaldevelopment.creditsplit.enabled','CONFG','Y','Determines whether the Credit Split is turned on for proposal','A','4029390d-3619-445a-951b-d7e2dafe5fed');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-GEN','A','personrole.readonly.roles','CONFG','KP','Proposal Person ROLE Id list for roles that are read-only','A','8ff96f72-e9bf-4a4f-88cc-685f84440ec1');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-GEN','A','personrole.nih.kp','CONFG','Key Person','Description of key person for Non-NIH Proposals','A','72c2877d-151b-4007-b29b-48505fcce1f0');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-GEN','A','personrole.nih.pi','CONFG','PI/Contact','Description of principal investigator contact for Non-NIH Proposals','A','293d512f-cd92-4359-a426-040ef09e0995');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-GEN','A','personrole.nih.coi','CONFG','PI/Multiple','Description of principal investigator multiple for Non-NIH Proposals','A','7c1d55e2-6066-47c1-849f-4c6eb4beda19');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-PD','D','proposaldevelopment.proposaltype.new','CONFG','1','ProposalTypeCode of NEW','A','aeecb1ed-a3f2-4823-8edc-0b41319422ef');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-PD','D','proposaldevelopment.proposaltype.renewal','CONFG','3','ProposalTypeCode of RENEWAL','A','06fe5a96-d244-48f7-821e-2cb3dd7ebe10');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-PD','D','proposaldevelopment.proposaltype.revision','CONFG','5','ProposalTypeCode of REVISION','A','acfe9c78-f644-46a2-8130-2b83ceecebcc');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-PD','D','proposaldevelopment.proposaltype.continuation','CONFG','4','ProposalTypeCode of CONTINUATION','A','395a9837-ca11-485c-96b0-a73b33af3921');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-PD','D','proposaldevelopment.proposaltype.resubmission','CONFG','2','ProposalTypeCode of RESUBMISSION','A','fb4d8d43-32ac-4f75-85bc-417882c65580');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-PD','D','proposaldevelopment.displayKeywordPanel','CONFG','TRUE','Display PROPOSAL Keyword panel','A','12a6021b-16f4-4ee3-9983-8b39149f20a3');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-GEN','A','personrole.kp','CONFG','Key Person','Description of key person for NIH Proposals','A','1edca222-74b0-462e-8f5a-99c53a085651');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-GEN','A','personrole.coi','CONFG','Co-Investigator','Description of co-investigator for NIH Proposals','A','9a801d65-487c-47e8-a167-f1c8354bb789');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-GEN','A','personrole.pi','CONFG','Principal Investigator','Description of principal investigator for NIH Proposals','A','2d4d582a-20b0-4126-9ffe-ec183e069c21');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-PD','D','proposalNarrativeTypeGroup','CONFG','P','Define NARRATIVE Type Group for PROPOSAL Attachments','A','1a3e346f-f0f6-4e38-b73b-215ed46940cc');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-PD','D','instituteNarrativeTypeGroup','CONFG','O','Define NARRATIVE Type Group for Institute Attachments','A','05b6716b-68ec-49a7-855c-6c3c76675ceb');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-PD','D','deliveryInfoDisplayIndicator','CONFG','Y','Flag to display delivery infor panel','A','082a353d-c02d-425a-ab85-9858d3e98599');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-B','D','budgetPersonDefaultJobCode','CONFG','0','The Job Code a new BUDGET Person should default to','A','d6173017-d9d4-4309-b69c-75c111efb04b');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-B','D','budgetPersonDefaultAppointmentType','CONFG','1','The Appointment Type a new BUDGET Person should default to','A','16f1162f-c2bd-4d18-ace4-7c79bfabb19e');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-B','D','budgetPersonDefaultCalculationBase','CONFG','0','The Calculation Base a new BUDGET Person should default to','A','eca249b7-76e0-49a6-bbd0-670b8ffed064');

-- Nervous system params we need
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,TXT,CONS_CD,PARM_DESC_TXT,PARM_TYP_CD,OBJ_ID) VALUES ('KR-NS','Document','SUPERVISOR_GROUP','KUALI_ROLE_SUPERVISOR','A','Workgroup which can perform almost any function within Kuali.','AUTH','28311bc2-7e3c-47dd-847c-ab7732345745'); 
INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD) VALUES('KR-NS', 'Document', 'PESSIMISTIC_LOCK_ADMIN_GROUP', 'fe5bc9af-f97b-41ae-981a-cb32563a90a4', 1, 'AUTH', 'WorkflowAdmin', 'Workgroup which can perform admin deletion and lookup functions for Pessimistic Locks.', 'A'); 
-- End nervous system params

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-B','D','budgetStatusCompleteCode','CONFG','1','Code corresponding to the BUDGET status of Complete','A','87db6adf-7bd3-42ec-9f4a-5c9240b65986');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-B','D','budgetStatusIncompleteCode','CONFG','2','Code corresponding to the BUDGET status of Incomplete','A','ce6dca0c-19b8-4915-b972-9fc1854854aa');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-B','D','defaultOverheadRateClassCode','CONFG','1','The overhead rate class a new BUDGET should default to','A','b22e7eaa-8c75-4438-8434-1d5825a83873');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-B','D','defaultOverheadRateTypeCode','CONFG','1','The overhead rate type a new BUDGET should default to','A','78d72f58-9097-4492-8035-8488cb1e2ef6');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-B','D','defaultUnderrecoveryRateClassCode','CONFG','1','The underrecovery rate class a new BUDGET should default to','A','f534d7ab-6eeb-440c-9a51-11e985eaf11f');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-B','D','defaultModularFlag','CONFG','N','Default value of modular flag for a new Budget.','A','e98947e3-c1f5-40b1-a7eb-474154df46b8');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-PD','D','s2s.revisiontype.other','CONFG','E','RevisionType of Other','A','e162a0c4-a702-46ae-b3a7-ba40d691e2c2');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-PD','D','s2s.submissiontype.changedCorrected','CONFG','3','SubmissionType of Changed/Corrected','A','3db66eff-d472-4157-9889-d23431991c52');

insert into KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD,OBJ_ID) values ('KC-B','D','budgetCurrentFiscalYear','CONFG','07/01/2000',' The starting fiscal year for a budget','A','6457928f-832e-4fc2-bd88-6b61631e765d');
insert into KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD,OBJ_ID) values ('KC-B','D','budgetCostSharingApplicabilityFlag','CONFG','Y',' Flag indicating if Cost Sharing is applicable for the budget','A','d6e7d8ea-07c8-448f-bbae-b070a01fc142');
insert into KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD,OBJ_ID) values ('KC-B','D','budgetUnrecoveredFandAApplicabilityFlag','CONFG','Y',' Flag indicating if Unrecovered &FA is applicable for the budget','A','39ee8471-b6ec-412a-a3c4-3b723d0a5acb');

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, ver_nbr, PARM_TYP_CD, TXT, PARM_DESC_TXT, cons_cd,OBJ_ID) VALUES('KC-B', 'D', 'budgetCostSharingEnforcementFlag', 1, 'CONFG', 'Y', 'Flag indicating if Cost Sharing allocation should be enforced', 'A','b0407efd-f1ce-4604-99db-136629180810');
INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, ver_nbr, PARM_TYP_CD, TXT, PARM_DESC_TXT, cons_cd,OBJ_ID) VALUES('KC-B', 'D', 'budgetUnrecoveredFandAEnforcementFlag',1, 'CONFG', 'Y', 'Flag indicating if Unrecovered F and A allocation should be enforced', 'A','3fc100f7-351f-40ce-9291-b54fcd55f913');

insert into KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, cons_cd,OBJ_ID) values ('KC-B','D','subcontractorFandAGreaterThan25k','CONFG','420630','Cost element code for subcontractor F&A over 25k','A','1ed5e79c-ef96-41d0-84d6-f8ae6ab150e9');
insert into KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, cons_cd,OBJ_ID) values ('KC-B','D','subcontractorFandALessThan25k','CONFG','420610','Cost element code for subcontractor F&A under 25k','A','cb490060-66c8-421d-ab9f-3344cb6e0ef4');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','budgetVersionsHelp','HELP','default.htm?turl=WordDocuments%2Fbudgetversionspage.htm','Budget Page Help','A','e14e46eb-37aa-4385-9df9-caa4dd3af2fd');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','budgetSummaryHelp','HELP','default.htm?turl=WordDocuments%2Fsummarypage.htm','Budget Page Help','A','04d07f99-8748-4238-89e8-0bb7c4b68c7a');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','budgetPersonnelHelp','HELP','default.htm?turl=WordDocuments%2Fprojectpersonnelpage.htm','Budget Page Help','A','a49a194c-a3d0-495e-895d-a12f4b66d5ec');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','budgetRatesHelp','HELP','default.htm?turl=WordDocuments%2Fratespage.htm','Budget Page Help','A','5427b171-2176-42b0-9183-a23eabb2e98f');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','budgetExpensesHelp','HELP','expensespage.htm','Budget Page Help','A','2ac5c941-8290-49bd-b041-674ce6a86f72');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','budgetTotalsHelp','HELP','default.htm?turl=WordDocuments%2Ftotalspage.htm','Budget Page Help','A','6dda3557-c451-4aff-9cf0-161a1a6f9f6d');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','budgetDistributionAndIncomeHelp','HELP','distributionincomepage.htm','Budget Page Help','A','e13d04ad-428f-431b-b2aa-beb109eaa10b');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','budgetModularBudgetHelp','HELP','default.htm?turl=WordDocuments%2Fmodularbudgetpage.htm','Budget Page Help','A','a5e557b0-14d2-4767-badb-1cdecc166a96');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','budgetActionsHelp','HELP','default.htm?turl=WordDocuments%2Fbudgetactionspage.htm','Budget Page Help','A','b406d322-1f51-4e66-ac1e-7f3d49d422ae');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','budgetStatusHelpUrl','HELP','','Budget Status Help','A','44f35deb-a125-4322-bc49-2744ae7e3284');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','budgetPeriodHelpUrl','HELP','default.htm?turl=WordDocuments%2Fbudgetperiodstotalstab.htm','Budget Period Help','A','fcf68b87-b2af-48d0-b895-64c82f64e71e');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','budgetRateClassHelpUrl','HELP','default.htm?turl=WordDocuments%2Frateclasstab.htm','Rate Class Help','A','c872c8dc-3eb7-4f2b-9082-9c2d42f12de8');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','budgetPersonHelpUrl','HELP','default.htm?turl=WordDocuments%2Fprojectpersonnelpage.htm','Budget Person Help','A','70f4bc6c-c100-4c3a-bf34-ca0566b36b55');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','budgetUnrecoveredFandAHelpUrl','HELP','default.htm?turl=WordDocuments%2Funrecoveredfatab.htm','Budget Unrecovered F and A Help','A','b26098dc-6835-42a4-94f0-5917f2c93f7f');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','budgetProjectIncomeHelpUrl','HELP','default.htm?turl=WordDocuments%2Fprojectincometab.htm','Budget Project Income Help','A','8e8c9fe2-1b41-4f4b-b424-658944578878');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','budgetCategoryHelpUrl','HELP','default.htm?turl=WordDocuments%2Fcategorytab.htm','Budget Category Help','A','8186da7f-a7c3-4575-8b75-c0d5098c0eb1');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','budgetCostShareHelpUrl','HELP','default.htm?turl=WordDocuments%2Fbudgetcostsharetab.htm','Budget Page Help','A','c2188957-5b62-450f-bc15-ac38dbaf0166');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','budgetLineItemHelpUrl','HELP','default.htm?turl=WordDocuments%2Fbudgetlineitem.htm','Budget Page Help','A','2049e539-222e-4458-9eba-d14e06b64739');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','budgetbudgetPersonnelDetailsHelpUrl','HELP','default.htm?turl=WordDocuments%2Fpersonneldetailstab.htm','Budget Page Help','A','79a50b9a-c77b-4ee7-bf19-6255077ae256');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','budgetModularHelpUrl','HELP','default.htm?turl=WordDocuments%2Fbudgetmodulartab.htm','Budget Page Help','A','10d3ba9d-a367-4695-adaf-4750b2dc98f0');


insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentKeyPersonnelHelp','HELP','default.htm?turl=WordDocuments%2Fkeypersonnelpage.htm','Proposal Development Page Help','A','70603feb-f137-4436-b92f-088127136ccf');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentSpecialReviewHelp','HELP','default.htm?turl=WordDocuments%2Fspecialreviewpage.htm','Proposal Development Page Help','A','a72646e7-3cda-4493-a1dd-ad0d061be015');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentCustomDataHelp','HELP','default.htm?turl=WordDocuments%2Fcustomdatapage.htm','Proposal Development Page Help','A','191381b3-9d13-433f-90a8-d943b5864790');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentQuestionsHelp','HELP','default.htm?turl=WordDocuments%2Fquestionspage.htm','Proposal Development Page Help','A','880cb602-32d7-4d99-bc8d-883c0cc8d1d4');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentPermissionsHelp','HELP','default.htm?turl=WordDocuments%2Fpermissionspage.htm','Proposal Development Page Help','A','2e39e8f8-ecca-4e3c-98ce-647889ef2e70');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentAbstractsAttachmentsHelp','HELP','default.htm?turl=WordDocuments%2Fabstractsandattachmentspage.htm','Proposal Development Page Help','A','c0575455-9fa1-41e1-8b68-2953f02192fb');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentProposalHelp','HELP','default.htm?turl=WordDocuments%2Fproposalpage.htm','Proposal Development Page Help','A','fad9291d-397e-437c-9850-ca1849b042d0');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentGrantsGovHelp','HELP','default.htm?turl=WordDocuments%2Fgrantsgovpage.htm','Proposal Development Page Help','A','dd8697ab-128a-42b8-ae21-566281faa400');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentActionsHelp','HELP','default.htm?turl=WordDocuments%2Fproposalactionspage.htm','Proposal Development Page Help','A','a65bcd69-4383-4d14-8d60-45347ee741e5');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentSponsorHelpUrl','HELP','default.htm?turl=WordDocuments%2Fsponsorprograminformationtab.htm','Sponsor Help','A','4dbb3766-2db7-457b-ad03-dfa528434d9b');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentOrganizationHelpUrl','HELP','default.htm?turl=WordDocuments%2F?organizationlocationtab.htm','Organization Help','A','dfc5e4e0-0d54-44a0-83fb-44c31488bd2b');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentLocationHelpUrl','HELP','default.htm?turl=WordDocuments%2Forganizationlocationtab.htm','Location Help','A','a4b8a26c-339d-41b8-853b-6152a184160c');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentKeywordsHelpUrl','HELP','default.htm?turl=WordDocuments%2Fkeywordstab.htm','Keywords Help','A','7de4f5a1-ea44-4d6b-a4f8-a32117a1a90d');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentCreditSplitHelpUrl','HELP','default.htm?turl=WordDocuments%2Fcombinedcreditsplittab.htm','Credit Split Help','A','5e4621eb-12f8-439d-8d8b-916317c91338');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentYnqHelpUrl','HELP','','Yes/No Questions Help','A','d282113e-65a1-4beb-819e-8cab6b16cffd');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentPersonHelpUrl','HELP','','Person Help','A','3c0cd336-7035-4191-bbe8-e7e01ddc6c6a');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentOpportunityHelpUrl','HELP','default.htm?turl=WordDocuments%2Fopportunitysection.htm','Grants.gov Opportunity Help','A','7f5afd69-31e1-4446-a150-55123406a222');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-PD','D','proposalDevelopmentNarrativeHelpUrl','HELP','','Narrative Help','A','27bff79f-f0d8-42b4-9bf7-66fb15f22743');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentAbstractHelpUrl','HELP','default.htm?turl=WordDocuments%2Fabstractstab.htm','Abstract Help','A','f2290dbd-d636-4c27-9d16-346542fe2615');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentCustomAttributeHelpUrl','HELP','default.htm?turl=WordDocuments%2Fcustomattributetab.htm','Budget Page Help','A','7a54aa7a-60b2-47f2-94ee-537f0f88ee4d');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentMailByHelpUrl','HELP','default.htm?turl=WordDocuments%2Fmailbytab.htm','Budget Page Help','A','f6510e0d-697f-4ffd-ba76-777830f92614');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentProposalTypeHelpUrl','HELP','default.htm?turl=WordDocuments%2Frequiredfieldstab.htm','Budget Page Help','A','5038e498-8425-462b-823c-16563ccabab4');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentSpecialReviewHelpUrl','HELP','default.htm?turl=WordDocuments%2Fspecialreviewtab.htm','Budget Page Help','A','25635d7c-f477-4461-a7dc-5105ae1c5ddb');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentCopyCriteriaHelpUrl','HELP','default.htm?turl=WordDocuments%2Fcopytab.htm','Proposal Copy Criteria Help','A','dd5a4965-cb55-47e5-b68e-f8d71e83cbbe');

Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KC-GEN','A','sponsorGroupHierarchyName','823CF24F070046C8A65E86EA8EAD9C6B',1,'HELP','Sponsor Groups','Sponsor Group Hierarchy Name','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-PD','D','proposaldevelopment.autogenerate.institutionalproposal','CONFG','Y','Should an Institutional PROPOSAL be automatically generated','A','3c10a8d4-bff9-4687-9871-eec0864afdb9');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','consortiumFnaCostElements','CONFG','420630;420610','Cost elements considered to be consortium F and A','A','d042fb61-59d7-4cbb-8c5e-1d5bc2fa5f85');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B','D','fnaRateClassTypeCode','CONFG','O','Rate class type code for F and A','A','796dcacd-225d-473e-9104-364fbcd1c366');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','proposalDevelopmentDocumentHelp','HELP','default.htm?turl=WordDocuments%2Fproposaldevelopmentdocument.htm','Proposal Development Document Help','A','aa0e0d98-5279-412b-b728-0d557f132c6d');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','initialUnitLoadDepth','CONFG','4','Initial UnitHierarchy Load Depth','A','d56a9ddf-3a6f-475e-bc39-b6f4e5db2486');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-B', 'D','budgetPersonDefaultPeriodType','CONFG', '3','Default Period Type','A','b86e0e79-5ff9-4f54-a5e1-f0f144dec2f8');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','pessimisticLocking.cronExpression','CONFG','0 0 1 ? * *','The Cron Expression for Quartz to activate a clearing of old locks','A','225d56d7-b9d9-45d7-893e-eb5d425832c2');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','pessimisticLocking.expriationAge','CONFG','1440','The expiration timeout in minutes; expired locks are deleted','A','961fde1b-f117-4bac-bbbc-18b70558deb4');

INSERT
INTO KRNS_PARM_T(NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID )
VALUES('KC-PD', 'A', 'numberPerSponsorHierarchyGroup', 'CONFG', '300', 'Number of nodes per SPONSOR group', 'A','961fde1b-f117-4bac-bdbc-18b70558deb4');

insert into KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD,OBJ_ID) values ('KC-B', 'D', 'JOBCODE_VALIDATION_ENABLED', 'CONFG', 'Y', 'Whether Job code based validation is enabled', 'A','b64966c5-fdd4-4bc4-bf11-7f762bd5acd2');

INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  VALUES ('KC-B','D','budgetPersonDetailsDefaultPeriodType','CONFG','3','The Period Type of a newly budgeted Person should default to','A','2c9a6b8c-b736-4161-8124-857cd3bfaa7d');

insert into KRNS_PARM_DTL_TYP_T(NMSPC_CD,PARM_DTL_TYP_CD,NM,OBJ_ID) values ('KC-B','A','All','0b85fa3a-2681-4d6f-9cc9-c1157ac56527');
insert into KRNS_PARM_DTL_TYP_T(NMSPC_CD,PARM_DTL_TYP_CD,NM,OBJ_ID) values ('KC-B','D','Document','03fb0909-e122-4061-8151-e656d58c70da');

insert into KRNS_PARM_T (NMSPC_CD,parm_dtl_typ_cd,parm_nm,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-B','A','instituteRateClassTypes','CONFG','E;I;O;V;X','Manages a list of Institute rate class types.','A','faf11437-c52e-4cb6-97b2-e4e72ace3320');

insert into KRNS_PARM_T (NMSPC_CD,parm_dtl_typ_cd,parm_nm,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-B','A','instituteLaRateClassTypes','CONFG','Y;L','Manages a list of Institute La rate class types.','A','a04b6329-2db2-40b2-966b-da395d45436d');

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD,OBJ_ID)  VALUES ('KC-B', 'D', 'budgetCategoryType.personnel', 'CONFG', 'P', 'Personnel BUDGET Category Type', 'A','c69c18b6-5150-4b12-8013-fb8bc1140dc7');

--INSERT INTO KRNS_PARM_T(NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM)  --VALUES('KR-NS', 'Document', 'PESSIMISTIC_LOCK_ADMIN_GROUP', '9ec127ef-d774-47b4-9df4-69c2140e6449', 1, 'AUTH', 'KUALI_ROLE_SUPERVISOR', 'Workgroup which can perform admin deletion and lookup functions for Pessimistic Locks.', 'A', 'KUALI_FMSOPS');

-- AWARD Parms
INSERT into KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID, VER_NBR)  Values ('KC-AWARD','D','mit.idc.validation.enabled','CONFG','1','MitIdcValidationEnabled is configurable at impl time','A','453473c8-023e-4993-a675-f52c9f578229','1');

INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD)  VALUES ('KC-AWARD','D','federalCapitalizationMinimum','19c1f996-b80a-4b63-af27-3c106ddfb98f',1,'CONFG','100.00','Federal Capitalization Minimum','A');

INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD)  VALUES ('KC-AWARD','D','institutionCapitalizationMinimum','e3539467-6e5a-4dda-866e-01daadb5d1b1',1,'CONFG','50.00','Institution Capitalization Minimum','A');

-- PROTOCOL Parms


Insert into KRNS_PARM_DTL_TYP_T ( NMSPC_CD, PARM_DTL_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) Values ('KC-PROTOCOL','D','1EA4E50A05844D36964A0FCBB2992881',1,'Document', 'Y');

Insert into KRNS_PARM_T ( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD) Values ('KC-PROTOCOL','D','irb.protocol.referenceID1','F1C228F9D4D8408A8E0BBC801C9525ab',1,'CONFG','Reference ID1','Referece id is configurable at impl time','A');

Insert into KRNS_PARM_T ( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD) Values ('KC-PROTOCOL','D','irb.protocol.referenceID2','F1C228F9D4D8408A8E0BBC801C9525az',1,'CONFG','Reference ID2','Referece id is configurable at impl time','A');

Insert into KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD) Values ('KC-PROTOCOL', 'D', 'protocolPersonTrainingSectionRequired', '2347F6B21CAB41DAB20A395611C6ED23', 1, 'CONFG', 'True', 'Implementing institution can decide on whether to display TRAINING section', 'A');

Insert INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD)  VALUES ('KC-AWARD','D','reportClassForPaymentsAndInvoices','0da684eb-a4e8-487e-b93f-30054d45a3c4',1,'CONFG','6','Report Class For Payments And Invoices','A');
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD)  VALUES ('KC-AWARD','D','contactTypeOther','8be8f1bb-973c-4318-b30a-bcea0abceac7',1,'CONFG','8','Contact Type Code For Contact Type Other','A');
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD)  VALUES ('KC-AWARD','D','scheduleGenerationPeriodInYearsWhenFrequencyBaseCodeIsFinalExpirationDate','112bddc2-3229-4920-9424-9037ddc605e8',1,'CONFG','1','Schedule Generation Period In Years When FREQUENCY Base Code Is Final Expiration Date','A');
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD)  VALUES ('KC-AWARD','D','closeoutReportTypeUserDefined','dc88d999-47ec-4f47-a827-3cbc1cb940cd',1,'CONFG','UD','User Defined Close out REPORT Type','A');
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD)  VALUES ('KC-AWARD','D','closeoutReportTypeFinancialReport','7348634f-1e09-4abd-bf54-bc8f42df7f83',1,'CONFG','1','This system parameter maps the CloseoutReportType Financial REPORT(closeoutReoprtTypeCode=1) with ReportClass Fiscal(reportClassCode=1). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.','A');
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD)  VALUES ('KC-AWARD','D','closeoutReportTypeTechnical','f219e14e-684e-4a6e-9ca8-057594836a4b',1,'CONFG','4','This system parameter maps the CloseoutReportType Technical(closeoutReoprtTypeCode=4) with ReportClass Technical Management(reportClassCode=4). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.','A');
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD)  VALUES ('KC-AWARD','D','closeoutReportTypePatent','c3bf6c90-5721-4cc2-8ef1-c42fea897886',1,'CONFG','3','This system parameter maps the CloseoutReportType Patent(closeoutReoprtTypeCode=3) with ReportClass Intellectual Property(reportClassCode=3). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.','A');
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD)  VALUES ('KC-AWARD','D','closeoutReportTypeProperty','1e333d58-29e0-449e-acbf-8b8e0de53ed9',1,'CONFG','2','This system parameter maps the CloseoutReportType Property(closeoutReoprtTypeCode=2) with ReportClass Property(reportClassCode=2). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.','A');
Insert into KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD) Values ('KC-PROTOCOL','D','irb.protocol.billable', 'F1C228F9D4D8408A8E0BBC801C9525ak', 1,'CONFG','Y','Billable is configurable at impl time','A');

Insert into KRNS_PARM_T ( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD) Values ('KC-PROTOCOL','D','irb.protocol.award.linking.enabled', '9ec14c1c-7cfb-4162-a434-b2c12c6e0760', 1,'CONFG','Y','Linking from AWARD to PROTOCOL Funding source is configurable at impl time','A');

Insert into KRNS_PARM_T ( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD) Values ('KC-PROTOCOL','D','irb.protocol.development.proposal.linking.enabled', 'de7fdb58-b3fc-49f6-89af-13b7e547596c', 1,'CONFG','Y','Linking from Development Proposal to PROTOCOL Funding source is configurable at impl time','A');

Insert into KRNS_PARM_T ( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD) Values ('KC-PROTOCOL','D','irb.protocol.institute.proposal.linking.enabled', 'ed1716e6-a21d-4284-baf6-d84a8793070d', 1,'CONFG','Y','Linking from Institute Proposal to PROTOCOL Funding source is configurable at impl time','A');

-- krew
--INSERT INTO KRNS_NMSPC_T(NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, APPL_NMSPC_CD) --VALUES('KR-WKFLW', '5E1D690C419B3E2EE0404F8189D82677', 0, 'Workflow', 'Y', NULL);
INSERT INTO KRNS_PARM_DTL_TYP_T(NMSPC_CD, PARM_DTL_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND)  VALUES ('KR-WKFLW', 'DocumentSearch', '18695E69ED0D4FBE8B084FCA8066D21C', 1, 'Document Search ', 'Y');

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD) VALUES ('KC-AWARD', 'D', 'award.creditsplit.enabled', 'cd9d4449-91d5-43fb-9e44-6f6027ab5edd', 1, 'CONFG', 'Y', 'Determines whether the Credit Split is turned on for Award', 'A');

Insert into KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) Values ('KC-PD', 'D', 'PROPOSAL_CONTACT_TYPE', '8de694ee-0ba0-4ce3-ac9e-baf832766de4', 1, 'CONFG', '2', 'Value for PROPOSAL Contact Type', 'A');

Insert into KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) Values('KC-PD', 'D', 'MULTI_CAMPUS_ENABLED', '2e677f3d-4d9a-444e-883b-b4b3b6ae836a', 1, 'CONFG', '0', 'Flag for enabling/disabling Multicampus', 'A');

Insert into KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) Values ('KC-PD', 'D', 'DHHS_AGREEMENT', 'df3ef743-67cd-4f7f-863e-5095ec282e50', 1, 'CONFG', '0', 'Value for DHHS Agreement', 'A');

Insert into KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) Values ('KC-PD', 'D', 'SCHEDULER_SERVICE_ENABLED', 'fb1829e1-094e-4e32-987c-be0db41a8a32', 1, 'CONFG', '0', 'Flag for enabling/disabling scheduler service', 'A');

Insert into KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) Values ('KC-PROTOCOL', 'D', 'IRB_COMM_SELECTION_DURING_SUBMISSION', '3aa4347c-2fa4-4972-8be5-57388799c6a0', 1, 'CONFG', 'O', 'Implementing institution can decide to allow committee/schedule/reviewers to be selected upon an IRB submission.', 'A');


insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-IP','D','proposalcommenttype.generalcomment','CONFG','16','Code for General PROPOSAL Comment Type','A','514e5659-84b8-42e1-9686-20a72fb911ae');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-IP','D','proposalcommenttype.reviewercomment','CONFG','17','Code for IP Reviewer PROPOSAL Comment Type','A','2928e2b3-8f63-4dac-b331-7b154ade08c5');

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD) VALUES ('KC-IP', 'D', 'institutionalproposal.creditsplit.enabled', 'd7de3475-6496-40fc-a55a-47ddf1e99abc', 1, 'CONFG', 'N', 'Determines whether the Credit Split is turned on for IP', 'A');

insert into KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) Values ('KC-PD', 'D', 'GENERIC_SPONSOR_CODE', '4515bac3-0167-4bbf-94f4-f737f3247e0c', 1, 'CONFG', '009800', 'Generic SPONSOR code used for printing SPONSOR form', 'A');

INSERT INTO KRNS_NMSPC_T(NMSPC_CD, NM, ACTV_IND, APPL_NMSPC_CD, OBJ_ID) VALUES ('KC-WKFLW', 'KC Workflow Infrastructure', 'Y', NULL, '9d6c41dd-964d-4af9-8be9-7a4f7b693006');

INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-IP','D','InstitutionalProposalHelpUrl','f02524cb-c127-42d6-88a2-81d9b30f4039',1,'HELP','default.htm','Institutional PROPOSAL Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-IP','D','InstitutionalProposalConstactsHelpUrl','63f40471-6a94-40d1-aa2d-30357153c83e',1,'HELP','default.htm','Institutional PROPOSAL Contacts Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-IP','D','InstitutionalProposalCustomDataHelpUrl','8159f87a-382b-4a5a-bfb6-0d5ff86cd316',1,'HELP','default.htm','Institutional PROPOSAL Custom Data Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-IP','D','InstitutionalProposalSpecialReviewHelpUrl','335eb9d7-5464-4129-ba67-438871ed9aa7',1,'HELP','default.htm','Institutional PROPOSAL Special Review Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-IP','D','InstitutionalProposalIPReviewHelpUrl','6ac8ce28-120c-40ae-bb6b-f72879d3144b',1,'HELP','default.htm','Institutional PROPOSAL Intellectual Property Review Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-IP','D','InstitutionalProposalDistributionHelpUrl','c8996799-887a-4a51-958f-f7df944b6d5e',1,'HELP','default.htm','Institutional PROPOSAL DISTRIBUTION Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-IP','D','InstitutionalProposalActionsHelpUrl','87eb73d7-bf75-4e4c-bd9f-55727959e168',1,'HELP','default.htm','Institutional PROPOSAL Actions Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-IP','D','IntellectualPropertyReviewActivityHelpUrl','64e773a3-cb7f-4d85-ad2f-625249920584',1,'HELP','default.htm','Institutional PROPOSAL Intellectual Property Reivew Activity Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-IP','D','InstitutionalProposalUnitAdministratorHelpUrl','5a166800-e959-4efe-88f6-16f750fcac90',1,'HELP','default.htm','Institutional PROPOSAL UNIT Administrator Help','A'); 
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardHelpUrl','9b05331a-a577-4673-ab42-2c86aa890774',1,'HELP','default.htm','Award Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardHomeHelp','0aea0e88-e4da-4739-b11f-5bdbb516e033',1,'HELP','default.htm','Award Home Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardContactsHelp','a907f1ab-8f52-4d32-8eb8-ab93b6b2c881',1,'HELP','default.htm','Award Contacts Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardCommitmentsHelp','1ec94373-eac3-4491-a9af-79fd9a54d93f',1,'HELP','default.htm','Award Commitments Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardTimeAndMoneyHelp','ae525e2a-ea85-45a7-8ac0-d9d7766c396c',1,'HELP','default.htm','Award Time and Money Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardPaymentsReportsAndTermsHelp','65ad375e-6bc1-496d-b799-09e765bfc2b6',1,'HELP','default.htm','Award Payments Reports and Terms Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardSpecialReviewHelp','66d3eda4-ee25-406d-a2f8-302a08ee9db0',1,'HELP','default.htm','Award Special Review Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardCustomDataHelp','adfb2268-106a-4f05-85ca-81cb072bd872',1,'HELP','default.htm','Award Custom Data Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardQuestionsHelp','90f9525a-78c9-45b0-a5f9-5bdcd94272df',1,'HELP','default.htm','Award Questions Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardPermissionsHelp','ff1fddb0-9a93-4234-8371-b083b780d9b1',1,'HELP','default.htm','Award Permissions Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardNoteAndAttachmentsHelp','a3aae861-cb01-438f-b0fd-95499588a3cd',1,'HELP','default.htm','Award Note and Attachments Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardActionsHelp','3cff7fd7-7ea9-4eaf-9f7a-8ee0d59a7836',1,'HELP','default.htm','Award Actions Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardApprovedEquipmentHelpUrl','e1f01099-d397-4cb2-961f-614c187a5d29',1,'HELP','default.htm','Award Approved Equipment Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardApprovedForeignTravelHelpUrl','93864f04-090e-489e-ae27-827afac60ff9',1,'HELP','default.htm','Award Approved Foreign Travel Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardApprovedSubawardHelpUrl','4cdcbf8b-43ec-4a67-89f0-1693d534189f',1,'HELP','default.htm','Award Approved Subaward Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardCommentHelpUrl','380e1466-ebc8-4f9f-b6fc-278bb8ad91fd',1,'HELP','default.htm','Award Comment Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardContactHelpUrl','9d4a1b31-49fd-4cce-aeeb-2cefb7263abf',1,'HELP','default.htm','Award Contact Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardCostShareHelpUrl','15021c0b-dbfc-4f10-a474-8f2683afdc6b',1,'HELP','default.htm','Award Cost Share Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardCustomDataHelpUrl','868f6947-ed66-4645-af3e-48e60ede6ec7',1,'HELP','default.htm','Award Custom Data Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardPersonCreditSplitHelpUrl','ac324538-5d3c-4fcf-b2b4-e9f31529c257',1,'HELP','default.htm','Award Person Credit Split Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','CommentTypeHelpUrl','5cd74625-06ee-4cd3-88d2-ee5e53742704',1,'HELP','default.htm','Award Comment Type Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','CostShareTypeHelpUrl','b16d4427-2b1f-4743-8771-7e4fb2316c23',1,'HELP','default.htm','Award Cost Share Type Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardFandaRateHelpUrl','dd64012d-85b1-4dc4-a1a0-f9aae452b945',1,'HELP','default.htm','Award F and A Rate Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardReportTermHelpUrl','459b51d1-19e7-4943-9e68-c418065ae3ae',1,'HELP','default.htm','Award REPORT Term Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardCloseoutHelpUrl','87597d21-46de-4822-810c-adfeaebf3f59',1,'HELP','default.htm','Award REPORT Term Help','A'); 
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-AWARD','D','awardAttachmentsHelpUrl','7ad8e408-096a-4452-b698-a5de773d1e00',1,'HELP','default.htm','Award Attachments Help','A'); 
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-T','D','timeAndMoneyHelp','a6e34291-5f6a-423f-9880-5b7239d00846',1,'HELP','default.htm','Time And Money Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-T','D','TransactionHelp','e5ea07b1-48f8-4e33-b0bc-b84dbef0c646',1,'HELP','default.htm','Transaction Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-T','D','PendingTransactionHelp','b942e8e0-1f0f-4524-83f2-b989cd2234ec',1,'HELP','default.htm','Pending Transaction Help','A');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-T','D','awardHierarchyNodeHelpUrl','67fbf2d6-4ef0-4ab5-aa1c-c9adf9999b55',1,'HELP','default.htm','Award Hierarchy Help','A'); 
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-T','D','awardHierarchyHelpUrl','9840e24e-f271-4e39-9308-d77ecd051b27',1,'HELP','default.htm','Award Hierarchy Help','A'); 
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-GEN','D','permissionsHelpUrl','5a37cdd6-a918-451f-9fcf-3f8bafc6deb2',1,'HELP','default.htm','Institutional PROPOSAL Intellectual Property Reivew Activity Help','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','A','SCHOOL_NAME','CONFG','Kuali Coeus','School Name','A','680e4304-ff27-49cd-8797-e8dfe37ae2b3');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','A','SCHOOL_ACRONYM','CONFG','KC','School acronym','A','b6726fce-906e-4e0e-a3a4-d066ff5c7288');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-AWARD','A','ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST','CONFG','2143','obligated direct indirect cost','A','fd940dd9-5227-45f5-a918-f4dd4a00d57d');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','A','FELLOWSHIP_OSP_ADMIN','CONFG','qucikStart','Fellowship admin name','A','0920b4d0-958a-4f83-8a69-105fb64f936f'); 

insert into KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, obj_id, ver_nbr, parm_typ_cd, txt, parm_desc_txt, cons_cd) VALUES ('KC-GEN', 'A', 'sponsorLevelHierarchy', '5183FC8FA0B11606E0404F8189D8140F', 1, 'HELP', 'NIH', 'Sponsor Level Hierarchy', 'A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','s2sschedulercronExpressionstarttime','CONFG','01-JAN-2010 00:00 AM','Starttime for s2s scheduler cron job to start','A','d658a498-6455-44ed-b457-63db92331e78');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)  values ('KC-PD','D','PI_CITIZENSHIP_FROM_CUSTOM_DATA','CONFG','01-JAN-2010 00:00 AM','It defines where the citizenship info should fetch from','A','8cf0972b-5bc1-4b50-96ea-12bb41825be8');
INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-PD','D','FEDERAL_ID_COMES_FROM_CURRENT_AWARD','73aa5808-4e2c-4463-ab8f-1e2950113ae2',1,'CONFG','N','Determines whether the Grants.Gov Federal ID must be populated from the current award.','A');

Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-IDM','EntityNameImpl','PREFIXES','61645D045B0105D7E0404F8189D849B1',1,'CONFG','Ms;Mrs;Mr;Dr',null,'A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-IDM','EntityNameImpl','SUFFIXES','61645D045B0205D7E0404F8189D849B1',1,'CONFG','Jr;Sr;Mr;Md',null,'A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','Batch','ACTIVE_FILE_TYPES','5A689075D35E7AEBE0404F8189D80321',1,'CONFG','collectorInputFileType;procurementCardInputFileType;enterpriseFeederFileSetType;assetBarcodeInventoryInputFileType;customerLoadInputFileType','Batch file types that are active options for the file upload screen.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','All','CHECK_ENCRYPTION_SERVICE_OVERRIDE_IND','53680C68F59AAD9BE0404F8189D80A6C',1,'CONFG','Y','Flag for enabling/disabling (Y/N) the demonstration encryption check.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','ScheduleStep','CUTOFF_TIME','5A689075D35C7AEBE0404F8189D80321',1,'CONFG','02:00:00:AM','Controls when the daily batch schedule should terminate. The scheduler service implementation compares the start time of the schedule job from quartz with this time on day after the schedule job started running.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','ScheduleStep','CUTOFF_TIME_NEXT_DAY_IND','5A689075D35D7AEBE0404F8189D80321',1,'CONFG','Y','Controls whether when the system is comparing the schedule start day with the scheduleStep_CUTOFF_TIME parameter, it considers the specified time to apply to the day after the schedule starts.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','Document','DEFAULT_CAN_PERFORM_ROUTE_REPORT_IND','53680C68F59EAD9BE0404F8189D80A6C',1,'CONFG','N','If Y, the Route REPORT button will be displayed on the document actions bar if the document is using the default DocumentAuthorizerBase.getDocumentActionFlags to set the canPerformRouteReport property of the returned DocumentActionFlags instance.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','All','ENABLE_DIRECT_INQUIRIES_IND','53680C68F59BAD9BE0404F8189D80A6C',1,'CONFG','Y','Flag for enabling/disabling direct inquiries on screens that are drawn by the nervous system (i.e. lookups and maintenance documents)','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','All','ENABLE_FIELD_LEVEL_HELP_IND','53680C68F59CAD9BE0404F8189D80A6C',1,'CONFG','N','Indicates whether field level help links are enabled on lookup pages and documents.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','PurgePendingAttachmentsStep','MAX_AGE','5A689075D35A7AEBE0404F8189D80321',1,'CONFG','86400','Pending attachments are attachments that do not yet have a permanent link with the associated Business Object (BO). These pending attachments are stored in the attachments.pending.directory (defined in the configuration service). If the BO is never persisted, then this attachment will become orphaned (i.e. not associated with any BO), but will remain in this directory. The PurgePendingAttachmentsStep batch step deletes these pending attachment files that are older than the value of this parameter. The UNIT of this value is seconds. Do not set this value too short, as this will cause problems attaching files to BOs.','A','KUALI');
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
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','ActionList','ACTION_LIST_DOCUMENT_POPUP_IND','290E45BA032F4F4FB423CE5F78AC52E1',1,'CONFG','N','Flag to specify if clicking on a Document ID from the Action List will load the Document in a new window.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','ActionList','ACTION_LIST_ROUTE_LOG_POPUP_IND','967B0311A5E94F7191B2C544FA7DE095',1,'CONFG','N','Flag to specify if clicking on a Route Log from the Action List will load the Route Log in a new window.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','Rule','CACHING_IND','E05A692D62E54B87901D872DC37208A1',1,'CONFG','Y','Indicator to determine if rule caching is enabled.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','Rule','CUSTOM_DOCUMENT_TYPES','BDE964269F2743338C00A4326B676195',1,'CONFG',null,'Defines custom Document Type processes to use for certain types of routing rules.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','EDocLite','DEBUG_TRANSFORM_IND','68B2EA08E13A4FF3B9EDBD5415818C93',1,'CONFG','N','Defines whether the debug transform is enabled for eDcoLite.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','Rule','DELEGATE_LIMIT','21EA54B9A9E846709E76C176DE0AF47C',1,'CONFG','20','Specifies that maximum number of delegation rules that will be displayed on a Rule inquiry before the screen shows a count of delegate rules and provides a link for the user to show them.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','DocSearchCriteriaDTO','DOCUMENT_SEARCH_POPUP_IND','E78100F6F14C4932B54F7719FA5C27E9',1,'CONFG','N','Flag to specify if clicking on a Document ID from Document Search will load the Document in a new window.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','ActionList','EMAIL_NOTIFICATION_TEST_ADDRESS','340789CDF30F4252A1A2A42AD39B90B2',1,'CONFG',null,'Default email address used for testing.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','DocSearchCriteriaDTO','DOCUMENT_SEARCH_ROUTE_LOG_POPUP_IND','632680DDE9A7478CBD379FAF90C7AE72',1,'CONFG','N','Flag to specify if clicking on a Route Log from Document Search will load the Route Log in a new window.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','DocSearchCriteriaDTO','FETCH_MORE_ITERATION_LIMIT','D43459D143FC46C6BF83C71AC2383B76',1,'CONFG',null,'Limit of fetch more iterations for document searches.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','Mailer','FROM_ADDRESS','700AB6A6E23740D0B3E00E02A8FB6347',1,'CONFG','quickstart@localhost','Default from email address for notifications.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','Rule','GENERATE_ACTION_REQUESTS_IND','96868C896B4B4A8BA87AD20E42948431',1,'CONFG','Y','Flag to determine whether or not a change to a routing rule should be applied retroactively to existing documents.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','Feature','IS_LAST_APPROVER_ACTIVATE_FIRST_IND','BEBDBCFA74A5458EADE2CF075FFF206E',1,'CONFG',null,'A flag to specify whether the WorkflowInfo.isLastApproverAtNode(...) API method attempts to active requests first, prior to execution.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','All','KIM_PRIORITY_ON_DOC_TYP_PERMS_IND','5C731F2968A3689AE0404F8189D86653',1,'CONFG','N','Flag for enabling/disabling document type permission checks to use KIM Permissions as priority over Document Type policies.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','All','MAXIMUM_NODES_BEFORE_RUNAWAY','4656B6E7E9844E2C9E2255014AFC86B5',1,'CONFG',null,'The maximum number of nodes the workflow engine will process before it determines the process is a runaway process. This is prevent infinite "loops" in the workflow engine.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','Notification','NOTIFY_EXCLUDED_USERS_IND','08280F2575904F3586CF48BB97907506',1,'CONFG',null,'Defines whether or not to send a notification to users excluded from a workgroup.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','ActionList','PAGE_SIZE_THROTTLE','2CE075BC0C59435CA6DEFF724492DE3F',1,'CONFG',null,'Throttles the number of results returned on all users Action Lists, regardless of their user preferences. This is intended to be used in a situation where excessively large Action Lists are causing performance issues.','A','KUALI');
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
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','All','SENSITIVE_DATA_PATTERNS','5a5fbe94-846f-102c-8db0-c405cae621f3',1,'CONFG','[0-9]{9};[0-9]{3}-[0-9]{2}-[0-9]{4}','A semi-colon delimted list of regular expressions that identify potentially sensitive data in strings. These patterns will be matched against notes, document explanations, and routing annotations.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','All','DEFAULT_COUNTRY','64B87B4C5E3B8F4CE0404F8189D8291A',1,'CONFG','US','Used as the default country code when relating records that do not have a country code to records that do have a country code, e.g. validating a zip code where the country is not collected.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-IDM','Document','MAX_MEMBERS_PER_PAGE','2238b58e-8fb9-102c-9461-def224dad9b3',1,'CONFG','20','The maximum number of ROLE or group members to display at once on their documents. If the number is above this value, the document will switch into a paging mode with only this many rows displayed at a time.','A','KUALI');
Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-NS','Document','ALLOW_ENROUTE_BLANKET_APPROVE_WITHOUT_APPROVAL_REQUEST_IND','74382C90B076049FE0404F8189D84C5D',1,'CONFG','N','Controls whether the nervous system will show the blanket approve button to a user who is authorized for blanket approval but is neither the initiator of the particular document nor the recipient of an active, pending, approve action request.','A','KUALI');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-B','D','awardBudgetEbRateClassCode','CONFG','5','The EB rate class code to be used for AWARD BUDGET if the rates are overridden on commitements tab','A','0628aaa3-8008-4c50-95bd-4bb0fada17cf');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-B','D','awardBudgetEbRateTypeCode','CONFG','6','The EB rate type code to be used for AWARD BUDGET if the rates are overridden on commitements tab','A','359f0f98-c1ab-44d0-a38d-2b495333b4c9');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-B','D','defaultFnARateClassCode','CONFG','1','The OH rate class code to be used for AWARD BUDGET if the fna rates are overridden on commitements tab','A','120e206f-df5f-4951-816b-1f76fc46da08');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-B','D','AWARD_BUDGET_TYPE_NEW_PARAMETER','CONFG','1','Default AWARD BUDGET status code','A','0d685c9d-0369-4f75-a551-51485b8f6718');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-B','D','AWARD_BUDGET_STATUS_IN_PROGRESS_CODE','CONFG','1','Default AWARD BUDGET type code','A','d38ae7e2-0c98-46c5-aead-4028e682a04c');

insert into KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD) values ('KUALI', 'KC-PD', 'D', 'DEFAULT_BIOGRAPHY_DOCUMENT_TYPE_CODE', '81237ef9-9d29-4a07-9fb8-ebd8aaddc834', 1, 'CONFG', '1', 'Value of the default biography document type code. This is the document type code that will be used when adding new users to a Proposal Development Document when they have an attached Biosketch file.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'ACTIVITY_TYPE_CODE_RESEARCH', 'D084A864C3C6465398E60E659B54D453',  1, 'CONFG',  '1', 'Code corresponding to Activity Type: Research.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'ACTIVITY_TYPE_CODE_INSTRUCTION', '1AF410B92C9E459C8C55CCFDF6E2BDD4',  1, 'CONFG',  '2', 'Code corresponding to Activity Type: Instruction.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'ACTIVITY_TYPE_CODE_PUBLIC_SERVICE', '51FA88CA7C034E8DBFCE3E1F6AE45565',  1, 'CONFG',  '3', 'Code corresponding to Activity Type: Public Service.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'ACTIVITY_TYPE_CODE_CLINICAL_TRIAL', '76B706A1176D4CEA855E9A31CAD18616',  1, 'CONFG',  '4', 'Code corresponding to Activity Type: Clinical Trial.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'ACTIVITY_TYPE_CODE_OTHER', 'BD3992B5D91E45A1B75DAAE167775382',  1, 'CONFG',  '5', 'Code corresponding to Activity Type: Other.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'ACTIVITY_TYPE_CODE_FELLOWSHIP_PRE_DOCTORAL', '3237E8333E7741799E7803E53AEB43A5',  1, 'CONFG',  '6', 'Code corresponding to Activity Type: Fellowship - Pre-Doctoral.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'ACTIVITY_TYPE_CODE_FELLOWSHIP_POST_DOCTORAL', 'ED1011EC3B444E998FAB1DF2ADFF69B4',  1, 'CONFG',  '7', 'Code corresponding to Activity Type: Fellowship - Post-Doctoral.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'ACTIVITY_TYPE_CODE_STUDENT_SERVICES', '4F537D424A8C435CBE14C48973B04DAE',  1, 'CONFG',  '8', 'Code corresponding to Activity Type: Student Services.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'ACTIVITY_TYPE_CODE_CONSTRUCTION', 'B75A13942BBB4E8FA98BBAE3D291BA51',  1, 'CONFG',  '9', 'Code corresponding to Activity Type: Construction.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'S2S_SUBMISSION_TYPE_CODE_PREAPPLICATION', 'DA775361D8B54A5DA57B8EE194709681',  1, 'CONFG',  '1', 'Code corresponding to S2S Submission Type: Pre-Application.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'S2S_SUBMISSION_TYPE_CODE_APPLICATION', 'BDBAD3A2EABD4A709158937192328405',  1, 'CONFG',  '2', 'Code corresponding to S2S Submission Type: Application.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'S2S_SUBMISSION_TYPE_CODE_CHANGE_CORRECTED_APPLICATION', '2F098F859FC645158026966E1CB6089F',  1, 'CONFG',  '3', 'Code corresponding to S2S Submission Type: Change/Corrected Application.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'PROPOSAL_TYPE_CODE_NEW', 'BBB7108561504EA4B00492BC2F6000B6',  1, 'CONFG',  '1', 'Code corresponding to Proposal Type: New.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'PROPOSAL_TYPE_CODE_RESUBMISSION', 'B8F2F9191E3A4805B6E53C2081D79F5C',  1, 'CONFG',  '2', 'Code corresponding to Proposal Type: Resubmission.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'PROPOSAL_TYPE_CODE_RENEWAL', 'B98EA0B6469542E0B075465BF3956962',  1, 'CONFG',  '3', 'Code corresponding to Proposal Type: Renewal.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'PROPOSAL_TYPE_CODE_CONTINUATION', 'FB2E720179774B2390277577ACCF95AD',  1, 'CONFG',  '4', 'Code corresponding to Proposal Type: Continuation.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'PROPOSAL_TYPE_CODE_REVISION', 'FD7990B7D8494E0F8D42EA140F5CCE04',  1, 'CONFG',  '5', 'Code corresponding to Proposal Type: Revision.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'PROPOSAL_TYPE_CODE_TASK_ORDER', '2BBF0A99B4A74CA68A7E372F48CBC0CC',  1, 'CONFG',  '6', 'Code corresponding to Proposal Type: Task Order.',  'A');

INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-GEN','A','NSF_SPONSOR_CODE','5183FC8FA0B21606E0404F8189D8140F',1,'CONFG','000500','The sponsor code of NSF.','A');

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-IP','D','PROPOSAL_LOG_PENDING_STATUS_CODE','CONFG','1','Code corresponding to Proposal Log status code Pending','A','2FE3959CF9BE4A7891E2EB4E553AF2FC');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-IP','D','PROPOSAL_LOG_MERGED_STATUS_CODE','CONFG','2','Code corresponding to Proposal Log status code Merged','A','C59FC3C0F5F844F88B516A633ED3A57A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-IP','D','PROPOSAL_LOG_SUBMITTED_STATUS_CODE','CONFG','3','Code corresponding to Proposal Log status code Submitted','A','30D4C11B6953463CAA01285AA5CA2E68');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-IP','D','PROPOSAL_LOG_VOID_STATUS_CODE','CONFG','4','Code corresponding to Proposal Log status code Void','A','B56527B8F5B24CA694B52E18E4A0E8DA');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-IP','D','PROPOSAL_LOG_TEMPORARY_STATUS_CODE','CONFG','5','Code corresponding to Proposal Log status code Temporary','A','1F94B00C936A438281CC53062F30F993');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-IP','D','PROPOSAL_LOG_PERMANENT_TYPE_CODE','CONFG','1','Code corresponding to Proposal Log type code Permanent','A','A6CD53052C4E4325A80F0D9B8265511D');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC-IP','D','PROPOSAL_LOG_TEMPORARY_TYPE_CODE','CONFG','2','Code corresponding to Proposal Log type code Temporary','A','D8BDAA693EE44FF5A78A623C11A0F809');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolProtocolHelp',1,'HELP','default.htm?turl=Documents/protocol1.htm','Protocol Page Help','A','62681381F5DB429FAC427F4F4A35C3AB');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolRequiredFieldsHelpUrl',1,'HELP','default.htm?turl=Documents/requiredfieldsforsavingdocument.htm','Protocol Required Fields for Saving Document Help','A','776F0D47EAFB474185BC9C9383DB6D25');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolStatusAndDatesHelpUrl',1,'HELP','default.htm?turl=Documents/statusdates1.htm','Protocol Status and Dates Help','A','2D31BDDBAAE64A4080C2D3254095BA62');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolAreaOfResearchHelpUrl',1,'HELP','default.htm?turl=Documents/areaofresearch.htm','Protocol Area of Research Help','A','6F4D5501FFC94B94A4F83C2DD8CA850C');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolAdditionalInformationHelpUrl',1,'HELP','default.htm?turl=Documents/additionalinformation1.htm','Protocol Additional Information Help','A','A1107E15A3B442BEB23CF38F6363742F');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolOtherIdentifiersHelpUrl',1,'HELP','default.htm?turl=Documents/otheridentifiers.htm','Protocol Other Identifiers Help','A','61CACCE09818497EBFB856A35CF349D2');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolOrganizationsHelpUrl',1,'HELP','default.htm?turl=Documents/organizations.htm','Protocol Organizations Help','A','FBF354C0EA8149599842014D985FB7D2');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolFundingSourcesHelpUrl',1,'HELP','default.htm?turl=Documents/fundingsources.htm','Protocol Funding Sources Help','A','66C554CBCE3C4EC2AC5AC0C22FB78C9E');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolParticipantTypesHelpUrl',1,'HELP','default.htm?turl=Documents/participanttypes.htm','Protocol Participant Types Help','A','90EFDFC57AB6439BA56861B216C7A3AC');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolPersonnelHelp',1,'HELP','default.htm?turl=Documents/personnel.htm','Protocol Personnel Page Help','A','491EA51A75674CA48C953093475E3A83');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolPersonnelHelpUrl',1,'HELP','default.htm?turl=Documents/addpersonnel.htm','Protocol Add Personnel Help','A','4F2167D3B2C64B78995763C72EC544F9');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolQuestionnaireHelp',1,'HELP','default.htm?turl=Documents/questionnaire.htm','Protocol Questionnaire Page Help','A','C2BF427CEB824202BEBB752CB69A4194');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolCustomDataHelp',1,'HELP','default.htm?turl=Documents/customdata1.htm','Protocol Custom Data Page Help','A','002256983F7948C18DC7A04A625D79BD');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolSpecialReviewHelp',1,'HELP','default.htm?turl=Documents/specialreview.htm','Protocol Special Review Page Help','A','3697C8AF76BC488EA42CE0E416FA4E76');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolPermissionsHelp',1,'HELP','default.htm?turl=Documents/permissions1.htm','Protocol Permissions Page Help','A','46064E3EF5EE4BD4B26C2CEC90175A86');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolNotesAndAttachmentsHelp',1,'HELP','default.htm?turl=Documents/notesattachments.htm','Protocol Notes and Attachments Page Help','A','CD5836F621D349BD9AB2ADD6A26F6DAB');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolAddProtocolAttachmentHelpUrl',1,'HELP','default.htm?turl=Documents/protocolattachments.htm','Protocol Add Protocol Attachments Help','A','A9909E9902DC4439BEF3C1D6A03A061D');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolAddPersonnelAttachmentHelpUrl',1,'HELP','default.htm?turl=Documents/personnelattachments.htm','Protocol Add Personnel Attachments Help','A','D83F68A55AEE4239A89E5AB519CBA846');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolNotesHelpUrl',1,'HELP','default.htm?turl=Documents/notes.htm','Protocol Notes Help','A','F2012927FED742BAABDB3E4C8FCFCCCC');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolActionsHelp',1,'HELP','default.htm?turl=Documents/protocolactions.htm','Protocol Actions Page Help','A','0621D61D4D794A6885A2972D5CED3AD7');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolAvailableActionsHelpUrl',1,'HELP','default.htm?turl=Documents/requestanaction.htm','Protocol Available Actions Help','A','9CA94CAF7D144927A99063000D6BDB99');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-QUESTIONNAIRE','D','questionnaireQuestionnaireHelp',1,'HELP','default.htm?turl=Documents/questionnaire1.htm','Questionnaire Page Help','A','A4A84A5E68C4432C8338418733EB0EC3');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-QUESTIONNAIRE','D','questionnaireQuestionHelpUrl',1,'HELP','default.htm?turl=Documents/question.htm','Questionnaire Question Help','A','B266CFC3582F4E8FB84EB60F267C8988');

INSERT INTO krns_nmspc_t (NMSPC_CD, VER_NBR, NM, ACTV_IND, OBJ_ID) 
VALUES ('KC-COMMITTEE', '1', 'KC IRB Committee', 'Y', 'A43CB5C126D94A07BE7212BDA202F560');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeCommitteeHelp',1,'HELP','default.htm?turl=Documents/committeecommittee.htm','Committee Page Help','A','F0999EC012964F4BB57F4D129071403D');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeCommitteeHelpUrl',1,'HELP','default.htm?turl=Documents/committeecommittee1.htm','Committee Help','A','56CD4B6359D642F587078F80250F19CC');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeAreaOfResearchHelpUrl',1,'HELP','default.htm?turl=Documents/committeeareaofresearch.htm','Committee Area of Research Help','A','7BDB765D7BDE4BC4B408F2F157F859ED');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeMembershipHelp',1,'HELP','default.htm?turl=Documents/committeemembership.htm','Committee Membership Page Help','A','26186A68AE5E4880806C447D7A7DCD88');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeMembershipHelpUrl',1,'HELP','default.htm?turl=Documents/committeemembership1.htm','Committee Membership Help','A','7D85BAB7634F45EE94EF26697443FA68');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeScheduleHelp',1,'HELP','default.htm?turl=Documents/committeeschedule.htm','Committee Schedule Page Help','A','51512FB764F949B781522E4AF2171BB4');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeScheduleHelpUrl',1,'HELP','default.htm?turl=Documents/committeeschedule1.htm','Committee Schedule Help','A','66ED9C579FAE4C23AED7B6640BE1B2DC');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeActionsHelp',1,'HELP','default.htm?turl=Documents/committeeactions.htm','Committee Actions Page Help','A','871829A661E447CABDCE39D25A273488');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeBatchCorrespondenceHelpUrl',1,'HELP','default.htm?turl=Documents/committeebatchcorrespondence.htm','Committee Batch Correspondence Help','A','EE5A4B68412B41CE8D5F08A9ECADDDB3');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingDetailsHelpUrl',1,'HELP','default.htm?turl=Documents/meetingdetails.htm','Meeting Details Help','A','943058F9A11848E5AA5D512136D0F2B4');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingProtocolSubmittedHelpUrl',1,'HELP','default.htm?turl=Documents/meetingprotocolsubmitted.htm','Meeting Protocol Submitted Help','A','F5E740C6320A4FB6B265A3452E2915B9');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingOtherActionsHelpUrl',1,'HELP','default.htm?turl=Documents/meetingotheractions.htm','Meeting Other Actions Help','A','0416E7DF9759442FAE4585D147F8098B');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingAttendanceHelpUrl',1,'HELP','default.htm?turl=Documents/meetingattendance.htm','Meeting Attendance Help','A','51864582F4C0496493282D83C202E773');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingMinutesHelpUrl',1,'HELP','default.htm?turl=Documents/meetingminutes.htm','Meeting Minutes Help','A','097CABACFCBD4D49A1C1734652935F78');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingGenerateAgendaHelpUrl',1,'HELP','default.htm?turl=Documents/meetinggenerateagenda.htm','Meeting Generate Agenda Help','A','3B8AC9D238264166B4B58AD6448832D0');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingGenerateMinutesHelpUrl',1,'HELP','default.htm?turl=Documents/meetinggenerateminutes.htm','Meeting Generate Minutes Help','A','F4CB139711294181B779AE890D4E6E72');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingCorrespondenceHelpUrl',1,'HELP','default.htm?turl=Documents/meetingcorrespondence.htm','Meeting Correspondence Help','A','77E4785ECD764FDF8753300A97829F15');