<%--
 Copyright 2006-2010 The Kuali Foundation
 
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

<c:set var="committeeAttributes" value="${DataDictionary.Committee.attributes}" />
<c:set var="batchCorrespondenceDetailAttributes" value="${DataDictionary.BatchCorrespondenceDetail.attributes}" />
<c:set var="kraAttributeReferenceDummyAttributes" value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />

<div id="workarea">
    <kul:tab tabTitle="Batch Correspondence"
             tabErrorKey=""
             auditCluster="requiredFieldsAuditErrors"  
             defaultOpen="false"
             useCurrentTabIndexAsKey="true"  
             transparentBackground="true">
        <div class="tab-container" align="center">
            <h3>
                <span class="subhead-left">Generate Batch Correspondence</span>
                <span class="subhead-right"><kul:help documentTypeName="CommitteeDocument" pageName="Committee Actions" /></span>
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
                            Batch Type:
                        </div>
                    </th> 
                    <td>
                        <div align="left">
                            <kul:htmlControlAttribute property="committeeHelper.committeeActionsHelper.generateBatchCorrespondenceTypeCode" 
                                                      attributeEntry="${batchCorrespondenceDetailAttributes.batchCorrespondenceTypeCode}" 
                                                      readOnly="false" />
                        </div>
                    </td>
                    
                    <th>
                        <div align="right">
                            Batch Run:
                        </div>
                    </th> 
                    <td>
                        <div align="left">
                            from
                            <kul:htmlControlAttribute property="committeeHelper.committeeActionsHelper.generateStartDate" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.genericDate}"
                                                      readOnly="false" />
                            to
                            <kul:htmlControlAttribute property="committeeHelper.committeeActionsHelper.generateEndDate" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.genericDate}" />
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

            <p>&nbsp;</p>

            <h3>
                <span class="subhead-left">Batch Correspondence History</span>
                <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.ResearchArea" altText="help"/></span>
            </h3>
            
            <table cellpadding="0" cellspacing="0">
            <table cellpadding="0" cellspacing="0">
                <tr>
                    <th width="20%">
                        <div align="right">
                            Committee ID:
                        </div>
                    </th> 
                    <td width="30%">
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
                            Batch Type:
                        </div>
                    </th> 
                    <td>
                        <div align="left">
                            <kul:htmlControlAttribute property="committeeHelper.committeeActionsHelper.historyBatchCorrespondenceTypeCode" 
                                                      attributeEntry="${batchCorrespondenceDetailAttributes.batchCorrespondenceTypeCode}" 
                                                      readOnly="false" />
                        </div>
                    </td>
                    
                    <th>
                        <div align="right">
                            Batch Run:
                        </div>
                    </th> 
                    <td>
                        <div align="left">
                            from
                            <kul:htmlControlAttribute property="committeeHelper.committeeActionsHelper.historyStartDate" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.genericDate}"
                                                      readOnly="false" />
                            to
                            <kul:htmlControlAttribute property="committeeHelper.committeeActionsHelper.historyEndDate" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.genericDate}" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="infoline" colspan="4">
                        <div align="center">
                            <html:image property="methodToCall.filterBatchCorrespondenceHistory"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-filter.gif' 
                                        styleClass="tinybutton"/>
                        </div>                         
                    </td>
                </tr> 
            </table>
        </div> 
    </kul:tab>
</div>