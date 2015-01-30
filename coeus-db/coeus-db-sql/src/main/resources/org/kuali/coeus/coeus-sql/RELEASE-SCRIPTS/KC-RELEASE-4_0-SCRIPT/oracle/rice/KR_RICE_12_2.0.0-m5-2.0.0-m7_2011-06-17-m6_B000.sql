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

--
-- NOTE: when assembling this script for release, please merge any table rebuilds with those from 2011-04-28.sql
--

-----------------------------------------------------------------------------
-- KREW_DOC_TYP_T
-----------------------------------------------------------------------------

DECLARE
c NUMBER;
BEGIN
select count(*) into c from all_constraints where CONSTRAINT_NAME = 'KREW_DOC_TYP_TC0' ;
IF c>0 THEN
EXECUTE IMMEDIATE 'ALTER TABLE KREW_DOC_TYP_T DROP CONSTRAINT KREW_DOC_TYP_TC0';
ELSE
DBMS_OUTPUT.PUT_LINE('KREW_DOC_TYP_TC0 does not exist, so not running statement to change/drop it.');
END IF;
END;
/
DECLARE
c NUMBER;
BEGIN
select count(*) into c from all_indexes where INDEX_NAME = 'KREW_DOC_TYP_TC0' ;
IF c>0 THEN
EXECUTE IMMEDIATE 'DROP INDEX KREW_DOC_TYP_TC0';
ELSE
DBMS_OUTPUT.PUT_LINE('KREW_DOC_TYP_TC0 does not exist, so not running statement to change/drop it.');
END IF;
END;
/
DECLARE
c NUMBER;
BEGIN
select count(*) into c from all_constraints where CONSTRAINT_NAME = 'KREW_DOC_TYP_TI1' ;
IF c>0 THEN
EXECUTE IMMEDIATE 'ALTER TABLE KREW_DOC_TYP_T DROP CONSTRAINT KREW_DOC_TYP_TI1';
ELSE
DBMS_OUTPUT.PUT_LINE('KREW_DOC_TYP_TI1 does not exist, so not running statement to change/drop it.');
END IF;
END;
/
DECLARE
c NUMBER;
BEGIN
select count(*) into c from all_indexes where INDEX_NAME = 'KREW_DOC_TYP_TI1' ;
IF c>0 THEN
EXECUTE IMMEDIATE 'DROP INDEX KREW_DOC_TYP_TI1';
ELSE
DBMS_OUTPUT.PUT_LINE('KREW_DOC_TYP_TI1 does not exist, so not running statement to change/drop it.');
END IF;
END;
/
ALTER TABLE KREW_DOC_TYP_T RENAME TO OLD_KREW_DOC_TYP_T
/

CREATE TABLE KREW_DOC_TYP_T
(
      DOC_TYP_ID VARCHAR2(40)
        , PARNT_ID VARCHAR2(40)
        , DOC_TYP_NM VARCHAR2(64)
        , DOC_TYP_VER_NBR NUMBER(10) default 0
        , ACTV_IND NUMBER(1)
        , CUR_IND NUMBER(1)
        , LBL VARCHAR2(128)
        , PREV_DOC_TYP_VER_NBR VARCHAR2(40)
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
        , APPL_ID VARCHAR2(255)
        , EMAIL_XSL VARCHAR2(255)
        , SEC_XML CLOB
        , VER_NBR NUMBER(8) default 0
        , BLNKT_APPR_GRP_ID VARCHAR2(40)
        , RPT_GRP_ID VARCHAR2(40)
        , GRP_ID VARCHAR2(40)
        , HELP_DEF_URL VARCHAR2(4000)
        , OBJ_ID VARCHAR2(36) NOT NULL
        , DOC_SEARCH_HELP_URL VARCHAR2(4000)
        , DOC_HDR_ID VARCHAR2(40)
    , CONSTRAINT KREW_DOC_TYP_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KREW_DOC_TYP_TI1 UNIQUE (DOC_TYP_NM, DOC_TYP_VER_NBR)
)
/

