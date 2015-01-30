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
ALTER TABLE COI_DISCL_PROJECTS CHANGE COI_PROJECT_TYPE SHRT_TXT_FLD_2 VARCHAR(20)
/
ALTER TABLE COI_DISCL_PROJECTS CHANGE COI_PROJECT_SPONSOR LNG_TXT_FLD_2 VARCHAR(200)
/
ALTER TABLE COI_DISCL_PROJECTS CHANGE COI_PROJECT_ROLE SHRT_TXT_FLD_3 VARCHAR(20)
/
ALTER TABLE COI_DISCL_PROJECTS CHANGE COI_PROJECT_START_DATE DATE_FLD_1 DATE
/
ALTER TABLE COI_DISCL_PROJECTS CHANGE COI_PROJECT_END_DATE DATE_FLD_2 DATE
/
ALTER TABLE COI_DISCL_PROJECTS CHANGE COI_PROJECT_FUNDING_AMOUNT NMBR_FLD_1 DECIMAL(12,2)
/
ALTER TABLE COI_DISCL_PROJECTS ADD LNG_TXT_FLD_3 VARCHAR(200)
/
ALTER TABLE COI_DISCL_PROJECTS ADD NMBR_FLD_2 DECIMAL(12,2)
/
ALTER TABLE COI_DISCL_PROJECTS ADD SLCT_BOX_1 VARCHAR(20)
/
ALTER TABLE COI_DISCL_PROJECTS ADD SHRT_TXT_FLD_1 VARCHAR(20)
/
ALTER TABLE COI_DISCL_PROJECTS ADD LNG_TXT_FLD_1 VARCHAR(200)
/
ALTER TABLE COI_DISCL_PROJECTS ADD MODULE_ITEM_KEY VARCHAR(20) NOT NULL
/
DELIMITER ;
