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

@./rice/bootstrap/V601_004__Resolve_Repackaging_Db_Issues.sql
@./rice/bootstrap/V601_010__KRACOEUS-8837.sql
@./rice/bootstrap/V601_011__KRACOEUS-8454.sql
@./rice/bootstrap/V601_012__Fix_Duplicate_KEW_Docs.sql
@./rice/bootstrap/V601_015__KRACOEUS-8855.sql
@./rice/bootstrap/V601_018__KRACOEUS-8890.sql
@./rice/bootstrap/V601_020__KRACOEUS-8898.sql
@./rice/bootstrap/V601_021__sponsor_kfs_int.sql
@./rice/bootstrap/V601_023__KRACOEUS-8767.sql
commit;
