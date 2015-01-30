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
spool KR-RELEASE-5_1_1-Upgrade-ORACLE-Install.log
@../../current/5.1.1/dml/KR_DML_01_KCINFR-814_B000.sql
@../../current/5.1.1/dml/KR_DML_01_KCIRB-1625_B000.sql
@../../current/5.1.1/dml/KR_DML_01_KRACOEUS-5726_B000.sql
@../../current/5.1.1/dml/KR_DML_01_KRACOEUS-6198_B000.sql
@../../current/5.1.1/dml/KR_DML_01_KRACOEUS-6361_B000.sql
@../../current/5.1.1/dml/KR_DML_01_KRACOEUS-6384_B000.sql
@../../current/5.1.1/dml/KR_DML_01_KRACOEUS-6388_B000.sql
@../../current/5.1.1/dml/KR_DML_01_KRACOEUS-6392_B000.sql
@../../current/5.1.1/dml/KR_DML_01_KRACOEUS-6402_B000.sql
@../../current/5.1.1/dml/KR_DML_01_KRACOEUS-6422_B000.sql
@../../current/5.1.1/dml/KR_DML_01_KRACOEUS-6445_B000.sql
@../../current/5.1.1/dml/KR_DML_01_KRACOEUS-6450_B000.sql
@../../current/5.1.1/dml/KR_DML_01_KRACOEUS-6466_B000.sql
@../../current/5.1.1/dml/KR_DML_01_KRACOEUS-6472_B000.sql
@../../current/5.1.1/dml/KR_DML_01_KRACOEUS-6526_B000.sql
@../../current/5.1.1/dml/KR_DML_01_KRACOEUS-6606_B000.sql
@../../current/5.1.1/dml/KR_DML_02_KRACOEUS-6389_B000.sql
commit;
exit
