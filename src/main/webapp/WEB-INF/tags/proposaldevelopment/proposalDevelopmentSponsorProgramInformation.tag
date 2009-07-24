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

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="textAreaFieldName" value="document.developmentProposalList[0].programAnnouncementTitle" />
<c:set var="action" value="proposalDevelopmentProposal" />

<kul:tab tabTitle="Sponsor & Program Information" defaultOpen="false" tabErrorKey="document.developmentProposalList[0].deadlineDate,document.developmentProposalList[0].noticeOfOpportunityCode,document.developmentProposalList[0].deadlineType,document.developmentProposalList[0].cfdaNumber,document.developmentProposalList[0].programAnnouncementNumber,document.developmentProposalList[0].primeSponsorCode,document.developmentProposalList[0].sponsorProposalNumber,document.developmentProposalList[0].nsfCode,document.developmentProposalList[0].subcontracts,document.developmentProposalList[0].agencyDivisionCode,document.developmentProposalList[0].agencyProgramCode,document.developmentProposalList[0].programAnnouncementTitle" auditCluster="sponsorProgramInformationAuditErrors,sponsorProgramInformationAuditWarnings" tabAuditKey="document.developmentProposalList[0].deadlineDate,document.developmentProposalList[0].programAnnouncementNumber,document.developmentProposalList[0].cfdaNumber,document.developmentProposalList[0].programAnnouncementTitle,document.developmentProposalList[0].sponsorProposalNumber" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Sponsor & Program Information</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.Sponsor" altText="help"/></span>
        </h3>

        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.deadlineDate}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].deadlineDate" attributeEntry="${proposalDevelopmentAttributes.deadlineDate}" datePicker="true" />
                </td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.noticeOfOpportunityCode}" /></div></th>
                <td>
                	<kra:kraControlAttribute property="document.developmentProposalList[0].noticeOfOpportunityCode" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.noticeOfOpportunityCode}"  styleClass="fixed-size-200-select" />
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.deadlineType}" /></div></th>
                <td>
                	<kra:kraControlAttribute property="document.developmentProposalList[0].deadlineType" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.deadlineType}"  styleClass="fixed-size-200-select"/>
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.cfdaNumber}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.developmentProposalList[0].cfdaNumber" attributeEntry="${proposalDevelopmentAttributes.cfdaNumber}" />
           		</td>
        	</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${DataDictionary.Sponsor.attributes.sponsorName}" /></div></th>
           		<td>
                	<div id="sponsorName.div">
                		${KualiForm.document.developmentProposalList[0].sponsor.sponsorName}&nbsp;
					</div>
           		</td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.programAnnouncementNumber}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.developmentProposalList[0].programAnnouncementNumber" attributeEntry="${proposalDevelopmentAttributes.programAnnouncementNumber}" />
           		</td>
        	</tr>
     		<tr>
     		    <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.primeSponsorCode}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].primeSponsorCode" attributeEntry="${proposalDevelopmentAttributes.primeSponsorCode}"  onblur="loadSponsorName('document.developmentProposalList[0].primeSponsorCode', 'primeSponsorName');" />
                	<kul:lookup boClassName="org.kuali.kra.bo.Sponsor" fieldConversions="sponsorCode:document.developmentProposalList[0].primeSponsorCode,sponsorName:primeSponsorName" anchor="${tabKey}"/>
                	<kul:directInquiry boClassName="org.kuali.kra.bo.Sponsor" inquiryParameters="document.developmentProposalList[0].primeSponsorCode:sponsorCode" anchor="${tabKey}"/>
                	<br />
                	<div id="primeSponsorName.div" class="fineprint">
                		${KualiForm.primeSponsorName}&nbsp;
					</div>
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.sponsorProposalNumber}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.developmentProposalList[0].sponsorProposalNumber" attributeEntry="${proposalDevelopmentAttributes.sponsorProposalNumber}" />
           		</td>
     		</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.nsfCode}" /></div></th>
                <td>
                	<kra:kraControlAttribute property="document.developmentProposalList[0].nsfCode" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.nsfCode}" styleClass="fixed-size-200-select"/>
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.subcontracts}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.developmentProposalList[0].subcontracts" attributeEntry="${proposalDevelopmentAttributes.subcontracts}" />
           		</td>
     		</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.agencyDivisionCode}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].agencyDivisionCode" attributeEntry="${proposalDevelopmentAttributes.agencyDivisionCode}" />
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.agencyProgramCode}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.developmentProposalList[0].agencyProgramCode" attributeEntry="${proposalDevelopmentAttributes.agencyProgramCode}" />
           		</td>
     		</tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.programAnnouncementTitle}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].programAnnouncementTitle" attributeEntry="${proposalDevelopmentAttributes.programAnnouncementTitle}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${proposalDevelopmentAttributes.programAnnouncementTitle.label}" />
                </td>
            </tr>
        </table>
    </div>
</kul:tab>
