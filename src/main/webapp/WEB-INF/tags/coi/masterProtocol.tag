<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="masterDisclosureProjects" required="true" type="java.util.List" description="A List of active or inactive FE" %>
<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
<kul:tab defaultOpen="false" tabTitle="Protocols" auditCluster="financialEntityDiscAuditErrors" tabAuditKey="${auditErrorKey}" useRiceAuditMode="true"
    tabErrorKey="disclosureHelper.newCoiDisclProject.*" >
	<div class="tab-container" align="center">
              
              
                                  
            <%-- New data --%>
            
            <%-- Existing data --%>

        	<c:forEach var="disclProjectBean" items="${masterDisclosureProjects}" varStatus="status">
                     <kra-coi:protocolHeader disclProject="${disclProjectBean.disclosureProject}" />                    
                     <kra-coi:masterProjectFE disclProjectBean="${disclProjectBean}" projectDivNamePrefix="masterProtocolFE"  idx="${status.index}"/>                    
        	</c:forEach> 
            <%-- Existing data --%>
       </div>
</kul:tab>
