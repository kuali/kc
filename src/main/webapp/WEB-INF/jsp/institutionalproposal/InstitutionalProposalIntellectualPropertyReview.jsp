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
<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="institutionalProposalIntellectualPropertyReview"
	documentTypeName="InstitutionalProposalDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="intellectualPropertyReview">
  	
This is the  Institutional Proposal IntellectualPropertyReview- Under Construction

<kra-ip:institutionalProposalReviewData readOnly="true" />
<kra-ip:institutionalProposalActivities />

<kul:panelFooter />	

<c:if test="${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_SAVE] and not viewOnly}">
    <div align="center">
        <html:image src="${ConfigProperties.kra.externalizable.images.url}buttonsmall_editipreview.gif" styleClass="globalbuttons" property="methodToCall.editIntellectualPropertyReview" title="Edit IP Review" alt="Edit Intellectual Property Review"
            onclick="javascript: openNewWindow('institutionalProposalIntellectualPropertyReview','editIntellectualPropertyReview','','${KualiForm.formKey}','${KualiForm.document.sessionDocument}');return false" />
    </div>
</c:if>
                
<script language="javascript" src="scripts/kuali_application.js"></script>

</kul:documentPage>