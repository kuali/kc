<%@ taglib uri="../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../tld/c.tld" prefix="c" %>
<%@ taglib uri="../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../tld/displaytag.tld" prefix="display-el" %>

<html>
<head>
<title>OneStart Workflow</title>
<link href="css/screen.css" rel="stylesheet" type="text/css">
</head>
<body>

<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
  <tr>
    <td><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5></td>
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

