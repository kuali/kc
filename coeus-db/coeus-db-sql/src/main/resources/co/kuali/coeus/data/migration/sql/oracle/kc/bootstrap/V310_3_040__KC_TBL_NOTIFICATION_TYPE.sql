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

CREATE TABLE NOTIFICATION_TYPE (
  NOTIFICATION_TYPE_ID NUMBER(6,0) NOT NULL, 
  MODULE_CODE          NUMBER(3,0) NOT NULL, 
  ACTION_CODE          VARCHAR2(3 BYTE) NOT NULL, 
  DESCRIPTION          VARCHAR2(200 BYTE) NOT NULL, 
  SUBJECT              VARCHAR2(1000 BYTE), 
  MESSAGE              VARCHAR2(4000 BYTE), 
  PROMPT_USER          CHAR(1 BYTE) DEFAULT 'N' NOT NULL, 
  SEND_NOTIFICATION    CHAR(1 BYTE) DEFAULT 'N' NOT NULL,
  SYSTEM_GENERATED     CHAR(1 BYTE) DEFAULT 'N',
  UPDATE_USER          VARCHAR2(60 BYTE) NOT NULL, 
  UPDATE_TIMESTAMP     DATE NOT NULL,
  VER_NBR              NUMBER(8,0) DEFAULT 1 NOT NULL, 
  OBJ_ID               VARCHAR2(36 BYTE) NOT NULL
);
 
ALTER TABLE NOTIFICATION_TYPE 
  ADD CONSTRAINT PK_NOTIFICATION_TYPE PRIMARY KEY (NOTIFICATION_TYPE_ID);
