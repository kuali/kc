create table custom_attribute
(
  id                number(12,0) constraint custom_attribute_n1 not null,
  name              varchar2(30) constraint custom_attribute_n2 not null,
  label             varchar2(30) constraint custom_attribute_n3 not null,
  data_type_code    varchar2(3) constraint custom_attribute_n4 not null,
  data_length       number (4),
  default_value     varchar2 (2000),
  lookup_class      varchar2 (100),
  lookup_return     varchar2 (30),
  group_name 	    varchar2(250) null,
  update_timestamp  date constraint custom_attribute_n5 not null,
  update_user       varchar2 (8) constraint custom_attribute_n6 not null,
  ver_nbr           number(8,0) DEFAULT 1 constraint custom_attribute_n7 NOT NULL,
  obj_id            varchar2(36) DEFAULT SYS_GUID() constraint custom_attribute_n8 NOT NULL,
  constraint custom_attribute_p1 primary key (id),
  constraint custom_attribute_c0 unique (obj_id)
)
/

alter table custom_attribute add (
	constraint fk_cust_attr_data_type
	foreign key (data_type_code)
	references custom_attribute_data_type (data_type_code)
)
/