<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>


<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
  <tr>
	<td class="thnormal" >Help Id</td>
    <td class="datacell"><c:out value="${HelpForm.helpEntry.helpId}" /></td>
  </tr>
  <tr>
	<td class="thnormal" >Help Name</td>
    <td class="datacell"><c:out value="${HelpForm.helpEntry.helpName}" /></td>
  </tr>
  <tr>
	<td class="thnormal" >Help Key</td>
    <td class="datacell"><c:out value="${HelpForm.helpEntry.helpKey}" /></td>
  </tr>
  <tr>
	<td class="thnormal" >Help Text</td>
    <td class="datacell"><c:out value="${HelpForm.helpEntry.helpText}" escapeXml="false"/></td>
  </tr>
</table>
