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
TRUNCATE TABLE PROTOCOL_MODULES
/
INSERT INTO PROTOCOL_MODULES (PROTOCOL_MODULE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('001','General Info','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_MODULES (PROTOCOL_MODULE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('002','Protocol Personnel','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_MODULES (PROTOCOL_MODULE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('004','Areas of Research','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_MODULES (PROTOCOL_MODULE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('006','Subjects','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_MODULES (PROTOCOL_MODULE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('007','Special Review','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_MODULES (PROTOCOL_MODULE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('008','Add/Modify Attachments','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_MODULES (PROTOCOL_MODULE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('016','Protocol References','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_MODULES (PROTOCOL_MODULE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('017','Protocol Organizations','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_MODULES (PROTOCOL_MODULE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('023','Others','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_MODULES (PROTOCOL_MODULE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('024','Funding Source','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_MODULES (PROTOCOL_MODULE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('025','Protocol Permissions','admin',NOW(),UUID(),1)
/
delimiter ;
