DELETE FROM KRNS_PARM_T WHERE PARM_NM='s2sschedulercronExpressionstarttime' and APPL_NMSPC_CD='KC' and NMSPC_CD='KC-PD'
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_TYP_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_DESC_TXT,TXT,CONS_CD,OBJ_ID,VER_NBR)
    VALUES ('KC','KC-PD','CONFG','Document','s2s.polling.scheduler.enabled','Enable S2S Polling Scheduler cron job".','false','A',SYS_GUID(),1)
/
