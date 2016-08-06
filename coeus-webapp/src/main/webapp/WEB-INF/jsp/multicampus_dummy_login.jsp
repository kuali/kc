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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp" %>

<html>
  <head>
    <title>Login</title>
    <c:forEach items="${fn:split(ConfigProperties.portal.css.files, ',')}" var="cssFile">
        <c:if test="${fn:length(fn:trim(cssFile)) > 0}">
            <link href="${pageContext.request.contextPath}/${fn:trim(cssFile)}" rel="stylesheet" type="text/css" />
        </c:if>
    </c:forEach>
    <c:forEach items="${fn:split(ConfigProperties.portal.javascript.files, ',')}" var="javascriptFile">
        <c:if test="${fn:length(fn:trim(javascriptFile)) > 0}">
            <script language="JavaScript" type="text/javascript" src="${ConfigProperties.application.url}/${fn:trim(javascriptFile)}"></script>
        </c:if>
    </c:forEach>
  </head>

<body OnLoad="document.loginForm.__login_user.focus();">

<form name="loginForm" action="" method="post">

<div class="body">
        <table id="login" cellspacing="0" cellpadding="0" align="center">
          <tbody>
            <tr>
              <th colspan="2">Login</th>
            </tr>
            <tr>
                <td class="leftTd" align="right" width="Infinity%">
                    <label>Username:&nbsp;</label>
                </td>
                <td class="rightTd" align="left">
                    <input type="text" name="__login_user" value="" size="20"/>
                </td>
            </tr>
            <c:set var="invalidAuthMsg" value="Invalid username" />
            <c:if test="${requestScope.showPasswordField}">
            <c:set var="invalidAuthMsg" value="Invalid username or password" />
            <tr>
            <td class="leftTd" width="Infinity%" align="right">
                <label>Password:&nbsp;</label>
            </td>
              <td class="rightTd" align="left"><input type="password" name="__login_pw" value="" size="20"/></td>
            </tr>
            </c:if>
            <tr>
              <td class="leftTd" width="Infinity%" align="right">
                <label>Campus:&nbsp;</label>
              </td>
              <td class="rightTd" align="left">
                <select name="__login_campusCode">
                  <c:forEach var="campus" items="${requestScope.campuses}">
                    <option value="${campus.code}">${campus.name}</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <c:if test="${requestScope.invalidAuth}">
            <tr>
              <td align="center" colspan="2"><strong>${invalidAuthMsg}</strong></td>
            </tr>
            </c:if>
            <tr>
              <td id="buttonRow" height="30" colspan="2" align="center"><input type="submit" value="Login"/>
              </td>
            </tr>
          </tbody>
        </table>
</div>
    <kul:csrf />
</form>
</body>
