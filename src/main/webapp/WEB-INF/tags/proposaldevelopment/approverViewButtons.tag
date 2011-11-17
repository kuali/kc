<%--
 Copyright 2005-2007 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="transactionalDocument" required="true" description="Boolean value of whether this is a transactional document the buttons are being displayed on or not." %>
<%@ attribute name="saveButtonOverride" required="false" description="Overrides the methodToCall for the save button." %>
<%@ attribute name="suppressRoutingControls" required="false" description="Boolean value of whether any buttons which result in routing - Submit, Approve, etc - should be displayed." %>
<%@ attribute name="suppressCancelButton" required="false" description="Boolean value of whether the cancel button should be displayed." %>
<%@ attribute name="extraButtonSource" required="false" description="The image src of a single extra button." %>
<%@ attribute name="extraButtonProperty" required="false" description="The methodToCall property of a single extra button." %>
<%@ attribute name="extraButtonAlt" required="false" description="The alt description of a single extra button." %>
<%@ attribute name="extraButtons" required="false" type="java.util.List" description="A List of org.kuali.rice.kns.web.ui.ExtraButton objects to render before the standard button." %>
<%@ attribute name="viewOnly" required="false" description="Boolean value of whether this document is view only, which means in effect the save button would be suppressed." %>

<c:set var="tabindex" value="0" />

<c:set var="documentTypeName" value="${KualiForm.docTypeName}" />
<c:set var="documentEntry" value="${DataDictionary[documentTypeName]}" />
        <c:set var="saveButtonValue" value="save" />
        <c:if test="${not empty saveButtonOverride}"><c:set var="saveButtonValue" value="${saveButtonOverride}" /></c:if>
		
		<c:if test="${not KualiForm.suppressAllButtons}">
	        <div id="globalbuttons" class="globalbuttons">
	            <c:if test="${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_APPROVE] and not suppressRoutingControls}">
	                <html:image src="${ConfigProperties.kra.externalizable.images.url}approve_button.png" styleClass="globalbuttons" property="methodToCall.approve" title="approve" alt="approve" tabindex="${tabindex}" />
	            </c:if>
	            <c:if test="${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_DISAPPROVE] and not suppressRoutingControls}">
	                <html:image src="${ConfigProperties.kra.externalizable.images.url}disapprove_button.png" styleClass="globalbuttons" property="methodToCall.disapprove" title="disapprove" alt="disapprove" tabindex="${tabindex}" />
	            </c:if>
	        </div>
        </c:if>
        
