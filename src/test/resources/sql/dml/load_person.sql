insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR)
 values ('10000000000', 'University', SYSDATE,'KRADEV','1')
 ;
insert into krim_entity_emp_info_t (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd)
       values (KRIM_ENTITY_EMP_ID_S.nextval, SYS_GUID(), 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000000'), 'Y', 'Y', SYSDATE, '000001');
 
insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR)
 values ('10000000001', 'CARDIOLOGY', SYSDATE,'KRADEV','1')
 ;
insert into krim_entity_emp_info_t (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd)
       values (KRIM_ENTITY_EMP_ID_S.nextval, SYS_GUID(), 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000001'), 'Y', 'Y', SYSDATE, 'IN-CARD');

insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR)
 values ('10000000002', 'University', SYSDATE,'KRADEV','1')
 ;
insert into krim_entity_emp_info_t (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd)
       values (KRIM_ENTITY_EMP_ID_S.nextval, SYS_GUID(), 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000002'), 'Y', 'Y', SYSDATE, '000001'); 
 
insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR)
 values ('10000000003', 'University', SYSDATE,'KRADEV','1')
 ;
 insert into krim_entity_emp_info_t (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd)
       values (KRIM_ENTITY_EMP_ID_S.nextval, SYS_GUID(), 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000003'), 'Y', 'Y', SYSDATE, '000001'); 
 
 
insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR)
 values ('10000000004', 'University', SYSDATE,'KRADEV','1')
 ;
  insert into krim_entity_emp_info_t (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd)
       values (KRIM_ENTITY_EMP_ID_S.nextval, SYS_GUID(), 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000004'), 'Y', 'Y', SYSDATE, '000001'); 
 
 
insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR)
 values ('10000000005', 'University', SYSDATE,'KRADEV','1')
 ;
   insert into krim_entity_emp_info_t (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd)
       values (KRIM_ENTITY_EMP_ID_S.nextval, SYS_GUID(), 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000005'), 'Y', 'Y', SYSDATE, '000001'); 
 
 
insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR)
 values ('10000000006', 'University', SYSDATE,'KRADEV','1')
 ;
    insert into krim_entity_emp_info_t (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd)
       values (KRIM_ENTITY_EMP_ID_S.nextval, SYS_GUID(), 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000006'), 'Y', 'Y', SYSDATE, '000001'); 
 
 
insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR)
 values ('10000000007', 'CARDIOLOGY', SYSDATE,'KRADEV','1')
 ;
     insert into krim_entity_emp_info_t (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd)
       values (KRIM_ENTITY_EMP_ID_S.nextval, SYS_GUID(), 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000007'), 'Y', 'Y', SYSDATE, 'IN-CARD'); 
 
 
insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR)
 values ('10000000008', 'University', SYSDATE,'KRADEV','1')
 ;
     insert into krim_entity_emp_info_t (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd)
       values (KRIM_ENTITY_EMP_ID_S.nextval, SYS_GUID(), 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000008'), 'Y', 'Y', SYSDATE, '000001'); 
 
 
 insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR)
 values ('10000000009', 'University', SYSDATE,'KRADEV','1')
 ;
      insert into krim_entity_emp_info_t (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd)
       values (KRIM_ENTITY_EMP_ID_S.nextval, SYS_GUID(), 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000009'), 'Y', 'Y', SYSDATE, '000001'); 
 
 
  insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR)
 values ('10000000058', 'BL-RUGS', SYSDATE,'KRADEV','1')
 ;
       insert into krim_entity_emp_info_t (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd)
       values (KRIM_ENTITY_EMP_ID_S.nextval, SYS_GUID(), 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000058'), 'Y', 'Y', SYSDATE, 'BL-RUGS'); 
 
 
 insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR)
 values ('10000000060', null, SYSDATE,'KRADEV','1')
 ;
       insert into krim_entity_emp_info_t (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd)
       values (KRIM_ENTITY_EMP_ID_S.nextval, SYS_GUID(), 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000060'), 'Y', 'Y', SYSDATE, null); 
 
 
 insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR)
 values ('10000000061', 'BL-BL', SYSDATE,'KRADEV','1')
 ;
       insert into krim_entity_emp_info_t (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd)
       values (KRIM_ENTITY_EMP_ID_S.nextval, SYS_GUID(), 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000061'), 'Y', 'Y', SYSDATE, 'BL-BL'); 
 
 
  insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR)
 values ('10000000062', 'University', SYSDATE,'KRADEV','1')
 ;
        insert into krim_entity_emp_info_t (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd)
       values (KRIM_ENTITY_EMP_ID_S.nextval, SYS_GUID(), 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000062'), 'Y', 'Y', SYSDATE, '000001'); 
 
