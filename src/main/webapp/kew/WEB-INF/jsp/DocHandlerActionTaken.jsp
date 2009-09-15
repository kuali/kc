<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp" %>
<html>
  <head>
    <title>DocHandlerActionTaken</title>
    <link href="<c:out value="${resourcePath}"/>css/screen.css" rel="stylesheet" type="text/css">
  </head>

  <body>

    <table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
      <tr>
        <td><img src="<c:out value="${resourcePath}"/>images/wf-logo.gif" alt="Workflow" width=150 height=21 hspace=5 vspace=5></td>
      </tr>
    </table>

    <br>
    <jsp:include page="WorkflowMessages.jsp" flush="true" />

    <br>

   <jsp:include page="BackdoorMessage.jsp" flush="true"/>

  </body>
</html>
