 <%--
 Copyright 2005-2010 The Kuali Foundation

 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.osedu.org/licenses/ECL-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
<c:set var="financialEntityAttributes" value="${DataDictionary.PersonFinIntDisclosure.attributes}" />
<kul:tab defaultOpen="false" tabTitle="Financial Entity Relationships" auditCluster="financialEntityDiscAuditErrors" tabAuditKey="document.coiDisclosureList[0].coiDisclEventProjects[*" useRiceAuditMode="true"
    tabErrorKey="document.coiDisclosureList[0].coiDiscDetails*" >
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Projects</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.coi.CoiDiscDetail" altText="help"/></span>
        </h3>
        
<table cellpadding=0 cellspacing=0 summary="">
    <tr>
        <td>
            
            <%-- Existing data --%>
        	<c:forEach var="disclProject" items="${KualiForm.document.coiDisclosureList[0].coiDisclEventProjects}" varStatus="status">

                <c:choose>
                    <c:when test="${disclProject.proposalEvent}">
                        <kra-coi:devProposalFinancialEntity disclProject="${disclProject}"  idx="${status.index}"/>	            
                    </c:when>
                    <c:when test="${disclProject.awardEvent}">
                        <kra-coi:awardFinancialEntity disclProject="${disclProject}"  idx="${status.index}"/>	            
                    </c:when>
                    <c:otherwise>
                        <kra-coi:protocolFinancialEntity disclProject="${disclProject}"  idx="${status.index}"/>	            
                    </c:otherwise>
                
                </c:choose>

        	</c:forEach> 
            <%-- Existing data --%>

        </td>
    </tr>
</table>


    </div>
</kul:tab>

