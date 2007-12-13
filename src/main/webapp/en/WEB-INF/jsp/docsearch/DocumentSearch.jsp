<%@ page import="org.apache.commons.beanutils.BeanUtils" %>
<%@ page import="edu.iu.uis.eden.lookupable.Column" %>
<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html-el:html>
<head>
<title>
  Document Search
</title>
<!-- TODO make sure that you fill in the head or make this common -->
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
<script language="JavaScript" src="scripts/cal2.js">
    /*
    Xin's Popup calendar script-  Xin Yang (http://www.yxscripts.com/) Script
    featured on/available at http://www.dynamicdrive.com/
    This notice must stay intact for use */
</script>
<script language="JavaScript" src="scripts/cal_conf2.js"></script>
<script language="javascript" src="scripts/docsearch-common.js"></script>
</head>

<body bgcolor="#ffffff" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">

<html-el:form method="post" action="/DocumentSearch.do">

<html-el:hidden name="DocumentSearchForm" property="superUserSearch"/>
<html-el:hidden name="DocumentSearchForm" property="isAdvancedSearch"/>
<html-el:hidden name="DocumentSearchForm" property="methodToCall" />
<html-el:hidden name="DocumentSearchForm" property="lookupType"/>
<html-el:hidden name="DocumentSearchForm" property="lookupableImplServiceName" />
<html-el:hidden name="DocumentSearchForm" property="conversionFields" />
<html-el:hidden name="DocumentSearchForm" property="headerBarEnabled"/>
<html-el:hidden name="DocumentSearchForm" property="searchCriteriaEnabled"/>
<html-el:hidden name="DocumentSearchForm" property="initiatorUser"/>
<html-el:hidden name="DocumentSearchForm" property="searchableAttributes"/>
<input type="hidden" name="searchStateKey" value="${searchStateKey}" />
<input type="hidden" name="quickFinderLookupable" />

<c:if test="${DocumentSearchForm.showHeaderBar}">
<table id="headerTable" width="100%" border="0" cellpadding="0" cellspacing="0" class="headercell1">
  <tr>
    <td>
        <img src="<bean-el:message key="wflogo"/>" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;&nbsp;&nbsp;&nbsp;
    </td>
    <td width="90%">
      <a id="searchType" href="javascript:setMethod('<c:if test="${DocumentSearchForm.isAdvancedSearch == 'YES'}">basic</c:if><c:if test="${DocumentSearchForm.isAdvancedSearch != 'YES'}">advanced</c:if>');document.forms[0].submit();">
        <span class="maintext"><c:if test="${DocumentSearchForm.isAdvancedSearch != 'YES'}">Detailed Search</c:if><c:if test="${DocumentSearchForm.isAdvancedSearch == 'YES'}">Basic Search</c:if></span></a>&nbsp;&nbsp;
      <a id="superUserSearch" href="javascript:setMethod('<c:if test="${DocumentSearchForm.superUserSearch == 'YES'}">clearSuperUserSearch</c:if><c:if test="${DocumentSearchForm.superUserSearch != 'YES'}">superUserSearch</c:if>');document.forms[0].submit();">
        <span class="maintext"><c:if test="${DocumentSearchForm.superUserSearch != 'YES'}">Superuser Search</c:if><c:if test="${DocumentSearchForm.superUserSearch == 'YES'}">Non Superuser Search</c:if></span></a>&nbsp;&nbsp;
      <a href="javascript:setMethod('resetNamedSearches');document.forms[0].submit();">
        <span class="maintext">Clear Saved Searches</span></a>&nbsp;&nbsp;
      <html-el:select name="DocumentSearchForm" property="namedSearch" onchange="if (this.options[this.selectedIndex].value != 'ignore') { this.value = this.options[this.selectedIndex].value; document.forms[0].methodToCall.value='doDocSearch'; document.forms[0].submit(); }">
         <html-el:options collection="namedSearches" labelProperty="value" property="key" filter="false"/>
      </html-el:select>
    </td>
  </tr>
</table>
<br>
</c:if>

<table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
    <td width="20">&nbsp;</td>
    <td><jsp:include page="../WorkflowMessages.jsp"/></td>
  </tr>
</table>

