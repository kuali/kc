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

<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />
<c:set var="syncChangeAttrs" value="${DataDictionary.AwardSyncChange.attributes}" />

<c:set var="syncMode" value="${KualiForm.syncMode}"/>

<style>
  #workarea .syncValidationSuccess th.syncStatus {
     width: 1em;
  }
  #workarea .syncValidationError th.syncStatus {
     width: 1em;
  }
  
  #workarea .syncactions input {
    border: 0;
    margin-top: 1em;
    margin-left: auto;
    margin-right: auto;
  }
  
  #awardSyncLogs-All {
     overflow: auto;
     max-height: 30em;
  }
  
</style>

<script type="text/javascript">
  function showDetails(link, awardNumber) {
	  if (jQuery(link).parent().parent().next().find('td div').hasClass('loaded')) {
		  toggleDetails(link);
	  } else {
		  jQuery.ajax({
			  url:'${ConfigProperties.application.url}/awardSyncAjaxStatus.do?methodToCall=syncStatus&awardSyncBean.statusAwardNumber='+awardNumber+
			  	'&docNum=${node.award.awardDocument.documentNumber}&formKey=${KualiForm.formKey}&docFormKey=${KualiForm.formKey}&documentWebScope=session',
			  type: 'POST',
			  datatype: 'html',
		  	  success:function(data) {
				  	jQuery(link).parent().parent().next().find('td div').html(data);
				  	toggleDetails(link);
				  	jQuery(link).parent().parent().next().find('td div').addClass('loaded');
			  }
		  });
	  }
  }
  function toggleDetails(link) {
	  if (!jQuery(link).parent().parent().next().find('td div').is(':visible')) {
		  jQuery(link).parent().parent().next().find('td div').show();
		  jQuery(link).find('img').attr('src', 'static/images/tinybutton-hidedetails.gif');
	  } else {
		  jQuery(link).parent().parent().next().find('td div').hide();
		  jQuery(link).find('img').attr('src', 'static/images/tinybutton-showdetails.gif');
	  }
  }
  function loadAllLogs() {
	  jQuery.ajax({
		  url:'${ConfigProperties.application.url}/awardSyncAjaxLogs.do?methodToCall=getAllLogs'+
		  	'&docNum=${node.award.awardDocument.documentNumber}&formKey=${KualiForm.formKey}&docFormKey=${KualiForm.formKey}&documentWebScope=session',
		  type: 'POST',
		  datatype: 'html',
		  async: true,
	  	  success:function(data) {
		    var loadingRow = jQuery('#awardSyncLogs-All table tr.syncLogRow-loading');
		    var lastRealRow = jQuery(loadingRow).prev().prev();
		    var tempDom = jQuery('#awardSyncLogs-temp');
		    tempDom.html(data);
		    var nextElems = tempDom.find('#'+jQuery(lastRealRow).attr('id')).next().nextAll();
		    jQuery(loadingRow).after(nextElems);
		    jQuery(loadingRow).detach();
		    tempDom.detach();
		  }
	  });
  }
  jQuery(document).ready(function () {
	  loadAllLogs();
  });
</script>

<input type="hidden" property="viewOnly" value="${readOnly}" />

