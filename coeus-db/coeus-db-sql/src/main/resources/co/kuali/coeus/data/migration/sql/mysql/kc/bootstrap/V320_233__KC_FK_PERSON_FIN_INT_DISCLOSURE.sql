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
ALTER TABLE PERSON_FIN_INT_DISCLOSURE 
ADD CONSTRAINT FK_FIN_INT_ENTITY_REL_TYPE 
FOREIGN KEY (RELATIONSHIP_TYPE_CODE) 
REFERENCES FIN_INT_ENTITY_REL_TYPE (RELATIONSHIP_TYPE_CODE)
/

ALTER TABLE PERSON_FIN_INT_DISCLOSURE 
ADD CONSTRAINT FK_FIN_INT_ENTITY_STATUS 
FOREIGN KEY (STATUS_CODE) 
REFERENCES FIN_INT_ENTITY_STATUS (STATUS_CODE)
/

ALTER TABLE PERSON_FIN_INT_DISCLOSURE 
ADD CONSTRAINT FK_FIN_INT_ENTITY_TYPE 
FOREIGN KEY (ENTITY_TYPE_CODE) 
REFERENCES ORGANIZATION_TYPE_LIST (ORGANIZATION_TYPE_CODE)
/
ALTER TABLE PERSON_FIN_INT_DISCLOSURE 
ADD CONSTRAINT FK_FIN_INT_REPORTER 
FOREIGN KEY (FINANCIAL_ENTITY_REPORTER_ID) 
REFERENCES FINANCIAL_ENTITY_REPORTER (FINANCIAL_ENTITY_REPORTER_ID)
/

DELIMITER ;
