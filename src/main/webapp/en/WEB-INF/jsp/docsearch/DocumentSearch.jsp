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
<html-el:hidden name="DocumentSearchForm" property="criteria.docTypeFullName"/>
<html-el:hidden name="DocumentSearchForm" property="lookupType"/>
<html-el:hidden name="DocumentSearchForm" property="lookupableImplServiceName" />
<html-el:hidden name="DocumentSearchForm" property="conversionFields" />

<input type="hidden" name="quickFinderLookupable" />

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="headercell1">
  <tr>
    <td>
        <img src="<bean-el:message key="wflogo"/>" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;&nbsp;&nbsp;&nbsp;
    </td>
    <td width="90%">
      <a href="javascript:setMethod('<c:if test="${DocumentSearchForm.isAdvancedSearch == 'YES'}">basic</c:if><c:if test="${DocumentSearchForm.isAdvancedSearch != 'YES'}">advanced</c:if>');document.forms[0].submit();">
        <span class="maintext"><c:if test="${DocumentSearchForm.isAdvancedSearch != 'YES'}">Detailed Search</c:if><c:if test="${DocumentSearchForm.isAdvancedSearch == 'YES'}">Basic Search</c:if></span></a>&nbsp;&nbsp;
      <a href="javascript:setMethod('<c:if test="${DocumentSearchForm.superUserSearch == 'YES'}">clearSuperUserSearch</c:if><c:if test="${DocumentSearchForm.superUserSearch != 'YES'}">superUserSearch</c:if>');document.forms[0].submit();">
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
<table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
    <td width="20">&nbsp;</td>
    <td><jsp:include page="../WorkflowMessages.jsp" flush="true" /></td>
  </tr>
</table>

