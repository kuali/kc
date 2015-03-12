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

CREATE TABLE FILE_DATA (ID VARCHAR2(36), DATA BLOB DEFAULT EMPTY_BLOB())
/
ALTER TABLE NARRATIVE_ATTACHMENT ADD FILE_DATA_ID VARCHAR2(36)
/
UPDATE NARRATIVE_ATTACHMENT SET FILE_DATA_ID = SYS_GUID()
/
INSERT INTO FILE_DATA (SELECT FILE_DATA_ID, NARRATIVE_DATA FROM NARRATIVE_ATTACHMENT)
/
ALTER TABLE NARRATIVE_ATTACHMENT MODIFY FILE_DATA_ID VARCHAR2(36) NOT NULL
/
ALTER TABLE NARRATIVE_ATTACHMENT DROP COLUMN NARRATIVE_DATA
/
ALTER TABLE EPS_PROP_PERSON_BIO_ATTACHMENT ADD FILE_DATA_ID VARCHAR2(36)
/
UPDATE EPS_PROP_PERSON_BIO_ATTACHMENT SET FILE_DATA_ID = SYS_GUID()
/
INSERT INTO FILE_DATA (SELECT FILE_DATA_ID, BIO_DATA FROM EPS_PROP_PERSON_BIO_ATTACHMENT)
/
ALTER TABLE EPS_PROP_PERSON_BIO_ATTACHMENT MODIFY FILE_DATA_ID VARCHAR2(36) NOT NULL
/
ALTER TABLE EPS_PROP_PERSON_BIO_ATTACHMENT DROP COLUMN BIO_DATA
/
