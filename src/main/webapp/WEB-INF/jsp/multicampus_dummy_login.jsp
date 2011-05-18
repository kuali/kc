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

    <style type="text/css">
        div.body {
            background-image: url("${ConfigProperties.application.url}/rice-portal/images/os-guy.gif");
            background-repeat: no-repeat;
            padding-top: 5em;
        }

        table#login {
            margin: auto;
            background-color: #dfdda9;
            border: .5em solid #fffdd8;
            /* simple rounded corners for mozilla & webkit */
            -moz-border-radius: 10px;
            -webkit-border-radius: 10px;
        }

        table#login th {
            height: 30 px;
            padding-top: .8em;
            padding-bottom: .8em;
            color: #a02919;
            font-size: 2em;
        }

        #login td {
            padding: .2em;
            height: 20px;
        }

        #login .rightTd {
            padding-right: 1.2em;
        }

        #login .leftTd {
            padding-left: 1.2em;
        }

        table#login td#buttonRow {
            padding-top: 1em;
            padding-bottom: .6em;
        }

    </style>
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
                    <option value="${campus.campusCode}">${campus.campusName}</option>
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
              <!-- input type="image" title="Click to login." value="login" name="imageField" src="${pageContext.request.contextPath}/rice-portal/images/tinybutton-login.gif"/ -->
              </td>
            </tr>
          </tbody>
        </table>
</div>
</form>
</body>
