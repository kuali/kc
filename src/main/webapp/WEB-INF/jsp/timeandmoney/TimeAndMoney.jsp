<%--
 Copyright 2006-2009 The Kuali Foundation

 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.osedu.org/licenses/ECL-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

  <kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="timeAndMoney"
	documentTypeName="TimeAndMoneyDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="timeAndMoney">

	<script language="JavaScript" type="text/javascript"
		src="dwr/engine.js"></script>

	<script language="JavaScript" type="text/javascript" src="dwr/util.js"></script>

	<script language="JavaScript" type="text/javascript"
		src="dwr/interface/CustomAttributeService.js"></script>

	<script language="JavaScript" type="text/javascript"
		src="dwr/interface/SponsorService.js"></script>
		
	<script language="JavaScript" type="text/javascript"
		src="dwr/interface/AwardHierarchyUIService.js"></script>	


	<script src="scripts/jquery/jquery.js"></script>
	<link rel="stylesheet" href="css/jquery/screen.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery/kuali-stylesheet.css"
		type="text/css" />
	<link rel="stylesheet" href="css/jquery/jquery.treeview.css"
		type="text/css" />
	<%-- link rel="stylesheet" href="http://dev.jquery.com/view/trunk/plugins/treeview/jquery.treeview.css" type="text/css" /--%>
	<script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>
	<script type="text/javascript" src="scripts/awardHierarchy.js"></script>
	
<div align="right"><kul:help documentTypeName="TimeAndMoneyDocument" pageName="Time and Money" /></div>
<kul:documentOverview editingMode="${KualiForm.editingMode}" />

<kra-timeandmoney:awardHierarchyTimeAndMoney />

<kra-timeandmoney:transactions />
<kra-a:awardDirectFnAFundsDistribution />
<kra-timeandmoney:summary />
<kra-timeandmoney:actionSummary />
<kra-timeandmoney:history />

<kul:adHocRecipients />
<kul:routeLog />
<kul:panelFooter />

<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
</SCRIPT>
<script language="javascript" src="scripts/kuali_application.js"></script>
<script language="javascript" src="dwr/interface/SponsorService.js"></script>

<kul:documentControls transactionalDocument="true" suppressRoutingControls="false" />

</kul:documentPage>
