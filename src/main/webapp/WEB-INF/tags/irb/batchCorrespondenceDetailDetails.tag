<%--
 Copyright 2006-2010 The Kuali Foundation

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

    <tr>
        <kul:htmlAttributeHeaderCell literalLabel="One or after this day" scope="col" align="center" width="30%" />
        <kul:htmlAttributeHeaderCell literalLabel="Take this Action" scope="col" align="center" width="50%" />
        <c:if test="${!readOnly}">
            <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" align="center" width="20%" />
        </c:if>
    </tr>
    
    <%-- New Details --%>
    <c:if test="${!readOnly}">
        <tr>
            <td>
                <div align="center" style="font-weight:bold;">
                    <kul:htmlControlAttribute property="newBatchCorrespondenceDetail.noOfDaysTillNext"
                                              attributeEntry="${DataDictionary.BatchCorrespondenceDetail.attributes.noOfDaysTillNext}" />
                    (of ${KualiForm.batchCorrespondence.defaultTimeWindow})
                </div>
            </td>
            <td>
                <div align="left">
                    <kul:htmlControlAttribute property="newBatchCorrespondenceDetail.protoCorrespTypeCode"
                                              attributeEntry="${DataDictionary.BatchCorrespondenceDetail.attributes.protoCorrespTypeCode}" />
                </div>
            </td>
            <td>
                <div align="center">
                <html:image property="methodToCall.addCorrespondenceTemplate.correspondenceType[${index}]" 
                    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" 
                    title="Add Correspondence Template" 
                    alt="Add Correspondence Template" 
                    styleClass="tinybutton" />
                </div>
            </td>
        </tr>
    </c:if>
    <%-- New Details --%>
    
    <%-- Existing Details --%> 
    <c:forEach items="${KualiForm.batchCorrespondence.batchCorrespondenceDetails}" var="details" varStatus="status">
        <tr>
            <td>
                <div align="center">
                   ${details.noOfDaysTillNext} (of ${KualiForm.batchCorrespondence.defaultTimeWindow})
                </div>
            </td>
            <td>
                <div align="left">
                    ${protocolCorrespondenceTemplate.committee.committeeName}
                </div>
            </td>
            <c:if test="${!readOnly}">
                <td>
                    <div align="center">
                        <html:image property="methodToCall.deleteBatchCorrespondenceDetail[${status.index}]}" 
                            src="${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif" 
                            title="delete" 
                            alt="delete" 
                            styleClass="tinybutton" />
                    </div>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    <%-- Existing Details --%>
    
    <%-- Final --%>
    <tr>
        <td>
            <div align="center" style="font-weight:bold;">
                ${KualiForm.batchCorrespondence.defaultTimeWindow} (of ${KualiForm.batchCorrespondence.defaultTimeWindow})
            </div>
        </td>
        <td>
            <div align="left">
                <kul:htmlControlAttribute property="batchCorrespondence.finalActionTypeCode"
                                          attributeEntry="${DataDictionary.BatchCorrespondence.attributes.finalActionTypeCode}" />
            </div>
        </td>
        <c:if test="${!readOnly}">
            <td>
            </td>
        </c:if>
    </tr>
    <%-- Final --%>
    

