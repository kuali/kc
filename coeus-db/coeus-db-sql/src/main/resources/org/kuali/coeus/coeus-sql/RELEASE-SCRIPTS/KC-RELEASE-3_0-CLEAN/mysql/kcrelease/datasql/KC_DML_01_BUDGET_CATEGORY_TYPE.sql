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
TRUNCATE TABLE BUDGET_CATEGORY_TYPE
/
INSERT INTO BUDGET_CATEGORY_TYPE (BUDGET_CATEGORY_TYPE_CODE,DESCRIPTION,SORT_ID,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('E','Equipment',2,'admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_TYPE (BUDGET_CATEGORY_TYPE_CODE,DESCRIPTION,SORT_ID,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('H','Proposal Hierarchy Sub-Projects',6,'admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_TYPE (BUDGET_CATEGORY_TYPE_CODE,DESCRIPTION,SORT_ID,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('O','Other Direct',5,'admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_TYPE (BUDGET_CATEGORY_TYPE_CODE,DESCRIPTION,SORT_ID,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('P','Personnel',1,'admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_TYPE (BUDGET_CATEGORY_TYPE_CODE,DESCRIPTION,SORT_ID,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S','Participant Support',4,'admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_TYPE (BUDGET_CATEGORY_TYPE_CODE,DESCRIPTION,SORT_ID,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('T','Travel',3,'admin',NOW(),UUID(),1)
/
delimiter ;
