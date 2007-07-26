create table trv_doc_2 (
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT FP_INT_BILL_DOC_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT FP_INT_BILL_DOC_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_INT_BILL_DOC_TN3 NOT NULL,
        FDOC_EXPLAIN_TXT               VARCHAR2(400),
	    request_trav varchar2(30) not null,
	    traveler          varchar2(200),
        org          varchar2(60),
        dest         varchar2(60),
	    CONSTRAINT trv_doc_2P1 PRIMARY KEY (FDOC_NBR) 
)
/

create table trv_acct (
    acct_num  varchar2(10) not null,
    acct_name varchar2(50),
    acct_type varchar2(100),
    acct_fo_id number(14),
    constraint trv_acct_pk primary key(acct_num)
) 
/

create table trv_doc_acct (
    doc_hdr_id  number(14) not null,
    acct_num    varchar2(10) not null,
    constraint trv_doc_acct_pk primary key(doc_hdr_id, acct_num)
) 
/

create table trv_acct_fo (
	acct_fo_id  number(14) not null,
	acct_fo_user_name varchar2(50) not null,
	constraint trv_acct_fo_id_pk primary key(acct_fo_id)
) 
/

create table TRAV_DOC_2_ACCOUNTS (
    FDOC_NBR VARCHAR2(14),
    ACCT_NUM varchar2(10),
    CONSTRAINT TRAV_DOC_2_ACCOUNTS_P1 PRIMARY KEY (FDOC_NBR, ACCT_NUM)
)
/

create table TRV_ACCT_TYPE (
    ACCT_TYPE VARCHAR2(10),
    ACCT_TYPE_NAME varchar2(50),
    CONSTRAINT TRV_ACCT_TYPE_PK PRIMARY KEY (ACCT_TYPE)
)
/

create table TRV_ACCT_EXT (
    ACCT_NUM VARCHAR2(10),
    ACCT_TYPE varchar2(100),
    CONSTRAINT TRV_ACCT_TYPE_P1 PRIMARY KEY (ACCT_NUM, ACCT_TYPE)
)
/

CREATE SEQUENCE SEQ_TRAVEL_DOC_ID INCREMENT BY 1 START WITH 1000 
/
CREATE SEQUENCE SEQ_TRAVEL_FO_ID INCREMENT BY 1 START WITH 1000 
/

alter table trv_acct add constraint trv_acct_fk1 foreign key(acct_fo_id) references trv_acct_fo(acct_fo_id) 
/

insert into trv_acct_fo (acct_fo_id, acct_fo_user_name) values (1, 'fred') 
/
insert into trv_acct_fo (acct_fo_id, acct_fo_user_name) values (2, 'fran') 
/
insert into trv_acct_fo (acct_fo_id, acct_fo_user_name) values (3, 'frank') 
/

insert into TRV_ACCT values ('a1', 'a1', 'CAT', 1) 
/
insert into TRV_ACCT values ('a2', 'a2', 'EAT', 2) 
/
insert into TRV_ACCT values ('a3', 'a3', 'IAT', 3) 
/

insert into TRV_DOC_ACCT (DOC_HDR_ID, ACCT_NUM) values (1, 'a1') 
/
insert into TRV_DOC_ACCT (DOC_HDR_ID, ACCT_NUM) values (1, 'a2') 
/
insert into TRV_DOC_ACCT (DOC_HDR_ID, ACCT_NUM) values (1, 'a3') 
/

insert into en_usr_t values ('quickstart','quickstart','quickstart','quickstart','quickstart@school.edu','quickstart','quickstart','quickstart',to_date('01/01/2000', 'dd/mm/yyyy'),to_date('01/01/2100', 'dd/mm/yyyy'),0,0) 
/
insert into en_wrkgrp_t values (1,1,'WorkflowAdmin',1,'W','Workflow Administrator Workgroup',1,null,0) 
/
insert into EN_WRKGRP_MBR_T values ('quickstart',1,'U',1,0) 
/

INSERT INTO FP_DOC_GROUP_T VALUES ('TR', '054EDFB3B260C8D2E043814FD881C8D2', 1,	'Travel Documents', null)
/
INSERT INTO FP_DOC_GROUP_T VALUES ('KR', '054EDFB3B260C8D2E043816FD881C8D2', 1,	'Kuali Rice', null)
/
insert into FP_DOC_TYPE_T values ('TRVA', '1A6FEB2501C7607EE043814FD881607E', 1, 'TR', 'TRAV ACCNT', 'N', 'Y', 'N', 0, 'N', 'N')
/
insert into FP_DOC_TYPE_T values ('TRFO', '1A6FEB250342607EE043814FD881607E', 1, 'TR', 'TRAV FO', 'N', 'Y', 'N', 0, 'N', 'N')
/
insert into FP_DOC_TYPE_T values ('TRD2', '1A6FEB250342607EE043814FD889607E', 1, 'TR', 'TRAV D2', 'N', 'Y', 'N', 0, 'N', 'N')
/
insert into FP_DOC_TYPE_T values ('RUSR', '1A6FEB253342607EE043814FD889607E', 1, 'TR', 'RICE USR', 'N', 'Y', 'N', 0, 'N', 'N') 
/
insert into FP_DOC_TYPE_T values ('PARM', '1A6FRB253342607EE043814FD889607E', 1, 'TR', 'System Parms', 'N', 'Y', 'N', 0, 'N', 'N') 
/
insert into FP_DOC_TYPE_T values ('BR', '1A6FRB253343337EE043814FD889607E', 1, 'TR', 'Biz Rules', 'N', 'Y', 'N', 0, 'N', 'N') 
/
insert into SH_NTE_TYP_T values ('BO', '2D3C44FE49415102E043814FD8815102',	1,	'DOCUMENT BUSINESS OBJECT', 'Y')
/
insert into SH_NTE_TYP_T values ('DH', '2D3C44FE49425102E043814FD8815102',	1,	'DOCUMENT HEADER', 'Y')
/

insert into TRV_ACCT_EXT values ('a1', 'IAT') 
/
insert into TRV_ACCT_EXT values ('a2', 'EAT') 
/
insert into TRV_ACCT_EXT values ('a3', 'IAT') 
/

insert into TRV_ACCT_TYPE values ('CAT', 'Clearing Account Type') 
/
insert into TRV_ACCT_TYPE values ('EAT', 'Expense Account Type') 
/
insert into TRV_ACCT_TYPE values ('IAT', ' Income Account Type') 
/

INSERT INTO kr_qrtz_locks values('TRIGGER_ACCESS')
/
INSERT INTO kr_qrtz_locks values('JOB_ACCESS')
/
INSERT INTO kr_qrtz_locks values('CALENDAR_ACCESS')
/
INSERT INTO kr_qrtz_locks values('STATE_ACCESS')
/
INSERT INTO kr_qrtz_locks values('MISFIRE_ACCESS')
/


commit 
/
