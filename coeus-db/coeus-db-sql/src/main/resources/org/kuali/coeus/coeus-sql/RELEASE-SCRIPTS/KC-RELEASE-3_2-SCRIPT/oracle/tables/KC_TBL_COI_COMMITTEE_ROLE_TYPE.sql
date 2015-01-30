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
	SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'COI_COMMITTEE_ROLE_TYPE';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE COI_COMMITTEE_ROLE_TYPE CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE COI_COMMITTEE_ROLE_TYPE (
	COI_COMMITTEE_ROLE_TYPE_CODE VARCHAR2(3),
	DESCRIPTION VARCHAR2(200) NOT NULL,
	UPDATE_TIMESTAMP DATE NOT NULL,
	UPDATE_USER VARCHAR2(60) NOT NULL,
	VER_NBR NUMBER(8) DEFAULT 1 NOT NULL,
	OBJ_ID VARCHAR2(36) NOT NULL)
/

ALTER TABLE COI_COMMITTEE_ROLE_TYPE
    ADD CONSTRAINT PK_COI_COMMITTEE_ROLE_TYPE
		PRIMARY KEY (COI_COMMITTEE_ROLE_TYPE_CODE)
/
