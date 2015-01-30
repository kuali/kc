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
DELETE FROM NOTIFICATION_TYPE_RECIPIENT WHERE NOTIFICATION_TYPE_ID IN
         (SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE MODULE_CODE=9 AND ACTION_CODE IN (200, 203, 309, 908))
         AND
         ROLE_NAME='KC-IACUC:PI'
/
DELETE FROM NOTIFICATION_TYPE_RECIPIENT WHERE NOTIFICATION_TYPE_ID IN
         (SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE MODULE_CODE=7 AND ACTION_CODE IN (200, 905))
         AND
         ROLE_NAME='KC-PROTOCOL:PI'
/
DELIMITER ;
