INSERT INTO CLOSEOUT_REPORT_TYPE(CLOSEOUT_REPORT_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID)
    VALUES ('6','Invoice',SYSDATE,'admin','1',SYS_GUID())
/

INSERT INTO KRNS_PARM_T(APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)
	VALUES('KC', 'KC-AWARD', 'Document', 'closeoutReportTypeInvoice', 1, 'CONFG', '6', 'This system parameter maps the CloseoutReportType Invoice(closeoutReoprtTypeCode=6) with ReportClass Payment/Invoice(reportClassCode=6). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.', 'A', SYS_GUID())
/

UPDATE CLOSEOUT_REPORT_TYPE SET DESCRIPTION = 'Financial' where CLOSEOUT_REPORT_CODE = 1
/
