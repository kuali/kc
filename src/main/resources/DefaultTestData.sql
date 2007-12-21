
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
insert into EN_WRKGRP_MBR_T (WRKGRP_MBR_PRSN_EN_ID, WRKGRP_ID, WRKGRP_MBR_TYP, WRKGRP_VER_NBR, DB_LOCK_VER_NBR)
values ('quickstart',1,'U',1,0)
;
insert into EN_WRKGRP_MBR_T (WRKGRP_MBR_PRSN_EN_ID, WRKGRP_ID, WRKGRP_MBR_TYP, WRKGRP_VER_NBR, DB_LOCK_VER_NBR)
values ('user4',1,'U',1,0)
;
INSERT INTO FP_DOC_GROUP_T VALUES ('TR', '054EDFB3B260C8D2E043814FD881C8D2', 1,	'Travel Documents', null)
;
insert into FP_DOC_TYPE_T values ('TRAV', '1A6FEB2501C7607EE043814FD881607E', 1, 'TR',	'TRAV ACCNT', 'N', 'Y', 'N', 0, 'N', 'N')
;
insert into FP_DOC_TYPE_T values ('TRFO', '1A6FEB250342607EE043814FD881607E', 1, 'TR',	'TRAV FO', 'N', 'Y', 'N', 0, 'N', 'N')
;
insert into FP_DOC_TYPE_T values ('TRD2', '1A6FEB250342607EE043814FD889607E', 1, 'TR',	'TRAV D2', 'N', 'Y', 'N', 0, 'N', 'N')
;
insert into FP_DOC_TYPE_T values ('TRVA', '1A5FEB250342607EE043814FD889607E', 1, 'TR',	'TRAV MAINT', 'N', 'Y', 'N', 0, 'N', 'N')
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
INSERT INTO KIM_NAMESPACES_T (ID, NAME, DESCRIPTION) VALUES (1, 'KIM', 'This record represents the actual KIM system and must always be loaded by default in order for the system to work properly.')
;
INSERT INTO KIM_PERSONS_T (ID, USERNAME, PASSWORD) VALUES (1, 'admin', 'admin')
;
insert into SH_PARM_TYP_T ("SH_PARM_TYP_CD","VER_NBR","SH_PARM_TYP_NM","ACTIVE_IND") values ('CONFG', 0,'Config',1)
;
insert into SH_PARM_TYP_T ("SH_PARM_TYP_CD","VER_NBR","SH_PARM_TYP_NM","ACTIVE_IND") values ('AUTH', 0,'Authorization',1)
;
insert into SH_PARM_NMSPC_T ("SH_PARM_NMSPC_CD","VER_NBR","SH_PARM_NMSPC_NM","ACTIVE_IND") values ('KR-NS', 0,'Kuali Rice',1)
;
INSERT INTO sh_parm_t ("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","SH_PARM_TXT","SH_PARM_CONS_CD","SH_PARM_DESC","SH_PARM_TYP_CD","WRKGRP_NM") VALUES ('KR-NS','All','CHECK_ENCRYPTION_SERVICE_OVERRIDE_IND','Y','A','Flag for enabling/disabling the demonstration encryption check.','CONFG','KUALI_FMSOPS')
;
INSERT INTO sh_parm_t ("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","SH_PARM_TXT","SH_PARM_CONS_CD","SH_PARM_DESC","SH_PARM_TYP_CD","WRKGRP_NM") VALUES ('KR-NS','All','ENABLE_FIELD_LEVEL_HELP_IND','N','A','Indicates whether field level help links are enabled on lookup pages and documents.','CONFG','KUALI_FMSOPS')
;
INSERT INTO sh_parm_t ("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","SH_PARM_TXT","SH_PARM_CONS_CD","SH_PARM_DESC","SH_PARM_TYP_CD","WRKGRP_NM") VALUES ('KR-NS','Lookup','RESULTS_LIMIT','200','A','Limit of results returned in a lookup query','CONFG','KUALI_FMSOPS')
;
INSERT INTO sh_parm_t ("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","SH_PARM_TXT","SH_PARM_CONS_CD","SH_PARM_DESC","SH_PARM_TYP_CD","WRKGRP_NM") VALUES ('KR-NS','Document','SUPERVISOR_GROUP','KUALI_ROLE_SUPERVISOR','A','Workgroup which can perform almost any function within Kuali.','AUTH','KUALI_FMSOPS')
;
INSERT INTO sh_parm_t ("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","SH_PARM_TXT","SH_PARM_CONS_CD","SH_PARM_DESC","SH_PARM_TYP_CD","WRKGRP_NM") VALUES ('KR-NS','Document','DEFAULT_CAN_PERFORM_ROUTE_REPORT_IND','N','A','If Y, the Route Report button will be displayed on the document actions bar if the document is using the default DocumentAuthorizerBase.getDocumentActionFlags to set the canPerformRouteReport property of the returned DocumentActionFlags instance.','CONFG','KUALI_FMSOPS')
;
INSERT INTO sh_parm_t ("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","SH_PARM_TXT","SH_PARM_CONS_CD","SH_PARM_DESC","SH_PARM_TYP_CD","WRKGRP_NM") VALUES ('KR-NS','Lookup','RESULTS_DEFAULT_MAX_COLUMN_LENGTH','70','A','If a maxLength attribute has not been set on a lookup result field in the data dictionary, then the result column''s max length will be the value of this parameter. Set this parameter to 0 for an unlimited default length or a positive value (i.e. greater than 0) for a finite max length.','CONFG','KUALI_FMSOPS')
;
INSERT INTO sh_parm_t ("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","SH_PARM_TXT","SH_PARM_CONS_CD","SH_PARM_DESC","SH_PARM_TYP_CD","WRKGRP_NM") VALUES ('KR-NS','Document','MAX_FILE_SIZE_ATTACHMENT','5M','A','Maximum attachment upload size for the application. Used by KualiDocumentFormBase. Must be an integer, optionally followed by ''K'', ''M'', or ''G''.','CONFG','KUALI_FMSOPS')
;