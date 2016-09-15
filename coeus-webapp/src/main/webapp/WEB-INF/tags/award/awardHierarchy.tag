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

<c:set var="awardHierarchyAttributes" value="${DataDictionary.AwardHierarchy.attributes}" />
<c:set var="action" value="awardHierarchy" />
<c:set var="awardDocumentNumber" value="${KualiForm.document.documentNumber}"/>
<c:set var="awardNumber" value="${KualiForm.document.awardList[0].awardNumber}"/>

<link rel="stylesheet" href="css/award_hierarchy.css" type="text/css" />

<input type="hidden" property="viewOnly" id="viewOnly" name="viewOnly" value="${readOnly}" />
<input type="hidden" id = "canCreateAward" name="canCreateAward" value="${KualiForm.canCreateAward}" />

<kul:tab tabTitle="Hierarchy Actions" defaultOpen="${param.command eq 'displayDocSearchView' ? true : false}" tabErrorKey="awardHierarchyTempObject*" auditCluster="awardHierarchyAuditErrors" tabAuditKey="document.awardHierarchyAuditRules*" useRiceAuditMode="true">
	<div class="tab-container" align="right">
    	<h3> 
    		<span class="subhead-left">Hierarchy Actions</span>  
    		<span class="subhead-right">
    			<a href="${pageContext.request.contextPath}/awardHierarchyFullView.do?command=redirectAwardHierarchyFullViewForPopup&awardDocumentNumber=${awardDocumentNumber}&awardNumber=${awardNumber}&docTypeName=${KualiForm.docTypeName}" target="_blank" >
    			<img align="top" width="80" height="15" alt="Open Window" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-openwindow.gif" styleClass="tinybutton" />
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
	  <div style="position:absolute; left:0px; height=285px; width:100%; text-align: center; z-index:100; display:none;" id="loading"><img style="margin-top: 90px;" src="static/images/awardHierarchy-loading.gif" alt="loading"/><span class="statusMessage"></span>
	  </div>	
     <div id="awardHierarchyScrollable" style = "background:#e4e4e4; clear: left; height: 285px; width: 100%; overflow-y: auto; overflow-x: auto; position: relative;" >
              
  		<ul id="awardhierarchy" class="awardHierarchy" class="filetree stripeli treeview"  >
    	</ul>
    </div>
    </div>
    
    </div>
    <div id="debugLog" style="position: relative; overflow-y: auto; height: 15em; display:none; text-align: left; width:100%;"><a href="javascript: $('#loading').hide(); return false;" style="position: absolute; top: 0; right: 0;">Hide Loading</a></div>

    <%-- in the case of a canceled Award, there will be no hierarchy info. So make Rice happy by calling  --%> 
    <%-- registerEditableProperty using the root award number.                                            --%>
    <c:choose>
        <c:when test="${fn:length(KualiForm.awardHierarchyBean.currentAwardHierarchy) == 0}" >
	    	<c:set var="copyAwardProperty" value="methodToCall.copyAward.awardNumber${KualiForm.rootAwardNumber}" />
		    ${kfunc:registerEditableProperty(KualiForm, copyAwardProperty)}
        </c:when> 
        <c:otherwise>   
	        <c:forEach items="${KualiForm.awardHierarchyBean.currentAwardHierarchy}" var="tempNode" varStatus="status">
		        <c:set var="createChildProperty" value="methodToCall.create.awardNumber${tempNode.key}" />  
		        <c:set var="copyAwardProperty" value="methodToCall.copyAward.awardNumber${tempNode.key}" />
		        ${kfunc:registerEditableProperty(KualiForm, createChildProperty)}  
		        ${kfunc:registerEditableProperty(KualiForm, copyAwardProperty)}
	        </c:forEach>
	    </c:otherwise>
	</c:choose> 
	
    <input type="hidden" id = "rootAwardNumber" name="rootAwardNumber" value="${KualiForm.rootAwardNumber}">
    <input type="hidden" id ="currentAwardNumber" name="document.awardList[0].awardNumber" value="${KualiForm.document.awardList[0].awardNumber}">
    <input type="hidden" id ="currentSeqNumber" name="document.awardList[0].sequenceNumber" value="${KualiForm.document.awardList[0].sequenceNumber}">
    <input type="hidden" id = "selectedAwardNumber" name="selectedAwardNumber" value="${(param.selectedAwardNumber == '' or param.selectedAwardNumber == null) ? selectedAwardNumber : param.selectedAwardNumber}">
    <c:set var="awardLookupMethodToCall" value="methodToCall.performLookup.(!!org.kuali.kra.award.home.Award!!).(((awardNumber:awardHierarchyTargetAwardNumber))).((``)).((<>)).(([])).((**)).((^^)).((&&)).((//)).((~~))"/>
	<script type="text/javascript">
		var awardHierarchyTargetAwardNumber = '${KualiForm.awardHierarchyTargetAwardNumber}';
		var awardHierarchySourceAwards = [${KualiForm.awardHierarchySourceAwardStrList}];
		var awardHierarchyTargetAwards = [${KualiForm.awardHierarchyTargetAwardStrList}];
		var awardLookupMethodToCall = "${awardLookupMethodToCall}";
	</script>
	${kfunc:registerEditableProperty(KualiForm, awardLookupMethodToCall)}
	<c:forEach var="i" begin="1" end="${KualiForm.awardHierarchyBean.maxAwardNumber}" step="1" varStatus ="status">
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
            
</kul:tab>
