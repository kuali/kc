DELIMITER /
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-PD', 'Document', 'allowProposalDevelopmentNotesDeletion', UUID(), 1, 'AUTH', 'N', 'This parameter is used by the proposal development document authorizer to allow/disallow deletion of notes in proposal development documents, if the person performing the deletion has proper permissions to do so.', 'A', 'KC')
/
DELIMITER ;
