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

spool 1506_oracle_kc_demo.sql.log
@./kc/demo/V1506_012__fixLARateCalculationsBudgetSummaryPeriod1_00SD.sql
@./kc/demo/V1506_013__fixLARateCalculationsBudgetSummaryPeriod2_00SD.sql
@./kc/demo/V1506_014__fixLARateCalculationsBudgetSummaryPeriod3_00SD.sql
@./kc/demo/V1506_015__fixLARateCalculationsBudgetSummaryPeriod4_00SD.sql
@./kc/demo/V1506_016__fixLARateCalculationsBudgetSummaryPeriod5_00SD.sql
@./kc/demo/V1506_018__RESKC-55.sql
@./kc/demo/V1506_020__RESKC-55.sql
commit;
