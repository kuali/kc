CREATE OR REPLACE VIEW OSP$REPORT AS SELECT 
	REPORT_CODE, 
	DESCRIPTION, 
	FINAL_REPORT_FLAG, 
	UPDATE_TIMESTAMP, 
	UPDATE_USER
FROM REPORT;