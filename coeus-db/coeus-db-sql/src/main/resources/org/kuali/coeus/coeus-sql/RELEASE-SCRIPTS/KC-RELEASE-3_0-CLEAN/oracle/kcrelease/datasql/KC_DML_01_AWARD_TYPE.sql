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

TRUNCATE TABLE AWARD_TYPE DROP STORAGE
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (1,'Grant','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (2,'NIH Training Grant ','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (3,'Contract','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (4,'Indefinite Delivery Contract ','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (5,'Cooperative Agreement','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (6,'Facilities Agreement','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (7,'Fellowship','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (8,'Consortium Membership','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (9,'Other Transaction Agreement','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (10,'Student Financial Aid','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (11,'Gift','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (12,'Consortium Expenditures','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO AWARD_TYPE (AWARD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (13,'Budget Office WBS','admin',SYSDATE,SYS_GUID(),1)
/
