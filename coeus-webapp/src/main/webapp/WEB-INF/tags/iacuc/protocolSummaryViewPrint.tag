<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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

<c:set var="protocolDocumentAttributes" value="${DataDictionary.IacucProtocolDocument.attributes}" />
<c:set var="protocolAttributes" value="${DataDictionary.IacucProtocol.attributes}" />
<c:set var="action" value="iacucProtocolActions" />
<c:set var="protocol" value="${KualiForm.document.protocolList[0]}" />
<c:set var="protocolPersonAttributes" value="${DataDictionary.IacucProtocolPerson.attributes}" />
<c:set var="protocolUnitAttributes" value="${DataDictionary.IacucProtocolUnit.attributes}" />
<c:set var="protocolResearchAreaAttributes" value="${DataDictionary.IacucProtocolResearchArea.attributes}" />
<c:set var="researchAreaAttributes" value="${DataDictionary.ResearchArea.attributes}" />

<kul:tab tabTitle="Summary & History" defaultOpen="false" tabErrorKey="">

	<div class="tab-container" align="left">
		<h3>
   			<span class="subhead-left">Summary & History</span>
   			<span class="subhead-right">
   				<kul:help parameterNamespace="KC-IACUC" parameterDetailType="Document" parameterName="protocolSummaryAndHistoryHelp" altText="Help"/>
			</span>
       </h3>
		<kra-iacuc:protocolSummaryPanel />
		<kra-iacuc:protocolSubmissionDetailsPanel />
		<kra-iacuc:protocolViewHistory />
		<kra-iacuc:protocolViewAmendmentHistory />  
    </div>	    
</kul:tab>
