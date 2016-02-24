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
	htmlFormAction="awardPaymentReportsAndTerms"
	documentTypeName="AwardDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="paymentReportsAndTerms"
  	extraTopButtons="${KualiForm.extraTopButtons}" >
 	
 	<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}" scope="request" />

<div align="right">
	<kra:shortUrl shortUrl="${KualiForm.shortUrl}"/>
	<kul:help documentTypeName="AwardDocument" pageName="Payment%2C%20Reports%20%26%20Terms" />
</div>

<kra-a:awardPaymentAndInvoices />
<kra-a:awardCgb />
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
