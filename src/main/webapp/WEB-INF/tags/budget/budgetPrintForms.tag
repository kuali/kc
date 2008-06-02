<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<kul:tabTop tabTitle="Print Forms" defaultOpen="false" tabErrorKey="">
	<div class="tab-container" align="center">
		<div class="h2-container">
			<span class="subhead-left">
				<h2>Print Forms</h2>
			</span>
			<span class="subhead-right">
				<kul:help businessObjectClassName="fillMeIn" altText="help" />
			</span>
		</div>
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
		                	<html:radio property="selectedBudgetPrintFormId" value="${KualiForm.document.budgetPrintForms[status.index].budgetReportId}"/>			                	
		                	</div>
		                </td>			       
		            </tr>    	
		    	</c:forEach>		    	
				<tr>
					<td colspan="3" class="infoline">
						<div align="center">
						<html:image property="methodToCall.printBudgetForm"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-printsel.gif' />
						</div>
					</td>
				</tr>			                         
			</tbody>
			<tbody id="G" style="display: none;" />
		</table>
	</div>
</kul:tabTop>