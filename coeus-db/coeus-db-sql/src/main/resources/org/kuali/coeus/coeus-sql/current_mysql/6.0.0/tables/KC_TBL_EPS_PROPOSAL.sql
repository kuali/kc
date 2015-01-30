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

DELIMITER /
alter table EPS_PROPOSAL add FINAL_BUDGET_ID DECIMAL(12)
/

update EPS_PROPOSAL proposal set proposal.FINAL_BUDGET_ID = 
(select max(budget.BUDGET_ID) from BUDGET budget 
	left join BUDGET_DOCUMENT budgetDoc on budget.DOCUMENT_NUMBER = budgetDoc.DOCUMENT_NUMBER 
where budget.FINAL_VERSION_FLAG = 'Y' and proposal.DOCUMENT_NUMBER = budgetDoc.PARENT_DOCUMENT_KEY)
/

alter table EPS_PROPOSAL add column HIERARCHY_LAST_BUDGET_ID DECIMAL(12)
/

update EPS_PROPOSAL proposal set proposal.HIERARCHY_LAST_BUDGET_ID =
(select max(budget.BUDGET_ID) from BUDGET budget 
	left join BUDGET_DOCUMENT budgetDoc on budget.DOCUMENT_NUMBER = budgetDoc.DOCUMENT_NUMBER 
where proposal.HIERARCHY_LAST_BUDGET_DOC_NBR = budgetDoc.DOCUMENT_NUMBER)
/

alter table EPS_PROPOSAL drop column HIERARCHY_LAST_BUDGET_DOC_NBR
/
 
DELIMITER ;
