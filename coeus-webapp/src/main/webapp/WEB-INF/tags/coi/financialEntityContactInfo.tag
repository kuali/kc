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
<%@ attribute name="prop" required="true" description="field prefix property" %>
<c:set var="entityContactInfoAttribute" value="${DataDictionary.FinancialEntityContactInfo.attributes}" />
<c:set var="personFinIntDisclAttribute" value="${DataDictionary.PersonFinIntDisclosure.attributes}" />


                                            <%-- contact info --%>
                                             <tr>
                                                <th align="right" valign="middle" >
                                                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.addressLine1}" />
                                                </th>
                                                <td align="left" valign="middle" colspan="3">
                                                    <kul:htmlControlAttribute property="${prop}.finEntityContactInfos[0].addressLine1" 
                                              attributeEntry="${entityContactInfoAttribute.addressLine1}" /> 
                                                </td>
                                                <th align="right" valign="middle" rowspan="1" colspan="2">
                                                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.entitySponsorsResearch}" />
                                                </th>
                                                <td align="left" valign="middle" rowspan="1" colspan="2">
                                                    <kul:htmlControlAttribute property="${prop}.entitySponsorsResearch" 
	                                             		attributeEntry="${personFinIntDisclAttribute.entitySponsorsResearch}" /> 
												</td>
                                            </tr>    
                                             <tr>
                                                <th align="right" valign="middle" >
                                                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.addressLine2}" />
                                                </th>
                                                <td align="left" valign="middle" colspan="3">
                                                    <kul:htmlControlAttribute property="${prop}.finEntityContactInfos[0].addressLine2" 
                                              attributeEntry="${entityContactInfoAttribute.addressLine2}" /> 
                                                </td>
                                                <th align="right" valign="middle" rowspan="4">
                                                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.orgRelationDescription}" />
                                                </th>
                                                <td align="left" valign="middle" rowspan="4" colspan="3">
                                                    <kul:htmlControlAttribute property="${prop}.orgRelationDescription" 
                                              attributeEntry="${personFinIntDisclAttribute.orgRelationDescription}" /> 
                                                </td>                                                
                                            </tr>    
                                             <tr>
                                                <th align="right" valign="middle" >
                                                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.addressLine3}" />
                                                </th>
                                                <td align="left" valign="middle" colspan="3">
                                                    <kul:htmlControlAttribute property="${prop}.finEntityContactInfos[0].addressLine3" 
                                              attributeEntry="${entityContactInfoAttribute.addressLine3}" /> 
                                                </td>
                                            </tr>    
                                             <tr>
                                                <th align="right" valign="middle" >
                                                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.city}" />
                                                </th>
                                                <td align="left" valign="middle" colspan="3">
                                                    <kul:htmlControlAttribute property="${prop}.finEntityContactInfos[0].city" 
                                              attributeEntry="${entityContactInfoAttribute.city}" /> 
                                                </td>
                                            </tr>
                                            <tr>
                                                <th align="right" valign="middle" >
                                                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.state}" />
                                                </th>
                                                <td align="left" valign="middle" colspan="3">
                                                    <kul:htmlControlAttribute property="${prop}.finEntityContactInfos[0].state" 
                                              attributeEntry="${entityContactInfoAttribute.state}" /> 
                                                </td>
                                            </tr>
                                            <tr>    
                                                 <th align="right" valign="middle" >
                                                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.countryCode}" />
                                                </th>
                                                <td align="left" valign="middle" colspan="3">
                                                    <kul:htmlControlAttribute property="${prop}.finEntityContactInfos[0].countryCode" 
                                              attributeEntry="${entityContactInfoAttribute.countryCode}" onchange="updateStateOnClick('${prop}.finEntityContactInfos[0].countryCode');"/> 
                                                </td>
                                               <th align="right" valign="middle" rowspan="4">
                                                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.principalBusinessActivity}" />
                                                </th>
                                                <td align="left" valign="middle" rowspan="4" colspan="3">
                                                    <kul:htmlControlAttribute property="${prop}.principalBusinessActivity" 
                                              attributeEntry="${personFinIntDisclAttribute.principalBusinessActivity}" /> 
                                                </td>                                                
                                            </tr>    
                                             <tr>
                                                <th align="right" valign="middle" >
                                                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.postalCode}" />
                                                </th>
                                                <td align="left" valign="middle" colspan="3">
                                                    <kul:htmlControlAttribute property="${prop}.finEntityContactInfos[0].postalCode" 
                                              attributeEntry="${entityContactInfoAttribute.postalCode}" /> 
                                                </td>
                                           </tr>  
                                           <tr>
                                               <th align="right" valign="middle" >
                                                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.webAddress1}" />
                                                </th>
                                                <td align="left" valign="middle" colspan="3">
                                                    <kul:htmlControlAttribute property="${prop}.finEntityContactInfos[0].webAddress1" 
                                              attributeEntry="${entityContactInfoAttribute.webAddress1}" /> 
                                                </td>
                                           </tr>          
                                           <tr>
                                               <th align="right" valign="middle" >
                                                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.webAddress2}" />
                                                </th>
                                                <td align="left" valign="middle" colspan="3">
                                                    <kul:htmlControlAttribute property="${prop}.finEntityContactInfos[0].webAddress2" 
                                              attributeEntry="${entityContactInfoAttribute.webAddress2}" /> 
                                                </td>
                                           </tr>
                                           
                                           <tr>
                                           		<th style="text-align: right; vertical-align: middle;" colspan="2">
                                           			<kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.studentInvolvement}" />
                                           		</th>
                                           		<td colspan="6">
                                           			<kul:htmlControlAttribute property="${prop}.studentInvolvement" 
                                              			attributeEntry="${personFinIntDisclAttribute.studentInvolvement}" /> 
                                           		</td>
                                           	</tr>
                                           <tr>
                                           		<th style="text-align: right; vertical-align: middle;" colspan="2">
                                           			<kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.staffInvolvement}" />
                                           		</th>
                                           		<td colspan="6">
                                           			<kul:htmlControlAttribute property="${prop}.staffInvolvement" 
                                              			attributeEntry="${personFinIntDisclAttribute.staffInvolvement}" /> 
                                           		</td>
                                           	</tr>
                                           	                                           	                                           <tr>
                                           		<th style="text-align: right; vertical-align: middle;" colspan="2">
                                           			<kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.facilityUse}" />
                                           		</th>
                                           		<td colspan="6">
                                           			<kul:htmlControlAttribute property="${prop}.facilityUse" 
                                              			attributeEntry="${personFinIntDisclAttribute.facilityUse}" /> 
                                           		</td>
                                           	</tr>
                                           	
