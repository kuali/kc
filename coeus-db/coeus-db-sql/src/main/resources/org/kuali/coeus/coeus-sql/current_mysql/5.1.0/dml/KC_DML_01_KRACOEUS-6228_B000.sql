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

DELIMITER /

UPDATE NOTIFICATION_TYPE SET DESCRIPTION='Notify IRB Event' WHERE MODULE_CODE=7 AND ACTION_CODE='116'
/
UPDATE NOTIFICATION_TYPE SET SUBJECT='Protocol {PROTOCOL_NUMBER} Notify IRB' WHERE MODULE_CODE=7 AND ACTION_CODE='116'
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='The IRB protocol number <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a>, Principal Investigator {PI_NAME} has had the action "Notify Irb" performed on it.<br />The action was executed by {USER_FULLNAME}.<br />The Submission Type Qualifier is "{LAST_SUBMISSION_TYPE_QUAL_NAME}".<br />The Submission Review Type is "{PROTOCOL_REVIEW_TYPE_DESC}".<br />The Committee name is "{COMMITTEE_NAME}".<br />The comment on the action is "{ACTION_COMMENTS}".<br />Additional information and further actions can be accessed through the Kuali Coeus system.' WHERE MODULE_CODE=7 AND ACTION_CODE='116'
/

DELIMITER ;
