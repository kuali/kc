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
spool KR_RICE-RELEASE-5_2_0-Upgrade-ORACLE-Install.log
@../../current/5.2.0/rice/KR_RICE_01_2.1.3-2.2.1_UPDATE_2.1.0-2.2.0_B000.sql
@../../current/5.2.0/rice/KR_RICE_01_2.2.1-2.2.2_2013-03-18_B000.sql
@../../current/5.2.0/rice/KR_RICE_01_2.3.0-2.3.1_2013-08-08_B000.sql
@../../current/5.2.0/rice/KR_RICE_01_2.3.0-2.3.1_2013-08-14_B000.sql
@../../current/5.2.0/rice/KR_RICE_01_2.3.0-2.3.1_2013-08-23_B000.sql
@../../current/5.2.0/rice/KR_RICE_01_2.3.0-2.3.1_2013-09-05_B000.sql
@../../current/5.2.0/rice/KR_RICE_02_2.3.0-2.3.1_2013-08-14_B000.sql
@../../current/5.2.0/rice/KR_RICE_02_2.3.0-2.3.1_2013-08-23_B000.sql
@../../current/5.2.0/rice/KR_RICE_03_2.3.0-2.3.1_2013-08-23_B000.sql
commit;
exit
