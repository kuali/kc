INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AB', 'D', 'awardBudgetStatusInProgress', 'CONFG', '1', 'This system parameter maps the AwardBudget status In Progress', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AB', 'D', 'awardBudgetStatusSubmitted', 'CONFG', '5', 'This system parameter maps the AwardBudget status Submitted', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AB', 'D', 'awardBudgetStatusRejected', 'CONFG', '8', 'This system parameter maps the AwardBudget status Rejected', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AB', 'D', 'awardBudgetStatusToBePosted', 'CONFG', '10', 'This system parameter maps the AwardBudget status To Be Posted', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AB', 'D', 'awardBudgetStatusPosted', 'CONFG', '9', 'This system parameter maps the AwardBudget status Posted', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AB', 'D', 'awardBudgetStatusErrorInPosting', 'CONFG', '11', 'This system parameter maps the AwardBudget status Error In Posting', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AB', 'D', 'AWARD_BUDGET_POST_ENABLED', 'CONFG', '1', 'This system parameter enables on demand Award Budget Posting', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AB', 'D', 'awardBudgetTypeNew', 'CONFG', '1', 'This system parameter maps the AwardBudget type New', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AB', 'D', 'awardBudgetTypeRebudget', 'CONFG', '2', 'This system parameter maps the AwardBudget type Rebudget', 'A') ;

