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

update notification_type set message='Please review the following proposal by clicking on Proposal Number. Please answer the certification questions if you agree to participate in this project. Proposal Details as follows:<br/>Document Number: {DOCUMENT_NUMBER}<br/>Proposal Number: <a title="" target="_self" href="{APP_LINK_PREFIX}/kc-pd-krad/proposalDevelopment?methodToCall=viewUtility&amp;viewId=PropDev-CertificationView&amp;docId={DOCUMENT_NUMBER}&amp;userName={USER_NAME}">{PROPOSAL_NUMBER}</a> <br/>Proposal Title: {PROPOSAL_TITLE}<br/>Principal Investigator: {PRINCIPAL INVESTIGATOR}
  <br/>Lead Unit: {LEAD_UNIT} - {LEAD_UNIT_NAME}<br/>Sponsor: {SPONSOR_CODE} - {SPONSOR_NAME}<br/>Dealine Date: {DEADLINE_DATE}' where module_code=3 and  action_code=104;

ALTER TABLE EPS_PROP_PERSON ADD CERTIFIED_BY VARCHAR2(60);

ALTER TABLE EPS_PROP_PERSON ADD LAST_NOTIFICATION DATE;

ALTER TABLE EPS_PROP_PERSON ADD CERTIFIED_TIME DATE;