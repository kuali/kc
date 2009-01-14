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
<%@ page
	import="org.kuali.kra.infrastructure.Constants,org.kuali.core.service.DataDictionaryService,org.kuali.kra.infrastructure.KraServiceLocator"%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<html>

<%
	String kraTextAreaFieldLabel = null;
	if (request.getParameter(Constants.TEXT_AREA_FIELD_LABEL) == null
			|| request.getParameter(Constants.TEXT_AREA_FIELD_LABEL).trim().equals("")) {
		kraTextAreaFieldLabel = (String) request.getAttribute(Constants.TEXT_AREA_FIELD_LABEL);
	} else {
		kraTextAreaFieldLabel = request.getParameter(Constants.TEXT_AREA_FIELD_LABEL);
	}
	
	String kraTextAreaFieldName = null;
	if (request.getParameter(Constants.TEXT_AREA_FIELD_NAME) == null
			|| request.getParameter(Constants.TEXT_AREA_FIELD_NAME).trim().equals("")) {
		kraTextAreaFieldName = (String) request.getAttribute(Constants.TEXT_AREA_FIELD_NAME);
	} else {
		kraTextAreaFieldName = request.getParameter(Constants.TEXT_AREA_FIELD_NAME);
	}
	String viewOnly = null;
	if (request.getParameter(Constants.VIEW_ONLY) == null
			|| request.getParameter(Constants.VIEW_ONLY).trim().equals("")) {
		viewOnly = (String) request.getAttribute(Constants.VIEW_ONLY);
	} else {
		viewOnly = request.getParameter(Constants.VIEW_ONLY);
	}

	String kraHtmlFormAction = null;
	if (request.getParameter(Constants.HTML_FORM_ACTION) == null
			|| request.getParameter(Constants.HTML_FORM_ACTION).trim().equals("")) {
		kraHtmlFormAction = (String) request.getAttribute(Constants.HTML_FORM_ACTION);
	} else {
		kraHtmlFormAction = request.getParameter(Constants.HTML_FORM_ACTION);
	}

%>

<link href="kr/css/kuali.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="scripts/kuali_application.js"></script>
<body onload="kraSetTextArea()">
<div class="headerarea" id="headerarea-small">
<h1><%=kraTextAreaFieldLabel%></h1>
</div>

<c:set var="kraAttributeReferenceDummyAttributes"
	value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />
<c:if test="${empty kraTextAreaFieldName}">
	<c:set var="kraTextAreaFieldName"
		value="<%=kraTextAreaFieldName%>" />
</c:if>
<c:if test="${empty htmlFormAction}">
	<c:set var="htmlFormAction"
		value="<%=kraHtmlFormAction%>" />
</c:if>
	<c:set var="viewOnly"
		value="<%=viewOnly%>" />

<html:form styleId="kualiForm" method="post"
	action="/${htmlFormAction}.do" enctype=""
	onsubmit="return hasFormAlreadyBeenSubmitted();">
	<table align="center">
		<tr>
			<td><kul:htmlControlAttribute property="${kraTextAreaFieldName}"
				attributeEntry="${kraAttributeReferenceDummyAttributes.bigDescription}" disabled="${viewOnly}"/>

			</td>
		</tr>

		<tr>
			<td>
			<c:choose>
			<c:when test="${viewOnly == 'true'}">
			<div id="globalbuttons" class="globalbuttons"><input
				type="image" name="methodToCall.kraPostTextAreaToParent.anchor${textAreaFieldAnchor}"
				onclick='javascript:window.close();'
				src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif"
				class="globalbuttons" title="return" alt="return"></div>
			</c:when>
			<c:otherwise>
			<div id="globalbuttons" class="globalbuttons"><input
				type="image" name="methodToCall.kraPostTextAreaToParent.anchor${textAreaFieldAnchor}"
				onclick='javascript:kraPostValueToParentWindow();return false'
				src="${ConfigProperties.kra.externalizable.images.url}buttonsmall_return.gif"
				class="globalbuttons" title="return" alt="return"></div>
			</c:otherwise>
			</c:choose>	
			</td>
		</tr>
	</table>
		<c:choose>
		<c:when test="${KualiForm.document.sessionDocument}" >
			<html:hidden property="documentWebScope" value="session"/>	
			<html:hidden property="formKey" value="${KualiForm.formKey}"/>	
			<html:hidden property="docFormKey" value="${KualiForm.formKey}"/>	
		</c:when>
		<c:otherwise>
			<html:hidden property="documentWebScope" value="request"/>	
		</c:otherwise>
	</c:choose>
	
</html:form>
</body>

</html>