<%--
 Copyright 2006-2008 The Kuali Foundation
 
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
<%@ tag
    description="This tag evaluates a String as an EL name, returning the Map named by the String.
    It would not be needed if there were some way to evaluate the contents of an EL as an EL." %>

<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="mapName" required="true"
    description="The name of the map to return, e.g., 'DataDictionary.Budget.attributes.budgetAgency'.
    This tag cannot handle map keys containing . (dot), because dot is used to delimit properties and keys." %>
<%@ attribute name="returnVar" required="true" rtexprvalue="false"
    description="The name of the variable in the caller to set with the result." %>
<%@ variable name-from-attribute="returnVar" alias="valueHolder" scope="AT_END" variable-class="java.util.Map" %>

<c:forTokens items='${mapName}' var='namePart' delims='.'>
    <c:choose>
        <c:when test="${empty walker}">
            <c:set var='walker' value="${pageScope[namePart]}" />
            <c:if test="${empty walker}">
                <c:set var='walker' value="${requestScope[namePart]}" />
            </c:if>
            <c:if test="${empty walker}">
                <c:set var='walker' value="${sessionScope[namePart]}" />
            </c:if>
            <c:if test="${empty walker}">
                <c:set var='walker' value="${applicationScope[namePart]}" />
            </c:if>
        </c:when>
        <c:otherwise>
            <c:set var='walker' value="${walker[namePart]}" />
        </c:otherwise>
    </c:choose>
</c:forTokens>
<c:set var='valueHolder' value="${walker}" />
