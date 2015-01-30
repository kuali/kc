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

set define off
set sqlblanklines on
spool KRC_RICE-RELEASE-4_0-Upgrade-ORACLE-Install.log
@oracle/rice/KRC_RICE_01_1.0.3.2-2.0.0_2010-04-15_B000.sql
@oracle/rice/KRC_RICE_02_1.0.3.2-2.0.0_2011-06-06_B000.sql
@oracle/rice/KRC_RICE_03_1.0.3.2-2.0.0_2012-01-19c_B000.sql
commit;
exit
