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
		<kra:researchAreas isAuthorizedToMaintainResearchArea="${ResearchAreasForm.authorizedToMaintainResearchAreas}"/>
	</kul:tabTop>
    <kul:panelFooter />
		${kfunc:registerEditableProperty(KualiForm, "methodToCall.reload")}  
		${kfunc:registerEditableProperty(KualiForm, "methodToCall.close")}  
		${kfunc:registerEditableProperty(KualiForm, "methodToCall.cancel")}  

<input type="hidden" id = "researchAreaAjaxCall" value="researchAreaAjax.do"/>
<input type="hidden" id = "authorizedToMaintainResearchAreas" value="${ResearchAreasForm.authorizedToMaintainResearchAreas}"/>
    <div id="globalbuttons" class="globalbuttons">
		<c:if test="${ResearchAreasForm.authorizedToMaintainResearchAreas}">
	        <input type="image" id="save" src="kr/static/images/buttonsmall_save.gif" /> 
		</c:if>
        <input type="image" name="methodToCall.reload" id = "refresh" src="kr/static/images/buttonsmall_refresh.gif" class="globalbuttons" title="refresh" alt="refresh">
	    <input type="image" name="methodToCall.close" id = "close" src="kr/static/images/buttonsmall_close.gif" class="globalbuttons" title="close" alt="close">
	    <input type="image" name="methodToCall.cancel" id = "cancel" src="kr/static/images/buttonsmall_cancel.gif" class="globalbuttons" title="cancel" alt="cancel">
    </div>

    <script type="text/javascript" src="scripts/researchAreaShared.js"></script> 
    <script type="text/javascript">
    var $j = jQuery.noConflict();          
        $j(document).ready(function(){
            $j("#listcontent00").show();
        })
    </script>
</kul:page>
    
 
