<%@ tag import="org.kuali.coeus.sys.framework.service.KcServiceLocator" %>
<%@ tag import="org.kuali.coeus.common.framework.motd.MessageOfTheDayService" %>
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

<c:set var="motd" value="<%= (KcServiceLocator.getService(MessageOfTheDayService.class)).getMessagesOfTheDay() %>" scope="page"/>
<c:if test="${!empty pageScope.motd}">
	<channel:portalChannelTop channelTitle="Messages Of The Day" />
	<c:set var = "printed" value = "false"/>
	<c:forEach items = "${pageScope.motd}" var = "msg">
	    	<c:set var= "printed" value = "true"/>
			<div class="body">
        		<strong><c:out value="${msg.message}"  /></strong>
        	</div>
   </c:forEach>
	<c:if test = "${!printed}">
	    <div class = "body">No messages today.</div>
	</c:if>
    <channel:portalChannelBottom />
</c:if>
