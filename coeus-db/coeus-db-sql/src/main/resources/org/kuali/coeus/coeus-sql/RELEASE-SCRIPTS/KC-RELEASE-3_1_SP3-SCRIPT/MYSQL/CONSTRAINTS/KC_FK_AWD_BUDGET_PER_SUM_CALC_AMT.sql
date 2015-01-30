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

ALTER TABLE AWD_BGT_PER_SUM_CALC_AMT ADD CONSTRAINT FK_AWARD_BGT_SUMM_CLAC_AMT 
                            FOREIGN KEY (BUDGET_PERIOD_ID) REFERENCES BUDGET_PERIODS (BUDGET_PERIOD_NUMBER);
ALTER TABLE AWD_BGT_PER_SUM_CALC_AMT ADD CONSTRAINT FK2_AWARD_BGT_SUMM_CLAC_AMT 
                            FOREIGN KEY (COST_ELEMENT) REFERENCES COST_ELEMENT (COST_ELEMENT);
