DELIMITER /

INSERT INTO KRCR_PARM_T (nmspc_cd, CMPNT_CD, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, VAL, parm_desc_txt, EVAL_OPRTR_CD)  VALUES ('KC-B','Document','ENABLE_COST_SHARE_SUBMIT',UUID(),1,'CONFG','1','Enable schools the option to submit or not-submit cost sharing at the summary and \/ or line item levels','A')
/
COMMIT
/


DELIMITER ;
