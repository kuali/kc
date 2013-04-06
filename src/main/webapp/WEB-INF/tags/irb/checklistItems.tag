<%--
 Copyright 2005-2013 The Kuali Foundation
 
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