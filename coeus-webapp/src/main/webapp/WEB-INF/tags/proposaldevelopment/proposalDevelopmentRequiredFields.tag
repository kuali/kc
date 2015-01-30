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
<%@ attribute name="transparent" required="false" %>


<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="textAreaFieldName" value="document.developmentProposalList[0].title" />
<c:set var="action" value="proposalDevelopmentProposal" />
<c:set var="className" value="org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument" />

<kul:tab tabTitle="Required Fields for Saving Document" defaultOpen="true" transparentBackground="${transparent}" 
	tabErrorKey="document.developmentProposalList[0].sponsorCode,document.developmentProposalList[0].currentAwardNumber*,document.developmentProposalList[0].continuedFrom,document.developmentProposalList[0].sponsorCode*,document.developmentProposalList[0].proposalTypeCode*,document.developmentProposalList[0].requestedStartDateInitial*,document.developmentProposalList[0].ownedByUnit*,document.developmentProposalList[0].requestedEndDateInitial*,document.developmentProposalList[0].activityTypeCode*,document.developmentProposalList[0].title" 
	auditCluster="requiredFieldsAuditErrors" tabAuditKey="document.developmentProposalList[0].title,document.developmentProposalList[0].continuedFrom" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Required Fields for Saving Document</span>
    		<span class="subhead-right"><kul:help parameterNamespace="KC-PD" parameterDetailType="Document" parameterName="requiredFieldForSavingDocumentHelpUrl" altText="help"/></span>
        </h3>
        
        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.proposalNumber}" /></div></th>
                <td>${KualiForm.document.developmentProposalList[0].proposalNumber}&nbsp;</td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.sponsorCode}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].sponsorCode" attributeEntry="${proposalDevelopmentAttributes.sponsorCode}" onblur="loadSponsorName('document.developmentProposalList[0].sponsorCode', 'sponsorName'); checkGrantsGovStatusOnSponsorChange('${KualiForm.document.developmentProposal.proposalNumber}', 'document.developmentProposalList[0].sponsorCode');" />
                	<kul:lookup boClassName="org.kuali.coeus.common.framework.sponsor.Sponsor" fieldConversions="sponsorCode:document.developmentProposalList[0].sponsorCode,sponsorName:document.developmentProposalList[0].sponsor.sponsorName" anchor="${tabKey}" />
                    <kul:directInquiry boClassName="org.kuali.coeus.common.framework.sponsor.Sponsor" inquiryParameters="document.developmentProposalList[0].sponsorCode:sponsorCode" anchor="${tabKey}" />
                    <%-- register the grants gov header tab in case it has been disabled on load, as it may be enabled via javascript --%>
                    ${kfunc:registerEditableProperty(KualiForm, 'methodToCall.headerTab.headerDispatch.save.navigateTo.grantsGov')}  
                    <div id="sponsorName.div" >
                        <c:if test="${!empty KualiForm.document.developmentProposalList[0].sponsorCode}">
            				<c:choose>
							    <c:when test="${empty KualiForm.document.developmentProposalList[0].sponsor}">
	                    			<span style='color: red;'>not found</span>
	               				</c:when>
	                  			<c:otherwise>
										<c:out value="${KualiForm.document.developmentProposalList[0].sponsor.sponsorName}" />
								</c:otherwise>  
							</c:choose>                        
                        </c:if>
					</div>
				</td>
            </tr>
            <tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.proposalTypeCode}" /></div></th>
                <td>
                      <kul:htmlControlAttribute property="document.developmentProposalList[0].proposalTypeCode" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.proposalTypeCode}" />
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.requestedStartDateInitial}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].requestedStartDateInitial" attributeEntry="${proposalDevelopmentAttributes.requestedStartDateInitial}"  />
                </td>
            </tr>
            <tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.ownedByUnitNumber}" /></div></th>
                <td align="left" valign="middle">
                  <c:choose>
                    <c:when test="${empty KualiForm.document.developmentProposalList[0].ownedByUnit or empty KualiForm.document.developmentProposalList[0].ownedByUnit.unitNumber}">
                    	<kul:htmlControlAttribute property="document.developmentProposalList[0].ownedByUnitNumber" attributeEntry="${proposalDevelopmentAttributes.ownedByUnitNumberRestricted}" />
                    </c:when>
                    <c:otherwise>
                      ${KualiForm.document.developmentProposalList[0].ownedByUnit.unitNumber} - ${KualiForm.document.developmentProposalList[0].ownedByUnit.unitName}
                    </c:otherwise>
                  </c:choose>
				</td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.requestedEndDateInitial}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].requestedEndDateInitial" attributeEntry="${proposalDevelopmentAttributes.requestedEndDateInitial}"  />
                </td>
            </tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.activityTypeCode}" /></div></th>
                <td><kul:htmlControlAttribute property="document.developmentProposalList[0].activityTypeCode" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.activityTypeCode}" /></td>
				<th>&nbsp;</th>
                <td align="left" valign="middle">&nbsp;</td>
            </tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.title}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].title" attributeEntry="${proposalDevelopmentAttributes.title}" />
                </td>
            </tr>
        </table>

         <br>
            <h3>
              <span class="subhead-left">Institutional Fields Conditionally Required</span>
        		<span class="subhead-right"><kul:help parameterNamespace="KC-PD" parameterDetailType="Document" parameterName="institutionalFieldsConditionallyRequiredHelpUrl" altText="help"/></span>
            </h3>
            
            <table summary="" cellpadding="0" cellspacing="0">
              <tbody><tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.currentAwardNumber}" /></div></th>
                <td align="left" valign="middle"><kul:htmlControlAttribute property="document.developmentProposalList[0].currentAwardNumber" attributeEntry="${proposalDevelopmentAttributes.currentAwardNumber}" />
                                	<kul:lookup boClassName="org.kuali.kra.award.home.Award" fieldConversions="awardNumber:document.developmentProposalList[0].currentAwardNumber" anchor="${tabKey}" />
                </td>
              </tr>
              <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.continuedFrom}" /></div></th>
                <td align="left" valign="middle"><kul:htmlControlAttribute property="document.developmentProposalList[0].continuedFrom" attributeEntry="${proposalDevelopmentAttributes.continuedFrom}" />
                <kul:lookup boClassName="org.kuali.kra.institutionalproposal.home.InstitutionalProposal" fieldConversions="proposalNumber:document.developmentProposalList[0].continuedFrom" anchor="${tabKey}" />
				</td>
              </tr>
            </tbody></table>
    </div>
</kul:tab>
