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
UPDATE ROLODEX T SET T.SPONSOR_ADDRESS_FLAG = 'Y' WHERE T.ROLODEX_ID IN (SELECT T1.ROLODEX_ID FROM SPONSOR T1)
/
UPDATE ROLODEX T SET T.SPONSOR_ADDRESS_FLAG = 'N' WHERE T.ROLODEX_ID NOT IN (SELECT T1.ROLODEX_ID FROM SPONSOR T1)
/

DELIMITER ;
