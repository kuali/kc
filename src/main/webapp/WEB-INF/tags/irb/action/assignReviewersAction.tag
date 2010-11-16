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

<c:set var="reviewerAttributes" value="${DataDictionary.ProtocolReviewerBean.attributes}" />
<c:set var="action" value="protocolProtocolActions" />

<kra:permission value="${KualiForm.actionHelper.canAssignReviewers}">

<kul:innerTab tabTitle="Assign Reviewers" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.protocolAssignReviewersBean*">
    <div class="innerTab-container" align="left">
        <table class="tab" cellpadding="0" cellspacing="0" summary=""> 
            <tbody>
                
                <tr>
                    <th width="15%">
                        <div align="right">
                            Reviewers:
                        </div>
                    </th>
                            
                    <td>
                        <div width="100%">
                            <table style="border: 0 none yellow">
                                <tr valign="top">
                                    <td width="50%" style="border: 0 none">
                                        <table id="reviewersTableLeft" style="border: 0 none" cellpadding="0" cellspacing="0" summary="">
                                            <c:forEach var="reviewer" items="${KualiForm.actionHelper.protocolAssignReviewersBean.leftReviewers}" varStatus="status">
                                                <tr>
                                                    <td style="border: 0 none">
                                                        ${reviewer.fullName}
                                                    </td>
                                                    <td style="border: 0 none">
                                                        <kul:htmlControlAttribute property="actionHelper.protocolAssignReviewersBean.reviewer[${status.index}].reviewerTypeCode"
                                                                                  attributeEntry="${reviewerAttributes.reviewerTypeCode}"/>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </td>
                                    <td style="border: 0 none">
                                        <table id="reviewersTableRight" style="border: 0 none" cellpadding="0" cellspacing="0" summary="">
                                            <c:set var="numLeftReviewers" value="${fn:length(KualiForm.actionHelper.protocolAssignReviewersBean.leftReviewers)}" />
                                            <c:forEach var="reviewer" items="${KualiForm.actionHelper.protocolAssignReviewersBean.rightReviewers}" varStatus="status">
                                                <tr>
                                                    <td style="border: 0 none">
                                                        ${reviewer.fullName}
                                                    </td>
                                                    <td style="border: 0 none">
                                                        <kul:htmlControlAttribute property="actionHelper.protocolAssignReviewersBean.reviewer[${status.index + numLeftReviewers}].reviewerTypeCode"
                                                                                  attributeEntry="${reviewerAttributes.reviewerTypeCode}"/>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </td>
                </tr>
                
                <tr>
                    <td align="center" colspan="2">
                        <div align="center">
                            <html:image property="methodToCall.assignReviewers.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    
</kul:innerTab>

</kra:permission>
