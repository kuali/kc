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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="helpRef" value="${ConfigProperties.application.url}/kr/help.do?methodToCall=getHelpUrlByNamespace&amp;helpParameterNamespace=KC-M&amp;helpParameterDetailType=Document&amp;helpParameterName=researchAreaHierarchyHelp" /> 

<kul:page showDocumentInfo="false" docTitle="Area Of Research"
	htmlFormAction="researchAreas" transactionalDocument="false"
	headerTitle="Area Of Research" auditCount="0">

	<div align="left"><kul:help parameterNamespace="KC-M" parameterDetailType="Document" parameterName="researchAreaHelp" altText="help"/>

	<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery/kuali-stylesheet.css"
		type="text/css" />
	<link rel="stylesheet" href="css/jquery/jquery.treeview.css"
		type="text/css" />
	<script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>

	<a name="topOfForm"></a>

	<div align="center" style="margin: 10px">
	<div id="headermsg" align="left"></div>
	<br />
	<kul:tabTop defaultOpen="true" tabTitle="Area of Research"
		tabErrorKey="researchArea*">
		<kra:researchAreas />
	</kul:tabTop>
    <kul:panelFooter />
		${kfunc:registerEditableProperty(KualiForm, "methodToCall.reload")}  
		${kfunc:registerEditableProperty(KualiForm, "methodToCall.close")}  
		${kfunc:registerEditableProperty(KualiForm, "methodToCall.cancel")}  

    <div id="globalbuttons" class="globalbuttons">
        <input type="image" id="save" src="kr/static/images/buttonsmall_save.gif" /> 
	    <input type="image" name="methodToCall.reload" id = "refresh" src="kr/static/images/buttonsmall_refresh.gif" class="globalbuttons" title="refresh" alt="refresh">
	    <input type="image" name="methodToCall.close" id = "close" src="kr/static/images/buttonsmall_close.gif" class="globalbuttons" title="close" alt="close">
	    <input type="image" name="methodToCall.cancel" id = "cancel" src="kr/static/images/buttonsmall_cancel.gif" class="globalbuttons" title="cancel" alt="cancel">
    </div>

    <script type="text/javascript" src="scripts/researchArea.js"></script> 
    <script type="text/javascript">
        $(document).ready(function(){
            $("#listcontent00").show();
        })
    </script>
</kul:page>
    
 
