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
<c:set var="kraAttributeReferenceDummyAttributes" value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />


<kul:tab tabTitle="Print Refactor" defaultOpen="false" tabErrorKey="actionHelper.reportType">
    <div class="tab-container" align="left">
        <h3>
            <span class="subhead-left">Print</span>
        </h3>

        <table cellpadding="0" cellspacing="0" summary="print forms">
            <tr>
                <td>
                       <div align="left">
                            <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.protocolDetails" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" styleClass="printOptions"/>Protocol Details
                        </div>
                </td>
                <td>
                       <div align="left">
                            <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.fundingSource" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" styleClass="printOptions"/>Funding Source
                        </div>
                </td>
                <td>
                       <div align="left">
                            <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.ammendmentRenewalSummary" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" styleClass="printOptions"/>Amendment & Renewal
                        </div>
                </td>
            </tr>
            
            <tr>
                <td>
                       <div align="left">
                            <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.organizaition" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" styleClass="printOptions"/>Organization
                        </div>
                </td>
                <td>
                       <div align="left">
                            <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.actions" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" styleClass="printOptions"/>Action History
                        </div>
                </td>
                <td>
                       <div align="left">
                            <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.otherData" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" styleClass="printOptions"/>Other Data
                        </div>
                </td>
            </tr>
            <tr>
                <td>
                       <div align="left">
                            <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.investigator" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" styleClass="printOptions"/>Investigator
                        </div>
                </td>
                <td>
                       <div align="left">
                            <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.subjects" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" styleClass="printOptions"/>Subjects
                        </div>
                </td>
                <td>
                       <div align="left">
                            <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.roles" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" styleClass="printOptions"/>Protocol Roles
                        </div>
                </td>
            </tr>
            <tr>
                <td>
                      <div align="left">
                            <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.studyPersonnels" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" styleClass="printOptions"/>Study Personnel
                        </div>
                </td>
                <td>
                       <div align="left">
                            <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.specialReview" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" styleClass="printOptions"/>Special Review
                        </div>
                </td>
                <td>
                       <div align="left">
                            <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.references" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" styleClass="printOptions"/>References
                        </div>
                </td>
            </tr>

            <tr>
                <td>
                      <div align="left">
                            <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.correspondents" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" styleClass="printOptions"/>Correspondents
                        </div>
                </td>
                <td>
                       <div align="left">
                            <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.riskLevel" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" styleClass="printOptions"/>Risk Levels
                        </div>
                </td>
                <td>
                       <div align="left">
                            &nbsp;
                        </div>
                </td>
            </tr>
            <tr>
                <td>
                      <div align="left">
                            <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.areaOfResearch" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" styleClass="printOptions"/>Area of Research
                        </div>
                </td>
                <td>
                       <div align="left">
                            <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.notes" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" styleClass="printOptions"/>Notes
                        </div>
                </td>
                <td>
                       <div align="left">
                            &nbsp;
                        </div>
                </td>
            </tr>
	        <c:if test="${fn:length(KualiForm.actionHelper.questionnairesToPrints) > 0}">
	           <c:set var = "qnLength" value = "${fn:length(KualiForm.actionHelper.questionnairesToPrints)}" />
	            <tr>
	                <td class="tab-subhead" colspan="3">Questionnaire</td>
	            </tr>
	
	            <%--<c:forEach var="qnPrintOption" items="${KualiForm.actionHelper.questionnairesToPrints}" varStatus="status">--%>
	            <c:forEach var="i" begin="1" end="${qnLength}" step="3">	            
	                <tr>
                        <td>
                            <div align="left">
                                <kul:htmlControlAttribute property="actionHelper.questionnairesToPrints[${i - 1}].selected" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" styleClass="printOptions"/>${KualiForm.actionHelper.questionnairesToPrints[i-1].label}
                            </div>
                        </td>
                        <td>
                            <div align="left">
	                            <c:if test="${qnLength >= i+1}">
                                    <kul:htmlControlAttribute property="actionHelper.questionnairesToPrints[${i}].selected" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" styleClass="printOptions"/>${KualiForm.actionHelper.questionnairesToPrints[i].label}
	                            </c:if>
                            </div>
	                    </td>
                        <td>
                            <div align="left">
	                            <c:if test="${qnLength >= i+2}">
                                    <kul:htmlControlAttribute property="actionHelper.questionnairesToPrints[${i+1}].selected" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" styleClass="printOptions"/>${KualiForm.actionHelper.questionnairesToPrints[i+1].label}
	                            </c:if>
                            </div>
	                    </td>
	                 </tr>     
	            </c:forEach>
            </c:if>
		    <tr>
			    <td colspan="2" class="infoline">
					<div align="center">
                        <html:image property="methodToCall.printProtocolSelectedItems.anchor${currentTabIndex}"
                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-printsel.gif' 
                                styleClass="tinybutton" onclick="excludeSubmitRestriction = true;"/>                         
				    </div>
				</td>
				<td>
					<div align="center">
      			        <html:image property="methodToCall.selectAllProtocolPrint.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif" title="Select All" alt="Select All" styleClass="tinybutton" onclick="$j('.printOptions').attr('checked', true);return false;" />
      			        <html:image property="methodToCall.deselectAllProtocolPrint.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif" title="Select None" alt="Select None" styleClass="tinybutton" onclick="$j('.printOptions').attr('checked', false);return false;" />
					</div>						
				</td>
			</tr>
			
	        <c:if test="${fn:length(KualiForm.document.protocolList[0].activeAttachmentProtocols) > 0}">
	            <tr>
	                <td class="tab-subhead" colspan="3">Protocol Attachments</td>
	            </tr>
	
	            <c:forEach var="protocolAttachment" items="${KualiForm.document.protocolList[0].activeAttachmentProtocols}" varStatus="status">
	                <tr>
	                    <td colspan="2">
	                        ${protocolAttachment.description} - ${protocolAttachment.status.description} (${protocolAttachment.file.name})
	                        <%--<kul:htmlControlAttribute property="document.protocolList[0].attachmentProtocols[${status.index}].file.fileName"
	                                                  attributeEntry="${protocolAttachmentBaseAttributes.description}"
	                                                  readOnly="true" /> --%>
	                    </td>
			            <td align="center" valign="middle">
	                        <div align="center">
	                             <html:image property="methodToCall.viewProtocolAttachment.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' 
										alt="View Attachment" onclick="excludeSubmitRestriction = true;" styleClass="tinybutton"/>
						     </div>
					     </td>
	                    
	                </tr>     
	            </c:forEach>
           </c:if>
           <c:if test="${fn:length(KualiForm.document.protocolList[0].attachmentPersonnels) > 0}">
           	<tr>
                <td class="tab-subhead" colspan="3">Personnel Attachments</td>
            </tr>
           	<c:forEach var="attachmentPersonnel" items="${KualiForm.document.protocolList[0].attachmentPersonnels}" varStatus="status">
           		<tr>
	                    <td colspan="2">
	                        ${attachmentPersonnel.description} - ${attachmentPersonnel.type.description} (${attachmentPersonnel.file.name})
	                    </td>
			            <td align="center" valign="middle">
	                        <div align="center">
	                             <html:image property="methodToCall.viewProtocolPersonnelAttachment.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' 
										alt="View Attachment" onclick="excludeSubmitRestriction = true;" styleClass="tinybutton"/>
						     </div>
					     </td>
	                    
	                </tr>
	          </c:forEach>
           </c:if>
						                         
        </table>
    </div>
</kul:tab>
