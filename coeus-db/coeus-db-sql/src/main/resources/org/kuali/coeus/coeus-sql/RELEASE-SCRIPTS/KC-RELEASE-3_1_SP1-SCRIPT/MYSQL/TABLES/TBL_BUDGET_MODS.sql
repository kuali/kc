--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 The Kuali Foundation
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

ALTER TABLE BUDGET_DETAILS DROP column PROPOSAL_NUMBER;
ALTER TABLE BUDGET_DETAILS DROP column  VERSION_NUMBER;
ALTER TABLE BUDGET_DETAILS_CAL_AMTS DROP  column PROPOSAL_NUMBER;
ALTER TABLE BUDGET_DETAILS_CAL_AMTS DROP  column VERSION_NUMBER;
ALTER TABLE BUDGET_MODULAR DROP column PROPOSAL_NUMBER;
ALTER TABLE BUDGET_MODULAR DROP column VERSION_NUMBER;
ALTER TABLE BUDGET_MODULAR_IDC DROP column PROPOSAL_NUMBER;
ALTER TABLE BUDGET_MODULAR_IDC DROP column VERSION_NUMBER;
ALTER TABLE BUDGET_PER_DET_RATE_AND_BASE DROP column PROPOSAL_NUMBER;
ALTER TABLE BUDGET_PER_DET_RATE_AND_BASE DROP column VERSION_NUMBER;
ALTER TABLE BUDGET_PERIODS DROP column PROPOSAL_NUMBER;
ALTER TABLE BUDGET_PERIODS DROP column VERSION_NUMBER;
ALTER TABLE BUDGET_PERSONNEL_CAL_AMTS DROP column PROPOSAL_NUMBER;
ALTER TABLE BUDGET_PERSONNEL_CAL_AMTS DROP column VERSION_NUMBER;
ALTER TABLE BUDGET_PERSONNEL_DETAILS DROP column PROPOSAL_NUMBER;
ALTER TABLE BUDGET_PERSONNEL_DETAILS DROP column VERSION_NUMBER;
ALTER TABLE BUDGET_PERSONS DROP column PROPOSAL_NUMBER;
ALTER TABLE BUDGET_PERSONS DROP column VERSION_NUMBER;
ALTER TABLE BUDGET_RATE_AND_BASE DROP column PROPOSAL_NUMBER;
ALTER TABLE BUDGET_RATE_AND_BASE DROP column VERSION_NUMBER;
ALTER TABLE BUDGET_SUB_AWARD_ATT DROP column PROPOSAL_NUMBER;
ALTER TABLE BUDGET_SUB_AWARD_ATT DROP column VERSION_NUMBER;
ALTER TABLE BUDGET_SUB_AWARD_FILES DROP column PROPOSAL_NUMBER;
ALTER TABLE BUDGET_SUB_AWARD_FILES DROP column VERSION_NUMBER;
ALTER TABLE BUDGET_SUB_AWARDS DROP column PROPOSAL_NUMBER;
ALTER TABLE BUDGET_SUB_AWARDS DROP column VERSION_NUMBER;
ALTER TABLE EPS_PROP_RATES DROP column PROPOSAL_NUMBER;
ALTER TABLE EPS_PROP_RATES DROP column VERSION_NUMBER;
ALTER TABLE EPS_PROP_LA_RATES DROP column PROPOSAL_NUMBER;
ALTER TABLE EPS_PROP_LA_RATES DROP column VERSION_NUMBER;

alter table budget drop foreign key FK_BUDGET_KRA;
alter table budget DROP INDEX PK_BUDGET_KRA;
ALTER TABLE BUDGET DROP column PROPOSAL_NUMBER;
