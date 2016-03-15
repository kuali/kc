--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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

alter table AWARD_BUDGET_DETAILS_EXT add obligated_amount_new number(12,2);
update AWARD_BUDGET_DETAILS_EXT set obligated_amount_new=obligated_amount where BUDGET_DETAILS_ID in
    (select BUDGET_DETAILS_ID from AWARD_BUDGET_DETAILS_EXT);
update AWARD_BUDGET_DETAILS_EXT set obligated_amount=null;
alter table AWARD_BUDGET_DETAILS_EXT drop column obligated_amount;
alter table AWARD_BUDGET_DETAILS_EXT rename column obligated_amount_new to obligated_amount;

alter table AWD_BGT_DET_CAL_AMTS_EXT add obligated_amount_new number(12,2);
update AWD_BGT_DET_CAL_AMTS_EXT set obligated_amount_new=obligated_amount where BUDGET_DETAILS_CAL_AMTS_ID in
    (select BUDGET_DETAILS_CAL_AMTS_ID from AWD_BGT_DET_CAL_AMTS_EXT);
update AWD_BGT_DET_CAL_AMTS_EXT set obligated_amount=null;
alter table AWD_BGT_DET_CAL_AMTS_EXT drop column obligated_amount;
alter table AWD_BGT_DET_CAL_AMTS_EXT rename column obligated_amount_new to obligated_amount;

alter table AWD_BUDGET_PER_DET_EXT add obligated_amount_new number(12,2);
update AWD_BUDGET_PER_DET_EXT set obligated_amount_new=obligated_amount where BUDGET_PERSONNEL_DETAILS_ID in
    (select BUDGET_PERSONNEL_DETAILS_ID from AWD_BUDGET_PER_DET_EXT);
update AWD_BUDGET_PER_DET_EXT set obligated_amount=null;
alter table AWD_BUDGET_PER_DET_EXT drop column obligated_amount;
alter table AWD_BUDGET_PER_DET_EXT rename column obligated_amount_new to obligated_amount;

alter table AWD_BUDGET_PER_CAL_AMTS_EXT add obligated_amount_new number(12,2);
update AWD_BUDGET_PER_CAL_AMTS_EXT set obligated_amount_new=obligated_amount where BUDGET_PERSONNEL_CAL_AMTS_ID in
    (select BUDGET_PERSONNEL_CAL_AMTS_ID from AWD_BUDGET_PER_CAL_AMTS_EXT);
update AWD_BUDGET_PER_CAL_AMTS_EXT set obligated_amount=null;
alter table AWD_BUDGET_PER_CAL_AMTS_EXT drop column obligated_amount;
alter table AWD_BUDGET_PER_CAL_AMTS_EXT rename column obligated_amount_new to obligated_amount;

alter table award_budget_period_ext add obligated_amount_new number(12,2);
alter table award_budget_period_ext add total_fringe_amount_new number(12,2);
update award_budget_period_ext set obligated_amount_new=obligated_amount,
                                    total_fringe_amount_new = total_fringe_amount
          where BUDGET_PERIOD_NUMBER in (select BUDGET_PERIOD_NUMBER from award_budget_period_ext);
update award_budget_period_ext set obligated_amount=null,total_fringe_amount=null;
alter table award_budget_period_ext drop column obligated_amount;
alter table award_budget_period_ext drop column total_fringe_amount;
alter table award_budget_period_ext rename column obligated_amount_new to obligated_amount;
alter table award_budget_period_ext rename column total_fringe_amount_new to total_fringe_amount;

