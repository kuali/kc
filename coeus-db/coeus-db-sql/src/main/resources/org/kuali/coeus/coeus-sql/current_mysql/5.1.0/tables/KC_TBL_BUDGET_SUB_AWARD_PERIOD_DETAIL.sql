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
create table BUDGET_SUB_AWARD_PERIOD_DETAIL (
	SUBAWARD_PERIOD_DETAIL_ID	DECIMAL(12,0)	NOT NULL,
	SUBAWARD_NUMBER				DECIMAL(12,0)	NOT NULL,
	BUDGET_ID					DECIMAL(12,0)	NOT NULL,
	BUDGET_PERIOD				DECIMAL(3,0)		NOT NULL,
	DIRECT_COST					DECIMAL(12,2),
	INDIRECT_COST				DECIMAL(12,2),
	COST_SHARING_AMOUNT			DECIMAL(12,2),
	TOTAL_COST					DECIMAL(12,2),
	UPDATE_TIMESTAMP 			DATE 			NOT NULL, 
    UPDATE_USER 				VARCHAR(60) 	NOT NULL, 
    VER_NBR 					DECIMAL(8,0) 	DEFAULT 1 NOT NULL, 
    OBJ_ID 						VARCHAR(36) 	NOT NULL
)
/

alter table BUDGET_SUB_AWARD_PERIOD_DETAIL 
add constraint PK_BUDGET_SUB_AWARD_PER_DETAIL 
primary key (SUBAWARD_PERIOD_DETAIL_ID) 
/

DELIMITER ;
