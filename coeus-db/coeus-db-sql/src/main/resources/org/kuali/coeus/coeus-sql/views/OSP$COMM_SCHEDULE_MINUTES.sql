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
CREATE OR REPLACE VIEW OSP$COMM_SCHEDULE_MINUTES AS SELECT 
    COMM_SCHEDULE.SCHEDULE_ID, 
    COMM_SCHEDULE_MINUTES.ENTRY_NUMBER, 
    COMM_SCHEDULE_MINUTES.MINUTE_ENTRY_TYPE_CODE, 
    PROTOCOL.PROTOCOL_NUMBER, 
    PROTOCOL.SEQUENCE_NUMBER, 
    COMM_SCHEDULE_MINUTES.SUBMISSION_NUMBER, 
    COMM_SCHEDULE_MINUTES.PRIVATE_COMMENT_FLAG, 
    COMM_SCHEDULE_MINUTES.PROTOCOL_CONTINGENCY_CODE, 
    COMM_SCHEDULE_MINUTES.MINUTE_ENTRY, 
    COMM_SCHEDULE_MINUTES.UPDATE_TIMESTAMP, 
    COMM_SCHEDULE_MINUTES.UPDATE_USER
FROM COMM_SCHEDULE_MINUTES,
     COMM_SCHEDULE,
     PROTOCOL
WHERE COMM_SCHEDULE.ID = COMM_SCHEDULE_MINUTES.SCHEDULE_ID_FK and
      PROTOCOL.PROTOCOL_ID = COMM_SCHEDULE_MINUTES.PROTOCOL_ID_FK;