<table width="100%" border=0 cellspacing=0 cellpadding=0>
	<c:if test="${DocumentSearchForm.showSearchCriteria}">
	  <tr>
	    <td width="20" height="30">&nbsp;</td>
	    <td height="30">
	       <strong><c:if test="${DocumentSearchForm.superUserSearch == 'YES'}">SuperUser </c:if> <bean-el:message key="docSearch.DocumentSearch.criteria.label.introduction"/>:</strong>
	       <%-- next line is for using enter key to do search --%>
	       <html-el:image property="methodToCall.doDocSearch" src="images/pixel_clear.gif"/>
	    </td>
	    <td width="20" height="30">&nbsp;</td>
	  </tr>
	</c:if>

		<tr>
			<td><img src="images/pixel_clear.gif" alt="" width="20"
				height="20"></td>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="bord-r-t">
				<c:set scope="request" var="criteriaManager" value="${DocumentSearchForm.documentSearchCriteriaManager}" />
				<c:set scope="request" var="criteriaColumns" value="${criteriaManager.columnsPreSearchAttributes}" />
				<c:set scope="request" var="maxRowsList" value="${criteriaManager.preSearchAttributeMaxRows}" />
				<c:set scope="request" var="totalColumnCount" value="${criteriaManager.maxColumnCount + criteriaManager.maxColumnCount + 1}" />
				<c:set scope="request" var="rightColspan" value="${criteriaManager.maxColumnCount + criteriaManager.maxColumnCount - 1}" />
				<c:import url="StandardDocumentSearchCriteria.jsp" />
				<c:import url="DocumentSearchSearchableAttributes.jsp" />
				<c:set scope="request" var="criteriaColumns" value="${criteriaManager.columnsPostSearchAttributes}" />
				<c:set scope="request" var="maxRowsList" value="${criteriaManager.postSearchAttributeMaxRows}" />
				<c:import url="StandardDocumentSearchCriteria.jsp" />

				<c:if test="${DocumentSearchForm.showSearchCriteria}">
					<tr>
						<th height="28" colspan="${totalColumnCount}" align="right" valign="top" class="thnormal">
						  <div align="center">
							<html-el:image property="methodToCall.doDocSearch" src="images/buttonsmall_search.gif" align="absmiddle" />&nbsp;&nbsp;&nbsp;&nbsp;
							<html-el:image src="images/buttonsmall_clear.gif" align="absmiddle" property="methodToCall.clear" />
						  </div>
						</th>
					</tr>
				</c:if>
			</table>
			</td>
		</tr>
		<tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
      <td>

  <%-- Setup column lables based on ApplicationsResources --%>
  <bean:define id="documentIdLabel">
 	<bean-el:message key="docSearch.DocumentSearch.results.label.routeHeaderId"/>
  </bean:define>
  <bean:define id="typeLabel">
 	<bean-el:message key="docSearch.DocumentSearch.results.label.docTypeLabel"/>
  </bean:define>
  <bean:define id="titleLabel">
 	<bean-el:message key="docSearch.DocumentSearch.results.label.documentTitle"/>
  </bean:define>
  <bean:define id="routeStatusLabel">
 	<bean-el:message key="docSearch.DocumentSearch.results.label.docRouteStatusCodeDesc"/>
  </bean:define>
  <bean:define id="initiatorLabel">
 	<bean-el:message key="docSearch.DocumentSearch.results.label.initiator"/>
  </bean:define>
  <bean:define id="dateCreatedLabel">
 	<bean-el:message key="docSearch.DocumentSearch.results.label.dateCreated"/>
  </bean:define>
  <bean:define id="routeLogLabel">
 	<bean-el:message key="docSearch.DocumentSearch.results.label.routeLog"/>
  </bean:define>

  <%--
  <display-el:table class="bord-r-t" style="width:100%" cellspacing="0" cellpadding="0" name="${reqSearchResults}" pagesize="100" defaultsort="1" sort="external" id="result" requestURI="Lookup.do?methodToCall=viewResults&listKey=${listKey}"
       decorator="edu.iu.uis.eden.lookupable.LookupDecorator" > --%>
  <%-- TODO delyea - add external sorting 'sort="external"' --%>
  <%-- TODO delyea - add in pagesize? --%>
  <%-- Table layout of the search results --%>
  <display-el:table excludedParams="*" class="bord-r-t" style="width:100%" cellspacing="0" cellpadding="0" name="${reqSearchResults}" export="true" sort="external" id="result" requestURI="DocumentSearch.do?methodToCall=viewResults&searchStateKey=${searchStateKey}&superUserSearch=${DocumentSearchForm.superUserSearch}&isAdvancedSearch=${DocumentSearchForm.isAdvancedSearch}" pagesize="${preferences.pageSize}" defaultsort="1" defaultorder="descending"
		decorator="edu.iu.uis.eden.docsearch.web.DocumentSearchDecorator">
    <display-el:setProperty name="paging.banner.placement" value="both" />
    <display-el:setProperty name="export.banner" value="" />
		<c:forEach items="${reqSearchResultColumns}" var="column">
			<display-el:column class="datacell" 
				sortable="${column.sortable}"
				sortName="${column.sortName}"
				title="${column.columnTitle}"
				property="${column.propertyName}"
				decorator="edu.iu.uis.eden.docsearch.web.DocumentSearchColumnDecorator">
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
  </display-el:table>
      </td>
      <td>&nbsp;</td>
    </tr>
  </table>
<%--
    <c:set var="colIndex" value="0"/>
    <c:forEach items="${DocumentSearchForm.searchableAttributeColumns}" var="column">
        <html-el:hidden property="searchableAttributeColumn[${colIndex}].columnTitle"/>
        <html-el:hidden property="searchableAttributeColumn[${colIndex}].sortable"/>
        <html-el:hidden property="searchableAttributeColumn[${colIndex}].propertyName"/>
        <c:set var="colIndex" value="${colIndex + 1}"/>
    </c:forEach>
--%>
  </html-el:form>

  <jsp:include page="../BackdoorMessage.jsp"/>

</body>
</html-el:html>
