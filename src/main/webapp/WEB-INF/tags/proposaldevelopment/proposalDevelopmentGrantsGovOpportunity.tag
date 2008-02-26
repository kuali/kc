<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="s2sOpportunity" value="${DataDictionary.S2sOpportunity.attributes}" />
<c:set var="textAreaFieldName" value="document.opportunityTitle" />
<c:set var="action" value="proposalDevelopmentProposal" />


<kul:tab tabTitle="Opportunity" defaultOpen="false" tabErrorKey="document.s2sOpportunity.opportunityId,document.s2sOpportunity.opportunityTitle,document.s2sOpportunity.s2sSubmissionTypeCode,document.s2sOpportunity.revisionCode,document.s2sOpportunity.cfdaNumber,document.s2sOpportunity.competetionId,document.s2sOpportunity.openingDate,document.s2sOpportunity.closingDate,document.s2sOpportunity.instructionUrl,document.s2sOpportunity.schemaUrl,document.s2sOpportunity.revisionOtherDescription" auditCluster="grantsGovAuditWarnings" tabAuditKey="document.s2sOpportunity.submissionTypeCode">
<div class="tab-container" align="left">
 <table class=tab cellpadding=0 cellspacing="0" summary=""> 
 <tbody id="G1">	
	<tr>
		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${s2sOpportunity.opportunityId}" /></div></th>
		<td><kul:htmlControlAttribute property="document.s2sOpportunity.opportunityId" attributeEntry="${s2sOpportunity.opportunityId}" /></td>
	</tr>
	<tr>
		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${s2sOpportunity.opportunityTitle}" /></div></th>
		<td><kul:htmlControlAttribute property="document.s2sOpportunity.opportunityTitle" attributeEntry="${s2sOpportunity.opportunityTitle}" /></td>
	</tr>
	
	<tr>
		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${s2sOpportunity.s2sSubmissionTypeCode}" /></div></th>		
		<td><kul:htmlControlAttribute property="document.s2sOpportunity.s2sSubmissionTypeCode" attributeEntry="${s2sOpportunity.s2sSubmissionTypeCode}" /></td>
	</tr>	   	

	<tr>				
		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${s2sOpportunity.revisionCode}" /></div></th>
		<td>
			<kul:htmlControlAttribute property="document.s2sOpportunity.revisionCode" attributeEntry="${s2sOpportunity.revisionCode}" />
			<kul:htmlControlAttribute property="document.s2sOpportunity.revisionOtherDescription" attributeEntry="${s2sOpportunity.revisionOtherDescription}" />			
		</td>
	</tr>
	
	<tr>
		<th ><div align="left"><kul:htmlAttributeLabel attributeEntry="${s2sOpportunity.cfdaNumber}" /></div></th>
        <td><kul:htmlControlAttribute property="document.s2sOpportunity.cfdaNumber" attributeEntry="${s2sOpportunity.cfdaNumber}" /></td>
        <!-- <td align="left" valign="middle"><kul:htmlControlAttribute property="document.currentAwardNumber" attributeEntry="${proposalDevelopmentAttributes.currentAwardNumber}" /></td>  --> 
	</tr>
	
	<tr>
		<th ><div align="left"><kul:htmlAttributeLabel attributeEntry="${s2sOpportunity.competetionId}" /></div></th>
        <td><kul:htmlControlAttribute property="document.s2sOpportunity.competetionId" attributeEntry="${s2sOpportunity.competetionId}" /></td>     
	</tr>
	
	<tr>
		<th ><div align="left"><kul:htmlAttributeLabel attributeEntry="${s2sOpportunity.openingDate}" /></div></th>
        <td><kul:htmlControlAttribute property="document.s2sOpportunity.openingDate" attributeEntry="${s2sOpportunity.openingDate}" datePicker="true" /></td>     
	</tr>
	
	<tr>
		<th ><div align="left"><kul:htmlAttributeLabel attributeEntry="${s2sOpportunity.closingDate}" /></div></th>
        <td><kul:htmlControlAttribute property="document.s2sOpportunity.closingDate" attributeEntry="${s2sOpportunity.closingDate}" datePicker="true" /></td>     
	</tr>
	
	<tr>
		<th ><div align="left"><kul:htmlAttributeLabel attributeEntry="${s2sOpportunity.instructionUrl}" /></div></th>
        <td><kul:htmlControlAttribute property="document.s2sOpportunity.instructionUrl" attributeEntry="${s2sOpportunity.instructionUrl}" /></td>     
	</tr>
	
	<tr>
		<th ><div align="left"><kul:htmlAttributeLabel attributeEntry="${s2sOpportunity.schemaUrl}" /></div></th>
        <td><kul:htmlControlAttribute property="document.s2sOpportunity.schemaUrl" attributeEntry="${s2sOpportunity.schemaUrl}" /></td>     
	</tr>   	
	   </tbody>
</table></div>			
</kul:tab>


