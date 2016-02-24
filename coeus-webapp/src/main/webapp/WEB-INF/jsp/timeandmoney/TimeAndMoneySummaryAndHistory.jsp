<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>


  <kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="timeAndMoneySummaryAndHistory"
	documentTypeName="TimeAndMoneyDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="timeAndMoneySummaryAndHistory"
  	extraTopButtons="${KualiForm.extraTopButtons}" >
  	
<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}" scope="request" />


<script language="JavaScript" type="text/javascript" src="dwr/util.js"></script>
	
<link rel="stylesheet" href="css/jquery/screen.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/kuali-stylesheet.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/jquery.treeview.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery/kuali-stylesheet.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery/jquery.treeview.css" type="text/css" />

<script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>
	
<div align="right"><kul:help documentTypeName="TimeAndMoneyDocument" pageName="Time And Money" /></div>
<div id="workarea">
<kra-timeandmoney:actionSummary />
<kra-timeandmoney:timeAndMoneyHistory />
<kul:panelFooter />
</div>

<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
</SCRIPT>
<script language="javascript" src="scripts/kuali_application.js"></script>

<c:if test="${readOnly}">
	<c:set var="extraButtons" value="${KualiForm.extraButtons}" scope="request"/>
</c:if>

<kul:documentControls transactionalDocument="true" suppressRoutingControls="true"
													extraButtons="${extraButtons}"
													extraButtonSource="${extraButtonSource}" 
													extraButtonProperty="${extraButtonProperty}"
													extraButtonAlt="${extraButtonAlt}" />

</kul:documentPage>
