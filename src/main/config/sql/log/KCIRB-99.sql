
create table comm_schedule (
  schedule_id            varchar2 (10)  not null PRIMARY KEY ,
  committee_id           number(12)     not null references committee,
  scheduled_date         date          not null,
  place                  varchar2 (200),
  time                   date,
  protocol_sub_deadline  date          not null,
  schedule_status_code   number (3)    not null,
  meeting_date           date,
  start_time             date,
  end_time               date,
  agenda_prod_rev_date   date,
  max_protocols          number (4),
  comments               varchar2 (2000),
  UPDATE_TIMESTAMP DATE NOT NULL, 
  UPDATE_USER VARCHAR2(60) NOT NULL,
  VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
  OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL);
CREATE OR REPLACE VIEW osp$comm_schedule AS SELECT 
  a.schedule_id schedule_id,
  b.committee_id committee_id,
  a.scheduled_date scheduled_date,
  a.place place,
  a.time time,
  a.protocol_sub_deadline protocol_sub_deadline,
  a.schedule_status_code schedule_status_code,
  a.meeting_date meeting_date,
  a.start_time start_time,
  a.end_time end_time,
  a.agenda_prod_rev_date agenda_prod_rev_date,
  a.max_protocols max_protocols,
  a.comments comments,
  a.update_timestamp update_timestamp,
  a.update_user update_user
FROM comm_schedule a
INNER JOIN committee b
ON a.committee_id = b.id;
create table comm_schedule_frequency (
  frequency_code    number (3)    not null PRIMARY KEY,
  description       varchar2 (200)  not null,
  no_of_days        number (3),
  UPDATE_TIMESTAMP DATE NOT NULL, 
  UPDATE_USER VARCHAR2(60) NOT NULL,
  VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
  OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL);
CREATE OR REPLACE VIEW osp$comm_schedule_frequency AS SELECT 
  frequency_code,
  description,
  no_of_days,
  update_timestamp,
  update_user
FROM comm_schedule_frequency;
commit;
Insert into COMM_SCHEDULE_FREQUENCY (FREQUENCY_CODE,DESCRIPTION,NO_OF_DAYS,UPDATE_TIMESTAMP,UPDATE_USER) values (2,'Weekly',7,to_date('16-JAN-04','DD-MON-RR'),'COEUS');
Insert into COMM_SCHEDULE_FREQUENCY (FREQUENCY_CODE,DESCRIPTION,NO_OF_DAYS,UPDATE_TIMESTAMP,UPDATE_USER) values (4,'Monthly',null,to_date('16-JAN-04','DD-MON-RR'),'COEUS');
Insert into COMM_SCHEDULE_FREQUENCY (FREQUENCY_CODE,DESCRIPTION,NO_OF_DAYS,UPDATE_TIMESTAMP,UPDATE_USER) values (5,'BiWeekly',14,to_date('16-JAN-04','DD-MON-RR'),'COEUS');
Insert into COMM_SCHEDULE_FREQUENCY (FREQUENCY_CODE,DESCRIPTION,NO_OF_DAYS,UPDATE_TIMESTAMP,UPDATE_USER) values (6,'BiWeekly- 1st 3rd',null,to_date('16-JAN-04','DD-MON-RR'),'COEUS');
Insert into COMM_SCHEDULE_FREQUENCY (FREQUENCY_CODE,DESCRIPTION,NO_OF_DAYS,UPDATE_TIMESTAMP,UPDATE_USER) values (7,'BiWeekly- 2d  4th',null,to_date('16-JAN-04','DD-MON-RR'),'COEUS');
Insert into COMM_SCHEDULE_FREQUENCY (FREQUENCY_CODE,DESCRIPTION,NO_OF_DAYS,UPDATE_TIMESTAMP,UPDATE_USER) values (8,'Monthly- 1st',null,to_date('16-JAN-04','DD-MON-RR'),'COEUS');
Insert into COMM_SCHEDULE_FREQUENCY (FREQUENCY_CODE,DESCRIPTION,NO_OF_DAYS,UPDATE_TIMESTAMP,UPDATE_USER) values (9,'Monthly- 2d',null,to_date('16-JAN-04','DD-MON-RR'),'COEUS');
Insert into COMM_SCHEDULE_FREQUENCY (FREQUENCY_CODE,DESCRIPTION,NO_OF_DAYS,UPDATE_TIMESTAMP,UPDATE_USER) values (10,'Monthly- 3d',null,to_date('16-JAN-04','DD-MON-RR'),'COEUS');
Insert into COMM_SCHEDULE_FREQUENCY (FREQUENCY_CODE,DESCRIPTION,NO_OF_DAYS,UPDATE_TIMESTAMP,UPDATE_USER) values (11,'Monthly- 4th',null,to_date('16-JAN-04','DD-MON-RR'),'COEUS');
Insert into COMM_SCHEDULE_FREQUENCY (FREQUENCY_CODE,DESCRIPTION,NO_OF_DAYS,UPDATE_TIMESTAMP,UPDATE_USER) values (3,'Yearly',null,to_date('16-JAN-04','DD-MON-RR'),'COEUS');
Insert into COMM_SCHEDULE_FREQUENCY (FREQUENCY_CODE,DESCRIPTION,NO_OF_DAYS,UPDATE_TIMESTAMP,UPDATE_USER) values (1,'Daily',1,to_date('16-JAN-04','DD-MON-RR'),'COEUS');


commit;