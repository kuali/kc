<%@ include file="/WEB-INF/jsp/committee/committeeMember.jsp"%>

<c:choose>
    <c:when test="${empty KualiForm.document.committee.committeeMemberships[memberIndex].personName}">
        <c:set var="parentTabName" value="" />
    </c:when>
    <c:otherwise>
        <bean:define id="parentTabName" name="KualiForm" property="${committeeMembership}.personName"/>
    </c:otherwise>
</c:choose>

<table cellpadding=0 cellspacing=0 summary="">
    <tr>
        <td>
            <kul:innerTab tabTitle="Person Details" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="">
                <div class="innerTab-container" align="left">
                    <table class=tab cellpadding=0 cellspacing="0" summary=""> 
                        <tr>
                            <th align="left" nowrap="nowrap">
                                <div align="right">
                                  <kul:htmlAttributeLabel attributeEntry="${committeeMembershipAttributes.membershipTypeCode}" />
                                </div>
                            </th>
                            <td align="left">
                                <kul:htmlControlAttribute property="${committeeMembership}.membershipTypeCode" attributeEntry="${committeeMembershipAttributes.membershipTypeCode}" />
                            </td>
                        </tr>
                    </table>
                </div>
            </kul:innerTab>
        </td>
    </tr>
</table>
