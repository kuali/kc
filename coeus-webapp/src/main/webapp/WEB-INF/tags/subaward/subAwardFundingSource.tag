<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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

<c:set var="subAwardFundingSourceAttributes" value="${DataDictionary.SubAwardFundingSource.attributes}" />
<c:set var="action" value="subAward" />
<c:set var="subAwardFundingSource" value="${KualiForm.document.subAwardList[0].subAwardFundingSourceList}"/>
<c:set var="newSubAwardFundingSource" value="${KualiForm.newSubAwardFundingSource}" />

<kul:tab tabTitle="Funding Source" defaultOpen="${KualiForm.document.subAwardList[0].defaultOpen}" alwaysOpen="true" transparentBackground="false" tabErrorKey="newSubAwardFundingSource.award.awardNumber*" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> Funding Source </span>	
    		<div align="right"><kul:help parameterNamespace="KC-SUBAWARD" parameterDetailType="Document" parameterName="subAwardFundingSourceHelpUrl" altText="help"/></div>
        </h3>
        <table cellpadding=0 cellspacing=0 summary="">
		<tbody class="addline">         
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
                 <kul:lookup boClassName="org.kuali.kra.award.home.Award" fieldConversions="awardNumber:newSubAwardFundingSource.award.awardNumber,awardDocument.documentNumber:newSubAwardFundingSource.award.awardDocument.documentNumber,awardId:newSubAwardFundingSource.awardId,accountNumber:newSubAwardFundingSource.award.accountNumber,statusCode:newSubAwardFundingSource.award.statusCode,sponsorCode:newSubAwardFundingSource.award.sponsorCode,sponsorName:newSubAwardFundingSource.award.sponsorName,awardAmountInfo.amountObligatedToDate:newSubAwardFundingSource.award.awardAmountInfos[0].amountObligatedToDate,awardAmountInfo.obligationExpirationDate:newSubAwardFundingSource.award.awardAmountInfos[0].obligationExpirationDate,awardStatus.description:newSubAwardFundingSource.award.awardStatus.description" anchor="${tabKey}" />
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
							<kul:htmlControlAttribute
									property="newSubAwardFundingSource.award.sponsorCode" readOnly="true"
									attributeEntry="${subAwardFundingSourceAttributes.sponsorCode}"
									datePicker="false" /><c:if
								test="${!empty newSubAwardFundingSource.award.sponsorName}">
								: <kul:htmlControlAttribute
									property="newSubAwardFundingSource.award.sponsorName" readOnly="true"
									attributeEntry="${subAwardFundingSourceAttributes.sponsorCode}"
									datePicker="false" />
							</c:if> 
						</div></td>
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
						            styleClass="tinybutton addButton"/>
				 </c:if>
	                </div>
	            </td>   
   			</tr> 
   			</c:if>
   			</tbody>
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
							<kul:htmlControlAttribute property="document.subAwardList[0].subAwardFundingSourceList[${status.index}].award.sponsorCode" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.sponsorCode}"/> : <kul:htmlControlAttribute property="document.subAwardList[0].subAwardFundingSourceList[${status.index}].award.sponsorName" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.sponsorCode}"/>         
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
