<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="s2sFormAttributes" value="${DataDictionary.S2sOppForms.attributes}" />
<c:set var="textAreaFieldName" value="document.programAnnouncementTitle" />
<c:set var="action" value="proposalDevelopmentGrantsGov" />

          	<tr>
				<td>
<kul:innerTab parentTab="Opportunity Search" defaultOpen="false" tabTitle="Forms">
<div class="innerTab-container" align="left">
 <table class=tab cellpadding=0 cellspacing="0" summary=""> 
 <tbody id="G1">
 		<c:choose>
    	<c:when test="${not empty KualiForm.document.s2sOpportunity.s2sOppForms}" >
    	<tr>
	    	<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${s2sFormAttributes.formName}" noColon="true" /></div></th>
			<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${s2sFormAttributes.mandatory}" noColon="true" /></div></th>
			<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${s2sFormAttributes.include}" noColon="true" /></div></th>
			<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${s2sFormAttributes.available}" noColon="true" /></div></th>			
			<th width="150"><div align="center">
			Select to Print:
			<br/>
			(<html:link href="#" onclick="javascript: selectAllGGForms(document);return false">All Included</html:link>
			|
			<html:link href="#" onclick="javascript: unselectAllGGForms(document);return false">None</html:link>)
			</div></th>
    	</tr>
    	
    	<c:forEach var="form" items="${KualiForm.document.s2sOpportunity.s2sOppForms}" varStatus="status">
	             <tr>	                
	                <td align="left" valign="middle">
	                	<kul:htmlControlAttribute property="document.s2sOpportunity.s2sOppForms[${status.index}].formName" attributeEntry="${s2sFormAttributes.formName}" readOnly="true" />
					</td>	                
	                <td>
	                	<div align="center">
	                	<kul:htmlControlAttribute property="document.s2sOpportunity.s2sOppForms[${status.index}].mandatory" attributeEntry="${s2sFormAttributes.mandatory}" readOnly="true"/>
	                	</div>
	                </td>
	                <td>
	                <div align="center">
	                <c:set var="isMandatory" value="${KualiForm.document.s2sOpportunity.s2sOppForms[status.index].mandatory}" /> 
	                <c:choose>
		               	<c:when test="${isMandatory}"> 		                	
		               		<kul:htmlControlAttribute property="document.s2sOpportunity.s2sOppForms[${status.index}].include" attributeEntry="${s2sFormAttributes.include}" readOnly="true"/>
		               	</c:when>
		               	<c:when test="${!KualiForm.document.s2sOpportunity.s2sOppForms[status.index].available}"> 		                	
		               		<kul:htmlControlAttribute property="document.s2sOpportunity.s2sOppForms[${status.index}].include" attributeEntry="${s2sFormAttributes.include}" disabled="true"/>
		               	</c:when>
		               	<c:otherwise>
							<kul:htmlControlAttribute property="document.s2sOpportunity.s2sOppForms[${status.index}].include" attributeEntry="${s2sFormAttributes.include}"/>                		
 		               	</c:otherwise>
	                </c:choose>
	                </td>
	                </div> 
	                <td align="center" valign="middle">
	                	<div align="center">	                		                	
	                	<c:choose>
		                	<c:when test="${KualiForm.document.s2sOpportunity.s2sOppForms[status.index].available}">
		                		<c:out value="Available"/>
		                	</c:when>
		                	<c:otherwise>
								<c:out value="Not Available"/>										                			                		
 		                	</c:otherwise>
	                	</c:choose>
	                			<kul:htmlControlAttribute property="document.s2sOpportunity.s2sOppForms[${status.index}].available" attributeEntry="${s2sFormAttributes.available}"/>
	                	</div>
	                </td>
	                <td align="center" valign="middle">
	                	<div align="center">
	                	<html:checkbox property="document.s2sOpportunity.s2sOppForms[${status.index}].selectToPrint" disabled="${!KualiForm.document.s2sOpportunity.s2sOppForms[status.index].available}"/>	                	
	                	</div>
	                </td>
	                
	            </tr>    	
    	</c:forEach>
   			<tr>
   				<td colspan="5">
   					<div align="right">
   						<html:image src="${ConfigProperties.kra.externalizable.images.url}tinybutton-printsel.gif" styleClass="globalbuttons" property="methodToCall.printForms" alt="Print Selected Forms"/>
   						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   					</div>
   				</td>
   			</tr>
    	</c:when>    	
    	<c:when test="${empty KualiForm.document.s2sOpportunity}" >
    		<tr><td>
			No Grants.gov opportunity has been selected
			</td></tr> 	
		</c:when>
		<c:when test="${empty KualiForm.document.s2sOpportunity.s2sOppForms}" >
			<tr><td>
			No forms are currently available for the Grants.gov opportunity selected.
			</td></tr> 
		</c:when>	
		</c:choose>		       
	   </tbody>
</table></div>    
</kul:innerTab>
</td></tr></table></div>


