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

delimiter /
TRUNCATE TABLE S2S_REVISION_TYPE
/
INSERT INTO S2S_REVISION_TYPE (S2S_REVISION_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('A','Increase Award','admin',NOW(),UUID(),1)
/
INSERT INTO S2S_REVISION_TYPE (S2S_REVISION_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('AC','Increase Award & Increase Duration','admin',NOW(),UUID(),1)
/
INSERT INTO S2S_REVISION_TYPE (S2S_REVISION_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('AD','Increase Award & Decrease Duration','admin',NOW(),UUID(),1)
/
INSERT INTO S2S_REVISION_TYPE (S2S_REVISION_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('B','Decrease Award','admin',NOW(),UUID(),1)
/
INSERT INTO S2S_REVISION_TYPE (S2S_REVISION_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('BC','Decrease Award & Increase Duration','admin',NOW(),UUID(),1)
/
INSERT INTO S2S_REVISION_TYPE (S2S_REVISION_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('BD','Decrease Award & Decrease Duration','admin',NOW(),UUID(),1)
/
INSERT INTO S2S_REVISION_TYPE (S2S_REVISION_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('C','Increase Duration','admin',NOW(),UUID(),1)
/
INSERT INTO S2S_REVISION_TYPE (S2S_REVISION_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('D','Decrease Duration','admin',NOW(),UUID(),1)
/
INSERT INTO S2S_REVISION_TYPE (S2S_REVISION_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('E','Other(Specify)','admin',NOW(),UUID(),1)
/
delimiter ;
