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
CREATE TABLE IACUC_PROTOCOL_ACTIONS (	
  	IACUC_PROTOCOL_ACTION_ID DECIMAL(12,0), 
	PROTOCOL_NUMBER VARCHAR(20) NOT NULL, 
	SEQUENCE_NUMBER DECIMAL(4,0) NOT NULL, 
	SUBMISSION_NUMBER DECIMAL(4,0), 
	ACTION_ID DECIMAL(6,0) NOT NULL, 
	PROTOCOL_ACTION_TYPE_CODE VARCHAR(3) NOT NULL, 
	PROTOCOL_ID DECIMAL(12,0) NOT NULL, 
	SUBMISSION_ID_FK DECIMAL(12,0), 
	COMMENTS VARCHAR(2000), 
	ACTION_DATE DATE, 
	UPDATE_TIMESTAMP DATE, 
	UPDATE_USER VARCHAR(60), 
	VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL, 
	ACTUAL_ACTION_DATE DATE, 
	OBJ_ID VARCHAR(36) NOT NULL, 
	PREV_SUBMISSION_STATUS_CODE VARCHAR(3), 
	SUBMISSION_TYPE_CODE VARCHAR(3), 
	PREV_PROTOCOL_STATUS_CODE VARCHAR(3), 
	FOLLOWUP_ACTION_CODE VARCHAR(3)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/
ALTER TABLE IACUC_PROTOCOL_ACTIONS
ADD CONSTRAINT PK_IACUC_PROTOCOL_ACTIONS
PRIMARY KEY (IACUC_PROTOCOL_ACTION_ID)
/


DELIMITER ;
