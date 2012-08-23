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

<c:set var="attributes" value="${DataDictionary.IacucProtocolAmendmentBean.attributes}" />
<c:set var="action" value="protocolProtocolActions" />

<kra:permission value="${KualiForm.actionHelper.canCreateContinuation && KualiForm.actionHelper.canCreateAmendment}">

<kul:innerTab tabTitle="Create Continuation with Amendment" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.protocolCreateContinuationWithAmendment*">
    <div class="innerTab-container" align="left">
        <table class="tab" cellpadding="0" cellspacing="0" summary="">
            <tbody>
            	<tr>
                    <th width="15%"> 
                        <div align="right">
                            <nobr>
                            * Summary:
                            </nobr>
                        </div>
                    </th>
                    <td colspan="3">
                        <nobr>
                        <kul:htmlControlAttribute property="actionHelper.protocolContinuationAmendmentBean.summary" attributeEntry="${attributes.summary}" />
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
	            					    <kul:htmlControlAttribute property="actionHelper.protocolContinuationAmendmentBean.generalInfo" 
	            					                              attributeEntry="${attributes.generalInfo}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolContinuationAmendmentBean.generalInfoEnabled}" />
	            					    General Info
	            					</td>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolContinuationAmendmentBean.addModifyAttachments" 
	            					                              attributeEntry="${attributes.generalInfo}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolContinuationAmendmentBean.addModifyAttachmentsEnabled}" />
	            					    Add/Modify Notes & Attachments
	            					</td>
	            				</tr>
	            				<tr>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolContinuationAmendmentBean.fundingSource" 
	            					                              attributeEntry="${attributes.fundingSource}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolContinuationAmendmentBean.fundingSourceEnabled}" />
	            					    Funding Source
	            					</td>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolContinuationAmendmentBean.areasOfResearch" 
	            					                              attributeEntry="${attributes.areasOfResearch}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolContinuationAmendmentBean.areasOfResearchEnabled}" />
	            					    Areas of Research
	            					</td>
	            				</tr>
	            				<tr>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolContinuationAmendmentBean.protocolReferencesAndOtherIdentifiers" 
	            					                              attributeEntry="${attributes.protocolReferencesAndOtherIdentifiers}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolContinuationAmendmentBean.protocolReferencesEnabled}" />
	            					    Protocol References &amp; Other Identifiers
	            					</td>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolContinuationAmendmentBean.specialReview" 
	            					                              attributeEntry="${attributes.specialReview}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolContinuationAmendmentBean.specialReviewEnabled}" />
	            					    Special Review
	            					</td>
	            				</tr>
	            				<tr>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolContinuationAmendmentBean.protocolOrganizations" 
	            					                              attributeEntry="${attributes.protocolOrganizations}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolContinuationAmendmentBean.protocolOrganizationsEnabled}" />
	            					    Protocol Organizations
	            					</td>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolContinuationAmendmentBean.protocolPersonnel" 
	            					                              attributeEntry="${attributes.protocolPersonnel}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolContinuationAmendmentBean.protocolPersonnelEnabled}" />
	            					    Protocol Personnel
	            					</td>
	            				</tr>
	            				
	            				<%-- 
	            				<tr>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolContinuationAmendmentBean.subjects" 
	            					                              attributeEntry="${attributes.subjects}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolContinuationAmendmentBean.subjectsEnabled}" />
	            					    Subjects
	            					</td>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolContinuationAmendmentBean.others" 
	            					                              attributeEntry="${attributes.others}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolContinuationAmendmentBean.othersEnabled}" />
	            					    Others
	            					</td>
	            				</tr>
	            				--%>
	            				
	            				<tr>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolContinuationAmendmentBean.questionnaire" 
	            					                              attributeEntry="${attributes.questionnaire}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolContinuationAmendmentBean.questionnaireEnabled}" />
	            					    Questionnaire
	            					</td>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolContinuationAmendmentBean.threers" 
	            					                              attributeEntry="${attributes.threers}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolContinuationAmendmentBean.threersEnabled}" />
	            					    Three R's
	            					</td>
	            				</tr>

	            				<tr>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolContinuationAmendmentBean.speciesAndGroups" 
	            					                              attributeEntry="${attributes.speciesAndGroups}"
	            					                              disabled="${!KualiForm.actionHelper.protocolContinuationAmendmentBean.speciesAndGroupsEnabled}" />
	            					    Species/Groups
	            					</td>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolContinuationAmendmentBean.procedures" 
	            					                              attributeEntry="${attributes.procedures}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolContinuationAmendmentBean.proceduresEnabled}" />
	            					    Procedures
	            					</td>
	            				</tr>
	            				<tr>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolContinuationAmendmentBean.protocolExceptions" 
	            					                              attributeEntry="${attributes.protocolExceptions}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolContinuationAmendmentBean.protocolExceptionsEnabled}" />
	            					    Protocol Exception
	            					</td>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolContinuationAmendmentBean.others" 
	            					                              attributeEntry="${attributes.others}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolContinuationAmendmentBean.othersEnabled}" />
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
							<html:image property="methodToCall.createContinuationWithAmendment.anchor${tabKey}"
							            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-create.gif' styleClass="tinybutton"/>
						</div>
	                </td>
                </tr>
            </tbody>
        </table>
    </div>
    
</kul:innerTab>

</kra:permission>
