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

INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL);
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
VALUES((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID),3,'501',
'Notification to other approvers when proposal has been approved in a stop where only one approval is needed.',
'Proposal {PROPOSAL_NUMBER} Approved by Another User',
'{PI_NAME}’s Proposal {PROPOSAL_NUMBER} has been approved by another user.</br>The referenced proposal has been approved by another user.</br>Proposal Summary:</br>PI: {PI_NAME}</br>Profit Center:  {LEAD_UNIT}  {LEAD_UNIT_NAME}</br>Proposal Number: {PROPOSAL_NUMBER}</br>Sponsor:	{SPONSOR_CODE} {SPONSOR_NAME}</br>Prime Sponsor:	{PRIME_SPONSOR_NAME}</br>Deadline Date: {DEADLINE_DATE}</br>Title: {PROPOSAL_TITLE}</br>Sponsor Announcement: {PROGRAM_ANNOUNCEMENT_NUMBER} {PROGRAM_ANNOUNCEMENT_TITLE}</br>You can view this proposal through KC at the following address: <a href="{APP_LINK_PREFIX}/kc-pd-krad/proposalDevelopment?methodToCall=docHandler&docId={DOCUMENT_NUMBER}&command=displayActionListView"> Open document. </a> </br></br>If you have questions, please contact {AGGREGATOR}</br>Thank you.',
'N','Y','admin',NOW(),1,UUID());
