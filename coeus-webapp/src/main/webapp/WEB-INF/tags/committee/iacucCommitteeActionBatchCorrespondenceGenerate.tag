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

<c:set var="committeeAttributes" value="${DataDictionary.IacucCommittee.attributes}" />
<c:set var="batchCorrespondenceDetailAttributes" value="${DataDictionary.IacucBatchCorrespondenceDetail.attributes}" />
<c:set var="kraAttributeReferenceDummyAttributes" value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />

<kra:softError softErrorKey="committeeHelper.generateBatchCorrespondenceTypeCode" />

<h3>
    <span class="subhead-left">Generate Batch Correspondence</span>
    <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.iacuc.correspondence.IacucBatchCorrespondence" altText="help"/></span>
</h3>

<table cellpadding="0" cellspacing="0">
    <tr>
        <th width="20%">
            <div align="right">
                Committee ID:
            </div>
        </th width="30%"> 
        <td>
            <div align="left">
                <kul:htmlControlAttribute property="document.committeeList[0].committeeId" 
                                          attributeEntry="${committeeAttributes.committeeId}" 
                                          readOnly="true" />
            </div>
        </td>
        
        <th width="20%">
            <div align="right">
                Committee Name:
            </div>
        </th> 
        <td width="30%">
            <div align="left">
                <kul:htmlControlAttribute property="document.committeeList[0].committeeName" 
                                          attributeEntry="${committeeAttributes.committeeName}" 
                                          readOnly="true" />
            </div>
        </td>
    </tr> 
    <tr>
        <th>
            <div align="right">
                *Batch Type:
            </div>
        </th> 
        <td>
            <div align="left">
                <kul:htmlControlAttribute property="committeeHelper.generateBatchCorrespondenceTypeCode" 
                                          attributeEntry="${batchCorrespondenceDetailAttributes.batchCorrespondenceTypeCode}" 
                                          readOnly="false" />
            </div>
        </td>
        
        <th>
            <div align="right">
                *Protocol Expiration / Committee Action:
            </div>
        </th> 
        <td>
            <div align="left">
                from
                <kul:htmlControlAttribute property="committeeHelper.generateStartDate" 
                                          attributeEntry="${kraAttributeReferenceDummyAttributes.genericDate}"
                                          readOnly="false" />
                to
                <kul:htmlControlAttribute property="committeeHelper.generateEndDate" 
                                          attributeEntry="${kraAttributeReferenceDummyAttributes.genericDate}" 
                                          readOnly="false" />
            </div>
        </td>
    </tr> 
    <tr>
        <td class="infoline" colspan="4">
            <div align="center">
                <html:image property="methodToCall.generateBatchCorrespondence"
                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' 
                            styleClass="tinybutton"/>
            </div>                         
        </td>
    </tr> 
</table>

<c:if test="${not empty KualiForm.committeeHelper.generateBatchCorrespondence}">
    <p>&nbsp;</p>
        
    <div align="left">
        <kul:errors keyMatch="committeeHelper.generateBatchCorrespondence" /> <br>
    </div>

    <h3>
        <span class="subhead-left">
            Generated Batch Correspondence
        </span>
        <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.committee.bo.CommitteeBatchCorrespondence" altText="help" /></span>
    </h3>

    <table cellpadding=0 cellspacing=0 border=0>
        <tr>
            <td class="infoline" colspan="2">
                <div id="correspondanceDetails">  
                <kra-committee:iacucCommitteeActionBatchCorrespondenceRun committeeBatchCorrespondence="${KualiForm.committeeHelper.generateBatchCorrespondence[0]}" committeeBatchCorrespondenceProperty="committeeHelper.generateBatchCorrespondence[0]" />
            	</div>
            </td>
        </tr>
        <tr>
            <td class="neutral" width = "94%" style="background-color: #e4e4e4;">
                &nbsp;
            </td>
            <td  style="background-color: #e4e4e4;" >
                <div align="center">
                    <a id="viewBatchCorrespondenceGenerated" href="#"><html:image property="methodToCall.viewBatchCorrespondenceGenerated"
                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' 
                                styleClass="tinybutton"
                                onclick="excludeSubmitRestriction = true;" />
                    </a>
                </div>                         
            </td>
        </tr>
    </table>
    
    <div style="center">
        Number of protocols on which final action has been performed: 
        ${KualiForm.committeeHelper.generateBatchCorrespondence[0].finalActionCounter}
    </div>
</c:if>            

