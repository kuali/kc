<%--
 Copyright 2005-2010 The Kuali Foundation

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

<c:set var="attributes" value="${DataDictionary.AdminCorrectionBean.attributes}" />
<c:set var="amendmentAttributes" value="${DataDictionary.ProtocolAmendmentBean.attributes}" />
<c:set var="action" value="protocolProtocolActions" />
<c:set var="textComments" value="actionHelper.protocolAdminCorrectionBean.comments" />
                                    
<kra:permission value="${KualiForm.actionHelper.canMakeAdminCorrection}">

<kra:innerTab tabTitle="Make Administrative Correction" parentTab="" defaultOpen="false" tabErrorKey="*">
    <div style="padding-left: 56px" >
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
                    <td>
						<div align="center" class="globalbuttons">
							<html:image property="methodToCall.openProtocolForAdminCorrection.anchor${tabKey}"
							            src='${ConfigProperties.kr.externalizable.images.url}buttonsmall_ok.gif' styleClass="globalbuttons" />
						</div>
	                </td>
                </tr>
                <tr>
                    <th width="30%"> 
                        <div align="right">
                            <kul:htmlAttributeLabel attributeEntry="${attributes.applyCorrection}" />
                        </div>
                    </th>
                    <td>
                        <nobr>
                        <kul:htmlControlAttribute property="actionHelper.protocolAdminCorrectionBean.applyCorrection" attributeEntry="${attributes.applyCorrection}" />
                        </nobr>
                    </td>
                    <td><nobr>&nbsp;</nobr></td>
                </tr>
                <tr>
					<td align="center" colspan="3">
						<div align="center">
							<html:image property="methodToCall.submitAdminCorrection.anchor${tabKey}"
							            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
						</div>
	                </td>
                </tr>
            </tbody>
        </table>
    </div>
    
</kra:innerTab>

</kra:permission>
