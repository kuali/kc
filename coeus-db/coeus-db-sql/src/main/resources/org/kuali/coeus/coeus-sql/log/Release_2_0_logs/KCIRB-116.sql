drop table protocol_references;

create table protocol_references (
  protocol_reference_id         number (4)	    not null,
  protocol_id 					number (12) 	not null,
  protocol_number               varchar2 (20)  	not null,
  sequence_number               number (4)    	not null,
  protocol_reference_number     number (4)    	not null,
  protocol_reference_type_code  number (3)    	not null,
  reference_key                 varchar2 (50)  	not null,
  application_date              date,
  approval_date                 date,
  comments                      long,
  update_timestamp              date          	not null,
  update_user                   varchar2 (60)  	not null,
  ver_nbr 						number(8) default 1 not null,
  obj_id						varchar2 (36) default sys_guid() not null);

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