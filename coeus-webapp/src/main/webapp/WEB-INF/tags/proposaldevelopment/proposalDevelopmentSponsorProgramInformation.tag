<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="textAreaFieldName" value="document.developmentProposalList[0].programAnnouncementTitle" />
<c:set var="action" value="proposalDevelopmentProposal" />

<kul:tab tabTitle="Sponsor & Program Information" defaultOpen="false" 
	tabErrorKey="document.developmentProposalList[0].primeSponsorCode,document.developmentProposalList[0].deadlineDate,document.developmentProposalList[0].deadlineTime,document.developmentProposalList[0].noticeOfOpportunityCode,document.developmentProposalList[0].deadlineType,document.developmentProposalList[0].cfdaNumber,document.developmentProposalList[0].programAnnouncementNumber,document.developmentProposalList[0].primeSponsorCode,document.developmentProposalList[0].sponsorProposalNumber,document.developmentProposalList[0].nsfCode,document.developmentProposalList[0].subcontracts,document.developmentProposalList[0].agencyDivisionCode,document.developmentProposalList[0].agencyProgramCode,document.developmentProposalList[0].programAnnouncementTitle,document.developmentProposalList[0].primeSponsorCode" 
	auditCluster="sponsorProgramInformationAuditErrors,sponsorProgramInformationAuditWarnings" 
	tabAuditKey="document.developmentProposalList[0].deadlineDate,document.developmentProposalList[0].programAnnouncementNumber,document.developmentProposalList[0].cfdaNumber,document.developmentProposalList[0].programAnnouncementTitle,document.developmentProposalList[0].sponsorProposalNumber,document.developmentProposalList[0].primeSponsorCode" 
	useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Sponsor &amp; Program Information</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.coeus.common.framework.sponsor.Sponsor" altText="help"/></span>
        </h3>

        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.deadlineDate}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].deadlineDate" attributeEntry="${proposalDevelopmentAttributes.deadlineDate}"  />
                </td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.deadlineTime}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].deadlineTime" attributeEntry="${proposalDevelopmentAttributes.deadlineTime}"  />
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.deadlineType}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].deadlineType" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.deadlineType}"  styleClass="fixed-size-200-select"/>
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.noticeOfOpportunityCode}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].noticeOfOpportunityCode" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.noticeOfOpportunityCode}"  styleClass="fixed-size-200-select" />
                </td>
        	</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${DataDictionary.Sponsor.attributes.sponsorName}" /></div></th>
           		<td>
                	<div id="sponsorName.div">
                		${KualiForm.document.developmentProposalList[0].sponsor.sponsorName}&nbsp;
					</div>
           		</td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.cfdaNumber}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.developmentProposalList[0].cfdaNumber" attributeEntry="${proposalDevelopmentAttributes.cfdaNumber}" />
           		</td>
        	</tr>
     		<tr>
     		    <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.primeSponsorCode}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].primeSponsorCode" attributeEntry="${proposalDevelopmentAttributes.primeSponsorCode}"  onblur="loadSponsorName('document.developmentProposalList[0].primeSponsorCode', 'primeSponsorName');" />
                	<kul:lookup boClassName="org.kuali.coeus.common.framework.sponsor.Sponsor" fieldConversions="sponsorCode:document.developmentProposalList[0].primeSponsorCode,sponsorName:primeSponsorName" anchor="${tabKey}"/>
                	<kul:directInquiry boClassName="org.kuali.coeus.common.framework.sponsor.Sponsor" inquiryParameters="document.developmentProposalList[0].primeSponsorCode:sponsorCode" anchor="${tabKey}"/>
                	<br />
                	<div id="primeSponsorName.div" class="fineprint">
                		${KualiForm.primeSponsorName}&nbsp;
					</div>
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.programAnnouncementNumber}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.developmentProposalList[0].programAnnouncementNumber" attributeEntry="${proposalDevelopmentAttributes.programAnnouncementNumber}" />
           		</td>
     		</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.nsfCode}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].nsfCode" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.nsfCode}" styleClass="fixed-size-200-select"/>
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.sponsorProposalNumber}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.developmentProposalList[0].sponsorProposalNumber" attributeEntry="${proposalDevelopmentAttributes.sponsorProposalNumber}" />
           		</td>
           		
     		</tr>`
        	<tr>				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.agencyDivisionCode}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].agencyDivisionCode" attributeEntry="${proposalDevelopmentAttributes.agencyDivisionCode}" />
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.subcontracts}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.developmentProposalList[0].subcontracts" attributeEntry="${proposalDevelopmentAttributes.subcontracts}" />
           		</td>
     		</tr>

     		<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.anticipatedAwardTypeCode}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].anticipatedAwardTypeCode" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.anticipatedAwardTypeCode}" styleClass="fixed-size-200-select"/>
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.agencyProgramCode}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.developmentProposalList[0].agencyProgramCode" attributeEntry="${proposalDevelopmentAttributes.agencyProgramCode}" />
           		</td>
     		</tr>
     		
     		<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.agencyRoutingIdentifier}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].agencyRoutingIdentifier" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.agencyRoutingIdentifier}" styleClass="fixed-size-200-select"/>
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.prevGrantsGovTrackingID}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.developmentProposalList[0].prevGrantsGovTrackingID" attributeEntry="${proposalDevelopmentAttributes.prevGrantsGovTrackingID}" />
           		</td>
     		</tr>
     		</tr>


            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.programAnnouncementTitle}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].programAnnouncementTitle" attributeEntry="${proposalDevelopmentAttributes.programAnnouncementTitle}" />
                </td>
            </tr>
            
            
            
            
        </table>
    </div>
</kul:tab>
