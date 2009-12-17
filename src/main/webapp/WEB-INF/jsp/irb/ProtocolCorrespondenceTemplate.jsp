<%--
 Copyright 2006-2009 The Kuali Foundation

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

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>
<c:set var="readOnly" value="false"  scope="request"/>

<kul:page lookup="true" 
          docTitle="Correspondence Template" 
          transactionalDocument="false" 
          htmlFormAction="protocolCorrespondenceTemplate">
          
    <script language="javascript" src="scripts/kuali_application.js"></script>
    
    <div id="workarea">

<kul:tab tabTitle="Correspondence Templates" 
         defaultOpen="true"
         alwaysOpen="true"
         transparentBackground="true" 
         useCurrentTabIndexAsKey="true"
         tabErrorKey="document.newMaintainableObject.*">
         
    <div class="tab-container" align="center" id="G100">
        <h3>
            <span class="subhead-left">Correspondence Templates</span>
            <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplate" altText="help" /></span>
        </h3>
        
        <c:forEach items="${KualiForm.correspondenceTypes}" var="correspondenceType" varStatus="status">
            <kra:innerTab tabTitle="${correspondenceType.description}" 
                          parentTab="Correspondence Templates" 
                          defaultOpen="false"
                          useCurrentTabIndexAsKey="true" 
                          tabErrorKey="document.committeeList[0].committeeMemberships[${memberIndex}].membershipTypeCode,document.committeeList[0].committeeMemberships[${memberIndex}].termStartDate,document.committeeList[0].committeeMemberships[${memberIndex}].termEndDate">
                <p style="text-align:left; font-weight:bold;">Default</p>
                <table style="border-top-width:1px; border-top-style:solid; border-top-color:#999999;" cellpAdding="0" cellspacing="0" width="50%" align="center" >
                    <tr>
                        <th width="75%">File</th>
                        <th width="25%">Actions</th>
                    </tr>

                    <%-- Default Template --%>
                    <tr>
                        <td>
                            <div align="center">
                                <c:set var="property" value="defaultCorrespondenceTemplates[${status.index}].templateFile" />
                        
                                <%-- attachment file error handling logic start--%>
                                    <kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
                                    <%-- highlighting does not work in firefox but does in ie... --%>
                                    <c:set var="textStyle" value="${hasErrors == true ? 'background-color:#FFD5D5' : ''}"/>
                                <%-- attachment file error handling logic end--%>
                        
                                <html:file property="${property}" style="${textStyle}" />
                            </div>
                        </td>
                        <td>
                            <div align="center">
                                <html:image property="methodToCall.viewCorrespondenceTemplate" 
                                    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif" 
                                    title="View Correspondence Template" 
                                    alt="View Correspondence Template" 
                                    styleClass="tinybutton" />
                                <html:image property="methodToCall.replaceCorrespondenceTemplate" 
                                    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif" 
                                    title="Replace Correspondence Template" 
                                    alt="Replace Correspondence Template" 
                                    styleClass="tinybutton" />
                                <html:image property="methodToCall.deleteCorrespondenceTemplate" 
                                    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif" 
                                    title="Delete Correspondence Template" 
                                    alt="Delete Correspondence Template" 
                                    styleClass="tinybutton" />
                             </div>
                         </td>
                     </tr>
                    <%-- Default Template --%>
                     
                </table>
                
                <p style="text-align:left; font-weight:bold;">Customized per Committee</p>
                <table style="border-top-width:1px; border-top-style:solid; border-top-color:#999999;" cellpAdding="0" cellspacing="0" width="50%" align="center" >
                    <tr>
                        <th width="5%"></th>
                        <th width="35%">Committee</th>
                        <th width="35%">File</th>
                        <th width="25%">Actions</th>
                    </tr>
                    
                    <%-- New Template --%>
                    <tr>
                        <td>
                            <div align="center" style="font-weight:bold;">
                                add
                            </div>
                        </td>
                        <td>
                            <div align="left">
                               <c:choose>
                                    <c:when test="${empty KualiForm.newCorrespondenceTemplates[status.index].committee.committeeName}">(select)</c:when>
                                    <c:otherwise>${KualiForm.newCorrespondenceTemplates[status.index].committee.committeeName}</c:otherwise>
                                </c:choose>
                                <kul:lookup boClassName="org.kuali.kra.committee.bo.Committee" 
                                    fieldConversions="id:newCorrespondenceTemplates[${status.index}].committeeIdFk,committeeName:newCorrespondenceTemplates[${status.index}].committee.committeeName" />
                            </div>
                        </td>
                        <td>
                            <div align="center">
                                <c:set var="property" value="newCorrespondenceTemplates[${status.index}].templateFile" />
                        
                                <%-- attachment file error handling logic start--%>
                                    <kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
                                    <%-- highlighting does not work in firefox but does in ie... --%>
                                    <c:set var="textStyle" value="${hasErrors == true ? 'background-color:#FFD5D5' : ''}"/>
                                <%-- attachment file error handling logic end--%>
                        
                                <html:file property="${property}" style="${textStyle}" />
                            </div>
                        </td>
                        <td>
                            <div align="center">
                            <html:image property="methodToCall.addCorrespondence Template" 
                                src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" 
                                title="Add Correspondence Template" 
                                alt="Add Correspondence Template" 
                                styleClass="tinybutton" />
                            </div>
                        </td>
                    </tr>
                    <%-- New Template --%>
                    
                    <%-- Existing Templates --%>
                    <c:forEach items="${correspondenceType.protocolCorrespondenceTemplates}" var="protocolCorrespondenceTemplate" varStatus="status2">
                        <tr>
                            <td>
                                <div align="center" style="font-weight:bold;">
                                   ${status2.index + 1}
                                </div>
                            </td>
                            <td>
                                <div align="left">
                                    ${protocolCorrespondenceTemplate.committee.committeeName}
                                </div>
                            </td>
                            <td>
                                <div align="center">
                <kul:htmlControlAttribute property="correspondenceTypes[${status.index}].protocolCorrespondenceTemplates[${status2.index}].fileName" 
                                          attributeEntry="${DataDictionary.ProtocolCorrespondenceTemplate.attributes.fileName}"
                                          readOnly="true" />
                                          <br>
                                    ${protocolCorrespondenceTemplate.fileName}
                                </div>
                            </td>
                            <td>
                                <div align="center">
                                    <html:image property="methodToCall.viewCorrespondenceTemplate" 
                                        src="${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif" 
                                        title="View Correspondence Template" 
                                        alt="View Correspondence Template" 
                                        styleClass="tinybutton" />
                                    <html:image property="methodToCall.replaceCorrespondenceTemplate" 
                                        src="${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif" 
                                        title="Replace Correspondence Template" 
                                        alt="Replace Correspondence Template" 
                                        styleClass="tinybutton" />
                                    <html:image property="methodToCall.deleteCorrespondenceTemplate" 
                                        src="${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif" 
                                        title="Delete Correspondence Template" 
                                        alt="Delete Correspondence Template" 
                                        styleClass="tinybutton" />
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    <%-- Existing Templates --%>

                </table>
                <p></p>
            </kra:innerTab>
        </c:forEach>
    </div> 
</kul:tab>

<kul:panelFooter /> 

</kul:page>