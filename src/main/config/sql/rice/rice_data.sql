INSERT INTO FS_PARM_SEC_T values ('SYSTEM', '1', 1, 'WorkflowAdmin', 'Desc')
/
INSERT INTO FS_PARM_SEC_T values ('CoreMaintenanceEDoc', '2', 1, 'WorkflowAdmin', 'Desc')
/
INSERT INTO FS_PARM_T VALUES('SYSTEM','HELP_URL','07D71A3FF0D604D8E043814FD88104D8','1','http://www.fms.indiana.edu/fis/home.asp','','N', 'MC')
/
INSERT INTO FS_PARM_T VALUES('SYSTEM','lookup.results.limit','1AFCED30C07B2070E043814FD8812070','0','200','Limit of results returned in a lookup query','N', 'MC')
/
INSERT INTO FS_PARM_T VALUES('SYSTEM','demonstrationEncryptionCheck_FLAG','1C3D291AAD51A08CE043814FD881A08C','1','Y','Flag for enabling/disabling the demonstration encryption check.','N', 'MC')
/
INSERT INTO FS_PARM_T VALUES('SYSTEM','loadDataFileStep_USER','1F75EFB795DFB050E043814FD881B050','1','KULUSER','determines who the loadDataFileStep of pcdo_batch.sh will run as','N', 'MC')
/
insert into FS_PARM_T values ('CoreMaintenanceEDoc','Kuali.Document.RoutingReport.Workgroup','263A097060A3F152E043814FD881F152','1','WorkflowAdmin','Workgroup which can perform the route report on documents.','N', 'MC')
/
insert into FS_PARM_T values ('CoreMaintenanceEDoc','CASPasswordEnabled','26C8E6D6E77F40B4E043814FD88140B4','1','N','Whether the built in CAS implementation should ask for a password. The password will be verified against the Universal User Table.','N', 'MC')
/
insert into FS_PARM_T values ('CoreMaintenanceEDoc','UniversalUser.EditWorkgroup','2409BD6AB4CA800EE043814FD881800E','1','WorkflowAdmin','Workgroup which can edit the universal user table.','N', 'MC')
/
insert into FS_PARM_T values ('CoreMaintenanceEDoc','Workflow.Exception.Workgroup','2409BD6AB4CB800EE043814FD881800E','1','WorkflowAdmin','Workgroup which can perform functions on documents in exception routing status.','N', 'MC')
/
insert into FS_PARM_T values ('CoreMaintenanceEDoc','Kuali.Supervisor.Workgroup','2409BD6AB4CC800EE043814FD881800E','1','WorkflowAdmin','Workgroup which can perform almost any function within Kuali.','N', 'MC')
/
insert into EN_APPL_CNST_T values ('Feature.CheckRouteLogAuthentication.CheckFuture', 'true', 1)
/
insert into EN_APPL_CNST_T values ('RouteQueue.maxRetryAttempts', '0', 1)
/
insert into EN_APPL_CNST_T values ('BAM', 'true', 1)
/
insert into EN_APPL_CNST_T values ('Security.HttpInvoker.SignMessages', 'true', 1)
/
insert into EN_APPL_CNST_T values ('Workflow.AdminWorkgroup', 'WorkflowAdmin', 1)
/
insert into EN_APPL_CNST_T values ('Routing.ImmediateExceptionRouting', 'true', 1)
/
insert into EN_APPL_CNST_T values ('Workgroup.IsRouteLogPopup', 'false', 0)
/
insert into EN_APPL_CNST_T values ('DocumentType.IsRouteLogPopup', 'false', 0)
/
insert into EN_APPL_CNST_T values ('DocumentSearch.IsRouteLogPopup', 'true', 0)
/
insert into EN_APPL_CNST_T values ('DocumentSearch.IsDocumentPopup', 'true', 0)
/
insert into EN_APPL_CNST_T values ('Config.Backdoor.TargetFrameName', 'iframe_51148', 0)
/
insert into FP_DOC_STATUS_T values ('A',	'2E0671732A684002E043814FD8814002',	1,	'Approved')
/
insert into FP_DOC_STATUS_T values ('C',	'2E0671732A694002E043814FD8814002',	1,	'Cancelled')
/
insert into FP_DOC_STATUS_T values ('E',	'2E0671732A6A4002E043814FD8814002',	1,	'Extracted')
/
insert into FP_DOC_STATUS_T values ('I',	'2E0671732A6B4002E043814FD8814002',	1,	'In Process')
/
insert into FP_DOC_STATUS_T values ('II',	'2E0671732A6C4002E043814FD8814002',	1,	'In Process')
/
insert into FP_DOC_STATUS_T values ('O',	'2E0671732A6D4002E043814FD8814002',	1,	'Pend Org')
/
insert into FP_DOC_STATUS_T values ('OO',	'2E0671732A6E4002E043814FD8814002',	1,	'Pend Org')
/
insert into FP_DOC_STATUS_T values ('P',	'2E0671732A6F4002E043814FD8814002',	1,	'Pend Acct')
/
insert into FP_DOC_STATUS_T values ('PP',	'2E0671732A704002E043814FD8814002',	1,	'Pend Acct')
/
insert into FP_DOC_STATUS_T values ('R',	'2E0671732A714002E043814FD8814002',	1,	'Pend Specl')
/
insert into FP_DOC_STATUS_T values ('RR',	'2E0671732A724002E043814FD8814002',	1,	'Pend Specl')
/
insert into FP_DOC_STATUS_T values ('S',	'2E0671732A734002E043814FD8814002',	1,	'Pend CG')
/
insert into FP_DOC_STATUS_T values ('V',	'2E0671732A744002E043814FD8814002',	1,	'Validation')
/
insert into FP_DOC_STATUS_T values ('Q',	'2E0671732A754002E043814FD8814002',	1,	'Doc Specif')
/