INSERT INTO krns_parm_t (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
VALUES('KUALI', 'KC-AB', 'D', 'awardBudgetStatusDoNotPost', 1, 'CONFG', '12', 'This system parameter maps the AwardBudget status Do Not Post', 'A', '8220798A9106418787B7A3F038796D13') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardHomeHelp', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/awarddocument.htm', 'Award Home Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardContactsHelp', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/awarddocument.htm', 'Award Contacts Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardCommitmentsHelp', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/commitmentstab.htm', 'Award Commitments Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardTimeAndMoneyHelp', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/timeandmoneysubpanel.htm', 'Award Time and Money Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardPaymentsReportsAndTermsHelp', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/awardpaymentschedulesection.htm', 'Award Payments Reports and Terms Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardSpecialReviewHelp', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/specialreviewpanel.htm', 'Award Special Review Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardCustomDataHelp', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/kcawardtabs.htm', 'Award Custom Data Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardQuestionsHelp', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/awarddocument.htm', 'Award Questions Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardNoteAndAttachmentsHelp', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/commentsnotesattachmentstab.htm', 'Award Note and Attachments Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardActionsHelp', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/awarddocument.htm', 'Award Actions Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardApprovedEquipmentHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/specialapprovalpanel.htm', 'Award Approved Equipment Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardApprovedForeignTravelHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/specialapprovalpanel.htm', 'Award Approved Foreign Travel Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardApprovedSubawardHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/subawardpanel.htm', 'Award Approved Subaward Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardCommentHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/awarddocument.htm', 'Award Comment Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardContactHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/awarddocument.htm', 'Award Contact Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardCostShareHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/awarddocument.htm', 'Award Cost Share Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardCustomDataHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/kcawardtabs.htm', 'Award Custom Data Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardPersonCreditSplitHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/awarddocument.htm', 'Award Person Credit Split Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'CommentTypeHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/commenttype.htm', 'Award Comment Type Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'CostShareTypeHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/costsharingtype.htm', 'Award Cost Share Type Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardFandaRateHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/faratessubpanel.htm', 'Award F and A Rate Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardReportTermHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/awarddocument.htm', 'Award Report Term Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardCloseoutHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/awarddocument.htm', 'Award Report Term Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardAttachmentsHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/attachmentspanel.htm', 'Award Attachments Help', 'A') ;

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

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND, OBJ_ID, VER_NBR)
VALUES ('KC-AWARD', 'D', 'mit.idc.validation.enabled', 'CONFG', '1', 'MitIdcValidationEnabled is configurable at impl time', 'A', 'WorkflowAdmin', 'Y', SYS_GUID () , '1') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'awardHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/awarddocument.htm', 'Award Help', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES('KC-AWARD', 'D', 'reportClassForPaymentsAndInvoices', SYS_GUID () , 1, 'CONFG', '6', 'Report Class For Payments And Invoices', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES('KC-AWARD', 'D', 'federalCapitalizationMinimum', SYS_GUID () , 1, 'CONFG', '0.00', 'Federal Capitalization Minimum', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES('KC-AWARD', 'D', 'institutionCapitalizationMinimum', SYS_GUID () , 1, 'CONFG', '0.00', 'Institution Capitalization Minimum', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES('KC-PROTOCOL', 'D', 'protocolPersonTrainingSectionRequired', '2347F6B21CAB41DAB20A395611C6ED23', 1, 'CONFG', 'True', 'Implementing institution can decide on whether to display training section', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES('KC-AWARD', 'D', 'scheduleGenerationPeriodInYearsWhenFrequencyBaseCodeIsFinalExpirationDate', SYS_GUID () , 1, 'CONFG', '1', 'Schedule Generation Period In Years When Frequency Base Code Is Final Expiration Date', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES('KC-AWARD', 'D', 'contactTypeOther', SYS_GUID () , 1, 'CONFG', '8', 'Contact Type Code For Contact Type Other', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST', 'CONFG', '2143', 'obligated direct indirect cost', 'A') ;

--comment types  
INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'scope.sync.PAYMENTS_AND_INVOICES_TAB.AwardComment.commentTypeCode', 'CONFG', '1', 'Comma delimited list of comment type codes to sync on the Payments and Invoices Tab.', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'scope.sync.COMMENTS_TAB.AwardComment.commentTypeCode', 'CONFG', '2,3,4,5,6', 'Comma delimited list of comment type codes to sync on the Comments Tab.', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'scope.sync.COST_SHARE.AwardComment.commentTypeCode', 'CONFG', '9', 'Comma delimited list of comment type codes to sync on the Cost Share Tab.', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'scope.sync.PREAWARD_AUTHORIZATIONS_TAB.AwardComment.commentTypeCode', 'CONFG', '18,19', 'Comma delimited list of comment type codes to sync on the PreAward Authorizations Tab.', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'scope.sync.RATES_TAB.AwardComment.commentTypeCode', 'CONFG', '20', 'Comma delimited list of comment type codes to sync on the Rates Tab.', 'A') ;

--Report TermS  
INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'scope.sync.PAYMENTS_AND_INVOICES_TAB.AwardReportTerm.reportClassCode', 'CONFG', '1,2,3,4,5,7', 'Comma delimited list of reportClassCodes for reports to sync on the Reports tab.', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'scope.sync.REPORTS_TAB.AwardReportTerm.reportClassCode', 'CONFG', '6', 'Comma delimited list of reportClassCodes for reports to sync on the Payments and Invoices tab.', 'A') ;

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD) 
VALUES ('KUALI', 'KC-AWARD', 'D', 'TXN_TYPE_DEF_COPIED_AWARD', 'CONFG', '9', 'New Transaction', 'A');

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-GEN', 'D', 'permissionsHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/permissionstab.htm', 'Institutional Proposal Intellectual Property Reivew Activity Help', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-GEN', 'DocumentType', 'customAttributeDocumentType', 'CONFG', 'AWRD=Award;INPR=Institutional Proposal;PRDV=Proposal Development;PROT=Protocol', 'List of Custom Attribute Document type name.', 'A') ;

UPDATE KRNS_PARM_T 
SET NMSPC_CD='KC-GEN', 
    PARM_DTL_TYP_CD='A',
    PARM_TYP_CD = 'CONFG'
    TXT='Sponsor Groups'
WHERE NMSPC_CD = 'KRA-PD'
      AND PARM_DTL_TYP_CD = 'D'
      AND PARM_NM = 'sponsorGroupHierarchyName';

UPDATE KRNS_PARM_T 
SET NMSPC_CD='KC-GEN', 
    PARM_DTL_TYP_CD='A',
    PARM_TYP_CD = 'CONFG'
    TXT='NIH'
WHERE NMSPC_CD = 'KRA-PD'
      AND PARM_DTL_TYP_CD = 'D'
      AND PARM_NM = 'sponsorLevelHierarchy';

UPDATE KRNS_PARM_T 
SET NMSPC_CD='KC-GEN', 
    PARM_DTL_TYP_CD='A', 
    PARM_NM='personrole.pi',
    PARM_DESC_TXT = 'Description of principal investigator for Non-NIH Proposals' 
WHERE NMSPC_CD = 'KRA-PD'
      AND PARM_DTL_TYP_CD = 'D'
      AND PARM_NM = 'proposaldevelopment.personrole.pi';

UPDATE KRNS_PARM_T 
SET NMSPC_CD='KC-GEN', 
    PARM_DTL_TYP_CD='A', 
    PARM_NM='personrole.nih.pi',
    PARM_DESC_TXT = 'Description of principal investigator contact for NIH Proposals' 
WHERE NMSPC_CD = 'KRA-PD'
      AND PARM_DTL_TYP_CD = 'D'
      AND PARM_NM = 'proposaldevelopment.personrole.nonnih.pi';

UPDATE KRNS_PARM_T 
SET NMSPC_CD='KC-GEN', 
    PARM_DTL_TYP_CD='A', 
    PARM_NM='personrole.kp',
    PARM_DESC_TXT = 'Description of key person for Non-NIH Proposals' 
WHERE NMSPC_CD = 'KRA-PD'
      AND PARM_DTL_TYP_CD = 'D'
      AND PARM_NM = 'proposaldevelopment.personrole.kp';

UPDATE KRNS_PARM_T 
SET NMSPC_CD='KC-GEN', 
    PARM_DTL_TYP_CD='A', 
    PARM_NM='personrole.nih.kp',
    PARM_DESC_TXT = 'Description of key person for NIH Proposals' 
WHERE NMSPC_CD = 'KRA-PD'
      AND PARM_DTL_TYP_CD = 'D'
      AND PARM_NM = 'proposaldevelopment.personrole.nonnih.kp';

UPDATE KRNS_PARM_T 
SET NMSPC_CD='KC-GEN', 
    PARM_DTL_TYP_CD='A', 
    PARM_NM='personrole.coi',
    PARM_DESC_TXT = 'Description of co-investigator for Non-NIH Proposals' 
WHERE NMSPC_CD = 'KRA-PD'
      AND PARM_DTL_TYP_CD = 'D'
      AND PARM_NM = 'proposaldevelopment.personrole.coi';

UPDATE KRNS_PARM_T 
SET NMSPC_CD='KC-GEN', 
    PARM_DTL_TYP_CD='A', 
    PARM_NM='personrole.nih.coi',
    PARM_DESC_TXT = 'Description of principal investigator multiple for NIH Proposals' 
WHERE NMSPC_CD = 'KRA-PD'
      AND PARM_DTL_TYP_CD = 'D'
      AND PARM_NM = 'proposaldevelopment.personrole.nonnih.coi';

UPDATE KRNS_PARM_T 
SET NMSPC_CD='KC-GEN', 
    PARM_DTL_TYP_CD='A', 
    PARM_NM='personrole.readonly.roles' 
WHERE NMSPC_CD = 'KRA-PD'
      AND PARM_DTL_TYP_CD = 'D'
      AND PARM_NM = 'proposaldevelopment.personrole.readonly.roles';

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-IP', 'D', 'InstitutionalProposalUnitAdministratorHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/institutionalproposaldocument.htm', 'Institutional Proposal Unit Administrator Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-IP', 'D', 'InstitutionalProposalHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/institutionalproposaldocument.htm', 'Institutional Proposal Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-IP', 'D', 'InstitutionalProposalConstactsHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/institutionalproposaldocument.htm', 'Institutional Proposal Contacts Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-IP', 'D', 'InstitutionalProposalCustomDataHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/customdatatab2.htm', 'Institutional Proposal Custom Data Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-IP', 'D', 'InstitutionalProposalSpecialReviewHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/specialreviewtab1.htm', 'Institutional Proposal Special Review Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-IP', 'D', 'InstitutionalProposalIPReviewHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/intellectualpropertyreviewtab.htm', 'Institutional Proposal Intellectual Property Review Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-IP', 'D', 'InstitutionalProposalDistributionHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/distributiontab.htm', 'Institutional Proposal Distribution Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-IP', 'D', 'InstitutionalProposalActionsHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/institutionalproposalactionstab.htm', 'Institutional Proposal Actions Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-IP', 'D', 'InstitutionalProposalIPReviewActivityHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/institutionalproposaldocument.htm', 'Institutional Proposal Intellectual Property Reivew Activity Help', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, ACTV_IND)
VALUES('KC-IP', 'D', 'proposalcommenttype.generalcomment', 'CONFG', '16', 'Code for General Proposal Comment Type', 'A', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, ACTV_IND)
VALUES('KC-IP', 'D', 'proposalcommenttype.reviewercomment', 'CONFG', '17', 'Code for IP Reviewer Proposal Comment Type', 'A', 'Y') ;

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-IP', 'D', 'institutionalproposal.creditsplit.enabled', SYS_GUID(), 1, 'CONFG', 'Y', 'Determines whether the Credit Split is turned on for Institutional Proposal', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-PROTOCOL', 'D', 'irb.protocol.award.linking.enabled', SYS_GUID () , 1, 'CONFG', 'N', 'Linking from Award to Protocol Funding source is configurable at impl time', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-PROTOCOL', 'D', 'irb.protocol.billable', 'F1C228F9D4D8408A8E0BBC801C9525ak', 1, 'CONFG', 'Y', 'Billable is configurable at impl time', 'A', 'WorkflowAdmin', 'Y') ; 

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-PROTOCOL', 'D', 'irb.protocol.development.proposal.linking.enabled', SYS_GUID () , 1, 'CONFG', 'N', 'Linking from Award to Protocol Funding source is configurable at impl time', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-PROTOCOL', 'D', 'irb.protocol.institute.proposal.linking.enabled', SYS_GUID () , 1, 'CONFG', 'N', 'Linking from Award to Protocol Funding source is configurable at impl time', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-PROTOCOL', 'D', 'irb.protocol.referenceID1', 'F1C228F9D4D8408A8E0BBC801C9525ab', 1, 'CONFG', 'Reference ID1', 'Referece id is configurable at impl time', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-PROTOCOL', 'D', 'irb.protocol.referenceID2', 'F1C228F9D4D8408A8E0BBC801C9525az', 1, 'CONFG', 'Reference ID2', 'Referece id is configurable at impl time', 'A', 'WorkflowAdmin', 'Y') ; 

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-PROTOCOL', 'D', 'IRB_COMM_SELECTION_DURING_SUBMISSION', SYS_GUID () , 1, 'CONFG', 'O', 'Implementing institution can decide to allow committee/schedule/reviewers to be selected upon an IRB submission.', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-QUESTIONNAIRE', 'P', 'associateModuleQuestionnairePermission', 'CONFG', 'Modify ProposalDevelopmentDocument:KRA-PD;Modify Protocol:KC-PROTOCOL', 'List of permissions that are allowed to associate a module with questionnaire.', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-T', 'D', 'timeAndMoneyHelp', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/timeandmoneysubpanel.htm', 'Time And Money Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-T', 'D', 'TransactionHelp', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/transactionspanel.htm', 'Transaction Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-T', 'D', 'PendingTransactionHelp', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/transactionspanel.htm', 'Pending Transaction Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-T', 'D', 'awardHierarchyNodeHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/awardhierarchypanel.htm', 'Award Hierarchy Help', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-T', 'D', 'awardHierarchyHelpUrl', SYS_GUID () , 1, 'HELP', 'default.htm?turl=Documents/awardhierarchypanel.htm', 'Award Hierarchy Help', 'A') ;

UPDATE KRNS_PARM_T T 
SET T.TXT='N' 
WHERE T.PARM_NM IN('ACTION_LIST_DOCUMENT_POPUP_IND', 'ACTION_LIST_ROUTE_LOG_POPUP_IND', 'DOCUMENT_SEARCH_POPUP_IND', 'DOCUMENT_SEARCH_ROUTE_LOG_POPUP_IND')
                  AND T.APPL_NMSPC_CD = 'KUALI';
                  
UPDATE KRNS_PARM_T 
SET TXT='4',NMSPC_CD = 'KC-PD'
WHERE NMSPC_CD = 'KRA-PD'
      AND PARM_DTL_TYP_CD = 'D'
      AND PARM_NM = 'proposaldevelopment.proposaltype.continuation';
      
UPDATE KRNS_PARM_T 
SET PARM_DTL_TYP_CD='D' ,NMSPC_CD = 'KC-PD'
WHERE NMSPC_CD = 'KRA-PD'
      AND PARM_DTL_TYP_CD = 'A'
      AND PARM_NM = 'initialUnitLoadDepth';

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-B', 'D', 'proposalHierarchySubProjectDirectCostElement', 'CONFG', 'PHTD01', 'The Cost Element to be used for the Direct Cost sub-project summary line items in a Proposal Hierarchy budget', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-B', 'D', 'proposalHierarchySubProjectIndirectCostElement', 'CONFG', 'PHTID02', 'The Cost Element to be used for the Indirect Cost sub-project summary line items in a Proposal Hierarchy budget', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-B', 'D', 'awardBudgetEbRateClassCode', 'CONFG', '5', 'The EB rate class code to be used for award budget if the eb rates are overridden on commitements tab', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-B', 'D', 'awardBudgetEbRateTypeCode', 'CONFG', '6', 'The EB rate type code to be used for award budget if the eb rates are overridden on commitements tab', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-B', 'D', 'defaultFnARateClassCode', 'CONFG', '1', 'The OH rate class code to be used for award budget if the fna rates are overridden on commitements tab', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-B', 'D', 'AWARD_BUDGET_STATUS_IN_PROGRESS_CODE', 'CONFG', '1', 'Default award budget status code', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-B', 'D', 'AWARD_BUDGET_TYPE_NEW_PARAMETER', 'CONFG', '1', 'Default award budget type code', 'A') ;

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'DEFAULT_BIOGRAPHY_DOCUMENT_TYPE_CODE', '81237ef9-9d29-4a07-9fb8-ebd8aaddc834', 1, 'CONFG', '1', 'Value of the default biography document type code. This is the document type code that will be used when adding new users to a Proposal Development Document and they have an attached Biosketch file.', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-PD', 'D', 'proposaldevelopment.autogenerate.institutionalproposal', 'CONFG', 'Y', 'Should an Institutional Proposal be automatically generated', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-PD', 'D', 'DHHS_AGREEMENT', SYS_GUID () , 1, 'CONFG', '0', 'Value for DHHS Agreement', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-PD', 'D', 'MULTI_CAMPUS_ENABLED', SYS_GUID () , 1, 'CONFG', '0', 'Flag for enabling/disabling Multicampus', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-PD', 'D', 'PROPOSAL_CONTACT_TYPE', SYS_GUID () , 1, 'CONFG', '6', 'Value for Proposal Contact Type', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
VALUES ('KC-PD', 'D', 'SCHEDULER_SERVICE_ENABLED', SYS_GUID () , 1, 'CONFG', '0', 'Value for enabling s2s polling service', 'A', 'WorkflowAdmin', 'Y') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-PD', 'D', 'GENERIC_SPONSOR_CODE', SYS_GUID () , 1, 'CONFG', '009800', 'Generic sponsor code used for printing sponsor form', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-PD', 'D', 'SCHOOL_NAME', 'CONFG', 'Kuali Coeus', 'School Name', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-PD', 'D', 'SCHOOL_ACRONYM', 'CONFG', 'KC', 'School acronym', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-AWARD', 'D', 'FELLOWSHIP_OSP_ADMIN', 'CONFG', 'qucikStart', 'Fellowship admin name', 'A') ;

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES ('KUALI', 'KC-PD', 'D', 's2sschedulercronExpressionstarttime', '22f44dbf-23b6-4aa9-9d72-f83a227eeedd', 1, 'CONFG', '01-JAN-2010 01:00 AM', 'Start Time expression for the S2S Polling Process. The S2S Polling Process will only start if this parameters date is before today. Must be formatted as "dd-MMM-yyyy hh:mm a". For example "01-JAN-2010 01:00 AM".', 'A') ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-PD', 'D', 'PI_CITIZENSHIP_FROM_CUSTOM_DATA', 'CONFG', '1', 'It defines where the citizenship info should fetch from', 'A') ;

INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-PD', 'D', 'FEDERAL_ID_COMES_FROM_CURRENT_AWARD', SYS_GUID () , 1, 'CONFG', 'N', 'Determines whether the Grants.Gov Federal ID must be populated from the current award.', 'A') ;

INSERT INTO krns_parm_t (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-PD', 'D', 'proposaldevelopment.proposaltype.resubmission', SYS_GUID () , 1, 'CONFG', '2', 'ProposalTypeCode of RESUBMISSION', 'A') ;

-- Activity Types
INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'ACTIVITY_TYPE_CODE_RESEARCH',  '1', 'CONFG',  '1', 'Code corresponding to Activity Type: Research.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'ACTIVITY_TYPE_CODE_INSTRUCTION',  '1', 'CONFG',  '2', 'Code corresponding to Activity Type: Instruction.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'ACTIVITY_TYPE_CODE_PUBLIC_SERVICE',  '1', 'CONFG',  '3', 'Code corresponding to Activity Type: Public Service.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'ACTIVITY_TYPE_CODE_CLINICAL_TRIAL',  '1', 'CONFG',  '4', 'Code corresponding to Activity Type: Clinical Trial.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'ACTIVITY_TYPE_CODE_OTHER',  '1', 'CONFG',  '5', 'Code corresponding to Activity Type: Other.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'ACTIVITY_TYPE_CODE_FELLOWSHIP_PRE_DOCTORAL',  '1', 'CONFG',  '6', 'Code corresponding to Activity Type: Fellowship - Pre-Doctoral.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'ACTIVITY_TYPE_CODE_FELLOWSHIP_POST_DOCTORAL',  '1', 'CONFG',  '7', 'Code corresponding to Activity Type: Fellowship - Post-Doctoral.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'ACTIVITY_TYPE_CODE_STUDENT_SERVICES',  '1', 'CONFG',  '8', 'Code corresponding to Activity Type: Student Services.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'ACTIVITY_TYPE_CODE_CONSTRUCTION',  '1', 'CONFG',  '9', 'Code corresponding to Activity Type: Construction.', 'A');

