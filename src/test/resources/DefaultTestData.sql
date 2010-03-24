INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR) VALUES ('Y','1','Default','KUALI','5ADF18B6D4827954E0404F8189D85002',1);

insert into KRNS_NMSPC_T
(nmspc_cd, obj_id, ver_nbr, nm, actv_ind, appl_nmspc_cd)
values
('KR-IDM', 'f6967c28-1e6b-44e6-ba4e-e67be1bf3b01', 1, 'Identity Management', 'Y', '');

insert into KRNS_NMSPC_T
(nmspc_cd, obj_id, ver_nbr, nm, actv_ind, appl_nmspc_cd)
values
('KR-NS', '49982efc-6868-4d99-896c-d2523715feb2', 1, 'Kuali Nervous System', 'Y', '');

insert into KRNS_NMSPC_T
(nmspc_cd, obj_id, ver_nbr, nm, actv_ind, appl_nmspc_cd)
values
('KR-WKFLW', 'ffd750aa-a6a8-4e10-a3b3-b63162677c72', 0, 'Workflow', 'Y', '');

insert into KRNS_PARM_TYP_T
(parm_typ_cd, obj_id, ver_nbr, nm, actv_ind)
values
('AUTH', 'eb21d3f9-0d0c-4123-8f89-86ea72892225', 1, 'Authorization', 'Y');

insert into KRNS_PARM_TYP_T
(parm_typ_cd, obj_id, ver_nbr, nm, actv_ind)
values
('CONFG', '34174aee-e957-4b57-8c37-c45af7879ebb', 1, 'Config', 'Y');

insert into KRNS_PARM_TYP_T
(parm_typ_cd, obj_id, ver_nbr, nm, actv_ind)
values
('HELP', 'd6c35827-ddba-42bc-9223-0229701ec8b5', 1, 'Help', 'Y');

insert into KRNS_PARM_TYP_T
(parm_typ_cd, obj_id, ver_nbr, nm, actv_ind)
values
('VALID', '803892be-9b56-4db3-bfdd-6e338f01daf1', 1, 'Document Validation', 'Y');

INSERT INTO KRNS_PARM_T( CONS_CD, NMSPC_CD, OBJ_ID, PARM_DESC_TXT, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD,TXT,VER_NBR) VALUES ( 'A', 'KR-NS', '5be61fd8-fb23-4092-8845-bb532dc3dd40', 'A semi-colon delimted list of strings representing date formats that the DateTimeService will use to parse dates when DateTimeServiceImpl.convertToSqlDate(String) or DateTimeServiceImpl.convertToDate(String) is called. Note that patterns will be applied in the order listed (and the first applicable one will be used). For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.', 'All', 'STRING_TO_DATE_FORMATS', 'CONFG', 'MM/dd/yyyy hh:mm a;MM/dd/yy;MM/dd/yyyy;MM-dd-yy;MMddyy;MMMM dd;yyyy;MM/dd/yy HH:mm:ss;MM/dd/yyyy HH:mm:ss;MM-dd-yy HH:mm:ss;MMddyy HH:mm:ss;MMMM dd HH:mm:ss;yyyy HH:mm:ss',
1
);

INSERT INTO KRNS_PARM_T( CONS_CD, NMSPC_CD, OBJ_ID, PARM_DESC_TXT, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD,TXT,VER_NBR) VALUES ( 'A', 'KR-NS', 'c5eed1b4-ac70-47a4-88bf-9f2a7039a2ff', 'A single date format string that the DateTimeService will use to format dates to be used in a file name when DateTimeServiceImpl.toDateStringForFilename(Date) is called. For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.', 'All', 'DATE_TO_STRING_FORMAT_FOR_FILE_NAME', 'CONFG', 'yyyyMMdd', 1 );

INSERT INTO KRNS_PARM_T( CONS_CD, NMSPC_CD, OBJ_ID, PARM_DESC_TXT, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD,TXT,VER_NBR) VALUES ( 'A', 'KR-NS', 'e1db29e2-2f90-491c-a9fe-2b2d0e7e421f', 'A single date format string that the DateTimeService will use to format a date and time string to be used in a file name when DateTimeServiceImpl.toDateTimeStringForFilename(Date) is called.. For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.', 'All', 'TIMESTAMP_TO_STRING_FORMAT_FOR_FILE_NAME', 'CONFG', 'yyyyMMdd-HH-mm-ss-S', 1 );

INSERT INTO KRNS_PARM_T( CONS_CD, NMSPC_CD, OBJ_ID, PARM_DESC_TXT, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD,TXT,VER_NBR) VALUES ( 'A', 'KR-NS', '1eceb471-55fd-4463-b176-6210098190ef', 'A single date format string that the DateTimeService will use to format a date to be displayed on a web page. For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.', 'All', 'DATE_TO_STRING_FORMAT_FOR_USER_INTERFACE', 'CONFG', 'MM/dd/yyyy', 1 );

INSERT INTO KRNS_PARM_T( CONS_CD, NMSPC_CD, OBJ_ID, PARM_DESC_TXT, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD,TXT,VER_NBR) VALUES ( 'A', 'KR-NS', 'dcea9479-f3ab-4331-8d57-e4a20a91a26e', 'A single date format string that the DateTimeService will use to format a date and time to be displayed on a web page. For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.', 'All', 'TIMESTAMP_TO_STRING_FORMAT_FOR_USER_INTERFACE', 'CONFG', 'MM/dd/yyyy hh:mm a', 1 );

INSERT INTO KRNS_PARM_T( CONS_CD, NMSPC_CD, OBJ_ID, PARM_DESC_TXT, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD,TXT,VER_NBR) VALUES ( 'A', 'KR-NS', '5762ca42-2ee7-4dd6-854f-7b3d330fd662', 'A semi-colon delimted list of strings representing date formats that the DateTimeService will use to parse date and times when DateTimeServiceImpl.convertToDateTime(String) or DateTimeServiceImpl.convertToSqlTimestamp(String) is called. Note that patterns will be applied in the order listed (and the first applicable one will be used). For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.', 'All', 'STRING_TO_TIMESTAMP_FORMATS', 'CONFG', 'MM/dd/yyyy hh:mm a;MM/dd/yy;MM/dd/yyyy;MM-dd-yy;MMddyy;MMMM dd;yyyy;MM/dd/yy HH:mm:ss;MM/dd/yyyy HH:mm:ss;MM-dd-yy HH:mm:ss;MMddyy HH:mm:ss;MMMM dd HH:mm:ss;yyyy HH:mm:ss',
1
);

