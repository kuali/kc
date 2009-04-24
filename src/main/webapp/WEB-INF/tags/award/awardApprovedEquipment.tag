<%--
 Copyright 2006-2008 The Kuali Foundation
 
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
<%-- member of awardSpecialApproval.tag --%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<kul:innerTab parentTab="Special Approval" tabItemCount="${KualiForm.document.awardList[0].approvedEquipmentItemCount}" defaultOpen="false" 
				tabTitle="Approved Equipment" tabErrorKey="approvedEquipmentBean.newAwardApprovedEquipment*,document.awardList[0].approvedEquipmentItems*" >
	<c:set var="approvedEquipmentAttributes" value="${DataDictionary.AwardApprovedEquipment.attributes}" />	
	
	<kra:softError softErrorKey="approvedEquipmentItems" />
	
	<table id="approved-equip-table" cellpadding="0" cellspacing="0" summary="Approved Equipment">
		<tr>
			<th scope="row">&nbsp;</th>
			<th><kul:htmlAttributeLabel attributeEntry="${approvedEquipmentAttributes.item}" useShortLabel="true" noColon="true" /></th>
			<th><kul:htmlAttributeLabel attributeEntry="${approvedEquipmentAttributes.vendor}" useShortLabel="true" noColon="true" /></th>
			<th><kul:htmlAttributeLabel attributeEntry="${approvedEquipmentAttributes.model}" useShortLabel="true" noColon="true"/></th>
			<th><kul:htmlAttributeLabel attributeEntry="${approvedEquipmentAttributes.amount}" useShortLabel="true" noColon="true"/></th>
			<th><div align="center">Actions</div></th>
		</tr>
		
		<tr>
	    	<th width="50" align="center" scope="row"><div align="right">Add:</div></th>
	    	<td class="infoline">
	    	  	<div align="center">
	    	  	 	<kul:htmlControlAttribute property="approvedEquipmentBean.newAwardApprovedEquipment.item" attributeEntry="${approvedEquipmentAttributes.item}"/>
	    	 	</div>
	    	</td>
	        <td class="infoline">
	          	<div align="center">
	        		<kul:htmlControlAttribute property="approvedEquipmentBean.newAwardApprovedEquipment.vendor" attributeEntry="${approvedEquipmentAttributes.vendor}" />
	          	</div>
	        </td>
	        <td class="infoline">
	        	<div align="center">
	    	    	<kul:htmlControlAttribute property="approvedEquipmentBean.newAwardApprovedEquipment.model" attributeEntry="${approvedEquipmentAttributes.model}"/>
	    	  	</div>
	        </td>
	        <td class="infoline">
	        	<div align="center">
	    	    	<kul:htmlControlAttribute property="approvedEquipmentBean.newAwardApprovedEquipment.amount" attributeEntry="${approvedEquipmentAttributes.amount}"/>
	    	  	</div>
	        </td>
	        <td class="infoline">
	        	<div align="center">
					<html:image property="methodToCall.addApprovedEquipmentItem.anchor${tabKey}"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
				</div>
	        </td>
	  	</tr>
	
	 	<c:forEach var="approvedEquipmentItem" items="${KualiForm.document.awardList[0].approvedEquipmentItems}" varStatus="status">
	         <tr>
				<th width="5%" class="infoline">
					<c:out value="${status.index+1}" />
				</th>
	            <td width="20%" valign="middle">
	            	<div align="center">
						<kul:htmlControlAttribute property="document.awardList[0].approvedEquipmentItems[${status.index}].item" attributeEntry="${approvedEquipmentAttributes.item}"/>
					</div>
				</td>
	            <td width="20%" valign="middle">
	            	<div align="center">
						<kul:htmlControlAttribute property="document.awardList[0].approvedEquipmentItems[${status.index}].vendor" attributeEntry="${approvedEquipmentAttributes.vendor}"/>
					</div>
				</td>
	            <td width="20%" valign="middle">
	            	<div align="center">                	
						<kul:htmlControlAttribute property="document.awardList[0].approvedEquipmentItems[${status.index}].model" attributeEntry="${approvedEquipmentAttributes.model}"/>
					</div> 
				</td>
	            <td width="20%" valign="middle">
	            	<div align="center">                	
						<kul:htmlControlAttribute property="document.awardList[0].approvedEquipmentItems[${status.index}].amount" 
								attributeEntry="${approvedEquipmentAttributes.amount}" styleClass="amount" />
					</div> 
				</td>
	            
				<td width="15%" class="infoline">
					<div align="center">
						<html:image property="methodToCall.deleteApprovedEquipmentItem.line${status.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					</div>
	            </td>
	        </tr>
		</c:forEach>
		<tr>
	  		<th align="center" scope="row"><div>Totals</div></th>
	  		<th colspan="3" scope="row">&nbsp;</th>
	  		<th align="right">
	  			<div align="right">
	  				<fmt:formatNumber type="CURRENCY" value="${KualiForm.document.award.totalApprovedEquipmentAmount}" />
	            </div>
	     	</th>
	     	<th scope="row">
	     		<div align="center">
					<html:image property="methodToCall.recalculateSpecialApprovalTotals.anchor${tabKey}" 
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' styleClass="tinybutton" />
				</div>
			</th>
	  	</tr>
	</table>
</kul:innerTab>