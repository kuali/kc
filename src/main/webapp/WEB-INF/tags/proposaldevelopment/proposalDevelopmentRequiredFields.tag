<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="textAreaFieldName" value="document.title" />
<c:set var="action" value="proposalDevelopmentProposal" />
<c:set var="className" value="org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument" />

<kul:tab tabTitle="Required Fields for Saving Document" defaultOpen="true" tabErrorKey="document.sponsorCode*,document.proposalTypeCode*,document.requestedStartDateInitial*,document.ownedByUnit*,document.requestedEndDateInitial*,document.activityTypeCode*,document.title">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Required Fields for Saving Document</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>
        
        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
				<th><div align="right">*Proposal #:</div></th>
                <td>${KualiForm.document.proposalNumber}</td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.sponsorCode}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.sponsorCode" attributeEntry="${proposalDevelopmentAttributes.sponsorCode}" onblur="loadSponsorName('document.sponsorCode', 'sponsorName');" />
                	<kul:lookup boClassName="org.kuali.kra.bo.Sponsor" fieldConversions="sponsorCode:document.sponsorCode,sponsorName:document.sponsor.sponsorName" />
                    <div id="sponsorName.div" >
                        <c:if test="${!empty KualiForm.document.sponsorCode}">
                        	<c:choose>
								<c:when test="${empty KualiForm.document.sponsor}">
									<span style='color: red;'>not found</span>
								</c:when>
								<c:otherwise>
									<c:out value="${KualiForm.document.sponsor.sponsorName}" />
								</c:otherwise>
						</c:choose>                        
                        </c:if>
					</div>
				</td>
            </tr>
            <tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.proposalTypeCode}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.proposalTypeCode" attributeEntry="${proposalDevelopmentAttributes.proposalTypeCode}" />
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.requestedStartDateInitial}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.requestedStartDateInitial" attributeEntry="${proposalDevelopmentAttributes.requestedStartDateInitial}" datePicker="true" />
                </td>
            </tr>
            <tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.ownedByUnitNumber}" /></div></th>
                <td align="left" valign="middle">
                  <c:choose>
                    <c:when test="${empty KualiForm.document.ownedByUnit}">
                    	<kul:htmlControlAttribute property="document.ownedByUnitNumber" attributeEntry="${proposalDevelopmentAttributes.ownedByUnitNumber}" />
                    </c:when>
                    <c:otherwise>
                      ${KualiForm.document.ownedByUnit.unitNumber} - ${KualiForm.document.ownedByUnit.unitName}
                    </c:otherwise>
                  </c:choose>
				</td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.requestedEndDateInitial}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.requestedEndDateInitial" attributeEntry="${proposalDevelopmentAttributes.requestedEndDateInitial}" datePicker="true" />
                </td>
            </tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.activityTypeCode}" /></div></th>
                <td><kul:htmlControlAttribute property="document.activityTypeCode" attributeEntry="${proposalDevelopmentAttributes.activityTypeCode}" /></td>
				<th>&nbsp;</th>
                <td align="left" valign="middle">&nbsp;</td>
            </tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.title}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.title" attributeEntry="${proposalDevelopmentAttributes.title}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${proposalDevelopmentAttributes.title.label}" />
                </td>
            </tr>
        </table>
    </div>
</kul:tab>
