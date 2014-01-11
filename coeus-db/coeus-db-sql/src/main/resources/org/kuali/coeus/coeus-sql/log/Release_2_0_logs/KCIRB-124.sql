drop view osp$comm_schedule;

drop table comm_schedule;

drop SEQUENCE SEQ_COMM_SCHEDULE_ID;

create table schedule_status (
  schedule_status_code  number (3)    not null,
  description           varchar2 (200)  not null,
  UPDATE_TIMESTAMP DATE NOT NULL, 
  UPDATE_USER VARCHAR2(60) NOT NULL,
  VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
  OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL);

alter table schedule_status
  add constraint pk_schedule_status_code
  primary key ( schedule_status_code );

CREATE OR REPLACE VIEW osp$schedule_status AS SELECT 
  schedule_status_code,
  description,
  update_timestamp,
  update_user
FROM schedule_status;

INSERT INTO SCHEDULE_STATUS (schedule_status_code,description,UPDATE_TIMESTAMP,UPDATE_USER) VALUES (1,'Scheduled',sysdate,user);
INSERT INTO SCHEDULE_STATUS (schedule_status_code,description,UPDATE_TIMESTAMP,UPDATE_USER) VALUES (2,'Cancelled',sysdate,user);
INSERT INTO SCHEDULE_STATUS (schedule_status_code,description,UPDATE_TIMESTAMP,UPDATE_USER) VALUES (3,'Agenda Closed',sysdate,user);
INSERT INTO SCHEDULE_STATUS (schedule_status_code,description,UPDATE_TIMESTAMP,UPDATE_USER) VALUES (4,'Committee has met',sysdate,user);


create table comm_schedule (
  id					 number(12)     not null,
  schedule_id            varchar2 (10)  not null,
  committee_id           number(12)     not null,
  scheduled_date         date           not null,
  place                  varchar2 (200),
  time                   date,
  protocol_sub_deadline  date           not null,
  schedule_status_code   number (3)     not null,
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

alter table comm_schedule
  add constraint pk_comm_schedule_id
  primary key ( id );  

alter table comm_schedule
  add constraint fk_committee_id
  foreign key (committee_id)
  references committee(id);
  
alter table comm_schedule
  add constraint fk_schedule_status_code
  foreign key (schedule_status_code)
  references schedule_status(schedule_status_code);  
  
  
CREATE OR REPLACE VIEW osp$comm_schedule AS SELECT 
  a.schedule_id schedule_id,
  b.committee_id committee_id,
  a.time scheduled_date,
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

CREATE SEQUENCE SEQ_COMM_SCHEDULE_ID INCREMENT BY 1 START WITH 1;

commit;