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
spool KR-RELEASE-5_2_1-Upgrade-ORACLE-Install.log
@../../current/5.2.1/dml/KR_DML_01_KRACOEUS-6640_B000.sql
@../../current/5.2.1/dml/KR_DML_01_KRACOEUS-6774_B000.sql
@../../current/5.2.1/dml/KR_DML_01_KRACOEUS-6848_B000.sql
@../../current/5.2.1/dml/KR_DML_01_KRACOEUS-6862_B000.sql
@../../current/5.2.1/dml/KR_DML_01_KRACOEUS-6960_B000.sql
@../../current/5.2.1/dml/KR_DML_01_KRACOEUS-6980_B000.sql
@../../current/5.2.1/dml/KR_DML_01_KRACOEUS-7031_B000.sql
@../../current/5.2.1/dml/KR_DML_01_KRACOEUS-7046_B000.sql
@../../current/5.2.1/dml/KR_DML_01_KRACOEUS-7150_B000.sql
@../../current/5.2.1/dml/KR_DML_01_KRACOEUS-7152_B000.sql
@../../current/5.2.1/dml/KR_DML_01_KRACOEUS-7174_B000.sql
@../../current/5.2.1/dml/KR_DML_01_KRACOEUS-7207_B000.sql
@../../current/5.2.1/dml/KR_DML_01_KRACOEUS-7211_B000.sql
@../../current/5.2.1/dml/KR_DML_01_KRACOEUS-7214_B000.sql
@../../current/5.2.1/dml/KR_DML_01_KRACOEUS-7230_B000.sql
@../../current/5.2.1/dml/KR_DML_02_KRACOEUS-6848_B000.sql
@../../current/5.2.1/dml/KR_DML_02_KRACOEUS-7444_B000.sql
@../../current/5.2.1/dml/KR_DML_03_KRACOEUS-6848_B000.sql
commit;
exit
