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


<c:set var="protocolExceptions" value="${KualiForm.document.protocol.iacucProtocolExceptions}" />
<c:set var="protocolExceptionAttributes" value="${DataDictionary.IacucProtocolException.attributes}" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="iacucProtocolException"
	documentTypeName="IacucProtocolDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="protocolException">

  	<div align="right">
		<kra:shortUrl shortUrl="${KualiForm.shortUrl}"/>
		<kul:help documentTypeName="${KualiForm.docTypeName}" pageName="Protocol Exception" />
	</div>
  	
	<div id="workarea">
		<kra-iacuc:protocolException businessObjectClassName="org.kuali.kra.iacuc.species.exception.IacucProtocolException"
		                            protocolExceptionAttributes="${protocolExceptionAttributes}"
		                            collectionReference="${protocolExceptions}"
		                            collectionProperty="document.protocolList[0].iacucProtocolExceptions"
		                            action="iacucProtocolException" />
		<kul:panelFooter />
	</div>


	<script type="text/javascript">
	   var $j = jQuery.noConflict();
	</script>
	
	<SCRIPT type="text/javascript">
	var kualiForm = document.forms['KualiForm'];
	var kualiElements = kualiForm.elements;
	</SCRIPT>
	<script language="javascript" src="scripts/kuali_application.js"></script>

	<kul:documentControls transactionalDocument="false" suppressRoutingControls="true" />
</kul:documentPage>
