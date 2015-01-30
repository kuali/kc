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
CREATE OR REPLACE VIEW OSP$COMM_CORRESP_BATCH_DETAIL AS SELECT 
    COMM_BATCH_CORRESP_DETAIL.COMM_BATCH_CORRESP_ID AS CORRESP_BATCH_ID, 
    PROTOCOL_ACTIONS.PROTOCOL_NUMBER, 
    PROTOCOL_ACTIONS.SEQUENCE_NUMBER, 
    PROTOCOL_ACTIONS.ACTION_ID, 
    COMM_BATCH_CORRESP_DETAIL.UPDATE_TIMESTAMP, 
    COMM_BATCH_CORRESP_DETAIL.UPDATE_USER
FROM COMM_BATCH_CORRESP_DETAIL, PROTOCOL_ACTIONS
WHERE COMM_BATCH_CORRESP_DETAIL.PROTOCOL_ACTIONS_ID = PROTOCOL_ACTIONS.PROTOCOL_ACTIONS_ID;
