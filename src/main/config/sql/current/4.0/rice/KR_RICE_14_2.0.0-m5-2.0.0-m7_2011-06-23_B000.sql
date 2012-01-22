--
-- Copyright 2005-2012 The Kuali Foundation
--
-- Licensed under the Educational Community License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http://www.opensource.org/licenses/ecl2.php
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
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
