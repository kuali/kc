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
	htmlFormAction="institutionalProposalActions"
	documentTypeName="InstitutionalProposalDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="actions">
  	
  	<div align="right"><kul:help documentTypeName="${KualiForm.documentTypeName}" pageName="Institutional Proposal Actions" /></div>

<c:set var="readOnly" value="${KualiForm.editingMode['viewOnly']}" scope="request" />
<c:set var="extraButtons" value="${KualiForm.extraActionsButtons}" scope="request" />
    
<kra:dataValidation auditActivated="${KualiForm.auditActivated}" topTab="true"/>
<kra-ip:institutionalProposalFundedAwards />
<kul:adHocRecipients />
<kra-ip:institutionalProposalPrint />
<kul:routeLog /> 

<kul:panelFooter />	
 
<kul:documentControls transactionalDocument="true"
                      extraButtonSource="${extraButtonSource}"
                      extraButtonProperty="${extraButtonProperty}"
                      extraButtonAlt="${extraButtonAlt}" 
                      extraButtons="${extraButtons}" />

<script language="javascript" src="scripts/kuali_application.js"></script>

</kul:documentPage>