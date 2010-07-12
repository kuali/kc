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

<%@ attribute name="bean" required="true" type="org.kuali.kra.irb.actions.risklevel.ProtocolRiskLevelBean" %>
<%@ attribute name="property" required="true" %>
<%@ attribute name="action" required="true" %>
<%@ attribute name="actionName" required="true" %>

<c:set var="attributes" value="${DataDictionary.ProtocolRiskLevel.attributes}" />

<kra:permission value="${KualiForm.actionHelper.canEnterRiskLevel}">

<kra:innerTab tabTitle="Enter Risk Level" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.newProtocolRiskLevel.*">
   
    <div style="padding-left: 56px" >
        <table class="tab" cellpadding="0" cellspacing="0" summary="">
            
	        <%-- Header --%>
	        <tr>
	            <th><div align="left">&nbsp;</div></th> 
	            <kul:htmlAttributeHeaderCell attributeEntry="${attributes.riskLevelCode}" />
	            <kul:htmlAttributeHeaderCell attributeEntry="${attributes.dateAssigned}" />
	            <kul:htmlAttributeHeaderCell attributeEntry="${attributes.dateUpdated}" />
	            <kul:htmlAttributeHeaderCell attributeEntry="${attributes.status}" />
	            <kul:htmlAttributeHeaderCell attributeEntry="${attributes.comments}" />
	            <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
	        </tr>
                
            <%-- New data --%>                
            <tr> 
                <th class="infoline">
                    <c:out value="Add:" />
                </th>
                <td align="left" valign="middle" class="infoline">
                    <div align="left">
                        <kul:htmlControlAttribute property="${property}.newProtocolRiskLevel.riskLevelCode" attributeEntry="${attributes.riskLevelCode}" styleClass="fixed-size-200-select" />
                    </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                    <div align="center">
                        <kul:htmlControlAttribute property="${property}.newProtocolRiskLevel.dateAssigned" attributeEntry="${attributes.dateAssigned}"  />
                    </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                    <div align="center">
                        <kul:htmlControlAttribute property="${property}.newProtocolRiskLevel.dateUpdated" attributeEntry="${attributes.dateUpdated}"  />
                    </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                    <div align="center">
                        <kul:htmlControlAttribute property="${property}.newProtocolRiskLevel.status" attributeEntry="${attributes.status}" />
                    </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                    <div align="left">
                        <kul:htmlControlAttribute property="${property}.newProtocolRiskLevel.comments" attributeEntry="${attributes.comments}" />
                    </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                    <div align="center">
                        <html:image property="methodToCall.add${actionName}RiskLevel.anchor${tabKey}"
                                    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
                    </div>
                </td>
            </tr>
            
            <%-- Existing data --%>
			<c:forEach var="protocolRiskLevel" items="${KualiForm.document.protocol.protocolRiskLevels}" varStatus="status">
			    <tr>
			         <th class="infoline">
			             ${status.index + 1}
			         </th>
                    <td align="left" valign="middle" class="infoline">
			             <div align="left">
			                 <kul:htmlControlAttribute property="document.protocol.protocolRiskLevels[${status.index}].riskLevelCode" 
			                                           attributeEntry="${attributes.riskLevelCode}" readOnly="true" />
			             </div>
			        </td>
			        <td align="left" valign="middle" class="infoline">
			             <div align="center">
			                 <kul:htmlControlAttribute property="document.protocol.protocolRiskLevels[${status.index}].dateAssigned" 
			                                           attributeEntry="${attributes.dateAssigned}" readOnly="true" />
			             </div>
			        </td>
                    <td align="left" valign="middle" class="infoline">
			             <div align="center">
			                 <kul:htmlControlAttribute property="document.protocol.protocolRiskLevels[${status.index}].dateUpdated" 
			                                           attributeEntry="${attributes.dateUpdated}" readOnly="true" />
                         </div>
                    </td>
			        <td align="left" valign="middle" class="infoline">
			            <div align="center">
			                 <kul:htmlControlAttribute property="document.protocol.protocolRiskLevels[${status.index}].status" 
			                                           attributeEntry="${attributes.status}" readOnly="true" />
			            </div>
			        </td>
			        <td align="left" valign="middle" class="infoline">
			            <div align="left">
			                 <kul:htmlControlAttribute property="document.protocol.protocolRiskLevels[${status.index}].comments" 
			                                           attributeEntry="${attributes.comments}" readOnly="true" />
			            </div>
			        </td>
			        <td align="left" valign="middle" class="infoline">
			            <div align="center">
			                <c:choose>
				                <c:when test="true">
					                <html:image property="methodToCall.delete${actionName}RiskLevel.line${status.index}.anchor${tabKey}"
					                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
				                </c:when>
				            </c:choose>
			            </div>
			        </td>
			    </tr>
			</c:forEach>
        </table>       
    </div>
    
</kra:innerTab>

</kra:permission>
