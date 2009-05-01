<%--
Copyright 2006-2008 The Kuali Foundation

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
<%-- Proposal Actions Page - Submit To Grants.gov Button - Commented Temporarily--%>
<kra:section permission="submitToSponsor">
 <c:set var="extraButtons" value="${KualiForm.extraActionsButtons}" scope="request"/> 
</kra:section>

<kul:documentPage
showDocumentInfo="true"
	htmlFormAction="proposalDevelopmentActions"
		documentTypeName="ProposalDevelopmentDocument"
			renderMultipart="false"
				showTabButtons="true"
					auditCount="0"
						headerDispatch="${KualiForm.headerDispatch}"
							headerTabActive="actions">
							
<div align="right"><kul:help documentTypeName="ProposalDevelopmentDocument" pageName="Proposal Actions" /></div>
<kra:dataValidation auditActivated="${KualiForm.auditActivated}" categories="Validation Errors,Warnings,Grants.Gov Errors" topTab="true">
   <p>You can activate a Validation check to determine any errors or incomplete information. The following Validations types will be determined:</p>
   <ul>
     <li>errors that prevent submission into routing</li>
     <li>warnings that serve as alerts to possible data issues but will not prevent submission into routing</li>
     <li>errors that prevent submission to grants.gov</li>
   </ul>
</kra:dataValidation>

<kra-pd:proposalDevelopmentHierarchy /> 
<kra:section permission="printProposal">
   <kra-pd:proposalDevelopmentPrintForms /> 
</kra:section>
<kra-pd:proposalDevelopmentCopy />

<kra:section permission="showAlterProposalData">
	<kra-pd:proposalDataOverride />
</kra:section>

<c:if test="${KualiForm.submissionStatusVisible}">
	<kra-pd:proposalDevelopmentPostSubmissionStatus />
	<c:if test="${!KualiForm.submissionStatusReadOnly}">
		<c:set var="extraButtonSource" value="${ConfigProperties.externalizable.images.url}buttonsmall_save.gif"/>
    	<c:set var="extraButtonProperty" value="methodToCall.saveProposalActions"/>
    	<c:set var="extraButtonAlt" value="Save the document"/>
    </c:if>
</c:if>

<kul:routeLog /> 
<kra-pd:adHocRecipients /> 
<kul:panelFooter />
<c:if test="${not KualiForm.suppressAllButtons}">
          <c:if test="${KualiForm.documentActions[Constants.KUALI_ACTION_CAN_APPROVE] and KualiForm.reject}">
              <c:set var="extraButtonSource" value="${ConfigProperties.externalizable.images.url}buttonsmall_reject.gif"/>
              <c:set var="extraButtonProperty" value="methodToCall.reject"/>
              <c:set var="extraButtonAlt" value="Reject the document"/>
           </c:if> 

</c:if>

<p>
<kul:documentControls 
transactionalDocument="true"
	extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
			extraButtonAlt="${extraButtonAlt}" 
				extraButtons="${extraButtons}" />
</p>

<script language="javascript" src="scripts/kuali_application.js"></script>
<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
</SCRIPT>
</kul:documentPage>