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

ALTER TABLE FIN_OBJECT_CODE_MAPPING
  ADD CONSTRAINT FK_UNIT_NUMBER FOREIGN KEY (UNIT_NUMBER)
  REFERENCES UNIT (UNIT_NUMBER)
/

ALTER TABLE FIN_OBJECT_CODE_MAPPING
  ADD CONSTRAINT FK_RATE_TYPE_CODE FOREIGN KEY (RATE_TYPE_CODE, RATE_CLASS_CODE)
  REFERENCES RATE_TYPE (RATE_TYPE_CODE, RATE_CLASS_CODE)
/

ALTER TABLE FIN_OBJECT_CODE_MAPPING
  ADD CONSTRAINT FK_ACTIVITY_TYPE FOREIGN KEY (ACTIVITY_TYPE_CODE)
  REFERENCES ACTIVITY_TYPE (ACTIVITY_TYPE_CODE)
/

DELIMITER ;
