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

CREATE TABLE REPORT_STATUS  ( 
	REPORT_STATUS_CODE	    VARCHAR2(3) NOT NULL,
	DESCRIPTION			    VARCHAR2(200) NOT NULL,
	ACTIVE_FLAG			    CHAR(1) NOT NULL,
	UPDATE_TIMESTAMP     	DATE NOT NULL,
	UPDATE_USER          	VARCHAR2(60) NOT NULL,
	VER_NBR              	NUMBER(8,0) NOT NULL,
	OBJ_ID               	VARCHAR2(36) NOT NULL
)
/

ALTER TABLE REPORT_STATUS
  ADD CONSTRAINT REPORT_STATUS_PK1
  PRIMARY KEY (REPORT_STATUS_CODE)
/
