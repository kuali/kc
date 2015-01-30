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

<c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/>
<c:set var="questionnaireAttributes" value="${DataDictionary.Questionnaire.attributes}" />

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Details </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.coeus.common.questionnaire.framework.core.Questionnaire" altText="help"/> </span>
    </h3>
        
    <table id="response-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
        <tr>
            <th align="right" valign="middle" width="115">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.name}" />
            </th>
            <td align="left" valign="middle">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.name" 
                                          attributeEntry="${questionnaireAttributes.name}" />
            </td>
            <td align="left" valign="middle">
                    Version ${KualiForm.document.newMaintainableObject.businessObject.sequenceNumber}
            </td>
            <th align="right" valign="middle" width="115">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.active}" />
            </th>
            <td align="left" valign="middle">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.active" 
                                          attributeEntry="${questionnaireAttributes.active}" />
            </td>
            
        </tr>
        <tr>
            <th align="right" valign="middle" width="115">
                Status:
            </th>
            <td align="left" valign="middle" colspan="4">
            	 <c:choose>
            		<c:when test="${KualiForm.allQuestionsAreUpToDate}" >
                		This questionnaire is up to date. All questions used are the latest versions.
                	</c:when>
                	
            		<c:otherwise>
            			This questionnaire is NOT up to date; one or more questions used are NOT the latest versions.
            		</c:otherwise>
            	</c:choose>	 
            </td>
        </tr>
        <tr>
            <th align="right" valign="middle" width="115">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.description}" />
            </th>
            <td align="left" valign="middle">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.description" 
                                          attributeEntry="${questionnaireAttributes.description}" />
            </td>
            <th align="right" valign="middle">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.fileName}" />
            </th>
            <c:choose>
            <c:when test="${not readOnly}" >
                <c:set var="addStyle" value="display: none;"/>
                <c:set var="viewStyle" value="display: block;"/>
                <c:if test="${empty KualiForm.document.newMaintainableObject.businessObject.fileName}">
                   <c:set var="addStyle" value="display: block;"/>
                   <c:set var="viewStyle" value="display: none;"/>
                </c:if>
                <td align="left" valign="middle">
                    <div id = "templateFileDiv" class="addsection" style="${addStyle}">
	                    <html:file styleId="templateFile" property="templateFile" size="50" onchange="showViewFile(this)" accept="text/xsl"/>
	                </div>
	                <html:hidden styleId="templateFileNameHidden" property="document.newMaintainableObject.businessObject.fileName" value="${KualiForm.document.newMaintainableObject.businessObject.fileName}"/> 
                    <div id = "fileNameDiv" class="viewsection" style="${viewStyle}">         
                         ${KualiForm.document.newMaintainableObject.businessObject.fileName}  
                    </div>                                                                    
                </td>
	            <td class="infoline">
		            <div id="viewTemplate"  class="viewsection"  style="${viewStyle} align="center">
						<html:image property="methodToCall.viewTemplate"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
										alt="View Protocol Attachment" onclick="excludeSubmitRestriction = true;"/>
                        <html:image property="methodToCall.replaceTemplate"
                        			    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif" 
			                            title="Replace Template" 
            			                alt="Replace Template" 
                        			    styleClass="tinybutton" 
                            			onclick="javascript: replaceTemplate(this);return false" />
					    <html:image property="methodToCall.deleteTemplate"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"
										alt="Delete Template"/>
				    </div>
			    </td>
            </c:when>
            <c:otherwise>
                <td align="left" valign="middle">
	                ${KualiForm.document.newMaintainableObject.businessObject.fileName}
                </td>
	            <td class="infoline">
	                &nbsp;
	            </td>
            </c:otherwise>
            </c:choose>
        </tr>
    </table>
	<input type="hidden" id="questionNumber" name="questionNumber" value = "${KualiForm.questionNumber}"/>
    <%--  <input type="hidden" id="sqlScripts" name="sqlScripts" value = "${KualiForm.sqlScripts}"/> --%>
	<input type="hidden" id="document.newMaintainableObject.businessObject.id" name="document.newMaintainableObject.businessObject.id" value = "${KualiForm.document.newMaintainableObject.businessObject.id}"/>
    <input type="hidden" id="document.newMaintainableObject.businessObject.questionnaireSeqId" name="document.newMaintainableObject.businessObject.questionnaireSeqId" value = "${KualiForm.document.newMaintainableObject.businessObject.questionnaireSeqId}"/>
    <input type="hidden" id="document.newMaintainableObject.businessObject.sequenceNumber" name="document.newMaintainableObject.businessObject.sequenceNumber" value = "${KualiForm.document.newMaintainableObject.businessObject.sequenceNumber}"/>
	<%--  <input type="hidden" id="retData" name="retData" value = "${KualiForm.retData}"/> --%>
	<input type="hidden" id="editData" name="editData" value = "${KualiForm.editData}"/>
	<%--  <input type="hidden" id="versioned" name="versioned" value = "${KualiForm.versioned}"/>--%>
</div>
