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
<%@ attribute name="id" required="true" %>
<%@ attribute name="roleName" required="true" %>
<%@ attribute name="userList" required="true" type="java.util.List" %>

<tr>
	<th align="right" valign="middle" width="20%">${roleName}:</th>
    <td id="${id}" align="left" valign="middle">
      	<c:forEach var="name" items="${userList}" varStatus="status"><c:if test="${status.index != 0}">;&nbsp;</c:if>${name}</c:forEach>
		&nbsp;
    </td>
</tr>
