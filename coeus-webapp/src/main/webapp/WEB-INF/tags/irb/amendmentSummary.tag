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
<c:choose>
    <c:when test="${KualiForm.protocolDocument.renewalWithAmendment}">
        <c:set var="tabTitle" value="Renewal With Amendment Details"/>
    </c:when>
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
		                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentSummaryBean.summary" 
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
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentSummaryBean.generalInfo" 
		                                                                  attributeEntry="${attributes.generalInfo}"
		                                                                  disabled="true" />
		                                        General Info
		                                    </td>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentSummaryBean.addModifyAttachments" 
		                                                                  attributeEntry="${attributes.generalInfo}" 
		                                                                  disabled="true" />
		                                        Add/Modify Notes & Attachments
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentSummaryBean.fundingSource" 
		                                                                  attributeEntry="${attributes.fundingSource}" 
		                                                                  disabled="true" />
		                                        Funding Source
		                                    </td>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentSummaryBean.areasOfResearch" 
		                                                                  attributeEntry="${attributes.areasOfResearch}" 
		                                                                  disabled="true" />
		                                        Areas of Research
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentSummaryBean.protocolReferencesAndOtherIdentifiers" 
		                                                                  attributeEntry="${attributes.protocolReferencesAndOtherIdentifiers}" 
		                                                                  disabled="true" />
		                                        Protocol References &amp; Other Identifiers
		                                    </td>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentSummaryBean.specialReview" 
		                                                                  attributeEntry="${attributes.specialReview}" 
		                                                                  disabled="true" />
		                                        Special Review
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentSummaryBean.protocolOrganizations" 
		                                                                  attributeEntry="${attributes.protocolOrganizations}" 
		                                                                  disabled="true" />
		                                        Protocol Organizations
		                                    </td>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentSummaryBean.protocolPersonnel" 
		                                                                  attributeEntry="${attributes.protocolPersonnel}" 
		                                                                  disabled="true" />
		                                        Protocol Personnel
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentSummaryBean.subjects" 
		                                                                  attributeEntry="${attributes.subjects}" 
		                                                                  disabled="true" />
		                                        Subjects
		                                    </td>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentSummaryBean.others" 
		                                                                  attributeEntry="${attributes.others}" 
		                                                                  disabled="true" />
		                                        Others
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td width="50%" style="border-left: 0 none; border-bottom: 0 none;">
		                                        <kul:htmlControlAttribute property="actionHelper.protocolAmendmentSummaryBean.questionnaire" 
		                                                                  attributeEntry="${attributes.questionnaire}" 
		                                                                  disabled="true" />
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
		            </tbody>
		        </table>
		    </div>
		    
		</kul:innerTab>
    </td>
</tr> 
