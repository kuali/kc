<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>
<%@ page import="java.util.Collection" %>


<html-el:html>
<head>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<title>Superuser Document Service</title>
<script language="JavaScript" src="scripts/en-common.js"></script>
</head>
<body>

<c:set var="ActionForm" value="${SuperUserForm}" scope="request" />
<%-- menu and logo --%>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="headercell1">
  <tr>
    <td>
        <img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;
    </td>
    <td width="90%">
        <a href="DocumentSearch.do?methodToCall=superUserSearch"><span class="maintext">Superuser Document Search</span></a>
    </td>
  </tr>
</table>

<br><%-- message and body of document --%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="20">&nbsp;</td>
  </tr>
  <tr>
    <td></td>
  	<td>
   		<jsp:include page="../WorkflowMessages.jsp" flush="true" />
   	</td>
   	<td></td>
  </tr>
  
  <tr>
    <td></td>
    <td>

  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t"> 
  
  <tr>
    <td height="30">
       <strong>Super User Action on RouteHeader <c:out value="${SuperUserForm.routeHeaderId}"/></strong>
       <br>
    </td>
  </tr>
  <tr>
    <td style="border=solid gray">
      <iframe src="<c:out value="${SuperUserForm.docHandlerUrl}"/>" width="100%" height="500" hspace="0" vspace="0" frameborder="0"></iframe>
    </td>
  </tr>

<c:if test="${SuperUserForm.routeHeader.docRouteStatus != Constants.ROUTE_HEADER_FINAL_CD && SuperUserForm.routeHeader.docRouteStatus != Constants.ROUTE_HEADER_CANCEL_CD}"> 
      <html-el:form method="post" action="/SuperUser.do">
      <html-el:hidden property="methodToCall" />
      <html-el:hidden property="routeHeaderId" value="${SuperUserForm.routeHeader.routeHeaderId}" />
	  <html-el:hidden property="docId" value="${SuperUserForm.flexDoc.routeHeaderId}" />
	  <html-el:hidden property="lookupableImplServiceName" />
  	  <html-el:hidden property="lookupType" />

<c:if test="${SuperUserForm.authorized}">  
  <tr>
    <td height=30>
      <table width="100%" border=0 cellpadding=0 cellspacing=0" class="bord-r-t">
        <tr>
          <td width="25%" align=right valign=top class="thnormal">Annotation:</td>
          <td width="75%" class="datacell">
            <textarea name="annotation" cols=50 rows=6></textarea>
          </td>
        </tr>
      </table>
    </td>
  </tr>
  
   <c:set var="inputLocation" value="SuperUser.jsp" scope="request"/>
   <jsp:include page="../AppSpecificRoute.jsp" flush="true" />
   
  
  <tr>
    <td height="30" class="headercell1" align="center" nowrap>
      <table width="100%" border=0 cellpadding=0 cellspacing=0>
       <tr>
	     <td nowrap align="center"> 
           <c:if test="${SuperUserForm.SUDocument && SuperUserForm.routeHeader.docRouteStatus != Constants.ROUTE_HEADER_PROCESSED_CD
                   && SuperUserForm.routeHeader.docRouteStatus != Constants.ROUTE_HEADER_APPROVED_CD  &&
              SuperUserForm.routeHeader.docRouteStatus != Constants.ROUTE_HEADER_DISAPPROVED_CD}">
             <html-el:image property="methodToCall.approve" src="images/buttonsmall_approvedoc.gif" align="absmiddle" />
             <c:set var="futureNodeNames" value="${SuperUserForm.futureNodeNames}" />
			<%
			if(!((Collection)pageContext.getAttribute("futureNodeNames")).isEmpty()) {
			%>
               &nbsp;&nbsp;&nbsp;&nbsp;Select a route node:&nbsp;   
               <html-el:select property="destNodeName" >
                 <c:forEach var="nodeName" items="${SuperUserForm.futureNodeNames}">
                   <html-el:option value="${nodeName}" />
                 </c:forEach>
               </html-el:select>&nbsp;<html-el:image property="methodToCall.routeLevelApprove" src="images/buttonsmall_docroulevapp.gif" align="absmiddle" />&nbsp;
             <%
			}
			%>
           </c:if>
         </td>
       </tr>
       <tr>
       	 <td>&nbsp;</td>
       </tr>       	 
       <tr>
       	 <td nowrap align="center"> 
           <c:if test="${SuperUserForm.SUDocument && SuperUserForm.routeHeader.docRouteStatus != Constants.ROUTE_HEADER_PROCESSED_CD
                && SuperUserForm.routeHeader.docRouteStatus != Constants.ROUTE_HEADER_APPROVED_CD &&
              SuperUserForm.routeHeader.docRouteStatus != Constants.ROUTE_HEADER_DISAPPROVED_CD}">
             <html-el:image property="methodToCall.disapprove" src="images/buttonsmall_disapprovedoc.gif" align="absmiddle" />&nbsp;&nbsp;&nbsp;&nbsp; 
           </c:if>
           <c:if test="${SuperUserForm.routeHeader.docRouteStatus != Constants.ROUTE_HEADER_PROCESSED_CD && SuperUserForm.routeHeader.docRouteStatus != Constants.ROUTE_HEADER_APPROVED_CD &&
              SuperUserForm.routeHeader.docRouteStatus != Constants.ROUTE_HEADER_DISAPPROVED_CD}">
             <html-el:image property="methodToCall.cancel" src="images/buttonsmall_canceldoc.gif" align="absmiddle" /> 
           </c:if>
           <c:set var="previousNodeNameCol" value="${SuperUserForm.previousNodes}" />
			<%
			if(!((Collection)pageContext.getAttribute("previousNodeNameCol")).isEmpty()) {
			%>
	           <c:if test="${SuperUserForm.SUDocument}">
	
		         &nbsp;&nbsp;&nbsp;&nbsp;Select a node name:&nbsp;
			     <html-el:select property="returnDestNodeName">
		           <html-el:options collection="previousNodeNameCol" labelProperty="value" property="key"/>
		         </html-el:select>&nbsp;<html-el:image src="images/buttonsmall_retprevrtlevel.gif" align="absmiddle" property="methodToCall.returnToPreviousNode" />&nbsp;&nbsp;&nbsp;&nbsp;
	           </c:if>
           <%
			}
			%>
         </td>
       </tr>
      </table> 
    </td>
  </tr>
 
  <c:if test="${SuperUserForm.SUDocument && ! empty SuperUserForm.actionRequests}">
    <tr>
      <td height="20"></td>
    </tr>
    <tr>
      <td height="30">
         <strong>Super User Action on Action Requests</strong>
         <br>
      </td>
    </tr>
    <tr>
      <td>
        <jsp:include page="SUActionRequests.jsp" flush="true"/>
      </td>
    </tr>
  </c:if>
</c:if>

</html-el:form>
</c:if>

</table>

    </td>
    <td></td>
  </tr>
</table>

<jsp:include page="../BackdoorMessage.jsp" flush="true"/>

</body>
</html-el:html>
