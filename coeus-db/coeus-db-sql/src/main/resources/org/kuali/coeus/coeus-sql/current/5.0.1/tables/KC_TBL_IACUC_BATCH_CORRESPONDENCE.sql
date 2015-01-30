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

CREATE TABLE IACUC_BATCH_CORRESPONDENCE ( 
    BATCH_CORRESPONDENCE_TYPE_CODE VARCHAR2(3) NOT NULL, 
    DESCRIPTION VARCHAR2(200) NOT NULL, 
    DAYS_TO_EVENT_UI_TEXT VARCHAR2(400) NOT NULL,
    SEND_CORRESPONDENCE VARCHAR2(10) NOT NULL,
    FINAL_ACTION_DAY NUMBER(3,0), 
    FINAL_ACTION_TYPE_CODE VARCHAR2(3), 
    FINAL_ACTION_CORRESP_TYPE VARCHAR2(3), 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL)
/


-- Primary Key Constraint 
ALTER TABLE IACUC_BATCH_CORRESPONDENCE 
ADD CONSTRAINT PK_BATCH_CORRESPONDENCE 
PRIMARY KEY (BATCH_CORRESPONDENCE_TYPE_CODE)
/

