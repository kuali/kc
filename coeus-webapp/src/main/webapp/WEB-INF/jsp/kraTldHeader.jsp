<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>

<%@ include file="/rice-portal/jsp/sys/riceTldHeader.jsp" %>

<%-- 

This is where application specific (kra) tag includes would go.

--%>
<%@ taglib tagdir="/WEB-INF/tags/budget" prefix="kra-b"%>
<%@ taglib tagdir="/WEB-INF/tags/award" prefix="kra-a"%>
<%@ taglib tagdir="/WEB-INF/tags/timeandmoney" prefix="kra-timeandmoney"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="kra"%>
<%@ taglib uri="/WEB-INF/tlds/kc-func.tld" prefix="krafn"%>
<%@ taglib tagdir="/WEB-INF/tags/irb" prefix="kra-irb"%>
<%@ taglib tagdir="/WEB-INF/tags/committee" prefix="kra-committee"%>
<%@ taglib tagdir="/WEB-INF/tags/permissions" prefix="kra-permissions"%>
<%@ taglib tagdir="/WEB-INF/tags/questionnaire" prefix="kra-questionnaire"%>
<%@ taglib tagdir="/WEB-INF/tags/customdata" prefix="kra-customdata" %>
<%@ taglib tagdir="/WEB-INF/tags/irb/action" prefix="kra-irb-action" %>
<%@ taglib tagdir="/WEB-INF/tags/irb/onlinereview" prefix="kra-irb-olr"%>
<%@ taglib tagdir="/WEB-INF/tags/institutionalproposal" prefix="kra-ip"%>
<%@ taglib tagdir="/WEB-INF/tags/proposalhierarchy" prefix="kra-ph"%>
<%@ taglib tagdir="/WEB-INF/tags/specialreview" prefix="kra-specialreview" %>
<%@ taglib tagdir="/WEB-INF/tags/meeting" prefix="kra-meeting"%>
<%@ taglib tagdir="/WEB-INF/tags/medusa" prefix="kra-m"%>
<%@ taglib tagdir="/WEB-INF/tags/coi" prefix="kra-coi"%>
<%@ taglib tagdir="/WEB-INF/tags/negotiation" prefix="kra-negotiation"%>
<%@ taglib tagdir="/WEB-INF/tags/subaward" prefix="kra-sub"%>
<%@ taglib tagdir="/WEB-INF/tags/notification" prefix="kra-notification"%>
<%@ taglib tagdir="/WEB-INF/tags/personmasschange" prefix ="kra-personmasschange"%>
<%@ taglib tagdir="/WEB-INF/tags/iacuc" prefix="kra-iacuc"%>
<%@ taglib tagdir="/WEB-INF/tags/iacuc/action" prefix="kra-iacuc-action" %>
<%@ taglib tagdir="/WEB-INF/tags/iacuc/onlinereview" prefix="kra-iacuc-olr"%>
<%@ taglib tagdir="/WEB-INF/tags/protocol" prefix="kra-protocol"%>
<%@ taglib tagdir="/WEB-INF/tags/summary" prefix="kra-summary"%>
<%@ taglib tagdir="/WEB-INF/tags/protocol/action" prefix="kra-protocol-action"%>

