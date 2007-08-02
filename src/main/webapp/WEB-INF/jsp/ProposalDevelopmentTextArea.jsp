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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%
			String textAreaFieldName = (String) request
			.getAttribute(org.kuali.kra.infrastructure.Constants.TEXT_AREA_FIELD_NAME);
	String htmlFormAction = (String) request
			.getAttribute(org.kuali.kra.infrastructure.Constants.HRML_FORM_ACTION);
	String contextPath = (String) request.getContextPath();
%>
<c:set var="textAreaFieldName" value="<%=textAreaFieldName%>" />
<c:set var="contextPath" value="<%=contextPath%>" />

<kul:page showDocumentInfo="false" headerTabActive="false"
	headerMenuBar="" headerTitle="TextArea" docTitle="Description"
	transactionalDocument="false" htmlFormAction="<%=htmlFormAction%>">


	<c:set var="kraAttributeReferenceDummyAttributes"
		value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />


	<kul:htmlControlAttribute property="${textAreaFieldName}"
		attributeEntry="${kraAttributeReferenceDummyAttributes.bigDescription}" />
	<div id="globalbuttons" class="globalbuttons"><input type="image"
		name="methodToCall.postTextAreaToParent"
		src="${contextPath}/kr/static/images/buttonsmall_save.gif"
		class="globalbuttons" title="save" alt="save"></div>
</kul:page>

