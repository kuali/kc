<%--
 Copyright 2005-2006 The Kuali Foundation.
 
 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
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
	DataDictionaryService dataDictionaryService = (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
	String textAreaFieldName = null;
	String documentClassName = null;
	if (request.getParameter(Constants.TEXT_AREA_FIELD_NAME) == null
			|| request.getParameter(Constants.TEXT_AREA_FIELD_NAME).trim().equals("")) {
		textAreaFieldName = (String) request.getAttribute(Constants.TEXT_AREA_FIELD_NAME);
		documentClassName = (String) request.getAttribute(Constants.DOCUMENT_CLASS_NAME);
	} else {
		textAreaFieldName = request.getParameter(Constants.TEXT_AREA_FIELD_NAME);
		documentClassName = (String) request.getParameter(Constants.DOCUMENT_CLASS_NAME);
	}
	String label = dataDictionaryService.getAttributeLabel(Class.forName(documentClassName),textAreaFieldName.split("\\.")[1]);
%>

<link href="/kra-dev/kr/css/kuali.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="scripts/kuali_application.js"></script>
<div class="headerarea" id="headerarea-small">
<h1><%=label%></h1>
</div>

<body onload="setTextArea()">
<c:set var="kraAttributeReferenceDummyAttributes"
	value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />
<c:if test="${empty textAreaFieldName}">
	<c:set var="textAreaFieldName"
		value="<%=request.getParameter("textAreaFieldName")%>" />
</c:if>
<c:if test="${empty htmlFormAction}">
	<c:set var="htmlFormAction"
		value="<%=request.getParameter("htmlFormAction")%>" />
</c:if>

<html:form styleId="kualiForm" method="post"
	action="/${htmlFormAction}.do" enctype=""
	onsubmit="return hasFormAlreadyBeenSubmitted();">
	<table>
		<tr>
			<td><kul:htmlControlAttribute property="${textAreaFieldName}"
				attributeEntry="${kraAttributeReferenceDummyAttributes.bigDescription}" />

			</td>
		</tr>

		<tr>
			<td>
			<div id="globalbuttons" class="globalbuttons"><input
				type="image" name="methodToCall.postTextAreaToParent"
				onclick='javascript:postValueToParentWindow();return false'
				src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif"
				class="globalbuttons" title="save" alt="save"></div>
			</td>
		</tr>
	</table>
</html:form>
</body>

</html>