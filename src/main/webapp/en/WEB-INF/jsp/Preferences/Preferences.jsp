<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>
<%@ page import="edu.iu.uis.eden.EdenConstants" %>
<%@ page import="edu.iu.uis.eden.preferences.PreferencesService" %>

<html-el:html>
<head>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
<title>Workflow Preferences</title>
</head>
<body>
<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
  <tr>
    <td><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5></td>
    <td width="90%"><html-el:link action="ActionList">Return to Action List</html-el:link></td>
  </tr>
</table>

<br>
<jsp:include page="../WorkflowMessages.jsp" flush="true" />

<table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="20">&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td>
      <strong>Action List Preferences</strong>
    </td>
    <td></td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
  	<td></td>
  	<td>
<html-el:form action="/Preferences.do">
<html-el:hidden property="returnMapping"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t" >
	<tr>
		<td class="thnormal">Automatic Refresh Rate:(in whole minutes - 0 is no automatic refresh.)</td>
        <td class="datacell"><html-el:text property="preferences.refreshRate" size="3" /></td>
	</tr>
<%--
        <tr>
          <td class="thnormal" width="50%">Action List Contents</td>
          <td class="datacell">
				<html-el:select property="preferences.actionListContentType">
				  <html-el:options collection="actionListContent" labelProperty="value" property="key"/>
				</html-el:select>
          </td>
        </tr>
