<%--
 Copyright 2005-2014 The Kuali Foundation
 
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



