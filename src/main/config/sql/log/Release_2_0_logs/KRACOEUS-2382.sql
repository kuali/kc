DROP TABLE SCHOOL_CODE;
CREATE TABLE SCHOOL_CODE (
    SCHOOL_CODE NUMBER(3,0) NOT NULL,
    DESCRIPTION VARCHAR2(200) NOT NULL,
    UPDATE_TIMESTAMP DATE NOT NULL,
    UPDATE_USER VARCHAR2(60) NOT NULL,
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL,
    OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL);

insert into school_code(school_code,description,update_timestamp,update_user) values ('1','DUNS',sysdate,'kradev');
insert into school_code(school_code,description,update_timestamp,update_user) values ('9','DUNS+4',sysdate,'kradev');
insert into school_code(school_code,description,update_timestamp,update_user) values ('10','DODACC',sysdate,'kradev');
insert into school_code(school_code,description,update_timestamp,update_user) values ('33','CAGE',sysdate,'kradev');

INSERT INTO FP_DOC_TYPE_T (FDOC_TYP_CD,FDOC_GRP_CD,FDOC_NM,FIN_ELIM_ELGBL_CD,FDOC_TYP_ACTIVE_CD,FDOC_RTNG_RULE_CD,FDOC_AUTOAPRV_DAYS,FDOC_BALANCED_CD,TRN_SCRBBR_OFST_GEN_IND) VALUES ('SCHC','KR','SCHOOL CODE','N','Y','N',0,'N','N');

commit;
