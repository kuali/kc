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
<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el"%>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el"%>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c"%>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt"%>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el"%>

<html-el:html>

<head>
<title>Exception Routing Queue</title>
<style type="text/css">
   .highlightrow {}
   tr.highlightrow:hover, tr.over td { background-color: #66FFFF; }
</style>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
<script language="JavaScript" src="scripts/messagequeue-common.js"></script>
</head>

<body bgcolor="#ffffff" marginheight="0" marginwidth="0" topmargin="0"
	leftmargin="0">

<table width="100%" border=0 cellpadding=0 cellspacing=0
	class="headercell1">
	<tr>
		<td width="15%"><img src="images/wf-logo.gif"
			alt="Workflow" width=150 height=21 hspace=5 vspace=5></td>
		<td width="85%"><a href="Quartz.do?methodToCall=start" />Refresh Page</a></td>
		<td>&nbsp;&nbsp;</td>
	</tr>
</table>
<br/>
<br/>
<table width="90%" border=0 cellspacing=0 cellpadding=0 align="center">
  <tr>
    <td width="20" height="20">&nbsp;</td>
    <td>
		  <display-el:table excludedParams="*" pagesize="40" class="bord-r-t" style="width:100%" cellspacing="0" cellpadding="0" name="jobs" export="true" id="result"  requestURI="Quartz.do?methodToCall=start" defaultsort="4" defaultorder="descending"
				decorator="org.kuali.rice.ksb.messaging.web.KSBTableDecorator">
		    <display-el:setProperty name="paging.banner.placement" value="both" />
		    <display-el:setProperty name="export.banner" value="" />
		    <display-el:column style="text-align:center;vertical-align:middle;" class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>Job Name</div>" sortProperty="jobDetail.name">
		    	<c:out value="${result.jobDetail.name}"/>&nbsp;
		    </display-el:column>
		    <display-el:column style="text-align:center;vertical-align:middle;" class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>Job Group</div>" >
		    	<c:out value="${result.jobDetail.group}"/>&nbsp;
		    </display-el:column>
		    <display-el:column style="text-align:center;vertical-align:middle;"  class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>Description</div>" >
		    	<c:out value="${result.jobDetail.description}"/>&nbsp;
		    </display-el:column>
		    <display-el:column style="text-align:center;vertical-align:middle;"  class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>Time to execute</div>" sortProperty="trigger.startTime.time">
		    	<c:out value="${result.trigger.nextFireTime}"/>&nbsp;
		    </display-el:column>
		    <display-el:column style="text-align:center;vertical-align:middle;" class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>FullName</div>" >
		    	<c:out value="${result.jobDetail.fullName}"/>&nbsp;
		    </display-el:column>
		    <display-el:column style="text-align:center;vertical-align:middle;" class="datacell" sortable="false" title="<div style='text-align:center;vertical-align:top;'>Actions</div>" >
		    	<a href='Quartz.do?methodToCall=moveToRouteQueue&jobName=<c:out value="${result.jobDetail.name}" />&jobGroup=<c:out value="${result.jobDetail.group}"/>'>Put in message queue</a>
		    </display-el:column>
		  </display-el:table>
	</td>
  </tr>
</table>

    <jsp:include page="../Footer.jsp"/>

</html-el:html>
