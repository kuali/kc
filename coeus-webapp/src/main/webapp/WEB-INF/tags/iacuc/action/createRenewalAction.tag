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

<c:set var="action" value="protocolProtocolActions" />
<c:set var="attributes" value="${DataDictionary.IacucProtocolAmendmentBean.attributes}" />

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
