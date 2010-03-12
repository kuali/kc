<%--
 Copyright 2006-2007 The Kuali Foundation.
 
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
<%@ attribute name="category" required="true" description="The category of the cluster of audit errors to display in this tag." %>

<c:set var="found" value="${false}"/>
    <tr><td colspan="4" class="subhead">${category}</td></tr>

<c:forEach items="${AuditErrors}" var="cluster">
    <c:if test="${cluster.value.category == category && cluster.value.size != 0}">
        <c:if test="${!found}"><c:set var="found" value="${true}"/></c:if>
        <kul:auditRow tabTitle="${cluster.value.label}" defaultOpen="false" totalErrors="${cluster.value.size}" category="${cluster.value.category}">
            <kul:auditErrors cluster="${cluster.key}" isLink="true"/>
        </kul:auditRow>
    </c:if>
</c:forEach>
<c:if test="${!found}">
    <tr>
        <td colspan="4" height="70" align=left valign=middle class="datacell">
            <div align="center">No ${category} present.</div>
        </td>
    </tr>
</c:if>