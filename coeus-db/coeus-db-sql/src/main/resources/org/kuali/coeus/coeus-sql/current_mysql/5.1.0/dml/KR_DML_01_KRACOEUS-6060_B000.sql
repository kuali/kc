DELIMITER /
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-B', 'Document', 'SUBCONTRACTOR_DIRECT_GT_25K', UUID(), 1, 'CONFG', '420620', 'Cost Element used to account for subcontract direct funds on budget (NIH Only) for subcontracts greater than 25,000. Related to CoeusQA-2115 expanded subaward file upload support.', 'A', 'KC')
/

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-B', 'Document', 'SUBCONTRACTOR_DIRECT_LT_25K', UUID(), 1, 'CONFG', '420600', 'Cost Element used to account for subcontract direct funds on budget (NIH Only) for subcontracts less than 25,000. Related to CoeusQA-2115 expanded subaward file upload support.', 'A', 'KC')
/

DELIMITER ;
