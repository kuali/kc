DELIMITER /

-- create the 'assign to IRB committee' permission
INSERT INTO KRIM_PERM_ID_BS_S VALUES(NULL)
/
INSERT INTO KRIM_PERM_T (PERM_ID, NMSPC_CD, NM, DESC_TXT, PERM_TMPL_ID, ACTV_IND, VER_NBR, OBJ_ID)
	VALUES ((SELECT (MAX(ID)) FROM KRIM_PERM_ID_BS_S), 'KC-PROTOCOL', 'Assign IRB Committee', 
			'This permission allows the assignment (of a submission) to an IRB committee, and is meant to be granted to unit-hierarchy type admin roles for IRB protocols.',
			(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'), 'Y', 1, UUID())
/

		
-- grant it to the irb admin role
INSERT INTO KRIM_ROLE_PERM_ID_BS_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
	VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_BS_S), UUID(), 1, 
			(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IRB Administrator' AND NMSPC_CD='KC-UNT'),
			(SELECT (MAX(ID)) FROM KRIM_PERM_ID_BS_S), 'Y')
/

-- create the 'assign to IACUC committee' permission
INSERT INTO KRIM_PERM_ID_BS_S VALUES(NULL)
/
INSERT INTO KRIM_PERM_T (PERM_ID, NMSPC_CD, NM, DESC_TXT, PERM_TMPL_ID, ACTV_IND, VER_NBR, OBJ_ID)
	VALUES ((SELECT (MAX(ID)) FROM KRIM_PERM_ID_BS_S), 'KC-IACUC', 'Assign IACUC Committee', 
			'This permission allows the assignment (of a submission) to an IACUC committee, and is meant to be granted to unit-hierarchy type admin roles for IACUC protocols.',
			(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'), 'Y', 1, UUID())
/


-- grant it to the iacuc admin role
INSERT INTO KRIM_ROLE_PERM_ID_BS_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
	VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_BS_S), UUID(), 1, 
			(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IACUC Administrator' AND NMSPC_CD='KC-UNT'),
			(SELECT (MAX(ID)) FROM KRIM_PERM_ID_BS_S), 'Y')
/

DELIMITER ;
