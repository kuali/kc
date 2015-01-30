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
TRUNCATE TABLE TBN
/
INSERT INTO TBN (TBN_ID,PERSON_NAME,JOB_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
  VALUES ('1','TBA Research','AA000','admin',NOW(),UUID(),1)
/
INSERT INTO TBN (TBN_ID,PERSON_NAME,JOB_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
  VALUES ('2','Research Associate','AA000','admin',NOW(),UUID(),1)
/
INSERT INTO TBN (TBN_ID,PERSON_NAME,JOB_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
  VALUES ('3','Data Entry Assistant','AA000','admin',NOW(),UUID(),1)
/
INSERT INTO TBN (TBN_ID,PERSON_NAME,JOB_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
  VALUES ('4','Computer Programmer','AA000','admin',NOW(),UUID(),1)
/
INSERT INTO TBN (TBN_ID,PERSON_NAME,JOB_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
  VALUES ('5','Data Manager','AA000','admin',NOW(),UUID(),1)
/
INSERT INTO TBN (TBN_ID,PERSON_NAME,JOB_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
  VALUES ('6','Project Director','AA000','admin',NOW(),UUID(),1)
/
delimiter ;
