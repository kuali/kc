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

CREATE TABLE PERSON_MASS_CHANGE (
  PERSON_MASS_CHANGE_ID NUMBER(12,0) NOT NULL,
  DOCUMENT_NUMBER       VARCHAR2(40) NOT NULL,
  REPLACEE_PERSON_ID    VARCHAR2(40),
  REPLACEE_ROLODEX_ID   NUMBER(12,0),
  REPLACER_PERSON_ID    VARCHAR2(40),
  REPLACER_ROLODEX_ID   NUMBER(12,0),
  CHANGE_ALL_SEQUENCES  CHAR(1) DEFAULT 'N' NOT NULL,
  UPDATE_USER           VARCHAR2(60) NOT NULL, 
  UPDATE_TIMESTAMP      DATE NOT NULL, 
  OBJ_ID                VARCHAR2(36) NOT NULL,
  VER_NBR               NUMBER(8,0) DEFAULT 1 NOT NULL
)
/
ALTER TABLE PERSON_MASS_CHANGE ADD CONSTRAINT PK_PMC
  PRIMARY KEY (PERSON_MASS_CHANGE_ID)
/
