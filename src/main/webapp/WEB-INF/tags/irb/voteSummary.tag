<%--
 Copyright 2005-2010 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="attributes" value="${DataDictionary.CommitteeDecision.attributes}" />
          	<tr>
           		<td class="tab-subhead" colspan="2" scope="row">
           		     
            		<kul:innerTab tabTitle="Vote Summary" parentTab="" defaultOpen="false" tabErrorKey="" overrideDivClass="inner-subhead" >
    
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
                                            ${fn:length(KualiForm.actionHelper.abstainees)}
                                        </td>
                                        <th> 
                                            <div align="right">
                                                <kul:htmlAttributeLabel attributeEntry="${attributes.recusedCount}" />
                                            </div>
                                        </th>
                                        <td>
                                            ${fn:length(KualiForm.actionHelper.recusers)}
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
                                                    <th colspan="9" align="right">
                                                       Abstainers:
                                                    </th>
                                                </c:when>
                                                <c:otherwise>
                                                    <th colspan="9" align="right">
                                                      &nbsp;
                                                    </th>
                                                </c:otherwise>
                                            </c:choose>
                                            <td>
                                                <div align="left">
                                                 ${abstainee.fullName}
                                                </div> 
                                            </td>
                                        </tr> 
                                    </c:forEach>
                                    <c:forEach items="${KualiForm.actionHelper.recusers}" var="recuser" varStatus="status">
                                        <tr>
                                            <c:choose>
                                                <c:when test="${status.index == 0}">
                                                    <th colspan="9" align="right">
                                                       Recused:
                                                    </th>
                                                </c:when>
                                                <c:otherwise>
                                                    <th colspan="9" align="right">
                                                      &nbsp;
                                                    </th>
                                                </c:otherwise>
                                            </c:choose>
                                            <td>
                                                <div align="left">
                                                 ${recuser.fullName}
                                                </div> 
                                            </td>
                                        </tr> 
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>         
                    </kul:innerTab>  
	            </td>
            </tr>    