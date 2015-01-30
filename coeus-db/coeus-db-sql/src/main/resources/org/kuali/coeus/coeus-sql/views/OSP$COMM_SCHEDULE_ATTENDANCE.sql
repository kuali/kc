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
CREATE OR REPLACE VIEW OSP$COMM_SCHEDULE_ATTENDANCE AS SELECT 
    COMM_SCHEDULE.SCHEDULE_ID, 
    COMM_SCHEDULE_ATTENDANCE.PERSON_ID, 
    COMM_SCHEDULE_ATTENDANCE.GUEST_FLAG, 
    COMM_SCHEDULE_ATTENDANCE.ALTERNATE_FLAG, 
    COMM_SCHEDULE_ATTENDANCE.ALTERNATE_FOR, 
    COMM_SCHEDULE_ATTENDANCE.NON_EMPLOYEE_FLAG, 
    COMM_SCHEDULE_ATTENDANCE.COMMENTS, 
    COMM_SCHEDULE_ATTENDANCE.UPDATE_TIMESTAMP, 
    COMM_SCHEDULE_ATTENDANCE.UPDATE_USER
FROM COMM_SCHEDULE_ATTENDANCE,
     COMM_SCHEDULE
WHERE COMM_SCHEDULE.ID = COMM_SCHEDULE_ATTENDANCE.SCHEDULE_ID_FK;

