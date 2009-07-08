Insert into KRNS_PARM_T
   (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd, grp_nm, ACTV_IND)
 Values
   ('KRA-PD', 'D', 'PROPOSAL_CONTACT_TYPE', sys_guid(), 1, 'CONFG', '2', 'Value for Proposal Contact Type', 'A', 'WorkflowAdmin', 'Y');
   
Insert into KRNS_PARM_T
   (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd, grp_nm, ACTV_IND)
 Values
   ('KRA-PD', 'D', 'MULTI_CAMPUS_ENABLED', sys_guid(), 1, 'CONFG', '0', 'Flag for enabling/disabling Multicampus', 'A', 'WorkflowAdmin', 'Y');

Insert into KRNS_PARM_T
   (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd, grp_nm, ACTV_IND)
 Values
   ('KRA-PD', 'D', 'DHHS_AGREEMENT', sys_guid(), 1, 'CONFG', '0', 'Value for DHHS Agreement', 'A', 'WorkflowAdmin', 'Y');

Insert into KRNS_PARM_T
   (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd, grp_nm, ACTV_IND)
 Values
   ('KRA-PD', 'D', 'SCHEDULER_SERVICE_ENABLED', sys_guid(), 1, 'CONFG', '0', 'Value for enabling s2s polling service', 'A', 'WorkflowAdmin', 'Y');

   INSERT INTO KRNS_PARM_T 
(NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND) 
VALUES('KC-AWARD', 'D', 'award.creditsplit.enabled', sys_guid(), 1, 'CONFG', 'Y', 'Determines whether the Credit Split is turned on for Award', 'A', 'WorkflowAdmin', 'Y'); 
  
Insert into KRNS_PARM_T 
( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
Values
('KC-PROTOCOL','D','irb.protocol.award.linking.enabled', sys_guid(), 1,'CONFG','Y','Linking from Award to Protocol Funding source is configurable at impl time','A','WorkflowAdmin','Y');

Insert into KRNS_PARM_T 
( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
Values
('KC-PROTOCOL','D','irb.protocol.development.proposal.linking.enabled', sys_guid(), 1,'CONFG','Y','Linking from Award to Protocol Funding source is configurable at impl time','A','WorkflowAdmin','Y');

Insert into KRNS_PARM_T 
( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
Values
('KC-PROTOCOL','D','irb.protocol.institute.proposal.linking.enabled', sys_guid(), 1,'CONFG','N','Linking from Award to Protocol Funding source is configurable at impl time','A','WorkflowAdmin','Y');

Insert into KRNS_PARM_T
   (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd, grp_nm, ACTV_IND)
 Values
   ('KC-PROTOCOL', 'D', 'protocolSelectSubmissionCommittee', sys_guid(), 1, 'CONFG', 'True', 'Implementing institution can decide on whether to allow a committee to be selected upon an IRB submittal', 'A', 'WorkflowAdmin', 'Y');

Insert into KRNS_PARM_T
   (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd, grp_nm, ACTV_IND)
 Values
   ('KC-PROTOCOL', 'D', 'protocolSelectSubmissionSchedule', sys_guid(), 1, 'CONFG', 'True', 'Implementing institution can decide on whether to allow a committee schedule to be selected upon an IRB submittal', 'A', 'WorkflowAdmin', 'Y');

Insert into KRNS_PARM_T
   (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd, grp_nm, ACTV_IND)
 Values
   ('KC-PROTOCOL', 'D', 'protocolSelectSubmissionReviewers', sys_guid(), 1, 'CONFG', 'True', 'Implementing institution can decide on whether to allow reviewers to be selected upon an IRB submittal', 'A', 'WorkflowAdmin', 'Y');
INSERT INTO KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD,GRP_NM,ACTV_IND) 
	VALUES 
	('KC-AWARD','D','closeoutReportTypeUserDefined',sys_guid(),1,'CONFG','UD','User Defined Close out Report Type','A','WorkflowAdmin','Y');
INSERT INTO KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD,GRP_NM,ACTV_IND) 
	VALUES 
	('KC-AWARD','D','closeoutReportTypeFinancialReport',sys_guid(),1,'CONFG','1','This system parameter maps the CloseoutReportType Financial Report(closeoutReoprtTypeCode=1) with ReportClass Fiscal(reportClassCode=1). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.','A','WorkflowAdmin','Y');
INSERT INTO KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD,GRP_NM,ACTV_IND) 
	VALUES 
	('KC-AWARD','D','closeoutReportTypeTechnical',sys_guid(),1,'CONFG','4','This system parameter maps the CloseoutReportType Technical(closeoutReoprtTypeCode=4) with ReportClass Technical Management(reportClassCode=4). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.','A','WorkflowAdmin','Y');
	
INSERT INTO KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD,GRP_NM,ACTV_IND) 
	VALUES 
	('KC-AWARD','D','closeoutReportTypePatent',sys_guid(),1,'CONFG','3','This system parameter maps the CloseoutReportType Patent(closeoutReoprtTypeCode=3) with ReportClass Intellectual Property(reportClassCode=3). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.','A','WorkflowAdmin','Y');
	
INSERT INTO KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD,GRP_NM,ACTV_IND) 
	VALUES 
	('KC-AWARD','D','closeoutReportTypeProperty',sys_guid(),1,'CONFG','2','This system parameter maps the CloseoutReportType Property(closeoutReoprtTypeCode=2) with ReportClass Property(reportClassCode=2). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.','A','WorkflowAdmin','Y');
	
commit;