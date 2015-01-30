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
CREATE OR REPLACE VIEW OSP$COMM_SCHEDULE AS SELECT 
    COMM_SCHEDULE.SCHEDULE_ID, 
    COMMITTEE.COMMITTEE_ID, 
    COMM_SCHEDULE.SCHEDULED_DATE, 
    COMM_SCHEDULE.PLACE, 
    COMM_SCHEDULE.TIME, 
    COMM_SCHEDULE.PROTOCOL_SUB_DEADLINE, 
    COMM_SCHEDULE.SCHEDULE_STATUS_CODE, 
    COMM_SCHEDULE.MEETING_DATE, 
    COMM_SCHEDULE.START_TIME, 
    COMM_SCHEDULE.END_TIME, 
    COMM_SCHEDULE.AGENDA_PROD_REV_DATE, 
    COMM_SCHEDULE.MAX_PROTOCOLS, 
    COMM_SCHEDULE.COMMENTS, 
    COMM_SCHEDULE.UPDATE_TIMESTAMP, 
    COMM_SCHEDULE.UPDATE_USER
FROM COMM_SCHEDULE, COMMITTEE
WHERE COMM_SCHEDULE.COMMITTEE_ID_FK = COMMITTEE.ID;
