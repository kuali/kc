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

CREATE VIEW OSP$PROTOCOL_OTHER_DOCUMENTS AS
SELECT pro.PROTOCOL_NUMBER, pro.SEQUENCE_NUMBER, pap.TYPE_CD, pap.DESCRIPTION, paf.FILE_NAME, paf.FILE_DATA,
    pap.UPDATE_TIMESTAMP, pap.UPDATE_USER, pap.DOCUMENT_ID
FROM PROTOCOL_ATTACHMENT_PROTOCOL pap, ATTACHMENT_FILE paf, PROTOCOL pro
WHERE pap.FILE_ID = paf.FILE_ID
    AND pap.TYPE_CD = '9' 
    AND pro.PROTOCOL_ID = pap.PA_PROTOCOL_ID
/
commit
/
