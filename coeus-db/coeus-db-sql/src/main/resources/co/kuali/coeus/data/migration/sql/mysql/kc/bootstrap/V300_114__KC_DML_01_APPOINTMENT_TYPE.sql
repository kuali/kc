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
TRUNCATE TABLE APPOINTMENT_TYPE
/
INSERT INTO APPOINTMENT_TYPE (APPOINTMENT_TYPE_CODE,DESCRIPTION,DURATION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','TEMPORARY EMPLOYEE',1,'admin',NOW(),UUID(),1)
/
INSERT INTO APPOINTMENT_TYPE (APPOINTMENT_TYPE_CODE,DESCRIPTION,DURATION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','SUMMER EMPLOYEE',3,'admin',NOW(),UUID(),1)
/
INSERT INTO APPOINTMENT_TYPE (APPOINTMENT_TYPE_CODE,DESCRIPTION,DURATION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('3','9M DURATION',9,'admin',NOW(),UUID(),1)
/
INSERT INTO APPOINTMENT_TYPE (APPOINTMENT_TYPE_CODE,DESCRIPTION,DURATION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('4','10M DURATION',10,'admin',NOW(),UUID(),1)
/
INSERT INTO APPOINTMENT_TYPE (APPOINTMENT_TYPE_CODE,DESCRIPTION,DURATION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('5','11M DURATION',11,'admin',NOW(),UUID(),1)
/
INSERT INTO APPOINTMENT_TYPE (APPOINTMENT_TYPE_CODE,DESCRIPTION,DURATION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('6','12M DURATION',12,'admin',NOW(),UUID(),1)
/
INSERT INTO APPOINTMENT_TYPE (APPOINTMENT_TYPE_CODE,DESCRIPTION,DURATION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('7','REGULAR EMPLOYEE',12,'admin',NOW(),UUID(),1)
/
delimiter ;
