DELIMITER /
--
--

-- add mapping for Preaward authorization help and correct link for Budget Versions help
INSERT INTO KRCR_PARM_T (APPL_ID, nmspc_cd, CMPNT_CD,parm_nm,ver_nbr,parm_typ_cd,VAL,parm_desc_txt,EVAL_OPRTR_CD,obj_id) 
   VALUES ('KC','KC-AWARD','Document','awardSponsorAuthHelp',1,'HELP','default.htm?turl=Documents/sponsorauthorization.htm','Sponsor Authorization Help','A',UUID())
/
INSERT INTO KRCR_PARM_T (APPL_ID, nmspc_cd, CMPNT_CD,parm_nm,ver_nbr,parm_typ_cd,VAL,parm_desc_txt,EVAL_OPRTR_CD,obj_id) 
   VALUES ('KC','KC-AWARD','Document','awardInstitutionalAuthHelp',1,'HELP','default.htm?turl=Documents/institutionalauthorization.htm','Institutional Authorization Help','A',UUID())
/
INSERT INTO KRCR_PARM_T (APPL_ID, nmspc_cd, CMPNT_CD,parm_nm,ver_nbr,parm_typ_cd,VAL,parm_desc_txt,EVAL_OPRTR_CD,obj_id) 
   VALUES ('KC','KC-AWARD','Document','awardBudgetVersionsHelp',1,'HELP','default.htm?turl=Documents/budgetversions3.htm','Budget Versions Help','A',UUID())
/

COMMIT
/

DELIMITER ;
