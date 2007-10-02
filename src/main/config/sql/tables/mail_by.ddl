create table mail_by
(
  mail_by_code  varchar2(3) constraint mail_by_n1 not null,
  description varchar2 (200) constraint mail_by_n2 not null,
  update_timestamp date constraint mail_by_n3 not null,
  update_user varchar2 (8) constraint mail_by_n4 not null,
  ver_nbr number(8,0) DEFAULT 1 constraint mail_by_n5 NOT NULL,
  obj_id varchar2(36) DEFAULT SYS_GUID() constraint mail_by_n6 NOT NULL,
  constraint mail_by_p1 primary key (mail_by_code),
  constraint mail_by_c0 unique (obj_id)
);