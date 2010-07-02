<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="checkListItemAttributes" value="${DataDictionary.ExemptStudiesCheckListItem.attributes}" />
          	<tr>
           		<td class="tab-subhead" colspan="2" scope="row">
           		     
            		<kra:innerTab tabTitle="Check List Items" parentTab="" defaultOpen="false" tabErrorKey="" overrideDivClass="inner-subhead" >
    
                        <div class="innerTab-container" align="left">
                                
                            <table class="tab" cellpadding="0" cellspacing="0" summary="">
                                <tbody>
                                    <c:forEach items="${KualiForm.actionHelper.selectedSubmission.exemptStudiesCheckList}" var="checkList" varStatus="status">
                                        <tr>
                                            <th class="infoline" align="center" >
                                                   ${status.index + 1}
                                            </th>

                                            <td style="border-left: 1 none; border-right: 0 none; padding: 5px ">
                                                <kra:truncateComment textAreaFieldName="actionHelper.selectedSubmission.exemptStudiesCheckList[${status.index}].exemptStudiesCheckListItem.description" action="protocolProtocolActions" textAreaLabel="CheckList Item" textValue="${checkList.exemptStudiesCheckListItem.description}" displaySize="200"/>
    <%-- TODO : this hidden field should be created by expandedtextarea.tag
         if this hidden is not created then the value can not be retrieved for popup display --%>
                                                <input type="hidden" name="actionHelper.selectedSubmission.exemptStudiesCheckList[${status.index}].exemptStudiesCheckListItem.description" value="${checkList.exemptStudiesCheckListItem.description}" id="actionHelper.selectedSubmission.exemptStudiesCheckList[${status.index}].exemptStudiesCheckListItem.description">
                                            </td>
                                        </tr>
                                    </c:forEach> 
                 <%-- this two list should be mutual exclusive --%>                  
                                    <c:forEach items="${KualiForm.actionHelper.selectedSubmission.expeditedReviewCheckList}" var="checkList" varStatus="status">
                                        <tr>
                                            <th class="infoline" align="center" >
                                                   ${status.index + 1}
                                            </th>

                                            <td style="border-left: 1 none; border-right: 0 none; padding: 5px ">
                                                <kra:truncateComment textAreaFieldName="actionHelper.selectedSubmission.expeditedReviewCheckList[${status.index}].expeditedReviewCheckListItem.description" action="protocolProtocolActions" textAreaLabel="CheckList Item" textValue="${checkList.expeditedReviewCheckListItem.description}" displaySize="200"/>
    <%-- TODO : this hidden field should be created by expandedtextarea.tag
         if this hidden is not created then the value can not be retrieved for popup display --%>
                                                <input type="hidden" name="actionHelper.selectedSubmission.expeditedReviewCheckList[${status.index}].expeditedReviewCheckListItem.description" value="${checkList.expeditedReviewCheckListItem.description}" id="actionHelper.selectedSubmission.expeditedReviewCheckList[${status.index}].expeditedReviewCheckListItem.description">
                                            </td>
                                        </tr>
                                    </c:forEach>    
                                           
                                </tbody>
                            </table>
                        </div>         
                    </kra:innerTab>  
	            </td>
            </tr>    