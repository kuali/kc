<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="committeeBatchCorrespondence" type="org.kuali.kra.committee.bo.CommitteeBatchCorrespondence" description="The committee batch correspondence." required="true" %>
<%@ attribute name="committeeBatchCorrespondenceProperty" description="The property of committee batch correspondence." required="true" %>

<c:set var="committeeBatchCorrespondenceDetailAttributes" value="${DataDictionary.CommitteeBatchCorrespondenceDetail.attributes}" />

<c:set var="batchRunTitle" value="${committeeBatchCorrespondence.formattedTimeWindowStart} through ${committeeBatchCorrespondence.formattedTimeWindowEnd}" />

<kul:innerTab tabTitle="${batchRunTitle}" 
              parentTab="$Batch Correspondence" 
              defaultOpen="false"
              useCurrentTabIndexAsKey="true" 
              tabErrorKey="">
<%--    <div align="left" style="padding-left: 56px;"> --%>
   <kra-irb-action:padLeft>
        <table class=tab cellpadding=0 cellspacing="0"> 
            <tr>
                <th>
                    <div align="right">
                        Run Date:
                    </div>
                </th>
                <td>
                    <div align="left">
                        ${committeeBatchCorrespondence.formattedBatchRunDate}
                    </div>
                </td>
                <th>
                    <div align="right">
                        Run Time:
                    </div>
                </th>
                <td> 
                    <div align="left">
                        ${committeeBatchCorrespondence.formattedBatchRunTime} 
                    </div>
                </td>
            </tr>
            <tr>
                <th>
                    <div align="right">
                        User ID:
                    </div>
                </th>
                <td>
                    <div align="left">
                        ${committeeBatchCorrespondence.updateUser} 
                    </div>
                </td>
                <th>
                    <div align="right">
                        Batch ID:
                    </div>
                </th>
                <td>
                    <div align="left">
                        ${committeeBatchCorrespondence.committeeBatchCorrespondenceId} 
                    </div>
                </td>
            </tr>
        </table>
        
        <table class=tab cellpadding=0 cellspacing="0"> 
            <tr>
                <th width ="25">
                    <div align="center">
                    &nbsp;
                    </div>
                </th>
                <th width="75">
                    <div align="center">
                        Protocol Number
                    </div>
                </th>
                <th>
                    <div align="center">
                        Title
                    </div>
                </th>
                <th width="75">
                    <div align="center">
                        Approval Date
                    </div>
                </th>
                <th width="75">
                    <div align="center">
                        Expiration Date
                    </div>
                </th>
                <th width="40%">
                    <div align="center">
                        Description
                    </div>
                </th>
                <th width="50">
                    <div align="center">
                        Actions
                    </div>
                </th>
            </tr>
            
        <c:forEach items="${committeeBatchCorrespondence.committeeBatchCorrespondenceDetails}" var="batchCorrespondenceDetails" varStatus="status">
            <tr>
                <th>
                    <div align="right">
                        ${status.index + 1}
                    </div>
                </th>
                <td>
                    <div align="center">
                        ${batchCorrespondenceDetails.protocolCorrespondence.protocol.protocolNumber} 
                    </div>
                </td>
                <td>
                    <div align="left">
                        ${batchCorrespondenceDetails.protocolCorrespondence.protocol.title} 
                    </div>
                </td>
                <td>
                    <div align="center">
                        ${batchCorrespondenceDetails.protocolCorrespondence.protocol.approvalDate} 
                    </div>
                </td>
                <td>
                    <div align="center">
                        ${batchCorrespondenceDetails.protocolCorrespondence.protocol.expirationDate} 
                    </div>
                </td>
                <td>
                    <div align="left">
                        ${batchCorrespondenceDetails.protocolCorrespondence.protocolCorrespondenceType.description} 
                    </div>
                </td>
                <td>
                    <div align="center">
                        <kul:htmlControlAttribute property="${committeeBatchCorrespondenceProperty}.committeeBatchCorrespondenceDetails[${status.index}].selected" 
                                                  attributeEntry="${committeeBatchCorrespondenceDetailAttributes.selected}" 
                                                  readOnly="false" />
                    </div>                         
                </td>
            </tr>
        </c:forEach>

        </table>
   </kra-irb-action:padLeft>
    <%--</div> --%>
</kul:innerTab>