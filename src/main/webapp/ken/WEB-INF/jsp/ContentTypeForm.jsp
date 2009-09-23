<%--
 Copyright 2007-2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="Include.jsp"%>

<html>
<head>
<title>Kuali Enterprise Notification - Manage Content Types</title>
<meta name="Author" content="John Fereira">
<link href="css/notification.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript">
function addContentType() {
   document.forms['AddOrUpdateContentType'].action = 'AddContentType.form';
   document.forms['AddOrUpdateContentType'].submit();
}
function updateContentType() {
   document.forms['AddOrUpdateContentType'].action = 'UpdateContentType.form';
   document.forms['AddOrUpdateContentType'].submit();
}
function cancel() {
   document.forms['AddOrUpdateContentType'].action = 'ContentTypeManager.form';
   document.forms['AddOrUpdateContentType'].submit();
}

</script>
</head>
<body>

<div id="pagebody">

		<div style="padding: 5px">
								<div class="title">Content Type Manager</div>
								<form name="AddOrUpdateContentType" action="#"><input
									value="<c:out value="${notificationContentType.id}" />"
									type="hidden" name="id">
								<table  border="0" cellpadding="0" cellspacing="0" class="bord-r-t" width="80%">
									<tr>
										<td class="thnormal" width="30%">Content Type Name</td>
										<td class="datacell"><input
											value="<c:out value="${notificationContentType.name}" />"
											type="text" name="name" size="30"></td>
									</tr>

									<tr>
										<td class="thnormal" width="30%">Description</td>
										<td class="datacell"><input
											value="<c:out value="${notificationContentType.description}" />"
											type="text" name="description" size="80"></td>
									</tr>

									<tr>
										<td class="thnormal" width="30%">Namespace</td>
										<td class="datacell"><input
											value="<c:out value="${notificationContentType.namespace}" />"
											type="text" name="namespace" size="80"></td>
									</tr>

									<tr>
										<td class="thnormal" width="30%">XSD content</td>
										<td class="datacell"><textarea cols="80" name="xsd"
											rows="8"><c:out
											value="${notificationContentType.xsd}" /></textarea></td>
									</tr>

									<tr>
										<td class="thnormal" width="30%">XSL content</td>
										<td class="datacell"><textarea cols="80" name="xsl"
											rows="8"><c:out
											value="${notificationContentType.xsl}" /></textarea></td>
									</tr>
									<tr>
										<td align="center" class="thnormal" colspan="2">
                                        <c:if test="${actionrequested == 'add'}">
											<input type="hidden" name="requestaction" value="add" />
											<input name="userAction" title="Add" type="button" value="Add" onClick="addContentType();">
										</c:if> 
                                        <c:if test="${actionrequested == 'update'}">
											<input type="hidden" name="requestaction" value="update" />
											<input name="userAction" title="Update" type="button" value="Update" onClick="updateContentType();" />
										</c:if> 
                                        <input name="userAction" title="Cancel" type="button" value="cancel" onClick="cancel();" />
                                        </td>
									</tr>
								</table>
								</form>
        </div>                       

</div>

</body>
</html>
