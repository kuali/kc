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

DELIMITER /
CREATE TABLE CUST_REPORT_TYPE (
    REPORT_TYPE_CODE INT NOT NULL,
    REPORT_TYPE_DESC VARCHAR(50) NOT NULL,
    UPDATE_USER VARCHAR(60) NOT NULL,
    UPDATE_TIMESTAMP DATE NOT NULL,
    VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL,
 	OBJ_ID VARCHAR(36) NOT NULL)
/


ALTER TABLE CUST_REPORT_TYPE 
ADD CONSTRAINT PK_CUST_REPORT_TYPE 
PRIMARY KEY (REPORT_TYPE_CODE)
/


DELIMITER ;
