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

CREATE TABLE IACUC_PROC_CAT_CUSTOM_DATA ( 
    ID NUMBER(12,0) NOT NULL, 
    PROCEDURE_CATEGORY_CODE NUMBER(3,0) NOT NULL, 
    NAME VARCHAR2(30) NOT NULL, 
    LABEL VARCHAR2(30) NOT NULL, 
    DATA_TYPE_CODE VARCHAR2(3) NOT NULL, 
    DATA_LENGTH NUMBER(4,0), 
    DEFAULT_VALUE VARCHAR2(2000),
	LOOKUP_CLASS      VARCHAR2(100),
  	LOOKUP_RETURN     VARCHAR2(30),
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    ACTIVE_FLAG CHAR(1), 
    SORT_ID NUMBER(12,0) DEFAULT 1 NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) NOT NULL) 
/


ALTER TABLE IACUC_PROC_CAT_CUSTOM_DATA 
ADD CONSTRAINT PK_PROC_CAT_CUSTOM_DATA 
PRIMARY KEY (ID) 
/

