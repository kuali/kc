<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="protocolAttributes" value="${DataDictionary.Protocol.attributes}" />
<c:set var="action" value="protocolActions" />
<c:set var="protocol" value="${KualiForm.document.protocolList[0]}" />
<c:set var="protocolPersonAttributes" value="${DataDictionary.ProtocolPerson.attributes}" />
<c:set var="protocolUnitAttributes" value="${DataDictionary.ProtocolUnit.attributes}" />
<c:set var="protocolResearchAreaAttributes" value="${DataDictionary.ProtocolResearchArea.attributes}" />
<c:set var="researchAreaAttributes" value="${DataDictionary.ResearchArea.attributes}" />

<kul:tab tabTitle="Summary & History" defaultOpen="false" tabErrorKey="">

	<div class="tab-container" align="left">
		<h3>
   			<span class="subhead-left">Summary & History</span>
   			<span class="subhead-right">
   				<kul:help parameterNamespace="KC-PROTOCOL" parameterDetailType="Document" parameterName="protocolSummaryAndHistoryHelp" altText="help"/>
			</span>
       </h3>
		<kra-irb:protocolSummaryPanel />
		<kra-irb:protocolSubmissionDetailsPanel />
		<kra-irb:protocolViewHistory />
		
    </div>	    
</kul:tab>
