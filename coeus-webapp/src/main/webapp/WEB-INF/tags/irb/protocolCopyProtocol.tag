<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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

<c:set var="openFlag" value="false" />
<c:forEach items="${param}" var="par">
    <c:if test="${fn:startsWith(par.key, 'command')==true and fn:startsWith(par.value, 'displayDocSearchView')==true}">
        <c:set var="openFlag" value="true" />
    </c:if>
</c:forEach>


<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="protocolAttributes" value="${DataDictionary.Protocol.attributes}" />
<c:set var="action" value="protocolActions" />

<kul:tab tabTitle="Copy to New Document" defaultOpen="${openFlag}" tabErrorKey="">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Copy to New Document</span>
			<span class="subhead-right"><kul:help parameterNamespace="KC-PROTOCOL" parameterDetailType="Document" parameterName="protocolActionCopyHelp" altText="help"/></span>
        </h3>
        
         <table cellpadding="0" cellspacing="0" summary="">
        
        	<tr>
        		<th align="right" valign="middle" width="35%">Protocol:</th>
        		<td align="left" valign="middle">yes</td>
        	</tr>
        
            <tr>
				<td align="center" colspan="2">
					<div align="center">
						<html:image property="methodToCall.copyProtocol.anchor${tabKey}"
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-copyprotocol.gif' styleClass="tinybutton"/>
					</div>
                </td>
			</tr>
        	
        </table>
    </div>
</kul:tab>
