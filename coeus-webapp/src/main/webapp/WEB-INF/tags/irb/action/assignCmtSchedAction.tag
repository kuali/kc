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

<c:set var="attributes" value="${DataDictionary.ProtocolAssignCmtSchedBean.attributes}" />
<c:set var="action" value="protocolProtocolActions" />

<kra:permission value="${KualiForm.actionHelper.canAssignCmtSched}">

<kul:innerTab tabTitle="Assign to Committee and Schedule" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.assignCmtSchedBean*">
    <div class="innerTab-container" align="left">
        <table class="tab" cellpadding="0" cellspacing="0" summary=""> 
            <tbody>
                <tr>
	                <th style="width: 300px"> 
	                    <div align="right">
	                        <kul:htmlAttributeLabel attributeEntry="${attributes.committeeId}" />
	                    </div>
	                </th>
	                <td style="width : 150px">
                        	                
	                    <c:set var="docNumber" value="${KualiForm.document.protocol.protocolNumber}" />
                        <html:select property="actionHelper.assignCmtSchedBean.committeeId" onchange="onlyLoadScheduleDates('actionHelper.assignCmtSchedBean.committeeId', '${docNumber}', 'actionHelper.assignCmtSchedBean.scheduleId');" >                               
                            <c:forEach items="${KualiForm.actionHelper.assignCmtSchedActionCommitteeIdByUnitKeyValues}" var="option" >
                                <c:choose>                      
                                    <c:when test="${KualiForm.actionHelper.assignCmtSchedBean.committeeId == option.key}">
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
	               
		            <th style="width: 150px"> 
		                <div align="right"><nobr>
		                    <kul:htmlAttributeLabel attributeEntry="${attributes.scheduleId}" /></nobr>
		                </div>
		            </th>
		            <td>
		               <nobr>
				           <kul:htmlControlAttribute property="actionHelper.assignCmtSchedBean.scheduleId" 
				                                         attributeEntry="${attributes.scheduleId}" />
				            <noscript>
                            <html:image property="methodToCall.refreshPage.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-refresh.gif' styleClass="tinybutton"/>
                            </noscript>
		               </nobr>
		            </td>
	            </tr>
                
                <tr>
					<td align="center" colspan="4">
						<div align="center">
							<html:image property="methodToCall.assignCommitteeSchedule.anchor${tabKey}"
							            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
						</div>
	                </td>
                </tr>
            </tbody>
        </table>
    </div>
    
</kul:innerTab>

</kra:permission>
