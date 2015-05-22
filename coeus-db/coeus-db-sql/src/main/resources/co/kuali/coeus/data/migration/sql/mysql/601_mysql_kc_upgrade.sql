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


\. ./kc/bootstrap/V601_001__KC_TBL_EPS_PROP_PERSON.sql
\. ./kc/bootstrap/V601_002__KC_DML_KRACOEUS-6639.sql
\. ./kc/bootstrap/V601_003__Resolve_Repackaging_Db_Issues.sql
\. ./kc/bootstrap/V601_007__KRACOEUS-8814.sql
\. ./kc/bootstrap/V601_013__KRACOEUS-8866.sql
\. ./kc/bootstrap/V601_017__KRACOEUS-8864.sql
\. ./kc/bootstrap/V601_019__KRACOEUS-8896.sql
\. ./kc/bootstrap/V601_024__KRACOEUS-8868.sql
\. ./kc/bootstrap/V601_025__KRACOUES-8839.sql
\. ./kc/bootstrap/V601_026__KRACOUES-8839.sql
\. ./kc/bootstrap/V601_027__expand_blob_columns.sql
commit;
