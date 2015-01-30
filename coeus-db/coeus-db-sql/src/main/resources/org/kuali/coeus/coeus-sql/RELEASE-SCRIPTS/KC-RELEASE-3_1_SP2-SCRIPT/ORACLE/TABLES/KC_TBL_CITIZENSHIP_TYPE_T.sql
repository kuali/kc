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

-- Moving data around so the column is empty when changed --
-- ALTER TABLE CITIZENSHIP_TYPE_T MODIFY CITIZENSHIP_TYPE_CODE NUMBER(15,0); --
ALTER TABLE CITIZENSHIP_TYPE_T 
  DROP PRIMARY KEY CASCADE;
ALTER TABLE CITIZENSHIP_TYPE_T 
  ADD (CITIZENSHIP_TYPE_CODE_TEMP NUMBER(15,0));
UPDATE CITIZENSHIP_TYPE_T
  SET CITIZENSHIP_TYPE_CODE_TEMP = CITIZENSHIP_TYPE_CODE;
ALTER TABLE CITIZENSHIP_TYPE_T
  DROP COLUMN CITIZENSHIP_TYPE_CODE;
ALTER TABLE CITIZENSHIP_TYPE_T 
  ADD (CITIZENSHIP_TYPE_CODE NUMBER(15,0));
UPDATE CITIZENSHIP_TYPE_T
  SET CITIZENSHIP_TYPE_CODE = CITIZENSHIP_TYPE_CODE_TEMP;
ALTER TABLE CITIZENSHIP_TYPE_T
  DROP COLUMN CITIZENSHIP_TYPE_CODE_TEMP;
ALTER TABLE CITIZENSHIP_TYPE_T
  ADD (CONSTRAINT CITIZEN_TYPE_PK1 PRIMARY KEY (CITIZENSHIP_TYPE_CODE));
ALTER TABLE PERSON_EXT_T
  ADD (CONSTRAINT PERSON_EXT_FK1 FOREIGN KEY(CITIZENSHIP_TYPE_CODE)
  REFERENCES CITIZENSHIP_TYPE_T (CITIZENSHIP_TYPE_CODE));
