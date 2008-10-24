<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="s2sSubmissionHistoryAttributes" value="${DataDictionary.S2sSubmissionHistory.attributes}" />
<c:set var="textAreaFieldName" value="document.programAnnouncementTitle" />
<c:set var="action" value="proposalDevelopmentGrantsGov" />

<kul:innerTab parentTab="Opportunity Search" defaultOpen="false" tabTitle="Submission History">
<div class="innerTab-container" align="left">
 <table class=tab cellpadding=0 cellspacing="0" summary=""> 
 <tbody id="G1">
 		<c:choose>
    	<c:when test="${not empty KualiForm.document.s2sSubmissionHistory}" >
    	<tr>
    		<th class="infoline"></th>
	    	<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${s2sSubmissionHistoryAttributes.submissionTime}" noColon="true" /></div></th>
			<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${s2sSubmissionHistoryAttributes.submittedBy}" noColon="true" /></div></th>
			<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${s2sSubmissionHistoryAttributes.s2sSubmissionTypeCode}" noColon="true" /></div></th>
			<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${s2sSubmissionHistoryAttributes.s2sRevisionTypeCode}" noColon="true" /></div></th>
			<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${s2sSubmissionHistoryAttributes.federalIdentifier}" noColon="true" /></div></th>
			<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${s2sSubmissionHistoryAttributes.originalProposalId}" noColon="true" /></div></th>
    	</tr>
    	
    	<c:forEach var="form" items="${KualiForm.document.s2sSubmissionHistory}" varStatus="status">
	             <tr>
	             	<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>	                	
	                <td align="left" valign="middle">	                		                	
	                	 <fmt:formatDate value="${KualiForm.document.s2sSubmissionHistory[status.index].submissionTime}" type="both" dateStyle="short" timeStyle="short"/>
					</td>	                
	                <td>
	                	<div align="center">
	                	<kul:htmlControlAttribute property="document.s2sSubmissionHistory[${status.index}].submittedBy" attributeEntry="${s2sSubmissionHistoryAttributes.submittedBy}" readOnly="true"/>
	                	</div>
	                </td>
	                <td>
	                	<div align="center">
	                	<kul:htmlControlAttribute property="document.s2sSubmissionHistory[${status.index}].s2sSubmissionType.description" attributeEntry="${s2sSubmissionHistoryAttributes.s2sSubmissionTypeCode}" readOnly="true"/>
	                	</div>
	                </td>
	                <td>
	                	<div align="center">
	                	<kul:htmlControlAttribute property="document.s2sSubmissionHistory[${status.index}].s2sRevisionType.description" attributeEntry="${s2sSubmissionHistoryAttributes.s2sRevisionTypeCode}" readOnly="true"/>
	                	</div>
	                </td>
	                <td>
	                	<div align="center">
	                	<kul:htmlControlAttribute property="document.s2sSubmissionHistory[${status.index}].federalIdentifier" attributeEntry="${s2sSubmissionHistoryAttributes.federalIdentifier}" readOnly="true"/>
	                	</div>
	                </td>
	                <td>
	                	<div align="center">
	                	<kul:htmlControlAttribute property="document.s2sSubmissionHistory[${status.index}].originalProposalId" attributeEntry="${s2sSubmissionHistoryAttributes.originalProposalId}" readOnly="true"/>
	                	</div>
	                </td>
	            </tr>    	
    	</c:forEach>    		
    	</c:when>   
		</c:choose>		       
	   </tbody>
</table></div>    
</kul:innerTab>