<%--
 Copyright 2006-2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="textAreaFieldName" value="document.programAnnouncementTitle" />
<c:set var="action" value="proposalDevelopmentProposal" />

<kul:tab tabTitle="Sponsor & Program Information" defaultOpen="false" tabErrorKey="document.deadlineDate,document.noticeOfOpportunityCode,document.deadlineType,document.cfdaNumber,document.programAnnouncementNumber,document.primeSponsorCode,document.sponsorProposalNumber,document.nsfCode,document.subcontracts,document.agencyDivisionCode,document.agencyProgramCode,document.programAnnouncementTitle" auditCluster="sponsorProgramInformationAuditErrors,sponsorProgramInformationAuditWarnings" tabAuditKey="document.deadlineDate,document.programAnnouncementNumber,document.cfdaNumber,document.programAnnouncementTitle,document.sponsorProposalNumber" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Sponsor & Program Information</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.Sponsor" altText="help"/></span>
        </div>

        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.deadlineDate}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.deadlineDate" attributeEntry="${proposalDevelopmentAttributes.deadlineDate}" datePicker="true" />
                </td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.noticeOfOpportunityCode}" /></div></th>
                <td>
                	<kra:kraControlAttribute property="document.noticeOfOpportunityCode" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.noticeOfOpportunityCode}"  styleClass="fixed-size-200-select" />
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.deadlineType}" /></div></th>
                <td>
                	<kra:kraControlAttribute property="document.deadlineType" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.deadlineType}"  styleClass="fixed-size-200-select"/>
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
                	<kra:kraControlAttribute property="document.nsfCode" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.nsfCode}" styleClass="fixed-size-200-select"/>
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
