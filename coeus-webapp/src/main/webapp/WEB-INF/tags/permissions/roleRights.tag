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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<%@ attribute name="roleName" required="true" %>
<%@ attribute name="permissions" required="true" type="java.util.List" %>

<div class="tab-container" align="center" style="margin:0px; padding:0px; border-width:0px">
	<h3>
    	<span class="subhead-left">${roleName}</span>
 	</h3>
 
 	<table cellpadding="0" cellspacing="0" summary="">
    	<c:forEach var="permission" items="${permissions}" varStatus="status">
    		<tr>
				<th align="right" valign="middle" width="40%">${permission.name}:</th>
    			<td align="left" valign="middle">${permission.description}</td>
    		</tr>
	    </c:forEach>
    </table>
</div>