<table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td height="30">
       <strong><c:if test="${DocumentSearchForm.superUserSearch == 'YES'}">SuperUser </c:if> <bean-el:message key="docSearch.DocumentSearch.criteria.label.introduction"/>:</strong>
       <%-- next line is for using enter key to do search --%>
       <html-el:image property="methodToCall.doDocSearch" src="images/pixel_clear.gif"/>
    </td>
    <td width="20" height="30">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
    <td>
      <c:if test="${DocumentSearchForm.isAdvancedSearch != 'YES'}">
      <%-- BEGIN BASIC SEARCH --%>
      <table width="100%" border="0" cellpadding="3" cellspacing="0" class="bord-r-t">
        <tr>
          <th align="right" class="thnormal"  colspan="2">
            <div align="right"><bean-el:message key="docSearch.DocumentSearch.criteria.label.documentType"/>:</div>
          </th>
          <td class="datacell">
            <table border="0" cellspacing="0" cellpadding="1">
              <tr>
                <td nowrap>
                  <div id="docLabelDiv">&nbsp;
                     <c:out value="${DocumentSearchForm.docTypeDisplayName}" />
                  </div>
                  <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'DocumentTypeLookupableImplService';"/>
                </td>
                <td width="40" rowspan="2" align="right" valign="middle" nowrap>
                  <bean-el:message key="general.help.documentType"/>
                </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <th align="right" class="thnormal"  colspan="2" >
            <p align="right"><bean-el:message key="docSearch.DocumentSearch.criteria.label.initiatorId"/>:</p>
          </th>
          <td nowrap class="datacell">
            <html-el:text name="DocumentSearchForm" property="criteria.initiator" />
            <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'UserLookupableImplService'; document.forms[0].elements['lookupType'].value = 'criteria.initiator';"/>
            <bean-el:message key="general.help.initiatorId"/>
          </td>
        </tr>
        <tr>
          <th width="22%" align="right" valign="top" class="thnormal"  colspan="2" >
            <div align="right"><bean-el:message key="docSearch.DocumentSearch.criteria.label.documentId"/>:</div>
          </th>
          <td width="78%" valign="top" class="datacell">
            <html-el:text name="DocumentSearchForm" property="criteria.routeHeaderId" />&nbsp;<bean-el:message key="general.help.routingId"/>
          </td>
        </tr>
        <tr>
          <th height="28" align="right" valign="center" class="thnormal"  colspan="2" >
            <div align="right"><bean-el:message key="docSearch.DocumentSearch.criteria.label.dateCreated"/>:</div>
          </th>
          <td valign="top" class="datacell">
            <table width="100" border="0" cellspacing="0" cellpadding="1">
              <tr>
                <td align="right" nowrap><bean-el:message key="docSearch.DocumentSearch.criteria.label.from"/>:</td>
                <td nowrap>
                  <html-el:text name="DocumentSearchForm" property="fromDateCreated" size="10" />
                  <a href="javascript:showCal('fromDateCreated');"><img src="images/cal.gif" width="16" height="16" align="absmiddle" alt="Click Here to pick up the from create date"></a>
 				</td>
                <td width="40" rowspan="2" align="right" valign="middle" nowrap>
                  <bean-el:message key="general.help.dateCreated"/>
                </td>
              </tr>
              <tr>
                <td align="right" nowrap><bean-el:message key="docSearch.DocumentSearch.criteria.label.to"/>:</td>
                <td nowrap>
                  <html-el:text name="DocumentSearchForm" property="toDateCreated" size="10" />
                  <a href="javascript:showCal('toDateCreated');"><img src="images/cal.gif" width="16" height="16" align="absmiddle" alt="Click Here to pick up the to create date"></a>
				</td>
              </tr>
            </table>
          </td>
        </tr>
        <c:import url="DocumentSearchSearchableAttributes.jsp" />
        <tr>
          <th height="28" align="right" valign="center" class="thnormal"  colspan="2" >
            <div align="right"><bean-el:message key="docSearch.DocumentSearch.criteria.label.searchName"/>:</div>
          </th>
          <td valign="center" class="datacell">
            <html-el:text name="DocumentSearchForm" property="criteria.namedSearch" size="60" />&nbsp;<bean-el:message key="general.help.namedSearch"/>
          </td>
        </tr>
        <tr>
          <th height="28" colspan="3" align="right" valign="top" class="thnormal"  >
            <div align="center">
            <%-- onclick="setMethod('doDocSearch');" --%>
              <html-el:image property="methodToCall.doDocSearch" src="images/buttonsmall_search.gif" align="absmiddle"/>&nbsp;&nbsp;&nbsp;&nbsp;
 	          <html-el:image src="images/buttonsmall_clear.gif" align="absmiddle" property="methodToCall.clear"/>
            </div>
          </th>
        </tr>
      </table>
        <%-- END BASIC SEARCH --%>
	  </c:if>
      <c:if test="${DocumentSearchForm.isAdvancedSearch == 'YES'}">
	  <%-- BEGIN ADVANCED BEGIN --%>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
          <tbody>
            <tr>
              <td colspan="5" class="catheader"></td>
            </tr>
            <tr>
              <td align="right" nowrap class="thnormal" colspan="2">
                <div align="right"> <bean-el:message key="docSearch.DocumentSearch.criteria.label.initiatorId"/>:</div>
              </td>
              <td nowrap class="datacell">
                <html-el:text name="DocumentSearchForm" property="criteria.initiator" size="16" maxlength="30"/>
                <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'UserLookupableImplService'; document.forms[0].elements['lookupType'].value = 'criteria.initiator';"/>
                <bean-el:message key="general.help.initiatorId"/>
              </td>
              <td nowrap class="thnormal">
                <div align="right"> <bean-el:message key="docSearch.DocumentSearch.criteria.label.viewerId"/>:</div>
              </td>
              <td nowrap class="datacell">
                <html-el:text name="DocumentSearchForm" property="criteria.viewer" size="16" maxlength="30"/>
                <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'UserLookupableImplService'; document.forms[0].elements['lookupType'].value = 'criteria.viewer';"/>
                <bean-el:message key="general.help.viewerId"/>
              </td>
            </tr>
            <tr>
              <td align="right" nowrap class="thnormal" colspan="2">
                <div align="right"> <bean-el:message key="docSearch.DocumentSearch.criteria.label.approverId"/>:</div>
              </td>
              <td nowrap class="datacell">
                <html-el:text name="DocumentSearchForm" property="criteria.approver" size="16" maxlength="30"/>
                <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'UserLookupableImplService'; document.forms[0].elements['lookupType'].value = 'criteria.approver';"/>
                <bean-el:message key="general.help.approverId"/>
              </td>
			  <td nowrap class="thnormal">
                <div align="right"> <bean-el:message key="docSearch.DocumentSearch.criteria.label.workgroupViewer"/>:</div>
              </td>
              <td nowrap class="datacell">
	            <html-el:text name="DocumentSearchForm" property="criteria.workgroupViewerName" size="16" maxlength="30"/>
                <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'WorkGroupLookupableImplService'; "/>
              </td>
            </tr>

            <tr>
              <td align="right" nowrap class="thnormal" colspan="2">
                <div align="right"> <bean-el:message key="docSearch.DocumentSearch.criteria.label.documentId"/>:</div>
              </td>
              <td nowrap class="datacell">
                <html-el:text name="DocumentSearchForm" property="criteria.routeHeaderId" size="16" maxlength="30" />
                <bean-el:message key="general.help.routingId"/>
              </td>
              <td nowrap class="thnormal">
                <div align="right"> <bean-el:message key="docSearch.DocumentSearch.criteria.label.documentRouteStatus"/>:</div>
              </td>
              <td nowrap class="datacell">
                <html-el:select name="DocumentSearchForm" property="criteria.docRouteStatus" style="background: rgb(255, 255, 255) none repeat scroll 0%; font-family: verdana,arial,helvetica,sans-serif; font-size: 10px; -moz-background-clip: initial; -moz-background-inline-policy: initial; -moz-background-origin: initial; color: rgb(51, 51, 153);">
                  <html-el:options collection="documentRouteStatus" labelProperty="value" property="key"/>
                </html-el:select>
                <bean-el:message key="general.help.routeStatus"/>
              </td>
            </tr>
            <tr>
              <td align="right" nowrap class="thnormal" colspan="2">
                <div align="right"> <bean-el:message key="docSearch.DocumentSearch.criteria.label.applicationDocumentId"/>:</div>
              </td>
              <td nowrap class="datacell">
                <html-el:text name="DocumentSearchForm" property="criteria.appDocId" size="16" maxlength="30" />
                <bean-el:message key="general.help.appDocId"/>
              </td>
              <td nowrap class="thnormal">
                <div align="right"> <bean-el:message key="docSearch.DocumentSearch.criteria.label.documentRouteNode"/>:</div>
              </td>
              <td nowrap class="datacell">
              	<c:choose>
             		<c:when test="${empty DocumentSearchForm.criteria.docTypeFullName}">
						<bean-el:message key="docSearch.DocumentSearch.criteria.label.documentTypeSelect"/>:
                	</c:when>
                	<c:otherwise>
						<html-el:select name="DocumentSearchForm" property="criteria.docRouteNodeId" style="background: rgb(255, 255, 255) none repeat scroll 0%; font-family: verdana,arial,helvetica,sans-serif; font-size: 10px; -moz-background-clip: initial; -moz-background-inline-policy: initial; -moz-background-origin: initial; color: rgb(51, 51, 153);">
		                  <html-el:options collection="routeNodes" labelProperty="routeNodeName" property="routeNodeId"/>
		                </html-el:select>

	                	<html-el:select name="DocumentSearchForm" property="criteria.docRouteNodeLogic" style="background: rgb(255, 255, 255) none repeat scroll 0%; font-family: verdana,arial,helvetica,sans-serif; font-size: 10px; -moz-background-clip: initial; -moz-background-inline-policy: initial; -moz-background-origin: initial; color: rgb(51, 51, 153);">
		                  <html-el:options collection="qualifierLogic" labelProperty="value" property="key"/>
		                </html-el:select>
                	</c:otherwise>
                </c:choose>
				<bean-el:message key="general.help.routeLevel"/>
              </td>
            </tr>
            <tr>
              <td align="right" nowrap class="thnormal" colspan="2">
                <div align="right"> <bean-el:message key="docSearch.DocumentSearch.criteria.label.dateLastModified"/>:</div>
              </td>
              <td nowrap class="datacell">
                <table border="0" cellspacing="0" cellpadding="1">
                  <tr>
                    <td align="right" nowrap><bean-el:message key="docSearch.DocumentSearch.criteria.label.from"/>:</td>
                    <td nowrap>
                      <html-el:text name="DocumentSearchForm" property="fromDateLastModified" size="10"/>
		              <a href="javascript:showCal('advancedFromDateLastModified');"><img src="images/cal.gif" alt="Click Here to pick up the from date last modified" width="16" height="16" border="0" align="absmiddle"></a>
		            </td>
                    <td width="40" rowspan="2" align="right" valign="middle" nowrap>
                      <bean-el:message key="general.help.dateLastModified"/>
                    </td>
                  </tr>
                  <tr>
                    <td align="right" nowrap><bean-el:message key="docSearch.DocumentSearch.criteria.label.to"/>:</td>
                    <td nowrap>
                      <html-el:text name="DocumentSearchForm" property="toDateLastModified" size="10" />
                      <a href="javascript:showCal('advancedToDateLastModified');"><img src="images/cal.gif" alt="Click Here to pick up the to date last modified" width="16" height="16" border="0" align="absmiddle"></a>
                    </td>
                  </tr>
                </table>
              </td>
              <td nowrap class="thnormal">
                <div align="right"> <bean-el:message key="docSearch.DocumentSearch.criteria.label.dateCreated"/>:</div>
              </td>
              <td nowrap class="datacell">
                  <table border="0" cellspacing="0" cellpadding="1">
                  <tr>
                    <td align="right" nowrap><bean-el:message key="docSearch.DocumentSearch.criteria.label.from"/>:</td>
                    <td nowrap>
                      <html-el:text name="DocumentSearchForm" property="fromDateCreated" size="10" />
                      <a href="javascript:showCal('advancedFromDateCreated');"><img src="images/cal.gif" width="16" height="16" border="0" align="absmiddle" alt="Click Here to pick up the from date created"></a>
                    </td>
                    <td width="40" rowspan="2" align="right" valign="middle" nowrap>
                      <bean-el:message key="general.help.dateCreated"/>
                    </td>
                  </tr>
                  <tr>
                    <td align="right" nowrap><bean-el:message key="docSearch.DocumentSearch.criteria.label.to"/>:</td>
                    <td nowrap>
                      <html-el:text name="DocumentSearchForm" property="toDateCreated" size="10" />
                      <a href="javascript:showCal('advancedToDateCreated');"><img src="images/cal.gif" width="16" height="16" border="0" align="absmiddle" alt="Click Here to pick up the to date created"></a>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
            <tr>
              <td align="right" nowrap class="thnormal" colspan="2"><bean-el:message key="docSearch.DocumentSearch.criteria.label.dateFinalized"/>:</td>
              <td nowrap class="datacell">
                  <table border="0" cellspacing="0" cellpadding="1">
                  <tr>
                    <td align="right" nowrap><bean-el:message key="docSearch.DocumentSearch.criteria.label.from"/>:</td>
                    <td nowrap>
                      <html-el:text name="DocumentSearchForm" property="fromDateFinalized" size="10"/>
                      <a href="javascript:showCal('advancedFromDateFinalized');"><img src="images/cal.gif" alt="Click Here to pick up the from date finalized" width="16" height="16" border="0" align="absmiddle"></a>
                    </td>
                    <td width="40" rowspan="2" align="right" valign="middle" nowrap>
                      <bean-el:message key="general.help.dateFinalized"/>
                    </td>
                  </tr>
                  <tr>
                    <td align="right" nowrap><bean-el:message key="docSearch.DocumentSearch.criteria.label.to"/>:</td>
                    <td nowrap>
                      <html-el:text name="DocumentSearchForm" property="toDateFinalized" size="10"/>
                      <a href="javascript:showCal('advancedToDateFinalized');"><img src="images/cal.gif" alt="Click Here to pick up the to date finalized" width="16" height="16" border="0" align="absmiddle"></a>
                    </td>
                  </tr>
                </table>
              </td>
              <td nowrap class="thnormal">
                <div align="right"> <bean-el:message key="docSearch.DocumentSearch.criteria.label.dateApproved"/>:</div>
              </td>
              <td nowrap class="datacell">
                  <table border="0" cellspacing="0" cellpadding="1">
                  <tr>
                    <td align="right" nowrap><bean-el:message key="docSearch.DocumentSearch.criteria.label.from"/>:</td>
                    <td nowrap>
                      <html-el:text name="DocumentSearchForm" property="fromDateApproved" size="10"/>
                      <a href="javascript:showCal('advancedFromDateApproved');"><img src="images/cal.gif" alt="Click Here to pick up the from date approved" width="16" height="16" border="0" align="absmiddle"></a>
                    </td>
                    <td width="40" rowspan="2" align="right" valign="middle" nowrap>
                      <bean-el:message key="general.help.dateApproved"/>
                    </td>
                  </tr>
                  <tr>
                    <td align="right" nowrap><bean-el:message key="docSearch.DocumentSearch.criteria.label.to"/>:</td>
                    <td nowrap>
                      <html-el:text name="DocumentSearchForm" property="toDateApproved" size="10" />
                      <a href="javascript:showCal('advancedToDateApproved');"><img src="images/cal.gif" alt="Click Here to pick up the to date approved" width="16" height="16" border="0" align="absmiddle"></a>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
            <tr>
              <td align="right" nowrap class="thnormal" colspan="2"><bean-el:message key="docSearch.DocumentSearch.criteria.label.documentType"/>:</td>
              <td class="datacell">
                <table border="0" cellspacing="0" cellpadding="1">
                  <tr>
                    <td nowrap>
                      <div id="docLabelDiv">&nbsp;
                         <c:out value="${DocumentSearchForm.docTypeDisplayName}" />
                      </div>
                      <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'DocumentTypeLookupableImplService';"/>
                    </td>
                    <td width="40" rowspan="2" align="right" valign="middle" nowrap>
                      <bean-el:message key="general.help.documentType"/>
                    </td>
                  </tr>
                </table>
              </td>
              <td nowrap class="thnormal">
                <div align="right"> <bean-el:message key="docSearch.DocumentSearch.criteria.label.documentTitle"/>:</div>
              </td>
              <td nowrap class="datacell">
                <html-el:text name="DocumentSearchForm" property="criteria.docTitle" size="16" maxlength="100" />
                <bean-el:message key="general.help.documentTitle"/>
              </td>
            </tr>
            <c:import url="DocumentSearchSearchableAttributes.jsp" />
            <tr>
              <td align="right" nowrap class="thnormal" colspan="2"><bean-el:message key="docSearch.DocumentSearch.criteria.label.searchName"/>:</td>
              <td colspan="3" class="datacell">
                <html-el:text name="DocumentSearchForm" property="criteria.namedSearch" size="20" />&nbsp;<bean-el:message key="general.help.namedSearch"/>
              </td>
            </tr>
            <tr>
              <td height="30" colspan="5" align="center" class="thnormal">
                <html-el:image property="methodToCall.doDocSearch" src="images/buttonsmall_search.gif" align="absmiddle"/>
		        <html-el:image src="images/buttonsmall_clear.gif" align="absmiddle" property="methodToCall.clear"/>
              </td>
            </tr>
          </tbody>
        </table>
        <%-- END ADVANCED SEARCH pagesize="100" defaultsort="1" --%>
	  </c:if>
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
  <display-el:table excludedParams="*" class="bord-r-t" style="width:100%" cellspacing="0" cellpadding="0" name="${reqSearchResults}" export="true" sort="external" id="result" requestURI="DocumentSearch.do?methodToCall=viewResults&key=${key}&superUserSearch=${DocumentSearchForm.superUserSearch}&isAdvancedSearch=${DocumentSearchForm.isAdvancedSearch}" pagesize="${preferences.pageSize}" defaultsort="1" defaultorder="descending"
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

  <jsp:include page="../BackdoorMessage.jsp" flush="true"/>

</body>
</html-el:html>
