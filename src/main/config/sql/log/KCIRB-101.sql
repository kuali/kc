drop view osp$comm_schedule;
drop table comm_schedule;
create table comm_schedule (
  id					 number(12)     not null PRIMARY KEY,
  schedule_id            varchar2 (10)  not null,
  committee_id           number(12)     not null references committee,
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
CREATE SEQUENCE SEQ_COMM_SCHEDULE_ID INCREMENT BY 1 START WITH 1;
commit;