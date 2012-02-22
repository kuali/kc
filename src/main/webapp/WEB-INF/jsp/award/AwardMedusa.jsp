<%--
 Copyright 2005-2010 The Kuali Foundation

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
<!-- AwardMedusa.jsp -->

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="awardMedusa"
	documentTypeName="AwardDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="medusa"
  	extraTopButtons="${KualiForm.extraTopButtons}" >

<kul:tabTop tabTitle="Medusa" defaultOpen="true" tabErrorKey="">
<kra-m:medusa helpParameterNamespace="KC-AWARD" helpParameterDetailType="Document" helpParameterName="awardMedusaHelpUrl" />
</kul:tabTop>
<kul:panelFooter />
<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" suppressCancelButton="true" />

	<script type="text/javascript" src="scripts/medusaView.js"></script>	
	
</kul:documentPage>
