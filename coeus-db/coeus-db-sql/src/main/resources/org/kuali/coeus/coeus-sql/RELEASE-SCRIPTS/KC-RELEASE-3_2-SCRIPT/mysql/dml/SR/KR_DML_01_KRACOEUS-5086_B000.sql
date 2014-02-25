DELIMITER /
--
--


INSERT INTO KRCR_PARM_T (APPL_ID,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
VALUES ('KC','A','KC-PD',UUID(),'This is the institutional Fields Conditionally Required','Document','institutionalFieldsConditionallyRequiredHelpUrl','HELP','default.htm?turl=Documents/institutionalfieldsconditionallyrequired.htm','1')
/

INSERT INTO KRCR_PARM_T (APPL_ID,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
VALUES ('KC','A','KC-PD',UUID(),'This is the required Fields for Saving Document','Document','requiredFieldForSavingDocumentHelpUrl','HELP','default.htm?turl=Documents/requiredfieldsforsavingdocument.htm','1')
/

DELIMITER ;
