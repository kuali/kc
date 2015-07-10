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

create index EPS_PP_BA_FILE_DATA_ID_IDX on EPS_PROP_PERSON_BIO_ATTACHMENT (FILE_DATA_ID);
create index NARR_ATTACH_FILE_DATA_ID_IDX on NARRATIVE_ATTACHMENT (FILE_DATA_ID);
create index BUDGET_DETAILS_BUDGET_ID_IDX on BUDGET_DETAILS(BUDGET_ID);
create index BUDGET_DETAILS_SUBAWD_ID_IDX on BUDGET_DETAILS(SUBAWARD_NUMBER);
create index BUDGET_DETAILS_CAL_AMTS_ID_IDX on BUDGET_DETAILS_CAL_AMTS(BUDGET_DETAILS_ID);
create index BUDGET_PERS_DETAILS_ID_IDX on BUDGET_PERSONNEL_DETAILS(BUDGET_DETAILS_ID);
