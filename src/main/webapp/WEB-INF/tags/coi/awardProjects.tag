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
<%@ attribute name="auditErrorKey" required="true" description="audit error keys" %>

<c:set var="coiDisclProjectAttributes" value="${DataDictionary.CoiDisclProject.attributes}" />
<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
<kul:tab defaultOpen="false" tabTitle="Awards" auditCluster="financialEntityDiscAuditErrors" tabAuditKey="${auditErrorKey}" useRiceAuditMode="true"
    tabErrorKey="disclosureHelper.newCoiDisclProject.*" >              
              
                                  
            
            <%-- Existing data --%>

        	<c:forEach var="disclProject" items="${KualiForm.document.coiDisclosureList[0].coiDisclEventProjects}" varStatus="status">

                 <c:if test="${disclProject.awardEvent}">
                 <kra-coi:awardFinancialEntity disclProject="${disclProject}"  idx="${status.index}"/>	            
                 </c:if>

        	</c:forEach> 
            <%-- Existing data --%>
</kul:tab>

