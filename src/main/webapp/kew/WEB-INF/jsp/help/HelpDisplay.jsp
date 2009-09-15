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
