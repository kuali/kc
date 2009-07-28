<%--
 Copyright 2006-2008 The Kuali Foundation
 
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

<%@ attribute name="readOnly" required="false" %>

<c:set var="ipReviewActivityAttributes" value="${DataDictionary.InstitutionalProposalIpReviewActivity.attributes}" />

<kul:tab tabTitle="Activities" defaultOpen="false" tabErrorKey="">

<div class="tab-container" align="center">

<h3>
    <span class="subhead-left">Activities</span>
    <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.award.bo.Award" altText="help"/></span>
</h3>

<table summary="" align="center" cellpadding="0" cellspacing="0">
    <tbody>
        <tr>
            <th>&nbsp;</th>
            <th><kul:htmlAttributeLabel attributeEntry="${ipReviewActivityAttributes.ipReviewActivityTypeCode}" /></th>
            <th><kul:htmlAttributeLabel attributeEntry="${ipReviewActivityAttributes.activityDate}" /></th>
            <th><kul:htmlAttributeLabel attributeEntry="${ipReviewActivityAttributes.comments}" /></th>
        </tr>
        <c:forEach var="activity" items="${KualiForm.document.institutionalProposal.intellectualPropertyReview.ipReviewActivities}" varStatus="status"> 
        <tr>
            <th><c:out value="${activity.activityNumber}" /></td>
            <td><c:out value="${activity.ipReviewActivityType.description}" /></td>
            <td><c:out value="${activity.activityDate}" /></td>
            <td><c:out value="${activity.comments}" /></td>
        </tr>
        </c:forEach>
    </tbody>
</table>

</div>
	
</kul:tab>
