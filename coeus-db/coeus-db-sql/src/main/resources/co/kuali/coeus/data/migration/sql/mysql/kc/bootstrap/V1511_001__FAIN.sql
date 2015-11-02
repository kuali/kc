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
ALTER TABLE AWARD ADD COLUMN (
  FAIN_ID VARCHAR(32),
  FED_AWARD_YEAR DECIMAL(4,0),
  FED_AWARD_DATE DATE
);

ALTER TABLE SUBAWARD ADD COLUMN (
  FED_AWARD_PROJ_DESC VARCHAR(200),
  F_AND_A_RATE DECIMAL (4,2),
  DE_MINIMUS CHAR(1)
);
