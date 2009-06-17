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
<script language="javascript" src="dwr/interface/OrganizationService.js"></script>

<c:set var="protocolLocationAttributes" value="${DataDictionary.ProtocolLocation.attributes}" />
<c:set var="organizationAttributes" value="${DataDictionary.Organization.attributes}" />
<c:set var="protocolOrganizationTypeAttributes" value="${DataDictionary.ProtocolOrganizationType.attributes}" />
<c:set var="rolodexAttributes" value="${DataDictionary.Rolodex.attributes}" />
<c:set var="action" value="protocolLocation" />
<c:set var="readOnly" value="${!KualiForm.protocolHelper.modifyProtocol}" />

<kul:tab tabTitle="Organizations" defaultOpen="false" tabErrorKey="protocolHelper.newProtocolLocation*" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Organizations</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.protocol.ProtocolLocation" altText="help"/></span>
        </h3>
        
        <table cellpadding="0" cellspacing="0" summary="">
          	<%-- Header --%>
          	<tr>
          		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" /> 
          		<kul:htmlAttributeHeaderCell attributeEntry="${protocolLocationAttributes.organizationId}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${protocolLocationAttributes.protocolOrganizationTypeCode}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${protocolLocationAttributes.rolodexId}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${organizationAttributes.humanSubAssurance}" scope="col" /></div></th>
          		<c:if test="${!readOnly}">
          		    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
          		</c:if>
          	</tr> 
          	<%-- Header --%>
          	
             <%-- New data --%>
        	<kra:permission value="${KualiForm.protocolHelper.modifyProtocol}">
	             <tr>
					<th class="infoline">
						<c:out value="Add:" />
					</th>
	
	                <td align="left" valign="middle"  class="infoline">
	                	<kul:htmlControlAttribute property="protocolHelper.newProtocolLocation.organizationId" attributeEntry="${protocolLocationAttributes.organizationId}" onblur="loadOrganizationName('protocolHelper.newProtocolLocation.organizationId', 'protocolHelper.organizationName');" />
	                    <kul:lookup boClassName="org.kuali.kra.bo.Organization" 
	                    fieldConversions="organizationId:protocolHelper.newProtocolLocation.organizationId,contactAddressId:protocolHelper.newProtocolLocation.rolodexId,humanSubAssurance:protocolHelper.newProtocolLocation.organization.humanSubAssurance,organizationName:protocolHelper.newProtocolLocation.organization.organizationName,rolodex.firstName:protocolHelper.newProtocolLocation.organization.rolodex.firstName,rolodex.lastName:protocolHelper.newProtocolLocation.organization.rolodex.lastName,rolodex.addressLine1:protocolHelper.newProtocolLocation.organization.rolodex.addressLine1,rolodex.addressLine2:protocolHelper.newProtocolLocation.organization.rolodex.addressLine2,rolodex.addressLine3:protocolHelper.newProtocolLocation.organization.rolodex.addressLine3,rolodex.city:protocolHelper.newProtocolLocation.organization.rolodex.city,rolodex.state:protocolHelper.newProtocolLocation.organization.rolodex.state" anchor="${currentTabIndex}"/> 
	                    <kul:directInquiry boClassName="org.kuali.kra.bo.Organization" inquiryParameters="protocolHelper.newProtocolLocation.organizationId:organizationId" anchor="${currentTabIndex}"/>
                		<br />
                		<div id="protocolHelper.organizationName.div" class="fineprint">
	                        <c:if test="${!empty KualiForm.protocolHelper.newProtocolLocation.organizationId}">
	            				<c:choose>
								    <c:when test="${empty KualiForm.protocolHelper.newProtocolLocation.organization}">
		                    			<span style='color: red;'>not found</span>
		               				</c:when>
		                  			<c:otherwise>
											<c:out value="${KualiForm.protocolHelper.newProtocolLocation.organization.organizationName}" />
									</c:otherwise>  
								</c:choose>                        
	                        </c:if>
						</div>
	                </td>
	                <td align="left" valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="protocolHelper.newProtocolLocation.protocolOrganizationTypeCode" attributeEntry="${protocolLocationAttributes.protocolOrganizationTypeCode}" />
	                	</div>
					</td>
	                <td align="left" valign="middle" class="infoline">
	                	<div align="center">
							&nbsp;
	                	</div>
					</td>
	                <td align="left" valign="middle" class="infoline">
	                	<div align="center">
							&nbsp;
	                	</div>
					</td>
					<td class="infoline">
						<div align="center">
							<html:image property="methodToCall.addProtocolLocation.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
						</div>
	                </td>
	            </tr>
            </kra:permission>
            <%-- New data --%>
            
            <%-- Existing data --%>
        	<c:forEach var="protocolLocation" items="${KualiForm.document.protocolList[0].protocolLocations}" varStatus="status">
	             <tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.protocolList[0].protocolLocations[${status.index}].organizationId" readOnly="true" attributeEntry="${protocolLocationAttributes.organizationId}" /> 
                		<html:hidden property="document.protocolList[0].protocolLocations[${status.index}].organizationId" /> 
                    	<kul:directInquiry boClassName="org.kuali.kra.bo.Organization" inquiryParameters="document.protocolList[0].protocolLocations[${status.index}].organizationId:organizationId" anchor="${currentTabIndex}"/> <br>
                		<kul:htmlControlAttribute property="document.protocolList[0].protocolLocations[${status.index}].organization.organizationName" readOnly="true" attributeEntry="${organizationAttributes.organizationName}" />
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.protocolList[0].protocolLocations[${status.index}].protocolOrganizationType.description" readOnly="true" attributeEntry="${protocolOrganizationTypeAttributes.description}" />
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
						<c:if test="${!empty KualiForm.document.protocolList[0].protocolLocations[status.index].rolodex.lastName}">
							${KualiForm.document.protocolList[0].protocolLocations[status.index].rolodex.lastName}, 
						</c:if>
						<c:if test="${!empty KualiForm.document.protocolList[0].protocolLocations[status.index].rolodex.firstName}">
							${KualiForm.document.protocolList[0].protocolLocations[status.index].rolodex.firstName}: 
						</c:if>
						<c:if test="${!empty KualiForm.document.protocolList[0].protocolLocations[status.index].rolodex.addressLine1 || !empty KualiForm.document.protocolList[0].protocolLocations[status.index].rolodex.addressLine2 || !empty KualiForm.document.protocolList[0].protocolLocations[status.index].rolodex.addressLine3}">
							${KualiForm.document.protocolList[0].protocolLocations[status.index].rolodex.addressLine1}
				            ${KualiForm.document.protocolList[0].protocolLocations[status.index].rolodex.addressLine2}
				            ${KualiForm.document.protocolList[0].protocolLocations[status.index].rolodex.addressLine3},
						</c:if>
						<c:if test="${!empty KualiForm.document.protocolList[0].protocolLocations[status.index].rolodex.city || !empty KualiForm.document.protocolList[0].protocolLocations[status.index].rolodex.state || !empty KualiForm.document.protocolList[0].protocolLocations[status.index].rolodex.postalCode}">
							${KualiForm.document.protocolList[0].protocolLocations[status.index].rolodex.city}, &nbsp
				            ${KualiForm.document.protocolList[0].protocolLocations[status.index].rolodex.state} &nbsp
				            ${KualiForm.document.protocolList[0].protocolLocations[status.index].rolodex.postalCode}
						</c:if>
						<kra:permission value="${KualiForm.protocolHelper.modifyProtocol}">  
	                    	<kul:lookup boClassName="org.kuali.kra.bo.Rolodex" 
	                    		fieldConversions="rolodexId:document.protocolList[0].protocolLocations[${status.index}].rolodexId,firstName:document.protocolList[0].protocolLocations[${status.index}].rolodex.firstName,lastName:document.protocolList[0].protocolLocations[${status.index}].rolodex.lastName,postalCode:document.protocolList[0].protocolLocations[${status.index}].rolodex.postalCode,addressLine1:document.protocolList[0].protocolLocations[${status.index}].rolodex.addressLine1,addressLine2:document.protocolList[0].protocolLocations[${status.index}].rolodex.addressLine2,addressLine3:document.protocolList[0].protocolLocations[${status.index}].rolodex.addressLine3,city:document.protocolList[0].protocolLocations[${status.index}].rolodex.city,state:document.protocolList[0].protocolLocations[${status.index}].rolodex.state"	anchor="${currentTabIndex}"/> 
						</kra:permission>  
                    	<kul:directInquiry boClassName="org.kuali.kra.bo.Rolodex" inquiryParameters="document.protocolList[0].protocolLocations[${status.index}].rolodexId:rolodexId" anchor="${currentTabIndex}"/>
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.protocolList[0].protocolLocations[${status.index}].organization.humanSubAssurance" readOnly="true" attributeEntry="${protocolLocationAttributes.sequenceNumber}" />
                		<html:hidden property="document.protocolList[0].protocolLocations[${status.index}].organization.humanSubAssurance"/>
					</div>
				  </td>
				  <c:if test="${!readOnly}">
					  <td>
						<div align=center>&nbsp;
							<html:image property="methodToCall.clearProtocolLocationAddress.line${status.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-clraddress.gif' styleClass="tinybutton"/>
							<html:image property="methodToCall.deleteProtocolLocation.line${status.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						</div>
		              </td>
		           </c:if>
	            </tr>
				<input type="hidden" name="document.protocolList[0].protocolLocations[${status.index}].rolodexId" value="${KualiForm.document.protocolList[0].protocolLocations[status.index].rolodexId}">

        	</c:forEach> 
            <%-- Existing data --%>
        </table>

    </div>
</kul:tab>
