<%--
 Copyright 2005-2008 The Kuali Foundation
 
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
<%@ attribute name="boClassName" required="true" %>
<%@ attribute name="keyValues" required="true" %>
<%@ attribute name="render" required="true"
              description="boolean indicating whether the inquiry link should be rendered.
              The body is rendered unconditionally." %>

<c:if test="${render}">
    <a href="${ConfigProperties.application.url}/kr/inquiry.do?methodToCall=start&businessObjectClassName=${boClassName}&${keyValues}"  target="_blank">
</c:if>
        <jsp:doBody/>
<c:if test="${render}">
    </a>
</c:if>
