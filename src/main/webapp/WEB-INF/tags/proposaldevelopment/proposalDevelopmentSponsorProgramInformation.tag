<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="textAreaFieldName" value="document.programAnnouncementTitle" />
<c:set var="action" value="proposalDevelopmentProposal" />

<kul:tab tabTitle="Sponsor & Program Information" defaultOpen="true" tabErrorKey="document.sponsorProgramNumber">
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
                	<kul:htmlControlAttribute property="document.noticeOfOpportunityCode" attributeEntry="${proposalDevelopmentAttributes.noticeOfOpportunityCode}" />
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.deadlineType}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.deadlineType" attributeEntry="${proposalDevelopmentAttributes.deadlineType}" />
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.cfdaNumber}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.cfdaNumber" attributeEntry="${proposalDevelopmentAttributes.cfdaNumber}" />
           		</td>
        	</tr>
        	<tr>
        		<!--
				<th><div align="right">Agency/Sponsor Name:</div></th>
        		 -->
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${DataDictionary.Sponsor.attributes.sponsorName}" /></div></th>
           		<td>
                	<div id="sponsorName.div">
                		<bean:write	name="KualiForm" property="document.sponsor.sponsorName" />&nbsp;
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
                	<br />
                	<div id="primeSponsorName.div" class="fineprint"><bean:write
						name="KualiForm" property="primeSponsorName" />&nbsp;
					</div>
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.sponsorProposalNumber}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.sponsorProposalNumber" attributeEntry="${proposalDevelopmentAttributes.sponsorProposalNumber}" />
           		</td>
     		</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.currentAwardNumber}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.currentAwardNumber" attributeEntry="${proposalDevelopmentAttributes.currentAwardNumber}" />
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.continuedFrom}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.continuedFrom" attributeEntry="${proposalDevelopmentAttributes.continuedFrom}" />
           		</td>
     		</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.nsfCode}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.nsfCode" attributeEntry="${proposalDevelopmentAttributes.nsfCode}" />
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.subcontracts}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.subcontracts" attributeEntry="${proposalDevelopmentAttributes.subcontracts}" /> (yes)
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
                    <html:image property="methodToCall.updateTextArea.((#${textAreaFieldName}:${action}:${proposalDevelopmentAttributes.programAnnouncementTitle.label}#)).anchor${currentTabIndex}" src='${ConfigProperties.kra.externalizable.images.url}pencil_add.png' onclick="javascript: textAreaPop(document.getElementById('${textAreaFieldName}').value,'${textAreaFieldName}','proposalDevelopment','${proposalDevelopmentAttributes.programAnnouncementTitle.label}');return false"/>
                </td>
            </tr>
        </table>
    </div>
</kul:tab>
