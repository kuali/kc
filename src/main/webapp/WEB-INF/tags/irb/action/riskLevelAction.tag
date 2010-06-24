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

<c:set var="attributes" value="${DataDictionary.ProtocolRiskLevel.attributes}" />
<c:set var="action" value="protocolProtocolActions" />
<c:set var="textComments" value="actionHelper.newRiskLevel.comments" />

<kra:permission value="${KualiForm.actionHelper.canEnterRiskLevel}">

<kra:innerTab tabTitle="Enter Risk Level" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.protocolRiskLevel*">
   
    <div style="padding-left: 56px" >
        <table class="tab" cellpadding="0" cellspacing="0" summary=""> 
            <tbody>
            
                <%-- Table headers --%>
                <tr>
                    <th><div align="left">&nbsp;</div></th> 
                    <kul:htmlAttributeHeaderCell attributeEntry="${attributes.riskLevelCode}" />
                    <kul:htmlAttributeHeaderCell attributeEntry="${attributes.dateAssigned}" />
                    <kul:htmlAttributeHeaderCell attributeEntry="${attributes.dateUpdated}" />
                    <kul:htmlAttributeHeaderCell attributeEntry="${attributes.status}" />
                    <kul:htmlAttributeHeaderCell attributeEntry="${attributes.comments}" />
                    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
                </tr>
                                        
                <tr> 
                    <th class="infoline">add</th>
                    
                    <td>
                        <kul:htmlControlAttribute property="actionHelper.newRiskLevel.riskLevelCode" attributeEntry="${attributes.riskLevelCode}" styleClass="fixed-size-200-select" />
                    </td>
                    
                    <td>
                        <nobr>
                            <kul:htmlControlAttribute property="actionHelper.newRiskLevel.dateAssigned" attributeEntry="${attributes.dateAssigned}"  />
                        </nobr>
                    </td>
                
                    <td>
                        <nobr>
                            <kul:htmlControlAttribute property="actionHelper.newRiskLevel.dateUpdated" attributeEntry="${attributes.dateUpdated}"  />
                        </nobr>
                    </td>
                    
                    <td>
                        <kul:htmlControlAttribute property="actionHelper.newRiskLevel.status" attributeEntry="${attributes.status}" />
                    </td>
                    
                    <td>
                        <nobr>
                            <kul:htmlControlAttribute property="actionHelper.newRiskLevel.comments" attributeEntry="${attributes.comments}" />
                        </nobr>
                    </td>
                    
                    <td>
                        <div align="center">
                            <html:image property="methodToCall.addRiskLevel.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
                        </div>
                    </td>
                </tr>
                
                <c:forEach var="riskLevel" items="${KualiForm.actionHelper.riskLevels}" varStatus="status">
                    <c:set var="riskLevelPath" value="actionHelper.riskLevels[${status.index}]" />
                    <tr>
                    
                        <th>${status.index + 1}</th>
                    
                        <td>
                            <kul:htmlControlAttribute property="${riskLevelPath}.riskLevelCode" attributeEntry="${attributes.riskLevelCode}" styleClass="fixed-size-200-select" />
                        </td>
                        
                        <td>
                            <nobr>
                                <kul:htmlControlAttribute property="${riskLevelPath}.dateAssigned" attributeEntry="${attributes.dateAssigned}"  />
                            </nobr>
                        </td>
                
                       <td>
                            <nobr>
                                <kul:htmlControlAttribute property="${riskLevelPath}.dateUpdated" attributeEntry="${attributes.dateUpdated}"  />
                            </nobr>
                        </td>
                    
                        <td>
                            <kul:htmlControlAttribute property="${riskLevelPath}.status" attributeEntry="${attributes.status}" />
                        </td>
                    
                        <td>
                            <nobr>
                                <kul:htmlControlAttribute property="${riskLevelPath}.comments" attributeEntry="${attributes.comments}" />
                            </nobr>
                        </td>
                    
                        <td>
                            <div align="center">
                                <html:image property="methodToCall.deleteRiskLevel.line${status.index}.anchor${tabKey}"
                                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                
                <tr>
                    <td align="center" colspan="7">
                        <div align="center">
                            <html:image property="methodToCall.submitRiskLevels.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>       
    </div>
    
</kra:innerTab>

</kra:permission>
