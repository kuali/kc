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

<c:set var="attributes" value="${DataDictionary.IacucProtocolAdministrativelyIncompleteBean.attributes}" />  <!-- using the parent bean class's attributes for the time being -->
<c:set var="action" value="protocolProtocolActions" />

<kra:permission value="${KualiForm.actionHelper.canAdministrativelyMarkIncomplete}">
	<tr>
		<td class="tab-subhead" scope="row">
			<kul:innerTab tabTitle="Administratively Mark Incomplete Protocol" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.protocolAdminIncompleteBean*" overrideDivClass="inner-subhead" >
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
			                        <kul:htmlControlAttribute property="actionHelper.protocolAdminIncompleteBean.reason" attributeEntry="${attributes.reason}" />
			                        </nobr>
			                    </td>
			                </tr>
			                <tr>
			                    <th width="15%"> 
			                        <div align="right">
			                            <nobr>
			                                <kul:htmlAttributeLabel attributeEntry="${attributes.actionDate}" />
			                            </nobr>
			                        </div>
			                    </th>
			                    <td>
			                        <nobr>
			                            <kul:htmlControlAttribute property="actionHelper.protocolAdminIncompleteBean.actionDate" 
			                                                      attributeEntry="${attributes.actionDate}"  />
			                        </nobr>
			                    </td>
			                </tr>
			                <tr>
								<td align="center" colspan="2">
									<div align="center">
										<html:image property="methodToCall.administrativelyMarkIncompleteProtocol.anchor${tabKey}"
										            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
									</div>
				                </td>
			                </tr>
			            </tbody>
			        </table>
			    </div>	    
			</kul:innerTab>
		</td>
	</tr>
</kra:permission>
