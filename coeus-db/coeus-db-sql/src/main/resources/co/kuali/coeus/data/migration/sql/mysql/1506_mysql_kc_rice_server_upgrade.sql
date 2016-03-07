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


\. ./rice/bootstrap/V1506_001__ParametersForCommentTypes.sql
\. ./rice/bootstrap/V1506_002__DefaultEmailRecipient.sql
\. ./rice/bootstrap/V1506_003__add_monitoring_auth.sql
\. ./rice/bootstrap/V1506_004__CertificationNotification.sql
\. ./rice/bootstrap/V1506_006__KR_Adding_reject_permission.sql
\. ./rice/bootstrap/V1506_008__RESKC-533.sql
\. ./rice/bootstrap/V1506_010__SinglePointEntry.sql
\. ./rice/bootstrap/V1506_017__remove_unused_parms.sql
commit;
