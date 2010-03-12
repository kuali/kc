<%--
 Copyright 2005-2008 The Kuali Foundation
 
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
<%@ tag body-content="scriptless" %>
<%@ attribute name="byteSize" required="true" description="The size, in bytes, to display as a file size." %>

<%@ variable name-given="fileSize" scope="NESTED" %>
<%@ variable name-given="fileSizeUnits" scope="NESTED" %>

<c:if test="${byteSize lt 1024}" >
    <c:set var="fileSize" value="${byteSize}" />
    <c:set var="fileSizeUnits" value="bytes" />
</c:if>

<c:if test="${byteSize ge 1024}">
    <c:set var="kiloSize" value="${byteSize / 1024}" />

    <c:if test="${kiloSize lt 1024}" >
        <c:set var="fileSize" value="${kiloSize}" />
        <c:set var="fileSizeUnits" value="KB" />
    </c:if>

    <c:if test="${kiloSize ge 1024}">
        <c:set var="megaSize" value="${kiloSize / 1024}" />

        <c:if test="${megaSize lt 1024}" >
            <c:set var="fileSize" value="${megaSize}" />
            <c:set var="fileSizeUnits" value="MB" />
        </c:if>

        <c:if test="${megaSize ge 1024}">
            <c:set var="gigaSize" value="${megaSize / 1024}" />

            <c:set var="fileSize" value="${gigaSize}" />
            <c:set var="fileSizeUnits" value="GB" />
        </c:if>
    </c:if>
</c:if>

<c:set var="dot" value="." />
<c:if test="${fn:contains(fileSize, dot)}" >
    <c:set var="fileSize" value="${fn:substringBefore(fileSize, dot)}" />
</c:if>

<jsp:doBody/>
