DELIMITER /
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-UNT','Document','unitHierarchyHelp',UUID(),1,'HELP','default.htm?turl=Documents/unithierarchy.htm','Unit Hierarchy Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-M','Document','batchCorrespondenceHelp',UUID(),1,'HELP','default.htm?turl=Documents/batchcorrespondence1.htm','Batch Correspondence Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-M','Document','researchAreaHelp',UUID(),1,'HELP','default.htm?turl=Documents/researchareas.htm','Research Areas Help','A','KC')
/
COMMIT
/
DELIMITER ;
