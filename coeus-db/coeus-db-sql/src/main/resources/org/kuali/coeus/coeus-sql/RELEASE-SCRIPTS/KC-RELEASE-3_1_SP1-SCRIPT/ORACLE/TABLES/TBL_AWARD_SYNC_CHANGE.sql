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

create table AWARD_SYNC_CHANGE (
  AWARD_SYNC_CHANGE_ID   NUMBER(22,0),
  AWARD_ID            NUMBER(22,0) NOT NULL,
  XML_CHANGES         CLOB,
  CLASS_NAME          VARCHAR2(100) NOT NULL,
  ATTRIBUTE_NAME      VARCHAR2(50),
  OBJECT_DESC         VARCHAR2(500),
  DATA_DESC	          VARCHAR2(500),
  SYNC_TYPE           CHAR(1) NOT NULL,
  SYNC_DESCEND    VARCHAR2(6),
  SYNC_FABRICATED_DESCEND CHAR(1) NOT NULL,
  SYNC_COST_SHARE_DESCEND CHAR(1) NOT NULL,
  UPDATE_TIMESTAMP    DATE NOT NULL,
  UPDATE_USER         VARCHAR2(60) NOT NULL,
  VER_NBR             NUMBER(8,0) NOT NULL,
  OBJ_ID              VARCHAR2(36) NOT NULL
);

ALTER TABLE AWARD_SYNC_CHANGE
	ADD CONSTRAINT PK_AWARD_SYNC_CHANGE 
		PRIMARY KEY (AWARD_SYNC_CHANGE_ID);
	
