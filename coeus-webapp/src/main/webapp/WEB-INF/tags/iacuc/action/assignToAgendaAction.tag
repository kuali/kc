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

<c:set var="attributes" value="${DataDictionary.IacucProtocolAssignToAgendaBean.attributes}" />
<c:set var="action" value="protocolProtocolActions" />

<kra:permission value="${KualiForm.actionHelper.canAssignToAgenda}">

<kul:innerTab tabTitle="Assign to Agenda" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.assignToAgendaBean*">
   <kra-protocol-action:padLeft>
        <table class="tab" cellpadding="0" cellspacing="0" summary=""> 
          <tbody>
	            <tr>
	            	<th> 
	                    <div align="right">
	                        <kul:htmlAttributeLabel attributeEntry="${attributes.committeeId}" />
	                    </div>
	                </th>
	                <td>
	                	<kul:htmlControlAttribute property="actionHelper.assignToAgendaBean.committeeId" 
			                                      attributeEntry="${attributes.committeeId}"
			                                      readOnly="${true}" />
	                </td>
	            </tr>
	            <tr>
	            	<th> 
	                    <div align="right">
	                        <kul:htmlAttributeLabel attributeEntry="${attributes.committeName}" />
	                    </div>
	                </th>
	                <td>
	                	<kul:htmlControlAttribute property="actionHelper.assignToAgendaBean.committeName" 
			                                      attributeEntry="${attributes.committeName}"
			                                      readOnly="${true}" />
	                </td>
	            </tr>
	            <tr>
	            	<th> 
	                    <div align="right">
	                        <kul:htmlAttributeLabel attributeEntry="${attributes.scheduleDate}" />
	                    </div>
	                </th>
	                <td>
	                	
	                	<kul:htmlControlAttribute property="actionHelper.assignToAgendaBean.scheduleDate" 
			                                      attributeEntry="${attributes.scheduleDate}"
			                                      readOnly="${true}" />
	                </td>
	            </tr>
	  
	            <tr>
	            	<th> 
	                    <div align="right">
	                        <kul:htmlAttributeLabel attributeEntry="${attributes.comments}" />
	                    </div>
	                </th>
	                <td>
	                	<kul:htmlControlAttribute property="actionHelper.assignToAgendaBean.comments" 
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
                            <kul:htmlControlAttribute property="actionHelper.assignToAgendaBean.actionDate" 
                            						  attributeEntry="${attributes.actionDate}"  />
                        </nobr>
                    </td>
                </tr>
	            
	            
	            <tr>
                    <td colspan="2">
                        <kra-iacuc-action:reviewComments bean="${KualiForm.actionHelper.assignToAgendaBean.reviewCommentsBean}"
                                                       property="actionHelper.assignToAgendaBean.reviewCommentsBean"
                                                       action="${action}"
                                                       taskName="protocolAssignToAgenda" />
                   </td>
                </tr>
	            
	             <tr>
					<td align="center" colspan="2">
						<div align="center">
							<html:image property="methodToCall.assignToAgenda.anchor${tabKey}"
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
