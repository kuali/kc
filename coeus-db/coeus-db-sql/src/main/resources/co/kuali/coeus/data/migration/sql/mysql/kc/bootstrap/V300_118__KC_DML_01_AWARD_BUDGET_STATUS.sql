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
TRUNCATE TABLE AWARD_BUDGET_STATUS
/
INSERT INTO AWARD_BUDGET_STATUS (AWARD_BUDGET_STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','In Progress','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_BUDGET_STATUS (AWARD_BUDGET_STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('5','Submitted','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_BUDGET_STATUS (AWARD_BUDGET_STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('8','Rejected','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_BUDGET_STATUS (AWARD_BUDGET_STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('9','Posted','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_BUDGET_STATUS (AWARD_BUDGET_STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('10','To Be Posted','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_BUDGET_STATUS (AWARD_BUDGET_STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('11','Error in Posting','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_BUDGET_STATUS (AWARD_BUDGET_STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('12','Do Not Post','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_BUDGET_STATUS (AWARD_BUDGET_STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('13','Disapproved','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_BUDGET_STATUS (AWARD_BUDGET_STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('14','Cancelled','admin',NOW(),UUID(),1)
/
delimiter ;
