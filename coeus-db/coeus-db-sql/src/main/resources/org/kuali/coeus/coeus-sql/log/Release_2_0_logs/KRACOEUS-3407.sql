delete from krim_role_perm_t 
where perm_id in (select perm_id from krim_perm_t where nm like '%Proposal Log%') 
and role_id in (select role_id from krim_role_t where role_nm = 'OSP Administrator');

INSERT INTO KRIM_ROLE_T (ROLE_ID,ROLE_NM,NMSPC_CD,DESC_TXT,KIM_TYP_ID,ACTV_IND,VER_NBR,OBJ_ID,LAST_UPDT_DT) 
VALUES (KRIM_ROLE_ID_S.NEXTVAL,'Create Temporary Proposal Log','KC-IP','Create Temporary Proposal Log',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy' AND NMSPC_CD = 'KC-SYS'),
'Y',1,SYS_GUID(),TO_TIMESTAMP('30-OCT-09','DD-MON-RR HH.MI.SSXFF AM'));

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Temporary Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Create Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Temporary Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Edit Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Temporary Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Save Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Temporary Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Submit Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Temporary Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Open Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Temporary Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Cancel Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

INSERT INTO KRIM_ROLE_T (ROLE_ID,ROLE_NM,NMSPC_CD,DESC_TXT,KIM_TYP_ID,ACTV_IND,VER_NBR,OBJ_ID,LAST_UPDT_DT) 
VALUES (KRIM_ROLE_ID_S.NEXTVAL,'Create Proposal Log','KC-IP','Create Proposal Log',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy' AND NMSPC_CD = 'KC-SYS'),
'Y',1,SYS_GUID(),TO_TIMESTAMP('30-OCT-09','DD-MON-RR HH.MI.SSXFF AM'));

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Create Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Edit Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Save Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Submit Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Open Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Cancel Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

INSERT INTO KRIM_ROLE_T (ROLE_ID,ROLE_NM,NMSPC_CD,DESC_TXT,KIM_TYP_ID,ACTV_IND,VER_NBR,OBJ_ID,LAST_UPDT_DT) 
VALUES (KRIM_ROLE_ID_S.NEXTVAL,'Modify Proposal Log','KC-IP','Modify Proposal Log',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy' AND NMSPC_CD = 'KC-SYS'),
'Y',1,SYS_GUID(),TO_TIMESTAMP('30-OCT-09','DD-MON-RR HH.MI.SSXFF AM'));

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Create Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Edit Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Save Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Submit Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Open Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Cancel Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

INSERT INTO KRIM_ROLE_T (ROLE_ID,ROLE_NM,NMSPC_CD,DESC_TXT,KIM_TYP_ID,ACTV_IND,VER_NBR,OBJ_ID,LAST_UPDT_DT) 
VALUES (KRIM_ROLE_ID_S.NEXTVAL,'View Proposal Log','KC-IP','View Proposal Log',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy' AND NMSPC_CD = 'KC-SYS'),
'Y',1,SYS_GUID(),TO_TIMESTAMP('30-OCT-09','DD-MON-RR HH.MI.SSXFF AM'));

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'View Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Open Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'OSP Administrator' AND NMSPC_CD = 'KC-ADM'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Create Institutional Proposal' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'OSP Administrator' AND NMSPC_CD = 'KC-ADM'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Edit Institutional Proposal' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'OSP Administrator' AND NMSPC_CD = 'KC-ADM'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Save Institutional Proposal' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'OSP Administrator' AND NMSPC_CD = 'KC-ADM'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Submit Institutional Proposal' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'OSP Administrator' AND NMSPC_CD = 'KC-ADM'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Open Institutional Proposal' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'OSP Administrator' AND NMSPC_CD = 'KC-ADM'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Cancel Institutional Proposal' AND NMSPC_CD = 'KC-IP'),
'Y',SYS_GUID(),1);

INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, MBR_TYP_CD, MBR_ID, ROLE_ID, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT, VER_NBR, OBJ_ID) 
VALUES (KRIM_ROLE_MBR_ID_S.NEXTVAL, 'P',
(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'borst'),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Proposal Log' AND NMSPC_CD = 'KC-IP'),
NULL, NULL, SYSDATE, 1, SYS_GUID());

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, VER_NBR, OBJ_ID) 
VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, krim_role_mbr_id_s.currval, 
	(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy' AND NMSPC_CD = 'KC-SYS'), 
	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'unitNumber' AND NMSPC_CD = 'KC-SYS'), 
	'000001', 1, sys_guid());

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, VER_NBR, OBJ_ID) 
VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, krim_role_mbr_id_s.currval, 
		(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy' AND NMSPC_CD = 'KC-SYS'), 
		(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'subunits' AND NMSPC_CD = 'KC-SYS'), 
		'Y', 1, sys_guid());

INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, MBR_TYP_CD, MBR_ID, ROLE_ID, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT, VER_NBR, OBJ_ID) 
VALUES (KRIM_ROLE_MBR_ID_S.NEXTVAL, 'P',
(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Proposal Log' AND NMSPC_CD = 'KC-IP'),
NULL, NULL, SYSDATE, 1, SYS_GUID());

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, VER_NBR, OBJ_ID) 
VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, krim_role_mbr_id_s.currval, 
	(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy' AND NMSPC_CD = 'KC-SYS'), 
	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'unitNumber' AND NMSPC_CD = 'KC-SYS'), 
	'000001', 1, sys_guid());

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, VER_NBR, OBJ_ID) 
VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, krim_role_mbr_id_s.currval, 
		(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy' AND NMSPC_CD = 'KC-SYS'), 
		(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'subunits' AND NMSPC_CD = 'KC-SYS'), 
		'Y', 1, sys_guid());

INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, MBR_TYP_CD, MBR_ID, ROLE_ID, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT, VER_NBR, OBJ_ID) 
VALUES (KRIM_ROLE_MBR_ID_S.NEXTVAL, 'P',
(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'borst'),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Proposal Log' AND NMSPC_CD = 'KC-IP'),
NULL, NULL, SYSDATE, 1, SYS_GUID());

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, VER_NBR, OBJ_ID) 
VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, krim_role_mbr_id_s.currval, 
	(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy' AND NMSPC_CD = 'KC-SYS'), 
	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'unitNumber' AND NMSPC_CD = 'KC-SYS'), 
	'000001', 1, sys_guid());

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, VER_NBR, OBJ_ID) 
VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, krim_role_mbr_id_s.currval, 
		(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy' AND NMSPC_CD = 'KC-SYS'), 
		(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'subunits' AND NMSPC_CD = 'KC-SYS'), 
		'Y', 1, sys_guid());

INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, MBR_TYP_CD, MBR_ID, ROLE_ID, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT, VER_NBR, OBJ_ID) 
VALUES (KRIM_ROLE_MBR_ID_S.NEXTVAL, 'P',
(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Proposal Log' AND NMSPC_CD = 'KC-IP'),
NULL, NULL, SYSDATE, 1, SYS_GUID());

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, VER_NBR, OBJ_ID) 
VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, krim_role_mbr_id_s.currval, 
	(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy' AND NMSPC_CD = 'KC-SYS'), 
	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'unitNumber' AND NMSPC_CD = 'KC-SYS'), 
	'000001', 1, sys_guid());

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, VER_NBR, OBJ_ID) 
VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, krim_role_mbr_id_s.currval, 
		(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy' AND NMSPC_CD = 'KC-SYS'), 
		(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'subunits' AND NMSPC_CD = 'KC-SYS'), 
		'Y', 1, sys_guid());


--COMMIT;