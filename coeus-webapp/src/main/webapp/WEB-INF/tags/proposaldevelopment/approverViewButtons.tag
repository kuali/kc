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
        
