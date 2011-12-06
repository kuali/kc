<%--
 Copyright 2005-2010 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="subAwardContactAttributes" value="${DataDictionary.SubAwardContact.attributes}" />
<c:set var="action" value="subAwardHome" />
<c:set var="subAwardContacts" value="${KualiForm.document.subAwardList[0].subAwardContactsList}"/>
<c:set var="newSubAwardContact" value="${KualiForm.newSubAwardContact}" />


<kul:tab tabTitle="Contacts"  transparentBackground="false" defaultOpen="true" tabErrorKey="newSubAwardContact.contactTypeCode*,newSubAwardContact.rolodex.firstName*" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> Contacts </span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.SubAwardContact" altText="help"/></span>
        </h3>
        
        <table cellpadding=0 cellspacing=0 summary="">
            <tr>
              <th><div align="left">&nbsp;</div></th> 
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardContactAttributes.rolodexName}" /></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardContactAttributes.contactTypeCode}" /></div></th>               
                <%-- <c:if test="${canModify}">  --%>
              	    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	    <%-- </c:if> --%>
            </tr>
             <c:if test="${readOnly!='true'}">
            <tr>    
    				<th class="infoline" rowspan="1">
						Add:
					</th>					
     			 
     			<td><div align="center">
     					 <kul:htmlControlAttribute property="newSubAwardContact.rolodex.firstName" readOnly="${readOnly}" attributeEntry="${subAwardContactAttributes.rolodexName}" />
     					  <c:if test="${readOnly!='true'}">
							<kul:lookup boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" 
								fieldConversions="firstName:newSubAwardContact.rolodex.firstName,rolodexId:newSubAwardContact.rolodexId,sponsorCode:newSubAwardContact.rolodex.sponsorCode,organization:newSubAwardContact.rolodex.organization,addressLine1:newSubAwardContact.rolodex.addressLine1,addressLine2:newSubAwardContact.rolodex.addressLine2,addressLine3:newSubAwardContact.rolodex.addressLine3,city:newSubAwardContact.rolodex.city,county:newSubAwardContact.rolodex.county,state:newSubAwardContact.rolodex.state,postalCode:newSubAwardContact.rolodex.postalCode,countryCode:newSubAwardContact.rolodex.countryCode,phoneNumber:newSubAwardContact.rolodex.phoneNumber,emailAddress:newSubAwardContact.rolodex.emailAddress,faxNumber:newSubAwardContact.rolodex.faxNumber,comments:newSubAwardContact.rolodex.comments" 			
          						anchor="${tabKey}"/>    
          				  </c:if>     
   					</div> 
   				</td>
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardContact.contactTypeCode" readOnly="${readOnly}" attributeEntry="${subAwardContactAttributes.contactTypeCode}" />           
   					</div> 
   				</td>   							
   				<td class="infoline" rowspan="1"><div align="center">
   						<c:if test="${readOnly!='true'}">
						<html:image property="methodToCall.addContacts.anchor${tabKey}" 
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
						            styleClass="tinybutton"/>
						</c:if>
	                </div>
	            </td>   				
   			</tr> 
   			</c:if>
   			<c:forEach var="subAwardContacts" items="${KualiForm.document.subAwardList[0].subAwardContactsList}" varStatus="status">
		              <tr>
						<th width="5%" class="infoline" rowspan="2">
							<c:out value="${status.index+1}" />
						</th>	                
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardContactsList[${status.index}].rolodex.firstName" readOnly="true" attributeEntry="${subAwardContactAttributes.rolodexName}" />
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardContactsList[${status.index}].contactTypeCode" attributeEntry="${subAwardContactAttributes.contactTypeCode}"  datePicker="true"/>                		
						</div>
						</td>	               
						<td width="10%" valign="middle" rowspan="1">
						<div align="center">
						  <c:if test="${!readOnly}">
	                		<html:image property="methodToCall.deleteContact.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						  </c:if>
						  <c:if test="${readOnly}">&nbsp;</c:if>
						</div>
						</td>	
		            </tr>		           	            
		            <tr>		            
		            <td colspan="3">
		            
