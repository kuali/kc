<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="tabTitle" required="true" %>
<%@ attribute name="canPerformAction" required="true" %>
<%@ attribute name="reason" required="true" %>

<kra:permission value="${canPerformAction}">

<kul:innerTab tabTitle="${tabTitle}" parentTab="" defaultOpen="false">
    <div class="innerTab-container" align="left">
        <table class="tab" cellpadding="0" cellspacing="0" summary=""> 
            <tbody>
                <tr>
                    <th width="15%"> 
                        <div align="right">
                            Reason:
                        </div>
                    </th>
                    <td>
                        ${reason}
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

</kul:innerTab>

</kra:permission>
