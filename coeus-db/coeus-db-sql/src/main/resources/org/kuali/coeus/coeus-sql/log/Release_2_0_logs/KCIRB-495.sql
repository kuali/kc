
alter table COMM_SCHEDULE_ATTENDANCE add PERSON_NAME VARCHAR2(90) NOT NULL;


alter table comm_schedule_minutes modify protocol_id_fk null;