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
<%@ page import="org.kuali.kra.infrastructure.Constants"%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="negotiationAttributes" value="${DataDictionary.NegotiationDocument.attributes}" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="negotiationNegotiation"
	documentTypeName="NegotiationDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="negotiation">
  	
<script src="scripts/jquery/jquery.js"></script>
<script type="text/javascript"> 
   var $jq = jQuery.noConflict();
</script>
<script type="text/javascript" src="scripts/medusaView.js"></script>  	
  	
<div align="right"><kul:help documentTypeName="NegotiationDocument" pageName="Negotiation" /></div>
<kul:documentOverview editingMode="${KualiForm.editingMode}" />

 <kra-negotiation:negotiation />
 <kra-negotiation:negotiationActivities />

 <kul:tab tabTitle="Custom Data" defaultOpen="false" tabErrorKey="">
 <kra-negotiation:NegotiationCustomDataTab name="${KualiForm.actionName}"/>
 </kul:tab>
  
<kul:tab tabTitle="Medusa" defaultOpen="false" tabErrorKey="">
<kra-m:medusa />
</kul:tab>


<kul:panelFooter />
	<kul:documentControls 
		transactionalDocument="true"
		suppressRoutingControls="true"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${!KualiForm.editingMode['create'] && !KualiForm.editingMode['modify'] && !KualiForm.editingMode['modify_activity']}"
		/>

</kul:documentPage>
