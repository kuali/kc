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

alter table AWARD_BUDGET_EXT add AWARD_ID decimal(22,0)
/

update AWARD_BUDGET_EXT budget set AWARD_ID = (select award.AWARD_ID from AWARD award left join BUDGET_DOCUMENT budgetdoc on award.document_number = budgetdoc.parent_document_key where budget.DOCUMENT_NUMBER = budgetdoc.DOCUMENT_NUMBER)
/

alter table AWARD_BUDGET_EXT modify AWARD_ID decimal(22,0) not null
/

DELIMITER ;
