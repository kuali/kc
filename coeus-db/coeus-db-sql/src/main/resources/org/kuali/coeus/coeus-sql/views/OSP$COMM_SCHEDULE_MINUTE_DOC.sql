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
CREATE OR REPLACE VIEW OSP$COMM_SCHEDULE_MINUTE_DOC AS SELECT 
    COMM_SCHEDULE.SCHEDULE_ID, 
    COMM_SCHEDULE_MINUTE_DOC.MINUTE_NUMBER, 
    COMM_SCHEDULE_MINUTE_DOC.MINUTE_NAME, 
    COMM_SCHEDULE_MINUTE_DOC.PDF_STORE, 
    COMM_SCHEDULE_MINUTE_DOC.CREATE_TIMESTAMP, 
    COMM_SCHEDULE_MINUTE_DOC.CREATE_USER, 
    COMM_SCHEDULE_MINUTE_DOC.UPDATE_TIMESTAMP, 
    COMM_SCHEDULE_MINUTE_DOC.UPDATE_USER
FROM COMM_SCHEDULE_MINUTE_DOC,
     COMM_SCHEDULE
WHERE COMM_SCHEDULE.ID = COMM_SCHEDULE_MINUTE_DOC.SCHEDULE_ID_FK;
