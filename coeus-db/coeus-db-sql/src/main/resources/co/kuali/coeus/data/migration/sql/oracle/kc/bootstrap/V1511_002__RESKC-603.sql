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
VALUES (SEQ_NOTIFICATION_TYPE_ID.NEXTVAL, 3, '109','Notification based on answers to the COI screening questions','Regarding your involvement in Development Proposal {PROPOSAL_TITLE}',
'Based on your answers to the COI screening questions in the proposal certification questions, you must now complete a full disclosure for this proposal before it can be routed.</br>
Please navigate to the Researcher > Conflict of Interest > My Disclosures menu.</br>Proposal Details as follows:<br/>Document Number:{DOCUMENT_NUMBER}<br/>
Proposal Number: <a title="" target="_self" href="{APP_LINK_PREFIX}/kc-pd-krad/proposalDevelopment?methodToCall=viewUtility&viewId=PropDev-CertificationView&docId={DOCUMENT_NUMBER}&userName={USER_NAME}">{PROPOSAL_NUMBER}
</a> <br/>Proposal Title:{PROPOSAL_TITLE}<br/>Principal Investigator:{PRINCIPAL INVESTIGATOR}<br/>Lead Unit:{LEAD_UNIT}-{LEAD_UNIT_NAME}<br/>Sponsor:{SPONSOR_CODE}-{SPONSOR_NAME}
<br/>Dealine Date:{DEADLINE_DATE}','N', 'Y', 'admin', SYSDATE, 1, SYS_GUID());
