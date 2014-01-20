create table comm_schedule_minutes (
  id            		     NUMBER(12)  not null,
  schedule_id_fk             NUMBER(12)      not null,
  schedule_id                varchar2 (10)  not null,
  entry_number               number (12)    not null,
  minute_entry_type_code     number (3)     not null,
  protocol_id_fk             NUMBER(12)  not null,
  protocol_number            varchar2 (20),
  sequence_number            number (4),
  submission_id_fk           NUMBER(12)  not null,
  submission_number          number (4),
  private_comment_flag       varchar2 (1),
  protocol_contingency_code  varchar2 (4),
  minute_entry               long,
  update_timestamp           date           not null,
  update_user                varchar2 (8)   not null, 
  ver_nbr                    number(8,0) DEFAULT 1 NOT NULL, 
  obj_id                     varchar2(36) DEFAULT SYS_GUID() NOT NULL);

ALTER TABLE comm_schedule_minutes 
ADD CONSTRAINT pk_csm_id 
PRIMARY KEY (id);

ALTER TABLE comm_schedule_minutes  
ADD CONSTRAINT fk_csm_schedule_id_fk
FOREIGN KEY (schedule_id_fk) 
REFERENCES COMM_SCHEDULE (id);

ALTER TABLE comm_schedule_minutes  
ADD CONSTRAINT fk_csm_protocol_id_fk 
FOREIGN KEY (protocol_id_fk) 
REFERENCES PROTOCOL (PROTOCOL_ID);

ALTER TABLE comm_schedule_minutes  
ADD CONSTRAINT fk_csm_submission_id_fk 
FOREIGN KEY (submission_id_fk) 
REFERENCES PROTOCOL_SUBMISSION (SUBMISSION_ID);

CREATE OR REPLACE VIEW OSP$comm_schedule_minutes AS SELECT 
  schedule_id,
  entry_number,
  minute_entry_type_code,
  protocol_number,
  sequence_number,
  submission_number,
  private_comment_flag,
  protocol_contingency_code,
  minute_entry,
  update_timestamp,
  update_user
FROM comm_schedule_minutes;

commit;