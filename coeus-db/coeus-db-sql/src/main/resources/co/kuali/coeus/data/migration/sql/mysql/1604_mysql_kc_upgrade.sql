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


\. ./kc/bootstrap/V1604_004__RESKC-1048.sql
\. ./kc/bootstrap/V1604_005__RESKC-1165.sql
\. ./kc/bootstrap/V1604_006__RESKC-1224.sql
\. ./kc/bootstrap/V1604_008__fix_questionnaire_for_form.sql
\. ./kc/bootstrap/V1604_009__modify_iacuc_dates.sql
\. ./kc/bootstrap/V1604_010__set_all_updatetimestamps_to_datetime.sql
commit;
