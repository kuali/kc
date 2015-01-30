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

CREATE TABLE PMC_UNIT_ADMINISTRATOR (
  PMC_UNIT_ADMINISTRATOR_ID     NUMBER(12,0) NOT NULL,
  PERSON_MASS_CHANGE_ID         NUMBER(12,0) NOT NULL,
  ADMINISTRATIVE_OFFICER        CHAR(1) DEFAULT 'N' NOT NULL,
  OSP_ADMINISTRATOR             CHAR(1) DEFAULT 'N' NOT NULL,
  UNIT_HEAD                     CHAR(1) DEFAULT 'N' NOT NULL,
  DEAN_VP                       CHAR(1) DEFAULT 'N' NOT NULL,
  OTHER_INDIVIDUAL_TO_NOTIFY    CHAR(1) DEFAULT 'N' NOT NULL,
  ADMINISTRATIVE_CONTACT        CHAR(1) DEFAULT 'N' NOT NULL,
  FINANCIAL_CONTACT             CHAR(1) DEFAULT 'N' NOT NULL,
  UPDATE_USER                   VARCHAR2(60) NOT NULL, 
  UPDATE_TIMESTAMP              DATE NOT NULL, 
  OBJ_ID                        VARCHAR2(36) NOT NULL,
  VER_NBR                       NUMBER(8,0) DEFAULT 1 NOT NULL
)
/
ALTER TABLE PMC_UNIT_ADMINISTRATOR ADD CONSTRAINT PK_PMC_UNIT_ADMINISTRATOR
  PRIMARY KEY (PMC_UNIT_ADMINISTRATOR_ID)
/
