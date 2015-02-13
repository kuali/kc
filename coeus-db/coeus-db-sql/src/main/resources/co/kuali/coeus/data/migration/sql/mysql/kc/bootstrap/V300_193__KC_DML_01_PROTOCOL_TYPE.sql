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
TRUNCATE TABLE PROTOCOL_TYPE
/
INSERT INTO PROTOCOL_TYPE (PROTOCOL_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','Standard','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_TYPE (PROTOCOL_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','Emergency Use','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_TYPE (PROTOCOL_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('3','Humanitarian Use Device (HUD)','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_TYPE (PROTOCOL_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('4','Exempt','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_TYPE (PROTOCOL_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('5','Single Patient Use','admin',NOW(),UUID(),0)
/
INSERT INTO PROTOCOL_TYPE (PROTOCOL_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('6','Expedited','admin',NOW(),UUID(),0)
/
INSERT INTO PROTOCOL_TYPE (PROTOCOL_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('7','Full Board','admin',NOW(),UUID(),0)
/
delimiter ;
