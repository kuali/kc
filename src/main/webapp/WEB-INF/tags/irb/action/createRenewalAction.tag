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

<c:set var="action" value="protocolProtocolActions" />
<c:set var="attributes" value="${DataDictionary.ProtocolAmendmentBean.attributes}" />

<kra:permission value="${KualiForm.actionHelper.canCreateRenewal}">

<kul:innerTab tabTitle="Create Renewal without Amendment" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.protocolRenewal*,actionHelper.renewalSummary">
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
                    <td>
                        <nobr>
                        <kul:htmlControlAttribute property="actionHelper.renewalSummary" attributeEntry="${attributes.summary}" />
                        </nobr>
                    </td>
                </tr>
 
                <tr>
					<td align="center" colspan="2">
						<div align="center">
						    <html:image property="methodToCall.createRenewal.anchor${tabKey}"
							        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-create.gif' styleClass="tinybutton"/>
                        </div>
	                </td>
                </tr>
            </tbody>
        </table>
    </div>
    
</kul:innerTab>

</kra:permission>
