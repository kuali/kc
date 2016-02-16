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

<script type='text/javascript' src='dwr/interface/AwardService.js'></script>

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
     			 <td align="center"  colspan=3>
					 <kul:htmlControlAttribute property="newSubAwardFundingSource.award.awardNumber"
											   attributeEntry="${subAwardFundingSourceAttributes.awardId}"
											   onblur="loadFundingSourceAwardNumber('newSubAwardFundingSource.award.awardNumber',
                								'key.accountNumber',
                								'key.accountNumberHidden',
                								'key.awardStatus',
                								'key.awardStatusHidden',
    							  				'key.amount',
    							  				'key.amountHidden',
    							  				'key.obligationExpirationDate',
    							  				'key.obligationExpirationDateHidden',
    							  				'key.sponsor',
    							  				'key.sponsorCode',
    							  				'key.sponsorName',
    							  				'key.awardDocumentNumber',
    							  				'key.awardId');" />

     			 	<c:if test="${readOnly!='true'}">
                 		<kul:lookup boClassName="org.kuali.kra.award.home.Award" fieldConversions="awardNumber:newSubAwardFundingSource.award.awardNumber,awardDocument.documentNumber:newSubAwardFundingSource.award.awardDocument.documentNumber,awardId:newSubAwardFundingSource.awardId,accountNumber:newSubAwardFundingSource.award.accountNumber,statusCode:newSubAwardFundingSource.award.statusCode,sponsorCode:newSubAwardFundingSource.award.sponsorCode,sponsorName:newSubAwardFundingSource.award.sponsorName,latestAwardAmountInfo.amountObligatedToDate:newSubAwardFundingSource.award.latestAwardAmountInfo.amountObligatedToDate,latestAwardAmountInfo.obligationExpirationDate:newSubAwardFundingSource.award.latestAwardAmountInfo.obligationExpirationDate,awardStatus.description:newSubAwardFundingSource.award.awardStatus.description" anchor="${tabKey}" />
                 	</c:if>

					 <html:hidden styleId ="key.awardId" property="newSubAwardFundingSource.awardId" />
						 ${kfunc:registerEditableProperty(KualiForm, "newSubAwardFundingSource.awardId")}

					 <html:hidden styleId ="key.awardNumber" property="newSubAwardFundingSource.award.awardNumber" />
						 ${kfunc:registerEditableProperty(KualiForm, "newSubAwardFundingSource.award.awardNumber")}

					 <html:hidden styleId ="key.awardDocumentNumber" property="newSubAwardFundingSource.award.awardDocument.documentNumber" />
						 ${kfunc:registerEditableProperty(KualiForm, "newSubAwardFundingSource.award.awardDocument.documentNumber")}

					 <html:hidden styleId ="key.accountNumberHidden" property="newSubAwardFundingSource.award.accountNumber" />
						 ${kfunc:registerEditableProperty(KualiForm, "newSubAwardFundingSource.award.accountNumber")}

					 <html:hidden styleId ="key.sponsorCode" property="newSubAwardFundingSource.award.sponsorCode" />
						 ${kfunc:registerEditableProperty(KualiForm, "newSubAwardFundingSource.award.sponsorCode")}

					 <html:hidden styleId ="key.sponsorName" property="newSubAwardFundingSource.award.sponsorName" />
						 ${kfunc:registerEditableProperty(KualiForm, "newSubAwardFundingSource.award.sponsorName")}

					 <html:hidden styleId ="key.awardStatusHidden" property="newSubAwardFundingSource.award.awardStatus.description" />
						 ${kfunc:registerEditableProperty(KualiForm, "newSubAwardFundingSource.award.awardStatus.description")}

					 <html:hidden styleId ="key.amountHidden" property="newSubAwardFundingSource.award.awardAmountInfos[0].amountObligatedToDate" />
						 ${kfunc:registerEditableProperty(KualiForm, "newSubAwardFundingSource.award.awardAmountInfos[0].amountObligatedToDate")}

					 <html:hidden styleId ="key.obligationExpirationDateHidden" property="newSubAwardFundingSource.award.awardAmountInfos[0].obligationExpirationDate" />
						 ${kfunc:registerEditableProperty(KualiForm, "newSubAwardFundingSource.award.awardAmountInfos[0].obligationExpirationDate")}
               	</td>
   				<td><div id="key.accountNumber" align="center">
     					<kul:htmlControlAttribute property="newSubAwardFundingSource.award.accountNumber" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.accountNumber}" datePicker="false" />           
   					</div> 
   				</td>
   				<td><div id="key.awardStatus" align="center">
     					<kul:htmlControlAttribute property="newSubAwardFundingSource.award.awardStatus.description" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.statusCode}" datePicker="false" />         
   					</div> 
   				</td>
					<td><div id="key.sponsor" align="center">
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
					<td><div id="key.amount" align="center">
     					<kul:htmlControlAttribute property="newSubAwardFundingSource.award.latestAwardAmountInfo.amountObligatedToDate" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.amountObligatedToDate}" datePicker="false" />
   					</div> 
   				</td>	
   				<td><div id="key.obligationExpirationDate" align="center">
     					<kul:htmlControlAttribute property="newSubAwardFundingSource.award.latestAwardAmountInfo.obligationExpirationDate" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.obligationExpirationDate}" datePicker="false" />
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
			</table>
		<h3>
			<span class="subhead-left">Current Funding Sources</span>
				<span class="subhead-right">
					<kul:help parameterNamespace="KC-SUBAWARD" parameterDetailType="Document" parameterName="awardCurrentFundingSourcesHelp" altText="help"/>
				</span>
		</h3>
		<table cellpadding=0 cellspacing=0 summary="Current Funding Sources">
			<tr>
				<th><div align="center">&nbsp;</div></th>
				<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardFundingSourceAttributes.awardId}" /></div></th>
				<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardFundingSourceAttributes.accountNumber}" /></div></th>
				<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardFundingSourceAttributes.statusCode}" /></div></th>
				<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardFundingSourceAttributes.sponsorCode}" /></div></th>
				<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardFundingSourceAttributes.amountObligatedToDate}" /></div></th>
				<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardFundingSourceAttributes.obligationExpirationDate}" /></div></th>
				<th><div align="center">&nbsp;</div></th>
				<th><div align="center">&nbsp;</div></th>
				<th><div align="center">&nbsp;</div></th>
			</tr>
			<c:forEach var="subAwardFundingSource" items="${KualiForm.document.subAwardList[0].subAwardFundingSourceList}" varStatus="status">

				<c:set var="parentTab" value="Funding Sources" scope="request"/>
				<c:set var="versionTab" value="FundingSources${status.index}"/>
				<c:set var="currentTab" value="${kfunc:getTabState(KualiForm, versionTab)}"/>
				<c:choose>
					<c:when test="${empty currentTab}">
						<c:set var="isOpen" value="false" />
					</c:when>
					<c:when test="${!empty currentTab}" >
						<c:set var="isOpen" value="${currentTab == 'OPEN'}" />
					</c:when>
				</c:choose>
				<c:if test="${isOpen == 'true' || isOpen == 'TRUE'}">
					<c:set var="displayStyle" value="display: table-row-group;"/>
				</c:if>
				<c:if test="${isOpen != 'true' && isOpen != 'TRUE'}">
					<c:set var="displayStyle" value="display: none;"/>
				</c:if>
				<html:hidden property="tabStates(${versionTab})" value="${(isOpen ? 'OPEN' : 'CLOSE')}" />

					<tr>
						<td align="right" class="tab-subhead" scope="row">
							<div align="center">
								<c:if test="${isOpen == 'true' || isOpen == 'TRUE'}">
									<html:image property="methodToCall.toggleTab.tab${versionTab}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-hide.gif" title="close ${tabTitle}" alt="close ${tabTitle}" styleClass="tinybutton"  styleId="tab-${versionTab}-imageToggle" onclick="javascript: return toggleTab(document, '${versionTab}'); " />
								</c:if>
								<c:if test="${isOpen != 'true' && isOpen != 'TRUE'}">
									<html:image  property="methodToCall.toggleTab.tab${versionTab}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" title="open ${tabTitle}" alt="open ${tabTitle}" styleClass="tinybutton" styleId="tab-${versionTab}-imageToggle" onclick="javascript: return toggleTab(document, '${versionTab}'); " />
								</c:if>
							</div>
						</td>
						<c:set  var="documentNumber" value="${subAwardFundingSource.activeAward.awardDocument.documentNumber}"/>

		                 <td class="tab-subhead">
		               
		                  
		                 
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardFundingSourceList[${status.index}].activeAward.awardNumber" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.awardId}" />
						</div>
						</td> 
		                <td class="tab-subhead">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardFundingSourceList[${status.index}].activeAward.accountNumber" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.accountNumber}"/>
						</div>
						</td>
		                <td class="tab-subhead">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardFundingSourceList[${status.index}].activeAward.awardStatus.description" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.statusCode}"/>
						</div>
						</td>
						<td class="tab-subhead">
						<div align="center">
							<kul:htmlControlAttribute property="document.subAwardList[0].subAwardFundingSourceList[${status.index}].activeAward.sponsorCode" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.sponsorCode}"/> : <kul:htmlControlAttribute property="document.subAwardList[0].subAwardFundingSourceList[${status.index}].activeAward.sponsorName" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.sponsorCode}"/>
						</div>
						</td>
						<td class="tab-subhead">
						<div align="center">
							<kul:htmlControlAttribute property="document.subAwardList[0].subAwardFundingSourceList[${status.index}].activeAward.latestAwardAmountInfo.amountObligatedToDate" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.amountObligatedToDate}"/>
						</div>
						</td>
						<td class="tab-subhead">
						<div align="center">
							<kul:htmlControlAttribute property="document.subAwardList[0].subAwardFundingSourceList[${status.index}].activeAward.latestAwardAmountInfo.obligationExpirationDate" readOnly="true" attributeEntry="${subAwardFundingSourceAttributes.obligationExpirationDate}" datePicker="false"/>
						</div>
						</td>
						<td class="tab-subhead">

							<a
									href="${ConfigProperties.application.url}/awardHome.do?methodToCall=docHandler&command=displayDocSearchView&docId=${documentNumber}&medusaOpenedDoc=true"
									target="_blank" class="medusaOpenLink">Open award</a>
						</td>
						<td class="tab-subhead">
							<a
									href="${ConfigProperties.application.url}/awardHome.do?methodToCall=medusa&command=displayDocSearchView&docId=${documentNumber}&medusaOpenedDoc=true"
									target="_blank" class="medusaOpenLink"> medusa </a>

						</td>
						    <td class="tab-subhead">
						<div align="center">
						  <c:if test="${!readOnly}">
	                		<html:image property="methodToCall.deleteFundingSource.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						  </c:if>
						  <c:if test="${readOnly}">&nbsp;</c:if>
						</div>
						</td>           
		            </tr>
					<tbody style="${displayStyle}" id = "tab-${versionTab}-div">
						<tr>
							<td colspan="9" class="infoline">
								<kra-sub:subAwardFundingSourceDetails subAwardFundingSource="${subAwardFundingSource}" index="${status.index}" />
							</td>
						</tr>
					</tbody>
	        	</c:forEach>
        </table>
    </div> 
</kul:tab>
