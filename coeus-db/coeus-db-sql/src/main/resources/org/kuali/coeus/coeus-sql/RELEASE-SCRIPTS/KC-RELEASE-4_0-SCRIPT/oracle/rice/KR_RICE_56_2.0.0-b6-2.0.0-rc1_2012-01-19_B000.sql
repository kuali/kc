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

