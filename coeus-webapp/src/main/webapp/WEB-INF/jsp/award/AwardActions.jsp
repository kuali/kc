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
	htmlFormAction="awardActions"
	documentTypeName="AwardDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="awardActions"
  	extraTopButtons="${KualiForm.extraTopButtons}" >
  	
  	<div align="right">
		<kra:shortUrl shortUrl="${KualiForm.shortUrl}"/>
  	    <kul:help parameterNamespace="KC-AWARD" parameterDetailType="Document" parameterName="awardActionsHelpUrl" altText="help"/>    
</div>
  	
  	
  	<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}" scope="request" />
  	<c:set var="extraButtons" value="${KualiForm.extraActionsButtons}" scope="request" />
  	<script>
	  $j = jQuery.noConflict();
	</script>


	<script language="JavaScript" type="text/javascript" src="dwr/util.js"></script>
		
	<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery/kuali-stylesheet.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery/jquery.treeview.css" type="text/css" />
	<script>
	  $j = jQuery.noConflict();
	</script>
    <script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>   	
  	
  	  	
<kra-a:awardDataValidation /> 
<kra-a:awardHierarchy />
<kra-a:awardSync />
<kra-a:awardPrint />
<kul:adHocRecipients />
<kul:routeLog />
<kul:superUserActions showTab="false"/>			

<kra:section permission="createAwardAccount">
<kra-a:awardCreateAccount />
</kra:section>

<kra:section permission="postAward">
    <kra-a:awardPostHistory />
</kra:section>
<kra:section permission="postTimeAndMoney">
    <kra-a:tmPostHistory />
</kra:section>

    <kul:panelFooter />
<kul:documentControls transactionalDocument="true"
                      extraButtonSource="${extraButtonSource}"
                      extraButtonProperty="${extraButtonProperty}"
                      extraButtonAlt="${extraButtonAlt}" 
                      extraButtons="${extraButtons}" />

</kul:documentPage>
<script type="text/javascript" src="scripts/awardHierarchyShared.js"></script>
<script type="text/javascript" src="scripts/awardHierarchy.js"></script>		
