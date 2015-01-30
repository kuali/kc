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

INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Protocol Narrative',SYS_GUID(),'1',SYSDATE,'admin',1)
/
INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Brochure',SYS_GUID(),'2',SYSDATE,'admin',1)
/
INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Other Protocol Attachments',SYS_GUID(),'3',SYSDATE,'admin',1)
/
INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Other Attachments',SYS_GUID(),'4',SYSDATE,'admin',1)
/
INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Investigational New Drug Brochure',SYS_GUID(),'5',SYSDATE,'admin',1)
/
INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Amendments and Renewals',SYS_GUID(),'6',SYSDATE,'admin',1)
/
INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Progress Report',SYS_GUID(),'7',SYSDATE,'admin',1)
/
INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Protocol Submission Documents',SYS_GUID(),'8',SYSDATE,'admin',1)
/
INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Other Documents',SYS_GUID(),'9',SYSDATE,'admin',1)
/
INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Adverse Event',SYS_GUID(),'10',SYSDATE,'admin',1)
/
INSERT INTO IACUC_PROTOCOL_ATTACHMENT_TYPE (DESCRIPTION,OBJ_ID,TYPE_CD,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES ('Biography',SYS_GUID(),'11',SYSDATE,'admin',1)
/
