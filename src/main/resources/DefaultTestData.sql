
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
INSERT INTO UNIT(UNIT_NUMBER, UNIT_NAME, UPDATE_TIMESTAMP, UPDATE_USER) VALUES('000002', 'IN-PEDS',  sysdate,'KRADEV')
;
INSERT INTO UNIT(UNIT_NUMBER, UNIT_NAME, UPDATE_TIMESTAMP, UPDATE_USER) VALUES('000003', 'BL-IIDC',  sysdate,'KRADEV')
;
