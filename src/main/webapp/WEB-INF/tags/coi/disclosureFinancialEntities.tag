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
<c:set var="awardAuditError" value=""/>
<c:set var="proposalAuditError" value=""/>
<c:set var="protocolAuditError" value=""/>
        	<c:forEach var="disclProject" items="${KualiForm.document.coiDisclosureList[0].coiDisclEventProjects}" varStatus="status">
                <c:choose>
                    <c:when test="${disclProject.proposalEvent}">
                        <c:choose>
                            <c:when test="${empty proposalAuditError}">
                                 <c:set var="proposalAuditError" value="document.coiDisclosureList[0].coiDisclEventProjects[${status.index}].*"/>
                            </c:when>
                            <c:otherwise>
                                 <c:set var="proposalAuditError" value="${proposalAuditError},document.coiDisclosureList[0].coiDisclEventProjects[${status.index}].*"/>
                            </c:otherwise>
                         </c:choose>
                    </c:when>
                    <c:when test="${disclProject.awardEvent}">
                        <c:choose>
                            <c:when test="${empty awardAuditError}">
                                 <c:set var="awardAuditError" value="document.coiDisclosureList[0].coiDisclEventProjects[${status.index}].*"/>
                            </c:when>
                            <c:otherwise>
                                 <c:set var="awardAuditError" value="${awardAuditError},document.coiDisclosureList[0].coiDisclEventProjects[${status.index}].*"/>
                            </c:otherwise>
                         </c:choose>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${empty protocolAuditError}">
                                 <c:set var="protocolAuditError" value="document.coiDisclosureList[0].coiDisclEventProjects[${status.index}].*"/>
                            </c:when>
                            <c:otherwise>
                                 <c:set var="protocolAuditError" value="${protocolAuditError},document.coiDisclosureList[0].coiDisclEventProjects[${status.index}].*"/>
                            </c:otherwise>
                         </c:choose>
                    </c:otherwise>
                
                </c:choose>
             </c:forEach>
     <c:if test="${not empty awardAuditError}">             
         <kra-coi:awardProjects auditErrorKey="${awardAuditError}" />	
     </c:if>                
     <c:if test="${not empty proposalAuditError}">             
         <kra-coi:proposalProjects auditErrorKey="${proposalAuditError}" />	            
     </c:if>                
     <c:if test="${not empty protocolAuditError}">             
         <kra-coi:protocolProjects auditErrorKey="${protocolAuditError}" />	            
     </c:if>                
         