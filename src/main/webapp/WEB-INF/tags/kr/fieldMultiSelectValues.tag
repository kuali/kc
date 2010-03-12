<%--
 Copyright 2007-2009 The Kuali Foundation
 
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

<%@ attribute name="field" required="true" type="org.kuali.rice.kns.web.ui.Field" description="The field to render option lines for." %>

<c:forEach items="${field.fieldValidValues}" var="select">
	<c:set var="propertySelected" value="${false}"/>
	<c:forEach items="${field.propertyValues}" var="propertyValue">
		<c:if test="${propertyValue eq select.key}">
			<c:set var="propertySelected" value="${true}"/>
		</c:if>
    </c:forEach>
    <option ${propertySelected ? 'selected="selected"' : ''}
            value='<c:out value="${select.key}"/>'>${select.htmlSpacePadding}<c:out value="${select.label}" /></option>
</c:forEach>
