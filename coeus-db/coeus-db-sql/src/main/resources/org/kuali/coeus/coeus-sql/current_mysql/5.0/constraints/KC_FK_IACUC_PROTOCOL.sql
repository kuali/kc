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
ALTER TABLE IACUC_PROTOCOL 
ADD CONSTRAINT FK_IACUC_PROTOCOL_STATUS 
FOREIGN KEY (PROTOCOL_STATUS_CODE) 
REFERENCES IACUC_PROTOCOL_STATUS (PROTOCOL_STATUS_CODE)
/

ALTER TABLE IACUC_PROTOCOL 
ADD CONSTRAINT FK_IACUC_PROTOCOL_PROJECT_TYPE 
FOREIGN KEY (PROJECT_TYPE_CODE) 
REFERENCES IACUC_PROTOCOL_PROJECT_TYPE (PROJECT_TYPE_CODE)
/

ALTER TABLE IACUC_PROTOCOL 
ADD CONSTRAINT FK_IACUC_PROTOCOL_TYPE 
FOREIGN KEY (PROTOCOL_TYPE_CODE) 
REFERENCES IACUC_PROTOCOL_TYPE (PROTOCOL_TYPE_CODE)
/

DELIMITER ;
