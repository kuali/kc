--delete from KRIM_PRNCPL_T;
--delete from PROPOSAL_PERSONS;
--delete from krim_entity_t;
--delete from proposal;
--delete from sponsor;
--delete from ROLODEX;
--delete from country_code;
--delete from activity_type;
--delete from notice_of_opportunity;
--delete from sponsor_type;
--delete from proposal_type;

insert into activity_type (activity_type_code, description, obj_id, update_timestamp, update_user, ver_nbr)
values('tst', 'test activity type', sys_guid(), sysdate, 'jhulslander', 1);

insert into notice_of_opportunity (description, notice_of_opportunity_code, obj_id, update_timestamp, update_user, ver_nbr)
values('opt descr', 'tod', sys_guid(), sysdate, 'jhulslander', 1);

insert into country_code(country_code, country_name, obj_id, update_timestamp, update_user, ver_nbr)
values ('USA', 'United States', sys_guid(), sysdate, 'jhulslander', 1);

insert into ROLODEX (COMMENTS, PHONE_NUMBER, COUNTRY_CODE, SPONSOR_CODE, OWNED_BY_UNIT, SPONSOR_ADDRESS_FLAG, DELETE_FLAG, CREATE_USER, UPDATE_TIMESTAMP, UPDATE_USER,
  VER_NBR, OBJ_ID, ROLODEX_ID, LAST_NAME, FIRST_NAME, MIDDLE_NAME, SUFFIX, PREFIX, TITLE, ORGANIZATION, ADDRESS_LINE_1, ADDRESS_LINE_2, ADDRESS_LINE_3, FAX_NUMBER,
  EMAIL_ADDRESS, CITY, COUNTY, STATE, POSTAL_CODE)
values('comment', '6072552047', 'USA', null, '01', 'N', null, 'jhulslander', sysdate, 'jhulslander',
  1, sys_guid(), 100, 'Hulslander', 'Jay', 'D', 'Mr.', null, null, '01', '120 maple ave', 'Ithaca NY 14850', null, null,
  'jdh34@cornell.edu', 'Ithaca', 'USA', 'NY', '14850');
  
insert into ROLODEX (COMMENTS, PHONE_NUMBER, COUNTRY_CODE, SPONSOR_CODE, OWNED_BY_UNIT, SPONSOR_ADDRESS_FLAG, DELETE_FLAG, CREATE_USER, UPDATE_TIMESTAMP, UPDATE_USER,
  VER_NBR, OBJ_ID, ROLODEX_ID, LAST_NAME, FIRST_NAME, MIDDLE_NAME, SUFFIX, PREFIX, TITLE, ORGANIZATION, ADDRESS_LINE_1, ADDRESS_LINE_2, ADDRESS_LINE_3, FAX_NUMBER,
  EMAIL_ADDRESS, CITY, COUNTY, STATE, POSTAL_CODE)
values('comment', '6072552047', 'USA', null, '01', 'N', null, 'jhulslander', sysdate, 'jhulslander',
  1, sys_guid(), 101, 'Hulslander2', 'Jay2', 'D', 'Mr.', null, null, '01', '120 maple ave', 'Ithaca NY 14850', null, null,
  'jdh34@cornell.edu', 'Ithaca', 'USA', 'NY', '14850');

insert into sponsor_type(description, obj_id, sponsor_type_code, update_timestamp, update_user, ver_nbr)
values('test code', sys_guid(), 'tst', sysdate, 'jhulslander', 1);
  
