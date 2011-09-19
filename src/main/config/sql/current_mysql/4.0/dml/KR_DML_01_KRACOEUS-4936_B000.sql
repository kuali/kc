DELIMITER /
INSERT INTO KRNS_NMSPC_T (ACTV_IND,NM,NMSPC_CD,APPL_NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','KC Negotiation','KC-NEGOTIATION','KC',UUID(),1)
/

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_TYP_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_DESC_TXT,TXT,CONS_CD,OBJ_ID,VER_NBR)
    VALUES ('KC', 'KC-NEGOTIATION', 'CONFG', 'Document', 'negotiationInProgressStatusCodes', 'A comma delimited list of the negotiation status codes that are considered in progress.', 'IP', 'A', UUID(), 1)
/

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_TYP_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_DESC_TXT,TXT,CONS_CD,OBJ_ID,VER_NBR)
    VALUES ('KC', 'KC-NEGOTIATION', 'CONFG', 'Document', 'negotiationCompletedStatusCodes', 'A comma delimited list of the negotiation status codes that are considered in progress.', 'COM,SP', 'A', UUID(), 1)
/
DELIMITER ;
