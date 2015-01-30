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

TRUNCATE TABLE LOCATION_TYPE DROP STORAGE
/
INSERT INTO LOCATION_TYPE (LOCATION_TYPE_CODE,LOCATION_TYPE_DESC,UPDATE_USER,UPDATE_TIMESTAMP) 
    VALUES (1,'Proposal Organization','admin',SYSDATE)
/
INSERT INTO LOCATION_TYPE (LOCATION_TYPE_CODE,LOCATION_TYPE_DESC,UPDATE_USER,UPDATE_TIMESTAMP) 
    VALUES (2,'Performing Organization','admin',SYSDATE)
/
INSERT INTO LOCATION_TYPE (LOCATION_TYPE_CODE,LOCATION_TYPE_DESC,UPDATE_USER,UPDATE_TIMESTAMP) 
    VALUES (3,'Other Organization','admin',SYSDATE)
/
INSERT INTO LOCATION_TYPE (LOCATION_TYPE_CODE,LOCATION_TYPE_DESC,UPDATE_USER,UPDATE_TIMESTAMP) 
    VALUES (4,'Performance Site','admin',SYSDATE)
/
