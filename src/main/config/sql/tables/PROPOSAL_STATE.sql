create table PROPOSAL_STATE 
(
  state_type_code  varchar2(3) constraint proposal_state_n1 not null,
  description varchar2 (200) constraint proposal_state_n2 not null,
  update_timestamp date constraint proposal_state_n3 not null,
  update_user varchar2 (60) constraint proposal_state_n4 not null,
  ver_nbr number(8,0) DEFAULT 1 constraint proposal_state_n5 NOT NULL,
  obj_id varchar2(36) DEFAULT SYS_GUID() constraint proposal_state_n6 NOT NULL,
  constraint proposal_state_p1 primary key (state_type_code),
  constraint proposal_state_c0 unique (obj_id)
);
