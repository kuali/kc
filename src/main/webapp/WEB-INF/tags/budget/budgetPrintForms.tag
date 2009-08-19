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

<kul:tabTop tabTitle="Print Forms" defaultOpen="false" tabErrorKey="">
    <c:set var="disableBox" value = "true" />
	<c:forEach var="budgetPeriod" items="${KualiForm.document.budget.budgetPeriods}" varStatus="idx">
	    <c:if test="${fn:length(budgetPeriod.budgetLineItems) > 0}" >
    		<c:set var="disableBox" value = "false" />
	    </c:if>
	</c:forEach>
	<div class="tab-container" align="center">
		<h3>Print Forms</h3>
		<table cellspacing="0" cellpadding="0" summary="">
			<tbody>
		    	<c:forEach var="form" items="${KualiForm.document.budget.budgetPrintForms}" varStatus="status">
		            <tr>	                
		                <td width="50">
		                	<c:out value="${status.index + 1 }"/>
		                </td>
		                <td align="left" valign="middle">
		                	<c:out value="${KualiForm.document.budget.budgetPrintForms[status.index].budgetReportName}"/>
						</td>
		                <td align="center" valign="middle">
		                	<div align="center">
		                	<c:choose>
		                		<c:when test="${status.index < 6}">
		                			<html:multibox property="selectedBudgetPrintFormId" value="${KualiForm.document.budget.budgetPrintForms[status.index].budgetReportId}"/>	
		                		</c:when>
		                		<c:otherwise>
		                			<html:multibox property="selectedBudgetPrintFormId" value="${KualiForm.document.budget.budgetPrintForms[status.index].budgetReportId}" disabled="${disableBox}"/>	
		                		</c:otherwise>
		                	</c:choose>			                	
		                	</div>
		                </td>			       
		            </tr>    	
		    	</c:forEach>		    	
				<tr>
					<td colspan="2" class="infoline">
						<div align="center">
						<html:image property="methodToCall.printBudgetForm"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-printsel.gif' styleClass="tinybutton" alt="Print Selected Forms" onclick="excludeSubmitRestriction=true"/>
						</div>
					</td>
					<td>
							<div align="center">
							Select (<html:link href="#" onclick="javascript: selectAllBudgetForms(document);return false">all</html:link> | <html:link href="#" onclick="javascript: unselectAllBudgetForms(document);return false">none</html:link>)
							</div>						
					</td>
				</tr>			                         
			</tbody>
			<tbody id="G" style="display: none;" />
		</table>
	</div>
</kul:tabTop>
