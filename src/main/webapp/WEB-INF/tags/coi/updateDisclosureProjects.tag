<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="masterDisclosure" value="${KualiForm.disclosureHelper.masterDisclosureBean}" />
<c:if test="${fn:length(masterDisclosure.manualAwardProjects) > 0}" >
    <%-- <kra-coi:proposalProjects /> --%>
    <kra-coi:masterManualAward masterDisclosureProjects="${masterDisclosure.manualAwardProjects}"/>
</c:if>
<c:if test="${fn:length(masterDisclosure.manualProposalProjects) > 0}" >
    <%-- <kra-coi:proposalProjects /> --%>
    <kra-coi:masterManualProposal masterDisclosureProjects="${masterDisclosure.manualProposalProjects}"/>
</c:if>
<c:if test="${fn:length(masterDisclosure.manualProtocolProjects) > 0}" >
    <%-- <kra-coi:proposalProjects /> --%>
    <kra-coi:masterManualProtocol masterDisclosureProjects="${masterDisclosure.manualProtocolProjects}"/>
</c:if>
<c:if test="${fn:length(masterDisclosure.awardProjects) > 0}" >
    <%-- <kra-coi:proposalProjects /> --%>
    <kra-coi:masterAward masterDisclosureProjects="${masterDisclosure.awardProjects}"/>
</c:if>
<c:if test="${fn:length(masterDisclosure.proposalProjects) > 0}" >
    <%-- <kra-coi:proposalProjects /> --%>
    <kra-coi:masterProposal masterDisclosureProjects="${masterDisclosure.proposalProjects}"/>
</c:if>
<c:if test="${fn:length(masterDisclosure.protocolProjects) > 0}" >
    <%-- <kra-coi:proposalProjects /> --%>
    <kra-coi:masterProtocol masterDisclosureProjects="${masterDisclosure.protocolProjects}"/>
</c:if>
