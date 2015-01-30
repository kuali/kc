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

DELIMITER /
UPDATE COI_DISCLOSURE_EVENT_TYPE SET PROJECT_ID_LABEL = 'Award Number', PROJECT_TITLE_LABEL = 'Award' WHERE EVENT_TYPE_CODE = '1'
/

UPDATE COI_DISCLOSURE_EVENT_TYPE SET PROJECT_ID_LABEL = 'Proposal Number', PROJECT_TITLE_LABEL = 'Proposal' WHERE EVENT_TYPE_CODE = '2'
/

UPDATE COI_DISCLOSURE_EVENT_TYPE SET PROJECT_ID_LABEL = 'Protocol Number', PROJECT_TITLE_LABEL = 'IRB Protocol' WHERE EVENT_TYPE_CODE = '3'
/

UPDATE COI_DISCLOSURE_EVENT_TYPE SET PROJECT_ID_LABEL = 'Protocol Number', PROJECT_TITLE_LABEL = 'IACUC Protocol' WHERE EVENT_TYPE_CODE = '4'
/

UPDATE COI_DISCLOSURE_EVENT_TYPE SET PROJECT_ID_LABEL = 'Proposal Number', PROJECT_TITLE_LABEL = 'Institutional Proposal' WHERE EVENT_TYPE_CODE = '10'
/


DELIMITER ;
