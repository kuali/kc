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

--
-- KULRICE-7378 - MySQL Upgrade script for Rice 2.0 is dropping not null constraints
--

--
-- NOTE - This is only an issue for the MySQL scripts, so that is why there is no corresponding
--        2012-010-24.sql script for Oracle.
--

ALTER TABLE KRSB_MSG_QUE_T MODIFY APPL_ID VARCHAR(255) NOT NULL
/
DELIMITER ;

