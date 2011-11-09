<%--
 Copyright 2005-2010 The Kuali Foundation

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
	htmlFormAction="awardPaymentReportsAndTerms"
	documentTypeName="AwardDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="paymentReportsAndTerms"
  	extraTopButtons="${KualiForm.extraTopButtons}" >
 	
 	<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}" scope="request" />

<div align="right"><kul:help documentTypeName="AwardDocument" pageName="Payment%2C%20Reports%20%26%20Terms" /></div>

<kra-a:awardPaymentAndInvoices />
<kra-a:awardReports />
<kra-a:awardTerms />
<kra-a:awardSpecialApproval />
<kra-a:awardCloseout />

<kul:panelFooter />

<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
</SCRIPT>
<script language="javascript" src="scripts/kuali_application.js"></script>
<script language="javascript" src="dwr/interface/AwardReportsService.js" ></script>
<script language="javascript" src="dwr/interface/AwardPaymentAndInvoicesService.js" ></script>
<script language="javascript" src="scripts/awardApprovedForeignTravel.js"></script>
<script type="text/javascript">
  jQuery(document).ready(function($) {
	  $('[name*="toggleTabReporting"]').each(function () {
		  var id = $(this).attr('id');
		  $(this).attr('id', id + '-noexpandall');
	  });
  });
</script>
<c:choose>
	<c:when test="${KualiForm.displayRegenerateButton }">
		<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" suppressCancelButton="true" 
			extraButtonSource="${ConfigProperties.kra.externalizable.images.url}generate-report-tracking.jpg" 
			extraButtonProperty="methodToCall.regenerateReports" extraButtonAlt="Generate Reports" />
	</c:when>
	<c:otherwise>
		<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" suppressCancelButton="true" />
	</c:otherwise>
</c:choose>
</kul:documentPage>
