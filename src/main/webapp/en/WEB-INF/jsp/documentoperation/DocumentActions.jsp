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
		<td class="thnormal" colspan=2 align=center height=30><strong>Document Actions</strong></td>
	  </tr>
	      <tr>
	  	    <td width="33%" align="right" class="thnormal"><input type="button" value="Flush Rule Cache" onclick="setMethodToCallAndSubmit('flushRuleCache')"></td>
	  	    <td width="66%" class="datacell">&nbsp;</td>
	  	  </tr>
		  <tr>
	  	    <td width="33%" align="right" class="thnormal"><input type="button" value="Queue Document" onclick="setMethodToCallAndSubmit('queueDocument')"></td>
	  	    <td width="66%" class="datacell">&nbsp;</td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal"><input type="button" value="Index Searchable Attributes" onclick="setMethodToCallAndSubmit('indexSearchableAttributes')"></td>
	  	    <td width="66%" class="datacell">&nbsp;</td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal"><input type="button" value="Queue Document Requeuer" onclick="setMethodToCallAndSubmit('queueDocumentRequeuer')"></td>
	  	    <td width="66%" class="datacell">&nbsp;</td>
	  	  </tr>
	  	  <tr>
		    <td width="33%" class="thnormal" align=right><input type="button" value="Queue Document Blanket Approve" onclick="setMethodToCallAndSubmit('blanketApproveDocument')"><br>
		    <input type="button" value="Queue Document Move" onclick="setMethodToCallAndSubmit('moveDocument')"></td>
		    <td width="66%" class="datacell">User: <html-el:text property="blanketApproveUser"/><br>
		    Action Taken Id: <html-el:text property="blanketApproveActionTakenId"/><br>
		    Node Names: <html-el:text property="blanketApproveNodes"/>
		    </td>
		  </tr>
		  <tr>
		    <td width="33%" class="thnormal" align=right><input type="button" value="Queue Action Invocation" onclick="setMethodToCallAndSubmit('queueActionInvocation')"></td>
		    <td width="66%" class="datacell">User: <html-el:text property="actionInvocationUser"/><br>
		    Action Item Id: <html-el:text property="actionInvocationActionItemId"/><br>
		    Action Code: <html-el:text property="actionInvocationActionCode"/>
		    </td>
		  </tr>
	  </table>
    </td>
    <td width="20" height="30">&nbsp;</td>
  </tr>
