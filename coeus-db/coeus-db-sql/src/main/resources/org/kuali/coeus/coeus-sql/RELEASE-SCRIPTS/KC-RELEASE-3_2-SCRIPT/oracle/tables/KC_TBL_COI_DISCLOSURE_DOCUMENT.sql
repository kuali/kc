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

DECLARE temp NUMBER;
BEGIN
        SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'COI_DISCLOSURE_DOCUMENT';
        IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE COI_DISCLOSURE_DOCUMENT CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE COI_DISCLOSURE_DOCUMENT  ( 
	DOCUMENT_NUMBER 	VARCHAR2(40) NOT NULL,
	VER_NBR         	NUMBER(8,0) DEFAULT 1 NOT NULL,
	UPDATE_TIMESTAMP	DATE NOT NULL,
	UPDATE_USER     	VARCHAR2(60) NOT NULL,
	OBJ_ID          	VARCHAR2(36) NOT NULL)
/
ALTER TABLE COI_DISCLOSURE_DOCUMENT 
ADD CONSTRAINT PK_COI_DISCLOSURE_DOCUMENT 
PRIMARY KEY (DOCUMENT_NUMBER)
/

