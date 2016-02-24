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

<bean:define id="personEditableFields" name="KualiForm" property="personEditableFields" />  

<c:choose>
	<c:when test="${empty KualiForm.document.protocolList[0].protocolPersons[personIndex].personName}">
		<c:set var="parentTabName" value="" />
	</c:when>
	<c:otherwise>
		<bean:define id="parentTabName" name="KualiForm" property="${protocolPerson}.personName"/>
	</c:otherwise>
</c:choose>
	<c:set var="viewOnly" value="${not KualiForm.personnelHelper.modifyPersonnel}" scope="request"/>

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
		                                                      readOnly="${viewOnly || !personEditableFields['fullName']}"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.userName}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${protocolPerson}.userName" 
		                    		attributeEntry="${personAttributes.userName}" readOnly="${viewOnly || !personEditableFields['userName'] }"/>
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
		                                                      readOnly="${viewOnly || !personEditableFields['emailAddress'] }"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.officePhone}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${protocolPerson}.officePhone" 
		                    		attributeEntry="${personAttributes.officePhone}" readOnly="${viewOnly || !personEditableFields['officePhone'] }"/>
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
		                                                      readOnly="${viewOnly || !personEditableFields['primaryTitle'] }"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes['extendedAttributes.directoryTitle']}"/>
									</div>
								</th>
		                    	<td width="30%">
		                    		<kul:htmlControlAttribute property="${protocolPerson}.directoryTitle" 
		                    		attributeEntry="${personAttributes['extendedAttributes.directoryTitle']}" readOnly="${viewOnly || !personEditableFields['directoryTitle'] }"/>
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
		                                                      readOnly="${viewOnly || !personEditableFields['homeUnit'] }"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes['extendedAttributes.school']}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${protocolPerson}.school" 
		                    		attributeEntry="${personAttributes['extendedAttributes.school']}" readOnly="${viewOnly || !personEditableFields['school'] }"/>
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
		                                                      readOnly="${viewOnly || !personEditableFields['eraCommonsUserName'] }"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.faxNumber}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${protocolPerson}.faxNumber" 
		                    		attributeEntry="${personAttributes.faxNumber}" readOnly="${viewOnly || !personEditableFields['faxNumber'] }"/>
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
		                                                      readOnly="${viewOnly || !personEditableFields['pagerNumber'] }"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.mobilePhoneNumber}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${protocolPerson}.mobilePhoneNumber" 
		                    		attributeEntry="${personAttributes.mobilePhoneNumber}" readOnly="${viewOnly || !personEditableFields['mobilePhoneNumber'] }"/>
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
		                                                      readOnly="${viewOnly || !personEditableFields['officeLocation'] }"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes['extendedAttributes.secondaryOfficeLocation']}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${protocolPerson}.secondaryOfficeLocation" 
		                    		attributeEntry="${personAttributes['extendedAttributes.secondaryOfficeLocation']}" readOnly="${viewOnly || !personEditableFields['secondaryOfficeLocation'] }"/>
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
		                                                      readOnly="${viewOnly || !personEditableFields['addressLine1'] }"/> 
		                    	</td>
		                    	<th> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.addressLine2}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${protocolPerson}.addressLine2" 
		                      								  attributeEntry="${personAttributes.addressLine2}" 
		                                                      readOnly="${viewOnly || !personEditableFields['addressLine2'] }"/> 
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
		                                                      readOnly="${viewOnly || !personEditableFields['addressLine3'] }"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.city}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${protocolPerson}.city" 
		                    		attributeEntry="${personAttributes.city}" readOnly="${viewOnly || !personEditableFields['city'] }"/>
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
		                    		attributeEntry="${personAttributes['extendedAttributes.county']}" readOnly="${viewOnly || !personEditableFields['county'] }"/>
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.state}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${protocolPerson}.state" 
		                    		attributeEntry="${personAttributes.state}" readOnly="${viewOnly || !personEditableFields['state'] }"/>
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
		                                                      readOnly="${viewOnly || !personEditableFields['postalCode'] }"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.countryCode}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${protocolPerson}.countryCode" 
		                    		attributeEntry="${personAttributes.countryCode}" readOnly="${viewOnly || !personEditableFields['countryCode'] }"/>
		                    	</td>
		                  	</tr>

     					</tbody>
					</table>
				</div>
			</kul:innerTab>
		</td>
	</tr>
</table>
