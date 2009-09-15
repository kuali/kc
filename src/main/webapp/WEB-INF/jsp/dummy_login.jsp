<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp" %>

<html>
  <head>
    <title>Login</title>
    <link href="${pageContext.request.contextPath}/kew/css/portal.css" rel="stylesheet" type="text/css">
  </head>
<body>
<form action="" method="post">
<table style="margin-top: 20px" border="0" align="center" cellpadding="0" cellspacing="0" cols="1" summary="">
  <tbody>
    <tr>
      <td><img src="${pageContext.request.contextPath}/kew/images/topleftcorner.gif" height="9" width="6"></td>
      <td class="uportal-channel-topborder"><img src="${pageContext.request.contextPath}/kew/images/transparent_002.gif" height="1" width="1"></td>
      <td><img src="${pageContext.request.contextPath}/kew/images/toprightcorner.gif" height="9" width="6"></td>
    </tr>
    <tr>
      <td class="uportal-channel-headerleftborder"><img src="${pageContext.request.contextPath}/kew/images/transparent_002.gif" alt="" width="6" height="1"></td>
      <td class="uportal-background-semidark" nowrap="nowrap"><span class="uportal-channel-title">Login</span></td>
      <td class="uportal-channel-headerrightborder"><img src="${pageContext.request.contextPath}/kew/images/transparent_002.gif" alt="" width="6" height="1"></td>
    </tr>
    <tr>
      <td><img src="${pageContext.request.contextPath}/kew/images/headerbottomleft.gif" alt="" width="6" height="8"></td>
      <td class="uportal-channel-headerbottomborder"><img src="${pageContext.request.contextPath}/kew/images/transparent_002.gif" alt="" width="1" height="1"></td>
      <td><img src="${pageContext.request.contextPath}/kew/images/headerbottomright.gif" alt="" width="6" height="8"></td>
    </tr>
    <tr>
      <td><img src="${pageContext.request.contextPath}/kew/images/channeltopleft.gif" alt="" width="6" height="7"></td>
      <td class="uportal-channel-channeltopborder"><img src="${pageContext.request.contextPath}/kew/images/transparent_002.gif" alt="" width="1" height="7"></td>
      <td ><img src="${pageContext.request.contextPath}/kew/images/channeltopright.gif" alt="" width="6" height="7"></td>
    </tr>
    <tr>
      <td class="uportal-channel-channelleftborder"><img src="${pageContext.request.contextPath}/kew/images/transparent_002.gif" alt="" width="6" height="1"></td>
      <td class="uportal-background-content">
        <table border="0" cellpadding="0" cellspacing="0" summary="">
          <tbody>
            <tr>
              <td align="right">Username: </td><td align="left"><input type="text" name="__login_user" value="admin" size="20"/></td>
            </tr>
            <c:if test="${requestScope.showPasswordField}">
            <tr>
              <td align="right">Password: </td><td align="left"><input type="password" name="__login_pw" value="admin" size="20"/></td>
            </tr>
            </c:if>
            <c:if test="${requestScope.invalidPassword}">
            <tr>
              <td align="center" colspan="2"><strong>Invalid username or password</strong></td>
            </tr>
            </c:if>
            <tr>
              <td align="center" colspan="2"><input type="submit" value="Login"/></td>
              <td><img src="${pageContext.request.contextPath}/kew/images/transparent_002.gif" height="1" width="1"></td>
            </tr>
          </tbody>
        </table>
      </td>
      <td class="uportal-channel-channelrightborder"><img src="${pageContext.request.contextPath}/kew/images/transparent_002.gif" alt="" width="1" height="1"></td>
    </tr>
    <tr>
      <td><img src="${pageContext.request.contextPath}/kew/images/bottomleftcorner.gif" alt="" width="6" height="6"></td>
      <td class="uportal-channel-bottomborder"><img src="${pageContext.request.contextPath}/kew/images/transparent_002.gif" alt="" width="1" height="1"></td>
      <td><img src="${pageContext.request.contextPath}/kew/images/bottomrightcorner.gif" alt="" width="6" height="6"></td>
    </tr>
  </tbody>
</table>
</form>
</body>