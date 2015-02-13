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
ALTER TABLE IACUC_PROTOCOL_SUBMISSION_DOC ADD SUBMISSION_DOC_ID DECIMAL(12)
/

ALTER TABLE IACUC_PROTOCOL_SUBMISSION_DOC
ADD CONSTRAINT PK_IACUC_PROTO_SUBMISSION_DOC
PRIMARY KEY (SUBMISSION_DOC_ID)
/

ALTER TABLE IACUC_PROTOCOL_SUBMISSION_DOC ADD PROTOCOL_ID DECIMAL(12,0) NOT NULL
/

ALTER TABLE IACUC_PROTOCOL_SUBMISSION_DOC ADD SUBMISSION_ID_FK DECIMAL(12,0) NOT NULL
/

ALTER TABLE IACUC_PROTOCOL_SUBMISSION_DOC
ADD CONSTRAINT FK_IACUC_PROTO_SUBMISSION_DOC1
FOREIGN KEY (PROTOCOL_ID) 
REFERENCES IACUC_PROTOCOL (PROTOCOL_ID)
/

ALTER TABLE IACUC_PROTOCOL_SUBMISSION_DOC
ADD CONSTRAINT FK_IACUC_PROTO_SUBMISSION_DOC2
FOREIGN KEY (SUBMISSION_ID_FK) 
REFERENCES IACUC_PROTOCOL_SUBMISSION (IACUC_PROTOCOL_SUBMISSION_ID)
/

DELIMITER ;
