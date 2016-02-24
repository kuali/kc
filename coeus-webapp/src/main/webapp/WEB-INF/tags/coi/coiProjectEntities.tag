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
<%@ attribute name="projectDivNamePrefix" required="true" description="name for project related div" %>
<%@ attribute name="projectListName" required="true" description="Project list name in master bean" %>
<%@ attribute name="parentTab" required="true" description="Parent Tab for any tabs created within this tag." %>
<%@ attribute name="disclosureGroupedByEvent" required="true" description="Boolean to check if project is grouped by event or entity" %>
<%@ attribute name="groupedEntityNumber" required="true" description="Entity number - valid only if grouped by entity" %>
              
                                  

<div <c:if test="${hidden}">style="display:none;"</c:if>>                                  
            <c:forEach var="disclProjectBean" items="${masterDisclosureProjects}" varStatus="status">
                    <kra-coi:projectStyle disclProjectBean="${disclProjectBean}"/>                    
                    <kra-coi:projectHeader disclProject="${disclProjectBean.coiDisclProject}" boLocation="${boLocation}[${status.index}].coiDisclProject"/>                    
                    <kra-coi:feStatusReview disclProjectBean="${disclProjectBean}" projectDivNamePrefix="${projectDivNamePrefix}"  idx="${status.index}" projectListName="${projectListName}"
                    disclosureGroupedByEvent="${disclosureGroupedByEvent}"
				    groupedEntityNumber="${groupedEntityNumber}"/>
            </c:forEach> 
 </div>         

