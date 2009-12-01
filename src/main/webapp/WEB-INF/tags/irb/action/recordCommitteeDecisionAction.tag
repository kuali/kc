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

<c:set var="attributes" value="${DataDictionary.CommitteeDecision.attributes}" />
<c:set var="action" value="protocolProtocolActions" />
<c:set var="votingComments" value="actionHelper.committeeDecision.votingComments" />

<kra:permission value="${KualiForm.actionHelper.canRecordCommitteeDecision}">

<kra:innerTab tabTitle="Record Committee Decision" parentTab="" defaultOpen="false" tabErrorKey="*">
   
    <div style="padding-left: 56px" >
        <table class="tab" cellpadding="0" cellspacing="0" summary=""> 
            <tbody>
                
                <tr>
                    <th> 
                        <div align="right">
                            <kul:htmlAttributeLabel attributeEntry="${attributes.motion}" />
                        </div>
                    </th>
                    <td>
                        <kul:htmlControlAttribute property="actionHelper.committeeDecision.motion" attributeEntry="${attributes.motion}" />
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
                        <kul:htmlControlAttribute property="actionHelper.committeeDecision.abstainCount" attributeEntry="${attributes.abstainCount}" />
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
                            <kra:expandedTextArea textAreaFieldName="${votingComments}" action="${action}" textAreaLabel="${attributes.votingComments.label}" />
                        </nobr>
                    </td>
                   
                </tr>
                
                <tr>
                    <td colspan="10">
                        <kra-irb-action:reviewComments bean="${KualiForm.actionHelper.committeeDecision.reviewComments}"
                                                       property="actionHelper.committeeDecision.reviewComments"
                                                       action="${action}"
                                                       actionName="CommitteeDecision" />
                   </td>
                </tr>
                
                <tr>
                    <td align="center" colspan="10">
                        <div align="center">
                            <html:image property="methodToCall.submitCommitteeDecision.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>       
    </div>
    
</kra:innerTab>

</kra:permission>
