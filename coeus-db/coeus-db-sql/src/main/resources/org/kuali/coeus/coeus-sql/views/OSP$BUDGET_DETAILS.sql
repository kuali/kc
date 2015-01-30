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

create or replace view OSP$BUDGET_DETAILS as 
	select PROPOSAL_NUMBER,VERSION_NUMBER,BUDGET_PERIOD,LINE_ITEM_NUMBER,BUDGET_CATEGORY_CODE,COST_ELEMENT,
	LINE_ITEM_DESCRIPTION,BASED_ON_LINE_ITEM,LINE_ITEM_SEQUENCE,START_DATE,END_DATE,
	LINE_ITEM_COST,COST_SHARING_AMOUNT,UNDERRECOVERY_AMOUNT,ON_OFF_CAMPUS_FLAG,APPLY_IN_RATE_FLAG,
	BUDGET_JUSTIFICATION,UPDATE_TIMESTAMP,UPDATE_USER,QUANTITY, BUDGET_PERIOD_NUMBER 
	from BUDGET_DETAILS;
