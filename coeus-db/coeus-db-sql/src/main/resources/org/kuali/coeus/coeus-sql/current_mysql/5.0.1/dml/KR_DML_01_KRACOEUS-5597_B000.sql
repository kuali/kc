DELIMITER /
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-PD', 'Document', 'proposal.summary.validSpecialReviewCodes', UUID(), 1, 'CONFG', '', 'comma delimited list of special review codes that should be displayed on approver view for proposal development. Display all special review codes if parameter value is empty or blank', 'A', 'KC')
/

DELIMITER ;
