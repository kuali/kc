<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
