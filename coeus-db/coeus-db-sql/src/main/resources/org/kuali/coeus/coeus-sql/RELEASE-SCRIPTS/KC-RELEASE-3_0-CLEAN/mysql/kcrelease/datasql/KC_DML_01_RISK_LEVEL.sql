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
TRUNCATE TABLE RISK_LEVEL
/
INSERT INTO RISK_LEVEL (RISK_LEVEL_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','No greater than minimal risk.','admin',NOW(),UUID(),1)
/
INSERT INTO RISK_LEVEL (RISK_LEVEL_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','Greater than minimal risk but potential for direct benefit for participant','admin',NOW(),UUID(),1)
/
INSERT INTO RISK_LEVEL (RISK_LEVEL_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('3','Moderate Risk','admin',NOW(),UUID(),1)
/
INSERT INTO RISK_LEVEL (RISK_LEVEL_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('4','Research involving greater than minimal risk, with no potential for benefit to participant, but likely to yield generalizable knowledge about the participant''s condition','admin',NOW(),UUID(),1)
/
INSERT INTO RISK_LEVEL (RISK_LEVEL_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('5','High Risk','admin',NOW(),UUID(),1)
/
delimiter ;
