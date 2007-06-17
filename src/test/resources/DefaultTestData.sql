insert into trv_acct_fo (acct_fo_id, acct_fo_user_name) values (1, 'fred')
;
insert into trv_acct_fo (acct_fo_id, acct_fo_user_name) values (2, 'fran')
;
insert into trv_acct_fo (acct_fo_id, acct_fo_user_name) values (3, 'frank')
;
insert into TRV_ACCT values ('a1', 'a1', 'CAT', 1)
;
insert into TRV_ACCT values ('a2', 'a2', 'EAT', 2)
;
insert into TRV_ACCT values ('a3', 'a3', 'IAT', 3)
;
insert into TRV_DOC_ACCT (DOC_HDR_ID, ACCT_NUM) values (1, 'a1')
;
insert into TRV_DOC_ACCT (DOC_HDR_ID, ACCT_NUM) values (1, 'a2')
;
insert into TRV_DOC_ACCT (DOC_HDR_ID, ACCT_NUM) values (1, 'a3')
;
insert into en_wrkgrp_t values (1,1,'WorkflowAdmin',1,'W','Workflow Administrator Workgroup',1,-1,0)
;
insert into EN_WRKGRP_MBR_T values ('quickstart',1,1,0)
;
insert into EN_WRKGRP_MBR_T values ('user4',1,1,0)
;
insert into FP_DOC_TYPE_T values ('TRVA', '1A6FEB2501C7607EE043814FD881607E', 1, 'TR',	'TRAV ACCNT', 'N', 'Y', 'N', 0, 'N', 'N')
;
INSERT INTO FP_DOC_GROUP_T VALUES ('TR', '054EDFB3B260C8D2E043814FD881C8D2', 1,	'Travel Documents', null)
;
insert into FP_DOC_TYPE_T values ('TRFO', '1A6FEB250342607EE043814FD881607E', 1, 'TR',	'TRAV FO', 'N', 'Y', 'N', 0, 'N', 'N')
;
insert into FP_DOC_TYPE_T values ('TRD2', '1A6FEB250342607EE043814FD889607E', 1, 'TR',	'TRAV D2', 'N', 'Y', 'N', 0, 'N', 'N')
;
INSERT INTO FS_PARM_T(FS_SCR_NM, FS_PARM_NM, OBJ_ID, VER_NBR, FS_PARM_TXT, FS_PARM_DESC, FS_MULT_VAL_IND) VALUES('SYSTEM','HELP_URL','07D71A3FF0D604D8E043814FD88104D8',1,'http://www.fms.indiana.edu/fis/home.asp','','N')
;
INSERT INTO FS_PARM_T(FS_SCR_NM, FS_PARM_NM, OBJ_ID, VER_NBR, FS_PARM_TXT, FS_PARM_DESC, FS_MULT_VAL_IND) VALUES('SYSTEM','lookup.results.limit','1AFCED30C07B2070E043814FD8812070',0,'200','Limit of results returned in a lookup query','N')
;
INSERT INTO FS_PARM_T(FS_SCR_NM, FS_PARM_NM, OBJ_ID, VER_NBR, FS_PARM_TXT, FS_PARM_DESC, FS_MULT_VAL_IND) VALUES('SYSTEM','demonstrationEncryptionCheck_FLAG','1C3D291AAD51A08CE043814FD881A08C',1,'Y','Flag for enabling/disabling the demonstration encryption check.','N')
;
INSERT INTO FS_PARM_T(FS_SCR_NM, FS_PARM_NM, OBJ_ID, VER_NBR, FS_PARM_TXT, FS_PARM_DESC, FS_MULT_VAL_IND) VALUES('SYSTEM','loadDataFileStep_USER','1F75EFB795DFB050E043814FD881B050',1,'KULUSER','determines who the loadDataFileStep of pcdo_batch.sh will run as','N')
;
insert into FS_PARM_T values ('CoreMaintenanceEDoc','Kuali.Document.RoutingReport.Workgroup','263A097060A3F152E043814FD881F152',1,'WorkflowAdmin','Workgroup which can perform the route report on documents.','N')
;
insert into FS_PARM_T values ('CoreMaintenanceEDoc','CASPasswordEnabled','26C8E6D6E77F40B4E043814FD88140B4',1,'N','Whether the built in CAS implementation should ask for a password. The password will be verified against the Universal User Table.','N')
;
insert into FS_PARM_T values ('CoreMaintenanceEDoc','UniversalUser.EditWorkgroup','2409BD6AB4CA800EE043814FD881800E',1,'WorkflowAdmin','Workgroup which can edit the universal user table.','N')
;
insert into FS_PARM_T values ('CoreMaintenanceEDoc','Workflow.Exception.Workgroup','2409BD6AB4CB800EE043814FD881800E',1,'WorkflowAdmin','Workgroup which can perform functions on documents in exception routing status.','N')
;
insert into FS_PARM_T values ('CoreMaintenanceEDoc','Kuali.Supervisor.Workgroup','2409BD6AB4CC800EE043814FD881800E',1,'WorkflowAdmin','Workgroup which can perform almost any function within Kuali.','N')
;
