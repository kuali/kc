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
<c:set var="checkListItemAttributes" value="${DataDictionary.ExemptStudiesCheckListItem.attributes}" />
          	<tr>
           		<td class="tab-subhead" colspan="2" scope="row">
           		     
            		<kul:innerTab tabTitle="Check List Items" parentTab="" defaultOpen="false" tabErrorKey="" overrideDivClass="inner-subhead" >
    
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
                    </kul:innerTab>  
	            </td>
            </tr>    
