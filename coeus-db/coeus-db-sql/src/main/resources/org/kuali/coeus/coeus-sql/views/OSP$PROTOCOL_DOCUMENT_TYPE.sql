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

CREATE OR REPLACE VIEW OSP$PROTOCOL_DOCUMENT_TYPE AS 
    SELECT t.TYPE_CD, t.DESCRIPTION, t.UPDATE_TIMESTAMP, t.UPDATE_USER, g.GROUP_CD
    FROM PROTOCOL_ATTACHMENT_TYPE t, PROTOCOL_ATTACHMENT_GROUP g, PROTOCOL_ATTACHMENT_TYPE_GROUP tg
    WHERE t.TYPE_CD = tg.TYPE_CD AND g.GROUP_CD = tg.GROUP_CD;
