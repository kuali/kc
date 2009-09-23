<%--
 Copyright 2008-2009 The Kuali Foundation
 
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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp" %>
<html>
<head>
<title>Error</title>
<link href="css/screen.css" rel="stylesheet" type="text/css">
</head>
<body>

<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
  <tr>
    <td><img src="images/wf-logo.gif" alt="Workflow" width=150 height=21 hspace=5 vspace=5></td>
  </tr>
</table>
<br>
<br>
      <table width="80%" border="0" align="center" cellpadding="1" cellspacing="0" class="bord-r-t">
          <tr>
            <th height="30"  class="thnormal">
               <div align="left"><strong>Workflow Error</strong></div>
            </th>
          </tr>
          <tr>
            <td height=100 width="100%" align="middle" valign="center" class="datacell">
              <div align="center" class="error-text">
                <c:out escapeXml="false" value="${requestScope.WORKFLOW_ERROR}"/>&nbsp;&nbsp;<html-el:link page="/Feedback.do?exception=${requestScope.WORKFLOW_ERROR}" target="_blank">Contact Us for Assistance</html-el:link><br /><br />
			  </div>
            </td>
          </tr>
      </table>

</body>
</html>

