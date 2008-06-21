/* Add boolean submit flag that is set to true when proposal is submitted. */
alter table EPS_PROPOSAL add (SUBMIT_FLAG CHAR(1) DEFAULT 'N' NOT NULL);

/* Proposal states */
create table PROPOSAL_STATE
(
  state_type_code  varchar2(3) constraint proposal_state_n1 not null,
  description varchar2 (200) constraint proposal_state_n2 not null,
  update_timestamp date constraint proposal_state_n3 not null,
  update_user varchar2 (60) constraint proposal_state_n4 not null,
  ver_nbr number(8,0) DEFAULT 1 constraint proposal_state_n5 NOT NULL,
  obj_id varchar2(36) DEFAULT SYS_GUID() constraint proposal_state_n6 NOT NULL,
  constraint proposal_state_p1 primary key (state_type_code),
  constraint proposal_state_c0 unique (obj_id)
);

insert into proposal_state (state_type_code,description,update_timestamp,update_user) values('1','In Progress',sysdate,'kradev');
insert into proposal_state (state_type_code,description,update_timestamp,update_user) values('2','Approval Pending',sysdate,'kradev');
insert into proposal_state (state_type_code,description,update_timestamp,update_user) values('3','Approval Granted',sysdate,'kradev');
insert into proposal_state (state_type_code,description,update_timestamp,update_user) values('4','Approval Not Initiated - Submitted',sysdate,'kradev');
insert into proposal_state (state_type_code,description,update_timestamp,update_user) values('5','Approval Pending - Submitted',sysdate,'kradev');
insert into proposal_state (state_type_code,description,update_timestamp,update_user) values('6','Approved and Submitted',sysdate,'kradev');
insert into proposal_state (state_type_code,description,update_timestamp,update_user) values('7','Disapproved',sysdate,'kradev');
insert into proposal_state (state_type_code,description,update_timestamp,update_user) values('8','Approved Post-Submission',sysdate,'kradev');
insert into proposal_state (state_type_code,description,update_timestamp,update_user) values('9','Disapproved Post-Submission',sysdate,'kradev');
insert into proposal_state (state_type_code,description,update_timestamp,update_user) values('10','Canceled',sysdate,'kradev');
insert into proposal_state (state_type_code,description,update_timestamp,update_user) values('11','Document Error Occurred',sysdate,'kradev');

update EPS_PROPOSAL set STATUS_CODE='1';
