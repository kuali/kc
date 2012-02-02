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

CREATE TABLE KRCR_STYLE_T
(
      STYLE_ID VARCHAR2(40)
        , NM VARCHAR2(200) NOT NULL
        , XML CLOB NOT NULL
        , ACTV_IND NUMBER(1) NOT NULL
        , VER_NBR NUMBER(8) default 0
        , OBJ_ID VARCHAR2(36) NOT NULL
    , CONSTRAINT KRCR_STYLE_TC0 UNIQUE (OBJ_ID)
)
/
INSERT INTO KRCR_STYLE_T SELECT STYLE_ID, NM, XML, ACTV_IND, VER_NBR, OBJ_ID FROM KREW_STYLE_T
/
DROP TABLE KREW_STYLE_T
/
ALTER TABLE KRCR_STYLE_T
    ADD CONSTRAINT KRCR_STYLE_TP1
PRIMARY KEY (STYLE_ID)
/

