<%--
 Copyright 2005-2013 The Kuali Foundation

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

<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />

<c:set var="readOnly" value="${not KualiForm.editingMode['modifyProposal'] or not KualiForm.grantsGovEnabled}" scope="request" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="proposalDevelopmentGrantsGov"
	documentTypeName="ProposalDevelopmentDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="grantsGov">
  	
  	<div align="right"><kul:help documentTypeName="ProposalDevelopmentDocument" pageName="Grants.gov" /></div>

<kra-pd:proposalDevelopmentGrantsGovOpportunitySearch />

<kul:panelFooter />
		<c:if test="${KualiForm.saveXmlPermission and KualiForm.grantsGovSelectFlag}">
			<c:set var="extraButtonSource" value="${ConfigProperties.kra.externalizable.images.url}buttonsmall_savexml.gif"/>
  			<c:set var="extraButtonProperty" value="methodToCall.saveXml"/>
  			<c:set var="extraButtonAlt" value="Save Grants.Gov Xml"/>
  			<c:set var="viewOnly" value="${not KualiForm.editingMode['modifyProposal']}" />
  			<div id="btn_img_content">
  			<html:image
						property="${extraButtonProperty}"
						src="${extraButtonSource}" alt="${extraButtonAlt}" onclick="excludeSubmitRestriction=true" style="border:none;" styleId="saveXml" />
						</div> 
  		</c:if>
  		<script type="text/javascript">		
  		function addToGlobalButtons() {
  			var imgdata=jQuery("#btn_img_content").html();
  			jQuery("#btn_img_content").remove();
  			setTimeout(function(){ 				
  				jQuery("#globalbuttons").prepend(imgdata); 
  			},100);
  		}
  		window.onload=addToGlobalButtons();
  		</script>
<kul:documentControls
		transactionalDocument="false" 
		suppressRoutingControls="true" 
		suppressCancelButton="true" 		
	    viewOnly="${viewOnly}" />
 </kul:documentPage>
