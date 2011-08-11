delimiter /
TRUNCATE TABLE KRNS_PARM_T
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-IDM','2238b58e-8fb9-102c-9461-def224dad9b3','The maximum number of role or group members to display at once on their documents. If the number is above this value, the document will switch into a paging mode with only this many rows displayed at a time.','Document','MAX_MEMBERS_PER_PAGE','CONFG','20',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-IDM','61645D045B0105D7E0404F8189D849B1','PersonDocumentName','PREFIXES','CONFG','Ms;Mrs;Mr;Dr',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-IDM','61645D045B0205D7E0404F8189D849B1','PersonDocumentName','SUFFIXES','CONFG','Jr;Sr;Mr;Md',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','53680C68F59AAD9BE0404F8189D80A6C','Flag for enabling/disabling (Y/N) the demonstration encryption check.','All','CHECK_ENCRYPTION_SERVICE_OVERRIDE_IND','CONFG','Y',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','664F8ABEC723DBCDE0404F8189D85427','A single date format string that the DateTimeService will use to format dates to be used in a file name when DateTimeServiceImpl.toDateStringForFilename(Date) is called. For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.','All','DATE_TO_STRING_FORMAT_FOR_FILE_NAME','CONFG','yyyyMMdd',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','664F8ABEC725DBCDE0404F8189D85427','A single date format string that the DateTimeService will use to format a date to be displayed on a web page. For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.','All','DATE_TO_STRING_FORMAT_FOR_USER_INTERFACE','CONFG','MM/dd/yyyy',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','64B87B4C5E3B8F4CE0404F8189D8291A','Used as the default country code when relating records that do not have a country code to records that do have a country code, e.g. validating a zip code where the country is not collected.','All','DEFAULT_COUNTRY','CONFG','US',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','53680C68F59BAD9BE0404F8189D80A6C','Flag for enabling/disabling direct inquiries on screens that are drawn by the nervous system (i.e. lookups and maintenance documents)','All','ENABLE_DIRECT_INQUIRIES_IND','CONFG','Y',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','53680C68F59CAD9BE0404F8189D80A6C','Indicates whether field level help links are enabled on lookup pages and documents.','All','ENABLE_FIELD_LEVEL_HELP_IND','CONFG','N',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','53680C68F59DAD9BE0404F8189D80A6C','Maximum file upload size for the application. Used by PojoFormBase. Must be an integer, optionally followed by "K", "M", or "G". Only used if no other upload limits are in effect.','All','MAX_FILE_SIZE_DEFAULT_UPLOAD','CONFG','5M',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','5a5fbe94-846f-102c-8db0-c405cae621f3','A semi-colon delimted list of regular expressions that identify 
potentially sensitive data in strings.  These patterns will be matched 
against notes, document explanations, and routing annotations.','All','SENSITIVE_DATA_PATTERNS','CONFG','[0-9]{9};[0-9]{3}-[0-9]{2}-[0-9]{4}',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','e7d133f3-b5fe-11df-ad0a-d18f5709259f','If set to \'Y\' when sensitive data is found the user will be prompted to continue the action or cancel. If this is set to \'N\' the user will be presented with an error message and will not be allowed to continue with the action until the sensitive data is removed.','All','SENSITIVE_DATA_PATTERNS_WARNING_IND','CONFG','N',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','664F8ABEC722DBCDE0404F8189D85427','A semi-colon delimted list of strings representing date formats that the DateTimeService will use to parse dates when DateTimeServiceImpl.convertToSqlDate(String) or DateTimeServiceImpl.convertToDate(String) is called. Note that patterns will be applied in the order listed (and the first applicable one will be used). For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.','All','STRING_TO_DATE_FORMATS','CONFG','MM/dd/yyyy hh:mm a;MM/dd/yy;MM/dd/yyyy;MM-dd-yy;MMddyy;MMMM dd;yyyy;MM/dd/yy HH:mm:ss;MM/dd/yyyy HH:mm:ss;MM-dd-yy HH:mm:ss;MMddyy HH:mm:ss;MMMM dd HH:mm:ss;yyyy HH:mm:ss',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','664F8ABEC727DBCDE0404F8189D85427','A semi-colon delimted list of strings representing date formats that the DateTimeService will use to parse date and times when DateTimeServiceImpl.convertToDateTime(String) or DateTimeServiceImpl.convertToSqlTimestamp(String) is called. Note that patterns will be applied in the order listed (and the first applicable one will be used). For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.','All','STRING_TO_TIMESTAMP_FORMATS','CONFG','MM/dd/yyyy hh:mm a;MM/dd/yy;MM/dd/yyyy;MM-dd-yy;MMddyy;MMMM dd;yyyy;MM/dd/yy HH:mm:ss;MM/dd/yyyy HH:mm:ss;MM-dd-yy HH:mm:ss;MMddyy HH:mm:ss;MMMM dd HH:mm:ss;yyyy HH:mm:ss',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','664F8ABEC724DBCDE0404F8189D85427','A single date format string that the DateTimeService will use to format a date and time string to be used in a file name when DateTimeServiceImpl.toDateTimeStringForFilename(Date) is called.. For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.','All','TIMESTAMP_TO_STRING_FORMAT_FOR_FILE_NAME','CONFG','yyyyMMdd-HH-mm-ss-S',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','664F8ABEC726DBCDE0404F8189D85427','A single date format string that the DateTimeService will use to format a date and time to be displayed on a web page. For a more technical description of how characters in the parameter value will be interpreted, please consult the javadocs for java.text.SimpleDateFormat. Any changes will be applied when the application is restarted.','All','TIMESTAMP_TO_STRING_FORMAT_FOR_USER_INTERFACE','CONFG','MM/dd/yyyy hh:mm a',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','5A689075D35E7AEBE0404F8189D80321','Batch file types that are active options for the file upload screen.','Batch','ACTIVE_FILE_TYPES','CONFG','collectorInputFileType;procurementCardInputFileType;enterpriseFeederFileSetType;assetBarcodeInventoryInputFileType;customerLoadInputFileType',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','9CD01DC99C65DF6CE040DC0A1F8A7146','Controls whether the nervous system will show the blanket approve button to a user who is authorized for blanket approval but is neither the initiator of the particular document nor the recipient of an active, pending, approve action request.','Document','ALLOW_ENROUTE_BLANKET_APPROVE_WITHOUT_APPROVAL_REQUEST_IND','CONFG','N',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','53680C68F59EAD9BE0404F8189D80A6C','If Y, the Route Report button will be displayed on the document actions bar if the document is using the default DocumentAuthorizerBase.getDocumentActionFlags to set the canPerformRouteReport property of the returned DocumentActionFlags instance.','Document','DEFAULT_CAN_PERFORM_ROUTE_REPORT_IND','CONFG','N',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','53680C68F5A0AD9BE0404F8189D80A6C','Maximum attachment upload size for the application. Used by KualiDocumentFormBase. Must be an integer, optionally followed by "K", "M", or "G".','Document','MAX_FILE_SIZE_ATTACHMENT','CONFG','5M',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','53680C68F5A1AD9BE0404F8189D80A6C','Some documents provide the functionality to send notes to another user using a workflow FYI or acknowledge functionality. This parameter specifies the default action that will be used when sending notes. This parameter should be one of the following 2 values: "K" for acknowledge or "F" for fyi. Depending on the notes and workflow service implementation, other values may be possible.','Document','SEND_NOTE_WORKFLOW_NOTIFICATION_ACTIONS','CONFG','K',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','53680C68F5A4AD9BE0404F8189D80A6C','The number of minutes before a session expires that user should be warned when a document uses pessimistic locking.','Document','SESSION_TIMEOUT_WARNING_MESSAGE_TIME','CONFG','5',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','53680C68F5A3AD9BE0404F8189D80A6C','Lookup results may continue to be persisted in the DB long after they are needed. This parameter represents the maximum amount of time, in seconds, that the results will be allowed to persist in the DB before they are deleted from the DB.','Lookup','MULTIPLE_VALUE_RESULTS_EXPIRATION_SECONDS','CONFG','86400',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','53680C68F5A6AD9BE0404F8189D80A6C','Maximum number of rows that will be displayed on a look-up results screen.','Lookup','MULTIPLE_VALUE_RESULTS_PER_PAGE','CONFG','100',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','53680C68F5A7AD9BE0404F8189D80A6C','If a maxLength attribute has not been set on a lookup result field in the data dictionary, then the result column\'s max length will be the value of this parameter. Set this parameter to 0 for an unlimited default length or a positive value (i.e. greater than 0) for a finite max length.','Lookup','RESULTS_DEFAULT_MAX_COLUMN_LENGTH','CONFG','70',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','53680C68F5A8AD9BE0404F8189D80A6C','Maximum number of results returned in a look-up query.','Lookup','RESULTS_LIMIT','CONFG','200',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','5A689075D35A7AEBE0404F8189D80321','Pending attachments are attachments that do not yet have a permanent link with the associated Business Object (BO). These pending attachments are stored in the attachments.pending.directory (defined in the configuration service). If the BO is never persisted, then this attachment will become orphaned (i.e. not associated with any BO), but will remain in this directory. The PurgePendingAttachmentsStep batch step deletes these pending attachment files that are older than the value of this parameter. The unit of this value is seconds. Do not set this value too short, as this will cause problems attaching files to BOs.','PurgePendingAttachmentsStep','MAX_AGE','CONFG','86400',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','5A689075D3597AEBE0404F8189D80321','Determines the age of the session document records that the the step will operate on, e.g. if this param is set to 4, the rows with a last update timestamp older that 4 days prior to when the job is running will be deleted.','PurgeSessionDocumentsStep','NUMBER_OF_DAYS_SINCE_LAST_UPDATE','CONFG','1',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','5A689075D35C7AEBE0404F8189D80321','Controls when the daily batch schedule should terminate. The scheduler service implementation compares the start time of the schedule job from quartz with this time on day after the schedule job started running.','ScheduleStep','CUTOFF_TIME','CONFG','02:00:00:AM',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','5A689075D35D7AEBE0404F8189D80321','Controls whether when the system is comparing the schedule start day & time with the scheduleStep_CUTOFF_TIME parameter, it considers the specified time to apply to the day after the schedule starts.','ScheduleStep','CUTOFF_TIME_NEXT_DAY_IND','CONFG','Y',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-NS','5A689075D35B7AEBE0404F8189D80321','Time in milliseconds that the scheduleStep should wait between iterations.','ScheduleStep','STATUS_CHECK_INTERVAL','CONFG','30000',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','290E45BA032F4F4FB423CE5F78AC52E1','Flag to specify if clicking on a Document ID from the Action List will load the Document in a new window.','ActionList','ACTION_LIST_DOCUMENT_POPUP_IND','CONFG','Y',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','967B0311A5E94F7191B2C544FA7DE095','Flag to specify if clicking on a Route Log from the Action List will load the Route Log in a new window.','ActionList','ACTION_LIST_ROUTE_LOG_POPUP_IND','CONFG','N',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','340789CDF30F4252A1A2A42AD39B90B2','Default email address used for testing.','ActionList','EMAIL_NOTIFICATION_TEST_ADDRESS','CONFG',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','2CE075BC0C59435CA6DEFF724492DE3F','Throttles the number of results returned on all users Action Lists, regardless of their user preferences.  This is intended to be used in a situation where excessively large Action Lists are causing performance issues.','ActionList','PAGE_SIZE_THROTTLE','CONFG',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','A87659E198214A8B90BE5BEF41630411','Flag to determine whether or not to send email notification.','ActionList','SEND_EMAIL_NOTIFICATION_IND','CONFG','N',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','5C731F2968A3689AE0404F8189D86653','Flag for enabling/disabling document type permission checks to use KIM Permissions as priority over Document Type policies.','All','KIM_PRIORITY_ON_DOC_TYP_PERMS_IND','CONFG','N',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','4656B6E7E9844E2C9E2255014AFC86B5','The maximum number of nodes the workflow engine will process before it determines the process is a runaway process.  This is prevent infinite "loops" in the workflow engine.','All','MAXIMUM_NODES_BEFORE_RUNAWAY','CONFG',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','8A37388A2D7A46EF9E6BF3FA8D08A03A','Flag to specify whether or not a file upload box is displayed for KEW notes which allows for uploading of an attachment with the note.','All','SHOW_ATTACHMENTS_IND','CONFG','Y',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','9BD6785416434C4D9E5F05AF077DB9B7','Flag to show the backdoor login.','Backdoor','SHOW_BACK_DOOR_LOGIN_IND','CONFG','Y',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','E78100F6F14C4932B54F7719FA5C27E9','Flag to specify if clicking on a Document ID from Document Search will load the Document in a new window.','DocSearchCriteriaDTO','DOCUMENT_SEARCH_POPUP_IND','CONFG','Y',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','632680DDE9A7478CBD379FAF90C7AE72','Flag to specify if clicking on a Route Log from Document Search will load the Route Log in a new window.','DocSearchCriteriaDTO','DOCUMENT_SEARCH_ROUTE_LOG_POPUP_IND','CONFG','N',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','D43459D143FC46C6BF83C71AC2383B76','Limit of fetch more iterations for document searches.','DocSearchCriteriaDTO','FETCH_MORE_ITERATION_LIMIT','CONFG',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','E324D85082184EB6967537B3EE1F655B','Maximum number of documents to return from a search.','DocSearchCriteriaDTO','RESULT_CAP','CONFG',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','68B2EA08E13A4FF3B9EDBD5415818C93','Defines whether the debug transform is enabled for eDcoLite.','EDocLite','DEBUG_TRANSFORM_IND','CONFG','N',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','FCAEE745A7E64AF5982937C47EBC2698','Defines whether XSLTC is used for eDocLite.','EDocLite','USE_XSLTC_IND','CONFG','N',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','BEBDBCFA74A5458EADE2CF075FFF206E','A flag to specify whether the WorkflowInfo.isLastApproverAtNode(...) API method attempts to active requests first, prior to execution.','Feature','IS_LAST_APPROVER_ACTIVATE_FIRST_IND','CONFG',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','700AB6A6E23740D0B3E00E02A8FB6347','Default from email address for notifications.','Mailer','FROM_ADDRESS','CONFG','quickstart@localhost',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','08280F2575904F3586CF48BB97907506','Defines whether or not to send a notification to users excluded from a workgroup.','Notification','NOTIFY_EXCLUDED_USERS_IND','CONFG',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','5292CFD9A0EA48BEB22A2EB3B3BD3CDA','Comma seperated list of Document Types to exclude from the Rule Quicklinks.','QuickLinks','RESTRICT_DOCUMENT_TYPES','CONFG',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','E05A692D62E54B87901D872DC37208A1','Indicator to determine if rule caching is enabled.','Rule','CACHING_IND','CONFG','Y',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','BDE964269F2743338C00A4326B676195','Defines custom Document Type processes to use for certain types of routing rules.','Rule','CUSTOM_DOCUMENT_TYPES','CONFG',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','21EA54B9A9E846709E76C176DE0AF47C','Specifies that maximum number of delegation rules that will be displayed on a Rule inquiry before the screen shows a count of delegate rules and provides a link for the user to show them.','Rule','DELEGATE_LIMIT','CONFG','20',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','96868C896B4B4A8BA87AD20E42948431','Flag to determine whether or not a change to a routing rule should be applied retroactively to existing documents.','Rule','GENERATE_ACTION_REQUESTS_IND','CONFG','Y',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KUALI','A','KR-WKFLW','8AE796DB88484468830A8879630CCF5D','Amount of time after a rule change is made before the rule cache update message is sent.','Rule','RULE_CACHE_REQUEUE_DELAY','CONFG','5000',1)
/
delimiter ;
