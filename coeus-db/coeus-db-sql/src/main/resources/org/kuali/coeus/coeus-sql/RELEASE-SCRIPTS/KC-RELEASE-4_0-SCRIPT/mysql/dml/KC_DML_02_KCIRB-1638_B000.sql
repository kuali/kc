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
UPDATE NOTIFICATION_TYPE SET MESSAGE='Funding source {FUNDING_TYPE} {ACTION} protocol <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a>' where notification_type_id=10007
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='Review comments for protocol <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a> deleted by {USER_FULLNAME}. <br/>The reason is: {REASON}' where notification_type_id=10062
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='Review comments for protocol <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a> returned to reviewer.<br/>The reason is : {REASON}' where notification_type_id=10003
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='The IRB protocol <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a> (Principal Investigator {PI_NAME}) has been deferred.<br />The action was executed by {USER_FULLNAME}.  Additional information and further actions can be accessed through the Kuali Coeus system.'  where notification_type_id=10043
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='The IRB protocol number <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a> has been assigned to agenda.' where notification_type_id=10036
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='The IRB protocol number <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a> has requested specific minor revisions.'  where notification_type_id=10049
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='The IRB protocol number <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a> has requested substantive revisions.'  where notification_type_id=10052
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='The IRB protocol number <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a>, Principal Investigator {PI_NAME} has had the action "Abandon" performed on it.<br />The action was executed by {USER_FULLNAME}.  Additional information and further actions can be accessed through the Kuali Coeus system.' where notification_type_id=10030
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='The IRB protocol number <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a>, Principal Investigator {PI_NAME} has had the action "IRB Acknowledgement" performed on it.<br />The action was executed by {USER_FULLNAME}.  Additional information and further actions can be accessed through the Kuali Coeus system.' where notification_type_id=10033
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='The IRB protocol number <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a>, Principal Investigator {PI_NAME} has had the action "Notify Irb" performed on it.<br />The action was executed by {USER_FULLNAME}.<br />The Submission Type Qualifier is "{LAST_SUBMISSION_TYPE_QUAL_NAME}".<br />The Submission Review Type is "{PROTOCOL_REVIEW_TYPE_DESC}".<br />The Committee name is "{COMMITTEE_NAME}".<br />The comment on the action is "{ACTION_COMMENTS}".<br />Additional information and further actions can be accessed through the Kuali Coeus system.' where notification_type_id=10012
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='The IRB protocol number <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a>, Principal Investigator {PI_NAME} has had the action "Request For Data Analysis" performed on it.The action was executed by {USER_FULLNAME}.  Additional information and further actions can be accessed through the Kuali Coeus system.' where notification_type_id=10027
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='The IRB protocol number <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a>, Principal Investigator {PI_NAME} has had the action "Request To Close Enrollment" performed on it.<br />The action was executed by {USER_FULLNAME}.  Additional information and further actions can be accessed through the Kuali Coeus system.' where notification_type_id=10021
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='The IRB protocol number <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a>, Principal Investigator {PI_NAME} has had the action "Request To Close" performed on it.<br />The action was executed by {USER_FULLNAME}.  Additional information and further actions can be accessed through the Kuali Coeus system.' where notification_type_id=10015
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='The IRB protocol number <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a>, Principal Investigator {PI_NAME} has had the action "Request for suspension" performed on it.<br />The action was executed by {USER_FULLNAME}.  Additional information and further actions can be accessed through the Kuali Coeus system.' where notification_type_id=10018
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='The IRB protocol number <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a>, Principal Investigator {PI_NAME} has had the action "Request to re-open enrollment" performed on it.<br />The action was executed by {USER_FULLNAME}.  Additional information and further actions can be accessed through the Kuali Coeus system.' where notification_type_id=10024
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='The IRB protocol number <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a>, Principal Investigator {PI_NAME} has had the action "Withdrawn" performed on it.<br />The action was executed by {USER_FULLNAME}.  Additional information and further actions can be accessed through the Kuali Coeus system.' where notification_type_id=10009
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='The IRB protocol number <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a>, Principal Investigator {PI_NAME} has had the action "generate batch correspondence" performed on it.<br />The action was executed by {USER_FULLNAME}.  Additional information and further actions can be accessed through the Kuali Coeus system.' where notification_type_id=10000
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='You can view this protocol <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a> through Kuali Coeus.' where notification_type_id=10040
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='{USER_FULLNAME} has approved review comments for protocol <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a>' where notification_type_id=10046
/
DELIMITER ;
