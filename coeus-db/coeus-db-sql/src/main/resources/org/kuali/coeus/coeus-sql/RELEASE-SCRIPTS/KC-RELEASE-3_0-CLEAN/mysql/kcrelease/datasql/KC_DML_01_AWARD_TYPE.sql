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
TRUNCATE TABLE AWARD_TYPE
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (1,'Grant','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (2,'NIH Training Grant ','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (3,'Contract','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (4,'Indefinite Delivery Contract ','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (5,'Cooperative Agreement','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (6,'Facilities Agreement','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (7,'Fellowship','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (8,'Consortium Membership','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (9,'Other Transaction Agreement','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (10,'Student Financial Aid','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (11,'Gift','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (12,'Consortium Expenditures','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (13,'Budget Office WBS','admin',NOW(),UUID(),1)
/
delimiter ;
