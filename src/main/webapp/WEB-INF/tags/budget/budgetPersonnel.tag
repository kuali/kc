<%--
 Copyright 2006-2009 The Kuali Foundation

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

<c:set var="budgetPersonAttributes" value="${DataDictionary.BudgetPerson.attributes}" />
<div id="workarea">
<kul:tab tabTitle="Budget Personnel" defaultOpen="true" transparentBackground="true" tabErrorKey="document.budgetPerson*" auditCluster="budgetPersonnelAuditWarnings" tabAuditKey="document.budgetPerson*" useRiceAuditMode="true" alwaysOpen="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Budget Personnel</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.budget.bo.BudgetPerson" altText="help"/></span>
        </h3>
        <table id="budget-personnel-table" cellpadding=0 cellspacing="0" summary="">
        	<tr>
	        	<th>&nbsp;</th>
	            <th>Person</th>
	            <th><kul:htmlAttributeLabel attributeEntry="${budgetPersonAttributes.jobCode}" /></div></th>
	            <th><kul:htmlAttributeLabel attributeEntry="${budgetPersonAttributes.appointmentType}" /></div></th>
	            <th><kul:htmlAttributeLabel attributeEntry="${budgetPersonAttributes.calculationBase}" /></div></th>
	            <th><kul:htmlAttributeLabel attributeEntry="${budgetPersonAttributes.effectiveDate}" /></th>
	            <th>Actions</th>
			</tr>
            <c:forEach var="person" items="${KualiForm.document.budgetPersons}" varStatus="status">
            <tr>
				<c:set var="personSelectStyle" value="" scope="request"/>
            	<c:forEach items="${ErrorPropertyList}" var="key">
            	    <c:set var="idxKey" value="document.budgetPerson[${status.index}].dupkey" />
				    <c:if test="${key eq idxKey}">
					  <c:set var="personSelectStyle" value="background-color:#FFD5D5" scope="request"/>
				    </c:if>
			     </c:forEach>
              	<th scope="row"><div align="center">${status.index + 1}</div></th>
              	<td style="${personSelectStyle}">${person.personName} <c:if test="${!empty person.role}"><span class="fineprint">(${person.role})</span></c:if></td>
              	<td>
              	 	<c:set var="jobCodeFieldName" value="document.budgetPersons[${status.index}].jobCode"/> 
              	 	<c:set var="jobTitleFieldName" value="document.budgetPersons[${status.index}].jobTitle"/> 
              		<kul:htmlControlAttribute property="document.budgetPersons[${status.index}].jobCode" attributeEntry="${budgetPersonAttributes.jobCode}" onblur="loadJobCodeTitle('${jobCodeFieldName}', '${jobTitleFieldName}');" />
              		<kul:lookup boClassName="org.kuali.kra.budget.bo.JobCode" fieldConversions="jobCode:document.budgetPersons[${status.index}].jobCode,jobTitle:document.budgetPersons[${status.index}].jobTitle" anchor="${tabKey}" />
              		
              		<div id="document.budgetPersons[${status.index}].jobTitle.div" align="left">
                        <c:if test="${!empty KualiForm.document.budgetPersons[status.index].jobCode}">               	    
							<c:out value="${KualiForm.document.budgetPersons[status.index].jobTitle}" /> 
							<c:if test="${empty KualiForm.document.budgetPersons[status.index].jobTitle}">
	                    		<span style='color: red;'>not found</span>
							</c:if>                   
                        </c:if>
					</div>
              	</td>
              	<td>
              		<kra:kraControlAttribute property="document.budgetPerson[${status.index}].appointmentTypeCode" readOnly="${readOnly}" attributeEntry="${budgetPersonAttributes.appointmentType}"/>
              	</td>
              	<td>
              		<div align="center">
                  		<kul:htmlControlAttribute property="document.budgetPerson[${status.index}].calculationBase" attributeEntry="${budgetPersonAttributes.calculationBase}" />
              		</div>
              	</td>
              	<td>
              		<div align="center">
						<kul:htmlControlAttribute property="document.budgetPerson[${status.index}].effectiveDate" attributeEntry="${budgetPersonAttributes.effectiveDate}" datePicker="true" />
                  	</div>
                </td>
              	<td>
              		<div align=center>&nbsp;
              			<kra:section permission="modifyBudgets">
	              			<html:image property="methodToCall.deleteBudgetPerson.line${status.index}.x" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
	              		</kra:section>
              		</div>
              	</td>
			</tr>
        	</c:forEach>
        </table>
   	</div>
</kul:tab>

<!--  "workarea" div is ended in panelfooter tag -->
<kul:panelFooter /> 
