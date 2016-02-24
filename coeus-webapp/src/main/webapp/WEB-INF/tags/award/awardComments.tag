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
