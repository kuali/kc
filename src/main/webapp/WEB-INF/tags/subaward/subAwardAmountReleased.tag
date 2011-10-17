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

<c:set var="subAwardAmountReleasedAttributes" value="${DataDictionary.SubAwardAmountReleased.attributes}" />
<c:set var="action" value="subAwardAmountReleased" />
<c:set var="newSubAwardAmountReleased" value="${KualiForm.newSubAwardAmountReleased}" />
<kul:tab tabTitle="SubAwardAmountReleased" defaultOpen="true" transparentBackground="true" tabErrorKey="" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> SubAward Amount Released</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.SubAwardAmountReleased" altText="help"/></span>
        </h3>
        
      <table cellpadding=0 cellspacing=0 summary="">
            <tr>
              <th><div align="left">&nbsp;</div></th> 
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountReleasedAttributes.invoiceNumber}" /></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountReleasedAttributes.startDate}" /></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountReleasedAttributes.endDate}" /></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountReleasedAttributes.fileName}" /></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountReleasedAttributes.effectiveDate}" /></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountReleasedAttributes.amountReleased}" /></div></th>
  
               
                <%-- <c:if test="${canModify}">  --%>
              	    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	    <%-- </c:if> --%>
            </tr>
            <tr>
    
    				<th class="infoline" rowspan="2">
						Add:
					</th>
					
     			 
     			<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardAmountReleased.invoiceNumber" readOnly="${readOnly}" attributeEntry="${subAwardAmountReleasedAttributes.invoiceNumber}" />           
   					</div> 
   				</td>
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardAmountReleased.startDate" readOnly="${readOnly}" attributeEntry="${subAwardAmountReleasedAttributes.startDate}" datePicker="true"/>           
   					</div> 
   				</td>
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardAmountReleased.endDate" readOnly="${readOnly}" attributeEntry="${subAwardAmountReleasedAttributes.endDate}" datePicker="true"/>         
   					</div> 
   				</td>
   				
   				   <td class="infoline">
                	<html:file property="newSubAwardAmountReleased.fileName" />
                </td>
                <td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardAmountReleased.effectiveDate" readOnly="${readOnly}" attributeEntry="${subAwardAmountReleasedAttributes.effectiveDate}" datePicker="true"/>         
   					</div> 
   				</td>
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardAmountReleased.amountReleased" readOnly="${readOnly}" attributeEntry="${subAwardAmountReleasedAttributes.amountReleased}" />         
   					</div> 
   				</td>
   				 				
   				<td class="infoline" rowspan="2"><div align="center">
						<html:image property="methodToCall.addAmountReleased.anchor${tabKey}" 
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
						            styleClass="tinybutton"/>
	                </div>
	            </td>   				
   			</tr> 
        	<tr>				
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountReleasedAttributes.comments}" /></div></th>
                <td colspan="5">
                      <kul:htmlControlAttribute property="newSubAwardAmountReleased.comments" readOnly="${readOnly}" attributeEntry="${subAwardAmountReleasedAttributes.comments}" />
                </td>
            </tr>     
   			
   			
   			<c:forEach var="newSubAwardAmountReleased" items="${KualiForm.document.subAwardList[0].subAwardAmountReleasedList}" varStatus="status">
		             <tr>
						<th width="5%" class="infoline" rowspan="2">
							<c:out value="${status.index+1}" />
						</th>	                
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].invoiceNumber" attributeEntry="${subAwardAmountReleasedAttributes.invoiceNumber}" readOnly="false" />
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].startDate" attributeEntry="${subAwardAmountReleasedAttributes.startDate}" datePicker="true" readOnly="false"  />                		
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].endDate" attributeEntry="${subAwardAmountReleasedAttributes.endDate}" datePicker="true" readOnly="false"   />
						</div>
						<%-- </td><kra:fileicon attachment="${subAward.fileName}"/><td> --%>
					
						<td width="9%" valign="middle">
						<div align="center">
						
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].fileName" attributeEntry="${subAwardAmountReleasedAttributes.fileName}" readOnly="true" />
						</div>
						</td>		
						<td width="9%" valign="middle">
						<div align="center">
						
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].effectiveDate" attributeEntry="${subAwardAmountReleasedAttributes.effectiveDate}" datePicker="true" readOnly="false" />
						</div>
						</td>	
						
						<td width="9%" valign="middle">
						<div align="center">
						
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].amountReleased" attributeEntry="${subAwardAmountReleasedAttributes.amountReleased}" readOnly="false" />
						</div>
						</td>	
						
						
						
						               
						<td width="10%" valign="middle" rowspan="2">    
						<div align="center">
						  <c:if test="${!readOnly}">
	                		<html:image property="methodToCall.deleteFandaRate.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						  </c:if>
						  <c:if test="${readOnly}">&nbsp;</c:if>
						</div>
						</td>	
		            </tr>
		            
		            <tr>		            			
						<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountReleasedAttributes.comments}" /></div></th>
                			<td colspan="5">
                      			<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].comments" readOnly="true" attributeEntry="${subAwardAmountReleasedAttributes.comments}" />
                			</td>
           		   </tr>
	        	</c:forEach>
        </table>
    </div>
</kul:tab>