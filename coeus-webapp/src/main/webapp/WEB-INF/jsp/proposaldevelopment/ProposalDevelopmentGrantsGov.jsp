<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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
<%@ page import="org.kuali.kra.infrastructure.Constants"%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />

<c:set var="readOnly" value="${not KualiForm.editingMode['modifyProposal'] or not KualiForm.grantsGovEnabled}" scope="request" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="proposalDevelopmentGrantsGov"
	documentTypeName="ProposalDevelopmentDocument"
	renderMultipart="true"
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
