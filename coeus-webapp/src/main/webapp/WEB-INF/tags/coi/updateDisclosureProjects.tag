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
<c:set var="masterDisclosure" value="${KualiForm.disclosureHelper.masterDisclosureBean}" />
<c:if test="${fn:length(masterDisclosure.manualAwardProjects) > 0 
	or fn:length(masterDisclosure.manualProposalProjects) > 0 
	or fn:length(masterDisclosure.manualProtocolProjects) > 0 
	or fn:length(masterDisclosure.manualIacucProtocolProjects) > 0 
	or fn:length(masterDisclosure.manualTravelProjects) > 0
	or fn:length(masterDisclosure.otherManualProjects) > 0}" >
<kul:tab defaultOpen="false" tabTitle="Manual Projects" auditCluster="financialEntityDiscAuditErrors,coiQuestionnaireKey" tabAuditKey="disclosureHelper.masterDisclosureBean.manualAwardProjects[*,disclosureHelper.masterDisclosureBean.manualProtocolProjects[*,disclosureHelper.masterDisclosureBean.manualProposalProjects[*,disclosureHelper.masterDisclosureBean.manualTravelProjects[*" useRiceAuditMode="true"
    tabErrorKey="disclosureHelper.newCoiDisclProject.*,document.coiDisclosureList[0].coiDisclProjects[0].*" >
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
    <c:if test="${fn:length(masterDisclosure.manualIacucProtocolProjects) > 0}" >
        <kra-coi:disclosureProjects masterDisclosureProjects="${masterDisclosure.manualIacucProtocolProjects}" 
        projectDivNamePrefix="masterManualProtocolFE" projectListName="manualIacucProtocolProjects" 
        boLocation="disclosureHelper.masterDisclosureBean.manualIacucProtocolProjects"
        parentTab="Manual Projects"/>
    </c:if>
    <c:if test="${fn:length(masterDisclosure.manualTravelProjects) > 0}" >
        <kra-coi:disclosureProjects masterDisclosureProjects="${masterDisclosure.manualTravelProjects}" 
        projectDivNamePrefix="masterManualTravelFE" projectListName="manualTravelProjects" 
        boLocation="disclosureHelper.masterDisclosureBean.manualTravelProjects"
        parentTab="Manual Projects"/>
    </c:if>
	<c:if test="${fn:length(masterDisclosure.otherManualProjects) > 0}">
        <kra-coi:disclosureProjects masterDisclosureProjects="${masterDisclosure.otherManualProjects}" 
        projectDivNamePrefix="masterManualOtherFE" projectListName="otherManualProjects" 
        boLocation="disclosureHelper.masterDisclosureBean.otherManualProjects"
        parentTab="Manual Projects"/>	
	</c:if>    
       </div>
</kul:tab>
</c:if>    
<c:if test="${fn:length(masterDisclosure.awardProjects) > 0}" >
    <kul:tab defaultOpen="false" tabTitle="Awards" auditCluster="financialEntityDiscAuditErrors,coiQuestionnaireKey" tabAuditKey="disclosureHelper.masterDisclosureBean.awardProjects[*" useRiceAuditMode="true"
    	tabErrorKey="disclosureHelper.newCoiDisclProject.*" hidden="${hidden}"><div class="tab-container" align="center">
	    <kra-coi:disclosureProjects masterDisclosureProjects="${masterDisclosure.awardProjects}" 
	    projectDivNamePrefix="masterAwardFE" projectListName="awardProjects" 
	    boLocation="disclosureHelper.masterDisclosureBean.awardProjects"
	    parentTab="Awards"/>
	</div></kul:tab> 
</c:if>
<c:if test="${fn:length(masterDisclosure.proposalProjects) > 0}" > 
    <kul:tab defaultOpen="false" tabTitle="Proposals" auditCluster="financialEntityDiscAuditErrors,coiQuestionnaireKey" tabAuditKey="disclosureHelper.masterDisclosureBean.proposalProjects[*" useRiceAuditMode="true"
    	tabErrorKey="disclosureHelper.newCoiDisclProject.*" hidden="${hidden}"><div class="tab-container" align="center">
	    <kra-coi:disclosureProjects masterDisclosureProjects="${masterDisclosure.proposalProjects}" 
	    projectDivNamePrefix="masterProposalFE" projectListName="proposalProjects" 
	    boLocation="disclosureHelper.masterDisclosureBean.proposalProjects"
	    parentTab="Proposals"/>
	</div></kul:tab> 
</c:if>
<c:if test="${fn:length(masterDisclosure.protocolProjects) > 0}" >
    <kul:tab defaultOpen="false" tabTitle="Protocols" auditCluster="financialEntityDiscAuditErrors,coiQuestionnaireKey" tabAuditKey="disclosureHelper.masterDisclosureBean.protocolProjects[*" useRiceAuditMode="true"
    	tabErrorKey="disclosureHelper.newCoiDisclProject.*" hidden="${hidden}"><div class="tab-container" align="center">
	    <kra-coi:disclosureProjects masterDisclosureProjects="${masterDisclosure.protocolProjects}" 
	    projectDivNamePrefix="masterProtocolFE" projectListName="protocolProjects" 
	    boLocation="disclosureHelper.masterDisclosureBean.protocolProjects"
	    parentTab="Protocols"/>
	</div></kul:tab> 
</c:if>
