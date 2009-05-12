create table proto_amend_renewal (
  id            		  varchar2 (10)  not null,
  proto_amend_ren_number  varchar2 (20)  not null,
  date_created            date          not null,
  summary                 CLOB,
  protocol_id             number(12,0) not null,
  protocol_number         varchar2 (20),
  sequence_number         number (4),
  update_timestamp        date          not null,
  update_user             varchar2 (8)  not null, 
  ver_nbr                 number(8,0) DEFAULT 1 NOT NULL, 
  obj_id                  varchar2(36) DEFAULT SYS_GUID() NOT NULL);
  
ALTER TABLE proto_amend_renewal 
ADD CONSTRAINT PK_par_id 
PRIMARY KEY (id);

ALTER TABLE proto_amend_renewal  
ADD CONSTRAINT fk_par_protocol_id 
FOREIGN KEY (protocol_id) 
REFERENCES protocol (protocol_id);

CREATE OR REPLACE VIEW OSP$proto_amend_renewal AS SELECT 
  proto_amend_ren_number,
  date_created,
  summary,
  protocol_number,
  sequence_number,
  update_timestamp,
  update_user
FROM proto_amend_renewal;

commit;