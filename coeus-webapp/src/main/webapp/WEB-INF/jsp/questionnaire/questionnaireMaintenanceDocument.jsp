<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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

<c:set var="docTitle" value="${KualiForm.document.newMaintainableObject.maintainableTitle}" />
<c:set var="isMaintenance" value="true" />
<c:set var="linesTitle" value="Edit Record" />
<c:set var="showDocumentInfo" value="true" />
<c:set var="docTitle" value="${docTitle}" />
<c:set var="htmlFormAction" value="maintenanceQn" />
<c:set var="renderMultipart" value="true" />
<c:set var="showTabButtons" value="true" />
<c:set var="defaultMethodToCall" value="save" />
<c:set var="additionalScriptFiles" value="${KualiForm.additionalScriptFiles}" />
<c:set var="lookup" value="false" />
<c:set var="headerMenuBar" value="" />
<c:set var="headerTitle" value="" />
<c:set var="displayTopicFieldInNotes" value="${KualiForm.document.displayTopicFieldInNotes}" />
<c:set var="documentTypeName" value="${KualiForm.docTypeName}" />
<c:set var="documentEntry" value="${DataDictionary[documentTypeName]}" />

<c:set var="renderRequiredFieldsLabel" value="${(KualiForm.documentActions[Constants.KUALI_ACTION_CAN_EDIT]
||KualiForm.documentActions[Constants.KUALI_ACTION_CAN_AD_HOC_ROUTE]) && (not KualiForm.suppressAllButtons)}" />
<kul:page showDocumentInfo="${showDocumentInfo}" docTitle="${docTitle}"
    htmlFormAction="${htmlFormAction}" transactionalDocument="false" maintenanceDocument="true"
    renderMultipart="${renderMultipart}" showTabButtons="${showTabButtons}"
    defaultMethodToCall="${defaultMethodToCall}" additionalScriptFiles="${additionalScriptFiles}"
    lookup="${lookup}" headerMenuBar="${headerMenuBar}" headerTitle="${headerTitle}" auditCount="0" renderRequiredFieldsLabel="${renderRequiredFieldsLabel}">

<%-- Put the header on the page. --%>
            <table width="100%" cellpadding="0" cellspacing="0" class="tab" id="ryansHeader">
                <tr>
                    <td>
                    
                        <%-- this line must stay above the set of hidden fields --%>
                        <c:set var="FieldSections" value="${KualiForm.sections}" />
                        <html:hidden property="document.documentNumber" />
                        
                        <c:forEach items="${additionalScriptFiles}" varStatus="status" >
                            <html:hidden property="additionalScriptFile[${status.index}]" />
                        </c:forEach>
                        <c:forEach items="${KualiForm.editingMode}" var="mode">
                            <html:hidden property="editingMode(${mode.key})" />
                        </c:forEach>
                        <c:forEach items="${KualiForm.document.newMaintainableObject.inactiveRecordDisplay}" var="inactiveDisplay">
                            <html:hidden property="document.newMaintainableObject.inactiveRecordDisplay(${fn:replace(inactiveDisplay.key,'.','_')})" />
                            <html:hidden property="document.oldMaintainableObject.inactiveRecordDisplay(${fn:replace(inactiveDisplay.key,'.','_')})" />
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

      <kul:tab tabTitle="${section.sectionTitle}" defaultOpen="${section.defaultOpen}" tabErrorKey="${section.errorKey}" highlightTab="${tabHighlight}" extraButtonSource="${section.extraButtonSource}" hidden="${section.hidden}" > 
        <div class="tab-container" align="center" <c:if test="${section.hidden}">style="display:none;"</c:if> >
          <table width="100%" cellpadding="0" cellspacing="0" class="datatable">
             <kul:rowDisplay rows="${section.rows}" numberOfColumns="${section.numberOfColumns}" rowsReadOnly="${section.readOnly}"/>
          </table>   
        </div>
      </kul:tab>
    </c:forEach>
    <%-- this is questionnaireEditor.jsp --%>
    <c:if test="${!empty KualiForm.additionalSectionsFile}">
        <jsp:include page="${KualiForm.additionalSectionsFile}" />
    </c:if>
    <%-- Put the footer on the page. --%>
   <%-- <c:if test="${KualiForm.document.newMaintainableObject.boNotesEnabled}">
    <kul:notes displayTopicFieldInNotes="${_displayTopicFieldInNotes}"/>
    </c:if>--%>
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

