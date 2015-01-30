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

CREATE TABLE PROTOCOL_NOTIFICATION
(
      NOTIFICATION_ID NUMBER(20)
        , NOTIFICATION_TYPE_ID NUMBER(6) NOT NULL
        , DOCUMENT_NUMBER VARCHAR2(40) NOT NULL
        , OWNING_DOCUMENT_ID_FK NUMBER(12) NOT NULL
        , RECIPIENTS VARCHAR2(1000)
        , SUBJECT VARCHAR2(1000)
        , MESSAGE VARCHAR2(4000)
        , UPDATE_USER VARCHAR2(60) NOT NULL
        , UPDATE_TIMESTAMP DATE NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , OBJ_ID VARCHAR2(36) NOT NULL
)
/
ALTER TABLE PROTOCOL_NOTIFICATION
    ADD CONSTRAINT IRB_NOTIFICATIONP1
PRIMARY KEY (NOTIFICATION_ID)
/
