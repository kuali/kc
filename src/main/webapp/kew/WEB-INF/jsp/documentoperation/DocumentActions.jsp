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

<tr>
  <td><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
  <td>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t" align="center">
	  <tr>
		<td class="thnormal" colspan="2" align="center" height="30"><strong>Document Actions</strong></td>
	  </tr>
	      <tr>
	  	    <td width="33%" align="right" class="thnormal">Flush Rule Cache<html-el:image property="methodToCall.flushRuleCache" src="${ConfigProperties.kew.url}/images/buttonsmall_submit.gif" alt="Flush Rule Cache" /></td>
	  	    <td width="66%" class="datacell">&nbsp;</td>
	  	  </tr>
		  <tr>
	  	    <td width="33%" align="right" class="thnormal">Queue Document<html-el:image property="methodToCall.queueDocument" src="${ConfigProperties.kew.url}/images/buttonsmall_submit.gif" alt="Queue Document" /></td>
	  	    <td width="66%" class="datacell">&nbsp;</td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">Index Searchable Attributes<html-el:image property="methodToCall.indexSearchableAttributes" src="${ConfigProperties.kew.url}/images/buttonsmall_submit.gif" alt="Index Searchable Attributes" /></td>
	  	    <td width="66%" class="datacell">&nbsp;</td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">Queue Document Requeuer<html-el:image property="methodToCall.queueDocumentRequeuer" src="${ConfigProperties.kew.url}/images/buttonsmall_submit.gif" alt="Queue Document Requeuer" /></td>
	  	    <td width="66%" class="datacell">&nbsp;</td>
	  	  </tr>
	  	  <tr>
		    <td width="33%" class="thnormal" align="right">Queue Document Blanket Approve<html-el:image property="methodToCall.blanketApproveDocument" src="${ConfigProperties.kew.url}/images/buttonsmall_submit.gif" alt="Queue Document Blanket Approve" /><br>
		    Queue Document Move<html-el:image property="methodToCall.moveDocument" src="${ConfigProperties.kew.url}/images/buttonsmall_submit.gif" alt="Queue Document Move" /></td>
		    <td width="66%" class="datacell">User: <html-el:text property="blanketApproveUser"/><br>
		    Action Taken Id: <html-el:text property="blanketApproveActionTakenId"/><br>
		    Node Names: <html-el:text property="blanketApproveNodes"/>
		    </td>
		  </tr>
		  <tr>
		    <td width="33%" class="thnormal" align="right">Queue Action Invocation<html-el:image property="methodToCall.queueActionInvocation" src="${ConfigProperties.kew.url}/images/buttonsmall_submit.gif" alt="Queue Action Invocation" /></td>
		    <td width="66%" class="datacell">User: <html-el:text property="actionInvocationUser"/><br>
		    Action Item Id: <html-el:text property="actionInvocationActionItemId"/><br>
		    Action Code: <html-el:text property="actionInvocationActionCode"/>
		    </td>
		  </tr>
	  </table>
    </td>
    <td width="20" height="30">&nbsp;</td>
  </tr>
