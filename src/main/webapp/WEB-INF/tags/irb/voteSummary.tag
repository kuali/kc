<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="attributes" value="${DataDictionary.CommitteeDecision.attributes}" />
          	<tr>
           		<td class="tab-subhead" colspan="2" scope="row">
           		     
            		<kra:innerTab tabTitle="Vote Summary" parentTab="" defaultOpen="false" tabErrorKey="" overrideDivClass="inner-subhead" >
    
                        <div class="innerTab-container" align="left">
                                
                            <table class="tab" cellpadding="0" cellspacing="0" summary="">
                                <tbody>
                                    <tr>
                                        <th> 
                                            <div align="right">
                                                <kul:htmlAttributeLabel attributeEntry="${attributes.noCount}" />
                                            </div>
                                        </th>
                                        <td>
                                            <kul:htmlControlAttribute property="actionHelper.selectedSubmission.noVoteCount" attributeEntry="${attributes.noCount}" readOnly="true"/>
                                        </td>
                    
                                        <th> 
                                            <div align="right">
                                                <kul:htmlAttributeLabel attributeEntry="${attributes.yesCount}" />
                                            </div>
                                        </th>
                                        <td>
                                            <kul:htmlControlAttribute property="actionHelper.selectedSubmission.yesVoteCount" attributeEntry="${attributes.yesCount}" readOnly="true" />
                                        </td>
                    
                                        <th> 
                                            <div align="right">
                                                <kul:htmlAttributeLabel attributeEntry="${attributes.abstainCount}" />
                                            </div>
                                        </th>
                                        <td>
                                            <kul:htmlControlAttribute property="actionHelper.selectedSubmission.abstainerCount" attributeEntry="${attributes.abstainCount}" readOnly="true" />
                                        </td>
                                        <th> 
                                            <div align="right">
                                                <nobr>
                                                    <kul:htmlAttributeLabel attributeEntry="${attributes.votingComments}" />
                                                </nobr>
                                            </div>
                                        </th>
                
                                        <td width="70%">
                                            <nobr>
                                                <kul:htmlControlAttribute property="actionHelper.selectedSubmission.votingComments" attributeEntry="${attributes.votingComments}" readOnly="true" />
                                            </nobr>
                                        </td>
                                    </tr>
                                    <c:forEach items="${KualiForm.actionHelper.abstainees}" var="abstainee" varStatus="status">
                                        <tr>
                                            <c:choose>
                                                <c:when test="${status.index == 0}">
                                                    <th colspan="7" align="right">
                                                       Abstainers:
                                                    </th>
                                                </c:when>
                                                <c:otherwise>
                                                    <th colspan="7" align="right">
                                                      &nbsp;
                                                    </th>
                                                </c:otherwise>
                                            </c:choose>
                                            <td>
                                                 ${abstainee.fullName}
                                            </td>
                                        </tr> 
                                    </c:forEach>
                                    <c:forEach items="${KualiForm.actionHelper.recusers}" var="recuser" varStatus="status">
                                        <tr>
                                            <c:choose>
                                                <c:when test="${status.index == 0}">
                                                    <th colspan="7" align="right">
                                                       Recused:
                                                    </th>
                                                </c:when>
                                                <c:otherwise>
                                                    <th colspan="7" align="right">
                                                      &nbsp;
                                                    </th>
                                                </c:otherwise>
                                            </c:choose>
                                            <td>
                                                 ${recuser.fullName}
                                            </td>
                                        </tr> 
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>         
                    </kra:innerTab>  
	            </td>
            </tr>    