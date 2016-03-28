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

<jsp:useBean id="paramMap" class="java.util.HashMap"/>
<c:set target="${paramMap}" property="sourceRoleId" value="${KualiForm.document.protocolList[0].protocolPersons[personIndex].protocolPersonRoleId}" />
<c:choose>
	<c:when test="${empty KualiForm.document.protocolList[0].protocolPersons[personIndex].personName}">
		<c:set var="parentTabName" value="" />
	</c:when>
	<c:otherwise>
		<bean:define id="parentTabName" name="KualiForm" property="${protocolPerson}.personName"/>
	</c:otherwise>
</c:choose>
<c:set var="personAffiliationRequired" value="${KualiForm.document.protocolList[0].protocolPersons[personIndex].protocolPersonRole.affiliationDetailsRequired}" />
<c:set var="personTrainingRequired" value="${KualiForm.document.protocolList[0].protocolPersons[personIndex].protocolPersonRole.trainingDetailsRequired}" />
<c:set var="commentsRequired" value="${KualiForm.document.protocolList[0].protocolPersons[personIndex].protocolPersonRole.commentsDetailsRequired}" />
<c:set var="readOnly" value="${!KualiForm.personnelHelper.modifyPersonnel}" />
<table cellpadding=0 cellspacing=0 summary="">
	<tr>
		<td>
			<kul:innerTab tabTitle="Person Details" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="" useCurrentTabIndexAsKey="true">
				<div class="innerTab-container" align="left">
            				<table class=tab cellpadding=0 cellspacing="0" summary=""> 
              				<tbody id="G1">
                				<tr>
                  				<th> 
								<div align="right">
									<kul:htmlAttributeLabel attributeEntry="${protocolPersonAttributes.protocolPersonRoleId}" />
								</div>
								</th>
                  				<td colspan="3">

									<c:choose>
									    <c:when test="${readOnly}">
									        ${KualiForm.document.protocolList[0].protocolPersons[personIndex].protocolPersonRole.description}
									    </c:when>
									    <c:otherwise>
							                <html:select property="${protocolPerson}.protocolPersonRoleId" tabindex="0">
							                <c:forEach items="${krafn:getOptionList('org.kuali.kra.irb.personnel.ProtocolPersonRoleValuesFinder', paramMap)}" var="option">
							                <c:choose>
							                    <c:when test="${KualiForm.document.protocol.protocolPersons[personIndex].protocolPersonRoleId == option.key}">
							                    <option value="${option.key}" selected>${option.value}</option>
							                    </c:when>
							                    <c:otherwise>
							                    <option value="${option.key}">${option.value}</option>
							                    </c:otherwise>
							                </c:choose>
							                </c:forEach>
							                </html:select>
		            						<html:image property="methodToCall.updateProtocolPersonView.${protocolPerson}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-updateview.gif" title="Update View" alt="Update View" styleClass="tinybutton"/>
	    								</c:otherwise>
									</c:choose>
                   				</td>
                				</tr>  
            
    							<c:if test="${personAffiliationRequired}">
	                				<tr>
	                  				<th> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${protocolPersonAttributes.affiliationTypeCode}" />
									</div>
									</th>
	                  				<td colspan="3">
										<c:choose>
										    <c:when test="${readOnly}">
										        ${KualiForm.document.protocolList[0].protocolPersons[personIndex].affiliationType.description}
										    </c:when>
										    <c:otherwise>
		              							<kul:htmlControlAttribute property="${protocolPerson}.affiliationTypeCode" attributeEntry="${protocolPersonAttributes.affiliationTypeCode}" readOnly="${readOnly}"/>
											</c:otherwise>
										</c:choose>
	                   				</td>
	                				</tr>
								</c:if>

    							<c:if test="${personTrainingRequired && KualiForm.personnelHelper.personTrainingSectionRequired}">
	                				<tr>
	                  					<th> 
											<div align="right">
												<kul:htmlAttributeLabel attributeEntry="${protocolPersonAttributes.trained}" />
											</div>
										</th>
	                  					<td colspan="3">
	              							<kul:htmlControlAttribute property="${protocolPerson}.trained" attributeEntry="${protocolPersonAttributes.trained}" readOnly="true"/>
	                   					</td>
	                				</tr>              
    							</c:if> 
                                
                                <c:if test="${commentsRequired}">
                                    <tr>
                                    <th>
                                    <div align="right">
                                        <kul:htmlAttributeLabel attributeEntry="${protocolPersonAttributes.comments}" />
                                    </div>
                                    </th>
                                    <td colspan="3">
                                         <kul:htmlControlAttribute property="${protocolPerson}.comments" attributeEntry="${protocolPersonAttributes.comments}" readOnly="${readOnly}"/>
                                    </td>
                                    </tr>
                                </c:if>
     						</tbody>
					</table>
				</div>
			</kul:innerTab>
		</td>
	</tr>
</table>
