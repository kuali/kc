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

<kul:tabTop tabTitle="Print Forms" defaultOpen="false" tabErrorKey="">
    <c:set var="disableBox" value = "true" />
	<c:forEach var="budgetPeriod" items="${KualiForm.document.budget.budgetPeriods}" varStatus="idx">
	    <c:if test="${fn:length(budgetPeriod.budgetLineItems) > 0}" >
    		<c:set var="disableBox" value = "false" />
	    </c:if>
	</c:forEach>
	<div class="tab-container" align="center">
		<table cellspacing="0" cellpadding="0" summary="">
			<tbody>
				<tr>
				 <td colspan="2" width="65%" style="padding: 0;border: 0">
						<h3>
							<div align="center">
								<span align="left">Print Forms</span>
							</div>
						</h3>
					</td>
					<td align="center" style="padding: 0;border: 0" width="25%">
						<h3>
							<div align="center">
								<span align="center">Print Budget Comments</span>
							</div>
						</h3>
					</td>
					<td style="padding: 0;border: 0" width="10%">
						<h3>
							<div align="center">
								<span align="center">Actions</span>
							</div>
						</h3>
					</td>
				</tr> 
				<c:forEach var="form" items="${KualiForm.document.budget.budgetPrintForms}" varStatus="status">
		            <tr>	                
		                <td width="3%">
		                	<c:out value="${status.index + 1 }"/>
		                </td>
		                <td align="left" valign="middle">
		                	<c:out value="${KualiForm.document.budget.budgetPrintForms[status.index].budgetReportName}"/>
						</td>
		                <td align="center" valign="middle" >
		                	<div align="center">
		                			<html:multibox  property="selectedToPrintComment"  value="${KualiForm.document.budget.budgetPrintForms[status.index].budgetReportId}" />
		                	</div>
		                </td>
						<td align="center" valign="middle"">
							<div align="center">
								<html:image
									property="methodToCall.printBudgetForm.line${status.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-print.gif'
									styleClass="tinybutton" alt="Print Selected Forms"
									onclick="excludeSubmitRestriction=true" />
							</div></td>
					</tr>    	
		    	</c:forEach>		    	
			</tbody>
			<tbody id="G" style="display: none;" />
		</table>
	</div>
</kul:tabTop>
