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

-- set default value for new field
UPDATE PERSON_EXT_T
SET CITIZENSHIP_TYPE_CODE = (SELECT CITIZENSHIP_TYPE_CODE FROM CITIZENSHIP_TYPE_T WHERE DESCRIPTION = 'US CITIZEN OR NONCITIZEN NATIONAL');

-- make new field a non-null field
ALTER TABLE "PERSON_EXT_T" MODIFY ( "CITIZENSHIP_TYPE_CODE" NUMBER(15,5) NOT NULL );
