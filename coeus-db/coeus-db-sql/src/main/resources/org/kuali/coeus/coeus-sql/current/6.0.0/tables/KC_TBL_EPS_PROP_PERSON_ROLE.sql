alter table AWARD_PERSONS drop foreign key FK_PROP_PERS_ROLE_AWD_PERSONS
/

alter table EPS_PROP_PERSON_ROLE drop primary key
/

alter table EPS_PROP_PERSON_ROLE add PROP_PERSON_ROLE_CODE varchar2(12)
/

update EPS_PROP_PERSON_ROLE set PROP_PERSON_ROLE_CODE = PROP_PERSON_ROLE_ID
/

alter table EPS_PROP_PERSON_ROLE modify column PROP_PERSON_ROLE_CODE varchar2(12) not null
/

alter table EPS_PROP_PERSON_ROLE add SPONSOR_HIERARCHY_NAME varchar2(50) not null default 'DEFAULT'
/

update EPS_PROP_PERSON_ROLE set PROP_PERSON_ROLE_ID = (select SEQ_EPS_PROP_PERSON_ROLE.nextval from dual)
/

alter table EPS_PROP_PERSON_ROLE modify column PROP_PERSON_ROLE_ID number(12,0) not null
/

alter table EPS_PROP_PERSON_ROLE add primary key (PROP_PERSON_ROLE_ID)
/

alter table EPS_PROP_PERSON_ROLE add constraint unique EPS_PROP_PERSON_ROLE_U1 (PROP_PERSON_ROLE_CODE, SPONSOR_HIERARCHY_NAME)
/

alter table EPS_PROP_PERSON_ROLE add READ_ONLY_ROLE char(1) not null default 'N'
/