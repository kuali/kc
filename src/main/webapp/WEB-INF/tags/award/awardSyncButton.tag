<%--
 Copyright 2006-2009 The Kuali Foundation
 
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
<%@ attribute name="propertyName" description="name of the property, empty if you do not need it." required="false" %>
<%@ attribute name="scopeNames" description="Named scope the sync should apply to. leave empty if you want to apply the global scope." required="false" %>
<%@ attribute name="tabKey" description="tab key for the button" required="false" %>


<html:image property="methodToCall.syncAwardTemplate.syncPropertyName${propertyName}:${scopeNames}.anchor${tabKey}"
		src='${ConfigProperties.kra.externalizable.images.url}tinybutton-synctotemplate.gif' styleClass="tinybutton"/>
		