<%-- Making a local copy to address the Role requests bug.Use the Rice version when this is fixed. --%>

<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<c:if test="${KualiForm.superUserDocument && KualiForm.stateAllowsSuperUserAction && KualiForm.superUserAuthorized}">
<c:set var="tabTitle"><bean:message key="superuser.tab.label" /></c:set>
<c:set var="actionLabel"><bean:message key="superuser.action.column.label" /></c:set>
<c:set var="requestedLabel"><bean:message key="superuser.requested.column.label" /></c:set>
<c:set var="timeLabel"><bean:message key="superuser.time.column.label" /></c:set>
<c:set var="annotationLabel"><bean:message key="superuser.annotation.column.label" /></c:set>

<kul:tab tabTitle="${tabTitle}"
         defaultOpen="false"
         tabErrorKey="superuser.errors"
         transparentBackground="${transparentBackground}">
    <div class="tab-container" align=center id="G4">
        <h3>${tabTitle}</h3>
        <table cellpadding="0" cellspacing="0" class="datatable" summary="view/add notes">
            <tbody>
                <tr>
                    <th style="width: 5%; text-align: center;"><input type="checkbox" onclick="jQuery('input.superUserAction').prop('checked', jQuery(this).prop('checked'))" /></th>
                    <th style="width: 15%;">${actionLabel}</th>
                    <th style="width: 15%;">${requestedLabel}</th>
                    <th style="width: 15%;">${timeLabel}</th>
                    <th style="width: 50%;">${annotationLabel}</th>
                </tr>
                <c:forEach var="actionRequest" items="${KualiForm.actionRequests}" varStatus="status">
                <c:if test="${!actionRequest.roleRequest}">
                <tr>
                    <td class="datacell" style="text-align: center;"><html:multibox property="selectedActionRequests" value="${actionRequest.id}" styleClass="superUserAction" /></td>
                    <td class="datacell">${actionRequest.actionRequested}</td>
                    <td class="datacell">
                        <c:choose>
                            <c:when test="${actionRequest.userRequest}">
                                <c:out value="${kfunc:getPrincipalDisplayName(actionRequest.principalId)}" />
                            </c:when>
                            <c:otherwise>
                                <c:out value="${kfunc:getKimGroupDisplayName(actionRequest.groupId)}"/>
                             </c:otherwise>
                         </c:choose>
                    </td>
                    <td class="datacell"><joda:format value="${actionRequest.dateCreated}" pattern="MM/dd/yyyy hh:mm a"/>&nbsp;</td>
                    <td class="datacell">${actionRequest.annotation}</td>
                </tr>
                </c:if>
                </c:forEach>
            </tbody>
        </table>
        <div style="vertical-align: top;">
            <label for="superUserAnnotation" style="vertical-align: top;">Annotation<span style="color: red; vertical-align: top;">*</span></label>
            <html:textarea property="superUserAnnotation" rows="5" cols="100" styleId="superUserAnnotation" />
        </div>
        <div>
            <html-el:image property="methodToCall.takeSuperUserActions" src="${ConfigProperties.kew.externalizable.images.url}buttonsmall_takeselected.gif" style="border-style:none;" align="absmiddle" />
            <html-el:image property="methodToCall.superUserDisapprove" src="${ConfigProperties.kew.externalizable.images.url}buttonsmall_disapprovedoc.gif" style="border-style:none;" align="absmiddle" />
        </div>
    </div>
</kul:tab>
</c:if>
