DELIMITER /
INSERT INTO KRCR_PARM_T(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-GEN','All','ALLOW_CLEAR_PESSIMISTIC_LOCK',UUID(),1,'CONFG','N','Possible values are N or Y. When set to Y, allows pessimistic locks to be cleared conveniently when a message is displayed. Do not use in production. This parameter should be set as Y for testing purposes only.','A','KC')
/
DELIMITER ;