INSERT INTO KREW_DOC_TYP_T(
        DOC_TYP_ID
        , PARNT_ID
        , DOC_TYP_NM
        , DOC_TYP_VER_NBR
        , ACTV_IND
        , CUR_IND
        , LBL
        , PREV_DOC_TYP_VER_NBR
        , DOC_TYP_DESC
        , DOC_HDLR_URL
        , POST_PRCSR
        , JNDI_URL
        , BLNKT_APPR_PLCY
        , ADV_DOC_SRCH_URL
        , CSTM_ACTN_LIST_ATTRIB_CLS_NM
        , CSTM_ACTN_EMAIL_ATTRIB_CLS_NM
        , CSTM_DOC_NTE_ATTRIB_CLS_NM
        , RTE_VER_NBR
        , NOTIFY_ADDR
        , APPL_ID
        , EMAIL_XSL
        , SEC_XML
        , VER_NBR
        , BLNKT_APPR_GRP_ID
        , RPT_GRP_ID
        , GRP_ID
        , HELP_DEF_URL
        , OBJ_ID
        , DOC_SEARCH_HELP_URL
        , DOC_HDR_ID
)
SELECT DOC_TYP_ID
        , PARNT_ID
        , DOC_TYP_NM
        , DOC_TYP_VER_NBR
        , ACTV_IND
        , CUR_IND
        , LBL
        , PREV_DOC_TYP_VER_NBR
        , DOC_TYP_DESC
        , DOC_HDLR_URL
        , POST_PRCSR
        , JNDI_URL
        , BLNKT_APPR_PLCY
        , ADV_DOC_SRCH_URL
        , CSTM_ACTN_LIST_ATTRIB_CLS_NM
        , CSTM_ACTN_EMAIL_ATTRIB_CLS_NM
        , CSTM_DOC_NTE_ATTRIB_CLS_NM
        , RTE_VER_NBR
        , NOTIFY_ADDR
        , APPL_ID
        , EMAIL_XSL
        , SEC_XML
        , VER_NBR
        , BLNKT_APPR_GRP_ID
        , RPT_GRP_ID
        , GRP_ID
        , HELP_DEF_URL
        , OBJ_ID
        , DOC_SEARCH_HELP_URL
        , DOC_HDR_ID
FROM OLD_KREW_DOC_TYP_T
/

DROP TABLE OLD_KREW_DOC_TYP_T CASCADE CONSTRAINTS PURGE
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
-- KREW_DOC_HDR_T
-----------------------------------------------------------------------------

DECLARE
c NUMBER;
BEGIN
select count(*) into c from all_constraints where CONSTRAINT_NAME = 'KREW_DOC_HDR_TC0' ;
IF c>0 THEN
EXECUTE IMMEDIATE 'ALTER TABLE KREW_DOC_HDR_T DROP CONSTRAINT KREW_DOC_HDR_TC0';
ELSE
DBMS_OUTPUT.PUT_LINE('KREW_DOC_HDR_TC0 does not exist, so not running statement to change/drop it.');
END IF;
END;
/
DECLARE
c NUMBER;
BEGIN
select count(*) into c from all_indexes where INDEX_NAME = 'KREW_DOC_HDR_TC0' ;
IF c>0 THEN
EXECUTE IMMEDIATE 'DROP INDEX KREW_DOC_HDR_TC0';
ELSE
DBMS_OUTPUT.PUT_LINE('KREW_DOC_HDR_TC0 does not exist, so not running statement to change/drop it.');
END IF;
END;
/
ALTER TABLE KREW_DOC_HDR_T RENAME TO OLD_KREW_DOC_HDR_T
/

