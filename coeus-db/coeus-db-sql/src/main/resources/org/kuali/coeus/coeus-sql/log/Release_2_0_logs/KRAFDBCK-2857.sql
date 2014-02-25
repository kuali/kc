insert into krim_entity_t (ENTITY_ID, ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR) 
select krim_entity_id_s.nextval, 'Y', sysdate, sys_guid(), 1 from dual;

insert into krim_entity_ent_typ_t (ENT_TYP_CD, ENTITY_ID, ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR)
select 'PERSON', krim_entity_id_s.currval, 'Y', sysdate, sys_guid(), 1 from dual;

insert into krim_entity_nm_t (ENTITY_NM_ID, entity_id, FIRST_NM, last_nm, NM_TYP_CD, DFLT_IND, ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR)
select krim_entity_nm_id_s.nextval, krim_entity_id_s.currval, 'Institutional Proposal', 'Viewer', 'PRFR', 'Y', 'Y', sysdate, sys_guid(), 1 from dual;

insert into krim_entity_emp_info_t (ENTITY_EMP_ID, ENTITY_ID, PRMRY_DEPT_CD, PRMRY_IND, ACTV_IND, LAST_UPDT_DT , OBJ_ID , VER_NBR)
select krim_entity_emp_id_s.nextval, krim_entity_id_s.currval, '000001','Y','Y',sysdate, sys_guid(), 1 from dual;

insert into krim_entity_addr_t (ENTITY_ADDR_ID, entity_id, ENT_TYP_CD, ADDR_LINE_1 , CITY_NM , POSTAL_STATE_CD , POSTAL_CD , POSTAL_CNTRY_CD ,  ADDR_TYP_CD , DFLT_IND ,ACTV_IND, LAST_UPDT_DT , OBJ_ID , VER_NBR)
select krim_entity_addr_id_s.nextval, krim_entity_id_s.currval, 'PERSON', '62 Kuali Drive', 'Coeus', 'MA', '53421', 'US', 'WRK', 'Y', 'Y', sysdate, sys_guid(), 1 from dual;

insert into krim_entity_email_t (entity_email_id, entity_id, ENT_TYP_CD, EMAIL_ADDR, EMAIL_TYP_CD, DFLT_IND, ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR)
select krim_entity_email_id_s.nextval, krim_entity_id_s.currval, 'PERSON', 'ipviewer@kuali.org', 'WRK', 'Y', 'Y', sysdate, sys_guid(), 1 from dual;

insert into krim_entity_phone_t (entity_phone_id, ENTITY_ID, ENT_TYP_CD, PHONE_NBR, PHONE_TYP_CD, DFLT_IND, ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR)
select krim_entity_phone_id_s.nextval, krim_entity_id_s.currval, 'PERSON', '321-321-1062', 'WRK', 'Y', 'Y', sysdate, sys_guid(), 1 from dual;

insert into krim_entity_phone_t (entity_phone_id, ENTITY_ID, ENT_TYP_CD, PHONE_NBR, PHONE_TYP_CD, DFLT_IND, ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR)
select krim_entity_phone_id_s.nextval, krim_entity_id_s.currval, 'PERSON', '321-321-2062', 'FAX', 'N', 'Y', sysdate, sys_guid(), 1 from dual;

insert into krim_prncpl_t (PRNCPL_ID, entity_id, PRNCPL_NM, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR)
select krim_prncpl_id_s.nextval, krim_entity_id_s.currval, 'ipviewer', 'fK69ATFsAydwQuteang+xMva+Tc=','Y',sysdate,sys_guid(),1 from dual;

insert into krim_role_mbr_t (ROLE_MBR_ID, role_id, mbr_id, mbr_typ_cd, last_updt_dt, obj_id, VER_NBR)
select krim_role_mbr_id_s.nextval, role_id, krim_prncpl_id_s.currval, 'P', sysdate, sys_guid(), 1 from krim_role_t
where ROLE_NM = 'Institutional Proposal Viewer';

insert into krim_role_mbr_attr_data_t (attr_data_id, role_mbr_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id, ver_nbr)
select krim_attr_data_id_s.nextval, krim_role_mbr_id_s.currval, t.kim_typ_id, d.kim_attr_defn_id, '000001', sys_guid(), 1 from krim_typ_t t, KRIM_ATTR_DEFN_T d
where t.nm = 'UnitHierarchy' and d.nm = 'unitNumber' and T.NMSPC_CD = 'KC-SYS' and D.NMSPC_CD = 'KC-SYS';

insert into krim_role_mbr_attr_data_t (attr_data_id, role_mbr_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id, ver_nbr)
select krim_attr_data_id_s.nextval, krim_role_mbr_id_s.currval, t.kim_typ_id, d.kim_attr_defn_id, 'Y', sys_guid(), 1 from krim_typ_t t, KRIM_ATTR_DEFN_T d
where t.nm = 'UnitHierarchy' and d.nm = 'subunits' and T.NMSPC_CD = 'KC-SYS' and D.NMSPC_CD = 'KC-SYS';

commit;