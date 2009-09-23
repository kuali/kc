<%--
 Copyright 2008-2009 The Kuali Foundation
 
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
<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html-el:html>
<head>
<title>Service Registry</title>
<style type="text/css">
   .highlightrow {}
   tr.highlightrow:hover, tr.over td { background-color: #66FFFF; }
</style>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
<script language="JavaScript" src="scripts/messagequeue-common.js"></script>
</head>

<body bgcolor="#ffffff" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">

<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
  <tr>
    <td width="15%"><img src="images/wf-logo.gif" alt="Workflow" width=150 height=21 hspace=5 vspace=5></td>
    <td width="85%"><a href="ServiceRegistry.do?methodToCall=start" />Refresh Page</a></td>
    <td>&nbsp;&nbsp;</td>
  </tr>
</table>

<html-el:form action="/ServiceRegistry.do">
<html-el:hidden property="methodToCall" />

  <table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
        <td width="20" height="20">&nbsp;</td>
  	<td>

      <br>
  	  <jsp:include page="../Messages.jsp"/>
      <br>

  	  <table border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
		<tr>
        		<td class="thnormal">
  					Current Node Info&nbsp;
  				</td>
   			</tr>
		  <tr>
        <td class="datacell">IP Address: <c:out value="${ServiceRegistryForm.myIpAddress}"/><br>
        	Service Namespace: <c:out value="${ServiceRegistryForm.myServiceNamespace}"/>
        </td>
        </tr>
        <tr>
        <td class="datacell">dev.mode: <c:out value="${ServiceRegistryForm.devMode}"/>
        </td>
	  </tr>
	</table><br>
  	</td>
  	<td width="20" height="20">&nbsp;</td>
  </tr>
  <tr>
  	<td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td width="20" height="20">&nbsp;</td>
  	<td><input type="button" value="Refresh Service Registry" onclick="refreshServiceRegistry()"/></td>
  	<td width="20" height="20">&nbsp;</td>
  <tr>
  	<td colspan="3">&nbsp;</td>
  </tr>
</table>
</html-el:form>
<table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
    <td width="20" height="20">&nbsp;</td>
    <td>
		  <b>Published Services:</b>
		  <%-- Table layout of the search results --%>
		  <display-el:table excludedParams="*" class="bord-r-t" style="width:100%" cellspacing="0" cellpadding="0" name="${ServiceRegistryForm.publishedServices}" id="result" requestURI="ServiceRegistry.do?methodToCall=start" defaultsort="1" defaultorder="ascending"
				decorator="org.kuali.rice.ksb.messaging.web.KSBTableDecorator">
		    <display-el:setProperty name="paging.banner.placement" value="both" />
		    <display-el:setProperty name="paging.banner.all_items_found" value=""/>
		    <display-el:setProperty name="export.banner" value="" />
		    <display-el:setProperty name="basic.msg.empty_list">No Published Services</display-el:setProperty>
		    <display-el:column class="datacell" sortable="true" title="<div>Service Name</div>" >
		    	<c:out value="${result.serviceName}"/>&nbsp;
		    </display-el:column>
		    <display-el:column class="datacell" sortable="true" title="<div>Endpoint URL</div>" >
				<c:choose>
				<c:when test='${result.serviceDefinition.class.name == "org.kuali.rice.ksb.messaging.SOAPServiceDefinition"}'>
		    	<a href="${result.endpointUrl}?wsdl"><c:out value="${result.endpointUrl}"/></a>&nbsp;
				</c:when>
				<c:otherwise>
		    	<c:out value="${result.endpointUrl}"/>&nbsp;
				</c:otherwise>
				</c:choose>
		    </display-el:column>
		    <display-el:column style="text-align:center;vertical-align:middle;"  class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>Service Namespace</div>" >
		    	<c:out value="${result.serviceNamespace}"/>&nbsp;
		    </display-el:column>
		    <display-el:column style="text-align:center;vertical-align:middle;" class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>IP Number</div>" >
		    	<c:out value="${result.serverIp}"/>&nbsp;
		    </display-el:column>
			<display-el:column style="text-align:center;vertical-align:middle;" class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>Alive</div>" >
		    	<c:if test="${result.alive}">true</c:if>
		    	<c:if test="${!result.alive}">false</c:if>
		    	&nbsp;
		    </display-el:column>
		  </display-el:table>

    </td>
    <td width="20" height="20">&nbsp;</td>
   </tr>
   <tr><td colspan="3">&nbsp;</td></tr>
    <tr>
    <td width="20" height="20">&nbsp;</td>
    <td>
		  <b>Published Temp Services:</b>
		  <%-- Table layout of the search results --%>
		  <display-el:table excludedParams="*" class="bord-r-t" style="width:100%" cellspacing="0" cellpadding="0" name="${ServiceRegistryForm.publishedTempServices}" id="result" requestURI="ServiceRegistry.do?methodToCall=start" defaultsort="1" defaultorder="ascending"
				decorator="org.kuali.rice.ksb.messaging.web.KSBTableDecorator">
		    <display-el:setProperty name="paging.banner.placement" value="both" />
		    <display-el:setProperty name="paging.banner.all_items_found" value=""/>
		    <display-el:setProperty name="export.banner" value="" />
		    <display-el:setProperty name="basic.msg.empty_list">No Published Temp Services</display-el:setProperty>
		    <display-el:column class="datacell" sortable="true" title="<div>Service Name</div>" >
		    	<c:out value="${result.serviceName}"/>&nbsp;
		    </display-el:column>
		    <display-el:column class="datacell" sortable="true" title="<div>Endpoint URL</div>" >
		    	<c:out value="${result.endpointUrl}"/>&nbsp;
		    </display-el:column>
		    <display-el:column style="text-align:center;vertical-align:middle;"  class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>Service Namespace</div>" >
		    	<c:out value="${result.serviceNamespace}"/>&nbsp;
		    </display-el:column>
		    <display-el:column style="text-align:center;vertical-align:middle;" class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>IP Number</div>" >
		    	<c:out value="${result.serverIp}"/>&nbsp;
		    </display-el:column>
			<display-el:column style="text-align:center;vertical-align:middle;" class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>Alive</div>" >
		    	<c:if test="${result.alive}">true</c:if>
		    	<c:if test="${!result.alive}">false</c:if>
		    	&nbsp;
		    </display-el:column>
		  </display-el:table>

    </td>
    <td width="20" height="20">&nbsp;</td>
  </tr>

   <tr><td colspan="3">&nbsp;</td></tr>
    <tr>
    <td width="20" height="20">&nbsp;</td>
    <td>
		  <b>All Registry Services:</b>
		  <%-- Table layout of the search results --%>
		  <display-el:table excludedParams="*" class="bord-r-t" style="width:100%" cellspacing="0" cellpadding="0" name="${ServiceRegistryForm.globalRegistryServices}" id="result" requestURI="ServiceRegistry.do?methodToCall=start" defaultsort="1" defaultorder="ascending"
				decorator="org.kuali.rice.ksb.messaging.web.KSBTableDecorator">
		    <display-el:setProperty name="paging.banner.placement" value="both" />
		    <display-el:setProperty name="paging.banner.all_items_found" value=""/>
		    <display-el:setProperty name="export.banner" value="" />
		    <display-el:setProperty name="basic.msg.empty_list">No Registry Services</display-el:setProperty>
		    <display-el:column class="datacell" sortable="true" title="<div>Service Name</div>" >
		    	<c:out value="${result.serviceName}"/>&nbsp;
		    </display-el:column>
		    <display-el:column class="datacell" sortable="true" title="<div>Endpoint URL</div>" >
		    	<c:out value="${result.endpointUrl}"/>&nbsp;
		    </display-el:column>
		    <display-el:column style="text-align:center;vertical-align:middle;"  class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>Service Namespace</div>" >
		    	<c:out value="${result.serviceNamespace}"/>&nbsp;
		    </display-el:column>
		    <display-el:column style="text-align:center;vertical-align:middle;" class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>IP Number</div>" >
		    	<c:out value="${result.serverIp}"/>&nbsp;
		    </display-el:column>
			<display-el:column style="text-align:center;vertical-align:middle;" class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>Alive</div>" >
		    	<c:if test="${result.alive}">true</c:if>
		    	<c:if test="${!result.alive}">false</c:if>
		    	&nbsp;
		    </display-el:column>
		  </display-el:table>

    </td>
    <td width="20" height="20">&nbsp;</td>
  </tr>


</table>

    <jsp:include page="../Footer.jsp"/>

</body>
</html-el:html>