-- S2S Submission Types
INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'S2S_SUBMISSION_TYPE_CODE_PREAPPLICATION',  '1', 'CONFG',  '1', 'Code corresponding to S2S Submission Type: Pre-Application.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'S2S_SUBMISSION_TYPE_CODE_APPLICATION',  '1', 'CONFG',  '2', 'Code corresponding to S2S Submission Type: Application.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'S2S_SUBMISSION_TYPE_CODE_CHANGE_CORRECTED_APPLICATION',  '1', 'CONFG',  '3', 'Code corresponding to S2S Submission Type: Change/Corrected Application.', 'A');

-- Proposal Types
INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'PROPOSAL_TYPE_CODE_NEW',  '1', 'CONFG',  '1', 'Code corresponding to Proposal Type: New.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'PROPOSAL_TYPE_CODE_RESUBMISSION',  '1', 'CONFG',  '2', 'Code corresponding to Proposal Type: Resubmission.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'PROPOSAL_TYPE_CODE_RENEWAL',  '1', 'CONFG',  '3', 'Code corresponding to Proposal Type: Renewal.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'PROPOSAL_TYPE_CODE_CONTINUATION',  '1', 'CONFG',  '4', 'Code corresponding to Proposal Type: Continuation.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'PROPOSAL_TYPE_CODE_REVISION',  '1', 'CONFG',  '5', 'Code corresponding to Proposal Type: Revision.', 'A');

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KUALI', 'KC-PD', 'D', 'PROPOSAL_TYPE_CODE_TASK_ORDER',  '1', 'CONFG',  '6', 'Code corresponding to Proposal Type: Task Order.',  'A');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-M','D','sponsorHierarchyHelp',1,'HELP','default.htm?turl=Documents%2Fsponsorhierarchy.htm','Sponsor Hierarchy Help','A','51FFE3D4890B4A9293E46CE58385B536');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-M','D','sponsorHierarchyCreateNewHelp',1,'HELP','default.htm?turl=Documents%2Fsponsorhierarchy.htm','Sponsor Hierarchy Help','A','A169E3D48f8C4A9293E46CE58385B536');

