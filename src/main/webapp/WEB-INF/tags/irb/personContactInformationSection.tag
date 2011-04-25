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
<bean:define id="personEditableFields" name="KualiForm" property="personEditableFields" />  

<c:choose>
	<c:when test="${empty KualiForm.document.protocolList[0].protocolPersons[personIndex].personName}">
		<c:set var="parentTabName" value="" />
	</c:when>
	<c:otherwise>
		<bean:define id="parentTabName" name="KualiForm" property="${protocolPerson}.personName"/>
	</c:otherwise>
</c:choose>

<table cellpadding=0 cellspacing=0 summary="">
 	<tr>
		<td>
			<kul:innerTab tabTitle="Contact Information" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="" useCurrentTabIndexAsKey="true">
				<div class="innerTab-container" align="left">
            		<table class=tab cellpadding=0 cellspacing="0" summary=""> 
              			<tbody id="G2">
		                	<tr>
		                    	<th> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.fullName}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${protocolPerson}.fullName" 
		                      								  attributeEntry="${personAttributes.fullName}" 
		                                                      readOnly="${!personEditableFields['fullName'] }"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.userName}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${protocolPerson}.userName" 
		                    		attributeEntry="${personAttributes.userName}" readOnly="${!personEditableFields['userName'] }"/>
		                    	</td>
		                  	</tr>
		                	<tr>
		                    	<th nowrap="nowrap"> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.emailAddress}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${protocolPerson}.emailAddress" 
		                      								  attributeEntry="${personAttributes.emailAddress}" 
		                                                      readOnly="${!personEditableFields['emailAddress'] }"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.officePhone}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${protocolPerson}.officePhone" 
		                    		attributeEntry="${personAttributes.officePhone}" readOnly="${!personEditableFields['officePhone'] }"/>
		                    	</td>
		                  	</tr>
		                	<tr>
		                    	<th nowrap="nowrap"> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes['extendedAttributes.primaryTitle']}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${protocolPerson}.primaryTitle" 
		                      								  attributeEntry="${personAttributes['extendedAttributes.primaryTitle']}" 
		                                                      readOnly="${!personEditableFields['primaryTitle'] }"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes['extendedAttributes.directoryTitle']}"/>
									</div>
								</th>
		                    	<td width="30%">
		                    		<kul:htmlControlAttribute property="${protocolPerson}.directoryTitle" 
		                    		attributeEntry="${personAttributes['extendedAttributes.directoryTitle']}" readOnly="${!personEditableFields['directoryTitle'] }"/>
		                    	</td>
		                  	</tr>
		                	<tr>
		                    	<th nowrap="nowrap"> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.organizationIdentifier}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${protocolPerson}.homeUnit" 
		                      								  attributeEntry="${personAttributes.organizationIdentifier}" 
		                                                      readOnly="${!personEditableFields['homeUnit'] }"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes['extendedAttributes.school']}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${protocolPerson}.school" 
		                    		attributeEntry="${personAttributes['extendedAttributes.school']}" readOnly="${!personEditableFields['school'] }"/>
		                    	</td>
		                  	</tr>
		                	<tr>
		                    	<th> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.eraCommonsUserName}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${protocolPerson}.eraCommonsUserName" 
		                      								  attributeEntry="${personAttributes.eraCommonsUserName}" 
		                                                      readOnly="${!personEditableFields['eraCommonsUserName'] }"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.faxNumber}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${protocolPerson}.faxNumber" 
		                    		attributeEntry="${personAttributes.faxNumber}" readOnly="${!personEditableFields['faxNumber'] }"/>
		                    	</td>
		                  	</tr>
		                	<tr>
		                    	<th> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.pagerNumber}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${protocolPerson}.pagerNumber" 
		                      								  attributeEntry="${personAttributes.pagerNumber}" 
		                                                      readOnly="${!personEditableFields['pagerNumber'] }"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.mobilePhoneNumber}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${protocolPerson}.mobilePhoneNumber" 
		                    		attributeEntry="${personAttributes.mobilePhoneNumber}" readOnly="${!personEditableFields['mobilePhoneNumber'] }"/>
		                    	</td>
		                  	</tr>
		                	<tr>
		                    	<th> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes['extendedAttributes.officeLocation']}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${protocolPerson}.officeLocation" 
		                      								  attributeEntry="${personAttributes['extendedAttributes.officeLocation']}" 
		                                                      readOnly="${!personEditableFields['officeLocation'] }"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes['extendedAttributes.secondaryOfficeLocation']}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${protocolPerson}.secondaryOfficeLocation" 
		                    		attributeEntry="${personAttributes['extendedAttributes.secondaryOfficeLocation']}" readOnly="${!personEditableFields['secondaryOfficeLocation'] }"/>
		                    	</td>
		                  	</tr>
		                	<tr>
		                    	<th> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.addressLine1}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${protocolPerson}.addressLine1" 
		                      								  attributeEntry="${personAttributes.addressLine1}" 
		                                                      readOnly="${!personEditableFields['addressLine1'] }"/> 
		                    	</td>
		                    	<th> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.addressLine2}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${protocolPerson}.addressLine2" 
		                      								  attributeEntry="${personAttributes.addressLine2}" 
		                                                      readOnly="${!personEditableFields['addressLine2'] }"/> 
		                    	</td>
		                  	</tr>
		                	<tr>
		                    	<th> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.addressLine3}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${protocolPerson}.addressLine3" 
		                      								  attributeEntry="${personAttributes.addressLine3}" 
		                                                      readOnly="${!personEditableFields['addressLine3'] }"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.city}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${protocolPerson}.city" 
		                    		attributeEntry="${personAttributes.city}" readOnly="${!personEditableFields['city'] }"/>
		                    	</td>
		                  	</tr>
		                	<tr>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes['extendedAttributes.county']}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${protocolPerson}.county" 
		                    		attributeEntry="${personAttributes['extendedAttributes.county']}" readOnly="${!personEditableFields['county'] }"/>
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.state}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${protocolPerson}.state" 
		                    		attributeEntry="${personAttributes.state}" readOnly="${!personEditableFields['state'] }"/>
		                    	</td>
		                  	</tr>
		                	<tr>
		                    	<th> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.postalCode}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${protocolPerson}.postalCode" 
		                      								  attributeEntry="${personAttributes.postalCode}" 
		                                                      readOnly="${!personEditableFields['postalCode'] }"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.countryCode}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${protocolPerson}.countryCode" 
		                    		attributeEntry="${personAttributes.countryCode}" readOnly="${!personEditableFields['countryCode'] }"/>
		                    	</td>
		                  	</tr>

     					</tbody>
					</table>
				</div>
			</kul:innerTab>
		</td>
	</tr>
</table>
