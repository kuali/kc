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

drop table KRSB_SVC_DEF_T
/
drop table KRSB_FLT_SVC_DEF_T
/
drop sequence KRSB_SVC_DEF_S
/
drop sequence KRSB_FLT_SVC_DEF_S
/
CREATE TABLE KRSB_SVC_DSCRPTR_T (
  SVC_DSCRPTR_ID varchar2(40) NOT NULL,
  DSCRPTR clob NOT NULL,
  PRIMARY KEY (SVC_DSCRPTR_ID)
)
/
CREATE SEQUENCE KRSB_SVC_DSCRPTR_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/
CREATE TABLE KRSB_SVC_DEF_T (
  SVC_DEF_ID varchar2(40) NOT NULL,
  SVC_NM varchar2(255) NOT NULL,
  SVC_URL varchar2(500) NOT NULL,
  INSTN_ID varchar2(255) NOT NULL,
  APPL_NMSPC varchar2(255) NOT NULL,
  SRVR_IP varchar2(40) NOT NULL,
  TYP_CD varchar2(40) NOT NULL,
  SVC_VER varchar2(40) NOT NULL,
  STAT_CD varchar2(1) NOT NULL,
  SVC_DSCRPTR_ID varchar2(40) NOT NULL,
  CHKSM varchar2(30) NOT NULL,
  VER_NBR number(8) DEFAULT 0 NOT NULL,
  PRIMARY KEY (SVC_DEF_ID),
  CONSTRAINT KRSB_SVC_DEF_FK1
    FOREIGN KEY (SVC_DSCRPTR_ID)
    REFERENCES KRSB_SVC_DSCRPTR_T(SVC_DSCRPTR_ID) ON DELETE CASCADE
)
/
CREATE SEQUENCE KRSB_SVC_DEF_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/
CREATE INDEX KRSB_SVC_DEF_TI1 on KRSB_SVC_DEF_T (INSTN_ID)
/
CREATE INDEX KRSB_SVC_DEF_TI2 on KRSB_SVC_DEF_T (SVC_NM, STAT_CD)
/
CREATE INDEX KRSB_SVC_DEF_TI3 on KRSB_SVC_DEF_T (STAT_CD)
/