UPDATE KRNS_PARM_T 
SET NMSPC_CD='KC-PD' 
WHERE NMSPC_CD = 'KRA-PD';

UPDATE KRNS_PARM_T 
SET NMSPC_CD='KC-B' 
WHERE NMSPC_CD = 'KRA-B';

UPDATE KRNS_PARM_T 
SET NMSPC_CD='KC-M' 
WHERE NMSPC_CD = 'KRA-M';

DELETE FROM KRNS_PARM_T 
WHERE APPL_NMSPC_CD='KUALI' 
AND NMSPC_CD='KC-PD' 
AND PARM_DTL_TYP_CD='D' 
AND PARM_NM='creditSplitEnabled';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/abstracttype.htm' 
WHERE PARM_NM = 'abstractTypeMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/activitytype.htm' 
WHERE PARM_NM = 'activityTypeMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/budgetactionstab.htm' 
WHERE PARM_NM = 'budgetActionsHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/personneldetailpanel.htm' 
WHERE PARM_NM = 'budgetbudgetPersonnelDetailsHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/budgetcategory.htm' 
WHERE PARM_NM = 'budgetCategoryHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/budgetcategory.htm' 
WHERE PARM_NM = 'budgetCategoryMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/budgetcategorymaps.htm' 
WHERE PARM_NM = 'budgetCategoryMapMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/budgetcategorymapping.htm' 
WHERE PARM_NM = 'budgetCategoryMappingMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/budgetcategorytype.htm' 
WHERE PARM_NM = 'budgetCategoryTypeMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/costsharingpanel.htm' 
WHERE PARM_NM = 'budgetCostShareHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/distributionincometab.htm' 
WHERE PARM_NM = 'budgetDistributionAndIncomeHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/budgetdocument.htm' 
WHERE PARM_NM = 'budgetDocumentHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/expensestabactionbutton.htm' 
WHERE PARM_NM = 'budgetExpensesHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/lineitemdetailssubpanel.htm' 
WHERE PARM_NM = 'budgetLineItemHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/modularbudgettab.htm' 
WHERE PARM_NM = 'budgetModularBudgetHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/modularbudgetoverviewpanel.htm' 
WHERE PARM_NM = 'budgetModularHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/modularbudgetoverviewpanel.htm' 
WHERE PARM_NM = 'budgetModularIdcHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/nonpersonneltab.htm' 
WHERE PARM_NM = 'budgetNonPersonnelHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/parameterstab.htm' 
WHERE PARM_NM = 'budgetParametersHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/budgetperiodstotalspanel.htm' 
WHERE PARM_NM = 'budgetPeriodHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/budgetperiodstotals.htm' 
WHERE PARM_NM = 'budgetPersonHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/personneldetailpanel.htm' 
WHERE PARM_NM = 'budgetPersonnelDetailsHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/personneltab.htm' 
WHERE PARM_NM = 'budgetPersonnelHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/projectincomepanel.htm' 
WHERE PARM_NM = 'budgetProjectIncomeHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/rateclassessubpanelontheobjectcodenamesubpanel.htm' 
WHERE PARM_NM = 'budgetRateClassHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/rateclassessubpanelontheobjectcodenamesubpanel.htm' 
WHERE PARM_NM = 'budgetRatesHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/budgetstatus.htm' 
WHERE PARM_NM = 'budgetStatusHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/budgetstatus.htm' 
WHERE PARM_NM = 'budgetStatusMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/summarypanel.htm' 
WHERE PARM_NM = 'budgetSummaryHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/summarytab.htm' 
WHERE PARM_NM = 'budgetTotalsHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/unrecoveredfapanel.htm' 
WHERE PARM_NM = 'budgetUnrecoveredFandAHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/budgetversionspanel.htm' 
WHERE PARM_NM = 'budgetVersionsHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/costelement.htm' 
WHERE PARM_NM = 'costElementMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/customattribute.htm' 
WHERE PARM_NM = 'customAttributeDocumentMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/customattributedocument.htm' 
WHERE PARM_NM = 'customAttributeMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/deadlinetype.htm' 
WHERE PARM_NM = 'deadlineTypeMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/degreetype.htm' 
WHERE PARM_NM = 'degreeTypeMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/institutelarate.htm' 
WHERE PARM_NM = 'instituteLaRateMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/instituterate.htm' 
WHERE PARM_NM = 'instituteRateMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/investigatorcredittype.htm' 
WHERE PARM_NM = 'investigatorCreditTypeMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/mailby.htm' 
WHERE PARM_NM = 'mailByMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/narrativestatus.htm' 
WHERE PARM_NM = 'narrativeStatusMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/narrativetype.htm' 
WHERE PARM_NM = 'narrativeTypeMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/noticeofopportunity.htm' 
WHERE PARM_NM = 'noticeOfOpportunityMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/organization.htm' 
WHERE PARM_NM = 'organizationMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/persontableeditablecolumns.htm' 
WHERE PARM_NM = 'personEditableFieldMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/personmaintenancedocument.htm' 
WHERE PARM_NM = 'personMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/budgetstatus.htm' 
WHERE PARM_NM = 'proposalBudgetStatusHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/abstractspanel.htm' 
WHERE PARM_NM = 'proposalDevelopmentAbstractHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/abstractsandattachmentstab.htm' 
WHERE PARM_NM = 'proposalDevelopmentAbstractsAttachmentsHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/proposalactionstab.htm' 
WHERE PARM_NM = 'proposalDevelopmentActionsHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/budgetversionspanel.htm' 
WHERE PARM_NM = 'proposalDevelopmentBudgetVersionsHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/copytonewdocumentpanel.htm' 
WHERE PARM_NM = 'proposalDevelopmentCopyCriteriaHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/combinedcreditsplitpanel.htm' 
WHERE PARM_NM = 'proposalDevelopmentCreditSplitHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/customattributedocument.htm' 
WHERE PARM_NM = 'proposalDevelopmentCustomAttributeHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/customdatatab1.htm' 
WHERE PARM_NM = 'proposalDevelopmentCustomDataHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/proposaldevelopmentdocument.htm' 
WHERE PARM_NM = 'proposalDevelopmentDocumentHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/grantsgovtab.htm' 
WHERE PARM_NM = 'proposalDevelopmentGrantsGovHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/keypersonneltab.htm' 
WHERE PARM_NM = 'proposalDevelopmentKeyPersonnelHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/keywordspanel1.htm' 
WHERE PARM_NM = 'proposalDevelopmentKeywordsHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/organizationlocationpanel.htm' 
WHERE PARM_NM = 'proposalDevelopmentLocationHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/mailby.htm' 
WHERE PARM_NM = 'proposalDevelopmentMailByHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/abstractsandattachmentstab.htm' 
WHERE PARM_NM = 'proposalDevelopmentNarrativeHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/grantsgovlookup.htm' 
WHERE PARM_NM = 'proposalDevelopmentOpportunityHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/organizationlocationpanel.htm' 
WHERE PARM_NM = 'proposalDevelopmentOrganizationHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/permissionstab.htm' 
WHERE PARM_NM = 'proposalDevelopmentPermissionsHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/personpanel.htm' 
WHERE PARM_NM = 'proposalDevelopmentPersonHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/proposalpagetab.htm' 
WHERE PARM_NM = 'proposalDevelopmentProposalHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/requiredfieldsforsavingdocumentpanel.htm' 
WHERE PARM_NM = 'proposalDevelopmentProposalTypeHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/questionstab.htm' 
WHERE PARM_NM = 'proposalDevelopmentQuestionsHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/specialreviewtab.htm' 
WHERE PARM_NM = 'proposalDevelopmentSpecialReviewHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/specialreviewpanel.htm' 
WHERE PARM_NM = 'proposalDevelopmentSpecialReviewHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/sponsorprograminformationpanel.htm' 
WHERE PARM_NM = 'proposalDevelopmentSponsorHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/proposalquestionspanelexample.htm' 
WHERE PARM_NM = 'proposalDevelopmentYnqHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/proposaltypemaintenancedocument.htm' 
WHERE PARM_NM = 'proposalTypeMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/userspanel.htm' 
WHERE PARM_NM = 'proposalUserHelpUrl';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/rateclassmaintenancedocument.htm' 
WHERE PARM_NM = 'rateClassMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/rateclasstypemaintenancedocument.htm' 
WHERE PARM_NM = 'rateClassTypeMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/ratetypemaintenancedocument.htm' 
WHERE PARM_NM = 'rateTypeMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/s2srevisiontypemaintenancedocument.htm' 
WHERE PARM_NM = 's2sRevisionTypeMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/s2ssubmissiontypemaintenancedocument.htm' 
WHERE PARM_NM = 's2sSubmissionTypeMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/sciencekeywordmaintenancedocument.htm' 
WHERE PARM_NM = 'scienceKeywordMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/specialreviewapprovalstatusmaintenancedocument.htm' 
WHERE PARM_NM = 'specialReviewApprovalStatusMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/specialreviewapprovaltypemaintenancedocument.htm' 
WHERE PARM_NM = 'specialReviewApprovalTypeMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/sponsormaintenancedocument.htm' 
WHERE PARM_NM = 'sponsorMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/statemaintenancedocument.htm' 
WHERE PARM_NM = 'stateMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/transactionspanel.htm' 
WHERE PARM_NM = 'TransactionHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/validcalculationtypemaintenancedocument.htm' 
WHERE PARM_NM = 'validCalcTypeMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/validcostelementratetypemaintenancedocument.htm' 
WHERE PARM_NM = 'validCeRateTypeMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/validspecialreviewapprovalmaintenancedocument.htm' 
WHERE PARM_NM = 'validSpecialReviewApprovalMaintenanceHelp';

UPDATE krns_parm_t 
SET TXT='default.htm?turl=Documents/ynqmaintenancedocument.htm' 
WHERE PARM_NM = 'ynqMaintenanceHelp';

COMMIT;