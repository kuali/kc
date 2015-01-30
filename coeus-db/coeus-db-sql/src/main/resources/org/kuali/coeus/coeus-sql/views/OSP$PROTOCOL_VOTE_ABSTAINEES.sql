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
CREATE OR REPLACE VIEW OSP$PROTOCOL_VOTE_ABSTAINEES AS SELECT 
    PROTOCOL.PROTOCOL_NUMBER, 
    PROTOCOL.SEQUENCE_NUMBER, 
    COMM_SCHEDULE.SCHEDULE_ID, 
    PROTOCOL_VOTE_ABSTAINEES.PERSON_ID, 
    PROTOCOL_VOTE_ABSTAINEES.NON_EMPLOYEE_FLAG, 
    PROTOCOL_VOTE_ABSTAINEES.COMMENTS, 
    PROTOCOL_VOTE_ABSTAINEES.UPDATE_TIMESTAMP, 
    PROTOCOL_VOTE_ABSTAINEES.UPDATE_USER
FROM PROTOCOL_VOTE_ABSTAINEES,
     COMM_SCHEDULE,
     PROTOCOL
WHERE COMM_SCHEDULE.ID = PROTOCOL_VOTE_ABSTAINEES.SCHEDULE_ID_FK and
      PROTOCOL.PROTOCOL_ID = PROTOCOL_VOTE_ABSTAINEES.PROTOCOL_ID_FK;

