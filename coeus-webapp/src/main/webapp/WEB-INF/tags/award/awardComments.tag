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
<%-- member of AwardNotesAndAttachments.jsp --%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<kul:tabTop tabTitle="Comments" defaultOpen="false" tabErrorKey="document.awardList[0].awardComment[*">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Comments</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.award.home.AwardComment" altText="help"/></span>
        </h3>
         <c:forEach var="commentType" items="${KualiForm.awardCommentBean.awardCommentScreenDisplayTypes}" varStatus="commentTypeIndex">        	        	
			<kra-a:awardCommentsTypes index="${commentTypeIndex.index}" commentTypeDescription="${commentType.description}" commentTypeCode="${commentType.commentTypeCode}" awardId="${KualiForm.document.award.awardId}"/>
		</c:forEach>
		
		<br/>
		
		 <c:if test="${(!readOnly)}">
			<kra-a:awardSyncButton  scopeNames="COMMENTS_TAB" tabKey="${tabKey}"/>		
		 </c:if>
 	</div>
</kul:tabTop>