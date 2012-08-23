<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="masterDisclosure" value="${KualiForm.disclosureHelper.masterDisclosureBean}" />
<c:if test="${fn:length(masterDisclosure.manualAwardProjects) > 0 or fn:length(masterDisclosure.manualProposalProjects) > 0 or  fn:length(masterDisclosure.manualProtocolProjects) > 0 or  fn:length(masterDisclosure.manualTravelProjects) > 0}" >
<kul:tab defaultOpen="false" tabTitle="Manual Projects" auditCluster="financialEntityDiscAuditErrors" tabAuditKey="disclosureHelper.masterDisclosureBean.manualAwardProjects[*,disclosureHelper.masterDisclosureBean.manualProtocolProjects[*,disclosureHelper.masterDisclosureBean.manualProposalProjects[*,disclosureHelper.masterDisclosureBean.manualTravelProjects[*" useRiceAuditMode="true"
    tabErrorKey="disclosureHelper.newCoiDisclProject.*" >
    <div class="tab-container" align="center">
              

    <c:if test="${fn:length(masterDisclosure.manualAwardProjects) > 0}" >
        <kra-coi:disclosureProjects masterDisclosureProjects="${masterDisclosure.manualAwardProjects}" 
        projectDivNamePrefix="masterManualAwardFE" projectListName="manualAwardProjects" 
        boLocation="disclosureHelper.masterDisclosureBean.manualAwardProjects"
        parentTab="Manual Projects"/>
    </c:if>
    <c:if test="${fn:length(masterDisclosure.manualProposalProjects) > 0}" >
        <kra-coi:disclosureProjects masterDisclosureProjects="${masterDisclosure.manualProposalProjects}" 
        projectDivNamePrefix="masterManualProposalFE" projectListName="manualProposalProjects" 
        boLocation="disclosureHelper.masterDisclosureBean.manualProposalProjects"
        parentTab="Manual Projects"/>
    </c:if>
    <c:if test="${fn:length(masterDisclosure.manualProtocolProjects) > 0}" >
        <kra-coi:disclosureProjects masterDisclosureProjects="${masterDisclosure.manualProtocolProjects}" 
        projectDivNamePrefix="masterManualProtocolFE" projectListName="manualProtocolProjects" 
        boLocation="disclosureHelper.masterDisclosureBean.manualProtocolProjects"
        parentTab="Manual Projects"/>
    </c:if>
    <c:if test="${fn:length(masterDisclosure.manualTravelProjects) > 0}" >
        <kra-coi:disclosureProjects masterDisclosureProjects="${masterDisclosure.manualTravelProjects}" 
        projectDivNamePrefix="masterManualTravelFE" projectListName="manualTravelProjects" 
        boLocation="disclosureHelper.masterDisclosureBean.manualTravelProjects"
        parentTab="Manual Projects"/>
    </c:if>
       </div>
</kul:tab>
</c:if>    
<c:if test="${fn:length(masterDisclosure.awardProjects) > 0}" >
    <kul:tab defaultOpen="false" tabTitle="Awards" auditCluster="financialEntityDiscAuditErrors" tabAuditKey="disclosureHelper.masterDisclosureBean.awardProjects[*" useRiceAuditMode="true"
    	tabErrorKey="disclosureHelper.newCoiDisclProject.*" hidden="${hidden}"><div class="tab-container" align="center">
	    <kra-coi:disclosureProjects masterDisclosureProjects="${masterDisclosure.awardProjects}" 
	    projectDivNamePrefix="masterAwardFE" projectListName="awardProjects" 
	    boLocation="disclosureHelper.masterDisclosureBean.awardProjects"
	    parentTab="Awards"/>
	</div></kul:tab> 
</c:if>
<c:if test="${fn:length(masterDisclosure.proposalProjects) > 0}" > 
    <kul:tab defaultOpen="false" tabTitle="Proposals" auditCluster="financialEntityDiscAuditErrors" tabAuditKey="disclosureHelper.masterDisclosureBean.proposalProjects[*" useRiceAuditMode="true"
    	tabErrorKey="disclosureHelper.newCoiDisclProject.*" hidden="${hidden}"><div class="tab-container" align="center">
	    <kra-coi:disclosureProjects masterDisclosureProjects="${masterDisclosure.proposalProjects}" 
	    projectDivNamePrefix="masterProposalFE" projectListName="proposalProjects" 
	    boLocation="disclosureHelper.masterDisclosureBean.proposalProjects"
	    parentTab="Proposals"/>
	</div></kul:tab> 
</c:if>
<c:if test="${fn:length(masterDisclosure.protocolProjects) > 0}" >
    <kul:tab defaultOpen="false" tabTitle="Protocols" auditCluster="financialEntityDiscAuditErrors" tabAuditKey="disclosureHelper.masterDisclosureBean.protocolProjects[*" useRiceAuditMode="true"
    	tabErrorKey="disclosureHelper.newCoiDisclProject.*" hidden="${hidden}"><div class="tab-container" align="center">
	    <kra-coi:disclosureProjects masterDisclosureProjects="${masterDisclosure.protocolProjects}" 
	    projectDivNamePrefix="masterProtocolFE" projectListName="protocolProjects" 
	    boLocation="disclosureHelper.masterDisclosureBean.protocolProjects"
	    parentTab="Protocols"/>
	</div></kul:tab> 
</c:if>
