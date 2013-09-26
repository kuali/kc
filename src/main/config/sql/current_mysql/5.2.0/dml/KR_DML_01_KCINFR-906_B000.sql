DELIMITER /

Insert into KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) values 
('KR-WKFLW','All','KIM_PRIORITY_ON_DOC_TYP_PERMS_IND',UUID(),2,'CONFG','N','Flag for enabling disabling document type permission checks to use KIM Permissions as priority over Document Type policies.','A','KC');
/

DELIMITER ;
