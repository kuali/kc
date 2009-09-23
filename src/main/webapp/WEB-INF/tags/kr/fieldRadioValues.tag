<%--
 Copyright 2007-2008 The Kuali Foundation
 
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

<%@ attribute name="field" required="true" type="org.kuali.rice.kns.web.ui.Field"%>

${kfunc:registerEditableProperty(KualiForm, field.propertyName)}
<c:forEach items="${field.fieldValidValues}" var="radio">
  <c:if test="${!empty radio.label}">
    <input type="radio"
        ${field.propertyValue eq radio.key ? 'checked="checked"' : ''}
        name='${field.propertyName}'
        value='<c:out value="${radio.key}" />'
		title='${field.fieldLabel} - ${radio.label}'
        ${onblurcall} />
    <c:out value="${radio.label}"/>
  </c:if>
</c:forEach>
