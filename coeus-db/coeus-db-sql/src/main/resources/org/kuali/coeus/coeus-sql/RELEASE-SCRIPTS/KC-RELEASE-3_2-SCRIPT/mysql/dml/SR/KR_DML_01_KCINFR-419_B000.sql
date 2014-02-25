DELIMITER /
--
--


INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID,VER_NBR) 
    VALUES ('KC','KC-AWARD','Document','GET_FIN_SYSTEM_URL_FROM_WSDL','CONFG','N','Whether or not to read the financial system service url directly from the WSDL file','A',UUID(),1)
/

DELIMITER ;
