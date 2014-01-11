DELIMITER /

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
   VALUES('KC', 'KC-AWARD', 'Document', 'FEDERAL_SPONSOR_TYPE_CODE', 'CONFG', '', 'When the sponsor or the prime sponsor has the sponsor type specified in this parameter and the KFS parameter FEDERAL_ONLY_IND is "Y", the Effort reporting Document is routed', 'A', UUID())
/

COMMIT
/

DELIMITER ;
