<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
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

<%@ attribute name="personIndex" description="Index of a ProposalPerson" required="true" %>
<%@ attribute name="protocolPerson" description="Index of a Protocol person" required="true" %>

<c:set var="protocolPersonAttributes" value="${DataDictionary.ProtocolPerson.attributes}" />
<c:set var="personAttributes" value="${DataDictionary.KcPerson.attributes}" />
<c:set var="protocolUnitsAttributes" value="${DataDictionary.ProtocolUnit.attributes}" />
<c:set var="unitAttributes" value="${DataDictionary.Unit.attributes}" />
<c:set var="protocolAttachmentPersonnelAttributes" value="${DataDictionary.ProtocolAttachmentPersonnel.attributes}" />
<c:set var="attachmentFileAttributes" value="${DataDictionary.AttachmentFile.attributes}" />
<c:set var="viewOnly" value="${KualiForm.editingMode['viewOnly']}" />

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
        					 <tbody class="addline">
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
			                    	<kul:lookup boClassName="org.kuali.coeus.common.framework.unit.Unit" fieldConversions="unitNumber:personnelHelper.newProtocolPersonUnits[${personIndex}].unitNumber,unitName:personnelHelper.newProtocolPersonUnits[${personIndex}].unitName" />
			                    	<span class="fineprint"></span> 
								</td>
				                <td align="left" valign="middle" class="infoline">
									<div align=left>
			                    		<kul:htmlControlAttribute property="personnelHelper.newProtocolPersonUnits[${personIndex}].unitNumber"
			                    			attributeEntry="${unitAttributes.unitNumber}" 
			                    		    onblur="ajaxLoad('getUnitName','personnelHelper.newProtocolPersonUnits[${personIndex}].unitNumber', 'personnelHelper.newProtocolPersonUnits[${personIndex}].unitName');" 
			                    		/>
			                      	</div>
			                        <span class="fineprint"></span> 
								</td>
                                <c:if test="${KualiForm.document.protocolList[0].protocolPersons[personIndex].principalInvestigator}">
				                    <td align="left" valign="middle" class="infoline">
									    <div align=center class="ignoreMeFromWarningOnAddRow">
										    <bean:define id="leadFlag" name="KualiForm" property="personnelHelper.newProtocolPersonUnits[${personIndex}].leadUnitFlag" />
										    <html:checkbox property="personnelHelper.newProtocolPersonUnits[${personIndex}].leadUnitFlag" title="Add this unit as lead" />
			                      	    </div>
								    </td>
                                </c:if>    
			                    <td class="infoline">
									<div align=center>
										<html:image property="methodToCall.addProtocolPersonUnit.${protocolPerson}.line${status.index}" 
										src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" title="Add Unit" alt="Add Unit" styleClass="tinybutton addButton"/></div>
								</td>
				            </tr>
				            </tbody>
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


