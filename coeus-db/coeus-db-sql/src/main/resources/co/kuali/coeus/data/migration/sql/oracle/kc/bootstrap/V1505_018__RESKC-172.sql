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

alter table proposal MODIFY (COST_SHARING_INDICATOR varchar(2), IDC_RATE_INDICATOR varchar(2), SPECIAL_REVIEW_INDICATOR varchar(2));
update proposal set COST_SHARING_INDICATOR = TRIM(COST_SHARING_INDICATOR);
update proposal set IDC_RATE_INDICATOR = TRIM(IDC_RATE_INDICATOR);
update proposal set SPECIAL_REVIEW_INDICATOR = TRIM(SPECIAL_REVIEW_INDICATOR);
alter table proposal MODIFY (COST_SHARING_INDICATOR char(1), IDC_RATE_INDICATOR char(1), SPECIAL_REVIEW_INDICATOR char(1));
