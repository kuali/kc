create table custom_attribute_data_type
(
  data_type_code  varchar2(3) constraint custom_attribute_data_type_n1 not null,
  description varchar2 (200) constraint custom_attribute_data_type_n2 not null,
  update_timestamp date constraint custom_attribute_data_type_n3 not null,
  update_user varchar2 (8) constraint custom_attribute_data_type_n4 not null,
  ver_nbr number(8,0) DEFAULT 1 constraint custom_attribute_data_type_n5 NOT NULL,
  obj_id varchar2(36) DEFAULT SYS_GUID() constraint custom_attribute_data_type_n6 NOT NULL,
  constraint custom_attribute_data_type_p1 primary key (data_type_code),
  constraint custom_attribute_data_type_c0 unique (obj_id)
)
/
