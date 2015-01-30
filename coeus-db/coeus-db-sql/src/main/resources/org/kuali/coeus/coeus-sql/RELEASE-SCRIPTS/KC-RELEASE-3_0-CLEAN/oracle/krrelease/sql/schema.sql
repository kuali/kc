--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--


-----------------------------------------------------------------------------
-- ACCT_DD_ATTR_DOC
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'ACCT_DD_ATTR_DOC';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE ACCT_DD_ATTR_DOC CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE ACCT_DD_ATTR_DOC
(
      DOC_HDR_ID VARCHAR2(14)
        , OBJ_ID VARCHAR2(36)
        , VER_NBR NUMBER(14)
        , ACCT_NUM NUMBER(14) NOT NULL
        , ACCT_OWNR VARCHAR2(50) NOT NULL
        , ACCT_BAL NUMBER(16,2) NOT NULL
        , ACCT_OPN_DAT DATE NOT NULL
        , ACCT_STAT VARCHAR2(30) NOT NULL
        , ACCT_UPDATE_DT_TM DATE
        , ACCT_AWAKE VARCHAR2(1)
    

)
/

ALTER TABLE ACCT_DD_ATTR_DOC
    ADD CONSTRAINT ACCT_DD_ATTR_DOCP1
PRIMARY KEY (DOC_HDR_ID)
/







-----------------------------------------------------------------------------
-- KREN_CHNL_PRODCR_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREN_CHNL_PRODCR_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREN_CHNL_PRODCR_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREN_CHNL_PRODCR_T
(
      CHNL_ID NUMBER(8)
        , PRODCR_ID NUMBER(8)
    

)
/

ALTER TABLE KREN_CHNL_PRODCR_T
    ADD CONSTRAINT KREN_CHNL_PRODCR_TP1
PRIMARY KEY (CHNL_ID,PRODCR_ID)
/


CREATE INDEX KREN_CHNL_PRODCR_TI1 
  ON KREN_CHNL_PRODCR_T 
  (CHNL_ID)
/
CREATE INDEX KREN_CHNL_PRODCR_TI2 
  ON KREN_CHNL_PRODCR_T 
  (PRODCR_ID)
/





-----------------------------------------------------------------------------
-- KREN_CHNL_SUBSCRP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREN_CHNL_SUBSCRP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREN_CHNL_SUBSCRP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREN_CHNL_SUBSCRP_T
(
      CHNL_SUBSCRP_ID NUMBER(8)
        , CHNL_ID NUMBER(8) NOT NULL
        , PRNCPL_ID VARCHAR2(40) NOT NULL
    
    , CONSTRAINT KREN_CHNL_SUBSCRP_TC0 UNIQUE (CHNL_ID, PRNCPL_ID)

)
/

ALTER TABLE KREN_CHNL_SUBSCRP_T
    ADD CONSTRAINT KREN_CHNL_SUBSCRP_TP1
PRIMARY KEY (CHNL_SUBSCRP_ID)
/


CREATE INDEX KREN_CHNL_SUBSCRP_TI1 
  ON KREN_CHNL_SUBSCRP_T 
  (CHNL_ID)
/





-----------------------------------------------------------------------------
-- KREN_CHNL_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREN_CHNL_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREN_CHNL_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREN_CHNL_T
(
      CHNL_ID NUMBER(8)
        , NM VARCHAR2(200) NOT NULL
        , DESC_TXT VARCHAR2(4000) NOT NULL
        , SUBSCRB_IND CHAR(1) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
    
    , CONSTRAINT KREN_CHNL_TC0 UNIQUE (NM)

)
/

ALTER TABLE KREN_CHNL_T
    ADD CONSTRAINT KREN_CHNL_TP1
PRIMARY KEY (CHNL_ID)
/







-----------------------------------------------------------------------------
-- KREN_CNTNT_TYP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREN_CNTNT_TYP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREN_CNTNT_TYP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREN_CNTNT_TYP_T
(
      CNTNT_TYP_ID NUMBER(8)
        , NM VARCHAR2(200) NOT NULL
        , CUR_IND CHAR(1) default 'T' NOT NULL
        , CNTNT_TYP_VER_NBR NUMBER(8) default 0 NOT NULL
        , DESC_TXT VARCHAR2(1000) NOT NULL
        , NMSPC_CD VARCHAR2(1000) NOT NULL
        , XSD CLOB NOT NULL
        , XSL CLOB NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
    
    , CONSTRAINT KREN_CNTNT_TYP_TC0 UNIQUE (NM, CNTNT_TYP_VER_NBR)

)
/

ALTER TABLE KREN_CNTNT_TYP_T
    ADD CONSTRAINT KREN_CNTNT_TYP_TP1
PRIMARY KEY (CNTNT_TYP_ID)
/







-----------------------------------------------------------------------------
-- KREN_MSG_DELIV_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREN_MSG_DELIV_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREN_MSG_DELIV_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREN_MSG_DELIV_T
(
      MSG_DELIV_ID NUMBER(8)
        , MSG_ID NUMBER(8) NOT NULL
        , TYP_NM VARCHAR2(200) NOT NULL
        , SYS_ID VARCHAR2(300)
        , STAT_CD VARCHAR2(15) NOT NULL
        , PROC_CNT NUMBER(4) default 0 NOT NULL
        , LOCKD_DTTM DATE
        , VER_NBR NUMBER(8) default 0 NOT NULL
    
    , CONSTRAINT KREN_MSG_DELIV_TC0 UNIQUE (MSG_ID, TYP_NM)

)
/

ALTER TABLE KREN_MSG_DELIV_T
    ADD CONSTRAINT KREN_MSG_DELIV_TP1
PRIMARY KEY (MSG_DELIV_ID)
/


CREATE INDEX KREN_MSG_DELIV_TI1 
  ON KREN_MSG_DELIV_T 
  (MSG_ID)
/





-----------------------------------------------------------------------------
-- KREN_MSG_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREN_MSG_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREN_MSG_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREN_MSG_T
(
      MSG_ID NUMBER(8)
        , ORGN_ID VARCHAR2(128)
        , DELIV_TYP VARCHAR2(500) NOT NULL
        , CRTE_DTTM DATE NOT NULL
        , TTL VARCHAR2(255)
        , CHNL VARCHAR2(300) NOT NULL
        , PRODCR VARCHAR2(300)
        , CNTNT CLOB NOT NULL
        , CNTNT_TYP VARCHAR2(128)
        , URL VARCHAR2(512)
        , RECIP_ID VARCHAR2(300) NOT NULL
        , VER_NBR NUMBER(8) default 0 NOT NULL
    
    , CONSTRAINT KREN_MSG_TC0 UNIQUE (ORGN_ID)

)
/

ALTER TABLE KREN_MSG_T
    ADD CONSTRAINT KREN_MSG_TP1
PRIMARY KEY (MSG_ID)
/







-----------------------------------------------------------------------------
-- KREN_NTFCTN_MSG_DELIV_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREN_NTFCTN_MSG_DELIV_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREN_NTFCTN_MSG_DELIV_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREN_NTFCTN_MSG_DELIV_T
(
      NTFCTN_MSG_DELIV_ID NUMBER(8)
        , NTFCTN_ID NUMBER(8) NOT NULL
        , RECIP_ID VARCHAR2(40) NOT NULL
        , STAT_CD VARCHAR2(15) NOT NULL
        , SYS_ID VARCHAR2(300)
        , LOCKD_DTTM DATE
        , VER_NBR NUMBER(8) default 0 NOT NULL
    
    , CONSTRAINT KREN_NTFCTN_MSG_DELIV_TC0 UNIQUE (NTFCTN_ID, RECIP_ID)

)
/

ALTER TABLE KREN_NTFCTN_MSG_DELIV_T
    ADD CONSTRAINT KREN_NTFCTN_MSG_DELIV_TP1
PRIMARY KEY (NTFCTN_MSG_DELIV_ID)
/


CREATE INDEX KREN_MSG_DELIVSI1 
  ON KREN_NTFCTN_MSG_DELIV_T 
  (NTFCTN_ID)
/





-----------------------------------------------------------------------------
-- KREN_NTFCTN_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREN_NTFCTN_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREN_NTFCTN_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREN_NTFCTN_T
(
      NTFCTN_ID NUMBER(8)
        , DELIV_TYP VARCHAR2(3) NOT NULL
        , CRTE_DTTM DATE NOT NULL
        , SND_DTTM DATE
        , AUTO_RMV_DTTM DATE
        , PRIO_ID NUMBER(8) NOT NULL
        , TTL VARCHAR2(255)
        , CNTNT CLOB NOT NULL
        , CNTNT_TYP_ID NUMBER(8) NOT NULL
        , CHNL_ID NUMBER(8) NOT NULL
        , PRODCR_ID NUMBER(8) NOT NULL
        , PROCESSING_FLAG VARCHAR2(15) NOT NULL
        , LOCKD_DTTM DATE
        , VER_NBR NUMBER(8) default 0 NOT NULL
    

)
/

ALTER TABLE KREN_NTFCTN_T
    ADD CONSTRAINT KREN_NTFCTN_TP1
PRIMARY KEY (NTFCTN_ID)
/


CREATE INDEX KREN_NTFCTN_I1 
  ON KREN_NTFCTN_T 
  (CNTNT_TYP_ID)
/
CREATE INDEX KREN_NTFCTN_I2 
  ON KREN_NTFCTN_T 
  (PRIO_ID)
/
CREATE INDEX KREN_NTFCTN_I3 
  ON KREN_NTFCTN_T 
  (PRODCR_ID)
/





-----------------------------------------------------------------------------
-- KREN_PRIO_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREN_PRIO_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREN_PRIO_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREN_PRIO_T
(
      PRIO_ID NUMBER(8)
        , NM VARCHAR2(40) NOT NULL
        , DESC_TXT VARCHAR2(500) NOT NULL
        , PRIO_ORD NUMBER(4) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
    
    , CONSTRAINT KREN_PRIO_TC0 UNIQUE (NM)

)
/

ALTER TABLE KREN_PRIO_T
    ADD CONSTRAINT KREN_PRIO_TP1
PRIMARY KEY (PRIO_ID)
/







-----------------------------------------------------------------------------
-- KREN_PRODCR_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREN_PRODCR_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREN_PRODCR_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREN_PRODCR_T
(
      PRODCR_ID NUMBER(8)
        , NM VARCHAR2(200) NOT NULL
        , DESC_TXT VARCHAR2(1000) NOT NULL
        , CNTCT_INFO VARCHAR2(1000) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
    
    , CONSTRAINT KREN_PRODCR_TC0 UNIQUE (NM)

)
/

ALTER TABLE KREN_PRODCR_T
    ADD CONSTRAINT KREN_PRODCR_TP1
PRIMARY KEY (PRODCR_ID)
/







-----------------------------------------------------------------------------
-- KREN_RECIP_DELIV_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREN_RECIP_DELIV_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREN_RECIP_DELIV_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREN_RECIP_DELIV_T
(
      RECIP_DELIV_ID NUMBER(8)
        , RECIP_ID VARCHAR2(40) NOT NULL
        , CHNL VARCHAR2(300) NOT NULL
        , NM VARCHAR2(200) NOT NULL
        , VER_NBR NUMBER(8) default 0 NOT NULL
    

)
/

ALTER TABLE KREN_RECIP_DELIV_T
    ADD CONSTRAINT KREN_RECIP_DELIV_TP1
PRIMARY KEY (RECIP_DELIV_ID)
/







-----------------------------------------------------------------------------
-- KREN_RECIP_LIST_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREN_RECIP_LIST_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREN_RECIP_LIST_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREN_RECIP_LIST_T
(
      RECIP_LIST_ID NUMBER(8)
        , CHNL_ID NUMBER(8) NOT NULL
        , RECIP_TYP_CD VARCHAR2(10) NOT NULL
        , RECIP_ID VARCHAR2(40) NOT NULL
    
    , CONSTRAINT KREN_RECIP_LIST_TC0 UNIQUE (CHNL_ID, RECIP_TYP_CD, RECIP_ID)

)
/

ALTER TABLE KREN_RECIP_LIST_T
    ADD CONSTRAINT KREN_RECIP_LIST_TP1
PRIMARY KEY (RECIP_LIST_ID)
/


CREATE INDEX KREN_RECIP_LIST_TI1 
  ON KREN_RECIP_LIST_T 
  (CHNL_ID)
/





-----------------------------------------------------------------------------
-- KREN_RECIP_PREFS_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREN_RECIP_PREFS_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREN_RECIP_PREFS_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREN_RECIP_PREFS_T
(
      RECIP_PREFS_ID NUMBER(8)
        , RECIP_ID VARCHAR2(40) NOT NULL
        , PROP VARCHAR2(200) NOT NULL
        , VAL VARCHAR2(1000) NOT NULL
        , VER_NBR NUMBER(8) default 0 NOT NULL
    
    , CONSTRAINT KREN_RECIP_PREFS_TC0 UNIQUE (RECIP_ID, PROP)

)
/

ALTER TABLE KREN_RECIP_PREFS_T
    ADD CONSTRAINT KREN_RECIP_PREFS_TP1
PRIMARY KEY (RECIP_PREFS_ID)
/







-----------------------------------------------------------------------------
-- KREN_RECIP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREN_RECIP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREN_RECIP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREN_RECIP_T
(
      RECIP_ID NUMBER(8)
        , NTFCTN_ID NUMBER(8) NOT NULL
        , RECIP_TYP_CD VARCHAR2(10) NOT NULL
        , PRNCPL_ID VARCHAR2(40) NOT NULL
    
    , CONSTRAINT KREN_RECIP_TC0 UNIQUE (NTFCTN_ID, RECIP_TYP_CD, PRNCPL_ID)

)
/

ALTER TABLE KREN_RECIP_T
    ADD CONSTRAINT KREN_RECIP_TP1
PRIMARY KEY (RECIP_ID)
/


CREATE INDEX KREN_RECIP_TI1 
  ON KREN_RECIP_T 
  (NTFCTN_ID)
/





-----------------------------------------------------------------------------
-- KREN_RVWER_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREN_RVWER_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREN_RVWER_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREN_RVWER_T
(
      RVWER_ID NUMBER(8)
        , CHNL_ID NUMBER(8) NOT NULL
        , TYP VARCHAR2(10) NOT NULL
        , PRNCPL_ID VARCHAR2(40) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
    
    , CONSTRAINT KREN_RVWER_TC0 UNIQUE (CHNL_ID, TYP, PRNCPL_ID)

)
/

ALTER TABLE KREN_RVWER_T
    ADD CONSTRAINT KREN_RVWER_TP1
PRIMARY KEY (RVWER_ID)
/


CREATE INDEX KREN_RVWER_TI1 
  ON KREN_RVWER_T 
  (CHNL_ID)
/





-----------------------------------------------------------------------------
-- KREN_SNDR_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREN_SNDR_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREN_SNDR_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREN_SNDR_T
(
      SNDR_ID NUMBER(8)
        , NTFCTN_ID NUMBER(8) NOT NULL
        , NM VARCHAR2(200) NOT NULL
    
    , CONSTRAINT KREN_SNDR_TC0 UNIQUE (NTFCTN_ID, NM)

)
/

ALTER TABLE KREN_SNDR_T
    ADD CONSTRAINT KREN_SNDR_TP1
PRIMARY KEY (SNDR_ID)
/


CREATE INDEX KREN_SNDR_TI1 
  ON KREN_SNDR_T 
  (NTFCTN_ID)
/





-----------------------------------------------------------------------------
-- KREW_ACTN_ITM_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_ACTN_ITM_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_ACTN_ITM_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_ACTN_ITM_T
(
      ACTN_ITM_ID NUMBER(14)
        , PRNCPL_ID VARCHAR2(40) NOT NULL
        , ASND_DT DATE NOT NULL
        , RQST_CD CHAR(1) NOT NULL
        , ACTN_RQST_ID NUMBER(14) NOT NULL
        , DOC_HDR_ID NUMBER(14) NOT NULL
        , ROLE_NM VARCHAR2(2000)
        , DLGN_PRNCPL_ID VARCHAR2(40)
        , DOC_HDR_TTL VARCHAR2(255)
        , DOC_TYP_LBL VARCHAR2(128) NOT NULL
        , DOC_HDLR_URL VARCHAR2(255) NOT NULL
        , DOC_TYP_NM VARCHAR2(64) NOT NULL
        , RSP_ID NUMBER(14) NOT NULL
        , DLGN_TYP VARCHAR2(1)
        , VER_NBR NUMBER(8) default 0
        , DTYPE VARCHAR2(50)
        , GRP_ID VARCHAR2(40)
        , DLGN_GRP_ID VARCHAR2(40)
        , RQST_LBL VARCHAR2(255)
    

)
/

ALTER TABLE KREW_ACTN_ITM_T
    ADD CONSTRAINT KREW_ACTN_ITM_TP1
PRIMARY KEY (ACTN_ITM_ID)
/


CREATE INDEX KREW_ACTN_ITM_T1 
  ON KREW_ACTN_ITM_T 
  (PRNCPL_ID)
/
CREATE INDEX KREW_ACTN_ITM_TI2 
  ON KREW_ACTN_ITM_T 
  (DOC_HDR_ID)
/
CREATE INDEX KREW_ACTN_ITM_TI3 
  ON KREW_ACTN_ITM_T 
  (ACTN_RQST_ID)
/
CREATE INDEX KREW_ACTN_ITM_TI5 
  ON KREW_ACTN_ITM_T 
  (PRNCPL_ID, DLGN_TYP, DOC_HDR_ID)
/





-----------------------------------------------------------------------------
-- KREW_ACTN_RQST_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_ACTN_RQST_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_ACTN_RQST_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_ACTN_RQST_T
(
      ACTN_RQST_ID NUMBER(14)
        , PARNT_ID NUMBER(14)
        , ACTN_RQST_CD CHAR(1) NOT NULL
        , DOC_HDR_ID NUMBER(14) NOT NULL
        , RULE_ID NUMBER(19)
        , STAT_CD CHAR(1) NOT NULL
        , RSP_ID NUMBER(14) NOT NULL
        , PRNCPL_ID VARCHAR2(40)
        , ROLE_NM VARCHAR2(2000)
        , QUAL_ROLE_NM VARCHAR2(2000)
        , QUAL_ROLE_NM_LBL_TXT VARCHAR2(2000)
        , RECIP_TYP_CD CHAR(1)
        , PRIO_NBR NUMBER(8) NOT NULL
        , RTE_TYP_NM VARCHAR2(255)
        , RTE_LVL_NBR NUMBER(8) NOT NULL
        , RTE_NODE_INSTN_ID NUMBER(19)
        , ACTN_TKN_ID NUMBER(14)
        , DOC_VER_NBR NUMBER(8) NOT NULL
        , CRTE_DT DATE NOT NULL
        , RSP_DESC_TXT VARCHAR2(200)
        , FRC_ACTN NUMBER(1) default 0
        , ACTN_RQST_ANNOTN_TXT VARCHAR2(2000)
        , DLGN_TYP CHAR(1)
        , APPR_PLCY CHAR(1)
        , CUR_IND NUMBER(1) default 1
        , VER_NBR NUMBER(8) default 0
        , GRP_ID VARCHAR2(40)
        , RQST_LBL VARCHAR2(255)
    

)
/

ALTER TABLE KREW_ACTN_RQST_T
    ADD CONSTRAINT KREW_ACTN_RQST_TP1
PRIMARY KEY (ACTN_RQST_ID)
/


CREATE INDEX KREW_ACTN_RQST_T11 
  ON KREW_ACTN_RQST_T 
  (DOC_HDR_ID)
/
CREATE INDEX KREW_ACTN_RQST_T12 
  ON KREW_ACTN_RQST_T 
  (PRNCPL_ID)
/
CREATE INDEX KREW_ACTN_RQST_T13 
  ON KREW_ACTN_RQST_T 
  (ACTN_TKN_ID)
/
CREATE INDEX KREW_ACTN_RQST_T14 
  ON KREW_ACTN_RQST_T 
  (PARNT_ID)
/
CREATE INDEX KREW_ACTN_RQST_T15 
  ON KREW_ACTN_RQST_T 
  (RSP_ID)
/
CREATE INDEX KREW_ACTN_RQST_T16 
  ON KREW_ACTN_RQST_T 
  (STAT_CD, RSP_ID)
/
CREATE INDEX KREW_ACTN_RQST_T17 
  ON KREW_ACTN_RQST_T 
  (RTE_NODE_INSTN_ID)
/
CREATE INDEX KREW_ACTN_RQST_T19 
  ON KREW_ACTN_RQST_T 
  (STAT_CD, DOC_HDR_ID)
/





-----------------------------------------------------------------------------
-- KREW_ACTN_TKN_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_ACTN_TKN_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_ACTN_TKN_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_ACTN_TKN_T
(
      ACTN_TKN_ID NUMBER(14)
        , DOC_HDR_ID NUMBER(14) NOT NULL
        , PRNCPL_ID VARCHAR2(40) NOT NULL
        , DLGTR_PRNCPL_ID VARCHAR2(40)
        , ACTN_CD CHAR(1) NOT NULL
        , ACTN_DT DATE NOT NULL
        , DOC_VER_NBR NUMBER(8) NOT NULL
        , ANNOTN VARCHAR2(2000)
        , CUR_IND NUMBER(1) default 1
        , VER_NBR NUMBER(8) default 0
        , DLGTR_GRP_ID VARCHAR2(40)
    

)
/

ALTER TABLE KREW_ACTN_TKN_T
    ADD CONSTRAINT KREW_ACTN_TKN_TP1
PRIMARY KEY (ACTN_TKN_ID)
/


CREATE INDEX KREW_ACTN_TKN_TI1 
  ON KREW_ACTN_TKN_T 
  (DOC_HDR_ID, PRNCPL_ID)
/
CREATE INDEX KREW_ACTN_TKN_TI2 
  ON KREW_ACTN_TKN_T 
  (DOC_HDR_ID, PRNCPL_ID, ACTN_CD)
/
CREATE INDEX KREW_ACTN_TKN_TI3 
  ON KREW_ACTN_TKN_T 
  (PRNCPL_ID)
/
CREATE INDEX KREW_ACTN_TKN_TI4 
  ON KREW_ACTN_TKN_T 
  (DLGTR_PRNCPL_ID)
/
CREATE INDEX KREW_ACTN_TKN_TI5 
  ON KREW_ACTN_TKN_T 
  (DOC_HDR_ID)
/





