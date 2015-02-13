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

ALTER TABLE PROTOCOL_FUNDING_SOURCE DROP FOREIGN KEY FK_PROTOCOL_FUNDING_SOURCE2;
ALTER TABLE PROTOCOL_FUNDING_SOURCE MODIFY FUNDING_SOURCE_TYPE_CODE VARCHAR(3);

ALTER TABLE FUNDING_SOURCE_TYPE MODIFY FUNDING_SOURCE_TYPE_CODE VARCHAR(3);

ALTER TABLE PROTOCOL_FUNDING_SOURCE
  ADD CONSTRAINT FK_PROTOCOL_FUNDING_SOURCE2 FOREIGN KEY (FUNDING_SOURCE_TYPE_CODE)
  REFERENCES FUNDING_SOURCE_TYPE (FUNDING_SOURCE_TYPE_CODE);
