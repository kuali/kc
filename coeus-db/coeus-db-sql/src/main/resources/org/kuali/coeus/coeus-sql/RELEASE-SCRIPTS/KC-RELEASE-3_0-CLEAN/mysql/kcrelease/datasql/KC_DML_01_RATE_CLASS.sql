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
TRUNCATE TABLE RATE_CLASS
/
INSERT INTO RATE_CLASS (RATE_CLASS_CODE,DESCRIPTION,RATE_CLASS_TYPE,ICR_TYPE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','MTDC','O','35','admin',NOW(),UUID(),1)
/
INSERT INTO RATE_CLASS (RATE_CLASS_CODE,DESCRIPTION,RATE_CLASS_TYPE,ICR_TYPE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','TDC','O','11','admin',NOW(),UUID(),1)
/
INSERT INTO RATE_CLASS (RATE_CLASS_CODE,DESCRIPTION,RATE_CLASS_TYPE,ICR_TYPE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('3','S&W','O','36','admin',NOW(),UUID(),1)
/
INSERT INTO RATE_CLASS (RATE_CLASS_CODE,DESCRIPTION,RATE_CLASS_TYPE,ICR_TYPE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('4','Fund with Transaction Fee (FUNSN)','O','11','admin',NOW(),UUID(),1)
/
INSERT INTO RATE_CLASS (RATE_CLASS_CODE,DESCRIPTION,RATE_CLASS_TYPE,ICR_TYPE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('5','Employee Benefits','E',null,'admin',NOW(),UUID(),1)
/
INSERT INTO RATE_CLASS (RATE_CLASS_CODE,DESCRIPTION,RATE_CLASS_TYPE,ICR_TYPE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('7','Inflation','I',null,'admin',NOW(),UUID(),1)
/
INSERT INTO RATE_CLASS (RATE_CLASS_CODE,DESCRIPTION,RATE_CLASS_TYPE,ICR_TYPE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('8','Vacation','V',null,'admin',NOW(),UUID(),1)
/
INSERT INTO RATE_CLASS (RATE_CLASS_CODE,DESCRIPTION,RATE_CLASS_TYPE,ICR_TYPE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('9','Other','X',null,'admin',NOW(),UUID(),1)
/
INSERT INTO RATE_CLASS (RATE_CLASS_CODE,DESCRIPTION,RATE_CLASS_TYPE,ICR_TYPE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('10','Lab Allocation - Salaries','Y',null,'admin',NOW(),UUID(),1)
/
INSERT INTO RATE_CLASS (RATE_CLASS_CODE,DESCRIPTION,RATE_CLASS_TYPE,ICR_TYPE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('11','Lab Allocation - M&S','L',null,'admin',NOW(),UUID(),1)
/
INSERT INTO RATE_CLASS (RATE_CLASS_CODE,DESCRIPTION,RATE_CLASS_TYPE,ICR_TYPE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('12','Lab Allocation - Utilities','L',null,'admin',NOW(),UUID(),1)
/
delimiter ;
