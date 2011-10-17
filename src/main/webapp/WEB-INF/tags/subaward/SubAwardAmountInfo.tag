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

<c:set var="subAwardAmountInfoAttributes" value="${DataDictionary.SubAwardAmountInfo.attributes}" />
<c:set var="action" value="subAward" />

<c:set var="newSubAwardAmountInfo" value="${KualiForm.newSubAwardAmountInfo}" />

<kul:tab tabTitle="SubAwardAmountInfo" defaultOpen="true" alwaysOpen="true" transparentBackground="true" tabErrorKey="" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> SubAward Amount Info</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.SubAwardAmountInfo" altText="help"/></span>
        </h3>
        
   <table cellpadding=0 cellspacing=0 summary="">
            <tr>
              <th><div align="left">&nbsp;</div></th> 
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountInfoAttributes.effectiveDate}" /></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountInfoAttributes.obligatedChange}" /></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountInfoAttributes.anticipatedChange}" /></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountInfoAttributes.fileName}" /></div></th>
                <%-- <c:if test="${canModify}">  --%>
              	    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	    <%-- </c:if> --%>
            </tr>
            <tr>
    
    				<th class="infoline" rowspan="2">
						Add:
					</th>
					
     			 
     			<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardAmountInfo.effectiveDate" readOnly="${readOnly}" attributeEntry="${subAwardAmountInfoAttributes.effectiveDate}" datePicker="true"/>           
   					</div> 
   				</td>
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardAmountInfo.obligatedChange" readOnly="${readOnly}" attributeEntry="${subAwardAmountInfoAttributes.obligatedChange}" />           
   					</div> 
   				</td>
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardAmountInfo.anticipatedChange" readOnly="${readOnly}" attributeEntry="${subAwardAmountInfoAttributes.anticipatedChange}" />         
   					</div> 
   				</td>
   				
   				   <td class="infoline">
                	<html:file property="newSubAwardAmountInfo.fileName" />
                </td>
   				 				
   				<td class="infoline" rowspan="2"><div align="center">
						<html:image property="methodToCall.addAmountInfo.anchor${tabKey}" 
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
						            styleClass="tinybutton"/>
	                </div>
	            </td>   				
   			</tr> 
        	<tr>				
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountInfoAttributes.comments}" /></div></th>
                <td colspan="3">
                      <kul:htmlControlAttribute property="newSubAwardAmountInfo.comments" readOnly="${readOnly}" attributeEntry="${subAwardAmountInfoAttributes.comments}" />
                </td>
            </tr>     
   			
   			
   			<c:forEach var="newSubAwardAmountInfo" items="${KualiForm.document.subAwardList[0].subAwardAmountInfoList}" varStatus="status">
		             <tr>
						<th width="5%" class="infoline" rowspan="2">
							<c:out value="${status.index+1}" />
						</th>	                
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountInfoList[${status.index}].effectiveDate" attributeEntry="${subAwardAmountInfoAttributes.effectiveDate}"  datePicker="true" />
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountInfoList[${status.index}].obligatedChange" attributeEntry="${subAwardAmountInfoAttributes.obligatedChange}"  />                		
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountInfoList[${status.index}].anticipatedChange" attributeEntry="${subAwardAmountInfoAttributes.anticipatedChange}"   />
						</div>
						<%-- </td><kra:fileicon attachment="${subAward.fileName}"/><td> --%>
					
						<td width="9%" valign="middle">
						<div align="center">
						
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountInfoList[${status.index}].fileName" attributeEntry="${subAwardAmountInfoAttributes.fileName}" readOnly="true" />
						</div>
						</td>		               
						<td width="10%" valign="middle" rowspan="2">
						<div align="center">
						  <c:if test="${!readOnly}">
	                		<html:image property="methodToCall.deleteAmountInfo.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						  </c:if>
						  <c:if test="${readOnly}">&nbsp;</c:if>
						</div>
						</td>	
		            </tr>
		            
		            <tr>		            			
						<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountInfoAttributes.comments}" /></div></th>
                			<td colspan="3">
                      			<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountInfoList[${status.index}].comments"  attributeEntry="${subAwardAmountInfoAttributes.comments}" />
                			</td>
           		   </tr>
	        	</c:forEach>
        </table>
    </div>
</kul:tab>