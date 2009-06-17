<%--
 Copyright 2006-2009 The Kuali Foundation.
 
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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<%@ attribute name="includeDocumentHeaderFields" required="false" %>
<%@ attribute name="includeEditMode" required="false" %>

<c:set var="documentTypeName" value="${KualiForm.docTypeName}" />
<c:set var="documentEntry" value="${DataDictionary[documentTypeName]}" />
<c:set var="sessionDocument" value="${documentEntry.sessionDocument}" />


<%-- set default values --%>
<c:if test="${empty includeDocumentHeaderFields}">
    <c:set var="includeDocumentHeaderFields" value="true" />
</c:if>
<c:if test="${empty includeEditMode}">
    <c:set var="includeEditMode" value="true" />
</c:if>

<html:hidden property="docId" />
<html:hidden property="document.documentNumber" />

<c:choose>
	<c:when test="${KualiForm.document.sessionDocument || sessionDocument}">
		</c:when>
		<c:otherwise>
			<html:hidden property="docTypeName" />
			<html:hidden property="document.versionNumber" />
			<html:hidden property="document.objectId" />
        </c:otherwise>
</c:choose>
<c:if test="${includeDocumentHeaderFields}">
  <html:hidden property="document.documentHeader.documentNumber" />  
  <c:choose>
	<c:when test="${KualiForm.document.sessionDocument || sessionDocument}">
		</c:when>
		<c:otherwise>
			<html:hidden property="document.documentHeader.versionNumber" />
    		<html:hidden property="document.documentHeader.objectId" />
    		<html:hidden property="document.documentHeader.documentTemplateNumber" />
        </c:otherwise>
	</c:choose>
</c:if>
<c:if test="${includeEditMode}">
    <c:forEach items="${KualiForm.editingMode}" var="mode">
      <html:hidden property="editingMode(${mode.key})"/>
    </c:forEach>
</c:if>
