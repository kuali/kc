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

<%@ attribute name="transactionalDocument" required="true" %>
<%@ attribute name="saveButtonOverride" required="false" %>
<%@ attribute name="suppressRoutingControls" required="false" %>
<%@ attribute name="extraButtonSource" required="false" %>
<%@ attribute name="extraButtonProperty" required="false" %>
<%@ attribute name="extraButtonAlt" required="false" %>
<%@ attribute name="extraButtons" required="false" type="java.util.List" %>
<%@ attribute name="viewOnly" required="false" %>

<c:set var="documentTypeName" value="${KualiForm.docTypeName}" />
<c:set var="documentEntry" value="${DataDictionary[documentTypeName]}" />
        <c:set var="saveButtonValue" value="save" />
        <c:if test="${not empty saveButtonOverride}"><c:set var="saveButtonValue" value="${saveButtonOverride}" /></c:if>
		
		<c:if test="${not KualiForm.suppressAllButtons}">
	        <div id="globalbuttons" class="globalbuttons">
	        	<c:if test="${!empty extraButtonSource}">
	        		<html:image src="${extraButtonSource}" styleClass="globalbuttons" property="${extraButtonProperty}" alt="${extraButtonAlt}"/>
	        	</c:if>
	        	<c:if test="${!empty extraButtons}">
		        	<c:forEach items="${extraButtons}" var="extraButton">
		        		<html:image src="${extraButton.extraButtonSource}" styleClass="globalbuttons" property="${extraButton.extraButtonProperty}" title="${extraButton.extraButtonAltText}" alt="${extraButton.extraButtonAltText}"/>
		        	</c:forEach>
	        	</c:if>
	            <c:if test="${!empty KualiForm.documentActions[Constants.KUALI_ACTION_PERFORM_ROUTE_REPORT] and not suppressRoutingControls}">
				    <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_routereport.gif" styleClass="globalbuttons" property="methodToCall.performRouteReport" title="Perform Route Report" alt="Perform Route Report" />
	            </c:if>
	            <c:if test="${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_SEND_ADHOC_REQUESTS] and not suppressRoutingControls}">
	                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_sendadhocreq.gif" styleClass="globalbuttons" property="methodToCall.sendAdHocRequests" title="Send AdHoc Requests" alt="Send AdHoc Requests"/>
	            </c:if>
	            <c:if test="${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_ROUTE] and not suppressRoutingControls}">
	                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_submit.gif" styleClass="globalbuttons" property="methodToCall.route" title="submit" alt="submit"/>
	            </c:if>
	            <c:if test="${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_SAVE] and not viewOnly}">
	                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif" styleClass="globalbuttons" property="methodToCall.${saveButtonValue}" title="save" alt="save"/>
	            </c:if>
	            <c:if test="${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_RELOAD]}">
	                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_reload.gif" styleClass="globalbuttons" property="methodToCall.reload" title="reload" alt="reload" onclick="excludeSubmitRestriction=true"/>
	            </c:if>
	            <c:if test="${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_BLANKET_APPROVE] and not suppressRoutingControls}">
	                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_blanketapp.gif" styleClass="globalbuttons" property="methodToCall.blanketApprove" title="blanket approve" alt="blanket approve"/>
	            </c:if>
	            <c:if test="${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_APPROVE] and not suppressRoutingControls}">
	                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_approve.gif" styleClass="globalbuttons" property="methodToCall.approve" title="approve" alt="approve"/>
	            </c:if>
	            <c:if test="${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_DISAPPROVE] and not suppressRoutingControls}">
	                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_disapprove.gif" styleClass="globalbuttons" property="methodToCall.disapprove" title="disapprove" alt="disapprove"/>
	            </c:if>
	            <c:if test="${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_FYI] and not suppressRoutingControls}">
	                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_fyi.gif" styleClass="globalbuttons" property="methodToCall.fyi" title="fyi" alt="fyi"/>
	            </c:if>
	            <c:if test="${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_ACKNOWLEDGE] and not suppressRoutingControls}">
	                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_acknowledge.gif" styleClass="globalbuttons" property="methodToCall.acknowledge" title="acknowledge" alt="acknowledge"/>
	            </c:if>
	            <c:if test="${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_CLOSE]}">
	                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif" styleClass="globalbuttons" property="methodToCall.close" title="close" alt="close"/>
	            </c:if>            
	            <c:if test="${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_CANCEL]}">
	                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_cancel.gif" styleClass="globalbuttons" property="methodToCall.cancel" title="cancel" alt="cancel"/>
	            </c:if>
                <c:if test="${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_COPY]}">
                   <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_copy.gif" styleClass="globalbuttons" property="methodToCall.copy" title="Copy current document" alt="Copy current document"/>
                </c:if>
	        </div>
        </c:if>
        
