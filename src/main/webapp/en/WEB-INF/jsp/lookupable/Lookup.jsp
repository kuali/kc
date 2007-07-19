<%@ page import="org.apache.commons.beanutils.BeanUtils" %>
<%@ page import="edu.iu.uis.eden.lookupable.Column" %>
<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>
 
<html-el:html>
<head>
<TITLE><c:out value="${workflowLookupable.title}" /></TITLE>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="javascript" src="scripts/en-common.js"></script>
<script language="javascript" src="scripts/lookupable-common.js"></script>
</head>
<body>

  <html-el:form method="get" action="/Lookup.do">
  <input type="hidden" name="returnLocation"/>
  <input type="hidden" name="quickFinderLookupable"/>
  <html-el:hidden name="LookupForm" property="backLocation" />
  <html-el:hidden name="LookupForm" property="formKey" />
  <html-el:hidden name="LookupForm" property="methodToCall" />
  <html-el:hidden name="LookupForm" property="lookupableImplServiceName" />
  <html-el:hidden name="LookupForm" property="conversionFields" />

<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
  <tr>
    <td><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td width="90%" >
      <c:if test="${workflowLookupable.htmlMenuBar != ''}" >
				<c:out escapeXml="false" value="${workflowLookupable.htmlMenuBar}" />
      </c:if>
    	</td>
  </tr>
</table>

<br>
<jsp:include page="../WorkflowMessages.jsp" flush="true" />

<table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td height=30>
      <table width="100%" border=0 cellspacing=0 cellpadding=0>
        <tr>
          <td height=30><strong><c:out value="${workflowLookupable.title}" /></strong></td>
        </tr>
        <tr>
          <td><c:out value="${workflowLookupable.lookupInstructions}" /></td>
        </tr>
      </table>
    </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  
  <tr>
    <td>&nbsp;</td>
    <td>
      <table height=40 border=0 align=center cellpadding=0 cellspacing=0 class="bord-r-t">
		
		<c:set var="FormName" value="LookupForm" scope="request" />
		<c:set var="FieldRows" value="${workflowLookupable.rows}" scope="request" />
		<c:set var="ActionName" value="Lookup.do" scope="request" />
		<c:set var="IsLookupDisplay" value="true" scope="request" />
		<jsp:include page="../RowDisplay.jsp" /> 
        
        <tr align=center>
          <td height=30 colspan=3 class="thnormal">
			<html-el:image property="methodToCall.search" value="search"
						src="images/buttonsmall_search.gif" styleClass="tinybutton"
						alt="search" title="search" border="0" />&nbsp;&nbsp;<html-el:image
						property="methodToCall.clearValues" value="clearValues"
						src="images/buttonsmall_clear.gif" styleClass="tinybutton"
						alt="clear" title="clear" border="0" />
<%-- OLD STUFF
              <a href="javascript:clearValues('search', 'LookupForm', 'Lookup.do')" value="Search" style='font-family:verdana,arial,helvetica,sans-serif;font-size:10px;font-weight:bold;background:#cccccc;color:#333399' ><img src="images/buttonsmall_search.gif" alt="search" border=0 align=absmiddle></a>
               &nbsp;&nbsp;<a href="javascript:clearValues('clearValues', 'LookupForm', 'Lookup.do')"><img src="images/buttonsmall_clear.gif" alt="clear" align=absmiddle></a>
--%>
          </td>
        </tr>
      </table>
    </td>
    <td>&nbsp;</td>
  </tr>
  
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  
  
  <tr>
      <td>&nbsp;</td>
<td align="right">
<logic-el:present name="LookupForm" property="formKey" >
<c:if test="${LookupForm.formKey!=''}">
<a href='<c:out value="${LookupForm.backLocation}?methodToCall=refresh&docFormKey=${LookupForm.formKey}${LookupForm.noReturnParams}" />' >return with no value</a>
</c:if>
</logic-el:present>&nbsp;
</td>
      <td>&nbsp;</td>
</tr>

<c:if test="${!empty reqSearchResults and !empty LookupForm.supportedExportFormats}">
<tr>
      <td>&nbsp;</td>
      <td>
        <div width="100%" align="right">Export results to: 
          <c:forEach items="${LookupForm.supportedExportFormats}" var="format" varStatus="loopStat">
            <a href="javascript:export_results('LookupForm', 'Lookup.do', '<c:out value="${format.formatName}"/>')">
            <c:out value="${format.formatName}"/></a><c:if test="!loopStat.last">| </c:if>
          </c:forEach>
        </div>	
      </td>
      <td>&nbsp;</td>
</c:if>

<tr>
      <td>&nbsp;</td>
<td >
  <html-el:hidden property="listKey" value="${listKey}"/>
  <display-el:table class="bord-r-t" style="width:100%" cellspacing="0" cellpadding="0" name="${reqSearchResults}" pagesize="100" defaultsort="1" sort="external" id="result" requestURI="Lookup.do?methodToCall=viewResults&listKey=${listKey}"
       decorator="edu.iu.uis.eden.lookupable.LookupDecorator" >
		<c:forEach items="${workflowLookupable.columns}" var="column">
			<display-el:column class="datacell" 
				sortable="${column.sortable}"
				sortName="${column.sortName}"
				title="${column.columnTitle}"
				property="${column.propertyName}"
				decorator="edu.iu.uis.eden.lookupable.LookupColumnDecorator">
			<%--<display-el:column class="datacell" sortable="${column.sortable}" sortName="${column.propertyName}" title="${column.columnTitle}" >
			    <%
			     Object objectName =  (Object)pageContext.getAttribute("result");
			     Column column = (Column)pageContext.getAttribute("column");
			     Object colObject = BeanUtils.getProperty(objectName,column.getPropertyName());
			     pageContext.setAttribute("colObject",colObject != null ? colObject : "");
			    %>
				<c:out escapeXml="false" value="${colObject}"  />&nbsp;--%>
			</display-el:column>
		</c:forEach>
		<logic-el:present name="LookupForm" property="formKey" >
			<c:if test="${LookupForm.formKey!=''}">
  	  	<display-el:column class="datacell" property="returnUrl" title="Return value"/>
			</c:if>
		</logic-el:present>
  </display-el:table>
  
  
</td>
    <td>&nbsp;</td>
</tr>
   
  <tr>
    <td width=20><img src="images/pixel_clear.gif" alt="" width=20 height=20></td>
    <td>&nbsp;</td>
    <td width=20><img src="images/pixel_clear.gif" alt="" width=20 height=20></td>
  </tr>
</table>
<p>&nbsp;</p> 
  </html-el:form>
  
</body>
</html-el:html>