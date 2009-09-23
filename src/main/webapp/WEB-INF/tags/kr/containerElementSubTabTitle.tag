<%--
 Copyright 2007 The Kuali Foundation
 
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


<%@ attribute name="containerField" required="true" type="org.kuali.rice.kns.web.ui.Field"
              description="A Field.CONTAINER (element) type field." %>
<%@ attribute name="isFieldAddingToACollection" required="true"
              description="Whether this is an add element (versus an existing element)." %>

<c:choose>
<c:when test="${isFieldAddingToACollection}" >
    New <c:out value="${containerField.containerElementName}" default="Addition"/>
</c:when>
<c:otherwise >
    <c:out value="${containerField.containerElementName}"/>
    <c:forEach items="${containerField.containerDisplayFields}" var="summaryField" varStatus="status"> 
        ${status.first ? "(" : ""}
        <kul:readonlyfield addHighlighting="false" field="${summaryField}"/>
        ${status.last ? ")" : "-"}
    </c:forEach>
</c:otherwise>
</c:choose>
