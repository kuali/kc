
insert into trv_acct_fo (acct_fo_id, acct_fo_user_name) values (1, 'fred')
;
insert into trv_acct_fo (acct_fo_id, acct_fo_user_name) values (2, 'fran')
;
insert into trv_acct_fo (acct_fo_id, acct_fo_user_name) values (3, 'frank')
;
insert into TRV_ACCT_TYPE values ('CAT', 'Clearing Account Type')
;
insert into TRV_ACCT_TYPE values ('EAT', 'Expense Account Type')
;
insert into TRV_ACCT_TYPE values ('IAT', 'Income Account Type')
;
insert into TRV_ACCT values ('a1', 'a1', 'CAT', 1)
;
insert into TRV_ACCT values ('a2', 'a2', 'EAT', 2)
;
insert into TRV_ACCT values ('a3', 'a3', 'IAT', 3)
;
insert into TRV_ACCT_EXT values ('a1', 'CAT')
;
insert into TRV_ACCT_EXT values ('a2', 'EAT')
;
insert into TRV_ACCT_EXT values ('a3', 'IAT')
;
insert into TRV_DOC_ACCT (DOC_HDR_ID, ACCT_NUM) values (1, 'a1')
;
insert into TRV_DOC_ACCT (DOC_HDR_ID, ACCT_NUM) values (1, 'a2')
;
insert into TRV_DOC_ACCT (DOC_HDR_ID, ACCT_NUM) values (1, 'a3')
;
insert into en_wrkgrp_t values (1,1,'WorkflowAdmin',1,'W','Workflow Administrator Workgroup',1,-1,0)
;
insert into EN_WRKGRP_MBR_T values ('quickstart',1,'U',1,0)
;
insert into en_usr_t values ('user4','user4','user4','user4','user4@school.edu','user4','user4','user4',to_date('01/01/2000', 'dd/mm/yyyy'),to_date('01/01/2100', 'dd/mm/yyyy'),0,0)
;
insert into EN_WRKGRP_MBR_T values ('user4',1,'U',1,0)
;
INSERT INTO FP_DOC_GROUP_T VALUES ('TR', '054EDFB3B260C8D2E043814FD881C8D2', 1,	'Travel Documents', null)
;
INSERT INTO FP_DOC_GROUP_T VALUES ('KR', '054EDFB3B260C8D2E043816FD881C8D2', 1,	'Kuali Rice', null)
;
insert into FP_DOC_TYPE_T values ('TRVA', '1A6FEB2501C7607EE043814FD881607E', 1, 'TR',	'TRAV ACCNT', 'N', 'Y', 'N', 0, 'N', 'N')
;
insert into FP_DOC_TYPE_T values ('TRFO', '1A6FEB250342607EE043814FD881607E', 1, 'TR',	'TRAV FO', 'N', 'Y', 'N', 0, 'N', 'N')
;
insert into FP_DOC_TYPE_T values ('TRD2', '1A6FEB250342607EE043814FD889607E', 1, 'TR',	'TRAV D2', 'N', 'Y', 'N', 0, 'N', 'N')
;
INSERT INTO FP_DOC_TYPE_T (FDOC_TYP_CD,FDOC_GRP_CD,FDOC_NM,FIN_ELIM_ELGBL_CD,FDOC_TYP_ACTIVE_CD,FDOC_RTNG_RULE_CD,FDOC_AUTOAPRV_DAYS,FDOC_BALANCED_CD,TRN_SCRBBR_OFST_GEN_IND) VALUES ('PRDV','KR','PROPOSAL DEVELOPMENT','N','Y','N',0,'N','N')
;
INSERT INTO FS_PARM_SEC_T VALUES('SYSTEM', '1', 0, 'WorkflowAdmin', 'desc')
;
INSERT INTO FS_PARM_SEC_T VALUES('CoreMaintenanceEDoc', '2', 0, 'WorkflowAdmin', 'desc')
;
INSERT INTO FS_PARM_T VALUES('SYSTEM','HELP_URL','07D71A3FF0D604D8E043814FD88104D8',1,'http://www.fms.indiana.edu/fis/home.asp','','N', 'KR')
;
INSERT INTO FS_PARM_T VALUES('SYSTEM','lookup.results.limit','1AFCED30C07B2070E043814FD8812070',0,'200','Limit of results returned in a lookup query','N', 'KR')
;
INSERT INTO FS_PARM_T VALUES('SYSTEM','demonstrationEncryptionCheck_FLAG','1C3D291AAD51A08CE043814FD881A08C',1,'Y','Flag for enabling/disabling the demonstration encryption check.','N', 'KR')
;
INSERT INTO FS_PARM_T VALUES('SYSTEM','loadDataFileStep_USER','1F75EFB795DFB050E043814FD881B050',1,'KULUSER','determines who the loadDataFileStep of pcdo_batch.sh will run as','N', 'KR')
;
insert into FS_PARM_T values ('CoreMaintenanceEDoc','Kuali.Document.RoutingReport.Workgroup','263A097060A3F152E043814FD881F152',1,'WorkflowAdmin','Workgroup which can perform the route report on documents.','N', 'KR')
;
insert into FS_PARM_T values ('CoreMaintenanceEDoc','CASPasswordEnabled','26C8E6D6E77F40B4E043814FD88140B4',1,'N','Whether the built in CAS implementation should ask for a password. The password will be verified against the Universal User Table.','N', 'KR')
;
insert into FS_PARM_T values ('CoreMaintenanceEDoc','UniversalUser.EditWorkgroup','2409BD6AB4CA800EE043814FD881800E',1,'WorkflowAdmin','Workgroup which can edit the universal user table.','N', 'KR')
;
insert into FS_PARM_T values ('CoreMaintenanceEDoc','Workflow.Exception.Workgroup','2409BD6AB4CB800EE043814FD881800E',1,'WorkflowAdmin','Workgroup which can perform functions on documents in exception routing status.','N', 'KR')
;
insert into FS_PARM_T values ('CoreMaintenanceEDoc','Kuali.Supervisor.Workgroup','2409BD6AB4CC800EE043814FD881800E',1,'WorkflowAdmin','Workgroup which can perform almost any function within Kuali.','N', 'KR')
;
INSERT INTO kr_qrtz_locks values('TRIGGER_ACCESS')
;
INSERT INTO kr_qrtz_locks values('JOB_ACCESS')
;
INSERT INTO kr_qrtz_locks values('CALENDAR_ACCESS')
;
INSERT INTO kr_qrtz_locks values('STATE_ACCESS')
;
INSERT INTO kr_qrtz_locks values('MISFIRE_ACCESS')
;
INSERT INTO CARRIER_TYPE (CARRIER_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) VALUES ('1','Regular',sysdate,'KRAREG',1,'351472CA90A55148E043814FD8815148')
;
INSERT INTO CARRIER_TYPE (CARRIER_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) VALUES ('2','DHL',sysdate,'KRAREG',1,'351472CA90A65148E043814FD8815148')
;
INSERT INTO CARRIER_TYPE (CARRIER_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) VALUES ('3','Electronic',sysdate,'KRAREG',1,'351472CA90A75148E043814FD8815148')
;
INSERT INTO NOTICE_OF_OPPORTUNITY (NOTICE_OF_OPPORTUNITY_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('1','Federal Solicitation',sysdate,'KRADEV')
;
INSERT INTO NOTICE_OF_OPPORTUNITY (NOTICE_OF_OPPORTUNITY_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('2','Unsolicited',sysdate,'KRADEV')
;
INSERT INTO NOTICE_OF_OPPORTUNITY (NOTICE_OF_OPPORTUNITY_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('3','Verbal Request for Proposal',sysdate,'KRADEV')
;
INSERT INTO NOTICE_OF_OPPORTUNITY (NOTICE_OF_OPPORTUNITY_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('4','SBIR Solicitation',sysdate,'KRADEV')
;
INSERT INTO NOTICE_OF_OPPORTUNITY (NOTICE_OF_OPPORTUNITY_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('5','STTR Solicitation',sysdate,'KRADEV')
;
INSERT INTO NOTICE_OF_OPPORTUNITY (NOTICE_OF_OPPORTUNITY_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('6','Non-Federal Solicitation',sysdate,'KRADEV')
;
INSERT INTO NOTICE_OF_OPPORTUNITY (NOTICE_OF_OPPORTUNITY_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('7','MIT Alliance/Internal',sysdate,'KRADEV')
;
insert into deadline_type (deadline_type_code,description,update_timestamp,update_user) values('P','Postmark',sysdate,'kradev')
;
insert into deadline_type (deadline_type_code,description,update_timestamp,update_user) values('R','Receipt',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(1,'J.02','Law - Non-Science and Engineering Fields: J.02',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(2,'A.01','Aeronautical and Astronautical - Engineering: A.01',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(3,'F.01','Agricultural - Life Sciences: F.01',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(4,'B.01','Astronomy - Physical Sciences: B.01',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(5,'C.01','Atmospheric - Environmental Sciences: C.01',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(6,'A.02','Bioengineering/Biomedical - Engineering: A.02',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(7,'F.02','Biological - Life Sciences: F.02',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(8,'J.05','Business and Management - Non-Science and Engineering Fields: J.05',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(9,'A.03','Chemical - Engineering: A.03',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(10,'B.02','Chemistry - Physical Sciences: B.02',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(11,'A.04','Civil - Engineering: A.04',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(12,'J.06','Communications, Journalism and Library Sciences - Non-Science and Engineering Fields: J.06',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(13,'E.01','Computer Sciences: E.01',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(14,'Z.99','Costs not included in NSF report - used for reconciliation purposes: Z.99',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(15,'J.98','Costs to be allocated: J.98',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(16,'C.02','Earth Sciences - Environmental Sciences: C.02',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(17,'H.01','Economics - Social Sciences: H.01',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(18,'J.01','Education - Non-Science and Engineering Fields: J.01',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(19,'A.05','Electrical - Engineering: A.05',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(20,'J.03','Humanities - Non-Science and Engineering Fields: J.03',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(21,'D.01','Mathematical Science: D.01',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(22,'A.06','Mechanical  - Engineering: A.06',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(23,'F.03','Medical - Life Sciences: F.03',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(24,'A.07','Metallurgical and Materials  - Engineering: A.07',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(25,'C.03','Oceanography - Environmental Sciences: C.03',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(26,'A.99','Other - Engineering: A.99',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(27,'C.99','Other - Environmental Sciences: C.99',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(28,'F.99','Other - Life Sciences: F.99',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(29,'J.99','Other - Non-Science and Engineering Fields: J.99',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(30,'B.99','Other - Physical Sciences: B.99',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(31,'H.99','Other - Social Sciences: H.99',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(32,'I.01','Other Sciences, n.e.c.: I.01',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(33,'B.03','Physics - Physical Sciences: B.03',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(34,'H.02','Political Science - Social Sciences: H.02',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(35,'G.01','Psychology: G.01',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(36,'J.07','Social Work - Non-Science and Engineering Fields: J.07',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(37,'H.03','Sociology - Social Sciences: H.03',sysdate,'kradev')
;
insert into nsf_codes(nsf_sequence_number,nsf_code,description,update_timestamp,update_user) values(38,'J.04','Visual and Performing Arts - Non-Science and Engineering Fields: J.04',sysdate,'kradev')
;
insert into activity_type (activity_type_code,description,update_timestamp,update_user) values('1','Organized Research',sysdate,'kradev')
;
insert into activity_type (activity_type_code,description,update_timestamp,update_user) values('2','Instruction and Departmental Research',sysdate,'kradev')
;
insert into activity_type (activity_type_code,description,update_timestamp,update_user) values('3','Fellowship - Pre-Doctoral',sysdate,'kradev')
;
insert into activity_type (activity_type_code,description,update_timestamp,update_user) values('4','Public Service',sysdate,'kradev')
;
insert into activity_type (activity_type_code,description,update_timestamp,update_user) values('5','Student Services',sysdate,'kradev')
;
insert into activity_type (activity_type_code,description,update_timestamp,update_user) values('6','Other',sysdate,'kradev')
;
insert into activity_type (activity_type_code,description,update_timestamp,update_user) values('7','Fellowship - Post-Doctoral',sysdate,'kradev')
;
insert into activity_type (activity_type_code,description,update_timestamp,update_user) values('8','Clinical Trial',sysdate,'kradev')
;
insert into activity_type (activity_type_code,description,update_timestamp,update_user) values('9','Construction',sysdate,'kradev')
;
INSERT INTO PROPOSAL_TYPE(PROPOSAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER) VALUES(1, 'New',  sysdate,'KRADEV')
;
INSERT INTO PROPOSAL_TYPE(PROPOSAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER) VALUES(2, 'Competing Continuation',  sysdate,'KRADEV')
;
INSERT INTO PROPOSAL_TYPE(PROPOSAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER) VALUES(3, 'Non-competing Continuation',  sysdate,'KRADEV')
;
INSERT INTO PROPOSAL_TYPE(PROPOSAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER) VALUES(4, 'Supplement',  sysdate,'KRADEV')
;
INSERT INTO PROPOSAL_TYPE(PROPOSAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER) VALUES(5, 'Renewal',  sysdate,'KRADEV')
;
INSERT INTO PROPOSAL_TYPE(PROPOSAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER) VALUES(6, 'Revision',  sysdate,'KRADEV')
;
INSERT INTO PROPOSAL_TYPE(PROPOSAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER) VALUES(7, 'Pre-Proposal',  sysdate,'KRADEV')
;
INSERT INTO PROPOSAL_TYPE(PROPOSAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER) VALUES(8, 'Accomplishment-based Renewal',  sysdate,'KRADEV')
;
INSERT INTO PROPOSAL_TYPE(PROPOSAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER) VALUES(9, 'Task Order',  sysdate,'KRADEV')
;
INSERT INTO UNIT(UNIT_NUMBER, UNIT_NAME, UPDATE_TIMESTAMP, UPDATE_USER) VALUES('000002', 'IN-PEDS',  sysdate,'KRADEV')
;
INSERT INTO UNIT(UNIT_NUMBER, UNIT_NAME, UPDATE_TIMESTAMP, UPDATE_USER) VALUES('000003', 'BL-IIDC',  sysdate,'KRADEV')
;