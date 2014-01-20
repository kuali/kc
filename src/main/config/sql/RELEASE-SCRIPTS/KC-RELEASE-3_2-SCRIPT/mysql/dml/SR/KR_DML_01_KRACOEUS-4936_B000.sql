DELIMITER /


INSERT INTO KRCR_NMSPC_T (ACTV_IND,NM,NMSPC_CD,APPL_ID,OBJ_ID,VER_NBR)
  VALUES ('Y','KC Negotiation','KC-NEGOTIATION','KC',UUID(),1)
/

INSERT INTO KRCR_CMPNT_T (NMSPC_CD, CMPNT_CD, NM, ACTV_IND, OBJ_ID, VER_NBR)
	VALUES ('KC-NEGOTIATION', 'Document', 'Document', 'Y', UUID(), 1)
/

INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,PARM_TYP_CD,CMPNT_CD,PARM_NM,PARM_DESC_TXT,VAL,EVAL_OPRTR_CD,OBJ_ID,VER_NBR)
    VALUES ('KC', 'KC-NEGOTIATION', 'CONFG', 'Document', 'negotiationInProgressStatusCodes', 'A comma delimited list of the negotiation status codes that are considered in progress. The first status defined here will be used as the default status for new negotiations.', 'IP', 'A', UUID(), 1)
/

INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,PARM_TYP_CD,CMPNT_CD,PARM_NM,PARM_DESC_TXT,VAL,EVAL_OPRTR_CD,OBJ_ID,VER_NBR)
    VALUES ('KC', 'KC-NEGOTIATION', 'CONFG', 'Document', 'negotiationCompletedStatusCodes', 'A comma delimited list of the negotiation status codes that are considered completed.', 'COM,SP', 'A', UUID(), 1)
/

DELIMITER ;
