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
