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

<c:set var="subAwardCloseoutAttributes" value="${DataDictionary.SubAwardCloseout.attributes}" />
<c:set var="action" value="subAwardCloseout" />
<c:set var="subAwardCloseouts" value="${KualiForm.document.subAwardList[0].subAwardCloseoutList}"/>
<c:set var="newSubAwardCloseout" value="${KualiForm.newSubAwardCloseout}" />

<kul:tab tabTitle="Closeout"  transparentBackground="false" defaultOpen="true" tabErrorKey="newSubAwardCloseout.closeoutTypeCode*,newSubAwardCloseout.dateRequested*,newSubAwardCloseout.dateFollowup*,newSubAwardCloseout.dateReceived*,newSubAwardCloseout.comments*" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> Closeout </span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.SubAwardCloseout" altText="help"/></span>
        </h3>
        
        <table cellpadding=0 cellspacing=0 summary="">
            <tr>
              <th><div align="left">&nbsp;</div></th> 
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardCloseoutAttributes.closeoutTypeCode}" /></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardCloseoutAttributes.dateRequested}" /></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardCloseoutAttributes.dateFollowup}" /></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardCloseoutAttributes.dateReceived}" /></div></th>
                <%-- <c:if test="${canModify}">  --%>
              	    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	    <%-- </c:if> --%>
            </tr>
             <c:if test="${readOnly!='true'}">
            <tr>
    
    				<th class="infoline" rowspan="2">
						Add:
					</th>
					
     			<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardCloseout.closeoutTypeCode" readOnly="${readOnly}" attributeEntry="${subAwardCloseoutAttributes.closeoutTypeCode}" />           
   					</div> 
   				</td>
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardCloseout.dateRequested" readOnly="${readOnly}" attributeEntry="${subAwardCloseoutAttributes.dateRequested}" datePicker="true" />           
   					</div> 
   				</td>
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardCloseout.dateFollowup" readOnly="${readOnly}" attributeEntry="${subAwardCloseoutAttributes.dateFollowup}" datePicker="true" />         
   					</div> 
   				</td>
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardCloseout.dateReceived" readOnly="${readOnly}" attributeEntry="${subAwardCloseoutAttributes.dateReceived}" datePicker="true" />         
   					</div> 
   				</td>   				
   				<td class="infoline" rowspan="2"><div align="center">
   					<c:if test="${readOnly!='true'}">
						<html:image property="methodToCall.addCloseouts.anchor${tabKey}" 
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
						            styleClass="tinybutton"/>
					</c:if>
	                </div>
	            </td>   				
   			</tr> 
        	<tr>				
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardCloseoutAttributes.comments}" /></div></th>
                <td colspan="3">
                      <kul:htmlControlAttribute property="newSubAwardCloseout.comments" readOnly="${readOnly}" attributeEntry="${subAwardCloseoutAttributes.comments}" />
                </td>
            </tr>     
   			</c:if>
   			
   			<c:forEach var="subAwardCloseouts" items="${KualiForm.document.subAwardList[0].subAwardCloseoutList}" varStatus="status">
		             <tr>
						<th width="5%" class="infoline" rowspan="2">
							<c:out value="${status.index+1}" />
						</th>	                
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardCloseoutList[${status.index}].closeoutTypeCode" attributeEntry="${subAwardCloseoutAttributes.closeoutTypeCode}" styleClass="amount"/>
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardCloseoutList[${status.index}].dateRequested" attributeEntry="${subAwardCloseoutAttributes.dateRequested}"  datePicker="true"/>                		
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardCloseoutList[${status.index}].dateFollowup" attributeEntry="${subAwardCloseoutAttributes.dateFollowup}"  datePicker="true"/>
						</div>
						</td>
						<td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardCloseoutList[${status.index}].dateReceived" attributeEntry="${subAwardCloseoutAttributes.dateReceived}"  datePicker="true"/>
						</div>
						</td>		               
						<td width="10%" valign="middle" rowspan="2">
						<div align="center">
						  <c:if test="${!readOnly}">
	                		<html:image property="methodToCall.deleteCloseout.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						  </c:if>
						  <c:if test="${readOnly}">&nbsp;</c:if>
						</div>
						</td>	
		            </tr>
		            
		            <tr>		            			
						<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardCloseoutAttributes.comments}" /></div></th>
                			<td colspan="3">
                      			<kul:htmlControlAttribute property="document.subAwardList[0].subAwardCloseoutList[${status.index}].comments" readOnly="${readOnly}" attributeEntry="${subAwardCloseoutAttributes.comments}" />
                			</td>
           		   </tr>
	        	</c:forEach>
        </table>
    </div>
</kul:tab>
