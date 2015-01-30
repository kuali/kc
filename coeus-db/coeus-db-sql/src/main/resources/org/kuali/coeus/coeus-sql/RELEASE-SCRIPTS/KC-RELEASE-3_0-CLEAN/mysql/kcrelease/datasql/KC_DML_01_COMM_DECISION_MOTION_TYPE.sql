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
TRUNCATE TABLE COMM_DECISION_MOTION_TYPE
/
INSERT INTO COMM_DECISION_MOTION_TYPE (COMM_DECISION_MOTION_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','Approve','admin',NOW(),UUID(),1)
/
INSERT INTO COMM_DECISION_MOTION_TYPE (COMM_DECISION_MOTION_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','Disapprove','admin',NOW(),UUID(),1)
/
INSERT INTO COMM_DECISION_MOTION_TYPE (COMM_DECISION_MOTION_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('3','Specific Minor Revisions','admin',NOW(),UUID(),1)
/
INSERT INTO COMM_DECISION_MOTION_TYPE (COMM_DECISION_MOTION_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('4','Substantive Revisions Required','admin',NOW(),UUID(),1)
/
delimiter ;
