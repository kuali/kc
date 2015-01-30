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

CREATE TABLE CUST_RPT_DEFAULT_PARMS(
PARAMETER_NAME VARCHAR2(100 BYTE) NOT NULL,
REPORT_TYPE_CODE NUMBER(3,0) NOT NULL,
CLASS_NAME VARCHAR2(100 BYTE) NOT NULL,
JAVA_STATEMENTS VARCHAR2(2000 BYTE)  NOT NULL,
UNIT_FOR_AUTH_CHECK CHAR(1 BYTE),
UPDATE_USER VARCHAR2(60 BYTE) NOT NULL,
UPDATE_TIMESTAMP DATE NOT NULL,
VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL,
OBJ_ID VARCHAR2(36) NOT NULL
)
/

ALTER TABLE CUST_RPT_DEFAULT_PARMS 
ADD CONSTRAINT PK_CUST_RPT_DEFAULT_PARMS
PRIMARY KEY (PARAMETER_NAME, REPORT_TYPE_CODE, CLASS_NAME)
/
