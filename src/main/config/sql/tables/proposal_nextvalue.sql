
create table proposal_nextvalue 
(
  proposal_number  number(12) constraint proposal_nextvalue_n1 not null,
  property_name varchar2 (200) constraint proposal_nextvalue_n2 not null,
  next_value number(12) constraint proposal_nextvalue_n3 not null,
  update_timestamp date constraint proposal_nextvalue_n4 not null,
  update_user varchar2 (8) constraint proposal_nextvalue_n5 not null,
  ver_nbr number(8,0) DEFAULT 1 constraint proposal_nextvalue_n6 NOT NULL,
  obj_id varchar2(36) DEFAULT SYS_GUID() constraint proposal_nextvalue_n7 NOT NULL,
  CONSTRAINT "PK_PROPOSAL_NEXTVALUE" PRIMARY KEY ("PROPOSAL_NUMBER", "PROPERTY_NAME") ENABLE
);
