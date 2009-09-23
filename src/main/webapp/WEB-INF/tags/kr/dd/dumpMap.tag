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

<c:if test="${!empty prefix}">
    <c:set var="prefix" value="${prefix}." />
</c:if>

<c:if test="${empty noTable}">
    <table border=1 cellpadding=4>
        <c:if test="${!empty title}">
            <tr>
                <th colspan=2 align=left>${title}</th>
            </tr>
        </c:if>
    
        <tr>
            <th align=left>key</th>
            <th align=left>value</th>
        </tr>
</c:if>
    
        <c:forEach items='${map}' var='mapEntry' >
            <c:if test="${fn:startsWith(mapEntry.value,'{')}">
                <dd:dumpMap noTable="true" prefix="${prefix}${mapEntry.key}" map="${mapEntry.value}" />
            </c:if>
    
            <c:if test="${!fn:startsWith(mapEntry.value,'{')}">
                <tr>
                    <td valign=top>${prefix}${mapEntry.key}</td>
    
                    <td>
                        <table cellspacing=0 cellpadding=0>
                            <c:set var="subItems" value="${fn:split(mapEntry.value, ',')}" />
        
                            <c:forEach items="${subItems}" var="subItem" >
                                <tr><td>${subItem}</td></tr>
                            </c:forEach>
                        </table>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
<c:if test="${empty noTable}">
    </table>
</c:if>
