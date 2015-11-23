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

CREATE INDEX COMM_MEMBERSHIPS_COM_IDX
ON COMM_MEMBERSHIPS (COMMITTEE_ID_FK);

CREATE INDEX COMM_SCHEDULE_COM_IDX
ON COMM_SCHEDULE (COMMITTEE_ID_FK);

CREATE INDEX COMM_SCHEDULE_ATT_SCH_IDX
ON COMM_SCHEDULE_ATTACHMENTS (SCHEDULE_ID_FK);
   