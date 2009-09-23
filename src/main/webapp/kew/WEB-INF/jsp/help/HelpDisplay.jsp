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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>


<table align="center" border="0" cellpadding="0" cellspacing="0" class="datatable-80">
  <tr>
	<th class="grid" align="right" width="33%">Help Id:</th>
    <td class="grid"><c:out value="${HelpForm.helpEntry.helpId}" /></td>
  </tr>
  <tr>
	<th class="grid" align="right" width="33%">Help Name:</th>
    <td class="grid"><c:out value="${HelpForm.helpEntry.helpName}" /></td>
  </tr>
  <tr>
	<th class="grid" align="right" width="33%">Help Key:</th>
    <td class="grid"><c:out value="${HelpForm.helpEntry.helpKey}" /></td>
  </tr>
  <tr>
	<th class="grid" align="right" width="33%">Help Text:</th>
    <td class="grid"><c:out value="${HelpForm.helpEntry.helpText}" escapeXml="false"/></td>
  </tr>
</table>
