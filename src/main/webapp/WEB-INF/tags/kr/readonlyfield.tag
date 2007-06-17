<%--
 Copyright 2007 The Kuali Foundation.
 
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

<%@ attribute name="field" required="true" type="org.kuali.core.web.ui.Field"%>
<%@ attribute name="addHighlighting" required="false"
              description="boolean indicating if this field should be highlighted (to indicate old/new change)" %>

<c:set var="result">
    <c:choose>
      <c:when test="${field.fieldType==field.DROPDOWN or field.fieldType==field.DROPDOWN_APC or field.fieldType==field.DROPDOWN_REFRESH or field.fieldType==field.DROPDOWN_SCRIPT}">
        <c:set var="value_found" value="false" />
        <c:forEach items="${field.fieldValidValues}" var="select">
          <c:if test="${field.propertyValue==select.key}">
            <c:out value="${select.label}" />
            <c:set var="value_found" value="true" />
          </c:if>
        </c:forEach>
        <c:if test="${!value_found}">
          <c:out value="${KualiForm.unconvertedValues[field.propertyName]}" default="${field.propertyValue}" />
        </c:if>
      </c:when>
      <c:otherwise>
        <c:out value="${KualiForm.unconvertedValues[field.propertyName]}" default="${field.propertyValue}" />
      </c:otherwise>
    </c:choose>
</c:set>

<c:choose>
  <c:when test="${empty result}">&nbsp;</c:when>
  <c:otherwise>${result}</c:otherwise>
</c:choose>

<c:if test="${addHighlighting && field.highlightField}">
   <kul:fieldShowChangedIcon />
</c:if>