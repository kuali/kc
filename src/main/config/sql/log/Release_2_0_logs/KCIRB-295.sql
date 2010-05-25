 
create table proto_corresp_type (
  proto_corresp_type_code  varchar2 (3)    not null,
  description              varchar2 (200)  not null,
  module_id 			   varchar2(1) default 'Y' not null,
  update_timestamp         date          not null,
  update_user              varchar2 (8)  not null,
  ver_nbr 				  	 number(8,0) DEFAULT 1 NOT NULL, 
  obj_id				  	 varchar2(36) DEFAULT SYS_GUID() NOT NULL);
  
ALTER TABLE proto_corresp_type 
ADD CONSTRAINT pk_pct_proto_corresp_type
PRIMARY KEY (proto_corresp_type_code);   

CREATE OR REPLACE VIEW osp$proto_corresp_type AS SELECT
  proto_corresp_type_code,
  description,
  module_id,
  update_timestamp,
  update_user
FROM proto_corresp_type;

INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('1','Approval Letter','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('2','Rejection Letter','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('3','Notice Of Deferral','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('4','Substantive Revisions Required Letter','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('5','Expedited Approval Letter','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('6','Specific Minor Revisions Letter','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('7','Suspension notice','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('8','Termination Notice','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('9','Agenda Report','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('10','Schedule Minutes','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('110','Schedule Optional Report #1','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('12','Schedule Optional Report #2','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('13','Protocol Optional Report #1','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('20','Renewal Reminder Letter #1','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('21','Renewal Reminder Letter #2','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('22','Renewal Reminder Letter #3','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('23','Reminder to IRB Notification #1','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('24','Reminder to IRB Notification #2','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('25','Reminder to IRB Notification #3','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('26','Closure Notice','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('17','Grant Exemption Notice','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('14','Protocol Optional Report #2','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('15','Committee Roster Report','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('16','Withdrawal Notice','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('18','Protocol Submission Report #1','Y',sysdate,'KRADEV');
INSERT INTO PROTO_CORRESP_TYPE (proto_corresp_type_code, description, module_id, update_timestamp, update_user) VALUES ('19','Protocol Submission Report #2','Y',sysdate,'KRADEV');

commit;



create table submission_status (
  submission_status_code  varchar2 (3)    not null,
  description             varchar2 (200)  not null,
  update_timestamp        date          not null,
  update_user             varchar2 (8)  not null,
  ver_nbr 				  number(8,0) DEFAULT 1 NOT NULL, 
  obj_id				  varchar2(36) DEFAULT SYS_GUID() NOT NULL);

ALTER TABLE submission_status 
ADD CONSTRAINT pk_ss_submission_status_code
PRIMARY KEY (submission_status_code);

CREATE OR REPLACE VIEW osp$submission_status AS SELECT
  submission_status_code,
  description,
  update_timestamp,
  update_user
FROM submission_status;

INSERT INTO SUBMISSION_STATUS (submission_status_code, description, update_timestamp, update_user) VALUES ('100','Submitted to Committee',sysdate,'KRADEV');
INSERT INTO SUBMISSION_STATUS (submission_status_code, description, update_timestamp, update_user) VALUES ('101','In Agenda',sysdate,'KRADEV');
INSERT INTO SUBMISSION_STATUS (submission_status_code, description, update_timestamp, update_user) VALUES ('102','Pending',sysdate,'KRADEV');
INSERT INTO SUBMISSION_STATUS (submission_status_code, description, update_timestamp, update_user) VALUES ('200','Complete',sysdate,'KRADEV');
INSERT INTO SUBMISSION_STATUS (submission_status_code, description, update_timestamp, update_user) VALUES ('201','Substantive Revisions Required',sysdate,'KRADEV');
INSERT INTO SUBMISSION_STATUS (submission_status_code, description, update_timestamp, update_user) VALUES ('202','Specific Minor Revisions Requested',sysdate,'KRADEV');
INSERT INTO SUBMISSION_STATUS (submission_status_code, description, update_timestamp, update_user) VALUES ('203','Approved',sysdate,'KRADEV');
INSERT INTO SUBMISSION_STATUS (submission_status_code, description, update_timestamp, update_user) VALUES ('204','Exemption Granted',sysdate,'KRADEV');
INSERT INTO SUBMISSION_STATUS (submission_status_code, description, update_timestamp, update_user) VALUES ('205','Disapproved',sysdate,'KRADEV');
INSERT INTO SUBMISSION_STATUS (submission_status_code, description, update_timestamp, update_user) VALUES ('206','Deferred',sysdate,'KRADEV');
INSERT INTO SUBMISSION_STATUS (submission_status_code, description, update_timestamp, update_user) VALUES ('207','Closed',sysdate,'KRADEV');
INSERT INTO SUBMISSION_STATUS (submission_status_code, description, update_timestamp, update_user) VALUES ('208','Terminated',sysdate,'KRADEV');
INSERT INTO SUBMISSION_STATUS (submission_status_code, description, update_timestamp, update_user) VALUES ('209','Suspended',sysdate,'KRADEV');
INSERT INTO SUBMISSION_STATUS (submission_status_code, description, update_timestamp, update_user) VALUES ('210','Withdrawn',sysdate,'KRADEV');
INSERT INTO SUBMISSION_STATUS (submission_status_code, description, update_timestamp, update_user) VALUES ('211','Closed for Enrollment',sysdate,'KRADEV');
INSERT INTO SUBMISSION_STATUS (submission_status_code, description, update_timestamp, update_user) VALUES ('212','IRB Acknowledgement',sysdate,'KRADEV');
INSERT INTO SUBMISSION_STATUS (submission_status_code, description, update_timestamp, update_user) VALUES ('104','IRB review not required',sysdate,'KRADEV');  

commit;


create table protocol_submission (
  submission_number          number (4)     not null,  
  protocol_number            varchar2 (20)  not null,
  sequence_number            number (4)     not null,  
  schedule_id                varchar2 (10),
  committee_id               varchar2 (15),
  submission_type_code       varchar2 (3)     not null,
  submission_type_qual_code  varchar2 (3),
  submission_status_code     varchar2 (3)     not null,  
  protocol_id 				 number(12,0)   not null,
  comm_schedule_id			 number(12)     not null,
  committee_id_new		 	 number(12)     not null,
  protocol_review_type_code  varchar2(3) not NULL,  
  submission_date            date           not null,
  comments                   varchar2 (2000),
  yes_vote_count             number (3),
  no_vote_count              number (3),
  abstainer_count            number (3),
  voting_comments            varchar2 (2000),
  update_timestamp           date,
  update_user                varchar2 (8),
  ver_nbr 				  	 number(8,0) DEFAULT 1 NOT NULL, 
  obj_id				  	 varchar2(36) DEFAULT SYS_GUID() NOT NULL);

ALTER TABLE protocol_submission 
ADD CONSTRAINT pk_ps_protocol_submission
PRIMARY KEY (submission_number);  

ALTER TABLE protocol_submission
ADD CONSTRAINT fk_ps_protocol_id
FOREIGN KEY (protocol_id) 
REFERENCES PROTOCOL (PROTOCOL_ID);

ALTER TABLE protocol_submission
ADD CONSTRAINT fk_ps_comm_schedule_id
FOREIGN KEY (comm_schedule_id) 
REFERENCES comm_schedule (id);

ALTER TABLE protocol_submission
ADD CONSTRAINT fk_ps_committee_id_new
FOREIGN KEY (committee_id_new) 
REFERENCES COMMITTEE (id);

ALTER TABLE protocol_submission
ADD CONSTRAINT fk_ps_prtc_new
FOREIGN KEY (protocol_review_type_code) 
REFERENCES PROTOCOL_REVIEW_TYPE (PROTOCOL_REVIEW_TYPE_CODE);

ALTER TABLE protocol_submission
ADD CONSTRAINT fk_ps_ssc
FOREIGN KEY (submission_status_code) 
REFERENCES submission_status (submission_status_code);

ALTER TABLE protocol_submission
ADD CONSTRAINT fk_ps_stc
FOREIGN KEY (SUBMISSION_TYPE_CODE) 
REFERENCES SUBMISSION_TYPE (SUBMISSION_TYPE_CODE);

ALTER TABLE protocol_submission
ADD CONSTRAINT fk_ps_stqc
FOREIGN KEY (SUBMISSION_TYPE_QUAL_CODE) 
REFERENCES SUBMISSION_TYPE_QUALIFIER (SUBMISSION_TYPE_QUAL_CODE);


CREATE OR REPLACE VIEW osp$protocol_submission AS SELECT
  protocol_number,
  sequence_number,
  submission_number,
  schedule_id,
  committee_id,
  submission_type_code,
  submission_type_qual_code,
  protocol_review_type_code,
  submission_status_code,
  submission_date,
  comments,
  yes_vote_count,
  no_vote_count,
  abstainer_count,
  voting_comments,
  update_timestamp,
  update_user
FROM protocol_submission;

create table protocol_actions (
  action_id                  number (6)     not null,
  protocol_id 			     number(12,0)   not NULL,
  protocol_number            varchar2 (20)  not null,
  sequence_number            number (4)     not null,  
  submission_number          number (4),
  comments                   varchar2 (2000),
  update_timestamp           date           not null,
  update_user                varchar2 (8)   not null,
  action_date                date           default null,
  ver_nbr 				     number(8,0)    default 1 NOT NULL, 
  obj_id				     varchar2(36)   default SYS_GUID() NOT NULL);

ALTER TABLE protocol_actions 
ADD CONSTRAINT pk_pa_action_id
PRIMARY KEY (action_id);    

ALTER TABLE protocol_actions
ADD CONSTRAINT fk_pa_protocol_id
FOREIGN KEY (protocol_id) 
REFERENCES PROTOCOL (PROTOCOL_ID);  

ALTER TABLE protocol_actions
ADD CONSTRAINT fk_pa_submission_number
FOREIGN KEY (submission_number) 
REFERENCES protocol_submission (submission_number);
  
CREATE OR REPLACE VIEW osp$protocol_actions AS SELECT
  protocol_number,
  sequence_number,
  action_id,
  submission_number,
  comments,
  update_timestamp,
  update_user,
  action_date
FROM protocol_actions;  

 
create table protocol_correspondence (
  id					   number(12,0)  not null,
  protocol_id 			   number(12,0)  not NULL,
  protocol_number          varchar2 (20) not null,
  sequence_number          number (4)    not null,
  action_id                number (6)    not null,
  proto_corresp_type_code  varchar2 (3)    not null,
  correspondence           blob          default empty_blob(),
  update_timestamp         date          not null,
  update_user              varchar2 (8)  not null,
  ver_nbr 				   number(8,0) DEFAULT 1 NOT NULL, 
  obj_id				   varchar2(36) DEFAULT SYS_GUID() NOT NULL);

ALTER TABLE protocol_correspondence 
ADD CONSTRAINT pk_pc_id
PRIMARY KEY (id);    

ALTER TABLE protocol_correspondence
ADD CONSTRAINT fk_pc_protocol_id
FOREIGN KEY (protocol_id) 
REFERENCES PROTOCOL (PROTOCOL_ID);

ALTER TABLE protocol_correspondence
ADD CONSTRAINT fk_pc_action_id
FOREIGN KEY (action_id) 
REFERENCES protocol_actions (action_id);

ALTER TABLE protocol_correspondence
ADD CONSTRAINT fk_pc_proto_corresp_type_code
FOREIGN KEY (proto_corresp_type_code) 
REFERENCES proto_corresp_type (proto_corresp_type_code);

CREATE OR REPLACE VIEW osp$protocol_correspondence AS SELECT
  protocol_number,
  sequence_number,
  action_id,
  proto_corresp_type_code,
  correspondence,
  update_timestamp,
  update_user
FROM protocol_correspondence; 

commit;