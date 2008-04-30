#
# $Id: EPS_PROP_PERSON_DEGREE.sql,v 1.3.4.1 2008-04-30 21:23:35 gmcgrego Exp $
#
create table EPS_PROP_PERSON_DEGREE(
  PROPOSAL_NUMBER     NUMBER(12)   CONSTRAINT EPS_PROP_PERSON_DEGREE_N1 not null,
  PROP_PERSON_NUMBER  NUMBER(12)   CONSTRAINT EPS_PROP_PERSON_DEGREE_N2 NOT NULL,
  DEGREE_SEQUENCE_NUMBER  NUMBER(3)   CONSTRAINT EPS_PROP_PERSON_DEGREE_N3 NOT NULL,
  GRADUATION_YEAR     VARCHAR2(4),
  DEGREE_CODE         VARCHAR2(6),
  DEGREE              VARCHAR2(80),
  FIELD_OF_STUDY      VARCHAR2(80),
  SPECIALIZATION      VARCHAR2(80),
  SCHOOL              VARCHAR2(50),
  SCHOOL_ID_CODE      VARCHAR2(3),
  SCHOOL_ID           VARCHAR2(20),
  update_timestamp  date         not null,
  update_user       varchar2 (60) not null,
  VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL,
  OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL,
  constraint EPS_PROP_PERSON_DEGREE_N6
  primary key (PROPOSAL_NUMBER, PROP_PERSON_NUMBER, DEGREE_SEQUENCE_NUMBER)
)
/
ALTER TABLE EPS_PROP_PERSON_DEGREE
  ADD ( CONSTRAINT EPS_PROP_PERSON_DEGREE_C0
      UNIQUE (OBJ_ID) 
      NOT DEFERRABLE INITIALLY IMMEDIATE);
/
ALTER TABLE EPS_PROP_PERSON_UNITS ADD (CONSTRAINT EPS_PROP_PERSON_DEGREE_C1 UNIQUE (PROPOSAL_NUMBER, PROP_PERSON_NUMBER))
