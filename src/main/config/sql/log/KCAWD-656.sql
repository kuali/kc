
delete from krim_role_perm_t t where t.perm_id in (select perm_id from krim_perm_t where nmspc_cd = 'KC-AWARD' and nm like '%AwardBudget%');
delete from krim_role_perm_t t where t.perm_id in (select perm_id from krim_perm_t where nmspc_cd = 'KC-AWARD' and nm like '%Award Budget%');
delete from krim_perm_t where nmspc_cd = 'KC-AWARD' and nm like '%AwardBudget%';
delete from krim_perm_t where nmspc_cd = 'KC-AWARD' and nm like '%Award Budget%';

delete from krim_role_mbr_attr_data_t where role_mbr_id in 
	(select role_mbr_id from krim_role_mbr_t t where t.role_id in 
			(select role_id from krim_role_t where role_nm like '%Award Budget%' and nmspc_cd = 'KC-AWARD'));
delete from krim_role_mbr_t t where t.role_id in 
	(select role_id from krim_role_t where role_nm like '%Award Budget%' and nmspc_cd = 'KC-AWARD');

delete from krim_role_t where nmspc_cd = 'KC-AWARD' and role_nm like '%AwardBudget%';
delete from krim_role_t where nmspc_cd = 'KC-AWARD' and role_nm like '%Award Budget%';

insert into krns_nmspc_t(NMSPC_CD,NM,ACTV_IND) values('KC-AB','Award Budget','Y');

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values (krim_perm_id_s.nextval, (select perm_tmpl_id from krim_perm_tmpl_t where nmspc_cd = 'KR-WKFLW' and nm = 'Blanket Approve Document'), 
'KC-AB', 'Blanket Approve AwardBudgetDocument', 'Blanket Approve AwardBudgetDocument', 'Y', sys_guid()); 

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values (krim_perm_id_s.nextval, (select perm_tmpl_id from krim_perm_tmpl_t where nmspc_cd = 'KR-WKFLW' and nm = 'Route Document'), 
'KC-AB', 'Submit AwardBudget', 'Submit award budget document', 'Y', sys_guid()); 

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values (krim_perm_id_s.nextval, (select perm_tmpl_id from krim_perm_tmpl_t where nmspc_cd = 'KC-IDM' and nm = 'Perform Document Action'), 
'KC-AB', 'Approve AwardBudget', 'Approve award budget document', 'Y', sys_guid()); 


insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values (krim_perm_id_s.nextval, (select perm_tmpl_id from krim_perm_tmpl_t where nmspc_cd = 'KC-IDM' and nm = 'Perform Document Action'), 
'KC-AB', 'Post AwardBudget', 'Post award budget document', 'Y', sys_guid()); 


insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values (krim_perm_id_s.nextval, (select perm_tmpl_id from krim_perm_tmpl_t where nmspc_cd = 'KC-IDM' and nm = 'Perform Document Action'), 
'KC-AB', 'Create AwardBudget', 'Create award budget document', 'Y', sys_guid()); 


insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values (krim_perm_id_s.nextval, (select perm_tmpl_id from krim_perm_tmpl_t where nmspc_cd = 'KC-IDM' and nm = 'Perform Document Action'), 
'KC-AB', 'Maintain AwardBudgetRouting', 'Maintaining Award budget routing', 'Y', sys_guid()); 


insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values (krim_perm_id_s.nextval, (select perm_tmpl_id from krim_perm_tmpl_t where nmspc_cd = 'KC-IDM' and nm = 'Edit Document Section'), 
'KC-AB', 'Modify AwardBudget', 'Modify Award Budget at unit level', 'Y', sys_guid()); 


insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values (krim_perm_id_s.nextval, (select perm_tmpl_id from krim_perm_tmpl_t where nmspc_cd = 'KC-IDM' and nm = 'View Document Section'), 
'KC-AB', 'View AwardBudget', 'View Award Budget at unit level', 'Y', sys_guid()); 

	
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID,OBJ_ID,VER_NBR,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL) 
	values (KRIM_ATTR_DATA_ID_S.NEXTVAL,sys_guid(),1,
			(select perm_id from krim_perm_t where nm = 'Submit AwardBudget' and nmspc_cd = 'KC-AB'),
			(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Type (Permission)' AND NMSPC_CD = 'KR-SYS'),
			(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW'),'AwardBudgetDocument');

insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID,OBJ_ID,VER_NBR,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL) 
	values (KRIM_ATTR_DATA_ID_S.NEXTVAL,sys_guid(),1,
			(select perm_id from krim_perm_t where nm = 'Approve AwardBudget' and nmspc_cd = 'KC-AB'),
			(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Type (Permission)' AND NMSPC_CD = 'KR-SYS'),
			(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW'),'AwardBudgetDocument');

insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID,OBJ_ID,VER_NBR,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL) 
	values (KRIM_ATTR_DATA_ID_S.NEXTVAL,sys_guid(),1,
			(select perm_id from krim_perm_t where nm = 'Post AwardBudget' and nmspc_cd = 'KC-AB'),
			(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Type (Permission)' AND NMSPC_CD = 'KR-SYS'),
			(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW'),'AwardBudgetDocument');

insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID,OBJ_ID,VER_NBR,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL) 
	values (KRIM_ATTR_DATA_ID_S.NEXTVAL,sys_guid(),1,
			(select perm_id from krim_perm_t where nm = 'Maintain AwardBudgetRouting' and nmspc_cd = 'KC-AB'),
			(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Type (Permission)' AND NMSPC_CD = 'KR-SYS'),
			(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW'),'AwardBudgetDocument');

insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID,OBJ_ID,VER_NBR,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL) 
	values (KRIM_ATTR_DATA_ID_S.NEXTVAL,sys_guid(),1,
			(select perm_id from krim_perm_t where nm = 'Create AwardBudget' and nmspc_cd = 'KC-AB'),
			(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Type (Permission)' AND NMSPC_CD = 'KR-SYS'),
			(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW'),'AwardBudgetDocument');

insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID,OBJ_ID,VER_NBR,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL) 
	values (KRIM_ATTR_DATA_ID_S.NEXTVAL,sys_guid(),1,
			(select perm_id from krim_perm_t where nm = 'Modify AwardBudget' and nmspc_cd = 'KC-AB'),
			(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Section' AND NMSPC_CD = 'KC-SYS'),
			(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW'),'AwardBudgetDocument');

insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID,OBJ_ID,VER_NBR,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL) 
	values (KRIM_ATTR_DATA_ID_S.NEXTVAL,sys_guid(),1,
			(select perm_id from krim_perm_t where nm = 'Blanket Approve AwardBudgetDocument' and nmspc_cd = 'KC-AB'),
			(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Type (Permission)' AND NMSPC_CD = 'KR-SYS'),
			(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW'),'AwardBudgetDocument');

insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID,OBJ_ID,VER_NBR,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL) 
	values (KRIM_ATTR_DATA_ID_S.NEXTVAL,sys_guid(),1,
			(select perm_id from krim_perm_t where nm = 'Submit AwardBudget' and nmspc_cd = 'KC-AB'),
			(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Type (Permission)' AND NMSPC_CD = 'KR-SYS'),
			(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW'),'AwardBudgetDocument');

insert into krim_role_t (role_id, role_nm, nmspc_cd, desc_txt, kim_typ_id, actv_ind, last_updt_dt, obj_id) 
values (krim_role_id_s.nextval, 'Award Budget Administrator', 'KC-AB', 'Award Budget Administrator - the role grants permissions to manage any award budget at OSP level',  
(select kim_typ_id from krim_typ_t t1 where t1.nm = 'UnitHierarchy' and nmspc_cd = 'KC-SYS'), 'Y', to_date('2010-02-18 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), sys_guid());

insert into krim_role_t (role_id, role_nm, nmspc_cd, desc_txt, kim_typ_id, actv_ind, last_updt_dt, obj_id) 
values (krim_role_id_s.nextval, 'Award Budget Maintainer', 'KC-AB', 'Maintain Award Budget - the role grants permissions to modify and submit award budget at departmental level',  
(select kim_typ_id from krim_typ_t t1 where t1.nm = 'UnitHierarchy' and nmspc_cd = 'KC-SYS'), 'Y', to_date('2010-02-18 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), sys_guid());


insert into krim_role_t (role_id, role_nm, nmspc_cd, desc_txt, kim_typ_id, actv_ind, last_updt_dt, obj_id) 
values (krim_role_id_s.nextval, 'Award Budget Aggregator', 'KC-AB', 'Award Budget Aggregator - the role grants permissions to create and maintain award budget at department level',  
(select kim_typ_id from krim_typ_t t1 where t1.nm = 'UnitHierarchy' and nmspc_cd = 'KC-SYS'), 'Y', to_date('2010-02-18 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), sys_guid());

insert into krim_role_t (role_id, role_nm, nmspc_cd, desc_txt, kim_typ_id, actv_ind, last_updt_dt, obj_id) 
values (krim_role_id_s.nextval, 'Award Budget Approver', 'KC-AB', 'Award Budget Approver - the role grants permissions to edit and approve award budget',  
(select kim_typ_id from krim_typ_t t1 where t1.nm = 'UnitHierarchy' and nmspc_cd = 'KC-SYS'), 'Y', to_date('2010-02-18 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), sys_guid());


insert into krim_role_t (role_id, role_nm, nmspc_cd, desc_txt, kim_typ_id, actv_ind, last_updt_dt, obj_id) 
values (krim_role_id_s.nextval, 'Award Budget Modifier', 'KC-AB', 'Award Budget Modifier - the role grants permissions to modify or view award budget at departmental level',  
(select kim_typ_id from krim_typ_t t1 where t1.nm = 'Unit' and nmspc_cd = 'KC-SYS'), 'Y', to_date('2010-02-18 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), sys_guid());

insert into krim_role_t (role_id, role_nm, nmspc_cd, desc_txt, kim_typ_id, actv_ind, last_updt_dt, obj_id) 
values (krim_role_id_s.nextval, 'Award Budget Viewer', 'KC-AB', 'Award Budget Viewer - the role grants permissions to view award budget at departmental level',  
(select kim_typ_id from krim_typ_t t1 where t1.nm = 'Unit' and nmspc_cd = 'KC-SYS'), 'Y', to_date('2010-02-18 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), sys_guid());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
	(krim_role_perm_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Award Budget Administrator'), 
	(select perm_id from krim_perm_t where nm = 'Create AwardBudget' and nmspc_cd = 'KC-AB'), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
	(krim_role_perm_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Award Budget Administrator'), 
	(select perm_id from krim_perm_t where nm = 'Post AwardBudget' and nmspc_cd = 'KC-AB'), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
	(krim_role_perm_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Award Budget Administrator'), 
	(select perm_id from krim_perm_t where nm = 'Submit AwardBudget' and nmspc_cd = 'KC-AB'), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
	(krim_role_perm_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Award Budget Administrator'), 
	(select perm_id from krim_perm_t where nm = 'Maintain AwardBudgetRouting' and nmspc_cd = 'KC-AB'), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
	(krim_role_perm_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Award Budget Administrator'), 
	(select perm_id from krim_perm_t where nm = 'Modify AwardBudget' and nmspc_cd = 'KC-AB'), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
	(krim_role_perm_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Award Budget Administrator'), 
	(select perm_id from krim_perm_t where nm = 'View AwardBudget' and nmspc_cd = 'KC-AB'), 'Y', sys_guid());
	
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
	(krim_role_perm_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Award Budget Maintainer'), 
	(select perm_id from krim_perm_t where nm = 'Submit AwardBudget' and nmspc_cd = 'KC-AB'), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
	(krim_role_perm_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Award Budget Maintainer'), 
	(select perm_id from krim_perm_t where nm = 'Modify AwardBudget' and nmspc_cd = 'KC-AB'), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
	(krim_role_perm_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Award Budget Maintainer'), 
	(select perm_id from krim_perm_t where nm = 'View AwardBudget' and nmspc_cd = 'KC-AB'), 'Y', sys_guid());
	
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
	(krim_role_perm_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Award Budget Aggregator'), 
	(select perm_id from krim_perm_t where nm = 'Create AwardBudget' and nmspc_cd = 'KC-AB'), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
	(krim_role_perm_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Award Budget Aggregator'), 
	(select perm_id from krim_perm_t where nm = 'Submit AwardBudget' and nmspc_cd = 'KC-AB'), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
	(krim_role_perm_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Award Budget Aggregator'), 
	(select perm_id from krim_perm_t where nm = 'View AwardBudget' and nmspc_cd = 'KC-AB'), 'Y', sys_guid());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
	(krim_role_perm_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Award Budget Viewer'), 
	(select perm_id from krim_perm_t where nm = 'View AwardBudget' and nmspc_cd = 'KC-AB'), 'Y', sys_guid());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
	(krim_role_perm_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Award Budget Modifier'), 
	(select perm_id from krim_perm_t where nm = 'Modify AwardBudget' and nmspc_cd = 'KC-AB'), 'Y', sys_guid());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
	(krim_role_perm_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Award Budget Approver'), 
	(select perm_id from krim_perm_t where nm = 'Approve AwardBudget' and nmspc_cd = 'KC-AB'), 'Y', sys_guid());

INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT, VER_NBR, OBJ_ID) 
VALUES (KRIM_ROLE_MBR_ID_S.NEXTVAL, (SELECT ROLE_ID FROM KRIM_ROLE_T T1 WHERE T1.ROLE_NM = 'Award Budget Administrator'), 
		(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T T2 WHERE T2.PRNCPL_NM = 'quickstart'), 
		'P', NULL, NULL, to_date('2010-02-18 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, SYS_GUID());

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

INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT, VER_NBR, OBJ_ID) 
VALUES (KRIM_ROLE_MBR_ID_S.NEXTVAL, (SELECT ROLE_ID FROM KRIM_ROLE_T T1 WHERE T1.ROLE_NM = 'Award Budget Approver'), 
		(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T T2 WHERE T2.PRNCPL_NM = 'jtester'), 
		'P', NULL, NULL, to_date('2010-02-18 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, SYS_GUID());

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
		
		
    delete from krim_role_t where ROLE_NM like '%AwardBudget%';
	delete from KRIM_ROLE_RSP_ACTN_T where ROLE_RSP_ID in (select role_rsp_id from KRIM_ROLE_RSP_T where rsp_id in (select rsp_id from krim_rsp_t where nm like '%Award Budget%'));
	delete from KRIM_ROLE_RSP_T where rsp_id in (select rsp_id from krim_rsp_t where nm like '%Award Budget%');
	delete from KRIM_RSP_ATTR_DATA_T where rsp_id in (select rsp_id from krim_rsp_t where nm like '%Award Budget%');
	delete from krim_rsp_t where nm like '%Award Budget%';
	
	
    INSERT INTO KRIM_RSP_T (RSP_ID, RSP_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID) VALUES 
    	(KRIM_RSP_ID_S.NEXTVAL, 1, 'KC-WKFLW', 'Award Budget InitialApproval', 'Award Budget Document - Initial Approval', 'Y', SYS_GUID());

    INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES 
    	(KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 7, 
    	    	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T T WHERE T.NM = 'documentTypeName' AND T.NMSPC_CD = 'KR-WKFLW'), 
    	    	'AwardBudgetDocument', SYS_GUID());

    INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
    		VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL,7, 
    		(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T T WHERE T.NM = 'routeNodeName' AND T.NMSPC_CD = 'KR-WKFLW'), 'AwardBudgetInitialApproval', SYS_GUID());

    INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
    		VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 7, 
    		(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T T WHERE T.NM = 'required' AND T.NMSPC_CD = 'KR-WKFLW'), 
    		'false', SYS_GUID());

    INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
    		VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL,7, 
    		(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T T WHERE T.NM = 'actionDetailsAtRoleMemberLevel' AND T.NMSPC_CD = 'KR-WKFLW'), 
    		'false', SYS_GUID());

    INSERT INTO KRIM_ROLE_RSP_T (ROLE_RSP_ID, ROLE_ID, RSP_ID, ACTV_IND, OBJ_ID) 
    		VALUES (KRIM_ROLE_RSP_ID_S.NEXTVAL, (select role_id from krim_role_t where role_nm = 'Award Budget Approver'), 
    		KRIM_RSP_ID_S.CURRVAL, 'Y', SYS_GUID());

    INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID, ROLE_MBR_ID, ACTN_TYP_CD, PRIORITY_NBR, ACTN_PLCY_CD, ROLE_RSP_ID, FRC_ACTN, OBJ_ID) 
    		VALUES (KRIM_ROLE_RSP_ACTN_ID_S.NEXTVAL, '*', 'A', 1, 'F', KRIM_ROLE_RSP_ID_S.CURRVAL, 'N', SYS_GUID());

    		
    INSERT INTO KRIM_RSP_T (RSP_ID, RSP_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID) VALUES 
    	(KRIM_RSP_ID_S.NEXTVAL, 1, 'KC-WKFLW', 'Award Budget OSPApproval', 'Award Budget Document - OSP Approval', 'Y', SYS_GUID());

    INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES 
    	(KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 7, 
    	    	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T T WHERE T.NM = 'documentTypeName' AND T.NMSPC_CD = 'KR-WKFLW'), 
    	    	'AwardBudgetDocument', SYS_GUID());

    INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
    		VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL,7, 
    		(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T T WHERE T.NM = 'routeNodeName' AND T.NMSPC_CD = 'KR-WKFLW'), 'AwardBudgetOSPApproval', SYS_GUID());

    INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
    		VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 7, 
    		(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T T WHERE T.NM = 'required' AND T.NMSPC_CD = 'KR-WKFLW'), 
    		'false', SYS_GUID());

    INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
    		VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL,7, 
    		(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T T WHERE T.NM = 'actionDetailsAtRoleMemberLevel' AND T.NMSPC_CD = 'KR-WKFLW'), 
    		'false', SYS_GUID());

    INSERT INTO KRIM_ROLE_RSP_T (ROLE_RSP_ID, ROLE_ID, RSP_ID, ACTV_IND, OBJ_ID) 
    		VALUES (KRIM_ROLE_RSP_ID_S.NEXTVAL, (select role_id from krim_role_t where role_nm = 'Award Budget Approver'), 
    		KRIM_RSP_ID_S.CURRVAL, 'Y', SYS_GUID());

    INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID, ROLE_MBR_ID, ACTN_TYP_CD, PRIORITY_NBR, ACTN_PLCY_CD, ROLE_RSP_ID, FRC_ACTN, OBJ_ID) 
    		VALUES (KRIM_ROLE_RSP_ACTN_ID_S.NEXTVAL, '*', 'A', 1, 'F', KRIM_ROLE_RSP_ID_S.CURRVAL, 'N', SYS_GUID());


	insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
		values ('KC-AB','D','awardBudgetStatusInProgress','CONFG','1','This system parameter maps the AwardBudget status In Progress','A');
    		
	insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
		values ('KC-AB','D','awardBudgetStatusSubmitted','CONFG','5','This system parameter maps the AwardBudget status Submitted','A');
    		
	insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
		values ('KC-AB','D','awardBudgetStatusRejected','CONFG','8','This system parameter maps the AwardBudget status Rejected','A');
    		
	insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
		values ('KC-AB','D','awardBudgetStatusToBePosted','CONFG','10','This system parameter maps the AwardBudget status To Be Posted','A');
    		
	insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
		values ('KC-AB','D','awardBudgetStatusPosted','CONFG','9','This system parameter maps the AwardBudget status Posted','A');
    		
	insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
		values ('KC-AB','D','awardBudgetStatusErrorInPosting','CONFG','11','This system parameter maps the AwardBudget status Error In Posting','A');

	insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
		values ('KC-AB','D','AWARD_BUDGET_POST_ENABLED','CONFG','1','This system parameter enables on demand Award Budget Posting','A');
commit;