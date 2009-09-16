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

<c:set var="attributes" value="${DataDictionary.ProtocolAmendmentBean.attributes}" />
<c:set var="action" value="protocolProtocolActions" />
<c:set var="textSummary" value="actionHelper.protocolRenewAmendmentBean.summary" />

<kra:permission value="${KualiForm.actionHelper.canCreateRenewal && KualiForm.actionHelper.canCreateAmendment}">

<kra:innerTab tabTitle="Create Renewal with Amendment" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.protocolCreateRenewalWithAmendment*">
    <div class="innerTab-container" align="left">
        <table class="tab" cellpadding="0" cellspacing="0" summary="">
            <tbody>
            	<tr>
                    <th width="15%"> 
                        <div align="right">
                            <nobr>
                            <kul:htmlAttributeLabel attributeEntry="${attributes.summary}" />
                            </nobr>
                        </div>
                    </th>
                    <td colspan="3">
                        <nobr>
                        <kul:htmlControlAttribute property="actionHelper.protocolRenewAmendmentBean.summary" attributeEntry="${attributes.summary}" />
                        <kra:expandedTextArea textAreaFieldName="${textSummary}" action="${action}" textAreaLabel="${attributes.summary.label}" />
                        </nobr>
                    </td>
                </tr>
            
            	<tr>
            		<th width="15%">
            			<div align="right">
            				*Amend:
            			</div>
            		</th>
            		<td>
            			<table cellpadding="0" cellspacing="0" summary="">
	            			<tbody>
	            				<tr>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolRenewAmendmentBean.generalInfo" 
	            					                              attributeEntry="${attributes.generalInfo}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolRenewAmendmentBean.generalInfoEnabled}" />
	            					    General Info
	            					</td>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolRenewAmendmentBean.addModifyAttachments" 
	            					                              attributeEntry="${attributes.generalInfo}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolRenewAmendmentBean.addModifyAttachmentsEnabled}" />
	            					    Add/Modify Attachments
	            					</td>
	            				</tr>
	            				<tr>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolRenewAmendmentBean.fundingSource" 
	            					                              attributeEntry="${attributes.fundingSource}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolRenewAmendmentBean.fundingSourceEnabled}" />
	            					    Funding Source
	            					</td>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolRenewAmendmentBean.areasOfResearch" 
	            					                              attributeEntry="${attributes.areasOfResearch}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolRenewAmendmentBean.areasOfResearchEnabled}" />
	            					    Areas of Research
	            					</td>
	            				</tr>
	            				<tr>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolRenewAmendmentBean.protocolReferences" 
	            					                              attributeEntry="${attributes.protocolReferences}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolRenewAmendmentBean.protocolReferencesEnabled}" />
	            					    Protocol References
	            					</td>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolRenewAmendmentBean.specialReview" 
	            					                              attributeEntry="${attributes.specialReview}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolRenewAmendmentBean.specialReviewEnabled}" />
	            					    Special Review
	            					</td>
	            				</tr>
	            				<tr>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolRenewAmendmentBean.protocolOrganizations" 
	            					                              attributeEntry="${attributes.protocolOrganizations}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolRenewAmendmentBean.protocolOrganizationsEnabled}" />
	            					    Protocol Organizations
	            					</td>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolRenewAmendmentBean.protocolPersonnel" 
	            					                              attributeEntry="${attributes.protocolPersonnel}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolRenewAmendmentBean.protocolPersonnelEnabled}" />
	            					    Protocol Personnel
	            					</td>
	            				</tr>
	            				<tr>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolRenewAmendmentBean.subjects" 
	            					                              attributeEntry="${attributes.subjects}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolRenewAmendmentBean.subjectsEnabled}" />
	            					    Subjects
	            					</td>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolRenewAmendmentBean.others" 
	            					                              attributeEntry="${attributes.others}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolRenewAmendmentBean.othersEnabled}" />
	            					    Others
	            					</td>
	            				</tr>
	            			</tbody>
                		</table>
            		</td>
            	</tr>
            		
                <tr>
					<td align="center" colspan="2">
						<div align="center">
							<html:image property="methodToCall.createRenewalWithAmendment.anchor${tabKey}"
							            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-create.gif' styleClass="tinybutton"/>
						</div>
	                </td>
                </tr>
            </tbody>
        </table>
    </div>
    
</kra:innerTab>

</kra:permission>
