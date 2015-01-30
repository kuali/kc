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
CREATE OR REPLACE VIEW OSP$CORRESP_BATCH_DETAIL AS SELECT 
    BATCH_CORRESPONDENCE_TYPE_CODE AS CORRESP_BATCH_TYPE_CODE, 
    CORRESPONDENCE_NUMBER, 
    PROTO_CORRESP_TYPE_CODE, 
    NO_OF_DAYS_TILL_NEXT, 
    UPDATE_TIMESTAMP, 
    UPDATE_USER
FROM BATCH_CORRESPONDENCE_DETAIL;
