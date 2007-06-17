<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<tr>
  <td><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
  <td> 
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t" align=center>
     <tr>
		<td class="thnormal" colspan=2 align=center height=30><strong>Document</strong></td>
	  </tr>
	  <tr>
	    <td width="33%" class="headercell3-b-l" align=right><b> Document ID: </b></td>
	    <td width="66%" class="headercell3-b-l"><c:out value="${DocumentOperationForm.routeHeader.routeHeaderId}" /> &nbsp;</td>
	  </tr>
	  <tr>
  	    <td width="33%" align=right class="thnormal">Document Version:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeHeader.docVersion}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Initiator ID:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeHeader.initiatorWorkflowId}" />&nbsp;</td>
  	  </tr>
  	   <tr>
  	    <td width="33%" align=right class="thnormal">Route Status:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeHeader.docRouteStatusLabel}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Route Level:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeHeader.docRouteLevel}" />&nbsp;</td>
  	  </tr>
  	   <tr>
  	    <td width="33%" align=right class="thnormal">Create Date:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.createDate}" />&nbsp;</td>
  	  </tr>	  		 
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Document Status Modification Date:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.statusModDate}" /></td>
  	  </tr>	
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Approved Date:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.approvedDate}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Finalized Date:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.finalizedDate}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Route Status Modification Date:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeStatusDate}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Route Level Modification Date:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeLevelDate}" />&nbsp;</td>
  	  </tr>
	  <tr>
  	    <td width="33%" align=right class="thnormal">Doc Type ID:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeHeader.documentTypeId}" />&nbsp;</td>
	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Doc Title:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeHeader.docTitle}"/>&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Application Doc ID:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeHeader.appDocId}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Override Indicator:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeHeader.overrideInd}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Lock Code:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeHeader.lockCode}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Doc Content:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeHeader.docContent}" />&nbsp;</td>
  	  </tr>
	</table>
  </td>
  <td width="20" height="30">&nbsp;</td>
</tr>
