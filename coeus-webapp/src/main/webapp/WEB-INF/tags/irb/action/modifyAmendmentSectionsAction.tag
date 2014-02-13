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

<c:set var="attributes" value="${DataDictionary.ProtocolAmendmentBean.attributes}" />
<c:set var="action" value="protocolProtocolActions" />

<kra:permission value="${KualiForm.actionHelper.canModifyAmendmentSections}">

<kul:innerTab tabTitle="Modify Amendment Sections" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.protocolCreateAmendment*">
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
                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.summary" attributeEntry="${attributes.summary}" />
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
                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.generalInfo" 
                                                                  attributeEntry="${attributes.generalInfo}"
                                                                  disabled="${!KualiForm.actionHelper.protocolAmendmentBean.generalInfoEnabled}" />
                                        General Info
                                    </td>
                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.addModifyAttachments" 
                                                                  attributeEntry="${attributes.generalInfo}" 
                                                                  disabled="${!KualiForm.actionHelper.protocolAmendmentBean.addModifyAttachmentsEnabled}" />
                                        Add/Modify Notes & Attachments
                                    </td>
                                </tr>
                                <tr>
                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.fundingSource" 
                                                                  attributeEntry="${attributes.fundingSource}" 
                                                                  disabled="${!KualiForm.actionHelper.protocolAmendmentBean.fundingSourceEnabled}" />
                                        Funding Source
                                    </td>
                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.areasOfResearch" 
                                                                  attributeEntry="${attributes.areasOfResearch}" 
                                                                  disabled="${!KualiForm.actionHelper.protocolAmendmentBean.areasOfResearchEnabled}" />
                                        Areas of Research
                                    </td>
                                </tr>
                                <tr>
                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.protocolReferencesAndOtherIdentifiers" 
                                                                  attributeEntry="${attributes.protocolReferencesAndOtherIdentifiers}" 
                                                                  disabled="${!KualiForm.actionHelper.protocolAmendmentBean.protocolReferencesEnabled}" />
                                        Protocol References &amp; Other Identifiers
                                    </td>
                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.specialReview" 
                                                                  attributeEntry="${attributes.specialReview}" 
                                                                  disabled="${!KualiForm.actionHelper.protocolAmendmentBean.specialReviewEnabled}" />
                                        Special Review
                                    </td>
                                </tr>
                                <tr>
                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.protocolOrganizations" 
                                                                  attributeEntry="${attributes.protocolOrganizations}" 
                                                                  disabled="${!KualiForm.actionHelper.protocolAmendmentBean.protocolOrganizationsEnabled}" />
                                        Protocol Organizations
                                    </td>
                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.protocolPersonnel" 
                                                                  attributeEntry="${attributes.protocolPersonnel}" 
                                                                  disabled="${!KualiForm.actionHelper.protocolAmendmentBean.protocolPersonnelEnabled}" />
                                        Protocol Personnel
                                    </td>
                                </tr>
                                <tr>
                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.subjects" 
                                                                  attributeEntry="${attributes.subjects}" 
                                                                  disabled="${!KualiForm.actionHelper.protocolAmendmentBean.subjectsEnabled}" />
                                        Subjects
                                    </td>
                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.others" 
                                                                  attributeEntry="${attributes.others}" 
                                                                  disabled="${!KualiForm.actionHelper.protocolAmendmentBean.othersEnabled}" />
                                        Others
                                    </td>
                                </tr>
                                <tr>
                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.questionnaire" 
                                                                  attributeEntry="${attributes.questionnaire}" 
                                                                  disabled="${!KualiForm.actionHelper.protocolAmendmentBean.questionnaireEnabled}" />
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
                            <html:image property="methodToCall.modifyAmendmentSections.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-update.gif' styleClass="tinybutton"/>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    
</kul:innerTab>

</kra:permission>
