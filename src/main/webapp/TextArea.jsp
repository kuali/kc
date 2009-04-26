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
	import="org.kuali.kra.infrastructure.Constants,org.kuali.rice.kns.service.DataDictionaryService,org.kuali.kra.infrastructure.KraServiceLocator"%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<html>

	<c:set var="popWindow" value="true" />
	<c:forEach items="${param}" var="par">
	   <c:if test="${fn:startsWith(par.key, 'methodToCall.kraUpdateTextArea')==true}">
	       <c:set var="popWindow" value="false" />
       </c:if>
	   <c:if test="${par.key == 'textAreaFieldLabel'}">
	       <c:set var="kraTextAreaFieldLabel" value="${par.value}" />
	   </c:if> 
	   <c:if test="${par.key == 'textAreaFieldName'}">
	       <c:set var="kraTextAreaFieldName" value="${par.value}" />
	   </c:if> 
	   <c:if test="${par.key == 'htmlFormAction'}">
	       <c:set var="htmlFormAction" value="${par.value}" />
	   </c:if> 
	   <c:if test="${par.key == 'viewOnly'}">
	       <c:set var="viewOnly" value="${par.value}" />
	   </c:if> 
	</c:forEach>
    <c:if test="${popWindow == 'false'}">
	       <c:set var="kraTextAreaFieldLabel" value="${requestScope['textAreaFieldLabel']}" />
	       <c:set var="kraTextAreaFieldName" value="${requestScope['textAreaFieldName']}" />
	       <c:set var="htmlFormAction" value="${requestScope['htmlFormAction']}" />
	       <c:set var="viewOnly" value="${requestScope['viewOnly']}" />
    </c:if>

<link href="kr/css/kuali.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="scripts/kuali_application.js"></script>
<body onload="kraSetTextArea()">
<div class="headerarea" id="headerarea-small">
<h1>${kraTextAreaFieldLabel}</h1>
</div>
<c:set var="kraAttributeReferenceDummyAttributes"
	value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />


<html:form styleId="kualiForm" method="post"
	action="/${htmlFormAction}.do" enctype=""
	onsubmit="return hasFormAlreadyBeenSubmitted();">
	<table align="center">
		<tr>
			<td>
			  <c:choose>
			    <c:when test="${viewOnly == 'true'}" >
		            <c:set var="attributeEntry" value="${kraAttributeReferenceDummyAttributes.bigDescription}"/>
		            <html:textarea property="${kraTextAreaFieldName}"  tabindex="0" 
		                           rows="${attributeEntry.control.rows}" cols="${attributeEntry.control.cols}" 
		                           styleId="${kraTextAreaFieldName}" 
		                           readonly="true"/> 
			    </c:when>
			    <c:otherwise>
					<kul:htmlControlAttribute property="${kraTextAreaFieldName}"
				 		attributeEntry="${kraAttributeReferenceDummyAttributes.bigDescription}"/>
                </c:otherwise>
			  </c:choose>
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
					class="globalbuttons" title="close" alt="close"></div>
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