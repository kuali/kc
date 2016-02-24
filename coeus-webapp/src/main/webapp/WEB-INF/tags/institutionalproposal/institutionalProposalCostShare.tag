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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="institutionalProposalAttributes" value="${DataDictionary.InstitutionalProposal.attributes}" />
<c:set var="institutionalProposalCostShareAttributes" value="${DataDictionary.InstitutionalProposalCostShare.attributes}" />
<c:set var="institutionalProposalCommentAttributes" value="${DataDictionary.InstitutionalProposalComment.attributes}" />
<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}" scope="request" />
<c:set var="action" value="institutionalProposalDistribution" />

<c:set var="tabItemCount" value="0" />
<c:forEach var="institutionalProposalCostShare" items="${KualiForm.document.institutionalProposal.institutionalProposalCostShares}" varStatus="status">               
        <c:set var="tabItemCount" value="${tabItemCount+1}" />
</c:forEach>
<div id="workarea">
<kul:tab tabItemCount="${tabItemCount}" tabTitle="Cost Sharing" defaultOpen="false" transparentBackground="true" tabErrorKey="institutionalProposalCostShareBean.newInstitutionalProposalCostShare.*,document.institutionalProposalList[0].institutionalProposalCostShares*">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Cost Share</span>
   			<span class="subhead-right">
   				<kul:help parameterNamespace="KC-IP" parameterDetailType="Document" parameterName="ipDistCostSharingHelp" altText="help"/>
   			</span>
        </h3>
        <table id="cost-share-table" cellpadding="0" cellspacing="0" summary="Cost Share">
			<tr>
				<th scope="row">&nbsp;</th>
				<th>
					* ${KualiForm.institutionalProposalCostShareBean.projectPeriodLabel }
					<%--<kul:htmlAttributeLabel attributeEntry="${institutionalProposalCostShareAttributes.projectPeriod}" useShortLabel="true" noColon="true" />--%>
				</th>
				
				<th><kul:htmlAttributeLabel attributeEntry="${institutionalProposalCostShareAttributes.costShareTypeCode}" useShortLabel="true" noColon="true" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${institutionalProposalCostShareAttributes.costSharePercentage}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${institutionalProposalCostShareAttributes.sourceAccount}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${institutionalProposalCostShareAttributes.amount}" useShortLabel="true" noColon="true"/></th>
				<c:if test="${!readOnly}"><th><div align="center">Actions</div></th></c:if>
			</tr>
			
			<tr>
            	<th width="50" align="center" scope="row"><div align="center">Add:</div></th>
            	<td class="infoline">
            	  	<div align="center">
            	  	 	<kul:htmlControlAttribute property="institutionalProposalCostShareBean.newInstitutionalProposalCostShare.projectPeriod" attributeEntry="${institutionalProposalCostShareAttributes.projectPeriod}"/>
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
	            <c:if test="${!readOnly}">
	               <td class="infoline">
	            	  <div align=center>
						  <html:image property="methodToCall.addCostShare.anchor${tabKey}"
						  src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					   </div>
	               </td>
	            </c:if>
          	</tr>
          	
         <c:forEach var="institutionalProposalCostShares" items="${KualiForm.document.institutionalProposal.institutionalProposalCostShares}" varStatus="status">
	             <tr>
					<th width="5%" class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td width="10%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.institutionalProposalList[0].institutionalProposalCostShares[${status.index}].projectPeriod" attributeEntry="${institutionalProposalCostShareAttributes.projectPeriod}"/>
					</div>
					</td>
	                <td width="20%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.institutionalProposalList[0].institutionalProposalCostShares[${status.index}].costShareTypeCode" attributeEntry="${institutionalProposalCostShareAttributes.costShareTypeCode}"/>
					</div>
	                </td>
	                <td width="15%" valign="middle">                	
					<div align="center">
                  		<kul:htmlControlAttribute property="document.institutionalProposalList[0].institutionalProposalCostShares[${status.index}].costSharePercentage" attributeEntry="${institutionalProposalCostShareAttributes.costSharePercentage}" styleClass="amount"/> 
					</div>
					</td>
	                <td width="15%" valign="middle">                	
					<div align="center">
                  		<kul:htmlControlAttribute property="document.institutionalProposalList[0].institutionalProposalCostShares[${status.index}].sourceAccount" attributeEntry="${institutionalProposalCostShareAttributes.sourceAccount}"/> 
					</div>
					</td>
	                <td width="15%" valign="middle">
					<div align="center">
	                	<kul:htmlControlAttribute property="document.institutionalProposalList[0].institutionalProposalCostShares[${status.index}].amount" attributeEntry="${institutionalProposalCostShareAttributes.amount}" styleClass="amount"/>
					</div>
	                </td>
	                <c:if test="${!readOnly}">
					<td width="10%">
					   <div align="center">&nbsp;
						  <html:image property="methodToCall.deleteCostShare.line${status.index}.anchor${currentTabIndex}"
						  src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					   </div>
	                </td>
	                </c:if>
	            </tr>
        	</c:forEach> 
          	<tr>
          		<th colspan="5" align="right" scope="row">Total:</th>
	         	<th align="right">
          			<div align="center">  		                		
	                	$<fmt:formatNumber value="${KualiForm.document.institutionalProposal.totalCostShareAmount}" type="currency" currencySymbol="" maxFractionDigits="2" />
	                </div>
	         	</th>
	         	<c:if test="${!readOnly}"><th scope="row">&nbsp;</th></c:if>
          	</tr>
          	
        </table>
   </div>
   <c:if test="${!readOnly}">
   <div class="tab-container" align="center">
		<html:image property="methodToCall.recalculateCostShareTotal.anchor${tabKey}"
		src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' styleClass="tinybutton"/>
   </div>           
   </c:if>
   <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left">Cost Share Comments</span>
        </h3>
        <table>
            <th width="100" align="right" scope="row"><div align="center">Add:</div></th>
            <td class="infoline">
                <div align="left">
                    <kul:htmlControlAttribute property="document.institutionalProposalList[0].costShareComment.comments" attributeEntry="${institutionalProposalCommentAttributes.comments}"/>
                </div>
            </td>
        </table>
    </div>
</kul:tab>
