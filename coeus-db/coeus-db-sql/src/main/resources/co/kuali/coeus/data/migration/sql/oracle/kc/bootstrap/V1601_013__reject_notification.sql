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

INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
       VALUES(SEQ_NOTIFICATION_TYPE_ID.NEXTVAL,3,'500','Notification to approvers when proposal is rejected.',
       '{PI_NAME}’s Proposal {PROPOSAL_NUMBER} has been returned to the Aggregator',
       '{PI_NAME}’s Proposal {PROPOSAL_NUMBER} has been returned to the Aggregator</br>The referenced proposal has been returned to the Aggregator for revisions and has been removed from your action list.</br>THIS NOTIFICATION HAS BEEN SENT TO ALL APPROVERS AT THIS STOP.</br>Proposal Summary:</br>Pl: {PI_NAME}</br>Profit Center:  {LEAD_UNIT}  {LEAD_UNIT_NAME}</br>Proposal Number: {PROPOSAL_NUMBER}</br>Sponsor:	{SPONSOR_CODE} {SPONSOR_NAME}</br>Prime Sponsor:	{PRIME_SPONSOR_NAME}</br>Deadline Date: {DEADLINE_DATE}</br>Title: {PROPOSAL_TITLE}</br>Sponsor Announcement: {PROGRAM_ANNOUNCEMENT_NUMBER} {PROGRAM_ANNOUNCEMENT_TITLE}</br>You can view this proposal through KC at the following address: <a href="{APP_LINK_PREFIX}/kc-pd-krad/proposalDevelopment?methodToCall=docHandler&docId={DOCUMENT_NUMBER}&command=displayActionListView"> Open document. </a> </br></br>If you have questions, please contact {AGGREGATOR}</br>Thank you.',
       'N','Y','admin',SYSDATE,1,SYS_GUID());
