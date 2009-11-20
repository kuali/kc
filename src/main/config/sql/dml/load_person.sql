insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR)
       values ('000000000', 'Kuali Foundation', SYSDATE,'KRADEV','1'); 

insert into krim_entity_emp_info_t (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd)
       values (KRIM_ENTITY_EMP_ID_S.nextval, SYS_GUID(), 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '000000000'), 'Y', 'Y', SYSDATE, '000001');

