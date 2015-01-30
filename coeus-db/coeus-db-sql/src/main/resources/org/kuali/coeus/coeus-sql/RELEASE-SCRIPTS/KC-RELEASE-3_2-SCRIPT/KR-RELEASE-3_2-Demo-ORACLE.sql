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
spool KR-RELEASE-3_2-Demo-ORACLE-Install.log
@../../current/4.0/dml/KR_DML_32001_KRACOEUS-4976_0TSD.sql
@../../current/4.0/dml/KR_DML_32001_KRACOEUS-5091_0TSD.sql
@../../current/4.0/dml/KR_DML_32001_KRIM_ROLE_MBR_T_0TSD.sql
commit;
exit
