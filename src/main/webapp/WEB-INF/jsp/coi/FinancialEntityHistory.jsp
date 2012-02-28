<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/kuali-stylesheet.css" type="text/css" />

<c:set var="financialEntityVersions" value= "${KualiForm.financialEntityHelper.versions}" />
<c:set var="entityName" value="${KualiForm.financialEntityHelper.versions[0].entityName}"/>
<div class="innerTab-container" align="left">
    <table id="activeEntities-table" cellpadding=0 cellspacing="0"  style="border-collapse:collapse;" class="elementhead_table">
        <tr>
            <th class="elementhead_left">${entityName}</th>
            <th class="elementhead_right">                    
                <span class="subhead-right"> 
               	        <kul:help businessObjectClassName="org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure" altText="help"/>
               	</span>
            </th>
        </tr>
    </table>
    <table class="content_table">
        <tr>
            <td class="content_grey" style="text-align: left;">Ver</td>
            <td class="content_grey" style="text-align: left;">Updated</td>
            <td class="content_grey" style="text-align: left;">By</td>
            <td class="content_grey" style="text-align: left;">Status</td>
            <td class="content_grey" style="text-align: left;">Explanation</td>
        </tr>
        <c:forEach var="finEntityVersion" items="${financialEntityVersions}">
            <tr>
                <td class="content_white" width=10>
                    ${finEntityVersion.sequenceNumber}
                </td>
                <td class="content_white" width=100>
                    <fmt:formatDate value="${finEntityVersion.updateTimestamp}" pattern="MM/dd/yyyy hh:mm a" />
                </td>
                <td class="content_white" width=20>
                    ${finEntityVersion.updateUser}
                </td>
                <td class="content_white" width=100>
                    <c:choose>
                    	<c:when test="${finEntityVersion.statusActive}">
                            Active
                        </c:when>
                        <c:otherwise>
                            Inactive
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="content_white" width=100>
                    ${finEntityVersion.statusDescription}
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
                                                