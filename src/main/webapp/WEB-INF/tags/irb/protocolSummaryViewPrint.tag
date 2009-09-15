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
		
		<kra-irb:protocolSummary/>
		<kra-irb:protocolViewNotes/>
		<kra-irb:protocolViewHistory/>
    </div>	    
</kul:tab>
