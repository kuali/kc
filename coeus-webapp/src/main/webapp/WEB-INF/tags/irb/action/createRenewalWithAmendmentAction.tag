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

<c:set var="attributes" value="${DataDictionary.ProtocolAmendmentBean.attributes}" />
<c:set var="action" value="protocolProtocolActions" />

<kra:permission value="${KualiForm.actionHelper.canCreateRenewal && KualiForm.actionHelper.canCreateAmendment}">

<kul:innerTab tabTitle="Create Renewal with Amendment" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.protocolCreateRenewalWithAmendment*">
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
                        <kul:htmlControlAttribute property="actionHelper.protocolRenewAmendmentBean.summary" attributeEntry="${attributes.summary}" />
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
	            					    Add/Modify Notes & Attachments
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
	            					    <kul:htmlControlAttribute property="actionHelper.protocolRenewAmendmentBean.protocolReferencesAndOtherIdentifiers" 
	            					                              attributeEntry="${attributes.protocolReferencesAndOtherIdentifiers}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolRenewAmendmentBean.protocolReferencesEnabled}" />
	            					    Protocol References &amp; Other Identifiers
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
	            				<tr>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    <kul:htmlControlAttribute property="actionHelper.protocolRenewAmendmentBean.questionnaire" 
	            					                              attributeEntry="${attributes.questionnaire}" 
	            					                              disabled="${!KualiForm.actionHelper.protocolRenewAmendmentBean.questionnaireEnabled}" />
	            					    Questionnaire
	            					</td>
	            					<td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
	            					    &nbsp;
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
    
</kul:innerTab>

</kra:permission>
