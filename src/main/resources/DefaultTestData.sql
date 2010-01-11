INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
 VALUES ('Y','1','Default','KUALI','5ADF18B6D4827954E0404F8189D85002',1);

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
'MM/dd/yyyy hh:mm a;MM/dd/yy;MM/dd/yyyy;MM-dd-yy;MMddyy;MMMM dd;yyyy;MM/dd/yy HH:mm:ss;MM/dd/yyyy HH:mm:ss;MM-dd-yy HH:mm:ss;MMddyy HH:mm:ss;MMMM dd HH:mm:ss;yyyy HH:mm:ss',
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
'MM/dd/yyyy hh:mm a;MM/dd/yy;MM/dd/yyyy;MM-dd-yy;MMddyy;MMMM dd;yyyy;MM/dd/yy HH:mm:ss;MM/dd/yyyy HH:mm:ss;MM-dd-yy HH:mm:ss;MMddyy HH:mm:ss;MMMM dd HH:mm:ss;yyyy HH:mm:ss',
1
);

drop sequence krim_entity_id_s;
create SEQUENCE krim_entity_id_s increment by 1 start with 1500; 