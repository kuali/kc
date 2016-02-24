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
<%@ attribute name="masterDisclosureProjects" required="true" type="java.util.List" description="A List of active or inactive FE" %>
<%@ attribute name="boLocation" required="true" description="Location of the disclosure projects list on the form." %>
<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />              
<%@ attribute name="projectDivNamePrefix" required="true" description="name for project related div" %>
<%@ attribute name="projectListName" required="true" description="Project list name in master bean" %>
<%@ attribute name="parentTab" required="true" description="Parent Tab for any tabs created within this tag." %>
              
                                  
            <%-- New data --%>
            
            <%-- Existing data --%>

<%--
<c:set var="hidden" value="${not masterDisclosureProjects[0].coiDisclProject.coiDisclosure.approvedDisclosure and masterDisclosureProjects[0].coiDisclProject.coiDisclosureEventType.excludeFromMasterDisclosure}" />
--%>

<div <c:if test="${hidden}">style="display:none;"</c:if>>                                  
            <c:forEach var="disclProjectBean" items="${masterDisclosureProjects}" varStatus="status">
                    <kra-coi:projectStyle disclProjectBean="${disclProjectBean}"/>                    
                    <kra-coi:projectHeader disclProject="${disclProjectBean.coiDisclProject}" boLocation="${boLocation}[${status.index}].coiDisclProject"/>                    
                    <kra-coi:masterProjectQuestionnaires disclProjectBean="${disclProjectBean}" parentTab="${parentTab}" boLocation="${boLocation}[${status.index}]"/>                    
                    <kra-coi:masterProjectFE disclProjectBean="${disclProjectBean}" projectDivNamePrefix="${projectDivNamePrefix}"  idx="${status.index}" projectListName="${projectListName}"/>
            </c:forEach> 
 </div>         
            <%-- Existing data --%>

