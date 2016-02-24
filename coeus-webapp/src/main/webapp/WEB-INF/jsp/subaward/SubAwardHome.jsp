<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
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

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
  <kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="subAwardHome"
	documentTypeName="SubAwardDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="subAward"
  	>
  	

<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}" scope="request" />
<div align="right">
	<kra:shortUrl shortUrl="${KualiForm.shortUrl}"/>
	<kul:help documentTypeName="SubAwardDocument" pageName="Subaward" />
</div>

<kul:documentOverview editingMode="${KualiForm.editingMode}" />

<kra-sub:subAward/>
<kra-sub:subAwardFundingSource/>
<kra-sub:subAwardContact/>	
<kra-sub:subAwardCloseout/>
<kul:panelFooter />

<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
</SCRIPT>
<script language="javascript" src="scripts/kuali_application.js"></script>
<script>
  $j = jQuery.noConflict();
</script>
<script language="javascript" src="dwr/interface/SponsorService.js"></script>
<c:if test="${readOnly}">
  <c:if test="${KualiForm.displayEditButton && KualiForm.subAwardDocument.subAward.editSubAward}">
	<c:set var="extraButtonSource" value="${ConfigProperties.kra.externalizable.images.url}buttonsmall_edit_temp.gif"/>
	<c:set var="extraButtonProperty" value="methodToCall.editOrVersion"/>
	<c:set var="extraButtonAlt" value="Edit or Version"/>
  </c:if>
</c:if>

<c:choose>
	<c:when test="${not KualiForm.editingMode['viewOnly']}">
		<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" 
								extraButtonSource="${extraButtonSource}" 
								extraButtonProperty="${extraButtonProperty}"
								extraButtonAlt="${extraButtonAlt}" 
							    suppressCancelButton="true"/>
	</c:when>				    
	<c:otherwise>
		<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" 
								extraButtonSource="${extraButtonSource}" 
								extraButtonProperty="${extraButtonProperty}"
								extraButtonAlt="${extraButtonAlt}"
								viewOnly= "true" 
							    suppressCancelButton="true"/>
	</c:otherwise>	
</c:choose>	    	    
</kul:documentPage>
