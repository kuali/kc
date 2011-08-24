DELIMITER /

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
   VALUES('KC', 'KC-AWARD', 'Document', 'FEDERAL_SPONSOR_TYPE_CODE', 'CONFG', '', 'When the sponsor or the prime sponsor has the sponsor type specified in this parameter and the KFS parameter FEDERAL_ONLY_IND is "Y", the Effort reporting Document is routed', 'A', UUID())
/
COMMIT
/

DELIMITER ;

