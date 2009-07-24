create table EPS_PROPOSAL_DOCUMENT as select DOCUMENT_NUMBER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR from EPS_PROPOSAL;
alter table EPS_PROPOSAL_DOCUMENT add (OBJ_ID varchar2(36) default SYS_GUID() not null enable); 
alter table EPS_PROPOSAL_DOCUMENT add constraint PK_EPS_PROPOSAL_DOCUMENT primary key (DOCUMENT_NUMBER);
alter table EPS_PROPOSAL add constraint FK_EPS_PROPOSAL_DOCUMENT foreign key (document_NUMBER) references EPS_PROPOSAL_DOCUMENT(DOCUMENT_NUMBER) enable;
