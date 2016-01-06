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

update DASH_BOARD_MENU_ITEMS set MENU_ACTION = '/kc-krad/landingPage?methodToCall=start&href=<<APPLICATION_URL>>%2Fkr%2Flookup.do%3FmethodToCall%3Dstart%26businessObjectClassName%3Dorg.kuali.kra.institutionalproposal.proposallog.ProposalLog%26docFormKey%3D88888888%26includeCustomActionUrls%3Dtrue%26returnLocation%3D<<APPLICATION_URL>>%252Fkc-krad%252FlandingPage%253FviewId%253DKc-LandingPage-RedirectView%26forInstitutionalProposal%26hideReturnLink%3Dtrue%26showMaintenanceLinks%3Dtrue%26refreshCaller%3Dcancel&viewId=Kc-Header-IframeView' where MENU_ITEM = 'Create Institute Proposal';