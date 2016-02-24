<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<tr><td colspan="4" class="subhead">Unit Business Rules Errors</td></tr>
<c:choose>
    <c:when test="${KualiForm.unitRulesErrorsExist}">
    <div class="error">
        <c:forEach items="${KualiForm.unitRulesErrors}" var="message">
            <tr>
                <td>&nbsp;</td>
                <td colspan="2"><c:out value="${message}" /></td>
            </tr>
        </c:forEach>
    </div>
    </c:when>
    <c:otherwise>
        <tr>
            <td colspan="4" height="70" align=left valign=middle class="datacell">
                <div align="center">No Unit Business Rules Errors present.</div>
            </td>
        </tr>
    </c:otherwise>
</c:choose>

<tr><td colspan="4" class="subhead">Unit Business Rules Warnings</td></tr>
<c:choose>
    <c:when test="${KualiForm.unitRulesWarningsExist}">
    <div class="error">
        <c:forEach items="${KualiForm.unitRulesWarnings}" var="message">
            <tr>
                <td>&nbsp;</td>
                <td colspan="2"><c:out value="${message}" /></td>
            </tr>
        </c:forEach>
    </div>
    </c:when>
    <c:otherwise>
        <tr>
            <td colspan="4" height="70" align=left valign=middle class="datacell">
                <div align="center">No Unit Business Rules Warnings present.</div>
            </td>
        </tr>
    </c:otherwise>
</c:choose>
