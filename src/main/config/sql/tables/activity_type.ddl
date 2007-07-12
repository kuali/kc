create table activity_type 
(
  activity_type_code  number(3) constraint activity_type_n1 not null,
  description varchar2 (200) constraint activity_type_n2 not null,
  update_timestamp date constraint activity_type_n3 not null,
  update_user varchar2 (8) constraint activity_type_n4 not null,
  ver_nbr number(8,0) DEFAULT 1 constraint activity_type_n5 NOT NULL,
  obj_id varchar2(36) DEFAULT SYS_GUID() constraint activity_type_n6 NOT NULL,
  constraint activity_type_p1 primary key (activity_type_code),
  constraint activity_type_c0 unique (obj_id)
);
