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

ALTER TABLE COI_DISCLOSURE_ATTACHMENT ADD FIN_ENTITY_ID_FK NUMBER(12,0)
/
ALTER TABLE COI_DISCLOSURE_ATTACHMENT
add CONSTRAINT fe_fk
  FOREIGN KEY (FIN_ENTITY_ID_FK)
  REFERENCES PERSON_FIN_INT_DISCLOSURE (PERSON_FIN_INT_DISCLOSURE_ID)
/
ALTER TABLE COI_DISCLOSURE_ATTACHMENT DROP COLUMN ENTITY_NUMBER
/
ALTER TABLE COI_DISCLOSURE_ATTACHMENT DROP COLUMN ENTITY_SEQUENCE_NUMBER
/
ALTER TABLE COI_DISCLOSURE_ATTACHMENT MODIFY UPDATE_USER VARCHAR(60)
/
ALTER TABLE COI_DISCLOSURE_ATTACHMENT ADD ATTACHMENT_TYPE VARCHAR(5)
/
alter table COI_DISCLOSURE_ATTACHMENT ADD EVENT_TYPE_CODE VARCHAR2(3)
/
alter table COI_DISCLOSURE_ATTACHMENT MODIFY PROJECT_ID VARCHAR2(20)
/
