insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR,OBJ_ID) values ('10000000000', 'University', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'),'KRADEV','1','d01f88a5-3d7d-4b2b-ad42-2aaa7ead5e49') ;
insert into KRIM_ENTITY_EMP_INFO_T (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd) values (1001, '755cb1c5-a001-4a12-98ae-7c27df78f7ea', 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000000'), 'Y', 'Y', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'), '000001');

insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR,OBJ_ID) values ('10000000001', 'CARDIOLOGY', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'),'KRADEV','1','bea939a9-b275-42ae-8b8f-b36e07fcb7cb') ;
insert into KRIM_ENTITY_EMP_INFO_T (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd) values (1002, '596c68f0-cfa4-4688-806b-b3a7cf982964', 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000001'), 'Y', 'Y', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'), 'IN-CARD');

insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR,OBJ_ID) values ('10000000002', 'University', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'),'KRADEV','1','70969a0f-2644-46e2-85fe-2376fbc581dd') ;
insert into KRIM_ENTITY_EMP_INFO_T (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd) values (1003, 'efddf0e7-b9cb-48fa-b802-71e6b99ea4c9', 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000002'), 'Y', 'Y', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'), '000001'); 

insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR,OBJ_ID) values ('10000000003', 'University', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'),'KRADEV','1','90ce2f54-663c-41a6-858c-d4e80a000542') ;
insert into KRIM_ENTITY_EMP_INFO_T (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd) values (1004, 'd574a467-521c-4dc9-8e44-c8931e6722f2', 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000003'), 'Y', 'Y', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'), '000001'); 


insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR,OBJ_ID) values ('10000000004', 'University', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'),'KRADEV','1','b138aef0-7383-46e2-9a48-139c28c3f95b') ;
insert into KRIM_ENTITY_EMP_INFO_T (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd) values (1005, 'e2fdb3c5-1720-4b5b-82a0-84b1a6c17a03', 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000004'), 'Y', 'Y', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'), '000001'); 


insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR,OBJ_ID) values ('10000000005', 'University', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'),'KRADEV','1','1a6a5a03-8540-4a3e-b4dd-d6c2cf96097f') ;
insert into KRIM_ENTITY_EMP_INFO_T (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd) values (1006, 'c11b0c05-5d46-49d8-b491-80ff43a8ba15', 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000005'), 'Y', 'Y', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'), '000001'); 


insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR,OBJ_ID) values ('10000000006', 'University', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'),'KRADEV','1','5b5bceff-31b6-4f60-85f8-7ad5c43ab9b0') ;
insert into KRIM_ENTITY_EMP_INFO_T (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd) values (1007, '34b29fad-e534-49c4-ab84-182fceb12262', 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000006'), 'Y', 'Y', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'), '000001'); 


insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR,OBJ_ID) values ('10000000007', 'CARDIOLOGY', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'),'KRADEV','1','2cfcea2c-9f02-4b7f-b432-51e9f259f343') ;
insert into KRIM_ENTITY_EMP_INFO_T (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd) values (1008, '2798d247-7e7f-4b24-a565-28f5ef2501c1', 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000007'), 'Y', 'Y', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'), 'IN-CARD'); 


insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR,OBJ_ID) values ('10000000008', 'University', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'),'KRADEV','1','1dc86e34-1625-4ff1-9603-a488beb24051') ;
insert into KRIM_ENTITY_EMP_INFO_T (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd) values (1009, 'b89cd0e2-e4db-41c5-8968-2a7da2cea62f', 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000008'), 'Y', 'Y', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'), '000001'); 


insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR,OBJ_ID) values ('10000000009', 'University', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'),'KRADEV','1','b9d25d72-034a-447a-bd36-1a867f514d29') ;
insert into KRIM_ENTITY_EMP_INFO_T (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd) values (1010, 'a8e398bd-0a7d-4bfa-b9d5-7e123d49f130', 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000009'), 'Y', 'Y', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'), '000001'); 


insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR,OBJ_ID) values ('10000000058', 'BL-RUGS', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'),'KRADEV','1','c91ff3a8-1677-4104-85ca-306e424e353a') ;
insert into KRIM_ENTITY_EMP_INFO_T (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd) values (1011, '1452e755-d2dd-4d2f-b143-c534003e02ec', 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000058'), 'Y', 'Y', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'), 'BL-RUGS'); 


insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR,OBJ_ID) values ('10000000060', null, to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'),'KRADEV','1','21cbe560-f9f8-494b-bd64-91ae29aabdf3') ;
insert into KRIM_ENTITY_EMP_INFO_T (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd) values (1012, 'b0913b33-0042-4554-8b1a-899b556a2d7b', 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000060'), 'Y', 'Y', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'), null); 


insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR,OBJ_ID) values ('10000000061', 'BL-BL', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'),'KRADEV','1','0c611b1c-af23-4fa5-b960-52e6434417d3') ;
insert into KRIM_ENTITY_EMP_INFO_T (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd) values (1013, 'd3cd67e5-1582-4061-8a41-4b4094fae23b', 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000061'), 'Y', 'Y', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'), 'BL-BL'); 


insert into PERSON_EXT_T (PERSON_ID, OFFICE_LOCATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR,OBJ_ID) values ('10000000062', 'University', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'),'KRADEV','1','aa8b4db7-fc15-42a2-acb1-bf845aec9b7e') ;
insert into KRIM_ENTITY_EMP_INFO_T (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd) values (1014, '80aadea4-adad-4d3d-b10b-a04f048cc3a4', 1, (SELECT p.entity_id FROM KRIM_PRNCPL_T p WHERE p.prncpl_id = '10000000062'), 'Y', 'Y', to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'), '000001'); 


