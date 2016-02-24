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

<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
<c:set var="financialEntityAttributes" value="${DataDictionary.PersonFinIntDisclosure.attributes}" />
<c:set var="awardDisplayValue" value=""/>
<c:set var="proposalDisplayValue" value=""/>
<c:set var="protocolDisplayValue" value=""/>

<c:forEach var="disclProject" items="${KualiForm.document.coiDisclosureList[0].coiDisclProjects}" varStatus="status">
    <c:choose>
        <c:when test="${disclProject.proposalEvent}">
            <c:choose>
                <c:when test="${empty proposalDisplayValue}">
                    <c:set var="proposalDisplayValue" value="document.coiDisclosureList[0].coiDisclProjects[${status.index}].*"/>
                </c:when>
                <c:otherwise>
                    <c:set var="proposalDisplayValue" value="${proposalDisplayValue},document.coiDisclosureList[0].coiDisclProjects[${status.index}].*"/>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:when test="${disclProject.awardEvent}">
            <c:choose>
                <c:when test="${empty awardDisplayValue}">
                     <c:set var="awardDisplayValue" value="document.coiDisclosureList[0].coiDisclProjects[${status.index}].*"/>
                </c:when>
                <c:otherwise>
                     <c:set var="awardDisplayValue" value="${awardDisplayValue},document.coiDisclosureList[0].coiDisclProjects[${status.index}].*"/>
                </c:otherwise>
             </c:choose>
        </c:when>
        <c:when test="${disclProject.protocolEvent}">
            <c:choose>
                <c:when test="${empty protocolDisplayValue}">
                    <c:set var="protocolDisplayValue" value="document.coiDisclosureList[0].coiDisclProjects[${status.index}].*"/>
                </c:when>
                <c:otherwise>
                    <c:set var="protocolDisplayValue" value="${protocolDisplayValue},document.coiDisclosureList[0].coiDisclProjects[${status.index}].*"/>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
        </c:otherwise>
                
    </c:choose>
</c:forEach>
<c:if test="${not empty awardDisplayValue}">      
	<kul:tab defaultOpen="false" tabTitle="Awards" auditCluster="financialEntityDiscAuditErrors" tabAuditKey="${awardDisplayValue}" useRiceAuditMode="true"
	    tabErrorKey="disclosureHelper.newCoiDisclProject.*" >
		<div class="tab-container" align="center">
			<c:forEach var="disclProject" items="${KualiForm.document.coiDisclosureList[0].coiDisclProjects}" varStatus="status">
				<c:if test="${disclProject.awardEvent}">
					<kra-coi:genericFinancialEntity disclProject="${disclProject}" idx="${status.index}" boLocation="document.coiDisclosureList[0].coiDisclProjects[${status.index}]"/>	            
				</c:if>
			</c:forEach>
		</div>
	</kul:tab>        	
</c:if>                
<c:if test="${not empty proposalDisplayValue}">             
	<kul:tab defaultOpen="false" tabTitle="Proposals" auditCluster="financialEntityDiscAuditErrors" tabAuditKey="${proposalDisplayValue}" useRiceAuditMode="true"
	    tabErrorKey="disclosureHelper.newCoiDisclProject.*" >
		<div class="tab-container" align="center">
			<c:forEach var="disclProject" items="${KualiForm.document.coiDisclosureList[0].coiDisclProjects}" varStatus="status">
				<c:if test="${disclProject.proposalEvent}">
					<kra-coi:genericFinancialEntity disclProject="${disclProject}"  idx="${status.index}" boLocation="document.coiDisclosureList[0].coiDisclProjects[${status.index}]"/>	            
				</c:if>
			</c:forEach>
		</div>
	</kul:tab>        	
</c:if>                
<c:if test="${not empty protocolDisplayValue}">   
    <kul:tab defaultOpen="false" tabTitle="Protocols" auditCluster="financialEntityDiscAuditErrors" tabAuditKey="${protocolDisplayValue}" useRiceAuditMode="true"
	    tabErrorKey="disclosureHelper.newCoiDisclProject.*" >
		<div class="tab-container" align="center">
			<c:forEach var="disclProject" items="${KualiForm.document.coiDisclosureList[0].coiDisclProjects}" varStatus="status">
				<c:if test="${disclProject.protocolEvent}">
					<kra-coi:genericFinancialEntity disclProject="${disclProject}"  idx="${status.index}" boLocation="document.coiDisclosureList[0].coiDisclProjects[${status.index}]"/>	            
				</c:if>
			</c:forEach>
		</div>
	</kul:tab>           
</c:if>             
