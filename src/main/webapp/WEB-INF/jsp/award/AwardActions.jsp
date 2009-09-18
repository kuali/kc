<%--
 Copyright 2006-2009 The Kuali Foundation

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

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="awardActions"
	documentTypeName="AwardDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="awardActions">
  	
<kra-a:awardDataValidation />
<%--<kra:dataValidation auditActivated="${KualiForm.auditActivated}" topTab="true"/>--%>
<kra-a:awardHierarchy />
<kra-a:awardPrint />
<kul:adHocRecipients />
<kul:routeLog /> 

<kul:panelFooter />

<%--
<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" />
--%>

<kul:documentControls transactionalDocument="true" />

</kul:documentPage>


	<script language="JavaScript" type="text/javascript" src="dwr/engine.js"></script>

	<script language="JavaScript" type="text/javascript" src="dwr/util.js"></script>
		
	<script language="JavaScript" type="text/javascript" src="dwr/interface/AwardHierarchyUIService.js"></script>	

	<script src="scripts/jquery/jquery.js"></script>
	<link rel="stylesheet" href="css/jquery/screen.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery/kuali-stylesheet.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery/jquery.treeview.css" type="text/css" />

	<script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>
	<script type="text/javascript" src="scripts/awardHierarchy.js"></script>