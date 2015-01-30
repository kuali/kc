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

TRUNCATE TABLE SPECIAL_REVIEW DROP STORAGE
/
INSERT INTO SPECIAL_REVIEW (SPECIAL_REVIEW_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','Human Subjects','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO SPECIAL_REVIEW (SPECIAL_REVIEW_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','Animal Usage','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO SPECIAL_REVIEW (SPECIAL_REVIEW_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('3','Recombinant DNA','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO SPECIAL_REVIEW (SPECIAL_REVIEW_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('4','Radioactive Isotopes','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO SPECIAL_REVIEW (SPECIAL_REVIEW_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('5','Biohazard Materials','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO SPECIAL_REVIEW (SPECIAL_REVIEW_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('6','International Programs','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO SPECIAL_REVIEW (SPECIAL_REVIEW_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('7','Space Change','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO SPECIAL_REVIEW (SPECIAL_REVIEW_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('8','TLO Review - No conflict (A)','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO SPECIAL_REVIEW (SPECIAL_REVIEW_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('9','TLO review - Reviewed, no conflict (B1)','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO SPECIAL_REVIEW (SPECIAL_REVIEW_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('10','TLO Review - Potential Conflict (B2)','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO SPECIAL_REVIEW (SPECIAL_REVIEW_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('11','TLO PR-Previously Reviewed','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO SPECIAL_REVIEW (SPECIAL_REVIEW_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('12','Foundation Relations','admin',SYSDATE,SYS_GUID(),1)
/
