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
TRUNCATE TABLE PROTOCOL_ATTACHMENT_TYPE
/
INSERT INTO PROTOCOL_ATTACHMENT_TYPE (TYPE_CD,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','Informed Consent Document','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_ATTACHMENT_TYPE (TYPE_CD,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','Recruitment Brochure','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_ATTACHMENT_TYPE (TYPE_CD,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('3','Advertisement','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_ATTACHMENT_TYPE (TYPE_CD,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('4','Protocol Narrative','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_ATTACHMENT_TYPE (TYPE_CD,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('5','Investigator Brochure','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_ATTACHMENT_TYPE (TYPE_CD,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('6','Children''s Assent Form','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_ATTACHMENT_TYPE (TYPE_CD,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('7','HIPAA Research Authorization Form','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_ATTACHMENT_TYPE (TYPE_CD,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('8','HIPAA Waiver of Research Authorization Form','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_ATTACHMENT_TYPE (TYPE_CD,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('9','Other','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_ATTACHMENT_TYPE (TYPE_CD,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('10','Adverse Event','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_ATTACHMENT_TYPE (TYPE_CD,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('11','Biography','admin',NOW(),UUID(),1)
/
delimiter ;
