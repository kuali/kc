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
<%@ page import="java.util.HashMap" %>

<c:set var="protocolAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="protocolPersonAttributes" value="${DataDictionary.ProtocolPerson.attributes}" />
<c:set var="personAttributes" value="${DataDictionary.Person.attributes}" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="personnel"
	documentTypeName="ProtocolDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="personnel">
  	
  	<div align="right">
		<kra:shortUrl shortUrl="${KualiForm.shortUrl}"/>
		<kul:help documentTypeName="ProtocolDocument" pageName="Personnel" />
	</div>

	<c:set var="viewOnly" value="${not KualiForm.personnelHelper.modifyPersonnel}" />
	<kra-irb:protocolAddPersonnelSection/>
	<kra-irb:protocolPersons/>
  <c:if test="${not empty viewOnly && ! viewOnly and fn:length(KualiForm.document.protocolList[0].protocolPersons) > 0}">
  	<c:set var="extraButtonSource" value="${ConfigProperties.kra.externalizable.images.url}buttonsmall_deletesel.gif"/>
  	<c:set var="extraButtonProperty" value="methodToCall.deleteProtocolPerson"/>
  	<c:set var="extraButtonAlt" value="Delete a Person"/>
  </c:if>  
  	<p>
	<kul:documentControls 
		transactionalDocument="false"
		suppressRoutingControls="true"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${viewOnly}"
		/>
	</p>

<script type="text/javascript">
   var $j = jQuery.noConflict();
</script>

<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
</SCRIPT>
<script language="javascript" src="scripts/kuali_application.js"></script>
<script language="javascript" src="dwr/interface/UnitService.js"></script>

</kul:documentPage>
