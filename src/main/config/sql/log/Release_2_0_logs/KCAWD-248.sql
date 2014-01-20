-- Table Script 
CREATE TABLE AWARD_CLOSEOUT ( 
    AWARD_CLOSEOUT_ID NUMBER(12,0) NOT NULL, 
    AWARD_ID NUMBER(12,0) NOT NULL, 
    AWARD_NUMBER VARCHAR2(10) NOT NULL, 
    SEQUENCE_NUMBER NUMBER(4,0) NOT NULL,
	CLOSEOUT_REPORT_CODE VARCHAR2(3) NOT NULL,
	CLOSEOUT_REPORT_NAME VARCHAR2(100) NOT NULL,
	DUE_DATE DATE,    
	FINAL_SUBMISSION_DATE DATE,
	MULTIPLE CHAR(1),
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL);

CREATE TABLE CLOSEOUT_REPORT_TYPE (
	CLOSEOUT_REPORT_CODE VARCHAR2(3) NOT NULL,
	DESCRIPTION VARCHAR2(200) NOT NULL,
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL);

ALTER TABLE AWARD
ADD ARCHIVE_LOCATION VARCHAR2(50);

ALTER TABLE AWARD 
ADD CLOSEOUT_DATE DATE; 
    
-- Primary Key Constraint 
ALTER TABLE AWARD_CLOSEOUT 
ADD CONSTRAINT PK_AWARD_CLOSEOUT 
PRIMARY KEY (AWARD_CLOSEOUT_ID);

ALTER TABLE CLOSEOUT_REPORT_TYPE 
ADD CONSTRAINT PK_CLOSEOUT_REPORT_TYPE 
PRIMARY KEY (CLOSEOUT_REPORT_CODE);

-- Foreign Key Constraint(s)
ALTER TABLE AWARD_CLOSEOUT 
ADD CONSTRAINT FK_AWARD_CLOSEOUT 
FOREIGN KEY (AWARD_ID) 
REFERENCES AWARD (AWARD_ID);

ALTER TABLE AWARD_CLOSEOUT 
ADD CONSTRAINT FK_CLOSEOUT_REPORT_TYPE 
FOREIGN KEY (CLOSEOUT_REPORT_CODE) 
REFERENCES CLOSEOUT_REPORT_TYPE (CLOSEOUT_REPORT_CODE);

-- Sequence
CREATE SEQUENCE SEQ_AWARD_AWARD_CLOSEOUT INCREMENT BY 1 START WITH 1 NOCACHE;


INSERT INTO CLOSEOUT_REPORT_TYPE(CLOSEOUT_REPORT_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES('1', 'Financial Report', sysdate,'KRADEV');
INSERT INTO CLOSEOUT_REPORT_TYPE(CLOSEOUT_REPORT_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES('4', 'Technical', sysdate,'KRADEV');
INSERT INTO CLOSEOUT_REPORT_TYPE(CLOSEOUT_REPORT_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES('3', 'Patent', sysdate,'KRADEV');
INSERT INTO CLOSEOUT_REPORT_TYPE(CLOSEOUT_REPORT_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES('2', 'Property', sysdate,'KRADEV');
INSERT INTO CLOSEOUT_REPORT_TYPE(CLOSEOUT_REPORT_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES('UD', 'USER DEFINED', sysdate,'KRADEV');


INSERT INTO KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD,GRP_NM,ACTV_IND) 
	VALUES 
	('KC-AWARD','D','closeoutReportTypeUserDefined',sys_guid(),1,'CONFG','UD','User Defined Close out Report Type','A','WorkflowAdmin','Y');
INSERT INTO KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD,GRP_NM,ACTV_IND) 
	VALUES 
	('KC-AWARD','D','closeoutReportTypeFinancialReport',sys_guid(),1,'CONFG','1','This system parameter maps the CloseoutReportType Financial Report(closeoutReoprtTypeCode=1) with ReportClass Fiscal(reportClassCode=1). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.','A','WorkflowAdmin','Y');
INSERT INTO KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD,GRP_NM,ACTV_IND) 
	VALUES 
	('KC-AWARD','D','closeoutReportTypeTechnical',sys_guid(),1,'CONFG','4','This system parameter maps the CloseoutReportType Technical(closeoutReoprtTypeCode=4) with ReportClass Technical Management(reportClassCode=4). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.','A','WorkflowAdmin','Y');
INSERT INTO KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD,GRP_NM,ACTV_IND) 
	VALUES 
	('KC-AWARD','D','closeoutReportTypePatent',sys_guid(),1,'CONFG','3','This system parameter maps the CloseoutReportType Patent(closeoutReoprtTypeCode=3) with ReportClass Intellectual Property(reportClassCode=3). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.','A','WorkflowAdmin','Y');
INSERT INTO KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT, PARM_DESC_TXT,CONS_CD,GRP_NM,ACTV_IND) 
	VALUES 
	('KC-AWARD','D','closeoutReportTypeProperty',sys_guid(),1,'CONFG','2','This system parameter maps the CloseoutReportType Property(closeoutReoprtTypeCode=2) with ReportClass Property(reportClassCode=2). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.','A','WorkflowAdmin','Y');
	
commit;	