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

TRUNCATE TABLE PROPOSAL_STATE DROP STORAGE
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','In Progress','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','Approval Pending','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('3','Approval Granted','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('4','Approval Not Initiated - Submitted','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('5','Approval Pending - Submitted','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('6','Approved and Submitted','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('7','Disapproved','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('8','Approved Post-Submission','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('9','Disapproved Post-Submission','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('10','Canceled','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('11','Document Error Occurred','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('12','Revisions Requested','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO PROPOSAL_STATE (STATE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('13','Approved','admin',SYSDATE,SYS_GUID(),1)
/
