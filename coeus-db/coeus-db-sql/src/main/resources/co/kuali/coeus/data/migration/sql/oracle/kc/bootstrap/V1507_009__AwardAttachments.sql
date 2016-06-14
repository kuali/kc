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

ALTER TABLE AWARD_ATTACHMENT add DOCUMENT_STATUS_CODE CHAR(1);
ALTER TABLE AWARD_ATTACHMENT ADD LAST_UPDATE_USER VARCHAR2(60);
ALTER TABLE AWARD_ATTACHMENT ADD LAST_UPDATE_TIMESTAMP DATE;
UPDATE AWARD_ATTACHMENT SET LAST_UPDATE_USER = UPDATE_USER WHERE LAST_UPDATE_USER IS NULL;
UPDATE AWARD_ATTACHMENT SET LAST_UPDATE_TIMESTAMP = UPDATE_TIMESTAMP WHERE LAST_UPDATE_TIMESTAMP IS NULL;