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

-- View for Coeus compatibility 
CREATE OR REPLACE VIEW OSP$SCHEDULE_AGENDA AS SELECT 
    COMM_SCHEDULE.SCHEDULE_ID, 
    SCHEDULE_AGENDA.AGENDA_NUMBER, 
    SCHEDULE_AGENDA.AGENDA_NAME, 
    SCHEDULE_AGENDA.PDF_STORE, 
    SCHEDULE_AGENDA.CREATE_TIMESTAMP, 
    SCHEDULE_AGENDA.CREATE_USER, 
    SCHEDULE_AGENDA.UPDATE_TIMESTAMP, 
    SCHEDULE_AGENDA.UPDATE_USER
FROM SCHEDULE_AGENDA,
     COMM_SCHEDULE
WHERE COMM_SCHEDULE.ID = SCHEDULE_AGENDA.SCHEDULE_ID_FK;

