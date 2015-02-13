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
TRUNCATE TABLE ACTIVITY_TYPE
/
INSERT INTO ACTIVITY_TYPE (ACTIVITY_TYPE_CODE,DESCRIPTION,HIGHER_EDUCATION_FUNCTION_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','Research','IPR','admin',NOW(),UUID(),1)
/
INSERT INTO ACTIVITY_TYPE (ACTIVITY_TYPE_CODE,DESCRIPTION,HIGHER_EDUCATION_FUNCTION_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','Instruction','IN','admin',NOW(),UUID(),1)
/
INSERT INTO ACTIVITY_TYPE (ACTIVITY_TYPE_CODE,DESCRIPTION,HIGHER_EDUCATION_FUNCTION_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('3','Public Service','CSSR','admin',NOW(),UUID(),1)
/
INSERT INTO ACTIVITY_TYPE (ACTIVITY_TYPE_CODE,DESCRIPTION,HIGHER_EDUCATION_FUNCTION_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('4','Clinical Trial','PSSR','admin',NOW(),UUID(),1)
/
INSERT INTO ACTIVITY_TYPE (ACTIVITY_TYPE_CODE,DESCRIPTION,HIGHER_EDUCATION_FUNCTION_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('5','other','User must select','admin',NOW(),UUID(),1)
/
INSERT INTO ACTIVITY_TYPE (ACTIVITY_TYPE_CODE,DESCRIPTION,HIGHER_EDUCATION_FUNCTION_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('6','Fellowship - Pre-Doctoral','S&F','admin',NOW(),UUID(),1)
/
INSERT INTO ACTIVITY_TYPE (ACTIVITY_TYPE_CODE,DESCRIPTION,HIGHER_EDUCATION_FUNCTION_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('7','Fellowship - Post-Doctoral','S&F','admin',NOW(),UUID(),1)
/
INSERT INTO ACTIVITY_TYPE (ACTIVITY_TYPE_CODE,DESCRIPTION,HIGHER_EDUCATION_FUNCTION_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('8','Student Services','SS','admin',NOW(),UUID(),1)
/
INSERT INTO ACTIVITY_TYPE (ACTIVITY_TYPE_CODE,DESCRIPTION,HIGHER_EDUCATION_FUNCTION_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('9','Construction','User must select','admin',NOW(),UUID(),1)
/
delimiter ;
