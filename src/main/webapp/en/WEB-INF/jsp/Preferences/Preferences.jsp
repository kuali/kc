<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>
<%@ page import="org.kuali.rice.kew.preferences.service.PreferencesService" %>

<html-el:html>
<head>
<link href="../kr/css/kuali.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
<title class="pagetitle" >Workflow Preferences</title>
</head>
<body>
<!-- table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1"-->
<div class="headerarea-small" id="headerarea-small">
<table width="100%" >
  <tr>
    <td><h1>Action List Preferences</h1></td>
    <td width="60%"><html-el:link action="ActionList">Return to Action List</html-el:link></td>
  	</tr>
</table>
<jsp:include page="../WorkflowMessages.jsp" flush="true" />
</div>
 <input class="tinybutton" name="methodToCall.search" src="images/pixel_clear.gif" type="image" border="0" height="0" width="0"><br><br>
  <table width="100%" cellpadding="0" cellspacing="0">
  <tr>
    <td class="column-left"><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="t3" summary="">
        <tbody>
          <tr>
            <td><img src="images/pixel_clear.gif" alt="" width="12" height="12" class="tl3"></td>
            <td align="right"><img src="images/pixel_clear.gif" alt="" width="12" height="12" class="tr3"></td>
          </tr>
        </tbody>
      </table>
<html-el:form action="/Preferences.do">
<html-el:hidden property="returnMapping"/>
<div id="workarea" >
  <div class="tab-container" align="center">
