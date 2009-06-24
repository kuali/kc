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
<c:set var="readOnly" value="${!KualiForm.personnelHelper.modifyPersonnel}" />

<table cellpadding=0 cellspacing=0 summary="">
	<tr>
		<td>
			<kul:innerTab tabTitle="Person Details" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="">
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
							                    <option value="${option.key}" selected>${option.label}</option>
							                    </c:when>
							                    <c:otherwise>
							                    <option value="${option.key}">${option.label}</option>
							                    </c:otherwise>
							                </c:choose>
							                </c:forEach>
							                </html:select>
		            						<html:image property="methodToCall.updateProtocolPersonView.${protocolPerson}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-updateview.gif" title="Update View" alt="Update View" styleClass="tinybutton"/>
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
     						</tbody>
					</table>
				</div>
			</kul:innerTab>
		</td>
	</tr>
</table>
