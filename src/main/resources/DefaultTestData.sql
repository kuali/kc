INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','1','Default','KUALI','5ADF18B6D4827954E0404F8189D85002',1);

-- insert into en_wrkgrp_t values (1,1,'WorkflowAdmin',1,'W','Workflow Administrator Workgroup',1,-1,0);

--insert into EN_WRKGRP_MBR_T (WRKGRP_MBR_PRSN_EN_ID, WRKGRP_ID, WRKGRP_MBR_TYP, WRKGRP_VER_NBR, DB_LOCK_VER_NBR)
--values ('quickstart',1,'U',1,0)
--;
--insert into EN_WRKGRP_MBR_T (WRKGRP_MBR_PRSN_EN_ID, WRKGRP_ID, WRKGRP_MBR_TYP, WRKGRP_VER_NBR, DB_LOCK_VER_NBR)
--values ('user4',1,'U',1,0)
--;
--INSERT INTO kr_qrtz_locks values('TRIGGER_ACCESS')
--;
--INSERT INTO kr_qrtz_locks values('JOB_ACCESS')
--;
--INSERT INTO kr_qrtz_locks values('CALENDAR_ACCESS')
--;
--INSERT INTO kr_qrtz_locks values('STATE_ACCESS')
--;
--INSERT INTO kr_qrtz_locks values('MISFIRE_ACCESS')
--;
--insert into SH_PARM_TYP_T ("SH_PARM_TYP_CD","VER_NBR","SH_PARM_TYP_NM","ACTIVE_IND") values ('CONFG', 0,'Config',1)
--;
--insert into SH_PARM_TYP_T ("SH_PARM_TYP_CD","VER_NBR","SH_PARM_TYP_NM","ACTIVE_IND") values ('AUTH', 0,'Authorization',1)
--;
--insert into SH_PARM_NMSPC_T ("SH_PARM_NMSPC_CD","VER_NBR","SH_PARM_NMSPC_NM","ACTIVE_IND") values ('KR-NS', 0,'Kuali Rice',1)
--;
--INSERT INTO sh_parm_t ("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","SH_PARM_TXT","SH_PARM_CONS_CD","SH_PARM_DESC","SH_PARM_TYP_CD","WRKGRP_NM") VALUES ('KR-NS','All','CHECK_ENCRYPTION_SERVICE_OVERRIDE_IND','Y','A','Flag for enabling/disabling the demonstration encryption check.','CONFG','KUALI_FMSOPS')
--;
--INSERT INTO sh_parm_t ("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","SH_PARM_TXT","SH_PARM_CONS_CD","SH_PARM_DESC","SH_PARM_TYP_CD","WRKGRP_NM") VALUES ('KR-NS','All','ENABLE_FIELD_LEVEL_HELP_IND','N','A','Indicates whether field level help links are enabled on lookup pages and documents.','CONFG','KUALI_FMSOPS')
--;
--INSERT INTO sh_parm_t ("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","SH_PARM_TXT","SH_PARM_CONS_CD","SH_PARM_DESC","SH_PARM_TYP_CD","WRKGRP_NM") VALUES ('KR-NS','Lookup','RESULTS_LIMIT','200','A','Limit of results returned in a lookup query','CONFG','KUALI_FMSOPS')
--;
--INSERT INTO sh_parm_t ("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","SH_PARM_TXT","SH_PARM_CONS_CD","SH_PARM_DESC","SH_PARM_TYP_CD","WRKGRP_NM") VALUES ('KR-NS','Document','SUPERVISOR_GROUP','KUALI_ROLE_SUPERVISOR','A','Workgroup which can perform almost any function within Kuali.','AUTH','KUALI_FMSOPS')
--;
--INSERT INTO sh_parm_t ("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","SH_PARM_TXT","SH_PARM_CONS_CD","SH_PARM_DESC","SH_PARM_TYP_CD","WRKGRP_NM") VALUES ('KR-NS','Document','DEFAULT_CAN_PERFORM_ROUTE_REPORT_IND','N','A','If Y, the Route Report button will be displayed on the document actions bar if the document is using the default DocumentAuthorizerBase.getDocumentActionFlags to set the canPerformRouteReport property of the returned DocumentActionFlags instance.','CONFG','KUALI_FMSOPS')
--;
--INSERT INTO sh_parm_t ("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","SH_PARM_TXT","SH_PARM_CONS_CD","SH_PARM_DESC","SH_PARM_TYP_CD","WRKGRP_NM") VALUES ('KR-NS','Lookup','RESULTS_DEFAULT_MAX_COLUMN_LENGTH','70','A','If a maxLength attribute has not been set on a lookup result field in the data dictionary, then the result column''s max length will be the value of this parameter. Set this parameter to 0 for an unlimited default length or a positive value (i.e. greater than 0) for a finite max length.','CONFG','KUALI_FMSOPS')
--;
--INSERT INTO sh_parm_t ("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","SH_PARM_TXT","SH_PARM_CONS_CD","SH_PARM_DESC","SH_PARM_TYP_CD","WRKGRP_NM") VALUES ('KR-NS','Document','MAX_FILE_SIZE_ATTACHMENT','5M','A','Maximum attachment upload size for the application. Used by KualiDocumentFormBase. Must be an integer, optionally followed by ''K'', ''M'', or ''G''.','CONFG','KUALI_FMSOPS')
--;
--INSERT INTO SH_PARM_T(SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_NM, OBJ_ID, VER_NBR, SH_PARM_TYP_CD, SH_PARM_TXT, SH_PARM_DESC, SH_PARM_CONS_CD, WRKGRP_NM) VALUES('KR-NS', 'All', 'ENABLE_DIRECT_INQUIRIES_IND', sys_guid(), 1, 'CONFG', 'Y', 'Flag for enabling/disabling direct inquiries on screens that are drawn by the nervous system (i.e. lookups and maintenance documents)', 'A', 'KUALI_FMSOPS')
--;
--insert into SH_NTE_TYP_T values ('BO', '2D3C44FE49415102E043814FD8815102', 1, 'DOCUMENT BUSINESS OBJECT', 'Y');
--insert into SH_NTE_TYP_T values ('DH', '2D3C44FE49425102E043814FD8815102', 1,	'DOCUMENT HEADER', 'Y');