<c:if test="${KualiForm.editingMode['awardSync']}">
<kul:tab tabTitle="Award Hierarchy Sync" auditCluster="awardSyncAuditErrors" tabErrorKey="document.awardList[0].syncChanges[*" defaultOpen="${syncMode || not empty KualiForm.awardDocument.award.syncStatuses}">
    
	<div class="tab-container" align="left">
	<c:if test="${!readOnly}">
    <center>
    <c:choose><c:when test="${!syncMode}">
      <html:image property="methodToCall.activateSyncMode" style="margin: 1em;" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-enablesync.gif' alt="Turn on Sync Mode" styleClass="tinybutton" disabled="${readOnly}"/>
    </c:when><c:otherwise>
      <html:image property="methodToCall.deactivateSyncMode" style="margin: 1em;" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-disablesync.gif' alt="Turn off Sync Mode" styleClass="tinybutton" disabled="${readOnly}"/>
    </c:otherwise></c:choose>
    </center>
    </c:if>
	
	<c:if test="${syncMode}">
	  <table cellpadding="0" cellspacing="0">
		<tr>
		  <td colspan="4" class="subhead">Synchronize Options</td>
		</tr>
		<tr>
		  <th><kul:htmlAttributeLabel attributeEntry="${awardAttributes.sponsorCode}" readOnly="true"/>
		      <kul:htmlControlAttribute readOnly="true" property="document.awardList[0].sponsorCode" attributeEntry="${awardAttributes.sponsorCode}"/>
	      </th>
		  <td style="text-align: center;"><html:image property="methodToCall.syncSponsor" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-sync.gif' alt="sync" styleClass="tinybutton" disabled="${readOnly}"/></td>
		  <th><kul:htmlAttributeLabel attributeEntry="${awardAttributes.statusCode}" readOnly="true"/>
		      <kul:htmlControlAttribute readOnly="true" property="document.awardList[0].statusCode" attributeEntry="${awardAttributes.statusCode}" readOnlyAlternateDisplay="${KualiForm.awardDocument.award.awardStatus.description}" />
		  </th>
		  <td style="text-align: center;"><html:image property="methodToCall.syncStatusCode" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-sync.gif' alt="sync" styleClass="tinybutton" disabled="${readOnly}"/></td>
		</tr>
	  </table>    		  
	</c:if>
	<c:if test="${syncMode || fn:length(KualiForm.awardDocument.award.syncChanges) > 0}">
   		<table cellpadding="0" cellspacing="0">
		       <tr>
		         <td colspan="8" class="subhead"><span class="subhead-left">Actions</span></td>
		       </tr>
	   		   <tr>
	   		     <th rowspan="2">&nbsp;</th>
	   		     <th rowspan="2"><kul:htmlAttributeLabel attributeEntry="${syncChangeAttrs.syncType}" noColon="true"/></th>
	   		     <th rowspan="2"><kul:htmlAttributeLabel attributeEntry="${syncChangeAttrs.objectDesc}" noColon="true"/></th>
	   		     <th rowspan="2"><kul:htmlAttributeLabel attributeEntry="${syncChangeAttrs.dataDesc}" noColon="true"/></th>
	   		     <th rowspan="2"><kul:htmlAttributeLabel attributeEntry="${syncChangeAttrs.syncDescendants}" noColon="true"/></th>
	   		     <th colspan="2">Include</th>
	   		   </tr>
	   		   <tr>
	   		     <th><kul:htmlAttributeLabel attributeEntry="${syncChangeAttrs.syncFabricated}" useShortLabel="true" noColon="true"/></th>
	   		     <th><kul:htmlAttributeLabel attributeEntry="${syncChangeAttrs.syncCostSharing}" useShortLabel="true" noColon="true"/></th>
	   		   </tr>
		  <c:forEach items="${KualiForm.awardDocument.award.syncChanges}" var="change" varStatus="i">
		  	<tr>
		  	  <td class="datacell" style="text-align: center;"><kul:htmlControlAttribute property="document.awardList[0].syncChanges[${i.index}].delete" attributeEntry="${syncChangeAttrs.delete}" readOnlyAlternateDisplay=" "/></td>
		  	  <td class="datacell"><c:out value="${change.type.syncDesc}"/></td>    		  	  
		  	  <td class="datacell">
		  	  	<c:out value="${change.objectDesc}"/>
		  	  </td>
		  	  <td class="datacell">
		  	    <c:out value="${change.dataDesc}"/>
		  	  </td>
		  	  <td class="datacell" style="text-align: center;"><kul:htmlControlAttribute property="document.awardList[0].syncChanges[${i.index}].syncDescendants" attributeEntry="${syncChangeAttrs.syncDescendants}"/></td>
		  	  <td class="datacell" style="text-align: center;"><kul:htmlControlAttribute property="document.awardList[0].syncChanges[${i.index}].syncFabricated" attributeEntry="${syncChangeAttrs.syncFabricated}"/></td>
              <td class="datacell" style="text-align: center;"><kul:htmlControlAttribute property="document.awardList[0].syncChanges[${i.index}].syncCostSharing" attributeEntry="${syncChangeAttrs.syncCostSharing}"/></td>
		    </tr>
		  </c:forEach>
		  <c:if test="${!readOnly}">
   		    <tr>
   		      <td class="infoline" colspan="8"><html:image property="methodToCall.deleteChanges" 
	   		     			src='${ConfigProperties.kra.externalizable.images.url}tinybutton-deleteselected.gif' styleClass="tinybutton" disabled="${readOnly}"/>	   		     			
	   		    <span style="float: right;"><html:image property="methodToCall.clearSyncSelections"  
	   		     			src='${ConfigProperties.kra.externalizable.images.url}tinybutton-clearselected.gif' styleClass="tinybutton" disabled="${readOnly}"/></span>
	   		  </td>
   		    </tr>
		  </c:if>   		    
   		</table>
   		
   		<div style="text-align: center; margin-top: .5em; font-size: 1.3em; color: red;"><c:out value="${KualiForm.awardSyncBean.parentAwardStatus.status}"/>
   		  <c:if test="${fn:containsIgnoreCase(KualiForm.awardSyncBean.parentAwardStatus.status, 'in progress')}">
   		   	<html:image property="methodToCall.reload"
   		  		src="${ConfigProperties.kra.externalizable.images.url}tinybutton-refresh.gif" styleClass="tinybutton"/>
   		  </c:if>
   		</div>
   		<c:if test="${not empty KualiForm.awardSyncBean.awardStatuses}">
   		<div id="awardSyncLogs" style="width: 100%; border: 1px inset black; text-align: left;">
   		  <h3>Award Hierarchy Sync Validation Messages</h3>
   		  <div id="awardSyncLogs-All">
   		    <table cellpadding="0" cellspacing="0">
   		      <tr>
   		        <th>&nbsp;</th>
   		        <th><kul:htmlAttributeLabel attributeEntry="${awardAttributes.awardId}" noColon="true"/> : <kul:htmlAttributeLabel attributeEntry="${awardAttributes.accountNumber}" noColon="true"/></th>
   		        <th>Version : Document ID</th>
   		        <th><kul:htmlAttributeLabel attributeEntry="${syncStatusAttrs.status}" noColon="true"/></th>
   		        <th>User Actions</th>
   		      </tr>
   		      <kra-a:awardSyncLogs rows="75"/>
   		    </table>
   		  </div>
   		</div>
   		<div id="awardSyncLogs-temp" class="hidden" style="display:none;"></div>
   		</c:if>
   		<c:if test="${KualiForm.awardSyncBean.onValidationNode}">
   		<div class="syncactions" style="text-align: center;">
   		  <html:image property="methodToCall.rerunValidation"
   		  		src="${ConfigProperties.kra.externalizable.images.url}tinybutton-rerunvalidation.gif"
   		  		styleClass="globalButtons"/>
   		</div>
   		</c:if>
	</c:if>
	
	</div>
            
</kul:tab>
</c:if>