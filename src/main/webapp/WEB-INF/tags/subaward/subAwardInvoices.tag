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
<%@ attribute name="htmlFormAction" required="false" %>
<%@ attribute name="renderMultipart" required="false" %>
<c:set var="subAwardAmountReleasedAttributes" value="${DataDictionary.SubAwardAmountReleased.attributes}" />
<c:set var="action" value="subAwardFinancial" />
<c:set var="newSubAwardAmountReleased" value="${KualiForm.newSubAwardAmountReleased}" />
<kul:tab tabTitle="Invoices" defaultOpen="true" transparentBackground="false" tabErrorKey="newSubAwardAmountReleased.invoiceNumber*,newSubAwardAmountReleased.startDate*,newSubAwardAmountReleased.endDate*,newSubAwardAmountReleased.effectiveDate*,newSubAwardAmountReleased.effectiveDate*,newSubAwardAmountReleased.amountReleased*,newSubAwardAmountReleased.comments*" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
	<h3>
    		<span class="subhead-left"></span>
    		<span class="subhead-right"></span>
        </h3>
	<table cellpadding=0 cellspacing=0 summary="">
   				<tr>
				<th>Obligated Amount</th>
                <td colspan="2">
                       ${KualiForm.subAwardDocument.subAward.totalObligatedAmount}&nbsp; 
                </td>
				<th>Available Amount</th>
                <td colspan="2">
                      ${KualiForm.subAwardDocument.subAward.totalAvailableAmount}&nbsp; 
                </td>
            </tr>    
            
            <tr>
				<th>Amount Released</th>
                <td colspan="2">
                       ${KualiForm.subAwardDocument.subAward.totalAmountReleased}&nbsp; 
                </td>
				
            </tr>
            </table>
    	<h3>
    		<span class="subhead-left">Invoices</span>
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
             <c:if test="${readOnly!='true'}">
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
   				   <c:if test="${readOnly!='true'}">
                	<html:file property="newSubAwardAmountReleased.newFile" />
                	</c:if>
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
   					<c:if test="${readOnly!='true'}">
						<html:image property="methodToCall.addAmountReleased.anchor${tabKey}" 
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
						            styleClass="tinybutton"/>
				   </c:if>
	                </div>
	            </td>   				
   			</tr> 
   		
        	<tr>				
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountReleasedAttributes.comments}" /></div></th>
                <td colspan="5">
                      <kul:htmlControlAttribute property="newSubAwardAmountReleased.comments" readOnly="${readOnly}" attributeEntry="${subAwardAmountReleasedAttributes.comments}" />
                </td>
            </tr>     
   				</c:if>
   			
   			<c:forEach var="newSubAwardAmountReleased" items="${KualiForm.document.subAwardList[0].subAwardAmountReleasedList}" varStatus="status">
		             <tr>
						<th width="5%" class="infoline" rowspan="2">
							<c:out value="${status.index+1}" />
						</th>	                
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].invoiceNumber"  readOnly="${readOnly}" attributeEntry="${subAwardAmountReleasedAttributes.invoiceNumber}" />
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].startDate" readOnly="${readOnly}" attributeEntry="${subAwardAmountReleasedAttributes.startDate}" datePicker="true" />                		
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].endDate" readOnly="${readOnly}" attributeEntry="${subAwardAmountReleasedAttributes.endDate}" datePicker="true" />
						</div>
					
						<td width="9%" valign="middle">
						<div id="invoiceReplaceDiv${status.index}" style="display: block;">
							<c:if test="${newSubAwardAmountReleased.fileName!=null}">
								<kra:fileicon attachment="${newSubAwardAmountReleased}" />
							</c:if>
							<kul:htmlControlAttribute
								property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].fileName"
								readOnly="true"
								attributeEntry="${subAwardAmountReleasedAttributes.fileName}" />
						</div>
						<div id="invoiceFileDiv${status.index}" valign="middle"
							style="display: none;">
							<html:file
								property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].newFile" />
							<html:image
								property="methodToCall.replaceInvoiceAttachment.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif'
								styleClass="tinybutton" />
						</div></td>		
						<td width="9%" valign="middle">
						<div align="center">
						
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].effectiveDate" readOnly="${readOnly}" attributeEntry="${subAwardAmountReleasedAttributes.effectiveDate}" datePicker="true" />
						</div>
						</td>	
						
						<td width="9%" valign="middle">
						<div align="center">
						
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].amountReleased" readOnly="${readOnly}" attributeEntry="${subAwardAmountReleasedAttributes.amountReleased}" />
						</div>
						</td>	
						
						
						
						               
						<td width="10%" valign="middle" rowspan="2">    
						<div align="center">
							<c:if test="${!readOnly}">
								<c:if test="${newSubAwardAmountReleased.fileName!=null}">
									<html:image
										styleId="downloadInvoiceAttachment.line${status.index}"
										property="methodToCall.downloadInvoiceAttachment.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif'
										styleClass="tinybutton"
										onclick="javascript: openNewWindow('${action}','downloadInvoiceAttachment','${status.index}',${KualiForm.formKey},'${KualiForm.document.sessionDocument}'); return false" />
								</c:if>
								<html:image
									styleId="replaceInvoiceAttachment.line${status.index}"
									onclick="javascript: showHide('invoiceFileDiv${status.index}','invoiceReplaceDiv${status.index}') ; return false"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif'
									styleClass="tinybutton"
									property="methodToCall.replaceNarrativeAttachment.line${status.index}.anchor${currentTabIndex};return false" />
								<html:image
									property="methodToCall.deleteAmountReleased.line${status.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif'
									styleClass="tinybutton" />
							</c:if>
							<c:if test="${readOnly}">&nbsp;</c:if>
						</div></td>	
		            </tr>
		            
		            <tr>		            			
						<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountReleasedAttributes.comments}" /></div></th>
                			<td colspan="5">
                      			<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].comments" readOnly="${readOnly}" attributeEntry="${subAwardAmountReleasedAttributes.comments}" />
                			</td>
           		   </tr>
	        	</c:forEach>
        </table>
    </div>
</kul:tab>