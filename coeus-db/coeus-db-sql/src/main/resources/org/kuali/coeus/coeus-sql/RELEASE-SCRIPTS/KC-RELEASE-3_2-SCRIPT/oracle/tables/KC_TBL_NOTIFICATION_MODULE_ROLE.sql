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

CREATE TABLE NOTIFICATION_MODULE_ROLE (
    NOTIFICATION_MODULE_ROLE_ID NUMBER(6,0) NOT NULL,
    OBJ_ID                  VARCHAR2(36),
    VER_NBR                 NUMBER(8, 0) DEFAULT 1 NOT NULL,
    UPDATE_TIMESTAMP        DATE NOT NULL,
    UPDATE_USER             VARCHAR2(60) NOT NULL,
    MODULE_CODE             NUMBER(3, 0),
    ROLE_NAME               VARCHAR2(125)
)
/
ALTER TABLE NOTIFICATION_MODULE_ROLE ADD CONSTRAINT PK_NOTIFICATION_MODULE_ROLE PRIMARY KEY (NOTIFICATION_MODULE_ROLE_ID)
/
