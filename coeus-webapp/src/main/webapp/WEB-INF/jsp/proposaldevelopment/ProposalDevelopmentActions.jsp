<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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

<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />

<c:set var="hierarchyStatus"
	value="${KualiForm.document.developmentProposalList[0].hierarchyStatus}" />
<c:set var="hierarchyChildStatus"
	value="${KualiForm.hierarchyChildStatus}" />
<c:set var="readOnly"
	value="${not KualiForm.editingMode['modifyProposal']}" scope="request" />
<%-- Proposal Actions Page - Submit To Grants.gov Button - Commented Temporarily--%>
<c:set var="extraButtons" value="${KualiForm.extraActionsButtons}"
	scope="request" />
<c:set var="showRejectionConfirmation" value="${KualiForm.showRejectionConfirmation}"
	scope="request" />
<kra:section permission="submitToSponsor">

</kra:section>

<kul:documentPage showDocumentInfo="true"
	htmlFormAction="proposalDevelopmentActions"
	documentTypeName="ProposalDevelopmentDocument" 
	renderMultipart="true"
	showTabButtons="true" auditCount="0"
	headerDispatch="${KualiForm.headerDispatch}" headerTabActive="actions">
	
	<c:choose>
		<c:when test="${showRejectionConfirmation}">
				<kra-pd:proposalDevelopmentRejection />
		</c:when>
		<c:otherwise>
			
			<div align="right"><kul:help
			documentTypeName="ProposalDevelopmentDocument"
			pageName="Proposal Actions" /></div>
		<c:if test="${hierarchyStatus != hierarchyChildStatus}">
			<kra:dataValidation auditActivated="${KualiForm.auditActivated}"
				categories="Validation Errors,Warnings,Grants.Gov Errors"
				topTab="true"
				helpParameterNamespace="KC-PD" 
				helpParameterDetailType="Document" 
				helpParameterName="proposalDevelopmentDataValidation1HelpUrl">
				<p>You can activate a Validation check to determine any errors or
				incomplete information. The following Validations types will be
				determined:</p>
				<ul>
					<li>errors that prevent submission into routing</li>
					<li>warnings that serve as alerts to possible data issues but
					will not prevent submission into routing</li>
					<li>errors that prevent submission to grants.gov</li>
				</ul>
	
			</kra:dataValidation>
		</c:if>
	
	
		<c:if test="${hierarchyStatus == hierarchyChildStatus}">
			<kul:tabTop tabTitle="Data Validation" defaultOpen="false">
				<div class="tab-container" align="center">
				<h3><span class="subhead-left">Data Validation</span></h3>
				<table cellpadding="0" cellspacing="0" summary="">
					<tr>
						<td>
						<div class="floaters">
						<p>Data Validation is not valid for a Child Proposal in a
						Proposal Hierarchy.</p>
						</div>
						</td>
					</tr>
				</table>
				</div>
			</kul:tabTop>
		</c:if>
		<kra-pd:proposalDevelopmentHierarchy />
		<kra:section permission="printProposal">
			<kra-pd:proposalDevelopmentPrintForms />
		</kra:section>
		<kra-pd:proposalDevelopmentCopy />
	
		<kra:section permission="showAlterProposalData">
			<kra-pd:proposalDataOverride />
		</kra:section>
		<kra:section permission="showAlterProposalData">
			<kra-pd:budgetDataOverride />
		</kra:section>
		<c:if test="${hierarchyStatus != hierarchyChildStatus}">
			<kul:routeLog />
			<kul:adHocRecipients />
			<kul:superUserActions showTab="false"/>
			
		</c:if>
	
		<kul:panelFooter />
		
		<%-- Added document authorizer for the reject functionality. --%>
		<%-- <c:if test="${not KualiForm.suppressAllButtons}">
	          <c:if test="${KualiForm.documentActions[Constants.KUALI_ACTION_CAN_APPROVE] and KualiForm.reject}">
	              <c:set var="extraButtonSource" value="${ConfigProperties.externalizable.images.url}buttonsmall_reject.gif"/>
	              <c:set var="extraButtonProperty" value="methodToCall.reject"/>
	              <c:set var="extraButtonAlt" value="Reject the document"/>
	           </c:if> 
	
	</c:if> --%>
	<c:if test="${KualiForm.saveXmlPermission and KualiForm.grantsGovSelectFlag}">
			<div id="btn_img_content">
  			<html:image
						property="methodToCall.saveXml"
						src="${ConfigProperties.kra.externalizable.images.url}buttonsmall_savexml.gif" alt="Save Grants.Gov Xml" onclick="excludeSubmitRestriction=true" style="border:none;" styleId="saveXml" />
						</div> 
  		
  	</c:if>
		<p><kul:documentControls transactionalDocument="true"
			extraButtonSource="${extraButtonSource}"
			extraButtonProperty="${extraButtonProperty}"
			extraButtonAlt="${extraButtonAlt}" extraButtons="${extraButtons}"
			suppressCancelButton="${hierarchyStatus == KualiForm.hierarchyParentStatus}" />
		</p>
	
		
		<script language="javascript" src="scripts/kuali_application.js"></script>
		<SCRIPT type="text/javascript">
			var kualiForm = document.forms['KualiForm'];
			var kualiElements = kualiForm.elements;
			function addToGlobalButtons() {
	  			var imgdata=jQuery("#btn_img_content").html();
	  			jQuery("#btn_img_content").remove();
	  			setTimeout(function(){ 				
	  				jQuery("#globalbuttons").prepend(imgdata); 
	  			},100);
	  		}
	  		window.onload=addToGlobalButtons();
		</SCRIPT>
			
		</c:otherwise>
	</c:choose>

	

</kul:documentPage>
