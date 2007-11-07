<%--
 Copyright 2007 The Kuali Foundation.
 
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
<%@ include file="header.jsp" %>

<%-- NOTE THAT THIS PAGE NOW REQUIRES A TRANSACTION ID TO BE PART OF THE
     LOGIN FORM!  IF YOU HAVE A CUSTOM login.jsp, YOU MUST ADD THIS
     NEW POST INFORMATION. --%>

<p>
<% if (request.getAttribute("edu.yale.its.tp.cas.badUsernameOrPassword") 
       != null) { %>
<font color="red">Sorry, you entered an invalid UserID. <br />
  Please try again. 
</font>
<% } else if (request.getAttribute("edu.yale.its.tp.cas.service") == null) { %>
  You may establish authentication now in order to access protected
  services later.
<% } else if (request.getAttribute("edu.yale.its.tp.cas.badLoginTicket") != null) { %>
<%-- place a message here if you want --%>
<% } else { %>
  You have requested access to a site that requires authentication. 
<% } %>
</p>
</font>

<p>
<font face="Arial,Helvetica">Enter your UserID below; then click on the <b>Login</b>
button to continue.  A password is not required, because this is a </font><font face="Arial,Helvetica" color="red"><b>DEMO ONLY</b></font><font face="Arial,Helvetica"> authentication application.</font>
</p>

        <form method="post" name="login_form">

        <table bgcolor="#FFFFAA" align="center"><tr><td>

        <table border="0" cellpadding="0" cellspacing="5">


        <tr>
        <td><font face="Arial,Helvetica"><b>UserID:</b></td>
        <td>
        <input type="text" name="username" maxlength="10"></td>
        </tr>
        <!-- 
		<c:if test="${requestScope.showPasswordField}">
	        <tr>
	        <td><font face="Arial,Helvetica"><b>Password:</b></td>
	        <td>
	        <input type="text" name="password" maxlength="200"></td>
	        </tr>
        </c:if>
         -->

        <tr>
        <td colspan="2" align="left">
	<input type="checkbox" name="warn" value="true" />
        <small>
	    <small>Warn me before logging me in to other sites.</small>
        </small>
	</tr>

        <tr>
        <td colspan="2" align="right">
	<input type="hidden" name="lt" value="<%= request.getAttribute("edu.yale.its.tp.cas.lt") %>" />
        <input type="submit" value="Login">
        </td>
        </tr>

        </td></tr></table>
        </form>

        </td></tr></table>

</td></tr>

<tr><td colspan="2">
<center>
<font color="red" face="Arial,Helvetica">
<i><b>For security reasons, quit your web browser when you are done
accessing services that require authentication!</b></i>
</font>
</center>
</td></tr>


<tr><td colspan="2">

</td></tr>

</table>
</table>
</table>