--%>
        <tr>
          <td class="thnormal" width="50%">Action List Page Size</td>
          <td class="datacell"><html-el:text property="preferences.pageSize" size="3" /></td>
        </tr>
        <tr>
          <td class="thnormal">Email Notification</td>
          <td class="datacell">
            <html-el:select property="preferences.emailNotification">
              <html-el:option value="${Constants.EMAIL_RMNDR_NO_VAL}">None</html-el:option>
              <html-el:option value="${Constants.EMAIL_RMNDR_DAY_VAL}">Daily</html-el:option>
              <html-el:option value="${Constants.EMAIL_RMNDR_WEEK_VAL}">Weekly</html-el:option>
              <html-el:option value="${Constants.EMAIL_RMNDR_IMMEDIATE}">Immediate</html-el:option>
            </html-el:select>
            <bean-el:message key="actionlist.help.emailNotification"/>
          </td>
        </tr>
        <tr>
          <td class="thnormal">Send Email Notifications for Documents where I am a Primary Delegate</td>
          <td class="datacell">Yes <html-el:radio property="preferences.notifyPrimaryDelegation" value="${Constants.PREFERENCES_YES_VAL}"/>
                No <html-el:radio property="preferences.notifyPrimaryDelegation" value="${Constants.PREFERENCES_NO_VAL}"/>
          </td>
        </tr>
        <tr>
          <td class="thnormal">Send Email Notifications for Documents where I am a Secondary Delegate</td>
          <td class="datacell">Yes <html-el:radio property="preferences.notifySecondaryDelegation" value="${Constants.PREFERENCES_YES_VAL}"/>
                No <html-el:radio property="preferences.notifySecondaryDelegation" value="${Constants.PREFERENCES_NO_VAL}"/>
          </td>
        </tr>
        <tr>
          <td class="thnormal" width="50%">Delegator Filter</td>
          <td class="datacell">
				<html-el:select property="preferences.delegatorFilter">
				  <html-el:options collection="delegatorFilter" labelProperty="value" property="key"/>
				</html-el:select>
          </td>
        </tr>
        <tr>
          <td class="thnormal" colspan="2">Fields Displayed In Action List</td>
        </tr>

        <tr>
          <td class="thnormal">Document Type</td>
          <td class="datacell">Yes <html-el:radio property="preferences.showDocType" value="${Constants.PREFERENCES_YES_VAL }"/>
          		No <html-el:radio property="preferences.showDocType" value="${Constants.PREFERENCES_NO_VAL }"/>
          </td>
        </tr>

        <tr>
          <td class="thnormal">Title</td>
          <td class="datacell">Yes <html-el:radio property="preferences.showDocTitle" value="${Constants.PREFERENCES_YES_VAL }"/>
          		No <html-el:radio property="preferences.showDocTitle" value="${Constants.PREFERENCES_NO_VAL }"/>
          </td>
        </tr>

        <tr>
          <td class="thnormal">ActionRequested</td>
          <td class="datacell">Yes <html-el:radio property="preferences.showActionRequested" value="${Constants.PREFERENCES_YES_VAL }"/>
          		No <html-el:radio property="preferences.showActionRequested" value="${Constants.PREFERENCES_NO_VAL }"/>
          </td>
        </tr>

        <tr>
          <td class="thnormal">Initiator</td>
			<td class="datacell">Yes <html-el:radio property="preferences.showInitiator" value="${Constants.PREFERENCES_YES_VAL }"/>
          		No <html-el:radio property="preferences.showInitiator" value="${Constants.PREFERENCES_NO_VAL }"/>
          </td>
        </tr>
        
        <tr>
          <td class="thnormal">Delegator</td>
			<td class="datacell">Yes <html-el:radio property="preferences.showDelegator" value="${Constants.PREFERENCES_YES_VAL }"/>
          		No <html-el:radio property="preferences.showDelegator" value="${Constants.PREFERENCES_NO_VAL }"/>
          </td>
        </tr>

        <tr>
          <td class="thnormal">Date Created</td>
          <td class="datacell">Yes <html-el:radio property="preferences.showDateCreated" value="${Constants.PREFERENCES_YES_VAL }"/>
          		No <html-el:radio property="preferences.showDateCreated" value="${Constants.PREFERENCES_NO_VAL }"/>
          </td>
        </tr>

        <tr>
          <td class="thnormal">WorkGroup Request</td>
			<td class="datacell">Yes <html-el:radio property="preferences.showWorkgroupRequest" value="${Constants.PREFERENCES_YES_VAL }"/>
          		No <html-el:radio property="preferences.showWorkgroupRequest" value="${Constants.PREFERENCES_NO_VAL }"/>
          </td>
        </tr>

        <tr>
          <td class="thnormal">Document Route Status</td>
          <td class="datacell">Yes <html-el:radio property="preferences.showDocumentStatus" value="${Constants.PREFERENCES_YES_VAL }"/>
          		No <html-el:radio property="preferences.showDocumentStatus" value="${Constants.PREFERENCES_NO_VAL }"/>
          </td>
        </tr>

        <tr>
          <td class="thnormal">Clear FYI</td>
          <td class="datacell">Yes <html-el:radio property="preferences.showClearFyi" value="${Constants.PREFERENCES_YES_VAL }"/>
          		No <html-el:radio property="preferences.showClearFyi" value="${Constants.PREFERENCES_NO_VAL }"/>
          </td>
        </tr>

        <tr>
          <td colspan="2" class="thnormal">Document Route Status Colors for Actionlist Entries</td>
        </tr>
		<tr>
			<td class="thnormal">Saved</td>
			<td class="datacell">
			  <table>
			    <tr>
                  <c:forEach items="${Constants.ACTION_LIST_COLOR_PALETTE}" var="colorType">
		            <td bgcolor='<c:out value="${colorType.value}"/>'><html-el:radio property="preferences.colorSaved" value="${colorType.key}" /></td>
                  </c:forEach>
				</tr>
			  </table>
			</td>
		</tr>
		
		<tr>
			<td class="thnormal">Initiated</td>
			<td class="datacell">
			  <table>
			    <tr>
                  <c:forEach items="${Constants.ACTION_LIST_COLOR_PALETTE}" var="colorType">
		            <td bgcolor='<c:out value="${colorType.value}"/>'><html-el:radio property="preferences.colorInitiated" value="${colorType.key}" /></td>
                  </c:forEach>
				</tr>
			  </table>
			</td>
		</tr>

		<tr>
			<td class="thnormal">Disapproved</td>
			<td class="datacell">
			  <table>
			    <tr>
                  <c:forEach items="${Constants.ACTION_LIST_COLOR_PALETTE}" var="colorType">
		            <td bgcolor='<c:out value="${colorType.value}"/>'><html-el:radio property="preferences.colorDissaproved" value="${colorType.key}" /></td>
                  </c:forEach>
				</tr>
			  </table>
			</td>
		</tr>
		<tr>
			<td class="thnormal">Enroute</td>
			<td class="datacell">
			  <table>
			    <tr>
                  <c:forEach items="${Constants.ACTION_LIST_COLOR_PALETTE}" var="colorType">
		            <td bgcolor='<c:out value="${colorType.value}"/>'><html-el:radio property="preferences.colorEnroute" value="${colorType.key}" /></td>
                  </c:forEach>
				</tr>
			  </table>
			</td>
		</tr>
		<tr>
			<td class="thnormal">Approved</td>
			<td class="datacell">
			  <table>
			    <tr>
                  <c:forEach items="${Constants.ACTION_LIST_COLOR_PALETTE}" var="colorType">
		            <td bgcolor='<c:out value="${colorType.value}"/>'><html-el:radio property="preferences.colorApproved" value="${colorType.key}" /></td>
                  </c:forEach>
				</tr>
			  </table>
			</td>
		</tr>
		<tr>
			<td class="thnormal">Final</td>
			<td class="datacell">
			  <table>
			    <tr>
                  <c:forEach items="${Constants.ACTION_LIST_COLOR_PALETTE}" var="colorType">
		            <td bgcolor='<c:out value="${colorType.value}"/>'><html-el:radio property="preferences.colorFinal" value="${colorType.key}" /></td>
                  </c:forEach>
				</tr>
			  </table>
			</td>
		</tr>
		<tr>
			<td class="thnormal">Processed</td>
			<td class="datacell">
			  <table>
			    <tr>
                  <c:forEach items="${Constants.ACTION_LIST_COLOR_PALETTE}" var="colorType">
		            <td bgcolor='<c:out value="${colorType.value}"/>'><html-el:radio property="preferences.colorProccessed" value="${colorType.key}" /></td>
                  </c:forEach>
				</tr>
			  </table>
			</td>
		</tr>
		<tr>
			<td class="thnormal">Exception</td>
			<td class="datacell">
			  <table>
			    <tr>
                  <c:forEach items="${Constants.ACTION_LIST_COLOR_PALETTE}" var="colorType">
		            <td bgcolor='<c:out value="${colorType.value}"/>'><html-el:radio property="preferences.colorException" value="${colorType.key}" /></td>
                  </c:forEach>
				</tr>
			  </table>
			</td>
		</tr>
		<tr>
			<td class="thnormal">Canceled</td>
			<td class="datacell">
			  <table>
			    <tr>
                  <c:forEach items="${Constants.ACTION_LIST_COLOR_PALETTE}" var="colorType">
		            <td bgcolor='<c:out value="${colorType.value}"/>'><html-el:radio property="preferences.colorCanceled" value="${colorType.key}" /></td>
                  </c:forEach>
				</tr>
			  </table>
			</td>
		</tr>
		
		<tr>
			<td class="thnormal" colspan="2" align="center">
                <html-el:image src="images/buttonsmall_save.gif" align="absmiddle" property="methodToCall.save" />&nbsp;
                <a href="javascript:document.forms[0].reset()"><img src="images/buttonsmall_reset.gif" border=0 alt="reset" align="absmiddle"></a>
			</td>
		</tr>
	</table>

</html-el:form>
</td>
<td></td>
</tr>
</table>
<jsp:include page="../BackdoorMessage.jsp" flush="true"/>

</body>
</html-el:html>