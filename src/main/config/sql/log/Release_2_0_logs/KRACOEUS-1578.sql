CREATE TABLE EPS_PROP_POST_SUB_STATUS
(
   STATUS_CODE NUMBER(3) PRIMARY KEY not null,
   DESCRIPTION VARCHAR2(200) not null,
   DEFINITION VARCHAR2(200) not null,
   UPDATE_TIMESTAMP DATE not null,
   UPDATE_USER VARCHAR2(60) not null,
   VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL,
   OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL
);

Insert into EPS_PROP_POST_SUB_STATUS (STATUS_CODE,DESCRIPTION,DEFINITION,UPDATE_TIMESTAMP,UPDATE_USER) values (1,'Not Submitted', 'not yet submitted to spondor',sysdate,'KRADEV');
Insert into EPS_PROP_POST_SUB_STATUS (STATUS_CODE,DESCRIPTION,DEFINITION,UPDATE_TIMESTAMP,UPDATE_USER) values (2,'Pending', 'awaiting sponsor decision',sysdate,'KRADEV');
Insert into EPS_PROP_POST_SUB_STATUS (STATUS_CODE,DESCRIPTION,DEFINITION,UPDATE_TIMESTAMP,UPDATE_USER) values (3,'Funded', 'grant proposal was accepted by sponsor and funds are forthcoming', sysdate,'KRADEV');
Insert into EPS_PROP_POST_SUB_STATUS (STATUS_CODE,DESCRIPTION,DEFINITION,UPDATE_TIMESTAMP,UPDATE_USER) values (4,'Withdrawn', 'the proposal was submitted to the sponsor but the submitting institution decided to cancel their application', sysdate,'KRADEV');
Insert into EPS_PROP_POST_SUB_STATUS (STATUS_CODE,DESCRIPTION,DEFINITION,UPDATE_TIMESTAMP,UPDATE_USER) values (5,'Rejected', 'the sponsor did not accept and approve the grant proposal for funding', sysdate,'KRADEV');
commit;


ALTER TABLE eps_proposal ADD "POST_SUB_STATUS_CODE" NUMBER(3,0);
ALTER TABLE eps_proposal ADD (CONSTRAINT "FK_POST_SUB_STATUS_KRA" FOREIGN KEY ("POST_SUB_STATUS_CODE")
	  REFERENCES "EPS_PROP_POST_SUB_STATUS" ("STATUS_CODE") );
