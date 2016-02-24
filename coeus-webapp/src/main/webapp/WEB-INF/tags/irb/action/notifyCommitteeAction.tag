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

<c:set var="notifyAttributes" value="${DataDictionary.ProtocolNotifyCommitteeBean.attributes}" />
<c:set var="action" value="protocolProtocolActions" />

<c:set var="isOpen" value="false" />
<c:forEach items="${param}" var="par">
    <c:if test="${fn:startsWith(par.key, 'lookupActionNotifyCommitteeProtocol') and fn:startsWith(par.value, 'true')}">
        <c:set var="isOpen" value="true" />
    </c:if>
</c:forEach>
<c:set var="parentTabValue" value="Notify Committee" scope="request"/>
                                    
<kra:permission value="${KualiForm.actionHelper.canNotifyCommittee}">

	<kul:innerTab tabTitle="Notify Committee" parentTab="" defaultOpen="${isOpen}" tabErrorKey="actionHelper.protocolNotifyCommitteeBean*">
    	<div class="innerTab-container" align="left">
        	<table class="tab" cellpadding="0" cellspacing="0" summary="">
            	<tbody>
    	            <tr>
        	            <th width="15%"> 
            	            <div align="right">
	            	            <nobr>
	                	            <kul:htmlAttributeLabel attributeEntry="${notifyAttributes.committeeId}" />
	                    	    </nobr>
		                    </div>
    	            	</th>
    	            	<td>
                        	<c:if test="${KualiForm.actionHelper.showCommittee}">
	                        	<nobr>
		                        	             
			                        <html:select property="actionHelper.protocolNotifyCommitteeBean.committeeId">                               
		                            	<c:forEach items="${KualiForm.actionHelper.notifyCmtActionCommitteeIdByUnitKeyValues}" var="option" >
		                                	<c:choose>                      
		                                    	<c:when test="${KualiForm.actionHelper.protocolNotifyCommitteeBean.committeeId == option.key}">
		                                        	<option value="${option.key}" selected="selected">${option.value}</option>
		                                    	</c:when>
		                                    	<c:otherwise>                               
		                                        	<c:out value="${option.value}"/>
		                                        	<option value="${option.key}">${option.value}</option>
		                                    	</c:otherwise>
		                                	</c:choose>                                                
		                            	</c:forEach>
		                        	</html:select>
		                        </nobr>
                        	</c:if>
                       	</td>
	        	    </tr>	
    	          
    	            <tr>
        	            <th width="15%"> 
            	            <div align="right">
                	            <nobr>
                    	            <kul:htmlAttributeLabel attributeEntry="${notifyAttributes.comment}" />
                        	    </nobr>
	                        </div>
    	                </th>
        	            <td>
            	            <nobr>
                	            <kul:htmlControlAttribute property="actionHelper.protocolNotifyCommitteeBean.comment" 
                    	                                  attributeEntry="${notifyAttributes.comment}" />
                        	</nobr>
	                    </td>
    	            </tr>
        	        
            	    <tr>
                	    <th width="15%"> 
                    	    <div align="right">
                        	    <nobr>
                            	    <kul:htmlAttributeLabel attributeEntry="${notifyAttributes.actionDate}" />
	                            </nobr>
    	                    </div>
        	            </th>
            	        <td>
                	        <nobr>
                    	        <kul:htmlControlAttribute property="actionHelper.protocolNotifyCommitteeBean.actionDate" 
                        	                              attributeEntry="${notifyAttributes.actionDate}"  />
	                        </nobr>
    	                </td>
        	        </tr>
            	    <tr>
                	    <td align="center" colspan="2">
                        	<div align="center">
                    	        <html:image property="methodToCall.notifyCommitteeProtocol.anchor${tabKey}"
                            	            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' 
                                	        styleClass="tinybutton"/>
	                        </div>
    	                </td>
        		        </tr>
	            </tbody>
    	    </table>       
	    </div>
    
	</kul:innerTab>

</kra:permission>
