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

DELIMITER /
INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Protocol Narrative',UUID(),'1',NOW(),'admin',1)
/
INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Brochure',UUID(),'2',NOW(),'admin',1)
/
INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Other Protocol Attachments',UUID(),'3',NOW(),'admin',1)
/
INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Other Attachments',UUID(),'4',NOW(),'admin',1)
/
INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Investigational New Drug Brochure',UUID(),'5',NOW(),'admin',1)
/
INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Amendments and Renewals',UUID(),'6',NOW(),'admin',1)
/
INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Progress Report',UUID(),'7',NOW(),'admin',1)
/
INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Protocol Submission Documents',UUID(),'8',NOW(),'admin',1)
/
INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Other Documents',UUID(),'9',NOW(),'admin',1)
/
INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Adverse Event',UUID(),'10',NOW(),'admin',1)
/
INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Biography',UUID(),'11',NOW(),'admin',1)
/
DELIMITER ;
