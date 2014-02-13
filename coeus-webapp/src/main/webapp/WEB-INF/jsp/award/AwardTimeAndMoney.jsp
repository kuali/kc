<%--
 Copyright 2005-2013 The Kuali Foundation

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
	htmlFormAction="awardTimeAndMoney"
	documentTypeName="AwardDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="timeAndMoney">
  	
This is the Award Time &amp; Money - Under Construction
<div align="right"><kul:help documentTypeName="AwardDocument" pageName="TimeAndMoney" /></div>

<kra-a:awardTimeAndMoney />
<kra-a:awardBudget />
<kra-a:awardDirectFnAFundsDistribution />
<kra-a:awardSummary />
<kra-a:awardHistory />

<kul:panelFooter />

<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
</SCRIPT>
<script language="javascript" src="scripts/kuali_application.js"></script>
<script language="javascript" src="dwr/interface/AwardFandaRateService.js"></script>
<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" suppressCancelButton="true" />

</kul:documentPage>

