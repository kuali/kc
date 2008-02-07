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
<%@ attribute name="roleName" required="true" %>
<%@ attribute name="permissions" required="true" type="java.util.List" %>

<div class="tab-container" align="center" style="margin:0px; padding:0px; border-width:0px">
	<div class="h2-container">
    	<span class="subhead-left"><h2>${roleName}</h2></span>
 	</div>
 
 	<table cellpadding="0" cellspacing="0" summary="">
    	<c:forEach var="permission" items="${permissions}" varStatus="status">
    		<tr>
				<th align="right" valign="middle" width="40%">${permission.name}:</th>
    			<td align="left" valign="middle">${permission.description}</td>
    		</tr>
	    </c:forEach>
    </table>
</div>