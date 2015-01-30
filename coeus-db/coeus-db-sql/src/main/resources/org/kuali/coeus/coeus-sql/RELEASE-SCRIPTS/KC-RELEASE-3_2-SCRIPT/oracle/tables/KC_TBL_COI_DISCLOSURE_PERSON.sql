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

CREATE TABLE COI_DISCLOSURE_PERSON  ( 
    COI_DISCLOSURE_PERSON_ID   NUMBER(12,0) NOT NULL,
    COI_DISCLOSURE_ID          NUMBER(12,0) NOT NULL,
    PERSON_ID                      VARCHAR2(40) NULL,
    PERSON_ROLE_ID                 VARCHAR2(12) NULL,
    UPDATE_TIMESTAMP               DATE NULL,
    UPDATE_USER                    VARCHAR2(60) NULL,
    VER_NBR                        NUMBER(8,0) DEFAULT 1 NOT NULL,
    OBJ_ID                         VARCHAR2(36) NOT NULL
)
/
ALTER TABLE COI_DISCLOSURE_PERSON 
ADD CONSTRAINT PK_COI_DISCLOSURE_PERSON 
PRIMARY KEY (COI_DISCLOSURE_PERSON_ID)
/
