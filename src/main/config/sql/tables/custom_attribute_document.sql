create table custom_attribute_document
(
  document_type_code  varchar2(4) constraint custom_attribute_document_n1 not null,
  custom_attribute_id number(12,0) constraint custom_attribute_document_n2 not null,
  type_name           varchar2(100),
  is_required         char(1),
  update_timestamp    date constraint custom_attribute_document_n3 not null,
  update_user         varchar2 (8) constraint custom_attribute_document_n4 not null,
  ver_nbr             number(8,0) DEFAULT 1 constraint custom_attribute_document_n5 NOT NULL,
  obj_id              varchar2(36) DEFAULT SYS_GUID() constraint custom_attribute_document_n6 NOT NULL,
  constraint custom_attribute_document_p1 primary key (document_type_code, custom_attribute_id),
  constraint custom_attribute_document_c0 unique (obj_id)
)
/

alter table custom_attribute_document add (
	constraint fk_cust_attr_doc_doc_type
	foreign key (document_type_code)
	references fp_doc_type_t (fdoc_typ_cd)
)
/

alter table custom_attribute_document add (
	constraint fk_cust_attr_doc_cust_attr
	foreign key (custom_attribute_id)
	references custom_attribute (id)
)
/