-----------------------------------------------------------------------------
-- KREW_APP_DOC_STAT_TRAN_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_APP_DOC_STAT_TRAN_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_APP_DOC_STAT_TRAN_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_APP_DOC_STAT_TRAN_T
(
      APP_DOC_STAT_TRAN_ID NUMBER(19)
        , DOC_HDR_ID NUMBER(14)
        , APP_DOC_STAT_FROM VARCHAR2(64)
        , APP_DOC_STAT_TO VARCHAR2(64)
        , STAT_TRANS_DATE DATE
        , VER_NBR NUMBER(8) default 0
        , OBJ_ID VARCHAR2(36) NOT NULL
    
    , CONSTRAINT KREW_APP_DOC_STAT_TRAN_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KREW_APP_DOC_STAT_TRAN_T
    ADD CONSTRAINT KREW_APP_DOC_STAT_TRAN_TP1
PRIMARY KEY (APP_DOC_STAT_TRAN_ID)
/


CREATE INDEX KREW_APP_DOC_STAT_TI1 
  ON KREW_APP_DOC_STAT_TRAN_T 
  (DOC_HDR_ID, STAT_TRANS_DATE)
/
CREATE INDEX KREW_APP_DOC_STAT_TI2 
  ON KREW_APP_DOC_STAT_TRAN_T 
  (DOC_HDR_ID, APP_DOC_STAT_FROM)
/
CREATE INDEX KREW_APP_DOC_STAT_TI3 
  ON KREW_APP_DOC_STAT_TRAN_T 
  (DOC_HDR_ID, APP_DOC_STAT_TO)
/





-----------------------------------------------------------------------------
-- KREW_ATT_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_ATT_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_ATT_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_ATT_T
(
      ATTACHMENT_ID NUMBER(19)
        , NTE_ID NUMBER(19) NOT NULL
        , FILE_NM VARCHAR2(255) NOT NULL
        , FILE_LOC VARCHAR2(255) NOT NULL
        , MIME_TYP VARCHAR2(255) NOT NULL
        , VER_NBR NUMBER(8) default 0
    

)
/

ALTER TABLE KREW_ATT_T
    ADD CONSTRAINT KREW_ATT_TP1
PRIMARY KEY (ATTACHMENT_ID)
/







-----------------------------------------------------------------------------
-- KREW_DLGN_RSP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_DLGN_RSP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_DLGN_RSP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_DLGN_RSP_T
(
      DLGN_RULE_ID NUMBER(19)
        , RSP_ID NUMBER(19) NOT NULL
        , DLGN_RULE_BASE_VAL_ID NUMBER(19) NOT NULL
        , DLGN_TYP VARCHAR2(20) NOT NULL
        , VER_NBR NUMBER(8) default 0
        , OBJ_ID VARCHAR2(36) NOT NULL
    
    , CONSTRAINT KREW_DLGN_RSP_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KREW_DLGN_RSP_T
    ADD CONSTRAINT KREW_DLGN_RSP_TP1
PRIMARY KEY (DLGN_RULE_ID)
/







-----------------------------------------------------------------------------
-- KREW_DOC_HDR_CNTNT_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_DOC_HDR_CNTNT_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_DOC_HDR_CNTNT_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_DOC_HDR_CNTNT_T
(
      DOC_HDR_ID NUMBER(14)
        , DOC_CNTNT_TXT CLOB
    

)
/

ALTER TABLE KREW_DOC_HDR_CNTNT_T
    ADD CONSTRAINT KREW_DOC_HDR_CNTNT_TP1
PRIMARY KEY (DOC_HDR_ID)
/







-----------------------------------------------------------------------------
-- KREW_DOC_HDR_EXT_DT_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_DOC_HDR_EXT_DT_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_DOC_HDR_EXT_DT_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_DOC_HDR_EXT_DT_T
(
      DOC_HDR_EXT_DT_ID NUMBER(19)
        , DOC_HDR_ID NUMBER(14) NOT NULL
        , KEY_CD VARCHAR2(256) NOT NULL
        , VAL DATE
    

)
/

ALTER TABLE KREW_DOC_HDR_EXT_DT_T
    ADD CONSTRAINT KREW_DOC_HDR_EXT_DT_TP1
PRIMARY KEY (DOC_HDR_EXT_DT_ID)
/


CREATE INDEX KREW_DOC_HDR_EXT_DT_TI1 
  ON KREW_DOC_HDR_EXT_DT_T 
  (KEY_CD, VAL)
/
CREATE INDEX KREW_DOC_HDR_EXT_DT_TI2 
  ON KREW_DOC_HDR_EXT_DT_T 
  (DOC_HDR_ID)
/
CREATE INDEX KREW_DOC_HDR_EXT_DT_TI3 
  ON KREW_DOC_HDR_EXT_DT_T 
  (VAL)
/





-----------------------------------------------------------------------------
-- KREW_DOC_HDR_EXT_FLT_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_DOC_HDR_EXT_FLT_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_DOC_HDR_EXT_FLT_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_DOC_HDR_EXT_FLT_T
(
      DOC_HDR_EXT_FLT_ID NUMBER(19)
        , DOC_HDR_ID NUMBER(14) NOT NULL
        , KEY_CD VARCHAR2(256) NOT NULL
        , VAL NUMBER(30,15)
    

)
/

ALTER TABLE KREW_DOC_HDR_EXT_FLT_T
    ADD CONSTRAINT KREW_DOC_HDR_EXT_FLT_TP1
PRIMARY KEY (DOC_HDR_EXT_FLT_ID)
/


CREATE INDEX KREW_DOC_HDR_EXT_FLT_TI1 
  ON KREW_DOC_HDR_EXT_FLT_T 
  (KEY_CD, VAL)
/
CREATE INDEX KREW_DOC_HDR_EXT_FLT_TI2 
  ON KREW_DOC_HDR_EXT_FLT_T 
  (DOC_HDR_ID)
/
CREATE INDEX KREW_DOC_HDR_EXT_FLT_TI3 
  ON KREW_DOC_HDR_EXT_FLT_T 
  (VAL)
/





-----------------------------------------------------------------------------
-- KREW_DOC_HDR_EXT_LONG_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_DOC_HDR_EXT_LONG_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_DOC_HDR_EXT_LONG_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_DOC_HDR_EXT_LONG_T
(
      DOC_HDR_EXT_LONG_ID NUMBER(19)
        , DOC_HDR_ID NUMBER(14) NOT NULL
        , KEY_CD VARCHAR2(256) NOT NULL
        , VAL NUMBER(22)
    

)
/

ALTER TABLE KREW_DOC_HDR_EXT_LONG_T
    ADD CONSTRAINT KREW_DOC_HDR_EXT_LONG_TP1
PRIMARY KEY (DOC_HDR_EXT_LONG_ID)
/


CREATE INDEX KREW_DOC_HDR_EXT_LONG_TI1 
  ON KREW_DOC_HDR_EXT_LONG_T 
  (KEY_CD, VAL)
/
CREATE INDEX KREW_DOC_HDR_EXT_LONG_TI2 
  ON KREW_DOC_HDR_EXT_LONG_T 
  (DOC_HDR_ID)
/
CREATE INDEX KREW_DOC_HDR_EXT_LONG_TI3 
  ON KREW_DOC_HDR_EXT_LONG_T 
  (VAL)
/





-----------------------------------------------------------------------------
-- KREW_DOC_HDR_EXT_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_DOC_HDR_EXT_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_DOC_HDR_EXT_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_DOC_HDR_EXT_T
(
      DOC_HDR_EXT_ID NUMBER(19)
        , DOC_HDR_ID NUMBER(14) NOT NULL
        , KEY_CD VARCHAR2(256) NOT NULL
        , VAL VARCHAR2(2000)
    

)
/

ALTER TABLE KREW_DOC_HDR_EXT_T
    ADD CONSTRAINT KREW_DOC_HDR_EXT_TP1
PRIMARY KEY (DOC_HDR_EXT_ID)
/


CREATE INDEX KREW_DOC_HDR_EXT_TI1 
  ON KREW_DOC_HDR_EXT_T 
  (KEY_CD, VAL)
/
CREATE INDEX KREW_DOC_HDR_EXT_TI2 
  ON KREW_DOC_HDR_EXT_T 
  (DOC_HDR_ID)
/
CREATE INDEX KREW_DOC_HDR_EXT_TI3 
  ON KREW_DOC_HDR_EXT_T 
  (VAL)
/





-----------------------------------------------------------------------------
-- KREW_DOC_HDR_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_DOC_HDR_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_DOC_HDR_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_DOC_HDR_T
(
      DOC_HDR_ID NUMBER(14)
        , DOC_TYP_ID NUMBER(19)
        , DOC_HDR_STAT_CD CHAR(1) NOT NULL
        , RTE_LVL NUMBER(8) NOT NULL
        , STAT_MDFN_DT DATE NOT NULL
        , CRTE_DT DATE NOT NULL
        , APRV_DT DATE
        , FNL_DT DATE
        , RTE_STAT_MDFN_DT DATE
        , RTE_LVL_MDFN_DT DATE
        , TTL VARCHAR2(255)
        , APP_DOC_ID VARCHAR2(255)
        , DOC_VER_NBR NUMBER(8) NOT NULL
        , INITR_PRNCPL_ID VARCHAR2(40) NOT NULL
        , VER_NBR NUMBER(8) default 0
        , RTE_PRNCPL_ID VARCHAR2(40)
        , DTYPE VARCHAR2(50)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , APP_DOC_STAT VARCHAR2(64)
        , APP_DOC_STAT_MDFN_DT DATE
    
    , CONSTRAINT KREW_DOC_HDR_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KREW_DOC_HDR_T
    ADD CONSTRAINT KREW_DOC_HDR_TP1
PRIMARY KEY (DOC_HDR_ID)
/


CREATE INDEX KREW_DOC_HDR_T10 
  ON KREW_DOC_HDR_T 
  (APP_DOC_STAT)
/
CREATE INDEX KREW_DOC_HDR_T12 
  ON KREW_DOC_HDR_T 
  (APP_DOC_STAT_MDFN_DT)
/
CREATE INDEX KREW_DOC_HDR_TI1 
  ON KREW_DOC_HDR_T 
  (DOC_TYP_ID)
/
CREATE INDEX KREW_DOC_HDR_TI2 
  ON KREW_DOC_HDR_T 
  (INITR_PRNCPL_ID)
/
CREATE INDEX KREW_DOC_HDR_TI3 
  ON KREW_DOC_HDR_T 
  (DOC_HDR_STAT_CD)
/
CREATE INDEX KREW_DOC_HDR_TI4 
  ON KREW_DOC_HDR_T 
  (TTL)
/
CREATE INDEX KREW_DOC_HDR_TI5 
  ON KREW_DOC_HDR_T 
  (CRTE_DT)
/
CREATE INDEX KREW_DOC_HDR_TI6 
  ON KREW_DOC_HDR_T 
  (RTE_STAT_MDFN_DT)
/
CREATE INDEX KREW_DOC_HDR_TI7 
  ON KREW_DOC_HDR_T 
  (APRV_DT)
/
CREATE INDEX KREW_DOC_HDR_TI8 
  ON KREW_DOC_HDR_T 
  (FNL_DT)
/
CREATE INDEX KREW_DOC_HDR_TI9 
  ON KREW_DOC_HDR_T 
  (APP_DOC_ID)
/





-----------------------------------------------------------------------------
-- KREW_DOC_LNK_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_DOC_LNK_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_DOC_LNK_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_DOC_LNK_T
(
      DOC_LNK_ID NUMBER(19)
        , ORGN_DOC_ID NUMBER(14) NOT NULL
        , DEST_DOC_ID NUMBER(14) NOT NULL
    

)
/

ALTER TABLE KREW_DOC_LNK_T
    ADD CONSTRAINT KREW_DOC_LNK_TP1
PRIMARY KEY (DOC_LNK_ID)
/


CREATE INDEX KREW_DOC_LNK_TI1 
  ON KREW_DOC_LNK_T 
  (ORGN_DOC_ID)
/





-----------------------------------------------------------------------------
-- KREW_DOC_NTE_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_DOC_NTE_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_DOC_NTE_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_DOC_NTE_T
(
      DOC_NTE_ID NUMBER(19)
        , DOC_HDR_ID NUMBER(14) NOT NULL
        , AUTH_PRNCPL_ID VARCHAR2(40) NOT NULL
        , CRT_DT DATE NOT NULL
        , TXT VARCHAR2(4000)
        , VER_NBR NUMBER(8) default 0
    

)
/

ALTER TABLE KREW_DOC_NTE_T
    ADD CONSTRAINT KREW_DOC_NTE_TP1
PRIMARY KEY (DOC_NTE_ID)
/


CREATE INDEX KREW_DOC_NTE_TI1 
  ON KREW_DOC_NTE_T 
  (DOC_HDR_ID)
/





-----------------------------------------------------------------------------
-- KREW_DOC_TYP_APP_DOC_STAT_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_DOC_TYP_APP_DOC_STAT_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_DOC_TYP_APP_DOC_STAT_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_DOC_TYP_APP_DOC_STAT_T
(
      DOC_TYP_ID NUMBER(19)
        , DOC_STAT_NM VARCHAR2(64)
        , VER_NBR NUMBER(8) default 0
        , OBJ_ID VARCHAR2(36) NOT NULL
    
    , CONSTRAINT KREW_DOC_TYP_APP_DOC_STAT_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KREW_DOC_TYP_APP_DOC_STAT_T
    ADD CONSTRAINT KREW_DOC_TYP_APP_DOC_STAT_TP1
PRIMARY KEY (DOC_TYP_ID,DOC_STAT_NM)
/


CREATE INDEX KREW_DOC_TYP_APP_DOC_STAT_T1 
  ON KREW_DOC_TYP_APP_DOC_STAT_T 
  (DOC_TYP_ID)
/





-----------------------------------------------------------------------------
-- KREW_DOC_TYP_ATTR_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_DOC_TYP_ATTR_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_DOC_TYP_ATTR_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_DOC_TYP_ATTR_T
(
      DOC_TYP_ATTRIB_ID NUMBER(19)
        , DOC_TYP_ID NUMBER(19) NOT NULL
        , RULE_ATTR_ID NUMBER(19) NOT NULL
        , ORD_INDX NUMBER(4) default 0
    

)
/

ALTER TABLE KREW_DOC_TYP_ATTR_T
    ADD CONSTRAINT KREW_DOC_TYP_ATTR_TP1
PRIMARY KEY (DOC_TYP_ATTRIB_ID)
/


CREATE INDEX KREW_DOC_TYP_ATTR_TI1 
  ON KREW_DOC_TYP_ATTR_T 
  (DOC_TYP_ID)
/





-----------------------------------------------------------------------------
-- KREW_DOC_TYP_PLCY_RELN_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_DOC_TYP_PLCY_RELN_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_DOC_TYP_PLCY_RELN_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_DOC_TYP_PLCY_RELN_T
(
      DOC_TYP_ID NUMBER(19)
        , DOC_PLCY_NM VARCHAR2(255)
        , PLCY_NM NUMBER(1) NOT NULL
        , VER_NBR NUMBER(8) default 0
        , OBJ_ID VARCHAR2(36) NOT NULL
        , PLCY_VAL VARCHAR2(64)
    
    , CONSTRAINT KREW_DOC_TYP_PLCY_RELN_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KREW_DOC_TYP_PLCY_RELN_T
    ADD CONSTRAINT KREW_DOC_TYP_PLCY_RELN_TP1
PRIMARY KEY (DOC_TYP_ID,DOC_PLCY_NM)
/







-----------------------------------------------------------------------------
-- KREW_DOC_TYP_PROC_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_DOC_TYP_PROC_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_DOC_TYP_PROC_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_DOC_TYP_PROC_T
(
      DOC_TYP_PROC_ID NUMBER(19)
        , DOC_TYP_ID NUMBER(19) NOT NULL
        , INIT_RTE_NODE_ID NUMBER(22)
        , NM VARCHAR2(255) NOT NULL
        , INIT_IND NUMBER(1) default 0 NOT NULL
        , VER_NBR NUMBER(8) default 0
    

)
/

ALTER TABLE KREW_DOC_TYP_PROC_T
    ADD CONSTRAINT KREW_DOC_TYP_PROC_TP1
PRIMARY KEY (DOC_TYP_PROC_ID)
/


CREATE INDEX KREW_DOC_TYP_PROC_TI1 
  ON KREW_DOC_TYP_PROC_T 
  (DOC_TYP_ID)
/
CREATE INDEX KREW_DOC_TYP_PROC_TI2 
  ON KREW_DOC_TYP_PROC_T 
  (INIT_RTE_NODE_ID)
/
CREATE INDEX KREW_DOC_TYP_PROC_TI3 
  ON KREW_DOC_TYP_PROC_T 
  (NM)
/





-----------------------------------------------------------------------------
-- KREW_DOC_TYP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_DOC_TYP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_DOC_TYP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_DOC_TYP_T
(
      DOC_TYP_ID NUMBER(19)
        , PARNT_ID NUMBER(19)
        , DOC_TYP_NM VARCHAR2(64)
        , DOC_TYP_VER_NBR NUMBER(10) default 0
        , ACTV_IND NUMBER(1)
        , CUR_IND NUMBER(1)
        , LBL VARCHAR2(128)
        , PREV_DOC_TYP_VER_NBR NUMBER(19)
        , DOC_HDR_ID NUMBER(14)
        , DOC_TYP_DESC VARCHAR2(4000)
        , DOC_HDLR_URL VARCHAR2(255)
        , POST_PRCSR VARCHAR2(255)
        , JNDI_URL VARCHAR2(255)
        , BLNKT_APPR_PLCY VARCHAR2(10)
        , ADV_DOC_SRCH_URL VARCHAR2(255)
        , CSTM_ACTN_LIST_ATTRIB_CLS_NM VARCHAR2(255)
        , CSTM_ACTN_EMAIL_ATTRIB_CLS_NM VARCHAR2(255)
        , CSTM_DOC_NTE_ATTRIB_CLS_NM VARCHAR2(255)
        , RTE_VER_NBR VARCHAR2(2) default '1'
        , NOTIFY_ADDR VARCHAR2(255)
        , SVC_NMSPC VARCHAR2(255)
        , EMAIL_XSL VARCHAR2(255)
        , SEC_XML CLOB
        , VER_NBR NUMBER(8) default 0
        , BLNKT_APPR_GRP_ID VARCHAR2(40)
        , RPT_GRP_ID VARCHAR2(40)
        , GRP_ID VARCHAR2(40)
        , HELP_DEF_URL VARCHAR2(4000)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , DOC_SEARCH_HELP_URL VARCHAR2(4000)
    
    , CONSTRAINT KREW_DOC_TYP_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KREW_DOC_TYP_TI1 UNIQUE (DOC_TYP_NM, DOC_TYP_VER_NBR)

)
/

ALTER TABLE KREW_DOC_TYP_T
    ADD CONSTRAINT KREW_DOC_TYP_TP1
PRIMARY KEY (DOC_TYP_ID)
/


CREATE INDEX KREW_DOC_TYP_TI2 
  ON KREW_DOC_TYP_T 
  (PARNT_ID)
/
CREATE INDEX KREW_DOC_TYP_TI3 
  ON KREW_DOC_TYP_T 
  (DOC_TYP_ID, PARNT_ID)
/
CREATE INDEX KREW_DOC_TYP_TI4 
  ON KREW_DOC_TYP_T 
  (PREV_DOC_TYP_VER_NBR)
/
CREATE INDEX KREW_DOC_TYP_TI5 
  ON KREW_DOC_TYP_T 
  (CUR_IND)
/
CREATE INDEX KREW_DOC_TYP_TI6 
  ON KREW_DOC_TYP_T 
  (DOC_TYP_NM)
/





-----------------------------------------------------------------------------
-- KREW_EDL_ASSCTN_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_EDL_ASSCTN_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_EDL_ASSCTN_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_EDL_ASSCTN_T
(
      EDOCLT_ASSOC_ID NUMBER(19)
        , DOC_TYP_NM VARCHAR2(64) NOT NULL
        , EDL_DEF_NM VARCHAR2(200)
        , STYLE_NM VARCHAR2(200)
        , ACTV_IND NUMBER(1) NOT NULL
        , VER_NBR NUMBER(8) default 0
        , OBJ_ID VARCHAR2(36) NOT NULL
    
    , CONSTRAINT KREW_EDL_ASSCTN_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KREW_EDL_ASSCTN_T
    ADD CONSTRAINT KREW_EDL_ASSCTN_TP1
PRIMARY KEY (EDOCLT_ASSOC_ID)
/







-----------------------------------------------------------------------------
-- KREW_EDL_DEF_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_EDL_DEF_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_EDL_DEF_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_EDL_DEF_T
(
      EDOCLT_DEF_ID NUMBER(19)
        , NM VARCHAR2(200) NOT NULL
        , XML CLOB NOT NULL
        , ACTV_IND NUMBER(1) NOT NULL
        , VER_NBR NUMBER(8) default 0
        , OBJ_ID VARCHAR2(36) NOT NULL
    
    , CONSTRAINT KREW_EDL_DEF_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KREW_EDL_DEF_T
    ADD CONSTRAINT KREW_EDL_DEF_TP1
PRIMARY KEY (EDOCLT_DEF_ID)
/







-----------------------------------------------------------------------------
-- KREW_EDL_DMP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_EDL_DMP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_EDL_DMP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_EDL_DMP_T
(
      DOC_HDR_ID NUMBER(14)
        , DOC_TYP_NM VARCHAR2(64) NOT NULL
        , DOC_HDR_STAT_CD CHAR(1) NOT NULL
        , DOC_HDR_MDFN_DT DATE NOT NULL
        , DOC_HDR_CRTE_DT DATE NOT NULL
        , DOC_HDR_TTL VARCHAR2(255)
        , DOC_HDR_INITR_PRNCPL_ID VARCHAR2(40) NOT NULL
        , CRNT_NODE_NM VARCHAR2(30) NOT NULL
        , VER_NBR NUMBER(8) default 0
    

)
/

ALTER TABLE KREW_EDL_DMP_T
    ADD CONSTRAINT KREW_EDL_DMP_TP1
PRIMARY KEY (DOC_HDR_ID)
/


CREATE INDEX KREW_EDL_DMP_TI1 
  ON KREW_EDL_DMP_T 
  (DOC_TYP_NM, DOC_HDR_ID)
/





-----------------------------------------------------------------------------
-- KREW_EDL_FLD_DMP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_EDL_FLD_DMP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_EDL_FLD_DMP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_EDL_FLD_DMP_T
(
      EDL_FIELD_DMP_ID NUMBER(14)
        , DOC_HDR_ID NUMBER(14) NOT NULL
        , FLD_NM VARCHAR2(255) NOT NULL
        , FLD_VAL VARCHAR2(4000)
        , VER_NBR NUMBER(8) default 0
    

)
/

ALTER TABLE KREW_EDL_FLD_DMP_T
    ADD CONSTRAINT KREW_EDL_FLD_DMP_TP1
PRIMARY KEY (EDL_FIELD_DMP_ID)
/







-----------------------------------------------------------------------------
-- KREW_HLP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_HLP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_HLP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_HLP_T
(
      HLP_ID NUMBER(19)
        , NM VARCHAR2(500) NOT NULL
        , KEY_CD VARCHAR2(500) NOT NULL
        , HLP_TXT VARCHAR2(4000) NOT NULL
        , VER_NBR NUMBER(8) default 0
    

)
/

ALTER TABLE KREW_HLP_T
    ADD CONSTRAINT KREW_HLP_TP1
PRIMARY KEY (HLP_ID)
/


CREATE INDEX KREW_HLP_TI1 
  ON KREW_HLP_T 
  (KEY_CD)
/





-----------------------------------------------------------------------------
-- KREW_INIT_RTE_NODE_INSTN_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_INIT_RTE_NODE_INSTN_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_INIT_RTE_NODE_INSTN_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_INIT_RTE_NODE_INSTN_T
(
      DOC_HDR_ID NUMBER(19)
        , RTE_NODE_INSTN_ID NUMBER(19)
    

)
/

ALTER TABLE KREW_INIT_RTE_NODE_INSTN_T
    ADD CONSTRAINT KREW_INIT_RTE_NODE_INSTN_TP1
PRIMARY KEY (DOC_HDR_ID,RTE_NODE_INSTN_ID)
/


CREATE INDEX KREW_INIT_RTE_NODE_INSTN_TI1 
  ON KREW_INIT_RTE_NODE_INSTN_T 
  (DOC_HDR_ID)
/
CREATE INDEX KREW_INIT_RTE_NODE_INSTN_TI2 
  ON KREW_INIT_RTE_NODE_INSTN_T 
  (RTE_NODE_INSTN_ID)
/





-----------------------------------------------------------------------------
-- KREW_OUT_BOX_ITM_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_OUT_BOX_ITM_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_OUT_BOX_ITM_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_OUT_BOX_ITM_T
(
      ACTN_ITM_ID NUMBER(14)
        , PRNCPL_ID VARCHAR2(40) NOT NULL
        , ASND_DT DATE NOT NULL
        , RQST_CD CHAR(1) NOT NULL
        , ACTN_RQST_ID NUMBER(14) NOT NULL
        , DOC_HDR_ID NUMBER(14) NOT NULL
        , ROLE_NM VARCHAR2(2000)
        , DLGN_PRNCPL_ID VARCHAR2(40)
        , DOC_HDR_TTL VARCHAR2(255)
        , DOC_TYP_LBL VARCHAR2(128) NOT NULL
        , DOC_HDLR_URL VARCHAR2(255) NOT NULL
        , DOC_TYP_NM VARCHAR2(64) NOT NULL
        , RSP_ID NUMBER(14) NOT NULL
        , DLGN_TYP VARCHAR2(1)
        , VER_NBR NUMBER(8) default 0
        , GRP_ID VARCHAR2(40)
        , DLGN_GRP_ID VARCHAR2(40)
        , RQST_LBL VARCHAR2(255)
    

)
/

ALTER TABLE KREW_OUT_BOX_ITM_T
    ADD CONSTRAINT KREW_OUT_BOX_ITM_TP1
PRIMARY KEY (ACTN_ITM_ID)
/


CREATE INDEX KREW_OUT_BOX_ITM_TI1 
  ON KREW_OUT_BOX_ITM_T 
  (PRNCPL_ID)
/
CREATE INDEX KREW_OUT_BOX_ITM_TI2 
  ON KREW_OUT_BOX_ITM_T 
  (DOC_HDR_ID)
/
CREATE INDEX KREW_OUT_BOX_ITM_TI3 
  ON KREW_OUT_BOX_ITM_T 
  (ACTN_RQST_ID)
/





-----------------------------------------------------------------------------
-- KREW_RIA_DOCTYPE_MAP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RIA_DOCTYPE_MAP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RIA_DOCTYPE_MAP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RIA_DOCTYPE_MAP_T
(
      ID NUMBER(22)
        , RIA_DOC_TYPE_NAME VARCHAR2(100)
        , UPDATED_AT DATE
        , RIA_URL VARCHAR2(255)
        , HELP_URL VARCHAR2(255)
        , EDITABLE CHAR(1)
        , INIT_GROUPS VARCHAR2(255)
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
    

)
/

ALTER TABLE KREW_RIA_DOCTYPE_MAP_T
    ADD CONSTRAINT KREW_RIA_DOCTYPE_MAP_TP1
PRIMARY KEY (ID)
/







-----------------------------------------------------------------------------
-- KREW_RIA_DOC_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RIA_DOC_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RIA_DOC_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RIA_DOC_T
(
      RIA_ID NUMBER(22)
        , XML_CONTENT VARCHAR2(4000)
        , RIA_DOC_TYPE_NAME VARCHAR2(100)
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
    

)
/

ALTER TABLE KREW_RIA_DOC_T
    ADD CONSTRAINT KREW_RIA_DOC_TP1
PRIMARY KEY (RIA_ID)
/







-----------------------------------------------------------------------------
-- KREW_RMV_RPLC_DOC_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RMV_RPLC_DOC_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RMV_RPLC_DOC_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RMV_RPLC_DOC_T
(
      DOC_HDR_ID NUMBER(14)
        , OPRN CHAR(1) NOT NULL
        , PRNCPL_ID VARCHAR2(40) NOT NULL
        , RPLC_PRNCPL_ID VARCHAR2(40)
        , VER_NBR NUMBER(8) default 0
    

)
/

ALTER TABLE KREW_RMV_RPLC_DOC_T
    ADD CONSTRAINT KREW_RMV_RPLC_DOC_TP1
PRIMARY KEY (DOC_HDR_ID)
/







-----------------------------------------------------------------------------
-- KREW_RMV_RPLC_GRP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RMV_RPLC_GRP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RMV_RPLC_GRP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RMV_RPLC_GRP_T
(
      DOC_HDR_ID NUMBER(14)
        , GRP_ID NUMBER(14)
    

)
/

ALTER TABLE KREW_RMV_RPLC_GRP_T
    ADD CONSTRAINT KREW_RMV_RPLC_GRP_TP1
PRIMARY KEY (DOC_HDR_ID,GRP_ID)
/







-----------------------------------------------------------------------------
-- KREW_RMV_RPLC_RULE_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RMV_RPLC_RULE_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RMV_RPLC_RULE_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RMV_RPLC_RULE_T
(
      DOC_HDR_ID NUMBER(14)
        , RULE_ID NUMBER(19)
    

)
/

ALTER TABLE KREW_RMV_RPLC_RULE_T
    ADD CONSTRAINT KREW_RMV_RPLC_RULE_TP1
PRIMARY KEY (DOC_HDR_ID,RULE_ID)
/







-----------------------------------------------------------------------------
-- KREW_RTE_BRCH_PROTO_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RTE_BRCH_PROTO_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RTE_BRCH_PROTO_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RTE_BRCH_PROTO_T
(
      RTE_BRCH_PROTO_ID NUMBER(19)
        , BRCH_NM VARCHAR2(255) NOT NULL
        , VER_NBR NUMBER(8) default 0
    

)
/

ALTER TABLE KREW_RTE_BRCH_PROTO_T
    ADD CONSTRAINT KREW_RTE_BRCH_PROTO_TP1
PRIMARY KEY (RTE_BRCH_PROTO_ID)
/


CREATE INDEX KREW_RTE_BRCH_PROTO_TI1 
  ON KREW_RTE_BRCH_PROTO_T 
  (BRCH_NM)
/





-----------------------------------------------------------------------------
-- KREW_RTE_BRCH_ST_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RTE_BRCH_ST_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RTE_BRCH_ST_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RTE_BRCH_ST_T
(
      RTE_BRCH_ST_ID NUMBER(19)
        , RTE_BRCH_ID NUMBER(19) NOT NULL
        , KEY_CD VARCHAR2(255) NOT NULL
        , VAL VARCHAR2(2000)
        , VER_NBR NUMBER(8) default 0
    

)
/

ALTER TABLE KREW_RTE_BRCH_ST_T
    ADD CONSTRAINT KREW_RTE_BRCH_ST_TP1
PRIMARY KEY (RTE_BRCH_ST_ID)
/


CREATE INDEX KREW_RTE_BRCH_ST_TI1 
  ON KREW_RTE_BRCH_ST_T 
  (RTE_BRCH_ID, KEY_CD)
/
CREATE INDEX KREW_RTE_BRCH_ST_TI2 
  ON KREW_RTE_BRCH_ST_T 
  (RTE_BRCH_ID)
/
CREATE INDEX KREW_RTE_BRCH_ST_TI3 
  ON KREW_RTE_BRCH_ST_T 
  (KEY_CD, VAL)
/





-----------------------------------------------------------------------------
-- KREW_RTE_BRCH_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RTE_BRCH_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RTE_BRCH_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RTE_BRCH_T
(
      RTE_BRCH_ID NUMBER(19)
        , NM VARCHAR2(255) NOT NULL
        , PARNT_ID NUMBER(19)
        , INIT_RTE_NODE_INSTN_ID NUMBER(19)
        , SPLT_RTE_NODE_INSTN_ID NUMBER(19)
        , JOIN_RTE_NODE_INSTN_ID NUMBER(19)
        , VER_NBR NUMBER(8) default 0
    

)
/

ALTER TABLE KREW_RTE_BRCH_T
    ADD CONSTRAINT KREW_RTE_BRCH_TP1
PRIMARY KEY (RTE_BRCH_ID)
/


CREATE INDEX KREW_RTE_BRCH_TI1 
  ON KREW_RTE_BRCH_T 
  (NM)
/
CREATE INDEX KREW_RTE_BRCH_TI2 
  ON KREW_RTE_BRCH_T 
  (PARNT_ID)
/
CREATE INDEX KREW_RTE_BRCH_TI3 
  ON KREW_RTE_BRCH_T 
  (INIT_RTE_NODE_INSTN_ID)
/
CREATE INDEX KREW_RTE_BRCH_TI4 
  ON KREW_RTE_BRCH_T 
  (SPLT_RTE_NODE_INSTN_ID)
/
CREATE INDEX KREW_RTE_BRCH_TI5 
  ON KREW_RTE_BRCH_T 
  (JOIN_RTE_NODE_INSTN_ID)
/





-----------------------------------------------------------------------------
-- KREW_RTE_NODE_CFG_PARM_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RTE_NODE_CFG_PARM_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RTE_NODE_CFG_PARM_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RTE_NODE_CFG_PARM_T
(
      RTE_NODE_CFG_PARM_ID NUMBER(19)
        , RTE_NODE_ID NUMBER(19) NOT NULL
        , KEY_CD VARCHAR2(255) NOT NULL
        , VAL VARCHAR2(4000)
    

)
/

ALTER TABLE KREW_RTE_NODE_CFG_PARM_T
    ADD CONSTRAINT KREW_RTE_NODE_CFG_PARM_TP1
PRIMARY KEY (RTE_NODE_CFG_PARM_ID)
/


CREATE INDEX KREW_RTE_NODE_CFG_PARM_TI1 
  ON KREW_RTE_NODE_CFG_PARM_T 
  (RTE_NODE_ID)
/





-----------------------------------------------------------------------------
-- KREW_RTE_NODE_INSTN_LNK_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RTE_NODE_INSTN_LNK_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RTE_NODE_INSTN_LNK_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RTE_NODE_INSTN_LNK_T
(
      FROM_RTE_NODE_INSTN_ID NUMBER(19)
        , TO_RTE_NODE_INSTN_ID NUMBER(19)
    

)
/

ALTER TABLE KREW_RTE_NODE_INSTN_LNK_T
    ADD CONSTRAINT KREW_RTE_NODE_INSTN_LNK_TP1
PRIMARY KEY (FROM_RTE_NODE_INSTN_ID,TO_RTE_NODE_INSTN_ID)
/


CREATE INDEX KREW_RTE_NODE_INSTN_LNK_TI1 
  ON KREW_RTE_NODE_INSTN_LNK_T 
  (FROM_RTE_NODE_INSTN_ID)
/
CREATE INDEX KREW_RTE_NODE_INSTN_LNK_TI2 
  ON KREW_RTE_NODE_INSTN_LNK_T 
  (TO_RTE_NODE_INSTN_ID)
/





-----------------------------------------------------------------------------
-- KREW_RTE_NODE_INSTN_ST_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RTE_NODE_INSTN_ST_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RTE_NODE_INSTN_ST_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RTE_NODE_INSTN_ST_T
(
      RTE_NODE_INSTN_ST_ID NUMBER(19)
        , RTE_NODE_INSTN_ID NUMBER(19) NOT NULL
        , KEY_CD VARCHAR2(255) NOT NULL
        , VAL VARCHAR2(2000)
        , VER_NBR NUMBER(8) default 0
    

)
/

ALTER TABLE KREW_RTE_NODE_INSTN_ST_T
    ADD CONSTRAINT KREW_RTE_NODE_INSTN_ST_TP1
PRIMARY KEY (RTE_NODE_INSTN_ST_ID)
/


CREATE INDEX KREW_RTE_NODE_INSTN_ST_TI1 
  ON KREW_RTE_NODE_INSTN_ST_T 
  (RTE_NODE_INSTN_ID, KEY_CD)
/
CREATE INDEX KREW_RTE_NODE_INSTN_ST_TI2 
  ON KREW_RTE_NODE_INSTN_ST_T 
  (RTE_NODE_INSTN_ID)
/
CREATE INDEX KREW_RTE_NODE_INSTN_ST_TI3 
  ON KREW_RTE_NODE_INSTN_ST_T 
  (KEY_CD, VAL)
/





-----------------------------------------------------------------------------
-- KREW_RTE_NODE_INSTN_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RTE_NODE_INSTN_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RTE_NODE_INSTN_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RTE_NODE_INSTN_T
(
      RTE_NODE_INSTN_ID NUMBER(19)
        , DOC_HDR_ID NUMBER(19) NOT NULL
        , RTE_NODE_ID NUMBER(19) NOT NULL
        , BRCH_ID NUMBER(19)
        , PROC_RTE_NODE_INSTN_ID NUMBER(19)
        , ACTV_IND NUMBER(1) default 0 NOT NULL
        , CMPLT_IND NUMBER(1) default 0 NOT NULL
        , INIT_IND NUMBER(1) default 0 NOT NULL
        , VER_NBR NUMBER(8) default 0
    

)
/

ALTER TABLE KREW_RTE_NODE_INSTN_T
    ADD CONSTRAINT KREW_RTE_NODE_INSTN_TP1
PRIMARY KEY (RTE_NODE_INSTN_ID)
/


CREATE INDEX KREW_RTE_NODE_INSTN_TI1 
  ON KREW_RTE_NODE_INSTN_T 
  (DOC_HDR_ID, ACTV_IND, CMPLT_IND)
/
CREATE INDEX KREW_RTE_NODE_INSTN_TI2 
  ON KREW_RTE_NODE_INSTN_T 
  (RTE_NODE_ID)
/
CREATE INDEX KREW_RTE_NODE_INSTN_TI3 
  ON KREW_RTE_NODE_INSTN_T 
  (BRCH_ID)
/
CREATE INDEX KREW_RTE_NODE_INSTN_TI4 
  ON KREW_RTE_NODE_INSTN_T 
  (PROC_RTE_NODE_INSTN_ID)
/





-----------------------------------------------------------------------------
-- KREW_RTE_NODE_LNK_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RTE_NODE_LNK_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RTE_NODE_LNK_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RTE_NODE_LNK_T
(
      FROM_RTE_NODE_ID NUMBER(19)
        , TO_RTE_NODE_ID NUMBER(19)
    

)
/

ALTER TABLE KREW_RTE_NODE_LNK_T
    ADD CONSTRAINT KREW_RTE_NODE_LNK_TP1
PRIMARY KEY (FROM_RTE_NODE_ID,TO_RTE_NODE_ID)
/


CREATE INDEX KREW_RTE_NODE_LNK_TI1 
  ON KREW_RTE_NODE_LNK_T 
  (FROM_RTE_NODE_ID)
/
CREATE INDEX KREW_RTE_NODE_LNK_TI2 
  ON KREW_RTE_NODE_LNK_T 
  (TO_RTE_NODE_ID)
/





-----------------------------------------------------------------------------
-- KREW_RTE_NODE_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RTE_NODE_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RTE_NODE_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RTE_NODE_T
(
      RTE_NODE_ID NUMBER(19)
        , DOC_TYP_ID NUMBER(19)
        , NM VARCHAR2(255) NOT NULL
        , TYP VARCHAR2(255) NOT NULL
        , RTE_MTHD_NM VARCHAR2(255)
        , RTE_MTHD_CD VARCHAR2(2)
        , FNL_APRVR_IND NUMBER(1)
        , MNDTRY_RTE_IND NUMBER(1)
        , ACTVN_TYP VARCHAR2(250)
        , BRCH_PROTO_ID NUMBER(19)
        , VER_NBR NUMBER(8) default 0
        , CONTENT_FRAGMENT VARCHAR2(4000)
        , GRP_ID VARCHAR2(40)
        , NEXT_DOC_STAT VARCHAR2(64)
    

)
/

ALTER TABLE KREW_RTE_NODE_T
    ADD CONSTRAINT KREW_RTE_NODE_TP1
PRIMARY KEY (RTE_NODE_ID)
/


CREATE INDEX KREW_RTE_NODE_TI1 
  ON KREW_RTE_NODE_T 
  (NM, DOC_TYP_ID)
/
CREATE INDEX KREW_RTE_NODE_TI2 
  ON KREW_RTE_NODE_T 
  (DOC_TYP_ID, FNL_APRVR_IND)
/
CREATE INDEX KREW_RTE_NODE_TI3 
  ON KREW_RTE_NODE_T 
  (BRCH_PROTO_ID)
/
CREATE INDEX KREW_RTE_NODE_TI4 
  ON KREW_RTE_NODE_T 
  (DOC_TYP_ID)
/





-----------------------------------------------------------------------------
-- KREW_RULE_ATTR_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RULE_ATTR_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RULE_ATTR_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RULE_ATTR_T
(
      RULE_ATTR_ID NUMBER(19)
        , NM VARCHAR2(255) NOT NULL
        , LBL VARCHAR2(2000) NOT NULL
        , RULE_ATTR_TYP_CD VARCHAR2(2000) NOT NULL
        , DESC_TXT VARCHAR2(2000)
        , CLS_NM VARCHAR2(2000)
        , XML CLOB
        , VER_NBR NUMBER(8) default 0
        , SVC_NMSPC VARCHAR2(255)
        , OBJ_ID VARCHAR2(36) NOT NULL
    
    , CONSTRAINT KREW_RULE_ATTR_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KREW_RULE_ATTR_T
    ADD CONSTRAINT KREW_RULE_ATTR_TP1
PRIMARY KEY (RULE_ATTR_ID)
/







-----------------------------------------------------------------------------
-- KREW_RULE_EXPR_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RULE_EXPR_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RULE_EXPR_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RULE_EXPR_T
(
      RULE_EXPR_ID NUMBER(19)
        , TYP VARCHAR2(256) NOT NULL
        , RULE_EXPR VARCHAR2(4000)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 0
    
    , CONSTRAINT KREW_RULE_EXPR_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KREW_RULE_EXPR_T
    ADD CONSTRAINT KREW_RULE_EXPR_TP1
PRIMARY KEY (RULE_EXPR_ID)
/







-----------------------------------------------------------------------------
-- KREW_RULE_EXT_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RULE_EXT_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RULE_EXT_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RULE_EXT_T
(
      RULE_EXT_ID NUMBER(19)
        , RULE_TMPL_ATTR_ID NUMBER(19) NOT NULL
        , RULE_ID NUMBER(19) NOT NULL
        , VER_NBR NUMBER(8) default 0
    

)
/

ALTER TABLE KREW_RULE_EXT_T
    ADD CONSTRAINT KREW_RULE_EXT_TP1
PRIMARY KEY (RULE_EXT_ID)
/


CREATE INDEX KREW_RULE_EXT_T1 
  ON KREW_RULE_EXT_T 
  (RULE_ID)
/





-----------------------------------------------------------------------------
-- KREW_RULE_EXT_VAL_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RULE_EXT_VAL_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RULE_EXT_VAL_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RULE_EXT_VAL_T
(
      RULE_EXT_VAL_ID NUMBER(19)
        , RULE_EXT_ID NUMBER(19) NOT NULL
        , VAL VARCHAR2(2000) NOT NULL
        , KEY_CD VARCHAR2(2000) NOT NULL
        , VER_NBR NUMBER(8) default 0
    

)
/

ALTER TABLE KREW_RULE_EXT_VAL_T
    ADD CONSTRAINT KREW_RULE_EXT_VAL_TP1
PRIMARY KEY (RULE_EXT_VAL_ID)
/


CREATE INDEX KREW_RULE_EXT_VAL_T1 
  ON KREW_RULE_EXT_VAL_T 
  (RULE_EXT_ID)
/
CREATE INDEX KREW_RULE_EXT_VAL_T2 
  ON KREW_RULE_EXT_VAL_T 
  (RULE_EXT_VAL_ID, KEY_CD)
/





-----------------------------------------------------------------------------
-- KREW_RULE_RSP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RULE_RSP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RULE_RSP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RULE_RSP_T
(
      RULE_RSP_ID NUMBER(19)
        , RSP_ID NUMBER(19) NOT NULL
        , RULE_ID NUMBER(19) NOT NULL
        , PRIO NUMBER(5)
        , ACTN_RQST_CD VARCHAR2(2000)
        , NM VARCHAR2(200)
        , TYP VARCHAR2(1)
        , APPR_PLCY CHAR(1)
        , VER_NBR NUMBER(8) default 0
        , OBJ_ID VARCHAR2(36) NOT NULL
    
    , CONSTRAINT KREW_RULE_RSP_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KREW_RULE_RSP_T
    ADD CONSTRAINT KREW_RULE_RSP_TP1
PRIMARY KEY (RULE_RSP_ID)
/


CREATE INDEX KREW_RULE_RSP_TI1 
  ON KREW_RULE_RSP_T 
  (RULE_ID)
/





-----------------------------------------------------------------------------
-- KREW_RULE_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RULE_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RULE_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RULE_T
(
      RULE_ID NUMBER(19)
        , NM VARCHAR2(256)
        , RULE_TMPL_ID NUMBER(19)
        , RULE_EXPR_ID NUMBER(19)
        , ACTV_IND NUMBER(1) NOT NULL
        , RULE_BASE_VAL_DESC VARCHAR2(2000)
        , FRC_ACTN NUMBER(1) NOT NULL
        , DOC_TYP_NM VARCHAR2(64) NOT NULL
        , DOC_HDR_ID NUMBER(14)
        , TMPL_RULE_IND NUMBER(1)
        , FRM_DT DATE
        , TO_DT DATE
        , DACTVN_DT DATE
        , CUR_IND NUMBER(1) default 0
        , RULE_VER_NBR NUMBER(8) default 0
        , DLGN_IND NUMBER(1)
        , PREV_RULE_VER_NBR NUMBER(19)
        , ACTVN_DT DATE
        , VER_NBR NUMBER(8) default 0
        , OBJ_ID VARCHAR2(36) NOT NULL
    
    , CONSTRAINT KREW_RULE_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KREW_RULE_T
    ADD CONSTRAINT KREW_RULE_TP1
PRIMARY KEY (RULE_ID)
/







-----------------------------------------------------------------------------
-- KREW_RULE_TMPL_ATTR_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RULE_TMPL_ATTR_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RULE_TMPL_ATTR_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RULE_TMPL_ATTR_T
(
      RULE_TMPL_ATTR_ID NUMBER(19)
        , RULE_TMPL_ID NUMBER(19) NOT NULL
        , RULE_ATTR_ID NUMBER(19) NOT NULL
        , REQ_IND NUMBER(1) NOT NULL
        , ACTV_IND NUMBER(1) NOT NULL
        , DSPL_ORD NUMBER(5) NOT NULL
        , DFLT_VAL VARCHAR2(2000)
        , VER_NBR NUMBER(8) default 0
        , OBJ_ID VARCHAR2(36) NOT NULL
    
    , CONSTRAINT KREW_RULE_TMPL_ATTR_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KREW_RULE_TMPL_ATTR_T
    ADD CONSTRAINT KREW_RULE_TMPL_ATTR_TP1
PRIMARY KEY (RULE_TMPL_ATTR_ID)
/


CREATE INDEX KREW_RULE_TMPL_ATTR_TI1 
  ON KREW_RULE_TMPL_ATTR_T 
  (RULE_TMPL_ID)
/
CREATE INDEX KREW_RULE_TMPL_ATTR_TI2 
  ON KREW_RULE_TMPL_ATTR_T 
  (RULE_ATTR_ID)
/





-----------------------------------------------------------------------------
-- KREW_RULE_TMPL_OPTN_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RULE_TMPL_OPTN_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RULE_TMPL_OPTN_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RULE_TMPL_OPTN_T
(
      RULE_TMPL_OPTN_ID NUMBER(19)
        , RULE_TMPL_ID NUMBER(19)
        , KEY_CD VARCHAR2(250)
        , VAL VARCHAR2(2000)
        , VER_NBR NUMBER(8) default 0
    

)
/

ALTER TABLE KREW_RULE_TMPL_OPTN_T
    ADD CONSTRAINT KREW_RULE_TMPL_OPTN_TP1
PRIMARY KEY (RULE_TMPL_OPTN_ID)
/







-----------------------------------------------------------------------------
-- KREW_RULE_TMPL_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_RULE_TMPL_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_RULE_TMPL_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_RULE_TMPL_T
(
      RULE_TMPL_ID NUMBER(19)
        , NM VARCHAR2(250) NOT NULL
        , RULE_TMPL_DESC VARCHAR2(2000)
        , DLGN_RULE_TMPL_ID NUMBER(19)
        , VER_NBR NUMBER(8) default 0
        , OBJ_ID VARCHAR2(36) NOT NULL
    
    , CONSTRAINT KREW_RULE_TMPL_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KREW_RULE_TMPL_TI1 UNIQUE (NM)

)
/

ALTER TABLE KREW_RULE_TMPL_T
    ADD CONSTRAINT KREW_RULE_TMPL_TP1
PRIMARY KEY (RULE_TMPL_ID)
/







-----------------------------------------------------------------------------
-- KREW_STYLE_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_STYLE_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_STYLE_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_STYLE_T
(
      STYLE_ID NUMBER(19)
        , NM VARCHAR2(200) NOT NULL
        , XML CLOB NOT NULL
        , ACTV_IND NUMBER(1) NOT NULL
        , VER_NBR NUMBER(8) default 0
        , OBJ_ID VARCHAR2(36) NOT NULL
    
    , CONSTRAINT KREW_STYLE_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KREW_STYLE_T
    ADD CONSTRAINT KREW_STYLE_TP1
PRIMARY KEY (STYLE_ID)
/







-----------------------------------------------------------------------------
-- KREW_USR_OPTN_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KREW_USR_OPTN_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KREW_USR_OPTN_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KREW_USR_OPTN_T
(
      PRNCPL_ID VARCHAR2(40)
        , PRSN_OPTN_ID VARCHAR2(200)
        , VAL VARCHAR2(2000)
        , VER_NBR NUMBER(8) default 0
    

)
/

ALTER TABLE KREW_USR_OPTN_T
    ADD CONSTRAINT KREW_USR_OPTN_TP1
PRIMARY KEY (PRNCPL_ID,PRSN_OPTN_ID)
/


CREATE INDEX KREW_USR_OPTN_TI1 
  ON KREW_USR_OPTN_T 
  (PRNCPL_ID)
/





-----------------------------------------------------------------------------
-- KRIM_ADDR_TYP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ADDR_TYP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ADDR_TYP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ADDR_TYP_T
(
      ADDR_TYP_CD VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , NM VARCHAR2(40)
        , ACTV_IND VARCHAR2(1) default 'Y'
        , DISPLAY_SORT_CD VARCHAR2(2)
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_ADDR_TYP_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_ADDR_TYP_TC1 UNIQUE (NM)

)
/

ALTER TABLE KRIM_ADDR_TYP_T
    ADD CONSTRAINT KRIM_ADDR_TYP_TP1
PRIMARY KEY (ADDR_TYP_CD)
/







-----------------------------------------------------------------------------
-- KRIM_AFLTN_TYP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_AFLTN_TYP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_AFLTN_TYP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_AFLTN_TYP_T
(
      AFLTN_TYP_CD VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , NM VARCHAR2(40)
        , EMP_AFLTN_TYP_IND VARCHAR2(1) default 'N'
        , ACTV_IND VARCHAR2(1) default 'Y'
        , DISPLAY_SORT_CD VARCHAR2(2)
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_AFLTN_TYP_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_AFLTN_TYP_TC1 UNIQUE (NM)

)
/

ALTER TABLE KRIM_AFLTN_TYP_T
    ADD CONSTRAINT KRIM_AFLTN_TYP_TP1
PRIMARY KEY (AFLTN_TYP_CD)
/







-----------------------------------------------------------------------------
-- KRIM_ATTR_DEFN_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ATTR_DEFN_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ATTR_DEFN_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ATTR_DEFN_T
(
      KIM_ATTR_DEFN_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , NM VARCHAR2(100)
        , LBL VARCHAR2(40)
        , ACTV_IND VARCHAR2(1) default 'Y'
        , NMSPC_CD VARCHAR2(40)
        , CMPNT_NM VARCHAR2(100)
    
    , CONSTRAINT KRIM_ATTR_DEFN_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_ATTR_DEFN_T
    ADD CONSTRAINT KRIM_ATTR_DEFN_TP1
PRIMARY KEY (KIM_ATTR_DEFN_ID)
/







-----------------------------------------------------------------------------
-- KRIM_CTZNSHP_STAT_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_CTZNSHP_STAT_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_CTZNSHP_STAT_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_CTZNSHP_STAT_T
(
      CTZNSHP_STAT_CD VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , NM VARCHAR2(40)
        , ACTV_IND VARCHAR2(1) default 'Y'
        , DISPLAY_SORT_CD VARCHAR2(2)
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_CTZNSHP_STAT_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_CTZNSHP_STAT_TC1 UNIQUE (NM)

)
/

ALTER TABLE KRIM_CTZNSHP_STAT_T
    ADD CONSTRAINT KRIM_CTZNSHP_STAT_TP1
PRIMARY KEY (CTZNSHP_STAT_CD)
/







-----------------------------------------------------------------------------
-- KRIM_DLGN_MBR_ATTR_DATA_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_DLGN_MBR_ATTR_DATA_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_DLGN_MBR_ATTR_DATA_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_DLGN_MBR_ATTR_DATA_T
(
      ATTR_DATA_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , DLGN_MBR_ID VARCHAR2(40)
        , KIM_TYP_ID VARCHAR2(40) NOT NULL
        , KIM_ATTR_DEFN_ID VARCHAR2(40)
        , ATTR_VAL VARCHAR2(400)
    
    , CONSTRAINT KRIM_DLGN_MBR_ATTR_DATA_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_DLGN_MBR_ATTR_DATA_T
    ADD CONSTRAINT KRIM_DLGN_MBR_ATTR_DATA_TP1
PRIMARY KEY (ATTR_DATA_ID)
/







-----------------------------------------------------------------------------
-- KRIM_DLGN_MBR_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_DLGN_MBR_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_DLGN_MBR_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_DLGN_MBR_T
(
      DLGN_MBR_ID VARCHAR2(40)
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , OBJ_ID VARCHAR2(36) NOT NULL
        , DLGN_ID VARCHAR2(40)
        , MBR_ID VARCHAR2(40)
        , MBR_TYP_CD CHAR(1) default 'P'
        , ACTV_FRM_DT DATE
        , ACTV_TO_DT DATE
        , LAST_UPDT_DT DATE default SYSDATE
        , ROLE_MBR_ID VARCHAR2(40)
    
    , CONSTRAINT KRIM_DLGN_MBR_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_DLGN_MBR_T
    ADD CONSTRAINT KRIM_DLGN_MBR_TP1
PRIMARY KEY (DLGN_MBR_ID)
/







-----------------------------------------------------------------------------
-- KRIM_DLGN_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_DLGN_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_DLGN_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_DLGN_T
(
      DLGN_ID VARCHAR2(40)
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , OBJ_ID VARCHAR2(36) NOT NULL
        , ROLE_ID VARCHAR2(40)
        , ACTV_IND VARCHAR2(1) default 'Y'
        , KIM_TYP_ID VARCHAR2(40) NOT NULL
        , DLGN_TYP_CD VARCHAR2(1)
    
    , CONSTRAINT KRIM_DLGN_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_DLGN_T
    ADD CONSTRAINT KRIM_DLGN_TP1
PRIMARY KEY (DLGN_ID)
/







-----------------------------------------------------------------------------
-- KRIM_EMAIL_TYP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_EMAIL_TYP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_EMAIL_TYP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_EMAIL_TYP_T
(
      EMAIL_TYP_CD VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , NM VARCHAR2(40)
        , ACTV_IND VARCHAR2(1) default 'Y'
        , DISPLAY_SORT_CD VARCHAR2(2)
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_EMAIL_TYP_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_EMAIL_TYP_TC1 UNIQUE (NM)

)
/

ALTER TABLE KRIM_EMAIL_TYP_T
    ADD CONSTRAINT KRIM_EMAIL_TYP_TP1
PRIMARY KEY (EMAIL_TYP_CD)
/







-----------------------------------------------------------------------------
-- KRIM_EMP_STAT_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_EMP_STAT_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_EMP_STAT_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_EMP_STAT_T
(
      EMP_STAT_CD VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , NM VARCHAR2(40)
        , ACTV_IND VARCHAR2(1) default 'Y'
        , DISPLAY_SORT_CD VARCHAR2(2)
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_EMP_STAT_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_EMP_STAT_TC1 UNIQUE (NM)

)
/

ALTER TABLE KRIM_EMP_STAT_T
    ADD CONSTRAINT KRIM_EMP_STAT_TP1
PRIMARY KEY (EMP_STAT_CD)
/







-----------------------------------------------------------------------------
-- KRIM_EMP_TYP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_EMP_TYP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_EMP_TYP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_EMP_TYP_T
(
      EMP_TYP_CD VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , NM VARCHAR2(40)
        , ACTV_IND VARCHAR2(1) default 'Y'
        , DISPLAY_SORT_CD VARCHAR2(2)
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_EMP_TYP_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_EMP_TYP_TC1 UNIQUE (NM)

)
/

ALTER TABLE KRIM_EMP_TYP_T
    ADD CONSTRAINT KRIM_EMP_TYP_TP1
PRIMARY KEY (EMP_TYP_CD)
/







-----------------------------------------------------------------------------
-- KRIM_ENTITY_ADDR_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ENTITY_ADDR_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ENTITY_ADDR_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ENTITY_ADDR_T
(
      ENTITY_ADDR_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ENTITY_ID VARCHAR2(40)
        , ENT_TYP_CD VARCHAR2(40)
        , ADDR_TYP_CD VARCHAR2(40)
        , ADDR_LINE_1 VARCHAR2(45)
        , ADDR_LINE_2 VARCHAR2(45)
        , ADDR_LINE_3 VARCHAR2(45)
        , CITY_NM VARCHAR2(30)
        , POSTAL_STATE_CD VARCHAR2(2)
        , POSTAL_CD VARCHAR2(20)
        , POSTAL_CNTRY_CD VARCHAR2(2)
        , DFLT_IND VARCHAR2(1) default 'N'
        , ACTV_IND VARCHAR2(1) default 'Y'
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_ENTITY_ADDR_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_ENTITY_ADDR_T
    ADD CONSTRAINT KRIM_ENTITY_ADDR_TP1
PRIMARY KEY (ENTITY_ADDR_ID)
/


CREATE INDEX KRIM_ENTITY_ADDR_TI1 
  ON KRIM_ENTITY_ADDR_T 
  (ENTITY_ID)
/





-----------------------------------------------------------------------------
-- KRIM_ENTITY_AFLTN_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ENTITY_AFLTN_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ENTITY_AFLTN_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ENTITY_AFLTN_T
(
      ENTITY_AFLTN_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ENTITY_ID VARCHAR2(40)
        , AFLTN_TYP_CD VARCHAR2(40)
        , CAMPUS_CD VARCHAR2(2)
        , DFLT_IND VARCHAR2(1) default 'N'
        , ACTV_IND VARCHAR2(1) default 'Y'
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_ENTITY_AFLTN_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_ENTITY_AFLTN_T
    ADD CONSTRAINT KRIM_ENTITY_AFLTN_TP1
PRIMARY KEY (ENTITY_AFLTN_ID)
/


CREATE INDEX KRIM_ENTITY_AFLTN_TI1 
  ON KRIM_ENTITY_AFLTN_T 
  (ENTITY_ID)
/





-----------------------------------------------------------------------------
-- KRIM_ENTITY_BIO_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ENTITY_BIO_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ENTITY_BIO_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ENTITY_BIO_T
(
      ENTITY_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , BIRTH_DT DATE
        , GNDR_CD VARCHAR2(1) NOT NULL
        , LAST_UPDT_DT DATE default SYSDATE
        , DECEASED_DT DATE
        , MARITAL_STATUS VARCHAR2(40)
        , PRIM_LANG_CD VARCHAR2(40)
        , SEC_LANG_CD VARCHAR2(40)
        , BIRTH_CNTRY_CD VARCHAR2(2)
        , BIRTH_STATE_CD VARCHAR2(2)
        , BIRTH_CITY VARCHAR2(30)
        , GEO_ORIGIN VARCHAR2(100)
    
    , CONSTRAINT KRIM_ENTITY_BIO_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_ENTITY_BIO_T
    ADD CONSTRAINT KRIM_ENTITY_BIO_TP1
PRIMARY KEY (ENTITY_ID)
/







-----------------------------------------------------------------------------
-- KRIM_ENTITY_CACHE_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ENTITY_CACHE_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ENTITY_CACHE_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ENTITY_CACHE_T
(
      ENTITY_ID VARCHAR2(40)
        , PRNCPL_ID VARCHAR2(40) NOT NULL
        , PRNCPL_NM VARCHAR2(40)
        , ENTITY_TYP_CD VARCHAR2(40)
        , FIRST_NM VARCHAR2(40)
        , MIDDLE_NM VARCHAR2(40)
        , LAST_NM VARCHAR2(40)
        , PRSN_NM VARCHAR2(40)
        , CAMPUS_CD VARCHAR2(40)
        , PRMRY_DEPT_CD VARCHAR2(40)
        , EMP_ID VARCHAR2(40)
        , LAST_UPDT_TS DATE
        , OBJ_ID VARCHAR2(36) NOT NULL
    
    , CONSTRAINT KRIM_ENTITY_CACHE_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_ENTITY_CACHE_TC1 UNIQUE (PRNCPL_ID)

)
/

ALTER TABLE KRIM_ENTITY_CACHE_T
    ADD CONSTRAINT KRIM_ENTITY_CACHE_TP1
PRIMARY KEY (ENTITY_ID)
/







-----------------------------------------------------------------------------
-- KRIM_ENTITY_CTZNSHP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ENTITY_CTZNSHP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ENTITY_CTZNSHP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ENTITY_CTZNSHP_T
(
      ENTITY_CTZNSHP_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ENTITY_ID VARCHAR2(40)
        , POSTAL_CNTRY_CD VARCHAR2(2)
        , CTZNSHP_STAT_CD VARCHAR2(40)
        , STRT_DT DATE
        , END_DT DATE
        , ACTV_IND VARCHAR2(1) default 'Y'
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_ENTITY_CTZNSHP_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_ENTITY_CTZNSHP_T
    ADD CONSTRAINT KRIM_ENTITY_CTZNSHP_TP1
PRIMARY KEY (ENTITY_CTZNSHP_ID)
/







-----------------------------------------------------------------------------
-- KRIM_ENTITY_EMAIL_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ENTITY_EMAIL_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ENTITY_EMAIL_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ENTITY_EMAIL_T
(
      ENTITY_EMAIL_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ENTITY_ID VARCHAR2(40)
        , ENT_TYP_CD VARCHAR2(40)
        , EMAIL_TYP_CD VARCHAR2(40)
        , EMAIL_ADDR VARCHAR2(200)
        , DFLT_IND VARCHAR2(1) default 'N'
        , ACTV_IND VARCHAR2(1) default 'Y'
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_ENTITY_EMAIL_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_ENTITY_EMAIL_T
    ADD CONSTRAINT KRIM_ENTITY_EMAIL_TP1
PRIMARY KEY (ENTITY_EMAIL_ID)
/


CREATE INDEX KRIM_ENTITY_EMAIL_TI1 
  ON KRIM_ENTITY_EMAIL_T 
  (ENTITY_ID)
/





-----------------------------------------------------------------------------
-- KRIM_ENTITY_EMP_INFO_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ENTITY_EMP_INFO_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ENTITY_EMP_INFO_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ENTITY_EMP_INFO_T
(
      ENTITY_EMP_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ENTITY_ID VARCHAR2(40)
        , ENTITY_AFLTN_ID VARCHAR2(40)
        , EMP_STAT_CD VARCHAR2(40)
        , EMP_TYP_CD VARCHAR2(40)
        , BASE_SLRY_AMT NUMBER(15,2)
        , PRMRY_IND VARCHAR2(1)
        , ACTV_IND VARCHAR2(1) default 'Y'
        , LAST_UPDT_DT DATE default SYSDATE
        , PRMRY_DEPT_CD VARCHAR2(40)
        , EMP_ID VARCHAR2(40)
        , EMP_REC_ID VARCHAR2(40)
    
    , CONSTRAINT KRIM_ENTITY_EMP_INFO_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_ENTITY_EMP_INFO_T
    ADD CONSTRAINT KRIM_ENTITY_EMP_INFO_TP1
PRIMARY KEY (ENTITY_EMP_ID)
/


CREATE INDEX KRIM_ENTITY_EMP_INFO_TI1 
  ON KRIM_ENTITY_EMP_INFO_T 
  (ENTITY_ID)
/
CREATE INDEX KRIM_ENTITY_EMP_INFO_TI2 
  ON KRIM_ENTITY_EMP_INFO_T 
  (ENTITY_AFLTN_ID)
/





-----------------------------------------------------------------------------
-- KRIM_ENTITY_ENT_TYP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ENTITY_ENT_TYP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ENTITY_ENT_TYP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ENTITY_ENT_TYP_T
(
      ENT_TYP_CD VARCHAR2(40)
        , ENTITY_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ACTV_IND VARCHAR2(1) default 'Y'
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_ENTITY_ENT_TYP_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_ENTITY_ENT_TYP_T
    ADD CONSTRAINT KRIM_ENTITY_ENT_TYP_TP1
PRIMARY KEY (ENT_TYP_CD,ENTITY_ID)
/


CREATE INDEX KRIM_ENTITY_ENT_TYP_TI1 
  ON KRIM_ENTITY_ENT_TYP_T 
  (ENTITY_ID)
/





-----------------------------------------------------------------------------
-- KRIM_ENTITY_ETHNIC_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ENTITY_ETHNIC_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ENTITY_ETHNIC_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ENTITY_ETHNIC_T
(
      ID VARCHAR2(40)
        , ENTITY_ID VARCHAR2(40)
        , ETHNCTY_CD VARCHAR2(40)
        , SUB_ETHNCTY_CD VARCHAR2(40)
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , OBJ_ID VARCHAR2(36) NOT NULL
    
    , CONSTRAINT KRIM_ENTITY_ETHNIC_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_ENTITY_ETHNIC_T
    ADD CONSTRAINT KRIM_ENTITY_ETHNIC_TP1
PRIMARY KEY (ID)
/







-----------------------------------------------------------------------------
-- KRIM_ENTITY_EXT_ID_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ENTITY_EXT_ID_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ENTITY_EXT_ID_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ENTITY_EXT_ID_T
(
      ENTITY_EXT_ID_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ENTITY_ID VARCHAR2(40)
        , EXT_ID_TYP_CD VARCHAR2(40)
        , EXT_ID VARCHAR2(100)
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_ENTITY_EXT_ID_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_ENTITY_EXT_ID_T
    ADD CONSTRAINT KRIM_ENTITY_EXT_ID_TP1
PRIMARY KEY (ENTITY_EXT_ID_ID)
/


CREATE INDEX KRIM_ENTITY_EXT_ID_TI1 
  ON KRIM_ENTITY_EXT_ID_T 
  (ENTITY_ID)
/





-----------------------------------------------------------------------------
-- KRIM_ENTITY_NM_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ENTITY_NM_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ENTITY_NM_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ENTITY_NM_T
(
      ENTITY_NM_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ENTITY_ID VARCHAR2(40)
        , NM_TYP_CD VARCHAR2(40)
        , FIRST_NM VARCHAR2(40)
        , MIDDLE_NM VARCHAR2(40)
        , LAST_NM VARCHAR2(80)
        , SUFFIX_NM VARCHAR2(20)
        , TITLE_NM VARCHAR2(20)
        , DFLT_IND VARCHAR2(1) default 'N'
        , ACTV_IND VARCHAR2(1) default 'Y'
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_ENTITY_NM_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_ENTITY_NM_T
    ADD CONSTRAINT KRIM_ENTITY_NM_TP1
PRIMARY KEY (ENTITY_NM_ID)
/


CREATE INDEX KRIM_ENTITY_NM_TI1 
  ON KRIM_ENTITY_NM_T 
  (ENTITY_ID)
/





-----------------------------------------------------------------------------
-- KRIM_ENTITY_PHONE_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ENTITY_PHONE_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ENTITY_PHONE_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ENTITY_PHONE_T
(
      ENTITY_PHONE_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ENTITY_ID VARCHAR2(40)
        , ENT_TYP_CD VARCHAR2(40)
        , PHONE_TYP_CD VARCHAR2(40)
        , PHONE_NBR VARCHAR2(20)
        , PHONE_EXTN_NBR VARCHAR2(8)
        , POSTAL_CNTRY_CD VARCHAR2(2)
        , DFLT_IND VARCHAR2(1) default 'N'
        , ACTV_IND VARCHAR2(1) default 'Y'
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_ENTITY_PHONE_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_ENTITY_PHONE_T
    ADD CONSTRAINT KRIM_ENTITY_PHONE_TP1
PRIMARY KEY (ENTITY_PHONE_ID)
/


CREATE INDEX KRIM_ENTITY_PHONE_TI1 
  ON KRIM_ENTITY_PHONE_T 
  (ENTITY_ID)
/





-----------------------------------------------------------------------------
-- KRIM_ENTITY_PRIV_PREF_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ENTITY_PRIV_PREF_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ENTITY_PRIV_PREF_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ENTITY_PRIV_PREF_T
(
      ENTITY_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , SUPPRESS_NM_IND VARCHAR2(1) default 'N'
        , SUPPRESS_EMAIL_IND VARCHAR2(1) default 'Y'
        , SUPPRESS_ADDR_IND VARCHAR2(1) default 'Y'
        , SUPPRESS_PHONE_IND VARCHAR2(1) default 'Y'
        , SUPPRESS_PRSNL_IND VARCHAR2(1) default 'Y'
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_ENTITY_PRIV_PREF_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_ENTITY_PRIV_PREF_T
    ADD CONSTRAINT KRIM_ENTITY_PRIV_PREF_TP1
PRIMARY KEY (ENTITY_ID)
/







-----------------------------------------------------------------------------
-- KRIM_ENTITY_RESIDENCY_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ENTITY_RESIDENCY_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ENTITY_RESIDENCY_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ENTITY_RESIDENCY_T
(
      ID VARCHAR2(40)
        , ENTITY_ID VARCHAR2(40)
        , DETERMINATION_METHOD VARCHAR2(40)
        , IN_STATE VARCHAR2(40)
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , OBJ_ID VARCHAR2(36) NOT NULL
    
    , CONSTRAINT KRIM_ENTITY_RESIDENCY_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_ENTITY_RESIDENCY_T
    ADD CONSTRAINT KRIM_ENTITY_RESIDENCY_TP1
PRIMARY KEY (ID)
/







-----------------------------------------------------------------------------
-- KRIM_ENTITY_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ENTITY_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ENTITY_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ENTITY_T
(
      ENTITY_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ACTV_IND VARCHAR2(1) default 'Y'
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_ENTITY_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_ENTITY_T
    ADD CONSTRAINT KRIM_ENTITY_TP1
PRIMARY KEY (ENTITY_ID)
/







-----------------------------------------------------------------------------
-- KRIM_ENTITY_VISA_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ENTITY_VISA_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ENTITY_VISA_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ENTITY_VISA_T
(
      ID VARCHAR2(40)
        , ENTITY_ID VARCHAR2(40)
        , VISA_TYPE_KEY VARCHAR2(40)
        , VISA_ENTRY VARCHAR2(40)
        , VISA_ID VARCHAR2(40)
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , OBJ_ID VARCHAR2(36) NOT NULL
    
    , CONSTRAINT KRIM_ENTITY_VISA_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_ENTITY_VISA_T
    ADD CONSTRAINT KRIM_ENTITY_VISA_TP1
PRIMARY KEY (ID)
/







-----------------------------------------------------------------------------
-- KRIM_ENT_NM_TYP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ENT_NM_TYP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ENT_NM_TYP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ENT_NM_TYP_T
(
      ENT_NM_TYP_CD VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , NM VARCHAR2(40)
        , ACTV_IND VARCHAR2(1) default 'Y'
        , DISPLAY_SORT_CD VARCHAR2(2)
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_ENT_NM_TYP_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_ENT_NM_TYP_TC1 UNIQUE (NM)

)
/

ALTER TABLE KRIM_ENT_NM_TYP_T
    ADD CONSTRAINT KRIM_ENT_NM_TYP_TP1
PRIMARY KEY (ENT_NM_TYP_CD)
/







-----------------------------------------------------------------------------
-- KRIM_ENT_TYP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ENT_TYP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ENT_TYP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ENT_TYP_T
(
      ENT_TYP_CD VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , NM VARCHAR2(40)
        , DISPLAY_SORT_CD VARCHAR2(2)
        , ACTV_IND VARCHAR2(1) default 'Y'
    
    , CONSTRAINT KRIM_ENT_TYP_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_ENT_TYP_TC1 UNIQUE (NM)

)
/

ALTER TABLE KRIM_ENT_TYP_T
    ADD CONSTRAINT KRIM_ENT_TYP_TP1
PRIMARY KEY (ENT_TYP_CD)
/







-----------------------------------------------------------------------------
-- KRIM_EXT_ID_TYP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_EXT_ID_TYP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_EXT_ID_TYP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_EXT_ID_TYP_T
(
      EXT_ID_TYP_CD VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , NM VARCHAR2(40)
        , ENCR_REQ_IND VARCHAR2(1) default 'N'
        , ACTV_IND VARCHAR2(1) default 'Y'
        , DISPLAY_SORT_CD VARCHAR2(2)
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_EXT_ID_TYP_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_EXT_ID_TYP_TC1 UNIQUE (NM)

)
/

ALTER TABLE KRIM_EXT_ID_TYP_T
    ADD CONSTRAINT KRIM_EXT_ID_TYP_TP1
PRIMARY KEY (EXT_ID_TYP_CD)
/







-----------------------------------------------------------------------------
-- KRIM_GRP_ATTR_DATA_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_GRP_ATTR_DATA_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_GRP_ATTR_DATA_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_GRP_ATTR_DATA_T
(
      ATTR_DATA_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , GRP_ID VARCHAR2(40)
        , KIM_TYP_ID VARCHAR2(40) NOT NULL
        , KIM_ATTR_DEFN_ID VARCHAR2(40)
        , ATTR_VAL VARCHAR2(400)
    
    , CONSTRAINT KRIM_GRP_ATTR_DATA_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_GRP_ATTR_DATA_T
    ADD CONSTRAINT KRIM_GRP_ATTR_DATA_TP1
PRIMARY KEY (ATTR_DATA_ID)
/







-----------------------------------------------------------------------------
-- KRIM_GRP_DOCUMENT_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_GRP_DOCUMENT_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_GRP_DOCUMENT_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_GRP_DOCUMENT_T
(
      FDOC_NBR VARCHAR2(14)
        , GRP_ID VARCHAR2(40) NOT NULL
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , KIM_TYP_ID VARCHAR2(40) NOT NULL
        , GRP_NMSPC VARCHAR2(100) NOT NULL
        , GRP_NM VARCHAR2(400)
        , GRP_DESC VARCHAR2(400)
        , ACTV_IND VARCHAR2(1) default 'Y'
    

)
/

ALTER TABLE KRIM_GRP_DOCUMENT_T
    ADD CONSTRAINT KRIM_GRP_DOCUMENT_TP1
PRIMARY KEY (FDOC_NBR)
/







-----------------------------------------------------------------------------
-- KRIM_GRP_MBR_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_GRP_MBR_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_GRP_MBR_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_GRP_MBR_T
(
      GRP_MBR_ID VARCHAR2(40)
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , OBJ_ID VARCHAR2(36) NOT NULL
        , GRP_ID VARCHAR2(40)
        , MBR_ID VARCHAR2(40)
        , MBR_TYP_CD CHAR(1) default 'P'
        , ACTV_FRM_DT DATE
        , ACTV_TO_DT DATE
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_GRP_MBR_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_GRP_MBR_T
    ADD CONSTRAINT KRIM_GRP_MBR_TP1
PRIMARY KEY (GRP_MBR_ID)
/


CREATE INDEX KRIM_GRP_MBR_TI1 
  ON KRIM_GRP_MBR_T 
  (MBR_ID)
/





-----------------------------------------------------------------------------
-- KRIM_GRP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_GRP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_GRP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_GRP_T
(
      GRP_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , GRP_NM VARCHAR2(80) NOT NULL
        , NMSPC_CD VARCHAR2(40) NOT NULL
        , GRP_DESC VARCHAR2(4000)
        , KIM_TYP_ID VARCHAR2(40) NOT NULL
        , ACTV_IND VARCHAR2(1) default 'Y'
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_GRP_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_GRP_TC1 UNIQUE (GRP_NM, NMSPC_CD)

)
/

ALTER TABLE KRIM_GRP_T
    ADD CONSTRAINT KRIM_GRP_TP1
PRIMARY KEY (GRP_ID)
/







-----------------------------------------------------------------------------
-- KRIM_PERM_ATTR_DATA_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PERM_ATTR_DATA_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PERM_ATTR_DATA_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PERM_ATTR_DATA_T
(
      ATTR_DATA_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , PERM_ID VARCHAR2(40)
        , KIM_TYP_ID VARCHAR2(40) NOT NULL
        , KIM_ATTR_DEFN_ID VARCHAR2(40)
        , ATTR_VAL VARCHAR2(400)
    
    , CONSTRAINT KRIM_PERM_ATTR_DATA_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_PERM_ATTR_DATA_T
    ADD CONSTRAINT KRIM_PERM_ATTR_DATA_TP1
PRIMARY KEY (ATTR_DATA_ID)
/


CREATE INDEX KRIM_PERM_ATTR_DATA_TI1 
  ON KRIM_PERM_ATTR_DATA_T 
  (PERM_ID)
/





-----------------------------------------------------------------------------
-- KRIM_PERM_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PERM_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PERM_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PERM_T
(
      PERM_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , PERM_TMPL_ID VARCHAR2(40)
        , NMSPC_CD VARCHAR2(40)
        , NM VARCHAR2(100)
        , DESC_TXT VARCHAR2(400)
        , ACTV_IND VARCHAR2(1) default 'Y'
    
    , CONSTRAINT KRIM_PERM_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_PERM_T
    ADD CONSTRAINT KRIM_PERM_TP1
PRIMARY KEY (PERM_ID)
/







-----------------------------------------------------------------------------
-- KRIM_PERM_TMPL_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PERM_TMPL_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PERM_TMPL_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PERM_TMPL_T
(
      PERM_TMPL_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , NMSPC_CD VARCHAR2(40)
        , NM VARCHAR2(100)
        , DESC_TXT VARCHAR2(400)
        , KIM_TYP_ID VARCHAR2(40) NOT NULL
        , ACTV_IND VARCHAR2(1) default 'Y'
    
    , CONSTRAINT KRIM_PERM_TMPL_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_PERM_TMPL_T
    ADD CONSTRAINT KRIM_PERM_TMPL_TP1
PRIMARY KEY (PERM_TMPL_ID)
/







-----------------------------------------------------------------------------
-- KRIM_PERSON_DOCUMENT_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PERSON_DOCUMENT_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PERSON_DOCUMENT_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PERSON_DOCUMENT_T
(
      FDOC_NBR VARCHAR2(14)
        , ENTITY_ID VARCHAR2(40) NOT NULL
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , PRNCPL_ID VARCHAR2(40) NOT NULL
        , PRNCPL_NM VARCHAR2(100) NOT NULL
        , PRNCPL_PSWD VARCHAR2(400)
        , UNIV_ID VARCHAR2(40)
        , ACTV_IND VARCHAR2(1) default 'Y'
    

)
/

ALTER TABLE KRIM_PERSON_DOCUMENT_T
    ADD CONSTRAINT KRIM_PERSON_DOCUMENT_TP1
PRIMARY KEY (FDOC_NBR)
/







-----------------------------------------------------------------------------
-- KRIM_PHONE_TYP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PHONE_TYP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PHONE_TYP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PHONE_TYP_T
(
      PHONE_TYP_CD VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , PHONE_TYP_NM VARCHAR2(40)
        , ACTV_IND VARCHAR2(1) default 'Y'
        , DISPLAY_SORT_CD VARCHAR2(2)
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_PHONE_TYP_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_PHONE_TYP_TC1 UNIQUE (PHONE_TYP_NM)

)
/

ALTER TABLE KRIM_PHONE_TYP_T
    ADD CONSTRAINT KRIM_PHONE_TYP_TP1
PRIMARY KEY (PHONE_TYP_CD)
/







-----------------------------------------------------------------------------
-- KRIM_PND_ADDR_MT
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PND_ADDR_MT';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PND_ADDR_MT CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PND_ADDR_MT
(
      FDOC_NBR VARCHAR2(14)
        , ADDR_TYP_CD VARCHAR2(40)
        , ADDR_LINE_1 VARCHAR2(50)
        , ADDR_LINE_2 VARCHAR2(50)
        , ADDR_LINE_3 VARCHAR2(50)
        , CITY_NM VARCHAR2(30)
        , POSTAL_STATE_CD VARCHAR2(2)
        , POSTAL_CD VARCHAR2(20)
        , POSTAL_CNTRY_CD VARCHAR2(2)
        , DISPLAY_SORT_CD VARCHAR2(2)
        , DFLT_IND VARCHAR2(1) default 'N'
        , ACTV_IND VARCHAR2(1) default 'Y'
        , ENTITY_ADDR_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , EDIT_FLAG VARCHAR2(1) default 'N'
    

)
/

ALTER TABLE KRIM_PND_ADDR_MT
    ADD CONSTRAINT KRIM_PND_ADDR_MTP1
PRIMARY KEY (FDOC_NBR,ENTITY_ADDR_ID)
/







-----------------------------------------------------------------------------
-- KRIM_PND_AFLTN_MT
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PND_AFLTN_MT';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PND_AFLTN_MT CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PND_AFLTN_MT
(
      FDOC_NBR VARCHAR2(14)
        , ENTITY_AFLTN_ID VARCHAR2(40)
        , AFLTN_TYP_CD VARCHAR2(40)
        , CAMPUS_CD VARCHAR2(2)
        , EDIT_FLAG VARCHAR2(1) default 'N'
        , DFLT_IND VARCHAR2(1) default 'N'
        , ACTV_IND VARCHAR2(1) default 'Y'
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
    

)
/

ALTER TABLE KRIM_PND_AFLTN_MT
    ADD CONSTRAINT KRIM_PND_AFLTN_MTP1
PRIMARY KEY (FDOC_NBR,ENTITY_AFLTN_ID)
/







-----------------------------------------------------------------------------
-- KRIM_PND_CTZNSHP_MT
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PND_CTZNSHP_MT';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PND_CTZNSHP_MT CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PND_CTZNSHP_MT
(
      FDOC_NBR VARCHAR2(14)
        , ENTITY_CTZNSHP_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , POSTAL_CNTRY_CD VARCHAR2(2)
        , CTZNSHP_STAT_CD VARCHAR2(40)
        , STRT_DT DATE
        , END_DT DATE
        , ACTV_IND VARCHAR2(1) default 'Y'
        , EDIT_FLAG VARCHAR2(1) default 'N'
    

)
/

ALTER TABLE KRIM_PND_CTZNSHP_MT
    ADD CONSTRAINT KRIM_PND_CTZNSHP_MTP1
PRIMARY KEY (FDOC_NBR,ENTITY_CTZNSHP_ID)
/







-----------------------------------------------------------------------------
-- KRIM_PND_DLGN_MBR_ATTR_DATA_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PND_DLGN_MBR_ATTR_DATA_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PND_DLGN_MBR_ATTR_DATA_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PND_DLGN_MBR_ATTR_DATA_T
(
      FDOC_NBR VARCHAR2(14)
        , ATTR_DATA_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , DLGN_MBR_ID VARCHAR2(40)
        , KIM_TYP_ID VARCHAR2(40)
        , KIM_ATTR_DEFN_ID VARCHAR2(40)
        , ATTR_VAL VARCHAR2(400)
        , ACTV_IND VARCHAR2(1) default 'Y'
        , EDIT_FLAG VARCHAR2(1) default 'N'
    

)
/

ALTER TABLE KRIM_PND_DLGN_MBR_ATTR_DATA_T
    ADD CONSTRAINT KRIM_PND_DLGN_MBR_ATTR_DATAP1
PRIMARY KEY (FDOC_NBR,ATTR_DATA_ID)
/







-----------------------------------------------------------------------------
-- KRIM_PND_DLGN_MBR_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PND_DLGN_MBR_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PND_DLGN_MBR_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PND_DLGN_MBR_T
(
      FDOC_NBR VARCHAR2(14)
        , DLGN_MBR_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , DLGN_ID VARCHAR2(40) NOT NULL
        , MBR_ID VARCHAR2(40)
        , MBR_NM VARCHAR2(40)
        , MBR_TYP_CD VARCHAR2(40) NOT NULL
        , ACTV_IND VARCHAR2(1) default 'Y'
        , ACTV_FRM_DT DATE
        , ACTV_TO_DT DATE
        , ROLE_MBR_ID VARCHAR2(40)
    

)
/

ALTER TABLE KRIM_PND_DLGN_MBR_T
    ADD CONSTRAINT KRIM_PND_DLGN_MBR_TP1
PRIMARY KEY (FDOC_NBR,DLGN_MBR_ID)
/







-----------------------------------------------------------------------------
-- KRIM_PND_DLGN_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PND_DLGN_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PND_DLGN_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PND_DLGN_T
(
      FDOC_NBR VARCHAR2(14)
        , DLGN_ID VARCHAR2(40)
        , ROLE_ID VARCHAR2(40) NOT NULL
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , KIM_TYP_ID VARCHAR2(40)
        , DLGN_TYP_CD VARCHAR2(100) NOT NULL
        , ACTV_IND VARCHAR2(1) default 'Y'
    

)
/

ALTER TABLE KRIM_PND_DLGN_T
    ADD CONSTRAINT KRIM_PND_DLGN_TP1
PRIMARY KEY (FDOC_NBR,DLGN_ID)
/







-----------------------------------------------------------------------------
-- KRIM_PND_EMAIL_MT
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PND_EMAIL_MT';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PND_EMAIL_MT CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PND_EMAIL_MT
(
      FDOC_NBR VARCHAR2(14)
        , ENTITY_EMAIL_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ENT_TYP_CD VARCHAR2(40)
        , EMAIL_TYP_CD VARCHAR2(40)
        , EMAIL_ADDR VARCHAR2(200)
        , DFLT_IND VARCHAR2(1) default 'N'
        , ACTV_IND VARCHAR2(1) default 'Y'
        , EDIT_FLAG VARCHAR2(1) default 'N'
    

)
/

ALTER TABLE KRIM_PND_EMAIL_MT
    ADD CONSTRAINT KRIM_PND_EMAIL_MTP1
PRIMARY KEY (FDOC_NBR,ENTITY_EMAIL_ID)
/







-----------------------------------------------------------------------------
-- KRIM_PND_EMP_INFO_MT
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PND_EMP_INFO_MT';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PND_EMP_INFO_MT CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PND_EMP_INFO_MT
(
      FDOC_NBR VARCHAR2(14)
        , PRMRY_DEPT_CD VARCHAR2(40)
        , ENTITY_EMP_ID VARCHAR2(40)
        , EMP_ID VARCHAR2(40)
        , EMP_REC_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ENTITY_AFLTN_ID VARCHAR2(40)
        , EMP_STAT_CD VARCHAR2(40)
        , EMP_TYP_CD VARCHAR2(40)
        , BASE_SLRY_AMT NUMBER(15,2)
        , PRMRY_IND VARCHAR2(1)
        , ACTV_IND VARCHAR2(1) default 'Y'
        , EDIT_FLAG VARCHAR2(1) default 'N'
    

)
/

ALTER TABLE KRIM_PND_EMP_INFO_MT
    ADD CONSTRAINT KRIM_PND_EMP_INFO_MTP1
PRIMARY KEY (FDOC_NBR,ENTITY_EMP_ID)
/







-----------------------------------------------------------------------------
-- KRIM_PND_GRP_ATTR_DATA_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PND_GRP_ATTR_DATA_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PND_GRP_ATTR_DATA_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PND_GRP_ATTR_DATA_T
(
      FDOC_NBR VARCHAR2(14)
        , ATTR_DATA_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , GRP_ID VARCHAR2(40)
        , KIM_TYP_ID VARCHAR2(40)
        , KIM_ATTR_DEFN_ID VARCHAR2(40)
        , ATTR_VAL VARCHAR2(400)
    

)
/

ALTER TABLE KRIM_PND_GRP_ATTR_DATA_T
    ADD CONSTRAINT KRIM_PND_GRP_ATTR_DATA_TP1
PRIMARY KEY (FDOC_NBR,ATTR_DATA_ID)
/







-----------------------------------------------------------------------------
-- KRIM_PND_GRP_MBR_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PND_GRP_MBR_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PND_GRP_MBR_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PND_GRP_MBR_T
(
      FDOC_NBR VARCHAR2(14)
        , GRP_MBR_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , GRP_ID VARCHAR2(40) NOT NULL
        , MBR_ID VARCHAR2(40)
        , MBR_NM VARCHAR2(40)
        , MBR_TYP_CD VARCHAR2(40) NOT NULL
        , ACTV_FRM_DT DATE
        , ACTV_TO_DT DATE
    

)
/

ALTER TABLE KRIM_PND_GRP_MBR_T
    ADD CONSTRAINT KRIM_PND_GRP_MBR_TP1
PRIMARY KEY (FDOC_NBR,GRP_MBR_ID)
/







-----------------------------------------------------------------------------
-- KRIM_PND_GRP_PRNCPL_MT
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PND_GRP_PRNCPL_MT';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PND_GRP_PRNCPL_MT CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PND_GRP_PRNCPL_MT
(
      GRP_MBR_ID VARCHAR2(40)
        , FDOC_NBR VARCHAR2(14)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , GRP_ID VARCHAR2(40) NOT NULL
        , PRNCPL_ID VARCHAR2(40)
        , ACTV_IND VARCHAR2(1) default 'Y'
        , GRP_NM VARCHAR2(80) NOT NULL
        , GRP_TYPE VARCHAR2(80)
        , KIM_TYP_ID VARCHAR2(40)
        , NMSPC_CD VARCHAR2(40)
        , ACTV_FRM_DT DATE
        , ACTV_TO_DT DATE
        , EDIT_FLAG VARCHAR2(1) default 'N'
    

)
/

ALTER TABLE KRIM_PND_GRP_PRNCPL_MT
    ADD CONSTRAINT KRIM_PND_GRP_PRNCPL_MTP1
PRIMARY KEY (GRP_MBR_ID,FDOC_NBR)
/







-----------------------------------------------------------------------------
-- KRIM_PND_NM_MT
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PND_NM_MT';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PND_NM_MT CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PND_NM_MT
(
      FDOC_NBR VARCHAR2(14)
        , ENTITY_NM_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , NM_TYP_CD VARCHAR2(40)
        , FIRST_NM VARCHAR2(40)
        , MIDDLE_NM VARCHAR2(40)
        , LAST_NM VARCHAR2(80)
        , SUFFIX_NM VARCHAR2(20)
        , TITLE_NM VARCHAR2(20)
        , DFLT_IND VARCHAR2(1) default 'N'
        , ACTV_IND VARCHAR2(1) default 'Y'
        , EDIT_FLAG VARCHAR2(1) default 'N'
    

)
/

ALTER TABLE KRIM_PND_NM_MT
    ADD CONSTRAINT KRIM_PND_NM_MTP1
PRIMARY KEY (FDOC_NBR,ENTITY_NM_ID)
/







-----------------------------------------------------------------------------
-- KRIM_PND_PHONE_MT
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PND_PHONE_MT';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PND_PHONE_MT CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PND_PHONE_MT
(
      FDOC_NBR VARCHAR2(14)
        , ENTITY_PHONE_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ENT_TYP_CD VARCHAR2(40)
        , PHONE_TYP_CD VARCHAR2(40)
        , PHONE_NBR VARCHAR2(20)
        , PHONE_EXTN_NBR VARCHAR2(8)
        , POSTAL_CNTRY_CD VARCHAR2(2)
        , DFLT_IND VARCHAR2(1) default 'N'
        , ACTV_IND VARCHAR2(1) default 'Y'
        , EDIT_FLAG VARCHAR2(1) default 'N'
    

)
/

ALTER TABLE KRIM_PND_PHONE_MT
    ADD CONSTRAINT KRIM_PND_PHONE_MTP1
PRIMARY KEY (FDOC_NBR,ENTITY_PHONE_ID)
/







-----------------------------------------------------------------------------
-- KRIM_PND_PRIV_PREF_MT
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PND_PRIV_PREF_MT';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PND_PRIV_PREF_MT CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PND_PRIV_PREF_MT
(
      FDOC_NBR VARCHAR2(14)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , SUPPRESS_NM_IND VARCHAR2(1) default 'N'
        , SUPPRESS_EMAIL_IND VARCHAR2(1) default 'Y'
        , SUPPRESS_ADDR_IND VARCHAR2(1) default 'Y'
        , SUPPRESS_PHONE_IND VARCHAR2(1) default 'Y'
        , SUPPRESS_PRSNL_IND VARCHAR2(1) default 'Y'
        , EDIT_FLAG VARCHAR2(1) default 'N'
    

)
/

ALTER TABLE KRIM_PND_PRIV_PREF_MT
    ADD CONSTRAINT KRIM_PND_PRIV_PREF_MTP1
PRIMARY KEY (FDOC_NBR)
/







-----------------------------------------------------------------------------
-- KRIM_PND_ROLE_MBR_ATTR_DATA_MT
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PND_ROLE_MBR_ATTR_DATA_MT';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PND_ROLE_MBR_ATTR_DATA_MT CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PND_ROLE_MBR_ATTR_DATA_MT
(
      FDOC_NBR VARCHAR2(14)
        , ATTR_DATA_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ROLE_MBR_ID VARCHAR2(40)
        , KIM_TYP_ID VARCHAR2(40)
        , KIM_ATTR_DEFN_ID VARCHAR2(40)
        , ATTR_VAL VARCHAR2(400)
        , ACTV_IND VARCHAR2(1) default 'Y'
        , EDIT_FLAG VARCHAR2(1) default 'N'
    

)
/

ALTER TABLE KRIM_PND_ROLE_MBR_ATTR_DATA_MT
    ADD CONSTRAINT KRIM_PND_ROLE_MBR_ATTR_DATAP1
PRIMARY KEY (FDOC_NBR,ATTR_DATA_ID)
/







-----------------------------------------------------------------------------
-- KRIM_PND_ROLE_MBR_MT
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PND_ROLE_MBR_MT';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PND_ROLE_MBR_MT CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PND_ROLE_MBR_MT
(
      FDOC_NBR VARCHAR2(14)
        , ROLE_MBR_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ROLE_ID VARCHAR2(40) NOT NULL
        , MBR_ID VARCHAR2(40)
        , MBR_TYP_CD VARCHAR2(40) NOT NULL
        , ACTV_IND VARCHAR2(1) default 'Y'
        , ACTV_FRM_DT DATE
        , ACTV_TO_DT DATE
        , EDIT_FLAG VARCHAR2(1) default 'N'
    

)
/

ALTER TABLE KRIM_PND_ROLE_MBR_MT
    ADD CONSTRAINT KRIM_PND_ROLE_MBR_MTP1
PRIMARY KEY (FDOC_NBR,ROLE_MBR_ID)
/







-----------------------------------------------------------------------------
-- KRIM_PND_ROLE_MT
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PND_ROLE_MT';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PND_ROLE_MT CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PND_ROLE_MT
(
      FDOC_NBR VARCHAR2(14)
        , ROLE_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ROLE_NM VARCHAR2(100) NOT NULL
        , KIM_TYP_ID VARCHAR2(40)
        , ACTV_IND VARCHAR2(1) default 'Y'
        , NMSPC_CD VARCHAR2(40)
        , EDIT_FLAG VARCHAR2(1) default 'N'
    

)
/

ALTER TABLE KRIM_PND_ROLE_MT
    ADD CONSTRAINT KRIM_PND_ROLE_MTP1
PRIMARY KEY (FDOC_NBR,ROLE_ID)
/







-----------------------------------------------------------------------------
-- KRIM_PND_ROLE_PERM_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PND_ROLE_PERM_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PND_ROLE_PERM_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PND_ROLE_PERM_T
(
      FDOC_NBR VARCHAR2(14)
        , ROLE_PERM_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ROLE_ID VARCHAR2(40) NOT NULL
        , PERM_ID VARCHAR2(40) NOT NULL
        , ACTV_IND VARCHAR2(1) default 'Y'
    

)
/

ALTER TABLE KRIM_PND_ROLE_PERM_T
    ADD CONSTRAINT KRIM_PND_ROLE_PERM_TP1
PRIMARY KEY (FDOC_NBR,ROLE_PERM_ID)
/







-----------------------------------------------------------------------------
-- KRIM_PND_ROLE_RSP_ACTN_MT
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PND_ROLE_RSP_ACTN_MT';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PND_ROLE_RSP_ACTN_MT CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PND_ROLE_RSP_ACTN_MT
(
      ROLE_RSP_ACTN_ID VARCHAR2(40)
        , FDOC_NBR VARCHAR2(14)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ACTN_TYP_CD VARCHAR2(40)
        , PRIORITY_NBR NUMBER(3)
        , ACTN_PLCY_CD VARCHAR2(40)
        , ROLE_MBR_ID VARCHAR2(40)
        , ROLE_RSP_ID VARCHAR2(40)
        , EDIT_FLAG VARCHAR2(1) default 'N'
        , FRC_ACTN VARCHAR2(1)
    

)
/

ALTER TABLE KRIM_PND_ROLE_RSP_ACTN_MT
    ADD CONSTRAINT KRIM_PND_ROLE_RSP_ACTN_MTP1
PRIMARY KEY (ROLE_RSP_ACTN_ID,FDOC_NBR)
/







-----------------------------------------------------------------------------
-- KRIM_PND_ROLE_RSP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PND_ROLE_RSP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PND_ROLE_RSP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PND_ROLE_RSP_T
(
      FDOC_NBR VARCHAR2(14)
        , ROLE_RSP_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ROLE_ID VARCHAR2(40) NOT NULL
        , RSP_ID VARCHAR2(40) NOT NULL
        , ACTV_IND VARCHAR2(1) default 'Y'
    

)
/

ALTER TABLE KRIM_PND_ROLE_RSP_T
    ADD CONSTRAINT KRIM_PND_ROLE_RSP_TP1
PRIMARY KEY (FDOC_NBR,ROLE_RSP_ID)
/







-----------------------------------------------------------------------------
-- KRIM_PRNCPL_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_PRNCPL_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_PRNCPL_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_PRNCPL_T
(
      PRNCPL_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , PRNCPL_NM VARCHAR2(100) NOT NULL
        , ENTITY_ID VARCHAR2(40)
        , PRNCPL_PSWD VARCHAR2(400)
        , ACTV_IND VARCHAR2(1) default 'Y'
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_PRNCPL_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_PRNCPL_TC1 UNIQUE (PRNCPL_NM)

)
/

ALTER TABLE KRIM_PRNCPL_T
    ADD CONSTRAINT KRIM_PRNCPL_TP1
PRIMARY KEY (PRNCPL_ID)
/







-----------------------------------------------------------------------------
-- KRIM_ROLE_DOCUMENT_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ROLE_DOCUMENT_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ROLE_DOCUMENT_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ROLE_DOCUMENT_T
(
      FDOC_NBR VARCHAR2(14)
        , ROLE_ID VARCHAR2(40) NOT NULL
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ROLE_TYP_ID VARCHAR2(40) NOT NULL
        , ROLE_NMSPC VARCHAR2(100) NOT NULL
        , ROLE_NM VARCHAR2(400)
        , ACTV_IND VARCHAR2(1) default 'Y'
        , DESC_TXT VARCHAR2(4000)
    

)
/

ALTER TABLE KRIM_ROLE_DOCUMENT_T
    ADD CONSTRAINT KRIM_ROLE_DOCUMENT_TP1
PRIMARY KEY (FDOC_NBR)
/







-----------------------------------------------------------------------------
-- KRIM_ROLE_MBR_ATTR_DATA_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ROLE_MBR_ATTR_DATA_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ROLE_MBR_ATTR_DATA_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ROLE_MBR_ATTR_DATA_T
(
      ATTR_DATA_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ROLE_MBR_ID VARCHAR2(40)
        , KIM_TYP_ID VARCHAR2(40) NOT NULL
        , KIM_ATTR_DEFN_ID VARCHAR2(40)
        , ATTR_VAL VARCHAR2(400)
    
    , CONSTRAINT KRIM_ROLE_MBR_ATTR_DATA_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_ROLE_MBR_ATTR_DATA_T
    ADD CONSTRAINT KRIM_ROLE_MBR_ATTR_DATA_TP1
PRIMARY KEY (ATTR_DATA_ID)
/


CREATE INDEX KRIM_ROLE_MBR_ATTR_DATA_TI1 
  ON KRIM_ROLE_MBR_ATTR_DATA_T 
  (ROLE_MBR_ID)
/





-----------------------------------------------------------------------------
-- KRIM_ROLE_MBR_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ROLE_MBR_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ROLE_MBR_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ROLE_MBR_T
(
      ROLE_MBR_ID VARCHAR2(40)
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , OBJ_ID VARCHAR2(36) NOT NULL
        , ROLE_ID VARCHAR2(40)
        , MBR_ID VARCHAR2(40)
        , MBR_TYP_CD CHAR(1) default 'P'
        , ACTV_FRM_DT DATE
        , ACTV_TO_DT DATE
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_ROLE_MBR_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_ROLE_MBR_T
    ADD CONSTRAINT KRIM_ROLE_MBR_TP1
PRIMARY KEY (ROLE_MBR_ID)
/


CREATE INDEX KRIM_ROLE_MBR_TI1 
  ON KRIM_ROLE_MBR_T 
  (MBR_ID)
/





-----------------------------------------------------------------------------
-- KRIM_ROLE_PERM_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ROLE_PERM_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ROLE_PERM_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ROLE_PERM_T
(
      ROLE_PERM_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ROLE_ID VARCHAR2(40)
        , PERM_ID VARCHAR2(40)
        , ACTV_IND VARCHAR2(1) default 'Y'
    
    , CONSTRAINT KRIM_ROLE_PERM_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_ROLE_PERM_T
    ADD CONSTRAINT KRIM_ROLE_PERM_TP1
PRIMARY KEY (ROLE_PERM_ID)
/


CREATE INDEX KRIM_ROLE_PERM_TI1 
  ON KRIM_ROLE_PERM_T 
  (PERM_ID)
/





-----------------------------------------------------------------------------
-- KRIM_ROLE_RSP_ACTN_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ROLE_RSP_ACTN_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ROLE_RSP_ACTN_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ROLE_RSP_ACTN_T
(
      ROLE_RSP_ACTN_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ACTN_TYP_CD VARCHAR2(40)
        , PRIORITY_NBR NUMBER(3)
        , ACTN_PLCY_CD VARCHAR2(40)
        , ROLE_MBR_ID VARCHAR2(40)
        , ROLE_RSP_ID VARCHAR2(40)
        , FRC_ACTN VARCHAR2(1) default 'N'
    
    , CONSTRAINT KRIM_ROLE_RSP_ACTN_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_ROLE_RSP_ACTN_TC1 UNIQUE (ROLE_RSP_ID, ROLE_MBR_ID)

)
/

ALTER TABLE KRIM_ROLE_RSP_ACTN_T
    ADD CONSTRAINT KRIM_ROLE_RSP_ACTN_TP1
PRIMARY KEY (ROLE_RSP_ACTN_ID)
/







-----------------------------------------------------------------------------
-- KRIM_ROLE_RSP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ROLE_RSP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ROLE_RSP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ROLE_RSP_T
(
      ROLE_RSP_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ROLE_ID VARCHAR2(40)
        , RSP_ID VARCHAR2(40)
        , ACTV_IND VARCHAR2(1) default 'Y'
    
    , CONSTRAINT KRIM_ROLE_RSP_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_ROLE_RSP_T
    ADD CONSTRAINT KRIM_ROLE_RSP_TP1
PRIMARY KEY (ROLE_RSP_ID)
/


CREATE INDEX KRIM_ROLE_RSP_TI1 
  ON KRIM_ROLE_RSP_T 
  (RSP_ID)
/





-----------------------------------------------------------------------------
-- KRIM_ROLE_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_ROLE_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_ROLE_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_ROLE_T
(
      ROLE_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ROLE_NM VARCHAR2(80) NOT NULL
        , NMSPC_CD VARCHAR2(40) NOT NULL
        , DESC_TXT VARCHAR2(4000)
        , KIM_TYP_ID VARCHAR2(40) NOT NULL
        , ACTV_IND VARCHAR2(1) default 'Y'
        , LAST_UPDT_DT DATE default SYSDATE
    
    , CONSTRAINT KRIM_ROLE_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_ROLE_TC1 UNIQUE (ROLE_NM, NMSPC_CD)

)
/

ALTER TABLE KRIM_ROLE_T
    ADD CONSTRAINT KRIM_ROLE_TP1
PRIMARY KEY (ROLE_ID)
/







-----------------------------------------------------------------------------
-- KRIM_RSP_ATTR_DATA_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_RSP_ATTR_DATA_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_RSP_ATTR_DATA_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_RSP_ATTR_DATA_T
(
      ATTR_DATA_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , RSP_ID VARCHAR2(40)
        , KIM_TYP_ID VARCHAR2(40) NOT NULL
        , KIM_ATTR_DEFN_ID VARCHAR2(40)
        , ATTR_VAL VARCHAR2(400)
    
    , CONSTRAINT KRIM_RSP_ATTR_DATA_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_RSP_ATTR_DATA_T
    ADD CONSTRAINT KRIM_RSP_ATTR_DATA_TP1
PRIMARY KEY (ATTR_DATA_ID)
/







-----------------------------------------------------------------------------
-- KRIM_RSP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_RSP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_RSP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_RSP_T
(
      RSP_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , RSP_TMPL_ID VARCHAR2(40)
        , NMSPC_CD VARCHAR2(40)
        , NM VARCHAR2(100)
        , DESC_TXT VARCHAR2(400)
        , ACTV_IND VARCHAR2(1) default 'Y'
    
    , CONSTRAINT KRIM_RSP_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_RSP_T
    ADD CONSTRAINT KRIM_RSP_TP1
PRIMARY KEY (RSP_ID)
/







-----------------------------------------------------------------------------
-- KRIM_RSP_TMPL_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_RSP_TMPL_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_RSP_TMPL_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_RSP_TMPL_T
(
      RSP_TMPL_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , NMSPC_CD VARCHAR2(40)
        , NM VARCHAR2(80)
        , KIM_TYP_ID VARCHAR2(100) NOT NULL
        , DESC_TXT VARCHAR2(400)
        , ACTV_IND VARCHAR2(1) default 'Y'
    
    , CONSTRAINT KRIM_RSP_TMPL_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_RSP_TMPL_T
    ADD CONSTRAINT KRIM_RSP_TMPL_TP1
PRIMARY KEY (RSP_TMPL_ID)
/







-----------------------------------------------------------------------------
-- KRIM_TYP_ATTR_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_TYP_ATTR_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_TYP_ATTR_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_TYP_ATTR_T
(
      KIM_TYP_ATTR_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , SORT_CD VARCHAR2(2)
        , KIM_TYP_ID VARCHAR2(40) NOT NULL
        , KIM_ATTR_DEFN_ID VARCHAR2(40)
        , ACTV_IND VARCHAR2(1) default 'Y'
    
    , CONSTRAINT KRIM_TYP_ATTR_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_TYP_ATTR_T
    ADD CONSTRAINT KRIM_TYP_ATTR_TP1
PRIMARY KEY (KIM_TYP_ATTR_ID)
/


CREATE INDEX KRIM_TYP_ATTRIBUTE_TI1 
  ON KRIM_TYP_ATTR_T 
  (KIM_TYP_ID)
/





-----------------------------------------------------------------------------
-- KRIM_TYP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRIM_TYP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRIM_TYP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRIM_TYP_T
(
      KIM_TYP_ID VARCHAR2(40)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , NM VARCHAR2(100)
        , SRVC_NM VARCHAR2(200)
        , ACTV_IND VARCHAR2(1) default 'Y'
        , NMSPC_CD VARCHAR2(40)
    
    , CONSTRAINT KRIM_TYP_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRIM_TYP_T
    ADD CONSTRAINT KRIM_TYP_TP1
PRIMARY KEY (KIM_TYP_ID)
/







-----------------------------------------------------------------------------
-- KRNS_ADHOC_RTE_ACTN_RECIP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRNS_ADHOC_RTE_ACTN_RECIP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRNS_ADHOC_RTE_ACTN_RECIP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRNS_ADHOC_RTE_ACTN_RECIP_T
(
      RECIP_TYP_CD NUMBER(1)
        , ACTN_RQST_CD VARCHAR2(30)
        , ACTN_RQST_RECIP_ID VARCHAR2(70)
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , DOC_HDR_ID VARCHAR2(14)
    
    , CONSTRAINT KRNS_ADHOC_RTE_ACTN_RECIP_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRNS_ADHOC_RTE_ACTN_RECIP_T
    ADD CONSTRAINT KRNS_ADHOC_RTE_ACTN_RECIP_TP1
PRIMARY KEY (RECIP_TYP_CD,ACTN_RQST_CD,ACTN_RQST_RECIP_ID,DOC_HDR_ID)
/


CREATE INDEX KRNS_ADHOC_RTE_ACTN_RECIP_T2 
  ON KRNS_ADHOC_RTE_ACTN_RECIP_T 
  (DOC_HDR_ID)
/





-----------------------------------------------------------------------------
-- KRNS_ATT_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRNS_ATT_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRNS_ATT_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRNS_ATT_T
(
      NTE_ID NUMBER(14)
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , MIME_TYP VARCHAR2(255)
        , FILE_NM VARCHAR2(250)
        , ATT_ID VARCHAR2(36)
        , FILE_SZ NUMBER(14)
        , ATT_TYP_CD VARCHAR2(40)
    
    , CONSTRAINT KRNS_ATT_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRNS_ATT_T
    ADD CONSTRAINT KRNS_ATT_TP1
PRIMARY KEY (NTE_ID)
/







-----------------------------------------------------------------------------
-- KRNS_CAMPUS_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRNS_CAMPUS_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRNS_CAMPUS_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRNS_CAMPUS_T
(
      CAMPUS_CD VARCHAR2(2)
        , CAMPUS_NM VARCHAR2(250)
        , CAMPUS_SHRT_NM VARCHAR2(250)
        , CAMPUS_TYP_CD VARCHAR2(1)
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ACTV_IND VARCHAR2(1) default 'Y' NOT NULL
    
    , CONSTRAINT KRNS_CAMPUS_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRNS_CAMPUS_T
    ADD CONSTRAINT KRNS_CAMPUS_TP1
PRIMARY KEY (CAMPUS_CD)
/







-----------------------------------------------------------------------------
-- KRNS_CMP_TYP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRNS_CMP_TYP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRNS_CMP_TYP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRNS_CMP_TYP_T
(
      CAMPUS_TYP_CD VARCHAR2(1)
        , CMP_TYP_NM VARCHAR2(250)
        , DOBJ_MAINT_CD_ACTV_IND VARCHAR2(1) NOT NULL
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , ACTV_IND VARCHAR2(1) default 'Y' NOT NULL
    
    , CONSTRAINT KRNS_CMP_TYP_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRNS_CMP_TYP_T
    ADD CONSTRAINT KRNS_CMP_TYP_TP1
PRIMARY KEY (CAMPUS_TYP_CD)
/







-----------------------------------------------------------------------------
-- KRNS_DOC_HDR_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRNS_DOC_HDR_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRNS_DOC_HDR_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRNS_DOC_HDR_T
(
      DOC_HDR_ID VARCHAR2(14)
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , FDOC_DESC VARCHAR2(40)
        , ORG_DOC_HDR_ID VARCHAR2(10)
        , TMPL_DOC_HDR_ID VARCHAR2(14)
        , EXPLANATION VARCHAR2(400)
    
    , CONSTRAINT KRNS_DOC_HDR_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRNS_DOC_HDR_T
    ADD CONSTRAINT KRNS_DOC_HDR_TP1
PRIMARY KEY (DOC_HDR_ID)
/


CREATE INDEX KRNS_DOC_HDR_TI3 
  ON KRNS_DOC_HDR_T 
  (ORG_DOC_HDR_ID)
/





-----------------------------------------------------------------------------
-- KRNS_LOOKUP_RSLT_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRNS_LOOKUP_RSLT_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRNS_LOOKUP_RSLT_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRNS_LOOKUP_RSLT_T
(
      LOOKUP_RSLT_ID VARCHAR2(14)
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , PRNCPL_ID VARCHAR2(40) NOT NULL
        , LOOKUP_DT DATE NOT NULL
        , SERIALZD_RSLTS CLOB
    
    , CONSTRAINT KRNS_LOOKUP_RSLT_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRNS_LOOKUP_RSLT_T
    ADD CONSTRAINT KRNS_LOOKUP_RSLT_TP1
PRIMARY KEY (LOOKUP_RSLT_ID)
/







-----------------------------------------------------------------------------
-- KRNS_LOOKUP_SEL_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRNS_LOOKUP_SEL_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRNS_LOOKUP_SEL_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRNS_LOOKUP_SEL_T
(
      LOOKUP_RSLT_ID VARCHAR2(14)
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , PRNCPL_ID VARCHAR2(40) NOT NULL
        , LOOKUP_DT DATE NOT NULL
        , SEL_OBJ_IDS CLOB
    
    , CONSTRAINT KRNS_LOOKUP_SEL_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRNS_LOOKUP_SEL_T
    ADD CONSTRAINT KRNS_LOOKUP_SEL_TP1
PRIMARY KEY (LOOKUP_RSLT_ID)
/







-----------------------------------------------------------------------------
-- KRNS_MAINT_DOC_ATT_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRNS_MAINT_DOC_ATT_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRNS_MAINT_DOC_ATT_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRNS_MAINT_DOC_ATT_T
(
      DOC_HDR_ID VARCHAR2(14)
        , ATT_CNTNT BLOB NOT NULL
        , FILE_NM VARCHAR2(150)
        , CNTNT_TYP VARCHAR2(255)
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
    
    , CONSTRAINT KRNS_MAINT_DOC_ATT_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRNS_MAINT_DOC_ATT_T
    ADD CONSTRAINT KRNS_MAINT_DOC_ATT_TP1
PRIMARY KEY (DOC_HDR_ID)
/







-----------------------------------------------------------------------------
-- KRNS_MAINT_DOC_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRNS_MAINT_DOC_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRNS_MAINT_DOC_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRNS_MAINT_DOC_T
(
      DOC_HDR_ID VARCHAR2(14)
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , DOC_CNTNT CLOB
    
    , CONSTRAINT KRNS_MAINT_DOC_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRNS_MAINT_DOC_T
    ADD CONSTRAINT KRNS_MAINT_DOC_TP1
PRIMARY KEY (DOC_HDR_ID)
/







-----------------------------------------------------------------------------
-- KRNS_MAINT_LOCK_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRNS_MAINT_LOCK_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRNS_MAINT_LOCK_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRNS_MAINT_LOCK_T
(
      MAINT_LOCK_REP_TXT VARCHAR2(500)
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , DOC_HDR_ID VARCHAR2(14) NOT NULL
        , MAINT_LOCK_ID VARCHAR2(14)
    
    , CONSTRAINT KRNS_MAINT_LOCK_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRNS_MAINT_LOCK_T
    ADD CONSTRAINT KRNS_MAINT_LOCK_TP1
PRIMARY KEY (MAINT_LOCK_ID)
/


CREATE INDEX KRNS_MAINT_LOCK_TI2 
  ON KRNS_MAINT_LOCK_T 
  (DOC_HDR_ID)
/





-----------------------------------------------------------------------------
-- KRNS_NMSPC_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRNS_NMSPC_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRNS_NMSPC_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRNS_NMSPC_T
(
      NMSPC_CD VARCHAR2(20)
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , NM VARCHAR2(40)
        , ACTV_IND CHAR(1) default 'Y' NOT NULL
        , APPL_NMSPC_CD VARCHAR2(20)
    
    , CONSTRAINT KRNS_NMSPC_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRNS_NMSPC_T
    ADD CONSTRAINT KRNS_NMSPC_TP1
PRIMARY KEY (NMSPC_CD)
/







-----------------------------------------------------------------------------
-- KRNS_NTE_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRNS_NTE_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRNS_NTE_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRNS_NTE_T
(
      NTE_ID NUMBER(14)
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , RMT_OBJ_ID VARCHAR2(36) NOT NULL
        , AUTH_PRNCPL_ID VARCHAR2(40) NOT NULL
        , POST_TS DATE NOT NULL
        , NTE_TYP_CD VARCHAR2(4) NOT NULL
        , TXT VARCHAR2(800)
        , PRG_CD VARCHAR2(1)
        , TPC_TXT VARCHAR2(40)
    
    , CONSTRAINT KRNS_NTE_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRNS_NTE_T
    ADD CONSTRAINT KRNS_NTE_TP1
PRIMARY KEY (NTE_ID)
/







-----------------------------------------------------------------------------
-- KRNS_NTE_TYP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRNS_NTE_TYP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRNS_NTE_TYP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRNS_NTE_TYP_T
(
      NTE_TYP_CD VARCHAR2(4)
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , TYP_DESC_TXT VARCHAR2(100)
        , ACTV_IND VARCHAR2(1)
    
    , CONSTRAINT KRNS_NTE_TYP_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRNS_NTE_TYP_T
    ADD CONSTRAINT KRNS_NTE_TYP_TP1
PRIMARY KEY (NTE_TYP_CD)
/







-----------------------------------------------------------------------------
-- KRNS_PARM_DTL_TYP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRNS_PARM_DTL_TYP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRNS_PARM_DTL_TYP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRNS_PARM_DTL_TYP_T
(
      NMSPC_CD VARCHAR2(20)
        , PARM_DTL_TYP_CD VARCHAR2(100)
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , NM VARCHAR2(255)
        , ACTV_IND CHAR(1) default 'Y' NOT NULL
    
    , CONSTRAINT KRNS_PARM_DTL_TYP_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRNS_PARM_DTL_TYP_T
    ADD CONSTRAINT KRNS_PARM_DTL_TYP_TP1
PRIMARY KEY (NMSPC_CD,PARM_DTL_TYP_CD)
/







-----------------------------------------------------------------------------
-- KRNS_PARM_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRNS_PARM_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRNS_PARM_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRNS_PARM_T
(
      NMSPC_CD VARCHAR2(20)
        , PARM_DTL_TYP_CD VARCHAR2(100)
        , PARM_NM VARCHAR2(255)
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , PARM_TYP_CD VARCHAR2(5) NOT NULL
        , TXT VARCHAR2(4000)
        , PARM_DESC_TXT VARCHAR2(4000)
        , CONS_CD VARCHAR2(1)
        , APPL_NMSPC_CD VARCHAR2(20) default 'KUALI'
    
    , CONSTRAINT KRNS_PARM_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRNS_PARM_T
    ADD CONSTRAINT KRNS_PARM_TP1
PRIMARY KEY (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,APPL_NMSPC_CD)
/







-----------------------------------------------------------------------------
-- KRNS_PARM_TYP_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRNS_PARM_TYP_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRNS_PARM_TYP_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRNS_PARM_TYP_T
(
      PARM_TYP_CD VARCHAR2(5)
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , NM VARCHAR2(40)
        , ACTV_IND CHAR(1) default 'Y' NOT NULL
    
    , CONSTRAINT KRNS_PARM_TYP_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRNS_PARM_TYP_T
    ADD CONSTRAINT KRNS_PARM_TYP_TP1
PRIMARY KEY (PARM_TYP_CD)
/







-----------------------------------------------------------------------------
-- KRNS_PESSIMISTIC_LOCK_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRNS_PESSIMISTIC_LOCK_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRNS_PESSIMISTIC_LOCK_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRNS_PESSIMISTIC_LOCK_T
(
      PESSIMISTIC_LOCK_ID NUMBER(14)
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , LOCK_DESC_TXT VARCHAR2(4000)
        , DOC_HDR_ID VARCHAR2(14) NOT NULL
        , GNRT_DT DATE NOT NULL
        , PRNCPL_ID VARCHAR2(40) NOT NULL
    
    , CONSTRAINT KRNS_PESSIMISTIC_LOCK_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRNS_PESSIMISTIC_LOCK_T
    ADD CONSTRAINT KRNS_PESSIMISTIC_LOCK_TP1
PRIMARY KEY (PESSIMISTIC_LOCK_ID)
/


CREATE INDEX KRNS_PESSIMISTIC_LOCK_TI1 
  ON KRNS_PESSIMISTIC_LOCK_T 
  (DOC_HDR_ID)
/
CREATE INDEX KRNS_PESSIMISTIC_LOCK_TI2 
  ON KRNS_PESSIMISTIC_LOCK_T 
  (PRNCPL_ID)
/





-----------------------------------------------------------------------------
-- KRNS_SESN_DOC_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRNS_SESN_DOC_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRNS_SESN_DOC_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRNS_SESN_DOC_T
(
      SESN_DOC_ID VARCHAR2(40)
        , DOC_HDR_ID VARCHAR2(14)
        , PRNCPL_ID VARCHAR2(40)
        , IP_ADDR VARCHAR2(60)
        , SERIALZD_DOC_FRM BLOB
        , LAST_UPDT_DT DATE
        , CONTENT_ENCRYPTED_IND CHAR(1) default 'N'
    

)
/

ALTER TABLE KRNS_SESN_DOC_T
    ADD CONSTRAINT KRNS_SESN_DOC_TP1
PRIMARY KEY (SESN_DOC_ID,DOC_HDR_ID,PRNCPL_ID,IP_ADDR)
/


CREATE INDEX KRNS_SESN_DOC_TI1 
  ON KRNS_SESN_DOC_T 
  (LAST_UPDT_DT)
/





-----------------------------------------------------------------------------
-- KRSB_BAM_PARM_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRSB_BAM_PARM_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRSB_BAM_PARM_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRSB_BAM_PARM_T
(
      BAM_PARM_ID NUMBER(14)
        , BAM_ID NUMBER(14) NOT NULL
        , PARM CLOB NOT NULL
    

)
/

ALTER TABLE KRSB_BAM_PARM_T
    ADD CONSTRAINT KRSB_BAM_PARM_TP1
PRIMARY KEY (BAM_PARM_ID)
/


CREATE INDEX KREW_BAM_PARM_TI1 
  ON KRSB_BAM_PARM_T 
  (BAM_ID)
/





-----------------------------------------------------------------------------
-- KRSB_BAM_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRSB_BAM_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRSB_BAM_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRSB_BAM_T
(
      BAM_ID NUMBER(14)
        , SVC_NM VARCHAR2(255) NOT NULL
        , SVC_URL VARCHAR2(500) NOT NULL
        , MTHD_NM VARCHAR2(2000) NOT NULL
        , THRD_NM VARCHAR2(500) NOT NULL
        , CALL_DT DATE NOT NULL
        , TGT_TO_STR VARCHAR2(2000) NOT NULL
        , SRVR_IND NUMBER(1) NOT NULL
        , EXCPN_TO_STR VARCHAR2(2000)
        , EXCPN_MSG CLOB
    

)
/

ALTER TABLE KRSB_BAM_T
    ADD CONSTRAINT KRSB_BAM_TP1
PRIMARY KEY (BAM_ID)
/


CREATE INDEX KRSB_BAM_TI1 
  ON KRSB_BAM_T 
  (SVC_NM, MTHD_NM)
/
CREATE INDEX KRSB_BAM_TI2 
  ON KRSB_BAM_T 
  (SVC_NM)
/





-----------------------------------------------------------------------------
-- KRSB_FLT_SVC_DEF_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRSB_FLT_SVC_DEF_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRSB_FLT_SVC_DEF_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRSB_FLT_SVC_DEF_T
(
      FLT_SVC_DEF_ID NUMBER(14)
        , FLT_SVC_DEF CLOB NOT NULL
    

)
/

ALTER TABLE KRSB_FLT_SVC_DEF_T
    ADD CONSTRAINT KRSB_FLT_SVC_DEF_TP1
PRIMARY KEY (FLT_SVC_DEF_ID)
/







-----------------------------------------------------------------------------
-- KRSB_MSG_PYLD_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRSB_MSG_PYLD_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRSB_MSG_PYLD_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRSB_MSG_PYLD_T
(
      MSG_QUE_ID NUMBER(14)
        , MSG_PYLD CLOB NOT NULL
    

)
/

ALTER TABLE KRSB_MSG_PYLD_T
    ADD CONSTRAINT KRSB_MSG_PYLD_TP1
PRIMARY KEY (MSG_QUE_ID)
/







-----------------------------------------------------------------------------
-- KRSB_MSG_QUE_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRSB_MSG_QUE_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRSB_MSG_QUE_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRSB_MSG_QUE_T
(
      MSG_QUE_ID NUMBER(14)
        , DT DATE NOT NULL
        , EXP_DT DATE
        , PRIO NUMBER(8) NOT NULL
        , STAT_CD CHAR(1) NOT NULL
        , RTRY_CNT NUMBER(8) NOT NULL
        , IP_NBR VARCHAR2(2000) NOT NULL
        , SVC_NM VARCHAR2(255)
        , SVC_NMSPC VARCHAR2(255) NOT NULL
        , SVC_MTHD_NM VARCHAR2(2000)
        , APP_VAL_ONE VARCHAR2(2000)
        , APP_VAL_TWO VARCHAR2(2000)
        , VER_NBR NUMBER(8) default 0
    

)
/

ALTER TABLE KRSB_MSG_QUE_T
    ADD CONSTRAINT KRSB_MSG_QUE_TP1
PRIMARY KEY (MSG_QUE_ID)
/


CREATE INDEX KRSB_MSG_QUE_TI1 
  ON KRSB_MSG_QUE_T 
  (SVC_NM, SVC_MTHD_NM)
/
CREATE INDEX KRSB_MSG_QUE_TI2 
  ON KRSB_MSG_QUE_T 
  (SVC_NMSPC, STAT_CD, IP_NBR, DT)
/





-----------------------------------------------------------------------------
-- KRSB_QRTZ_BLOB_TRIGGERS
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRSB_QRTZ_BLOB_TRIGGERS';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRSB_QRTZ_BLOB_TRIGGERS CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRSB_QRTZ_BLOB_TRIGGERS
(
      TRIGGER_NAME VARCHAR2(80)
        , TRIGGER_GROUP VARCHAR2(80)
        , BLOB_DATA BLOB
    

)
/

ALTER TABLE KRSB_QRTZ_BLOB_TRIGGERS
    ADD CONSTRAINT KRSB_QRTZ_BLOB_TRIGGERSP1
PRIMARY KEY (TRIGGER_NAME,TRIGGER_GROUP)
/







-----------------------------------------------------------------------------
-- KRSB_QRTZ_CALENDARS
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRSB_QRTZ_CALENDARS';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRSB_QRTZ_CALENDARS CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRSB_QRTZ_CALENDARS
(
      CALENDAR_NAME VARCHAR2(80)
        , CALENDAR BLOB NOT NULL
    

)
/

ALTER TABLE KRSB_QRTZ_CALENDARS
    ADD CONSTRAINT KRSB_QRTZ_CALENDARSP1
PRIMARY KEY (CALENDAR_NAME)
/







-----------------------------------------------------------------------------
-- KRSB_QRTZ_CRON_TRIGGERS
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRSB_QRTZ_CRON_TRIGGERS';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRSB_QRTZ_CRON_TRIGGERS CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRSB_QRTZ_CRON_TRIGGERS
(
      TRIGGER_NAME VARCHAR2(80)
        , TRIGGER_GROUP VARCHAR2(80)
        , CRON_EXPRESSION VARCHAR2(80) NOT NULL
        , TIME_ZONE_ID VARCHAR2(80)
    

)
/

ALTER TABLE KRSB_QRTZ_CRON_TRIGGERS
    ADD CONSTRAINT KRSB_QRTZ_CRON_TRIGGERSP1
PRIMARY KEY (TRIGGER_NAME,TRIGGER_GROUP)
/







-----------------------------------------------------------------------------
-- KRSB_QRTZ_FIRED_TRIGGERS
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRSB_QRTZ_FIRED_TRIGGERS';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRSB_QRTZ_FIRED_TRIGGERS CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRSB_QRTZ_FIRED_TRIGGERS
(
      ENTRY_ID VARCHAR2(95)
        , TRIGGER_NAME VARCHAR2(80) NOT NULL
        , TRIGGER_GROUP VARCHAR2(80) NOT NULL
        , IS_VOLATILE VARCHAR2(1) NOT NULL
        , INSTANCE_NAME VARCHAR2(80) NOT NULL
        , FIRED_TIME NUMBER(13) NOT NULL
        , PRIORITY NUMBER(13) NOT NULL
        , STATE VARCHAR2(16) NOT NULL
        , JOB_NAME VARCHAR2(80)
        , JOB_GROUP VARCHAR2(80)
        , IS_STATEFUL VARCHAR2(1)
        , REQUESTS_RECOVERY VARCHAR2(1)
    

)
/

ALTER TABLE KRSB_QRTZ_FIRED_TRIGGERS
    ADD CONSTRAINT KRSB_QRTZ_FIRED_TRIGGERSP1
PRIMARY KEY (ENTRY_ID)
/


CREATE INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI1 
  ON KRSB_QRTZ_FIRED_TRIGGERS 
  (JOB_GROUP)
/
CREATE INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI2 
  ON KRSB_QRTZ_FIRED_TRIGGERS 
  (JOB_NAME)
/
CREATE INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI3 
  ON KRSB_QRTZ_FIRED_TRIGGERS 
  (REQUESTS_RECOVERY)
/
CREATE INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI4 
  ON KRSB_QRTZ_FIRED_TRIGGERS 
  (IS_STATEFUL)
/
CREATE INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI5 
  ON KRSB_QRTZ_FIRED_TRIGGERS 
  (TRIGGER_GROUP)
/
CREATE INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI6 
  ON KRSB_QRTZ_FIRED_TRIGGERS 
  (INSTANCE_NAME)
/
CREATE INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI7 
  ON KRSB_QRTZ_FIRED_TRIGGERS 
  (TRIGGER_NAME)
/
CREATE INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI8 
  ON KRSB_QRTZ_FIRED_TRIGGERS 
  (TRIGGER_NAME, TRIGGER_GROUP)
/
CREATE INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI9 
  ON KRSB_QRTZ_FIRED_TRIGGERS 
  (IS_VOLATILE)
/





-----------------------------------------------------------------------------
-- KRSB_QRTZ_JOB_DETAILS
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRSB_QRTZ_JOB_DETAILS';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRSB_QRTZ_JOB_DETAILS CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRSB_QRTZ_JOB_DETAILS
(
      JOB_NAME VARCHAR2(80)
        , JOB_GROUP VARCHAR2(80)
        , DESCRIPTION VARCHAR2(120)
        , JOB_CLASS_NAME VARCHAR2(128) NOT NULL
        , IS_DURABLE VARCHAR2(1) NOT NULL
        , IS_VOLATILE VARCHAR2(1) NOT NULL
        , IS_STATEFUL VARCHAR2(1) NOT NULL
        , REQUESTS_RECOVERY VARCHAR2(1) NOT NULL
        , JOB_DATA BLOB
    

)
/

ALTER TABLE KRSB_QRTZ_JOB_DETAILS
    ADD CONSTRAINT KRSB_QRTZ_JOB_DETAILSP1
PRIMARY KEY (JOB_NAME,JOB_GROUP)
/


CREATE INDEX KRSB_QRTZ_JOB_DETAILS_TI1 
  ON KRSB_QRTZ_JOB_DETAILS 
  (REQUESTS_RECOVERY)
/





-----------------------------------------------------------------------------
-- KRSB_QRTZ_JOB_LISTENERS
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRSB_QRTZ_JOB_LISTENERS';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRSB_QRTZ_JOB_LISTENERS CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRSB_QRTZ_JOB_LISTENERS
(
      JOB_NAME VARCHAR2(80)
        , JOB_GROUP VARCHAR2(80)
        , JOB_LISTENER VARCHAR2(80)
    

)
/

ALTER TABLE KRSB_QRTZ_JOB_LISTENERS
    ADD CONSTRAINT KRSB_QRTZ_JOB_LISTENERSP1
PRIMARY KEY (JOB_NAME,JOB_GROUP,JOB_LISTENER)
/







-----------------------------------------------------------------------------
-- KRSB_QRTZ_LOCKS
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRSB_QRTZ_LOCKS';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRSB_QRTZ_LOCKS CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRSB_QRTZ_LOCKS
(
      LOCK_NAME VARCHAR2(40)
    

)
/

ALTER TABLE KRSB_QRTZ_LOCKS
    ADD CONSTRAINT KRSB_QRTZ_LOCKSP1
PRIMARY KEY (LOCK_NAME)
/







-----------------------------------------------------------------------------
-- KRSB_QRTZ_PAUSED_TRIGGER_GRPS
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRSB_QRTZ_PAUSED_TRIGGER_GRPS';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRSB_QRTZ_PAUSED_TRIGGER_GRPS CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRSB_QRTZ_PAUSED_TRIGGER_GRPS
(
      TRIGGER_GROUP VARCHAR2(80)
    

)
/

ALTER TABLE KRSB_QRTZ_PAUSED_TRIGGER_GRPS
    ADD CONSTRAINT KRSB_QRTZ_PAUSED_TRIGGER_GRP1
PRIMARY KEY (TRIGGER_GROUP)
/







-----------------------------------------------------------------------------
-- KRSB_QRTZ_SCHEDULER_STATE
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRSB_QRTZ_SCHEDULER_STATE';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRSB_QRTZ_SCHEDULER_STATE CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRSB_QRTZ_SCHEDULER_STATE
(
      INSTANCE_NAME VARCHAR2(80)
        , LAST_CHECKIN_TIME NUMBER(13) NOT NULL
        , CHECKIN_INTERVAL NUMBER(13) NOT NULL
    

)
/

ALTER TABLE KRSB_QRTZ_SCHEDULER_STATE
    ADD CONSTRAINT KRSB_QRTZ_SCHEDULER_STATEP1
PRIMARY KEY (INSTANCE_NAME)
/







-----------------------------------------------------------------------------
-- KRSB_QRTZ_SIMPLE_TRIGGERS
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRSB_QRTZ_SIMPLE_TRIGGERS';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRSB_QRTZ_SIMPLE_TRIGGERS CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRSB_QRTZ_SIMPLE_TRIGGERS
(
      TRIGGER_NAME VARCHAR2(80)
        , TRIGGER_GROUP VARCHAR2(80)
        , REPEAT_COUNT NUMBER(7) NOT NULL
        , REPEAT_INTERVAL NUMBER(12) NOT NULL
        , TIMES_TRIGGERED NUMBER(7) NOT NULL
    

)
/

ALTER TABLE KRSB_QRTZ_SIMPLE_TRIGGERS
    ADD CONSTRAINT KRSB_QRTZ_SIMPLE_TRIGGERSP1
PRIMARY KEY (TRIGGER_NAME,TRIGGER_GROUP)
/







-----------------------------------------------------------------------------
-- KRSB_QRTZ_TRIGGERS
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRSB_QRTZ_TRIGGERS';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRSB_QRTZ_TRIGGERS CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRSB_QRTZ_TRIGGERS
(
      TRIGGER_NAME VARCHAR2(80)
        , TRIGGER_GROUP VARCHAR2(80)
        , JOB_NAME VARCHAR2(80) NOT NULL
        , JOB_GROUP VARCHAR2(80) NOT NULL
        , IS_VOLATILE VARCHAR2(1) NOT NULL
        , DESCRIPTION VARCHAR2(120)
        , NEXT_FIRE_TIME NUMBER(13)
        , PREV_FIRE_TIME NUMBER(13)
        , PRIORITY NUMBER(13)
        , TRIGGER_STATE VARCHAR2(16) NOT NULL
        , TRIGGER_TYPE VARCHAR2(8) NOT NULL
        , START_TIME NUMBER(13) NOT NULL
        , END_TIME NUMBER(13)
        , CALENDAR_NAME VARCHAR2(80)
        , MISFIRE_INSTR NUMBER(2)
        , JOB_DATA BLOB
    

)
/

ALTER TABLE KRSB_QRTZ_TRIGGERS
    ADD CONSTRAINT KRSB_QRTZ_TRIGGERSP1
PRIMARY KEY (TRIGGER_NAME,TRIGGER_GROUP)
/


CREATE INDEX KRSB_QRTZ_TRIGGERS_TI1 
  ON KRSB_QRTZ_TRIGGERS 
  (NEXT_FIRE_TIME)
/
CREATE INDEX KRSB_QRTZ_TRIGGERS_TI2 
  ON KRSB_QRTZ_TRIGGERS 
  (NEXT_FIRE_TIME, TRIGGER_STATE)
/
CREATE INDEX KRSB_QRTZ_TRIGGERS_TI3 
  ON KRSB_QRTZ_TRIGGERS 
  (TRIGGER_STATE)
/
CREATE INDEX KRSB_QRTZ_TRIGGERS_TI4 
  ON KRSB_QRTZ_TRIGGERS 
  (IS_VOLATILE)
/





-----------------------------------------------------------------------------
-- KRSB_QRTZ_TRIGGER_LISTENERS
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRSB_QRTZ_TRIGGER_LISTENERS';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRSB_QRTZ_TRIGGER_LISTENERS CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRSB_QRTZ_TRIGGER_LISTENERS
(
      TRIGGER_NAME VARCHAR2(80)
        , TRIGGER_GROUP VARCHAR2(80)
        , TRIGGER_LISTENER VARCHAR2(80)
    

)
/

ALTER TABLE KRSB_QRTZ_TRIGGER_LISTENERS
    ADD CONSTRAINT KRSB_QRTZ_TRIGGER_LISTENERSP1
PRIMARY KEY (TRIGGER_NAME,TRIGGER_GROUP,TRIGGER_LISTENER)
/







-----------------------------------------------------------------------------
-- KRSB_SVC_DEF_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRSB_SVC_DEF_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRSB_SVC_DEF_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRSB_SVC_DEF_T
(
      SVC_DEF_ID NUMBER(14)
        , SVC_NM VARCHAR2(255) NOT NULL
        , SVC_URL VARCHAR2(500) NOT NULL
        , SRVR_IP VARCHAR2(40) NOT NULL
        , SVC_NMSPC VARCHAR2(255) NOT NULL
        , SVC_ALIVE NUMBER(1) NOT NULL
        , VER_NBR NUMBER(8) default 0
        , FLT_SVC_DEF_ID NUMBER(14) NOT NULL
        , SVC_DEF_CHKSM VARCHAR2(30) NOT NULL
    
    , CONSTRAINT KRSB_SVC_DEF_TI2 UNIQUE (FLT_SVC_DEF_ID)

)
/

ALTER TABLE KRSB_SVC_DEF_T
    ADD CONSTRAINT KRSB_SVC_DEF_TP1
PRIMARY KEY (SVC_DEF_ID)
/


CREATE INDEX KRSB_SVC_DEF_TI1 
  ON KRSB_SVC_DEF_T 
  (SRVR_IP, SVC_NMSPC)
/





-----------------------------------------------------------------------------
-- KR_COUNTRY_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KR_COUNTRY_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KR_COUNTRY_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KR_COUNTRY_T
(
      POSTAL_CNTRY_CD VARCHAR2(2)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , POSTAL_CNTRY_NM VARCHAR2(40)
        , PSTL_CNTRY_RSTRC_IND VARCHAR2(1) NOT NULL
        , ACTV_IND VARCHAR2(1) default 'Y' NOT NULL
        , ALT_POSTAL_CNTRY_CD VARCHAR2(3)
    
    , CONSTRAINT KR_COUNTRY_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KR_COUNTRY_T
    ADD CONSTRAINT KR_COUNTRY_TP1
PRIMARY KEY (POSTAL_CNTRY_CD)
/







-----------------------------------------------------------------------------
-- KR_COUNTY_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KR_COUNTY_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KR_COUNTY_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KR_COUNTY_T
(
      COUNTY_CD VARCHAR2(10)
        , STATE_CD VARCHAR2(2)
        , POSTAL_CNTRY_CD VARCHAR2(2) default 'US'
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , COUNTY_NM VARCHAR2(100)
        , ACTV_IND VARCHAR2(1)
    
    , CONSTRAINT KR_COUNTY_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KR_COUNTY_T
    ADD CONSTRAINT KR_COUNTY_TP1
PRIMARY KEY (COUNTY_CD,STATE_CD,POSTAL_CNTRY_CD)
/







-----------------------------------------------------------------------------
-- KR_POSTAL_CODE_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KR_POSTAL_CODE_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KR_POSTAL_CODE_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KR_POSTAL_CODE_T
(
      POSTAL_CD VARCHAR2(20)
        , POSTAL_CNTRY_CD VARCHAR2(2) default 'US'
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , POSTAL_STATE_CD VARCHAR2(2)
        , COUNTY_CD VARCHAR2(10)
        , POSTAL_CITY_NM VARCHAR2(30)
        , ACTV_IND VARCHAR2(1) default 'Y' NOT NULL
    
    , CONSTRAINT KR_POSTAL_CODE_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KR_POSTAL_CODE_T
    ADD CONSTRAINT KR_POSTAL_CODE_TP1
PRIMARY KEY (POSTAL_CD,POSTAL_CNTRY_CD)
/







-----------------------------------------------------------------------------
-- KR_STATE_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KR_STATE_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KR_STATE_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KR_STATE_T
(
      POSTAL_STATE_CD VARCHAR2(2)
        , POSTAL_CNTRY_CD VARCHAR2(2) default 'US'
        , OBJ_ID VARCHAR2(36) NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , POSTAL_STATE_NM VARCHAR2(40)
        , ACTV_IND VARCHAR2(1) default 'Y' NOT NULL
    
    , CONSTRAINT KR_STATE_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KR_STATE_T
    ADD CONSTRAINT KR_STATE_TP1
PRIMARY KEY (POSTAL_STATE_CD,POSTAL_CNTRY_CD)
/







-- -----------------------------------------------------------------------
-- KRIM_GRP_MBR_V
-- -----------------------------------------------------------------------
CREATE OR REPLACE FORCE VIEW KRIM_GRP_MBR_V AS 
SELECT g.NMSPC_CD
, g.grp_nm
, g.GRP_ID
, p.PRNCPL_NM
, p.PRNCPL_ID
, mg.GRP_NM AS mbr_grp_nm
, mg.GRP_ID AS mbr_grp_id
FROM KRIM_GRP_MBR_T gm
LEFT JOIN krim_grp_t g
ON g.GRP_ID = gm.GRP_ID
LEFT OUTER JOIN krim_grp_t mg
ON mg.GRP_ID = gm.MBR_ID
AND gm.MBR_TYP_CD = 'G'
LEFT OUTER JOIN krim_prncpl_t p
ON p.PRNCPL_ID = gm.MBR_ID
AND gm.MBR_TYP_CD = 'P'
LEFT OUTER JOIN krim_entity_nm_t en
ON en.ENTITY_ID = p.ENTITY_ID
AND en.DFLT_IND = 'Y'
AND en.ACTV_IND = 'Y'
ORDER BY nmspc_cd, grp_nm, prncpl_nm



/

-- -----------------------------------------------------------------------
-- KRIM_GRP_V
-- -----------------------------------------------------------------------
CREATE OR REPLACE FORCE VIEW KRIM_GRP_V AS 
SELECT g.NMSPC_CD
, g.grp_nm
, g.GRP_ID
, t.NM AS grp_typ_nm
, a.NM AS attr_nm
, d.ATTR_VAL AS attr_val
FROM krim_grp_t g
LEFT OUTER JOIN KRIM_GRP_ATTR_DATA_T d
ON d.grp_id = g.GRP_ID
LEFT OUTER JOIN KRIM_ATTR_DEFN_T a
ON a.KIM_ATTR_DEFN_ID = d.KIM_ATTR_DEFN_ID
LEFT OUTER JOIN KRIM_TYP_T t
ON g.KIM_TYP_ID = t.KIM_TYP_ID



/

-- -----------------------------------------------------------------------
-- KRIM_PERM_ATTR_V
-- -----------------------------------------------------------------------
CREATE OR REPLACE FORCE VIEW KRIM_PERM_ATTR_V AS 
SELECT
t.nmspc_cd AS tmpl_nmspc_cd
, t.NM AS tmpl_nm
, t.PERM_TMPL_ID
, p.nmspc_cd AS perm_nmspc_cd
, p.NM AS perm_nm
, p.PERM_ID
, a.NM AS attr_nm
, ad.ATTR_VAL AS attr_val
FROM KRIM_PERM_T p
LEFT JOIN KRIM_PERM_TMPL_T t
ON p.PERM_TMPL_ID = t.PERM_TMPL_ID
LEFT OUTER JOIN KRIM_PERM_ATTR_DATA_T ad
ON p.PERM_ID = ad.perm_id
LEFT OUTER JOIN KRIM_ATTR_DEFN_T a
ON ad.KIM_ATTR_DEFN_ID = a.KIM_ATTR_DEFN_ID
ORDER BY tmpl_nmspc_cd, tmpl_nm, perm_nmspc_cd, perm_id, attr_nm



/

-- -----------------------------------------------------------------------
-- KRIM_PERM_V
-- -----------------------------------------------------------------------
CREATE OR REPLACE FORCE VIEW KRIM_PERM_V AS 
SELECT
t.nmspc_cd AS tmpl_nmspc_cd
, t.NM AS tmpl_nm
, t.PERM_TMPL_ID
, p.nmspc_cd AS perm_nmspc_cd
, p.NM AS perm_nm
, p.PERM_ID
, typ.NM AS perm_typ_nm
, typ.SRVC_NM
FROM KRIM_PERM_T p
INNER JOIN KRIM_PERM_TMPL_T t
ON p.PERM_TMPL_ID = t.PERM_TMPL_ID
LEFT OUTER JOIN KRIM_TYP_T typ
ON t.KIM_TYP_ID = typ.KIM_TYP_ID



/

-- -----------------------------------------------------------------------
-- KRIM_PRNCPL_V
-- -----------------------------------------------------------------------
CREATE OR REPLACE FORCE VIEW KRIM_PRNCPL_V AS 
SELECT
p.PRNCPL_ID
,p.PRNCPL_NM
,en.FIRST_NM
,en.LAST_NM
,ea.AFLTN_TYP_CD
,ea.CAMPUS_CD
,eei.EMP_STAT_CD
,eei.EMP_TYP_CD
FROM krim_prncpl_t p
LEFT OUTER JOIN krim_entity_emp_info_t eei
ON eei.ENTITY_ID = p.ENTITY_ID
LEFT OUTER JOIN krim_entity_afltn_t ea
ON ea.ENTITY_ID = p.ENTITY_ID
LEFT OUTER JOIN krim_entity_nm_t en
ON p.ENTITY_ID = en.ENTITY_ID
AND 'Y' = en.DFLT_IND



/

-- -----------------------------------------------------------------------
-- KRIM_ROLE_GRP_V
-- -----------------------------------------------------------------------
CREATE OR REPLACE FORCE VIEW KRIM_ROLE_GRP_V AS 
SELECT r.NMSPC_CD
, r.ROLE_NM
, r.role_id
, g.NMSPC_CD AS grp_nmspc_cd
, g.GRP_NM
, rm.ROLE_MBR_ID
, a.NM AS attr_nm
, d.ATTR_VAL AS attr_val
FROM KRIM_ROLE_MBR_T rm
LEFT JOIN KRIM_ROLE_T r
ON r.ROLE_ID = rm.ROLE_ID
LEFT JOIN KRIM_GRP_T g
ON g.GRP_ID = rm.MBR_ID
LEFT OUTER JOIN KRIM_ROLE_MBR_ATTR_DATA_T d
ON d.role_mbr_id = rm.ROLE_MBR_ID
LEFT OUTER JOIN KRIM_ATTR_DEFN_T a
ON a.KIM_ATTR_DEFN_ID = d.KIM_ATTR_DEFN_ID
WHERE rm.MBR_TYP_CD = 'G'
ORDER BY nmspc_cd, role_nm, grp_nmspc_cd, grp_nm, role_mbr_id, attr_nm



/

-- -----------------------------------------------------------------------
-- KRIM_ROLE_PERM_V
-- -----------------------------------------------------------------------
CREATE OR REPLACE FORCE VIEW KRIM_ROLE_PERM_V AS 
SELECT r.NMSPC_CD
, r.ROLE_NM
, r.role_id
, pt.NMSPC_CD AS tmpl_nmspc_cd
, pt.NM AS tmpl_nm
, pt.PERM_TMPL_ID
, p.NMSPC_CD AS perm_nmpsc_cd
, p.NM AS perm_nm
, p.PERM_ID
, a.NM AS attr_nm
, ad.ATTR_VAL AS attr_val
FROM KRIM_PERM_T p
LEFT JOIN KRIM_PERM_TMPL_T pt
ON p.PERM_TMPL_ID = pt.PERM_TMPL_ID
LEFT OUTER JOIN KRIM_PERM_ATTR_DATA_T ad
ON p.PERM_ID = ad.perm_id
LEFT OUTER JOIN KRIM_ATTR_DEFN_T a
ON ad.KIM_ATTR_DEFN_ID = a.KIM_ATTR_DEFN_ID
LEFT OUTER JOIN KRIM_ROLE_PERM_T rp
ON rp.PERM_ID = p.PERM_ID
LEFT OUTER JOIN KRIM_ROLE_T r
ON rp.ROLE_ID = r.ROLE_ID
ORDER BY NMSPC_CD, role_nm, tmpl_nmspc_cd, tmpl_nm, perm_id, attr_nm



/

-- -----------------------------------------------------------------------
-- KRIM_ROLE_PRNCPL_V
-- -----------------------------------------------------------------------
CREATE OR REPLACE FORCE VIEW KRIM_ROLE_PRNCPL_V AS 
SELECT r.NMSPC_CD
, r.ROLE_NM
, r.ROLE_ID
, p.PRNCPL_NM
, p.PRNCPL_ID
, en.FIRST_NM
, en.LAST_NM
, rm.ROLE_MBR_ID
, ad.NM AS attr_nm
, rmad.ATTR_VAL AS attr_val
FROM KRIM_ROLE_T r
LEFT OUTER JOIN KRIM_ROLE_MBR_T rm
ON r.ROLE_ID = rm.ROLE_ID
LEFT OUTER JOIN KRIM_ROLE_MBR_ATTR_DATA_T rmad
ON rm.ROLE_MBR_ID = rmad.role_mbr_id
LEFT OUTER JOIN KRIM_ATTR_DEFN_T ad
ON rmad.KIM_ATTR_DEFN_ID = ad.KIM_ATTR_DEFN_ID
LEFT OUTER JOIN KRIM_PRNCPL_T p
ON rm.MBR_ID = p.PRNCPL_ID
AND rm.mbr_typ_cd = 'P'
LEFT OUTER JOIN KRIM_ENTITY_NM_T en
ON p.ENTITY_ID = en.ENTITY_ID
WHERE (en.DFLT_IND = 'Y')
ORDER BY nmspc_cd, role_nm, prncpl_nm, rm.ROLE_MBR_ID, attr_nm



/

-- -----------------------------------------------------------------------
-- KRIM_ROLE_ROLE_V
-- -----------------------------------------------------------------------
CREATE OR REPLACE FORCE VIEW KRIM_ROLE_ROLE_V AS 
SELECT r.NMSPC_CD
, r.ROLE_NM
, r.role_id
, mr.NMSPC_CD AS mbr_role_nmspc_cd
, mr.role_NM AS mbr_role_nm
, mr.role_id AS mbr_role_id
, rm.role_mbr_id
, a.NM AS attr_nm
, d.ATTR_VAL AS attr_val
FROM KRIM_ROLE_MBR_T rm
LEFT JOIN KRIM_ROLE_T r
ON r.ROLE_ID = rm.ROLE_ID
LEFT JOIN KRIM_role_T mr
ON mr.role_ID = rm.MBR_ID
LEFT OUTER JOIN KRIM_ROLE_MBR_ATTR_DATA_T d
ON d.role_mbr_id = rm.ROLE_MBR_ID
LEFT OUTER JOIN KRIM_ATTR_DEFN_T a
ON a.KIM_ATTR_DEFN_ID = d.KIM_ATTR_DEFN_ID
WHERE rm.MBR_TYP_CD = 'R'
ORDER BY nmspc_cd, role_nm, mbr_role_nmspc_cd, mbr_role_nm, role_mbr_id, attr_nm



/

-- -----------------------------------------------------------------------
-- KRIM_ROLE_V
-- -----------------------------------------------------------------------
CREATE OR REPLACE FORCE VIEW KRIM_ROLE_V AS 
SELECT r.NMSPC_CD
, r.ROLE_NM
, r.ROLE_ID
, t.nm AS role_typ_nm
, t.SRVC_NM
, t.KIM_TYP_ID
FROM KRIM_ROLE_T r
, KRIM_TYP_T t
WHERE t.KIM_TYP_ID = r.KIM_TYP_ID
AND r.ACTV_IND = 'Y'
ORDER BY nmspc_cd
, role_nm



/

-- -----------------------------------------------------------------------
-- KRIM_RSP_ATTR_V
-- -----------------------------------------------------------------------
CREATE OR REPLACE FORCE VIEW KRIM_RSP_ATTR_V AS 
SELECT
krim_typ_t.NM AS responsibility_type_name
, KRIM_rsp_TMPL_T.NM AS rsp_TEMPLATE_NAME
, KRIM_rsp_T.nmspc_cd AS rsp_namespace_code
, KRIM_rsp_T.NM AS rsp_NAME
, krim_rsp_t.RSP_ID AS rsp_id
, KRIM_ATTR_DEFN_T.NM AS attribute_name
, KRIM_rsp_ATTR_DATA_T.ATTR_VAL AS attribute_value
FROM KRIM_rsp_T KRIM_rsp_T
INNER JOIN KRIM_rsp_ATTR_DATA_T KRIM_rsp_ATTR_DATA_T
ON KRIM_rsp_T.rsp_ID = KRIM_rsp_ATTR_DATA_T.rsp_id
INNER JOIN KRIM_ATTR_DEFN_T KRIM_ATTR_DEFN_T
ON KRIM_rsp_ATTR_DATA_T.KIM_ATTR_DEFN_ID = KRIM_ATTR_DEFN_T.KIM_ATTR_DEFN_ID
INNER JOIN KRIM_rsp_TMPL_T KRIM_rsp_TMPL_T
ON KRIM_rsp_T.rsp_TMPL_ID = KRIM_rsp_TMPL_T.rsp_TMPL_ID
INNER JOIN KRIM_TYP_T KRIM_TYP_T
ON KRIM_rsp_TMPL_T.KIM_TYP_ID = KRIM_TYP_T.KIM_TYP_ID
ORDER BY rsp_TEMPLATE_NAME, rsp_NAME, attribute_name



/

-- -----------------------------------------------------------------------
-- KRIM_RSP_ROLE_ACTN_V
-- -----------------------------------------------------------------------
CREATE OR REPLACE FORCE VIEW KRIM_RSP_ROLE_ACTN_V AS 
select
rsp.nmspc_cd as rsp_nmspc_cd
, rsp.rsp_id
, r.NMSPC_CD
, r.ROLE_NM
, rr.ROLE_ID
, rm.MBR_ID
, rm.MBR_TYP_CD
, rm.ROLE_MBR_ID
, actn.ACTN_TYP_CD
, actn.ACTN_PLCY_CD
, actn.FRC_ACTN
, actn.PRIORITY_NBR
from krim_rsp_t rsp
left join krim_rsp_tmpl_t rspt
on rsp.rsp_tmpl_id = rspt.rsp_tmpl_id
left outer join krim_role_rsp_t rr
on rr.rsp_id = rsp.rsp_id
left outer join KRIM_ROLE_MBR_T rm
ON rm.ROLE_ID = rr.ROLE_ID
left outer join KRIM_ROLE_RSP_ACTN_T actn
ON actn.ROLE_RSP_ID = rr.ROLE_RSP_ID
AND (actn.ROLE_MBR_ID = rm.ROLE_MBR_ID OR actn.ROLE_MBR_ID = '*')
left outer join krim_role_t r
on rr.role_id = r.role_id
order by rsp_nmspc_cd
, rsp_id
, role_id
, role_mbr_id



/

-- -----------------------------------------------------------------------
-- KRIM_RSP_ROLE_V
-- -----------------------------------------------------------------------
CREATE OR REPLACE FORCE VIEW KRIM_RSP_ROLE_V AS 
select
rspt.nmspc_cd as rsp_tmpl_nmspc_cd
, rspt.nm as rsp_tmpl_nm
, rsp.nmspc_cd as rsp_nmspc_cd
, rsp.nm as rsp_nm
, rsp.rsp_id
, a.nm as attr_nm
, d.attr_val
, r.NMSPC_CD
, r.ROLE_NM
, rr.ROLE_ID
from krim_rsp_t rsp
left join krim_rsp_tmpl_t rspt
on rsp.rsp_tmpl_id = rspt.rsp_tmpl_id
left outer join krim_rsp_attr_data_t d
on rsp.rsp_id = d.rsp_id
left outer join krim_attr_defn_t a
on d.kim_attr_defn_id = a.kim_attr_defn_id
left outer join krim_role_rsp_t rr
on rr.rsp_id = rsp.rsp_id
left outer join krim_role_t r
on rr.role_id = r.role_id
order by rsp_tmpl_nmspc_cd, rsp_tmpl_nm, rsp_nmspc_cd, rsp_nm, rsp_id, attr_nm, attr_val



/
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREN_CHNL_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREN_CHNL_S'; END IF;
END;
/

CREATE SEQUENCE KREN_CHNL_S INCREMENT BY 1 START WITH 1000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREN_CHNL_SUBSCRP_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREN_CHNL_SUBSCRP_S'; END IF;
END;
/

CREATE SEQUENCE KREN_CHNL_SUBSCRP_S INCREMENT BY 1 START WITH 1020 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREN_CNTNT_TYP_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREN_CNTNT_TYP_S'; END IF;
END;
/

CREATE SEQUENCE KREN_CNTNT_TYP_S INCREMENT BY 1 START WITH 1000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREN_MSG_DELIV_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREN_MSG_DELIV_S'; END IF;
END;
/

CREATE SEQUENCE KREN_MSG_DELIV_S INCREMENT BY 1 START WITH 1000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREN_MSG_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREN_MSG_S'; END IF;
END;
/

CREATE SEQUENCE KREN_MSG_S INCREMENT BY 1 START WITH 1000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREN_NTFCTN_MSG_DELIV_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREN_NTFCTN_MSG_DELIV_S'; END IF;
END;
/

CREATE SEQUENCE KREN_NTFCTN_MSG_DELIV_S INCREMENT BY 1 START WITH 1000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREN_NTFCTN_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREN_NTFCTN_S'; END IF;
END;
/

CREATE SEQUENCE KREN_NTFCTN_S INCREMENT BY 1 START WITH 1000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREN_PRIO_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREN_PRIO_S'; END IF;
END;
/

CREATE SEQUENCE KREN_PRIO_S INCREMENT BY 1 START WITH 1000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREN_PRODCR_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREN_PRODCR_S'; END IF;
END;
/

CREATE SEQUENCE KREN_PRODCR_S INCREMENT BY 1 START WITH 1000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREN_RECIP_DELIV_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREN_RECIP_DELIV_S'; END IF;
END;
/

CREATE SEQUENCE KREN_RECIP_DELIV_S INCREMENT BY 1 START WITH 1000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREN_RECIP_LIST_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREN_RECIP_LIST_S'; END IF;
END;
/

CREATE SEQUENCE KREN_RECIP_LIST_S INCREMENT BY 1 START WITH 1000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREN_RECIP_PREF_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREN_RECIP_PREF_S'; END IF;
END;
/

CREATE SEQUENCE KREN_RECIP_PREF_S INCREMENT BY 1 START WITH 1000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREN_RECIP_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREN_RECIP_S'; END IF;
END;
/

CREATE SEQUENCE KREN_RECIP_S INCREMENT BY 1 START WITH 1000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREN_RVWER_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREN_RVWER_S'; END IF;
END;
/

CREATE SEQUENCE KREN_RVWER_S INCREMENT BY 1 START WITH 1000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREN_SNDR_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREN_SNDR_S'; END IF;
END;
/

CREATE SEQUENCE KREN_SNDR_S INCREMENT BY 1 START WITH 1000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREW_ACTN_ITM_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREW_ACTN_ITM_S'; END IF;
END;
/

CREATE SEQUENCE KREW_ACTN_ITM_S INCREMENT BY 1 START WITH 10226 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREW_ACTN_LIST_OPTN_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREW_ACTN_LIST_OPTN_S'; END IF;
END;
/

CREATE SEQUENCE KREW_ACTN_LIST_OPTN_S INCREMENT BY 1 START WITH 1269 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREW_ACTN_RQST_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREW_ACTN_RQST_S'; END IF;
END;
/

CREATE SEQUENCE KREW_ACTN_RQST_S INCREMENT BY 1 START WITH 2369 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREW_ACTN_TKN_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREW_ACTN_TKN_S'; END IF;
END;
/

CREATE SEQUENCE KREW_ACTN_TKN_S INCREMENT BY 1 START WITH 2329 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREW_DOC_HDR_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREW_DOC_HDR_S'; END IF;
END;
/

CREATE SEQUENCE KREW_DOC_HDR_S INCREMENT BY 1 START WITH 3000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREW_DOC_LNK_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREW_DOC_LNK_S'; END IF;
END;
/

CREATE SEQUENCE KREW_DOC_LNK_S INCREMENT BY 1 START WITH 2000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREW_DOC_NTE_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREW_DOC_NTE_S'; END IF;
END;
/

CREATE SEQUENCE KREW_DOC_NTE_S INCREMENT BY 1 START WITH 2020 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREW_DOC_TYP_ATTR_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREW_DOC_TYP_ATTR_S'; END IF;
END;
/

CREATE SEQUENCE KREW_DOC_TYP_ATTR_S INCREMENT BY 1 START WITH 2009 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREW_EDL_FLD_DMP_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREW_EDL_FLD_DMP_S'; END IF;
END;
/

CREATE SEQUENCE KREW_EDL_FLD_DMP_S INCREMENT BY 1 START WITH 5000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREW_EDL_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREW_EDL_S'; END IF;
END;
/

CREATE SEQUENCE KREW_EDL_S INCREMENT BY 1 START WITH 2020 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREW_HLP_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREW_HLP_S'; END IF;
END;
/

CREATE SEQUENCE KREW_HLP_S INCREMENT BY 1 START WITH 100 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREW_OUT_BOX_ITM_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREW_OUT_BOX_ITM_S'; END IF;
END;
/

CREATE SEQUENCE KREW_OUT_BOX_ITM_S INCREMENT BY 1 START WITH 10043 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREW_RIA_DOCTYPE_MAP_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREW_RIA_DOCTYPE_MAP_ID_S'; END IF;
END;
/

CREATE SEQUENCE KREW_RIA_DOCTYPE_MAP_ID_S INCREMENT BY 1 START WITH 1000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREW_RSP_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREW_RSP_S'; END IF;
END;
/

CREATE SEQUENCE KREW_RSP_S INCREMENT BY 1 START WITH 2065 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREW_RTE_NODE_CFG_PARM_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREW_RTE_NODE_CFG_PARM_S'; END IF;
END;
/

CREATE SEQUENCE KREW_RTE_NODE_CFG_PARM_S INCREMENT BY 1 START WITH 2456 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREW_RTE_NODE_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREW_RTE_NODE_S'; END IF;
END;
/

CREATE SEQUENCE KREW_RTE_NODE_S INCREMENT BY 1 START WITH 2912 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREW_RTE_TMPL_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREW_RTE_TMPL_S'; END IF;
END;
/

CREATE SEQUENCE KREW_RTE_TMPL_S INCREMENT BY 1 START WITH 1645 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREW_RULE_EXPR_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREW_RULE_EXPR_S'; END IF;
END;
/

CREATE SEQUENCE KREW_RULE_EXPR_S INCREMENT BY 1 START WITH 2002 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREW_RULE_TMPL_OPTN_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREW_RULE_TMPL_OPTN_S'; END IF;
END;
/

CREATE SEQUENCE KREW_RULE_TMPL_OPTN_S INCREMENT BY 1 START WITH 2020 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREW_SRCH_ATTR_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREW_SRCH_ATTR_S'; END IF;
END;
/

CREATE SEQUENCE KREW_SRCH_ATTR_S INCREMENT BY 1 START WITH 2060 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KREW_USR_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KREW_USR_S'; END IF;
END;
/

CREATE SEQUENCE KREW_USR_S INCREMENT BY 1 START WITH 100000000000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_ATTR_DATA_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_ATTR_DATA_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_ATTR_DATA_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_ATTR_DEFN_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_ATTR_DEFN_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_ATTR_DEFN_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_DLGN_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_DLGN_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_DLGN_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_DLGN_MBR_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_DLGN_MBR_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_DLGN_MBR_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_ENTITY_ADDR_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_ENTITY_ADDR_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_ENTITY_ADDR_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_ENTITY_AFLTN_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_ENTITY_AFLTN_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_ENTITY_AFLTN_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_ENTITY_CTZNSHP_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_ENTITY_CTZNSHP_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_ENTITY_CTZNSHP_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_ENTITY_EMAIL_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_ENTITY_EMAIL_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_ENTITY_EMAIL_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_ENTITY_EMP_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_ENTITY_EMP_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_ENTITY_EMP_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_ENTITY_ETHNIC_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_ENTITY_ETHNIC_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_ENTITY_ETHNIC_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_ENTITY_EXT_ID_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_ENTITY_EXT_ID_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_ENTITY_EXT_ID_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_ENTITY_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_ENTITY_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_ENTITY_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_ENTITY_NM_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_ENTITY_NM_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_ENTITY_NM_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_ENTITY_PHONE_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_ENTITY_PHONE_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_ENTITY_PHONE_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_ENTITY_RESIDENCY_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_ENTITY_RESIDENCY_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_ENTITY_RESIDENCY_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_ENTITY_VISA_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_ENTITY_VISA_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_ENTITY_VISA_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_GRP_ATTR_DATA_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_GRP_ATTR_DATA_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_GRP_ATTR_DATA_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_GRP_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_GRP_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_GRP_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_GRP_MBR_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_GRP_MBR_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_GRP_MBR_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_PERM_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_PERM_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_PERM_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_PERM_RQRD_ATTR_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_PERM_RQRD_ATTR_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_PERM_RQRD_ATTR_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_PERM_TMPL_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_PERM_TMPL_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_PERM_TMPL_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_PRNCPL_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_PRNCPL_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_PRNCPL_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_ROLE_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_ROLE_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_ROLE_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_ROLE_MBR_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_ROLE_MBR_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_ROLE_MBR_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_ROLE_PERM_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_ROLE_PERM_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_ROLE_PERM_ID_S INCREMENT BY 1 START WITH 1000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_ROLE_RSP_ACTN_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_ROLE_RSP_ACTN_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_ROLE_RSP_ACTN_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_ROLE_RSP_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_ROLE_RSP_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_ROLE_RSP_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_RSP_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_RSP_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_RSP_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_RSP_RQRD_ATTR_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_RSP_RQRD_ATTR_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_RSP_RQRD_ATTR_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_RSP_TMPL_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_RSP_TMPL_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_RSP_TMPL_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_TYP_ATTR_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_TYP_ATTR_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_TYP_ATTR_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_TYP_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_TYP_ID_S'; END IF;
END;
/

CREATE SEQUENCE KRIM_TYP_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRNS_DOC_TYP_ATTR_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRNS_DOC_TYP_ATTR_S'; END IF;
END;
/

CREATE SEQUENCE KRNS_DOC_TYP_ATTR_S INCREMENT BY 1 START WITH 1000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRNS_LOCK_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRNS_LOCK_S'; END IF;
END;
/

CREATE SEQUENCE KRNS_LOCK_S INCREMENT BY 1 START WITH 2000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRNS_LOOKUP_RSLT_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRNS_LOOKUP_RSLT_S'; END IF;
END;
/

CREATE SEQUENCE KRNS_LOOKUP_RSLT_S INCREMENT BY 1 START WITH 2000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRNS_MAINT_LOCK_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRNS_MAINT_LOCK_S'; END IF;
END;
/

CREATE SEQUENCE KRNS_MAINT_LOCK_S INCREMENT BY 1 START WITH 2020 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRNS_NTE_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRNS_NTE_S'; END IF;
END;
/

CREATE SEQUENCE KRNS_NTE_S INCREMENT BY 1 START WITH 2020 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRSB_BAM_PARM_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRSB_BAM_PARM_S'; END IF;
END;
/

CREATE SEQUENCE KRSB_BAM_PARM_S INCREMENT BY 1 START WITH 2000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRSB_BAM_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRSB_BAM_S'; END IF;
END;
/

CREATE SEQUENCE KRSB_BAM_S INCREMENT BY 1 START WITH 2000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRSB_FLT_SVC_DEF_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRSB_FLT_SVC_DEF_S'; END IF;
END;
/

CREATE SEQUENCE KRSB_FLT_SVC_DEF_S INCREMENT BY 1 START WITH 1000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRSB_MSG_QUE_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRSB_MSG_QUE_S'; END IF;
END;
/

CREATE SEQUENCE KRSB_MSG_QUE_S INCREMENT BY 1 START WITH 463 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRSB_SVC_DEF_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRSB_SVC_DEF_S'; END IF;
END;
/

CREATE SEQUENCE KRSB_SVC_DEF_S INCREMENT BY 1 START WITH 4058 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

