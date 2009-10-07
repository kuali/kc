INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-AWARD', 'D', 'award.creditsplit.enabled', SYS_GUID () , 1, 'CONFG', 'Y', 'Determines whether the Credit Split is turned on for Award', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-AWARD', 'D', 'closeoutReportTypeUserDefined', SYS_GUID () , 1, 'CONFG', 'UD', 'User Defined Close out Report Type', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-AWARD', 'D', 'closeoutReportTypeFinancialReport', SYS_GUID () , 1, 'CONFG', '1', 'This system parameter maps the CloseoutReportType Financial Report(closeoutReoprtTypeCode=1) with ReportClass Fiscal(reportClassCode=1). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-AWARD', 'D', 'closeoutReportTypeTechnical', SYS_GUID () , 1, 'CONFG', '4', 'This system parameter maps the CloseoutReportType Technical(closeoutReoprtTypeCode=4) with ReportClass Technical Management(reportClassCode=4). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-AWARD', 'D', 'closeoutReportTypePatent', SYS_GUID () , 1, 'CONFG', '3', 'This system parameter maps the CloseoutReportType Patent(closeoutReoprtTypeCode=3) with ReportClass Intellectual Property(reportClassCode=3). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-AWARD', 'D', 'closeoutReportTypeProperty', SYS_GUID () , 1, 'CONFG', '2', 'This system parameter maps the CloseoutReportType Property(closeoutReoprtTypeCode=2) with ReportClass Property(reportClassCode=2). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, ACTV_IND)
VALUES('KC-IP', 'D', 'proposalcommenttype.generalcomment', 'CONFG', '1', 'Code for General Proposal Comment Type', 'A', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, ACTV_IND)
VALUES('KC-IP', 'D', 'proposalcommenttype.reviewercomment', 'CONFG', '1', 'Code for IP Reviewer Proposal Comment Type', 'A', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KRA-PD', 'D', 'DHHS_AGREEMENT', SYS_GUID () , 1, 'CONFG', '0', 'Value for DHHS Agreement', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KRA-PD', 'D', 'MULTI_CAMPUS_ENABLED', SYS_GUID () , 1, 'CONFG', '0', 'Flag for enabling/disabling Multicampus', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KRA-PD', 'D', 'PROPOSAL_CONTACT_TYPE', SYS_GUID () , 1, 'CONFG', '2', 'Value for Proposal Contact Type', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KRA-PD', 'D', 'SCHEDULER_SERVICE_ENABLED', SYS_GUID () , 1, 'CONFG', '0', 'Value for enabling s2s polling service', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-PROTOCOL', 'D', 'irb.protocol.award.linking.enabled', SYS_GUID () , 1, 'CONFG', 'Y', 'Linking from Award to Protocol Funding source is configurable at impl time', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-PROTOCOL', 'D', 'irb.protocol.billable', 'F1C228F9D4D8408A8E0BBC801C9525ak', 1, 'CONFG', 'Y', 'Billable is configurable at impl time', 'A', 'WorkflowAdmin', 'Y') ; 

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-PROTOCOL', 'D', 'irb.protocol.development.proposal.linking.enabled', SYS_GUID () , 1, 'CONFG', 'Y', 'Linking from Award to Protocol Funding source is configurable at impl time', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-PROTOCOL', 'D', 'irb.protocol.institute.proposal.linking.enabled', SYS_GUID () , 1, 'CONFG', 'N', 'Linking from Award to Protocol Funding source is configurable at impl time', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-PROTOCOL', 'D', 'irb.protocol.referenceID1', 'F1C228F9D4D8408A8E0BBC801C9525ab', 1, 'CONFG', 'Reference ID1', 'Referece id is configurable at impl time', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-PROTOCOL', 'D', 'irb.protocol.referenceID2', 'F1C228F9D4D8408A8E0BBC801C9525az', 1, 'CONFG', 'Reference ID2', 'Referece id is configurable at impl time', 'A', 'WorkflowAdmin', 'Y') ; 

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-PROTOCOL', 'D', 'IRB_COMM_SELECTION_DURING_SUBMISSION', SYS_GUID () , 1, 'CONFG', 'O', 'Implementing institution can decide to allow committee/schedule/reviewers to be selected upon an IRB submission.', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND, OBJ_ID, VER_NBR)
VALUES ('KC-AWARD', 'D', 'mit.idc.validation.enabled', 'CONFG', '1', 'MitIdcValidationEnabled is configurable at impl time', 'A', 'WorkflowAdmin', 'Y', SYS_GUID () , '1') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-QUESTIONNAIRE', 'P', 'associateModuleQuestionnairePermission', 'CONFG', 'MODIFY_PROPOSAL;MODIFY_PROTOCOL', 'List of permissions that are allowed to associate a module with questionnaire.', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KRA-PD', 'D', 'GENERIC_SPONSOR_CODE', SYS_GUID () , 1, 'CONFG', '009800', 'Generic sponsor code used for printing sponsor form', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KRA-PD', 'D', 'proposaldevelopment.autogenerate.institutionalproposal', 'CONFG', 'Y', 'Should an Institutional Proposal be automatically generated', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-IP', 'D', 'InstitutionalProposalHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Institutional Proposal Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-IP', 'D', 'InstitutionalProposalConstactsHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Institutional Proposal Contacts Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-IP', 'D', 'InstitutionalProposalCustomDataHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Institutional Proposal Custom Data Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-IP', 'D', 'InstitutionalProposalSpecialReviewHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Institutional Proposal Special Review Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-IP', 'D', 'InstitutionalProposalIPReviewHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Institutional Proposal Intellectual Property Review Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-IP', 'D', 'InstitutionalProposalDistributionHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Institutional Proposal Distribution Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-IP', 'D', 'InstitutionalProposalActionsHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Institutional Proposal Actions Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-IP', 'D', 'InstitutionalProposalIPReviewActivityHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Institutional Proposal Intellectual Property Reivew Activity Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardHomeHelp', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Home Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardContactsHelp', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Contacts Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardCommitmentsHelp', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Commitments Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardTimeAndMoneyHelp', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Time and Money Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardPaymentsReportsAndTermsHelp', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Payments Reports and Terms Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardSpecialReviewHelp', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Special Review Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardCustomDataHelp', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Custom Data Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardQuestionsHelp', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Questions Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardPermissionsHelp', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Permissions Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardNoteAndAttachmentsHelp', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Note and Attachments Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardActionsHelp', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Actions Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardApprovedEquipmentHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Approved Equipment Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardApprovedForeignTravelHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Approved Foreign Travel Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardApprovedSubawardHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Approved Subaward Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardCommentHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Comment Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardContactHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Contact Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardCostShareHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Cost Share Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardCustomDataHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Custom Data Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardPersonCreditSplitHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Person Credit Split Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'CommentTypeHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Comment Type Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'CostShareTypeHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Cost Share Type Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardFandaRateHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award F and A Rate Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardReportTermHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Report Term Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardCloseoutHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Report Term Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardAttachmentsHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Attachments Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-T', 'D', 'timeAndMoneyHelp', SYS_GUID () , 1, 'HELP', 'default.htm', 'Time And Money Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-T', 'D', 'TransactionHelp', SYS_GUID () , 1, 'HELP', 'default.htm', 'Transaction Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-T', 'D', 'PendingTransactionHelp', SYS_GUID () , 1, 'HELP', 'default.htm', 'Pending Transaction Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-T', 'D', 'awardHierarchyNodeHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Hierarchy Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-T', 'D', 'awardHierarchyHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Award Hierarchy Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-GEN', 'D', 'permissionsHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Institutional Proposal Intellectual Property Reivew Activity Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-IP', 'D', 'InstitutionalProposalUnitAdministratorHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm', 'Institutional Proposal Unit Administrator Help', 'A') ;

COMMIT;