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

spool 1505_oracle_kc_upgrade.sql.log
@./kc/bootstrap/V1505_001__RESKC_341.sql
@./kc/bootstrap/V1505_002__RESKC-275.sql
@./kc/bootstrap/V1505_014__AlphabeticallySortSpecialReview.sql
@./kc/bootstrap/V1505_016__mult_choice_question_type.sql
@./kc/bootstrap/V1505_017__RESKC-348.sql
@./kc/bootstrap/V1505_018__RESKC-172.sql
commit;
