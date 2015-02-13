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

INSERT INTO SEQ_PROTO_CORRESP_TEMPL VALUES (null);
INSERT INTO PROTO_CORRESP_TEMPL(PROTO_CORRESP_TEMPL_ID, PROTO_CORRESP_TYPE_CODE, COMMITTEE_ID, FILE_NAME, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID, CORRESPONDENCE_TEMPLATE)
SELECT LAST_INSERT_ID(), '28', 'DEFAULT', 'DEFAULT-28-AbandonNotice.xsl', STR_TO_DATE('2011-01-27 16:00:14','%Y-%m-%d %k:%i:%s'), 'admin', 1, uuid(),
   (select replace(replace(CORRESPONDENCE_TEMPLATE, 'Withdrawal','Abandon'), 'withdrawn', 'abandoned') from PROTO_CORRESP_TEMPL where PROTO_CORRESP_TYPE_CODE = '16');
   
COMMIT;
