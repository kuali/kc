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
            	            <nobr>
	            	            <kul:htmlControlAttribute property="actionHelper.protocolNotifyCommitteeBean.committeeId" attributeEntry="${notifyAttributes.committeeId}" readOnly="true" />
                    	    </nobr>
	                	</td>
	        	    </tr>	
    	            <tr>
        	            <th width="15%"> 
            	            <div align="right">
	            	            <nobr>
	                	            <kul:htmlAttributeLabel attributeEntry="${notifyAttributes.committeeName}" />
	                    	    </nobr>
		                    </div>
    	            	</th>
        	        	<td>
            	            <nobr>
	            	            <kul:htmlControlAttribute property="actionHelper.protocolNotifyCommitteeBean.committeeName" attributeEntry="${notifyAttributes.committeeName}" readOnly="true" />
                    	    </nobr>
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