<table width="100%" class="datatable-80" align="center">
	<tbody id="G" style="display: nonee;">
            </tbody>
            <tbody id="G448" style="display: none;">
            </tbody>
            <tbody id="G449" style="display: none;">
            </tbody>
            <tbody id="G2449" style="display: none;">
            </tbody>
            <tbody id="G55" style="display: none;">
            </tbody>
            <tbody id="G56" style="display: none;">
            </tbody>
            <tbody id="G57" style="display: none;">
            </tbody>
            <tbody id="G58" style="display: none;">
            </tbody>
            <tbody id="G538" style="display: none;">
            </tbody>
            <tr>
		<td colspan="2" class="subhead" >General</td>
	</tr>
	<tr>
		<th ><div align="right">Automatic Refresh Rate:</div></th>
        <td class="datacell"><html-el:text property="preferences.refreshRate" size="3" />
         in whole minutes - 0 is no automatic refresh.</td>
	</tr>
    <tr>
          <th  width="50%"><div align="right">Action List Page Size</div></th>
          <td class="datacell"><html-el:text property="preferences.pageSize" size="3" /></td>
        </tr>
        <tr>
           <th ><div align="right">Email Notification</div></th>
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
          <th ><div align="right">Receive Primary Delegate Emails</div></th>
          <td class="datacell"><html-el:checkbox styleClass="nobord" property="preferences.notifyPrimaryDelegation" value="${Constants.PREFERENCES_YES_VAL}"/></td>
        </tr>
        <tr>
           <th ><div align="right">Receive Secondary Delegate Emails</div></th>
           <td class="datacell"><html-el:checkbox styleClass="nobord" property="preferences.notifySecondaryDelegation" value="${Constants.PREFERENCES_YES_VAL}"/></td>
        </tr>
        <tr>
          <th  width="50%"><div align="right">Delegator Filter</div></th>
          <td class="datacell">
				<html-el:select property="preferences.delegatorFilter">
				  <html-el:options collection="delegatorFilter" labelProperty="value" property="key"/>
				</html-el:select>
          </td>
        </tr>
        <tr>
           <td colspan="2" class="subhead" >Fields Displayed In Action List</td>
        </tr>

        <tr>
          <th ><div align="right">Document Type</div></th>
          <td class="datacell"><html-el:checkbox styleClass="nobord" property="preferences.showDocType" value="${Constants.PREFERENCES_YES_VAL }"/></td>
        </tr>

        <tr>
          <th ><div align="right">Title</div></th>
          <td class="datacell"><html-el:checkbox styleClass="nobord" property="preferences.showDocTitle" value="${Constants.PREFERENCES_YES_VAL }"/></td>
        </tr>

        <tr>
          <th ><div align="right">ActionRequested</div></th>
          <td class="datacell"><html-el:checkbox styleClass="nobord" property="preferences.showActionRequested" value="${Constants.PREFERENCES_YES_VAL }"/></td>
        </tr>

        <tr>
          <th ><div align="right">Initiator</div></th>
			<td class="datacell"><html-el:checkbox styleClass="nobord" property="preferences.showInitiator" value="${Constants.PREFERENCES_YES_VAL }"/>
          </td>
        </tr>

        <tr>
          <th ><div align="right">Delegator</div></th>
			<td class="datacell"><html-el:checkbox styleClass="nobord" property="preferences.showDelegator" value="${Constants.PREFERENCES_YES_VAL }"/>
          </td>
        </tr>

        <tr>
          <th ><div align="right">Date Created</div></th>
          <td class="datacell"><html-el:checkbox styleClass="nobord" property="preferences.showDateCreated" value="${Constants.PREFERENCES_YES_VAL }"/>
          </td>
        </tr>
		<tr>
          <th ><div align="right">Date Approved</div></th>
          <td class="datacell"><html-el:checkbox styleClass="nobord" property="preferences.showDateApproved" value="${Constants.PREFERENCES_YES_VAL }"/>
          </td>
        </tr>
        <tr>
          <th ><div align="right">Current Route Node(s)</div></th>
			<td class="datacell"><html-el:checkbox styleClass="nobord" property="preferences.showCurrentNode" value="${Constants.PREFERENCES_YES_VAL }"/>
          		</td>
        </tr>
        <tr>
          <th ><div align="right">WorkGroup Request</div></th>
			<td class="datacell"><html-el:checkbox styleClass="nobord" property="preferences.showWorkgroupRequest" value="${Constants.PREFERENCES_YES_VAL }"/>          </td>
        </tr>

        <tr>
          <th ><div align="right">Document Route Status</div></th>
          <td class="datacell"> <html-el:checkbox styleClass="nobord" property="preferences.showDocumentStatus" value="${Constants.PREFERENCES_YES_VAL }"/>          </td>
        </tr>

        <tr>
          <th ><div align="right">Clear FYI</div></th>
          <td class="datacell"><html-el:checkbox styleClass="nobord" property="preferences.showClearFyi" value="${Constants.PREFERENCES_YES_VAL }"/>
    		</td>
        </tr>

		<c:if test="${PreferencesForm.showOutbox }">
	        <tr>
	          <th ><div align="right">Use Outbox</div></th>
	          <td class="datacell"><html-el:checkbox styleClass="nobord" property="preferences.useOutbox" value="${Constants.PREFERENCES_YES_VAL }"/>	          </td>
	        </tr>
        </c:if>

        <tr>
         <td colspan="2" class="subhead" >Document Route Status Colors for Actionlist Entries</td>
        </tr>
		<tr>
			<th class="thnormal"><div align="right">Saved</div></th>
			<td>
			  <table style="border:none">
			    <tbody><tr>
                  <c:forEach items="${Constants.ACTION_LIST_COLOR_PALETTE}" var="colorType">
		            <td bgcolor='<c:out value="${colorType.value}"/>' style=" border:none; background-color:${colorType.value}"><div align="center"><html-el:radio styleClass="nobord" property="preferences.colorSaved" value="${colorType.key}" /></div></td>
                  </c:forEach>
				</tr>
			  </tbody>
			 </table>
			</td>
		</tr>

		<tr>
			<th class="thnormal"><div align="right">Initiated</div></th>
			<td>
			  <table style="border:none">
                <tbody>
			    <tr>
                  <c:forEach items="${Constants.ACTION_LIST_COLOR_PALETTE}" var="colorType">
		            <td bgcolor='<c:out value="${colorType.value}"/>' style=" border:none; background-color:${colorType.value}"><div align="center"><html-el:radio styleClass="nobord" property="preferences.colorInitiated" value="${colorType.key}" /></div></td>
                  </c:forEach>
				</tr>
			  </table>
			</td>
		</tr>

		<tr>
			<th class="thnormal"><div align="right">Disapproved</div></th>
			<td>
			  <table style="border:none">
                <tbody>
			    <tr>
                  <c:forEach items="${Constants.ACTION_LIST_COLOR_PALETTE}" var="colorType">
		            <td bgcolor='<c:out value="${colorType.value}"/>' style=" border:none; background-color:${colorType.value}"><div align="center"><html-el:radio  styleClass="nobord" property="preferences.colorDissaproved" value="${colorType.key}" /></div></td>
                  </c:forEach>
				</tr>
			  </table>
			</td>
		</tr>
		<tr>
			<th class="thnormal"><div align="right">Enroute</div></th>
			<td>
			  <table style="border:none">
                <tbody>
			    <tr>
                  <c:forEach items="${Constants.ACTION_LIST_COLOR_PALETTE}" var="colorType">
		            <td bgcolor='<c:out value="${colorType.value}"/>' style=" border:none; background-color:${colorType.value}"><div align="center"><html-el:radio styleClass="nobord" property="preferences.colorEnroute" value="${colorType.key}" /></div></td>
                  </c:forEach>
				</tr>
			  </table>
			</td>
		</tr>
		<tr>
			<th class="thnormal"><div align="right">Approved</div></th>
			<td>
			  <table style="border:none">
                <tbody>
			    <tr>
                  <c:forEach items="${Constants.ACTION_LIST_COLOR_PALETTE}" var="colorType">
		            <td bgcolor='<c:out value="${colorType.value}"/>' style=" border:none; background-color:${colorType.value}"><div align="center"><html-el:radio styleClass="nobord" property="preferences.colorApproved" value="${colorType.key}" /></div></td>
                  </c:forEach>
				</tr>
			  </table>
			</td>
		</tr>
		<tr>
			<th class="thnormal"><div align="right">Final</div></th>
			<td>
			  <table style="border:none">
                <tbody>
			    <tr>
                  <c:forEach items="${Constants.ACTION_LIST_COLOR_PALETTE}" var="colorType">
		            <td bgcolor='<c:out value="${colorType.value}"/>' style=" border:none; background-color:${colorType.value}"><div align="center"><html-el:radio styleClass="nobord" property="preferences.colorFinal" value="${colorType.key}" /></div></td>
                  </c:forEach>
				</tr>
			  </table>
			</td>
		</tr>
		<tr>
			<th class="thnormal"><div align="right">Processed</div></th>
			<td>
			  <table style="border:none">
                <tbody>
			    <tr>
                  <c:forEach items="${Constants.ACTION_LIST_COLOR_PALETTE}" var="colorType">
		            <td bgcolor='<c:out value="${colorType.value}"/>' style=" border:none; background-color:${colorType.value}"><div align="center"><html-el:radio styleClass="nobord" property="preferences.colorProccessed" value="${colorType.key}" /></div></td>
                  </c:forEach>
				</tr>
			  </table>
			</td>
		</tr>
		<tr>
			<th class="thnormal"><div align="right">Exception</div></th>
			<td>
			  <table style="border:none">
                <tbody>
			    <tr>
                  <c:forEach items="${Constants.ACTION_LIST_COLOR_PALETTE}" var="colorType">
		            <td bgcolor='<c:out value="${colorType.value}"/>' style=" border:none; background-color:${colorType.value}"><div align="center"><html-el:radio styleClass="nobord" property="preferences.colorException" value="${colorType.key}" /></div></td>
                  </c:forEach>
				</tr>
			  </table>
			</td>
		</tr>
		<tr>
			<th class="thnormal"><div align="right">Canceled</div></th>
			<td>
			  <table style="border:none">
                <tbody>
			    <tr>
                  <c:forEach items="${Constants.ACTION_LIST_COLOR_PALETTE}" var="colorType">
		            <td bgcolor='<c:out value="${colorType.value}"/>' style=" border:none; background-color:${colorType.value}"><div align="center"><html-el:radio styleClass="nobord" property="preferences.colorCanceled" value="${colorType.key}" /></div></td>
                  </c:forEach>
				</tr>
			  </table>
			</td>
		</tr>
	  </table>
        </div>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="b3" summary="">
          <tr>
            <td align="left" class="footer"><img src="images/pixel_clear.gif" alt="" width="12" height="14" class="bl3"></td>
            <td align="right" class="footer-right"><img src="images/pixel_clear.gif" alt="" width="12" height="14" class="br3"></td>
          </tr>
        </table>
	 <div class="globalbuttons">
                <html-el:image style="border-width:0px" property="methodToCall.save" src="images/buttonsmall_save.gif"  />
	 				<a href="javascript:document.forms[0].reset()"><img src="images/buttonsmall_reset.gif" alt="cancel" width="59" height="18" hspace="5" border="0"></a> </div>
    </div>
      <div class="globalbuttons"></div></td>
    <td class="column-right"><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
  </tr>
</table>
</html-el:form>
<jsp:include page="../BackdoorMessage.jsp" flush="true"/>

</body>
</html-el:html>