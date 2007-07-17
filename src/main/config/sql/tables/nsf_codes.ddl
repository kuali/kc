create table nsf_codes
(
  nsf_sequence_number number(12) constraint nsf_sequence_number_n1 not null,
  nsf_code      varchar2 (15) constraint nsf_code_n2 not null,
  description       varchar2 (200) constraint nsf_code_n3 not null,
  update_timestamp  date         constraint nsf_code_n4 not null,
  update_user       varchar2 (8) constraint nsf_code_n5 not null,
  VER_NBR NUMBER(8,0) DEFAULT 1 constraint nsf_code_n6 NOT NULL,
  OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() constraint nsf_code_n7 NOT NULL,
  constraint nsf_sequence_number_p1 primary key (nsf_sequence_number),
  constraint nsf_code_c0 unique (obj_id)
);
