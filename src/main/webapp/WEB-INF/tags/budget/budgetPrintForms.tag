<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<kul:tabTop tabTitle="Print Forms" defaultOpen="false" tabErrorKey="">
    <c:set var="disableBox" value = "true" />
	<c:forEach var="budgetPeriod" items="${KualiForm.document.budgetPeriods}" varStatus="idx">
	    <c:if test="${fn:length(budgetPeriod.budgetLineItems) > 0}" >
    		<c:set var="disableBox" value = "false" />
	    </c:if>
	</c:forEach>
	<div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Print Forms</span>
		</h3>
		<table cellspacing="0" cellpadding="0" summary="">
			<tbody>
		    	<c:forEach var="form" items="${KualiForm.document.budgetPrintForms}" varStatus="status">
		            <tr>	                
		                <td width="50">
		                	<c:out value="${status.index + 1 }"/>
		                </td>
		                <td align="left" valign="middle">
		                	<c:out value="${KualiForm.document.budgetPrintForms[status.index].budgetReportName}"/>
						</td>
		                <td align="center" valign="middle">
		                	<div align="center">
		                	<c:choose>
		                		<c:when test="${status.index < 6}">
		                			<html:multibox property="selectedBudgetPrintFormId" value="${KualiForm.document.budgetPrintForms[status.index].budgetReportId}"/>	
		                		</c:when>
		                		<c:otherwise>
		                			<html:multibox property="selectedBudgetPrintFormId" value="${KualiForm.document.budgetPrintForms[status.index].budgetReportId}" disabled="${disableBox}"/>	
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
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-printsel.gif' styleClass="tinybutton"/>
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