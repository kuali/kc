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
  	headerTabActive="home">

This is the Time and Money Page - Under Construction

<div align="right"><kul:help documentTypeName="TimeAndMoneyDocument" pageName="Time and Money" /></div>
<kul:documentOverview editingMode="${KualiForm.editingMode}" />
<kra-timeandmoney:awardHierarchyTimeAndMoney />
<kra-timeandmoney:transactions />
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
