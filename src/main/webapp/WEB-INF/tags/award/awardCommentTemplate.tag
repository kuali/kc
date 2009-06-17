<%--
 Copyright 2006-2008 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="awardAttributes" value="${DataDictionary.AwardDocument.attributes}" />
<c:set var="awardApprovedSubawardAttributes" value="${DataDictionary.AwardTemplate.attributes}" />
<c:set var="syncPropertyName" value="awardComments" />
<c:set var="action" value="awardTemplateSync" />

<kul:tab tabTitle="Comment Template" defaultOpen="false" tabErrorKey="document.award.awardTemplate*">
	<div class="tab-container" align="center">
	    <html:image property="methodToCall.syncAwardTemplate.syncPropertyName${syncPropertyName}.anchor${tabKey}"
		src='${ConfigProperties.kra.externalizable.images.url}tinybutton-synctotemplate.gif' styleClass="tinybutton"/>
	</div>
</kul:tab>
