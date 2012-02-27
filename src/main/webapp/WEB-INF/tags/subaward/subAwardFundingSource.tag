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

<c:set var="subAwardFundingSourceAttributes" value="${DataDictionary.SubAwardFundingSource.attributes}" />
<c:set var="action" value="subAward" />
<c:set var="subAwardFundingSource" value="${KualiForm.document.subAwardList[0].subAwardFundingSourceList}"/>
<c:set var="newSubAwardFundingSource" value="${KualiForm.newSubAwardFundingSource}" />

<kul:tab tabTitle="Funding Source" defaultOpen="true" alwaysOpen="true" transparentBackground="false" tabErrorKey="newSubAwardFundingSource.award.awardNumber*" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> Funding Source </span>	
    		<div align="right"><kul:help parameterNamespace="KC-SUBAWARD" parameterDetailType="Document" parameterName="subAwardFundingSourceHelpUrl" altText="help"/></div>
        </h3>
        <table cellpadding=0 cellspacing=0 summary="">
        
        <tr>
      	<th><div align="left">&nbsp;</div></th>
        <th colspan=3><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardFundingSourceAttributes.awardId}" /></div></th>
        <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardFundingSourceAttributes.accountNumber}" /></div></th>
        <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardFundingSourceAttributes.statusCode}" /></div></th>
        <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardFundingSourceAttributes.sponsorCode}" /></div></th>
        <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardFundingSourceAttributes.amountObligatedToDate}" /></div></th>
        <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardFundingSourceAttributes.obligationExpirationDate}" /></div></th>
         <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	   </tr>     
          	    <c:if test="${readOnly!='true'}">
			   <tr>
               <th class="infoline" >
						Add:
				</th>
     			 <td align="center"  colspan=3><kul:htmlControlAttribute property="newSubAwardFundingSource.award.awardNumber" attributeEntry="${subAwardFundingSourceAttributes.awardId}" />
     			 <c:if test="${readOnly!='true'}">
                 <kul:lookup boClassName="org.kuali.kra.award.home.Award" fieldConversions="awardNumber:newSubAwardFundingSource.award.awardNumber,awardDocument.documentNumber:newSubAwardFundingSource.award.awardDocument.documentNumber,awardId:newSubAwardFundingSource.awardId,accountNumber:newSubAwardFundingSource.award.accountNumber,statusCode:newSubAwardFundingSource.award.statusCode,sponsorCode:newSubAwardFundingSource.award.sponsorCode,sponsorName:newSubAwardFundingSource.award.sponsorName,awardAmountInfos[0].amountObligatedToDate:newSubAwardFundingSource.award.awardAmountInfos[0].amountObligatedToDate,awardAmountInfos[0].obligationExpirationDate:newSubAwardFundingSource.award.awardAmountInfos[0].obligationExpirationDate,awardStatus.description:newSubAwardFundingSource.award.awardStatus.description" anchor="${tabKey}" />
                 </c:if>
               	</td>
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardFundingSource.award.accountNumber" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.accountNumber}" datePicker="false" />           
   					</div> 
   				</td>
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardFundingSource.award.awardStatus.description" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.statusCode}" datePicker="false" />         
   					</div> 
   				</td>
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardFundingSource.sponsorName" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.sponsorCode}" datePicker="false" />         
   					</div> 
   					</td>	
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardFundingSource.award.awardAmountInfos[0].amountObligatedToDate" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.amountObligatedToDate}" datePicker="false" />         
   					</div> 
   				</td>	
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardFundingSource.award.awardAmountInfos[0].obligationExpirationDate" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.obligationExpirationDate}" datePicker="false" />         
   					</div> 
   				</td>		
   				<td class="infoline" rowspan="1"><div align="center">
   				 <c:if test="${readOnly!='true'}">
						<html:image property="methodToCall.addFundingSource.anchor${tabKey}" 
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
						            styleClass="tinybutton"/>
				 </c:if>
	                </div>
	            </td>   
   			</tr> 
   			</c:if>
     	<c:forEach var="subAwardFundingSource" items="${KualiForm.document.subAwardList[0].subAwardFundingSourceList}" varStatus="status">
		             <tr>
		             
						<th width="5%" class="infoline" rowspan="1">
							<c:out value="${status.index+1}" />
						</th>
						<c:set  var="documentNumber" value="${subAwardFundingSource.award.awardDocument.documentNumber}"/> 
						    <td width="6%" valign="middle"> 
						    
						    <a
						href="${ConfigProperties.application.url}/awardHome.do?methodToCall=docHandler&command=displayDocSearchView&docId=${documentNumber}&medusaOpenedDoc=true"
						target="_blank" class="medusaOpenLink">Open award</a>
						</td>
						  <td width="6%" valign="middle"> 
						 <a
						href="${ConfigProperties.application.url}/awardHome.do?methodToCall=medusa&command=displayDocSearchView&docId=${documentNumber}&medusaOpenedDoc=true"
						target="_blank" class="medusaOpenLink"> medusa </a>
						 
						    </td>
		                 <td width="9%" valign="middle">
		               
		                  
		                 
						<div align="left">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardFundingSourceList[${status.index}].award.awardNumber" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.awardId}" />
						</div>
						</td> 
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardFundingSourceList[${status.index}].award.accountNumber" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.accountNumber}"/>                		
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardFundingSourceList[${status.index}].award.awardStatus.description" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.statusCode}"/>
						</div>
						</td>
						<td width="9%" valign="middle">
						<div align="center">
							<kul:htmlControlAttribute property="document.subAwardList[0].subAwardFundingSourceList[${status.index}].sponsorName" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.sponsorCode}"/>         
						</div>
						</td>
						<td width="9%" valign="middle">
						<div align="center">
							<kul:htmlControlAttribute property="document.subAwardList[0].subAwardFundingSourceList[${status.index}].award.awardAmountInfos[0].amountObligatedToDate" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.amountObligatedToDate}"/>
						</div>
						</td>
						<td width="9%" valign="middle">
						<div align="center">
							<kul:htmlControlAttribute property="document.subAwardList[0].subAwardFundingSourceList[${status.index}].award.awardAmountInfos[0].obligationExpirationDate" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.obligationExpirationDate}" datePicker="false"/>
						</div>
						</td>
						
						    <td width="10%" valign="middle" rowspan="1">
						<div align="center">
						  <c:if test="${!readOnly}">
	                		<html:image property="methodToCall.deleteFundingSource.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						  </c:if>
						  <c:if test="${readOnly}">&nbsp;</c:if>
						</div>
						</td>           
		            </tr>
      
	        	</c:forEach>
        </table>
    </div> 
</kul:tab>