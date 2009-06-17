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
<%@ include file="/WEB-INF/jsp/irb/ProtocolPerson.jsp"%>
<c:choose>
	<c:when test="${empty KualiForm.document.protocolList[0].protocolPersons[personIndex].personName}">
		<c:set var="parentTabName" value="" />
	</c:when>
	<c:otherwise>
		<bean:define id="parentTabName" name="KualiForm" property="${protocolPerson}.personName"/>
	</c:otherwise>
</c:choose>
<bean:define id="protocolPersonUnits" name="KualiForm" property="${protocolPerson}.protocolUnits" />
<c:set var="readOnly" value="${!KualiForm.personnelHelper.modifyProtocol}" />
<table cellpadding=0 cellspacing=0 summary="">
 	<tr>
		<td>
			<kul:innerTab tabTitle="Unit Details" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="personnelHelper.newProtocolPersonUnits[${personIndex}]*">
				<div class="innerTab-container" align="left">
			        <table class=tab cellpadding="0" cellspacing="0" summary="">
              			<tbody id="G3">
			          	<%-- Header --%>
			          	<tr>
			          		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" /> 
			          		<kul:htmlAttributeHeaderCell attributeEntry="${unitAttributes.unitName}" scope="col" align="center"/>
			          		<kul:htmlAttributeHeaderCell attributeEntry="${protocolUnitsAttributes.unitNumber}" scope="col" align="center"/>
			          		<kul:htmlAttributeHeaderCell attributeEntry="${protocolUnitsAttributes.leadUnitFlag}" scope="col" align="center"/>
          					<c:if test="${!readOnly}">
			          			<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" align="center"/>
							</c:if>
			          	</tr> 
			          	<%-- Header --%>
			          	
			             <%-- New data --%>
        				<kra:permission value="${KualiForm.personnelHelper.modifyProtocol}">
				             <tr>
								<th class="infoline">
									<c:out value="Add:" />
								</th>
				                <td align="left" valign="middle" class="infoline">
			   						<div id="newProtocolPersonUnits[${personIndex}].unitName.div" class="same-line">
			                    		<c:choose>
			                    			<c:when test="${empty KualiForm.personnelHelper.newProtocolPersonUnits[personIndex].unitName}" >
			                        			(select)
			                      			</c:when>
			                      			<c:otherwise>
			                         			${KualiForm.personnelHelper.newProtocolPersonUnits[personIndex].unitName}
			                      			</c:otherwise>
			                    		</c:choose> 
			                    	</div>
			                    	&nbsp; <kul:lookup boClassName="org.kuali.kra.bo.Unit" fieldConversions="unitNumber:personnelHelper.newProtocolPersonUnits[${personIndex}].unitNumber,unitName:personnelHelper.newProtocolPersonUnits[${personIndex}].unitName" />
			                    	<span class="fineprint"></span> 
								</td>
				                <td align="left" valign="middle" class="infoline">
									<div align=left>
			                    		<kul:htmlControlAttribute attributeEntry="${unitAttributes.unitNumber}" property="personnelHelper.newProtocolPersonUnits[${personIndex}].unitNumber" />
			                      	</div>
			                        <span class="fineprint"></span> 
								</td>
				                <td align="left" valign="middle" class="infoline">
									<div align=center>
										<bean:define id="leadFlag" name="KualiForm" property="personnelHelper.newProtocolPersonUnits[${personIndex}].leadUnitFlag" />
										<html:radio property="personnelHelper.newProtocolPersonUnits[${personIndex}].leadUnitFlag" value="true"/>
			                      	</div>
								</td>
			                    <td class="infoline">
									<div align=center>
										<html:image property="methodToCall.addProtocolPersonUnit.${protocolPerson}.line${status.index}" 
										src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" title="Add Unit" alt="Add Unit" styleClass="tinybutton"/></div>
								</td>
				            </tr>
						</kra:permission>
			            <%-- New data --%>
			            
			            <%-- Existing data --%>
			        	<c:forEach var="protocolUnits" items="${protocolPersonUnits}" varStatus="status">
				             <tr>
								<th class="infoline">
									<c:out value="${status.index+1}" />
								</th>
			                  <td align="left" valign="middle">
								<div align="left">
			                		<kul:htmlControlAttribute property="${protocolPerson}.protocolUnits[${status.index}].unit.unitName" attributeEntry="${unitAttributes.unitName}"  readOnly="true" />
								</div>
							  </td>
			                  <td align="left" valign="middle">
								<div align="left">
			                		<kul:htmlControlAttribute property="${protocolPerson}.protocolUnits[${status.index}].unitNumber" attributeEntry="${unitAttributes.unitNumber}"  readOnly="true" />
								</div>
							  </td>
			                  <td align="left" valign="middle">
								<div align="center">
									<html:radio property="${protocolPerson}.selectedUnit" value="${status.index}" disabled="${readOnly}"/>
								</div>
							  </td>
				  			  <c:if test="${!readOnly}">
								  <td class="infoline">
									<div align="center">
										<html:image property="methodToCall.deleteProtocolPersonUnit.${protocolPerson}.line${status.index}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
									</div>
				                  </td>
		           			</c:if>
				            </tr>
			        	</c:forEach>
			            <%-- Existing data --%>
     					</tbody>
			        </table>
				</div>
			</kul:innerTab>
		</td>
	</tr>
</table>


