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
<kul:page lookup="true" docTitle="Proposal Person Certification" transactionalDocument="true" htmlFormAction="proposalDevelopment">
	<script language="javascript" src="scripts/kuali_application.js"></script>
	<link type="text/css" href="css/jquery/questionnaire.css" rel="stylesheet">
	<script>var $j = jQuery.noConflict();</script>
	<script type="text/javascript" src="scripts/questionnaireAnswer.js"></script>

	
	<div id="workarea">
	<c:out value = "${proposalPersonProperty}"/>
	<c:out value = "${status.index}"/>
	<kra-summary:proposalDevelopmentPersonCertification  />
	<kul:panelFooter />
	<div id="globalbuttons" class="globalbuttons"> 
 		<html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif" styleClass="globalbuttons" onclick="window.close()" title="close" alt="close"/>
	</div>
	</div>
</kul:page>