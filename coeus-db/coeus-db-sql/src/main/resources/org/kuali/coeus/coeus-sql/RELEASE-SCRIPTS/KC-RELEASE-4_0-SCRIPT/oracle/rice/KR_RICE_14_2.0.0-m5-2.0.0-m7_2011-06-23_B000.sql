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

CREATE SEQUENCE KRMS_CTGRY_S INCREMENT BY 1 START WITH 1 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE TABLE KRMS_CTGRY_T
(
    CTGRY_ID VARCHAR2(40) NOT NULL
      , NM VARCHAR2(255) NOT NULL
      , NMSPC_CD VARCHAR2(40) NOT NULL
      , VER_NBR NUMBER(8) DEFAULT 0
    , PRIMARY KEY (CTGRY_ID)
    , CONSTRAINT KRMS_CTGRY_TC0 UNIQUE (NM, NMSPC_CD)
)
/

CREATE TABLE KRMS_TERM_SPEC_CTGRY_T
(
  TERM_SPEC_ID VARCHAR2(40) NOT NULL
      , CTGRY_ID VARCHAR2(40) NOT NULL
  , PRIMARY KEY (TERM_SPEC_ID, CTGRY_ID)
  , CONSTRAINT KRMS_TERM_SPEC_CTGRY_FK1 FOREIGN KEY (TERM_SPEC_ID) REFERENCES KRMS_TERM_SPEC_T (TERM_SPEC_ID)
  , CONSTRAINT KRMS_TERM_SPEC_CTGRY_FK2 FOREIGN KEY (CTGRY_ID) REFERENCES KRMS_CTGRY_T (CTGRY_ID)
)
/

CREATE TABLE KRMS_FUNC_CTGRY_T
(
  FUNC_ID VARCHAR2(40) NOT NULL
  , CTGRY_ID VARCHAR2(40) NOT NULL
  , PRIMARY KEY (FUNC_ID, CTGRY_ID)
  , CONSTRAINT KRMS_FUNC_CTGRY_FK1 FOREIGN KEY (FUNC_ID) REFERENCES KRMS_FUNC_T (FUNC_ID)
  , CONSTRAINT KRMS_FUNC_CTGRY_FK2 FOREIGN KEY (CTGRY_ID) REFERENCES KRMS_CTGRY_T (CTGRY_ID)
)
/
