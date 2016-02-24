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
<%@ page import="org.kuali.kra.infrastructure.Constants"%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="committeeAttributes" value="${DataDictionary.CommonCommitteeDocument.attributes}" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="iacucCommitteeCommittee"
	documentTypeName="CommonCommitteeDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="committee">

<div align="right"><kul:help documentTypeName="CommonCommitteeDocument" pageName="Committee" /></div>
<kul:documentOverview editingMode="${KualiForm.editingMode}" />

<kra-committee:committee cmtAttributes="${DataDictionary.IacucCommittee.attributes}" />
<kra-committee:committeeResearchAreas researchAreaReference = "org.kuali.kra.iacuc.IacucResearchArea"/>

<kul:panelFooter />
	<kul:documentControls 
		transactionalDocument="false"
		suppressRoutingControls="false"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${KualiForm.editingMode['viewOnly']}"
		/>





<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
</SCRIPT>

<script language="javascript" src="dwr/interface/UnitService.js"></script>
<script>
loadUnitNameTo('document.committeeList[0].homeUnitNumber','document.committee.homeUnitName');
</script>
</kul:documentPage>
