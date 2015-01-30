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

CREATE OR REPLACE VIEW OSP$PROTOCOL_SUBMISSION_DOC AS 
    SELECT pan.PROTOCOL_NUMBER, pan.SEQUENCE_NUMBER, paf.FILE_NAME, paf.FILE_DATA,
    pan.UPDATE_TIMESTAMP, pan.UPDATE_USER, pan.DOCUMENT_ID
    FROM PROTOCOL_ATTACHMENT_NOTIF pan, PROTOCOL_ATTACHMENT_FILE paf, PROTOCOL_SUBMISSION ps
    WHERE pan.FILE_ID_FK = paf.PA_FILE_ID AND pan.SUBMISSION_ID_FK = ps.SUBMISSION_ID;
