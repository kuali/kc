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

<c:set var="attributes" value="${DataDictionary.ProtocolWithdrawBean.attributes}" />
<c:set var="action" value="protocolProtocolActions" />
<c:set var="textWithdrawReason" value="actionHelper.protocolWithdrawBean.reason" />

<kra:permission value="${KualiForm.actionHelper.canSubmitProtocol}">

<kul:innerTab tabTitle="Withdraw Protocol" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.protocolWithdrawBean*">
    <div class="innerTab-container" align="left">
        <table class="tab" cellpadding="0" cellspacing="0" summary=""> 
            <tbody>
                <tr>
                    <th width="15%"> 
                        <div align="right">
                            <nobr>
                            <kul:htmlAttributeLabel attributeEntry="${attributes.reason}" />
                            </nobr>
                        </div>
                    </th>
                    <td>
                        <nobr>
                        <kul:htmlControlAttribute property="actionHelper.protocolWithdrawBean.reason" attributeEntry="${attributes.reason}" />
                        <kra:expandedTextArea textAreaFieldName="${textWithdrawReason}" action="${action}" textAreaLabel="${attributes.reason.label}" />
                        </nobr>
                    </td>
                </tr>
                <tr>
					<td align="center" colspan="2">
						<div align="center">
							<html:image property="methodToCall.withdrawProtocol.anchor${tabKey}"
							            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
						</div>
	                </td>
                </tr>
            </tbody>
        </table>
    </div>
    
</kul:innerTab>

</kra:permission>
