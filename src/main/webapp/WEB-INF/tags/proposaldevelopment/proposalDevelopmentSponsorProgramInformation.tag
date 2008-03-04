<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="textAreaFieldName" value="document.programAnnouncementTitle" />
<c:set var="action" value="proposalDevelopmentProposal" />

<kul:tab tabTitle="Sponsor & Program Information" defaultOpen="false" tabErrorKey="document.deadlineDate,document.noticeOfOpportunityCode,document.deadlineType,document.cfdaNumber,document.programAnnouncementNumber,document.primeSponsorCode,document.sponsorProposalNumber,document.nsfCode,document.subcontracts,document.agencyDivisionCode,document.agencyProgramCode,document.programAnnouncementTitle" auditCluster="sponsorProgramInformationAuditErrors,sponsorProgramInformationAuditWarnings" tabAuditKey="document.deadlineDate,document.programAnnouncementNumber,document.cfdaNumber,document.programAnnouncementTitle,document.sponsorProposalNumber" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Sponsor & Program Information</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>

        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.deadlineDate}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.deadlineDate" attributeEntry="${proposalDevelopmentAttributes.deadlineDate}" datePicker="true" />
                </td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.noticeOfOpportunityCode}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.noticeOfOpportunityCode" attributeEntry="${proposalDevelopmentAttributes.noticeOfOpportunityCode}"  styleClass="fixed-size-200-select" />
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.deadlineType}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.deadlineType" attributeEntry="${proposalDevelopmentAttributes.deadlineType}"  styleClass="fixed-size-200-select"/>
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.cfdaNumber}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.cfdaNumber" attributeEntry="${proposalDevelopmentAttributes.cfdaNumber}" />
           		</td>
        	</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${DataDictionary.Sponsor.attributes.sponsorName}" /></div></th>
           		<td>
                	<div id="sponsorName.div">
                		${KualiForm.document.sponsor.sponsorName}&nbsp;
					</div>
           		</td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.programAnnouncementNumber}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.programAnnouncementNumber" attributeEntry="${proposalDevelopmentAttributes.programAnnouncementNumber}" />
           		</td>
        	</tr>
     		<tr>
     		    <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.primeSponsorCode}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.primeSponsorCode" attributeEntry="${proposalDevelopmentAttributes.primeSponsorCode}"  onblur="loadSponsorName('document.primeSponsorCode', 'primeSponsorName');" />
                	<kul:lookup boClassName="org.kuali.kra.bo.Sponsor" fieldConversions="sponsorCode:document.primeSponsorCode,sponsorName:primeSponsorName" anchor="${tabKey}"/>
                	<kul:directInquiry boClassName="org.kuali.kra.bo.Sponsor" inquiryParameters="document.primeSponsorCode:sponsorCode" anchor="${tabKey}"/>
                	<br />
                	<div id="primeSponsorName.div" class="fineprint">
                		${KualiForm.primeSponsorName}&nbsp;
					</div>
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.sponsorProposalNumber}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.sponsorProposalNumber" attributeEntry="${proposalDevelopmentAttributes.sponsorProposalNumber}" />
           		</td>
     		</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.nsfCode}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.nsfCode" attributeEntry="${proposalDevelopmentAttributes.nsfCode}" styleClass="fixed-size-200-select"/>
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.subcontracts}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.subcontracts" attributeEntry="${proposalDevelopmentAttributes.subcontracts}" />
           		</td>
     		</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.agencyDivisionCode}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.agencyDivisionCode" attributeEntry="${proposalDevelopmentAttributes.agencyDivisionCode}" />
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.agencyProgramCode}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.agencyProgramCode" attributeEntry="${proposalDevelopmentAttributes.agencyProgramCode}" />
           		</td>
     		</tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.programAnnouncementTitle}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.programAnnouncementTitle" attributeEntry="${proposalDevelopmentAttributes.programAnnouncementTitle}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${proposalDevelopmentAttributes.programAnnouncementTitle.label}" />
                </td>
            </tr>
        </table>
    </div>
</kul:tab>
