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
TRUNCATE TABLE PROPOSAL_STATE
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','In Progress','admin',NOW(),UUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','Approval Pending','admin',NOW(),UUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('3','Approval Granted','admin',NOW(),UUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('4','Approval Not Initiated - Submitted','admin',NOW(),UUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('5','Approval Pending - Submitted','admin',NOW(),UUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('6','Approved and Submitted','admin',NOW(),UUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('7','Disapproved','admin',NOW(),UUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('8','Approved Post-Submission','admin',NOW(),UUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('9','Disapproved Post-Submission','admin',NOW(),UUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('10','Canceled','admin',NOW(),UUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('11','Document Error Occurred','admin',NOW(),UUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('12','Revisions Requested','admin',NOW(),UUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('13','Approved','admin',NOW(),UUID(),1)
/
delimiter ;
