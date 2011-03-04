-- KULRICE-3522 -- Drop application URL from KIM attribute definition table
alter table krim_attr_defn_t drop column appl_url
/ 

-- KULRICE-3685 --  Kim entity tables missing foreign-key relationships 
ALTER TABLE KRIM_ENTITY_ETHNIC_T 
add CONSTRAINT KRIM_ENTITY_ETHNIC_TR1
  FOREIGN KEY (entity_id)
  REFERENCES KRIM_ENTITY_T(entity_id)
/
ALTER TABLE KRIM_ENTITY_RESIDENCY_T  
add CONSTRAINT KRIM_ENTITY_RESIDENCY_TR1
  FOREIGN KEY (entity_id)
  REFERENCES KRIM_ENTITY_T(entity_id)
/
ALTER TABLE KRIM_ENTITY_VISA_T  
add CONSTRAINT KRIM_ENTITY_VISA_TR1
  FOREIGN KEY (entity_id)
  REFERENCES KRIM_ENTITY_T(entity_id)
/

-- KULRICE-3692 -- Eliminate unnecessary deserialization of ServiceDefinition objects
DELETE FROM KRSB_SVC_DEF_T
/
ALTER TABLE KRSB_SVC_DEF_T DROP (SVC_DEF)
/
ALTER TABLE KRSB_SVC_DEF_T ADD (
	FLT_SVC_DEF_ID NUMBER(14, 0) NOT NULL,
	SVC_DEF_CHKSM VARCHAR2(30) NOT NULL
)
/
CREATE UNIQUE INDEX KRSB_SVC_DEF_TI2 ON KRSB_SVC_DEF_T (FLT_SVC_DEF_ID)
/
CREATE TABLE KRSB_FLT_SVC_DEF_T (
	FLT_SVC_DEF_ID NUMBER(14, 0),
	FLT_SVC_DEF CLOB NOT NULL,
	CONSTRAINT KRSB_FLT_SVC_DEF_TP1 PRIMARY KEY (FLT_SVC_DEF_ID)
)
/
CREATE SEQUENCE KRSB_FLT_SVC_DEF_S START WITH 1000 INCREMENT BY 1
/
-- KULRICE-3700 -- Add DataDictionarySearchableAttributeTest to the master databases and update validation params
UPDATE KRNS_PARM_T SET TXT='MM/dd/yyyy;MM/dd/yyyy HH:mm:ss;MM/dd/yy;MM-dd-yy;MMddyy;MMMM dd;yyyy;MM/dd/yy HH:mm:ss;MM-dd-yy HH:mm:ss;MMddyy HH:mm:ss;MMMM dd HH:mm:ss;yyyy HH:mm:ss' WHERE NMSPC_CD='KR-NS' AND PARM_DTL_TYP_CD='All' AND PARM_NM='STRING_TO_DATE_FORMATS' AND APPL_NMSPC_CD='KUALI' 
/ 
UPDATE KRNS_PARM_T SET TXT='MM/dd/yyyy hh:mm a;MM/dd/yyyy;MM/dd/yyyy HH:mm:ss;MM/dd/yy;MM-dd-yy;MMddyy;MMMM dd;yyyy;MM/dd/yy HH:mm:ss;MM-dd-yy HH:mm:ss;MMddyy HH:mm:ss;MMMM dd HH:mm:ss;yyyy HH:mm:ss' WHERE NMSPC_CD='KR-NS' AND PARM_DTL_TYP_CD='All' AND PARM_NM='STRING_TO_TIMESTAMP_FORMATS' AND APPL_NMSPC_CD='KUALI' 
/ 
CREATE TABLE ACCT_DD_ATTR_DOC ( 
DOC_HDR_ID VARCHAR2(14), 
OBJ_ID VARCHAR2(36), 
VER_NBR NUMBER(14), 
ACCT_NUM NUMBER(14) NOT NULL, 
ACCT_OWNR VARCHAR2(50) NOT NULL, 
ACCT_BAL NUMBER(16,2) NOT NULL, 
ACCT_OPN_DAT DATE NOT NULL, 
ACCT_STAT VARCHAR2(30) NOT NULL, 
ACCT_UPDATE_DT_TM TIMESTAMP, 
ACCT_AWAKE VARCHAR2(1), 
CONSTRAINT ACCT_DD_ATTR_DOC_PK PRIMARY KEY (DOC_HDR_ID) 
) 
/
commit;
/

create table TST_SEARCH_ATTR_INDX_TST_DOC_T (
    DOC_HDR_ID VARCHAR2(14),
	OBJ_ID VARCHAR2(36),
	VER_NBR NUMBER(14),
	RTE_LVL_CNT NUMBER(14),
	CNSTNT_STR VARCHAR2(50),
    RTD_STR VARCHAR2(50),
    HLD_RTD_STR VARCHAR2(50),
    RD_ACCS_CNT NUMBER(14),
    CONSTRAINT TST_SEARCH_ATTR_INDX_TST_DOC_T PRIMARY KEY (DOC_HDR_ID)
)
/
-- KULRICE-3773 --  Apply the database changes resulting from the doc search date validation fixes to the master DBs
UPDATE KRNS_PARM_T SET TXT='MM/dd/yy;MM/dd/yyyy;MM-dd-yy;MMddyy;MMMM dd;yyyy;MM/dd/yy HH:mm:ss;MM/dd/yyyy HH:mm:ss;MM-dd-yy HH:mm:ss;MMddyy HH:mm:ss;MMMM dd HH:mm:ss;yyyy HH:mm:ss' WHERE NMSPC_CD='KR-NS' AND PARM_DTL_TYP_CD='All' AND PARM_NM='STRING_TO_DATE_FORMATS' AND APPL_NMSPC_CD='KUALI' 
/ 
UPDATE KRNS_PARM_T SET TXT='MM/dd/yyyy hh:mm a;MM/dd/yy;MM/dd/yyyy;MM-dd-yy;MMddyy;MMMM dd;yyyy;MM/dd/yy HH:mm:ss;MM/dd/yyyy HH:mm:ss;MM-dd-yy HH:mm:ss;MMddyy HH:mm:ss;MMMM dd HH:mm:ss;yyyy HH:mm:ss' WHERE NMSPC_CD='KR-NS' AND PARM_DTL_TYP_CD='All' AND PARM_NM='STRING_TO_TIMESTAMP_FORMATS' AND APPL_NMSPC_CD='KUALI' 
/
commit;
/

alter table KREW_DOC_TYP_T add DOC_SEARCH_HELP_URL VARCHAR(4000) DEFAULT NULL;
/
-- KULRICE-3780 -- more date validation formats
UPDATE KRNS_PARM_T SET TXT='MM/dd/yyyy hh:mm a;MM/dd/yy;MM/dd/yyyy;MM-dd-yy;MMddyy;MMMM dd;yyyy;MM/dd/yy HH:mm:ss;MM/dd/yyyy HH:mm:ss;MM-dd-yy HH:mm:ss;MMddyy HH:mm:ss;MMMM dd HH:mm:ss;yyyy HH:mm:ss' WHERE NMSPC_CD='KR-NS' AND PARM_DTL_TYP_CD='All' AND PARM_NM='STRING_TO_DATE_FORMATS' AND APPL_NMSPC_CD='KUALI' 
/
commit;
/