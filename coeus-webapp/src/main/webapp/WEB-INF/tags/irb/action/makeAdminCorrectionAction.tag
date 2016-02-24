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

<c:set var="attributes" value="${DataDictionary.AdminCorrectionBean.attributes}" />
<c:set var="amendmentAttributes" value="${DataDictionary.ProtocolAmendmentBean.attributes}" />
<c:set var="action" value="protocolProtocolActions" />
                                    
<kra:permission value="${KualiForm.actionHelper.canMakeAdminCorrection}">

<kul:innerTab tabTitle="Make Administrative Correction" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.protocolAdminCorrectionBean.*">
	<c:if test="${KualiForm.actionHelper.protocolAdminCorrectionBean.amendmentRenewalOutstanding}">
	<table cellpadding="0" cellspacing="0" summary="">
		<tbody>
			<tr>
				 <th width="30%"> 
                    <div align="right">
						Sections that cannot be corrected due to outstanding amendments
					</div>
				</th>
				<td>
					<table cellpadding="0" cellspacing="0" summary="">
						<tbody>
							<c:if test="${!KualiForm.actionHelper.protocolAdminCorrectionBean.generalInfoEnabled}">
								<tr><td width="100%" style="border-left: 0 none; border-right: 0 none; border-bottom: 0 none;">
							    	<kul:htmlAttributeLabel attributeEntry="${amendmentAttributes.generalInfo}" noColon="true"/> 
								</td></tr>
							</c:if>
							<c:if test="${!KualiForm.actionHelper.protocolAdminCorrectionBean.addModifyAttachmentsEnabled}">
								<tr><td width="100%" style="border-left: 0 none; border-right: 0 none; border-bottom: 0 none;">					
								    <kul:htmlAttributeLabel attributeEntry="${amendmentAttributes.addModifyAttachments}" noColon="true"/>
								</td></tr>		
							</c:if>	
							<c:if test="${!KualiForm.actionHelper.protocolAdminCorrectionBean.fundingSourceEnabled}">
								<tr><td width="100%" style="border-left: 0 none; border-right: 0 none; border-bottom: 0 none;">		                              
								    <kul:htmlAttributeLabel attributeEntry="${amendmentAttributes.fundingSource}" noColon="true"/>
								</td></tr>
							</c:if>
							<c:if test="${!KualiForm.actionHelper.protocolAdminCorrectionBean.areasOfResearchEnabled}">
								<tr><td width="100%" style="border-left: 0 none; border-right: 0 none; border-bottom: 0 none;">
								    <kul:htmlAttributeLabel attributeEntry="${amendmentAttributes.areasOfResearch}" noColon="true"/>
								</td></tr>					
							</c:if>
							<c:if test="${!KualiForm.actionHelper.protocolAdminCorrectionBean.protocolReferencesEnabled}">
								<tr><td width="100%" style="border-left: 0 none; border-right: 0 none; border-bottom: 0 none;">
								    <kul:htmlAttributeLabel attributeEntry="${amendmentAttributes.protocolReferences}" noColon="true"/>
								</td></tr>
							</c:if>
							<c:if test="${!KualiForm.actionHelper.protocolAdminCorrectionBean.specialReviewEnabled}">
								<tr><td width="100%" style="border-left: 0 none; border-right: 0 none; border-bottom: 0 none;">
								    <kul:htmlAttributeLabel attributeEntry="${amendmentAttributes.specialReview}" noColon="true"/>
								</td></tr>
							</c:if>
							<c:if test="${!KualiForm.actionHelper.protocolAdminCorrectionBean.protocolOrganizationsEnabled}">
								<tr><td width="100%" style="border-left: 0 none; border-right: 0 none; border-bottom: 0 none;">
								    <kul:htmlAttributeLabel attributeEntry="${amendmentAttributes.protocolOrganizations}" noColon="true"/>
								</td></tr>
							</c:if>
							<c:if test="${!KualiForm.actionHelper.protocolAdminCorrectionBean.protocolPersonnelEnabled}">
								<tr><td width="100%" style="border-left: 0 none; border-right: 0 none; border-bottom: 0 none;">
								    <kul:htmlAttributeLabel attributeEntry="${amendmentAttributes.protocolPersonnel}" noColon="true"/>
								</td></tr>
							</c:if>
							<c:if test="${!KualiForm.actionHelper.protocolAdminCorrectionBean.subjectsEnabled}">
								<tr><td width="100%" style="border-left: 0 none; border-right: 0 none; border-bottom: 0 none;">
								    <kul:htmlAttributeLabel attributeEntry="${amendmentAttributes.subjects}" noColon="true"/>
								</td></tr>
							</c:if>
							<c:if test="${!KualiForm.actionHelper.protocolAdminCorrectionBean.othersEnabled}">
								<tr><td width="100%" style="border-left: 0 none; border-right: 0 none; border-bottom: 0 none;">
								    <kul:htmlAttributeLabel attributeEntry="${amendmentAttributes.others}" noColon="true"/>
								</td></tr>
							</c:if>
                            <c:if test="${!KualiForm.actionHelper.protocolAdminCorrectionBean.protocolPermissionsEnabled}">
                                <tr><td width="100%" style="border-left: 0 none; border-right: 0 none; border-bottom: 0 none;">
                                    <kul:htmlAttributeLabel attributeEntry="${amendmentAttributes.protocolPermissions}" noColon="true"/>
                                </td></tr>
                            </c:if>
					</tbody>
				</table>
			
			</td></tr>
		</tbody>
	</table>
	</c:if>

    <table class="tab" cellpadding="0" cellspacing="0" summary="">
        <tbody>
            <tr>
                <th width="30%"> 
                    <div align="right">
                        <kul:htmlAttributeLabel attributeEntry="${attributes.comments}" />
                    </div>
                </th>
                <td>
                    <nobr>
                    <kul:htmlControlAttribute property="actionHelper.protocolAdminCorrectionBean.comments" attributeEntry="${attributes.comments}" />
                    </nobr>
                </td>
            </tr>
            <tr>
                <td colspan="2">
					<div align="center" class="globalbuttons">
						<html:image property="methodToCall.openProtocolForAdminCorrection.anchor${tabKey}"
						            src='${ConfigProperties.kr.externalizable.images.url}tinybutton-edit1.gif' styleClass="tinybutton" />
					</div>
                </td>
            </tr>
        </tbody>
    </table>
    
</kul:innerTab>

</kra:permission>
