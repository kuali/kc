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
<%@ attribute name="budgetPersonList" required="true" type="java.util.List" %>
<%@ attribute name="budgetPersonProperty" required="true" %>
<c:set var="proposalBudgetFlag" value="${KualiForm.document.parentDocument.proposalBudgetFlag}"  scope="session"/>
<%-- <c:set target="${KualiForm.document}" property="proposalBudgetFlag" value="${proposalBudgetFlag}"/> --%>
<bean:define id="proposalBudgetFlag" value="${proposalBudgetFlag}"/>
<c:set var="budgetPersonSalaryDetailsAttributes" value="${DataDictionary.BudgetPersonSalaryDetails.attributes}" />

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
			<c:set var="canViewSalary" value="${KualiForm.editingMode['viewSalaries']}" />
			 <c:choose>
				<c:when test="${canViewSalary}">
				   	<div align="center">
			        	<kul:htmlControlAttribute property="${budgetPersonProperty}[${status.index}].calculationBase" 
			                                  attributeEntry="${attributes.calculationBase}" 
			                                  styleClass="amount" />
			    	</div>
				</c:when>
				<c:otherwise>
					<div align="center">0.0 </div>
				</c:otherwise>
			</c:choose>
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
				   
				    <c:if test="${KualiForm.enableBudgetSalaryByPeriod=='Y'}">
						<c:if test="${proposalBudgetFlag}">
						<c:if test= "${person.personId != null}" >
							<div align=center>
								&nbsp; <a id="budgetPersonSalaryDetail"
									title="Person Salary Details" href="#"
									onclick="showBudgetPersonSalaryDetails(${KualiForm.viewDivBooleanFlag}, ${status.index}, ${KualiForm.document.budget.budgetId}, ${person.personSequenceNumber}, ${person.personId}, showBudgetPersonSalaryDetails_Callback);"
									noresize> Base Salary by Period </a>
							</div>
						</c:if>
						<c:if test= "${person.personId == null}" >
						<c:if test = "${person.rolodexId != null }" >
							<div align=center>
								&nbsp; <a id="budgetPersonSalaryDetail"
									title="Person Salary Details" href="#"
									onclick="showBudgetPersonSalaryDetails(${KualiForm.viewDivBooleanFlag}, ${status.index}, ${KualiForm.document.budget.budgetId}, ${person.personSequenceNumber}, ${person.rolodexId}, showBudgetPersonSalaryDetails_Callback);"
									noresize> Base Salary by Period </a>
							</div>
							</c:if>
							<c:if test = "${person.rolodexId == null}" >
							<div align=center>
								&nbsp; <a id="budgetPersonSalaryDetail"
									title="Person Salary Details" href="#"
									onclick="showBudgetPersonSalaryDetails(${KualiForm.viewDivBooleanFlag}, ${status.index}, ${KualiForm.document.budget.budgetId}, ${person.personSequenceNumber}, ${person.tbnId}, showBudgetPersonSalaryDetails_Callback);"
									noresize> Base Salary by Period </a>
							</div>
							</c:if>
						</c:if>
					</c:if>
					</c:if>
					<div id="paramDiv+${status.index}" class="dialog"
							style="z-index: 999; width: 450px; height: auto; min-height: 170px; border: 2; border-style: solid; top: 200px; left: 500px; position: absolute; border-color: #6B6B6B; background-color: #FFFFFF; display: none;"center" >
							<kra:section permission="modifyBudgets">
								<table cellpadding=0 cellspacing="0" summary="" id="BudgetPersonSalaryInPeriods${status.index}">
								<tr><h3>Base Salary By Period</h3></tr>
									<tr>
										<th class="content_grey" style="horizondal-align: top;"
											width="10%" height="20"><div align="left">
												<kul:htmlAttributeLabel
													attributeEntry="${budgetPersonSalaryDetailsAttributes.budgetPeriod}"
													noColon="true" />
											</div></th>
										
										<th class="content_grey" style="horizondal-align: top;"
											width="10%" height="20"><div align="left">
												<kul:htmlAttributeLabel
													attributeEntry="${budgetPersonSalaryDetailsAttributes.baseSalary}"
													noColon="true" />
											</div></th>
									</tr>

									<c:forEach var="newbudgetSalaryDetails"
										items="${KualiForm.document.budget.budgetPeriods}"
										varStatus="periodStatus">
										
										<tr>
											<td width="5%" class="infoline"><c:out
													value="${periodStatus.index+1}" /> &nbsp;</td>
											<td id="BudgetPersonSalaryInPeriodsCol+${status.index}${periodStatus.index}">
														<kul:htmlControlAttribute
															property="${budgetPersonProperty}[${status.index}].budgetPersonSalaryDetails[${periodStatus.index}].baseSalary"
															attributeEntry="${budgetPersonSalaryDetailsAttributes.baseSalary}"
															readOnly="${readOnly}" />
													</td>

										</tr>
										
									</c:forEach>
								
								</table>

								<div align="center">
									<br>
									<html:image
										src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif"
										styleClass="globalbuttons" property="methodToCall.closePopUp"
										title="close" alt="close"										
										tabindex="${tabindex}" />

									<html:image
										src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif"
										styleClass="globalbuttons"
										property="methodToCall.saveBudgetPersonSalaryInDiffPeriods.line${status.index}" title="save"
										alt="save" onclick="resetScrollPosition();"
										tabindex="${tabindex}" />

									<html:image
										src="${ConfigProperties.kra.externalizable.images.url}buttonsmall_calculate.gif"
										styleClass="globalbuttons"
										property="methodToCall.calculatePersonSalaryDetails.line${status.index}" />
										

								</div>
							</kra:section>
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





