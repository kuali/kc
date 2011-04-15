INSERT INTO KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
VALUES (KRIM_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' and NM = 'View Document Section'), 'KC-PROTOCOL',
    'Maintain Protocol Notes', 'permission for maintaining notes on a submitted protocol', 'Y');

INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID, PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Create Award Account','Create Award Account','KC-AWARD',SYS_GUID(),KRIM_PERM_ID_BS_S.nextval,
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),1);

INSERT INTO KRIM_PERM_T(PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND) 
	VALUES( KRIM_PERM_ID_BS_S.nextval, SYS_GUID(), 0, 
            (select perm_tmpl_id from KRIM_PERM_TMPL_T where nm = 'Questionnaire Permission'), 
            'KC-PD', 'Maintain Questionnaire Usage', 'Add/Edit Questionnaire Usages for Proposal Development', 'Y');
            
INSERT INTO KRIM_PERM_T(PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND) 
	VALUES( KRIM_PERM_ID_BS_S.nextval, SYS_GUID(), 0, 
           (select perm_tmpl_id from KRIM_PERM_TMPL_T where nm = 'Questionnaire Permission'), 
            'KC-PROTOCOL', 'Maintain Questionnaire Usage', 'Add/Edit Questionnaire Usages for Protocol', 'Y');


COMMIT;
