DELIMITER /
--
--

INSERT INTO KRCR_PARM_T(APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID)
	VALUES('KC', 'KC-AWARD', 'Document', 'closeoutReportTypeInvoice', 1, 'CONFG', '6', 'This system parameter maps the CloseoutReportType Invoice(closeoutReoprtTypeCode=6) with ReportClass Payment/Invoice(reportClassCode=6). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.', 'A', UUID())
/

DELIMITER ;
