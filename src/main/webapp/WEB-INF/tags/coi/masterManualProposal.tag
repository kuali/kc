<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="masterDisclosureProjects" required="true" type="java.util.List" description="A List of active or inactive FE" %>
<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
<kul:tab defaultOpen="false" tabTitle="Manual Proposals" auditCluster="financialEntityDiscAuditErrors" tabAuditKey="${auditErrorKey}" useRiceAuditMode="true"
    tabErrorKey="disclosureHelper.newCoiDisclProject.*" >
	<div class="tab-container" align="center">
              
              
                                  
            <%-- New data --%>
            
            <%-- Existing data --%>

        	<c:forEach var="disclProjectBean" items="${masterDisclosureProjects}" varStatus="status">
                     <kra-coi:projectStyle disclProjectBean="${disclProjectBean}"/>                    
                     <kra-coi:manualProposalHeader disclProject="${disclProjectBean.disclosureProject}" idx = "0" style="${style}"/>                    
                     <kra-coi:masterProjectFE disclProjectBean="${disclProjectBean}" projectDivNamePrefix="masterManualProposalFE"  idx="${status.index}" projectListName="manualProposalProjects"/>                    
       	</c:forEach> 
            <%-- Existing data --%>
       </div>
</kul:tab>
