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

<c:set var="readOnly" value="${not KualiForm.editingMode['modifyBudgets'] && ( not parentReadOnlyFlag )}" scope="request" />


<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="${KualiForm.actionPrefix}ModularBudget"
	documentTypeName="${KualiForm.docTypeName}"
  	headerDispatch="${KualiForm.headerDispatch}"
  	showTabButtons="true"
  	headerTabActive="modularBudget"
  	extraTopButtons="${KualiForm.extraTopButtons}">
  	
  	<div align="right"><kul:help documentTypeName="BudgetDocument" pageName="Modular Budget" /></div>
	
	<kra:uncollapsable tabTitle="Select Modular Budget Period">
  		<div align="center">
			<label for="modularSelectedPeriod">Budget Period:
	  			<html:select property="modularSelectedPeriod">
    				<html:option value="0">View All</html:option>
    				<c:forEach var="budgetPeriod" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status">
	      				<html:option value="${budgetPeriod.budgetPeriod}">${budgetPeriod.budgetPeriod}: ${budgetPeriod.dateRange}</html:option>
    				</c:forEach>
  				</html:select>
			</label>
          	<br/><br/>
          	<span><html:image property="methodToCall.updateView" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-updateview.gif' styleClass="tinybutton"/></span>
        </div>
	</kra:uncollapsable>
	<br/>
	
	<kra-b:budgetModular periodNum="${KualiForm.modularSelectedPeriod}"/>
	<c:set var="extraButtonSource" value="" scope="request" />
	<c:set var="extraButtonProperty" value="" scope="request" />
	<c:set var="extraButtonAlt" value="" scope="request" />
	<c:if test="${KualiForm.editingMode['modifyBudgets'] && KualiForm.document.budget.budgetStatus != '1'}">
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
		suppressCancelButton="true"
		/>

</kul:documentPage>
