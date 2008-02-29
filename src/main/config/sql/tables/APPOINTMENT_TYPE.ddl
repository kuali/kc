create table appointment_type 
(
  appointment_type_code  varchar2(3) constraint appointment_type_n1 not null,
  description varchar2 (200) constraint appointment_type_n2 not null,
  update_timestamp date constraint appointment_type_n3 not null,
  update_user varchar2 (8) constraint appointment_type_n4 not null,
  ver_nbr number(8,0) DEFAULT 1 constraint appointment_type_n5 NOT NULL,
  obj_id varchar2(36) DEFAULT SYS_GUID() constraint appointment_type_n6 NOT NULL,
  constraint appointment_type_p1 primary key (appointment_type_code),
  constraint appointment_type_c0 unique (obj_id)
);
