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

spool 1608_oracle_kc_upgrade.sql.log
@./kc/bootstrap/V1608_001__FixIPSequenceNumbers.sql
@./kc/bootstrap/V1608_008__ffata_required.sql
@./kc/bootstrap/V1608_009__dd_override.sql
@./kc/bootstrap/V1608_011__remove_legacy_save.sql
@./kc/bootstrap/V1608_012__comm_sched_min_add_prot_sub.sql
@./kc/bootstrap/V1608_013__subaward_modification_type.sql
@./kc/bootstrap/V1608_014__fsrs_subaward_number.sql
@./kc/bootstrap/V1608_015__r_and_d.sql
@./kc/bootstrap/V1608_016__includes_cost_sharing.sql
@./kc/bootstrap/V1608_017__FCIO.sql
commit;
