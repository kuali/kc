<%--
 Copyright 2005-2013 The Kuali Foundation
 
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
<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}" scope="request" />
<c:set var="canViewCfdaLookup" value="${KualiForm.cfdaLookupRequired}" scope="request" />

<kul:tab tabTitle="Sponsor & Program Information" defaultOpen="false" 
	tabErrorKey="document.institutionalProposal.noticeOfOpportunityCode,document.institutionalProposal.programAnnouncementNumber,document.institutionalProposal.sponsorProposalNumber,document.institutionalProposal.nsfCode,document.institutionalProposal.cfdaNumber,document.institutionalProposal.sponsorCode,,document.institutionalProposalList[0].sponsorCode,document.institutionalProposal.deadlineTime, document.institutionalProposalList[0].deadlineTime,document.institutionalProposal.primeSponsorCode,document.institutionalProposalList[0].opportunity,document.institutionalProposalList[0].primeSponsorCode" 
	auditCluster="sponsorProgramInformationAuditErrors,sponsorProgramInformationAuditWarnings" 
	tabAuditKey="document.institutionalProposal.cfdaNumber,document.institutionalProposal.sponsorProposalNumber" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Sponsor &amp; Program Information</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.Sponsor" altText="help"/></span>
        </h3>

        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.sponsorCode}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.institutionalProposalList[0].sponsorCode" attributeEntry="${institutionalProposalAttributes.sponsorCode}" onblur="loadSponsorName('document.institutionalProposalList[0].sponsorCode', 'sponsorName');" />
                	<c:if test="${!readOnly}">
                	   <kul:lookup boClassName="org.kuali.kra.bo.Sponsor" fieldConversions="sponsorCode:document.institutionalProposalList[0].sponsorCode,sponsorName:document.institutionalProposalList[0].sponsor.sponsorName" anchor="${tabKey}"/>
                	</c:if>
                	<kul:directInquiry boClassName="org.kuali.kra.bo.Sponsor" inquiryParameters="document.institutionalProposalList[0].sponsorCode:sponsorCode" anchor="${tabKey}"/>
                	<br />
                	<div id="sponsorName.div" class="fineprint">
                		${KualiForm.document.institutionalProposalList[0].sponsor.sponsorName}&nbsp;
					</div>
                </td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.sponsorProposalNumber}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.institutionalProposal.sponsorProposalNumber" readOnly="${readOnly}" attributeEntry="${institutionalProposalAttributes.sponsorProposalNumber}" />
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.primeSponsorCode}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.institutionalProposalList[0].primeSponsorCode" attributeEntry="${institutionalProposalAttributes.primeSponsorCode}" onblur="loadSponsorName('document.institutionalProposalList[0].primeSponsorCode', 'primeSponsorName');" />
                	<c:if test="${!readOnly}">
                	   <kul:lookup boClassName="org.kuali.kra.bo.Sponsor" fieldConversions="sponsorCode:document.institutionalProposalList[0].primeSponsorCode,sponsorName:document.institutionalProposalList[0].primeSponsor.sponsorName" anchor="${tabKey}"/>
                	</c:if>
                	<kul:directInquiry boClassName="org.kuali.kra.bo.Sponsor" inquiryParameters="document.institutionalProposalList[0].primeSponsorCode:sponsorCode" anchor="${tabKey}"/>
                	<br />
                	<div id="primeSponsorName.div" class="fineprint">
                		${KualiForm.document.institutionalProposal.primeSponsor.sponsorName}&nbsp;
					</div>
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.nsfCode}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.institutionalProposal.nsfCode" readOnly="${readOnly}" attributeEntry="${institutionalProposalAttributes.nsfCode}" styleClass="fixed-size-200-select" />
           		</td>
        	</tr>
        	<tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.deadlineDate}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.institutionalProposal.deadlineDate" attributeEntry="${institutionalProposalAttributes.deadlineDate}"  />
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.deadlineTime}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.institutionalProposal.deadlineTime" attributeEntry="${institutionalProposalAttributes.deadlineTime}"  />
                </td>
            </tr>
        	<tr>
        		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.deadlineType}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.institutionalProposal.deadlineType" readOnly="${readOnly}" attributeEntry="${institutionalProposalAttributes.deadlineType}"  styleClass="fixed-size-200-select"/>
                </td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.noticeOfOpportunityCode}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.institutionalProposal.noticeOfOpportunityCode" readOnly="${readOnly}" attributeEntry="${institutionalProposalAttributes.noticeOfOpportunityCode}"  styleClass="fixed-size-200-select" />
                </td>
        	</tr>
     		<tr>
     		    <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.subcontractFlag}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.institutionalProposal.subcontractFlag" attributeEntry="${institutionalProposalAttributes.subcontractFlag}" readOnly="${readOnly}" />
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.awardTypeCode}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.institutionalProposal.awardTypeCode" attributeEntry="${institutionalProposalAttributes.awardTypeCode}" />
           		</td>
     		</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.cfdaNumber}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.institutionalProposal.cfdaNumber" readOnly="${readOnly}" attributeEntry="${institutionalProposalAttributes.cfdaNumber}" styleClass="fixed-size-200-select"/>
            	<c:if test="${canViewCfdaLookup}"> 
            		<c:if test="${!readOnly}">
    					<kul:lookup boClassName="org.kuali.kra.award.home.CFDA" fieldConversions="cfdaNumber:document.institutionalProposal.cfdaNumber" anchor="${tabKey}" />
    				</c:if>
    				<c:if test="${!readOnly or !empty KualiForm.document.institutionalProposalList[0].cfdaNumber}">
    					<kul:directInquiry boClassName="org.kuali.kra.award.home.CFDA" inquiryParameters="document.institutionalProposal.cfdaNumber:cfdaNumber" anchor="${tabKey}" />
    				</c:if>
    			</c:if>
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.opportunity}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.institutionalProposalList[0].opportunity" attributeEntry="${institutionalProposalAttributes.opportunity}" />
           		</td>
     		</tr>
        </table>
    </div>
</kul:tab>
