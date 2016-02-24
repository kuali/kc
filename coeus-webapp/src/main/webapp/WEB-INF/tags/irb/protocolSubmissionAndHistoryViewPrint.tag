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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<kul:tabTop tabTitle="Submission & History" defaultOpen="false" tabErrorKey="">

	<div class="tab-container" align="left">
		<h3>
   			<span class="subhead-left">Submission &amp; History</span>
   			<span class="subhead-right">
   				<kul:help parameterNamespace="KC-PROTOCOL" parameterDetailType="Document" parameterName="protocolSummaryAndHistoryHelp" altText="Help"/>
			</span>
       </h3>
		<kra-irb:protocolSubmissionDetailsPanel />
		<kra-irb:protocolViewHistory />
 		<kra-irb:protocolViewAmendmentHistory />
    </div>	    
</kul:tabTop>
