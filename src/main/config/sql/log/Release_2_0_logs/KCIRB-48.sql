create table protocol_reference_type (
  protocol_reference_type_code  number (3)    not null,
  description                   varchar2 (200)  not null,
  update_timestamp              date          not null,
  update_user                   varchar2 (60)  not null,
  ver_nbr 						number(8) default 1 not null,
  obj_id						varchar2 (36) default sys_guid() not null);
alter table protocol_reference_type
  add constraint pk_protocol_reference_type
  primary key ( protocol_reference_type_code );
create or replace view osp$protocol_reference_type as select
  protocol_reference_type_code,
  description,
  update_timestamp,
  update_user
from protocol_reference_type;
create table protocol_references (
  protocol_reference_id         number (4)	    not null,
  protocol_id 					number (12) 	not null,
  protocol_number               varchar2 (20)  	not null,
  sequence_number               number (4)    	not null,
  protocol_reference_number     number (3)    	not null,
  protocol_reference_type_code  number (3)    	not null,
  reference_key                 varchar2 (50)  	not null,
  application_date              date,
  approval_date                 date,
  comments                      long,
  update_timestamp              date          	not null,
  update_user                   varchar2 (60)  	not null,
  ver_nbr 						number(8) default 1 not null,
  obj_id						varchar2 (36) default sys_guid() not null);
CREATE SEQUENCE SEQ_PROTOCOL_REFERENCES_ID START WITH 1 MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER;
alter table protocol_references
  add constraint pk_protocol_reference_id
  primary key ( protocol_reference_id );
alter table protocol_references
  add constraint fk_protocol_ref_type_code
  foreign key (protocol_reference_type_code)
  references protocol_reference_type(protocol_reference_type_code);
alter table protocol_references
  add constraint fk_protocol_id
  foreign key (protocol_id)
  references protocol(protocol_id);
create or replace view osp$protocol_references as select
  protocol_number,
  sequence_number,
  protocol_reference_number,
  protocol_reference_type_code,
  reference_key,
  application_date,
  approval_date,
  comments,
  update_timestamp,
  update_user
from protocol_references;
commit;

INSERT INTO PROTOCOL_REFERENCE_TYPE ( protocol_reference_type_code, description, update_timestamp, update_user ) VALUES (1,'CALGB',sysdate,user);
INSERT INTO PROTOCOL_REFERENCE_TYPE ( protocol_reference_type_code, description, update_timestamp, update_user ) VALUES (3,'IRBNet',sysdate,user);
INSERT INTO PROTOCOL_REFERENCE_TYPE ( protocol_reference_type_code, description, update_timestamp, update_user ) VALUES (2,'RTOG',sysdate,user);
INSERT INTO PROTOCOL_REFERENCE_TYPE ( protocol_reference_type_code, description, update_timestamp, update_user ) VALUES (4,'COAG',sysdate,user);
	
commit;	