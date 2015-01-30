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

INSERT INTO CUSTOM_ATTRIBUTE (ID,NAME,LABEL,DATA_TYPE_CODE,DATA_LENGTH,LOOKUP_CLASS,LOOKUP_RETURN,GROUP_NAME,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (1,'billingElement','Billing Element',(SELECT DATA_TYPE_CODE FROM CUSTOM_ATTRIBUTE_DATA_TYPE WHERE DESCRIPTION = 'String'),40,null,null,'asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO CUSTOM_ATTRIBUTE (ID,NAME,LABEL,DATA_TYPE_CODE,DATA_LENGTH,LOOKUP_CLASS,LOOKUP_RETURN,GROUP_NAME,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (2,'costSharingBudget','Cost Sharing Budget',(SELECT DATA_TYPE_CODE FROM CUSTOM_ATTRIBUTE_DATA_TYPE WHERE DESCRIPTION = 'String'),30,null,null,'asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO CUSTOM_ATTRIBUTE (ID,NAME,LABEL,DATA_TYPE_CODE,DATA_LENGTH,LOOKUP_CLASS,LOOKUP_RETURN,GROUP_NAME,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (3,'numberOfTrainees','# of Trainees',(SELECT DATA_TYPE_CODE FROM CUSTOM_ATTRIBUTE_DATA_TYPE WHERE DESCRIPTION = 'Number'),6,null,null,'asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO CUSTOM_ATTRIBUTE (ID,NAME,LABEL,DATA_TYPE_CODE,DATA_LENGTH,LOOKUP_CLASS,LOOKUP_RETURN,GROUP_NAME,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (4,'graduateStudentCount','Graduate Student Count',(SELECT DATA_TYPE_CODE FROM CUSTOM_ATTRIBUTE_DATA_TYPE WHERE DESCRIPTION = 'Number'),6,null,null,'Personnel Items for Review','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO CUSTOM_ATTRIBUTE (ID,NAME,LABEL,DATA_TYPE_CODE,DATA_LENGTH,LOOKUP_CLASS,LOOKUP_RETURN,GROUP_NAME,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (5,'tenured','Tenured',(SELECT DATA_TYPE_CODE FROM CUSTOM_ATTRIBUTE_DATA_TYPE WHERE DESCRIPTION = 'String'),30,'org.kuali.kra.bo.KcPerson','userName','Personnel Items for Review','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO CUSTOM_ATTRIBUTE (ID,NAME,LABEL,DATA_TYPE_CODE,DATA_LENGTH,LOOKUP_CLASS,LOOKUP_RETURN,GROUP_NAME,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (6,'exportControls','Export Controls',(SELECT DATA_TYPE_CODE FROM CUSTOM_ATTRIBUTE_DATA_TYPE WHERE DESCRIPTION = 'String'),30,null,null,'Project Details','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO CUSTOM_ATTRIBUTE (ID,NAME,LABEL,DATA_TYPE_CODE,DATA_LENGTH,LOOKUP_CLASS,LOOKUP_RETURN,GROUP_NAME,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (7,'inventions','Inventions',(SELECT DATA_TYPE_CODE FROM CUSTOM_ATTRIBUTE_DATA_TYPE WHERE DESCRIPTION = 'String'),30,'org.kuali.kra.bo.KcPerson','userName','Project Details','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO CUSTOM_ATTRIBUTE (ID,NAME,LABEL,DATA_TYPE_CODE,DATA_LENGTH,LOOKUP_CLASS,LOOKUP_RETURN,GROUP_NAME,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (8,'localReviewDate','Local Review Date',(SELECT DATA_TYPE_CODE FROM CUSTOM_ATTRIBUTE_DATA_TYPE WHERE DESCRIPTION = 'Date'),10,null,null,'asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO CUSTOM_ATTRIBUTE (ID,NAME,LABEL,DATA_TYPE_CODE,DATA_LENGTH,LOOKUP_CLASS,LOOKUP_RETURN,GROUP_NAME,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (9,'courseName','Course Name',(SELECT DATA_TYPE_CODE FROM CUSTOM_ATTRIBUTE_DATA_TYPE WHERE DESCRIPTION = 'String'),80,null,null,'Course Related','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO CUSTOM_ATTRIBUTE (ID,NAME,LABEL,DATA_TYPE_CODE,DATA_LENGTH,LOOKUP_CLASS,LOOKUP_RETURN,GROUP_NAME,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (10,'instructorName','Instructor Name',(SELECT DATA_TYPE_CODE FROM CUSTOM_ATTRIBUTE_DATA_TYPE WHERE DESCRIPTION = 'String'),80,'org.kuali.kra.bo.KcPerson','fullName','Course Related','admin',SYSDATE,SYS_GUID(),1)
/
