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

alter table BUDGET_SUB_AWARD_PERIOD_DETAIL add SUBAWARD_NUMBER_OLD NUMBER(12,0)
/

alter table BUDGET_SUB_AWARD_PERIOD_DETAIL modify SUBAWARD_NUMBER null
/

update BUDGET_SUB_AWARD_PERIOD_DETAIL set SUBAWARD_NUMBER_OLD = SUBAWARD_NUMBER, SUBAWARD_NUMBER = null
/

alter table BUDGET_SUB_AWARD_PERIOD_DETAIL modify SUBAWARD_NUMBER NUMBER(3,0)
/

update BUDGET_SUB_AWARD_PERIOD_DETAIL set SUBAWARD_NUMBER = SUBAWARD_NUMBER_OLD
/

alter table BUDGET_SUB_AWARD_PERIOD_DETAIL drop column SUBAWARD_NUMBER_OLD
/
