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
<%@ attribute name="budgetPersonList" required="true" type="java.util.List" %>
<%@ attribute name="budgetPersonProperty" required="true" %>

<c:set var="attributes" value="${DataDictionary.BudgetPerson.attributes}" />

<table id="budget-personnel-table" cellpadding=0 cellspacing="0" summary="">
	<tr>
		<th>&nbsp;</th>
		<th>Person</th>
		<th><kul:htmlAttributeLabel attributeEntry="${attributes.jobCode}" /></div></th>
		<th><kul:htmlAttributeLabel attributeEntry="${attributes.appointmentType}" /></div></th>
		<th><kul:htmlAttributeLabel attributeEntry="${attributes.calculationBase}" /></div></th>
		<th><kul:htmlAttributeLabel attributeEntry="${attributes.effectiveDate}" /></th>
		<c:if test="${KualiForm.document.budget.salaryInflationEnabled}">
			<th><kul:htmlAttributeLabel attributeEntry="${attributes.salaryAnniversaryDate}" /></th>
		</c:if>
        <kra:section permission="modifyBudgets">
		    <th>Actions</th>
		</kra:section>
	</tr>
	
	<kra:section permission="modifyBudgets">
		<tr>
		<th>Add:</th>
		    <td colspan="7">
			    <label>Employee Search</label>
			    <label><kul:multipleValueLookup boClassName="org.kuali.kra.bo.KcPerson" 
				                                lookedUpCollectionName="newBudgetPersons" /></label><br>
				<label>Non-employee Search</label>
				<label><kul:multipleValueLookup boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" 
				                                lookedUpCollectionName="newBudgetRolodexes" /></label><br>
				<label>To be named</label>
				<label><kul:multipleValueLookup boClassName="org.kuali.kra.budget.personnel.TbnPerson" 
				                                lookedUpCollectionName="newTbnPersons" /></label>
			</td>
		</tr>
	</kra:section>

	<c:forEach var="person" items="${budgetPersonList}" varStatus="status">
           <tr>
               <th scope="row"><div align="center">${status.index + 1}</div></th>
               <td>${person.personName} <c:if test="${!empty person.role}"><span class="fineprint">(${person.role})</span></c:if></td>
               
               <td>
                   <c:set var="jobCodeFieldName" value="${budgetPersonProperty}[${status.index}].jobCode"/> 
                   <c:set var="jobTitleFieldName" value="${budgetPersonProperty}[${status.index}].jobTitle"/> 
                   <kul:htmlControlAttribute property="${budgetPersonProperty}[${status.index}].jobCode" 
                                             attributeEntry="${attributes.jobCode}" 
                                             onblur="loadJobCodeTitle('${jobCodeFieldName}', '${jobTitleFieldName}');" />
                   <kra:section permission="modifyBudgets">
                       <kul:lookup boClassName="org.kuali.kra.budget.personnel.JobCode" 
                                   fieldConversions="jobCode:${budgetPersonProperty}[${status.index}].jobCode,jobTitle:${budgetPersonProperty}[${status.index}].jobTitle" 
                                   anchor="${tabKey}" />
                   </kra:section>
		
                   <div id="${budgetPersonProperty}[${status.index}].jobTitle.div" align="left">
                    <c:if test="${!empty budgetPersonList[status.index].jobCode}">                       
                        ${budgetPersonList[status.index].jobTitle}
					    <c:if test="${empty budgetPersonList[status.index].jobTitle}">
					        <span style='color: red;'>not found</span>
					    </c:if>
				    </c:if>
                   </div>
               </td>
               
			<td>
			    <kul:htmlControlAttribute property="${budgetPersonProperty}[${status.index}].appointmentTypeCode" 
			                              attributeEntry="${attributes.appointmentType}" 
			                              readOnly="${readOnly}" />
			</td>
			
			<td>
			    <div align="center">
			        <kul:htmlControlAttribute property="${budgetPersonProperty}[${status.index}].calculationBase" 
			                                  attributeEntry="${attributes.calculationBase}" 
			                                  styleClass="amount" />
			    </div>
			</td>
			<td>
			    <div align="center">
			        <kul:htmlControlAttribute property="${budgetPersonProperty}[${status.index}].effectiveDate" 
			                                  attributeEntry="${attributes.effectiveDate}"  />
			    </div>
			</td>
			<c:if test="${KualiForm.document.budget.salaryInflationEnabled}">
			<td>
			    <div align="center">
			        <kul:htmlControlAttribute property="${budgetPersonProperty}[${status.index}].salaryAnniversaryDate" 
			                                  attributeEntry="${attributes.salaryAnniversaryDate}"  />
			    </div>
			</td>
			</c:if>
			<kra:section permission="modifyBudgets">
				<td>
				    <div align=center>&nbsp;
				        <html:image property="methodToCall.deleteBudgetPerson.line${status.index}" 
				                    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' 
				                    styleClass="tinybutton" />
				    </div>
				</td>
			</kra:section>
		</tr>
	</c:forEach>
</table>

<br/>

<kra:section permission="modifyBudgets">
	<div align="center">
	    <html:image property="methodToCall.synchToProposal" 
	                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-syncpersonnel.gif' 
	                styleClass="tinybutton"/>
	</div>
</kra:section>