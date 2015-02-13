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
ALTER TABLE IACUC_PROTOCOL_STUDY_GROUP_HDR 
ADD CONSTRAINT FK_STUD_GRP_HDR_PROC_CAT_CODE 
FOREIGN KEY (PROCEDURE_CATEGORY_CODE) 
REFERENCES IACUC_PROCEDURE_CATEGORY (PROCEDURE_CATEGORY_CODE)
/

ALTER TABLE IACUC_PROTOCOL_STUDY_GROUP_HDR 
ADD CONSTRAINT FK_STUD_GRP_HDR_PROC_CODE 
FOREIGN KEY (PROCEDURE_CODE) 
REFERENCES IACUC_PROCEDURES (PROCEDURE_CODE)
/

ALTER TABLE IACUC_PROTOCOL_STUDY_GROUP_HDR 
ADD CONSTRAINT FK_STUD_GRP_HDR_PROTOCOL_ID 
FOREIGN KEY (PROTOCOL_ID) 
REFERENCES IACUC_PROTOCOL (PROTOCOL_ID)
/

DELIMITER ;
