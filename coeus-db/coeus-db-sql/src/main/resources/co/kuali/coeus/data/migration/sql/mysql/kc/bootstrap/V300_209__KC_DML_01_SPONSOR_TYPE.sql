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
TRUNCATE TABLE SPONSOR_TYPE
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('0','Federal','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','State','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','Local Government','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('3','Private Profit','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('4','Private Non-Profit','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('5','Foundation','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('6','Institution of Higher Education','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('10','Foreign Federal Government','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('11','Foreign State Government','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('12','Foreign Local Government','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('13','Foreign Private Profit','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('14','Foreign Private Non-Profit','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('15','Foreign Foundation','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('16','Foreign Institution of Higher Education','admin',NOW(),UUID(),1)
/
delimiter ;
