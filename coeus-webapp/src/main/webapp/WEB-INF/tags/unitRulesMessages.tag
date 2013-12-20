
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