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

<%@ attribute name="readOnly" required="false" %>

<c:set var="ipReviewActivityAttributes" value="${DataDictionary.IntellectualPropertyReviewActivity.attributes}" />

<kul:tab tabTitle="Activities" defaultOpen="false" tabErrorKey="">

<div class="tab-container" align="center">

<h3>
    <span class="subhead-left">Activities</span>
    <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.institutionalproposal.ipreview.IntellectualPropertyReviewActivity" altText="help"/></span>
</h3>

<table summary="" align="center" cellpadding="0" cellspacing="0">
    <tbody>
        <tr>
            <th>&nbsp;</th>
            <th><kul:htmlAttributeLabel attributeEntry="${ipReviewActivityAttributes.ipReviewActivityTypeCode}" /></th>
            <th><kul:htmlAttributeLabel attributeEntry="${ipReviewActivityAttributes.activityDate}" /></th>
            <th><kul:htmlAttributeLabel attributeEntry="${ipReviewActivityAttributes.comments}" /></th>
        </tr>
        <c:forEach var="activity" items="${KualiForm.document.institutionalProposal.proposalIpReviewJoin.intellectualPropertyReview.ipReviewActivities}" varStatus="status"> 
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
