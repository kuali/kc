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
	htmlFormAction="institutionalProposalIntellectualPropertyReview"
	documentTypeName="InstitutionalProposalDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="intellectualPropertyReview">
  	
  	<div align="right"><kul:help documentTypeName="InstitutionalProposalDocument" pageName="Intellectual Property Review" /></div>

<kra-ip:institutionalProposalReviewData />
<kra-ip:institutionalProposalActivities />

<kul:panelFooter />	

<div id="globalbuttons" class="globalbuttons">
    <html:image src="${ConfigProperties.kra.externalizable.images.url}buttonsmall_editipreview.gif" styleClass="globalbuttons" property="methodToCall.editIntellectualPropertyReview" title="Edit IP Review" alt="Edit Intellectual Property Review"
    onclick="javascript: openNewWindow('institutionalProposalIntellectualPropertyReview','editIntellectualPropertyReview','','${KualiForm.formKey}','${KualiForm.document.sessionDocument}');return false" />
    <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_reload.gif" styleClass="globalbuttons" property="methodToCall.reload" title="reload" alt="reload" onclick="excludeSubmitRestriction=true"/>
    <c:if test="${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_CLOSE]}">
        <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif" styleClass="globalbuttons" property="methodToCall.close" title="close" alt="close"/>
    </c:if>
</div>
                
<script language="javascript" src="scripts/kuali_application.js"></script>

</kul:documentPage>