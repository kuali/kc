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
	htmlFormAction="institutionalProposalIntellectualPropertyReview"
	documentTypeName="InstitutionalProposalDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="intellectualPropertyReview">
  	
  	<div align="right">
        <kra:shortUrl shortUrl="${KualiForm.shortUrl}"/>
        <kul:help documentTypeName="InstitutionalProposalDocument" pageName="Intellectual Property Review" />
    </div>

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
