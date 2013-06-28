DELIMITER /

insert into KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
	values ('KC-COIDISCLOSURE', 'Document', 'WORK_IN_PROGRESS_REVIEW_STATUSES', UUID(), 1, 'CONFG', '6;7;8', 'A list of semi-colon delimited COI review status codes that are considered ''In Progress''', 'A', 'KC')
/

DELIMITER ;