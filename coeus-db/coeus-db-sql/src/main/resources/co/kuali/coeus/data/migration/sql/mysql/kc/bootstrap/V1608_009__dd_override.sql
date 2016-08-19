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

CREATE TABLE SEQ_DD_OVERRIDE_ID
(
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM;

ALTER TABLE SEQ_DD_OVERRIDE_ID auto_increment = 1;

CREATE TABLE DATA_DICTIONARY_OVERRIDE (
  ID BIGINT(19) NOT NULL PRIMARY KEY,
  OVERRIDE_BEANS LONGTEXT NOT NULL,
  FILE_NAME VARCHAR(150) NOT NULL,
  CONTENT_TYPE VARCHAR(255) NOT NULL,
  ACTIVE CHAR(1) NOT NULL DEFAULT 'Y',
  UPDATE_USER VARCHAR(60) NOT NULL,
  UPDATE_TIMESTAMP DATETIME NOT NULL,
  VER_NBR DECIMAL (8,0) NOT NULL DEFAULT 1,
  OBJ_ID VARCHAR(36) NOT NULL);