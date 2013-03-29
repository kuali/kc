DELIMITER /


DELETE FROM KRCR_PARM_T WHERE PARM_NM='s2sschedulercronExpressionstarttime' and APPL_ID='KC' and NMSPC_CD='KC-PD'
/
INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,PARM_TYP_CD,CMPNT_CD,PARM_NM,PARM_DESC_TXT,VAL,EVAL_OPRTR_CD,OBJ_ID,VER_NBR)
    VALUES ('KC','KC-PD','CONFG','Document','s2s.polling.scheduler.enabled','Enable S2S Polling Scheduler cron job".','false','A',UUID(),1)
/

DELIMITER ;
