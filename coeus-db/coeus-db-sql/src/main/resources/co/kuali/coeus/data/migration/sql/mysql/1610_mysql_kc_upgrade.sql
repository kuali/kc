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


\. ./kc/bootstrap/V1610_002__fdp_form_update.sql
\. ./kc/bootstrap/V1610_003__RESKC-1480.sql
\. ./kc/bootstrap/V1610_004__RESKC-1746.sql
\. ./kc/bootstrap/V1610_006__unit_sync_parameter.sql
\. ./kc/bootstrap/V1610_007__project_status_feature_flag.sql
commit;
