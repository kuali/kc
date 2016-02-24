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

<c:set var="attributes" value="${DataDictionary.IacucCommitteeDecision.attributes}" />
<c:set var="cpattributes" value="${DataDictionary.IacucCommitteePerson.attributes}" />
<c:set var="action" value="protocolProtocolActions" />
<c:set var="votingComments" value="actionHelper.committeeDecision.votingComments" />

<kra:permission value="${KualiForm.actionHelper.canRecordCommitteeDecision}">

<kul:innerTab tabTitle="Record Committee Decision" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.committeeDecision*">
   
   <kra-protocol-action:padLeft>
        <table class="tab" cellpadding="0" cellspacing="0" summary=""> 
            <tbody>
                
                <tr>
                    <th> 
                        <div align="right">
                            <kul:htmlAttributeLabel attributeEntry="${attributes.motionTypeCode}" />
                        </div>
                    </th>
                    <td>
                        <kul:htmlControlAttribute property="actionHelper.committeeDecision.motionTypeCode" attributeEntry="${attributes.motionTypeCode}" />
                    </td>
                    
                    <th> 
                        <div align="right">
                            <kul:htmlAttributeLabel attributeEntry="${attributes.noCount}" />
                        </div>
                    </th>
                    <td>
                        <kul:htmlControlAttribute property="actionHelper.committeeDecision.noCount" attributeEntry="${attributes.noCount}" />
                    </td>
                    
                    <th> 
                        <div align="right">
                            <kul:htmlAttributeLabel attributeEntry="${attributes.yesCount}" />
                        </div>
                    </th>
                    <td>
                        <kul:htmlControlAttribute property="actionHelper.committeeDecision.yesCount" attributeEntry="${attributes.yesCount}" />
                    </td>
                    
                    <th> 
                        <div align="right">
                            <kul:htmlAttributeLabel attributeEntry="${attributes.abstainCount}" />
                        </div>
                    </th>
                    <td>
                        <kul:htmlControlAttribute property="actionHelper.committeeDecision.abstainCount" attributeEntry="${attributes.abstainCount}" readOnly="true" />
                    </td>
                    
                    <th> 
                        <div align="right">
                            <kul:htmlAttributeLabel attributeEntry="${attributes.recusedCount}" />
                        </div>
                    </th>
                    <td>
                        <kul:htmlControlAttribute property="actionHelper.committeeDecision.recusedCount" attributeEntry="${attributes.recusedCount}" readOnly="true" />
                    </td>
                    
                    <th> 
                        <div align="right">
                            <nobr>
                                <kul:htmlAttributeLabel attributeEntry="${attributes.votingComments}" />
                            </nobr>
                        </div>
                    </th>
                
                    <td>
                        <nobr>
                            <kul:htmlControlAttribute property="actionHelper.committeeDecision.votingComments" attributeEntry="${attributes.votingComments}" />
                        </nobr>
                    </td>
                   
                </tr>
                
                <tr>
                    <td colspan="12">
                    
                        <kul:innerTab tabTitle="Abstainers" parentTab="" defaultOpen="false" tabErrorKey="" useCurrentTabIndexAsKey="true">
                            <div class="innerTab-container" align="left">
                                <table class="tab" cellpadding="0" cellspacing="0" summary="">
                                    <tbody>
                                    
                                        <%-- Table headers --%>
                                        <tr>
                                            <th><div align="left">&nbsp;</div></th> 
                                            <kul:htmlAttributeHeaderCell literalLabel="Person" scope="col" />
                                            <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
                                        </tr>
                                        
                                        <tr> 
                                            <th class="infoline">add</th>
                                            
                                            <td>
                                                <div align="center">
                                                    <kul:htmlControlAttribute property="actionHelper.committeeDecision.newAbstainer.membershipId" 
                                                                              attributeEntry="${cpattributes.membershipId}" />
                                                </div>
                                            </td>

                                            <td>
                                                <div align="center">
                                                    <html:image property="methodToCall.addAbstainer.anchor${tabKey}"
                                                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
                                                </div>
                                           </td>
                                        </tr>
                                        
                                        <c:forEach var="abstainer" items="${KualiForm.actionHelper.committeeDecision.abstainers}" varStatus="status">
                                            <tr>
                                                <th>${status.index + 1}</th>
                                                 
                                                <td style="text-align:center; vertical-align:middle">
                                                    <kul:htmlControlAttribute property="actionHelper.committeeDecision.abstainers[${status.index}].membershipId" 
                                                                              attributeEntry="${cpattributes.membershipId}" readOnly="true" />
                                                </td>
                                                 
                                                <td>
                                                    <div align="center">
                                                        <html:image property="methodToCall.deleteAbstainer.line${status.index}.anchor${tabKey}"
                                                                    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </kul:innerTab>
                    
                    </td>
                </tr>
                
                <tr>
                    <td colspan="12">
                    
                        <kul:innerTab tabTitle="Recused" parentTab="" defaultOpen="false" tabErrorKey="" useCurrentTabIndexAsKey="true">
                            <div class="innerTab-container" align="left">
                                <table class="tab" cellpadding="0" cellspacing="0" summary="">
                                    <tbody>
                                    
                                        <%-- Table headers --%>
                                        <tr>
                                            <th><div align="left">&nbsp;</div></th> 
                                            <kul:htmlAttributeHeaderCell literalLabel="Person" scope="col" />
                                            <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
                                        </tr>
                                        
                                        <tr> 
                                            <th class="infoline">add</th>
                                            
                                            <td>
                                                <div align="center">
                                                    <kul:htmlControlAttribute property="actionHelper.committeeDecision.newRecused.membershipId" 
                                                                              attributeEntry="${cpattributes.membershipId}" />
                                                </div>
                                            </td>

                                            <td>
                                                <div align="center">
                                                    <html:image property="methodToCall.addRecused.anchor${tabKey}"
                                                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
                                                </div>
                                           </td>
                                        </tr>
                                        
                                        <c:forEach var="recused" items="${KualiForm.actionHelper.committeeDecision.recused}" varStatus="status">
                                            <tr>
                                                <th>${status.index + 1}</th>
                                                 
                                                <td style="text-align:center; vertical-align:middle">
                                                    <kul:htmlControlAttribute property="actionHelper.committeeDecision.recused[${status.index}].membershipId" 
                                                                              attributeEntry="${cpattributes.membershipId}" readOnly="true" />
                                                </td>
                                                 
                                                <td>
                                                    <div align="center">
                                                        <html:image property="methodToCall.deleteRecused.line${status.index}.anchor${tabKey}"
                                                                    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </kul:innerTab>
                    
                    </td>
                </tr>
                
                <tr>
                    <td colspan="12">
                        <kra-iacuc-action:reviewComments bean="${KualiForm.actionHelper.committeeDecision.reviewCommentsBean}"
                                                       property="actionHelper.committeeDecision.reviewCommentsBean"
                                                       action="${action}"
                                                       taskName="protocolRecordCommitteeDecision" />
                   </td>
                </tr>
                
                <tr>
                    <td align="center" colspan="12">
                        <div align="center">
                            <html:image property="methodToCall.submitCommitteeDecision.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>       
   </kra-protocol-action:padLeft>
    
</kul:innerTab>

</kra:permission>
