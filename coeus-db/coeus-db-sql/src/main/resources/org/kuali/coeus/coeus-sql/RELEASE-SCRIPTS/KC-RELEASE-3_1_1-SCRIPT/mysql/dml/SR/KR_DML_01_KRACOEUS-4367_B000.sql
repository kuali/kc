DELIMITER /
--
--

INSERT INTO KRCR_PARM_T (APPL_ID, nmspc_cd, CMPNT_CD,parm_nm,ver_nbr,parm_typ_cd,VAL,parm_desc_txt,EVAL_OPRTR_CD,obj_id)
   VALUES ('KC','KC-AWARD','Document','option.warning.error.award.FnA.validation',1,'CONFG','E','This parameter is used when paramater enable.award.FnA.validation = 2.If this parameter value = "W" then warning is given after validation and if value= "E" then error is given after validation.','A',UUID())
/
update KRCR_PARM_T set parm_nm='enable.award.FnA.validation',parm_desc_txt='0=No validation of Award F&A or Benefits rates, 1=Award rates must be entered as valid rate pairs, 2=Single Award rates are validated against Valid Rates table' where parm_nm='mit.idc.validation.enabled'
/

DELIMITER ;
