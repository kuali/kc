<%--
 Copyright 2006-2008 The Kuali Foundation
 
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

<c:set var="institutionalProposalAttributes" value="${DataDictionary.InstitutionalProposal.attributes}" />
<c:set var="action" value="institutionalProposalHome" />

<kul:tab tabTitle="Sponsor & Program Information" defaultOpen="false" tabErrorKey="document.institutionalProposal.noticeOfOpportunityCode,document.institutionalProposal.programAnnouncementNumber,document.institutionalProposal.sponsorProposalNumber,document.institutionalProposal.nsfCode,document.institutionalProposal.cfdaNumber,document.institutionalProposal.sponsorCode,document.institutionalProposalList[0].sponsorCode,document.institutionalProposal.primeSponsorCode" auditCluster="sponsorProgramInformationAuditErrors,sponsorProgramInformationAuditWarnings" tabAuditKey="document.institutionalProposal.cfdaNumber,document.institutionalProposal.sponsorProposalNumber" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Sponsor & Program Information</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.Sponsor" altText="help"/></span>
        </h3>

        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.sponsorCode}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.institutionalProposalList[0].sponsorCode" attributeEntry="${institutionalProposalAttributes.sponsorCode}" onblur="loadSponsorName('document.institutionalProposalList[0].sponsorCode', 'sponsorName');" />
                	<kul:lookup boClassName="org.kuali.kra.bo.Sponsor" fieldConversions="sponsorCode:document.institutionalProposalList[0].sponsorCode,sponsorName:document.institutionalProposalList[0].sponsor.sponsorName" anchor="${tabKey}"/>
                	<kul:directInquiry boClassName="org.kuali.kra.bo.Sponsor" inquiryParameters="document.institutionalProposalList[0].sponsorCode:sponsorCode" anchor="${tabKey}"/>
                	<br />
                	<div id="sponsorName.div" class="fineprint">
                		${KualiForm.document.institutionalProposalList[0].sponsor.sponsorName}&nbsp;
					</div>
                </td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.sponsorProposalNumber}" /></div></th>
                <td>
                	<kra:kraControlAttribute property="document.institutionalProposal.sponsorProposalNumber" readOnly="${readOnly}" attributeEntry="${institutionalProposalAttributes.sponsorProposalNumber}" />
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.primeSponsorCode}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.institutionalProposal.primeSponsorCode" attributeEntry="${institutionalProposalAttributes.primeSponsorCode}" onblur="loadSponsorName('document.institutionalProposal.primeSponsorCode', 'primeSponsorName');" />
                	<kul:lookup boClassName="org.kuali.kra.bo.Sponsor" fieldConversions="sponsorCode:document.institutionalProposal.primeSponsorCode" anchor="${tabKey}"/>
                	<kul:directInquiry boClassName="org.kuali.kra.bo.Sponsor" inquiryParameters="document.institutionalProposal.sponsorCode:primeSponsorCode" anchor="${tabKey}"/>
                	<br />
                	<div id="primeSponsorName.div" class="fineprint">
                		${KualiForm.document.institutionalProposal.primeSponsor.sponsorName}&nbsp;
					</div>
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.nsfCode}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.institutionalProposal.nsfCode" attributeEntry="${institutionalProposalAttributes.nsfCode}" />
           		</td>
        	</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.noticeOfOpportunityCode}" /></div></th>
                <td>
                	<kra:kraControlAttribute property="document.institutionalProposal.noticeOfOpportunityCode" readOnly="${readOnly}" attributeEntry="${institutionalProposalAttributes.noticeOfOpportunityCode}"  styleClass="fixed-size-200-select" />
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.awardTypeCode}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.institutionalProposal.awardTypeCode" attributeEntry="${institutionalProposalAttributes.awardTypeCode}" />
           		</td>
        	</tr>
     		<tr>
     		    <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.subcontractFlag}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.institutionalProposal.subcontractFlag" attributeEntry="${institutionalProposalAttributes.subcontractFlag}" readOnly="${readOnly}" />
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.opportunity}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.institutionalProposal.opportunity" attributeEntry="${institutionalProposalAttributes.opportunity}" />
           		</td>
     		</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.cfdaNumber}" /></div></th>
                <td>
                	<kra:kraControlAttribute property="document.institutionalProposal.cfdaNumber" readOnly="${readOnly}" attributeEntry="${institutionalProposalAttributes.cfdaNumber}" styleClass="fixed-size-200-select"/>
                </td>
           		<th><div align="right">&nbsp;</div></th>
           		<td>
           			&nbsp;
           		</td>
     		</tr>
        </table>
    </div>
</kul:tab>
