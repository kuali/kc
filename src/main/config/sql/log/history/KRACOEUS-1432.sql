/* Authz changes so KRA more closely resembles COEUS */

create table KIM_ROLE_TYPE_T
(
  role_type_code char(1) constraint kim_role_type_n1 not null,
  description varchar2 (200) constraint kim_role_type_n2 not null,
  ver_nbr number(8,0) DEFAULT 1 constraint kim_role_type_n5 NOT NULL,
  obj_id varchar2(36) DEFAULT SYS_GUID() constraint kim_role_type_n6 NOT NULL,
  constraint kim_role_type_p1 primary key (role_type_code),
  constraint kim_role_type_c0 unique (obj_id)
);

insert into KIM_ROLE_TYPE_T (ROLE_TYPE_CODE, DESCRIPTION) values ('P', 'Proposal');
insert into KIM_ROLE_TYPE_T (ROLE_TYPE_CODE, DESCRIPTION) values ('O', 'OSP');

alter table KIM_ROLES_T add (ROLE_TYPE_CODE CHAR(1) DEFAULT 'P' NOT NULL);
update KIM_ROLES_T set ROLE_TYPE_CODE='O' where NAME='Proposal Creator';

