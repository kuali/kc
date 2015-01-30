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
TRUNCATE TABLE RATE_CLASS_TYPE
/
INSERT INTO RATE_CLASS_TYPE (RATE_CLASS_TYPE,DESCRIPTION,PREFIX_ACTIVITY_TYPE,SORT_ID,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('E','Fringe Benefits','N',2,'admin',NOW(),UUID(),1)
/
INSERT INTO RATE_CLASS_TYPE (RATE_CLASS_TYPE,DESCRIPTION,PREFIX_ACTIVITY_TYPE,SORT_ID,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('I','Inflation','N',3,'admin',NOW(),UUID(),1)
/
INSERT INTO RATE_CLASS_TYPE (RATE_CLASS_TYPE,DESCRIPTION,PREFIX_ACTIVITY_TYPE,SORT_ID,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('L','Lab Allocation - Other','N',6,'admin',NOW(),UUID(),1)
/
INSERT INTO RATE_CLASS_TYPE (RATE_CLASS_TYPE,DESCRIPTION,PREFIX_ACTIVITY_TYPE,SORT_ID,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('O','F & A','Y',1,'admin',NOW(),UUID(),1)
/
INSERT INTO RATE_CLASS_TYPE (RATE_CLASS_TYPE,DESCRIPTION,PREFIX_ACTIVITY_TYPE,SORT_ID,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('V','Vacation','N',4,'admin',NOW(),UUID(),1)
/
INSERT INTO RATE_CLASS_TYPE (RATE_CLASS_TYPE,DESCRIPTION,PREFIX_ACTIVITY_TYPE,SORT_ID,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('X','Other','N',7,'admin',NOW(),UUID(),1)
/
INSERT INTO RATE_CLASS_TYPE (RATE_CLASS_TYPE,DESCRIPTION,PREFIX_ACTIVITY_TYPE,SORT_ID,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('Y','Lab Allocation - Salaries','N',5,'admin',NOW(),UUID(),1)
/
delimiter ;
