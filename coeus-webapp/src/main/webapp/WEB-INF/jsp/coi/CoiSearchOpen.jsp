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

<c:set var="readOnly" value="true"  scope="request" />


<kul:page lookup="false" 
          docTitle="Open And In Progress Reviews" 
          transactionalDocument="false"
          renderMultipart="false" 
          htmlFormAction="coiSearchOpen">

 <div id="workarea">

	<kul:tab tabTitle="Open Reviews"
	         defaultOpen="false"
	         alwaysOpen="false"
	         transparentBackground="true" 
	         useCurrentTabIndexAsKey="true">
	<div class="tab-container" align="center">	
		<c:choose><c:when test="${fn:length(KualiForm.customAdminSearchHelper.allOpenReviews) > 0}">
			<kra-coi:coiOpenReviewSearchResult name="Open Reviews With No Financial Entities" 
				reviews="${KualiForm.customAdminSearchHelper.allOpenReviewsWithoutFinEnts}" showApprove="true"/>         
			<kra-coi:coiOpenReviewSearchResult name="Open Reviews With Financial Entities" 
				reviews="${KualiForm.customAdminSearchHelper.allOpenReviewsWithFinEnts}" showApprove="false"/>
		</c:when><c:otherwise>No reviews found.</c:otherwise></c:choose>
	</div>

	</kul:tab>
	<kul:tab tabTitle="Pending Reviews"
	         defaultOpen="false"
	         alwaysOpen="false"
	         transparentBackground="false" 
	         useCurrentTabIndexAsKey="true">
	<div class="tab-container" align="center">	 
		<c:choose><c:when test="${fn:length(KualiForm.customAdminSearchHelper.pendingReviews) > 0}"> 
			<kra-coi:coiOpenReviewSearchResult name="Pending Reviews With No Financial Entities" 
				reviews="${KualiForm.customAdminSearchHelper.pendingReviewsWithoutFinEnts}" showApprove="true"/>
			<kra-coi:coiOpenReviewSearchResult name="Pending Reviews With Financial Entities" 
				reviews="${KualiForm.customAdminSearchHelper.pendingReviewsWithFinEnts}" showApprove="false"/>
		</c:when><c:otherwise>No reviews found.</c:otherwise></c:choose>       
	</div>

	</kul:tab>	
	<kul:tab tabTitle="Work in Progress Reviews"
	         defaultOpen="false"
	         alwaysOpen="false"
	         transparentBackground="false" 
	         useCurrentTabIndexAsKey="true">
	<div class="tab-container" align="center">
		<c:choose><c:when test="${fn:length(KualiForm.customAdminSearchHelper.inProgressReviews) > 0}">	
			<kra-coi:coiOpenReviewSearchResult name="Work In Progress Reviews With No Financial Entities" 
				reviews="${KualiForm.customAdminSearchHelper.inProgressReviewsWithoutFinEnts}" showApprove="true"/>
			<kra-coi:coiOpenReviewSearchResult name="Work In Progress Reviews With Financial Entities" 
				reviews="${KualiForm.customAdminSearchHelper.inProgressReviewsWithFinEnts}" showApprove="false"/>
		</c:when><c:otherwise>No reviews found.</c:otherwise></c:choose>
	</div>

	</kul:tab>
	<kul:panelFooter />
	
</div>

</kul:page>
