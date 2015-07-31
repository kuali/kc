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


\. ./kc/bootstrap/V1507_002__RESKC-589.sql
\. ./kc/bootstrap/V1507_003__RESKC-595.sql
\. ./kc/bootstrap/V1507_005__InstitutionalProposalAttachments.sql
\. ./kc/bootstrap/V1507_008__SubAwardAttachments.sql
\. ./kc/bootstrap/V1507_009__AwardAttachments.sql
\. ./kc/bootstrap/V1507_011__add_indexes.sql
\. ./kc/bootstrap/V1507_013__protocol_submission_views.sql
\. ./kc/bootstrap/V1507_015__RESKC-595.sql
\. ./kc/bootstrap/V1507_016__RESKC-561.sql
\. ./kc/bootstrap/V1507_020__fix_repackaging.sql
commit;
