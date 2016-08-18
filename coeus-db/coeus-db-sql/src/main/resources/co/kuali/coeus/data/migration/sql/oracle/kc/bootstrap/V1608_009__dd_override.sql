--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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

CREATE SEQUENCE SEQ_DD_OVERRIDE_ID INCREMENT BY 1 START WITH 1 NOCACHE;

CREATE TABLE DATA_DICTIONARY_OVERRIDE(
ID NUMBER(19,0),
OVERRIDE_BEANS CLOB NOT NULL,
FILE_NAME VARCHAR2(150) NOT NULL,
CONTENT_TYPE VARCHAR2(255) NOT NULL,
ACTIVE CHAR(1) default 'Y' NOT NULL,
UPDATE_USER VARCHAR2(60) NOT NULL,
UPDATE_TIMESTAMP DATE NOT NULL,
VER_NBR NUMBER (8) DEFAULT 1 NOT NULL,
OBJ_ID VARCHAR2(36) NOT NULL);
