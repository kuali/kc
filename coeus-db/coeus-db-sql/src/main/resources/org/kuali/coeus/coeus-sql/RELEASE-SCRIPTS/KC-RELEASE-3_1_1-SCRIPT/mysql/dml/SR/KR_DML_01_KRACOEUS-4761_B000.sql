DELIMITER /
--
--

insert into KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID)
values ('KC','KC-PD','Document','CURRENT_PENDING_REPORT_GROUP_NAME','CONFG','Current and pending report group','Custom group name','A',UUID())
/

DELIMITER ;
