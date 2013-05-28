DELIMITER /

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-COIDISCLOSURE', 'Document', 'ANNUAL_DISCLOSURE_ADVANCE_NOTICE', UUID(), 1, 'CONFG', '30', 'Number of days in advance of Annual Disclosure due date to start displaying message in COI channel', 'A', 'KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-COIDISCLOSURE', 'Document', 'ANNUAL_DISCLOSURE_RENEWAL_DATE', UUID(), 1, 'CONFG', '0', 'Date on which Annual Disclosures are due for renewal, in MM/DD/YYYY format.  Or, use 0 if renewal is due on anniversary of last Annual Disclosure Certification', 'A', 'KC')
/

DELIMITER ;
