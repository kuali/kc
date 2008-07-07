<%--
 Copyright 2005-2006 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl1.php

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="budgetModularBudget"
	documentTypeName="BudgetDocument"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="modularBudget"
  	extraTopButtons="${KualiForm.extraTopButtons}">
	
	<kul:uncollapsable tabTitle="Select Modular Budget Period">
  		<div align="center">
  			<label for="budget period">Budget Period:</label>
  			<html:select property="modularSelectedPeriod">
  				<html:option value="0">View All</html:option>
  				<c:forEach var="budgetPeriod" items="${KualiForm.document.budgetPeriods}" varStatus="status">
  					<html:option value="${budgetPeriod.budgetPeriod}">${budgetPeriod.budgetPeriod}: ${budgetPeriod.dateRange}</html:option>
  				</c:forEach>
  			</html:select>
          	<br/><br/>
          	<span><html:image property="methodToCall.updateView" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-updateview.gif' /></span>
        </div>
	</kul:uncollapsable>
	<br/>
	
	<kra-b:budgetModular periodNum="${KualiForm.modularSelectedPeriod}"/>
	
	<c:set var="extraButtonSource" value="" scope="request" />
	<c:set var="extraButtonProperty" value="" scope="request" />
	<c:set var="extraButtonAlt" value="" scope="request" />
    <!--  probably only need to check  KualiForm.editingMode['modifyBudgets'] -->
	<c:if test="${KualiForm.editingMode['modifyBudgets'] && KualiForm.document.budgetStatus != '1'}">
		<c:set var="extraButtonSource" value="${ConfigProperties.kra.externalizable.images.url}buttonsmall_sync.gif" scope="request" />
	    <c:set var="extraButtonProperty" value="methodToCall.sync" scope="request" />
		<c:set var="extraButtonAlt" value="" scope="request" />
	</c:if>
	
	<kul:documentControls 
		transactionalDocument="false"
		suppressRoutingControls="true"
		viewOnly="${KualiForm.editingMode['viewOnly']}"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		/>

</kul:documentPage>