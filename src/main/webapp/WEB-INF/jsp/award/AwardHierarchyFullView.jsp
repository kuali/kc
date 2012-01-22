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


<kul:documentPage
    showDocumentInfo="true"
    htmlFormAction="awardActions"
    documentTypeName="AwardDocument"
    renderMultipart="false"
    showTabButtons="false"
    auditCount="0"
    headerTabActive="actions">

<c:set var="awardHierarchyAttributes" value="${DataDictionary.AwardHierarchy.attributes}" />
<c:set var="action" value="awardHierarchy" />
<input type="hidden" property="viewOnly" id="viewOnly" name="viewOnly" value="${readOnly}" />
<input type="hidden" id = "canCreateAward" name="canCreateAward" value="${KualiForm.canCreateAward}" />
<input type="hidden" property="docTypeName" name="docTypeName" value="${KualiForm.docTypeName}" />

<kul:tabTop tabTitle="Award Hierarchy" defaultOpen="true">

   <div class="tab-container" align="right">
    	<h3> 
    		<span class="subhead-left">Hierarchy Actions</span>  
    		<span class="subhead-right">
    			<kul:help businessObjectClassName="org.kuali.kra.award.awardhierarchy.AwardHierarchy" altText="help"/>
			</span>
        </h3>        
		    
	<table style="border: medium none ; border-collapse: collapse;">    
	<tbody><tr>
		<td style="border: medium none ; border-collapse: collapse; background-color: rgb(234, 233, 234);">
		<!-- need 2 sets of these links so one can be handled by our code to expand all since we are 
			 dynamically loading the tree and the default expand all only expands currently displayed nodes -->
		<span style="display: inline;" id="showntreecontrol">
		<a href="#" title="Collapse the entire tree below" id="shownCollapseLink"><img src="static/images/jquery/minus.gif" /> Collapse All</a>
		<a href="#" title="Expand the entire tree below" id="shownExpandLink"><img src="static/images/jquery/plus.gif" /> Expand All</a>
		</span>
		<span style="display: none;" id="treecontrol">
		<a href="#" title="Collapse the entire tree below">Collapse All</a>
		<a href="#" title="Expand the entire tree below">Expand All</a>
		</span>
		
		</td>
	</tr>
	</tbody></table>
	<div style="position: relative; margin: 2px 0px 0px 0px;">
	  <div style="position:absolute; left:0px; height:725px; width:100%; text-align: center; z-index:100; display:none;" id="loading"><img style="margin-top: 90px;" src="static/images/awardHierarchy-loading.gif" alt="loading"/><span class="statusMessage"></span>
	  </div>	
    <div style = "background:#e4e4e4; clear: left; height: 725px; overflow-y: auto; overflow-x: auto; overflow: auto; position: relative;" id="awardHierarchyScrollable">
              
  		<ul id="awardhierarchy" class="awardHierarchy" class="filetree stripeli treeview"  >
        <%-- <li><span class="folder">00000</span>
        </li> --%>
    	</ul>
    </div>
    </div>
    <div id="debugLog" style="position: relative; overflow-y: auto; height: 15em; display:none; text-align: left; width:100%;"><a href="javascript: $('#loading').hide(); return false;" style="position: absolute; top: 0; right: 0;">Hide Loading</a></div>
    </div>
    
	<c:forEach items="${KualiForm.awardHierarchyNodes}" var="tempNode" varStatus="status">
		<c:set var="createChildProperty" value="methodToCall.create.awardNumber${tempNode.key}" />  
		<c:set var="copyAwardProperty" value="methodToCall.copyAward.awardNumber${tempNode.key}" />
		${kfunc:registerEditableProperty(KualiForm, createChildProperty)}  
		${kfunc:registerEditableProperty(KualiForm, copyAwardProperty)}
	</c:forEach> 
	
    <input type="hidden" id = "rootAwardNumber" name="rootAwardNumber" value="${KualiForm.rootAwardNumber}">
    <input type="hidden" id ="currentAwardNumber" name="document.awardList[0].awardNumber" value="${KualiForm.document.awardList[0].awardNumber}">
    <input type="hidden" id ="currentSeqNumber" name="document.awardList[0].sequenceNumber" value="${KualiForm.document.awardList[0].sequenceNumber}">
    <input type="hidden" id = "selectedAwardNumber" name="selectedAwardNumber" value="${(param.selectedAwardNumber == '' or param.selectedAwardNumber == null) ? selectedAwardNumber : param.selectedAwardNumber}">
	
	<c:forEach var="i" begin="1" end="${fn:length(KualiForm.awardHierarchyNodes)}" step="1" varStatus ="status">
		<input type="hidden" id = "awardHierarchyTempObject[${i}].awardNumber1" name="awardHierarchyTempObject[${i}].awardNumber1" value="${KualiForm.awardHierarchyTempObjects[i].awardNumber1}">
		<c:set var="lookupAwardNumber1" value="methodToCall.performLookup.(!!org.kuali.kra.award.home.Award!!).(((awardNumber:awardHierarchyTempObject[${i}].awardNumber1,awardHierarchyTempObject[${i}].awardNumber1:awardNumber))).((``)).((<>)).(([])).((**)).((^^)).((&&)).((//)).((~~))" />
		${kfunc:registerEditableProperty(KualiForm, lookupAwardNumber1)}
		<input type="hidden" id = "awardHierarchyTempObject[${i}].selectBox1" name="awardHierarchyTempObject[${i}].selectBox1" value="${KualiForm.awardHierarchyTempObjects[i].selectBox1}">
		<input type="hidden" id = "awardHierarchyTempObject[${i}].awardNumber2" name="awardHierarchyTempObject[${i}].awardNumber2" value="${KualiForm.awardHierarchyTempObjects[i].awardNumber2}">
		<c:set var="lookupAwardNumber2" value="methodToCall.performLookup.(!!org.kuali.kra.award.home.Award!!).(((awardNumber:awardHierarchyTempObject[${i}].awardNumber2,awardHierarchyTempObject[${i}].awardNumber2:awardNumber))).((``)).((<>)).(([])).((**)).((^^)).((&&)).((//)).((~~))" />
		${kfunc:registerEditableProperty(KualiForm, lookupAwardNumber2)}
		<input type="hidden" id = "awardHierarchyTempObject[${i}].selectBox2" name="awardHierarchyTempObject[${i}].selectBox2" value="${KualiForm.awardHierarchyTempObjects[i].selectBox2}">
		<input type="hidden" id = "awardHierarchyTempObject[${i}].copyDescendants" name="awardHierarchyTempObject[${i}].copyDescendants" value="${KualiForm.awardHierarchyTempObjects[i].copyDescendants}">
		<input type="hidden" id = "awardHierarchyTempObject[${i}].createNewChildRadio" name="awardHierarchyTempObject[${i}].createNewChildRadio" value="${KualiForm.awardHierarchyTempObjects[i].createNewChildRadio}">
		<input type="hidden" id = "awardHierarchyTempObject[${i}].copyAwardRadio" name="awardHierarchyTempObject[${i}].copyAwardRadio" value="${KualiForm.awardHierarchyTempObjects[i].copyAwardRadio}">
	</c:forEach>
	
	<div id="templates" style="display: none;">
	  <table style="border: 1px solid rgb(147, 147, 147); padding: 0px; border-collapse: collapse;" class="detailTable awardDetails" id="awardDetails">
	    <tbody>
	      <tr><th style="border-style: solid; text-align: left; border-color: rgb(230, 230, 230) rgb(147, 147, 147) rgb(147, 147, 147); border-width: 1px; padding: 3px; border-collapse: collapse; background-color: rgb(184, 184, 184); background-image: none;" colspan="4">Detail: <span class="detailHeading"></span></th></tr>
	      <tr>
	        <th style="text-align: right; width: 160px;"><b>Project Start Date</b></th><td style="width: 200px;" class="projectStartDate"></td>
	        <th style="text-align: right; width: 160px;"><b>Obligation Start Date</b></th><td style="width: 200px;" class="obligationStartDate"></td>
	      </tr>
	      <tr>
	        <th style="text-align: right; width: 160px;"><b>Project End Date</b></th><td class="projectEndDate"></td>
	        <th style="text-align: right; width: 160px;"><b>Obligation End Date</b></th><td class="obligationEndDate"></td>
	      </tr>
	      <tr>
	        <th style="text-align: right; width: 160px;"><b>Anticipated Amount</b></th><td class="anticipatedAmount"></td>
	        <th style="text-align: right; width: 160px;"><b>Obligated Amount</b></th><td class="obligatedAmount"></td>
	      </tr>
	      <tr>
	        <th style="text-align: right; width: 160px;"><b>Title</b></th><td colspan="3" class="title"></td>
	      </tr>
	    </tbody>
	  </table>
	</div>


</kul:tabTop>
<kul:panelFooter />

</kul:documentPage>

	<script language="JavaScript" type="text/javascript" src="dwr/engine.js"></script>

	<script language="JavaScript" type="text/javascript" src="dwr/util.js"></script>
		
	<script language="JavaScript" type="text/javascript" src="dwr/interface/AwardHierarchyUIService.js"></script>	

	<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery/kuali-stylesheet.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery/jquery.treeview.css" type="text/css" />
	<link rel="stylesheet" href="css/award_hierarchy.css" type="text/css" />

	<script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>
	<script type="text/javascript" src="scripts/awardHierarchyShared.js"></script>
	<script type="text/javascript" src="scripts/awardHierarchy.js"></script>