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
UPDATE KRCR_CMPNT_T T SET T.CMPNT_CD = 'Document' where T.CMPNT_CD = 'D' AND NMSPC_CD LIKE 'KC%'
/
UPDATE KRCR_CMPNT_T T SET T.CMPNT_CD = 'All' where T.CMPNT_CD = 'A' AND NMSPC_CD LIKE 'KC%'
/
UPDATE KRCR_CMPNT_T T SET T.CMPNT_CD = 'Lookup' where T.CMPNT_CD = 'L' AND NMSPC_CD LIKE 'KC%'
/
COMMIT
/
DELIMITER ;
