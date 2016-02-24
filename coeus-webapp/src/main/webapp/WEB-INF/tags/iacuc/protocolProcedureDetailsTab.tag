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

<div id="tabs">
	<dl class="tabul">
    	<c:set var="activeTabName" value="${KualiForm.iacucProtocolProceduresHelper.currentProcedureDetailTab.displayName}"/>
		<c:forEach var="procedureTab" items="${KualiForm.iacucProtocolProceduresHelper.procedureNavigationTabs}" varStatus="status">
        	<c:set var="displayTabName" value="${procedureTab.displayName}"/>
            <c:set var="methodName" value="${procedureTab.methodName}"/>
            <c:set var="currentTab" value="${activeTabName eq displayTabName}" /> 
  			<c:choose>
  				<c:when test="${currentTab}">
  					<dt class="licurrent">
  				</c:when>
  				<c:otherwise>
  					<dt>
  				</c:otherwise>
  			</c:choose>
  			<span class="tabright ${currentTab ? 'tabcurrent' : ''}">
  				<html:submit value="${displayTabName}" property="methodToCall.${methodName}.anchor${tabKey}"  alt="${displayName}" disabled="false"  />
  			</span></dt>
		</c:forEach>
 	</dl>
</div>



