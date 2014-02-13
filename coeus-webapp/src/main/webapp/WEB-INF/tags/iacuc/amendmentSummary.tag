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

<c:set var="attributes" value="${DataDictionary.IacucProtocolAmendmentBean.attributes}" />
<c:choose>
    <c:when test="${KualiForm.protocolDocument.renewal}">
        <c:set var="tabTitle" value="Renewal Details"/>
    </c:when>
    <c:otherwise>
        <c:set var="tabTitle" value="Amendment Details"/>
    </c:otherwise>
</c:choose>

<tr>
    <td class="tab-subhead" colspan="2" scope="row">
		<kul:innerTab tabTitle="${tabTitle}" parentTab="" defaultOpen="false" tabErrorKey="" overrideDivClass="inner-subhead">
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
		                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.summary" 
		                                                  attributeEntry="${attributes.summary}"
		                                                  disabled="true" />
		                        </nobr>
		                    </td>
		                </tr>
		            
		                <tr>
		                    <th width="15%">
		                        <div align="right">
		                        	<c:choose>
		                        		<c:when test="${KualiForm.protocolDocument.renewal}">
		                            		* Renew:
		                            	</c:when>
		                            	<c:otherwise>
		                                	* Amend: 
		                            	</c:otherwise>
		                            </c:choose>
		                        </div>
		                    </th>
		                    <td>
		                        <table cellpadding="0" cellspacing="0" summary="">
		                            <tbody>
		                                <tr>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.generalInfo" 
		                                                                  attributeEntry="${attributes.generalInfo}"
		                                                                  disabled="true" />
		                                        General Info
		                                    </td>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.addModifyAttachments" 
		                                                                  attributeEntry="${attributes.generalInfo}" 
		                                                                  disabled="true" />
		                                        Add/Modify Notes & Attachments
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.fundingSource" 
		                                                                  attributeEntry="${attributes.fundingSource}" 
		                                                                  disabled="true" />
		                                        Funding Source
		                                    </td>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.areasOfResearch" 
		                                                                  attributeEntry="${attributes.areasOfResearch}" 
		                                                                  disabled="true" />
		                                        Areas of Research
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.protocolReferencesAndOtherIdentifiers" 
		                                                                  attributeEntry="${attributes.protocolReferencesAndOtherIdentifiers}" 
		                                                                  disabled="true" />
		                                        Protocol References &amp; Other Identifiers
		                                    </td>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.specialReview" 
		                                                                  attributeEntry="${attributes.specialReview}" 
		                                                                  disabled="true" />
		                                        Special Review
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.protocolOrganizations" 
		                                                                  attributeEntry="${attributes.protocolOrganizations}" 
		                                                                  disabled="true" />
		                                        Protocol Organizations
		                                    </td>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.protocolPersonnel" 
		                                                                  attributeEntry="${attributes.protocolPersonnel}" 
		                                                                  disabled="true" />
		                                        Protocol Personnel
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.subjects" 
		                                                                  attributeEntry="${attributes.subjects}" 
		                                                                  disabled="true" />
		                                        Subjects
		                                    </td>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.others" 
		                                                                  attributeEntry="${attributes.others}" 
		                                                                  disabled="true" />
		                                        Others
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.questionnaire" 
		                                                                  attributeEntry="${attributes.questionnaire}" 
		                                                                  disabled="true" />
		                                        Questionnaire 
		                                    </td>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.threers" 
		                                                                  attributeEntry="${attributes.threers}" 
		                                                                  disabled="true" />
		                                        Three R's
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.speciesAndGroups" 
		                                                                  attributeEntry="${attributes.speciesAndGroups}" 
		                                                                  disabled="true" />
		                                        Species and Groups
		                                    </td>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.procedures" 
		                                                                  attributeEntry="${attributes.procedures}" 
		                                                                  disabled="true" />
		                                        Procedures
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentBean.protocolExceptions" 
		                                                                  attributeEntry="${attributes.protocolExceptions}" 
		                                                                  disabled="true" />
		                                        Protocol Exceptions
		                                    </td>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        &nbsp;
		                                    </td>
		                                </tr>
		                            </tbody>
		                        </table>
		                    </td>
		                </tr>
		            </tbody>
		        </table>
		    </div>
		    
		</kul:innerTab>
    </td>
</tr> 