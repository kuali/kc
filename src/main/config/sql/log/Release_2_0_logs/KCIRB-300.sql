create table comm_schedule_attendance (
  id				 number(12,0)  not null,	
  schedule_id_new	 number(12,0)  not null,	
  rolodex_id		 number(12,0)  not null,
  person_id          varchar2 (9)  not null,
  schedule_id        varchar2 (10) not null,
  guest_flag         varchar2 (1)  not null,
  alternate_flag     varchar2 (1)  not null,
  alternate_for      varchar2 (9),
  non_employee_flag  varchar2 (1)  not null,
  comments           varchar2 (2000),
  update_timestamp   date,
  update_user        varchar2 (8),
  ver_nbr 			 number(8,0) DEFAULT 1 NOT NULL, 
  obj_id			 varchar2(36) DEFAULT SYS_GUID() NOT NULL);

ALTER TABLE comm_schedule_attendance 
ADD CONSTRAINT pk_csa_id
PRIMARY KEY (id);  

ALTER TABLE comm_schedule_attendance
ADD CONSTRAINT fk_csa_schedule_id_new
FOREIGN KEY (schedule_id_new) 
REFERENCES comm_schedule (id);  

ALTER TABLE comm_schedule_attendance
ADD CONSTRAINT fk_csa_person_id
FOREIGN KEY (person_id) 
REFERENCES person (person_id);  

ALTER TABLE comm_schedule_attendance
ADD CONSTRAINT fk_csa_rolodex_id
FOREIGN KEY (rolodex_id) 
REFERENCES rolodex (rolodex_id);

CREATE OR REPLACE VIEW osp$comm_schedule_attendance AS SELECT 
  schedule_id,
  person_id,
  guest_flag,
  alternate_flag,
  alternate_for,
  non_employee_flag,
  comments,
  update_timestamp,
  update_user
FROM comm_schedule_attendance;  

commit;