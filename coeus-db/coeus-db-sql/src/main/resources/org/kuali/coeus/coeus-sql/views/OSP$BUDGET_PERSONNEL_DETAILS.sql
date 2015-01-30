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

create or replace view OSP$BUDGET_PERSONNEL_DETAILS as 
	select PROPOSAL_NUMBER,VERSION_NUMBER,BUDGET_PERIOD,LINE_ITEM_NUMBER,PERSON_NUMBER,PERSON_ID,
	JOB_CODE,START_DATE,END_DATE,PERIOD_TYPE,LINE_ITEM_DESCRIPTION,
	SEQUENCE_NUMBER,SALARY_REQUESTED,PERCENT_CHARGED,PERCENT_EFFORT,COST_SHARING_PERCENT,
	COST_SHARING_AMOUNT,UNDERRECOVERY_AMOUNT,ON_OFF_CAMPUS_FLAG,APPLY_IN_RATE_FLAG,BUDGET_JUSTIFICATION,
	UPDATE_TIMESTAMP,UPDATE_USER, BUDGET_PERIOD_NUMBER 
	from BUDGET_PERSONNEL_DETAILS;
