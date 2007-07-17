create table narrative_type 
(
  narrative_type_code  varchar2(3) constraint narrative_type_n1 not null,
  description          varchar2(200) constraint narrative_type_n2 not null,
  system_generated     varchar2(1) constraint narrative_type_n3 not null,
  allow_multiple       varchar2(1) constraint narrative_type_n4 not null,
  narrative_type_group varchar2(1),
  update_timestamp     date constraint narrative_type_n5 not null,
  update_user          varchar2(8) constraint narrative_type_n6 not null,
  ver_nbr number(8,0) DEFAULT 1 constraint narrative_type_n7 NOT NULL,
  obj_id varchar2(36) DEFAULT SYS_GUID() constraint narrative_type_n8 NOT NULL,
  constraint narrative_type_p1 primary key (narrative_type_code),
  constraint narrative_type_c0 unique (obj_id)
);
