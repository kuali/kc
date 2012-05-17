DELIMITER /

INSERT INTO KRNS_PARM_T (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)  VALUES ('KC-B','Document','ENABLE_COST_SHARE_SUBMIT',UUID(),1,'CONFG','1','Enable schools the option to submit or not-submit cost sharing at the summary and \/ or line item levels','A')
/
COMMIT
/

DELIMITER ;