insert into sponsor (SPONSOR_CODE, SPONSOR_NAME, ACRONYM, SPONSOR_TYPE_CODE, DUN_AND_BRADSTREET_NUMBER, DUNS_PLUS_FOUR_NUMBER, DODAC_NUMBER, CAGE_NUMBER, POSTAL_CODE,
  STATE, COUNTRY_CODE, ROLODEX_ID, AUDIT_REPORT_SENT_FOR_FY, OWNED_BY_UNIT, CREATE_USER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
values('tst', 'tester', null, 'tst', null, null, null, null, '13068',
  'NY', 'USA', 100, null, '01', 'jhulslander', sysdate, 'jhulslander', 1, sys_guid());
  
insert into proposal_type (description, obj_id, proposal_type_code, update_timestamp, update_user, ver_nbr)
values('test type', sys_guid(), 'tst', sysdate, 'jhulslander', 1);

insert into proposal (MAIL_DESCRIPTION, PROPOSAL_SEQUENCE_STATUS, FISCAL_MONTH, FISCAL_YEAR, ACTIVITY_TYPE_CODE, REQUESTED_START_DATE_INITIAL, REQUESTED_START_DATE_TOTAL,
  REQUESTED_END_DATE_INITIAL, REQUESTED_END_DATE_TOTAL, TOTAL_DIRECT_COST_INITIAL, TOTAL_DIRECT_COST_TOTAL, TOTAL_INDIRECT_COST_INITIAL, TOTAL_INDIRECT_COST_TOTAL,
  NUMBER_OF_COPIES, DEADLINE_DATE, DEADLINE_TYPE, MAIL_BY, MAIL_TYPE, MAIL_ACCOUNT_NUMBER, SUBCONTRACT_FLAG, COST_SHARING_INDICATOR, IDC_RATE_INDICATOR, SPECIAL_REVIEW_INDICATOR,
  STATUS_CODE, SCIENCE_CODE_INDICATOR, NSF_CODE, PRIME_SPONSOR_CODE, INITIAL_CONTRACT_ADMIN, IP_REVIEW_ACTIVITY_INDICATOR, CURRENT_AWARD_NUMBER, CFDA_NUMBER, OPPORTUNITY,
  UPDATE_TIMESTAMP, UPDATE_USER, AWARD_TYPE_CODE, VER_NBR, OBJ_ID, DOCUMENT_NUMBER, CREATE_TIMESTAMP, PROPOSAL_ID, PROPOSAL_NUMBER, SPONSOR_PROPOSAL_NUMBER, SEQUENCE_NUMBER,
  PROPOSAL_TYPE_CODE, CURRENT_ACCOUNT_NUMBER, TITLE, SPONSOR_CODE, ROLODEX_ID, NOTICE_OF_OPPORTUNITY_CODE, GRAD_STUD_HEADCOUNT, GRAD_STUD_PERSON_MONTHS, TYPE_OF_ACCOUNT)
values(null, 'sstatus', 10, 2009, 'tst', sysdate, null,
  null, null, null, null, null, null,
  5, sysdate, null, null, null, null, 'N', 'N', 'N', 'N',
  6, 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N',
  sysdate, 'jhulslander', null, 1, sys_guid(), 1, sysdate, '12345', '12345', null, 1,
  'tst', null, 'testing title', 'tst', 100, null, null, null, null);

INSERT INTO KRIM_ENT_NM_TYP_T (ACTV_IND,DISPLAY_SORT_CD,ENT_NM_TYP_CD,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','b','PRFR',TO_DATE( '20081113140633', 'YYYYMMDDHH24MISS' ),'Preferred','5B97C50B03866110E0404F8189D85213',1);  
  
--tester a records
insert into krim_entity_t(actv_ind, entity_id, last_updt_dt, obj_id, ver_nbr)
values('Y', '1', sysdate, sys_guid(), 1);

INSERT INTO KRIM_ENTITY_ENT_TYP_T (ACTV_IND,ENTITY_ID,ENT_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR)
  VALUES ('Y', '1', 'PERSON', sysdate, sys_guid(), 1);

insert into KRIM_PRNCPL_T (VER_NBR, PRNCPL_NM,  ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID, OBJ_ID)
values(1, 'testerA', 1, '', 'Y', sysdate, '1', sys_guid());

insert into krim_entity_nm_t
  (entity_nm_id, obj_id, ver_nbr, entity_id, nm_typ_cd, first_nm, middle_nm, last_nm, suffix_nm, title_nm, dflt_ind, actv_ind, last_updt_dt)
values ('1', sys_guid(), 1, '1', 'PRFR', 'testerA', '', '', '', '', 'Y', 'Y', sysdate);

insert into PROPOSAL_PERSONS ( PROPOSAL_PERSON_ID, PROPOSAL_NUMBER, SEQUENCE_NUMBER, PERSON_ID, ROLODEX_ID, PROP_PERSON_ROLE_ID, PERSON_NAME, FACULTY_FLAG, 
  CONFLICT_OF_INTEREST_FLAG, PERCENTAGE_EFFORT, FEDR_DEBR_FLAG, FEDR_DELQ_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, MULTI_PI_FLAG, ACADEMIC_YEAR_EFFORT,
  SUMMER_YEAR_EFFORT, CALENDAR_YEAR_EFFORT, VER_NBR, OBJ_ID )
values (1, 12345, 1, '1', 100, null, 'testerA', null,
  null, null, null, null, sysdate, 'jhulslander', 'N', null,
  null, null, 1, sys_guid());

--testerb records
insert into krim_entity_t(actv_ind, entity_id, last_updt_dt, obj_id, ver_nbr)
values('Y', '2', sysdate, sys_guid(), 1);

INSERT INTO KRIM_ENTITY_ENT_TYP_T (ACTV_IND,ENTITY_ID,ENT_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR)
  VALUES ('Y', '2', 'PERSON', sysdate, sys_guid(), 1);

insert into KRIM_PRNCPL_T (VER_NBR, PRNCPL_NM,  ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID, OBJ_ID)
values(1, 'testerB', '2', '', 'Y', sysdate, '2', sys_guid());

insert into krim_entity_nm_t
  (entity_nm_id, obj_id, ver_nbr, entity_id, nm_typ_cd, first_nm, middle_nm, last_nm, suffix_nm, title_nm, dflt_ind, actv_ind, last_updt_dt)
values ('2', sys_guid(), 1, '2', 'PRFR', 'testerB', '', '', '', '', 'Y', 'Y', sysdate);

insert into PROPOSAL_PERSONS ( PROPOSAL_PERSON_ID, PROPOSAL_NUMBER, SEQUENCE_NUMBER, PERSON_ID, ROLODEX_ID, PROP_PERSON_ROLE_ID, PERSON_NAME, FACULTY_FLAG, 
  CONFLICT_OF_INTEREST_FLAG, PERCENTAGE_EFFORT, FEDR_DEBR_FLAG, FEDR_DELQ_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, MULTI_PI_FLAG, ACADEMIC_YEAR_EFFORT,
  SUMMER_YEAR_EFFORT, CALENDAR_YEAR_EFFORT, VER_NBR, OBJ_ID )
values (2, 12345, 1, '2', 100, null, 'testerB', null,
  null, null, null, null, sysdate, 'jhulslander', 'N', null,
  null, null, 1, sys_guid());
commit;