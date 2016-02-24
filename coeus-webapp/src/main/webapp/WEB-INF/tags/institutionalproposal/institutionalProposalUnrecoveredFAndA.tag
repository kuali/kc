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
<c:set var="institutionalProposalUnrecoveredFandAAttributes" value="${DataDictionary.InstitutionalProposalUnrecoveredFandA.attributes}" />
<c:set var="institutionalProposalCommentAttributes" value="${DataDictionary.InstitutionalProposalComment.attributes}" />
<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}" scope="request" />
<c:set var="tabItemCount" value="0" />
<c:set var="action" value="institutionalProposalDistribution" />

<c:forEach var="institutionalProposalUnrecoveredFandA" items="${KualiForm.document.institutionalProposal.institutionalProposalUnrecoveredFandAs}" varStatus="status">               
        <c:set var="tabItemCount" value="${tabItemCount+1}" />
</c:forEach>

<kul:tab tabItemCount="${tabItemCount}" tabTitle="Unrecovered F&A" defaultOpen="false" tabErrorKey="newInstitutionalProposalUnrecoveredFandA.*,institutionalProposalUnrecoveredFandABean.newInstitutionalProposalUnrecoveredFandA.*,document.institutionalProposal.institutionalProposalUnrecoveredFandA*">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Unrecovered F&A Distribution List</span>
   			<span class="subhead-right">
   				<kul:help parameterNamespace="KC-IP" parameterDetailType="Document" parameterName="ipDistUnrecoveredFandAHelp" altText="help"/>
			</span>
        </h3>
        <table id="unrecovered-fanda-table" cellpadding="0" cellspacing="0" summary="Unrecovered F&A">
			<tr>
				<th scope="row">&nbsp;</th>
				<th><kul:htmlAttributeLabel attributeEntry="${institutionalProposalUnrecoveredFandAAttributes.fiscalYear}" useShortLabel="true" noColon="true" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${institutionalProposalUnrecoveredFandAAttributes.indirectcostRateTypeCode}" useShortLabel="true" noColon="true" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${institutionalProposalUnrecoveredFandAAttributes.applicableIndirectcostRate}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${institutionalProposalUnrecoveredFandAAttributes.onCampusFlag}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${institutionalProposalUnrecoveredFandAAttributes.sourceAccount}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${institutionalProposalUnrecoveredFandAAttributes.underrecoveryOfIndirectcost}" useShortLabel="true" noColon="true"/></th>
				
				<c:if test="${!readOnly}"><th><div align="center">Actions</div></th></c:if>
			</tr>
			<tr>
            	<th width="50" align="center" scope="row"><div align="center">Add:</div></th>
            	<td class="infoline">
            	  	<div align="center">
            	  	 	<kul:htmlControlAttribute property="institutionalProposalUnrecoveredFandABean.newInstitutionalProposalUnrecoveredFandA.fiscalYear" attributeEntry="${institutionalProposalUnrecoveredFandAAttributes.fiscalYear}"/>
            	 	</div>
            	</td>
	            <td class="infoline">
	              	<div width="75" align="center">
	            		<kul:htmlControlAttribute property="institutionalProposalUnrecoveredFandABean.newInstitutionalProposalUnrecoveredFandA.indirectcostRateTypeCode" attributeEntry="${institutionalProposalUnrecoveredFandAAttributes.indirectcostRateTypeCode}" />
	              	</div>
	            </td>
	            <td class="infoline">
	            	<div align="center">
            	    	<kul:htmlControlAttribute property="institutionalProposalUnrecoveredFandABean.newInstitutionalProposalUnrecoveredFandA.applicableIndirectcostRate" attributeEntry="${institutionalProposalUnrecoveredFandAAttributes.applicableIndirectcostRate}" styleClass="amount"/>
            	  	</div>
	            </td>
	            <td class="infoline">
	            	<div align="center">
            	    	<kul:htmlControlAttribute property="institutionalProposalUnrecoveredFandABean.newInstitutionalProposalUnrecoveredFandA.onCampusFlag" attributeEntry="${institutionalProposalUnrecoveredFandAAttributes.onCampusFlag}"/>
            	  	</div>
	            </td>
	            <td class="infoline">
	            	<div align="center">
            	   	 	<kul:htmlControlAttribute property="institutionalProposalUnrecoveredFandABean.newInstitutionalProposalUnrecoveredFandA.sourceAccount" attributeEntry="${institutionalProposalUnrecoveredFandAAttributes.sourceAccount}"/>
            	  	</div>
	            </td>
	            <td class="infoline">
	            	<div align="center">
            	   	 	<kul:htmlControlAttribute property="institutionalProposalUnrecoveredFandABean.newInstitutionalProposalUnrecoveredFandA.underrecoveryOfIndirectcost" attributeEntry="${institutionalProposalUnrecoveredFandAAttributes.underrecoveryOfIndirectcost}" styleClass="amount"/>
            	  	</div>
	            </td>
	            <c:if test="${!readOnly}">
	            <td class="infoline">
	            	<div align=center>
						<html:image property="methodToCall.addUnrecoveredFandA.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
	            </td>
	            </c:if>
          	</tr>
         <c:forEach var="institutionalProposalUnrecoveredFandAs" items="${KualiForm.document.institutionalProposal.institutionalProposalUnrecoveredFandAs}" varStatus="status">
	             <tr>
					<th width="5%" class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td width="10%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.institutionalProposal.institutionalProposalUnrecoveredFandAs[${status.index}].fiscalYear" attributeEntry="${institutionalProposalUnrecoveredFandAAttributes.fiscalYear}"/>
					</div>
					</td>
	                <td width="20%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.institutionalProposal.institutionalProposalUnrecoveredFandAs[${status.index}].indirectcostRateTypeCode" attributeEntry="${institutionalProposalUnrecoveredFandAAttributes.indirectcostRateTypeCode}"/>
					</div>
	                </td>
	                <td width="15%" valign="middle">                	
					<div align="center">
                  		<kul:htmlControlAttribute property="document.institutionalProposal.institutionalProposalUnrecoveredFandAs[${status.index}].applicableIndirectcostRate" attributeEntry="${institutionalProposalUnrecoveredFandAAttributes.applicableIndirectcostRate}" styleClass="amount"/> 
					</div>
					</td>
	                <td width="15%" valign="middle">                	
					<div align="center">
                  		<kul:htmlControlAttribute property="document.institutionalProposal.institutionalProposalUnrecoveredFandAs[${status.index}].onCampusFlag" attributeEntry="${institutionalProposalUnrecoveredFandAAttributes.onCampusFlag}"/> 
					</div>
					</td>
	                <td width="15%" valign="middle">
					<div align="center">
	                	<kul:htmlControlAttribute property="document.institutionalProposal.institutionalProposalUnrecoveredFandAs[${status.index}].sourceAccount" attributeEntry="${institutionalProposalUnrecoveredFandAAttributes.sourceAccount}"/>
					</div>
	                </td>
	                <td width="15%" valign="middle">
					<div align="center">
	                	<kul:htmlControlAttribute property="document.institutionalProposal.institutionalProposalUnrecoveredFandAs[${status.index}].underrecoveryOfIndirectcost" attributeEntry="${institutionalProposalUnrecoveredFandAAttributes.underrecoveryOfIndirectcost}" styleClass="amount"/>
					</div>
	                </td>
	                <c:if test="${!readOnly}">
					<td width="10%">
					<div align="center">&nbsp;
						<html:image property="methodToCall.deleteUnrecoveredFandA.line${status.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					</div>
	                </td>
	                </c:if>
	            </tr>
        	</c:forEach> 
          	<tr>
          		<th colspan="6" align="right" scope="row">Total:</th>
	         	<th align="right">
          			<div align="center">  		                		
	                	$<fmt:formatNumber value="${KualiForm.document.institutionalProposal.totalUnrecoveredFandAAmount}" type="currency" currencySymbol="" maxFractionDigits="2" />
	                </div>
	         	</th>
	         	<c:if test="${!readOnly}"><th scope="row">&nbsp;</th></c:if>
          	</tr>
          	
        </table>
   </div>
   <c:if test="${!readOnly}">
   <div class="tab-container" align="center">
		<html:image property="methodToCall.recalculateUnrecoveredFandATotal.anchor${tabKey}"
		src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' styleClass="tinybutton"/>
   </div>
   </c:if>
   <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left">Unrecovered F&A Comments</span>
        </h3>
        <table>
            <th width="100" align="right" scope="row"><div align="center">Add:</div></th>
            <td class="infoline">
                <div align="left">
                    <kul:htmlControlAttribute property="document.institutionalProposalList[0].unrecoveredFandAComment.comments" attributeEntry="${institutionalProposalCommentAttributes.comments}"/>
                </div>
            </td>
        </table>
    </div>
</kul:tab>
