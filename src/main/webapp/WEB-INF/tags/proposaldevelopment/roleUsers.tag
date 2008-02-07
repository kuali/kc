<%--
 Copyright 2005-2007 The Kuali Foundation.
 
 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<%@ attribute name="id" required="true" %>
<%@ attribute name="roleName" required="true" %>
<%@ attribute name="userList" required="true" type="java.util.List" %>

<tr>
	<th align="right" valign="middle" width="20%">${roleName}:</th>
    <td id="${id}" align="left" valign="middle">
      	<c:forEach var="name" items="${userList}" varStatus="status">
       		<c:if test="${status.index != 0}">;&nbsp;</c:if>
	        ${name}
		</c:forEach>
    </td>
</tr>