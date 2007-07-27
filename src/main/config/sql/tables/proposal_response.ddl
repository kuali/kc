create table proposal_response 
(
  proposal_response_code  number (3) constraint proposal_response_n1 not null,
  description         varchar2 (200) constraint proposal_response_n2 not null,
  update_timestamp    date constraint proposal_response_n3 not null,
  update_user         varchar2 (8)  constraint proposal_response_n4 not null,
  VER_NBR NUMBER(8,0) DEFAULT 1 constraint proposal_response_n5 NOT NULL,
  OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() constraint proposal_response_n6 NOT NULL,
  constraint proposal_response_p1 primary key (proposal_response_code),
  constraint proposal_response_c0 unique (obj_id)
);
