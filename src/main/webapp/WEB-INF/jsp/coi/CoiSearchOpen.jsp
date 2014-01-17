<%--
 Copyright 2005-2014 The Kuali Foundation

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