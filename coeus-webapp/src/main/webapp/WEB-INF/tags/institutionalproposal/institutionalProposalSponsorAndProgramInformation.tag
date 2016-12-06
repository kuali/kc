<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
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

<c:set var="institutionalProposalAttributes" value="${DataDictionary.InstitutionalProposal.attributes}" />
<c:set var="action" value="institutionalProposalHome" />
<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}" scope="request" />
<c:set var="canViewCfdaLookup" value="${KualiForm.cfdaLookupRequired}" scope="request" />

<kul:tab tabTitle="Sponsor & Program Information" defaultOpen="false" 
	tabErrorKey="document.institutionalProposal.noticeOfOpportunityCode,document.institutionalProposal.programAnnouncementNumber,document.institutionalProposal.sponsorProposalNumber,document.institutionalProposal.nsfSequenceNumber,document.institutionalProposal.cfdaNumber,document.institutionalProposal.sponsorCode,document.institutionalProposalList[0].sponsorCode,document.institutionalProposal.deadlineTime, document.institutionalProposalList[0].deadlineTime,document.institutionalProposal.primeSponsorCode,document.institutionalProposalList[0].opportunity,document.institutionalProposalList[0].primeSponsorCode"
	auditCluster="sponsorProgramInformationAuditErrors,sponsorProgramInformationAuditWarnings" 
	tabAuditKey="document.institutionalProposal.cfdaNumber,document.institutionalProposal.sponsorProposalNumber" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Sponsor &amp; Program Information</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.coeus.common.framework.sponsor.Sponsor" altText="help"/></span>
        </h3>

        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.sponsorCode}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.institutionalProposalList[0].sponsorCode" attributeEntry="${institutionalProposalAttributes.sponsorCode}" onblur="loadSponsorName('document.institutionalProposalList[0].sponsorCode', 'sponsorName');" />
                	<c:if test="${!readOnly}">
                	   <kul:lookup boClassName="org.kuali.coeus.common.framework.sponsor.Sponsor" fieldConversions="sponsorCode:document.institutionalProposalList[0].sponsorCode,sponsorName:document.institutionalProposalList[0].sponsor.sponsorName" anchor="${tabKey}"/>
                	</c:if>
                	<kul:directInquiry boClassName="org.kuali.coeus.common.framework.sponsor.Sponsor" inquiryParameters="document.institutionalProposalList[0].sponsorCode:sponsorCode" anchor="${tabKey}"/>
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
                	   <kul:lookup boClassName="org.kuali.coeus.common.framework.sponsor.Sponsor" fieldConversions="sponsorCode:document.institutionalProposalList[0].primeSponsorCode,sponsorName:document.institutionalProposalList[0].primeSponsor.sponsorName" anchor="${tabKey}"/>
                	</c:if>
                	<kul:directInquiry boClassName="org.kuali.coeus.common.framework.sponsor.Sponsor" inquiryParameters="document.institutionalProposalList[0].primeSponsorCode:sponsorCode" anchor="${tabKey}"/>
                	<br />
                	<div id="primeSponsorName.div" class="fineprint">
                		${KualiForm.document.institutionalProposalList[0].primeSponsor.sponsorName}&nbsp;
					</div>
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.nsfSequenceNumber}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.institutionalProposal.nsfSequenceNumber" readOnly="${readOnly}" attributeEntry="${institutionalProposalAttributes.nsfSequenceNumber}" styleClass="fixed-size-200-select" />
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
