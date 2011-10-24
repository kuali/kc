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
<c:set var="readOnly" value="${!KualiForm.personnelHelper.modifyPersonnel}" />
<table cellpadding=0 cellspacing=0 summary="">
 	<tr>
		<td>
			<kul:innerTab tabTitle="Unit Details" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="personnelHelper.newProtocolPersonUnits[${personIndex}]*" useCurrentTabIndexAsKey="true">
				<div class="innerTab-container" align="left">
			        <table class=tab cellpadding="0" cellspacing="0" summary="">
              			<tbody id="G3">
			          	<%-- Header --%>
			          	<tr>
			          		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" /> 
			          		<kul:htmlAttributeHeaderCell attributeEntry="${unitAttributes.unitName}" scope="col" align="center"/>
			          		<kul:htmlAttributeHeaderCell attributeEntry="${protocolUnitsAttributes.unitNumber}" scope="col" align="center"/>
			          		<c:if test="${KualiForm.document.protocolList[0].protocolPersons[personIndex].principalInvestigator}">
                                <kul:htmlAttributeHeaderCell attributeEntry="${protocolUnitsAttributes.leadUnitFlag}" scope="col" align="center"/>
                            </c:if>
          					<c:if test="${!readOnly}">
			          			<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" align="center"/>
							</c:if>
			          	</tr> 
			          	<%-- Header --%>
			          	
			             <%-- New data --%>
        				<kra:permission value="${KualiForm.personnelHelper.modifyPersonnel}">
				             <tr>
								<th class="infoline">
									<c:out value="Add:" />
								</th>
				                <td align="left" valign="middle" class="infoline">
			   						<div id="personnelHelper.newProtocolPersonUnits[${personIndex}].unitName.div" class="same-line">
                                        <kul:htmlControlAttribute property="personnelHelper.newProtocolPersonUnits[${personIndex}].unitName"
                                                                  attributeEntry="${unitAttributes.unitName}" readOnly="true"
                                                                  readOnlyBody="${empty KualiForm.personnelHelper.newProtocolPersonUnits[personIndex].unitName}">
                                            (select)
                                        </kul:htmlControlAttribute>
			                    	</div>
			                    	&nbsp;
			                    	<kul:lookup boClassName="org.kuali.kra.bo.Unit" fieldConversions="unitNumber:personnelHelper.newProtocolPersonUnits[${personIndex}].unitNumber,unitName:personnelHelper.newProtocolPersonUnits[${personIndex}].unitName" />
			                    	<span class="fineprint"></span> 
								</td>
				                <td align="left" valign="middle" class="infoline">
									<div align=left>
			                    		<kul:htmlControlAttribute property="personnelHelper.newProtocolPersonUnits[${personIndex}].unitNumber"
			                    			attributeEntry="${unitAttributes.unitNumber}" 
			                    		    onblur="ajaxLoad('getUnitName','personnelHelper.newProtocolPersonUnits[${personIndex}].unitNumber', 'personnelHelper.newProtocolPersonUnits[${personIndex}].unitName');" 
			                    		/>
			                    		<%--   onblur="loadUnitNameTo('personnelHelper.newProtocolPersonUnits[${personIndex}].unitNumber','personnelHelper.newProtocolPersonUnits[${personIndex}].unitName');" />  --%>
			                      	</div>
			                        <span class="fineprint"></span> 
								</td>
                                <c:if test="${KualiForm.document.protocolList[0].protocolPersons[personIndex].principalInvestigator}">
				                    <td align="left" valign="middle" class="infoline">
									    <div align=center>
										    <bean:define id="leadFlag" name="KualiForm" property="personnelHelper.newProtocolPersonUnits[${personIndex}].leadUnitFlag" />
										    <html:checkbox property="personnelHelper.newProtocolPersonUnits[${personIndex}].leadUnitFlag" title="Add this unit as lead" />
			                      	    </div>
								    </td>
                                </c:if>    
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
                              <c:if test="${KualiForm.document.protocolList[0].protocolPersons[personIndex].principalInvestigator}">
    			                  <td align="left" valign="middle">
	    							<div align="center">
		    							<html:radio property="${protocolPerson}.selectedUnit" value="${status.index}" disabled="${readOnly}"/>
			    					</div>
				    			  </td>
                              </c:if>
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


