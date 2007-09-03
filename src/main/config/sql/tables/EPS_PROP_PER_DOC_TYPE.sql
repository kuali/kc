create table EPS_PROP_PERSON_ROLE(
  ID                NUMBER(2)   CONSTRAINT EPS_PROP_PERSON_ROLE_N1 not null,
  DESCRIPTION       VARCHAR(25) NOT NULL,
  update_timestamp  date         not null,
  update_user       varchar2 (8) not null,
  VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL,
  OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL,
  constraint EPS_PROP_PERSON_ROLE_N2
  primary key ( ID ) 
);