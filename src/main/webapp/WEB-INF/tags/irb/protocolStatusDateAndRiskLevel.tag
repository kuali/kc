<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />

<kul:tab tabTitle="Status & Dates" defaultOpen="true" tabErrorKey="" >
	<div class="tab-container" align="center">
        <kra-irb:protocolStatusDate />        
        <kra-irb:protocolRiskLevel />        
    </div>
</kul:tab>
