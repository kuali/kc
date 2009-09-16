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
<c:choose>
	<c:when test="${!empty KualiForm.document.protocolList[0].protocolPersons[personIndex].personId}">
		<c:set var="person" value="${protocolPerson}.person" />
	</c:when>
	<c:otherwise>
		<c:set var="person" value="${protocolPerson}.rolodex" />
	</c:otherwise>
</c:choose>

<table cellpadding=0 cellspacing=0 summary="">
 	<tr>
		<td>
			<kra:innerTab tabTitle="Contact Information" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="" useCurrentTabIndexAsKey="true">
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
		                      		<kul:htmlControlAttribute property="${person}.fullName" 
		                      								  attributeEntry="${personAttributes.fullName}" 
		                                                      readOnly="true"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.userName}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${person}.userName" 
		                    		attributeEntry="${personAttributes.userName}" readOnly="true"/>
		                    	</td>
		                  	</tr>
		                	<tr>
		                    	<th nowrap="nowrap"> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.emailAddress}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${person}.emailAddress" 
		                      								  attributeEntry="${personAttributes.emailAddress}" 
		                                                      readOnly="true"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.officePhone}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${person}.officePhone" 
		                    		attributeEntry="${personAttributes.officePhone}" readOnly="true"/>
		                    	</td>
		                  	</tr>
		                	<tr>
		                    	<th nowrap="nowrap"> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.primaryTitle}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${person}.primaryTitle" 
		                      								  attributeEntry="${personAttributes.primaryTitle}" 
		                                                      readOnly="true"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.directoryTitle}"/>
									</div>
								</th>
		                    	<td width="30%">
		                    		<kul:htmlControlAttribute property="${person}.directoryTitle" 
		                    		attributeEntry="${personAttributes.directoryTitle}" readOnly="true"/>
		                    	</td>
		                  	</tr>
		                	<tr>
		                    	<th nowrap="nowrap"> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.homeUnit}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${person}.homeUnit" 
		                      								  attributeEntry="${personAttributes.homeUnit}" 
		                                                      readOnly="true"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.school}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${person}.school" 
		                    		attributeEntry="${personAttributes.school}" readOnly="true"/>
		                    	</td>
		                  	</tr>
		                	<tr>
		                    	<th> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.eraCommonsUserName}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${person}.eraCommonsUserName" 
		                      								  attributeEntry="${personAttributes.eraCommonsUserName}" 
		                                                      readOnly="true"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.faxNumber}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${person}.faxNumber" 
		                    		attributeEntry="${personAttributes.faxNumber}" readOnly="true"/>
		                    	</td>
		                  	</tr>
		                	<tr>
		                    	<th> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.pagerNumber}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${person}.pagerNumber" 
		                      								  attributeEntry="${personAttributes.pagerNumber}" 
		                                                      readOnly="true"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.mobilePhoneNumber}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${person}.mobilePhoneNumber" 
		                    		attributeEntry="${personAttributes.mobilePhoneNumber}" readOnly="true"/>
		                    	</td>
		                  	</tr>
		                	<tr>
		                    	<th> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.officeLocation}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${person}.officeLocation" 
		                      								  attributeEntry="${personAttributes.officeLocation}" 
		                                                      readOnly="true"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.secondaryOfficeLocation}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${person}.secondaryOfficeLocation" 
		                    		attributeEntry="${personAttributes.secondaryOfficeLocation}" readOnly="true"/>
		                    	</td>
		                  	</tr>
		                	<tr>
		                    	<th> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.addressLine1}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${person}.addressLine1" 
		                      								  attributeEntry="${personAttributes.addressLine1}" 
		                                                      readOnly="true"/> 
		                    	</td>
		                    	<th> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.addressLine2}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${person}.addressLine2" 
		                      								  attributeEntry="${personAttributes.addressLine2}" 
		                                                      readOnly="true"/> 
		                    	</td>
		                  	</tr>
		                	<tr>
		                    	<th> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.addressLine3}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${person}.addressLine3" 
		                      								  attributeEntry="${personAttributes.addressLine3}" 
		                                                      readOnly="true"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.city}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${person}.city" 
		                    		attributeEntry="${personAttributes.city}" readOnly="true"/>
		                    	</td>
		                  	</tr>
		                	<tr>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.county}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${person}.county" 
		                    		attributeEntry="${personAttributes.county}" readOnly="true"/>
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.state}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${person}.state" 
		                    		attributeEntry="${personAttributes.state}" readOnly="true"/>
		                    	</td>
		                  	</tr>
		                	<tr>
		                    	<th> 
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.postalCode}"/>
									</div>
								</th>
		                    	<td>
		                      		<kul:htmlControlAttribute property="${person}.postalCode" 
		                      								  attributeEntry="${personAttributes.postalCode}" 
		                                                      readOnly="true"/> 
		                    	</td>
		                    	<th width="15%">
									<div align="right">
										<kul:htmlAttributeLabel attributeEntry="${personAttributes.countryCode}"/>
									</div>
								</th>
		                    	<td align="left" width="30%">
		                    		<kul:htmlControlAttribute property="${person}.countryCode" 
		                    		attributeEntry="${personAttributes.countryCode}" readOnly="true"/>
		                    	</td>
		                  	</tr>

     					</tbody>
					</table>
				</div>
			</kra:innerTab>
		</td>
	</tr>
</table>
