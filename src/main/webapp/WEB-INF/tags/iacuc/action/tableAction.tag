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

<c:set var="attributes" value="${DataDictionary.IacucProtocolTableBean.attributes}" />
<c:set var="action" value="protocolProtocolActions" />

<kra:permission value="${KualiForm.actionHelper.canTable}">

<kul:innerTab tabTitle="Table Protocol" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.iacucProtocolTableBean*">
   <kra-protocol-action:padLeft>
        <table class="tab" cellpadding="0" cellspacing="0" summary=""> 
          <tbody>
	            <tr>
	            	<th> 
	                    <div align="right">
	                        <kul:htmlAttributeLabel attributeEntry="${attributes.comments}" />
	                    </div>
	                </th>
	                <td>
	                	<kul:htmlControlAttribute property="actionHelper.iacucProtocolTableBean.comments" 
			                                      attributeEntry="${attributes.comments}"/>
	                </td>
	            </tr>
	            
	            <tr>
                    <th> 
                        <div align="right">
                            <nobr>
                                <kul:htmlAttributeLabel attributeEntry="${attributes.actionDate}" />
                            </nobr>
                        </div>
                    </th>
                    <td>
                        <nobr>
                            <kul:htmlControlAttribute property="actionHelper.iacucProtocolTableBean.actionDate" 
                            						  attributeEntry="${attributes.actionDate}"  />
                        </nobr>
                    </td>
                </tr>
	            
	             <tr>
					<td align="center" colspan="2">
						<div align="center">
							<html:image property="methodToCall.tableProtocol.anchor${tabKey}"
							            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' 
							            styleClass="tinybutton"/>
						</div>
	                </td>
                </tr>
	            
          </tbody>
        </table>
   </kra-protocol-action:padLeft>
</kul:innerTab>

</kra:permission>
