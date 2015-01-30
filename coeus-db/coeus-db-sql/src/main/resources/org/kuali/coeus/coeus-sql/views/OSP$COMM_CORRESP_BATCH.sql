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
CREATE OR REPLACE VIEW OSP$COMM_CORRESP_BATCH AS SELECT 
    COMM_BATCH_CORRESP_ID AS CORRESP_BATCH_ID, 
    COMMITTEE_ID, 
    BATCH_CORRESPONDENCE_TYPE_CODE AS CORRESP_BATCH_TYPE_CODE, 
    BATCH_RUN_DATE, 
    TIME_WINDOW_START, 
    TIME_WINDOW_END, 
    UPDATE_TIMESTAMP, 
    UPDATE_USER
FROM COMM_BATCH_CORRESP;
