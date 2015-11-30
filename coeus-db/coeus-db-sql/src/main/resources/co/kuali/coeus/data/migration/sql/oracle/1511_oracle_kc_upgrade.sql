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

spool 1511_oracle_kc_upgrade.sql.log
@./kc/bootstrap/V1511_001__FAIN.sql
@./kc/bootstrap/V1511_002__RESKC-603.sql
@./kc/bootstrap/V1511_003__watermark_text.sql
@./kc/bootstrap/V1511_009__RESKC-978.sql
@./kc/bootstrap/V1511_010__RESKC-1007.sql
@./kc/bootstrap/V1511_011__fix_copied_budget_data.sql
@./kc/bootstrap/V1511_012__RESKC-1015.sql
@./kc/bootstrap/V1511_013__RESKC-862.sql
@./kc/bootstrap/V1511_015__RESOPS-555.sql
@./kc/bootstrap/V1511_016__org_audit_accepted.sql
commit;
