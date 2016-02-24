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
 
<c:set var="attributes" value="${DataDictionary.IacucProtocolAssignCmtBean.attributes}" />

<kra:permission value="${KualiForm.actionHelper.canAssignCmt}">

<kul:innerTab tabTitle="Assign to Committee" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.protocolAssignCmtBean*">
    <div class="innerTab-container" align="left">
        <table class="tab" cellpadding="0" cellspacing="0" summary=""> 
            <tbody>
                <tr>
	                <th style="width: 150px"> 
	                    <div align="right">
	                        <kul:htmlAttributeLabel attributeEntry="${attributes.committeeId}" />
	                    </div>
	                </th>
	                <td style="width : 150px">
                        <html:select property="actionHelper.protocolAssignCmtBean.committeeId" >                               
                            <c:forEach items="${KualiForm.actionHelper.assignCmtActionCommitteeIdByUnitKeyValues}" var="option" >
                                <c:choose>                      
                                    <c:when test="${KualiForm.actionHelper.protocolAssignCmtBean.committeeId == option.key}">
                                        <option value="${option.key}" selected="selected">${option.value}</option>
                                    </c:when>
                                    <c:otherwise>                               
                                        <c:out value="${option.value}"/>
                                        <option value="${option.key}">${option.value}</option>
                                    </c:otherwise>
                                </c:choose>                                                
                            </c:forEach>
                        </html:select>
	                </td>
	            </tr>
                
                <tr>
					<td align="center" colspan="4">
						<div align="center">
							<html:image property="methodToCall.assignCommittee.anchor${tabKey}"
							            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
						</div>
	                </td>
                </tr>
            </tbody>
        </table>
    </div>
    
</kul:innerTab>
</kra:permission>
