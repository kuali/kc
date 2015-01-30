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
UPDATE EPS_PROP_COLUMNS_TO_ALTER SET COLUMN_LABEL = 'Opportunity ID' 
WHERE COLUMN_NAME = 'PROGRAM_ANNOUNCEMENT_NUMBER' AND COLUMN_LABEL = 'Program Number'
/
UPDATE EPS_PROP_COLUMNS_TO_ALTER SET COLUMN_LABEL = 'Opportunity Title' 
WHERE COLUMN_NAME = 'PROGRAM_ANNOUNCEMENT_TITLE' AND COLUMN_LABEL = 'Program Title'
/
UPDATE EPS_PROP_COLUMNS_TO_ALTER SET COLUMN_LABEL = 'Project Title' 
WHERE COLUMN_NAME = 'TITLE' AND COLUMN_LABEL = 'Title'
/
UPDATE EPS_PROP_COLUMNS_TO_ALTER SET COLUMN_LABEL = 'Sponsor Deadline Type' 
WHERE COLUMN_NAME = 'DEADLINE_TYPE' AND COLUMN_LABEL = 'Deadline Type'
/

COMMIT
/
DELIMITER ;
