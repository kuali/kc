<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="s2sFormAttributes" value="${DataDictionary.S2sOppForms.attributes}" />
<c:set var="textAreaFieldName" value="document.programAnnouncementTitle" />
<c:set var="action" value="proposalDevelopmentProposal" />


<kul:tab tabTitle="Forms" defaultOpen="false">
<div class="innerTab-container" align="left">
 <table class=tab cellpadding=0 cellspacing="0" summary=""> 
 <tbody id="G1">
    	<tr>
	    	<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${s2sFormAttributes.formName}" noColon="true" /></div></th>
			<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${s2sFormAttributes.mandatory}" noColon="true" /></div></th>
			<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${s2sFormAttributes.include}" noColon="true" /></div></th>
			<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${s2sFormAttributes.available}" noColon="true" /></div></th>			
			<th width="150">
			Select to Print:
			<br/>
			<a href="#">All Included</a>
			|
			<a href="#">None</a>
			</th>
    	</tr>
    	
    	<c:forEach var="form" items="${KualiForm.document.s2sOpportunity.s2sOppForms}" varStatus="status">
	             <tr>	                
	                <td align="left" valign="middle">
	                	<kul:htmlControlAttribute property="document.s2sOpportunity.s2sOppForms[${status.index}].formName" attributeEntry="${s2sFormAttributes.formName}" readOnly="true" />
					</td>
	                <td>
	                	<kul:htmlControlAttribute property="document.s2sOpportunity.s2sOppForms[${status.index}].mandatory" attributeEntry="${s2sFormAttributes.mandatory}" readOnly="true"/>
	                </td>
	                <c:set var="isMandatory" value="${KualiForm.document.s2sOpportunity.s2sOppForms[status.index].mandatory}" /> 
	                <c:choose>
		               	<c:when test="${isMandatory}"> 		                	
		               		<td><kul:htmlControlAttribute property="document.s2sOpportunity.s2sOppForms[${status.index}].include" attributeEntry="${s2sFormAttributes.include}" readOnly="true"/></td>
		               	</c:when>
		               	<c:otherwise>
							<td><kul:htmlControlAttribute property="document.s2sOpportunity.s2sOppForms[${status.index}].include" attributeEntry="${s2sFormAttributes.include}"/></td>                		
 		               	</c:otherwise>
	                </c:choose> 
	                <td align="left" valign="middle">
	                	<c:set var="isAvailable" value="${KualiForm.document.s2sOpportunity.s2sOppForms[status.index].available}" />	                	
	                	<c:choose>
		                	<c:when test="${isAvailable}">		                			                	
		                		<kul:htmlControlAttribute property="document.s2sOpportunity.s2sOppForms[${status.index}].available" attributeEntry="${s2sFormAttributes.available}"/>
		                		<c:out value="Available"/>
		                	</c:when>
		                	<c:otherwise>
								<c:out value="Not Available"/>
								<kul:htmlControlAttribute property="document.s2sOpportunity.s2sOppForms[${status.index}].available" attributeEntry="${s2sFormAttributes.available}"/>		                			                		
 		                	</c:otherwise>
	                	</c:choose>
	                </td>
	                <td align="left" valign="middle">
	                	<kul:htmlControlAttribute property="document.s2sOpportunity.s2sOppForms[${status.index}].selectToPrint" attributeEntry="${s2sFormAttributes.selectToPrint}" />
	                </td>
	                
	            </tr>    	
    	</c:forEach>        
	   </tbody>
</table></div>    
</kul:tab>


