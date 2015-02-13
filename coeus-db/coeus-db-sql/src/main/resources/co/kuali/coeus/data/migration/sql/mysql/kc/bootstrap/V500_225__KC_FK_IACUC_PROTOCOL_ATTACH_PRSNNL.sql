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
ALTER TABLE IACUC_PROTOCOL_ATTACH_PRSNNL
ADD CONSTRAINT FK_IACUC_PROTO_ATTACH_PRSNNL 
FOREIGN KEY (PROTOCOL_ID_FK) 
REFERENCES IACUC_PROTOCOL (PROTOCOL_ID)
/

ALTER TABLE IACUC_PROTOCOL_ATTACH_PRSNNL
ADD CONSTRAINT FK_IACUC_PA_PRSNNL_PERSON 
FOREIGN KEY (PERSON_ID) 
REFERENCES IACUC_PROTOCOL_PERSONS (PROTOCOL_PERSON_ID)
/

ALTER TABLE IACUC_PROTOCOL_ATTACH_PRSNNL
ADD CONSTRAINT FK_IACUC_PA_PRSNNL_FILE 
FOREIGN KEY (FILE_ID) 
REFERENCES ATTACHMENT_FILE (FILE_ID)
/

ALTER TABLE IACUC_PROTOCOL_ATTACH_PRSNNL
ADD CONSTRAINT FK_IACUC_PA_PRSNNL_TYPE
FOREIGN KEY (TYPE_CD) 
REFERENCES IACUC_PROTOCOL_ATTACHMENT_TYPE (TYPE_CD)
/
DELIMITER ;
