DELIMITER /


INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,PARM_TYP_CD,CMPNT_CD,PARM_NM,PARM_DESC_TXT,VAL,EVAL_OPRTR_CD,OBJ_ID,VER_NBR)
    VALUES ('KC', 'KC-NEGOTIATION', 'CONFG', 'Document', 'CLOSED_NEGOTIATION_STATUS', 'This parameter defines the negotiation status code which is used to consider a Negotiation as closed', 'COM', 'A', UUID(), 1)
/

DELIMITER ;
