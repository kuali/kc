<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="minutesAttributes" value="${DataDictionary.CommitteeScheduleMinute.attributes}" />
          	<tr>
           		<td class="tab-subhead" colspan="2" scope="row">
           		     
            		<kra:innerTab tabTitle="Review Comments" parentTab="" defaultOpen="false" tabErrorKey="" overrideDivClass="inner-subhead" >
    
                        <div class="innerTab-container" align="left">
                                
                            <table class="tab" cellpadding="0" cellspacing="0" summary="">
                                <tbody>
                                    <tr>
                                        <th><div align="left">&nbsp;</div></th> 
                                        <kul:htmlAttributeHeaderCell literalLabel="Standard Comment" scope="col" />
                                        <kul:htmlAttributeHeaderCell literalLabel="Comment" scope="col" />
                                        <kul:htmlAttributeHeaderCell literalLabel="Private" scope="col" />
                                    </tr>
                                    <c:forEach items="${KualiForm.actionHelper.reviewComments}" var="comment" varStatus="status">
                                        <tr>
                                            <th class="infoline" align="right" >
                                                   ${status.index + 1}
                                            </th>

                                            <c:choose>
                                                <c:when test="${empty comment.protocolContingencyCode}">
                                                    <td style="text-align:center;">
                                                   n/a
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td style="text-align:center;">
                                                   ${comment.protocolContingencyCode}
                                                    </td>
                                                </c:otherwise>
                                            </c:choose>
                                     
                                            <td style="border-left: 1 none; border-right: 0 none; padding: 5px ">
                                                <kra:truncateComment textAreaFieldName="actionHelper.reviewComments[${status.index}].minuteEntry" action="protocolProtocolActions" textAreaLabel="Review Comment" textValue="${comment.minuteEntry}" displaySize="200"/>
        <%-- TODO : this hidden field should be created by expandedtextarea.tag
         if this hidden is not created then the value can not be retrieved for popup display --%>
                                                <input type="hidden" name="actionHelper.reviewComments[${status.index}].minuteEntry" value="${comment.minuteEntry}" id="actionHelper.reviewComments[${status.index}].minuteEntry">
                                            </td>                
                                                
                                            <td style="text-align:center; vertical-align:middle">
                                                <kul:htmlControlAttribute property="actionHelper.reviewComments[${status.index}].privateCommentFlag" 
                                                      attributeEntry="${minutesAttributes.privateCommentFlag}"
                                                      readOnly="true" />
                                            </td>
                                        </tr>
                                    </c:forEach>    
                                </tbody>
                            </table>
                        </div>         
                    </kra:innerTab>  
	            </td>
             </tr>    