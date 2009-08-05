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

COMMIT;