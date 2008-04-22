create table exemption_type 
(
  exemption_type_code varchar2(3) constraint exemption_type_n1 not null,
  description varchar2 (200) constraint exemption_type_n2 not null,
  detailed_description CLOB, 
  update_timestamp date constraint exemption_type_n3 not null,
  update_user varchar2 (8) constraint exemption_type_n4 not null,
  ver_nbr number(8,0) DEFAULT 1 constraint exemption_type_n5 NOT NULL,
  obj_id varchar2(36) DEFAULT SYS_GUID() constraint exemption_type_n6 NOT NULL,
  constraint exemption_type_p1 primary key (exemption_type_code),
  constraint exemption_type_c0 unique (obj_id)
);