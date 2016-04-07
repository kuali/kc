<%--
 Copyright 2005-2014 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
Testing

<c:if test="${!empty UserSession}">
    <c:if test="${UserSession.backdoorInUse == 'true'}">
        <div class="backdoor">
            Backdoor Id <b>${UserSession.principalName}</b> is in use
        </div>
    </c:if>
    <c:if test="${UserSession.getObject['AUTH_SERVICE_FILTER_AUTHED_USER'] != null 
    	&& UserSession.getObject['AUTH_SERVICE_FILTER_AUTHED_USER'].actualUser != null}">
    	CoreAuth User <b><c:out value="${UserSession.getObject['AUTH_SERVICE_FILTER_AUTHED_USER'].actualUser}"/></b> is proxied
    </c:if>
</c:if>
