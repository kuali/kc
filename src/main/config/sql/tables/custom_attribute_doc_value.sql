create table custom_attribute_doc_value
(
  document_number	  number(10) constraint custom_attribute_doc_value_n1 not null,
  custom_attribute_id number(12,0) constraint custom_attribute_doc_value_n2 not null,
  value               varchar2(2000),
  update_timestamp    date constraint custom_attribute_doc_value_n3 not null,
  update_user         varchar2 (8) constraint custom_attribute_doc_value_n4 not null,
  ver_nbr             number(8,0) DEFAULT 1 constraint custom_attribute_doc_value_n5 NOT NULL,
  obj_id              varchar2(36) DEFAULT SYS_GUID() constraint custom_attribute_doc_value_n6 NOT NULL,
  constraint custom_attribute_doc_value_p1 primary key (document_number, custom_attribute_id),
  constraint custom_attribute_doc_value_c0 unique (obj_id)
)
/

alter table custom_attribute_doc_value add (
	constraint fk_cust_attr_doc_val_cust_attr
	foreign key (custom_attribute_id)
	references custom_attribute (id)
)
/