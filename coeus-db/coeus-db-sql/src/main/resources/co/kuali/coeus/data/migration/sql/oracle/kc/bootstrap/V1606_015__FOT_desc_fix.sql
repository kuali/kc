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

UPDATE arg_value_lookup SET DESCRIPTION = '*Do NOT Select* BIOLOGICAL/BIOMEDICAL SCIENCES' WHERE ARG_VALUE_LOOKUP_ID = '-1';
UPDATE arg_value_lookup SET DESCRIPTION = '*Do NOT Select* HEALTH SCIENCES' WHERE ARG_VALUE_LOOKUP_ID = '-40';
UPDATE arg_value_lookup SET DESCRIPTION = '*Do NOT Select* ENGINEERING' WHERE ARG_VALUE_LOOKUP_ID = '-57';
UPDATE arg_value_lookup SET DESCRIPTION = '*Do NOT Select* COMPUTER SCIENCES' WHERE ARG_VALUE_LOOKUP_ID = '-60';
UPDATE arg_value_lookup SET DESCRIPTION = '*Do NOT Select* MATHEMATICS' WHERE ARG_VALUE_LOOKUP_ID = '-63';
UPDATE arg_value_lookup SET DESCRIPTION = '*Do NOT Select* CHEMISTRY' WHERE ARG_VALUE_LOOKUP_ID = '-65';
UPDATE arg_value_lookup SET DESCRIPTION = '*Do NOT Select* PSYCHOLOGY' WHERE ARG_VALUE_LOOKUP_ID = '-72';
UPDATE arg_value_lookup SET DESCRIPTION = '*Do NOT Select* SOCIAL SCIENCES' WHERE ARG_VALUE_LOOKUP_ID = '-83';
UPDATE arg_value_lookup SET DESCRIPTION = '*Do NOT Select* OTHER FIELDS' WHERE ARG_VALUE_LOOKUP_ID = '-87';