<kul:innerTab tabTitle="Person Details" parentTab="${subAwardContactAttributes.rolodexName}" defaultOpen="false" 
                          useCurrentTabIndexAsKey="true" 
                          tabErrorKey="">	
	
	 <table cellpadding="0" cellspacing="0" summary="Project Personnel Details">
		<tr>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${subAwardContactAttributes.rolodexName}" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.subAwardList[0].subAwardContactsList[${status.index}].rolodex.firstName" attributeEntry="${subAwardContactAttributes.rolodexName}" readOnly="true" />
			</td>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${subAwardContactAttributes.rolodexId}" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.subAwardList[0].subAwardContactsList[${status.index}].rolodexId" attributeEntry="${subAwardContactAttributes.rolodexName}" readOnly="true" />
			</td>
		</tr>
		<tr>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${subAwardContactAttributes.sponsorCode}" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.subAwardList[0].subAwardContactsList[${status.index}].rolodex.sponsorCode" attributeEntry="${subAwardContactAttributes.sponsorCode}" readOnly="true" />
			</td>
			<th class="infoline" >
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${subAwardContactAttributes.organization}" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.subAwardList[0].subAwardContactsList[${status.index}].rolodex.organization" attributeEntry="${subAwardContactAttributes.organization}" readOnly="true" />
			</td>
		</tr>
		<tr>			
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${subAwardContactAttributes.addressLine1}" />
				</div>
			</th>
			<td colspan="3">
				<kul:htmlControlAttribute property="document.subAwardList[0].subAwardContactsList[${status.index}].rolodex.addressLine1" attributeEntry="${subAwardContactAttributes.addressLine1}" readOnly="true" />
			</td>
		</tr>
		<tr>
			<th class="infoline">
				<div align="right">
					
				</div>
			</th>
			<td colspan="3">
				<kul:htmlControlAttribute property="document.subAwardList[0].subAwardContactsList[${status.index}].rolodex.addressLine2" attributeEntry="${subAwardContactAttributes.addressLine2}" readOnly="true" />
			</td>
		</tr>
		<tr>			
			<th class="infoline">
				<div align="right">
					
				</div>
			</th>
			<td colspan="3">
				<kul:htmlControlAttribute property="document.subAwardList[0].subAwardContactsList[${status.index}].rolodex.addressLine3" attributeEntry="${subAwardContactAttributes.addressLine3}" readOnly="true" />
			</td>
		</tr>
		<tr>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${subAwardContactAttributes.city}" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.subAwardList[0].subAwardContactsList[${status.index}].rolodex.city" attributeEntry="${subAwardContactAttributes.city}" readOnly="true" />
			</td>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${subAwardContactAttributes.county}" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.subAwardList[0].subAwardContactsList[${status.index}].rolodex.county" attributeEntry="${subAwardContactAttributes.county}" readOnly="true" />
			</td>
		</tr>	
		<tr>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${subAwardContactAttributes.state}" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.subAwardList[0].subAwardContactsList[${status.index}].rolodex.state" attributeEntry="${subAwardContactAttributes.state}" readOnly="true" />
			</td>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${subAwardContactAttributes.postalCode}" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.subAwardList[0].subAwardContactsList[${status.index}].rolodex.postalCode" attributeEntry="${subAwardContactAttributes.postalCode}" readOnly="true" />
			</td>
		</tr>
		<tr>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${subAwardContactAttributes.countryCode}" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.subAwardList[0].subAwardContactsList[${status.index}].rolodex.countryCode" attributeEntry="${subAwardContactAttributes.countryCode}" readOnly="true" />
			</td>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${subAwardContactAttributes.phoneNumber}" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.subAwardList[0].subAwardContactsList[${status.index}].rolodex.phoneNumber" attributeEntry="${subAwardContactAttributes.phoneNumber}" readOnly="true" />
			</td>
		</tr>	
		<tr>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${subAwardContactAttributes.emailAddress}" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.subAwardList[0].subAwardContactsList[${status.index}].rolodex.emailAddress" attributeEntry="${subAwardContactAttributes.emailAddress}" readOnly="true" />
			</td>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${subAwardContactAttributes. faxNumber}" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.subAwardList[0].subAwardContactsList[${status.index}].rolodex.faxNumber" attributeEntry="${subAwardContactAttributes. faxNumber}" readOnly="true" />
			</td>
		</tr>
		<tr>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${subAwardContactAttributes.comments}" />
				</div>
			</th>
			<td colspan="3">
				<kul:htmlControlAttribute property="document.subAwardList[0].subAwardContactsList[${status.index}].rolodex.comments" attributeEntry="${subAwardContactAttributes.comments}" readOnly="true" />
			</td>			
		</tr>	   		   	  	  	  	            				
	</table> 
  </kul:innerTab>
  
        </td>
       </tr>     
	 </c:forEach>
   </table>
  </div>
</kul:tab>
