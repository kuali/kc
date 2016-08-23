--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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

ALTER TABLE COMM_SCHEDULE_MINUTES ADD PROTOCOL_NUMBER VARCHAR(20);
UPDATE COMM_SCHEDULE_MINUTES SET PROTOCOL_NUMBER = (SELECT PROTOCOL_NUMBER FROM PROTOCOL WHERE PROTOCOL_ID = PROTOCOL_ID_FK);

ALTER TABLE COMM_SCHEDULE_MINUTES ADD SUBMISSION_NUMBER DECIMAL(4);
UPDATE COMM_SCHEDULE_MINUTES SET SUBMISSION_NUMBER = (SELECT SUBMISSION_NUMBER FROM PROTOCOL_SUBMISSION WHERE SUBMISSION_ID = SUBMISSION_ID_FK);
