<%--
 Copyright 2005-2007 The Kuali Foundation.
 
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

<%@ attribute name="editingMode" required="true" description="used to decide editability of overview fields" type="java.util.Map"%>
<%@ attribute name="includePostingYear" required="false" description="set to true to include posting year in document overview" %>
<%@ attribute name="postingYearOnChange" required="false" description="set to the value of the onchange event for the posting year control" %>
<%@ attribute name="includePostingYearRefresh" required="false" description="set to true to include posting year refresh button in document overview" %>
<%@ attribute name="postingYearAttributes" required="false" type="java.util.Map" description="The DataDictionary entry containing attributes for the posting year field." %>

<c:set var="readOnly" value="${empty editingMode['fullEntry']}" />
<c:set var="docHeaderAttributes" value="${DataDictionary.DocumentHeader.attributes}" />

<dd:evalNameToMap mapName="DataDictionary.${KualiForm.docTypeName}.attributes" returnVar="documentAttributes"/>
<kul:tabTop tabTitle="Document Overview" defaultOpen="true" tabErrorKey="${Constants.DOCUMENT_ERRORS}" >
	<div class="tab-container" align=center>
		  <!-- DOC OVERVIEW TABLE -->
		  <html:hidden property="document.documentHeader.versionNumber" />
		  <html:hidden property="document.documentHeader.objectId" />
		  <html:hidden property="document.documentHeader.documentNumber" />
		  <div class="h2-container">
		    <h2>Document Overview</h2>
		  </div>
		  <table cellpadding="0" cellspacing="0" class="datatable" title="view/edit document overview information" summary="view/edit document overview information">
		    <tr>
		      <kul:htmlAttributeHeaderCell
		          labelFor="document.documentHeader.financialDocumentDescription"
		          attributeEntry="${docHeaderAttributes.financialDocumentDescription}"
		          horizontal="true"
		          />
		      <td align="left" valign="middle">
		      	<kul:htmlControlAttribute property="document.documentHeader.financialDocumentDescription" attributeEntry="${docHeaderAttributes.financialDocumentDescription}" readOnly="${readOnly}"/>
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
                      readOnlyAlternateDisplay="${fn:replace(KualiForm.document.documentHeader.explanation, Constants.NEWLINE, '<br/>')}"
                      />
              </td>
		    </tr>
		    <tr>
		      <c:if test="${includePostingYear}">
		          <kul:htmlAttributeHeaderCell
                    labelFor="document.postingYear"
                    attributeEntry="${postingYearAttributes.postingYear}"
                    horizontal="true"
                   />

                <td class="datacell-nowrap">
                    <kul:htmlControlAttribute 
                        attributeEntry="${postingYearAttributes.postingYear}" 
                        property="document.postingYear" 
                        onchange="${postingYearOnChange}"
                        readOnly="${!KualiForm.editingMode['fullEntry']}"/>
                    <c:if test="${!readOnly and includePostingYearRefresh}">
                       <html:image property="methodToCall.refresh" src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_refresh.gif" alt="refresh" title="refresh" styleClass="tinybutton"/>    
                    </c:if>   
                </td>
              </c:if>
              <c:if test="${!includePostingYear}">
		        <th colspan="2">
		        	&nbsp;
		        </th>
		      </c:if>
		    </tr>
		    <tr>
	            <c:if test="${KualiForm.documentActionFlags.hasAmountTotal}">
		          <kul:htmlAttributeHeaderCell
                    labelFor="document.documentHeader.financialDocumentTotalAmount"
                    attributeEntry="${docHeaderAttributes.financialDocumentTotalAmount}"
                    horizontal="true"/>

                   <td align="left" valign="middle">
                    <kul:htmlControlAttribute 
                        attributeEntry="${docHeaderAttributes.financialDocumentTotalAmount}" 
                        property="document.documentHeader.financialDocumentTotalAmount" 
                        readOnly="true"/>
                  </td>
                </c:if>
	            <c:if test="${!KualiForm.documentActionFlags.hasAmountTotal}">
		          <th colspan="2">
		             &nbsp;
		          </th>
		        </c:if>
		        
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