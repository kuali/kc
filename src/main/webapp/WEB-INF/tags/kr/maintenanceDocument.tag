<%--
 Copyright 2007 The Kuali Foundation.
 
 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<c:set var="docTitle" value="${KualiForm.document.newMaintainableObject.maintainableTitle}" />
<c:set var="isMaintenance" value="true" />
<c:set var="linesTitle" value="Edit Record" />
<c:set var="showDocumentInfo" value="true" />
<c:set var="docTitle" value="${docTitle}" />
<c:set var="htmlFormAction" value="maintenance" />
<c:set var="renderMultipart" value="true" />
<c:set var="showTabButtons" value="true" />
<c:set var="defaultMethodToCall" value="save" />
<c:set var="additionalScriptFiles" value="${KualiForm.additionalScriptFiles}" />
<c:set var="lookup" value="false" />
<c:set var="headerMenuBar" value="" />
<c:set var="headerTitle" value="" />
<c:set var="displayTopicFieldInNotes" value="${KualiForm.document.displayTopicFieldInNotes}" />


<kul:page showDocumentInfo="${showDocumentInfo}" docTitle="${docTitle}"
	htmlFormAction="${htmlFormAction}" transactionalDocument="false"
	renderMultipart="${renderMultipart}" showTabButtons="${showTabButtons}"
	defaultMethodToCall="${defaultMethodToCall}" additionalScriptFiles="${additionalScriptFiles}"
	lookup="${lookup}" headerMenuBar="${headerMenuBar}" headerTitle="${headerTitle}" auditCount="0">

<%-- Put the header on the page. --%>
			<table width="100%" cellpadding="0" cellspacing="0" class="tab" id="ryansHeader">
			    <tr>
			        <td>
			        
						<%-- this line must stay above the set of hidden fields --%>
						<c:set var="FieldSections" value="${KualiForm.sections}" />
						<html:hidden property="document.documentNumber" />
						<html:hidden property="document.objectId" />
						<html:hidden property="document.versionNumber" />
						<html:hidden property="docTypeName" />
						<html:hidden property="readOnly" />
						<html:hidden property="businessObjectClassName" />
						<html:hidden property="maintenanceAction" />
						<html:hidden property="document.newMaintainableObject.maintenanceAction" />
						<html:hidden property="document.oldMaintainableObject.maintenanceAction" />
						<html:hidden property="document.newMaintainableObject.businessObject.objectId" />
						<html:hidden property="document.fieldsClearedOnCopy" />
						<c:forEach items="${additionalScriptFiles}" varStatus="status" >
							<html:hidden property="additionalScriptFile[${status.index}]" />
						</c:forEach>
						<c:forEach items="${KualiForm.editingMode}" var="mode">
							<html:hidden property="editingMode(${mode.key})" />
						</c:forEach>
						<c:forEach items="${KualiForm.document.newMaintainableObject.inactiveRecordDisplay}" var="inactiveDisplay">
							<html:hidden property="document.newMaintainableObject.inactiveRecordDisplay(${inactiveDisplay.key})" />
							<html:hidden property="document.oldMaintainableObject.inactiveRecordDisplay(${inactiveDisplay.key})" />
						</c:forEach>

						<kul:documentOverview editingMode="${KualiForm.editingMode}" />

	<script type="text/javascript"><!--
	    var kualiForm = document.forms['KualiForm'];
	    var kualiElements = kualiForm.elements;
	    // -->
	</script>
	

    <%-- Show the information about the business object. --%>
	<c:forEach items="${FieldSections}" var="section">
	  <%-- call helper tag to look ahead through fields for old to new changes, and highlight tab if so --%>
      <kul:checkTabHighlight rows="${section.rows}" addHighlighting="${isMaintenance && (Constants.MAINTENANCE_EDIT_ACTION eq KualiForm.maintenanceAction)}" />

	  <kul:tab tabTitle="${section.sectionTitle}" defaultOpen="true" tabErrorKey="${section.errorKey}" highlightTab="${tabHighlight}" extraButtonSource="${section.extraButtonSource}" > 
	    <div class="tab-container" align="center">
	      <table width="100%" cellpadding=0 cellspacing=0 class="datatable">
		     <kul:rowDisplay rows="${section.rows}" numberOfColumns="${section.numberOfColumns}" />
		  </table>   
        </div>
	  </kul:tab>
	</c:forEach>

            <%-- Put the footer on the page. --%>
			<c:if test="${KualiForm.document.newMaintainableObject.boNotesEnabled}">
				<kul:notes notesBo="${KualiForm.document.documentBusinessObject.boNotes}" noteType="${Constants.NoteTypeEnum.BUSINESS_OBJECT_NOTE_TYPE}" displayTopicFieldInNotes="${_displayTopicFieldInNotes}"/>
			</c:if>
			<c:if test="${!KualiForm.document.newMaintainableObject.boNotesEnabled}">
				<kul:notes displayTopicFieldInNotes="${displayTopicFieldInNotes}"/>
			</c:if>

			<kul:adHocRecipients />
			<kul:routeLog />
			<kul:panelFooter />
			<kul:documentControls transactionalDocument="false" />
	</td>
	</tr>
	</table>

</kul:page>
