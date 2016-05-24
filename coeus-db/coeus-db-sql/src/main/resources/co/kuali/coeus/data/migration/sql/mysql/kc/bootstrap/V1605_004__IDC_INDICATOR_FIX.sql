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
UPDATE proposal SET idc_rate_indicator = '0' WHERE idc_rate_indicator IS NULL;
ALTER TABLE proposal MODIFY idc_rate_indicator CHAR(1) NOT NULL;
UPDATE proposal SET cost_sharing_indicator = '0' WHERE cost_sharing_indicator IS NULL;
ALTER TABLE proposal MODIFY cost_sharing_indicator CHAR(1) NOT NULL;
UPDATE proposal SET special_review_indicator = '0' WHERE special_review_indicator IS NULL;
ALTER TABLE proposal MODIFY special_review_indicator CHAR(1) NOT NULL;
UPDATE proposal SET science_code_indicator = '0' WHERE science_code_indicator IS NULL;
ALTER TABLE proposal MODIFY science_code_indicator CHAR(1) NOT NULL;