create table nsf_codes
(
  nsf_code      varchar2 (15) constraint nsf_code_n1 not null,
  description       varchar2 (200) constraint nsf_code_n2 not null,
  update_timestamp  date         constraint nsf_code_n3 not null,
  update_user       varchar2 (8) constraint nsf_code_n4 not null,
  VER_NBR NUMBER(8,0) DEFAULT 1 constraint nsf_code_n5 NOT NULL,
  OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() constraint nsf_code_n6 NOT NULL,
  constraint nsf_code_p1 primary key (nsf_code),
  constraint nsf_code_c0 unique (obj_id)
);
