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
		
    </div>	    
</kul:tab>
