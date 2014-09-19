DELIMITER /
INSERT INTO KRCR_PARM_T(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','KEY_PERSON_CERTIFICATION_SELF_CERTIFY_ONLY',UUID(),1,'CONFG','Y','Determines if the Proposal Person Certification must match the user login name to complete questionnaire.','A','KC')
/

DELIMITER ;
