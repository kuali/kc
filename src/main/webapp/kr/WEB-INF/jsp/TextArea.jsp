<%--
 Copyright 2005-2008 The Kuali Foundation
 
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
<%@ page
	import="org.kuali.rice.kns.web.struts.action.KualiAction,org.kuali.rice.core.util.RiceConstants,org.kuali.rice.kns.util.KNSConstants,java.util.Map"%>
<%@ include file="tldHeader.jsp"%>
<html:html>

<%
String textAreaFieldLabel = request.getParameter(KualiAction.TEXT_AREA_FIELD_LABEL);
if (textAreaFieldLabel == null) {
    textAreaFieldLabel = (String) request.getAttribute(KualiAction.TEXT_AREA_FIELD_LABEL);
}
%>

<head>
<link href="${pageContext.request.contextPath}/kr/css/kuali.css" rel="stylesheet" type="text/css" />
<%--<script language="javascript" src="/kuali-dev/kr/scripts/core.js"></script>--%>
<script language="javascript" src="${pageContext.request.contextPath}/kr/scripts/core.js"></script>
</head>
<body onload="setTextArea()">
<div class="headerarea" id="headerarea-small">
<h1><%=textAreaFieldLabel%></h1>
</div>

<c:set var="parameters"	value="<%=request.getParameterMap()%>" />

<c:set var="textAreaAttributes"
	value="${DataDictionary.AttributeReferenceElements.attributes}" />
<c:if test="${empty textAreaFieldName}">
	<c:set var="textAreaFieldName"
		value="<%=request.getAttribute(KualiAction.TEXT_AREA_FIELD_NAME)%>" />
</c:if>
<c:if test="${empty textAreaFieldName}">
	<c:set var="textAreaFieldName"
		value="<%=request.getParameter(KualiAction.TEXT_AREA_FIELD_NAME)%>" />
</c:if>
<c:if test="${empty htmlFormAction}">
	<c:set var="htmlFormAction"
		value="<%=request.getAttribute(KualiAction.FORM_ACTION)%>" />
</c:if>
<c:if test="${empty htmlFormAction}">
	<c:set var="htmlFormAction"
		value="<%=request.getParameter(KualiAction.FORM_ACTION)%>" />
</c:if>
<c:if test="${empty documentWebScope}">
	<c:set var="documentWebScope"
		value="<%=request.getAttribute(KNSConstants.DOCUMENT_WEB_SCOPE)%>" />
</c:if>
<c:if test="${empty documentWebScope}">
	<c:set var="documentWebScope"
		value="<%=request.getParameter(KNSConstants.DOCUMENT_WEB_SCOPE)%>" />
</c:if>
<c:if test="${empty documentWebScope}">
	<c:set var="documentWebScope" value="request" />
</c:if>
<c:if test="${empty docFormKey}">
	<c:set var="docFormKey"
		value="<%=request.getAttribute(KNSConstants.DOC_FORM_KEY)%>" />
</c:if>
<c:if test="${empty docFormKey}">
	<c:set var="docFormKey"
		value="<%=request.getParameter(KNSConstants.DOC_FORM_KEY)%>" />
</c:if>
<c:if test="${empty docFormKey}">
	<c:set var="docFormKey"	value="88888888" />
</c:if>
<c:if test="${empty textAreaFieldAnchor}">
	<c:set var="textAreaFieldAnchor"
		value="<%=request.getAttribute(KualiAction.TEXT_AREA_FIELD_ANCHOR)%>" />
</c:if>
<c:if test="${empty textAreaFieldAnchor}">
	<c:set var="textAreaFieldAnchor"
		value="<%=request.getParameter(KualiAction.TEXT_AREA_FIELD_ANCHOR)%>" />
</c:if>

<html:form styleId="kualiForm" method="post"
	action="/${htmlFormAction}.do" enctype=""
	onsubmit="return hasFormAlreadyBeenSubmitted();">

	<table>
		<tr>
			<td>
			  <div>
			    <kul:htmlControlAttribute property="${textAreaFieldName}"
				                          attributeEntry="${textAreaAttributes.extendedTextArea}" />
			  </div>
			</td>
		</tr>

		<tr>
			<td>
			  <div id="globalbuttons" class="globalbuttons">
				<html:image
					property="methodToCall.postTextAreaToParent.anchor${textAreaFieldAnchor}"
					onclick="javascript:postValueToParentWindow();return false"
					src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_continue.gif"
					styleClass="globalbuttons" title="return" alt="return" />
			  </div>
			</td>
		</tr>
	</table>

	<html:hidden property="documentWebScope" value="${documentWebScope}"/>
	<html:hidden property="formKey" value="${formKey}"/>
	<html:hidden property="docFormKey" value="${docFormKey}"/>
	<html:hidden property="refreshCaller" value="TextAreaRefresh"/>

    <c:if test="${not empty parameters}">
      <c:forEach items="${parameters}" var="mapEntry" >
        <c:if test="${not fn:contains(mapEntry.key,'methodToCall')}">
        <html:hidden property="${mapEntry.key}" value="${mapEntry.value[0]}"/>
        </c:if>
      </c:forEach>
	</c:if>

</html:form>
</body>

</html:html>
