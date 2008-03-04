<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="textAreaFieldName" value="document.programAnnouncementTitle" />
<c:set var="action" value="proposalDevelopmentProposal" />

<!--  <kul:tab tabTitle="Submission Details" defaultOpen="false">-->
          	<tr>
				<td>
<kul:innerTab parentTab="Opportunity Search" defaultOpen="false" tabTitle="Submission Details">
<div class="innerTab-container" align="left">
 <table class=tab cellpadding=0 cellspacing="0" summary=""> 
 <tbody id="G1">
 <c:choose>	
 	<c:when test ="${empty document.s2sAppSubmission.submissionNumber}">
 		<tr><td>
 			Submission details will be available after the proposal is submitted.
 		</td></tr> 	
 	</c:when>
 	<c:otherwise>
 	 	<tr><td>
 			Application has been submitted to grants.gov
 		</td></tr> 	
 	</c:otherwise>
 </c:choose>
 	   </tbody>
</table></div>
</kul:innerTab>
</td></tr>
<!-- </kul:tab> -->


