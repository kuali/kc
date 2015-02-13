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
UPDATE NOTIFICATION_TYPE SET MESSAGE='The negotiation is complete.<br/><br/>Negotiation ID: <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{NEGOTIATION_ID}</a><br/>Negotiator: {NEGOTIATOR}<br/>Negotiation Status: {NEGOTIATION_STATUS}<br/>Anticipated Project Start Date: {ANTICIPATED_START_DATE}<br/>Negotiation Start Date: {START_DATE}<br/>Negotiation End Date: {END_DATE}<br/>Title: {TITLE}<br/>Primary Investigator: {PI}<br/>Lead Unit: {LEAD_UNIT_NAME} ({LEAD_UNIT_NUMBER})<br/>Sponsor: {SPONSOR_NAME} ({SPONSOR_CODE})<br/>Prime Sponsor: {PRIME_SPONSOR_NAME} ({PRIME_SPONSOR_CODE})<br/>' WHERE MODULE_CODE=5 AND ACTION_CODE='100'
/

DELIMITER ;
