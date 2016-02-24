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
<%-- member of awardSpecialApproval.tag --%>

<%@ include file="/WEB-INF/jsp/award/awardTldHeader.jsp"%>

<kul:innerTab parentTab="Special Approval" tabItemCount="${formAward.approvedEquipmentItemCount}" defaultOpen="false" 
				tabTitle="Approved Equipment" tabErrorKey="approvedEquipmentBean.newAwardApprovedEquipment*,${docAward}.approvedEquipmentItems*,approvedEquipmentItems" >
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
		
		<c:if test="${!readOnly}">
		<tbody class="addline">
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
	    	    	<kul:htmlControlAttribute property="approvedEquipmentBean.newAwardApprovedEquipment.amount" attributeEntry="${approvedEquipmentAttributes.amount}" styleClass="amount"/>
	    	  	</div>
	        </td>
	        <td class="infoline">
	        	<div align="center">
					<html:image property="methodToCall.addApprovedEquipmentItem.anchor${tabKey}"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
				</div>
	        </td>
	  	</tr>
	  	</tbody>
	  	</c:if>
	
	 	<c:forEach var="approvedEquipmentItem" items="${formAward.approvedEquipmentItems}" varStatus="status">
	         <tr>
				<th width="5%" class="infoline">
					<c:out value="${status.index+1}" />
				</th>
	            <td width="20%" valign="middle">
	            	<div align="center">
						<kul:htmlControlAttribute property="${docAward}.approvedEquipmentItems[${status.index}].item" attributeEntry="${approvedEquipmentAttributes.item}"/>
					</div>
				</td>
	            <td width="20%" valign="middle">
	            	<div align="center">
						<kul:htmlControlAttribute property="${docAward}.approvedEquipmentItems[${status.index}].vendor" attributeEntry="${approvedEquipmentAttributes.vendor}"/>
					</div>
				</td>
	            <td width="20%" valign="middle">
	            	<div align="center">                	
						<kul:htmlControlAttribute property="${docAward}.approvedEquipmentItems[${status.index}].model" attributeEntry="${approvedEquipmentAttributes.model}"/>
					</div> 
				</td>
	            <td width="20%" valign="middle">
	            	<div align="center">                	
						<kul:htmlControlAttribute property="${docAward}.approvedEquipmentItems[${status.index}].amount" 
								attributeEntry="${approvedEquipmentAttributes.amount}" styleClass="amount" />
					</div> 
				</td>
	            
				<td width="15%" class="infoline">
					<div align="center">
					   <c:if test="${!readOnly}">
						<html:image property="methodToCall.deleteApprovedEquipmentItem.line${status.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					   </c:if>
					   <c:if test="${readOnly}">&nbsp;</c:if>
					</div>
	            </td>
	        </tr>
		</c:forEach>
		<tr>
	  		<th colspan="4" align="right" scope="row"><div>Totals:</div></th>
	  		<th align="right">
	  			<div align="right">
	  				<fmt:formatNumber type="CURRENCY" value="${formAward.totalApprovedEquipmentAmount}" />
	            </div>
	     	</th>
	     	<th scope="row">
	     		<div align="center">
	     		    <c:if test="${!readOnly}">
					   <html:image property="methodToCall.recalculateSpecialApprovalTotals.anchor${tabKey}" 
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' styleClass="tinybutton" />
					</c:if>
					<c:if test="${readOnly}">&nbsp;</c:if>
				</div>
			</th>
	  	</tr>
	</table>
</kul:innerTab>
