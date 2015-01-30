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

alter table EPS_PROPOSAL_BUDGET_EXT drop column VER_NBR
/
alter table EPS_PROPOSAL_BUDGET_EXT drop column OBJ_ID
/
alter table EPS_PROPOSAL_BUDGET_EXT drop column UPDATE_TIMESTAMP
/
alter table EPS_PROPOSAL_BUDGET_EXT drop column UPDATE_USER
/

alter table EPS_PROPOSAL_BUDGET_EXT add PROPOSAL_NUMBER varchar2(12)
/

update EPS_PROPOSAL_BUDGET_EXT budget set PROPOSAL_NUMBER = 
	(select prop.PROPOSAL_NUMBER from EPS_PROPOSAL prop 
		left join BUDGET_DOCUMENT budgetDoc on prop.DOCUMENT_NUMBER = budgetDoc.PARENT_DOCUMENT_KEY 
	where budgetDoc.DOCUMENT_NUMBER = budget.DOCUMENT_NUMBER)
/

alter table EPS_PROPOSAL_BUDGET_EXT drop column DOCUMENT_NUMBER
/

alter table EPS_PROPOSAL_BUDGET_EXT modify PROPOSAL_NUMBER varchar2(12) not null
/

alter table EPS_PROPOSAL_BUDGET_EXT add STATUS_CODE char(1)
/

update EPS_PROPOSAL_BUDGET_EXT prop_budget set STATUS_CODE = (select BUDGET_STATUS from EPS_PROPOSAL prop where prop.PROPOSAL_NUMBER = prop_budget.PROPOSAL_NUMBER) where (select FINAL_VERSION_FLAG from BUDGET where prop_budget.BUDGET_ID = BUDGET.BUDGET_ID) = 'Y'
/

update EPS_PROPOSAL_BUDGET_EXT prop_budget set STATUS_CODE = '2' where STATUS_CODE is null
/

alter table EPS_PROPOSAL_BUDGET_EXT modify STATUS_CODE char(1) not null
/
