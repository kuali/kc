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
VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), 3, '107','Proposal Person Certification Request Notification for Sponsors in the Hierarchy COI Disclosues with KP Req','Regarding your involvement in Development Proposal {PROPOSAL_TITLE}',
'Please review the following proposal by clicking on Proposal Number. Please answer the certification questions if you agree to participate in this project. Proposal Details as follows:<br/>Document Number: {DOCUMENT_NUMBER}<br/>Proposal Number: <a title="" target="_self" href="{APP_LINK_PREFIX}/kc-pd-krad/proposalDevelopment?methodToCall=viewUtility&amp;viewId=PropDev-CertificationView&amp;docId={DOCUMENT_NUMBER}&amp;userName={USER_NAME}">{PROPOSAL_NUMBER}</a> <br/>Proposal Title: {PROPOSAL_TITLE}<br/>Principal Investigator: {PRINCIPAL INVESTIGATOR}
<br/>Lead Unit: {LEAD_UNIT} - {LEAD_UNIT_NAME}<br/>Sponsor: {SPONSOR_CODE} - {SPONSOR_NAME}<br/>Dealine Date: {DEADLINE_DATE}','N', 'Y', 'admin', now(), 1, uuid());
