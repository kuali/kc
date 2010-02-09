delete from krim_role_perm_t t where t.perm_id = (select perm_id from krim_perm_t where nmspc_cd = 'KC-AWARD' and nm = 'Approve AwardBudget');
delete from krim_role_perm_t t where t.perm_id = (select perm_id from krim_perm_t where nmspc_cd = 'KC-AWARD' and nm = 'Maintain AwardBudget Routing');
delete from krim_role_perm_t t where t.role_id = (select role_id from krim_role_t where role_nm = 'Award Budget Admnistrator') and t.perm_id = (select perm_id from krim_perm_t where nm = 'Modify AwardBudget' and nmspc_cd = 'KC-AWARD');
delete from krim_role_perm_t t where t.role_id = (select role_id from krim_role_t where role_nm = 'Award Budget Admnistrator') and t.perm_id = (select perm_id from krim_perm_t where nm = 'View AwardBudget' and nmspc_cd = 'KC-AWARD');
delete from krim_role_perm_t t where t.role_id = (select role_id from krim_role_t where role_nm = 'Award Budget Aggregator') and t.perm_id = (select perm_id from krim_perm_t where nm = 'View AwardBudget' and nmspc_cd = 'KC-AWARD');
delete from krim_role_perm_t t where t.role_id = (select role_id from krim_role_t where role_nm = 'Award Budget Modifier') and t.perm_id = (select perm_id from krim_perm_t where nm = 'View AwardBudget' and nmspc_cd = 'KC-AWARD');
delete from krim_perm_t where nmspc_cd = 'KC-AWARD' and nm = 'Maintain AwardBudget Routing';
delete from krim_perm_t where nmspc_cd = 'KC-AWARD' and nm = 'Approve AwardBudget';
delete from krim_role_t where nmspc_cd = 'KC-AWARD' and role_nm = 'Award Budget Approver';
delete from krim_role_mbr_attr_data_t where role_mbr_id in (select role_mbr_id from krim_role_mbr_t t where t.role_id = (select role_id from krim_role_t where role_nm = 'Award Budget Admnistrator' and nmspc_cd = 'KC-AWARD'));
delete from krim_role_mbr_t t where t.role_id = (select role_id from krim_role_t where role_nm = 'Award Budget Admnistrator' and nmspc_cd = 'KC-AWARD');

UPDATE KRIM_PERM_ATTR_DATA_T T SET T.ATTR_VAL = 'BudgetDocument' where t.perm_id = 
(select perm_id from krim_perm_t where nm = 'Blanket Approve AwardBudgetDocument' and nmspc_cd = 'KC-AWARD');

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values (krim_perm_id_s.nextval, (select perm_tmpl_id from krim_perm_tmpl_t where nmspc_cd = 'KC-IDM' and nm = 'Perform Document Action'), 
'KC-AWARD', 'Approve AwardBudget', 'Approve the final node in AwardBudget document approval path', 'Y', sys_guid()); 

insert into krim_role_t (role_id, role_nm, nmspc_cd, desc_txt, kim_typ_id, actv_ind, last_updt_dt, obj_id) 
values (krim_role_id_s.nextval, 'Award Budget Approver', 'KC-AWARD', 'Award Budget Approver - the role that grants permission to do the final approval',  
(select kim_typ_id from krim_typ_t t1 where t1.nm = 'UnitHierarchy' and nmspc_cd = 'KC-SYS'), 'Y', sysdate, sys_guid());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
(krim_role_perm_id_s.nextval, krim_role_id_s.currval, krim_perm_id_s.currval, 'Y', sys_guid());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
(krim_role_perm_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Award Budget Admnistrator'), krim_perm_id_s.currval, 'Y', sys_guid());

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values (krim_perm_id_s.nextval, (select perm_tmpl_id from krim_perm_tmpl_t where nmspc_cd = 'KC-IDM' and nm = 'Perform Document Action'), 
'KC-AWARD', 'Maintain AwardBudget Routing', 'Maintain AwardBudget Routing', 'Y', sys_guid());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
(krim_role_perm_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Award Budget Admnistrator'), krim_perm_id_s.currval, 'Y', sys_guid());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
(krim_role_perm_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Award Budget Admnistrator'), (select perm_id from krim_perm_t where nm = 'Modify AwardBudget' and nmspc_cd = 'KC-AWARD'), 'Y', sys_guid());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
(krim_role_perm_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Award Budget Admnistrator'), (select perm_id from krim_perm_t where nm = 'View AwardBudget' and nmspc_cd = 'KC-AWARD'), 'Y', sys_guid());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
(krim_role_perm_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Award Budget Aggregator'), (select perm_id from krim_perm_t where nm = 'View AwardBudget' and nmspc_cd = 'KC-AWARD'), 'Y', sys_guid());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values
(krim_role_perm_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Award Budget Modifier'), (select perm_id from krim_perm_t where nm = 'View AwardBudget' and nmspc_cd = 'KC-AWARD'), 'Y', sys_guid());


INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT, VER_NBR, OBJ_ID) 
VALUES (KRIM_ROLE_MBR_ID_S.NEXTVAL, (SELECT ROLE_ID FROM KRIM_ROLE_T T1 WHERE T1.ROLE_NM = 'Award Budget Admnistrator'), 
(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T T2 WHERE T2.PRNCPL_NM = 'quickstart'), 'P', NULL, NULL, SYSDATE, 1, SYS_GUID());

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, VER_NBR, OBJ_ID) 
VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, krim_role_mbr_id_s.currval, (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'KIM_TYP_ID' AND NMSPC_CD = 'KC-SYS'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'unitNumber' AND NMSPC_CD = 'KC-SYS'), '000001', 1, sys_guid());

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, VER_NBR, OBJ_ID) 
VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, krim_role_mbr_id_s.currval, (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'KIM_TYP_ID' AND NMSPC_CD = 'KC-SYS'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'subunits' AND NMSPC_CD = 'KC-SYS'), 'Y', 1, sys_guid());

commit;