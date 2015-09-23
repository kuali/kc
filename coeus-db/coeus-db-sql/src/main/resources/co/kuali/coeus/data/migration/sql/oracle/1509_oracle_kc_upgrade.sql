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

spool 1509_oracle_kc_upgrade.sql.log
@./kc/bootstrap/V1509_001__RESKC-715-createUser.sql
@./kc/bootstrap/V1509_002__activemq.sql
@./kc/bootstrap/V1509_003__RESKC-793.sql
@./kc/bootstrap/V1509_004__RESKC-852.sql
@./kc/bootstrap/V1509_005__RESKC-753.sql
@./kc/bootstrap/V1509_006__RESKC-836.sql
commit;
