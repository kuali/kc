create table abstract_type 
(
  abstract_type_code  varchar2(3) constraint abstract_type_n1 not null,
  description varchar2 (200) constraint abstract_type_n2 not null,
  update_timestamp date constraint abstract_type_n3 not null,
  update_user varchar2 (8) constraint abstract_type_n4 not null,
  ver_nbr number(8,0) DEFAULT 1 constraint abstract_type_n5 NOT NULL,
  obj_id varchar2(36) DEFAULT SYS_GUID() constraint abstract_type_n6 NOT NULL,
  constraint abstract_type_p1 primary key (abstract_type_code),
  constraint abstract_type_c0 unique (obj_id)
);
