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

update notification_type set description = 'Return Review' where description = 'Reject Review';
update notification_type set description = 'IACUC Return Review' where description = 'IACUC Reject Review';
update notification_type set description = 'Notification to approvers when proposal is returned.' where description = 'Notification to approvers when proposal is rejected.';

update award_budget_status set description = 'Returned' where description = 'Rejected';

update EPS_PROPOSAL_STATUS set description = 'Returned' where description = 'Rejected';
update EPS_PROPOSAL_STATUS set description = 'Post-Submission Return' where description = 'Post-Submission Rejection';

update PROPOSAL_STATUS set description = 'Returned' where description = 'Rejected';

update IACUC_SUBMISSION_STATUS set description = 'Returned Submission' where description = 'Rejected Submission';
update IACUC_SUBMISSION_STATUS set description = 'Returned In Routing' where description = 'Rejected In Routing';

update SUBMISSION_STATUS set description = 'Returned In Routing' where description = 'Rejected In Routing';

update PROTOCOL_ACTION_TYPE set description = 'Returned In Routing' where description = 'Rejected In Routing';

update IACUC_PROTOCOL_ACTION_TYPE set description = 'Returned In Routing' where description = 'Rejected In Routing';


