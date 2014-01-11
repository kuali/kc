alter table EPS_PROPOSAL_DOCUMENT add PROPOSAL_DELETED VARCHAR2(1);
alter table BUDGET_DOCUMENT add BUDGET_DELETED VARCHAR2(1);

insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
	VALUES ('1247', sys_guid(), 1, '1002', 'KC-PD', 'Delete Proposal', 'Delete a Proposal', 'Y');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
	VALUES ('1704', sys_guid(), 1, '1247', '1007', '1004', 'delete_proposal');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
	VALUES ('1705', sys_guid(), 1, '1247', '1007', '13', 'ProposalDevelopmentDocument');

insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
	VALUES ('1245', sys_guid(), 1, 'Delete Proposal', 'KC-PD', 'Delete Proposal Permission', '1001', 'Y', null);
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
	VALUES ('1155', sys_guid(), 1, '1245', '1247', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
	VALUES ('1156', sys_guid(), 1, '1245', '1021', 'Y');	
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
	VALUES ('1157', sys_guid(), 1, '1245', '1022', 'Y');	
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
	VALUES ('1158', sys_guid(), 1, '1245', '1023', 'Y');	

insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
	VALUES ('1506', 1, sys_guid(), '1245', '10000000001', 'P', null, null, null);
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
	VALUES ('1404', sys_guid(), 1, '1506', '1002', '1001', '000001');