CREATE TABLE KREW_DOC_HDR_T
(
      DOC_HDR_ID VARCHAR2(40)
        , DOC_TYP_ID VARCHAR2(40)
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

INSERT INTO KREW_DOC_HDR_T(DOC_HDR_ID, DOC_TYP_ID, DOC_HDR_STAT_CD, RTE_LVL, STAT_MDFN_DT, CRTE_DT, APRV_DT, FNL_DT, RTE_STAT_MDFN_DT, RTE_LVL_MDFN_DT, TTL, APP_DOC_ID, DOC_VER_NBR, INITR_PRNCPL_ID, VER_NBR, RTE_PRNCPL_ID, DTYPE, OBJ_ID, APP_DOC_STAT, APP_DOC_STAT_MDFN_DT)
SELECT DOC_HDR_ID, DOC_TYP_ID, DOC_HDR_STAT_CD, RTE_LVL, STAT_MDFN_DT, CRTE_DT, APRV_DT, FNL_DT, RTE_STAT_MDFN_DT, RTE_LVL_MDFN_DT, TTL, APP_DOC_ID, DOC_VER_NBR, INITR_PRNCPL_ID, VER_NBR, RTE_PRNCPL_ID, DTYPE, OBJ_ID, APP_DOC_STAT, APP_DOC_STAT_MDFN_DT
FROM OLD_KREW_DOC_HDR_T
/

DROP TABLE OLD_KREW_DOC_HDR_T CASCADE CONSTRAINTS PURGE
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
-- KREW_DOC_TYP_PLCY_RELN_T
-----------------------------------------------------------------------------

DECLARE
c NUMBER;
BEGIN
select count(*) into c from all_constraints where CONSTRAINT_NAME = 'KREW_DOC_TYP_PLCY_RELN_TC0' ;
IF c>0 THEN
EXECUTE IMMEDIATE 'ALTER TABLE KREW_DOC_TYP_PLCY_RELN_T DROP CONSTRAINT KREW_DOC_TYP_PLCY_RELN_TC0';
ELSE
DBMS_OUTPUT.PUT_LINE('KREW_DOC_TYP_PLCY_RELN_TC0 does not exist, so not running statement to change/drop it.');
END IF;
END;
/
DECLARE
c NUMBER;
BEGIN
select count(*) into c from all_indexes where INDEX_NAME = 'KREW_DOC_TYP_PLCY_RELN_TC0' ;
IF c>0 THEN
EXECUTE IMMEDIATE 'DROP INDEX KREW_DOC_TYP_PLCY_RELN_TC0';
ELSE
DBMS_OUTPUT.PUT_LINE('KREW_DOC_TYP_PLCY_RELN_TC0 does not exist, so not running statement to change/drop it.');
END IF;
END;
/
ALTER TABLE KREW_DOC_TYP_PLCY_RELN_T RENAME TO OLD_KREW_DOC_TYP_PLCY_RELN_T
/

CREATE TABLE KREW_DOC_TYP_PLCY_RELN_T
(
      DOC_TYP_ID VARCHAR2(40)
        , DOC_PLCY_NM VARCHAR2(255)
        , PLCY_NM NUMBER(1) NOT NULL
        , VER_NBR NUMBER(8) default 0
        , OBJ_ID VARCHAR2(36) NOT NULL
        , PLCY_VAL VARCHAR2(64)

    , CONSTRAINT KREW_DOC_TYP_PLCY_RELN_TC0 UNIQUE (OBJ_ID)
)
/

INSERT INTO KREW_DOC_TYP_PLCY_RELN_T(
DOC_TYP_ID
        , DOC_PLCY_NM
        , PLCY_NM
        , VER_NBR
        , OBJ_ID
        , PLCY_VAL
)
SELECT DOC_TYP_ID
        , DOC_PLCY_NM
        , PLCY_NM
        , VER_NBR
        , OBJ_ID
        , PLCY_VAL
FROM OLD_KREW_DOC_TYP_PLCY_RELN_T
/

DROP TABLE OLD_KREW_DOC_TYP_PLCY_RELN_T CASCADE CONSTRAINTS PURGE
/

ALTER TABLE KREW_DOC_TYP_PLCY_RELN_T
    ADD CONSTRAINT KREW_DOC_TYP_PLCY_RELN_TP1
PRIMARY KEY (DOC_TYP_ID,DOC_PLCY_NM)
/

-----------------------------------------------------------------------------
-- KREW_DOC_TYP_APP_DOC_STAT_T
-----------------------------------------------------------------------------

DECLARE
c NUMBER;
BEGIN
select count(*) into c from all_constraints where CONSTRAINT_NAME = 'KREW_DOC_TYP_APP_DOC_STAT_TC0' ;
IF c>0 THEN
EXECUTE IMMEDIATE 'ALTER TABLE KREW_DOC_TYP_APP_DOC_STAT_T DROP CONSTRAINT KREW_DOC_TYP_APP_DOC_STAT_TC0';
ELSE
DBMS_OUTPUT.PUT_LINE('KREW_DOC_TYP_APP_DOC_STAT_TC0 does not exist, so not running statement to change/drop it.');
END IF;
END;
/
DECLARE
c NUMBER;
BEGIN
select count(*) into c from all_indexes where INDEX_NAME = 'KREW_DOC_TYP_APP_DOC_STAT_TC0' ;
IF c>0 THEN
EXECUTE IMMEDIATE 'DROP INDEX KREW_DOC_TYP_APP_DOC_STAT_TC0';
ELSE
DBMS_OUTPUT.PUT_LINE('KREW_DOC_TYP_APP_DOC_STAT_TC0 does not exist, so not running statement to change/drop it.');
END IF;
END;
/
ALTER TABLE KREW_DOC_TYP_APP_DOC_STAT_T RENAME TO O_KREW_DOC_TYP_APP_DOC_STAT_T
/

CREATE TABLE KREW_DOC_TYP_APP_DOC_STAT_T
(
      DOC_TYP_ID VARCHAR2(40)
        , DOC_STAT_NM VARCHAR2(64)
        , VER_NBR NUMBER(8) default 0
        , OBJ_ID VARCHAR2(36) NOT NULL
    , CONSTRAINT KREW_DOC_TYP_APP_DOC_STAT_TC0 UNIQUE (OBJ_ID)
)
/

INSERT INTO KREW_DOC_TYP_APP_DOC_STAT_T(
DOC_TYP_ID
        , DOC_STAT_NM
        , VER_NBR
        , OBJ_ID
)
SELECT DOC_TYP_ID
        , DOC_STAT_NM
        , VER_NBR
        , OBJ_ID
FROM O_KREW_DOC_TYP_APP_DOC_STAT_T
/

DROP TABLE O_KREW_DOC_TYP_APP_DOC_STAT_T CASCADE CONSTRAINTS PURGE
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

ALTER TABLE KREW_DOC_TYP_ATTR_T RENAME TO OLD_KREW_DOC_TYP_ATTR_T
/
CREATE TABLE KREW_DOC_TYP_ATTR_T
(
      DOC_TYP_ATTRIB_ID NUMBER(19)
        , DOC_TYP_ID VARCHAR2(40) NOT NULL
        , RULE_ATTR_ID NUMBER(19) NOT NULL
        , ORD_INDX NUMBER(4) default 0
)
/

INSERT INTO KREW_DOC_TYP_ATTR_T(
DOC_TYP_ATTRIB_ID
        , DOC_TYP_ID
        , RULE_ATTR_ID
        , ORD_INDX
)
SELECT DOC_TYP_ATTRIB_ID
        , DOC_TYP_ID
        , RULE_ATTR_ID
        , ORD_INDX
FROM OLD_KREW_DOC_TYP_ATTR_T
/

DROP TABLE OLD_KREW_DOC_TYP_ATTR_T CASCADE CONSTRAINTS PURGE
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
-- KREW_RTE_NODE_T
-----------------------------------------------------------------------------

ALTER TABLE KREW_RTE_NODE_T RENAME TO OLD_KREW_RTE_NODE_T
/

CREATE TABLE KREW_RTE_NODE_T
(
      RTE_NODE_ID NUMBER(19)
        , DOC_TYP_ID VARCHAR2(40)
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

INSERT INTO KREW_RTE_NODE_T(
RTE_NODE_ID
        , DOC_TYP_ID
        , NM
        , TYP
        , RTE_MTHD_NM
        , RTE_MTHD_CD
        , FNL_APRVR_IND
        , MNDTRY_RTE_IND
        , ACTVN_TYP
        , BRCH_PROTO_ID
        , VER_NBR
        , CONTENT_FRAGMENT
        , GRP_ID
        , NEXT_DOC_STAT
)
SELECT RTE_NODE_ID
        , DOC_TYP_ID
        , NM
        , TYP
        , RTE_MTHD_NM
        , RTE_MTHD_CD
        , FNL_APRVR_IND
        , MNDTRY_RTE_IND
        , ACTVN_TYP
        , BRCH_PROTO_ID
        , VER_NBR
        , CONTENT_FRAGMENT
        , GRP_ID
        , NEXT_DOC_STAT
FROM OLD_KREW_RTE_NODE_T
/

DROP TABLE OLD_KREW_RTE_NODE_T CASCADE CONSTRAINTS PURGE
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
-- KREW_DOC_TYP_PROC_T
-----------------------------------------------------------------------------

ALTER TABLE KREW_DOC_TYP_PROC_T RENAME TO OLD_KREW_DOC_TYP_PROC_T
/

CREATE TABLE KREW_DOC_TYP_PROC_T
(
      DOC_TYP_PROC_ID NUMBER(19)
        , DOC_TYP_ID VARCHAR2(40) NOT NULL
        , INIT_RTE_NODE_ID NUMBER(22)
        , NM VARCHAR2(255) NOT NULL
        , INIT_IND NUMBER(1) default 0 NOT NULL
        , VER_NBR NUMBER(8) default 0
)
/

INSERT INTO KREW_DOC_TYP_PROC_T(
DOC_TYP_PROC_ID
        , DOC_TYP_ID
        , INIT_RTE_NODE_ID
        , NM
        , INIT_IND
        , VER_NBR
)
SELECT DOC_TYP_PROC_ID
        , DOC_TYP_ID
        , INIT_RTE_NODE_ID
        , NM
        , INIT_IND
        , VER_NBR
FROM OLD_KREW_DOC_TYP_PROC_T
/

DROP TABLE OLD_KREW_DOC_TYP_PROC_T CASCADE CONSTRAINTS PURGE
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
