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

@./rice/bootstrap/V1505_003__add_schemaspy_auth.sql
@./rice/bootstrap/V1505_005__hide_ip_review.sql
@./rice/bootstrap/V1505_006__KRACOEUS-8109.sql
@./rice/bootstrap/V1505_008__SalariesDocumentLevel.sql
@./rice/bootstrap/V1505_011__RESOPS-114.sql
@./rice/bootstrap/V1505_012__clean_term_spec.sql
@./rice/bootstrap/V1505_015__RESKC-5.sql
commit;
