<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>
 
<html-el:html>
<head>
<title>Help Pop</title>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="headercell1">
  <tr>
    <td>
      <img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;&nbsp;&nbsp;&nbsp;
    </td>
    <td width="90%">
      <a href="Help.do?methodToCall=getSearch">
        <span class="maintext">Search Help Entry</span></a>&nbsp;&nbsp;
    </td>
  </tr>
</table>

<table width="90%" border=0 align=center>
  <tr>
    <td>
      <table>
        <tr>
          <td valign='top' width='100%' colspan="2" class="channel-text">
            <br />        
            <strong>
              <center>
                  <c:out value="${HelpForm.helpEntry.helpName}"/>
              </center>
            </strong>        
           
            <p> 
              <c:out value="${HelpForm.helpEntry.helpText}" escapeXml="false"/>
            </p>
           
          </td>
        </tr>
        </table>

      </table>
      <br/>
    </td>
  </tr>
</table>

</body>
</html-el:html>