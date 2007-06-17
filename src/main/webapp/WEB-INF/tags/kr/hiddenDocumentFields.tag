<%--
 Copyright 2005-2007 The Kuali Foundation.
 
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
<%@ attribute name="isFinancialDocument" required="false" %>
<%@ attribute name="isTransactionalDocument" required="false" %>
<%@ attribute name="excludePostingYear" required="false" %>

<%-- set default values --%>
<c:if test="${empty isFinancialDocument}">
    <c:set var="isFinancialDocument" value="true" />
</c:if>
<c:if test="${empty isTransactionalDocument}">
    <c:set var="isTransactionalDocument" value="true" />
</c:if>


<html:hidden property="docId" />
<html:hidden property="docTypeName" />

<html:hidden property="document.documentNumber" />
<html:hidden property="document.versionNumber" />
<html:hidden property="document.objectId" />

<%-- if !isFinancialDocument, can't be transactional --%>
<c:if test="${isFinancialDocument}">    
    <html:hidden property="document.documentHeader.versionNumber" />
    <html:hidden property="document.documentHeader.documentNumber" />
	<html:hidden property="document.documentHeader.objectId" />
    <c:if test="${isTransactionalDocument}">
        <c:if test="${!excludePostingYear}">
            <html:hidden property="document.postingYear" /> 
        </c:if>
        <html:hidden property="document.postingPeriodCode" /> 
        
        <html:hidden property="document.nextSourceLineNumber" />
        <html:hidden property="document.nextTargetLineNumber" />
        
        <html:hidden property="document.documentHeader.financialDocumentInErrorNumber" />
        <html:hidden property="document.documentHeader.correctedByDocumentId" />
    
        <html:hidden property="document.documentHeader.financialDocumentTemplateNumber" />
    </c:if>    
        
    <c:forEach items="${KualiForm.editingMode}" var="mode">
      <html:hidden property="editingMode(${mode.key})"/>
    </c:forEach>
</c:if>
