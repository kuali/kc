DELIMITER /
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-IACUC', 'Document', 'iacuc.protocol.proposal.development.linking.enabled', UUID(), 1, 'CONFG', 'Y', 'Linking from Proposal Development to IACUC PROTOCOL Funding source is configurable at impl time', 'A', 'KC')
/

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-IACUC', 'Document', 'iacuc.protocol.institute.proposal.linking.enabled', UUID(), 1, 'CONFG', 'Y', 'Linking from Institution Proposal to IACUC PROTOCOL Funding source is configurable at impl time', 'A', 'KC')
/

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-IACUC', 'Document', 'iacuc.protocol.award.linking.enabled', UUID(), 1, 'CONFG', 'Y', 'Linking from Award to IACUC PROTOCOL Funding source is configurable at impl time', 'A', 'KC')
/

DELIMITER ;