insert into krns_nmspc_t
  (nmspc_cd, obj_id, ver_nbr, nm, actv_ind, appl_nmspc_cd)
values
  ('KR-IDM',  SYS_GUID(),  1,  'Identity Management',  'Y', '');

insert into krns_nmspc_t
  (nmspc_cd, obj_id, ver_nbr, nm, actv_ind, appl_nmspc_cd)
values
  ('KR-NS',  SYS_GUID(),  1,  'Kuali Nervous System',  'Y', '');
  
insert into krns_nmspc_t
  (nmspc_cd, obj_id, ver_nbr, nm, actv_ind, appl_nmspc_cd)
values
  ('KR-WKFLW',  SYS_GUID(),  0,  'Workflow',  'Y', '');
  
insert into krns_parm_typ_t
  (parm_typ_cd, obj_id, ver_nbr, nm, actv_ind)
values
  ('AUTH', SYS_GUID(), 1, 'Authorization', 'Y');

insert into krns_parm_typ_t
  (parm_typ_cd, obj_id, ver_nbr, nm, actv_ind)
values
  ('CONFG', SYS_GUID(), 1, 'Config', 'Y');
  
insert into krns_parm_typ_t
  (parm_typ_cd, obj_id, ver_nbr, nm, actv_ind)
values
  ('HELP', SYS_GUID(), 1,	'Help',	'Y');
  
insert into krns_parm_typ_t
  (parm_typ_cd, obj_id, ver_nbr, nm, actv_ind)
values
  ('VALID',	SYS_GUID(),	1,	'Document Validation',	'Y');

