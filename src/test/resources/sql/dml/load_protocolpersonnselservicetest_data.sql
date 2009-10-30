insert into ORGANIZATION (ORGANIZATION_ID,ORGANIZATION_NAME,CONTACT_ADDRESS_ID,ADDRESS,CABLE_ADDRESS,TELEX_NUMBER,COUNTY,CONGRESSIONAL_DISTRICT,INCORPORATED_IN,INCORPORATED_DATE,NUMBER_OF_EMPLOYEES,IRS_TAX_EXCEMPTION,FEDRAL_EMPLOYER_ID,MASS_TAX_EXCEMPT_NUM,AGENCY_SYMBOL,VENDOR_CODE,COM_GOV_ENTITY_CODE,MASS_EMPLOYEE_CLAIM,DUNS_NUMBER,DUNS_PLUS_FOUR_NUMBER,DODAC_NUMBER,CAGE_NUMBER,HUMAN_SUB_ASSURANCE,ANIMAL_WELFARE_ASSURANCE,SCIENCE_MISCONDUCT_COMPL_DATE,PHS_ACOUNT,NSF_INSTITUTIONAL_CODE,INDIRECT_COST_RATE_AGREEMENT,COGNIZANT_AUDITOR,ONR_RESIDENT_REP,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values ('000001','University',1,'77 Massachusetts Ave, Cambridge, MA 02139','MITCAM','92-1473','Middlesex','Eighth','Massachusetts',to_date('10-APR-61','DD-MON-RR'),7500,'09-123-9876','042103594','E042-103-594','75-08-9701','042-103-594-000-1','80230','72-002250','000000000','4328',null,'80230','FWA00004881','A-3125-01',to_date('02-MAR-98','DD-MON-RR'),'1042103594A1','002 178 2000','03/08/2002',null,null,to_date('17-AUG-07','DD-MON-RR'),'KRADEV',1,'37EC277AF430004AE043814FD881004A');

insert into protocol_type (description, obj_id, protocol_type_code, update_timestamp, update_user, ver_nbr)
values ('test type', sys_guid(), 'tst', sysdate, 'jhulslander', 1);

insert into protocol_status(description, obj_id, protocol_status_code, update_timestamp, update_user, ver_nbr)
values('test status', sys_guid(), 'tst', sysdate, 'jhulslander', 1);

insert into protocol_document(document_number, obj_id, protocol_workflow_type, update_timestamp, update_user, ver_nbr)
values (101, sys_guid(), 'pwt', sysdate, 'jhulslder', 1);

insert into protocol(protocol_id, active, document_number, protocol_number, sequence_number, protocol_type_code, protocol_status_code, title, is_billable, special_review_indicator, 
  vulnerable_subject_indicator, key_study_person_indicator, funding_source_indicator, correspondent_indicator, reference_indicator, related_projects_indicator, update_timestamp, update_user, ver_nbr, obj_id)
values(1, 0, 101, 1, 1, 'tst', 'tst', 'test title', 0, 0,
  0, 0, 0 , 0 , 0, 0, sysdate, 'jhulslander', 1, sys_guid());
  
insert into protocol_person_roles ( protocol_person_role_id, description, update_timestamp, update_user, ver_nbr, obj_id)
values(666, 'COI', sysdate, 'jhulslander', 1, sys_guid());

insert into affiliation_type( affiliation_type_code, description, obj_id, update_timestamp, update_user, ver_nbr)
values(100, 'test aff', sys_guid(), sysdate, 'jhulslander', 1);

insert into PROTOCOL_PERSONS ( OBJ_ID, PROTOCOL_PERSON_ID, PROTOCOL_ID, PROTOCOL_NUMBER, SEQUENCE_NUMBER, PERSON_ID, PERSON_NAME, PROTOCOL_PERSON_ROLE_ID, ROLODEX_ID, 
  AFFILIATION_TYPE_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR)
values('dhafjkhadlkjfha', 2, 1, 1, 456, null, 'Philip Berg', 666, null,
  100, sysdate, 'jhulslander', 1);


commit;