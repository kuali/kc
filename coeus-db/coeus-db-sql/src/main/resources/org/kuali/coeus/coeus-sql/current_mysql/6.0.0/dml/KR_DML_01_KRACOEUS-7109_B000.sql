DELIMITER /

INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-GEN','All','PERSON_ROLE_SPONSOR_HIERARCHIES',UUID(),1,'CONFG','NIH Multiple PI','A comma delimited list of sponsor hierarchies that are used to determine what roles from the PropAwardPersonRole table will be used for PD, IP and Award Personnel','A','KC')
/

DELIMITER ;
