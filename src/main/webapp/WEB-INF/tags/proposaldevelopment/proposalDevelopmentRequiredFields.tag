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
<c:set var="textAreaFieldName" value="document.title" />
<c:set var="action" value="proposalDevelopmentProposal" />
<c:set var="className" value="org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument" />

<kul:tab tabTitle="Required Fields for Saving Document" defaultOpen="true" tabErrorKey="document.currentAwardNumber*,document.continuedFrom,document.sponsorCode*,document.proposalTypeCode*,document.requestedStartDateInitial*,document.ownedByUnit*,document.requestedEndDateInitial*,document.activityTypeCode*,document.title" auditCluster="requiredFieldsAuditErrors" tabAuditKey="document.title" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Required Fields for Saving Document</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.proposaldevelopment.bo.ProposalType" altText="help"/></span>
        </h3>
        
        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.proposalNumber}" /></div></th>
                <td>${KualiForm.document.proposalNumber}&nbsp;</td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.sponsorCode}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.sponsorCode" attributeEntry="${proposalDevelopmentAttributes.sponsorCode}" onblur="loadSponsorName('document.sponsorCode', 'sponsorName');" />
                	<kul:lookup boClassName="org.kuali.kra.bo.Sponsor" fieldConversions="sponsorCode:document.sponsorCode,sponsorName:document.sponsor.sponsorName" anchor="${tabKey}" />
                    <kul:directInquiry boClassName="org.kuali.kra.bo.Sponsor" inquiryParameters="document.sponsorCode:sponsorCode" anchor="${tabKey}" />
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
                      <kra:kraControlAttribute property="document.proposalTypeCode" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.proposalTypeCode}" />
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
                <td><kra:kraControlAttribute property="document.activityTypeCode" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.activityTypeCode}" /></td>
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

         <br>
            <h3>
              <span class="subhead-left">Institutional Fields Conditionally Required</span>
              <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.proposaldevelopment.bo.ProposalType" altText="help"/></span>
            </h3>
            
            <table summary="" cellpadding="0" cellspacing="0">
              <tbody><tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.currentAwardNumber}" /></div></th>
                <td align="left" valign="middle"><kul:htmlControlAttribute property="document.currentAwardNumber" attributeEntry="${proposalDevelopmentAttributes.currentAwardNumber}" /></td>
              </tr>
              <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.continuedFrom}" /></div></th>
                <td align="left" valign="middle"><kul:htmlControlAttribute property="document.continuedFrom" attributeEntry="${proposalDevelopmentAttributes.continuedFrom}" /></td>
              </tr>
            </tbody></table>
    </div>
</kul:tab>
