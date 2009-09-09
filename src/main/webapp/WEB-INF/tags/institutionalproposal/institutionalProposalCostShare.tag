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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="institutionalProposalAttributes" value="${DataDictionary.InstitutionalProposal.attributes}" />
<c:set var="institutionalProposalCostShareAttributes" value="${DataDictionary.InstitutionalProposalCostShare.attributes}" />

<c:set var="tabItemCount" value="0" />
<c:forEach var="institutionalProposalCostShare" items="${KualiForm.document.institutionalProposal.institutionalProposalCostShares}" varStatus="status">               
        <c:set var="tabItemCount" value="${tabItemCount+1}" />
</c:forEach>
<div id="workarea">
<kul:tab tabItemCount="${tabItemCount}" tabTitle="Cost Sharing" defaultOpen="false" transparentBackground="true" tabErrorKey="newInstitutionalProposalCostShare.*">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Cost Share</span>
        </h3>
        <table id="cost-share-table" cellpadding="0" cellspacing="0" summary="Cost Share">
			<tr>
				<th scope="row">&nbsp;</th>
				<th><kul:htmlAttributeLabel attributeEntry="${institutionalProposalCostShareAttributes.fiscalYear}" useShortLabel="true" noColon="true" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${institutionalProposalCostShareAttributes.costShareTypeCode}" useShortLabel="true" noColon="true" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${institutionalProposalCostShareAttributes.costSharePercentage}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${institutionalProposalCostShareAttributes.sourceAccount}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${institutionalProposalCostShareAttributes.amount}" useShortLabel="true" noColon="true"/></th>
				<th><div align="center">Actions</div></th>
			</tr>
			
			<tr>
            	<th width="50" align="center" scope="row"><div align="center">Add:</div></th>
            	<td class="infoline">
            	  	<div align="center">
            	  	 	<kul:htmlControlAttribute property="institutionalProposalCostShareBean.newInstitutionalProposalCostShare.fiscalYear" attributeEntry="${institutionalProposalCostShareAttributes.fiscalYear}"/>
            	 	</div>
            	</td>
	            <td class="infoline">
	              	<div width="75" align="center">
	            		<kul:htmlControlAttribute property="institutionalProposalCostShareBean.newInstitutionalProposalCostShare.costShareTypeCode" attributeEntry="${institutionalProposalCostShareAttributes.costShareTypeCode}" />
	              	</div>
	            </td>
	            <td class="infoline">
	            	<div align="center">
            	    	<kul:htmlControlAttribute property="institutionalProposalCostShareBean.newInstitutionalProposalCostShare.costSharePercentage" attributeEntry="${institutionalProposalCostShareAttributes.costSharePercentage}" styleClass="amount"/>
            	  	</div>
	            </td>
	            <td class="infoline">
	            	<div align="center">
            	    	<kul:htmlControlAttribute property="institutionalProposalCostShareBean.newInstitutionalProposalCostShare.sourceAccount" attributeEntry="${institutionalProposalCostShareAttributes.sourceAccount}"/>
            	  	</div>
	            </td>
	            <td class="infoline">
	            	<div align="center">
            	   	 	<kul:htmlControlAttribute property="institutionalProposalCostShareBean.newInstitutionalProposalCostShare.amount" attributeEntry="${institutionalProposalCostShareAttributes.amount}" styleClass="amount"/>
            	  	</div>
	            </td>
	            <td class="infoline">
	            	<div align=center>
						<html:image property="methodToCall.addCostShare.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
	            </td>
          	</tr>
          	
         <c:forEach var="institutionalProposalCostShares" items="${KualiForm.document.institutionalProposal.institutionalProposalCostShares}" varStatus="status">
	             <tr>
					<th width="5%" class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td width="10%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.institutionalProposal.institutionalProposalCostShares[${status.index}].fiscalYear" attributeEntry="${institutionalProposalCostShareAttributes.fiscalYear}"/>
					</div>
					</td>
	                <td width="20%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.institutionalProposal.institutionalProposalCostShares[${status.index}].costShareTypeCode" attributeEntry="${institutionalProposalCostShareAttributes.costShareTypeCode}"/>
					</div>
	                </td>
	                <td width="15%" valign="middle">                	
					<div align="center">
                  		<kul:htmlControlAttribute property="document.institutionalProposal.institutionalProposalCostShares[${status.index}].costSharePercentage" attributeEntry="${institutionalProposalCostShareAttributes.costSharePercentage}" styleClass="amount"/> 
					</div>
					</td>
	                <td width="15%" valign="middle">                	
					<div align="center">
                  		<kul:htmlControlAttribute property="document.institutionalProposal.institutionalProposalCostShares[${status.index}].sourceAccount" attributeEntry="${institutionalProposalCostShareAttributes.sourceAccount}"/> 
					</div>
					</td>
	                <td width="15%" valign="middle">
					<div align="center">
	                	<kul:htmlControlAttribute property="document.institutionalProposal.institutionalProposalCostShares[${status.index}].amount" attributeEntry="${institutionalProposalCostShareAttributes.amount}" styleClass="amount"/>
					</div>
	                </td>
					<td width="10%">
					<div align="center">&nbsp;
						<html:image property="methodToCall.deleteCostShare.line${status.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					</div>
	                </td>
	            </tr>
        	</c:forEach> 
          	<tr>
          		<th colspan="5" align="right" scope="row">Total:</th>
	         	<th align="right">
          			<div align="center">  		                		
	                	$<fmt:formatNumber value="${KualiForm.document.institutionalProposal.totalCostShareAmount}" type="currency" currencySymbol="" maxFractionDigits="2" />
	                </div>
	         	</th>
	         	<th scope="row">&nbsp;</th>
          	</tr>
          	
        </table>
   </div>
   <div class="tab-container" align="center">
		<html:image property="methodToCall.recalculateCostShareTotal.anchor${tabKey}"
		src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' styleClass="tinybutton"/>
   </div>           
</kul:tab>

