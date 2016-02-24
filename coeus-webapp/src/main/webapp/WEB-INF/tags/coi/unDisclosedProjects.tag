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
<%@ attribute name="disclosureProjects" required="true" type="java.util.List" description="A List of active or inactive FE" %>
<%@ attribute name="boLocation" required="true" description="Location of the disclosure projects list on the form." %>
<%@ attribute name="projectDivNamePrefix" required="true" description="name for project related div" %>
<%@ attribute name="projectListName" required="true" description="Project list name in master bean" %>
<%@ attribute name="idx" required="true" description="Coi disl project list index" %>
              
                                  

<div <c:if test="${hidden}">style="display:none;"</c:if>>                                  
	<c:forEach var="disclProjectBean" items="${disclosureProjects}" varStatus="status">
		<kra-coi:coiFinancialEntity disclProject="${disclProjectBean.coiDisclProject}"  idx="${status.index}" boLocation="${boLocation}[${status.index}]"
		projectDivNamePrefix="${projectDivNamePrefix}" projectIndex="${idx}" projectListName="${projectListName}"/>	            
    </c:forEach> 
 </div>         

