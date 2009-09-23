<%--
 Copyright 2005-2007 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="editingMode" required="true" description="used to decide editability of overview fields" type="java.util.Map"%>
<c:set var="isMaintenance" value="${KualiForm.class.name eq 'org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm' || maintenanceViewMode eq Constants.PARAM_MAINTENANCE_VIEW_MODE_MAINTENANCE}" />
<c:set var="readOnly" value="${ ! KualiForm.documentActions[Constants.KUALI_ACTION_CAN_EDIT__DOCUMENT_OVERVIEW]}" />

<c:set var="docHeaderAttributes" value="${DataDictionary.DocumentHeader.attributes}" />
<c:set var="documentTypeName" value="${KualiForm.docTypeName}" />
<c:set var="documentEntry" value="${DataDictionary[documentTypeName]}" />

<dd:evalNameToMap mapName="DataDictionary.${KualiForm.docTypeName}.attributes" returnVar="documentAttributes"/>
<kul:tabTop tabTitle="Document Overview" defaultOpen="true" tabErrorKey="${Constants.DOCUMENT_ERRORS}" >
	<div class="tab-container" align=center>
		  <!-- DOC OVERVIEW TABLE -->
		  <html:hidden property="document.documentHeader.documentNumber" />
		  <h3>Document Overview</h3>
		  <table cellpadding="0" cellspacing="0" class="datatable" title="view/edit document overview information" summary="view/edit document overview information">
		    <tr>
		      <kul:htmlAttributeHeaderCell
		          labelFor="document.documentHeader.documentDescription"
		          attributeEntry="${docHeaderAttributes.documentDescription}"
		          horizontal="true"
		          />
		      <td align="left" valign="middle">
		      	<kul:htmlControlAttribute property="document.documentHeader.documentDescription" attributeEntry="${docHeaderAttributes.documentDescription}" readOnly="${readOnly}"/>
		      </td>
		      <kul:htmlAttributeHeaderCell
                  labelFor="document.documentHeader.explanation"
                  attributeEntry="${docHeaderAttributes.explanation}"
                  horizontal="true"
		          rowspan="2"
                  />
		      <td align="left" valign="middle" rowspan="2">
                  <kul:htmlControlAttribute
                      property="document.documentHeader.explanation"
                      attributeEntry="${docHeaderAttributes.explanation}"
                      readOnly="${readOnly}"
                      readOnlyAlternateDisplay="${fn:replace(fn:escapeXml(KualiForm.document.documentHeader.explanation), Constants.NEWLINE, '<br/>')}"
                      />
              </td>
		    </tr>
		    <tr>
			  <kul:htmlAttributeHeaderCell
		        labelFor="document.documentHeader.organizationDocumentNumber"
		        attributeEntry="${docHeaderAttributes.organizationDocumentNumber}"
		        horizontal="true"
		      />			  
              <td align="left" valign="middle">
              	<kul:htmlControlAttribute property="document.documentHeader.organizationDocumentNumber" attributeEntry="${docHeaderAttributes.organizationDocumentNumber}" readOnly="${readOnly}"/>
              </td>
            </tr>
          </table>
          <jsp:doBody/>            
        </div>
</kul:tabTop>