INSERT INTO KRNS_PARM_T(
CONS_CD,
NMSPC_CD,
OBJ_ID,
PARM_DESC_TXT,
PARM_DTL_TYP_CD,
PARM_NM,
PARM_TYP_CD,TXT,VER_NBR)
VALUES
(
'A',
'KR-NS',
SYS_GUID(),
'A semi-colon delimted list of strings representing date formats that the DateTimeService will use to parse dates when DateTimeServiceImpl.convertToSqlDate(String) or DateTimeServiceImpl.convertToDate(String) is called. Note that patterns will be applied in the order listed (and the first applicable one will be used). For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.',
'All',
'STRING_TO_DATE_FORMATS',
'CONFG',
'MM/dd/yy;MM-dd-yy;MMMM dd, yyyy;MMddyy',
1
);

INSERT INTO KRNS_PARM_T(
CONS_CD,
NMSPC_CD,
OBJ_ID,
PARM_DESC_TXT,
PARM_DTL_TYP_CD,
PARM_NM,
PARM_TYP_CD,TXT,VER_NBR)
VALUES
(
'A',
'KR-NS',
SYS_GUID(),
'A single date format string that the DateTimeService will use to format dates to be used in a file name when DateTimeServiceImpl.toDateStringForFilename(Date) is called. For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.',
'All',
'DATE_TO_STRING_FORMAT_FOR_FILE_NAME',
'CONFG',
'yyyyMMdd',
1
);

INSERT INTO KRNS_PARM_T(
CONS_CD,
NMSPC_CD,
OBJ_ID,
PARM_DESC_TXT,
PARM_DTL_TYP_CD,
PARM_NM,
PARM_TYP_CD,TXT,VER_NBR)
VALUES
(
'A',
'KR-NS',
SYS_GUID(),
'A single date format string that the DateTimeService will use to format a date and time string to be used in a file name when DateTimeServiceImpl.toDateTimeStringForFilename(Date) is called.. For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.',
'All',
'TIMESTAMP_TO_STRING_FORMAT_FOR_FILE_NAME',
'CONFG',
'yyyyMMdd-HH-mm-ss-S',
1
);

INSERT INTO KRNS_PARM_T(
CONS_CD,
NMSPC_CD,
OBJ_ID,
PARM_DESC_TXT,
PARM_DTL_TYP_CD,
PARM_NM,
PARM_TYP_CD,TXT,VER_NBR)
VALUES
(
'A',
'KR-NS',
SYS_GUID(),
'A single date format string that the DateTimeService will use to format a date to be displayed on a web page. For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.',
'All',
'DATE_TO_STRING_FORMAT_FOR_USER_INTERFACE',
'CONFG',
'MM/dd/yyyy',
1
);

INSERT INTO KRNS_PARM_T(
CONS_CD,
NMSPC_CD,
OBJ_ID,
PARM_DESC_TXT,
PARM_DTL_TYP_CD,
PARM_NM,
PARM_TYP_CD,TXT,VER_NBR)
VALUES
(
'A',
'KR-NS',
SYS_GUID(),
'A single date format string that the DateTimeService will use to format a date and time to be displayed on a web page. For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.',
'All',
'TIMESTAMP_TO_STRING_FORMAT_FOR_USER_INTERFACE',
'CONFG',
'MM/dd/yyyy hh:mm a',
1
);

INSERT INTO KRNS_PARM_T(
CONS_CD,
NMSPC_CD,
OBJ_ID,
PARM_DESC_TXT,
PARM_DTL_TYP_CD,
PARM_NM,
PARM_TYP_CD,TXT,VER_NBR)
VALUES
(
'A',
'KR-NS',
SYS_GUID(),
'A semi-colon delimted list of strings representing date formats that the DateTimeService will use to parse date and times when DateTimeServiceImpl.convertToDateTime(String) or DateTimeServiceImpl.convertToSqlTimestamp(String) is called. Note that patterns will be applied in the order listed (and the first applicable one will be used). For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.',
'All',
'STRING_TO_TIMESTAMP_FORMATS',
'CONFG',
'MM/dd/yyyy hh:mm a',
1
);
